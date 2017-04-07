package com.helmet.bean;

import java.util.List;

public class TrainingCompanyUserEntity extends TrainingCompanyUser
{
  private List<TrainingCompanyCourseUser> trainingCompanyCourseUsers;

  public List<TrainingCompanyCourseUser> getTrainingCompanyCourseUsers()
  {
    return trainingCompanyCourseUsers;
  }

  public void setTrainingCompanyCourseUsers(List<TrainingCompanyCourseUser> trainingCompanyCourseUsers)
  {
    this.trainingCompanyCourseUsers = trainingCompanyCourseUsers;
  }  
  
  public Boolean getHasTrainingCompanyCourses()
  {
    if (trainingCompanyCourseUsers == null)
    {
      return false;
    }
    return trainingCompanyCourseUsers.size() > 0;
  }
  
  public Boolean getHasActiveTrainingCompanyCourses()
  {
    Boolean hasActiveTrainings = false;
    if (trainingCompanyCourseUsers != null)
    {
      for (TrainingCompanyCourseUser trainingCompanyCourseUser : trainingCompanyCourseUsers)
      {
        if (trainingCompanyCourseUser.getActive())
        {
          hasActiveTrainings = true;
          break;
        }
      }
    }
    return hasActiveTrainings;
  }
  
}
