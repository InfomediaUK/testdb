package com.helmet.application.agy;

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
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.bean.Applicant;
import com.helmet.bean.CompliancyTest;


public class ApplicantsCompliancyTestProcess extends ApplicantCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    ApplicantCompliancyTest applicantCompliancyTest = ApplicantCompliancyTest.getInstance();
    StringBuffer notesStringBuffer = null;
    StringBuffer reasonStringBuffer = null;
    List<CompliancyTest> listCompliancyTest = agyService.getCompliancyTests(true);
    List<Applicant> applicantList = agyService.getApplicantsForAgency(getConsultantLoggedIn().getAgencyId());
    List<Applicant> applicantChangedList = new ArrayList<Applicant>();
    Boolean applicantCompliant = null;
    for (Applicant applicant : applicantList)
    {
      // For each Applicant...
      applicantCompliant = applicant.getCompliant();
      reasonStringBuffer = new StringBuffer();
      applicantCompliancyTest.isApplicantCompliant(listCompliancyTest, applicant, reasonStringBuffer);
      // Get the Notes for the Applicant from the file system.
      String existingNotes = getApplicantNotes(applicant);
      notesStringBuffer = new StringBuffer(existingNotes);
      if (!applicantCompliant.equals(applicant.getCompliant()))
      {
        // Applicant Compliant value has changed.
        try
        {
          // Set the Compliant and Recently Compliant flags to the same value, true or false.
          int rowCount = agyService.compliantApplicant(applicant.getApplicantId(), applicant.getNoOfChanges(), getConsultantLoggedIn().getConsultantId(), applicant.getCompliant());
          applicantChangedList.add(applicant);
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
      saveApplicantNotes(applicant, notesStringBuffer.toString());
    }
    dynaForm.set("applicantsCompliancyTestResultList", applicantChangedList);
    logger.exit("Out going !!!");
    if (applicantChangedList.isEmpty())
    {
      return mapping.findForward("emptyList");      
    }
    else
    {  
      return mapping.findForward("success");
    }
  }

}
