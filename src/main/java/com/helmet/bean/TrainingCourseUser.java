package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class TrainingCourseUser extends TrainingCourse
{
  private Boolean mandatory;

  public Boolean getMandatory()
  {
    return mandatory;
  }

  public void setMandatory(Boolean mandatory)
  {
    this.mandatory = mandatory;
  }

  public String getNameWithMandatory()
  {
    if (mandatory)
    {
      return getName() + " (Mandatory)";
    }
    return getName();
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setMandatory(rs.getBoolean("MANDATORY"));
  }

  @Override
  public String toString()
  {
    return "TrainingCourseUser [mandatory=" + mandatory + "] " + super.toString();
  }

}
