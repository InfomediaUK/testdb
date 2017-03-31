package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DisciplineCategoryTraining extends Base
{
  private Integer disciplineCategoryTrainingId;
  private Integer disciplineCategoryId;
  private Integer trainingCourseId;
  private Boolean mandatory;

  public Integer getDisciplineCategoryTrainingId()
  {
    return disciplineCategoryTrainingId;
  }

  public void setDisciplineCategoryTrainingId(Integer disciplineCategoryTrainingId)
  {
    this.disciplineCategoryTrainingId = disciplineCategoryTrainingId;
  }

  public Integer getDisciplineCategoryId()
  {
    return disciplineCategoryId;
  }

  public void setDisciplineCategoryId(Integer disciplineCategoryId)
  {
    this.disciplineCategoryId = disciplineCategoryId;
  }

  public Integer getTrainingCourseId()
  {
    return trainingCourseId;
  }

  public void setTrainingCourseId(Integer trainingId)
  {
    this.trainingCourseId = trainingId;
  }

  public Boolean getMandatory()
  {
    return mandatory;
  }

  public void setMandatory(Boolean mandatory)
  {
    this.mandatory = mandatory;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setDisciplineCategoryTrainingId(rs.getInt("DISCIPLINECATEGORYTRAININGID"));
    setDisciplineCategoryId(rs.getInt("DISCIPLINECATEGORYID"));
    setTrainingCourseId(rs.getInt("TRAININGCOURSEID"));
    setMandatory(rs.getBoolean("MANDATORY"));
  }

}
