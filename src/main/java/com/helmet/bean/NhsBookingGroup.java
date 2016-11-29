package com.helmet.bean;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.agy.AgyConstants;

public class NhsBookingGroup extends Base
{
  private Integer clientId;
  private String clientName;
  private Integer siteId;
  private String siteName;
  private Integer locationId;
  private String locationName;
  private String assignment;
  private Integer jobProfileId;
  private String jobProfileName;
  private String jobFamilyCode;
  private String jobSubFamilyCode;
  private Integer bookingGroupId;
  private Integer noOfBookings;
  private Date earliestDate;
  private Date latestDate;
  private Time earliestStartTime;
  private Time latestEndTime;


  public String getAssignment()
  {
    return assignment;
  }


  public void setAssignment(String assignment)
  {
    this.assignment = assignment;
  }


  public Integer getClientId()
  {
    return clientId;
  }


  public void setClientId(Integer clientId)
  {
    this.clientId = clientId;
  }


  public String getClientName()
  {
    return clientName;
  }


  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }


  public Integer getSiteId()
  {
    return siteId;
  }


  public void setSiteId(Integer siteId)
  {
    this.siteId = siteId;
  }


  public Integer getLocationId()
  {
    return locationId;
  }


  public void setLocationId(Integer locationId)
  {
    this.locationId = locationId;
  }


  public Integer getJobProfileId()
  {
    return jobProfileId;
  }


  public void setJobProfileId(Integer gradeId)
  {
    this.jobProfileId = gradeId;
  }


  public Integer getBookingGroupId()
  {
    return bookingGroupId;
  }


  public void setBookingGroupId(Integer bookingGroupId)
  {
    this.bookingGroupId = bookingGroupId;
  }


  public Date getEarliestDate()
  {
    return earliestDate;
  }


  public void setEarliestDate(Date earliestDate)
  {
    this.earliestDate = earliestDate;
  }


  public Time getEarliestStartTime()
  {
    return earliestStartTime;
  }


  public void setEarliestStartTime(Time earliestStartTime)
  {
    this.earliestStartTime = earliestStartTime;
  }


  public String getJobFamilyCode()
  {
    return jobFamilyCode;
  }


  public void setJobFamilyCode(String jobFamilyCode)
  {
    this.jobFamilyCode = jobFamilyCode;
  }


  public String getJobProfileName()
  {
    return jobProfileName;
  }


  public void setJobProfileName(String jobProfileName)
  {
    this.jobProfileName = jobProfileName;
  }


  public String getJobSubFamilyCode()
  {
    return jobSubFamilyCode;
  }


  public void setJobSubFamilyCode(String jobSubFamilyCode)
  {
    this.jobSubFamilyCode = jobSubFamilyCode;
  }


  public Date getLatestDate()
  {
    return latestDate;
  }


  public void setLatestDate(Date latestDate)
  {
    this.latestDate = latestDate;
  }


  public Time getLatestEndTime()
  {
    return latestEndTime;
  }


  public void setLatestEndTime(Time latestEndTime)
  {
    this.latestEndTime = latestEndTime;
  }


  public String getLocationName()
  {
    return locationName;
  }


  public void setLocationName(String locationName)
  {
    this.locationName = locationName;
  }


  public Integer getNoOfBookings()
  {
    return noOfBookings;
  }


  public void setNoOfBookings(Integer noOfBookings)
  {
    this.noOfBookings = noOfBookings;
  }


  public String getSiteName()
  {
    return siteName;
  }


  public void setSiteName(String siteName)
  {
    this.siteName = siteName;
  }

  public boolean getSingleDate()
  {
    return earliestDate.equals(latestDate);
  }
  
  public String getNhsBookingGroupIds()
  {
    StringBuffer nhsBookingGroupIds = new StringBuffer();
    nhsBookingGroupIds.append(clientId);
    nhsBookingGroupIds.append(',');
    nhsBookingGroupIds.append(siteId);
    nhsBookingGroupIds.append(',');
    nhsBookingGroupIds.append(locationId);
    nhsBookingGroupIds.append(',');
    nhsBookingGroupIds.append(jobProfileId);
    nhsBookingGroupIds.append(',');
    nhsBookingGroupIds.append(bookingGroupId);
    return nhsBookingGroupIds.toString();
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

  public void load(SqlRowSet rs)
  {
    // super.load(rs); DELIBERATLY OMITTED. THIS IS NOT A DB TABLE AND SO HAS NO BASE COLUMNS... LYNDON
    setClientId(rs.getInt("CLIENTID"));
    setSiteId(rs.getInt("SITEID"));
    setLocationId(rs.getInt("LOCATIONID"));
    setAssignment(rs.getString("ASSIGNMENT"));
    setJobProfileId(rs.getInt("JOBPROFILEID"));
    setBookingGroupId(rs.getInt("BOOKINGGROUPID"));
    setClientName(rs.getString("CLIENTNAME"));
    setSiteName(rs.getString("SITENAME"));
    setLocationName(rs.getString("LOCATIONNAME"));
    setJobProfileName(rs.getString("JOBPROFILENAME"));
    setJobFamilyCode(rs.getString("JOBFAMILYCODE"));
    setJobSubFamilyCode(rs.getString("JOBSUBFAMILYCODE"));
    setEarliestDate(rs.getDate("EARLIESTDATE"));
    setLatestDate(rs.getDate("LATESTDATE"));
    setEarliestStartTime(rs.getTime("EARLIESTSTARTTIME"));
    setLatestEndTime(rs.getTime("LATESTENDTIME"));
    setNoOfBookings(rs.getInt("NOOFBOOKINGS"));
  }

  public String toString()
  {
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
    formatDate.setLenient(false);
    formatTime.setLenient(false);
    StringBuffer text = new StringBuffer();
    text.append("Group=");
    text.append(bookingGroupId);
    text.append(",");
    text.append("Earliest Date=");
    text.append(formatDate.format(earliestDate));
    text.append(",");
    text.append("Latest Date=");
    text.append(formatDate.format(latestDate));
    text.append(",");
    text.append("Earliest Start Time=");
    text.append(formatTime.format(earliestStartTime));
    text.append(",");
    text.append("Latest End Time=");
    text.append(formatTime.format(latestEndTime));
    return text.toString();
  }

}
