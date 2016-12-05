package com.helmet.application.admin.filter;

import static com.helmet.application.admin.AdminConstants.RESETPWDHINT;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.admin.AdminUtilities;
import com.helmet.application.admin.abztract.AdminLoginFilter;

public class CheckPwdFilter extends AdminLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {

		String pwdHint = AdminUtilities.getCurrentAdministrator(request).getUser().getPwdHint();		
        // return true of NOT equal
		return !RESETPWDHINT.equals(pwdHint);
		
	}

}
