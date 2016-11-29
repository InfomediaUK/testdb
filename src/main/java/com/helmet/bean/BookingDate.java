package com.helmet.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.Utilities;

public class BookingDate extends Base
{

  public final static int BOOKINGDATE_STATUS_DRAFT = 0;

  public final static int BOOKINGDATE_STATUS_OPEN = 1;

  public final static int BOOKINGDATE_STATUS_OFFERED = 2;

  public final static int BOOKINGDATE_STATUS_FILLED = 3;

  public final static int BOOKINGDATE_STATUS_COMPLETED = 4;

  public final static int BOOKINGDATE_STATUS_CANCELLED = 5;

  public final static int BOOKINGDATE_STATUS_CREDITED = 6;

  public final static String[] BOOKINGDATE_STATUS_DESCRIPTION_KEYS = { "bookingDate.status.draft", "bookingDate.status.open", "bookingDate.status.offered", "bookingDate.status.filled",
      "bookingDate.status.completed", "bookingDate.status.cancelled", "bookingDate.status.credited" };

  public final static String BOOKINGDATE_AGENCY_STATUSES = BOOKINGDATE_STATUS_OPEN + "," + BOOKINGDATE_STATUS_OFFERED + "," + BOOKINGDATE_STATUS_FILLED + "," + BOOKINGDATE_STATUS_COMPLETED + ","
      + BOOKINGDATE_STATUS_CANCELLED + "," + BOOKINGDATE_STATUS_CREDITED; // csv

  public final static String BOOKINGDATE_APPLICANT_STATUSES = BOOKINGDATE_STATUS_FILLED + ""; // csv

  public final static int BOOKINGDATE_WORKEDSTATUS_NULL = -1; // used by
                                                              // 'outstanding'
                                                              // links - when
                                                              // looking for
                                                              // where no times
                                                              // have been
                                                              // entered yet

  public final static int BOOKINGDATE_WORKEDSTATUS_DRAFT = 0;

  public final static int BOOKINGDATE_WORKEDSTATUS_SUBMITTED = 1;

  public final static int BOOKINGDATE_WORKEDSTATUS_AUTHORIZED = 2;

  public final static int BOOKINGDATE_WORKEDSTATUS_REJECTED = 3;

  public final static int BOOKINGDATE_WORKEDSTATUS_INVOICED = 4;

  public final static int BOOKINGDATE_WORKEDSTATUS_CANCELLED = 5;

  public final static int BOOKINGDATE_WORKEDSTATUS_CREDITED = 6;

  public final static String[] BOOKINGDATE_WORKEDSTATUS_DESCRIPTION_KEYS = { "bookingDate.workedStatus.draft", "bookingDate.workedStatus.submitted", "bookingDate.workedStatus.authorized",
      "bookingDate.workedStatus.rejected", "bookingDate.workedStatus.invoiced", "bookingDate.workedStatus.cancelled", "bookingDate.workedStatus.credited" };

  private Integer bookingDateId;

  private Integer bookingId;

  private Date bookingDate;

  private Integer shiftId;

  private String shiftName;

  private String shiftCode;

  private Boolean shiftUseUplift = false;

  private BigDecimal shiftUpliftFactor = new BigDecimal(1);

  private BigDecimal shiftUpliftValue = new BigDecimal(0);

  private Time shiftStartTime;

  private Time shiftEndTime;

  private Time shiftBreakStartTime;

  private Time shiftBreakEndTime;

  private BigDecimal shiftNoOfHours;

  private BigDecimal shiftBreakNoOfHours;

  private BigDecimal shiftOvertimeNoOfHours = new BigDecimal(0);

  private BigDecimal shiftOvertimeUpliftFactor = new BigDecimal(1);

  private BigDecimal value;

  private Integer status;

  private String cancelText;

  private Integer bookingGradeApplicantDateId;

  private Boolean activated;

  private Integer workedStatus;

  private Time workedStartTime;

  private Time workedEndTime;

  private Time workedBreakStartTime;

  private Time workedBreakEndTime;

  private BigDecimal workedNoOfHours;

  private BigDecimal workedBreakNoOfHours;

  private BigDecimal workedChargeRateValue;

  private BigDecimal workedPayRateValue;

  private BigDecimal workedWtdValue;

  private BigDecimal workedNiValue;

