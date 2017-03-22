package com.helmet.application.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.application.agy.ApplicantCompliancyTest;
import com.helmet.bean.Applicant;
import com.helmet.bean.CompliancyTest;
import com.helmet.bean.IdDocument;


public class IdDocumentEditProcess extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    IdDocument idDocument = (IdDocument)dynaForm.get("idDocument");
    Boolean originalRequiresVisa = (Boolean)dynaForm.get("originalRequiresVisa");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    int rowCount = 0;
    try
    {
      rowCount = adminService.updateIdDocument(idDocument, getAdministratorLoggedIn().getAdministratorId());
    }
    catch (DuplicateDataException e)
    {
      errors.add("idDocument", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    if (rowCount == 1)
    {
      // ID Document updated successfully...
      if (!originalRequiresVisa.equals(idDocument.getRequiresVisa()))
      {
        // Requires Visa has Changed. This may affect compliancy of Applicants...
        ApplicantCompliancyTest applicantCompliancyTest = ApplicantCompliancyTest.getInstance();
        StringBuffer notesStringBuffer = null;
        StringBuffer reasonStringBuffer = null;
        List<CompliancyTest> listCompliancyTest = adminService.getCompliancyTests(true);
        List<Applicant> applicantList = adminService.getApplicantsForIdDocument(idDocument.getIdDocumentId());
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
              rowCount = adminService.compliantApplicant(applicant.getApplicantId(), applicant.getNoOfChanges(), getAdministratorLoggedIn().getAdministratorId(), applicant.getCompliant());
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
      } 
    }
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

  protected String getApplicantNotes(Applicant applicant)
  {
    String notesFileName = FileHandler.getInstance().getApplicantFileLocation() +
                           FileHandler.getInstance().getApplicantFileFolder() + 
                           "/" + applicant.getApplicantId() + "/notes.txt";
    StringBuffer notes   = new StringBuffer(); 
    Utilities.suckInFile(notesFileName, notes);
    return notes.toString();
  }
  
  protected void saveApplicantNotes(Applicant applicant, String notes)
  {
    String notesFileName = FileHandler.getInstance().getApplicantFileLocation() +
                           FileHandler.getInstance().getApplicantFileFolder() + 
                           "/" + applicant.getApplicantId() + "/notes.txt";
    File folder = new File(notesFileName).getParentFile();
    if (!folder.exists())
    {
      // Create any required directories.
      folder.mkdirs();
    }
    try
    {
      Utilities.saveFile(notesFileName, notes);
    }
    catch (IOException e)
    {
      // TODO 
      System.out.println("IOException - uploading " + notesFileName);
    }
  }

}
