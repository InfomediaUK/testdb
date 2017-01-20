package com.helmet.application;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.commons.validator.routines.EmailValidator;

import com.sun.mail.imap.protocol.FLAGS;

/**
 * Handles sending of emails either single or multiple mail merge.
 *
 * The properties and mail merge files can be reloaded by calling the init() method
 * but this is not very safe. Any mail processes that were running when the 'reload'
 * started would likely fail. It's best to be sure that there are no mail processes
 * running before doing a 'reload'.
 *
 */
public class MailHandler {

	private static MailHandler mailHandler;

	protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	private Boolean sendAutoFillEmail;
	private Boolean sendResetPwdEmail;
	private String siteUrl;
  private String hostName;
  private String hostValue;
  private String portName;
  private String portValue;
	private String connectionTimeoutName;
	private String connectionTimeoutValue;
	private String timeoutName;
	private String timeoutValue;
  private String userName;
  private String password;

	private boolean debug;

	private HashMap<String, ErrorTextCode> emailErrorMap;

	public static MailHandler getInstance() 
	{
		if (mailHandler == null) {
			// NOT instantiated yet so create it.
			synchronized (MailHandler.class) {
				// Only ONE thread at a time here!
				if (mailHandler == null) {
					mailHandler = new MailHandler();
				}
			}
		}
		return mailHandler;
	}

	public MailHandler() 
	{

		emailErrorMap = new HashMap<String, ErrorTextCode>();

		emailErrorMap.put("EmailError0", new ErrorTextCode(
				"Could not connect to SMTP host", 101));
		emailErrorMap.put("EmailError1", new ErrorTextCode(
				"SendFailedException: 550 5.7.1", 102));
		emailErrorMap.put("EmailError2", new ErrorTextCode(
				"SendFailedException: 550 5.1.1", 103));
		emailErrorMap.put("EmailError3", new ErrorTextCode(
				"SendFailedException: 553 5.1.8", 200));
		emailErrorMap.put("EmailError4", new ErrorTextCode(
				"SendFailedException: 451 4.1.8", 201));
		emailErrorMap.put("EmailError5", new ErrorTextCode(
				"MessagingException: 553 5.1.2", 202));
		emailErrorMap.put("EmailError6", new ErrorTextCode(
				"MessagingException: 451 4.7.1", 203));
		emailErrorMap.put("EmailError7", new ErrorTextCode(
				"SendFailedException: 450 4.7.1", 300));
		emailErrorMap.put("EmailError8", new ErrorTextCode(
				"SendFailedException: 553 5.1.2", 301));
		emailErrorMap.put("EmailError9", new ErrorTextCode(
				"MessagingException: 451 4.1.8", 302));
		emailErrorMap.put("EmailError10", new ErrorTextCode(
				"MessagingException: 553 5.1.8", 303));
		emailErrorMap.put("EmailError11", new ErrorTextCode(
				"Illegal whitespace in address", 304));

	}

	public Boolean getSendAutoFillEmail() 
	{
		return sendAutoFillEmail;
	}

	public void setSendAutoFillEmail(Boolean sendAutoFillEmail) {
		this.sendAutoFillEmail = sendAutoFillEmail;
	}

	public Boolean getSendResetPwdEmail() {
		return sendResetPwdEmail;
	}

	public void setSendResetPwdEmail(Boolean sendResetPwdEmail) 
	{
		this.sendResetPwdEmail = sendResetPwdEmail;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) 
	{
		this.siteUrl = siteUrl;
	}

	public void setTimeoutName(String timeoutName) 
	{
		this.timeoutName = timeoutName;
	}

	public void setTimeoutValue(String timeoutValue) 
	{
		this.timeoutValue = timeoutValue;
	}

	public void setConnectionTimeoutName(String connectionTimeoutName) 
	{
		this.connectionTimeoutName = connectionTimeoutName;
	}

	public void setConnectionTimeoutValue(String connectionTimeoutValue) 
	{
		this.connectionTimeoutValue = connectionTimeoutValue;
	}

	public String getHostName()
  {
    return hostName;
  }

  public void setHostName(String hostName) 
	{
		this.hostName = hostName;
	}

	public String getHostValue()
  {
    return hostValue;
  }

  public void setHostValue(String hostValue) 
	{
		this.hostValue = hostValue;
	}

	public String getPortName()
  {
    return portName;
  }

  public void setPortName(String portName)
  {
    this.portName = portName;
  }

  public String getPortValue()
  {
    return portValue;
  }

  public void setPortValue(String portValue)
  {
    this.portValue = portValue;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public void setDebug(boolean debug) 
  {
		this.debug = debug;
	}

	public int sendSingleMail(String from, String to, String subject,
			Object content, String contentType) 
	{
		// No cc or bcc.
		return sendSingleMail(from, to, null, null, subject, content, contentType);
	}

	public int sendSingleMail(String from, String to, String cc, String bcc,
			String subject, Object content, String contentType) 
	{
		int status = 0;
		// Create some properties and get the default Session.
		Properties props = new Properties();
    props.put(hostName, hostValue);
    if (StringUtils.isNotEmpty(portName) && StringUtils.isNotEmpty(portValue))
    {
      props.put(portName, portValue);
    }
    props.setProperty("mail.transport.protocol", "smtp");
    props.setProperty("mail.smtp.auth", "true");
//    props.setProperty("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//        props.put("mail.host", "customermail2.easily.co.uk");
//        props.put("mail.user", "cgg0dhqh51j0");
//        props.put("mail.password", "helmet22");
// 
// don't seem to work !!!
//
//		props.put(connectionTimeoutName, connectionTimeoutValue);
//		props.put(timeoutName, connectionTimeoutValue);
//
		// See: http://stackoverflow.com/questions/31605066/javamail-second-time-connection-failure-connect-to-different-host-and-port
    SMTPAuthenticator authenticator = new SMTPAuthenticator(userName, password);//"cgg0dhqh51j0", "helmet22"
    // Base64 ////////////////////////////
    // cgg0dhqh51j0 = Y2dnMGRocWg1MWow
    //     helmet22 = aGVsbWV0MjI=
    //////////////////////////////////////
    Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);
		status = sendMail(session, from, to, cc, bcc, subject, content, contentType);
		if (status > 0) 
		{
			logger.info("Send Single Mail Failed:\n");
		}
		else
		{
      logger.info("Sent Mail OK:\n");
		}
    logger.info("From:        " + from);
    logger.info("To:          " + to);
    logger.info("Cc:          " + cc);
    logger.info("Bcc:         " + bcc);
    logger.info("Subject:     " + subject);
    logger.info("ContentType: " + contentType);
    logger.info("Content:     " + content);
		return status;
	}

