package com.helmet.application.zap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

// Deliberately NOT extending ZapAction. Does NOT rely on anthing in the Session.
public class ZapFatalErrorAction extends Action
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.debug("GOING DOWN !!!");
    return mapping.findForward("success");
  }
}
