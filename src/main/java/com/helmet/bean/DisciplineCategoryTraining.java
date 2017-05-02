package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DisciplineCategoryTraining extends Base
{
  private Integer disciplineCategoryTrainingId;
  private Integer disciplineCategoryId;
  private Integer trainingCourseId;

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

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setDisciplineCategoryTrainingId(rs.getInt("DISCIPLINECATEGORYTRAININGID"));
    setDisciplineCategoryId(rs.getInt("DISCIPLINECATEGORYID"));
    setTrainingCourseId(rs.getInt("TRAININGCOURSEID"));
  }

}
