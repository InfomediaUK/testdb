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


public class ChangePasswordProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    	
      	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

      	String oldPwd = (String)dynaForm.get("oldPwd");
      	String newPwd = (String)dynaForm.get("newPwd");
      	String confirmPwd = (String)dynaForm.get("confirmPwd");
      	String pwdHint = (String)dynaForm.get("pwdHint");

		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

    	// check old password is correct
        Manager tempManager = new Manager();
        
        tempManager.setClientId(getManagerLoggedIn().getClientId());         
        tempManager.getUser().setLogin(getManagerLoggedIn().getUser().getLogin());         
        tempManager.getUser().setPwd(oldPwd);         
		
      	try {
      		tempManager = mgrService.validateLogin(tempManager);
      	}
      	catch (DataNotFoundException e) {
            errors.add("login", new ActionMessage("errors.invalidCaseSensitive", messageResources.getMessage("label.login")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	catch (InvalidDetailException e) {
            errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.oldPwd")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}

      	if (MgrConstants.RESETPWDHINT.equals(pwdHint)) {
      		// pwdHint cannot be the same as the resetPwdHintValue (currently 'password') - this forces a password change
            errors.add("login", new ActionMessage("errors.mustNotEqual", messageResources.getMessage("label.pwdHint"), "'" + MgrConstants.RESETPWDHINT + "'"));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	
    	// set new password and hint
      	// update the manager record
      	int rc = mgrService.updateManagerPwd(getManagerLoggedIn().getManagerId(), newPwd, pwdHint, tempManager.getNoOfChanges(), getManagerLoggedIn().getManagerId());

      	// set the session manager.user.pwdHint
    	Manager currentManager = MgrUtilities.getCurrentManager(request);
    	currentManager.getUser().setPwdHint(pwdHint);
    	MgrUtilities.setCurrentManager(request, currentManager);
    	
      	return mapping.findForward("success");
    }

}
