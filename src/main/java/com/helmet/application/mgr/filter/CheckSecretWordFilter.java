package com.helmet.application.mgr.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.mgr.MgrUtilities;
import com.helmet.application.mgr.abztract.MgrLoginFilter;

public class CheckSecretWordFilter extends MgrLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {

		String login = MgrUtilities.getCurrentManager(request).getUser().getLogin();		
		String secretWord = MgrUtilities.getCurrentManager(request).getUser().getSecretWord();		
        // return true of NOT equal
		return !secretWord.equals(login);
		
	}

}
