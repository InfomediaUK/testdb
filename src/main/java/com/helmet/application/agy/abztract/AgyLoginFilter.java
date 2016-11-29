package com.helmet.application.agy.abztract;

import javax.servlet.http.HttpServletRequest;

public abstract class AgyLoginFilter extends com.helmet.application.filter.LoginFilter {

	protected String addLoginParameters(String loginPage, HttpServletRequest hreq) {

		if (loginPage.indexOf("agencyCode=") == -1) {
            // not already added
			String agencyCode = hreq.getParameter("agencyCode");
			if (agencyCode != null && !"".equals(agencyCode)) {
				loginPage += "?agencyCode=" + agencyCode;
			}
		}

		return loginPage;
	}
	
}
