package com.helmet.bean.nhs;

public class BackingReportLineError
{
  private Integer bookingGradeApplicantId;
  private String message;
  
  public BackingReportLineError(String message)
  {
    super();
    this.message = message;
  }

  public BackingReportLineError(Integer bookingGradeApplicantId, String message)
  {
    super();
    this.bookingGradeApplicantId = bookingGradeApplicantId;
    this.message = message;
  }

  public Integer getBookingGradeApplicantId()
  {
    return bookingGradeApplicantId;
  }

  public void setBookingGradeApplicantId(Integer bookingGradeId)
  {
    this.bookingGradeApplicantId = bookingGradeId;
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
