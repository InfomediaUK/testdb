package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Course;

public interface CourseDAO 
{
	public List<Course> getCourses(boolean showOnlyActive);
	public Course getCourse(Integer visaTypeId);
	public Course getCourseForName(String name);
	public Course getCourseForCode(String code);
	public int insertCourse(Course visaType, Integer auditorId);
  public int updateCourse(Course visaType, Integer auditorId);
	public int deleteCourse(Integer visaTypeId, Integer noOfChanges, Integer auditorId);
}
