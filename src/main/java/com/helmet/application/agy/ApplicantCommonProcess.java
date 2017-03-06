package com.helmet.application.agy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.Unavailable;

public abstract class ApplicantCommonProcess extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  /*
   * Get the file extension of the supplied filename. Eg. .txt
   */
  private String getFileExtension(String filename)
  {
    int indexOfLastDot = filename.lastIndexOf(".");

    String fileExtension = "";

    if (indexOfLastDot > -1)
    {
      fileExtension = filename.substring(indexOfLastDot);
    }
    return fileExtension;
  }
  
  protected void validateApplicant(Applicant applicant, 
      DynaValidatorForm dynaForm, 
      ActionMessages errors,
      MessageResources messageResources)
  {
    if (applicant.getDisciplineCategoryId().equals(0))
    {
      // Discipline Category Required.
      errors.add("applicant", new ActionMessage("error.disciplineCategory.required"));
    }
    if (applicant.getRequiresVisa())
    {
      // Visa Required.
      if (applicant.getVisaType().equals(0))
      {
        // Error, Visa Type NOT selected.
        errors.add("applicant", new ActionMessage("error.visaType.required"));
      }
    }
    else
    {
      // Visa NOT Required.
      if (applicant.getVisaType() > 0)
      {
        // Error, Visa Type selected.
        errors.add("applicant", new ActionMessage("error.visaType.notRequired"));
      }
    }
    StringBuffer uploadFileNames = new StringBuffer();
    FormFile uploadFormFile = (FormFile)dynaForm.get("cvFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.cv"));
    }
    uploadFormFile = (FormFile)dynaForm.get("crbFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.disclosure"));
    }
    uploadFormFile = (FormFile)dynaForm.get("dbsFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.dbs"));
    }
    uploadFormFile = (FormFile)dynaForm.get("reference1FormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.reference1"));
    }
    uploadFormFile = (FormFile)dynaForm.get("reference2FormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.reference2"));
    }
    uploadFormFile = (FormFile)dynaForm.get("photoFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.photo"));
    }
    uploadFormFile = (FormFile)dynaForm.get("birthCertificateFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.birthCertificate"));
    }
    uploadFormFile = (FormFile)dynaForm.get("proofOfAddress1FormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.proofOfAddress1"));
    }
    uploadFormFile = (FormFile)dynaForm.get("proofOfAddress2FormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.proofOfAddress2"));
    }
    uploadFormFile = (FormFile)dynaForm.get("idDocumentFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.idDocument"));
    }
    uploadFormFile = (FormFile)dynaForm.get("varicellaFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.varicella"));
    }
    uploadFormFile = (FormFile)dynaForm.get("hepbFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.hepb"));
    }
    uploadFormFile = (FormFile)dynaForm.get("tbFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.tb"));
    }
    uploadFormFile = (FormFile)dynaForm.get("mmrx2FormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.mmrx2"));
    }
    uploadFormFile = (FormFile)dynaForm.get("mmrFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.mmr"));
    }
    uploadFormFile = (FormFile)dynaForm.get("ivsEppFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.ivsEpp"));
    }
    uploadFormFile = (FormFile)dynaForm.get("fitToWorkFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.fitToWork"));
    }
    uploadFormFile = (FormFile)dynaForm.get("englishTestCertificateFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.englishTestCertificate"));
    }
    uploadFormFile = (FormFile)dynaForm.get("trainingFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.training"));
    }
    uploadFormFile = (FormFile)dynaForm.get("paediatricLifeSupportFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.paediatricLifeSupport"));
    }
    uploadFormFile = (FormFile)dynaForm.get("hpcFormFile");
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      appendFileName(uploadFileNames, messageResources.getMessage("label.hpc"));
    }
    if (errors.isEmpty())
    {
      // Validation has failed. Warn user if any files are being uploaded...
      if (uploadFileNames.length() > 0)
      {
        // Upload files are lost during request/response and have to be chosen from the file system again. Warn user to do this...
        errors.add("applicant", new ActionMessage("error.uploadFiles.mustBeRechosen", uploadFileNames.toString()));
      }
    }
  }

  private void appendFileName(StringBuffer sb, String fileName)
  {
    if (sb.length() > 0)
    {
      sb.append(", ");
    }
    sb.append(fileName);
  }
  
  protected void loadApplicant(Applicant applicant, 
                               DynaValidatorForm dynaForm, 
                               ActionMessages errors,
                               MessageResources messageResources)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    FormFile uploadFormFile = null;
    String uploadFilename = null;
    String dateStr = (String) dynaForm.get("dateOfBirthStr");
    applicant.setDateOfBirth(convertDate(dateStr, sdf, errors, messageResources, "label.dateOfBirth"));
    dateStr = (String) dynaForm.get("performanceEvaluationDateStr");
    applicant.setPerformanceEvaluationDate(convertDate(dateStr, sdf, errors, messageResources, "label.performanceEvaluationDate"));
    dateStr = (String) dynaForm.get("reference2DateStr");
    applicant.setReference2Date(convertDate(dateStr, sdf, errors, messageResources, "label.reference2Date"));
    dateStr = (String) dynaForm.get("reference1DateStr");
    applicant.setReference1Date(convertDate(dateStr, sdf, errors, messageResources, "label.reference1Date"));
    dateStr = (String) dynaForm.get("fitToWorkExpiryDateStr");
    applicant.setFitToWorkExpiryDate(convertDate(dateStr, sdf, errors, messageResources, "label.fitToWorkExpiryDate"));
    dateStr = (String) dynaForm.get("idDocumentExpiryDateStr");
    applicant.setIdDocumentExpiryDate(convertDate(dateStr, sdf, errors, messageResources, "label.idDocumentExpiryDate"));
    dateStr = (String) dynaForm.get("trainingExpiryDateStr");
    applicant.setTrainingExpiryDate(convertDate(dateStr, sdf, errors, messageResources, "label.trainingExpiryDate"));
    dateStr = (String) dynaForm.get("crbExpiryDateStr");
    applicant.setCrbExpiryDate(convertDate(dateStr, sdf, errors, messageResources, "label.crbExpiryDate"));
    dateStr = (String) dynaForm.get("crbIssueDateStr");
    applicant.setCrbIssueDate(convertDate(dateStr, sdf, errors, messageResources, "label.crbIssueDate"));
    dateStr = (String) dynaForm.get("registeredWithDbsDateStr");
    applicant.setRegisteredWithDbsDate(convertDate(dateStr, sdf, errors, messageResources, "label.registeredWithDbsDateStr"));
    dateStr = (String) dynaForm.get("dbsRenewalDateStr");
    applicant.setDbsRenewalDate(convertDate(dateStr, sdf, errors, messageResources, "label.dbsRenewalDate"));    
    dateStr = (String) dynaForm.get("hpcExpiryDateStr");
    applicant.setHpcExpiryDate(convertDate(dateStr, sdf, errors, messageResources, "label.hpcExpiryDate"));
    dateStr = (String) dynaForm.get("hpcLastCheckedDateStr");
    applicant.setHpcLastCheckedDate(convertDate(dateStr, sdf, errors, messageResources, "label.hpcLastCheckedDate"));
    dateStr = (String) dynaForm.get("interviewDateStr");
    applicant.setInterviewDate(convertDate(dateStr, sdf, errors, messageResources, "label.interviewDate"));
    dateStr = (String) dynaForm.get("assessment12WeekDateStr");
    applicant.setAssessment12WeekDate(convertDate(dateStr, sdf, errors, messageResources, "label.assessment12WeekDate"));
    dateStr = (String) dynaForm.get("availabilityDateStr");
    applicant.setAvailabilityDate(convertDate(dateStr, sdf, errors, messageResources, "label.availabilityDate"));
    dateStr = (String) dynaForm.get("arrivalInCountryDateStr");
    applicant.setArrivalInCountryDate(convertDate(dateStr, sdf, errors, messageResources, "label.arrivalInUKDate"));
    dateStr = (String) dynaForm.get("visaExpiryDateStr");
    applicant.setVisaExpiryDate(convertDate(dateStr, sdf, errors, messageResources, "label.visaExpiryDate"));
    dateStr = (String) dynaForm.get("drivingLicenseExpiryDateStr");
    applicant.setDrivingLicenseExpiryDate(convertDate(dateStr, sdf, errors, messageResources, "label.drivingLicenseExpiryDate"));
    dateStr = (String) dynaForm.get("paediatricLifeSupportIssuedDateStr");
    applicant.setPaediatricLifeSupportIssuedDate(convertDate(dateStr, sdf, errors, messageResources, "label.paediatricLifeSupportIssuedDate"));
    uploadFormFile = (FormFile)dynaForm.get("varicellaFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setVaricellaFilename("varicella" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("hepbFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setHepbFilename("hepb" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("tbFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setTbFilename("tb" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("mmrx2FormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setMmrx2Filename("mmrx2" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("mmrFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setMmrFilename("mmr" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("reference2FormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setReference2Filename("reference2" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("reference1FormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setReference1Filename("reference1" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("cvFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setCvFilename("cv" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("birthCertificateFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setBirthCertificateFilename("birthcertificate" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("proofOfAddress1FormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setProofOfAddress1Filename("proofofaddress1" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("proofOfAddress2FormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setProofOfAddress2Filename("proofofaddress2" + getFileExtension(uploadFilename));
    }    
    uploadFormFile = (FormFile)dynaForm.get("fitToWorkFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setFitToWorkFilename("fittowork" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("idDocumentFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setIdDocumentFilename("iddocument" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("trainingFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setTrainingFilename("training" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("crbFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setCrbFilename("crb" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("hpcFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setHpcFilename("hpc" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("dbsFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setDbsFilename("dbs" + getFileExtension(uploadFilename));
    }
    // NEW -->
    uploadFormFile = (FormFile)dynaForm.get("ivsEppFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setIvsEppFilename("ivsepp" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("paediatricLifeSupportFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setPaediatricLifeSupportFilename("paediatriclifesupport" + getFileExtension(uploadFilename));
    }
    uploadFormFile = (FormFile)dynaForm.get("englishTestCertificateFormFile");
    uploadFilename = uploadFormFile.getFileName();
    if (StringUtils.isNotEmpty(uploadFilename))
    {
      applicant.setEnglishTestCertificateFilename("englishtestcertificate" + getFileExtension(uploadFilename));
    }
  }

  protected void upoadApplicantFiles(Applicant applicant, DynaValidatorForm dynaForm)
  {
    controlUploadFile(dynaForm, "varicellaFormFile", applicant.getVaricellaFileUrl());
    controlUploadFile(dynaForm, "hepbFormFile", applicant.getHepbFileUrl());
    controlUploadFile(dynaForm, "tbFormFile", applicant.getTbFileUrl());
    controlUploadFile(dynaForm, "mmrx2FormFile", applicant.getMmrx2FileUrl());
    controlUploadFile(dynaForm, "mmrFormFile", applicant.getMmrFileUrl());
    controlUploadFile(dynaForm, "reference2FormFile", applicant.getReference2FileUrl());
    controlUploadFile(dynaForm, "reference1FormFile", applicant.getReference1FileUrl());
    controlUploadFile(dynaForm, "photoFormFile", applicant.getPhotoFileUrl());
    controlUploadFile(dynaForm, "cvFormFile", applicant.getCvFileUrl());
    controlUploadFile(dynaForm, "birthCertificateFormFile", applicant.getBirthCertificateFileUrl());
    controlUploadFile(dynaForm, "proofOfAddress1FormFile", applicant.getProofOfAddress1FileUrl());
    controlUploadFile(dynaForm, "proofOfAddress2FormFile", applicant.getProofOfAddress2FileUrl());
    controlUploadFile(dynaForm, "fitToWorkFormFile", applicant.getFitToWorkFileUrl());
    controlUploadFile(dynaForm, "idDocumentFormFile", applicant.getIdDocumentFileUrl());
    controlUploadFile(dynaForm, "trainingFormFile", applicant.getTrainingFileUrl());
    controlUploadFile(dynaForm, "crbFormFile", applicant.getCrbFileUrl());
    controlUploadFile(dynaForm, "hpcFormFile", applicant.getHpcFileUrl());
    controlUploadFile(dynaForm, "dbsFormFile", applicant.getDbsFileUrl());
    controlUploadFile(dynaForm, "ivsEppFormFile", applicant.getIvsEppFileUrl());
    controlUploadFile(dynaForm, "paediatricLifeSupportFormFile", applicant.getPaediatricLifeSupportFileUrl());
    controlUploadFile(dynaForm, "englishTestCertificateFormFile", applicant.getEnglishTestCertificateFileUrl());
  }
 
  private void controlUploadFile(DynaValidatorForm dynaForm, String formFile, String fileUrl)
  {
    FormFile uploadFormFile = null;
    uploadFormFile = (FormFile)dynaForm.get(formFile);
    if (StringUtils.isNotEmpty(uploadFormFile.getFileName()))
    {
      uploadFile(uploadFormFile, fileUrl);
    }
  }
  
  private void uploadFile(FormFile formFile, String fileUrl)
  {
    String filePath = FileHandler.getInstance().getApplicantFileLocation() + fileUrl;
    FileOutputStream fileOutputStream = null;
    // Read the InputStream and store it in a FileOutputStream.
    try
    {
      byte[] fileData = formFile.getFileData();

      File folder = new File(filePath).getParentFile();
      if (!folder.exists())
      {
        // Create any required directories.
        folder.mkdirs();
      }
      try
      {
        fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(fileData);
      }
      finally
      {
        fileOutputStream.close();
      }
    }
    catch (IOException e)
    {
      logger.error("IOException - uploading " + filePath);
    }
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
  
  protected List<Unavailable> getApplicantUnavailables(AgyService agyService, Applicant applicant, boolean showOnlyActive)
  {
    // Set Calendar to Start of Today one year ago.
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new java.util.Date());
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.YEAR, -1);
    java.util.Date fromDate = calendar.getTime();
    // Set Calendar to End of Today 1 year ahead.
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    calendar.add(Calendar.YEAR, 2);
    java.util.Date toDate = calendar.getTime();
    // Get ONLY the Unavailables for the date range.
    return agyService.getUnavailablesForApplicantInDateRange(applicant.getAgencyId(), applicant.getApplicantId(), showOnlyActive, fromDate, toDate);
  }

  // Very similar code to the following method exists in com.helmet.application.zap.ApplicantUnavailableProcess so if you change
  // it here you will have to change it there probably.
  protected int updateUnavailablesForApplicant(AgyService agyService, Applicant applicant, String unavailableDates)
  {
    int rowCount = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // Load ALL Unavailable records (Active & Inactive) for the Applicant into a list.
    List<Unavailable> listUnavailableDates = getApplicantUnavailables(agyService, applicant, false);
    // Build a HashMap of all the existing Unavailable records keyed by the yyyy-mm-dd date.
    Map<String, Unavailable> mapUnavailableDates = new HashMap<String, Unavailable>();
    for (Unavailable unavailable : listUnavailableDates)
    {
      mapUnavailableDates.put(sdf.format(unavailable.getUnavailableDate()), unavailable);
    }
    StringTokenizer st = new StringTokenizer(unavailableDates, ",");
    Unavailable unavailable = null;
    String date = null;
    while (st.hasMoreTokens())
    {
      // For each new Unavailable date...
      date = st.nextToken();
      unavailable = mapUnavailableDates.get(date);
      if (unavailable == null)
      {
        // The date had NO Unavailable record before, so it is new. Insert an Unavailable record for the date.
        unavailable = new Unavailable();
        unavailable.setAgencyId(applicant.getAgencyId());
        unavailable.setApplicantId(applicant.getApplicantId());
        unavailable.setUnavailableDate(java.sql.Date.valueOf(date));
        rowCount += agyService.insertUnavailable(unavailable, getConsultantLoggedIn().getConsultantId());
      }
      else
      {
        // An Unavailable record already exists for the date.
        if (unavailable.getActive() == false)
        {
          // The Unavailable record was NOT active. Make it active now.
          unavailable.setActive(true);
          rowCount += agyService.updateUnavailable(unavailable, getConsultantLoggedIn().getConsultantId());
        }
        // Now remove the Unavailable record from the List and Map.
        mapUnavailableDates.remove(date);
        listUnavailableDates.remove(unavailable);
      }
    }
    if (listUnavailableDates.size() > 0)
    {
      // The only ACTIVE Unavailable records in the List now are for dates that the Applicant is now available.
      for (Unavailable available : listUnavailableDates)
      {
        if (available.getActive())
        {
          available.setActive(false);
          rowCount += agyService.updateUnavailable(available, getConsultantLoggedIn().getConsultantId());
        }        
      }
    }    
    return rowCount;
  }
  
  protected void signAndDateNotes(StringBuffer notesStringBuffer)
  {
    if (notesStringBuffer.length() > 0)
    {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
      Calendar calendar = Calendar.getInstance();
      notesStringBuffer.append("\n[");
      notesStringBuffer.append(sdf.format(new Date(calendar.getTimeInMillis())));
      notesStringBuffer.append(" ");
      notesStringBuffer.append(getConsultantLoggedIn().getUser().getFullName());
      notesStringBuffer.append("]\n");
    }

  }
}
