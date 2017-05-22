package com.helmet.application.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.helmet.bean.ApplicantEntity;

public class ApplicantTrainingComparator implements Comparator<Object>, Serializable
{
  private static final long serialVersionUID = 1L;

  public int compare(Object o1, Object o2)
  {
    if (!(o1 instanceof ApplicantEntity))
      throw new ClassCastException();
    if (!(o2 instanceof ApplicantEntity))
      throw new ClassCastException();

    ApplicantEntity ae1 = (ApplicantEntity) o1;
    ApplicantEntity ae2 = (ApplicantEntity) o2;


    if (ae1.getLatestApplicantTrainingCourseEndDate().compareTo(ae2.getLatestApplicantTrainingCourseEndDate()) == 0)
    {
      // Equal ApplicantTrainingCourse EndDate now use LastName
      if (ae1.getLastName().compareTo(ae2.getLastName()) == 0)
      {
        // Equal LastName now use FirstName
        return ae1.getFirstName().compareTo(ae2.getFirstName()) * -1;
      }
      else
      {
        // Last Name differs.
        return ae1.getLastName().compareTo(ae2.getLastName());
      }
    }
    else
    {
      // Latest ApplicantTrainingCourse EndDate differs.
      return ae1.getLatestApplicantTrainingCourseEndDate().compareTo(ae2.getLatestApplicantTrainingCourseEndDate());
    }

    
  }

}
