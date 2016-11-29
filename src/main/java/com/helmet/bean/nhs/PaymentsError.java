package com.helmet.bean.nhs;

public class PaymentsError
{
  private Integer nhsBackingReportId;
  private String message;
  private String view;
  
  public PaymentsError(String message)
  {
    super();
    this.message = message;
    view = "DETAIL";
  }

  public PaymentsError(Integer nhsBackingReportId, String message)
  {
    super();
    this.nhsBackingReportId = nhsBackingReportId;
    this.message = message;
    view = "DETAIL";
  }

  public PaymentsError(Integer nhsBackingReportId, String message, String view)
  {
    super();
    this.nhsBackingReportId = nhsBackingReportId;
    this.message = message;
    this.view = view;
  }

  public Integer getNhsBackingReportId()
  {
    return nhsBackingReportId;
  }

  public void setNhsBackingReportId(Integer nhsBackingReportId)
  {
    this.nhsBackingReportId = nhsBackingReportId;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public String getView()
  {
    return view;
  }
 
  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("PaymentsError:");
    result.append(" ");
    result.append("nhsBackingReportId=");
    result.append(nhsBackingReportId);
    result.append(" ");
    result.append("message=");
    result.append(message);
    result.append(" ");
    result.append("view=");
    result.append(view);
    result.append("\n");
    return result.toString();
  } 
}
