package com.helmet.application.agy;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.MailHandler;
import com.helmet.application.Utilities;
import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;
import com.helmet.bean.CompliancyTest;
import com.helmet.bean.EmailAction;
import com.helmet.bean.EmailActionResult;


public class SendApplicantEmailProcess extends SendEmailProcess 
{

  public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) 
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm  = (DynaValidatorForm) form;
    EmailAction emailAction     = null;
    StringBuffer textTemplate   = new StringBuffer();
    StringBuffer htmlTemplate   = new StringBuffer();
    String fromEmailAddress     = null;
    String ccEmailAddress       = null;
    String bccEmailAddress      = null;
    String subject              = null;
    String attachment           = null;
    ActionMessages errors         = new ActionMessages();
    MessageResources messageResources = getResources(request);
    String emailActionIdSTR    = (String)dynaForm.get("emailActionId");
    AgyService agyService      = ServiceFactory.getInstance().getAgyService();
    Integer emailActionId      = Integer.parseInt(emailActionIdSTR);
    if (emailActionId.intValue() == 0)
    {
      errors.add("sendApplicantEmail", new ActionMessage("errors.required", messageResources.getMessage("label.emailAction")));
    }
    if (errors.isEmpty())
    {
      // Email Action specified, continue with setup...
      emailAction             = agyService.getEmailAction(emailActionId);
      dynaForm.set("emailActionName", emailAction.getName());
      fromEmailAddress        = getConsultantLoggedIn().getUser().getEmailAddress(); // "kevin@matchmyjob.co.uk";
      ccEmailAddress          = null;
      subject                 = emailAction.getSubject();
      logger.debug("emailActionName {}", emailAction.getName());
      logger.debug("fromEmailAddress {}", fromEmailAddress);
      logger.debug("ccEmailAddress {}", ccEmailAddress);
      logger.debug("bccEmailAddress {}", bccEmailAddress);
      logger.debug("subject {}", subject);
      logger.debug("attachment {}", attachment);
      validateEmailAction(request, emailAction, textTemplate, htmlTemplate, errors);
    }
    if (!errors.isEmpty())
    {
      saveErrors(request, errors);
      return mapping.getInputForward();
    }
    EmailActionResult emailActionResult = null;
    List<EmailActionResult> emailActionResultList = new ArrayList<EmailActionResult>();
    Enumeration paramNames = request.getParameterNames();
    while(paramNames.hasMoreElements()) 
    {
      String paramName = (String)paramNames.nextElement();
      if (paramName.equals("applicantId"))
      {
        int applicantId = 0;
        String[] paramValues = request.getParameterValues(paramName);
        for(int i = 0; i < paramValues.length; i++) 
        {
          // For each Applicant to be emailed...
          applicantId = Integer.parseInt(paramValues[i]);
          Applicant applicant = agyService.getApplicant(applicantId);
          if (applicant == null)
          {
            // Applicant NOT found is a disaster...
            emailActionResult = new EmailActionResult();
            emailActionResult.setFullName("ERROR");
            emailActionResult.setMessage(messageResources.getMessage("errors.emailAction.applicantNotFound", applicantId));
          }
          else
          {
            // Applicant found, so now validate them.
            emailActionResult = new EmailActionResult(applicant.getApplicantId(), applicant.getUser().getFullName(), applicant.getUser().getEmailAddress());
            if (validateApplicant(applicant, emailAction, emailActionResult, messageResources))
            {
              MimeMultipart multipartRoot = buildMimeMultipart(request, applicant, textTemplate, htmlTemplate, attachment, messageResources);
              String contentType = "text/plain";
              int status = MailHandler.getInstance().sendSingleMail(fromEmailAddress, applicant.getUser().getEmailAddress(), ccEmailAddress, bccEmailAddress, subject, multipartRoot, contentType);
              System.out.println(status);
              // A non-zero status indicates an error has occurred...
              String messageKey = (status == 0) ? "label.emailOk" : "error.email." + status;
              emailActionResult.setEmailStatus(Integer.toString(status));
              emailActionResult.setMessage(messageResources.getMessage(messageKey));
            }
          }
          emailActionResultList.add(emailActionResult);
        }
      }
    }
    dynaForm.set("emailActionResultList", emailActionResultList);
    logger.exit("Out going !!!");
    return mapping.findForward("success");  
  }

  protected void validateEmailAction(HttpServletRequest request, EmailAction emailAction, StringBuffer textTemplate, StringBuffer htmlTemplate, ActionMessages errors)
  {
    String textTemplateFileName = null;
    String htmlTemplateFileName = null;
    // Text Content Template.
    if (StringUtils.isEmpty(emailAction.getTextTemplate()))
    {
      errors.add("sendApplicantEmail", new ActionMessage("errors.emailAction.templateNotSpecified", "Text"));
    }
    else
    {
      textTemplateFileName = FileHandler.getInstance().getEmailTemplateRealPath(emailAction.getTextTemplate());
      logger.debug("textTemplateFileName {}", textTemplateFileName);
      Utilities.suckInFile(textTemplateFileName, textTemplate);
    }
    // HTML Content Template.
    if (StringUtils.isEmpty(emailAction.getHtmlTemplate()))
    {
      errors.add("sendApplicantEmail", new ActionMessage("errors.emailAction.templateNotSpecified", "HTML"));
    }
    else
    {
      htmlTemplateFileName = FileHandler.getInstance().getEmailTemplateRealPath(emailAction.getHtmlTemplate());
      logger.debug("htmlTemplateFileName {}", htmlTemplateFileName);
      Utilities.suckInFile(htmlTemplateFileName, htmlTemplate);
    }
    if (StringUtils.isEmpty(textTemplate.toString()))
    {
      errors.add("sendApplicantEmail", new ActionMessage("errors.emailAction.templateNotFound", textTemplateFileName));
    }
    if (StringUtils.isEmpty(htmlTemplate.toString()))
    {
      errors.add("sendApplicantEmail", new ActionMessage("errors.emailAction.templateNotFound", htmlTemplateFileName));
    }
    if (StringUtils.isEmpty(emailAction.getSubject()))
    {
      errors.add("sendApplicantEmail", new ActionMessage("errors.emailAction.subjectMissing"));
    }
  }
  
  protected MimeMultipart buildMimeMultipart(HttpServletRequest request, Applicant applicant, StringBuffer textTemplate, StringBuffer htmlTemplate, String attachment, MessageResources messageResources)
  {
    logger.debug("buildMimeMultipart() {}", applicant.getFullName());
    StringBuffer content = new StringBuffer();
    StringBuffer htmlContent = new StringBuffer();
    emailTop(htmlContent, FileHandler.getInstance().getEmailTemplateRealPath("/agy/site.css"));
    Agency agency = AgyUtilities.getCurrentAgency(request);
    emailHeader(htmlContent, agency);
    emailTopDivider(htmlContent);
    // Process Text Content.
    StringBuffer message = processTagSubstitution(agency, applicant, textTemplate, false, messageResources);
    content.append(cleanTextLine(message.toString()) + "\n");
    System.out.println(">>>>> TEXT OUTPUT >>>>>");
    System.out.println(content.toString());
    System.out.println("<<<<< TEXT OUTPUT <<<<<");
    System.out.println("");
    // Process HTML Content.
    StringBuffer htmlMessage = processTagSubstitution(agency, applicant, htmlTemplate, true, messageResources);
    htmlContent.append(cleanHtmlLine(htmlMessage.toString()) + "<br/>\n");
    emailBottomDivider(htmlContent);
    emailFooter(htmlContent, messageResources);
    emailBottom(htmlContent);
    System.out.println("");
    System.out.println(">>>>> HTML OUTPUT >>>>>");
    System.out.println(htmlContent.toString());
    System.out.println("<<<<< HTML OUTPUT <<<<<");
    System.out.println("");
    String serverName = request.getServerName();
    MimeMultipart multipartRoot = emailMultipart(content, htmlContent, attachment, agency, serverName);
    return multipartRoot;
  }
  
  private boolean validateApplicant(Applicant applicant, EmailAction emailAction, EmailActionResult emailActionResult, MessageResources messageResources)
  {
    logger.debug("validateApplicant() {}", applicant.getFullName());
    boolean status = true;
    if (StringUtils.isEmpty(applicant.getUser().getEmailAddress()))
    {
      // Applicant has NO email address. Cannot notify them...
      emailActionResult.setMessage(messageResources.getMessage("errors.emailAction.noEmailAddress"));
      status = false;
    }
    else
    {
      // Applicant has email address, now check they have a value in the Depends On field.
      if (StringUtils.isNotEmpty(emailAction.getDependsOn()))
      {
        // Email Action depends on specific field being non-null on Applicant.
        Class applicantClass = applicant.getClass();
        Class userClass      = applicant.getUser().getClass();
        Class addressClass   = applicant.getAddress().getClass();
        Object sourceObject  = null;
        Method method        = null;
        String methodName    = null;
        Object value         = null;
        List<String> listDependsOn = Arrays.asList(emailAction.getDependsOn().split("\\s*,\\s*"));
        for (String dependsOn : listDependsOn)
        {
          try
          {
            // The matcher.group() will be something like %fullName% and the method will be: getFullName.
            methodName = "get" + StringUtils.capitalize(dependsOn.substring(dependsOn.lastIndexOf(".") + 1));
            logger.debug("methodName {}", methodName);
            int dotCount = StringUtils.countMatches(dependsOn, ".");
            if (dotCount == 1)
            {
              // Eg. applicant.crbExpiryDate
              method = applicantClass.getMethod(methodName, new Class[] {});
              sourceObject = applicant;
            }
            else
            {
              // Has 2 dots. Eg. applicant.user.fullName or applicant.address.postalCode 
              if (dependsOn.substring(dependsOn.indexOf(".") + 1, dependsOn.lastIndexOf(".")).equalsIgnoreCase("user"))
              {
                // User method.
                method = userClass.getMethod(methodName, new Class[] {});
                sourceObject = applicant.getUser();
              }
              else
              {
                // Address method.
                method = addressClass.getMethod(methodName, new Class[] {});
                sourceObject = applicant.getAddress();
              }
            }
            if (method.getReturnType() == String.class)
            {
              value = (String)method.invoke(sourceObject, new Object[0]);
            }
            else
              if (method.getReturnType() == Integer.class)
              {
                value = (Integer)method.invoke(sourceObject, new Object[0]);
              }
              else
                if (method.getReturnType() == Date.class)
                {
                  value = (Date)method.invoke(sourceObject, new Object[0]);
                }
            if (value == null)
            {
              String label = messageResources.getMessage("label." + dependsOn.substring(dependsOn.lastIndexOf(".") + 1));
              emailActionResult.setMessage(messageResources.getMessage("errors.emailAction.DependsOn", label == null ? dependsOn : label));
              status = false;
            }
          }
          catch (SecurityException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          catch (NoSuchMethodException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          catch (IllegalArgumentException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          catch (IllegalAccessException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          catch (InvocationTargetException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } 
        }
      }
    }
    return status;
  }
  
  private StringBuffer processTagSubstitution(Agency agency, Applicant applicant, StringBuffer template, Boolean html, MessageResources messageResources)
  {
    logger.debug("processTagSubstitution() {}", applicant.getFullName());
    StringBuffer content = new StringBuffer(template);
    Class agencyClass    = agency.getClass();
    Class applicantClass = applicant.getClass();
    Class userClass      = applicant.getUser().getClass();
    Class addressClass   = applicant.getAddress().getClass();
    Object sourceObject  = null;
    Method method        = null;
    Pattern pattern      = Pattern.compile("(%[a-zA-Z0-9._]+%)");
    Matcher matcher      = pattern.matcher(content.toString());
    String tag           = null;
    String sourceName    = null;
    String methodName    = null;
    int index = -1;
    while (matcher.find())
    {
      // The matcher.group() such as: %applicant.user.fullName% is the method getFullName on User object.
      tag = matcher.group().substring(1, matcher.group().length() - 1);
      index = content.indexOf(matcher.group());
      if (tag.equals("REQUEST_DOCUMENTS"))
      {
        content.replace(index, index + matcher.group().length(), requestDocumentsText(applicant, html));
      }
      else
      {
        // Class Name will be agency or applicant.
        sourceName = tag.substring(0, tag.indexOf("."));
        logger.debug("sourceName {}", sourceName);
        try
        {
          // The matcher.group() will be something like %fullName% and the method will be: getFullName.
          methodName = "get" + StringUtils.capitalize(tag.substring(tag.lastIndexOf(".") + 1));
          logger.debug("methodName {}", methodName);
          int dotCount = StringUtils.countMatches(tag, ".");
          if (dotCount == 1)
          {
            if (sourceName.equals("applicant"))
            {
              // Eg. applicant.crbExpiryDate
              method = applicantClass.getMethod(methodName, new Class[] {});
              sourceObject = applicant;
            }
            else
            {
              // Eg. agency.telephoneNumber
              method = agencyClass.getMethod(methodName, new Class[] {});
              sourceObject = agency;
            }
          }
          else
          {
            // Has 2 dots. Eg. applicant.user.fullName or applicant.address.postalCode 
            if (tag.substring(tag.indexOf(".") + 1, tag.lastIndexOf(".")).equalsIgnoreCase("user"))
            {
              // User method.
              method = userClass.getMethod(methodName, new Class[] {});
              sourceObject = applicant.getUser();
            }
            else
            {
              // Address method. Can be from Applicant or Agency.
              if (sourceName.equals("applicant"))
              {
                method = addressClass.getMethod(methodName, new Class[] {});
                sourceObject = applicant.getAddress();
              }
              else
              {
                method = addressClass.getMethod(methodName, new Class[] {});
                sourceObject = agency.getAddress();
              }
            }
          }
          if (method.getReturnType() == String.class)
          {
            String value = (String)method.invoke(sourceObject, new Object[0]);
            if (value == null)
            {
              content.replace(index, index + matcher.group().length(), methodName + " " + messageResources.getMessage("label.emailWarningNoData"));
            }
            else
            {
              content.replace(index, index + matcher.group().length(), value);
            }
          }
          else
            if (method.getReturnType() == Integer.class)
            {
              Integer value = (Integer)method.invoke(sourceObject, new Object[0]);
              if (value == null)
              {
                content.replace(index, index + matcher.group().length(), methodName + " " + messageResources.getMessage("label.emailWarningNoData"));
              }
              else
              {
                content.replace(index, index + matcher.group().length(), value.toString());
              }
            }
            else
              if (method.getReturnType() == Date.class)
              {
                Date value = (Date)method.invoke(sourceObject, new Object[0]);
                if (value == null)
                {
                  content.replace(index, index + matcher.group().length(), methodName + " " + messageResources.getMessage("label.emailWarningNoData"));
                }
                else
                {
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
                  content.replace(index, index + matcher.group().length(), simpleDateFormat.format(value));
                }
              }
              else
                if (method.getReturnType() == Boolean.class)
                {
                  Boolean value = (Boolean)method.invoke(sourceObject, new Object[0]);
                  if (value)
                  {
                    content.replace(index, index + matcher.group().length(), messageResources.getMessage("label.yes"));
                  }
                  else
                  {
                    content.replace(index, index + matcher.group().length(), messageResources.getMessage("label.no"));
                  }
                }
                else
                {
                  Object value = method.invoke(sourceObject, new Object[0]);
                  content.replace(index, index + matcher.group().length(), value.toString());
                }
        }
        catch (SecurityException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } 
      }
    }
    return content;
  }
  
  private String requestDocumentsText(Applicant applicant, Boolean html)
  {
    StringBuffer text = new StringBuffer();
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<CompliancyTest> listCompliancyTest = agyService.getCompliancyTests(true);
    Class applicantClass = applicant.getClass();
    String methodName    = null;
    String property      = null;
    for (CompliancyTest compliancyTest : listCompliancyTest)
    {
      property = compliancyTest.getProperty();
      methodName = "get" + StringUtils.capitalize(property.substring(property.lastIndexOf(".") + 1));
      try
      {
        Method method = applicantClass.getMethod(methodName, new Class[] {});
        if (method.getReturnType() == String.class)
        {
          // Method returns a String.
          String value = (String)method.invoke(applicant, new Object[0]);
          if (compliancyTest.getValue().equals("NOT EMPTY"))
          {
            if (StringUtils.isNotEmpty(value))
            {
              // Compliant. Nothing to request.
            }
            else
            {
              // Request Documents...
              writeLine(text, compliancyTest, html);
            }
          }
          else if (compliancyTest.getValue().equals("EMPTY"))
          {
            if (StringUtils.isEmpty(value))
            {
              // Compliant. Nothing to request.
            }
            else
            {
              // Request Documents...
              writeLine(text, compliancyTest, html);
            }
          }
          else
          {
            // Test actual value.
            if (compliancyTest.getValue().equals(value))
            {
              // Compliant. Nothing to request.
            }
            else
            {
              // Request Documents...
              writeLine(text, compliancyTest, html);
            }
          }
        }
        else if (method.getReturnType() == Boolean.class)
        {
          Boolean value = (Boolean)method.invoke(applicant, new Object[0]);
          Boolean compliancyTestValue = new Boolean(compliancyTest.getValue());
          if (compliancyTestValue.equals(value))
          {
            // Compliant. Nothing to request.
          }
          else
          {
            // Request Documents...
            writeLine(text, compliancyTest, html);
          }
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return text.toString();
  }
  
  private void writeLine(StringBuffer text, CompliancyTest compliancyTest, Boolean html)
  {
    if (html)
    {
      text.append("<p>");
    }
    else
    {
      // NOT html...
      if (text.length() > 0)
      {
        // NOT first line. Add a new line.
        text.append("\n");
      }
    }
    text.append(compliancyTest.getRequiredDocumentText());
    if (html)
    {
      text.append("</p>");
    }
  }
}
