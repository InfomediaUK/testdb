package com.helmet.application.admin.taglib;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.taglib.logic.ConditionalTagBase;

import com.helmet.application.admin.AdminUtilities;

public class HasAccessTag extends ConditionalTagBase {

    protected String forward;

    public String getForward() {
        return forward;
    }
    public void setForward(String forward) {
        this.forward = forward;
    }
	protected boolean condition() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return AdminUtilities.hasAccess(request, forward);
    }

}
