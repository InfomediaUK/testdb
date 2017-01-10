package com.helmet.application.agy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.application.comparator.ApplicantWorkingComparator;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.Unavailable;

public class ApplicantWorkingList extends AgyAction
{
  private static BigDecimal MILLISECONDS_IN_HOUR = new BigDecimal(60 * 60 * 1000);
  private static Long MILLISECONDS_IN_DAY = new Long(60 * 60 * 24 * 1000);
  private static BigDecimal PERIOD = new BigDecimal(6);
  private static BigDecimal MILLISECONDS_IN_PERIOD = MILLISECONDS_IN_HOUR.multiply(PERIOD);

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    Calendar calendar = Calendar.getInstance();  // TimeZone.getTimeZone("GMT")
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    Boolean showAll = (Boolean) dynaForm.get("showAll");
    Integer weekToShow = (Integer)dynaForm.get("weekToShow");
    Date startDate = Utilities.getStartOfWeek(weekToShow - 1);
    Date endDate = Utilities.getEndOfWeek(weekToShow + 1);
    List<Date> listDate = new ArrayList<Date>();
    Date day = startDate;
    // Build list of Dates.
    while (!day.after(endDate))
    {
      listDate.add(day);
      day = new Date(day.getTime() + MILLISECONDS_IN_DAY);
    }
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    // Get all the Applicants for the Agency.
    List<Applicant> listApplicant = agyService.getApplicantsForAgency(getConsultantLoggedIn().getAgencyId());
    // Get all the BookingDateUserApplicants for the Date Range.
    List<BookingDateUserApplicant> listBookingDateUserApplicant = agyService.getBookingDateUserApplicantsForAgencyAndDateRange(getConsultantLoggedIn().getAgencyId(), startDate, endDate);
    // Get all the BookingDateUserApplicants for the Date Range.
    List<Unavailable> listUnavailable = agyService.getActiveUnavailablesInDateRange(getConsultantLoggedIn().getAgencyId(), startDate, endDate);
    // Map all ApplicantWorking objects by ApplicantId to allow fast access.
    Map<Integer, ApplicantWorking> mapApplicantWorking = new HashMap<Integer, ApplicantWorking>();
    List<ApplicantWorking> listApplicantWorking = new ArrayList<ApplicantWorking>();
    ApplicantWorking applicantWorking = null;
    // Build ApplicantWorking List and Map.
    for (Applicant applicant : listApplicant)
    {
      applicantWorking = new ApplicantWorking(applicant.getApplicantId(), applicant.getAgencyId(), applicant.getFirstName(), applicant.getLastName(), startDate, endDate);
      listApplicantWorking.add(applicantWorking);
      mapApplicantWorking.put(applicantWorking.getApplicantId(), applicantWorking);
    }
    // Clear the Applicant List as it is no longer required.
    listApplicant.clear();
    listApplicant = null;
    Integer applicantId = null;
    for (BookingDateUserApplicant bookingDateUserApplicant : listBookingDateUserApplicant)
    {
      if (bookingDateUserApplicant.getStatus() != BookingDate.BOOKINGDATE_STATUS_CANCELLED)
      {
        // For each BookingDateUserApplicant...
        applicantId = bookingDateUserApplicant.getApplicantId();
        if (applicantWorking == null || applicantId != applicantWorking.getApplicantId())
        {
          // Current ApplicantWorking object NOT for this Applicant. So get the one that is.
          applicantWorking = mapApplicantWorking.get(applicantId);
        }
        int index = 0;
        for (Date date : listDate)
        {
          // For each date...
          if (DateUtils.isSameDay(bookingDateUserApplicant.getBookingDate(), date))
          {
            // The BookingDateUserApplicant is for the date. Update hours scheduled and worked.
            updateHoursScheduled(applicantWorking, index, bookingDateUserApplicant, calendar);
            if (bookingDateUserApplicant.getWorkedEndTime() != null && bookingDateUserApplicant.getWorkedStartTime() != null)
            {
              updateHoursWorked(applicantWorking, index, bookingDateUserApplicant, calendar);
            }
            break;
          }
          index++;
        }
      }      
    }
    applicantWorking = null;
    for (Unavailable unavailable : listUnavailable)
    {
      applicantId = unavailable.getApplicantId();
      logger.debug("Applicant:  " + unavailable.getApplicantId() + " Date: " + unavailable.getUnavailableDate());
      if (applicantWorking == null || applicantId != applicantWorking.getApplicantId())
      {
        // Current ApplicantWorking object NOT for this Applicant. So get the one that is.
        applicantWorking = mapApplicantWorking.get(applicantId);
      }
      if (applicantWorking == null)
      {
        logger.debug("ApplicantWorking NULL  ");
      }
      else
      {
        logger.debug("ApplicantWorking:  " + applicantWorking.toString());
        int index = 0;
        for (Date date : listDate)
        {
          // For each date...
          if (DateUtils.isSameDay(unavailable.getUnavailableDate(), date))
          {
            // The Unavailable is for the date. Set Unavailable flag.
            applicantWorking.setUnavailableForDate(index);
            break;
          }
          index++;
        }
      }
    }
    if (!showAll)
    {
      // Only show Applicants with at least one Booking.
      for (ListIterator<ApplicantWorking> iterator = listApplicantWorking.listIterator(listApplicantWorking.size()); iterator.hasPrevious();)
      {
        applicantWorking = iterator.previous();
        if (!applicantWorking.hasBooking())
        {
          // Applicant does NOT have any Bookings. Remove Applicant from list.
          iterator.remove();
        }
      }
    }
    mapApplicantWorking.clear();
    Collections.sort(listApplicantWorking, new ApplicantWorkingComparator());
    dynaForm.set("listDate", listDate);
    dynaForm.set("list", listApplicantWorking);
    dynaForm.set("showAll", showAll);
    dynaForm.set("weekToShow", weekToShow);
    dynaForm.set("startDate", startDate);
    dynaForm.set("endDate", endDate);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

