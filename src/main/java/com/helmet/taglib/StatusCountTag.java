package com.helmet.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.helmet.bean.StatusCount;

abstract public class StatusCountTag extends TagSupport {

	private int status = 0;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	abstract protected String getAttributeName();

	public int doStartTag() throws JspException {

		List<StatusCount> statusCounts = (List<StatusCount>) pageContext
				.getRequest().getAttribute(getAttributeName());
		int count = 0;
		if (statusCounts != null && statusCounts.size() > 0) {
			for (StatusCount statusCount : statusCounts) {
				if (statusCount.getStatus() == status) {
					count = statusCount.getCount();
					break;
				}
			}
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
