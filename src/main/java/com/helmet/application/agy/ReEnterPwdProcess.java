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
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Consultant;


public class ReEnterPwdProcess extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
        ActionForward af = validateLogin(mapping, form, request);
		
        if (af != null) {
        	return af;
        }
        
        return Utilities.whereToNow(mapping, request);
        
    }

    public ActionForward validateLogin(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request) {
    	
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;

      	String pwd = (String)dynaForm.get("pwd");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

    	// check old password is correct
        Consultant tempConsultant = new Consultant();
        
        tempConsultant.setAgencyId(getConsultantLoggedIn().getAgencyId());         
        tempConsultant.getUser().setLogin(getConsultantLoggedIn().getUser().getLogin());         
        tempConsultant.getUser().setPwd(pwd);         
		
		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);
		
      	try {
      		tempConsultant = agyService.validateLogin(tempConsultant);
      	}
      	catch (DataNotFoundException e) {
            errors.add("login", new ActionMessage("errors.invalidCaseSensitive", messageResources.getMessage("label.login")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	catch (InvalidDetailException e) {
            errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.pwd")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}

      	AgyUtilities.setPwdEntered(request);
      	
     	// re-initialize the form
    	dynaForm.initialize(mapping);

      	return null;

    }

}
