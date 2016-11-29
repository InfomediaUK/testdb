package com.helmet.application.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.helmet.application.agy.ApplicantWorking;

public class ApplicantWorkingComparator implements Comparator, Serializable
{

  public int compare(Object o1, Object o2)
  {
    if (!(o1 instanceof ApplicantWorking))
      throw new ClassCastException();
    if (!(o2 instanceof ApplicantWorking))
      throw new ClassCastException();

    ApplicantWorking aw1 = (ApplicantWorking) o1;
    ApplicantWorking aw2 = (ApplicantWorking) o2;

    if (aw1.getLastName().compareTo(aw2.getLastName()) == 0)
    {
      // Equal LastName now use FirstName
      return aw1.getFirstName().compareTo(aw2.getFirstName()) * -1;
    }
    else
    {
      // Last Name differs.
      return aw1.getLastName().compareTo(aw2.getLastName());
    }
  }

}