  private Date calculateStartTime(long bookingDate, long startHourMinute, Calendar calendar)
  {
    calendar.setTimeInMillis(startHourMinute);
    int startHour = calendar.get(Calendar.HOUR_OF_DAY);
    int startMinute = calendar.get(Calendar.MINUTE);
    calendar.setTimeInMillis(bookingDate);
    setCalendarToTime(calendar, startHour, startMinute);
    return new Date(calendar.getTimeInMillis());
  }
  
  private Date calculateEndTime(long date, long endTimeHourMinute, Calendar calendar)
  {
    calendar.setTimeInMillis(endTimeHourMinute);
    int endHour = calendar.get(Calendar.HOUR_OF_DAY);
    int endMinute = calendar.get(Calendar.MINUTE);
    calendar.setTimeInMillis(date);
    setCalendarToTime(calendar, endHour, endMinute);
    return new Date(calendar.getTimeInMillis());
  }

  private Date calculateStartOfTomorrow(long date, Calendar calendar)
  {
    // Reset Calendar to Booking Date.
    calendar.setTimeInMillis(date);
    setCalendarToEndOfDay(calendar);
    addMillisecondsToCalendar(calendar, 1);
    return new Date(calendar.getTimeInMillis());
  }
  
  private void updateHoursScheduled(ApplicantWorking applicantWorking, int index, BookingDateUserApplicant bookingDateUserApplicant, Calendar calendar)
  {
    BigDecimal hours = null;
    if (bookingDateUserApplicant.getShiftEndTime().after(bookingDateUserApplicant.getShiftStartTime()))
    {
      // The SIMPLE CASE: The shift is all in one calendar day.
      hours = bookingDateUserApplicant.getShiftNoOfHours();
      applicantWorking.updateHoursScheduled(index, hours);
      applicantWorking.setFinishedPeriodForDate(index, getFinishedPeriodForTime(bookingDateUserApplicant.getShiftEndTime().getTime()));
    }
    else
    {
      // The shift spans midnight. That is, it exists on two days.
      Long shiftTodayInMilliseconds = MILLISECONDS_IN_DAY - bookingDateUserApplicant.getShiftStartTime().getTime();
      Long shiftTomorrowInMilliseconds = bookingDateUserApplicant.getShiftEndTime().getTime();
      // Update Scheduled Hours for Today.
      hours = new BigDecimal(shiftTodayInMilliseconds);
      hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
      applicantWorking.updateHoursScheduled(index, hours); 
      applicantWorking.setFinishedPeriodForDate(index, getFinishedPeriodForTime(MILLISECONDS_IN_DAY));
      // Update Scheduled Hours for Tomorrow.
      hours = new BigDecimal(shiftTomorrowInMilliseconds);
      hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
      applicantWorking.updateHoursScheduled(index + 1, hours);
      applicantWorking.setFinishedPeriodForDate(index + 1, getFinishedPeriodForTime(bookingDateUserApplicant.getShiftEndTime().getTime()));
      if (bookingDateUserApplicant.getShiftBreakStartTime() != null && bookingDateUserApplicant.getShiftBreakEndTime() != null)
      {
        // Shift HAS a Break in it.
        if (bookingDateUserApplicant.getShiftBreakEndTime().before(bookingDateUserApplicant.getShiftBreakStartTime()))
        {
          // Break is in both today and tomorrow. That is, it spans midnight.
          // Firstly, calculate break duration today.
          Long breakInMilliseconds = (MILLISECONDS_IN_DAY - bookingDateUserApplicant.getShiftBreakStartTime().getTime());
          hours = new BigDecimal(breakInMilliseconds * -1l);
          hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
          applicantWorking.updateHoursScheduled(index, hours);
          // Secondly, calculate break duration tomorrow.
          breakInMilliseconds = (bookingDateUserApplicant.getShiftBreakEndTime().getTime());
          hours = new BigDecimal(breakInMilliseconds * -1l);
          hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
          applicantWorking.updateHoursScheduled(index + 1, hours);
        }
        else
        {
          // Break is wholly either in today or tomorrow. That is, it does NOT span midnight.
          Long breakInMilliseconds = (bookingDateUserApplicant.getShiftBreakEndTime().getTime() - bookingDateUserApplicant.getShiftBreakStartTime().getTime());
          hours = new BigDecimal(breakInMilliseconds * -1l);
          hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
          if (bookingDateUserApplicant.getShiftBreakStartTime().before(bookingDateUserApplicant.getShiftStartTime()))
          {
            // Break is in today.
            applicantWorking.updateHoursScheduled(index, hours);
          }
          else
          {
            // Break is in tomorrow.
            applicantWorking.updateHoursScheduled(index + 1, hours);
          }
        }
      }      
    }
  }
  
