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
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingUserEntityMgr;


public class BookingExtend extends MgrAction {

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

		if (booking == null) {
	     	return mapping.findForward("illegalaccess");
		}

     	BookingGradeApplicantUser bookingGradeApplicant = mgrService.getBookingGradeApplicantUserForBookingFilledSingleCandidate(booking.getBookingId());
        
    	// TODO need to send a better response back to user ...
        
		if (bookingGradeApplicant == null) {
	     	return mapping.findForward("illegalaccess");
		}
		
		BookingDateUser[] bookingDates = new BookingDateUser[booking.getBookingDates().size()];
        int i = 0;
        for (BookingDateUser bookingDate: booking.getBookingDateUserApplicants()) {
        	bookingDates[i] = bookingDate;
        	i++;
        }

        
        
        
        dynaForm.set("currentBookingDates", bookingDates);
		
        dynaForm.set("hourlyRate", booking.getRate());
        dynaForm.set("rrp", booking.getValue());
        
        dynaForm.set("booking", booking); 
        dynaForm.set("bookingGradeApplicant", bookingGradeApplicant); 

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
