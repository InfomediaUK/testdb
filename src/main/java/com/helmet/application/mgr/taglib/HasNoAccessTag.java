package com.helmet.application.mgr.taglib;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.taglib.logic.ConditionalTagBase;

import com.helmet.application.mgr.MgrUtilities;

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
		return !MgrUtilities.hasAccess(request, forward);
    }

}
