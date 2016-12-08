package com.helmet.application.app.taglib;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.app.AppUtilities;
import com.helmet.taglib.abztract.AbstractTagSupport;

public class AgencyTag extends AbstractTagSupport {

	protected Object getObject() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return AppUtilities.getCurrentAgency(request);
	}

}
