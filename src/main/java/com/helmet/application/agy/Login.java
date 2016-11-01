package com.helmet.application.agy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class Login extends Action
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) 
  {
    logger.entry("In coming !!!");
    String pattern = "dd/MM/yyyy";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    Date date = new Date();
    for (int i = 0; i < 100; i++)
    {
      logger.debug("Date: {} {}", format.format(date), i);
    }
    try
    {
      throw new IOException("Bloody thing's gone pants...");
    }
    catch (Exception e)
    {
      logger.catching(e);
    }
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }
}
