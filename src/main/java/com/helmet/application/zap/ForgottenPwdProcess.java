package com.helmet.application.zap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AppService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.bean.Applicant;


public class ForgottenPwdProcess extends Action
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    if (isCancelled(request)) { return mapping.findForward("cancel"); }
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    Applicant applicant = (Applicant)dynaForm.get("applicant");
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    AppService appService = ServiceFactory.getInstance().getAppService();
    try
    {
      // need to validate secret word ...
      applicant = appService.validateSecretWord(applicant);
    }
    catch (DataNotFoundException e)
    {
      errors.add("login", new ActionMessage("errors.invalidCaseSensitive", messageResources.getMessage("label.login")));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    catch (InvalidDetailException e)
    {
      errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.secretWord")));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    dynaForm.set("applicant", applicant);
    return mapping.findForward("success");
  }

}
