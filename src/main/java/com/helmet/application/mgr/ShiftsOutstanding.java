package com.helmet.application.mgr;

import java.sql.Date;
import java.util.Calendar;
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
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUserApplicant;


public class ShiftsOutstanding extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		// need to to search for a date range - 1 year ago til today ???
		
		Date toDate = new Date(System.currentTimeMillis());
		
		Calendar theDate = Calendar.getInstance();
		theDate.setTime(toDate);
	    theDate.add(Calendar.YEAR, -1);
		  
		Date fromDate = new Date(theDate.getTimeInMillis());
		
		
		List<BookingDateUserApplicant> list = mgrService.getBookingDateUserApplicantsForManager(getManagerLoggedIn().getManagerId(), 
				                                null, null, null, null, BookingDate.BOOKINGDATE_STATUS_FILLED, BookingDate.BOOKINGDATE_WORKEDSTATUS_NULL,                                                  
				                                null, true, null, null, null, fromDate, toDate);
    	
		// in ancestor class
		sortShiftTotals(list, dynaForm);

		dynaForm.set("list", list); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
