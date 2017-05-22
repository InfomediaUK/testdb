package com.helmet.application.agy;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.comparator.AgencyWorkerChecklistFileComparator;
import com.helmet.bean.ApplicantEntity;
import com.helmet.bean.ApplicantTrainingCoursesInfo;
import com.helmet.bean.BookingDateUserApplicant;


public class ApplicantView extends ApplicantCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    HttpSession session = request.getSession();
    Integer applicantTab = (Integer)dynaForm.get("applicantTab");
    if (applicantTab == null)
    {
      applicantTab = (Integer)session.getAttribute("applicantTab");
      applicantTab = applicantTab == null ? 0 : applicantTab;
    }
    session.setAttribute("applicantTab", applicantTab);
    Integer weekTab = (Integer)dynaForm.get("weekTab");
    if (weekTab == null)
    {
      weekTab = (Integer)session.getAttribute("weekTab");
      weekTab = weekTab == null ? 0 : weekTab;
    }
    session.setAttribute("weekTab", weekTab);
    Integer weekToShow = (Integer)dynaForm.get("weekToShow");
    if (weekToShow == null)
    {
      // WeekToShow not a request parameter, get it from session.
      weekToShow = (Integer)session.getAttribute("weekToShow");
      weekToShow = weekToShow == null ? new Integer(0) : weekToShow;
    }
    session.setAttribute("weekToShow", weekToShow);
    ApplicantEntity applicant = (ApplicantEntity)dynaForm.get("applicant");
    Boolean showWeekly = (Boolean)dynaForm.get("showWeekly");
    Date startOfWeekDate = null;
    Date endOfWeekDate = null;
    if (showWeekly)
    {
      // Showing weekly so determine which week to show 0=current, -1=last week, 1=next week
      startOfWeekDate = Utilities.getStartOfWeek(weekToShow);
      endOfWeekDate = Utilities.getEndOfWeek(weekToShow);
      dynaForm.set("startDate", startOfWeekDate);
      dynaForm.set("endDate", endOfWeekDate);
    }
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    applicant = agyService.getApplicantEntity(applicant.getApplicantId(), startOfWeekDate, endOfWeekDate);
    if (applicant == null) 
    { 
      return mapping.findForward("illegalaccess"); 
    }
    List<BookingDateUserApplicant> list = agyService.getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(applicant.getApplicantId(), getConsultantLoggedIn().getAgencyId(), startOfWeekDate, endOfWeekDate);
    // Get ONLY the ACTIVE Unavailables from one year ago to one year in the future.
    String unavailableDates = getUnavailablesForApplicant(agyService, applicant);

    // in ancestor class
    sortShiftTotals(list, dynaForm);

    List <BookingBookingDateUserApplicant> listBookingBookingDateUserApplicant = loadBookingBookingDateUserApplicantList(applicant.getApplicantId(), list, weekToShow);
    
    List<AgencyWorkerChecklistFile> agencyWorkerChecklists = loadAgencyWorkerChecklists(FileHandler.getInstance().getApplicantFileLocation() + FileHandler.getInstance().getApplicantFileFolder() + "/" + applicant.getApplicantId(), applicant.getApplicantId());
    
    Collections.sort(agencyWorkerChecklists, new AgencyWorkerChecklistFileComparator());
    
    ApplicantTrainingCoursesInfo applicantTrainingCoursesInfo = null;
    // Get ApplicantTrainingCoursesInfo for ALL TIME not just this week.
    applicantTrainingCoursesInfo = agyService.getApplicantTrainingCoursesInfoForApplicant(applicant.getApplicantId());
    dynaForm.set("applicantTrainingCoursesInfo", applicantTrainingCoursesInfo);
    
    dynaForm.set("applicant", applicant);
    String notes = getApplicantNotes(applicant).toString();
    dynaForm.set("notes", notes.replaceAll("\n", "<br />"));
    dynaForm.set("list", listBookingBookingDateUserApplicant);
    dynaForm.set("agencyWorkerChecklists", agencyWorkerChecklists);
    dynaForm.set("unavailableDates", unavailableDates);
    dynaForm.set("applicantTab", applicantTab);
    dynaForm.set("weekTab", weekTab);
    dynaForm.set("weekToShow", weekToShow);
    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
