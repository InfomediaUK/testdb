package com.helmet.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.persistence.Constants;

public class BookingDateUserApplicant extends BookingDateUserLong
{

  BigDecimal divisor100 = new BigDecimal(100);

  private Integer bookingGradeApplicantDateStatus;

  private Integer bookingGradeApplicantId;

  private Integer bookingGradeId;

  private BigDecimal chargeRate;

  private BigDecimal payRate;

  private BigDecimal wageRate;

  private BigDecimal wtdPercentage;

  private BigDecimal niPercentage;

  private BigDecimal chargeRateVatRate;

  private BigDecimal payRateVatRate;

  private BigDecimal wtdVatRate;

  private BigDecimal niVatRate;

  private BigDecimal commissionVatRate;

  private Integer applicantId;

  private String applicantFirstName;

  private String applicantLastName;

  private char applicantGender;

  private Date applicantDateOfBirth;

  private String applicantPhotoFilename;

  private Integer applicantOriginalAgencyId;

  private Integer agencyId;

  private String agencyCode;

  private String agencyName;

  private Integer gradeId;

  private String gradeName;

  private String cancelledByLogin;

  private String cancelledByFirstName;

  private String cancelledByLastName;

  private String invoicedByLogin;

  private String invoicedByFirstName;

  private String invoicedByLastName;

  private String rejectedByLogin;

  private String rejectedByFirstName;

  private String rejectedByLastName;

  private String authorizedByLogin;

  private String authorizedByFirstName;

  private String authorizedByLastName;

  private String activatedByLogin;

  private String activatedByFirstName;

  private String activatedByLastName;

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

  public String getAgencyCode()
  {
    return agencyCode;
  }

  public void setAgencyCode(String agencyCode)
  {
    this.agencyCode = agencyCode;
  }

  public String getApplicantFirstName()
  {
    return applicantFirstName;
  }

  public void setApplicantFirstName(String applicantFirstName)
  {
    this.applicantFirstName = applicantFirstName;
  }

