package com.helmet.application.mgr.filter;

import static com.helmet.application.mgr.MgrConstants.RESETPWDHINT;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.mgr.MgrUtilities;
import com.helmet.application.mgr.abztract.MgrLoginFilter;

public class CheckPwdFilter extends MgrLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {

		String pwdHint = MgrUtilities.getCurrentManager(request).getUser().getPwdHint();		
        // return true of NOT equal
		return !RESETPWDHINT.equals(pwdHint);
		
	}

}
