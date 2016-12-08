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

import com.helmet.api.ServiceFactory;
import com.helmet.api.ZapService;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.application.app.AppUtilities;
import com.helmet.application.zap.abztract.ZapAction;
import com.helmet.bean.Applicant;
import static com.helmet.application.zap.ZapConstants.RESETPWDHINT;


public class ChangePasswordProcess extends ZapAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    String oldPwd     = (String) dynaForm.get("oldPwd");
    String newPwd     = (String) dynaForm.get("newPwd");
    String confirmPwd = (String) dynaForm.get("confirmPwd");
    String pwdHint    = (String) dynaForm.get("pwdHint");

    ActionMessages errors = new ActionMessages();

    MessageResources messageResources = getResources(request);

    ZapService zapService = ServiceFactory.getInstance().getZapService();

    // check old password is correct
    Applicant applicant = new Applicant();

    applicant.setApplicantId(getApplicantLoggedIn().getApplicantId());
    applicant.getUser().setPwd(oldPwd);

    try
    {
      applicant = zapService.validateLogin(applicant);
    }
    catch (DataNotFoundException e)
    {
      errors.add("login", new ActionMessage("errors.invalidCaseSensitive", messageResources.getMessage("label.login")));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    catch (InvalidDetailException e)
    {
      errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.oldPwd")));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    if (RESETPWDHINT.equals(pwdHint))
    {
      // pwdHint cannot be the same as the resetPwdHintValue (currently 'password') - this forces a password change
      errors.add("login", new ActionMessage("errors.mustNotEqual", messageResources.getMessage("label.pwdHint"), "'" + RESETPWDHINT + "'"));
      saveErrors(request, errors);
      return mapping.getInputForward();
    }

    // set new password and hint
    // update the Applicant record
    int rc = zapService.updateApplicantPwd(getApplicantLoggedIn().getApplicantId(), newPwd, pwdHint, applicant.getNoOfChanges(), getApplicantLoggedIn().getApplicantId());

    Applicant currentApplicant = zapService.getApplicant(getApplicantLoggedIn().getApplicantId());
    AppUtilities.setCurrentApplicant(request, currentApplicant);

    return mapping.findForward("success");
  }

}
