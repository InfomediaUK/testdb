package com.helmet.application.mgr;

import javax.servlet.http.HttpServletRequest;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Manager;


public class ChangeSecretWordProcess2 extends ChangeSecretWordProcess {

    protected int doAfterValidation(HttpServletRequest request, Integer managerId, String newSecretWord, Integer noOfChanges) {

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
    	// update the manager record with the new secret Word
      	int rc = mgrService.updateManagerSecretWord(managerId, newSecretWord, noOfChanges, getManagerLoggedIn().getManagerId());
    	
      	// set the session manager.secretWord
    	Manager currentManager = MgrUtilities.getCurrentManager(request);
    	currentManager.getUser().setSecretWord(newSecretWord);
    	MgrUtilities.setCurrentManager(request, currentManager);
    	
    	return rc;
    }


}
