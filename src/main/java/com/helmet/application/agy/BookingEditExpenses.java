package com.helmet.application.agy;

import java.util.List;

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
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.Expense;


public class BookingEditExpenses extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingGradeAgyEntity bookingGrade = (BookingGradeAgyEntity)dynaForm.get("bookingGrade");
    	
    	dynaForm.initialize(mapping);

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

        bookingGrade = agyService.getBookingGradeAgyEntity(bookingGrade.getBookingGradeId(), getConsultantLoggedIn().getAgencyId());

        if (bookingGrade == null) {
	     	return mapping.findForward("illegalaccess");
        }

    	List<Expense> list = agyService.getExpensesForLocation(bookingGrade.getLocationId());
    	
		List<BookingExpense> bookingExpenses = bookingGrade.getBookingExpenses();
		String[] selectedExpenses = new String[bookingExpenses.size() + 1];
		selectedExpenses[0] = "0"; // the DUMMY expense! 
		int e = 1;
		for (BookingExpense bookingExpense: bookingExpenses){
			selectedExpenses[e] = bookingExpense.getExpenseId().toString();
			e++;
		}

		dynaForm.set("list", list);
        dynaForm.set("selectedExpenses", selectedExpenses);
        dynaForm.set("bookingGrade", bookingGrade); 

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
