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


public class BookingExtendFinish extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	Integer page = (Integer)dynaForm.get("page");
    	if (isCancelled(request)) {
    		dynaForm.set("page", page - 1);
         	return mapping.findForward("back");
    	}

    	BookingUserEntityMgr booking = (BookingUserEntityMgr)dynaForm.get("booking");

    	BookingDateUser[] bookingDates = (BookingDateUser[])dynaForm.get("bookingDates");
    	
        BookingGradeApplicantUser bookingGradeApplicant = (BookingGradeApplicantUser)dynaForm.get("bookingGradeApplicant"); 
        
    	MgrService mgrService = ServiceFactory.getInstance().getMgrService();

    	int rc = mgrService.updateBookingExtend(booking, bookingDates, bookingGradeApplicant, getManagerLoggedIn().getManagerId());

    	rc = anyOtherBusiness(booking);
    	
    	logger.exit("Out going !!!");

    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?booking.bookingId=" + booking.getBookingId(),
    	                         actionForward.getRedirect());
    }

    protected int anyOtherBusiness(BookingUserEntityMgr booking) {
    	// overidden in descendant classes
    	return 0;
    }
}
