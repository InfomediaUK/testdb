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
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingExpense;

public class BookingDateExpenses extends AppAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	BookingDateUserApplicant bookingDate = (BookingDateUserApplicant)dynaForm.get("bookingDate");

		AppService appService = ServiceFactory.getInstance().getAppService();

		bookingDate = appService.getBookingDateUserApplicantForApplicantForBookingDate(bookingDate.getBookingDateId());

    	List<BookingExpense> expenses = appService.getBookingExpensesForBooking(bookingDate.getBookingId());

		dynaForm.set("expenses", expenses);
		
		dynaForm.set("bookingDate", bookingDate); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}