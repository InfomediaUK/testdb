package com.helmet.application.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.Applicant;


public class ChangeSecretWord extends AppAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
    	

     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    	
     	// re-initialize the form
    	dynaForm.initialize(mapping);
    	
    	
    	// if first time secretWord will equal login - 
    	// so set it in the form as the user is being forced to enter a secretWord for the first time 
    	Applicant currentApplicant = getApplicantLoggedIn();
    	if (currentApplicant.getUser().getSecretWord().equals(currentApplicant.getUser().getLogin())) {
    		dynaForm.set("oldSecretWord", currentApplicant.getUser().getSecretWord());
    	}
    	
      	return mapping.findForward("success");
    }

}
