package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UpliftMinute extends Base
{
  private Integer upliftMinuteId;
  private Integer upliftId;
  private Integer upliftMinute;
  private BigDecimal upliftFactor = new BigDecimal(1);
  private BigDecimal upliftValue = new BigDecimal(0);

  public Integer getUpliftMinuteId()
  {
    return upliftMinuteId;
  }

  public void setUpliftMinuteId(Integer upliftMinuteId)
  {
    this.upliftMinuteId = upliftMinuteId;
  }

  public Integer getUpliftId()
  {
    return upliftId;
  }

  public void setUpliftId(Integer upliftId)
  {
    this.upliftId = upliftId;
  }

  public Integer getUpliftMinute()
  {
    return upliftMinute;
  }

  public void setUpliftMinute(Integer upliftMinute)
  {
    this.upliftMinute = upliftMinute;
  }

  public BigDecimal getUpliftFactor()
  {
    return upliftFactor;
  }

  public void setUpliftFactor(BigDecimal upliftFactor)
  {
    this.upliftFactor = upliftFactor;
  }

  public BigDecimal getUpliftValue()
  {
    return upliftValue;
  }

  public void setUpliftValue(BigDecimal upliftValue)
  {
    this.upliftValue = upliftValue;
  }

  public String getUpliftMinuteAsString() 
  {
    return " Minute: " + getUpliftMinute().toString() + " Factor: " + getUpliftFactor().toString() + " Value: " + getUpliftValue().toString();
  }
  
  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setUpliftMinuteId(rs.getInt("UPLIFTMINUTEID"));
    setUpliftId(rs.getInt("UPLIFTID"));
    setUpliftMinute(rs.getInt("UPLIFTMINUTE"));
    setUpliftFactor(rs.getBigDecimal("UPLIFTFACTOR"));
    setUpliftValue(rs.getBigDecimal("UPLIFTVALUE"));
  }

}
