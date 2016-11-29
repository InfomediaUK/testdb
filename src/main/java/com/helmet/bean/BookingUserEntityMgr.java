package com.helmet.bean;

import java.util.List;

public class BookingUserEntityMgr extends BookingUserEntity {

	private List<BookingGradeUser> bookingGradeUsers;
//	private List<BookingGradeApplicantDateUser> bookingGradeApplicantDateUsers;

	private List<BookingDateUserApplicant> bookingDateUserApplicants;

	public List<BookingGradeUser> getBookingGradeUsers() {
		return bookingGradeUsers;
	}

	public void setBookingGradeUsers(List<BookingGradeUser> bookingGradeUsers) {
		this.bookingGradeUsers = bookingGradeUsers;
	}

//	public List<BookingGradeApplicantDateUser> getBookingGradeApplicantDateUsers() {
//		return bookingGradeApplicantDateUsers;
//	}
//
//	public void setBookingGradeApplicantDateUsers(
//			List<BookingGradeApplicantDateUser> bookingGradeApplicantDateUsers) {
//		this.bookingGradeApplicantDateUsers = bookingGradeApplicantDateUsers;
//	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicants() {
		return bookingDateUserApplicants;
	}

	public void setBookingDateUserApplicants(
			List<BookingDateUserApplicant> bookingDateUserApplicants) {
		this.bookingDateUserApplicants = bookingDateUserApplicants;
	}
	
}
