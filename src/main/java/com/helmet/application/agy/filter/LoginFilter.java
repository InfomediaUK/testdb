package com.helmet.application.agy.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.agy.AgyUtilities;
import com.helmet.application.agy.abztract.AgyLoginFilter;
import com.helmet.bean.Consultant;

public class LoginFilter extends AgyLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {
		Consultant consultant = AgyUtilities.getCurrentConsultant(request);
		return consultant != null;
	}

}
