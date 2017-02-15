package com.helmet.bean;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.Constants;
import com.helmet.application.FileHandler;
import com.helmet.application.agy.AgyConstants;

public class Applicant extends Base
{

  private Integer applicantId;

  private Integer agencyId;

  private User user = new User();

  private String reference;

  private String professionalReference;

  private Date dateOfBirth;

  private String niNumber;

  private String photoFilename;

  private char gender;

  private String limitedCompanyName;

  private Boolean hideMoney = false;

  private Boolean canToggleHideMoney = false;

  private String mobileNumber;

  private Address address = new Address();

  private String countryName;

  private String telephoneNumber;

  private String varicellaFilename;

  private String hepbFilename;

  private String tbFilename;

  private String mmrx2Filename;

  private String mmrFilename;

  private Date performanceEvaluationDate;

  private Boolean performanceEvaluation = false;

  private Boolean archived = false;

  private Boolean compliant = false;

  private String reference2Filename;

  private Date reference2Date;

  private Integer reference2Status;

  private String reference2;

  private String reference1Filename;

  private Date reference1Date;

  private Integer reference1Status;

  private String reference1;

  private String cvFilename;

  private Boolean overseasPoliceClearance = false;

  private String degreeDetail;

  private Boolean degree = false;

  private String birthCertificateFilename;

  private Boolean birthCertificate = false;

  private String proofOfAddress1Filename;

  private Boolean proofOfAddress1 = false;

  private String proofOfAddress2Filename;

  private Boolean proofOfAddress2 = false;

  private Integer niNumberStatus;

  private String fitToWorkFilename;

  private Date fitToWorkExpiryDate;

  private String idDocumentFilename;

  private Date idDocumentExpiryDate;

  private String idDocumentNumber;

  private String trainingFilename;

  private Date trainingExpiryDate;

  private String crbFilename;

  private Date crbExpiryDate;

  private Date crbIssueDate;

  private String hpcFilename;

  private Date hpcExpiryDate;

  private String hpcNumber;

  private Date interviewDate;

  private Boolean assessment12Week = false;
  
  private Date assessment12WeekDate;
  
  private Date availabilityDate;
  
  private Date arrivalInCountryDate;
  
  private Date visaExpiryDate;
  
  private Integer areaOfSpecialityId;

  private Integer areaOfSpecialityId2;

  private String areaOfSpecialityName;

  private String areaOfSpecialityName2;

  private Integer geographicalRegionId;

  private String geographicalRegionName;

  private Integer disciplineCategoryId;

  private String disciplineCategoryName;
  private String visaTypeName;
  private String idDocumentName;
  private Integer fitToWorkStatus;
  private Integer clientGroup;
  private Boolean drivingLicense = false;
  private Date drivingLicenseExpiryDate;
  private Boolean hasBeenUnarchived = false;
  private Boolean requiresVisa = false;
  private Boolean recentlyCompliant = false;
  private Boolean currentlyWorking = false;
  private Boolean vatChargeable = false;
  private String bankName;
  private String bankSortCode;
  private String bankAccountName;
  private String bankAccountNumber;
  private Integer idDocument;
  private Boolean languageCompetency = false;
  private Integer fitToWorkIssuedBy;
  private String ivsEppFilename;
  private Boolean manualHandlingTraining = false;
  private Boolean basicLifeSupportTraining = false;
  private Boolean elearningTraining = false;
  private Boolean povaTraining = false;
  private Boolean neonatalLifeSupportTraining = false;
  private Integer ahpRegistrationType;
  private Date hpcLastCheckedDate;
  private Integer hpcAlertNotification;
  private String paediatricLifeSupportFilename;
  private Date paediatricLifeSupportIssuedDate;
  private Integer visaType;
  private String crbDisclosureNumber;
  private Date checklistCreatedTime;
  private String englishTestCertificateFilename;
  private List<String> agencyWorkerChecklists;
  private Integer originalAgencyId;
  private Date registeredWithDbsDate;
  // NEW -->
  private Date dbsRenewalDate;
  private String dbsFilename;
  // <-- NEW

