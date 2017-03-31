package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.TrainingCourse;

public interface TrainingDAO 
{
  public List<TrainingCourse> getTrainingCourses(boolean showOnlyActive);
  public List<TrainingCourse> getTrainingCoursesNotForDisciplineCategory(Integer disciplineCategoryId);
	public TrainingCourse getTrainingCourse(Integer trainingCourseId);
	public TrainingCourse getTrainingCourseForName(String name);
	public TrainingCourse getTrainingCourseForCode(String code);
	public int insertTrainingCourse(TrainingCourse trainingCourse, Integer auditorId);
  public int updateTrainingCourse(TrainingCourse trainingCourse, Integer auditorId);
  public int updateTrainingCourseDisplayOrder(TrainingCourse trainingCourse, Integer auditorId);
	public int deleteTrainingCourse(Integer trainingCourseId, Integer noOfChanges, Integer auditorId);
}
