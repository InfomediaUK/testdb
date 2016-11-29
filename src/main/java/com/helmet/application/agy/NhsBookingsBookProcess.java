package com.helmet.application.agy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.AlreadyLockedRuntimeException;
import com.helmet.application.BookingLockHandler;
import com.helmet.application.NotLockedRuntimeException;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Address;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Expense;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.NhsBookingApplicantIds;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Shift;
import com.helmet.bean.SiteUser;
import com.helmet.bean.Uplift;

public class NhsBookingsBookProcess extends AgyAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    SiteUser siteUser                     = (SiteUser)dynaForm.get("siteUser"); 
    ClientUser clientUser                 = (ClientUser)dynaForm.get("clientUser");
    LocationUser locationUser             = (LocationUser)dynaForm.get("locationUser"); 
    JobProfileUser jobProfileUser         = (JobProfileUser)dynaForm.get("jobProfileUser"); 
    Integer clientAgencyJobProfileGradeId = (Integer)dynaForm.get("clientAgencyJobProfileGradeId");
    BigDecimal hourlyRate                 = (BigDecimal)dynaForm.get("hourlyRate");
    List<NhsBookingApplicantIds> listNhsBookingApplicantIds = new ArrayList<NhsBookingApplicantIds>();
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    clientUser = agyService.getClientUser(clientUser.getClientId());
    siteUser = agyService.getSiteUser(siteUser.getSiteId());
    locationUser = agyService.getLocationUser(locationUser.getLocationId());
    jobProfileUser = agyService.getJobProfileUser(jobProfileUser.getJobProfileId());
    ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser = agyService.getClientAgencyJobProfileGradeUser(clientAgencyJobProfileGradeId);    
    ClientAgencyJobProfileGradeUser[] bookingGrades = new ClientAgencyJobProfileGradeUser[1];
    Expense[] bookingExpenses = new Expense[0];
    bookingGrades[0] = clientAgencyJobProfileGradeUser;
    List<ReasonForRequest> listReasonForRequest = agyService.getReasonForRequestsForClient(clientUser.getClientId());
    ReasonForRequest reasonForRequest = listReasonForRequest.get(0);
    NhsBooking nhsBooking = null;
    Booking newBooking = null;
    BookingDate bookingDate = null;
    BookingDate[] bookingDates = null;
    Shift shift = null;
    List<PublicHoliday> listPublicHoliday = agyService.getPublicHolidaysForClient(clientUser.getClientId());
    List<Uplift> listUplift = agyService.getUpliftsForClient(clientUser.getClientId());
    Enumeration paramNames = request.getParameterNames();
    int rowCount = 0;
    StringBuffer bookingIds = new StringBuffer();
    NhsBookingApplicantIds nhsBookingApplicantIds = null;
    while(paramNames.hasMoreElements()) 
    {
      String paramName = (String)paramNames.nextElement();
      if (paramName.equals("nhsBookingId"))
      {
        String[] paramValues = request.getParameterValues(paramName);
        for(String param : paramValues) 
        {
          // For each NHS Booking to be booked...
          nhsBooking = agyService.getNhsBooking(Integer.parseInt(param));
          if (nhsBooking != null)
          {
            shift = agyService.getShift(nhsBooking.getShiftId());
            bookingDates = new BookingDate[1];
            bookingDate = loadBookingDate(nhsBooking, shift);
            bookingDates[0] = bookingDate;
            newBooking = loadBooking(nhsBooking, clientUser, locationUser, jobProfileUser, clientAgencyJobProfileGradeUser, reasonForRequest, shift, listPublicHoliday, listUplift, bookingDates, dynaForm);
            rowCount += agyService.insertBooking(newBooking, bookingDates, bookingGrades, bookingExpenses, getConsultantLoggedIn().getConsultantId());
            if (bookingIds.length() > 0)
            {
              bookingIds.append(",");
            }
            bookingIds.append(newBooking.getBookingId());
            nhsBooking.setBookingId(newBooking.getBookingId());
            agyService.updateNhsBooking(nhsBooking, getConsultantLoggedIn().getConsultantId());
            nhsBookingApplicantIds = new NhsBookingApplicantIds(nhsBooking.getNhsBookingId(), nhsBooking.getApplicantId(), newBooking.getBookingId(), bookingDate.getBookingDateId(), clientAgencyJobProfileGradeId);
            listNhsBookingApplicantIds.add(nhsBookingApplicantIds);
          }
        }
      }
    }
    dynaForm.set("bookingIds", bookingIds.toString());
    dynaForm.set("listNhsBookingApplicantIds", listNhsBookingApplicantIds);
    logger.debug("Out going !!! - Row Count = " + rowCount);
    ActionForward actionForward = mapping.findForward("success");
    // Removed  + "?bookingIds=" + bookingIds.toString()
    return new ActionForward(actionForward.getName(), actionForward.getPath(), actionForward.getRedirect());
  }
 
  private Booking loadBooking(NhsBooking nhsBooking, ClientUser clientUser, LocationUser locationUser, JobProfileUser jobProfileUser, ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser, ReasonForRequest reasonForRequest, Shift shift, List<PublicHoliday> listPublicHoliday, List<Uplift> listUplift, BookingDate[] bookingDates, DynaValidatorForm dynaForm)
  {
    Booking booking = new Booking();
    booking.setReasonForRequestId(reasonForRequest.getReasonForRequestId());
    booking.setLocationId(locationUser.getLocationId());
    booking.setJobProfileId(jobProfileUser.getJobProfileId());
    booking.setShiftId(shift.getShiftId());
    booking.setBookingReference(nhsBooking.getBankReqNum());
    booking.setContactName((String)dynaForm.get("contactName"));
    booking.setContactJobTitle((String)dynaForm.get("contactJobTitle"));
    booking.setContactEmailAddress((String)dynaForm.get("contactEmailAddress"));
    booking.setContactTelephoneNumber((String)dynaForm.get("contactTelephoneNumber"));
    booking.setAccountContactName((String)dynaForm.get("accountContactName"));
    booking.setAccountContactAddress((Address)dynaForm.get("accountContactAddress"));
    booking.setAccountContactEmailAddress((String)dynaForm.get("accountContactEmailAddress"));
    booking.setSingleCandidate(true);
    booking.setAutoFill(true);
    booking.setAutoActivate(true);
    BigDecimal hourlyRate = (BigDecimal)dynaForm.get("hourlyRate");
    BigDecimal noOfHours = shift.getNoOfHours();
    booking.setRate(hourlyRate);
    booking.setNoOfHours(noOfHours);
// See OrderStaff7.java line 121.
    BigDecimal value = Utilities.calculateIt(bookingDates, clientAgencyJobProfileGradeUser.getRate(), listPublicHoliday, listUplift, true);    
    booking.setValue(value);
    clientAgencyJobProfileGradeUser.setValue(booking.getValue()); // NOT SURE THIS IS RIGHT...
    return booking;
  }
  
  private BookingDate loadBookingDate(NhsBooking nhsBooking, Shift shift)
  {
    BookingDate bookingDate = new BookingDate();
    bookingDate.setBookingDate(nhsBooking.getDate());
    bookingDate.setShiftId(shift.getShiftId());
    bookingDate.setShiftName(shift.getName());
    bookingDate.setShiftCode(shift.getCode());
    bookingDate.setShiftStartTime(shift.getStartTime());
    bookingDate.setShiftEndTime(shift.getEndTime());
    bookingDate.setShiftBreakStartTime(shift.getBreakStartTime());
    bookingDate.setShiftBreakEndTime(shift.getBreakEndTime());
    bookingDate.setShiftNoOfHours(shift.getNoOfHours());
    bookingDate.setShiftBreakNoOfHours(shift.getBreakNoOfHours());
    bookingDate.setShiftUseUplift(shift.getUseShiftUplift());
    bookingDate.setShiftUpliftFactor(shift.getUpliftFactor());
    bookingDate.setShiftUpliftValue(shift.getUpliftValue());
    bookingDate.setShiftOvertimeNoOfHours(shift.getOvertimeNoOfHours());
    bookingDate.setShiftOvertimeUpliftFactor(shift.getOvertimeUpliftFactor());
    return bookingDate;
  }
  
  private boolean lockBooking(Integer bookingId)
  {
    logger.debug("About to lock - " + bookingId + " from " + getClass().getName());
    try
    {
      BookingLockHandler.getInstance().addLock(bookingId);
    }
    catch (AlreadyLockedRuntimeException e)
    {
      logger.warn("***** Already locked - " + bookingId);
      return false;
    }
    return true;
  }

  private boolean unlockBooking(Integer bookingId)
  {
    logger.debug("About to unlock - " + bookingId);
    try
    {
      BookingLockHandler.getInstance().removeLock(bookingId);
    }
    catch (NotLockedRuntimeException e)
    {
      logger.error("Error trying to remove lock on bookingId - " + bookingId);
      return false;
    }
    return true;
  }

}
