package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DisciplineCategoryTrainingUser extends DisciplineCategoryTraining
{
  private String disciplineCategoryName;
  private String disciplineCategoryCode;
  private String trainingName;
  private String trainingCode;
  
  public String getDisciplineCategoryName()
  {
    return disciplineCategoryName;
  }

  public void setDisciplineCategoryName(String disciplineCategoryName)
  {
    this.disciplineCategoryName = disciplineCategoryName;
  }

  public String getDisciplineCategoryCode()
  {
    return disciplineCategoryCode;
  }

  public void setDisciplineCategoryCode(String disciplineCategoryCode)
  {
    this.disciplineCategoryCode = disciplineCategoryCode;
  }

  public String getTrainingName()
  {
    return trainingName;
  }

  public void setTrainingName(String trainingName)
  {
    this.trainingName = trainingName;
  }

  public String getTrainingCode()
  {
    return trainingCode;
  }

  public void setTrainingCode(String trainingCode)
  {
    this.trainingCode = trainingCode;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setDisciplineCategoryName(rs.getString("DISCIPLINECATEGORYNAME"));
    setDisciplineCategoryCode(rs.getString("DISCIPLINECATEGORYCODE"));
    setTrainingName(rs.getString("TRAININGNAME"));
    setTrainingCode(rs.getString("TRAININGCODE"));
  }

}
