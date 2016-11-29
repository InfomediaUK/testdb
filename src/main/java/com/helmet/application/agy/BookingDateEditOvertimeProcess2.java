package com.helmet.application.agy;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.BookingDateUserApplicant;


public class BookingDateEditOvertimeProcess2 extends BookingDateEditOvertimeProcess {

    protected int doAfterValidation(BookingDateUserApplicant bookingDate) {
    
    	AgyService agyService = ServiceFactory.getInstance().getAgyService();
        //
    	return agyService.updateBookingDateOvertime(bookingDate, getConsultantLoggedIn().getConsultantId());
		
    }

    
    
}
