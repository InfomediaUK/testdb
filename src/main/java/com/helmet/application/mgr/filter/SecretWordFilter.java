package com.helmet.application.mgr.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.mgr.MgrUtilities;
import com.helmet.application.mgr.abztract.MgrLoginFilter;

public class SecretWordFilter extends MgrLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {
		if (MgrUtilities.needToEnterSecretWord(request)) {
			Boolean level2Login = MgrUtilities.getLevel2Login(request);
			return level2Login != null;
		}
		else {
			return true;
		}
	}

}
