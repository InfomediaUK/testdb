package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Course extends Base
{
  private Integer courseId;
  private String code;
  private String name;  
  private String description;  

  public Integer getCourseId()
  {
    return courseId;
  }

  public void setCourseId(Integer courseId)
  {
    this.courseId = courseId;
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

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setCourseId(rs.getInt("COURSEID"));
    setCode(rs.getString("CODE"));
    setName(rs.getString("NAME"));
    setDescription(rs.getString("DESCRIPTION"));
  }

}
