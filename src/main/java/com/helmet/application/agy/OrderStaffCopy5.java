package com.helmet.application.agy;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.BookingDate;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationUser;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.Shift;
import com.helmet.bean.Uplift;


public class OrderStaffCopy5 extends OrderStaffCopyBase 
{

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    Integer page   = (Integer)dynaForm.get("page");
    Boolean complete = (Boolean)dynaForm.get("complete");
    if (isCancelled(request))
    {
      dynaForm.set("page", page - 1);
      return mapping.findForward("back");
    }

    ClientUser client = (ClientUser) dynaForm.get("client");
    if (client.getClientId() == null || client.getClientId() == 0) { return mapping.findForward("orderStaffCopy"); }
    LocationUser locationUser = (LocationUser) dynaForm.get("location");
    if (locationUser.getLocationId() == null || locationUser.getLocationId() == 0) { return mapping.findForward("orderStaffCopy"); }
    JobProfileUser jobProfileUser = (JobProfileUser) dynaForm.get("jobProfile");
    if (jobProfileUser.getJobProfileId() == null || jobProfileUser.getJobProfileId() == 0) { return mapping.findForward("orderStaffCopy"); }

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    List<Shift> shifts = agyService.getShiftsForLocation(locationUser.getLocationId());
    Boolean undefinedShift = isUndefinedShift(shifts);
    int noOfShifts = loadFormFromShifts(shifts, undefinedShift, dynaForm);
    boolean singleShift = noOfShifts == 1;
    String dates = (String) dynaForm.get("dates");
    if (dates == null || "".equals(dates)) { return mapping.findForward("orderStaffCopy"); }

