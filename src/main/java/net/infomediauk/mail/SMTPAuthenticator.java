package net.infomediauk.mail;

import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends javax.mail.Authenticator
{
  String username = "cgg0dhqh51j0";//"infomedia@infomediauk.net";
  String password = "helmet22";//"eksnzy4g";

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public PasswordAuthentication getPasswordAuthentication() 
  {
    return new PasswordAuthentication(username, password);
 }
}
