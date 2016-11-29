package com.helmet.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AgencyInvoiceUserEntity extends AgencyInvoiceUser {

	private List<BookingDateUserApplicantEntity> bookingDateUserApplicants;

	private List<BigDecimal> upliftFactors;
	
	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicants() {
		return bookingDateUserApplicants;
	}

	public void setBookingDateUserApplicants(
			List<BookingDateUserApplicantEntity> bookingDateUserApplicants) {
		this.bookingDateUserApplicants = bookingDateUserApplicants;
		sortUpliftFactors();
	}

	public List<BigDecimal> getUpliftFactors() {
		return upliftFactors;
	}

	public void setUpliftFactors(List<BigDecimal> upliftFactors) {
		this.upliftFactors = upliftFactors;
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

	public void sortUpliftFactors() {
		upliftFactors = new ArrayList<BigDecimal>();
		
        for (BookingDateUserApplicantEntity bookingDateUserApplicantEntity: bookingDateUserApplicants) {
        	if (bookingDateUserApplicantEntity.getBookingDateHours() != null) {
                for (BookingDateHour bookingDateHour: bookingDateUserApplicantEntity.getBookingDateHours()) {
                	// check if the upliftFactor already exists in the list
                	boolean upliftExists = false;
                	for (BigDecimal upliftFactor: upliftFactors) {
                		if (upliftFactor.compareTo(bookingDateHour.getUpliftFactor()) == 0) {
                			// already exists
                			upliftExists = true;
                			break;
                		}
                	}
                	if (!upliftExists) {
                		upliftFactors.add(bookingDateHour.getUpliftFactor());
                	}
                }
        	}
        }

		// remove uplift stuff if only one and = 1
		if (upliftFactors.size() == 1 && upliftFactors.get(0).compareTo(new BigDecimal(1)) == 0 ) {
			upliftFactors.remove(0);
		}
		
        Collections.sort(upliftFactors);

	}
	
	
	
}
