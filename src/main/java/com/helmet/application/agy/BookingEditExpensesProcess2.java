package com.helmet.application.agy;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.Expense;

public class BookingEditExpensesProcess2 extends BookingEditExpensesProcess {

    protected int doAfterValidation(BookingGradeAgyEntity bookingGrade, Expense[] bookingExpenses) {
    
    	AgyService agyService = ServiceFactory.getInstance().getAgyService();
    	
    	//
    	// we have a bookingGrade and we need to update the booking !!
    	//
    	Booking booking = agyService.getBooking(bookingGrade.getBookingId()); 
    	
    	booking.setExpensesText(bookingGrade.getExpensesText());
    	
        //
    	return agyService.updateBookingExpenses(booking, bookingExpenses, getConsultantLoggedIn().getConsultantId());
		
    }
    
}
