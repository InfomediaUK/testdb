package com.helmet.application.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.FileHandler;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;
import com.helmet.bean.Consultant;
import com.helmet.persistence.Utilities;

public class AgencyApplicantCopyProcess extends AdminAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    Agency sourceAgency = (Agency)dynaForm.get("sourceAgency");
    Agency targetAgency = (Agency)dynaForm.get("targetAgency");
    String consultantIdStr = (String)dynaForm.get("consultantId");
    Integer consultantId = StringUtils.isEmpty(consultantIdStr) ? null : Integer.parseInt(consultantIdStr);
    Consultant consultant = adminService.getConsultant(consultantId);
    sourceAgency = adminService.getAgency(sourceAgency.getAgencyId());
    targetAgency = adminService.getAgency(targetAgency.getAgencyId());
    List<Applicant> agencyApplicantCopyList = new ArrayList<Applicant>();
    Enumeration paramNames = request.getParameterNames();
    int rowCount = 0;
    Integer sourceApplicantId = null;
    while(paramNames.hasMoreElements()) 
    {
      String paramName = (String)paramNames.nextElement();
      if (paramName.equals("applicantId"))
      {
        int applicantId = 0;
        String[] paramValues = request.getParameterValues(paramName);
        for(int i = 0; i < paramValues.length; i++) 
        {
          // For each Applicant to be copied...
          applicantId = Integer.parseInt(paramValues[i]);
          Applicant applicant = adminService.getApplicant(applicantId);
          if (applicant == null)
          {
            // Applicant NOT found is a disaster...
          }
          else
          {
            // Applicant found...
            agencyApplicantCopyList.add(applicant);
          }
        }
      }
    }
    // 
    for (Applicant applicant : agencyApplicantCopyList)
    {
      sourceApplicantId = applicant.getApplicantId();
      applicant.setApplicantId(null);
      applicant.setAgencyId(targetAgency.getAgencyId());
      applicant.setMobileNumber(null);
      applicant.setTelephoneNumber(null);
      applicant.setPerformanceEvaluation(false);    // 6 Week Assessment.
      applicant.setPerformanceEvaluationDate(null); // 6 Week Assessment Date.
      applicant.setAssessment12Week(false);
      applicant.setAssessment12WeekDate(null);
      applicant.setInterviewDate(null);
      applicant.setOriginalAgencyId(sourceAgency.getAgencyId());
//      applicant.getUser().setLogin(temp);
//      applicant.getUser().setPwd(temp);
//      applicant.getUser().setPwdHint(temp);
//      applicant.getUser().setSecretWord(temp);  // *** Could use existing secret word.
      try
      {
        rowCount = adminService.insertApplicant(applicant, consultant.getConsultantId());
        copyApplicantsFilesForward(sourceApplicantId, applicant);
      }
      catch (DuplicateDataException e)
      {
//        errors.add("consultant", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
//        saveErrors(request, errors);
//        return mapping.getInputForward();
      }
    }
    dynaForm.set("sourceAgency", sourceAgency);
    dynaForm.set("sourceAgencyId", sourceAgency.getAgencyId().toString());
    dynaForm.set("targetAgency", targetAgency);
    dynaForm.set("targetAgencyId", targetAgency.getAgencyId());
    dynaForm.set("consultantId", consultantIdStr);
    dynaForm.set("list", agencyApplicantCopyList);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

  private void copyApplicantsFilesForward(Integer sourceApplicantId, Applicant targetApplicant)
  {
    String applicationFolder = FileHandler.getInstance().getApplicantFileLocation() + FileHandler.getInstance().getApplicantFileFolder();
    String sourceFolderPath = applicationFolder + "/" + sourceApplicantId;
    String targetFolderPath = applicationFolder + "/" + targetApplicant.getApplicantId();
    File sourceFolder = new File(sourceFolderPath);
    File targetFolder = new File(targetFolderPath);
    String targetFileName = null;
    File targetFile = null;
    try
    {
      if (sourceFolder.exists())
      {
        // It should exist but on test systems it may not.
        if (!targetFolder.exists())
        {
          targetFolder.mkdirs();
        }
        File[] files = sourceFolder.listFiles();
        for (File sourceFile : files)
        {
          if (sourceFile.getName().startsWith("checklist") || sourceFile.getName().equals("notes.txt"))
          {
            // Ignore checklist files and notes.txt.
          }
          else
          {
            targetFileName = targetFolderPath + "/" + sourceFile.getName();
            targetFile = new File(targetFileName);
            FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
            FileChannel targetChannel = new FileOutputStream(targetFile).getChannel();
            targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            System.out.println(sourceFile.getName());
          }
        }
      }      
    }
    catch (Exception e)
    {
      // TODO: handle exception
    }    
  }
  
}
