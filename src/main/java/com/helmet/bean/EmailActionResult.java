package com.helmet.bean;

public class EmailActionResult
{
  private Integer applicantId;
  private String fullName;
  private String emailAddress;
  private String emailStatus;
  private String message;
  public EmailActionResult()
  {
    super();
  }
  public EmailActionResult(Integer applicantId, String fullName, String emailAddress)
  {
    super();
    this.applicantId = applicantId;
    this.fullName = fullName;
    this.emailAddress = emailAddress;
  }
  public Integer getApplicantId()
  {
    return applicantId;
  }
  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }
  public String getEmailAddress()
  {
    return emailAddress;
  }
  public void setEmailAddress(String emailAddress)
  {
    this.emailAddress = emailAddress;
  }
  public String getEmailStatus()
  {
    return emailStatus;
  }
  public void setEmailStatus(String emailStatus)
  {
    this.emailStatus = emailStatus;
  }
  public String getMessage()
  {
    return message;
  }
  public void setMessage(String message)
  {
    this.message = message;
  }
  public String getFullName()
  {
    return fullName;
  }
  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }
}
