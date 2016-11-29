package com.helmet.application.agy.taglib;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.taglib.logic.ConditionalTagBase;

import com.helmet.application.agy.AgyUtilities;

public class HasNoAccessTag extends ConditionalTagBase {

    protected String forward;

    public String getForward() {
        return forward;
    }
    public void setForward(String forward) {
        this.forward = forward;
    }
	protected boolean condition() {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		return !AgyUtilities.hasAccess(request, forward);
    }

}
