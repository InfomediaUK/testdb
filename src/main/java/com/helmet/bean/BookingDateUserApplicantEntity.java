package com.helmet.bean;

import java.util.List;

public class BookingDateUserApplicantEntity extends BookingDateUserApplicant {

	private List<BookingDateHour> bookingDateHours;

	private List<BookingDateExpenseUser> bookingDateExpenses;

	public List<BookingDateHour> getBookingDateHours() {
		return bookingDateHours;
	}

	public void setBookingDateHours(List<BookingDateHour> bookingDateHours) {
		this.bookingDateHours = bookingDateHours;
	}

	public List<BookingDateExpenseUser> getBookingDateExpenses() {
		return bookingDateExpenses;
	}

	public void setBookingDateExpenses(List<BookingDateExpenseUser> bookingDateExpenses) {
		this.bookingDateExpenses = bookingDateExpenses;
	}

}
