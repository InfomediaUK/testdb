package com.helmet.api.exceptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public class MMJExceptionHandler extends ExceptionHandler {
	public ActionForward execute(Exception exception, 
			                     ExceptionConfig exceptionConfig,
			                     ActionMapping mapping, 
			                     ActionForm formInstance,
			                     HttpServletRequest request, 
			                     HttpServletResponse response)
			throws ServletException {
		System.out.println(">>>>> " + getClass().getName() + " <<<<<");
		System.out.println(exception.getClass().getName());
		System.out.println(mapping.getPath());
		System.out.println(formInstance);
		System.out.println(">>>>> " + getClass().getName() + " <<<<<");
		return new ActionForward(exceptionConfig.getPath());
	}
}
