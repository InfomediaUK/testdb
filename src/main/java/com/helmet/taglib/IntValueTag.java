package com.helmet.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.helmet.bean.IntValue;
import com.helmet.bean.StatusCount;

abstract public class IntValueTag extends TagSupport {

	abstract protected String getAttributeName();

	public int doStartTag() throws JspException {

		int count = 0;
		IntValue intValue = (IntValue)pageContext.getRequest().getAttribute(getAttributeName());
        if (intValue != null) {
        	count = intValue.getValue();
        }

		JspWriter out = pageContext.getOut();
		try {
			out.print(count);
		} catch (IOException ex) {
		}
		return SKIP_BODY;

	}

	public int doEndTag() throws JspException {
		return (EVAL_PAGE);
	}

}
