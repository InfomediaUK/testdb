package com.helmet.application.admin;

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

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Administrator;


public class ChangeSecretWordProcess extends AdminAction {

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
		
		AdminService adminService = ServiceFactory.getInstance().getAdminService();

    	// check old password is correct
        Administrator tempAdministrator = new Administrator();
        
        tempAdministrator.getUser().setLogin(getAdministratorLoggedIn().getUser().getLogin());         
        tempAdministrator.getUser().setSecretWord(oldSecretWord);         
		
      	try {
      		tempAdministrator = adminService.validateSecretWord(tempAdministrator);
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

      	if (getAdministratorLoggedIn().getUser().getLogin().equals(newSecretWord)) {
      		// pwdHint cannot be the same as the resetPwdHintValue (currently 'password') - this forces a password change
            errors.add("login", new ActionMessage("errors.mustNotEqual", messageResources.getMessage("label.newSecretWord"), messageResources.getMessage("label.login")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	
      	int rc = doAfterValidation(request, getAdministratorLoggedIn().getAdministratorId(), newSecretWord, tempAdministrator.getNoOfChanges());
      	
      	return mapping.findForward("success");
    }

    protected int doAfterValidation(HttpServletRequest request, Integer administratorId, String newSecretWord, Integer noOfChanges) {
    	// overidden in descendant class
    	return 0;
    }
    
}
