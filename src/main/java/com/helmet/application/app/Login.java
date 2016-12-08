package com.helmet.application.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;


public class Login extends Action {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) 
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    String login = (String) dynaForm.get("login");
    return mapping.findForward("success");
  }

}
