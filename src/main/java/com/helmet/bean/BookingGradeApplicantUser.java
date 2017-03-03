package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;

public class BookingGradeApplicantUser extends BookingGradeApplicant
{

  private Integer bookingId;

  private BigDecimal chargeRate;

  private String applicantFirstName;

  private String applicantLastName;

  private String applicantEmailAddress;

  private String applicantMobileNumber;

  private String applicantReference;

  private String applicantProfessionalReference;

  private Date applicantDateOfBirth;

  private String applicantNiNumber;

  private String applicantPhotoFilename;

  private char applicantGender;

  private Integer applicantOriginalAgencyId;

  private Integer agencyId;

  private String agencyName;

  private String agencyCode;

  private Integer jobProfileId;

  private String jobProfileCode;

  private String jobProfileName;

  private Integer locationId;

  private String locationCode;

  private String locationName;

  private String locationDescription;

  private Integer siteId;

  private String siteCode;

  private String siteName;

  private Integer clientId;

  private String clientCode;

  private String clientName;

  private String bookingExpensesText;

  private BigDecimal bookingNoOfHours;

  private String gradeName;

  public String getApplicantFirstName()
  {
    return applicantFirstName;
  }

  public void setApplicantFirstName(String applicantFirstName)
  {
    this.applicantFirstName = applicantFirstName;
  }

  public Date getApplicantDateOfBirth()
  {
    return applicantDateOfBirth;
  }

  public void setApplicantDateOfBirth(Date applicantDateOfBirth)
  {
    this.applicantDateOfBirth = applicantDateOfBirth;
  }

  public String getApplicantEmailAddress()
  {
    return applicantEmailAddress;
  }

  public void setApplicantEmailAddress(String applicantEmailAddress)
  {
    this.applicantEmailAddress = applicantEmailAddress;
  }

  public String getApplicantMobileNumber()
  {
    return applicantMobileNumber;
  }

  public void setApplicantMobileNumber(String applicantMobileNumber)
  {
    this.applicantMobileNumber = applicantMobileNumber;
  }

  public String getApplicantLastName()
  {
    return applicantLastName;
  }

  public void setApplicantLastName(String applicantLastName)
  {
    this.applicantLastName = applicantLastName;
  }

  public String getApplicantNiNumber()
  {
    return applicantNiNumber;
  }

  public void setApplicantNiNumber(String applicantNiNumber)
  {
    this.applicantNiNumber = applicantNiNumber;
  }

  public String getApplicantReference()
  {
    return applicantReference;
  }

  public void setApplicantReference(String applicantReference)
  {
    this.applicantReference = applicantReference;
  }

  public String getApplicantFullNameLastFirst()
  {
    return applicantLastName + ", " + applicantFirstName;
  }

  public String getApplicantFullName()
  {
    return applicantFirstName + " " + applicantLastName;
  }

  public String getAgencyCode()
  {
    return agencyCode;
  }

  public void setAgencyCode(String agencyCode)
  {
    this.agencyCode = agencyCode;
  }

  public Integer getAgencyId()
  {
    return agencyId;
  }

  public void setAgencyId(Integer agencyId)
  {
    this.agencyId = agencyId;
  }

  public String getAgencyName()
  {
    return agencyName;
  }

  public void setAgencyName(String agencyName)
  {
    this.agencyName = agencyName;
  }

  public String getApplicantPhotoFilename()
  {
    return applicantPhotoFilename;
  }

  public void setApplicantPhotoFilename(String applicantPhotoFilename)
  {
    this.applicantPhotoFilename = applicantPhotoFilename;
  }

  public String getApplicantProfessionalReference()
  {
    return applicantProfessionalReference;
  }

  public void setApplicantProfessionalReference(String applicantProfessionalReference)
  {
    this.applicantProfessionalReference = applicantProfessionalReference;
  }

  public char getApplicantGender()
  {
    return applicantGender;
  }

  public void setApplicantGender(char applicantGender)
  {
    this.applicantGender = applicantGender;
  }

  public Integer getApplicantOriginalAgencyId()
  {
    return applicantOriginalAgencyId;
  }

  public void setApplicantOriginalAgencyId(Integer applicantOriginalAgencyId)
  {
    this.applicantOriginalAgencyId = applicantOriginalAgencyId;
  }

  public Integer getBookingId()
  {
    return bookingId;
  }

  public void setBookingId(Integer bookingId)
  {
    this.bookingId = bookingId;
  }

  public String getJobProfileCode()
  {
    return jobProfileCode;
  }

  public void setJobProfileCode(String jobProfileCode)
  {
    this.jobProfileCode = jobProfileCode;
  }

  public Integer getJobProfileId()
  {
    return jobProfileId;
  }

  public void setJobProfileId(Integer jobProfileId)
  {
    this.jobProfileId = jobProfileId;
  }

  public String getJobProfileName()
  {
    return jobProfileName;
  }

  public void setJobProfileName(String jobProfileName)
  {
    this.jobProfileName = jobProfileName;
  }

  public String getLocationCode()
  {
    return locationCode;
  }

  public void setLocationCode(String locationCode)
  {
    this.locationCode = locationCode;
  }

