package com.helmet.bean;

import java.io.Serializable;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class IntValue extends Base implements Serializable
{
  private Integer value;

  public Integer getValue()
  {
    return value;
  }
  public void setValue(Integer value)
  {
    this.value = value;
  }
  
  public void load(SqlRowSet rs)
  {
    setValue(rs.getInt(1));
  }
  
  public String toString()
  {
    return value.toString();
  }
}