package com.helmet.application.app.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.app.AppUtilities;

public class SecretWordFilter extends com.helmet.application.filter.LoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {
		if (AppUtilities.needToEnterSecretWord(request)) {
			Boolean level2Login = AppUtilities.getLevel2Login(request);
			return level2Login != null;
		}
		else {
			return true;
		}
	}

}
