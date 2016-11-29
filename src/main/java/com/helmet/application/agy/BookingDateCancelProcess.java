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
import com.helmet.bean.BookingDateUserApplicant;

public class BookingDateCancelProcess extends AgyBookingLockAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	BookingDateUserApplicant bookingDate = (BookingDateUserApplicant)dynaForm.get("bookingDate");

     	String cancelText = bookingDate.getCancelText();
     	
     	if (cancelText == null || "".equals(cancelText)) {
    		ActionMessages errors = new ActionMessages();
    		MessageResources messageResources = getResources(request);
            errors.add("bookingDate", new ActionMessage("errors.required", messageResources.getMessage("label.cancelText")));
            saveErrors(request, errors);
            return mapping.getInputForward();
     	}
     	AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
     	int rowCount = agyService.updateBookingDateCancel(bookingDate.getBookingDateId(), cancelText, bookingDate.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());
    	
    	logger.exit("Out going !!!");
    	
     	Integer bookingId = (Integer)dynaForm.get("bookingId");

     	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    			                 actionForward.getPath() + 
  				                 (actionForward.getPath().indexOf("?") > 0 ? "&" : "?") + 
  				                 "bookingGrade.bookingGradeId=" + bookingDate.getBookingGradeId(),
                                 actionForward.getRedirect());

    }

}