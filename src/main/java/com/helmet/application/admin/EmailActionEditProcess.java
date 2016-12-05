package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.FileHandler;
import com.helmet.bean.EmailAction;


public class EmailActionEditProcess extends EmailActionCommon
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    EmailAction emailAction = (EmailAction)dynaForm.get("emailAction");
    String textTemplateFileName = FileHandler.getInstance().getEmailTemplateRealPath(emailAction.getTextTemplate());
    String htmlTemplateFileName = FileHandler.getInstance().getEmailTemplateRealPath(emailAction.getHtmlTemplate());
    String text = StringUtils.trim((String)dynaForm.get("text"));
    String html = StringUtils.trim((String)dynaForm.get("html"));

    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    if (StringUtils.isNotEmpty(emailAction.getTextTemplate()))
    {
      validateTemplate(errors, emailAction.getTextTemplate(), "text", ".txt");
    }
    validateTemplateFolder(errors, textTemplateFileName);
    if (StringUtils.isNotEmpty(emailAction.getHtmlTemplate()))
    {
      validateTemplate(errors, emailAction.getHtmlTemplate(), "html", ".html");
    }
    validateTemplateFolder(errors, htmlTemplateFileName);
    if (!errors.isEmpty()) 
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    if (writeTemplate(textTemplateFileName, text) && writeTemplate(htmlTemplateFileName, html))
    {
      AdminService adminService = ServiceFactory.getInstance().getAdminService();

      try
      {
        int rowCount = adminService.updateEmailAction(emailAction, getAdministratorLoggedIn().getAdministratorId());
      }
      catch (DuplicateDataException e)
      {
        errors.add("emailAction", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
        saveErrors(request, errors);
        return mapping.getInputForward();
      }
    }
    
    logger.exit("Out going !!!");

    return mapping.findForward("success");
  }

}
