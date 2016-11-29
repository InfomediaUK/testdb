package com.helmet.application;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator
{
  private String userName = null;//"cgg0dhqh51j0";//"infomedia@infomediauk.net";
  private String password = null;//"helmet22";//"eksnzy4g";

  public SMTPAuthenticator(String userName, String password)
  {
    super();
    this.userName = userName;
    this.password = password;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  public PasswordAuthentication getPasswordAuthentication() 
  {
    return new PasswordAuthentication(userName, password);
  }

}
