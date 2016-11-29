package com.helmet.application.agy;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.helmet.bean.Applicant;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.NhsBookingUser;
import com.helmet.bean.Site;

public class NhsBookingsSubcontractInvoice extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Date startOfWeekDate = null;
    Date endOfWeekDate = null;
    Calendar calendar = Calendar.getInstance();
    Date todaysDate = new Date(calendar.getTimeInMillis());
    // Showing weekly so determine which week to show 0=current, -1=last week, 1=next week
    Integer weekToShow = getWeekToShow(request, dynaForm);
    startOfWeekDate = Utilities.getStartOfWeek(weekToShow);
    endOfWeekDate = Utilities.getEndOfWeek(weekToShow);
    Integer clientId = (Integer)dynaForm.get("clientId");
    Integer siteId = (Integer)dynaForm.get("siteId");
    Integer locationId = (Integer)dynaForm.get("locationId");
    Integer jobProfileId = (Integer)dynaForm.get("jobProfileId");
    ClientUser client = agyService.getClientUser(clientId);
    Site site = agyService.getSite(siteId);
    Location location = agyService.getLocation(locationId);
    JobProfileUser jobProfile = agyService.getJobProfileUser(jobProfileId);
    NhsBookingReportGroup nhsBookingReportGroup = new NhsBookingReportGroup(client, site, location, jobProfile);
    AgencyUser fromAgency = null;
    // Fill list with NHSBookings for the week and NHS Booking Report Group.
    List<NhsBookingUser> listNhsBookings = agyService.getNhsBookingUsersForAgencyDateRangeNhsBookingReportGroup(getConsultantLoggedIn().getAgencyId(), startOfWeekDate, endOfWeekDate, nhsBookingReportGroup);
    // Create an empty list of NhsBookingReportGroups.
    if (listNhsBookings.size() > 0) 
    {
      // Found NHSBookings for the week.
      Applicant applicant = null;
      Integer fromAgencyId = null;
      for (NhsBookingUser nhsBookingUser : listNhsBookings)
      {
        // For each NhsBookingUser... 
        if (nhsBookingUser.getActive())
        {
          // NHS Booking IS Active. 
          if (nhsBookingUser.getBookingId() != null)
          {
            // NHS Booking has actual Booking.
            if (nhsBookingUser.getSubcontractInvoiceId() <= 0)
            {
              // NHS Booking is NOT on a Subcontract Invoice (yet) or has been credited.
              if (nhsBookingUser.getValue().compareTo(new BigDecimal(0)) > 0)
              {
                // NHS Booking has a Value. So it can become an invoice line.
                if (nhsBookingUser.getWorkedBreakNoOfHours() != null)
                {
                  // Actual Worked Hours has been entered for the shift (BookingDate).
                  applicant = agyService.getApplicant(nhsBookingUser.getApplicantId());
                  if (!applicant.getOriginalAgencyId().equals(0))
                  {
                    // Applicant is Subcontracted.
                    fromAgencyId = applicant.getOriginalAgencyId();
                    nhsBookingReportGroup.addNhsBookingUserToList(nhsBookingUser);
                  }
                }
              }
            }
          }
        }        
      }
      fromAgency = agyService.getAgencyUser(fromAgencyId);
    }    
    dynaForm.set("fromAgency", fromAgency);
    dynaForm.set("invoiceDateStr", sdf.format(todaysDate));
    dynaForm.set("weekToShow", weekToShow);
    dynaForm.set("startDate", startOfWeekDate);
    dynaForm.set("endDate", endOfWeekDate);
    dynaForm.set("nhsBookingReportGroup", nhsBookingReportGroup);
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
