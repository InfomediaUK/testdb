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
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.persistence.Utilities;

public class BookingDatesAuthorizeProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");


    	
    	
    	// below >>>>> should be in an ancestor class


//    	ActionMessages errors = new ActionMessages();
//
//		MessageResources messageResources = getResources(request);
//
//		String pwd = (String)dynaForm.get("pwd");
//
//        if (pwd == null || "".equals(pwd)) {
//            // required pwd
//        	errors.add("pwd", new ActionMessage("errors.required", messageResources.getMessage("label.pwd")));
//	        saveErrors(request, errors);
//			return mapping.getInputForward();
//     	}
//        else if (!Utilities.encryptPassword(pwd).equals(getManagerLoggedIn().getUser().getPwd())) {
//            errors.add("pwd", new ActionMessage("errors.invalid", messageResources.getMessage("label.pwd")));
//            saveErrors(request, errors);
//    		return mapping.getInputForward();
//        }

        
    	// above <<<<< should be in an ancestor class


        
    	
    	
        String bookingDateIdStrs = (String)dynaForm.get("bookingDateIdStrs");
    	
     	MgrService mgrService = ServiceFactory.getInstance().getMgrService();

     	int rowCount = mgrService.updateBookingDatesAuthorized(bookingDateIdStrs, getManagerLoggedIn().getManagerId());
     	
     	
     	// need to clear form as now held in the session
     	
     	
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");

    }

}