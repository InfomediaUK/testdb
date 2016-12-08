package com.helmet.application.zap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.ServiceFactory;
import com.helmet.api.ZapService;
import com.helmet.application.zap.abztract.ZapAction;


public class SendEmail extends ZapAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    String referer = request.getHeader("Referer");
    ZapService zapService = ServiceFactory.getInstance().getZapService();
    dynaForm.set("referer", referer);
    ActionForward af = setFormProperties(mapping, dynaForm, request);

    if (af != null) { return af; }

    return mapping.findForward("success");
  }

  protected ActionForward setFormProperties(ActionMapping mapping, DynaValidatorForm dynaForm, HttpServletRequest request)
  {
    // override in descendant classes
    return null;
  }

}
