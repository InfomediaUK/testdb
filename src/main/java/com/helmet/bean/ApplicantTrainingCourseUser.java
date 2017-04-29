package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ApplicantTrainingCourseUser extends ApplicantTrainingCourse
{
  private String applicantFirstName;
  private String applicantLastName;
  private String applicantNhsStaffName;
  private String applicantEmailAddress;
  private String disciplineCategoryName;
  private String trainingCompanyName;
  private String trainingCompanyCourseName;
  private Boolean online = false;

  public String getApplicantFirstName()
  {
    return applicantFirstName;
  }

  public void setApplicantFirstName(String applicantFirstName)
  {
    this.applicantFirstName = applicantFirstName;
  }

  public String getApplicantLastName()
  {
    return applicantLastName;
  }

  public void setApplicantLastName(String applicantLastName)
  {
    this.applicantLastName = applicantLastName;
  }

  public String getApplicantNhsStaffName()
  {
    return applicantNhsStaffName;
  }

  public void setApplicantNhsStaffName(String applicantNhsStaffName)
  {
    this.applicantNhsStaffName = applicantNhsStaffName;
  }

  public String getApplicantEmailAddress()
  {
    return applicantEmailAddress;
  }

  public void setApplicantEmailAddress(String applicantEmailAddress)
  {
    this.applicantEmailAddress = applicantEmailAddress;
  }

  public String getDisciplineCategoryName()
  {
    return disciplineCategoryName;
  }

  public void setDisciplineCategoryName(String disciplineCategoryName)
  {
    this.disciplineCategoryName = disciplineCategoryName;
  }

  public Boolean getOnline()
  {
    return online;
  }

  public void setOnline(Boolean online)
  {
    this.online = online;
  }

  public String getTrainingCompanyName()
  {
    return trainingCompanyName;
  }

  public void setTrainingCompanyName(String trainingCompanyName)
  {
    this.trainingCompanyName = trainingCompanyName;
  }

  public String getTrainingCompanyCourseName()
  {
    return trainingCompanyCourseName;
  }

  public void setTrainingCompanyCourseName(String trainingCompanyCourseName)
  {
    this.trainingCompanyCourseName = trainingCompanyCourseName;
  }

  public String getApplicantFullName() 
  {
    return applicantFirstName + " " + applicantLastName;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setApplicantFirstName(rs.getString("APPLICANTFIRSTNAME"));
    setApplicantLastName(rs.getString("APPLICANTLASTNAME"));
    setApplicantNhsStaffName(rs.getString("APPLICANTNHSSTAFFNAME"));
    setApplicantEmailAddress(rs.getString("APPLICANTEMAILADDRESS"));
    setDisciplineCategoryName(rs.getString("DISCIPLINECATEGORYNAME"));
    setTrainingCompanyName(rs.getString("TRAININGCOMPANYNAME"));
    setTrainingCompanyCourseName(rs.getString("TRAININGCOMPANYCOURSENAME"));
    setOnline(rs.getBoolean("ONLINE"));
  }

  @Override
  public String toString()
  {
    return "ApplicantTrainingCourseUser [applicantFirstName=" + applicantFirstName + ", applicantLastName=" + applicantLastName + ", applicantNhsStaffName=" + applicantNhsStaffName
        + ", applicantEmailAddress=" + applicantEmailAddress + ", disciplineCategoryName=" + disciplineCategoryName + ", trainingCompanyName=" + trainingCompanyName + ", trainingCompanyCourseName="
        + trainingCompanyCourseName + ", online=" + online + "] " + super.toString();
  }

}
