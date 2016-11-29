package com.helmet.taglib.abztract;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.helmet.taglib.TagUtil;

public abstract class AbstractTagSupport extends TagSupport {

    protected String var;
    protected String scope = "page";

    public String getVar() {
        return var;
    }
    public void setVar(String var) {
        this.var = var;
    }

    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }

    public void release() {
        this.scope = "page";
        this.var = null;
    }

    public int doStartTag() throws JspException {
        return (EVAL_BODY_INCLUDE);
    }

    public int doEndTag() throws JspException {

        pageContext.setAttribute(var, getObject(), TagUtil.getScope(getScope()));
        return (EVAL_PAGE);
    }

    protected abstract Object getObject();
    
}
