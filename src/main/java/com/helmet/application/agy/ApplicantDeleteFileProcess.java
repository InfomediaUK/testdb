package com.helmet.application.agy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;


public class ApplicantDeleteFileProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    Integer applicantId = (Integer)dynaForm.get("applicantId");
    String fileProperty = (String)dynaForm.get("fileProperty");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Applicant applicant = agyService.getApplicant(applicantId);
    String fileUrl = getFileUrl(fileProperty, applicant);
    String newFileUrl = null;
    File oldFile = new File(fileUrl);
    newFileUrl = fileUrl + ".bak";
    File newFile = new File(newFileUrl);
    for (int i = 1; newFile.exists(); i++)
    {
      newFileUrl = fileUrl + ".bak" + i;
      newFile = new File(newFileUrl);
    }
    boolean success = oldFile.renameTo(newFile);
    if (success)
    {
      clearFilenameField(fileProperty, applicant);
      if (fileProperty.equals("crb"))
      {
        clearCrbAssociatedFields(applicant);
      }
      if (fileProperty.equals("reference1"))
      {
        clearReference1AssociatedFields(applicant);
      }
      if (fileProperty.equals("reference2"))
      {
        clearReference2AssociatedFields(applicant);
      }
      if (fileProperty.equals("birthCertificate"))
      {
        clearBirthCertificateAssociatedFields(applicant);
      }
      if (fileProperty.equals("proofOfAddress"))
      {
        clearProofOfAddressAssociatedFields(applicant);
      }
      if (fileProperty.equals("passport"))
      {
        clearPassportAssociatedFields(applicant);
      }
      if (fileProperty.equals("fitToWork"))
      {
        clearFitToWorkAssociatedFields(applicant);
      }
      if (fileProperty.equals("training"))
      {
        clearTrainingAssociatedFields(applicant);
      }
      if (fileProperty.equals("hpc"))
      {
        clearHpcAssociatedFields(applicant);
      }
      // Test for Compliancy just before SAVE...
      ApplicantCompliancyTest applicantCompliancyTest = ApplicantCompliancyTest.getInstance();
      StringBuffer notesStringBuffer = new StringBuffer(getApplicantNotes(applicant));
      StringBuffer reasonStringBuffer = new StringBuffer();
      applicantCompliancyTest.isApplicantCompliant(applicant, reasonStringBuffer);
      if (reasonStringBuffer.length() > 0)
      {
        // Applicant is NOT Compliant. Turn Off RecentlyCompliant flag.
        applicant.setRecentlyCompliant(false);
        applicantCompliancyTest.addCompliancyTestFailureReasonToNotes(reasonStringBuffer, notesStringBuffer);
      }
      int rowCount = agyService.updateApplicant(applicant, getConsultantLoggedIn().getConsultantId());
      if (reasonStringBuffer.length() > 0)
      {
        // Applicant is NOT Compliant.
        saveApplicantNotes(applicant, notesStringBuffer.toString());
      }
    }
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicant.getApplicantId(), actionForward.getRedirect());
  }

  private String getFileUrl(String fileProperty, Applicant applicant)
  {
    String fileLocation = null;
    String fileUrl = null;
    Class applicantClass = applicant.getClass();
    // It would have been really nice if ALL the files were in the same damn place!
    if (fileProperty.equals("photoFilename"))
    {
      fileLocation = FileHandler.getInstance().getPhotoFileLocation(); 
    }
    else if (fileProperty.equals("cvFilename"))
    {
      fileLocation = FileHandler.getInstance().getCvFileLocation(); 
    }
    else
    {
      fileLocation = FileHandler.getInstance().getApplicantFileLocation(); 
    }
    String methodName    = "get" + StringUtils.capitalize(fileProperty) + "FileUrl";
    try
    {
      Method method = applicantClass.getMethod(methodName, new Class[] {});
      fileUrl = fileLocation + (String)method.invoke(applicant, new Object[0]);
    }
    catch (SecurityException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NoSuchMethodException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalArgumentException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalAccessException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InvocationTargetException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fileUrl;   
  }

  private void clearFilenameField(String fileProperty, Applicant applicant)
  {
    Class applicantClass = applicant.getClass();
    String methodName    = "set" + StringUtils.capitalize(fileProperty) + "Filename";
    try
    {
      Method method = applicantClass.getMethod(methodName, String.class);
      method.invoke(applicant, (String)null);
    }
    catch (SecurityException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NoSuchMethodException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalArgumentException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalAccessException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InvocationTargetException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private void clearCrbAssociatedFields(Applicant applicant)
  {
    applicant.setCrbExpiryDate(null);
    applicant.setCrbIssueDate(null);
  }

  private void clearReference1AssociatedFields(Applicant applicant)
  {
    applicant.setReference1(null);
    applicant.setReference1Date(null);
    applicant.setReference1Status(null);
  }

  private void clearReference2AssociatedFields(Applicant applicant)
  {
    applicant.setReference2(null);
    applicant.setReference2Date(null);
    applicant.setReference2Status(null);
  }

  private void clearBirthCertificateAssociatedFields(Applicant applicant)
  {
    applicant.setBirthCertificate(false);
  }

  private void clearProofOfAddressAssociatedFields(Applicant applicant)
  {
    applicant.setProofOfAddress(false);
  }

  private void clearPassportAssociatedFields(Applicant applicant)
  {
    applicant.setPassportExpiryDate(null);
    applicant.setPassportNumber(null);
  }
  
  private void clearFitToWorkAssociatedFields(Applicant applicant)
  {
    applicant.setFitToWorkExpiryDate(null);
    applicant.setFitToWorkStatus(null);
  }
  
  private void clearTrainingAssociatedFields(Applicant applicant)
  {
    applicant.setTrainingExpiryDate(null);
    applicant.setFitToWorkStatus(null);
  }

  private void clearHpcAssociatedFields(Applicant applicant)
  {
    applicant.setHpcExpiryDate(null);
    applicant.setHpcNumber(null);
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
