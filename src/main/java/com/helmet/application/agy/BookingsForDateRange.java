package com.helmet.application.agy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.BookingReportGroup;
import com.helmet.application.NhsBookingReportGroup;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingGradeAgy;
import com.helmet.bean.NhsBookingUser;

public class BookingsForDateRange extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Date startOfWeekDate = null;
    Date endOfWeekDate = null;
    // showing weekly so determine which week to show 0=current, -1=last week, 1=next week
    Integer weekToShow = getWeekToShow(request, dynaForm);
    startOfWeekDate = Utilities.getStartOfWeek(weekToShow);
    endOfWeekDate = Utilities.getEndOfWeek(weekToShow);

    dynaForm.set("startDate", startOfWeekDate);
    dynaForm.set("endDate", endOfWeekDate);
    // Fill list with NHSBookings for the week.
    int countBooking = 0;
    int countBookingBooked = 0;
    int countBookingDeleted = 0;
    List<BookingGradeAgy> listBookings = agyService.getBookingGradeAgysForAgencyAndDateRange(getConsultantLoggedIn().getAgencyId(), startOfWeekDate, endOfWeekDate, null, false);
    // Create an empty list of BookingReportGroups.
    List<BookingReportGroup> listBookingReportGroup = new ArrayList<BookingReportGroup>();
    dynaForm.set("list", listBookings);
    dynaForm.set("countBooking", countBooking);
    dynaForm.set("countBookingBooked", countBookingBooked);
    dynaForm.set("countBookingDeleted", countBookingDeleted);
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return actionForward;
  }

}
