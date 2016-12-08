package com.helmet.application.zap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
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
import com.helmet.application.app.AppUtilities;
import com.helmet.application.zap.abztract.ZapAction;
import com.helmet.bean.Applicant;


public class ChangeSecretWordProcess extends ZapAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    String oldSecretWord = (String) dynaForm.get("oldSecretWord");
    String newSecretWord = (String) dynaForm.get("newSecretWord");
    String confirmSecretWord = (String) dynaForm.get("confirmSecretWord");

    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    AppService appService = ServiceFactory.getInstance().getAppService();

    // check old password is correct
    Applicant tempApplicant = new Applicant();

    tempApplicant.setApplicantId(getApplicantLoggedIn().getApplicantId());
    tempApplicant.getUser().setSecretWord(oldSecretWord);

    try
    {
      tempApplicant = appService.validateSecretWord(tempApplicant);
    }
    catch (DataNotFoundException e)
    {
      errors.add("login", new ActionMessage("errors.invalidCaseSensitive", messageResources.getMessage("label.login")));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    catch (InvalidDetailException e)
    {
      errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.oldSecretWord")));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    if (getApplicantLoggedIn().getUser().getLogin().equals(newSecretWord))
    {
      // secrwtWord cannot be the same as the login - this forces it to be changed on login
      errors.add("login", new ActionMessage("errors.mustNotEqual", messageResources.getMessage("label.newSecretWord"), messageResources.getMessage("label.login")));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    if (getApplicantLoggedIn().getUser().getLogin().equals(oldSecretWord))
    {
      // first time thru - the success page needs to be informed
      request.getSession().setAttribute("popupHelp", "true");
    }

    int rc = doAfterValidation(request, getApplicantLoggedIn().getApplicantId(), newSecretWord, getApplicantLoggedIn().getNoOfChanges());

    return mapping.findForward("success");
  }

  protected int doAfterValidation(HttpServletRequest request, Integer applicantId, String newSecretWord, Integer noOfChanges)
  {

    AppService appService = ServiceFactory.getInstance().getAppService();

    // update the applicant record with the new secret Word
    int rc = appService.updateApplicantSecretWord(applicantId, newSecretWord, noOfChanges, getApplicantLoggedIn().getApplicantId());

    // set the session applicant.secretWord
    Applicant currentApplicant = appService.getApplicant(applicantId);
    AppUtilities.setCurrentApplicant(request, currentApplicant);
    AppUtilities.setLevel2Login(request);

    return rc;
  }

}
