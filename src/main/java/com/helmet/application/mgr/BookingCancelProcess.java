package com.helmet.application.mgr;

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

import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.BookingUserEntity;


public class BookingCancelProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingUserEntity booking = (BookingUserEntity)dynaForm.get("booking");

     	String cancelText = booking.getCancelText();
     	
     	if (cancelText == null || "".equals(cancelText)) {
    		ActionMessages errors = new ActionMessages();
    		MessageResources messageResources = getResources(request);
            errors.add("booking", new ActionMessage("errors.required", messageResources.getMessage("label.cancelText")));
            saveErrors(request, errors);
            return mapping.getInputForward();
     	}
    	
      	int rc = doAfterValidation(booking.getBookingId(), cancelText, booking.getNoOfChanges());
      	
		logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?booking.bookingId=" + booking.getBookingId(),
    	                         actionForward.getRedirect());
    }

    protected int doAfterValidation(Integer bookingId, String cancelText, Integer noOfChanges) {
    	// overidden in descendant class
    	return 0;
    }
		
}
