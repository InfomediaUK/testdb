package com.helmet.bean;


public class NhsBookingApplicantIds
{
  private Integer nhsBookingId;
  private Integer applicantId;
  private Integer bookingId;
  private Integer bookingDateId;
  private Integer clientAgencyJobProfileGradeId;
  
  public NhsBookingApplicantIds(Integer nhsBookingId, Integer applicantId, Integer bookingId, Integer bookingDateId, Integer clientAgencyJobProfileGradeId)
  {
    super();
    this.nhsBookingId = nhsBookingId;
    this.applicantId = applicantId;
    this.bookingId = bookingId;
    this.bookingDateId = bookingDateId;
    this.clientAgencyJobProfileGradeId = clientAgencyJobProfileGradeId;
  }

  public Integer getApplicantId()
  {
    return applicantId;
  }


  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }


  public Integer getBookingDateId()
  {
    return bookingDateId;
  }


  public void setBookingDateId(Integer bookingDateId)
  {
    this.bookingDateId = bookingDateId;
  }


  public Integer getBookingId()
  {
    return bookingId;
  }


  public void setBookingId(Integer bookingId)
  {
    this.bookingId = bookingId;
  }


  public Integer getNhsBookingId()
  {
    return nhsBookingId;
  }


  public void setNhsBookingId(Integer nhsBookingId)
  {
    this.nhsBookingId = nhsBookingId;
  }


  public Integer getClientAgencyJobProfileGradeId()
  {
    return clientAgencyJobProfileGradeId;
  }


  public void setClientAgencyJobProfileGradeId(Integer clientAgencyJobProfileGradeId)
  {
    this.clientAgencyJobProfileGradeId = clientAgencyJobProfileGradeId;
  }

  public String toString()
  {
    StringBuffer text = new StringBuffer();
    text.append("nhsBookingId=");
    text.append(nhsBookingId);
    text.append(",");
    text.append("applicantId=");
    text.append(applicantId);
    text.append(",");
    text.append("bookingId=");
    text.append(bookingId);
    text.append(",");
    text.append("bookingDateId=");
    text.append(bookingDateId);
    text.append(",");
    text.append("clientAgencyJobProfileGradeId=");
    text.append(clientAgencyJobProfileGradeId);
    return text.toString();
  }

}
