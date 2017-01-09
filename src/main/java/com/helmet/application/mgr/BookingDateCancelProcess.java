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

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.mgr.abztract.MgrBookingLockAction;
import com.helmet.bean.BookingDate;

public class BookingDateCancelProcess extends MgrBookingLockAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	BookingDate bookingDate = (BookingDate)dynaForm.get("bookingDate");

     	String cancelText = bookingDate.getCancelText();
     	
     	if (cancelText == null || "".equals(cancelText)) {
    		ActionMessages errors = new ActionMessages();
    		MessageResources messageResources = getResources(request);
            errors.add("bookingDate", new ActionMessage("errors.required", messageResources.getMessage("label.cancelText")));
            saveErrors(request, errors);
            return mapping.getInputForward();
     	}
     	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
     	int rowCount = mgrService.updateBookingDateCancel(bookingDate.getBookingDateId(), cancelText, bookingDate.getNoOfChanges(), getManagerLoggedIn().getManagerId());
    	
    	logger.exit("Out going !!!");
    	
     	Integer bookingId = (Integer)dynaForm.get("bookingId");

     	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?booking.bookingId=" + bookingId,
    	                         actionForward.getRedirect());

    }

}