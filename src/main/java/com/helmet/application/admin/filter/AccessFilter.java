package com.helmet.application.admin.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.application.Utilities;
import com.helmet.application.admin.AdminUtilities;

public class AccessFilter extends com.helmet.application.filter.AccessFilter {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	protected boolean hasAccess(HttpServletRequest hreq, String path) {
		
		path = Utilities.cleanPath(path);
        
		logger.debug("******************************** " + path);
        
		return AdminUtilities.hasAccess(hreq, path);
	}

}
