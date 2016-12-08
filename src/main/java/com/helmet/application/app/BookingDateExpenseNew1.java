package com.helmet.application.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AppService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingExpense;


public class BookingDateExpenseNew1 extends AppAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	if (isCancelled(request)) {
    		dynaForm.set("page", (Integer)dynaForm.get("page") - 1);
         	return mapping.findForward("back");
    	}

    	// TODO - needs to be made secure - i.e. somehow use the thing held in the session
    	
    	BookingDateUserApplicant bookingDate = (BookingDateUserApplicant)dynaForm.get("bookingDate");

		AppService appService = ServiceFactory.getInstance().getAppService();

		bookingDate = appService.getBookingDateUserApplicantForApplicantForBookingDate(bookingDate.getBookingDateId());
		Booking booking = appService.getBooking(bookingDate.getBookingId());

//    	List<BookingExpense> expenses = appService.getBookingExpensesForBookingNotForBookingDate(bookingDate.getBookingId(), bookingDate.getBookingDateId());
    	List<BookingExpense> expenses = appService.getBookingExpensesForBooking(bookingDate.getBookingId());

		dynaForm.set("bookingDate", bookingDate); 
		dynaForm.set("bookingExpensesText", booking.getExpensesText()); 
		dynaForm.set("list", expenses);

    	logger.exit("Out going !!!");

     	return mapping.findForward("success");
    }

}
