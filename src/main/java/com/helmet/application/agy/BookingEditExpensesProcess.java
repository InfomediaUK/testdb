package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.Expense;


public class BookingEditExpensesProcess extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingGradeAgyEntity bookingGrade = (BookingGradeAgyEntity)dynaForm.get("bookingGrade");
        String[] selectedExpenses = (String[])dynaForm.get("selectedExpenses");

    	AgyService agyService = ServiceFactory.getInstance().getAgyService();

		Expense[] bookingExpenses = new Expense[selectedExpenses.length - 1]; // array includes a dummy 0 entry - checkbox related!
        int e = 0;
		for (String expenseIdStr : selectedExpenses) {
    		int expenseId = Integer.parseInt(expenseIdStr);
    		if (expenseId > 0) {
    			bookingExpenses[e] = agyService.getExpense(expenseId);    			
    			e++;
    		}
    	}

    	// VALIDATION
    	
      	int rc = doAfterValidation(bookingGrade, bookingExpenses);
      	
		logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
				                 actionForward.getPath() + 
	                             (actionForward.getPath().indexOf("?") > 0 ? "&" : "?") + 
	                             "bookingGrade.bookingGradeId=" + bookingGrade.getBookingGradeId(),
                                 actionForward.getRedirect());
    }

    protected int doAfterValidation(BookingGradeAgyEntity bookingGrade, Expense[] bookingExpenses) {
    	// overidden in descendant class
    	return 0;
    }
		
}
