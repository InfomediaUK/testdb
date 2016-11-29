package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UpliftMinuteUser extends UpliftMinute
{
  private Integer upliftDay;

  private Integer upliftHour;

  private Integer upliftMinutePeriod;

  public Integer getUpliftDay()
  {
    return upliftDay;
  }

  public void setUpliftDay(Integer upliftDay)
  {
    this.upliftDay = upliftDay;
  }

  public Integer getUpliftHour()
  {
    return upliftHour;
  }

  public void setUpliftHour(Integer upliftHour)
  {
    this.upliftHour = upliftHour;
  }

  public Integer getUpliftMinutePeriod()
  {
    return upliftMinutePeriod;
  }

  public void setUpliftMinutePeriod(Integer upliftMinutePeriod)
  {
    this.upliftMinutePeriod = upliftMinutePeriod;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setUpliftDay(rs.getInt("UPLIFTDAY"));
    setUpliftHour(rs.getInt("UPLIFTHOUR"));
    setUpliftMinutePeriod(rs.getInt("UPLIFTMINUTEPERIOD"));
  }

}
