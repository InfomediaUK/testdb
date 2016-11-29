package com.helmet.application.agy;

import java.sql.Date;
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
import com.helmet.application.Utilities;
import com.helmet.bean.Applicant;

public class ApplicantBookingDatesView extends ApplicantCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Applicant applicant = null;
    Integer applicantId  = (Integer)dynaForm.get("applicantId");
    Integer weekToShow  = (Integer)dynaForm.get("weekToShow");
    String[] selectedBookingDates = (String[])dynaForm.get("selectedBookingDates");
    String bookingDateIdStrs = "";
    Date startDate = null;
    Date endDate   = null;

    for (String bookingDateIdStr : selectedBookingDates)
    {
      if (!"".equals(bookingDateIdStrs))
      {
        bookingDateIdStrs = bookingDateIdStrs + ",";
      }
      bookingDateIdStrs = bookingDateIdStrs + bookingDateIdStr;
    }

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    applicant = agyService.getApplicant(applicantId);

    if (applicant == null) { return mapping.findForward("illegalaccess"); }
    startDate = Utilities.getStartOfWeek(weekToShow);
    endDate   = Utilities.getEndOfWeek(weekToShow);

    List bookingDateUserApplicants = agyService.getBookingDateUserApplicantsForAgencyAndBookingDates(getConsultantLoggedIn().getAgencyId(), bookingDateIdStrs);

    // in ancestor class
    sortShiftTotals(bookingDateUserApplicants, dynaForm);

    List <BookingBookingDateUserApplicant> listBookingBookingDateUserApplicant = loadBookingBookingDateUserApplicantList(applicant.getApplicantId(), bookingDateUserApplicants, weekToShow);
    
    dynaForm.set("applicant", applicant);
    dynaForm.set("bookingDateIdStrs", bookingDateIdStrs);
    dynaForm.set("weekToShow", weekToShow);
    dynaForm.set("startDate", startDate);
    dynaForm.set("endDate", endDate);
    dynaForm.set("list", listBookingBookingDateUserApplicant);

    logger.exit("Out going !!!");

    return mapping.findForward("success");

  }

}