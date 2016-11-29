package com.helmet.bean.nhs;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BackingReportShift
{
  private Time startTime;
  private Time endTime;
  private Integer breakMinutes;
  private String workedTime;
  private Integer workedMinutes;
  private Boolean startTimeChanged = new Boolean(false);
  private Boolean endTimeChanged = new Boolean(false);

  public Time getStartTime()
  {
    return startTime;
  }

  public void setStartTime(Time startTime)
  {
    this.startTime = startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public void setEndTime(Time endTime)
  {
    this.endTime = endTime;
  }

  public Integer getBreakMinutes()
  {
    return breakMinutes;
  }

  public void setBreakMinutes(Integer breakMinutes)
  {
    this.breakMinutes = breakMinutes;
  }

  public Integer getWorkedMinutes()
  {
    return workedMinutes;
  }

  public void setWorkedMinutes(Integer workedMinutes)
  {
    this.workedMinutes = workedMinutes;
  }  

  public String getWorkedTime()
  {
    return workedTime;
  }

  public void setWorkedTime(String workedTime)
  {
    this.workedTime = workedTime;
  }

  public Boolean getStartTimeChanged()
  {
    return startTimeChanged;
  }

  public void setStartTimeChanged(Boolean startTimeChanged)
  {
    this.startTimeChanged = startTimeChanged;
  }

  public Boolean getEndTimeChanged()
  {
    return endTimeChanged;
  }

  public void setEndTimeChanged(Boolean endTimeChanged)
  {
    this.endTimeChanged = endTimeChanged;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    result.append("startTime=");
    result.append(timeFormat.format(startTime));
    if (startTimeChanged)
    {
      result.append(" *");
    }
    result.append(", ");
    result.append("endTime=");
    result.append(timeFormat.format(endTime));
    if (endTimeChanged)
    {
      result.append(" *");
    }
    result.append(", ");
    result.append("breakMinutes=");
    result.append(breakMinutes);
    result.append(", ");
    result.append("workedMinutes (");
    result.append(workedTime);
    result.append(")=");
    result.append(workedMinutes);
    return result.toString();
  }
}
