package com.helmet.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.persistence.Constants;

public class BookingGradeApplicantDateUser extends BookingGradeApplicantDate
{

  BigDecimal divisor100 = new BigDecimal(100);

  private Integer bookingId;

  private Date bookingDate;

  private BigDecimal bookingDateValue;

  private Boolean bookingDateActivated;

  private Integer bookingDateNoOfChanges;

  private Integer shiftId;

  private String shiftName;

  private String shiftCode;

  private Time shiftStartTime;

  private Time shiftEndTime;

  private Time shiftBreakStartTime;

  private Time shiftBreakEndTime;

  private BigDecimal shiftNoOfHours;

  private BigDecimal shiftBreakNoOfHours;

  private Boolean shiftUseUplift;

  private BigDecimal shiftUpliftFactor;

  private BigDecimal shiftUpliftValue;

  private BigDecimal shiftOvertimeNoOfHours = new BigDecimal(0);

  private BigDecimal shiftOvertimeUpliftFactor = new BigDecimal(1);

  private Integer bookingDateStatus;

  private Integer applicantId;

  private String applicantFirstName;

  private String applicantLastName;

  private char applicantGender;

  private Date applicantDateOfBirth;

  private String applicantPhotoFilename;

  private Integer agencyId;

  private String agencyName;

  private String agencyCode;

  private BigDecimal bookingGradeRate;

  private BigDecimal bookingGradePayRate;

  private BigDecimal bookingGradeWtdPercentage;

  private BigDecimal bookingGradeNiPercentage;

  private BigDecimal bookingGradeWageRate;

  private Integer jobProfileId;

  private String jobProfileCode;

  private String jobProfileName;

  private Integer locationId;

  private String locationCode;

  private String locationName;

  private Integer siteId;

  private String siteCode;

  private String siteName;

  private Integer clientId;

  private Integer workedStatus;

  private Time workedStartTime;

  private Time workedEndTime;

  private Time workedBreakStartTime;

  private Time workedBreakEndTime;

  private BigDecimal workedNoOfHours;

  private BigDecimal workedBreakNoOfHours;

  private BigDecimal workedChargeRateValue;

  private BigDecimal workedPayRateValue;

  private BigDecimal workedWageRateValue;

  private BigDecimal workedWtdValue;

  private BigDecimal workedNiValue;

  private BigDecimal workedCommissionValue;

  private BigDecimal workedVatValue;

  private String comment;

  private String rejectText;

  private BigDecimal expenseValue;

  private BigDecimal expenseVatValue;

  private Boolean hasUplift;

  public BigDecimal getBookingDateValue()
  {
    return bookingDateValue;
  }

  public void setBookingDateValue(BigDecimal bookingDateValue)
  {
    this.bookingDateValue = bookingDateValue;
  }

  public Time getShiftBreakEndTime()
  {
    return shiftBreakEndTime;
  }

  public void setShiftBreakEndTime(Time shiftBreakEndTime)
  {
    this.shiftBreakEndTime = shiftBreakEndTime;
  }

  public Time getShiftBreakStartTime()
  {
    return shiftBreakStartTime;
  }

  public void setShiftBreakStartTime(Time shiftBreakStartTime)
  {
    this.shiftBreakStartTime = shiftBreakStartTime;
  }

  public String getShiftCode()
  {
    return shiftCode;
  }

  public void setShiftCode(String shiftCode)
  {
    this.shiftCode = shiftCode;
  }

  public Time getShiftEndTime()
  {
    return shiftEndTime;
  }

  public void setShiftEndTime(Time shiftEndTime)
  {
    this.shiftEndTime = shiftEndTime;
  }

  public String getShiftName()
  {
    return shiftName;
  }

  public void setShiftName(String shiftName)
  {
    this.shiftName = shiftName;
  }

  public BigDecimal getShiftNoOfHours()
  {
    return shiftNoOfHours;
  }

  public void setShiftNoOfHours(BigDecimal shiftNoOfHours)
  {
    this.shiftNoOfHours = shiftNoOfHours;
  }

  public BigDecimal getShiftBreakNoOfHours()
  {
    return shiftBreakNoOfHours;
  }

  public void setShiftBreakNoOfHours(BigDecimal shiftBreakNoOfHours)
  {
    this.shiftBreakNoOfHours = shiftBreakNoOfHours;
  }

  public Time getShiftStartTime()
  {
    return shiftStartTime;
  }

  public void setShiftStartTime(Time shiftStartTime)
  {
    this.shiftStartTime = shiftStartTime;
  }

