package com.helmet.application;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.util.MessageResources;

import com.helmet.bean.Agency;

public class MultipartEmailer
{
  private Agency agency;
  private MessageResources messageResources;
  private String cssFileName;
  private String serverName; 
  private String fromEmailAddress; 
  private String toEmailAddress;
  private String ccEmailAddress;
  private String bccEmailAddress;
  private String subject;
  private String message;
  private String[] attachments;
  private final String contentType = "text/plain";
  
  
  public MultipartEmailer(Agency agency, MessageResources messageResources, String cssFileName, String serverName, String fromEmailAddress, String toEmailAddress, String ccEmailAddress, String bccEmailAddress, String subject, String message, String[] attachments)
  {
    super();
    this.agency = agency;
    this.messageResources = messageResources;
    this.cssFileName = cssFileName;
    this.serverName = serverName;
    this.fromEmailAddress = fromEmailAddress;
    this.toEmailAddress = toEmailAddress;
    this.ccEmailAddress = ccEmailAddress;
    this.bccEmailAddress = bccEmailAddress;
    this.subject = subject;
    this.message = message;
    this.attachments = attachments;
  }

  public int sendEmail()
  {
//    System.out.println(fromEmailAddress);
//    System.out.println(toEmailAddress);
//    System.out.println(ccEmailAddress);
//    System.out.println(bccEmailAddress);
//    System.out.println(subject);
//    System.out.println(message);
//    System.out.println(attachments);

    StringBuffer content = new StringBuffer();
    StringBuffer htmlContent = new StringBuffer();

    emailTop(htmlContent);
    emailHeader(htmlContent, agency);
    emailTopDivider(htmlContent);

    String[] lines = message.split("\r\n|\r|\n");

    for (int i = 0; i < lines.length; i++)
    {
      String line = lines[i];

      content.append(cleanTextLine(line) + "\n");
      htmlContent.append(cleanHtmlLine(line) + "<br/>\n");

    }

    emailBottomDivider(htmlContent);
    emailFooter(htmlContent, messageResources);
    emailBottom(htmlContent);

//    System.out.println("");
//    System.out.println(">>>>> HTML OUTPUT >>>>>");
//    System.out.println(htmlContent.toString());
//    System.out.println("<<<<< HTML OUTPUT <<<<<");
//    System.out.println("");
//    System.out.println(">>>>> TEXT OUTPUT >>>>>");
//    System.out.println(content.toString());
//    System.out.println("<<<<< TEXT OUTPUT <<<<<");
//    System.out.println("");

    String contentType = "text/plain";

    MimeMultipart multipartRoot = emailMultipart(content, htmlContent, attachments, agency, serverName);

    int status = MailHandler.getInstance().sendSingleMail(fromEmailAddress, toEmailAddress, ccEmailAddress, bccEmailAddress, subject, multipartRoot, contentType);

    System.out.println(status);
    
    return status;
  }
  

  protected void emailTop(StringBuffer htmlContent)
  {
    htmlContent.append("<html><head><title>MMJ Email</title>");
    if (StringUtils.isNotEmpty(cssFileName))
    {
      htmlContent.append("<style>");
      Utilities.suckInFile(cssFileName, htmlContent);
      htmlContent.append("</style>");
    }
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
  
  protected MimeMultipart emailMultipart(StringBuffer content, StringBuffer htmlContent, String[] attachments, Agency agency, String serverName)
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
      String serverNamePrefix = null;
      if (serverName.indexOf(".") == -1)
      {
        serverNamePrefix = "local";
      }
      else
      {
        serverNamePrefix = serverName.substring(0, serverName.indexOf("."));
      }
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

      if (attachments != null && attachments.length > 0)
      {
        // attach a file
        for (String attachment : attachments)
        {
          if (StringUtils.isNotEmpty(attachment))
          {
            messageBodyPart = new MimeBodyPart();
            // String attachments = FileHandler.getInstance().getTempFileLocation() +
            // FileHandler.getInstance().getTempFileFolder() + "/" + "ai40340.pdf";
            fds = new FileDataSource(attachment);
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setFileName(fds.getName());
            multipartRoot.addBodyPart(messageBodyPart);
          }          
        }
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
