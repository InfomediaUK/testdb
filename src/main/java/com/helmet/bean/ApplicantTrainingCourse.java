package com.helmet.bean;

import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;

public class ApplicantTrainingCourse extends Base
{
  private Integer applicantTrainingCourseId;
  private Integer applicantId;
  private Integer trainingCompanyCourseId;
  private Date startDate;
  private Date endDate;
  private String comment;
  private String documentationFileName;
  
  public Integer getApplicantTrainingCourseId()
  {
    return applicantTrainingCourseId;
  }

  public void setApplicantTrainingCourseId(Integer applicantTrainingCourseId)
  {
    this.applicantTrainingCourseId = applicantTrainingCourseId;
  }

  public Integer getApplicantId()
  {
    return applicantId;
  }

  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }

  public Integer getTrainingCompanyCourseId()
  {
    return trainingCompanyCourseId;
  }

  public void setTrainingCompanyCourseId(Integer trainingCompanyCourseId)
  {
    this.trainingCompanyCourseId = trainingCompanyCourseId;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String name)
  {
    this.comment = name;
  }

  public String getDocumentationFileName()
  {
    return documentationFileName;
  }

  public void setDocumentationFileName(String documentationFileName)
  {
    this.documentationFileName = documentationFileName;
  }

  public String getDocumentationFileFolder()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + applicantId + "/training";
  }

  public String getDocumentationFileFolderPath()
  {
    return FileHandler.getInstance().getApplicantFileLocation() + getDocumentationFileFolder();
  }
  
  public String getDocumentationFileUrl()
  {
    return getDocumentationFileFolder() + "/" + documentationFileName;
  }

  public String getDocumentationFilePath()
  {
    return FileHandler.getInstance().getApplicantFileLocation() + getDocumentationFileUrl();
  }
  
  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setApplicantTrainingCourseId(rs.getInt("APPLICANTTRAININGCOURSEID"));
    setApplicantId(rs.getInt("APPLICANTID"));
    setTrainingCompanyCourseId(rs.getInt("TRAININGCOMPANYCOURSEID"));
    setStartDate(rs.getDate("STARTDATE"));
    setEndDate(rs.getDate("ENDDATE"));
    setComment(rs.getString("COMMENT"));
    setDocumentationFileName(rs.getString("DOCUMENTATIONFILENAME"));
  }

  @Override
  public String toString()
  {
    return "ApplicantTrainingCourse [applicantTrainingCourseId=" + applicantTrainingCourseId + ", applicantId=" + applicantId + ", trainingCompanyCourseId=" + trainingCompanyCourseId + ", startDate="
        + startDate + ", endDate=" + endDate + ", comment=" + comment + ", documentationFileName=" + documentationFileName + "]";
  }

}