  private void updateHoursWorked(ApplicantWorking applicantWorking, int index, BookingDateUserApplicant bookingDateUserApplicant, Calendar calendar)
  {
    BigDecimal hours = null;
//    System.out.println(bookingDateUserApplicant.getApplicantFullNameLastFirst());
    if (bookingDateUserApplicant.getWorkedEndTime().after(bookingDateUserApplicant.getWorkedStartTime()))
    {
      // The SIMPLE CASE: The shift is all in one calendar day.
      hours = bookingDateUserApplicant.getWorkedNoOfHours();
      applicantWorking.updateHoursWorked(index, hours);
      applicantWorking.setFinishedPeriodForDate(index, getFinishedPeriodForTime(bookingDateUserApplicant.getWorkedEndTime().getTime()));
      applicantWorking.setWorkedStatusForDate(index, bookingDateUserApplicant.getWorkedStatus());
    }
    else
    {
      // The shift spans midnight. That is, it exists on two days.
      Long shiftTodayInMilliseconds = MILLISECONDS_IN_DAY - bookingDateUserApplicant.getWorkedStartTime().getTime();
      Long shiftTomorrowInMilliseconds = bookingDateUserApplicant.getWorkedEndTime().getTime();
      // Update Worked Hours & Worked Status for Today.
      hours = new BigDecimal(shiftTodayInMilliseconds);
      hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
      applicantWorking.updateHoursWorked(index, hours); 
      applicantWorking.setFinishedPeriodForDate(index, getFinishedPeriodForTime(MILLISECONDS_IN_DAY));
      applicantWorking.setWorkedStatusForDate(index, bookingDateUserApplicant.getWorkedStatus());
      // Update Worked Hours & Worked Status for Tomorrow.
      hours = new BigDecimal(shiftTomorrowInMilliseconds);
      hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
      applicantWorking.updateHoursWorked(index + 1, hours);
      applicantWorking.setFinishedPeriodForDate(index + 1, getFinishedPeriodForTime(bookingDateUserApplicant.getWorkedEndTime().getTime()));
      applicantWorking.setWorkedStatusForDate(index + 1, bookingDateUserApplicant.getWorkedStatus());
      if (bookingDateUserApplicant.getWorkedBreakStartTime() == null && bookingDateUserApplicant.getWorkedBreakEndTime() == null)
      {
        // The Applicant did NOT take a break.
        logger.debug("Applicant {} did not take a break {}", bookingDateUserApplicant.getApplicantFullNameLastFirst(), bookingDateUserApplicant.getBookingDate());
      }
      else
      {
        // The Applicant took a break.
        if (bookingDateUserApplicant.getWorkedBreakEndTime().before(bookingDateUserApplicant.getWorkedBreakStartTime()))
        {
          // Break is in both today and tomorrow. That is, it spans midnight.
          // Firstly, calculate break duration today.
          Long breakInMilliseconds = (MILLISECONDS_IN_DAY - bookingDateUserApplicant.getWorkedBreakStartTime().getTime());
          hours = new BigDecimal(breakInMilliseconds * -1l);
          hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
          applicantWorking.updateHoursWorked(index, hours);
          // Secondly, calculate break duration tomorrow.
          breakInMilliseconds = (bookingDateUserApplicant.getWorkedBreakEndTime().getTime());
          hours = new BigDecimal(breakInMilliseconds * -1l);
          hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
          applicantWorking.updateHoursWorked(index + 1, hours);
        }
        else
        {
          // Break is wholly either in today or tomorrow. That is, it does NOT span midnight.
          Long breakInMilliseconds = (bookingDateUserApplicant.getWorkedBreakEndTime().getTime() - bookingDateUserApplicant.getWorkedBreakStartTime().getTime());
          hours = new BigDecimal(breakInMilliseconds * -1l);
          hours = hours.divide(MILLISECONDS_IN_HOUR, 2, RoundingMode.HALF_UP);
          if (bookingDateUserApplicant.getWorkedBreakStartTime().before(bookingDateUserApplicant.getWorkedStartTime()))
          {
            // Break is in today.
            applicantWorking.updateHoursWorked(index, hours);
          }
          else
          {
            // Break is in tomorrow.
            applicantWorking.updateHoursWorked(index + 1, hours);
          }
        } 
      }
    }
  }
  
