package com.helmet.application.zap;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.ZapService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.Utilities;
import com.helmet.application.agy.BookingBookingDateUserApplicant;
import com.helmet.application.app.AppConstants;
import com.helmet.application.app.AppUtilities;
import com.helmet.application.zap.abztract.ZapAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;


public class ApplicantBookingDatesView extends ZapAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    logger.entry("In coming !!!");
    ZapService zapService = ServiceFactory.getInstance().getZapService();
    Date startDate = null;
    Date endDate = null;
    // Get show from the Session. 
    String show = AppUtilities.getShow(request);
    if (!StringUtils.equals(show, AppConstants.WEEKLY))
    {
      // show Not in Session, default to weekly.
      show = new String(AppConstants.WEEKLY);
    }
    if (StringUtils.isNotEmpty((String)dynaForm.get("show")))
    {
      // show on form. Override current value.
      show = (String)dynaForm.get("show");
    }
    // Get weekToShow from the Session. 
    Integer weekToShow = AppUtilities.getWeekToShow(request);
    if (weekToShow == null)
    {
      // weekToShow Not in Session, default to Current Week.
      weekToShow = new Integer(0);
    }
    if (show.equals(AppConstants.WEEKLY))
    {
      // showing weekly so determine which week to show 0=current, -1=last week, 1=next week
      if (dynaForm.get("weekToShow") != null)
      {
        // weekToShow on form. Override current value.
        weekToShow = (Integer)dynaForm.get("weekToShow");
      }
      // find out this weeks monday
      startDate = Utilities.getStartOfWeek(weekToShow);
      endDate = Utilities.getEndOfWeek(weekToShow);

      dynaForm.set("startDate", startDate);
      dynaForm.set("endDate", endDate);

    }
    Applicant applicant = zapService.getApplicant(getApplicantLoggedIn().getApplicantId());
    if (applicant == null) { return mapping.findForward("illegalAccess"); }
    List<BookingDateUserApplicant> list = zapService.getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(applicant.getApplicantId(), applicant.getAgencyId(), startDate, endDate);
    sortShiftTotals(list, dynaForm);
    List <BookingBookingDateUserApplicant> listBookingBookingDateUserApplicant = loadBookingBookingDateUserApplicantList(applicant.getApplicantId(), list, weekToShow);
    dynaForm.set("list", listBookingBookingDateUserApplicant);
    dynaForm.set("show", show);
    AppUtilities.setShow(request, show);
    dynaForm.set("weekToShow", weekToShow);
    AppUtilities.setWeekToShow(request, weekToShow);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

  protected void sortShiftTotals(List list, DynaValidatorForm dynaForm)
  {
    BigDecimal totalHours = new BigDecimal(0);
    BigDecimal totalActualHours = new BigDecimal(0);

    for (int i = 0; i < list.size(); i++)
    {
      BookingDateUserApplicant bookingDateUserApplicant = (BookingDateUserApplicant) list.get(i);
      // LYNDON. The following 'add' code did NOT have the defensive check for null in it. 11/07/2011
      if (bookingDateUserApplicant.getShiftNoOfHours() != null && bookingDateUserApplicant.getUndefinedShift() == false)
      {
        totalHours = totalHours.add(bookingDateUserApplicant.getShiftNoOfHours());
      }
      if (bookingDateUserApplicant.getWorkedNoOfHours() != null)
      {
        totalActualHours = totalActualHours.add(bookingDateUserApplicant.getWorkedNoOfHours());
      }
    }

    dynaForm.set("totalHours", totalHours);
    dynaForm.set("totalActualHours", totalActualHours);
  }

  // Also in ApplicantCommon.
  protected List <BookingBookingDateUserApplicant> loadBookingBookingDateUserApplicantList(Integer applicantId, List<BookingDateUserApplicant> list, Integer weekToShow)
  {
    List <BookingBookingDateUserApplicant> listBookingBookingDateUserApplicant = new ArrayList<BookingBookingDateUserApplicant>();
    Integer bookingId = null;
    BookingBookingDateUserApplicant bookingBookingDateUserApplicant = null;
    for (BookingDateUserApplicant bookingDateUserApplicant : list)
    {
      bookingId = bookingDateUserApplicant.getBookingId();
      if (listBookingBookingDateUserApplicant.size() == 0)
      {
        bookingBookingDateUserApplicant = new BookingBookingDateUserApplicant(applicantId, bookingId, weekToShow);
        listBookingBookingDateUserApplicant.add(bookingBookingDateUserApplicant);
      }
      else
      {
        bookingBookingDateUserApplicant = getBookingBookingDateUserApplicantForBookingId(listBookingBookingDateUserApplicant, bookingId);
        if (bookingBookingDateUserApplicant == null)
        {
          bookingBookingDateUserApplicant = new BookingBookingDateUserApplicant(applicantId, bookingId, weekToShow);
          listBookingBookingDateUserApplicant.add(bookingBookingDateUserApplicant);
        }
      }
      bookingBookingDateUserApplicant.addBookingDateUserApplicantToList(bookingDateUserApplicant);
    }
    return listBookingBookingDateUserApplicant;
  }

  // Also in ApplicantCommon.
  public BookingBookingDateUserApplicant getBookingBookingDateUserApplicantForBookingId(List<BookingBookingDateUserApplicant> listBookingBookingDateUserApplicant, Integer bookingId)
  {
    for (BookingBookingDateUserApplicant bookingBookingDateUserApplicant: listBookingBookingDateUserApplicant)
    {
      if (bookingBookingDateUserApplicant.getBookingId().equals(bookingId))
      {
        return bookingBookingDateUserApplicant;
      }
    }
    return null;
  }

}
