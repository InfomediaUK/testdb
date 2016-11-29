package com.helmet.bean;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BookingGradeApplicantDateUserEntity extends BookingGradeApplicantDateUser {

	private List<BookingDateExpenseUser> bookingDateExpenseUsers;
	
	private List<BookingDateHour> bookingDateHours;

	public List<BookingDateExpenseUser> getBookingDateExpenseUsers() {
		return bookingDateExpenseUsers;
	}

	public void setBookingDateExpenseUsers(
			List<BookingDateExpenseUser> bookingDateExpenseUsers) {
		this.bookingDateExpenseUsers = bookingDateExpenseUsers;
	}

	public List<BookingDateHour> getBookingDateHours() {
		return bookingDateHours;
	}

	public void setBookingDateHours(List<BookingDateHour> bookingDateHours) {
		this.bookingDateHours = bookingDateHours;
	}

	public void load(SqlRowSet rs)
    {
	    super.load(rs);
    }
	
}
