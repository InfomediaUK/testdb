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
import com.helmet.bean.Booking;
import com.helmet.bean.BookingUser;


public class BookingList extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
		List<BookingUser> list = null;
		
		String bookingStatusStr = (String)dynaForm.get("bookingStatus");

		try {
			Integer.parseInt(bookingStatusStr);
		}
		catch (NumberFormatException e) {
		    bookingStatusStr = null;	
		}
		
		String workedStatusStr = (String)dynaForm.get("workedStatus");

		try {
			Integer.parseInt(workedStatusStr);
		}
		catch (NumberFormatException e) {
		    workedStatusStr = null;	
		}
		
		String bookingGradeApplicantDateStatusStr = (String)dynaForm.get("bookingGradeApplicantDateStatus");

		try {
			Integer.parseInt(bookingGradeApplicantDateStatusStr);
		}
		catch (NumberFormatException e) {
			bookingGradeApplicantDateStatusStr = null;	
		}
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
        
		if (bookingStatusStr != null) {
			Integer bookingStatus = Integer.parseInt(bookingStatusStr);
			list = mgrService.getBookingUsersForManagerAndStatus(getManagerLoggedIn().getManagerId(), bookingStatus, bookingStatus == Booking.BOOKING_STATUS_DRAFT ? null : true);
		}
		else if (workedStatusStr != null) {
			list = mgrService.getBookingUsersForManagerAndWorkedStatus(getManagerLoggedIn().getManagerId(), Integer.parseInt(workedStatusStr));
		}
		else if (bookingGradeApplicantDateStatusStr != null) {
			list = mgrService.getBookingUsersForManagerAndBookingGradeApplicantDateStatus(getManagerLoggedIn().getManagerId(), Integer.parseInt(bookingGradeApplicantDateStatusStr));
		}
		else {
			// list shifts regardless of single candidate
			list = mgrService.getBookingUsersForManager(getManagerLoggedIn().getManagerId(), null);
		}
     	
		// in ancestor class
		sortBookingTotals(list, dynaForm);

		dynaForm.set("list", list);

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
