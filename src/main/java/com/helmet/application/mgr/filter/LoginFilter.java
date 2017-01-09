package com.helmet.application.mgr.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.mgr.MgrUtilities;
import com.helmet.application.mgr.abztract.MgrLoginFilter;
import com.helmet.bean.Manager;

public class LoginFilter extends MgrLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {
		Manager manager = MgrUtilities.getCurrentManager(request);
		return manager != null;
	}

}
