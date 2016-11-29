package com.helmet.bean;

import java.util.List;

public class InvoiceAgencyUserEntity extends InvoiceAgencyUser {

	private List<BookingDateUserApplicantEntity> bookingDateUserApplicants;

	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicants() {
		return bookingDateUserApplicants;
	}

	public void setBookingDateUserApplicants(
			List<BookingDateUserApplicantEntity> bookingDateUserApplicants) {
		this.bookingDateUserApplicants = bookingDateUserApplicants;
	}
	
	public boolean getAllTheSameBookingAndApplicant() {
		
		// temporary to determine if we can print and invoice or not
		
		if (bookingDateUserApplicants != null) {
            Integer prevBookingId = null;
            Integer prevApplicantId = null;
			for (BookingDateUserApplicant bookingDate: bookingDateUserApplicants) {
				if (prevBookingId == null) {
					prevBookingId = bookingDate.getBookingId();
				}
				if (prevApplicantId == null) {
					prevApplicantId = bookingDate.getApplicantId();
				}
                if (!bookingDate.getBookingId().equals(prevBookingId)) {
                	return false;
                }
                if (!bookingDate.getApplicantId().equals(prevApplicantId)) {
                	return false;
                }
			}
		}
		
		return true;
	}

}
