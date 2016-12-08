package com.helmet.application.app;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.BookingExpense;


public class BookingDateExpenseNew2 extends AppAction {

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

    	BookingExpense bookingExpenseSelected = (BookingExpense)dynaForm.get("bookingExpense");

    	List<BookingExpense> list = (List<BookingExpense>)dynaForm.get("list");
        for (BookingExpense bookingExpense: list) {
        	if (bookingExpense.getBookingExpenseId().equals(bookingExpenseSelected.getBookingExpenseId())) {
        		bookingExpenseSelected = bookingExpense;
        	}
        }
        
		dynaForm.set("bookingExpense", bookingExpenseSelected);

    	logger.exit("Out going !!!");

     	return mapping.findForward("success");
    }

}
