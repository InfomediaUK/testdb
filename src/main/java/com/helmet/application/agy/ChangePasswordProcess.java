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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Consultant;


public class ChangePasswordProcess extends AgyAction {

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
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

    	// check old password is correct
        Consultant tempConsultant = new Consultant();
        
        tempConsultant.setAgencyId(getConsultantLoggedIn().getAgencyId());         
        tempConsultant.getUser().setLogin(getConsultantLoggedIn().getUser().getLogin());         
        tempConsultant.getUser().setPwd(oldPwd);         
		
      	try {
      		tempConsultant = agyService.validateLogin(tempConsultant);
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

      	if (AgyConstants.RESETPWDHINT.equals(pwdHint)) {
      		// pwdHint cannot be the same as the resetPwdHintValue (currently 'password') - this forces a password change
            errors.add("login", new ActionMessage("errors.mustNotEqual", messageResources.getMessage("label.pwdHint"), "'" + AgyConstants.RESETPWDHINT + "'"));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	
    	// set new password and hint
      	// update the consultant record
      	int rc = agyService.updateConsultantPwd(getConsultantLoggedIn().getConsultantId(), newPwd, pwdHint, tempConsultant.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());

      	// set the session consultant.user.pwdHint
    	Consultant currentConsultant = AgyUtilities.getCurrentConsultant(request);
    	currentConsultant.getUser().setPwdHint(pwdHint);
    	AgyUtilities.setCurrentConsultant(request, currentConsultant);
    	
      	return mapping.findForward("success");
    }

}
