package com.helmet.bean;

import java.util.List;

public class InvoiceEntity extends Invoice {

	private List<BookingDateUserApplicant> bookingDateUserApplicants;

	private List<InvoiceAgencyUser> invoiceAgencies;

//	private List<BookingDateUserApplicantEntity> bookingDateUserApplicantEntities;

	public List<BookingDateUserApplicant> getBookingDateUserApplicants() {
		return bookingDateUserApplicants;
	}

	public void setBookingDateUserApplicants(
			List<BookingDateUserApplicant> bookingDateUserApplicants) {
		this.bookingDateUserApplicants = bookingDateUserApplicants;
	}

	public List<InvoiceAgencyUser> getInvoiceAgencies() {
		return invoiceAgencies;
	}

	public void setInvoiceAgencies(List<InvoiceAgencyUser> invoiceAgencies) {
		this.invoiceAgencies = invoiceAgencies;
	}

//	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntities() {
//		return bookingDateUserApplicantEntities;
//	}
//
//	public void setBookingDateUserApplicantEntities(
//			List<BookingDateUserApplicantEntity> bookingDateUserApplicantEntities) {
//		this.bookingDateUserApplicantEntities = bookingDateUserApplicantEntities;
//	}
	
//	public List<Integer> getAgencyIds() {
//		List<Integer> agencyIds = new ArrayList<Integer>();
//		for(BookingDateUserApplicantEntity bookingDate: getBookingDateUserApplicantEntities()) {
//			boolean found = false;
//			for (Integer agencyId: agencyIds) {
//				if (agencyId.equals(bookingDate.getAgencyId())) {
//					found = true;
//					break;
//				}
//			}
//			if (!found) {
//				agencyIds.add(bookingDate.getAgencyId());
//			}
//		}
//		return agencyIds;
//	}

//	public List<Integer> getApplicantIdsForAgency(Integer agencyId) {
//		List<Integer> applicantIds = new ArrayList<Integer>();
//		for(BookingDateUserApplicantEntity bookingDate: getBookingDateUserApplicantEntities()) {
//			if (agencyId.equals(bookingDate.getAgencyId())) {
//				boolean found = false;
//				for (Integer applicantId: applicantIds) {
//					if (applicantId.equals(bookingDate.getApplicantId())) {
//						found = true;
//						break;
//					}
//				}
//				if (!found) {
//					applicantIds.add(bookingDate.getApplicantId());
//				}
//			}
//		}
//		return applicantIds;
//	}
	
}
