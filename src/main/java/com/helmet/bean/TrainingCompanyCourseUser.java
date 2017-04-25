package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class TrainingCompanyCourseUser extends TrainingCompanyCourse
{
  private String trainingCompanyName;
  private String trainingCompanyCode;
  private String trainingCourseName;
  private String trainingCourseCode;

  public String getTrainingCompanyName()
  {
    return trainingCompanyName;
  }

  public void setTrainingCompanyName(String trainingCompanyName)
  {
    this.trainingCompanyName = trainingCompanyName;
  }

  public String getTrainingCompanyCode()
  {
    return trainingCompanyCode;
  }

  public void setTrainingCompanyCode(String trainingCompanyCode)
  {
    this.trainingCompanyCode = trainingCompanyCode;
  }

  public String getTrainingCourseName()
  {
    return trainingCourseName;
  }

  public void setTrainingCourseName(String trainingCourseName)
  {
    this.trainingCourseName = trainingCourseName;
  }

  public String getTrainingCourseCode()
  {
    return trainingCourseCode;
  }

  public void setTrainingCourseCode(String trainingCourseCode)
  {
    this.trainingCourseCode = trainingCourseCode;
  }

  public String getNameWithOnlineAndTrainingCompanyName()
  {
    return getNameWithOnline() + " (" + trainingCompanyName + ")";
  }
  
  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setTrainingCompanyName(rs.getString("TRAININGCOMPANYNAME"));
    setTrainingCompanyCode(rs.getString("TRAININGCOMPANYCODE"));
    setTrainingCourseName(rs.getString("TRAININGCOURSENAME"));
    setTrainingCourseCode(rs.getString("TRAININGCOURSECODE"));
  }

  @Override
  public String toString()
  {
    super.toString();
    return "TrainingCompanyCourseUser [trainingCompanyName=" + trainingCompanyName + ", trainingCompanyCode=" + trainingCompanyCode + ", trainingCourseName=" + trainingCourseName
        + ", trainingCourseCode=" + trainingCourseCode + "]";
  }

}