    // determine whether the user is going forwards (next) or backwards (back)
    boolean userPressedNext = page == 4;
    if (userPressedNext)
    {
      // going forward ... 
      String origDates = (String) dynaForm.get("origDates");

      BookingDate[] oldBookingDates = (BookingDate[]) dynaForm.get("bookingDates");
      Integer onlyShiftId = getOnlyShiftId(oldBookingDates);
      //
      // currently overwriting any previous selections - would be good to keep existing if possible
      //
      BookingDate[] bookingDates;
      if (StringUtils.equals(origDates, dates))
      {
        // Dates have NOT changed.
        dynaForm.set("bookingDates", oldBookingDates);
      }
      else
      {
        // Dates HAVE changed. Rebuild BookingDates array.
        if (complete)
        {
          // User has clicked Complete button...
          if (onlyShiftId == null)
          {
            // There is more than one shift involved, 'complete' is NOT possible until shifts have been chosen.
            complete = new Boolean(false);
          }
        }
        int noOfDates = new java.util.StringTokenizer(dates, ",").countTokens();
        dynaForm.set("noOfDates", new Integer(noOfDates));
        // load the dates WITHOUT shifts - unless there is only one !!!
        StringTokenizer st = new StringTokenizer(dates, ",");
        bookingDates = new BookingDate[st.countTokens()];
        int d = 0;
        while (st.hasMoreTokens())
        {
          BookingDate bookingDate = new BookingDate();
          bookingDate.setBookingDate(Date.valueOf(st.nextToken()));
          if (onlyShiftId != null)
          {
            Shift onlyShift = getShiftFromList(onlyShiftId, shifts);
            bookingDate.setShift(onlyShift);
          }
          else
          {
            if (oldBookingDates != null)
            {
              for (BookingDate oldBookingDate : oldBookingDates)
              {
                if (oldBookingDate.getBookingDate().equals(bookingDate.getBookingDate()))
                {
                  if (oldBookingDate.getShiftId() != null)
                  {
                    // Old BookingDate had a Shift.
                    if (oldBookingDate.getShiftId() > 0)
                    {
                      bookingDate.setShift(oldBookingDate.getShift());
//                      bookingDate.setShiftId(oldBookingDate.getShiftId());
//                      bookingDate.setShiftName(oldBookingDate.getShiftName());
//                      bookingDate.setShiftCode(oldBookingDate.getShiftCode());
//                      bookingDate.setShiftStartTime(oldBookingDate.getShiftStartTime());
//                      bookingDate.setShiftEndTime(oldBookingDate.getShiftEndTime());
//                      bookingDate.setShiftBreakStartTime(oldBookingDate.getShiftBreakStartTime());
//                      bookingDate.setShiftBreakEndTime(oldBookingDate.getShiftBreakEndTime());
//                      bookingDate.setShiftNoOfHours(oldBookingDate.getShiftNoOfHours());
//                      bookingDate.setShiftBreakNoOfHours(oldBookingDate.getShiftBreakNoOfHours());
//                      bookingDate.setShiftUseUplift(oldBookingDate.getShiftUseUplift());
//                      bookingDate.setShiftUpliftFactor(oldBookingDate.getShiftUpliftFactor());
//                      bookingDate.setShiftUpliftValue(oldBookingDate.getShiftUpliftValue());
//                      bookingDate.setShiftOvertimeNoOfHours(oldBookingDate.getShiftOvertimeNoOfHours());
//                      bookingDate.setShiftOvertimeUpliftFactor(oldBookingDate.getShiftOvertimeUpliftFactor());
                    }                    
                  }
                  break;
                }
              }
            }
          }
          bookingDates[d] = bookingDate;
          d++;
        }
        dynaForm.set("bookingDates", bookingDates);
        if (complete)
        {
          // The following is done in OrderStaffCopy6 if User clicked Next button.
          BigDecimal shiftNoOfHours = onlyShiftId == null ? shifts.get(0).getNoOfHours() : getNoOfHoursForShift(onlyShiftId, shifts);
          BigDecimal totalHours = calculateTotalHoursFromBookingDates(bookingDates, shiftNoOfHours, undefinedShift);
          if (totalHours.equals(0))
          {
            // Must be an undefined shift. Preserve value on form.
            totalHours = (BigDecimal) dynaForm.get("totalHours");
          }
          dynaForm.set("totalHours", totalHours);
          BigDecimal hourlyRate = (BigDecimal) dynaForm.get("hourlyRate");
//          if (hourlyRate == null)
//          {
//            LocationJobProfile locationJobProfile = agyService.getLocationJobProfileForLocationAndJobProfile(locationUser.getLocationId(), jobProfileUser.getJobProfileId());
//            hourlyRate = locationJobProfile.getRate();
//          }
//        dynaForm.set("hourlyRate", hourlyRate);
          List<PublicHoliday> publicHolidays = agyService.getPublicHolidaysForClient(client.getClientId());
          List<Uplift> uplifts = agyService.getUpliftsForClient(client.getClientId());
          BigDecimal rrp = calculateUpliftedRate(agyService, publicHolidays, uplifts, bookingDates, shifts, totalHours, hourlyRate);
          dynaForm.set("rrp", rrp);
          loadFormWithClientAgencyJobProfileGradeStuff(dynaForm, agyService, jobProfileUser, null, bookingDates, publicHolidays, uplifts, totalHours, hourlyRate, rrp, undefinedShift);    
        }        
      }      
    }
    else
    {
      // going backwards so leave the booking dates well alone !!!
    }
    if (complete)
    {
      return mapping.findForward("complete");
    }
    else
    {
      return mapping.findForward("success");
    }
  }

  private int loadFormFromShifts(List<Shift> shifts, Boolean undefinedShift, DynaValidatorForm dynaForm)
  {
    int noOfShifts = shifts.size();
    Boolean definedShifts = new Boolean(noOfShifts > 0);
    if (undefinedShift && noOfShifts == 1)
    {
      // The only shift is undefined.
      definedShifts = new Boolean(false);
    }
    dynaForm.set("shifts", shifts);
    dynaForm.set("noOfShifts", noOfShifts);
    dynaForm.set("undefinedShift", undefinedShift);
    dynaForm.set("definedShifts", definedShifts);
    return noOfShifts;
  }
  
}
