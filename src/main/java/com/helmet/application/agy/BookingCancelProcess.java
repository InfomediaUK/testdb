package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyBookingLockAction;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingGradeAgyEntity;


public class BookingCancelProcess extends AgyBookingLockAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingGradeAgyEntity bookingGrade = (BookingGradeAgyEntity)dynaForm.get("bookingGrade");

     	String cancelText = (String)dynaForm.get("cancelText");
     	
     	if (cancelText == null || "".equals(cancelText)) {
    		ActionMessages errors = new ActionMessages();
    		MessageResources messageResources = getResources(request);
            errors.add("booking", new ActionMessage("errors.required", messageResources.getMessage("label.cancelText")));
            saveErrors(request, errors);
            return mapping.getInputForward();
     	}
    	
    	AgyService agyService = ServiceFactory.getInstance().getAgyService();
     	
    	Booking booking = agyService.getBooking(bookingGrade.getBookingId());
    	
      	int rc = doAfterValidation(booking.getBookingId(), cancelText, booking.getNoOfChanges());
      	
		logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?bookingGrade.bookingGradeId=" + bookingGrade.getBookingGradeId(),
    	                         actionForward.getRedirect());
    }

    protected int doAfterValidation(Integer bookingId, String cancelText, Integer noOfChanges) {
    	// overidden in descendant class
    	return 0;
    }
		
}
