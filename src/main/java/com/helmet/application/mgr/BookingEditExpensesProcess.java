package com.helmet.application.mgr;

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
import com.helmet.bean.BookingUserEntity;
import com.helmet.bean.BookingUserEntityMgr;
import com.helmet.bean.Expense;


public class BookingEditExpensesProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingUserEntityMgr booking = (BookingUserEntityMgr)dynaForm.get("booking");
        String[] selectedExpenses = (String[])dynaForm.get("selectedExpenses");

    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		Expense[] bookingExpenses = new Expense[selectedExpenses.length - 1]; // array includes a dummy 0 entry - checkbox related!
        int e = 0;
		for (String expenseIdStr : selectedExpenses) {
    		int expenseId = Integer.parseInt(expenseIdStr);
    		if (expenseId > 0) {
    			bookingExpenses[e] = mgrService.getExpense(expenseId);    			
    			e++;
    		}
    	}

    	// VALIDATION
    	
      	int rc = doAfterValidation(booking, bookingExpenses);
      	
		logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?booking.bookingId=" + booking.getBookingId(),
    	                         actionForward.getRedirect());
    }

    protected int doAfterValidation(BookingUserEntity booking, Expense[] bookingExpenses) {
    	// overidden in descendant class
    	return 0;
    }
		
}
