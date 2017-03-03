package com.helmet.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;

public class BookingGradeApplicant extends Base
{

  BigDecimal divisor100 = new BigDecimal(100);

  public final static int BOOKINGGRADEAPPLICANT_STATUS_DRAFT = 0;

  public final static int BOOKINGGRADEAPPLICANT_STATUS_OPEN = 1;

  public final static int BOOKINGGRADEAPPLICANT_STATUS_OFFERED = 2;

  public final static int BOOKINGGRADEAPPLICANT_STATUS_FILLED = 3;

  public final static int BOOKINGGRADEAPPLICANT_STATUS_REFUSED = 4;

  public final static int BOOKINGGRADEAPPLICANT_STATUS_UNSUCCESSFUL = 5;

  public final static int BOOKINGGRADEAPPLICANT_STATUS_REJECTED = 6;

  public final static int BOOKINGGRADEAPPLICANT_STATUS_WITHDRAWN = 7;

  public final static int BOOKINGGRADEAPPLICANT_STATUS_RENEGOTIATE = 8;

  public final static String[] BOOKINGGRADEAPPLICANT_STATUS_DESCRIPTION_KEYS = { "bookingGradeApplicant.status.draft", "bookingGradeApplicant.status.open", "bookingGradeApplicant.status.offered",
      "bookingGradeApplicant.status.filled", "bookingGradeApplicant.status.refused", "bookingGradeApplicant.status.unsuccessful", "bookingGradeApplicant.status.rejected",
      "bookingGradeApplicant.status.withdrawn", "bookingGradeApplicant.status.renegotiate" };

  public final static String BOOKINGGRADEAPPLICANT_CLIENT_STATUSES = BOOKINGGRADEAPPLICANT_STATUS_OPEN + "," + BOOKINGGRADEAPPLICANT_STATUS_OFFERED + "," + BOOKINGGRADEAPPLICANT_STATUS_FILLED + ","
      + BOOKINGGRADEAPPLICANT_STATUS_REFUSED + "," + BOOKINGGRADEAPPLICANT_STATUS_UNSUCCESSFUL + "," + BOOKINGGRADEAPPLICANT_STATUS_REJECTED + "," + BOOKINGGRADEAPPLICANT_STATUS_RENEGOTIATE; // csv

  private Integer bookingGradeApplicantId;

  private Integer bookingGradeId;

  private Integer applicantId;

  private Integer clientAgencyJobProfileGradeId;

  private Integer status;

  private String filename;

  private BigDecimal rate;

  private BigDecimal payRate;

  private BigDecimal wtdPercentage;

  private BigDecimal niPercentage;

  private BigDecimal wageRate;

  private BigDecimal chargeRateVatRate;

  private BigDecimal commissionVatRate;

  private BigDecimal payRateVatRate;

  private BigDecimal wtdVatRate;

  private BigDecimal niVatRate;

  private String rejectText;

  private String renegotiateText;

  private Boolean activated;

  private Date checklistCreatedTime;

  public Integer getBookingGradeApplicantId()
  {
    return bookingGradeApplicantId;
  }

  public void setBookingGradeApplicantId(Integer bookingGradeApplicantId)
  {
    this.bookingGradeApplicantId = bookingGradeApplicantId;
  }

  public Integer getBookingGradeId()
  {
    return bookingGradeId;
  }

  public void setBookingGradeId(Integer bookingGradeId)
  {
    this.bookingGradeId = bookingGradeId;
  }

