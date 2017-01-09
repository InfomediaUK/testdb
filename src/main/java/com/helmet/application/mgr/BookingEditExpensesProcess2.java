package com.helmet.application.mgr;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.BookingUserEntity;
import com.helmet.bean.Expense;

public class BookingEditExpensesProcess2 extends BookingEditExpensesProcess {

    protected int doAfterValidation(BookingUserEntity booking, Expense[] bookingExpenses) {
    
    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
        //
    	return mgrService.updateBookingExpenses(booking, bookingExpenses, getManagerLoggedIn().getManagerId());
		
    }
    
}
