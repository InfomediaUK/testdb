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
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.Manager;


public class ChangeSecretWordProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    	
      	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

      	String oldSecretWord = (String)dynaForm.get("oldSecretWord");
      	String newSecretWord = (String)dynaForm.get("newSecretWord");
      	String confirmSecretWord = (String)dynaForm.get("confirmSecretWord");

		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

    	// check old password is correct
        Manager tempManager = new Manager();
        
        tempManager.setClientId(getManagerLoggedIn().getClientId());         
        tempManager.getUser().setLogin(getManagerLoggedIn().getUser().getLogin());         
        tempManager.getUser().setSecretWord(oldSecretWord);         
		
      	try {
      		tempManager = mgrService.validateSecretWord(tempManager);
      	}
      	catch (DataNotFoundException e) {
            errors.add("login", new ActionMessage("errors.invalidCaseSensitive", messageResources.getMessage("label.login")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	catch (InvalidDetailException e) {
            errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.oldSecretWord")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}

      	if (getManagerLoggedIn().getUser().getLogin().equals(newSecretWord)) {
      		// pwdHint cannot be the same as the resetPwdHintValue (currently 'password') - this forces a password change
            errors.add("login", new ActionMessage("errors.mustNotEqual", messageResources.getMessage("label.newSecretWord"), messageResources.getMessage("label.login")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	
      	int rc = doAfterValidation(request, getManagerLoggedIn().getManagerId(), newSecretWord, tempManager.getNoOfChanges());
      	
      	return mapping.findForward("success");
    }

    protected int doAfterValidation(HttpServletRequest request, Integer managerId, String newSecretWord, Integer noOfChanges) {
    	// overidden in descendant class
    	return 0;
    }
    
}
