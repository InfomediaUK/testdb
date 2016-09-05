package net.infomediauk.mail;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator
{
  public PasswordAuthentication getPasswordAuthentication() 
  {
    String username = "infomedia@infomediauk.net";
    String password = "eksnzy4g";
    return new PasswordAuthentication(username, password);
 }
}