  public Integer getApplicantId()
  {
    return applicantId;
  }

  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }

  public Integer getStatus()
  {
    return status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public String getFilename()
  {
    return filename;
  }

  public void setFilename(String filename)
  {
    this.filename = filename;
  }

  public BigDecimal getPayRate()
  {
    return payRate;
  }

  public void setPayRate(BigDecimal payRate)
  {
    this.payRate = payRate;
  }

  public BigDecimal getNiPercentage()
  {
    return niPercentage;
  }

  public void setNiPercentage(BigDecimal niPercentage)
  {
    this.niPercentage = niPercentage;
  }

  public BigDecimal getWtdPercentage()
  {
    return wtdPercentage;
  }

  public void setWtdPercentage(BigDecimal wtdPercentage)
  {
    this.wtdPercentage = wtdPercentage;
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

  public BigDecimal getWtdVatRate()
  {
    return wtdVatRate;
  }

  public void setWtdVatRate(BigDecimal wtdVatRate)
  {
    this.wtdVatRate = wtdVatRate;
  }

  public String getRejectText()
  {
    return rejectText;
  }

  public void setRejectText(String rejectText)
  {
    this.rejectText = rejectText;
  }

  public String getRenegotiateText()
  {
    return renegotiateText;
  }

  public void setRenegotiateText(String rejectText)
  {
    this.renegotiateText = rejectText;
  }

  public Boolean getActivated()
  {
    return activated;
  }

  public void setActivated(Boolean activated)
  {
    this.activated = activated;
  }

  public Integer getClientAgencyJobProfileGradeId()
  {
    return clientAgencyJobProfileGradeId;
  }

  public void setClientAgencyJobProfileGradeId(Integer clientAgencyJobProfileGradeId)
  {
    this.clientAgencyJobProfileGradeId = clientAgencyJobProfileGradeId;
  }

  public BigDecimal getRate()
  {
    return rate;
  }

  public void setRate(BigDecimal rate)
  {
    this.rate = rate;
  }

  public boolean getCanBeEdited()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_DRAFT || status == BOOKINGGRADEAPPLICANT_STATUS_WITHDRAWN || status == BOOKINGGRADEAPPLICANT_STATUS_RENEGOTIATE;
  }

  public boolean getCanBeDeleted()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_DRAFT || status == BOOKINGGRADEAPPLICANT_STATUS_OPEN;
  }

  public boolean getCanBeSubmitted()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_DRAFT || status == BOOKINGGRADEAPPLICANT_STATUS_WITHDRAWN;
  }

  public boolean getCanBeWithdrawn()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_OPEN;
  }

  public boolean getCanBeOffered()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_OPEN;
  }

  public boolean getCanBeRejected()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_OPEN;
  }

  public boolean getCanBeMarkedRenegotiate()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_OPEN;
  }

  public boolean getCanBeAccepted()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_OFFERED;
  }

  public boolean getCanBeRefused()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_OFFERED;
  }

  public boolean getCanBeActivated()
  {
    return getHasBeenFilled() && !getActivated();
  }

  public boolean getHasBeenFilled()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_FILLED;
  }

  public boolean getHasBeenRejected()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_REJECTED;
  }

  public boolean getCanBeRenegotiated()
  {
    return status == BOOKINGGRADEAPPLICANT_STATUS_RENEGOTIATE;
  }

  public String getStatusDescriptionKey()
  {
    return BOOKINGGRADEAPPLICANT_STATUS_DESCRIPTION_KEYS[status];
  }

  public String getDocumentUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + bookingGradeApplicantId + "/" + filename;
  }

  public String getLogin()
  {
    return getBookingGradeApplicantId() + "-" + getApplicantId();
  }

  public String getPwd()
  {
    return new SimpleDateFormat("ssmmHHSSS").format(getCreationTimestamp());
  }

  public BigDecimal getWtdRate()
  {
    return Utilities.round(getPayRate().multiply(getWtdPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
  }

  public BigDecimal getNiRate()
  {
    return Utilities.round((getPayRate().add(getWtdRate())).multiply(getNiPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
  }

  public BigDecimal getPayeRate()
  {
    return getPayRate().add(getWtdRate()).add(getNiRate());
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
    return getRate().subtract(getPayeRate());
  }

  public boolean getShowPhotoToManager()
  {
    return getHasBeenFilled();
  }

  public Date getChecklistCreatedTime()
  {
    return checklistCreatedTime;
  }

  public void setChecklistCreatedTime(Date checklistCreatedTime)
  {
    this.checklistCreatedTime = checklistCreatedTime;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setBookingGradeApplicantId(rs.getInt("BOOKINGGRADEAPPLICANTID"));
    setBookingGradeId(rs.getInt("BOOKINGGRADEID"));
    setApplicantId(rs.getInt("APPLICANTID"));
    setClientAgencyJobProfileGradeId(rs.getInt("CLIENTAGENCYJOBPROFILEGRADEID"));
    setStatus(rs.getInt("STATUS"));
    setFilename(rs.getString("FILENAME"));
    setRate(rs.getBigDecimal("RATE"));
    setPayRate(rs.getBigDecimal("PAYRATE"));
    setWtdPercentage(rs.getBigDecimal("WTDPERCENTAGE"));
    setNiPercentage(rs.getBigDecimal("NIPERCENTAGE"));
    setWageRate(rs.getBigDecimal("WAGERATE"));
    setChargeRateVatRate(rs.getBigDecimal("CHARGERATEVATRATE"));
    setCommissionVatRate(rs.getBigDecimal("COMMISSIONVATRATE"));
    setPayRateVatRate(rs.getBigDecimal("PAYRATEVATRATE"));
    setWtdVatRate(rs.getBigDecimal("WTDVATRATE"));
    setNiVatRate(rs.getBigDecimal("NIVATRATE"));
    setRejectText(rs.getString("REJECTTEXT"));
    setActivated(rs.getBoolean("ACTIVATED"));
    setRenegotiateText(rs.getString("RENEGOTIATETEXT"));
    setChecklistCreatedTime(rs.getDate("CHECKLISTCREATEDTIME"));
  }

}
