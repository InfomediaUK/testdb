package com.helmet.application.agy;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login extends Action
{
  private static final Logger logger = LoggerFactory.getLogger(Login.class);

  public ActionForward execute(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) 
  {
    logger.debug("Entering");
    String pattern = "dd/MM/yyyy";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    Date date = new Date();
    for (int i = 0; i < 100; i++)
    {
      logger.debug("Date: {} {}", format.format(date), i);
    }
    logger.debug("Leaving");
    return mapping.findForward("success");
  }
}
