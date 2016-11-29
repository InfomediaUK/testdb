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


public class ChangeSecretWordProcess extends AgyAction {

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
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

    	// check old password is correct
        Consultant tempConsultant = new Consultant();
        
        tempConsultant.setAgencyId(getConsultantLoggedIn().getAgencyId());         
        tempConsultant.getUser().setLogin(getConsultantLoggedIn().getUser().getLogin());         
        tempConsultant.getUser().setSecretWord(oldSecretWord);         
		
      	try {
      		tempConsultant = agyService.validateSecretWord(tempConsultant);
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

      	if (getConsultantLoggedIn().getUser().getLogin().equals(newSecretWord)) {
      		// pwdHint cannot be the same as the resetPwdHintValue (currently 'password') - this forces a password change
            errors.add("login", new ActionMessage("errors.mustNotEqual", messageResources.getMessage("label.newSecretWord"), messageResources.getMessage("label.login")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	
      	int rc = doAfterValidation(request, getConsultantLoggedIn().getConsultantId(), newSecretWord, tempConsultant.getNoOfChanges());
      	
      	return mapping.findForward("success");
    }

    protected int doAfterValidation(HttpServletRequest request, Integer consultantId, String newSecretWord, Integer noOfChanges) {
    	// overidden in descendant class
    	return 0;
    }
    
}
