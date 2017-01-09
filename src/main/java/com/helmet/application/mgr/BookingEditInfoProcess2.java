package com.helmet.application.mgr;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.BookingUserEntity;


public class BookingEditInfoProcess2 extends BookingEditInfoProcess {

    protected int doAfterValidation(BookingUserEntity booking) {
    
    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
        //
    	return mgrService.updateBookingInfo(booking, getManagerLoggedIn().getManagerId());
		
    }

    
    
}
