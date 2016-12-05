package com.helmet.application.admin.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.application.admin.AdminUtilities;
import com.helmet.application.admin.abztract.AdminLoginFilter;

public class PwdFilter extends AdminLoginFilter {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	protected boolean isLoggedIn(HttpServletRequest request) {
		String path = request.getServletPath();
		path = path.replaceAll(".do", "");
		path = path.substring(path.lastIndexOf("/") + 1);
		logger.debug(getClass().getName() + " - checking - " + path);

		if (AdminUtilities.needToReEnterPwd(request, path)) {
			Boolean pwdEntered = AdminUtilities.getPwdEntered(request);
			return pwdEntered != null;
		}
		else {
			return true;
		}
	}
	
	protected void postProcess(HttpServletRequest request) {
		AdminUtilities.removePwdEntered(request);
	}

}