	private int sendMail(Session session, String from, String to,
			String subject, Object content, String contentType) {
		return sendMail(session, from, to, null, null, subject, content,
				contentType);
	}

	private int sendMail(Session session, String from, String to, String cc,
			String bcc, String subject, Object content, String contentType) 
	{
		int status = 0;
		try 
		{
		  Transport transport = null;
		  try
		  {
        transport = session.getTransport("smtp");
  			// Create a message.
  			MimeMessage mimeMessage = new CustomMimeMessage(session);
  			// Added header stuff. 23/07/2016 Lyndon
  			mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
//  			mimeMessage.addHeader("format", "flowed");
//  			mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
  			if (StringUtils.isEmpty(from)) 
  			{
  				mimeMessage.setFrom();
  			} 
  			else 
  			{
  				mimeMessage.setFrom(new InternetAddress(from));
  			}
  			InternetAddress[] toInternetAddresses = InternetAddress.parse(to);
        mimeMessage.addRecipients(Message.RecipientType.TO, toInternetAddresses);
  			if (StringUtils.isNotEmpty(cc)) 
  			{
  			  // Handle CC comma delimited list or single email address.
  			  InternetAddress[] ccInternetAddresses = InternetAddress.parse(cc);
          mimeMessage.addRecipients(Message.RecipientType.CC, ccInternetAddresses);
  			}
  			if (StringUtils.isNotEmpty(bcc)) 
  			{
          // Handle BCC comma delimited list or single email address.
          InternetAddress[] bccInternetAddresses = InternetAddress.parse(bcc);
          mimeMessage.addRecipients(Message.RecipientType.BCC, bccInternetAddresses);
  			}
  
  			mimeMessage.setSubject(subject);
  
  			if (content instanceof Multipart) 
  			{
  				mimeMessage.setContent((Multipart) content);
  			} 
  			else 
  			{
  				mimeMessage.setContent(content, contentType);
  			}
  			mimeMessage.setSentDate(new java.util.Date());
        transport.connect();
  			// Send the mail.
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        Store store = null;
        try
        {
          store = session.getStore("imap");
          //store.connect("mail.easily.co.uk", from, "eFTba8JnAeYbdk"); // For lyndon@matchmyjob.co.uk
          store.connect("mail.easily.co.uk", from, "helmet22");
          Folder folder = (Folder)store.getFolder("Sent");
          folder.open(Folder.READ_WRITE);
          folder.appendMessages(new Message[]{mimeMessage});
          mimeMessage.setFlag(FLAGS.Flag.RECENT, true);
        }
        finally
        {
          if (store != null)
          {
            store.close();
          }
        }
      }
      finally
      {
        if (transport != null)
        {
          transport.close();
        }
      }
		} 
		catch (Exception e) 
		{
			// Will be javax.mail.SendFailedException, javax.mail.MessagingException or
			// javax.mail.internet.AddressException.
			// It is more likely that the recipient address is wrong than the sender's...
      logger.catching(e);
			status = getErrorCode(e);

			// TODO
			logger.error("ERROR - " + status);

			return status;
		}
		return status;
	}

	/**
	 * Searches the exception message for text as found in each entry in the emailErrorMap.
	 * If a match is found then the errorCode is returned. Otherwise a default value is returned.
	 */
	private int getErrorCode(Exception e) {
		String errorMessage = e.getMessage();
		// An Exception has occurred - set the errorCode to a 'catch all' value. This will
		// be overwritten below if a specific error has occurred.
		int errorCode = 100; // Unknown - considered to be a host problem.

		ErrorTextCode errorTextCode;
		for (int i = 0; i < emailErrorMap.size(); i++) {
			errorTextCode = (ErrorTextCode) emailErrorMap.get("EmailError" + i);
			if (errorMessage.indexOf(errorTextCode.getErrorText()) > -1) {
				// Error message contains the errorText. Set the errorCode.
				errorCode = errorTextCode.getErrorCode();
				break;
			}
		}

		return errorCode;
	}
	
	public String validateEmailAddresses(String emailAddresses)
	{
	  InternetAddress[] internetAddresses = null;
	  StringBuffer result = new StringBuffer();
    try
    {
      internetAddresses = InternetAddress.parse(emailAddresses, true);
    }
    catch (AddressException e)
    {
      logger.catching(e);
      result.append("Unable to parse email address.");
      result.append(e.getMessage());
      return result.toString();
    }
    for (InternetAddress internetAddress : internetAddresses)
    {
      if (!EmailValidator.getInstance().isValid(internetAddress.getAddress()))
      {
        result.append(internetAddress.getAddress());
        result.append(". ");
      }
    }
	  return result.toString();
	}
}