  public Date getBookingDate()
  {
    return bookingDate;
  }

  public void setBookingDate(Date bookingDate)
  {
    this.bookingDate = bookingDate;
  }

  public Integer getShiftId()
  {
    return shiftId;
  }

  public void setShiftId(Integer shiftId)
  {
    this.shiftId = shiftId;
  }

  public Integer getBookingDateStatus()
  {
    return bookingDateStatus;
  }

  public void setBookingDateStatus(Integer bookingDateStatus)
  {
    this.bookingDateStatus = bookingDateStatus;
  }

  public Integer getBookingId()
  {
    return bookingId;
  }

  public void setBookingId(Integer bookingId)
  {
    this.bookingId = bookingId;
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

  public String getApplicantFirstName()
  {
    return applicantFirstName;
  }

  public void setApplicantFirstName(String applicantFirstName)
  {
    this.applicantFirstName = applicantFirstName;
  }

  public String getApplicantFullNameLastFirst()
  {
    return applicantLastName + ", " + applicantFirstName;
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

  public BigDecimal getBookingGradeRate()
  {
    return bookingGradeRate;
  }

  public void setBookingGradeRate(BigDecimal bookingGradeRate)
  {
    this.bookingGradeRate = bookingGradeRate;
  }

  public BigDecimal getBookingGradePayRate()
  {
    return bookingGradePayRate;
  }

  public void setBookingGradePayRate(BigDecimal bookingGradePayRate)
  {
    this.bookingGradePayRate = bookingGradePayRate;
  }

  public Boolean getBookingDateActivated()
  {
    return bookingDateActivated;
  }

  public void setBookingDateActivated(Boolean bookingDateActivated)
  {
    this.bookingDateActivated = bookingDateActivated;
  }

  public Integer getBookingDateNoOfChanges()
  {
    return bookingDateNoOfChanges;
  }

  public void setBookingDateNoOfChanges(Integer bookingDateNoOfChanges)
  {
    this.bookingDateNoOfChanges = bookingDateNoOfChanges;
  }

  public Integer getClientId()
  {
    return clientId;
  }

  public void setClientId(Integer clientId)
  {
    this.clientId = clientId;
  }

  public BigDecimal getShiftUpliftFactor()
  {
    return shiftUpliftFactor;
  }

  public void setShiftUpliftFactor(BigDecimal shiftUpliftFactor)
  {
    this.shiftUpliftFactor = shiftUpliftFactor;
  }

  public BigDecimal getShiftUpliftValue()
  {
    return shiftUpliftValue;
  }

  public void setShiftUpliftValue(BigDecimal shiftUpliftValue)
  {
    this.shiftUpliftValue = shiftUpliftValue;
  }

  public Boolean getShiftUseUplift()
  {
    return shiftUseUplift;
  }

  public void setShiftUseUplift(Boolean shiftUseUplift)
  {
    this.shiftUseUplift = shiftUseUplift;
  }

  public BigDecimal getShiftOvertimeNoOfHours()
  {
    return shiftOvertimeNoOfHours;
  }

  public void setShiftOvertimeNoOfHours(BigDecimal shiftOvertimeNoOfHours)
  {
    this.shiftOvertimeNoOfHours = shiftOvertimeNoOfHours;
  }

  public BigDecimal getShiftOvertimeUpliftFactor()
  {
    return shiftOvertimeUpliftFactor;
  }

  public void setShiftOvertimeUpliftFactor(BigDecimal shiftOvertimeUpliftFactor)
  {
    this.shiftOvertimeUpliftFactor = shiftOvertimeUpliftFactor;
  }

  public boolean getIsActivated()
  {
    return getBookingDateActivated();
  }

  public boolean getBookingDateIsFilled()
  {
    return getBookingDateStatus() == BookingDate.BOOKINGDATE_STATUS_FILLED;
  }

  public boolean getBookingDateIsCancelled()
  {
    return getBookingDateStatus() == BookingDate.BOOKINGDATE_STATUS_CANCELLED;
  }

  public String getBookingDateStatusDescriptionKey()
  {
    return BookingDate.BOOKINGDATE_STATUS_DESCRIPTION_KEYS[bookingDateStatus];
  }

  public String getWorkedStatusDescriptionKey()
  {
    return BookingDate.BOOKINGDATE_WORKEDSTATUS_DESCRIPTION_KEYS[workedStatus];
  }

  public boolean getHasBeenEntered()
  {
    // start time has been entered or comment has been entered
    return workedStartTime != null || (comment != null && !"".equals(comment));
  }

  public boolean getCanBeSubmitted()
  {
    // has been entered and is draft or has been rejected
    return getHasBeenEntered() && (workedStatus == BookingDate.BOOKINGDATE_WORKEDSTATUS_DRAFT || workedStatus == BookingDate.BOOKINGDATE_WORKEDSTATUS_REJECTED);
  }

  public boolean getCanBeWithdrawn()
  {
    return workedStatus == BookingDate.BOOKINGDATE_WORKEDSTATUS_SUBMITTED;
  }

  public boolean getCanBeEdited()
  {
    // activated AND NOT cancelled AND either hasn't been entered OR is in
    // Draft/Rejected state
    return getIsActivated() && getBookingDateStatus() != BookingDate.BOOKINGDATE_STATUS_CANCELLED
        && (!getHasBeenEntered() || getWorkedStatus() == BookingDate.BOOKINGDATE_WORKEDSTATUS_DRAFT || getWorkedStatus() == BookingDate.BOOKINGDATE_WORKEDSTATUS_REJECTED);
  }

  public boolean getCanBeActivated()
  {
    return getIsFilled() && getBookingDateIsFilled() && !getBookingDateActivated();
  }

  public boolean getWorkedStatusIsRejected()
  {
    return workedStatus == BookingDate.BOOKINGDATE_WORKEDSTATUS_REJECTED;
  }

  public boolean getWorkedStatusIsDraft()
  {
    return workedStatus == BookingDate.BOOKINGDATE_WORKEDSTATUS_DRAFT;
  }

  public boolean getCanBeAuthorized()
  {
    return workedStatus == BookingDate.BOOKINGDATE_WORKEDSTATUS_SUBMITTED;
  }

  public boolean getCanBeInvoiced()
  {
    return workedStatus == BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED;
  }

  public String getApplicantGenderDescriptionKey()
  {
    return applicantGender == 'M' ? "label.male" : "label.female";
  }

  public String getApplicantPhotoFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + getApplicantId() + "/" + applicantPhotoFilename;
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

  public String getRejectText()
  {
    return rejectText;
  }

  public void setRejectText(String rejectText)
  {
    this.rejectText = rejectText;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public Time getWorkedBreakEndTime()
  {
    return workedBreakEndTime;
  }

  public void setWorkedBreakEndTime(Time workedBreakEndTime)
  {
    this.workedBreakEndTime = workedBreakEndTime;
  }

  public BigDecimal getWorkedBreakNoOfHours()
  {
    return workedBreakNoOfHours;
  }

  public void setWorkedBreakNoOfHours(BigDecimal workedBreakNoOfHours)
  {
    this.workedBreakNoOfHours = workedBreakNoOfHours;
  }

  public Time getWorkedBreakStartTime()
  {
    return workedBreakStartTime;
  }

  public void setWorkedBreakStartTime(Time workedBreakStartTime)
  {
    this.workedBreakStartTime = workedBreakStartTime;
  }

  public Time getWorkedEndTime()
  {
    return workedEndTime;
  }

  public void setWorkedEndTime(Time workedEndTime)
  {
    this.workedEndTime = workedEndTime;
  }

  public BigDecimal getWorkedNoOfHours()
  {
    return workedNoOfHours;
  }

  public void setWorkedNoOfHours(BigDecimal workedNoOfHours)
  {
    this.workedNoOfHours = workedNoOfHours;
  }

  public BigDecimal getWorkedPayRateValue()
  {
    return workedPayRateValue;
  }

  public void setWorkedPayRateValue(BigDecimal workedPayRateValue)
  {
    this.workedPayRateValue = workedPayRateValue;
  }

  public BigDecimal getWorkedPayeRateValue()
  {
    return getWorkedPayRateValue().add(getWorkedWtdValue()).add(getWorkedNiValue());
  }

  public Time getWorkedStartTime()
  {
    return workedStartTime;
  }

  public void setWorkedStartTime(Time workedStartTime)
  {
    this.workedStartTime = workedStartTime;
  }

  public Integer getWorkedStatus()
  {
    return workedStatus;
  }

  public void setWorkedStatus(Integer workedStatus)
  {
    this.workedStatus = workedStatus;
  }

  public BigDecimal getWorkedChargeRateValue()
  {
    return workedChargeRateValue;
  }

  public void setWorkedChargeRateValue(BigDecimal workedChargeRateValue)
  {
    this.workedChargeRateValue = workedChargeRateValue;
  }

  public BigDecimal getWorkedCommissionValue()
  {
    return workedCommissionValue;
  }

  public void setWorkedCommissionValue(BigDecimal workedCommissionValue)
  {
    this.workedCommissionValue = workedCommissionValue;
  }

  public BigDecimal getWorkedNiValue()
  {
    return workedNiValue;
  }

  public void setWorkedNiValue(BigDecimal workedNiValue)
  {
    this.workedNiValue = workedNiValue;
  }

  public BigDecimal getWorkedVatValue()
  {
    return workedVatValue;
  }

  public void setWorkedVatValue(BigDecimal workedVatValue)
  {
    this.workedVatValue = workedVatValue;
  }

  public BigDecimal getWorkedWtdValue()
  {
    return workedWtdValue;
  }

  public void setWorkedWtdValue(BigDecimal workedWtdValue)
  {
    this.workedWtdValue = workedWtdValue;
  }

  public BigDecimal getExpenseValue()
  {
    return expenseValue;
  }

  public void setExpenseValue(BigDecimal expenseValue)
  {
    this.expenseValue = expenseValue;
  }

  public BigDecimal getExpenseVatValue()
  {
    return expenseVatValue;
  }

  public void setExpenseVatValue(BigDecimal expenseVatValue)
  {
    this.expenseVatValue = expenseVatValue;
  }

  public BigDecimal getBookingGradeNiPercentage()
  {
    return bookingGradeNiPercentage;
  }

  public void setBookingGradeNiPercentage(BigDecimal bookingGradeNiPercentage)
  {
    this.bookingGradeNiPercentage = bookingGradeNiPercentage;
  }

  public BigDecimal getBookingGradeWtdPercentage()
  {
    return bookingGradeWtdPercentage;
  }

  public void setBookingGradeWtdPercentage(BigDecimal bookingGradeWtdPercentage)
  {
    this.bookingGradeWtdPercentage = bookingGradeWtdPercentage;
  }

  public BigDecimal getBookingGradeWtdRate()
  {
    return Utilities.round(getBookingGradePayRate().multiply(getBookingGradeWtdPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
  }

  public BigDecimal getBookingGradeNiRate()
  {
    return Utilities.round((getBookingGradePayRate().add(getBookingGradeWtdRate())).multiply(getBookingGradeNiPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
  }

  public BigDecimal getBookingGradePayeRate()
  {
    return getBookingGradePayRate().add(getBookingGradeWtdRate()).add(getBookingGradeNiRate());
  }

  public BigDecimal getWageRatePaye()
  {
    return Utilities.round(getPayRateValue().multiply(getBookingGradePayeRate()).divide(getBookingGradePayRate(), 5, RoundingMode.HALF_UP));
  }

  public BigDecimal getBookingGradeWageRate()
  {
    return bookingGradeWageRate;
  }

  public void setBookingGradeWageRate(BigDecimal bookingGradeWageRate)
  {
    this.bookingGradeWageRate = bookingGradeWageRate;
  }

  public Boolean getHasUplift()
  {
    return hasUplift;
  }

  public void setHasUplift(Boolean hasUplift)
  {
    this.hasUplift = hasUplift;
  }

  public BigDecimal getWorkedWageRateValue()
  {
    return workedWageRateValue;
  }

  public void setWorkedWageRateValue(BigDecimal workedWageRateValue)
  {
    this.workedWageRateValue = workedWageRateValue;
  }

  public Boolean getUndefinedShift()
  {
    return shiftStartTime.equals(shiftEndTime);
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setBookingId(rs.getInt("BOOKINGID"));
    setBookingDate(rs.getDate("BOOKINGDATE"));
    setBookingDateValue(rs.getBigDecimal("BOOKINGDATEVALUE"));
    setBookingDateActivated(rs.getBoolean("BOOKINGDATEACTIVATED"));
    setBookingDateNoOfChanges(rs.getInt("BOOKINGDATENOOFCHANGES"));
    setShiftId(rs.getInt("SHIFTID"));
    setShiftName(rs.getString("SHIFTNAME"));
    setShiftCode(rs.getString("SHIFTCODE"));
    setShiftStartTime(rs.getTime("SHIFTSTARTTIME"));
    setShiftEndTime(rs.getTime("SHIFTENDTIME"));
    setShiftBreakStartTime(rs.getTime("SHIFTBREAKSTARTTIME"));
    setShiftBreakEndTime(rs.getTime("SHIFTBREAKENDTIME"));
    setShiftNoOfHours(rs.getBigDecimal("SHIFTNOOFHOURS"));
    setShiftBreakNoOfHours(rs.getBigDecimal("SHIFTBREAKNOOFHOURS"));
    setShiftUseUplift(rs.getBoolean("SHIFTUSEUPLIFT"));
    setShiftUpliftFactor(rs.getBigDecimal("SHIFTUPLIFTFACTOR"));
    setShiftUpliftValue(rs.getBigDecimal("SHIFTUPLIFTVALUE"));
    setShiftOvertimeNoOfHours(rs.getBigDecimal("SHIFTOVERTIMENOOFHOURS"));
    setShiftOvertimeUpliftFactor(rs.getBigDecimal("SHIFTOVERTIMEUPLIFTFACTOR"));

    setBookingDateStatus(rs.getInt("BOOKINGDATESTATUS"));
    setApplicantId(rs.getInt("APPLICANTID"));
    setApplicantFirstName(rs.getString("APPLICANTFIRSTNAME"));
    setApplicantLastName(rs.getString("APPLICANTLASTNAME"));
    setApplicantDateOfBirth(rs.getDate("APPLICANTDATEOFBIRTH"));
    setApplicantPhotoFilename(rs.getString("APPLICANTPHOTOFILENAME"));
    // yuk !!!
    setApplicantGender(rs.getString("APPLICANTGENDER") == null ? Constants.sqlMale.charAt(0) : rs.getString("APPLICANTGENDER").charAt(0));
    setAgencyId(rs.getInt("AGENCYID"));
    setAgencyName(rs.getString("AGENCYNAME"));
    setAgencyCode(rs.getString("AGENCYCODE"));
    setJobProfileId(rs.getInt("JOBPROFILEID"));
    setJobProfileCode(rs.getString("JOBPROFILECODE"));
    setJobProfileName(rs.getString("JOBPROFILENAME"));
    setLocationId(rs.getInt("LOCATIONID"));
    setLocationCode(rs.getString("LOCATIONCODE"));
    setLocationName(rs.getString("LOCATIONNAME"));
    setSiteId(rs.getInt("SITEID"));
    setSiteCode(rs.getString("SITECODE"));
    setSiteName(rs.getString("SITENAME"));
    setBookingGradeRate(rs.getBigDecimal("BOOKINGGRADERATE"));
    setBookingGradePayRate(rs.getBigDecimal("BOOKINGGRADEPAYRATE"));
    setBookingGradeWtdPercentage(rs.getBigDecimal("BOOKINGGRADEWTDPERCENTAGE"));
    setBookingGradeNiPercentage(rs.getBigDecimal("BOOKINGGRADENIPERCENTAGE"));
    setBookingGradeWageRate(rs.getBigDecimal("BOOKINGGRADEWAGERATE"));
    setClientId(rs.getInt("CLIENTID"));

    setWorkedStatus(rs.getInt("WORKEDSTATUS"));
    setWorkedStartTime(rs.getTime("WORKEDSTARTTIME"));
    setWorkedEndTime(rs.getTime("WORKEDENDTIME"));
    setWorkedBreakStartTime(rs.getTime("WORKEDBREAKSTARTTIME"));
    setWorkedBreakEndTime(rs.getTime("WORKEDBREAKENDTIME"));
    setWorkedNoOfHours(rs.getBigDecimal("WORKEDNOOFHOURS"));
    setWorkedBreakNoOfHours(rs.getBigDecimal("WORKEDBREAKNOOFHOURS"));
    setWorkedChargeRateValue(rs.getBigDecimal("WORKEDCHARGERATEVALUE"));
    setWorkedPayRateValue(rs.getBigDecimal("WORKEDPAYRATEVALUE"));
    setWorkedWageRateValue(rs.getBigDecimal("WORKEDWAGERATEVALUE"));
    setWorkedWtdValue(rs.getBigDecimal("WORKEDWTDVALUE"));
    setWorkedNiValue(rs.getBigDecimal("WORKEDNIVALUE"));
    setWorkedCommissionValue(rs.getBigDecimal("WORKEDCOMMISSIONVALUE"));
    setWorkedVatValue(rs.getBigDecimal("WORKEDVATVALUE"));
    setComment(rs.getString("COMMENT"));
    setRejectText(rs.getString("REJECTTEXT"));

    setExpenseValue(rs.getBigDecimal("EXPENSEVALUE"));
    setExpenseVatValue(rs.getBigDecimal("EXPENSEVATVALUE"));

    setHasUplift(rs.getBoolean("HASUPLIFT"));
  }

}
