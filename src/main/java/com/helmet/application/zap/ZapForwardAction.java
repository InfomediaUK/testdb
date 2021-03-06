package com.helmet.application.zap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.helmet.application.zap.abztract.ZapAction;


public class ZapForwardAction extends ZapAction
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());
  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    return mapping.findForward("success");
  }
}
