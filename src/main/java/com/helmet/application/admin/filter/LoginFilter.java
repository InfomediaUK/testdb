package com.helmet.application.admin.filter;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.admin.AdminUtilities;
import com.helmet.application.admin.abztract.AdminLoginFilter;
import com.helmet.bean.Administrator;

public class LoginFilter extends AdminLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {
		Administrator administrator = AdminUtilities.getCurrentAdministrator(request);
		return administrator != null;
	}

}
