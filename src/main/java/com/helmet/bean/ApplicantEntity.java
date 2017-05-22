package com.helmet.bean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ApplicantEntity extends Applicant
{
  private List<DisciplineCategoryTrainingUser> disciplineCategoryTrainingUsers;
  private List<ApplicantTrainingCourseUser> applicantTrainingCourseUsers;
  // This is a temporary field to hold data for the ApplicantTrainingCourse data take-on...
  private String trainingDocumentInfo;
  
  public List<DisciplineCategoryTrainingUser> getDisciplineCategoryTrainingUsers()
  {
    return disciplineCategoryTrainingUsers;
  }

  public void setDisciplineCategoryTrainingUsers(List<DisciplineCategoryTrainingUser> disciplineCategoryTrainingUsers)
  {
    this.disciplineCategoryTrainingUsers = disciplineCategoryTrainingUsers;
  }

  public List<ApplicantTrainingCourseUser> getApplicantTrainingCourseUsers()
  {
    return applicantTrainingCourseUsers;
  }

  public void setApplicantTrainingCourseUsers(List<ApplicantTrainingCourseUser> applicantTrainingCourseUsers)
  {
    this.applicantTrainingCourseUsers = applicantTrainingCourseUsers;
  }

  public Boolean getHasDisciplineCategoryTrainings()
  {
    if (disciplineCategoryTrainingUsers == null)
    {
      return false;
    }
    return disciplineCategoryTrainingUsers.size() > 0;
  }
  
  public Boolean getHasApplicantTrainingCourses()
  {
    if (applicantTrainingCourseUsers == null)
    {
      return false;
    }
    return applicantTrainingCourseUsers.size() > 0;
  }

  public String getTrainingDocumentInfo()
  {
    return trainingDocumentInfo;
  }

  public void setTrainingDocumentInfo(String trainingDocumentInfo)
  {
    this.trainingDocumentInfo = trainingDocumentInfo;
  }

  public Boolean hasRequiredTraining()
  {
    Calendar calendar = Calendar.getInstance();
    Date todaysDate = new Date(calendar.getTimeInMillis());
    Boolean hasRequiredTraining = true;
    Boolean trainingCourseFound = false;
    if (getHasDisciplineCategoryTrainings())
    {
      for (DisciplineCategoryTrainingUser disciplineCategoryTrainingUser : disciplineCategoryTrainingUsers)
      {
        // For each mandatory Training for the Applicant's Discipline Category...
        trainingCourseFound = false;
        for (ApplicantTrainingCourseUser applicantTrainingCourseUser : applicantTrainingCourseUsers)
        {
          // For each Training Course that the Applicant has taken...
          if (disciplineCategoryTrainingUser.getTrainingCourseId().equals(applicantTrainingCourseUser.getTrainingCourseId()))
          {
            if (todaysDate.after(applicantTrainingCourseUser.getStartDate()) && todaysDate.before(applicantTrainingCourseUser.getEndDate()))
            {
              // Applicant has this Training Course current.
              trainingCourseFound = true;
              break;
            }
          }
        }
        if (!trainingCourseFound)
        {
          // Applicant has NOT done this mandatory training.
          hasRequiredTraining = false;
        }
      } 
    }
    else
    {
      hasRequiredTraining = false;
    }
    return hasRequiredTraining;
  }
  

//  public Boolean hasRequiredTraining(StringBuffer reason)
//  {
//    Calendar calendar = Calendar.getInstance();
//    Date todaysDate = new Date(calendar.getTimeInMillis());
//    Boolean hasRequiredTraining = true;
//    Boolean trainingCourseFound = false;
//    if (getHasDisciplineCategoryTrainings())
//    {
//      for (DisciplineCategoryTrainingUser disciplineCategoryTrainingUser : disciplineCategoryTrainingUsers)
//      {
//        // For each mandatory Training for the Applicant's Discipline Category...
//        trainingCourseFound = false;
//        for (ApplicantTrainingCourseUser applicantTrainingCourseUser : applicantTrainingCourseUsers)
//        {
//          // For each Training Course that the Applicant has taken...
//          if (disciplineCategoryTrainingUser.getTrainingCourseId().equals(applicantTrainingCourseUser.getTrainingCourseId()))
//          {
//            if (todaysDate.after(applicantTrainingCourseUser.getStartDate()) && todaysDate.before(applicantTrainingCourseUser.getEndDate()))
//            {
//              // Applicant has this Training Course current.
//              trainingCourseFound = true;
//              break;
//            }
//          }
//        }
//        if (!trainingCourseFound)
//        {
//          // Applicant has NOT done this mandatory training.
//          reason.append("Current " + disciplineCategoryTrainingUser.getTrainingName() + "\n");
//          hasRequiredTraining = false;
//        }
//      } 
//    }
//    else
//    {
//      reason.append("Discipline Category MISSING Training NOT Checked \n");
//      hasRequiredTraining = false;
//    }
//    return hasRequiredTraining;
//  }
//  
  public String getTrainingAboutToExpire()
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
    if (getHasApplicantTrainingCourses())
    {
      // This Applicant HAS ApplicantTrainingCourse records. Return any that expire within one month.
      StringBuffer trainingAboutToExpire = new StringBuffer();
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.DATE, -1);
      Date dateYesterday = new Date(calendar.getTimeInMillis());
      calendar.add(Calendar.DATE, 1);
      calendar.add(Calendar.MONTH, 1);
      Date dateOneMonthAhead = new Date(calendar.getTimeInMillis());
      for (ApplicantTrainingCourseUser applicantTrainingCourseUser : applicantTrainingCourseUsers)
      {
        if (applicantTrainingCourseUser.getEndDate().before(dateOneMonthAhead) && applicantTrainingCourseUser.getEndDate().after(dateYesterday))
        {
          // ApplicantTrainingCourse is today or later and expires in less than one month. 
          // Add it to the comma delimited string.
          if (trainingAboutToExpire.length() > 0)
          {
            trainingAboutToExpire.append(", ");
          }
          trainingAboutToExpire.append(applicantTrainingCourseUser.getTrainingCompanyCourseName());
          trainingAboutToExpire.append(" - ");
          trainingAboutToExpire.append(simpleDateFormat.format(applicantTrainingCourseUser.getEndDate()));
        }
      }
      trainingAboutToExpire.append(".");
      return trainingAboutToExpire.toString();      
    }
    else
    {
      // This Applicant does NOT have ApplicantTrainingCourse records. Just return the TrainingExpiryDate.
      return simpleDateFormat.format(getTrainingExpiryDate());
    }
  }
  
  public Date getLatestApplicantTrainingCourseEndDate()
  {
    Date latestDate = new Date(0);
    for (ApplicantTrainingCourseUser applicantTrainingCourseUser : applicantTrainingCourseUsers)
    {
      if (applicantTrainingCourseUser.getEndDate().after(latestDate))
      {
        latestDate = applicantTrainingCourseUser.getEndDate();
      }
    }
    return latestDate;
  }

  public Date getEarliestApplicantTrainingCourseStartDate()
  {
    Date earliestDate = new Date(Long.MAX_VALUE);
    for (ApplicantTrainingCourseUser applicantTrainingCourseUser : applicantTrainingCourseUsers)
    {
      if (applicantTrainingCourseUser.getStartDate().before(earliestDate))
      {
        earliestDate = applicantTrainingCourseUser.getStartDate();
      }
    }
    return earliestDate;
  }
  
}
