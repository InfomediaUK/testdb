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


public class ChangePasswordProcess extends AdminAction {

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
		
		AdminService adminService = ServiceFactory.getInstance().getAdminService();

    	// check old password is correct
        Administrator tempAdministrator = new Administrator();
        
        tempAdministrator.getUser().setLogin(getAdministratorLoggedIn().getUser().getLogin());         
        tempAdministrator.getUser().setPwd(oldPwd);         
		
      	try {
      		tempAdministrator = adminService.validateLogin(tempAdministrator);
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

      	if (AdminConstants.RESETPWDHINT.equals(pwdHint)) {
      		// pwdHint cannot be the same as the resetPwdHintValue (currently 'password') - this forces a password change
            errors.add("login", new ActionMessage("errors.mustNotEqual", messageResources.getMessage("label.pwdHint"), "'" + AdminConstants.RESETPWDHINT + "'"));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	
    	// set new password and hint
      	// update the administrator record
      	int rc = adminService.updateAdministratorPwd(getAdministratorLoggedIn().getAdministratorId(), newPwd, pwdHint, tempAdministrator.getNoOfChanges(), getAdministratorLoggedIn().getAdministratorId());

      	// set the session consultant.user.pwdHint
      	Administrator currentAdministrator = AdminUtilities.getCurrentAdministrator(request);
    	currentAdministrator.getUser().setPwdHint(pwdHint);
    	AdminUtilities.setCurrentAdministrator(request, currentAdministrator);
    	
      	return mapping.findForward("success");
    }

}
