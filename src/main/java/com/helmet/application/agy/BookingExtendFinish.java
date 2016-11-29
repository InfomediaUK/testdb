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
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicantUser;


public class BookingExtendFinish extends AgyAction {

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

    	BookingGradeAgyEntity bookingGrade = (BookingGradeAgyEntity)dynaForm.get("bookingGrade");
		
    	BookingDateUser[] bookingDates = (BookingDateUser[])dynaForm.get("bookingDates");
    	
        BookingGradeApplicantUser bookingGradeApplicant = (BookingGradeApplicantUser)dynaForm.get("bookingGradeApplicant"); 
        
    	AgyService agyService = ServiceFactory.getInstance().getAgyService();
    	
    	Booking booking = agyService.getBooking(bookingGrade.getBookingId());

    	int rc = agyService.updateBookingExtend(booking, bookingDates, bookingGradeApplicant, getConsultantLoggedIn().getConsultantId());

    	rc = anyOtherBusiness(booking);
    	
    	logger.exit("Out going !!!");

    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?bookingGrade.bookingGradeId=" + bookingGrade.getBookingGradeId(),
    	                         actionForward.getRedirect());
    }

    protected int anyOtherBusiness(Booking booking) {
    	// overidden in descendant classes
    	return 0;
    }
}
