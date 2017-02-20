package com.helmet.application.admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.admin.abztract.AdminAction;

public class SiteUnavailableView extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    String filePath = FileHandler.getInstance().getXmlRealPath("/siteUnavailable.xml");
    File file = new File(filePath);
    String text = null;
    if (file.exists())
    {
      try
      {
        text = Utilities.openFile(filePath);
      }
      catch (IOException e)
      {
        // TODO Auto-generated catch block
        text = e.getMessage();
      }
    }
    else
    {
      StringBuffer sb = new StringBuffer();
      sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      sb.append("<siteUnavailable>\n");
      sb.append("  <msg active=\"false\">\n");
      sb.append("    <title>- - - SITE UNAVAILABLE NOTICE - - -</title>\n");
      sb.append("    <text>This site will be unavailable from HH:MM until HH:MM on MONTH DATE.</text>\n");
      sb.append("    <text>Sorry for the inconvenience.</text>\n");
      sb.append("  </msg>\n");
      sb.append("</siteUnavailable>\n");
      text = sb.toString();
    }
    dynaForm.set("text", text);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
