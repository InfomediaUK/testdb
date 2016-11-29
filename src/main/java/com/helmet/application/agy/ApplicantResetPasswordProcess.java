package com.helmet.application.agy;

import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
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

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.MailHandler;
import com.helmet.application.Utilities;
import com.helmet.bean.Applicant;
import com.helmet.bean.EmailAction;

import static com.helmet.application.agy.AgyConstants.RESETPWDHINT;


public class ApplicantResetPasswordProcess extends SendApplicantEmailProcess 
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    EmailAction emailAction     = null;
    StringBuffer textTemplate   = new StringBuffer();
    StringBuffer htmlTemplate   = new StringBuffer();
    String subject              = null;
    ActionMessages errors = new ActionMessages();
    MessageResources messageResources = getResources(request);
    Applicant applicant = (Applicant) dynaForm.get("applicant");
    String emailActionIdSTR = (String)dynaForm.get("emailActionId");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    applicant = agyService.getApplicant(applicant.getApplicantId());
    Integer emailActionId = Integer.parseInt(emailActionIdSTR);
    if (emailActionId.intValue() == 0)
    {
      errors.add("sendApplicantEmail", new ActionMessage("errors.required", messageResources.getMessage("label.emailAction")));
    }
    if (errors.isEmpty())
    {
      // Email Action specified, continue with setup...
      emailAction = agyService.getEmailAction(emailActionId);
      subject = emailAction.getSubject();
      validateEmailAction(request, emailAction, textTemplate, htmlTemplate, errors);
    }
    if (!errors.isEmpty())
    {
      saveErrors(request, errors);
      ActionForward actionForward = mapping.getInputForward();
      return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicant.getApplicantId(), actionForward.getRedirect());
    }
    // Update the Applicant with their new password.
    String newPassword = createNewPassword(applicant);
    int rowCount = agyService.updateApplicantPassword(applicant.getApplicantId(), newPassword, RESETPWDHINT, applicant.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());
    // Put new password into the templates.
    String passwordPlaceholder = "%password%";
    int index = textTemplate.indexOf(passwordPlaceholder);
    if (index > -1)
    {
      // Password Placeholder found, replace it with new password.
      textTemplate.replace(index, index + passwordPlaceholder.length(), newPassword);
      index = htmlTemplate.indexOf(passwordPlaceholder);
      htmlTemplate.replace(index, index + passwordPlaceholder.length(), newPassword);
    }    
    // Now send the email notification...
    String fromEmailAddress = getConsultantLoggedIn().getUser().getEmailAddress();
    String fromFullName = getConsultantLoggedIn().getUser().getFullName();
    String fromNiceEmailAddress = Utilities.makeNiceEmailAddress(fromFullName, fromEmailAddress);
    String toEmailAddress = null;
    String toNiceEmailAddress = null;
    if (StringUtils.isEmpty(applicant.getEmailAddress()))
    {
      // Applicant does NOT have an email address. Send email to consultant.
      toNiceEmailAddress = fromNiceEmailAddress;
      String consultantNamePlaceholder = "%consultant%";
      index = textTemplate.indexOf(consultantNamePlaceholder);
      if (index > -1)
      {
        // Consultant Placeholder found, replace it with consultant's name.
        textTemplate.replace(index, index + consultantNamePlaceholder.length(), fromFullName);
        index = htmlTemplate.indexOf(consultantNamePlaceholder);
        htmlTemplate.replace(index, index + consultantNamePlaceholder.length(), fromFullName);
      }      
      subject += " - " + applicant.getUser().getFullName();
    }
    else
    {
      // Applicant does have an email address. Send email to applicant.
      toEmailAddress = applicant.getEmailAddress();
      toNiceEmailAddress = Utilities.makeNiceEmailAddress(applicant.getUser().getFullName(), toEmailAddress);
    }
    // Now do actual sending of email.
    MimeMultipart multipartRoot = buildMimeMultipart(request, applicant, textTemplate, htmlTemplate, "", messageResources);
    String contentType = "text/plain";
    int status = MailHandler.getInstance().sendSingleMail(fromNiceEmailAddress, toNiceEmailAddress, "", "", subject, multipartRoot, contentType);
    if (status != 0)
    {
      // an error has occurred ... inform the user ...
      String errorKey = "error.email." + status;
      errors.add("sendApplicantEmail", new ActionMessage(errorKey));
      saveErrors(request, errors);
      logger.debug("Out going with email error: " + errorKey);
      return mapping.getInputForward();
    }
    logger.exit("Out going !!!");
    ActionForward actionForward = mapping.findForward("success");
    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?applicant.applicantId=" + applicant.getApplicantId(), actionForward.getRedirect());
  }
  
  private String createNewPassword(Applicant applicant)
  {
    StringBuffer sb = new StringBuffer();
    StringBuffer randomString = new StringBuffer(RandomStringUtils.randomAlphabetic(8));
    StringBuffer applicantNameReversed = new StringBuffer(applicant.getFullName()).reverse();
    if (applicantNameReversed.length() < 8)
    {
      applicantNameReversed.append(randomString);
    }
    for (int i = 0; i < 8; i++)
    {
      if (new java.util.Date().getTime() % 2 == 1)
      {
        sb.append(randomString.charAt(i));
      }
      else
      {
        if (applicantNameReversed.charAt(i) == ' ')
        {
          sb.append(randomString.charAt(i));
        }
        else
        {
          sb.append(applicantNameReversed.charAt(i));
        }
      }
    }
    return sb.toString();
  }
}
