package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

//import com.helmet.api.AgyService;
//import com.helmet.api.ServiceFactory;
//import com.helmet.api.exceptions.DataNotFoundException;
//import com.helmet.api.exceptions.InvalidDetailException;
//import com.helmet.bean.Agency;
import com.helmet.bean.Consultant;

public class ForgottenPwdProcess extends Action
{
//  private static final Logger logger = LoggerFactory.getLogger(Login.class);
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    if (isCancelled(request))
    {
      return mapping.findForward("cancel");
    }

    DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    Consultant consultant = (Consultant)dynaForm.get("consultant");
    String agencyCode = (String)dynaForm.get("agencyCode");

    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
