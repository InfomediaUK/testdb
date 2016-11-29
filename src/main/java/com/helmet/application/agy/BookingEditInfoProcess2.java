package com.helmet.application.agy;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingGradeAgyEntity;


public class BookingEditInfoProcess2 extends BookingEditInfoProcess {

    protected int doAfterValidation(BookingGradeAgyEntity bookingGrade) {
    
    	AgyService agyService = ServiceFactory.getInstance().getAgyService();

    	//
    	// we have a bookingGrade and we need to update the booking !!
    	//
    	Booking booking = agyService.getBooking(bookingGrade.getBookingId()); 
    	
    	booking.setDuration(bookingGrade.getDuration());
    	booking.setBookingReference(bookingGrade.getBookingReference());
    	booking.setContactName(bookingGrade.getContactName());
    	booking.setContactJobTitle(bookingGrade.getContactJobTitle());
    	booking.setContactEmailAddress(bookingGrade.getContactEmailAddress());
    	booking.setContactTelephoneNumber(bookingGrade.getContactTelephoneNumber());
    	booking.setAccountContactName(bookingGrade.getAccountContactName());
    	booking.setAccountContactAddress(bookingGrade.getAccountContactAddress());
    	booking.setAccountContactEmailAddress(bookingGrade.getAccountContactEmailAddress());
    	
    	return agyService.updateBookingInfo(booking, getConsultantLoggedIn().getConsultantId());
		
    }

    
    
}
