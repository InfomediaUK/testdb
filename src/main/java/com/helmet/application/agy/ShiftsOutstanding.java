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
import com.helmet.application.BookingDateUserApplicantReportGroup;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUserApplicant;

public class ShiftsOutstanding extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");
    ActionForward forward = null;

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    Boolean groupByLocation = new Boolean(true); //dynaForm.get("groupByLocation") == null ? new Boolean(false) : (Boolean)dynaForm.get("groupByLocation");
    Date startOfWeekDate = null;
    Date endOfWeekDate = null;
    // showing weekly so determine which week to show 0=current, -1=last week, 1=next week
    Integer weekToShow = getWeekToShow(request, dynaForm);
    startOfWeekDate = Utilities.getStartOfWeek(weekToShow);
    endOfWeekDate = Utilities.getEndOfWeek(weekToShow);
    dynaForm.set("startDate", startOfWeekDate);
    dynaForm.set("endDate", endOfWeekDate);
    List<BookingDateUserApplicantReportGroup> listBookingDateUserApplicantReportGroup = new ArrayList<BookingDateUserApplicantReportGroup>();
    List<BookingDateUserApplicant> listBookingDateUserApplicant = null;
    if (groupByLocation)
    {
      listBookingDateUserApplicant = agyService.getBookingDateUserApplicantsForAgencyByClientSiteLocation(getConsultantLoggedIn().getAgencyId(), null, null, null, null, null, BookingDate.BOOKINGDATE_STATUS_FILLED,
        BookingDate.BOOKINGDATE_WORKEDSTATUS_NULL, null, true, null, null, null, null, null, null, startOfWeekDate, endOfWeekDate);
    }
    else
    {
      listBookingDateUserApplicant = agyService.getBookingDateUserApplicantsForAgency(getConsultantLoggedIn().getAgencyId(), null, null, null, null, null, BookingDate.BOOKINGDATE_STATUS_FILLED,
        BookingDate.BOOKINGDATE_WORKEDSTATUS_NULL, null, true, null, null, null, null, null, null, startOfWeekDate, endOfWeekDate);
    }
    // in ancestor class
    sortShiftTotals(listBookingDateUserApplicant, dynaForm);

    dynaForm.set("list", listBookingDateUserApplicant);
    if (groupByLocation)
    {
      if (listBookingDateUserApplicant.size() > 0) 
      {
        // Found BookingDateUserApplicants for the week. 
        BookingDateUserApplicantReportGroup bookingDateUserApplicantReportGroup = null;
        for (BookingDateUserApplicant bookingDateUserApplicant : listBookingDateUserApplicant)
        {
          // For each BookingDateUserApplicant... 
          if (bookingDateUserApplicantReportGroup != null && bookingDateUserApplicantReportGroup.getReportGroupKey().equals(bookingDateUserApplicant.getReportGroupKey()))
          {
            // BookingDateUserApplicant is for current Report Group, just add it to the group's list.
            bookingDateUserApplicantReportGroup.addBookingDateUserApplicantToList(bookingDateUserApplicant);
          }
          else
          {
            // BookingDateUserApplicant is NOT for current Report Group. Create a new group and add BookingDateUserApplicant to the group's list. 
            bookingDateUserApplicantReportGroup = new BookingDateUserApplicantReportGroup(bookingDateUserApplicant);
            bookingDateUserApplicantReportGroup.addBookingDateUserApplicantToList(bookingDateUserApplicant);
            // Add the BookingDateUserApplicantReportGroup to the list of BookingDateUserApplicantReportGroups.
            listBookingDateUserApplicantReportGroup.add(bookingDateUserApplicantReportGroup);
          }
        }
      }    
      dynaForm.set("listBookingDateUserApplicantReportGroup", listBookingDateUserApplicantReportGroup);
      forward = mapping.findForward("successByLocation");
    }
    else
    {
      forward = mapping.findForward("success");
    }

    logger.exit("Out going !!!");

    return forward;
  }

}
