package com.helmet.application.agy.taglib;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.agy.AgyUtilities;
import com.helmet.taglib.abztract.AbstractTagSupport;

public class AgencyTag extends AbstractTagSupport {

	protected Object getObject() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return AgyUtilities.getCurrentAgency(request);
	}

}
