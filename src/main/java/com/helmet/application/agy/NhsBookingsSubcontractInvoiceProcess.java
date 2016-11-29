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
import com.helmet.application.PdfHandler;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.NhsBookingUser;
import com.helmet.bean.Site;
import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceItemUser;
import com.helmet.bean.SubcontractInvoiceUser;

public class NhsBookingsSubcontractInvoiceProcess extends AgyAction
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
    AgencyUser toAgency = null;
    AgencyUser fromAgency = (AgencyUser)dynaForm.get("fromAgency");
    String invoiceDateStr = (String)dynaForm.get("invoiceDateStr");
    Date invoiceDate =  convertDate(invoiceDateStr, sdf, errors, messageResources, "label.invoiceDate");
    // Showing weekly so determine which week to show 0=current, -1=last week, 1=next week
    Integer weekToShow = getWeekToShow(request, dynaForm);
    startOfWeekDate = Utilities.getStartOfWeek(weekToShow);
    endOfWeekDate = Utilities.getEndOfWeek(weekToShow);
    toAgency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());
    fromAgency = agyService.getAgencyUser(fromAgency.getAgencyId());
    Integer clientId = (Integer)dynaForm.get("clientId");
    Integer siteId = (Integer)dynaForm.get("siteId");
    Integer locationId = (Integer)dynaForm.get("locationId");
    Integer jobProfileId = (Integer)dynaForm.get("jobProfileId");
    String subcontractInvoiceNotes = (String)dynaForm.get("subcontractInvoiceNotes");
    ClientUser client = agyService.getClientUser(clientId);
    Site site = agyService.getSite(siteId);
    Location location = agyService.getLocation(locationId);
    JobProfileUser jobProfile = agyService.getJobProfileUser(jobProfileId);
    SubcontractInvoiceUser subcontractInvoiceUser = null;
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
          // NHS Booking has a Value. So it can become an invoice line.
          nhsBookingReportGroup.addNhsBookingUserToList(nhsBookingUser);
        }
      }
      subcontractInvoiceUser = new SubcontractInvoiceUser();
      subcontractInvoiceUser.setToAgencyId(toAgency.getAgencyId());
      subcontractInvoiceUser.setFromAgencyId(fromAgency.getAgencyId());
      subcontractInvoiceUser.setDate(invoiceDate);
      subcontractInvoiceUser.setClientId(clientId);
      subcontractInvoiceUser.setClientNhsName(client.getNhsName());
      subcontractInvoiceUser.setSiteId(siteId);
      subcontractInvoiceUser.setSiteNhsLocation(site.getNhsLocation());
      subcontractInvoiceUser.setLocationId(locationId);
      subcontractInvoiceUser.setLocationNhsWard(location.getNhsWard());
      subcontractInvoiceUser.setJobProfileId(jobProfileId);
      subcontractInvoiceUser.setJobProfileName(jobProfile.getName());
      subcontractInvoiceUser.setJobFamilyCode(jobProfile.getJobFamilyCode());
      subcontractInvoiceUser.setJobSubFamilyCode(jobProfile.getJobSubFamilyCode());
      subcontractInvoiceUser.setJobProfileNhsAssignment(jobProfile.getNhsAssignment());
      subcontractInvoiceUser.setStartDate(startOfWeekDate);
      subcontractInvoiceUser.setEndDate(endOfWeekDate);
      subcontractInvoiceUser.setValue(nhsBookingReportGroup.getNhsBookingsToInvoiceValue());
      subcontractInvoiceUser.setNotes(subcontractInvoiceNotes);
      //
      SubcontractInvoiceItemUser subcontractInvoiceItemUser = null;
      int rowCount = agyService.insertSubcontractInvoice(subcontractInvoiceUser, getConsultantLoggedIn().getConsultantId());
      if (rowCount == 1)
      {
        //
        List<SubcontractInvoiceItemUser> listSubcontractInvoiceItemUser = new ArrayList<SubcontractInvoiceItemUser>();
        for (NhsBookingUser nhsBookingUser : nhsBookingReportGroup.getListNhsBookingUser())
        {
          // For each NHS Booking 'on the invoice'...
          subcontractInvoiceItemUser = new SubcontractInvoiceItemUser(subcontractInvoiceUser.getSubcontractInvoiceId(), nhsBookingUser);
          rowCount += agyService.insertSubcontractInvoiceItem(subcontractInvoiceItemUser, getConsultantLoggedIn().getConsultantId());
          listSubcontractInvoiceItemUser.add(subcontractInvoiceItemUser);
          nhsBookingUser.setSubcontractInvoiceId(subcontractInvoiceUser.getSubcontractInvoiceId());
          rowCount += agyService.updateNhsBookingSubcontractInvoiceId(nhsBookingUser, getConsultantLoggedIn().getConsultantId());
        }
        // Put the SubcontractInvoiceItems into the SubcontractInvoice.
        subcontractInvoiceUser.setListSubcontractInvoiceItemUser(listSubcontractInvoiceItemUser);
        if (rowCount == (subcontractInvoiceUser.getNoOfNhsBookings() * 2) + 1)
        {
          // The SubcontractInvoice and SubcontractInvoiceItems have been inserted and the NHS Bookings have been updated.
          try
          {
            String serverName = request.getServerName();
            PdfHandler.getInstance().writeSubcontractInvoice(messageResources, toAgency, fromAgency, subcontractInvoiceUser, serverName);
          }
          catch (Exception e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }        
      }      
    }    
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?subcontractInvoiceUser.subcontractInvoiceId=" + subcontractInvoiceUser.getSubcontractInvoiceId(), actionForward.getRedirect());
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
