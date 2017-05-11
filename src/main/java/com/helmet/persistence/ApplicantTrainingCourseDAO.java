package com.helmet.persistence;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.ApplicantTrainingCourse;
import com.helmet.bean.ApplicantTrainingCourseUser;
import com.helmet.bean.RecordCount;

public interface ApplicantTrainingCourseDAO 
{
  // NON-STANDARD INSERT METHOD. ApplicantTrainingCourseId supplied in object.
  public Integer getApplicantTrainingCourseId(); 
  public List<ApplicantTrainingCourseUser> getApplicantTrainingCourseUsersForApplicant(Integer applicantId, boolean showOnlyActive);
  public List<ApplicantTrainingCourseUser> getApplicantTrainingCourseUsersForApplicant(Integer applicantId);
  public List<ApplicantTrainingCourseUser> getApplicantTrainingCourseUsersForApplicantAndDateRange(Integer applicantId, Date startDate, Date endDate);
  public List<ApplicantTrainingCourseUser> getApplicantTrainingCourseUsersForApplicantTrainingAboutToExpire(Integer applicantId, Date dateToCheck);
  public ApplicantTrainingCourse getApplicantTrainingCourse(Integer applicantTrainingCourseId);
  public ApplicantTrainingCourseUser getApplicantTrainingCourseUser(Integer applicantTrainingCourseId);
	public int insertApplicantTrainingCourse(ApplicantTrainingCourse applicantTrainingCourse, Integer auditorId);
	public int deleteApplicantTrainingCourse(Integer applicantTrainingCourseId, Integer noOfChanges, Integer auditorId);
	public int updateApplicantTrainingCourse(ApplicantTrainingCourse applicantTrainingCourse, Integer auditorId);
  public RecordCount getActiveApplicantTrainingCourseCountForTrainingCourse(Integer trainingCourseId); 
}
