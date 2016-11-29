package com.helmet.application.agy;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Booking;


public class BookingExtendCompletedFinish extends BookingExtendFinish {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    protected int anyOtherBusiness(Booking booking) {

    	AgyService agyService = ServiceFactory.getInstance().getAgyService();
    	
    	// bit dodgy - get the booking that will have just been updated so that we have the correct noOfChanges
    	Booking theBooking = agyService.getBooking(booking.getBookingId());

    	return agyService.updateBookingClosed(theBooking.getBookingId(), theBooking.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());

    }

}
