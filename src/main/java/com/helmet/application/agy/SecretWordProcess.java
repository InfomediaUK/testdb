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

import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;


public class SecretWordProcess extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
        ActionForward af = validateSecretWord(mapping, form, request);
		
        if (af != null) {
        	return af;
        }
        
        return Utilities.whereToNow(mapping, request);
        
    }
    
    public ActionForward validateSecretWord(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request) {
    	
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
      	
      	Integer secretWordKey1 = (Integer)dynaForm.get("secretWordKey1");
      	Integer secretWordKey2 = (Integer)dynaForm.get("secretWordKey2");
      	Integer secretWordKey3 = (Integer)dynaForm.get("secretWordKey3");

      	String secretWordValue1 = (String)dynaForm.get("secretWordValue1");
      	String secretWordValue2 = (String)dynaForm.get("secretWordValue2");
      	String secretWordValue3 = (String)dynaForm.get("secretWordValue3");

      	String secretWord = getConsultantLoggedIn().getUser().getSecretWord();
      	
        String value1 = secretWord.substring(secretWordKey1 - 1, secretWordKey1);	
        String value2 = secretWord.substring(secretWordKey2 - 1, secretWordKey2);	
        String value3 = secretWord.substring(secretWordKey3 - 1, secretWordKey3);	
      	
      	if (!secretWordValue1.equalsIgnoreCase(value1) || !secretWordValue2.equalsIgnoreCase(value2) || !secretWordValue3.equalsIgnoreCase(value3)) {
    		logger.debug("Incorrect secret word attempt - " + getConsultantLoggedIn().getUser().getLogin() + " " + 
				       secretWordKey1 + "=" + secretWordValue1 + " " +
				       secretWordKey2 + "=" + secretWordValue2 + " " +
				       secretWordKey3 + "=" + secretWordValue3);
      		ActionMessages errors = new ActionMessages();
			MessageResources messageResources = getResources(request);
            errors.add("login", new ActionMessage("errors.invalidDetails"));
            saveErrors(request, errors);
  	     	return mapping.getInputForward();
      	}

      	AgyUtilities.setLevel2Login(request);

     	// re-initialize the form
    	dynaForm.initialize(mapping);

      	return null;

    }

}
