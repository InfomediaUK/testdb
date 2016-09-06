package net.infomediauk.action;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.infomediauk.SendMailForm;
import net.infomediauk.mail.SMTPAuthenticator;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SendMailAction extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
  {

    SendMailForm sendMailForm = (SendMailForm)form;
    
    String to = "admin@adultebookshop.com";//"patrice@pjlocums.co.uk";//"lyndon@infomediauk.net";
    String from = "admin@matchmyjob.co.uk";//"admin@adultebookshop.com";
    String host = "customermail2.easily.co.uk";//"mail.infomediauk.net";
    Properties properties = new Properties();
    properties.setProperty("mail.smtp.host", host);
    properties.setProperty("mail.smtp.port", "25");
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.auth", "true");
    SMTPAuthenticator authenticator = new SMTPAuthenticator();
    Session session = Session.getInstance(properties, authenticator);
    session.setDebug(true);
    try
    {
      Transport transport = null;
      try
      {
        transport = session.getTransport();
        // Create a default MimeMessage object.
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
        mimeMessage.addHeader("format", "flowed");
        mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
        // Set From: header field of the header.
        mimeMessage.setFrom(new InternetAddress(from));
        // Set To: header field of the header.
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // Set Subject: header field
        mimeMessage.setSubject("Sent from " + host);
        // Now set the actual message
        mimeMessage.setText("To: " + to + " From: " + from + " Host: " + host + " User: " + authenticator.getUsername());
        transport.connect();
        // Send message
        transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
        sendMailForm.setMessage("Sent message successfully. To: " + to + " From: " + from + " Host: " + host + " User: " + authenticator.getUsername());
      }
      finally
      {
        transport.close();
      }
    }
    catch (MessagingException mex)
    {
      sendMailForm.setMessage(mex.getMessage());
      mex.printStackTrace();
   }

    return mapping.findForward("success");
  }

}
