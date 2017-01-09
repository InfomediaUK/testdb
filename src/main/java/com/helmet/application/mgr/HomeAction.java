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
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;


public class HomeAction extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date fromDate = new Date(cal.getTimeInMillis());
		Date toDate = fromDate;
		
        List unfilledBookings = mgrService.getBookingUsersForManagerAndStatusAndDateRange(getManagerLoggedIn().getManagerId(),
        		                                                                          Booking.BOOKING_STATUS_OPEN, 
        		                                                                          true, // singleCandidate
	                                                                                      fromDate, toDate);

        List unfilledShifts = mgrService.getBookingDateUserApplicantsForManagerAndStatusAndDateRange(getManagerLoggedIn().getManagerId(),
        		                                                                                     BookingDate.BOOKINGDATE_STATUS_OPEN, 
        		                                                                                     false, // singleCandidate
        		                                                                                     fromDate, toDate);

        List filledShifts = mgrService.getBookingDateUserApplicantsForManagerAndStatusAndDateRange(getManagerLoggedIn().getManagerId(),
																					               BookingDate.BOOKINGDATE_STATUS_FILLED, 
																					               null, // singleCandidate
																					               fromDate, toDate);
        
		dynaForm.set("unfilledBookings", unfilledBookings); 
		dynaForm.set("unfilledShifts", unfilledShifts); 
		dynaForm.set("filledShifts", filledShifts); 

    	logger.exit("Out going !!!");

    	return mapping.findForward("success");
    }

}
