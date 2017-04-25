package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class TrainingCourse extends Base
{
  private Integer trainingCourseId;
  private String code;
  private String name;  
  private Integer displayOrder;

  public Integer getTrainingCourseId()
  {
    return trainingCourseId;
  }

  public void setTrainingCourseId(Integer trainingId)
  {
    this.trainingCourseId = trainingId;
  }

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getNameWithCode()
  {
    return name + " (" + code + ")";
  }

  public String getCodeWithName()
  {
    return code + " - " + name;
  }

  public Integer getDisplayOrder()
  {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder)
  {
    this.displayOrder = displayOrder;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setTrainingCourseId(rs.getInt("TRAININGCOURSEID"));
    setCode(rs.getString("CODE"));
    setName(rs.getString("NAME"));
    setDisplayOrder(rs.getInt("DISPLAYORDER"));
  }

  @Override
  public String toString()
  {
    return "TrainingCourse [trainingCourseId=" + trainingCourseId + ", code=" + code + ", name=" + name + ", displayOrder=" + displayOrder + "]";
  }

}
