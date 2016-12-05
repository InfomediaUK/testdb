package com.helmet.application.admin.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.admin.AdminUtilities;
import com.helmet.application.admin.abztract.AdminLoginFilter;

public class SecretWordFilter extends AdminLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {
		if (AdminUtilities.needToEnterSecretWord(request)) {
			Boolean level2Login = AdminUtilities.getLevel2Login(request);
			return level2Login != null;
		}
		else {
			return true;
		}
	}

}
