package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ApplicantPaymentUpload extends NhsBooking
{
  private String uploadStaffName;
  private boolean validStaffName;
  private String uploadDate;
  private boolean validDate;
  private String uploadStart;
  private boolean validStart;
  private String uploadEnd;
  private boolean validEnd;
  private String uploadLocation;
  private boolean validLocation;
  private String uploadWard;
  private boolean validWard;
  private String uploadAssignment;
  private boolean validAssignment;
  private Time workedStartTime;
  private Time workedEndTime;
  private Time workedBreakStartTime;
  private Time workedBreakEndTime;
  private BigDecimal workedNoOfHours;
  private BigDecimal workedBreakNoOfHours;
  private String backingReport;
  
  public String getUploadStaffName()
  {
    return uploadStaffName;
  }

  public void setUploadStaffName(String uploadStaffName)
  {
    this.uploadStaffName = uploadStaffName;
  }

  public boolean isValidStaffName()
  {
    return validStaffName;
  }

  public void setValidStaffName(boolean validStaffName)
  {
    this.validStaffName = validStaffName;
  }

  public boolean getBothTimesValid()
  {
    return (validStart && validEnd);
  }

  public String getUploadEnd()
  {
    return uploadEnd;
  }

  public void setUploadEnd(String uploadEnd)
  {
    this.uploadEnd = uploadEnd;
  }

  public boolean isValidEnd()
  {
    return validEnd;
  }

  public void setValidEnd(boolean validEnd)
  {
    this.validEnd = validEnd;
  }

  public String getUploadDate()
  {
    return uploadDate;
  }

  public void setUploadDate(String uploadDate)
  {
    this.uploadDate = uploadDate;
  }

  public boolean isValidDate()
  {
    return validDate;
  }

  public void setValidDate(boolean validDate)
  {
    this.validDate = validDate;
  }

  public String getUploadStart()
  {
    return uploadStart;
  }

  public void setUploadStart(String uploadStart)
  {
    this.uploadStart = uploadStart;
  }

  public boolean isValidStart()
  {
    return validStart;
  }

  public void setValidStart(boolean validStart)
  {
    this.validStart = validStart;
  }

  public String getUploadLocation()
  {
    return uploadLocation;
  }

  public void setUploadLocation(String uploadLocation)
  {
    this.uploadLocation = uploadLocation;
  }

  public boolean getValidLocation()
  {
    return validLocation;
  }

  public void setValidLocation(boolean validLocation)
  {
    this.validLocation = validLocation;
  }

  public String getUploadWard()
  {
    return uploadWard;
  }

  public void setUploadWard(String uploadWard)
  {
    this.uploadWard = uploadWard;
  }

  public boolean getValidWard()
  {
    return validWard;
  }

  public void setValidWard(boolean validWard)
  {
    this.validWard = validWard;
  }

  public String getUploadAssignment()
  {
    return uploadAssignment;
  }

  public void setUploadAssignment(String uploadAssignment)
  {
    this.uploadAssignment = uploadAssignment;
  }

  public boolean getValidAssignment()
  {
    return validAssignment;
  }

  public void setValidAssignment(boolean validAssignment)
  {
    this.validAssignment = validAssignment;
  }

  @Override
  public boolean isValid()
  {
    return getBookingId() > 0 && validStaffName && validDate && validStart && validEnd && validLocation & validWard && validAssignment;
  }
  
  public Time getWorkedStartTime()
  {
    return workedStartTime;
  }

  public void setWorkedStartTime(Time workedStartTime)
  {
    this.workedStartTime = workedStartTime;
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

  public Time getWorkedBreakStartTime()
  {
    return workedBreakStartTime;
  }

  public void setWorkedBreakStartTime(Time workedBreakStartTime)
  {
    this.workedBreakStartTime = workedBreakStartTime;
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

  public String getBackingReport()
  {
    return backingReport;
  }

  public void setBackingReport(String backingReport)
  {
    this.backingReport = backingReport;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setWorkedStartTime(rs.getTime("WORKEDSTARTTIME"));
    setWorkedEndTime(rs.getTime("WORKEDENDTIME"));
    setWorkedBreakStartTime(rs.getTime("WORKEDBREAKSTARTTIME"));
    setWorkedBreakEndTime(rs.getTime("WORKEDBREAKENDTIME"));
    setWorkedNoOfHours(rs.getBigDecimal("WORKEDNOOFHOURS"));
    setWorkedBreakNoOfHours(rs.getBigDecimal("WORKEDBREAKNOOFHOURS"));
    setBackingReport(rs.getString("BACKINGREPORT"));
  }

  
}