  private Integer getFinishedPeriodForTime(Long time)
  {
    // Value 1,2,3 or 4
    Integer p = new Integer(((time.intValue() - 1) / MILLISECONDS_IN_PERIOD.intValueExact()) + 1);
    return p;
  }
  
  /** 
   * Adds the specified milliseconds to the Calendar.
   */
  private void addMillisecondsToCalendar(Calendar c, int milliseconds) 
  {
    c.add(Calendar.MILLISECOND, milliseconds);
  }
  
  /** 
   * Set the Calendar to the end of the day time for the day.
   */
  private void setCalendarToEndOfDay(Calendar c) 
  {
      c.set(Calendar.HOUR_OF_DAY, 23);
      c.set(Calendar.MINUTE, 59);
      c.set(Calendar.SECOND, 59);
      c.set(Calendar.MILLISECOND, 999);
  }
  
  /** 
   * Set the Calendar to the specified time HH:MM for the day.
   */
  private void setCalendarToTime(Calendar c, int hour, int minute) 
  {
      c.set(Calendar.HOUR_OF_DAY, hour);
      c.set(Calendar.MINUTE, minute);
      c.set(Calendar.SECOND, 0);
      c.set(Calendar.MILLISECOND, 0);
//      addMillisecondsToCalendar(c, hour * 60 * 60 * 1000);
//      addMillisecondsToCalendar(c, minute * 60 * 1000);
  }
}


