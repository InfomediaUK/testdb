package com.helmet.application.agy;

import java.math.BigDecimal;
import java.sql.Date;

import com.helmet.bean.BookingDate;

public class ApplicantWorkingDate
{
  private Date date;
  private Integer finishedPeriod;
  private Integer workedStatus;
  private BigDecimal hoursScheduled;
  private BigDecimal hoursWorked;
  // Defaults to Available.
  private boolean unavailable;

  public ApplicantWorkingDate(Date date)
  {
    super();
    this.date = date;
    this.workedStatus = new Integer(0);
    this.finishedPeriod = new Integer(0);
    this.hoursScheduled = new BigDecimal(0);
    this.hoursWorked = new BigDecimal(0);
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public Integer getFinishedPeriod()
  {
    return finishedPeriod;
  }

  public void setFinishedPeriod(Integer finishedPeriod)
  {
    this.finishedPeriod = finishedPeriod;
  }

  public Integer getWorkedStatus()
  {
    return workedStatus;
  }

  public void setWorkedStatus(Integer workedStatus)
  {
    this.workedStatus = workedStatus;
  }

  public boolean isUnavailable()
  {
    return unavailable;
  }

  public void setUnavailable(boolean unavailable)
  {
    this.unavailable = unavailable;
  }

  public boolean getWorkedStatusIsInvoiced() 
  {
    return workedStatus == BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED;
  }

  public BigDecimal getHoursScheduled()
  {
    return hoursScheduled;
  }

  public void updateHoursScheduled(BigDecimal hoursScheduled)
  {
    if (hoursScheduled != null)
    {
      this.hoursScheduled = this.hoursScheduled.add(hoursScheduled);
    }
  }

  public BigDecimal getHoursWorked()
  {
    return hoursWorked;
  }

  public void updateHoursWorked(BigDecimal hoursWorked)
  {
    if (hoursWorked != null)
    {
      this.hoursWorked = this.hoursWorked.add(hoursWorked);
    }
  }
  
  public String getFinishedPeriodCode()
  {
    String result = null;
    switch(finishedPeriod) 
    {
      case 0: result = "Z"; break;
      case 1: result = "N"; break;
      case 2: result = "M"; break;
      case 3: result = "A"; break;
      case 4: result = "E"; break;
      default: result = "";
    }      
    return result;
  }

}
