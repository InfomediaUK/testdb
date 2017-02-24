package com.helmet.application.admin;

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
import com.helmet.reporting.XMLGenerator;


public class QueriesEditProcess extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    String filePath = FileHandler.getInstance().getXmlRealPath("/queries.xml");
    String queriesXml = (String) dynaForm.get("queriesXml");
    try
    {
      Utilities.saveFile(filePath, queriesXml);
      XMLGenerator xmlGenerator = XMLGenerator.getInstance();
      xmlGenerator.reloadXml();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