//private void updateHoursScheduled(ApplicantWorking applicantWorking, int index, BookingDateUserApplicant bookingDateUserApplicant, Calendar calendar)
//{
//  SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss.SSS z");
//  Date bookingDate = bookingDateUserApplicant.getBookingDate();
////  Time startHourMinute = bookingDateUserApplicant.getShiftStartTime();
////  Time endHourMinute   = bookingDateUserApplicant.getShiftEndTime();
////  Date startTime = calculateStartTime(bookingDate.getTime(), startHourMinute.getTime(), calendar);
//  long milliSecondsPerHour = 1000 * 60 * 60;
//  long milliSecondsPerDay = 24 * milliSecondsPerHour;
//  BigDecimal hours = null;
//  if (bookingDateUserApplicant.getShiftEndTime().after(bookingDateUserApplicant.getShiftStartTime()))
//  {
//    // The SIMPLE CASE: The shift is all in one calendar day.
//    // endTime = calculateEndTime(bookingDate.getTime(), endHourMinute.getTime(), calendar);
//    // Long shiftInMilliseconds = (endTime.getTime() - startTime.getTime());
////    Long shiftInMilliseconds = (bookingDateUserApplicant.getShiftEndTime().getTime() - bookingDateUserApplicant.getShiftStartTime().getTime());
////    Long breakInMilliseconds = (bookingDateUserApplicant.getShiftBreakEndTime().getTime() - bookingDateUserApplicant.getShiftBreakStartTime().getTime());
////    hours = new BigDecimal(shiftInMilliseconds - breakInMilliseconds);
////    hours = hours.divide(MILLISECONDS_IN_HOUR);
//    hours = bookingDateUserApplicant.getShiftNoOfHours();
//    applicantWorking.updateHoursScheduled(index, hours);
//    applicantWorking.setFinishedPeriodForDate(index, getFinishedPeriodForTime(bookingDateUserApplicant.getShiftEndTime().getTime()));
//  }
//  else
//  {
//    // The shift spans midnight. That is, it exists on two days.
//    Date startOfTomorrow = calculateStartOfTomorrow(bookingDate.getTime(), calendar);
//    //endTime = calculateEndTime(startOfTomorrow.getTime(), endHourMinute.getTime(), calendar);
//    // Calculate length of Shift for today and tomorrow.
////    Long shiftTodayInMilliseconds = (startOfTomorrow.getTime() - bookingDateUserApplicant.getShiftStartTime().getTime());
////    Long shiftTomorrowInMilliseconds = (bookingDateUserApplicant.getShiftEndTime().getTime() - startOfTomorrow.getTime());
//    Long shiftTodayInMilliseconds = milliSecondsPerDay - bookingDateUserApplicant.getShiftStartTime().getTime();
//    Long shiftTomorrowInMilliseconds = bookingDateUserApplicant.getShiftEndTime().getTime();
//    // Update Scheduled Hours for Today.
//    hours = new BigDecimal(shiftTodayInMilliseconds);
//    hours = hours.divide(MILLISECONDS_IN_HOUR);
//    applicantWorking.updateHoursScheduled(index, hours); 
//    applicantWorking.setFinishedPeriodForDate(index, getFinishedPeriodForTime(milliSecondsPerDay));
//    // Update Scheduled Hours for Tomorrow.
//    hours = new BigDecimal(shiftTomorrowInMilliseconds);
//    hours = hours.divide(MILLISECONDS_IN_HOUR);
//    applicantWorking.updateHoursScheduled(index + 1, hours);
//    applicantWorking.setFinishedPeriodForDate(index + 1, getFinishedPeriodForTime(bookingDateUserApplicant.getShiftEndTime().getTime()));
//    if (bookingDateUserApplicant.getShiftBreakEndTime().before(bookingDateUserApplicant.getShiftBreakStartTime()))
//    {
//      // Break is in both today and tomorrow. That is, it spans midnight.
//      // Firstly, calculate break duration today.
//      Long breakInMilliseconds = (milliSecondsPerDay - bookingDateUserApplicant.getShiftBreakStartTime().getTime());
//      hours = new BigDecimal(breakInMilliseconds * -1l);
//      hours = hours.divide(MILLISECONDS_IN_HOUR);
//      applicantWorking.updateHoursScheduled(index, hours); 
//      // Secondly, calculate break duration tomorrow.
//      breakInMilliseconds = (bookingDateUserApplicant.getShiftBreakEndTime().getTime());
//      hours = new BigDecimal(breakInMilliseconds * -1l);
//      hours = hours.divide(MILLISECONDS_IN_HOUR);
//      applicantWorking.updateHoursScheduled(index + 1, hours); 
//    }
//    else
//    {
//      // Break is wholly either in today or tomorrow. That is, it does NOT span midnight.
//      Long breakInMilliseconds = (bookingDateUserApplicant.getShiftBreakEndTime().getTime() - bookingDateUserApplicant.getShiftBreakStartTime().getTime());
//      hours = new BigDecimal(breakInMilliseconds * -1l);
//      hours = hours.divide(MILLISECONDS_IN_HOUR);
//      if (bookingDateUserApplicant.getShiftBreakStartTime().before(bookingDateUserApplicant.getShiftStartTime()))
//      {
//        // Break is in today.
//        applicantWorking.updateHoursScheduled(index, hours); 
//      }
//      else
//      {
//        // Break is in tomorrow.
//        applicantWorking.updateHoursScheduled(index + 1, hours); 
//      }
//    }
//  }
//}
//
//
