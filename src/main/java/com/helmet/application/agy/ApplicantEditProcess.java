package com.helmet.application.agy;

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
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.bean.Applicant;
import com.helmet.bean.DisciplineCategoryUser;

public class ApplicantEditProcess extends ApplicantCommonProcess
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Applicant applicant = (Applicant)dynaForm.get("applicant"); 
    DisciplineCategoryUser disciplineCategory = null;
    if (!applicant.getDisciplineCategoryId().equals(0))
    {
      // Discipline Category NOT entered...
      disciplineCategory = agyService.getDisciplineCategoryUser(applicant.getDisciplineCategoryId());
    }
    prepareApplicant(applicant, disciplineCategory, agyService);
    validateApplicant(applicant, disciplineCategory, dynaForm, errors, messageResources);
    if (errors.isEmpty()) 
    {
      loadApplicant(applicant, dynaForm, errors, messageResources);
    }
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    // Get the set of new Unavailable dates from the form.
    String unavailableDates = (String)dynaForm.get("unavailableDates");
    // Test for Compliancy just before SAVE...
    ApplicantCompliancyTest applicantCompliancyTest = ApplicantCompliancyTest.getInstance();
    String existingNotes = getApplicantNotes(applicant);
    // Remove all \r characters but leave the \n characters. Or compareTo will fail.
    String notes = ((String)dynaForm.get("notes")).replaceAll("\r", "");
    StringBuffer notesStringBuffer = new StringBuffer(notes);
    
    if (existingNotes.compareTo(notes) != 0)
    {
      signAndDateNotes(notesStringBuffer);
    }
    StringBuffer reasonStringBuffer = new StringBuffer();
    Boolean compliant = applicant.getCompliant();
    applicantCompliancyTest.isApplicantCompliant(applicant, reasonStringBuffer);
    if (!compliant && applicant.getCompliant())
    {
      // Applicant has just become Compliant. Set RecentlyCompliant flag to TRUE.
      applicant.setRecentlyCompliant(true);
    }
    else
    {
      applicant.setRecentlyCompliant(false);
    }
    if (reasonStringBuffer.length() > 0)
    {
      // Applicant is NOT Compliant.
      applicantCompliancyTest.addCompliancyTestFailureReasonToNotes(reasonStringBuffer, notesStringBuffer);
    }
    else
    {
      applicantCompliancyTest.removeCompliancyTestFailureReasonFromNotes(notesStringBuffer);
    }
    int rowCount = 0;
    try
    {
      rowCount = agyService.updateApplicant(applicant, getConsultantLoggedIn().getConsultantId());
    }
    catch (DuplicateDataException e)
    {
      errors.add("applicant", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    rowCount += updateUnavailablesForApplicant(agyService, applicant, unavailableDates);

    upoadApplicantFiles(applicant, dynaForm);
    // NEW -->
    saveApplicantNotes(applicant, notesStringBuffer.toString());
    // <-- NEW
    logger.exit("Out going !!!");

    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicant.getApplicantId(), actionForward.getRedirect());

  }
   
}
