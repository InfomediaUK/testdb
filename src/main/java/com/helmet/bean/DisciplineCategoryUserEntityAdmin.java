package com.helmet.bean;

import java.util.List;

public class DisciplineCategoryUserEntityAdmin extends DisciplineCategoryUserEntity
{
  private List<TrainingCourse> trainingCourses;

  public List<TrainingCourse> getTrainingCourses()
  {
    return trainingCourses;
  }

  public void setTrainingCourses(List<TrainingCourse> trainingCourses)
  {
    this.trainingCourses = trainingCourses;
  }

}
