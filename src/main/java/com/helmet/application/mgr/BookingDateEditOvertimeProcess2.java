package com.helmet.application.mgr;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingUserEntity;


public class BookingDateEditOvertimeProcess2 extends BookingDateEditOvertimeProcess {

    protected int doAfterValidation(BookingDateUserApplicant bookingDate) {
    
    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
        //
    	return mgrService.updateBookingDateOvertime(bookingDate, getManagerLoggedIn().getManagerId());
		
    }

    
    
}
