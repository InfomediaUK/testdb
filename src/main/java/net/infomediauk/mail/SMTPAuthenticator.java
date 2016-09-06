package net.infomediauk.mail;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator
{
  public PasswordAuthentication getPasswordAuthentication() 
  {
    String username = "cgg0dhqh51j0";//"infomedia@infomediauk.net";
    String password = "helmet22";//"eksnzy4g";
    return new PasswordAuthentication(username, password);
 }
}
