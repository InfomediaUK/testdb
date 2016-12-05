package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.EmailAction;


public class EmailActionDelete extends AdminAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    EmailAction emailAction = (EmailAction) dynaForm.get("emailAction");
    AdminService adminService = ServiceFactory.getInstance().getAdminService();
    emailAction = adminService.getEmailAction(emailAction.getEmailActionId());
    String textTemplateFileName = null;
    String htmlTemplateFileName = null;
    StringBuffer text = new StringBuffer();
    StringBuffer html = new StringBuffer();
    // Text Content Template.
    if (StringUtils.isNotEmpty(emailAction.getTextTemplate()))
    {
      textTemplateFileName = FileHandler.getInstance().getEmailTemplateRealPath(emailAction.getTextTemplate());
      Utilities.suckInFile(textTemplateFileName, text);
    }
    // HTML Content Template.
    if (StringUtils.isNotEmpty(emailAction.getHtmlTemplate()))
    {
      htmlTemplateFileName = FileHandler.getInstance().getEmailTemplateRealPath(emailAction.getHtmlTemplate());
      Utilities.suckInFile(htmlTemplateFileName, html);
    }

    // TODO check not null, maybe service should throw a known exception

    dynaForm.set("emailAction", emailAction);
    dynaForm.set("html", html.toString());
    dynaForm.set("text", text.toString());

    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
