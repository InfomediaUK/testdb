package com.helmet.application.admin.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.admin.AdminUtilities;
import com.helmet.application.admin.abztract.AdminLoginFilter;

public class CheckSecretWordFilter extends AdminLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {

		String login = AdminUtilities.getCurrentAdministrator(request).getUser().getLogin();		
		String secretWord = AdminUtilities.getCurrentAdministrator(request).getUser().getSecretWord();		
        // return true of NOT equal
		return !secretWord.equals(login);
		
	}

}
