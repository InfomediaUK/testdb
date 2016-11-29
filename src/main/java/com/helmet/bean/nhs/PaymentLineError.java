package com.helmet.bean.nhs;

public class PaymentLineError
{
  private String backingReportId;
  private String message;
  
  public PaymentLineError(String message)
  {
    super();
    this.message = message;
  }

  public PaymentLineError(String backingReportId, String message)
  {
    super();
    this.backingReportId = backingReportId;
    this.message = message;
  }

  public String getBackingReportId()
  {
    return backingReportId;
  }

  public void setBackingReportId(String backingReportId)
  {
    this.backingReportId = backingReportId;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }
  
}
