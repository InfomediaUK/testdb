package com.helmet.application.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.helmet.bean.BookingDateUserApplicant;

public class BookingDateUserApplicantComparatorByJobProfileName implements Comparator, Serializable {

	public int compare(Object o1, Object o2) {
		if (!(o1 instanceof BookingDateUserApplicant))
			throw new ClassCastException();
		if (!(o2 instanceof BookingDateUserApplicant))
			throw new ClassCastException();

		BookingDateUserApplicant b1 = (BookingDateUserApplicant) o1;
		BookingDateUserApplicant b2 = (BookingDateUserApplicant) o2;

		return b1.getJobProfileName().compareTo(b2.getJobProfileName());
	}

} // end class JobProfileNameComparator

