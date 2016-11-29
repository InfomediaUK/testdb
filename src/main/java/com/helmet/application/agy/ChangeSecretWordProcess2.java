package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Consultant;


public class ChangeSecretWordProcess2 extends ChangeSecretWordProcess {

    protected int doAfterValidation(HttpServletRequest request, Integer consultantId, String newSecretWord, Integer noOfChanges) {

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
    	// update the consultant record with the new secret Word
      	int rc = agyService.updateConsultantSecretWord(consultantId, newSecretWord, noOfChanges, getConsultantLoggedIn().getConsultantId());
    	
      	// set the session consultant.secretWord
    	Consultant currentConsultant = AgyUtilities.getCurrentConsultant(request);
    	currentConsultant.getUser().setSecretWord(newSecretWord);
    	AgyUtilities.setCurrentConsultant(request, currentConsultant);
    	
    	return rc;
    }


}
