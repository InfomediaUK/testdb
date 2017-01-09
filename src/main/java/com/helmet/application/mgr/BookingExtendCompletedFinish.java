package com.helmet.application.mgr;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingUserEntityMgr;


public class BookingExtendCompletedFinish extends BookingExtendFinish {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    protected int anyOtherBusiness(BookingUserEntityMgr booking) {

    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
    	
    	// bit dodgy - get the booking that will have just been updated so that we have the correct noOfChanges
    	Booking theBooking = mgrService.getBooking(booking.getBookingId());

    	return mgrService.updateBookingClosed(theBooking.getBookingId(), theBooking.getNoOfChanges(), getManagerLoggedIn().getManagerId());

    }

}
