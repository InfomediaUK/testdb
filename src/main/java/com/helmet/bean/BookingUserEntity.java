package com.helmet.bean;

import java.util.List;

abstract public class BookingUserEntity extends BookingUser {

	private List<BookingDate> bookingDates;
	private List<BookingExpense> bookingExpenses;
	private List<BookingGradeApplicantUser> bookingGradeApplicantUsers;

	public List<BookingDate> getBookingDates() {
		return bookingDates;
	}

	public void setBookingDates(List<BookingDate> bookingDates) {
		this.bookingDates = bookingDates;
	}

	public List<BookingExpense> getBookingExpenses() {
		return bookingExpenses;
	}

	public void setBookingExpenses(List<BookingExpense> bookingExpenses) {
		this.bookingExpenses = bookingExpenses;
	}

	public List<BookingGradeApplicantUser> getBookingGradeApplicantUsers() {
		return bookingGradeApplicantUsers;
	}

	public void setBookingGradeApplicantUsers(List<BookingGradeApplicantUser> bookingGradeApplicantUsers) {
		this.bookingGradeApplicantUsers = bookingGradeApplicantUsers;
	}

	public String getBookingDatesAsString() {
		String result = "";
		for (BookingDate bookingDate: bookingDates) {
			String theDate = bookingDate.getBookingDate().toString();
			if (!"".equals(result)) {
				result += ",";
			}
			result += theDate;
		}
		return result;
	}
	
	public boolean getCanBeCancelled() {
		return getStatus() == BOOKING_STATUS_DRAFT ||
			  (getStatus() == BOOKING_STATUS_OPEN && getNoOfFilledOrBeyondDates() == 0);
	}

	public int getNoOfFilledOrBeyondDates() {
		
		// set to -1 incase no bookingDate objects exist which doesn't mean there aren't any just that they haven't been selected.
        int noOfFilledOrBeyondDates = -1;
        
		if (bookingDates != null) {

			noOfFilledOrBeyondDates = 0;
			for (BookingDate bookingDate: bookingDates) {
            	if (bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_FILLED ||
            		bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_COMPLETED) {
            		noOfFilledOrBeyondDates++;
            	}
            }
		}
		
		return noOfFilledOrBeyondDates;		
	
	}
}
