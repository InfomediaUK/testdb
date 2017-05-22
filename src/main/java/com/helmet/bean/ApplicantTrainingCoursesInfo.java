package com.helmet.bean;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ApplicantTrainingCoursesInfo extends Base implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Date earliestStartDate;
  private Date latestEndDate;
  private Integer count;

  public Date getEarliestStartDate()
  {
    return earliestStartDate;
  }
  public void setEarliestStartDate(Date earliestStartDate)
  {
    this.earliestStartDate = earliestStartDate;
  }
  
  public Date getLatestEndDate()
  {
    return latestEndDate;
  }
  
  public void setLatestEndDate(Date latestEndDate)
  {
    this.latestEndDate = latestEndDate;
  }
  
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
    setEarliestStartDate(rs.getDate("EARLIESTSTARTDATE"));
    setLatestEndDate(rs.getDate("LATESTENDDATE"));
    setCount(rs.getInt("COUNT"));
  }
  
}