package com.helmet.application.agy;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.NhsBookingReportGroup;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.NhsBookingUser;
import com.helmet.bean.Site;

public class NhsBookingsSubcontractInvoiceConfirm extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Date startOfWeekDate = null;
    Date endOfWeekDate = null;
    AgencyUser fromAgency = (AgencyUser)dynaForm.get("fromAgency");;
    String invoiceDateStr = (String)dynaForm.get("invoiceDateStr");
    Date invoiceDate =  convertDate(invoiceDateStr, sdf, errors, messageResources, "label.invoiceDate");
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    // Showing weekly so determine which week to show 0=current, -1=last week, 1=next week
    Integer weekToShow = getWeekToShow(request, dynaForm);
    startOfWeekDate = Utilities.getStartOfWeek(weekToShow);
    endOfWeekDate = Utilities.getEndOfWeek(weekToShow);
    Integer clientId = (Integer)dynaForm.get("clientId");
    Integer siteId = (Integer)dynaForm.get("siteId");
    Integer locationId = (Integer)dynaForm.get("locationId");
    Integer jobProfileId = (Integer)dynaForm.get("jobProfileId");
    String subcontractInvoiceNotes = (String)dynaForm.get("subcontractInvoiceNotes");
    fromAgency = agyService.getAgencyUser(fromAgency.getAgencyId());
    ClientUser client = agyService.getClientUser(clientId);
    Site site = agyService.getSite(siteId);
    Location location = agyService.getLocation(locationId);
    JobProfileUser jobProfile = agyService.getJobProfileUser(jobProfileId);
    Enumeration paramNames = request.getParameterNames();
    List<Integer> nhsBookingIdList = new ArrayList<Integer>();
    while(paramNames.hasMoreElements()) 
    {
      String paramName = (String)paramNames.nextElement();
      if (paramName.equals("nhsBookingId"))
      {
        String[] paramValues = request.getParameterValues(paramName);
        for(String param : paramValues) 
        {
          // For each NHS Booking to be booked...
          nhsBookingIdList.add(new Integer(Integer.parseInt(param)));
        }
      }
    }
    NhsBookingReportGroup nhsBookingReportGroup = new NhsBookingReportGroup(client, site, location, jobProfile);

    // Fill list with NHSBookings for the week and NHS Booking Report Group.
    List<NhsBookingUser> listNhsBookings = agyService.getNhsBookingUsersForAgencyDateRangeNhsBookingReportGroup(getConsultantLoggedIn().getAgencyId(), startOfWeekDate, endOfWeekDate, nhsBookingReportGroup);
    // Create an empty list of NhsBookingReportGroups.
    if (listNhsBookings.size() > 0) 
    {
      // Found NHSBookings for the week. 
      for (NhsBookingUser nhsBookingUser : listNhsBookings)
      {
        // For each NhsBookingUser... 
        if (nhsBookingIsRequired(nhsBookingIdList, nhsBookingUser.getNhsBookingId()))
        {
          // NHS Booking is required to become an invoice line.
          nhsBookingReportGroup.addNhsBookingUserToList(nhsBookingUser);
        }
      }
    }    
    dynaForm.set("fromAgency", fromAgency);
    dynaForm.set("invoiceDateStr", invoiceDateStr);
    dynaForm.set("weekToShow", weekToShow);
    dynaForm.set("startDate", startOfWeekDate);
    dynaForm.set("endDate", endOfWeekDate);
    dynaForm.set("nhsBookingReportGroup", nhsBookingReportGroup);
    dynaForm.set("subcontractInvoiceNotes", subcontractInvoiceNotes);
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

  private Boolean nhsBookingIsRequired(List<Integer> nhsBookingIdList, Integer nhsBookingId)
  {
    for (Integer requiredNhsBookingId : nhsBookingIdList)
    {
      if (nhsBookingId.equals(requiredNhsBookingId))
      {
        return true;
      }
    }
    return false;
  }
}
