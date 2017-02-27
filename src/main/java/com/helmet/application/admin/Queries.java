package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.reporting.XMLGenerator;


public class Queries extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    XMLGenerator xmlGenerator = XMLGenerator.getInstance();
    String xmlFileName = xmlGenerator.getXmlFileName();
    String xsltFileName = xmlGenerator.getXsltFileName();
    dynaForm.set("xmlFileName", xmlFileName);
    dynaForm.set("xsltFileName", xsltFileName);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

}
