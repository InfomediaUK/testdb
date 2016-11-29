package com.helmet.application.agy;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;


public class BookingCancelProcess2 extends BookingCancelProcess {

    protected int doAfterValidation(Integer bookingId, String cancelText, Integer noOfChanges) {
    
    	AgyService agyService = ServiceFactory.getInstance().getAgyService();
        //
    	return agyService.updateBookingCancel(bookingId, cancelText, noOfChanges, getConsultantLoggedIn().getConsultantId());

		
    }

    
    
}
