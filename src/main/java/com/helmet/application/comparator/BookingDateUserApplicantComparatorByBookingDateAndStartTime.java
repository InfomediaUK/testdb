package com.helmet.application.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.helmet.bean.BookingDateUserApplicant;

public class BookingDateUserApplicantComparatorByBookingDateAndStartTime implements Comparator, Serializable {

	public int compare(Object o1, Object o2) {
		if (!(o1 instanceof BookingDateUserApplicant))
			throw new ClassCastException();
		if (!(o2 instanceof BookingDateUserApplicant))
			throw new ClassCastException();

		BookingDateUserApplicant b1 = (BookingDateUserApplicant) o1;
		BookingDateUserApplicant b2 = (BookingDateUserApplicant) o2;

		// Do an upper-case comparison
		Long b1BookingDateAndStartTime = new Long(b1.getBookingDate().getTime() + b1.getShiftStartTime().getTime());
		Long b2BookingDateAndStartTime = new Long(b2.getBookingDate().getTime() + b2.getShiftStartTime().getTime());
		
		return b1BookingDateAndStartTime.compareTo(b2BookingDateAndStartTime);
	}

} // end class JobProfileNameComparator

