package com.helmet.application.admin.taglib;

import javax.servlet.http.HttpServletRequest;

import com.helmet.application.admin.AdminUtilities;
import com.helmet.taglib.abztract.AbstractTagSupport;

public class AdministratorTag extends AbstractTagSupport {

	protected Object getObject() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return AdminUtilities.getCurrentAdministrator(request);
	}

}
