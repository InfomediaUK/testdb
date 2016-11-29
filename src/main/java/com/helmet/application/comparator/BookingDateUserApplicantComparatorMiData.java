package com.helmet.application.comparator;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

import com.helmet.bean.BookingDateUserApplicant;

public class BookingDateUserApplicantComparatorMiData implements Comparator, Serializable {

	/**
   * 
   */
  private static final long serialVersionUID = -3364698025015681271L;

  public int compare(Object o1, Object o2) {
		if (!(o1 instanceof BookingDateUserApplicant))
			throw new ClassCastException();
		if (!(o2 instanceof BookingDateUserApplicant))
			throw new ClassCastException();

		BookingDateUserApplicant b1 = (BookingDateUserApplicant) o1;
		BookingDateUserApplicant b2 = (BookingDateUserApplicant) o2;

		
    if (b1.getClientName().compareTo(b2.getClientName()) == 0)
    {
      // Equal ClientName now check Time
      if (b1.getShiftStartTime().compareTo(b2.getShiftStartTime()) == 0)
      {
        // Equal Start Time now check Day of Week
        Calendar c = Calendar.getInstance();
        c.setTime(b1.getBookingDate());
        Integer b1BookingDateDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        c.setTime(b2.getBookingDate());
        Integer b2BookingDateDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return b1BookingDateDayOfWeek.compareTo(b2BookingDateDayOfWeek);
      }
      else
      {
        // Client Name differs.
        return b1.getShiftStartTime().compareTo(b2.getShiftStartTime());
      }
    }
    else
    {
      // Client Name differs.
      return b1.getClientName().compareTo(b2.getClientName());
    }
	}

} // end class JobProfileNameComparator

