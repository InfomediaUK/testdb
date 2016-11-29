package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingDateUserApplicant;


public class BookingDateEditOvertimeProcess extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingDateUserApplicant bookingDate = (BookingDateUserApplicant)dynaForm.get("bookingDate");

    	// VALIDATION
    	
      	int rc = doAfterValidation(bookingDate);
      	
		logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + 
                   				 (actionForward.getPath().indexOf("?") > 0 ? "&" : "?") + 
                   				 "bookingGrade.bookingGradeId=" + bookingDate.getBookingGradeId(),
    	                         actionForward.getRedirect());
    }

    protected int doAfterValidation(BookingDateUserApplicant bookingDate) {
    	// overidden in descendant class
    	return 0;
    }
		
}
