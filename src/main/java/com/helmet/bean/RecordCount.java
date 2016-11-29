package com.helmet.bean;

import java.io.Serializable;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class RecordCount extends Base implements Serializable
{
  private static final long serialVersionUID = -2913344978443482910L;
  private Integer count;

  public Integer getCount()
  {
    return count;
  }

  public void setCount(Integer count)
  {
    this.count = count;
  }

  public void load(SqlRowSet rs)
  {
    setCount(rs.getInt(1));
  }
}