package com.helmet.application.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.helmet.application.agy.AgencyWorkerChecklistFile;

public class AgencyWorkerChecklistFileComparator implements Comparator, Serializable
{

  public int compare(Object o1, Object o2)
  {
    if (!(o1 instanceof AgencyWorkerChecklistFile))
      throw new ClassCastException();
    if (!(o2 instanceof AgencyWorkerChecklistFile))
      throw new ClassCastException();

    AgencyWorkerChecklistFile awcf1 = (AgencyWorkerChecklistFile) o1;
    AgencyWorkerChecklistFile awcf2 = (AgencyWorkerChecklistFile) o2;

    if (awcf1.getBookingId().compareTo(awcf2.getBookingId()) == 0)
    {
      // Equal BookingId now use FileDate
      return awcf1.getFileDate().compareTo(awcf2.getFileDate()) * -1;
    }
    else
    {
      // BookingIds differ.
      return awcf1.getBookingId().compareTo(awcf2.getBookingId()) * -1;
    }
  }

}
