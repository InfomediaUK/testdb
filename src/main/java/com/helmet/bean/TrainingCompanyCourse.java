package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class TrainingCompanyCourse extends BaseDisplayOrder
{
  private Integer trainingCompanyCourseId;
  private Integer trainingCompanyId;
  private Integer trainingCourseId;
  private String name;
  private Boolean online;

  public Integer getTrainingCompanyCourseId()
  {
    return trainingCompanyCourseId;
  }

  public void setTrainingCompanyCourseId(Integer trainingCompanyCourseId)
  {
    this.trainingCompanyCourseId = trainingCompanyCourseId;
  }

  public Integer getTrainingCompanyId()
  {
    return trainingCompanyId;
  }

  public void setTrainingCompanyId(Integer trainingCompanyId)
  {
    this.trainingCompanyId = trainingCompanyId;
  }

  public Integer getTrainingCourseId()
  {
    return trainingCourseId;
  }

  public void setTrainingCourseId(Integer trainingId)
  {
    this.trainingCourseId = trainingId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Boolean getOnline()
  {
    return online;
  }

  public void setOnline(Boolean online)
  {
    this.online = online;
  }

  public String getNameWithOnline()
  {
    if (online)
    {
      return name + " (Online)";
    }
    else
    {
      return name;
    }
  }
  
  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setTrainingCompanyCourseId(rs.getInt("TRAININGCOMPANYCOURSEID"));
    setTrainingCompanyId(rs.getInt("TRAININGCOMPANYID"));
    setTrainingCourseId(rs.getInt("TRAININGCOURSEID"));
    setName(rs.getString("NAME"));
    setOnline(rs.getBoolean("ONLINE"));
  }

}
