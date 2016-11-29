package com.helmet.application.agy.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.agy.AgyUtilities;
import com.helmet.application.agy.abztract.AgyLoginFilter;

public class SecretWordFilter extends AgyLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {
		if (AgyUtilities.needToEnterSecretWord(request)) {
			Boolean level2Login = AgyUtilities.getLevel2Login(request);
			return level2Login != null;
		}
		else {
			return true;
		}
	}

}