  public Integer getApplicantId()
  {
    return applicantId;
  }

  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }

  public String getApplicantLastName()
  {
    return applicantLastName;
  }

  public void setApplicantLastName(String applicantLastName)
  {
    this.applicantLastName = applicantLastName;
  }

  public String getApplicantFullNameLastFirst()
  {
    return applicantLastName + ", " + applicantFirstName;
  }

  public Integer getApplicantOriginalAgencyId()
  {
    return applicantOriginalAgencyId;
  }

  public void setApplicantOriginalAgencyId(Integer applicantOriginalAgencyId)
  {
    this.applicantOriginalAgencyId = applicantOriginalAgencyId;
  }

  public Integer getBookingGradeApplicantDateStatus()
  {
    return bookingGradeApplicantDateStatus;
  }

  public void setBookingGradeApplicantDateStatus(Integer bookingGradeApplicantDateStatus)
  {
    this.bookingGradeApplicantDateStatus = bookingGradeApplicantDateStatus;
  }

  public Integer getBookingGradeApplicantId()
  {
    return bookingGradeApplicantId;
  }

  public void setBookingGradeApplicantId(Integer bookingGradeApplicantId)
  {
    this.bookingGradeApplicantId = bookingGradeApplicantId;
  }

  public Date getApplicantDateOfBirth()
  {
    return applicantDateOfBirth;
  }

  public void setApplicantDateOfBirth(Date applicantDateOfBirth)
  {
    this.applicantDateOfBirth = applicantDateOfBirth;
  }

  public char getApplicantGender()
  {
    return applicantGender;
  }

  public void setApplicantGender(char applicantGender)
  {
    this.applicantGender = applicantGender;
  }

  public String getApplicantPhotoFilename()
  {
    return applicantPhotoFilename;
  }

  public void setApplicantPhotoFilename(String applicantPhotoFilename)
  {
    this.applicantPhotoFilename = applicantPhotoFilename;
  }

  public BigDecimal getChargeRateVatRate()
  {
    return chargeRateVatRate;
  }

  public void setChargeRateVatRate(BigDecimal chargeRateVatRate)
  {
    this.chargeRateVatRate = chargeRateVatRate;
  }

  public BigDecimal getCommissionVatRate()
  {
    return commissionVatRate;
  }

  public void setCommissionVatRate(BigDecimal commissionVatRate)
  {
    this.commissionVatRate = commissionVatRate;
  }

  public BigDecimal getNiPercentage()
  {
    return niPercentage;
  }

  public void setNiPercentage(BigDecimal niPercentage)
  {
    this.niPercentage = niPercentage;
  }

  public BigDecimal getNiVatRate()
  {
    return niVatRate;
  }

  public void setNiVatRate(BigDecimal niVatRate)
  {
    this.niVatRate = niVatRate;
  }

  public BigDecimal getPayRateVatRate()
  {
    return payRateVatRate;
  }

  public void setPayRateVatRate(BigDecimal payRateVatRate)
  {
    this.payRateVatRate = payRateVatRate;
  }

  public BigDecimal getWtdPercentage()
  {
    return wtdPercentage;
  }

  public void setWtdPercentage(BigDecimal wtdPercentage)
  {
    this.wtdPercentage = wtdPercentage;
  }

  public BigDecimal getWtdVatRate()
  {
    return wtdVatRate;
  }

  public void setWtdVatRate(BigDecimal wtdVatRate)
  {
    this.wtdVatRate = wtdVatRate;
  }

  public BigDecimal getChargeRate()
  {
    return chargeRate;
  }

  public void setChargeRate(BigDecimal chargeRate)
  {
    this.chargeRate = chargeRate;
  }

  public BigDecimal getPayRate()
  {
    return payRate;
  }

  public void setPayRate(BigDecimal payRate)
  {
    this.payRate = payRate;
  }

  public BigDecimal getWageRate()
  {
    return wageRate;
  }

  public void setWageRate(BigDecimal wageRate)
  {
    this.wageRate = wageRate;
  }

  public BigDecimal getCommissionRate()
  {
    if (getChargeRate() == null || getPayeRate() == null) { return null; }
    return getChargeRate().subtract(getPayeRate());
  }

  public BigDecimal getWtdRate()
  {
    if (getPayRate() == null || getWtdPercentage() == null) { return null; }
    return Utilities.round(getPayRate().multiply(getWtdPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
  }

  public BigDecimal getNiRate()
  {
    if (getPayRate() == null || getWtdRate() == null || getNiPercentage() == null) { return null; }
    return Utilities.round((getPayRate().add(getWtdRate())).multiply(getNiPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
  }

  public BigDecimal getPayeRate()
  {
    if (getPayRate() == null || getWtdRate() == null || getNiRate() == null) { return null; }
    return getPayRate().add(getWtdRate()).add(getNiRate());
  }

  public BigDecimal getPayeRateValue()
  {
    if (getPayRateValue() == null || getWageRate() == null || getPayRate() == null) { return null; }
    return Utilities.round(getPayRateValue().multiply(getWageRate()).divide(getPayRate(), 5, RoundingMode.HALF_UP));
  }

  public String getApplicantGenderDescriptionKey()
  {
    return applicantGender == 'M' ? "label.male" : "label.female";
  }

  public String getApplicantPhotoFileUrl()
  {
    return FileHandler.getInstance().getPhotoFileFolder() + "/" + getApplicantId() + "/" + applicantPhotoFilename;
  }

  public String getBookingGradeApplicantDateStatusDescriptionKey()
  {
    return BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_DESCRIPTION_KEYS[bookingGradeApplicantDateStatus];
  }

  public String getGradeName()
  {
    return gradeName;
  }

  public void setGradeName(String gradeName)
  {
    this.gradeName = gradeName;
  }

  public Integer getBookingGradeId()
  {
    return bookingGradeId;
  }

  public void setBookingGradeId(Integer bookingGradeId)
  {
    this.bookingGradeId = bookingGradeId;
  }

  public String getActivatedByFirstName()
  {
    return activatedByFirstName;
  }

  public void setActivatedByFirstName(String activatedByFirstName)
  {
    this.activatedByFirstName = activatedByFirstName;
  }

  public String getActivatedByLastName()
  {
    return activatedByLastName;
  }

  public void setActivatedByLastName(String activatedByLastName)
  {
    this.activatedByLastName = activatedByLastName;
  }

  public String getActivatedByLogin()
  {
    return activatedByLogin;
  }

  public void setActivatedByLogin(String activatedByLogin)
  {
    this.activatedByLogin = activatedByLogin;
  }

  public String getAuthorizedByFirstName()
  {
    return authorizedByFirstName;
  }

  public void setAuthorizedByFirstName(String authorizedByFirstName)
  {
    this.authorizedByFirstName = authorizedByFirstName;
  }

  public String getAuthorizedByLastName()
  {
    return authorizedByLastName;
  }

  public void setAuthorizedByLastName(String authorizedByLastName)
  {
    this.authorizedByLastName = authorizedByLastName;
  }

  public String getAuthorizedByLogin()
  {
    return authorizedByLogin;
  }

  public void setAuthorizedByLogin(String authorizedByLogin)
  {
    this.authorizedByLogin = authorizedByLogin;
  }

  public String getCancelledByFirstName()
  {
    return cancelledByFirstName;
  }

  public void setCancelledByFirstName(String cancelledByFirstName)
  {
    this.cancelledByFirstName = cancelledByFirstName;
  }

  public String getCancelledByLastName()
  {
    return cancelledByLastName;
  }

  public void setCancelledByLastName(String cancelledByLastName)
  {
    this.cancelledByLastName = cancelledByLastName;
  }

  public String getCancelledByLogin()
  {
    return cancelledByLogin;
  }

  public void setCancelledByLogin(String cancelledByLogin)
  {
    this.cancelledByLogin = cancelledByLogin;
  }

  public String getInvoicedByFirstName()
  {
    return invoicedByFirstName;
  }

  public void setInvoicedByFirstName(String invoicedByFirstName)
  {
    this.invoicedByFirstName = invoicedByFirstName;
  }

  public String getInvoicedByLastName()
  {
    return invoicedByLastName;
  }

  public void setInvoicedByLastName(String invoicedByLastName)
  {
    this.invoicedByLastName = invoicedByLastName;
  }

  public String getInvoicedByLogin()
  {
    return invoicedByLogin;
  }

  public void setInvoicedByLogin(String invoicedByLogin)
  {
    this.invoicedByLogin = invoicedByLogin;
  }

  public String getRejectedByFirstName()
  {
    return rejectedByFirstName;
  }

  public void setRejectedByFirstName(String rejectedByFirstName)
  {
    this.rejectedByFirstName = rejectedByFirstName;
  }

  public String getRejectedByLastName()
  {
    return rejectedByLastName;
  }

  public void setRejectedByLastName(String rejectedByLastName)
  {
    this.rejectedByLastName = rejectedByLastName;
  }

  public String getRejectedByLogin()
  {
    return rejectedByLogin;
  }

  public void setRejectedByLogin(String rejectedByLogin)
  {
    this.rejectedByLogin = rejectedByLogin;
  }

  public Integer getGradeId()
  {
    return gradeId;
  }

  public void setGradeId(Integer gradeId)
  {
    this.gradeId = gradeId;
  }

  public String getLogin()
  {
    return getBookingGradeApplicantId() + "-" + getApplicantId();
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);

    setChargeRate(rs.getBigDecimal("CHARGERATE"));
    setChargeRateValue(rs.getBigDecimal("CHARGERATEVALUE"));
    setPayRate(rs.getBigDecimal("PAYRATE"));
    setPayRateValue(rs.getBigDecimal("PAYRATEVALUE"));
    setWageRate(rs.getBigDecimal("WAGERATE"));
    setWageRateValue(rs.getBigDecimal("WAGERATEVALUE"));

    setWtdPercentage(rs.getBigDecimal("WTDPERCENTAGE"));
    setNiPercentage(rs.getBigDecimal("NIPERCENTAGE"));
    setChargeRateVatRate(rs.getBigDecimal("CHARGERATEVATRATE"));
    setPayRateVatRate(rs.getBigDecimal("PAYRATEVATRATE"));
    setWtdVatRate(rs.getBigDecimal("WTDVATRATE"));
    setNiVatRate(rs.getBigDecimal("NIVATRATE"));
    setCommissionVatRate(rs.getBigDecimal("COMMISSIONVATRATE"));

    setBookingGradeApplicantDateStatus(rs.getInt("BOOKINGGRADEAPPLICANTDATESTATUS"));
    setBookingGradeApplicantId(rs.getInt("BOOKINGGRADEAPPLICANTID"));
    setBookingGradeId(rs.getInt("BOOKINGGRADEID"));
    setApplicantId(rs.getInt("APPLICANTID"));
    setApplicantFirstName(rs.getString("APPLICANTFIRSTNAME"));
    setApplicantLastName(rs.getString("APPLICANTLASTNAME"));
    setApplicantDateOfBirth(rs.getDate("APPLICANTDATEOFBIRTH"));
    setApplicantPhotoFilename(rs.getString("APPLICANTPHOTOFILENAME"));
    setApplicantOriginalAgencyId(rs.getInt("APPLICANTORIGINALAGENCYID"));
    // yuk !!!
    setApplicantGender(rs.getString("APPLICANTGENDER") == null ? Constants.sqlMale.charAt(0) : rs.getString("APPLICANTGENDER").charAt(0));
    setAgencyId(rs.getInt("AGENCYID"));
    setAgencyCode(rs.getString("AGENCYCODE"));
    setAgencyName(rs.getString("AGENCYNAME"));
    setGradeId(rs.getInt("GRADEID"));
    setGradeName(rs.getString("GRADENAME"));

    setCancelledByLogin(rs.getString("CANCELLEDBYLOGIN"));
    setCancelledByFirstName(rs.getString("CANCELLEDBYFIRSTNAME"));
    setCancelledByLastName(rs.getString("CANCELLEDBYLASTNAME"));
    setInvoicedByLogin(rs.getString("INVOICEDBYLOGIN"));
    setInvoicedByFirstName(rs.getString("INVOICEDBYFIRSTNAME"));
    setInvoicedByLastName(rs.getString("INVOICEDBYLASTNAME"));
    setRejectedByLogin(rs.getString("REJECTEDBYLOGIN"));
    setRejectedByFirstName(rs.getString("REJECTEDBYFIRSTNAME"));
    setRejectedByLastName(rs.getString("REJECTEDBYLASTNAME"));
    setAuthorizedByLogin(rs.getString("AUTHORIZEDBYLOGIN"));
    setAuthorizedByFirstName(rs.getString("AUTHORIZEDBYFIRSTNAME"));
    setAuthorizedByLastName(rs.getString("AUTHORIZEDBYLASTNAME"));
    setActivatedByLogin(rs.getString("ACTIVATEDBYLOGIN"));
    setActivatedByFirstName(rs.getString("ACTIVATEDBYFIRSTNAME"));
    setActivatedByLastName(rs.getString("ACTIVATEDBYLASTNAME"));

  }

}
