package com.helmet.application.mgr.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.application.mgr.MgrUtilities;
import com.helmet.application.mgr.abztract.MgrLoginFilter;

public class PwdFilter extends MgrLoginFilter {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	protected boolean isLoggedIn(HttpServletRequest request) {
		String path = request.getServletPath();
		path = path.replaceAll(".do", "");
		path = path.substring(path.lastIndexOf("/") + 1);
		logger.debug(getClass().getName() + " - checking - " + path);

		if (MgrUtilities.needToReEnterPwd(request, path)) {
			Boolean pwdEntered = MgrUtilities.getPwdEntered(request);
			return pwdEntered != null;
		}
		else {
			return true;
		}
	}
	
	protected void postProcess(HttpServletRequest request) {
		MgrUtilities.removePwdEntered(request);
	}

}