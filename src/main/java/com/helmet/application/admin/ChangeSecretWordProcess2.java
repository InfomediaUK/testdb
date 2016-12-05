package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Administrator;


public class ChangeSecretWordProcess2 extends ChangeSecretWordProcess {

    protected int doAfterValidation(HttpServletRequest request, Integer administratorId, String newSecretWord, Integer noOfChanges) {

		AdminService adminService = ServiceFactory.getInstance().getAdminService();
    	// update the administrator record with the new secret Word
      	int rc = adminService.updateAdministratorSecretWord(administratorId, newSecretWord, noOfChanges, getAdministratorLoggedIn().getAdministratorId());
    	
      	// set the session administrator.secretWord
      	Administrator currentAdministrator = AdminUtilities.getCurrentAdministrator(request);
    	currentAdministrator.getUser().setSecretWord(newSecretWord);
    	AdminUtilities.setCurrentAdministrator(request, currentAdministrator);
    	
    	return rc;
    }


}
