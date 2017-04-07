package com.helmet.bean;

import java.util.List;

public class DisciplineCategoryUserEntity extends DisciplineCategoryUser
{
  private List<DisciplineCategoryTrainingUser> disciplineCategoryTrainingUsers;
  
  public List<DisciplineCategoryTrainingUser> getDisciplineCategoryTrainingUsers()
  {
    return disciplineCategoryTrainingUsers;
  }

  public void setDisciplineCategoryTrainingUsers(List<DisciplineCategoryTrainingUser> disciplineCategoryTrainingUsers)
  {
    this.disciplineCategoryTrainingUsers = disciplineCategoryTrainingUsers;
  }

  public Boolean getHasDisciplineCategoryTrainings()
  {
    if (disciplineCategoryTrainingUsers == null)
    {
      return false;
    }
    return disciplineCategoryTrainingUsers.size() > 0;
  }
  
  public Boolean getHasActiveDisciplineCategoryTrainings()
  {
    Boolean hasActiveTrainings = false;
    if (disciplineCategoryTrainingUsers != null)
    {
      for (DisciplineCategoryTrainingUser disciplineCategoryTrainingUser : disciplineCategoryTrainingUsers)
      {
        if (disciplineCategoryTrainingUser.getActive())
        {
          hasActiveTrainings = true;
          break;
        }
      }
    }
    return hasActiveTrainings;
  }
  
}
