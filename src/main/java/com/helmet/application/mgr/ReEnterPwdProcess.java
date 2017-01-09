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
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.application.Utilities;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.Manager;


public class ReEnterPwdProcess extends MgrAction {

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

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

    	// check old password is correct
        Manager tempManager = new Manager();
        
        tempManager.setClientId(getManagerLoggedIn().getClientId());         
        tempManager.getUser().setLogin(getManagerLoggedIn().getUser().getLogin());         
        tempManager.getUser().setPwd(pwd);         
		
		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);
		
      	try {
      		tempManager = mgrService.validateLogin(tempManager);
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

      	MgrUtilities.setPwdEntered(request);
      	
     	// re-initialize the form
    	dynaForm.initialize(mapping);

      	return null;

    }

}