  public Integer getLocationId()
  {
    return locationId;
  }

  public void setLocationId(Integer locationId)
  {
    this.locationId = locationId;
  }

  public String getLocationName()
  {
    return locationName;
  }

  public void setLocationName(String locationName)
  {
    this.locationName = locationName;
  }

  public String getSiteCode()
  {
    return siteCode;
  }

  public void setSiteCode(String siteCode)
  {
    this.siteCode = siteCode;
  }

  public Integer getSiteId()
  {
    return siteId;
  }

  public void setSiteId(Integer siteId)
  {
    this.siteId = siteId;
  }

  public String getSiteName()
  {
    return siteName;
  }

  public void setSiteName(String siteName)
  {
    this.siteName = siteName;
  }

  public String getClientCode()
  {
    return clientCode;
  }

  public void setClientCode(String clientCode)
  {
    this.clientCode = clientCode;
  }

  public Integer getClientId()
  {
    return clientId;
  }

  public void setClientId(Integer clientId)
  {
    this.clientId = clientId;
  }

  public String getClientName()
  {
    return clientName;
  }

  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }

  public String getLocationDescription()
  {
    return locationDescription;
  }

  public void setLocationDescription(String locationDescription)
  {
    this.locationDescription = locationDescription;
  }

  public String getBookingExpensesText()
  {
    return bookingExpensesText;
  }

  public void setBookingExpensesText(String bookingExpensesText)
  {
    this.bookingExpensesText = bookingExpensesText;
  }

  public BigDecimal getBookingNoOfHours()
  {
    return bookingNoOfHours;
  }

  public void setBookingNoOfHours(BigDecimal bookingNoOfHours)
  {
    this.bookingNoOfHours = bookingNoOfHours;
  }

  public String getGradeName()
  {
    return gradeName;
  }

  public void setGradeName(String gradeName)
  {
    this.gradeName = gradeName;
  }

  public BigDecimal getChargeRate()
  {
    return chargeRate;
  }

  public void setChargeRate(BigDecimal chargeRate)
  {
    this.chargeRate = chargeRate;
  }

  public String getApplicantGenderDescriptionKey()
  {
    return applicantGender == 'M' ? "label.male" : "label.female";
  }

  public String getApplicantPhotoFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + getApplicantId() + "/" + applicantPhotoFilename;
  }

  public String getChecklistFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() 
      + "/" + getApplicantId() 
      + "/" + "checklist" + getApplicantId() + "-" + bookingId  + "-" + getBookingGradeApplicantId() + "[" + clientName + "].000.pdf"; 
  }

  public String getChecklistFilePath()
  {
    return FileHandler.getInstance().getApplicantFileLocation() + getChecklistFileUrl(); 
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setApplicantFirstName(rs.getString("APPLICANTFIRSTNAME"));
    setApplicantLastName(rs.getString("APPLICANTLASTNAME"));
    setApplicantEmailAddress(rs.getString("APPLICANTEMAILADDRESS"));
    setApplicantMobileNumber(rs.getString("APPLICANTMOBILENUMBER"));
    setApplicantReference(rs.getString("APPLICANTREFERENCE"));
    setApplicantProfessionalReference(rs.getString("APPLICANTPROFESSIONALREFERENCE"));
    setApplicantDateOfBirth(rs.getDate("APPLICANTDATEOFBIRTH"));
    setApplicantNiNumber(rs.getString("APPLICANTNINUMBER"));
    setApplicantPhotoFilename(rs.getString("APPLICANTPHOTOFILENAME"));
    setApplicantOriginalAgencyId(rs.getInt("APPLICANTORIGINALAGENCYID"));
    setApplicantGender(rs.getString("APPLICANTGENDER").charAt(0));
    setAgencyId(rs.getInt("AGENCYID"));
    setAgencyName(rs.getString("AGENCYNAME"));
    setAgencyCode(rs.getString("AGENCYCODE"));
    setJobProfileId(rs.getInt("JOBPROFILEID"));
    setJobProfileCode(rs.getString("JOBPROFILECODE"));
    setJobProfileName(rs.getString("JOBPROFILENAME"));
    setLocationId(rs.getInt("LOCATIONID"));
    setLocationCode(rs.getString("LOCATIONCODE"));
    setLocationName(rs.getString("LOCATIONNAME"));
    setLocationDescription(rs.getString("LOCATIONDESCRIPTION"));
    setSiteId(rs.getInt("SITEID"));
    setSiteCode(rs.getString("SITECODE"));
    setSiteName(rs.getString("SITENAME"));
    setClientId(rs.getInt("CLIENTID"));
    setClientCode(rs.getString("CLIENTCODE"));
    setClientName(rs.getString("CLIENTNAME"));
    setBookingId(rs.getInt("BOOKINGID"));
    setChargeRate(rs.getBigDecimal("CHARGERATE"));
    setBookingExpensesText(rs.getString("BOOKINGEXPENSESTEXT"));
    setBookingNoOfHours(rs.getBigDecimal("BOOKINGNOOFHOURS"));
    setGradeName(rs.getString("GRADENAME"));
  }

}
