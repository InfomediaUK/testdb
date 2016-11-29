package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SubcontractInvoiceItem extends Base
{
  private Integer subcontractInvoiceItemId;
  private Integer subcontractInvoiceId;
  private String bankReqNum;
  private String staffName;
  private Date date;
  private Time startTime;
  private Time endTime;
  private BigDecimal noOfHours;
  private BigDecimal rate;
  private BigDecimal value;
  private Integer nhsBookingId;
  private Integer applicantId;
  private Integer shiftId;
  private Integer bookingId;
  private Integer bookingGradeId;
  private Integer bookingDateId;
  private String comment;
  
  public SubcontractInvoiceItem(Integer subcontractInvoiceId, NhsBookingUser nhsBookingUser)
  {
    super();
    this.subcontractInvoiceId = subcontractInvoiceId;
    this.bankReqNum = nhsBookingUser.getBankReqNum();
    this.staffName = nhsBookingUser.getStaffName();
    this.date = nhsBookingUser.getDate();
    this.startTime = nhsBookingUser.getStartTime();
    this.endTime = nhsBookingUser.getEndTime();
    this.noOfHours = nhsBookingUser.getWorkedNoOfHours();
    this.rate = nhsBookingUser.getRate();
    this.value = nhsBookingUser.getValue();
    this.nhsBookingId = nhsBookingUser.getNhsBookingId();
    this.applicantId = nhsBookingUser.getApplicantId();
    this.shiftId = nhsBookingUser.getShiftId();
    this.bookingId = nhsBookingUser.getBookingId();
    this.bookingGradeId = nhsBookingUser.getBookingGradeId();
    this.bookingDateId = nhsBookingUser.getBookingDateId();
    this.comment = nhsBookingUser.getComment();
  }

  public SubcontractInvoiceItem()
  {
    super();
  }

  public Integer getNhsBookingId()
  {
    return nhsBookingId;
  }

  public void setNhsBookingId(Integer nhsBookingId)
  {
    this.nhsBookingId = nhsBookingId;
  }

  public Integer getApplicantId()
  {
    return applicantId;
  }

  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }

  public Integer getSubcontractInvoiceId()
  {
    return subcontractInvoiceId;
  }

  public void setSubcontractInvoiceId(Integer subcontractInvoiceId)
  {
    this.subcontractInvoiceId = subcontractInvoiceId;
  }

  public String getSubcontractInvoiceNumber()
  {
    return "S" + String.format("%06d", subcontractInvoiceId);
  }

  public BigDecimal getNoOfHours()
  {
    return noOfHours;
  }

  public void setNoOfHours(BigDecimal noOfHours)
  {
    this.noOfHours = noOfHours;
  }

  public BigDecimal getRate()
  {
    return rate;
  }

  public void setRate(BigDecimal rate)
  {
    this.rate = rate;
  }

  public BigDecimal getValue()
  {
    return value;
  }

  public void setValue(BigDecimal value)
  {
    this.value = value;
  }

  public String getBankReqNum()
  {
    return bankReqNum;
  }

  public void setBankReqNum(String bankReqNum)
  {
    this.bankReqNum = bankReqNum;
  }

  public Integer getBookingId()
  {
    return bookingId;
  }

  public void setBookingId(Integer bookingId)
  {
    this.bookingId = bookingId;
  }

  public Integer getBookingDateId()
  {
    return bookingDateId;
  }

  public void setBookingDateId(Integer bookingDateId)
  {
    this.bookingDateId = bookingDateId;
  }

  public Integer getBookingGradeId()
  {
    return bookingGradeId;
  }

  public void setBookingGradeId(Integer bookingGradeId)
  {
    this.bookingGradeId = bookingGradeId;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public void setEndTime(Time endTime)
  {
    this.endTime = endTime;
  }

  public Integer getSubcontractInvoiceItemId()
  {
    return subcontractInvoiceItemId;
  }

  public void setSubcontractInvoiceItemId(Integer nhsBookingId)
  {
    this.subcontractInvoiceItemId = nhsBookingId;
  }

  public Integer getShiftId()
  {
    return shiftId;
  }

  public void setShiftId(Integer shiftId)
  {
    this.shiftId = shiftId;
  }

  public String getStaffName()
  {
    return staffName;
  }

  public void setStaffName(String staffName)
  {
    this.staffName = staffName;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public void setStartTime(Time startTime)
  {
    this.startTime = startTime;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setSubcontractInvoiceItemId(rs.getInt("SUBCONTRACTINVOICEITEMID"));
    setSubcontractInvoiceId(rs.getInt("SUBCONTRACTINVOICEID"));
    setBankReqNum(rs.getString("BANKREQNUM"));
    setStaffName(rs.getString("STAFFNAME"));
    setDate(rs.getDate("DATE"));
    setStartTime(rs.getTime("STARTTIME"));
    setEndTime(rs.getTime("ENDTIME"));
    setNoOfHours(rs.getBigDecimal("NOOFHOURS"));
    setRate(rs.getBigDecimal("RATE"));
    setValue(rs.getBigDecimal("VALUE"));
    setNhsBookingId(rs.getInt("NHSBOOKINGID"));
    setApplicantId(rs.getInt("APPLICANTID"));
    setShiftId(rs.getInt("SHIFTID"));
    setBookingId(rs.getInt("BOOKINGID"));
    setBookingDateId(rs.getInt("BOOKINGDATEID"));
    setBookingGradeId(rs.getInt("BOOKINGGRADEID"));
    setComment(rs.getString("COMMENT"));
  }

  public String toString()
  {
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
    formatDate.setLenient(false);
    formatTime.setLenient(false);
    StringBuffer text = new StringBuffer();
    text.append("bankReqNum=");
    text.append(bankReqNum);
    text.append(",");
    text.append("staffName=");
    text.append(staffName);
    text.append(",");
    text.append("bookingId=");
    text.append(bookingId);
    text.append(",");
    text.append("bookingGradeId=");
    text.append(bookingGradeId);
    text.append(",");
    text.append("date=");
    text.append(date == null ? "NULL" : formatDate.format(date));
    text.append(",");
    text.append("start time=");
    text.append(startTime == null ? "NULL" : formatTime.format(startTime));
    text.append(",");
    text.append("end time=");
    text.append(endTime == null ? "NULL" : formatTime.format(endTime));
    text.append(",");
    if (subcontractInvoiceId != null)
    {
      text.append(",");
      text.append("subcontractInvoiceId=");
      text.append(subcontractInvoiceId);
    }    
    if (rate.compareTo(new BigDecimal(0)) == 0)
    {
      text.append(",");
      text.append("rate=");
      text.append(rate);
    }    
    if (value.compareTo(new BigDecimal(0)) == 0)
    {
      text.append(",");
      text.append("value=");
      text.append(value);
    }    
    if (applicantId != null)
    {
      text.append(",");
      text.append("applicantId=");
      text.append(applicantId);
    }    
    text.append(",");
    if (shiftId != null)
    {
      text.append(",");
      text.append("shiftId=");
      text.append(shiftId);
    }    
    if (bookingId != null)
    {
      text.append(",");
      text.append("bookingId=");
      text.append(bookingId);
    }    
    if (bookingDateId != null)
    {
      text.append(",");
      text.append("bookingDateId=");
      text.append(bookingDateId);
    }    
    return text.toString();
  }

}
