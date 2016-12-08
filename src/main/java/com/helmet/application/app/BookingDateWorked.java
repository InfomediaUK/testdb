package com.helmet.application.app;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AppService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.BookingDateUserApplicant;

public class BookingDateWorked extends AppAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    BookingDateUserApplicant bookingDate = (BookingDateUserApplicant) dynaForm.get("bookingDate");

    AppService appService = ServiceFactory.getInstance().getAppService();

    bookingDate = appService.getBookingDateUserApplicantForApplicantForBookingDate(bookingDate.getBookingDateId());

    Time workedStartTime = null;
    Time workedEndTime = null;
    Time workedBreakStartTime = null;
    Time workedBreakEndTime = null;
    BigDecimal workedNoOfHours = null;

    if (bookingDate.getHasBeenEntered())
    {
      // NOT first time - editing, so prime the fields with the values
      // previously entered
      workedStartTime = bookingDate.getWorkedStartTime();
      workedEndTime = bookingDate.getWorkedEndTime();
      workedBreakStartTime = bookingDate.getWorkedBreakStartTime();
      workedBreakEndTime = bookingDate.getWorkedBreakEndTime();
      workedNoOfHours = bookingDate.getWorkedNoOfHours();
    }
    else
    {
      // first time - not editing, so prime fields with 'expected' values
      workedStartTime = bookingDate.getShiftStartTime();
      workedEndTime = bookingDate.getShiftEndTime();
      workedBreakStartTime = bookingDate.getShiftBreakStartTime();
      workedBreakEndTime = bookingDate.getShiftBreakEndTime();
      if (bookingDate.getUndefinedShift())
      {
        workedNoOfHours = new BigDecimal(0);
      }
      else
      {
        workedNoOfHours = bookingDate.getShiftNoOfHours();
      }
    }

    Calendar cal = Calendar.getInstance();
    if (workedStartTime != null)
    {
      cal.setTimeInMillis(workedStartTime.getTime());
      dynaForm.set("workedStartHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
      dynaForm.set("workedStartMinute", String.valueOf(cal.get(Calendar.MINUTE)));
    }
    if (workedEndTime != null)
    {
      cal.setTimeInMillis(workedEndTime.getTime());
      dynaForm.set("workedEndHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
      dynaForm.set("workedEndMinute", String.valueOf(cal.get(Calendar.MINUTE)));
    }
    if (workedBreakStartTime != null)
    {
      cal.setTimeInMillis(workedBreakStartTime.getTime());
      dynaForm.set("workedBreakStartHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
      dynaForm.set("workedBreakStartMinute", String.valueOf(cal.get(Calendar.MINUTE)));
    }
    if (workedBreakEndTime != null)
    {
      cal.setTimeInMillis(workedBreakEndTime.getTime());
      dynaForm.set("workedBreakEndHour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
      dynaForm.set("workedBreakEndMinute", String.valueOf(cal.get(Calendar.MINUTE)));
    }
    dynaForm.set("workedNoOfHours", workedNoOfHours);

    if (bookingDate.getAgencyId() == 5) // HARD CODE WARNING
    {
      if (StringUtils.isNotEmpty(bookingDate.getBookingReference()))
      {
        // A Booking Reference is available.
        MessageResources messageResources = getResources(request);
        if (StringUtils.isEmpty(bookingDate.getComment()))
        {
          // The Booking's Comment is empty, just set it to the Booking Reference.
          bookingDate.setComment(messageResources.getMessage("booking.reference") + " " + bookingDate.getBookingReference());
        }
        else
        {
          String comment = bookingDate.getComment().toLowerCase();
          if (!comment.contains(bookingDate.getBookingReference().toLowerCase()))
          {
            bookingDate.setComment(messageResources.getMessage("booking.reference") + " " + bookingDate.getBookingReference() + ". " + bookingDate.getComment());
          }
        }
      }
    }    
    dynaForm.set("bookingDate", bookingDate);

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}