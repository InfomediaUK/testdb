package com.helmet.application.mgr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingUserEntityMgr;
import com.helmet.bean.Expense;


public class BookingEditExpenses extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingUserEntityMgr booking = (BookingUserEntityMgr)dynaForm.get("booking");

    	dynaForm.initialize(mapping);

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		booking = mgrService.getBookingUserEntityMgr(booking.getBookingId(), getManagerLoggedIn().getManagerId());

    	// TODO need to check for NULL object ...
        
		if (booking == null) {
	     	return mapping.findForward("illegalaccess");
		}
		
    	List<Expense> list = mgrService.getExpensesForLocation(booking.getLocationId());
    	
		List<BookingExpense> bookingExpenses = booking.getBookingExpenses();
		String[] selectedExpenses = new String[bookingExpenses.size() + 1];
		selectedExpenses[0] = "0"; // the DUMMY expense! 
		int e = 1;
		for (BookingExpense bookingExpense: bookingExpenses){
			selectedExpenses[e] = bookingExpense.getExpenseId().toString();
			e++;
		}

		dynaForm.set("list", list);
        dynaForm.set("selectedExpenses", selectedExpenses);
        dynaForm.set("booking", booking); 

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
