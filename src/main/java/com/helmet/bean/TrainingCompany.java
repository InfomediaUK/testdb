package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class TrainingCompany extends BaseOwner
{
  private Integer trainingCompanyId;

  public Integer getTrainingCompanyId()
  {
    return trainingCompanyId;
  }

  public void setTrainingCompanyId(Integer trainingCompanyId)
  {
    this.trainingCompanyId = trainingCompanyId;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setTrainingCompanyId(rs.getInt("TRAININGCOMPANYID"));
  }

  @Override
  public String toString()
  {
    return "TrainingCompany [trainingCompanyId=" + trainingCompanyId + "]";
  }

}
