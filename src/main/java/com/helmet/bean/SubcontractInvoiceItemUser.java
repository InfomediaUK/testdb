package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SubcontractInvoiceItemUser extends SubcontractInvoiceItem
{
  private Time workedStartTime;
  private Time workedEndTime;
  private Time workedBreakStartTime;
  private Time workedBreakEndTime;
  private BigDecimal workedNoOfHours;
  private BigDecimal workedBreakNoOfHours;

  public SubcontractInvoiceItemUser(Integer subcontractInvoiceId, NhsBookingUser nhsBookingUser)
  {
    super(subcontractInvoiceId, nhsBookingUser);
    this.workedStartTime = nhsBookingUser.getWorkedStartTime();
    this.workedEndTime = nhsBookingUser.getWorkedEndTime();
    this.workedBreakStartTime = nhsBookingUser.getWorkedBreakStartTime();
    this.workedBreakEndTime = nhsBookingUser.getWorkedBreakEndTime();
    this.workedNoOfHours = nhsBookingUser.getWorkedNoOfHours();
    this.workedBreakNoOfHours = nhsBookingUser.getWorkedBreakNoOfHours();
  }

  public SubcontractInvoiceItemUser()
  {
    super();
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

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setWorkedStartTime(rs.getTime("WORKEDSTARTTIME"));
    setWorkedEndTime(rs.getTime("WORKEDENDTIME"));
    setWorkedBreakStartTime(rs.getTime("WORKEDBREAKSTARTTIME"));
    setWorkedBreakEndTime(rs.getTime("WORKEDBREAKENDTIME"));
    setWorkedNoOfHours(rs.getBigDecimal("WORKEDNOOFHOURS"));
    setWorkedBreakNoOfHours(rs.getBigDecimal("WORKEDBREAKNOOFHOURS"));
  }

}