  public Integer getApplicantId()
  {
    return applicantId;
  }

  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }

  public Integer getAgencyId()
  {
    return agencyId;
  }

  public void setAgencyId(Integer agencyId)
  {
    this.agencyId = agencyId;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public Date getDateOfBirth()
  {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  public String getNiNumber()
  {
    return niNumber;
  }

  public void setNiNumber(String niNumber)
  {
    this.niNumber = niNumber;
  }

  public String getPhotoFilename()
  {
    return photoFilename;
  }

  public void setPhotoFilename(String photoFilename)
  {
    this.photoFilename = photoFilename;
  }

  public String getReference()
  {
    return reference;
  }

  public void setReference(String reference)
  {
    this.reference = reference;
  }

  public String getProfessionalReference()
  {
    return professionalReference;
  }

  public void setProfessionalReference(String professionalReference)
  {
    this.professionalReference = professionalReference;
  }

  public char getGender()
  {
    return gender;
  }

  public void setGender(char gender)
  {
    this.gender = gender;
  }

  public String getLimitedCompanyName()
  {
    return limitedCompanyName;
  }

  public void setLimitedCompanyName(String limitedCompanyName)
  {
    this.limitedCompanyName = limitedCompanyName;
  }

  public String getMobileNumber()
  {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber)
  {
    this.mobileNumber = mobileNumber;
  }

  public Boolean getHideMoney()
  {
    return hideMoney;
  }

  public void setHideMoney(Boolean hideMoney)
  {
    this.hideMoney = hideMoney;
  }

  public Boolean getCanToggleHideMoney()
  {
    return canToggleHideMoney;
  }

  public void setCanToggleHideMoney(Boolean canToggleHideMoney)
  {
    this.canToggleHideMoney = canToggleHideMoney;
  }

  public String getGenderDescriptionKey()
  {
    return gender == 'M' ? "label.male" : "label.female";
  }

  /**
   * Deprcated. Use getPhotoFileUrl() instead.
   * @return
   */
  public String getPhotoDocumentUrl()
  {
    return FileHandler.getInstance().getPhotoFileFolder() + "/" + applicantId + "/" + photoFilename;
  }

  public String getPhotoFileUrl()
  {
    return FileHandler.getInstance().getPhotoFileFolder() + "/" + applicantId + "/" + photoFilename;
  }

  public Address getAddress()
  {
    return address;
  }

  public void setAddress(Address address)
  {
    this.address = address;
  }

  public String getCountryName()
  {
    return countryName;
  }

  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }

  public String getTelephoneNumber()
  {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber)
  {
    this.telephoneNumber = telephoneNumber;
  }

  public Boolean getArchived()
  {
    return archived;
  }

  public void setArchived(Boolean archived)
  {
    this.archived = archived;
  }

  public Boolean getBirthCertificate()
  {
    return birthCertificate;
  }

  public void setBirthCertificate(Boolean birthCertificate)
  {
    this.birthCertificate = birthCertificate;
  }

  public String getBirthCertificateFilename()
  {
    return birthCertificateFilename;
  }

  public void setBirthCertificateFilename(String birthCertificateFilename)
  {
    this.birthCertificateFilename = birthCertificateFilename;
  }

  public String getBirthCertificateFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + birthCertificateFilename;
  }

  public Boolean getCompliant()
  {
    return compliant;
  }

  public void setCompliant(Boolean compliant)
  {
    this.compliant = compliant;
  }

  public Date getCrbExpiryDate()
  {
    return crbExpiryDate;
  }

  public void setCrbExpiryDate(Date crbExpiryDate)
  {
    this.crbExpiryDate = crbExpiryDate;
  }

  public String getCrbFilename()
  {
    return crbFilename;
  }

  public void setCrbFilename(String crbFilename)
  {
    this.crbFilename = crbFilename;
  }

  public String getCrbFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + crbFilename;
  }

  public Date getCrbIssueDate()
  {
    return crbIssueDate;
  }

  public void setCrbIssueDate(Date crbIssueDate)
  {
    this.crbIssueDate = crbIssueDate;
  }

  public String getCvFilename()
  {
    return cvFilename;
  }

  public void setCvFilename(String cvFilename)
  {
    this.cvFilename = cvFilename;
  }

  public String getCvFileUrl()
  {
    return FileHandler.getInstance().getCvFileFolder() + "/" + applicantId + "/" + cvFilename;
//    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + cvFilename;
  }

  public Boolean getDegree()
  {
    return degree;
  }

  public void setDegree(Boolean degree)
  {
    this.degree = degree;
  }

  public String getDegreeDetail()
  {
    return degreeDetail;
  }

  public void setDegreeDetail(String degreeDetail)
  {
    this.degreeDetail = degreeDetail;
  }

  public Date getFitToWorkExpiryDate()
  {
    return fitToWorkExpiryDate;
  }

  public void setFitToWorkExpiryDate(Date fitToWorkExpiryDate)
  {
    this.fitToWorkExpiryDate = fitToWorkExpiryDate;
  }

  public String getFitToWorkFilename()
  {
    return fitToWorkFilename;
  }

  public void setFitToWorkFilename(String fitToWorkFilename)
  {
    this.fitToWorkFilename = fitToWorkFilename;
  }

  public String getFitToWorkFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + fitToWorkFilename;
  }

  public String getHepbFilename()
  {
    return hepbFilename;
  }

  public void setHepbFilename(String hepbFilename)
  {
    this.hepbFilename = hepbFilename;
  }

  public String getHepbFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + hepbFilename;
  }

  public Date getHpcExpiryDate()
  {
    return hpcExpiryDate;
  }

  public void setHpcExpiryDate(Date hpcExpiryDate)
  {
    this.hpcExpiryDate = hpcExpiryDate;
  }

  public String getHpcFilename()
  {
    return hpcFilename;
  }

  public void setHpcFilename(String hpcFilename)
  {
    this.hpcFilename = hpcFilename;
  }

  public String getHpcFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + hpcFilename;
  }

  public String getHpcNumber()
  {
    return hpcNumber;
  }

  public void setHpcNumber(String hpcNumber)
  {
    this.hpcNumber = hpcNumber;
  }

  public Date getInterviewDate()
  {
    return interviewDate;
  }

  public void setInterviewDate(Date interviewDate)
  {
    this.interviewDate = interviewDate;
  }

  public String getAreaOfSpecialityName()
  {
    return areaOfSpecialityName;
  }

  public void setAreaOfSpecialityName(String areaOfSpecialityName)
  {
    this.areaOfSpecialityName = areaOfSpecialityName;
  }

  public String getAreaOfSpecialityName2()
  {
    return areaOfSpecialityName2;
  }

  public void setAreaOfSpecialityName2(String areaOfSpecialityName2)
  {
    this.areaOfSpecialityName2 = areaOfSpecialityName2;
  }

  public Integer getDisciplineCategoryId()
  {
    return disciplineCategoryId;
  }

  public void setDisciplineCategoryId(Integer disciplineCategoryId)
  {
    this.disciplineCategoryId = disciplineCategoryId;
  }

  public String getDisciplineCategoryName()
  {
    return disciplineCategoryName;
  }

  public void setDisciplineCategoryName(String disciplineCategoryName)
  {
    this.disciplineCategoryName = disciplineCategoryName;
  }

  public String getIdDocumentName()
  {
    return idDocumentName;
  }

  public void setIdDocumentName(String idDocumentName)
  {
    this.idDocumentName = idDocumentName;
  }

  public String getVisaTypeName()
  {
    return visaTypeName;
  }

  public void setVisaTypeName(String visaTypeName)
  {
    this.visaTypeName = visaTypeName;
  }

  public Integer getGeographicalRegionId()
  {
    return geographicalRegionId;
  }

  public void setGeographicalRegionId(Integer geographicalRegionId)
  {
    this.geographicalRegionId = geographicalRegionId;
  }

  public String getGeographicalRegionName()
  {
    return geographicalRegionName;
  }

  public void setGeographicalRegionName(String geographicalRegionName)
  {
    this.geographicalRegionName = geographicalRegionName;
  }

  public Date getArrivalInCountryDate()
  {
    return arrivalInCountryDate;
  }

  public void setArrivalInCountryDate(Date arrivalInCountryDate)
  {
    this.arrivalInCountryDate = arrivalInCountryDate;
  }

  public Boolean getAssessment12Week()
  {
    return assessment12Week;
  }

  public void setAssessment12Week(Boolean assessment12Week)
  {
    this.assessment12Week = assessment12Week;
  }

  public Date getAssessment12WeekDate()
  {
    return assessment12WeekDate;
  }

  public void setAssessment12WeekDate(Date assessment12WeekDate)
  {
    this.assessment12WeekDate = assessment12WeekDate;
  }

  public Date getAvailabilityDate()
  {
    return availabilityDate;
  }

  public void setAvailabilityDate(Date availabilityDate)
  {
    this.availabilityDate = availabilityDate;
  }

  public Date getVisaExpiryDate()
  {
    return visaExpiryDate;
  }

  public void setVisaExpiryDate(Date visaExpiryDate)
  {
    this.visaExpiryDate = visaExpiryDate;
  }

  public Integer getAreaOfSpecialityId()
  {
    return areaOfSpecialityId;
  }

  public void setAreaOfSpecialityId(Integer areaOfSpecialityId)
  {
    this.areaOfSpecialityId = areaOfSpecialityId;
  }

  public Integer getAreaOfSpecialityId2()
  {
    return areaOfSpecialityId2;
  }

  public void setAreaOfSpecialityId2(Integer areaOfSpecialityId2)
  {
    this.areaOfSpecialityId2 = areaOfSpecialityId2;
  }

  public String getMmrFilename()
  {
    return mmrFilename;
  }

  public void setMmrFilename(String mmrFilename)
  {
    this.mmrFilename = mmrFilename;
  }

  public String getMmrFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + mmrFilename;
  }

  public String getMmrx2Filename()
  {
    return mmrx2Filename;
  }

  public void setMmrx2Filename(String mmrx2Filename)
  {
    this.mmrx2Filename = mmrx2Filename;
  }

  public String getMmrx2FileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + mmrx2Filename;
  }

  public Integer getNiNumberStatus()
  {
    return niNumberStatus;
  }

  public void setNiNumberStatus(Integer niNumberStatus)
  {
    this.niNumberStatus = niNumberStatus;
  }

  public Boolean getOverseasPoliceClearance()
  {
    return overseasPoliceClearance;
  }

  public void setOverseasPoliceClearance(Boolean overseasPoliceClearance)
  {
    this.overseasPoliceClearance = overseasPoliceClearance;
  }

  public Date getIdDocumentExpiryDate()
  {
    return idDocumentExpiryDate;
  }

  public void setIdDocumentExpiryDate(Date idDocumentExpiryDate)
  {
    this.idDocumentExpiryDate = idDocumentExpiryDate;
  }

  public String getIdDocumentFilename()
  {
    return idDocumentFilename;
  }

  public void setIdDocumentFilename(String idDocumentFilename)
  {
    this.idDocumentFilename = idDocumentFilename;
  }

  public String getIdDocumentFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + idDocumentFilename;
  }

  public String getIdDocumentNumber()
  {
    return idDocumentNumber;
  }

  public void setIdDocumentNumber(String idDocumentNumber)
  {
    this.idDocumentNumber = idDocumentNumber;
  }

  public Boolean getPerformanceEvaluation()
  {
    return performanceEvaluation;
  }

  public void setPerformanceEvaluation(Boolean performanceEvaluation)
  {
    this.performanceEvaluation = performanceEvaluation;
  }

  public Date getPerformanceEvaluationDate()
  {
    return performanceEvaluationDate;
  }

  public void setPerformanceEvaluationDate(Date performanceEvaluationDate)
  {
    this.performanceEvaluationDate = performanceEvaluationDate;
  }

  public Boolean getProofOfAddress1()
  {
    return proofOfAddress1;
  }

  public void setProofOfAddress1(Boolean proofOfAddress1)
  {
    this.proofOfAddress1 = proofOfAddress1;
  }

  public String getProofOfAddress1Filename()
  {
    return proofOfAddress1Filename;
  }

  public void setProofOfAddress1Filename(String proofOfAddress1Filename)
  {
    this.proofOfAddress1Filename = proofOfAddress1Filename;
  }

  public String getProofOfAddress1FileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + proofOfAddress1Filename;
  }

  public Boolean getProofOfAddress2()
  {
    return proofOfAddress2;
  }

  public void setProofOfAddress2(Boolean proofOfAddress2)
  {
    this.proofOfAddress2 = proofOfAddress2;
  }

  public String getProofOfAddress2Filename()
  {
    return proofOfAddress2Filename;
  }

  public void setProofOfAddress2Filename(String proofOfAddress2Filename)
  {
    this.proofOfAddress2Filename = proofOfAddress2Filename;
  }

  public String getProofOfAddress2FileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + proofOfAddress2Filename;
  }
  
  public String getReference1()
  {
    return reference1;
  }

  public void setReference1(String reference1)
  {
    this.reference1 = reference1;
  }

  public Date getReference1Date()
  {
    return reference1Date;
  }

  public void setReference1Date(Date reference1Date)
  {
    this.reference1Date = reference1Date;
  }

  public String getReference1Filename()
  {
    return reference1Filename;
  }

  public void setReference1Filename(String reference1Filename)
  {
    this.reference1Filename = reference1Filename;
  }

  public String getReference1FileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + reference1Filename;
  }

  public Integer getReference1Status()
  {
    return reference1Status;
  }

  public void setReference1Status(Integer reference1Status)
  {
    this.reference1Status = reference1Status;
  }

  public String getReference2()
  {
    return reference2;
  }

  public void setReference2(String reference2)
  {
    this.reference2 = reference2;
  }

  public Date getReference2Date()
  {
    return reference2Date;
  }

  public void setReference2Date(Date reference2Date)
  {
    this.reference2Date = reference2Date;
  }

  public String getReference2Filename()
  {
    return reference2Filename;
  }

  public void setReference2Filename(String reference2Filename)
  {
    this.reference2Filename = reference2Filename;
  }

  public String getReference2FileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + reference2Filename;
  }

  public Integer getReference2Status()
  {
    return reference2Status;
  }

  public void setReference2Status(Integer reference2Status)
  {
    this.reference2Status = reference2Status;
  }

  public String getTbFilename()
  {
    return tbFilename;
  }

  public void setTbFilename(String tbFilename)
  {
    this.tbFilename = tbFilename;
  }

  public String getTbFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + tbFilename;
  }

  public Date getTrainingExpiryDate()
  {
    return trainingExpiryDate;
  }

  public void setTrainingExpiryDate(Date trainingExpiryDate)
  {
    this.trainingExpiryDate = trainingExpiryDate;
  }

  public String getTrainingFilename()
  {
    return trainingFilename;
  }

  public void setTrainingFilename(String trainingFilename)
  {
    this.trainingFilename = trainingFilename;
  }

  public String getTrainingFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + trainingFilename;
  }

  public String getVaricellaFilename()
  {
    return varicellaFilename;
  }

  public void setVaricellaFilename(String varicellaFilename)
  {
    this.varicellaFilename = varicellaFilename;
  }

  public String getVaricellaFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + varicellaFilename;
  }

  public Integer getClientGroup()
  {
    return clientGroup;
  }

  public void setClientGroup(Integer clientGroup)
  {
    this.clientGroup = clientGroup;
  }

  public Boolean getDrivingLicense()
  {
    return drivingLicense;
  }

  public void setDrivingLicense(Boolean drivingLicense)
  {
    this.drivingLicense = drivingLicense;
  }

  public Date getDrivingLicenseExpiryDate()
  {
    return drivingLicenseExpiryDate;
  }

  public void setDrivingLicenseExpiryDate(Date drivingLicenseExpiryDate)
  {
    this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
  }

  public Integer getFitToWorkStatus()
  {
    return fitToWorkStatus;
  }

  public void setFitToWorkStatus(Integer fitToWorkStatus)
  {
    this.fitToWorkStatus = fitToWorkStatus;
  }

  public Boolean getHasBeenUnarchived()
  {
    return hasBeenUnarchived;
  }

  public void setHasBeenUnarchived(Boolean hasBeenUnarchived)
  {
    this.hasBeenUnarchived = hasBeenUnarchived;
  }

  public Boolean getRequiresVisa()
  {
    return requiresVisa;
  }

  public void setRequiresVisa(Boolean requiresVisa)
  {
    this.requiresVisa = requiresVisa;
  }

  public Boolean getRecentlyCompliant()
  {
    return recentlyCompliant;
  }

  public void setRecentlyCompliant(Boolean recentlyCompliant)
  {
    this.recentlyCompliant = recentlyCompliant;
  }

  public Boolean getCurrentlyWorking()
  {
    return currentlyWorking;
  }

  public void setCurrentlyWorking(Boolean currentlyWorking)
  {
    this.currentlyWorking = currentlyWorking;
  }

  public Boolean getVatChargeable()
  {
    return vatChargeable;
  }

  public void setVatChargeable(Boolean vatChargeable)
  {
    this.vatChargeable = vatChargeable;
  }

  public String getBankName()
  {
    return bankName;
  }

  public void setBankName(String bankName)
  {
    this.bankName = bankName;
  }

  public String getBankSortCode()
  {
    return bankSortCode;
  }

  public void setBankSortCode(String bankSortCode)
  {
    this.bankSortCode = bankSortCode;
  }

  public String getBankAccountName()
  {
    return bankAccountName;
  }

  public void setBankAccountName(String bankAccountName)
  {
    this.bankAccountName = bankAccountName;
  }

  public String getBankAccountNumber()
  {
    return bankAccountNumber;
  }

  public void setBankAccountNumber(String bankAccountNumber)
  {
    this.bankAccountNumber = bankAccountNumber;
  }

  public Boolean getLanguageCompetency()
  {
    return languageCompetency;
  }

  public void setLanguageCompetency(Boolean languageCompetency)
  {
    this.languageCompetency = languageCompetency;
  }

  public Integer getIdDocument()
  {
    return idDocument;
  }

  public void setIdDocument(Integer idDocument)
  {
    this.idDocument = idDocument;
  }

  public Integer getFitToWorkIssuedBy()
  {
    return fitToWorkIssuedBy;
  }

  public void setFitToWorkIssuedBy(Integer fitToWorkIssuedBy)
  {
    this.fitToWorkIssuedBy = fitToWorkIssuedBy;
  }

  public String getIvsEppFilename()
  {
    return ivsEppFilename;
  }

  public void setIvsEppFilename(String ivsEppFilename)
  {
    this.ivsEppFilename = ivsEppFilename;
  }

  public String getIvsEppFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + ivsEppFilename;
  }

  public Boolean getBasicLifeSupportTraining()
  {
    return basicLifeSupportTraining;
  }

  public void setBasicLifeSupportTraining(Boolean basicLifeSupportTraining)
  {
    this.basicLifeSupportTraining = basicLifeSupportTraining;
  }

  public Boolean getElearningTraining()
  {
    return elearningTraining;
  }

  public void setElearningTraining(Boolean elearningTraining)
  {
    this.elearningTraining = elearningTraining;
  }

  public Boolean getManualHandlingTraining()
  {
    return manualHandlingTraining;
  }

  public void setManualHandlingTraining(Boolean manualHandlingTraining)
  {
    this.manualHandlingTraining = manualHandlingTraining;
  }

  public Boolean getPovaTraining()
  {
    return povaTraining;
  }

  public void setPovaTraining(Boolean povaTraining)
  {
    this.povaTraining = povaTraining;
  }

  public Boolean getNeonatalLifeSupportTraining()
  {
    return neonatalLifeSupportTraining;
  }

  public void setNeonatalLifeSupportTraining(Boolean neonatalLifeSupportTraining)
  {
    this.neonatalLifeSupportTraining = neonatalLifeSupportTraining;
  }

  public Integer getAhpRegistrationType()
  {
    return ahpRegistrationType;
  }

  public void setAhpRegistrationType(Integer ahpRegistrationType)
  {
    this.ahpRegistrationType = ahpRegistrationType;
  }

  public Integer getHpcAlertNotification()
  {
    return hpcAlertNotification;
  }

  public void setHpcAlertNotification(Integer hpcAlertNotification)
  {
    this.hpcAlertNotification = hpcAlertNotification;
  }

  public Date getHpcLastCheckedDate()
  {
    return hpcLastCheckedDate;
  }

  public void setHpcLastCheckedDate(Date hpcLastCheckedDate)
  {
    this.hpcLastCheckedDate = hpcLastCheckedDate;
  }

  public String getPaediatricLifeSupportFilename()
  {
    return paediatricLifeSupportFilename;
  }

  public void setPaediatricLifeSupportFilename(String paediatricLifeSupportFilename)
  {
    this.paediatricLifeSupportFilename = paediatricLifeSupportFilename;
  }

  public String getPaediatricLifeSupportFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + paediatricLifeSupportFilename;
  }

  public Date getPaediatricLifeSupportIssuedDate()
  {
    return paediatricLifeSupportIssuedDate;
  }

  public void setPaediatricLifeSupportIssuedDate(Date paediatricLifeSupportIssuedDate)
  {
    this.paediatricLifeSupportIssuedDate = paediatricLifeSupportIssuedDate;
  }

  public Integer getVisaType()
  {
    return visaType;
  }

  public void setVisaType(Integer visaType)
  {
    this.visaType = visaType;
  }

  public String getCrbDisclosureNumber()
  {
    return crbDisclosureNumber;
  }

  public void setCrbDisclosureNumber(String crbDisclosureNumber)
  {
    this.crbDisclosureNumber = crbDisclosureNumber;
  }

  public Date getChecklistCreatedTime()
  {
    return checklistCreatedTime;
  }

  public void setChecklistCreatedTime(Date checklistCreatedTime)
  {
    this.checklistCreatedTime = checklistCreatedTime;
  }

  public String getEnglishTestCertificateFilename()
  {
    return englishTestCertificateFilename;
  }

  public void setEnglishTestCertificateFilename(String englishTestCertificateFilename)
  {
    this.englishTestCertificateFilename = englishTestCertificateFilename;
  }

  public String getEnglishTestCertificateFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + englishTestCertificateFilename;
  }

  public String getChecklistFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + "checklist" + applicantId + ".pdf"; 
  }

  public String getChecklistFilePath()
  {
    return FileHandler.getInstance().getApplicantFileLocation() + getChecklistFileUrl(); 
  }

  public List<String> getAgencyWorkerChecklists()
  {
    return agencyWorkerChecklists;
  }

  public void setAgencyWorkerChecklists(List<String> agencyWorkerChecklists)
  {
    this.agencyWorkerChecklists = agencyWorkerChecklists;
  }

  public Integer getOriginalAgencyId()
  {
    return originalAgencyId;
  }

  public void setOriginalAgencyId(Integer originalAgencyId)
  {
    this.originalAgencyId = originalAgencyId;
  }

  public Date getRegisteredWithDbsDate()
  {
    return registeredWithDbsDate;
  }

  public void setRegisteredWithDbsDate(Date registeredWithDbsDate)
  {
    this.registeredWithDbsDate = registeredWithDbsDate;
  }

  public Date getDbsRenewalDate()
  {
    return dbsRenewalDate;
  }

  public void setDbsRenewalDate(Date dbsRenewalDate)
  {
    this.dbsRenewalDate = dbsRenewalDate;
  }

  public String getDbsFilename()
  {
    return dbsFilename;
  }

  public void setDbsFilename(String dbsFilename)
  {
    this.dbsFilename = dbsFilename;
  }

  public String getDbsFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/" + dbsFilename;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setApplicantId(rs.getInt("APPLICANTID"));
    setAgencyId(rs.getInt("AGENCYID"));
    getUser().setFirstName(rs.getString("FIRSTNAME"));
    getUser().setLastName(rs.getString("LASTNAME"));
    getUser().setEmailAddress(rs.getString("EMAILADDRESS"));
    getUser().setLogin(rs.getString("LOGIN"));
    getUser().setPwd(rs.getString("PWD"));
    getUser().setPwdHint(rs.getString("PWDHINT"));
    getUser().setSecretWord(rs.getString("SECRETWORD"));
    getUser().setShowPageHelp(rs.getBoolean("SHOWPAGEHELP"));
    setReference(rs.getString("REFERENCE"));
    setProfessionalReference(rs.getString("PROFESSIONALREFERENCE"));
    setDateOfBirth(rs.getDate("DATEOFBIRTH"));
    setNiNumber(rs.getString("NINUMBER"));
    setPhotoFilename(rs.getString("PHOTOFILENAME"));
    setGender(rs.getString("GENDER").charAt(0));
    setLimitedCompanyName(rs.getString("LIMITEDCOMPANYNAME"));
    setHideMoney(rs.getBoolean("HIDEMONEY"));
    setCanToggleHideMoney(rs.getBoolean("CANTOGGLEHIDEMONEY"));
    setMobileNumber(rs.getString("MOBILENUMBER"));
    getAddress().setAddress1(rs.getString("ADDRESS1"));
    getAddress().setAddress2(rs.getString("ADDRESS2"));
    getAddress().setAddress3(rs.getString("ADDRESS3"));
    getAddress().setAddress4(rs.getString("ADDRESS4"));
    getAddress().setPostalCode(rs.getString("POSTALCODE"));
    getAddress().setCountryId(rs.getInt("COUNTRYID"));
    setCountryName(rs.getString("COUNTRYNAME"));
    setTelephoneNumber(rs.getString("TELEPHONENUMBER"));
    setArchived(rs.getBoolean("ARCHIVED"));
    setBirthCertificate(rs.getBoolean("BIRTHCERTIFICATE"));
    setBirthCertificateFilename(rs.getString("BIRTHCERTIFICATEFILENAME"));
    setCompliant(rs.getBoolean("COMPLIANT"));
    setCrbExpiryDate(rs.getDate("CRBEXPIRYDATE"));
    setCrbFilename(rs.getString("CRBFILENAME"));
    setCrbIssueDate(rs.getDate("CRBISSUEDATE"));
    setCvFilename(rs.getString("CVFILENAME"));
    setDegree(rs.getBoolean("DEGREE"));
    setDegreeDetail(rs.getString("DEGREEDETAIL"));
    setFitToWorkExpiryDate(rs.getDate("FITTOWORKEXPIRYDATE"));
    setFitToWorkFilename(rs.getString("FITTOWORKFILENAME"));
    setHepbFilename(rs.getString("HEPBFILENAME"));
    setHpcExpiryDate(rs.getDate("HPCEXPIRYDATE"));
    setHpcFilename(rs.getString("HPCFILENAME"));
    setHpcNumber(rs.getString("HPCNUMBER"));
    setInterviewDate(rs.getDate("INTERVIEWDATE"));
    setMmrFilename(rs.getString("MMRFILENAME"));
    setMmrx2Filename(rs.getString("MMRX2FILENAME"));
    setNiNumberStatus(rs.getInt("NINUMBERSTATUS"));
    setOverseasPoliceClearance(rs.getBoolean("OVERSEASPOLICECLEARANCE"));
    setIdDocumentExpiryDate(rs.getDate("IDDOCUMENTEXPIRYDATE"));
    setIdDocumentFilename(rs.getString("IDDOCUMENTFILENAME"));
    setIdDocumentNumber(rs.getString("IDDOCUMENTNUMBER"));
    setPerformanceEvaluation(rs.getBoolean("PERFORMANCEEVALUATION"));
    setPerformanceEvaluationDate(rs.getDate("PERFORMANCEEVALUATIONDATE"));
    setProofOfAddress1(rs.getBoolean("PROOFOFADDRESS1"));
    setProofOfAddress1Filename(rs.getString("PROOFOFADDRESS1FILENAME"));
    setProofOfAddress2(rs.getBoolean("PROOFOFADDRESS2"));
    setProofOfAddress2Filename(rs.getString("PROOFOFADDRESS2FILENAME"));
    setReference1(rs.getString("REFERENCE1"));
    setReference1Date(rs.getDate("REFERENCE1DATE"));
    setReference1Filename(rs.getString("REFERENCE1FILENAME"));
    setReference1Status(rs.getInt("REFERENCE1STATUS"));
    setReference2(rs.getString("REFERENCE2"));
    setReference2Date(rs.getDate("REFERENCE2DATE"));
    setReference2Filename(rs.getString("REFERENCE2FILENAME"));
    setReference2Status(rs.getInt("REFERENCE2STATUS"));
    setTbFilename(rs.getString("TBFILENAME"));
    setTrainingExpiryDate(rs.getDate("TRAININGEXPIRYDATE"));
    setTrainingFilename(rs.getString("TRAININGFILENAME"));
    setVaricellaFilename(rs.getString("VARICELLAFILENAME"));
    setAssessment12Week(rs.getBoolean("ASSESSMENT12WEEK"));
    setAssessment12WeekDate(rs.getDate("ASSESSMENT12WEEKDATE"));
    setAvailabilityDate(rs.getDate("AVAILABILITYDATE"));
    setArrivalInCountryDate(rs.getDate("ARRIVALINCOUNTRYDATE"));
    setVisaExpiryDate(rs.getDate("VISAEXPIRYDATE"));
    setAreaOfSpecialityId(rs.getInt("AREAOFSPECIALITYID"));
    setAreaOfSpecialityId2(rs.getInt("AREAOFSPECIALITYID2"));
    setAreaOfSpecialityName(rs.getString("AREAOFSPECIALITYNAME"));
    setAreaOfSpecialityName2(rs.getString("AREAOFSPECIALITYNAME2"));
    setGeographicalRegionId(rs.getInt("GEOGRAPHICALREGIONID"));
    setGeographicalRegionName(rs.getString("GEOGRAPHICALREGIONNAME"));
    setDisciplineCategoryId(rs.getInt("DISCIPLINECATEGORYID"));
    setDisciplineCategoryName(rs.getString("DISCIPLINECATEGORYNAME"));
    setIdDocumentName(rs.getString("IDDOCUMENTNAME"));
    setVisaTypeName(rs.getString("VISATYPENAME"));
    setClientGroup(rs.getInt("CLIENTGROUP"));
    setDrivingLicense(rs.getBoolean("DRIVINGLICENSE"));
    setDrivingLicenseExpiryDate(rs.getDate("DRIVINGLICENSEEXPIRYDATE"));
    setFitToWorkStatus(rs.getInt("FITTOWORKSTATUS"));
    setHasBeenUnarchived(rs.getBoolean("HASBEENUNARCHIVED"));
    setRequiresVisa(rs.getBoolean("REQUIRESVISA"));
    setRecentlyCompliant(rs.getBoolean("RECENTLYCOMPLIANT"));
    setCurrentlyWorking(rs.getBoolean("CURRENTLYWORKING"));
    setVatChargeable(rs.getBoolean("VATCHARGEABLE"));
    setBankName(rs.getString("BANKNAME"));
    setBankSortCode(rs.getString("BANKSORTCODE"));
    setBankAccountName(rs.getString("BANKACCOUNTNAME"));
    setBankAccountNumber(rs.getString("BANKACCOUNTNUMBER"));
    setIdDocument(rs.getInt("IDDOCUMENT"));
    setLanguageCompetency(rs.getBoolean("LANGUAGECOMPETENCY"));
    setFitToWorkIssuedBy(rs.getInt("FITTOWORKISSUEDBY"));
    setIvsEppFilename(rs.getString("IVSEPPFILENAME"));
    setManualHandlingTraining(rs.getBoolean("MANUALHANDLINGTRAINING"));
    setBasicLifeSupportTraining(rs.getBoolean("BASICLIFESUPPORTTRAINING"));
    setElearningTraining(rs.getBoolean("ELEARNINGTRAINING"));
    setPovaTraining(rs.getBoolean("POVATRAINING"));
    setNeonatalLifeSupportTraining(rs.getBoolean("NEONATALLIFESUPPORTTRAINING"));
    setAhpRegistrationType(rs.getInt("AHPREGISTRATIONTYPE"));
    setHpcLastCheckedDate(rs.getDate("HPCLASTCHECKEDDATE"));
    setHpcAlertNotification(rs.getInt("HPCALERTNOTIFICATION"));
    setPaediatricLifeSupportFilename(rs.getString("PAEDIATRICLIFESUPPORTFILENAME"));
    setPaediatricLifeSupportIssuedDate(rs.getDate("PAEDIATRICLIFESUPPORTISSUEDDATE"));
    setVisaType(rs.getInt("VISATYPE"));
    setCrbDisclosureNumber(rs.getString("CRBDISCLOSURENUMBER"));
    setChecklistCreatedTime(rs.getDate("CHECKLISTCREATEDTIME"));
    setEnglishTestCertificateFilename(rs.getString("ENGLISHTESTCERTIFICATEFILENAME"));
    getUser().setNhsStaffName(rs.getString("NHSSTAFFNAME"));
    setOriginalAgencyId(rs.getInt("ORIGINALAGENCYID"));
    setRegisteredWithDbsDate(rs.getDate("REGISTEREDWITHDBSDATE"));
    // NEW -->
    setDbsRenewalDate(rs.getDate("DBSRENEWALDATE"));
    setDbsFilename(rs.getString("DBSFILENAME"));
    // <-- NEW
  }

  public Boolean getHasCurrentDisclosure()
  {
    java.util.Date today = new java.util.Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(today);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    today = calendar.getTime();
    if (dbsRenewalDate != null && dbsRenewalDate.before(today))
    {
      // DBS overides CRB Expiry Date as it nolonger expires...
      return false; 
    }
    if (crbIssueDate != null && crbExpiryDate != null)
    {
      if (today.compareTo(crbIssueDate) >= 0 && today.compareTo(crbExpiryDate) <= 0) 
      { 
        return true; 
      }
    }    
    return false;
  }
  
  public Boolean getHasCurrentFitToWork()
  {
    return fitToWorkExpiryDate == null ? false : dateInFuture(fitToWorkExpiryDate);
  }
  
  public Boolean getHasValidProfessionalRegistration()
  {
    if (ahpRegistrationType == 2)
    {
      // HCA, no need to validate registration file or expiry date...
      return true;
    }
    // NOT HCA...
    return getHasCurrentProfessionalRegistration();
  }
  
  public Boolean getHasCurrentProfessionalRegistration()
  {
    // True: Must have hpcExpiryDate set AND hpcExpiryDate must not be before today AND hpcFilename entered.
    return hpcExpiryDate == null ? false : dateInFuture(hpcExpiryDate) && (hpcFilename != null);
  }
  
  public Boolean getHasCurrentDBS()
  {
    // True: Must have dbsRenewalDate set AND dbsRenewalDate must not be before today AND dbsFilename entered.
    return dbsRenewalDate == null ? false : dateInFuture(dbsRenewalDate) && (dbsFilename != null);
  }
  
  public Boolean getHasCurrentIdDocument()
  {
    return idDocumentExpiryDate == null ? false : dateInFuture(idDocumentExpiryDate);
  }
  
  public Boolean getHasValidIdDocument()
  { 
    Boolean validIdDocument = getHasCurrentIdDocument();
    if (validIdDocument)
    {
      // ID Document has Expiry Date in future.
      if (StringUtils.isEmpty(idDocumentNumber))
      {
        // ID Document Number NOT supplied.
        validIdDocument = false;
      }
      else
      {
        if (StringUtils.isEmpty(idDocumentFilename))
        {
          // ID Document (Image) File NOT supplied.
          validIdDocument = false;
        }
      }
    }
    if (!validIdDocument)
    {
      // Has NO valid ID Document. A Birth Certificate will do (apparently)...
      validIdDocument = getHasBirthCertificate();
    }
    return validIdDocument;
  }
  
  public Boolean getHasRightToWork()
  {
    Boolean rightToWork = getHasValidIdDocument();
    if (getIdDocument().equals(AgyConstants.BRITISH_ID_DOCUMENT))
    {
      // British person...
      if (rightToWork == false)
      {
        // British person but no valid ID Document. Check Birth Certificate and NI Number.
        rightToWork = getHasBirthCertificate() && getNiNumberReceived();
      }
    }
    return rightToWork;
  }

  public Boolean getHasOverseasPoliceClearanceIfRequired()
  { 
    Boolean cool = arrivalInCountryDate == null;
    if (!cool)
    {
      cool = getOverseasPoliceClearance();
    }
    return cool;
  }
  
  public Boolean getHasCurrentTraining()
  {
    Boolean hasCurrentTraining = false;
    if (StringUtils.isNotEmpty(trainingFilename))
    {
      // Applicant has a Training document.
      if (trainingExpiryDate != null)
      {
        // Applicant has a Training Expiry Date entered.
        if (dateInFuture(trainingExpiryDate))
        {
          // Applicant has a Training Expiry Date that is in the future.
          if (manualHandlingTraining)
          {
            // Applicant has a Manual Handling Training.
            if (basicLifeSupportTraining)
            {
              // Applicant has a Basic Life Support Training.
              if (elearningTraining || povaTraining)
              {
                // Applicant has either E Learning Training OR POVA Training.
                hasCurrentTraining = true;
              }
            }
          }
        }
      } 
    }
    return hasCurrentTraining;
  }
  
  public Boolean getHasCurrentVisa()
  {
    if (requiresVisa)
    {
      // Visa required, check that its Expiry Date is in the future.
      return visaExpiryDate == null ? false : dateInFuture(visaExpiryDate);
    }
    // Visa NOT required. The implicit visa is always 'current'.
    return true;
  }
  
  public String getHasCurrentVisaIfRequired()
  {
    if (requiresVisa)
    {
      // Visa required, check that its Expiry Date is in the future.
      if (visaExpiryDate == null)
      {
        return "No";
      }
      else
      {
        if (dateInFuture(visaExpiryDate))
        {
          return "Yes";
        }
        else
        {
          return "No";
        }
      }
    }
    // Visa NOT required. The implicit visa is always 'current'.
    return "Not Required";
  }
  
  public Boolean getHasDegree()
  {
    // True: Must have degree flag set. ***** Maybe needs degreeDetail entered too. *****
    return degree;
  }
  
  public Boolean getHasBirthCertificate()
  {
    // True: Must have birthCertificate flag set AND birthCertificateFilename entered.
    return birthCertificate;
  }
  
  public Boolean getHasProofOfAddress1()
  {
    // True: Must have proofOfAddress1 flag set AND proofOfAddress1Filename entered.
    return proofOfAddress1 == true && proofOfAddress1Filename != null;
  }
  
  public Boolean getHasProofOfAddress2()
  {
    // True: Must have proofOfAddress2 flag set AND proofOfAddress2Filename entered.
    return proofOfAddress2 == true && proofOfAddress2Filename != null;
  }
  
  public Boolean getDocumentsRequired()
  {
    Boolean documentsRequired = new Boolean(false);
    if (getHasCurrentIdDocument() == true)
    {
      if (getHasCurrentVisaIfRequired().equals("Yes") || getHasCurrentVisaIfRequired().equals("Not Required"))
      {
        if (getHasCurrentDisclosure())
        {
          if (getHasCurrentTraining())
          {
            if (getHasBirthCertificate())
            {
              if (getHasDegree())
              {
                if (getHasProofOfAddress1())
                {
                  if (getHasProofOfAddress2())
                  {
                    if (getHasOverseasPoliceClearanceIfRequired())
                    {
                      if (getNiNumberReceived())
                      {
                        
                      }
                      else
                      {
                        documentsRequired = true;
                      }
                    }
                    else
                    {
                      documentsRequired = true;
                    }
                  }
                  else
                  {
                    documentsRequired = true;
                  }
                }
                else
                {
                  documentsRequired = true;
                }
              }
              else
              {
                documentsRequired = true;
              }
            }
            else
            {
              documentsRequired = true;
            }
          }
          else
          {
            documentsRequired = true;
          }
        }
        else
        {
          documentsRequired = true;
        }
      }
      else
      {
        documentsRequired = true;
      }
    }
    else
    {
      documentsRequired = true;
    }
    return documentsRequired;
  }
  
  public Boolean getNiNumberReceived()
  {
    return niNumberStatus.equals(Constants.RECEIVED);
  }
  
  public String getEmailAddress()
  {
    return user.getEmailAddress();
  }

  public String getFirstName()
  {
    return user.getFirstName();
  }

  public String getFullName()
  {
    return user.getFullName();
  }

  public String getLastName()
  {
    return user.getLastName();
  }

  /**
   * Checks that supplied date is NOT before today.
   * 
   * @param dateToCheck
   * @return
   */
  private boolean dateInFuture(Date dateToCheck)
  {
    java.util.Date today = new java.util.Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(today);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    today = calendar.getTime();
    return today.compareTo(dateToCheck) <= 0;
  }
}
