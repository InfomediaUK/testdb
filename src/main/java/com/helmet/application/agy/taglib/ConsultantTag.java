package com.helmet.application.agy.taglib;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.agy.AgyUtilities;
import com.helmet.taglib.abztract.AbstractTagSupport;

public class ConsultantTag extends AbstractTagSupport {

	protected Object getObject() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return AgyUtilities.getCurrentConsultant(request);
	}

}
