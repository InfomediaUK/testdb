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
    
    String to = "lyndon@infomediauk.net";
    String from = "admin@adultebookshop.com";
    String host = "mail.infomediauk.net";
    Properties properties = new Properties();
    properties.setProperty("mail.smtp.host", host);
    properties.setProperty("mail.smtp.port", "25");
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.auth", "true");
    Authenticator auth = new SMTPAuthenticator();
    Session session = Session.getDefaultInstance(properties, auth);
    session.setDebug(true);
    try
    {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: header field
      message.setSubject("Sent from Java Web App");

      // Now set the actual message
      message.setText("Authenticated message!");

      // Send message
      Transport.send(message);
      sendMailForm.setMessage("Sent message successfully....");
    }
    catch (MessagingException mex)
    {
      sendMailForm.setMessage(mex.getMessage());
      mex.printStackTrace();
   }

    return mapping.findForward("success");
  }

}
