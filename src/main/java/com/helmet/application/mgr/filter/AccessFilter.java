package com.helmet.application.mgr.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.helmet.application.Utilities;
import com.helmet.application.mgr.MgrUtilities;

public class AccessFilter extends com.helmet.application.filter.AccessFilter {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	protected boolean hasAccess(HttpServletRequest hreq, String path) {
		
		path = Utilities.cleanPath(path);
        
		logger.debug("******************************** " + path);
        
		return MgrUtilities.hasAccess(hreq, path);
	}

}
