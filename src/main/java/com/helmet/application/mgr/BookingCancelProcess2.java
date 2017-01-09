package com.helmet.application.mgr;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;


public class BookingCancelProcess2 extends BookingCancelProcess {

    protected int doAfterValidation(Integer bookingId, String cancelText, Integer noOfChanges) {
    
    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
        //
    	return mgrService.updateBookingCancel(bookingId, cancelText, noOfChanges, getManagerLoggedIn().getManagerId());

		
    }

    
    
}
