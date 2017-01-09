package com.helmet.application.mgr.taglib;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.mgr.MgrUtilities;
import com.helmet.taglib.abztract.AbstractTagSupport;

public class ManagerTag extends AbstractTagSupport {

	protected Object getObject() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return MgrUtilities.getCurrentManager(request);
	}

}
