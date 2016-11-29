package com.helmet.application.agy.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.agy.AgyUtilities;
import com.helmet.application.agy.abztract.AgyLoginFilter;

public class CheckSecretWordFilter extends AgyLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {

		String login = AgyUtilities.getCurrentConsultant(request).getUser().getLogin();		
		String secretWord = AgyUtilities.getCurrentConsultant(request).getUser().getSecretWord();		
        // return true of NOT equal
		return !secretWord.equals(login);
		
	}

}
