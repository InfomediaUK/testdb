package com.helmet.bean;

import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Unavailable extends Base
{

  private Integer unavailableId;

  private Integer agencyId;

  private Integer applicantId;

  private Date unavailableDate;

  public Integer getUnavailableId()
  {
    return unavailableId;
  }

  public void setUnavailableId(Integer unavailableId)
  {
    this.unavailableId = unavailableId;
  }

  public Integer getAgencyId()
  {
    return agencyId;
  }

  public void setAgencyId(Integer agencyId)
  {
    this.agencyId = agencyId;
  }

  public Integer getApplicantId()
  {
    return applicantId;
  }

  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }

  public Date getUnavailableDate()
  {
    return unavailableDate;
  }

  public void setUnavailableDate(Date unavailableDate)
  {
    this.unavailableDate = unavailableDate;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setUnavailableId(rs.getInt("UNAVAILABLEID"));
    setApplicantId(rs.getInt("AGENCYID"));
    setApplicantId(rs.getInt("APPLICANTID"));
    setUnavailableDate(rs.getDate("UNAVAILABLEDATE"));
  }

}
