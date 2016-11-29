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
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingGradeAgy;
import com.helmet.bean.Expense;


public class BookingGradeApplicantNew4 extends AgyAction {

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

    	String[] selectedBookingDates = (String[])dynaForm.get("selectedBookingDates");
		
    	// array includes a dummy 0 entry - checkbox related!
        
    	if (selectedBookingDates.length == 1) {
    		// no dates selected
			ActionMessages errors = new ActionMessages();
			MessageResources messageResources = getResources(request);
	        errors.add("bookingGradeApplicantNew", new ActionMessage("errors.required", messageResources.getMessage("label.atLeastOneDate")));
	        saveErrors(request, errors);
	   		return mapping.getInputForward();
    	}
    	
    	BookingGradeAgy bookingGradeAgy = (BookingGradeAgy)dynaForm.get("bookingGrade");

        if (!bookingGradeAgy.getCvRequired()) {
 		
	    	// no cv required so skip the step
			//
			// determine whether the user is going forwards (next) or backwards (back)
	    	boolean userPressedNext = page == 3;
	        // miss out this step and based on the mapping config go to the next
	     	if (userPressedNext) {
	    		dynaForm.set("page", page + 1);
	     		return mapping.findForward("noCvRequiredSuccess");
		    }
	     	else {
	    		dynaForm.set("page", page - 1);
	         	return mapping.findForward("noCvRequiredBack");
	     	}
    	
        }    	
    	
    	logger.exit("Out going !!!");

     	return mapping.findForward("success");
    }

}
