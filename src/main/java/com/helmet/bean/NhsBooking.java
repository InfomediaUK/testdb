package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.agy.AgyConstants;

public class NhsBooking extends Base
{
  private Integer nhsBookingId;
  private String bankReqNum;
  private String staffName;
  private Date date;
  private Time startTime;
  private Time endTime;
  private String location;
  private String ward;
  private String assignment;
  private Integer subcontractInvoiceId;
  private BigDecimal value = new BigDecimal(0);
  private Integer applicantId;
  private Integer agencyId;
  private Integer clientId;
  private Integer siteId;
  private Integer locationId;
  private Integer jobProfileId;
  private Integer shiftId;
  private Integer bookingGroupId;
  private Integer bookingId;
  private Integer bookingGradeId;
  private Integer bookingDateId;
  private Timestamp bookingTime;
  private Timestamp applicantNotificationSent;
  private String comment;
  
  public Integer getApplicantId()
  {
    return applicantId;
  }

  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }

  public Timestamp getApplicantNotificationSent()
  {
    return applicantNotificationSent;
  }

  public void setApplicantNotificationSent(Timestamp applicantNotificationSent)
  {
    this.applicantNotificationSent = applicantNotificationSent;
  }


  public Timestamp getBookingTime()
  {
    return bookingTime;
  }

  public void setBookingTime(Timestamp bookingTime)
  {
    this.bookingTime = bookingTime;
  }

  public String getAssignment()
  {
    return assignment;
  }

  public void setAssignment(String assignment)
  {
    this.assignment = assignment;
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
    if (subcontractInvoiceId == null)
    {
      return "";
    }
    else
    {
      if (subcontractInvoiceId > 0)
      {
        // POSITIVE: Invoice
        return "S" + String.format("%06d", subcontractInvoiceId);
      }
      else
      {
        // NEGATIVE: Credit Note
        return "C" + String.format("%06d", subcontractInvoiceId * -1);
      }
    }    
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


  public Integer getJobProfileId()
  {
    return jobProfileId;
  }


  public void setJobProfileId(Integer gradeId)
  {
    this.jobProfileId = gradeId;
  }


  public String getLocation()
  {
    return location;
  }


  public void setLocation(String location)
  {
    this.location = location;
  }


  public Integer getLocationId()
  {
    return locationId;
  }


  public void setLocationId(Integer locationId)
  {
    this.locationId = locationId;
  }


  public Integer getBookingGroupId()
  {
    return bookingGroupId;
  }


  public void setBookingGroupId(Integer bookingGroupId)
  {
    this.bookingGroupId = bookingGroupId;
  }


  public Integer getNhsBookingId()
  {
    return nhsBookingId;
  }


  public void setNhsBookingId(Integer nhsBookingId)
  {
    this.nhsBookingId = nhsBookingId;
  }


  public Integer getShiftId()
  {
    return shiftId;
  }

  public Integer getAgencyId()
  {
    return agencyId;
  }

  public void setAgencyId(Integer agencyId)
  {
    this.agencyId = agencyId;
  }

  public Integer getClientId()
  {
    return clientId;
  }

  public void setClientId(Integer clientId)
  {
    this.clientId = clientId;
  }

  public void setShiftId(Integer shiftId)
  {
    this.shiftId = shiftId;
  }


  public Integer getSiteId()
  {
    return siteId;
  }


  public void setSiteId(Integer siteId)
  {
    this.siteId = siteId;
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


  public String getWard()
  {
    return ward;
  }


  public void setWard(String ward)
  {
    this.ward = ward;
  }

  public String getBookingGroupName()
  {
    if (bookingGroupId.equals(AgyConstants.WEEKDAY_EARLY) )
    {
      return AgyConstants.WEEKDAY_EARLY_NAME;
    }
    if (bookingGroupId.equals(AgyConstants.SATURDAY_OR_WEEKDAY_LATE))
    {
      return AgyConstants.SATURDAY_OR_WEEKDAY_LATE_NAME;
    }
    if (bookingGroupId.equals(AgyConstants.SUNDAY_OR_PUBLICHOLIDAY))
    {
      return AgyConstants.SUNDAY_OR_PUBLICHOLIDAY_NAME;
    }
    return AgyConstants.UNDEFINED_BOOKING_GROUP_NAME + bookingGroupId;
  }

  public boolean isValid()
  {
    return applicantId       != null &&
           shiftId           != null &&
           siteId            != null &&
           locationId        != null &&
           jobProfileId      != null &&
           bookingGroupId    != null;
           
  }

  public boolean isReadyToBook()
  {
    return bookingId == 0 && isValid();
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setNhsBookingId(rs.getInt("NHSBOOKINGID"));
    setBankReqNum(rs.getString("BANKREQNUM"));
    setStaffName(rs.getString("STAFFNAME"));
    setDate(rs.getDate("DATE"));
    setStartTime(rs.getTime("STARTTIME"));
    setEndTime(rs.getTime("ENDTIME"));
    setLocation(rs.getString("LOCATION"));
    setWard(rs.getString("WARD"));
    setAssignment(rs.getString("ASSIGNMENT"));
    setSubcontractInvoiceId(rs.getInt("SUBCONTRACTINVOICEID"));
    setValue(rs.getBigDecimal("VALUE"));
    setApplicantId(rs.getInt("APPLICANTID"));
    setAgencyId(rs.getInt("AGENCYID"));
    setClientId(rs.getInt("CLIENTID"));
    setSiteId(rs.getInt("SITEID"));
    setLocationId(rs.getInt("LOCATIONID"));
    setJobProfileId(rs.getInt("JOBPROFILEID"));
    setShiftId(rs.getInt("SHIFTID"));
    setBookingGroupId(rs.getInt("BOOKINGGROUPID"));
    setBookingId(rs.getInt("BOOKINGID"));
    setBookingDateId(rs.getInt("BOOKINGDATEID"));
    setBookingGradeId(rs.getInt("BOOKINGGRADEID"));
    setBookingTime(rs.getTimestamp("BOOKINGTIME"));
    setApplicantNotificationSent(rs.getTimestamp("APPLICANTNOTIFICATIONSENT"));
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
    text.append("bookingGroupId=");
    text.append(bookingGroupId);
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
    text.append("location=");
    text.append(location);
    text.append(",");
    text.append("ward=");
    text.append(ward);
    text.append(",");
    text.append("assignment=");
    text.append(assignment);
    if (subcontractInvoiceId != null)
    {
      text.append(",");
      text.append("subcontractInvoiceId=");
      text.append(subcontractInvoiceId);
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
    text.append("agencyId=");
    text.append(agencyId);
    if (clientId != null)
    {
      text.append(",");
      text.append("clientId=");
      text.append(clientId);
    }    
    if (siteId != null)
    {
      text.append(",");
      text.append("siteId=");
      text.append(siteId);
    }    
    if (locationId != null)
    {
      text.append(",");
      text.append("locationId=");
      text.append(locationId);
    }    
    if (jobProfileId != null)
    {
      text.append(",");
      text.append("jobProfileId=");
      text.append(jobProfileId);
    }    
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
    if (bookingTime != null)
    {
      text.append(",");
      text.append("bookingTime=");
      text.append(bookingTime);
    }    
    if (applicantNotificationSent != null)
    {
      text.append(",");
      text.append("applicantNotificationSent=");
      text.append(applicantNotificationSent);
    }    
    return text.toString();
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public String getReportGroupKey()
  {
    return clientId + "," + siteId + "," + locationId + "," + jobProfileId; 
  }

}
