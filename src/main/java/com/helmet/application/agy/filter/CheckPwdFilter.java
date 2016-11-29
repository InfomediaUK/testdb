package com.helmet.application.agy.filter;

import static com.helmet.application.agy.AgyConstants.RESETPWDHINT;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.agy.AgyUtilities;
import com.helmet.application.agy.abztract.AgyLoginFilter;

public class CheckPwdFilter extends AgyLoginFilter {

	protected boolean isLoggedIn(HttpServletRequest request) {

		String pwdHint = AgyUtilities.getCurrentConsultant(request).getUser().getPwdHint();		
        // return true of NOT equal
		return !RESETPWDHINT.equals(pwdHint);
		
	}

}
