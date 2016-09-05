package net.infomediauk;

import org.apache.struts.action.ActionForm;

public class SendMailForm extends ActionForm
{
  String message;

  public String getMessage() 
  {
    return message;
  }

  public void setMessage(String message) 
  {
    this.message = message;
  }
}
