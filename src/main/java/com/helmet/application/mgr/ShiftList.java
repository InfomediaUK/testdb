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
import com.helmet.bean.BookingDateUserApplicant;


public class ShiftList extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		List<BookingDateUserApplicant> list = null;
		
		String bookingDateStatusStr = (String)dynaForm.get("bookingDateStatus");

		try {
			Integer.parseInt(bookingDateStatusStr);
		}
		catch (NumberFormatException e) {
		    bookingDateStatusStr = null;	
		}
		
		String workedStatusStr = (String)dynaForm.get("workedStatus");

		try {
			Integer.parseInt(workedStatusStr);
		}
		catch (NumberFormatException e) {
		    workedStatusStr = null;	
		}
		

		
		if (bookingDateStatusStr != null) {
			// list only shifts that are from NON single candidate bookings
			list = mgrService.getBookingDateUserApplicantsForManagerAndStatus(getManagerLoggedIn().getManagerId(),
					                                                          Integer.parseInt(bookingDateStatusStr), false);
		}
		else if (workedStatusStr != null) {
			// list shifts regardless of single candidate
			list = mgrService.getBookingDateUserApplicantsForManagerAndWorkedStatus(getManagerLoggedIn().getManagerId(), 
                                                                                    Integer.parseInt(workedStatusStr), 
                                                                                    null);
		}
		else {
			// list shifts regardless of single candidate
			list = mgrService.getBookingDateUserApplicantsForManager(getManagerLoggedIn().getManagerId(), null);
		}
    	
		// in ancestor class
		sortShiftTotals(list, dynaForm);

		dynaForm.set("list", list); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
