package com.helmet.application.mgr.abztract;

import javax.servlet.http.HttpServletRequest;

public abstract class MgrLoginFilter extends com.helmet.application.filter.LoginFilter {

	protected String addLoginParameters(String loginPage, HttpServletRequest hreq) {

		if (loginPage.indexOf("clientCode=") == -1) {
            // not already added
			String clientCode = hreq.getParameter("clientCode");
			if (clientCode != null && !"".equals(clientCode)) {
				loginPage += "?clientCode=" + clientCode;
			}
		}

		return loginPage;
	}
	
}
