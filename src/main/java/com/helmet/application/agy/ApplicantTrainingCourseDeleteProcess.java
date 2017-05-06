package com.helmet.application.agy;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.ApplicantEntity;
import com.helmet.bean.ApplicantTrainingCourseUser;
import com.helmet.bean.CompliancyTest;

public class ApplicantTrainingCourseDeleteProcess extends ApplicantTrainingCourseCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    ApplicantTrainingCourseUser applicantTrainingCourseUser = (ApplicantTrainingCourseUser)dynaForm.get("applicantTrainingCourseUser");
    if (isCancelled(request))
    {
      ActionForward actionForward = mapping.findForward("cancel");
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicantTrainingCourseUser.getApplicantId(), true);
    }    
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<CompliancyTest> listCompliancyTest = agyService.getCompliancyTests(true);
    File fileApplicantTrainingCourseDocumentation = null;
    if (StringUtils.isNotEmpty(applicantTrainingCourseUser.getDocumentationFileName()))
    {
      String applicantTrainingCourseDocumentationFilePath = applicantTrainingCourseUser.getDocumentationFilePath();
      fileApplicantTrainingCourseDocumentation = new File(applicantTrainingCourseDocumentationFilePath);
    }    
    // Now delete the Nhs Backing Report record. 
    int rowCount = agyService.deleteApplicantTrainingCourse(applicantTrainingCourseUser.getApplicantTrainingCourseId(), applicantTrainingCourseUser.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());
    if (rowCount == 1)
    {
      if (fileApplicantTrainingCourseDocumentation.exists())
      {
        // Documentation exists. Delete it and its backups.
        String folderPath = applicantTrainingCourseUser.getDocumentationFileFolderPath();
        Path pathTrainingFolder = FileSystems.getDefault().getPath(folderPath);
        if (Files.exists(pathTrainingFolder))
        {
          try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(pathTrainingFolder, applicantTrainingCourseUser.getDocumentationFileName() + "*"))
          {
            for (Path path : directoryStream)
            {
              // Iterate over the paths in the folder and delete the files...
              Files.delete(path);
            }
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
        }
      } 
    }

    ApplicantEntity applicant = agyService.getApplicantEntity(applicantTrainingCourseUser.getApplicantId());
    ApplicantCompliancyTest applicantCompliancyTest = ApplicantCompliancyTest.getInstance();
    StringBuffer notesStringBuffer = new StringBuffer(getApplicantNotes(applicant));
    StringBuffer reasonStringBuffer = new StringBuffer();
    applicantCompliancyTest.isApplicantCompliant(listCompliancyTest, applicant, reasonStringBuffer);
    if (reasonStringBuffer.length() > 0)
    {
      // Applicant is NOT Compliant. Turn Off RecentlyCompliant flag.
      applicant.setRecentlyCompliant(false);
      applicantCompliancyTest.addCompliancyTestFailureReasonToNotes(reasonStringBuffer, notesStringBuffer);
    }
    rowCount = agyService.updateApplicant(applicant, getConsultantLoggedIn().getConsultantId());
    if (reasonStringBuffer.length() > 0)
    {
      // Applicant is NOT Compliant.
      saveApplicantNotes(applicant, notesStringBuffer.toString());
    }
    
    ActionForward actionForward = mapping.findForward("success");
    logger.exit("Out going !!!");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicantTrainingCourseUser.getApplicantId(), true);
  }

}
