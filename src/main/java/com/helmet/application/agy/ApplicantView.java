package com.helmet.application.agy;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
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
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.comparator.AgencyWorkerChecklistFileComparator;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.Unavailable;


public class ApplicantView extends ApplicantCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    logger.entry("In coming !!!");

    Applicant applicant = (Applicant) dynaForm.get("applicant");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    applicant = agyService.getApplicant(applicant.getApplicantId());

    if (applicant == null) { return mapping.findForward("illegalaccess"); }

    Boolean showWeekly = (Boolean) dynaForm.get("showWeekly");

    Date startOfWeekDate = null;
    Date endOfWeekDate = null;
    Integer weekToShow = new Integer(0);
    
    if (showWeekly)
    {
      // showing weekly so determine which week to show 0=current, -1=last week, 1=next week
      weekToShow = (Integer) dynaForm.get("weekToShow");
      // find out this weeks monday

      startOfWeekDate = Utilities.getStartOfWeek(weekToShow);
      endOfWeekDate = Utilities.getEndOfWeek(weekToShow);

      dynaForm.set("startDate", startOfWeekDate);
      dynaForm.set("endDate", endOfWeekDate);

    }

    List<BookingDateUserApplicant> list = agyService.getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(applicant.getApplicantId(), getConsultantLoggedIn().getAgencyId(), startOfWeekDate, endOfWeekDate);
    // Get ONLY the ACTIVE Unavailables from one year ago to one year in the future.
    String unavailableDates = getUnavailablesForApplicant(agyService, applicant);

    // in ancestor class
    sortShiftTotals(list, dynaForm);

    List <BookingBookingDateUserApplicant> listBookingBookingDateUserApplicant = loadBookingBookingDateUserApplicantList(applicant.getApplicantId(), list, weekToShow);
    
    List<AgencyWorkerChecklistFile> agencyWorkerChecklists = loadAgencyWorkerChecklists(FileHandler.getInstance().getApplicantFileLocation() + FileHandler.getInstance().getApplicantFileFolder() + "/" + applicant.getApplicantId(), applicant.getApplicantId());
    Collections.sort(agencyWorkerChecklists, new AgencyWorkerChecklistFileComparator());
    dynaForm.set("applicant", applicant);
    String notes = getApplicantNotes(applicant).toString();
    dynaForm.set("notes", notes.replaceAll("\n", "<br />"));
    dynaForm.set("list", listBookingBookingDateUserApplicant);
    dynaForm.set("agencyWorkerChecklists", agencyWorkerChecklists);
    dynaForm.set("unavailableDates", unavailableDates);

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
