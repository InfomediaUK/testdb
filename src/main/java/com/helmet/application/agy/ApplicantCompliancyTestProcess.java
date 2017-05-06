package com.helmet.application.agy;

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
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.bean.Applicant;
import com.helmet.bean.ApplicantEntity;
import com.helmet.bean.CompliancyTest;


public class ApplicantCompliancyTestProcess extends ApplicantCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    Applicant applicant = (Applicant) dynaForm.get("applicant");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<CompliancyTest> listCompliancyTest = agyService.getCompliancyTests(true);
    ApplicantCompliancyTest applicantCompliancyTest = ApplicantCompliancyTest.getInstance();
    StringBuffer notesStringBuffer = null;
    StringBuffer reasonStringBuffer = null;
    ApplicantEntity applicantEntity = agyService.getApplicantEntity(applicant.getApplicantId());
    Boolean applicantCompliant = null;
    applicantCompliant = applicantEntity.getCompliant();
    reasonStringBuffer = new StringBuffer();
    applicantCompliancyTest.isApplicantCompliant(listCompliancyTest, applicantEntity, reasonStringBuffer);
    // Get the Notes for the Applicant from the file system.
    String existingNotes = getApplicantNotes(applicantEntity);
    notesStringBuffer = new StringBuffer(existingNotes);
    if (!applicantCompliant.equals(applicantEntity.getCompliant()))
    {
      // Applicant Compliant value has changed.
      try
      {
        // Set the Compliant and Recently Compliant flags to the same value, true or false.
        int rowCount = agyService.compliantApplicant(applicantEntity.getApplicantId(), applicantEntity.getNoOfChanges(), getConsultantLoggedIn().getConsultantId(), applicantEntity.getCompliant());
      }
      catch (DuplicateDataException e)
      {
      }
    }
    // Even though applicantCompliant flag may not have changed, the reasons may have, so save the Notes. 
    if (reasonStringBuffer.length() > 0)
    {
      // Applicant is NOT Compliant.
      applicantCompliancyTest.addCompliancyTestFailureReasonToNotes(reasonStringBuffer, notesStringBuffer);
    }
    else
    {
      applicantCompliancyTest.removeCompliancyTestFailureReasonFromNotes(notesStringBuffer);
    }
    saveApplicantNotes(applicantEntity, notesStringBuffer.toString());
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicantEntity.getApplicantId(), actionForward.getRedirect());
  }

}
