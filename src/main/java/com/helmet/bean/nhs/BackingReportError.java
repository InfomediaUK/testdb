package com.helmet.bean.nhs;

public class BackingReportError
{
  private Integer nhsBackingReportId;
  private String message;
  private String view;
  
  public BackingReportError(String message)
  {
    super();
    this.message = message;
    view = "DETAIL";
  }

  public BackingReportError(Integer nhsBackingReportId, String message)
  {
    super();
    this.nhsBackingReportId = nhsBackingReportId;
    this.message = message;
    view = "DETAIL";
  }

  public BackingReportError(Integer nhsBackingReportId, String message, String view)
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
  
}
