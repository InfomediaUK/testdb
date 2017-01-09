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
import com.helmet.bean.BookingGrade;


public class BookingGradeSubmitProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingGrade bookingGrade = (BookingGrade)dynaForm.get("bookingGrade");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		bookingGrade = mgrService.getBookingGrade(bookingGrade.getBookingGradeId());

		int rc = mgrService.updateBookingGradeOpen(bookingGrade.getBookingGradeId(), bookingGrade.getNoOfChanges(), getManagerLoggedIn().getManagerId());

    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?booking.bookingId=" + bookingGrade.getBookingId(),
    	                         actionForward.getRedirect());
    }

}
