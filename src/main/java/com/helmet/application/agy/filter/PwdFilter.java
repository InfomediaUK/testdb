package com.helmet.application.agy.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.application.agy.AgyUtilities;
import com.helmet.application.agy.abztract.AgyLoginFilter;

public class PwdFilter extends AgyLoginFilter {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	protected boolean isLoggedIn(HttpServletRequest request) {
		String path = request.getServletPath();
		path = path.replaceAll(".do", "");
		path = path.substring(path.lastIndexOf("/") + 1);
		logger.debug(getClass().getName() + " - checking - " + path);

		if (AgyUtilities.needToReEnterPwd(request, path)) {
			Boolean pwdEntered = AgyUtilities.getPwdEntered(request);
			return pwdEntered != null;
		}
		else {
			return true;
		}
	}
	
	protected void postProcess(HttpServletRequest request) {
		AgyUtilities.removePwdEntered(request);
	}

}