  private BigDecimal workedCommissionValue;

  private BigDecimal workedChargeRateVatValue;

  private BigDecimal workedPayRateVatValue;

  private BigDecimal workedWtdVatValue;

  private BigDecimal workedNiVatValue;

  private BigDecimal workedCommissionVatValue;

  private BigDecimal workedVatValue;

  private BigDecimal workedWageRateValue;

  private BigDecimal expenseValue;

  private BigDecimal expenseVatValue;

  private Integer invoiceId;

  private Integer agencyInvoiceId;

  private String rejectText;

  private String comment;

  private BigDecimal feePerShift;

  private BigDecimal feePerHour;

  private BigDecimal feePercentage;

  private BigDecimal feeValue;

  private Integer cancelledById;

  private Timestamp cancelledTimestamp;

  private Integer invoicedById;

  private Timestamp invoicedTimestamp;

  private Integer rejectedById;

  private Timestamp rejectedTimestamp;

  private Integer authorizedById;

  private Timestamp authorizedTimestamp;

  private Integer activatedById;

  private Timestamp activatedTimestamp;

  private Boolean hasUplift = false;

  private Integer agencyInvoiceCreditId;
  
  private String backingReport;

  public Integer getBookingDateId()
  {
    return bookingDateId;
  }

  public void setBookingDateId(Integer bookingDateId)
  {
    this.bookingDateId = bookingDateId;
  }

  public Integer getBookingId()
  {
    return bookingId;
  }

