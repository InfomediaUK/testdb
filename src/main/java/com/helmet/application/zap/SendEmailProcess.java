package com.helmet.application.zap;

import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
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
import com.helmet.application.FileHandler;
import com.helmet.application.MailHandler;
import com.helmet.application.Utilities;
import com.helmet.application.app.AppUtilities;
import com.helmet.application.zap.abztract.ZapAction;
import com.helmet.bean.Agency;
import com.helmet.bean.Applicant;

public class SendEmailProcess extends ZapAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm) form;

    String subject = (String) dynaForm.get("subject");
    String message = (String) dynaForm.get("message");
    String attachment = (String) dynaForm.get("attachment");
    ZapService zapService = ServiceFactory.getInstance().getZapService();
    Applicant applicant = AppUtilities.getCurrentApplicant(request);
    Agency agency = zapService.getAgency(applicant.getAgencyId());

    int status = sendEmail(request, agency, applicant, subject, message, attachment);

    if (status != 0)
    {
      // an error has occurred ... inform the user ...

      String errorKey = "error.email." + status;

      ActionMessages errors = new ActionMessages();

      errors.add("sendEmail", new ActionMessage(errorKey));
      saveErrors(request, errors);
      return mapping.getInputForward();

    }

    String referer = (String) dynaForm.get("referer");

    if (referer == null || "".equals(referer)) { return mapping.findForward("success"); }

    try
    {
      response.sendRedirect(referer);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
      //
      return mapping.findForward("success");
    }

    return null;

  }

  protected int sendEmail(HttpServletRequest request, Agency agency, Applicant applicant, String subject, String message, String attachment)
  {
    String fromEmailAddress = agency.getAgencyAdminEmailAddress();
    String toEmailAddress = agency.getAgencyAdminEmailAddress();
    if (fromEmailAddress.startsWith("test"))
    {
      // Only use in TEST mode.
      toEmailAddress = applicant.getEmailAddress();
    }
    System.out.println(subject);
    System.out.println(message);
    System.out.println(attachment);

    StringBuffer content = new StringBuffer();
    StringBuffer htmlContent = new StringBuffer();

    MessageResources messageResources = getResources(request);

    emailTop(htmlContent, FileHandler.getInstance().getEmailTemplateRealPath("/agy/site.css"));
    emailHeader(htmlContent, agency);
    emailTopDivider(htmlContent);

    String[] lines = message.split("\r\n|\r|\n");

    for (int i = 0; i < lines.length; i++)
    {
      String line = lines[i];

      content.append(cleanTextLine(line) + "\n");
      htmlContent.append(cleanHtmlLine(line) + "<br/>\n");

    }
    htmlContent.append("<br/>");
    htmlContent.append(applicant.getFullName());
    htmlContent.append("<br/>");
    htmlContent.append(applicant.getEmailAddress());
    if (applicant.getTelephoneNumber() != null)
    {
      htmlContent.append("<br/>");
      htmlContent.append(applicant.getTelephoneNumber());
    }    
    if (applicant.getMobileNumber() != null)
    {
      htmlContent.append("<br/>");
      htmlContent.append(applicant.getMobileNumber());
    }    
    emailBottomDivider(htmlContent);
    emailFooter(htmlContent, messageResources);
    emailBottom(htmlContent);

    System.out.println("");
    System.out.println(">>>>> HTML OUTPUT >>>>>");
    System.out.println(htmlContent.toString());
    System.out.println("<<<<< HTML OUTPUT <<<<<");
    System.out.println("");
    System.out.println(">>>>> TEXT OUTPUT >>>>>");
    System.out.println(content.toString());
    System.out.println("<<<<< TEXT OUTPUT <<<<<");
    System.out.println("");

    String contentType = "text/plain";

    MimeMultipart multipartRoot = emailMultipart(content, htmlContent, attachment, agency, request);

    int status = MailHandler.getInstance().sendSingleMail(fromEmailAddress, toEmailAddress, null, null, subject, multipartRoot, contentType);

    System.out.println(status);

    return status;
  }

  protected void emailTop(StringBuffer htmlContent, String cssFileName)
  {
    htmlContent.append("<html><head><title>MMJ Email</title>");

    htmlContent.append("<style>");
    Utilities.suckInFile(cssFileName, htmlContent);
    htmlContent.append("</style>");

    htmlContent.append("</head><body>");
  }

  protected void emailHeader(StringBuffer htmlContent, Agency agency)
  {
    htmlContent.append("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
    htmlContent.append("  <tr>");
    htmlContent.append("    <td valign=\"top\" align=\"left\">");

    htmlContent.append("<img src=\"cid:mmjLogo\" border=\"0\">");

    htmlContent.append("    </td>");
    htmlContent.append("    <td valign=\"top\" align=\"left\" width=\"100%\">");
    htmlContent.append("      &nbsp;");
    htmlContent.append("    </td>");
    htmlContent.append("    <td valign=\"top\" align=\"right\">");

    String agencyLogoFilename = agency.getLogoFilename();

    if (agencyLogoFilename == null || "".equals(agencyLogoFilename))
    {
      htmlContent.append(agency.getName());
    }
    else
    {
      htmlContent.append("<img src=\"cid:agencyLogo\" border=\"0\">");
    }
    htmlContent.append("    </td>");
    htmlContent.append("  </tr>");
    htmlContent.append("</table>");
  }

  protected void emailTopDivider(StringBuffer htmlContent)
  {
    htmlContent.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
    htmlContent.append("<tr><td height=\"1\" width=\"100%\" bgcolor=\"#e0e0e0\"></td></tr>");
    htmlContent.append("</table>");
    htmlContent.append("<br/>\n");
  }

  protected void emailBottomDivider(StringBuffer htmlContent)
  {
    htmlContent.append("<br/>\n");
    htmlContent.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
    htmlContent.append("<tr><td height=\"1\" width=\"100%\" bgcolor=\"#e0e0e0\"></td></tr>");
    htmlContent.append("</table>");
  }

  protected void emailBottom(StringBuffer htmlContent)
  {
    htmlContent.append("</body></html>");
  }

  protected MimeMultipart emailMultipart(StringBuffer content, StringBuffer htmlContent, String attachment, Agency agency, HttpServletRequest request)
  {
    MimeMultipart multipartRoot = new MimeMultipart("related");
    // TODO - NEED TO sort out try - catch !!! shouldn't be here !!!
    try
    {
      // add related multi-part for root
      MimeBodyPart contentRoot = new MimeBodyPart();
      MimeMultipart multipartAlt = new MimeMultipart("alternative");

      // alternative message
      BodyPart messageBodyPart;
      messageBodyPart = new MimeBodyPart();
      messageBodyPart.setContent(content.toString(), "text/plain");
      multipartAlt.addBodyPart(messageBodyPart);

      messageBodyPart = new MimeBodyPart();
      messageBodyPart.setContent(htmlContent.toString(), "text/html");
      multipartAlt.addBodyPart(messageBodyPart);

      // Hierarchy
      contentRoot.setContent(multipartAlt);
      multipartRoot.addBodyPart(contentRoot);

      // add a part for the image
      messageBodyPart = new MimeBodyPart();
      String serverNamePrefix = request.getServerName().substring(0, request.getServerName().indexOf("."));
      serverNamePrefix = "www".equals(serverNamePrefix) ? "" : serverNamePrefix;
      String mmjLogo = FileHandler.getInstance().getFileLocation() + "/images/" + serverNamePrefix + "master-logo.jpg";
      DataSource fds = new FileDataSource(mmjLogo);
      messageBodyPart.setDataHandler(new DataHandler(fds));
      messageBodyPart.setHeader("Content-ID", "<mmjLogo>");
      messageBodyPart.setHeader("Content-Type", "image/jpeg");
      messageBodyPart.setDisposition(MimeBodyPart.INLINE);
      // add it
      multipartRoot.addBodyPart(messageBodyPart);

      String agencyLogoFilename = agency.getLogoFilename();
      if (agencyLogoFilename == null || "".equals(agencyLogoFilename))
      {
        // nuffink
      }
      else
      {
        // add a part for the image
        messageBodyPart = new MimeBodyPart();
        String agencyLogo = FileHandler.getInstance().getLogoFileLocation() + agency.getLogoUrl();
        fds = new FileDataSource(agencyLogo);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID", "<agencyLogo>");
        messageBodyPart.setHeader("Content-Type", "image/jpeg");
        messageBodyPart.setDisposition(MimeBodyPart.INLINE);
        // add it
        multipartRoot.addBodyPart(messageBodyPart);
      }

      if (attachment != null && !"".equals(attachment))
      {

        // attach a file

        messageBodyPart = new MimeBodyPart();
        // String attachment = FileHandler.getInstance().getTempFileLocation() +
        // FileHandler.getInstance().getTempFileFolder() + "/" + "ai40340.pdf";
        fds = new FileDataSource(attachment);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setFileName(fds.getName());
        multipartRoot.addBodyPart(messageBodyPart);

      }
    }
    catch (MessagingException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return multipartRoot;
  }

  protected String cleanTextLine(String line)
  {

    // remove LINK entries

    // strip out from < to > everywhere
    return line.replaceAll("<[^>]*>", "");

  }

  protected void emailFooter(StringBuffer htmlContent, MessageResources messageResources)
  {
    htmlContent.append("<div align=\"center\">");
    htmlContent.append("  <br/>");
    htmlContent.append(messageResources.getMessage("text.copyright"));
    htmlContent.append("  <br/>");
    htmlContent.append("</div>");
  }

  protected String cleanHtmlLine(String line)
  {

    // replace LINK entries with anchor

    if (line.startsWith("<LINK>"))
    {

      // strip out the url
      String theUrl = line.replaceAll("<[^>]*LINK>", "");
      // replace the <LINK>
      String withLinkReplaced = line.replaceAll("<LINK>", "<a href=\"" + theUrl + "\">");
      // replace the </LINK>
      String allReplaced = withLinkReplaced.replaceAll("</LINK>", "</a>");

      return allReplaced;

    }

    return line;

  }

}
