package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.TrainingCompanyCourse;
import com.helmet.bean.TrainingCompanyCourseUser;

public interface TrainingCompanyCourseDAO 
{
	public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsersForTrainingCompany(Integer trainingCompanyId, boolean showOnlyActive);
	public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsersForTrainingCompany(Integer trainingCompanyId);
	public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsersForTrainingCourse(Integer trainingCourseId, boolean showOnlyActive);
	public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsersForTrainingCourse(Integer trainingCourseId);
  public TrainingCompanyCourse getTrainingCompanyCourse(Integer trainingCompanyCourseId);
  public TrainingCompanyCourseUser getTrainingCompanyCourseUser(Integer trainingCompanyCourseId);
	public TrainingCompanyCourse getTrainingCompanyCourseForTrainingCompanyAndTrainingCourse(Integer trainingCompanyId, Integer trainingCourseId);
	public int insertTrainingCompanyCourse(TrainingCompanyCourse trainingCompanyCourse, Integer auditorId);
	public int deleteTrainingCompanyCourse(Integer trainingCompanyCourseId, Integer noOfChanges, Integer auditorId);
	public int updateTrainingCompanyCourse(TrainingCompanyCourse trainingCompanyCourse, Integer auditorId);
}