  public void setBookingId(Integer bookingId)
  {
    this.bookingId = bookingId;
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

  public Time getShiftBreakEndTime()
  {
    return shiftBreakEndTime;
  }

  public void setShiftBreakEndTime(Time shiftBreakEndTime)
  {
    this.shiftBreakEndTime = shiftBreakEndTime;
  }

  public BigDecimal getShiftBreakNoOfHours()
  {
    return shiftBreakNoOfHours;
  }

  public void setShiftBreakNoOfHours(BigDecimal shiftBreakNoOfHours)
  {
    this.shiftBreakNoOfHours = shiftBreakNoOfHours;
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

  public Time getShiftStartTime()
  {
    return shiftStartTime;
  }

  public void setShiftStartTime(Time shiftStartTime)
  {
    this.shiftStartTime = shiftStartTime;
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

  public Integer getStatus()
  {
    return status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public BigDecimal getValue()
  {
    return value;
  }

  public void setValue(BigDecimal value)
  {
    this.value = value;
  }

  public Boolean getActivated()
  {
    return activated;
  }

  public void setActivated(Boolean activated)
  {
    this.activated = activated;
  }

  public String getCancelText()
  {
    return cancelText;
  }

  public void setCancelText(String cancelText)
  {
    this.cancelText = cancelText;
  }

  public Integer getBookingGradeApplicantDateId()
  {
    return bookingGradeApplicantDateId;
  }

  public void setBookingGradeApplicantDateId(Integer bookingGradeApplicantDateId)
  {
    this.bookingGradeApplicantDateId = bookingGradeApplicantDateId;
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

  public BigDecimal getWorkedVatValue()
  {
    return workedVatValue;
  }

  public void setWorkedVatValue(BigDecimal workedVatValue)
  {
    this.workedVatValue = workedVatValue;
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

  public String getRejectText()
  {
    return rejectText;
  }

  public void setRejectText(String rejectText)
  {
    this.rejectText = rejectText;
  }

  public Integer getInvoiceId()
  {
    return invoiceId;
  }

  public void setInvoiceId(Integer invoiceId)
  {
    this.invoiceId = invoiceId;
  }

  public Integer getAgencyInvoiceId()
  {
    return agencyInvoiceId;
  }

  public void setAgencyInvoiceId(Integer agencyInvoiceId)
  {
    this.agencyInvoiceId = agencyInvoiceId;
  }

  public Integer getActivatedById()
  {
    return activatedById;
  }

  public void setActivatedById(Integer activatedById)
  {
    this.activatedById = activatedById;
  }

  public Integer getAuthorizedById()
  {
    return authorizedById;
  }

  public void setAuthorizedById(Integer authorizedById)
  {
    this.authorizedById = authorizedById;
  }

  public Integer getCancelledById()
  {
    return cancelledById;
  }

  public void setCancelledById(Integer cancelledById)
  {
    this.cancelledById = cancelledById;
  }

  public Integer getInvoicedById()
  {
    return invoicedById;
  }

  public void setInvoicedById(Integer invoicedById)
  {
    this.invoicedById = invoicedById;
  }

  public Integer getRejectedById()
  {
    return rejectedById;
  }

  public void setRejectedById(Integer rejectedById)
  {
    this.rejectedById = rejectedById;
  }

  public BigDecimal getWorkedPayeRateValue()
  {
    return getWorkedPayRateValue() == null ? new BigDecimal(0) : getWorkedPayRateValue().add(getWorkedWtdValue()).add(getWorkedNiValue());
  }

  public BigDecimal getWorkedTotalNetValue()
  {
    return getWorkedChargeRateValue() == null ? new BigDecimal(0) : getWorkedChargeRateValue().add(getExpenseValue());

  }

  public BigDecimal getTotalVatValue()
  {
    return (getWorkedVatValue() == null ? new BigDecimal(0) : getWorkedVatValue()).add(getExpenseVatValue() == null ? new BigDecimal(0) : getExpenseVatValue());
  }

  public BigDecimal getWorkedTotalValue()
  {
    return getWorkedChargeRateValue() == null ? new BigDecimal(0) : getWorkedTotalNetValue().add(getTotalVatValue());

  }

  public BigDecimal getWorkedWageRateValue()
  {
    return workedWageRateValue;
  }

  public void setWorkedWageRateValue(BigDecimal workedWageRateValue)
  {
    this.workedWageRateValue = workedWageRateValue;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public BigDecimal getFeePercentage()
  {
    return feePercentage;
  }

  public void setFeePercentage(BigDecimal feePercentage)
  {
    this.feePercentage = feePercentage;
  }

  public BigDecimal getFeePerHour()
  {
    return feePerHour;
  }

  public void setFeePerHour(BigDecimal feePerHour)
  {
    this.feePerHour = feePerHour;
  }

  public BigDecimal getFeePerShift()
  {
    return feePerShift;
  }

  public void setFeePerShift(BigDecimal feePerShift)
  {
    this.feePerShift = feePerShift;
  }

  public BigDecimal getFeeValue()
  {
    return feeValue;
  }

  public void setFeeValue(BigDecimal feeValue)
  {
    this.feeValue = feeValue;
  }

  public boolean getIsDraft()
  {
    return getStatus() == BOOKINGDATE_STATUS_DRAFT;
  }

  public boolean getIsCancelled()
  {
    return getStatus() == BOOKINGDATE_STATUS_CANCELLED;
  }

  public boolean getIsCredited()
  {
    return getStatus() == BOOKINGDATE_STATUS_CREDITED;
  }

  public boolean getIsFilled()
  {
    return status == BOOKINGDATE_STATUS_FILLED;
  }

  public boolean getHasBeenEntered()
  {
    // start time has been entered or comment has been entered
    return workedStartTime != null || (comment != null && !"".equals(comment));
  }

  public boolean getCanBeCancelled()
  {

    // return (getStatus() == BOOKINGDATE_STATUS_OPEN ||
    // getStatus() == BOOKINGDATE_STATUS_OFFERED ||
    // getStatus() == BOOKINGDATE_STATUS_FILLED) && !getActivated();

    // TODO this has been changed so we can cancel a 'completed' bookingDate

    return getStatus() == BOOKINGDATE_STATUS_OPEN || getStatus() == BOOKINGDATE_STATUS_OFFERED || getStatus() == BOOKINGDATE_STATUS_FILLED;

  }

  public boolean getCanBeCancelledCompleted()
  {

    return getStatus() == BOOKINGDATE_STATUS_COMPLETED;

  }

  public boolean getCanBeAppliedFor()
  {
    return getStatus() == BOOKINGDATE_STATUS_OPEN || getStatus() == BOOKINGDATE_STATUS_OFFERED;
  }

  public boolean getCanBeActivated()
  {
    return getIsFilled() && !getActivated();
  }

  public boolean getHasBeEntered()
  {
    return workedStartTime != null || (comment != null && !"".equals(comment));
  }

  public boolean getWorkedStatusIsDraft()
  {
    return workedStatus == BOOKINGDATE_WORKEDSTATUS_DRAFT;
  }

  public boolean getWorkedStatusIsRejected()
  {
    return workedStatus == BOOKINGDATE_WORKEDSTATUS_REJECTED;
  }

  public boolean getWorkedStatusIsInvoiced()
  {
    return workedStatus == BOOKINGDATE_WORKEDSTATUS_INVOICED;
  }

  public boolean getWorkedStatusIsCredited()
  {
    return workedStatus == BOOKINGDATE_WORKEDSTATUS_CREDITED;
  }

  public boolean getCanBeEdited()
  {
    return getActivated() && (getWorkedStartTime() == null || getWorkedStatus() == BOOKINGDATE_WORKEDSTATUS_DRAFT || getWorkedStatus() == BOOKINGDATE_WORKEDSTATUS_REJECTED);
  }

  public boolean getCanBeSubmitted()
  {
    return workedStartTime != null && workedStatus == BOOKINGDATE_WORKEDSTATUS_DRAFT;
  }

  public boolean getCanBeWithdrawn()
  {
    return workedStatus == BOOKINGDATE_WORKEDSTATUS_SUBMITTED;
  }

  public boolean getCanBeAuthorized()
  {
    return workedStatus == BOOKINGDATE_WORKEDSTATUS_SUBMITTED;
  }

  public boolean getCanBeInvoiced()
  {
    return workedStatus == BOOKINGDATE_WORKEDSTATUS_AUTHORIZED;
  }

  public String getStatusDescriptionKey()
  {
    return BOOKINGDATE_STATUS_DESCRIPTION_KEYS[status];
  }

  public String getWorkedStatusDescriptionKey()
  {
    return BOOKINGDATE_WORKEDSTATUS_DESCRIPTION_KEYS[workedStatus];
  }

  public BigDecimal getWorkedChargeRateVatValue()
  {
    return workedChargeRateVatValue;
  }

  public void setWorkedChargeRateVatValue(BigDecimal workedChargeRateVatValue)
  {
    this.workedChargeRateVatValue = workedChargeRateVatValue;
  }

  public BigDecimal getWorkedCommissionVatValue()
  {
    return workedCommissionVatValue;
  }

  public void setWorkedCommissionVatValue(BigDecimal workedCommissionVatValue)
  {
    this.workedCommissionVatValue = workedCommissionVatValue;
  }

  public BigDecimal getWorkedNiVatValue()
  {
    return workedNiVatValue;
  }

  public void setWorkedNiVatValue(BigDecimal workedNiVatValue)
  {
    this.workedNiVatValue = workedNiVatValue;
  }

  public BigDecimal getWorkedPayRateVatValue()
  {
    return workedPayRateVatValue;
  }

  public void setWorkedPayRateVatValue(BigDecimal workedPayRateVatValue)
  {
    this.workedPayRateVatValue = workedPayRateVatValue;
  }

  public BigDecimal getWorkedWtdVatValue()
  {
    return workedWtdVatValue;
  }

  public void setWorkedWtdVatValue(BigDecimal workedWtdVatValue)
  {
    this.workedWtdVatValue = workedWtdVatValue;
  }

  public Boolean getHasUplift()
  {
    return hasUplift;
  }

  public void setHasUplift(Boolean hasUplift)
  {
    this.hasUplift = hasUplift;
  }

  public Timestamp getActivatedTimestamp()
  {
    return activatedTimestamp;
  }

  public void setActivatedTimestamp(Timestamp activatedTimestamp)
  {
    this.activatedTimestamp = activatedTimestamp;
  }

  public Timestamp getAuthorizedTimestamp()
  {
    return authorizedTimestamp;
  }

  public void setAuthorizedTimestamp(Timestamp authorizedTimestamp)
  {
    this.authorizedTimestamp = authorizedTimestamp;
  }

  public Timestamp getCancelledTimestamp()
  {
    return cancelledTimestamp;
  }

  public void setCancelledTimestamp(Timestamp cancelledTimestamp)
  {
    this.cancelledTimestamp = cancelledTimestamp;
  }

  public Timestamp getInvoicedTimestamp()
  {
    return invoicedTimestamp;
  }

  public void setInvoicedTimestamp(Timestamp invoicedTimestamp)
  {
    this.invoicedTimestamp = invoicedTimestamp;
  }

  public Timestamp getRejectedTimestamp()
  {
    return rejectedTimestamp;
  }

  public void setRejectedTimestamp(Timestamp rejectedTimestamp)
  {
    this.rejectedTimestamp = rejectedTimestamp;
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

  public boolean getCanEditOvertime()
  {
    return (workedStatus == BOOKINGDATE_WORKEDSTATUS_DRAFT || workedStatus == BOOKINGDATE_WORKEDSTATUS_REJECTED) && status != BOOKINGDATE_STATUS_CANCELLED;
  }

  public Integer getAgencyInvoiceCreditId()
  {
    return agencyInvoiceCreditId;
  }

  public void setAgencyInvoiceCreditId(Integer agencyInvoiceCreditId)
  {
    this.agencyInvoiceCreditId = agencyInvoiceCreditId;
  }

  public String getBackingReport()
  {
    return backingReport;
  }

  public void setBackingReport(String backingReport)
  {
    this.backingReport = backingReport;
  }

  public Shift getShift()
  {
    Shift shift = new Shift();
    shift.setShiftId(getShiftId());
    shift.setName(getShiftName());
    shift.setCode(getShiftCode());
    shift.setStartTime(getShiftStartTime());
    shift.setEndTime(getShiftEndTime());
    shift.setBreakStartTime(getShiftBreakStartTime());
    shift.setBreakEndTime(getShiftBreakEndTime());
    shift.setNoOfHours(getShiftNoOfHours());
    shift.setBreakNoOfHours(getShiftBreakNoOfHours());
    shift.setUseShiftUplift(getShiftUseUplift());
    shift.setUpliftFactor(getShiftUpliftFactor());
    shift.setUpliftValue(getShiftUpliftValue());
    shift.setOvertimeNoOfHours(getShiftOvertimeNoOfHours());
    shift.setOvertimeUpliftFactor(getShiftOvertimeUpliftFactor());
    return shift;
  }

  public void setShift(Shift shift)
  {
    setShiftId(shift.getShiftId());
    setShiftName(shift.getName());
    setShiftCode(shift.getCode());
    setShiftStartTime(shift.getStartTime());
    setShiftEndTime(shift.getEndTime());
    setShiftBreakStartTime(shift.getBreakStartTime());
    setShiftBreakEndTime(shift.getBreakEndTime());
    setShiftNoOfHours(shift.getNoOfHours());
    setShiftBreakNoOfHours(shift.getBreakNoOfHours());
    setShiftUseUplift(shift.getUseShiftUplift());
    setShiftUpliftFactor(shift.getUpliftFactor());
    setShiftUpliftValue(shift.getUpliftValue());
    setShiftOvertimeNoOfHours(shift.getOvertimeNoOfHours());
    setShiftOvertimeUpliftFactor(shift.getOvertimeUpliftFactor());
  }

  public Boolean getUndefinedShift()
  {
    return shiftStartTime.equals(shiftEndTime);
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setBookingDateId(rs.getInt("BOOKINGDATEID"));
    setBookingId(rs.getInt("BOOKINGID"));
    setBookingDate(rs.getDate("BOOKINGDATE"));
    setShiftId(rs.getInt("SHIFTID"));
    setShiftName(rs.getString("SHIFTNAME"));
    setShiftCode(rs.getString("SHIFTCODE"));
    setShiftUseUplift(rs.getBoolean("SHIFTUSEUPLIFT"));
    setShiftUpliftFactor(rs.getBigDecimal("SHIFTUPLIFTFACTOR"));
    setShiftUpliftValue(rs.getBigDecimal("SHIFTUPLIFTVALUE"));
    setShiftStartTime(rs.getTime("SHIFTSTARTTIME"));
    setShiftEndTime(rs.getTime("SHIFTENDTIME"));
    setShiftBreakStartTime(rs.getTime("SHIFTBREAKSTARTTIME"));
    setShiftBreakEndTime(rs.getTime("SHIFTBREAKENDTIME"));
    setShiftNoOfHours(rs.getBigDecimal("SHIFTNOOFHOURS"));
    setShiftBreakNoOfHours(rs.getBigDecimal("SHIFTBREAKNOOFHOURS"));
    setStatus(rs.getInt("STATUS"));
    setValue(rs.getBigDecimal("VALUE"));
    setActivated(rs.getBoolean("ACTIVATED"));
    setCancelText(rs.getString("CANCELTEXT"));

    setShiftOvertimeNoOfHours(rs.getBigDecimal("SHIFTOVERTIMENOOFHOURS"));
    setShiftOvertimeUpliftFactor(rs.getBigDecimal("SHIFTOVERTIMEUPLIFTFACTOR"));

    setBookingGradeApplicantDateId(rs.getInt("BOOKINGGRADEAPPLICANTDATEID"));
    setWorkedStatus(rs.getInt("WORKEDSTATUS"));
    setWorkedStartTime(rs.getTime("WORKEDSTARTTIME"));
    setWorkedEndTime(rs.getTime("WORKEDENDTIME"));
    setWorkedBreakStartTime(rs.getTime("WORKEDBREAKSTARTTIME"));
    setWorkedBreakEndTime(rs.getTime("WORKEDBREAKENDTIME"));
    setWorkedNoOfHours(rs.getBigDecimal("WORKEDNOOFHOURS"));
    setWorkedBreakNoOfHours(rs.getBigDecimal("WORKEDBREAKNOOFHOURS"));
    setWorkedChargeRateValue(rs.getBigDecimal("WORKEDCHARGERATEVALUE"));
    setWorkedPayRateValue(rs.getBigDecimal("WORKEDPAYRATEVALUE"));
    setWorkedWtdValue(rs.getBigDecimal("WORKEDWTDVALUE"));
    setWorkedNiValue(rs.getBigDecimal("WORKEDNIVALUE"));
    setWorkedCommissionValue(rs.getBigDecimal("WORKEDCOMMISSIONVALUE"));
    setWorkedWageRateValue(rs.getBigDecimal("WORKEDWAGERATEVALUE"));

    setWorkedChargeRateVatValue(rs.getBigDecimal("WORKEDCHARGERATEVATVALUE"));
    setWorkedPayRateVatValue(rs.getBigDecimal("WORKEDPAYRATEVATVALUE"));
    setWorkedWtdVatValue(rs.getBigDecimal("WORKEDWTDVATVALUE"));
    setWorkedNiVatValue(rs.getBigDecimal("WORKEDNIVATVALUE"));
    setWorkedCommissionVatValue(rs.getBigDecimal("WORKEDCOMMISSIONVATVALUE"));

    setWorkedVatValue(rs.getBigDecimal("WORKEDVATVALUE"));
    setExpenseValue(rs.getBigDecimal("EXPENSEVALUE"));
    setExpenseVatValue(rs.getBigDecimal("EXPENSEVATVALUE"));
    setInvoiceId(rs.getInt("INVOICEID"));
    setAgencyInvoiceId(rs.getInt("AGENCYINVOICEID"));
    setAgencyInvoiceCreditId(rs.getInt("AGENCYINVOICECREDITID"));
    setRejectText(rs.getString("REJECTTEXT"));
    setComment(rs.getString("COMMENT"));
    setFeePerShift(rs.getBigDecimal("FEEPERSHIFT"));
    setFeePerHour(rs.getBigDecimal("FEEPERHOUR"));
    setFeePercentage(rs.getBigDecimal("FEEPERCENTAGE"));
    setFeeValue(rs.getBigDecimal("FEEVALUE"));

    setCancelledById(rs.getInt("CANCELLEDBYID"));
    setCancelledTimestamp(rs.getTimestamp("CANCELLEDTIMESTAMP"));
    setInvoicedById(rs.getInt("INVOICEDBYID"));
    setInvoicedTimestamp(rs.getTimestamp("INVOICEDTIMESTAMP"));
    setRejectedById(rs.getInt("REJECTEDBYID"));
    setRejectedTimestamp(rs.getTimestamp("REJECTEDTIMESTAMP"));
    setAuthorizedById(rs.getInt("AUTHORIZEDBYID"));
    setAuthorizedTimestamp(rs.getTimestamp("AUTHORIZEDTIMESTAMP"));
    setActivatedById(rs.getInt("ACTIVATEDBYID"));
    setActivatedTimestamp(rs.getTimestamp("ACTIVATEDTIMESTAMP"));

    setHasUplift(rs.getBoolean("HASUPLIFT"));
    setBackingReport(rs.getString("BACKINGREPORT"));
  }

}
