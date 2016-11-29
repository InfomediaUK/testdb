package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class NhsBookingUser extends NhsBooking
{
  private String applicantFirstName;
  private String applicantLastName;
  private String applicantEmailAddress;
  private Integer applicantOriginalAgencyId;
  private String shiftName;
  private String clientName;
  private String siteName;
  private String locationName;
  private String jobFamilyName;
  private String jobFamilyCode;
  private String jobSubFamilyName;
  private String jobSubFamilyCode;
  private String jobProfileName;
  private String jobProfileCode;
  private Time workedStartTime;
  private Time workedEndTime;
  private Time workedBreakStartTime;
  private Time workedBreakEndTime;
  private BigDecimal workedNoOfHours;
  private BigDecimal workedBreakNoOfHours;
  private Date subcontractInvoiceSentDate;
  private Date subcontractInvoicePaidDate;
  private BigDecimal rate;
  private String backingReport;
  
  public String getApplicantEmailAddress()
  {
    return applicantEmailAddress;
  }


  public void setApplicantEmailAddress(String applicantEmailAddress)
  {
    this.applicantEmailAddress = applicantEmailAddress;
  }


  public String getApplicantFirstName()
  {
    return applicantFirstName;
  }


  public void setApplicantFirstName(String applicantFirstName)
  {
    this.applicantFirstName = applicantFirstName;
  }


  public String getApplicantLastName()
  {
    return applicantLastName;
  }


  public void setApplicantLastName(String applicantLastName)
  {
    this.applicantLastName = applicantLastName;
  }

  public String getApplicantFullName()
  {
    return applicantFirstName + " " + applicantLastName;
  }

  public String getApplicantFullNameLastNameFirst()
  {
    return applicantLastName + " " + applicantFirstName;
  }

  public Integer getApplicantOriginalAgencyId()
  {
    return applicantOriginalAgencyId;
  }


  public void setApplicantOriginalAgencyId(Integer applicantOriginalAgencyId)
  {
    this.applicantOriginalAgencyId = applicantOriginalAgencyId;
  }


  public String getClientName()
  {
    return clientName;
  }


  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }


  public String getJobFamilyName()
  {
    return jobFamilyName;
  }


  public void setJobFamilyName(String jobFamilyName)
  {
    this.jobFamilyName = jobFamilyName;
  }


  public String getJobProfileName()
  {
    return jobProfileName;
  }


  public void setJobProfileName(String jobProfileName)
  {
    this.jobProfileName = jobProfileName;
  }


  public String getJobSubFamilyName()
  {
    return jobSubFamilyName;
  }


  public void setJobSubFamilyName(String jobSubFamilyName)
  {
    this.jobSubFamilyName = jobSubFamilyName;
  }


  public String getLocationName()
  {
    return locationName;
  }


  public void setLocationName(String locationName)
  {
    this.locationName = locationName;
  }


  public String getShiftName()
  {
    return shiftName;
  }


  public void setShiftName(String shiftName)
  {
    this.shiftName = shiftName;
  }


  public String getSiteName()
  {
    return siteName;
  }


  public void setSiteName(String siteName)
  {
    this.siteName = siteName;
  }

  public String getJobFamilyCode()
  {
    return jobFamilyCode;
  }


  public void setJobFamilyCode(String jobFamilyCode)
  {
    this.jobFamilyCode = jobFamilyCode;
  }


  public String getJobProfileCode()
  {
    return jobProfileCode;
  }


  public void setJobProfileCode(String jobProfileCode)
  {
    this.jobProfileCode = jobProfileCode;
  }


  public String getJobSubFamilyCode()
  {
    return jobSubFamilyCode;
  }


  public void setJobSubFamilyCode(String jobSubFamilyCode)
  {
    this.jobSubFamilyCode = jobSubFamilyCode;
  }


  public Time getWorkedStartTime()
  {
    return workedStartTime;
  }


  public void setWorkedStartTime(Time workedStartTime)
  {
    this.workedStartTime = workedStartTime;
  }


  public Time getWorkedEndTime()
  {
    return workedEndTime;
  }


  public void setWorkedEndTime(Time workedEndTime)
  {
    this.workedEndTime = workedEndTime;
  }


  public BigDecimal getWorkedNoOfHours()
  {
    return workedNoOfHours;
  }


  public void setWorkedNoOfHours(BigDecimal workedNoOfHours)
  {
    this.workedNoOfHours = workedNoOfHours;
  }


  public Time getWorkedBreakStartTime()
  {
    return workedBreakStartTime;
  }


  public void setWorkedBreakStartTime(Time workedBreakStartTime)
  {
    this.workedBreakStartTime = workedBreakStartTime;
  }


  public Time getWorkedBreakEndTime()
  {
    return workedBreakEndTime;
  }


  public void setWorkedBreakEndTime(Time workedBreakEndTime)
  {
    this.workedBreakEndTime = workedBreakEndTime;
  }


  public BigDecimal getWorkedBreakNoOfHours()
  {
    return workedBreakNoOfHours;
  }


  public void setWorkedBreakNoOfHours(BigDecimal workedBreakNoOfHours)
  {
    this.workedBreakNoOfHours = workedBreakNoOfHours;
  }


  public Date getSubcontractInvoicePaidDate()
  {
    return subcontractInvoicePaidDate;
  }


  public void setSubcontractInvoicePaidDate(Date subcontractInvoicePaidDate)
  {
    this.subcontractInvoicePaidDate = subcontractInvoicePaidDate;
  }


  public Date getSubcontractInvoiceSentDate()
  {
    return subcontractInvoiceSentDate;
  }


  public void setSubcontractInvoiceSentDate(Date subcontractInvoiceSentDate)
  {
    this.subcontractInvoiceSentDate = subcontractInvoiceSentDate;
  }

  public BigDecimal getRate()
  {
    return rate;
  }

  public void setRate(BigDecimal rate)
  {
    this.rate = rate;
  }

  public String getBackingReport()
  {
    return backingReport;
  }


  public void setBackingReport(String backingReport)
  {
    this.backingReport = backingReport;
  }


  public boolean getNhsStaffNameMatchedApplicant()
  {
    return getStaffName().equals(getApplicantLastName() + " " + getApplicantFirstName());
  }

  public boolean getNhsLocationMatchedSite()
  {
    return getLocation().equals(getSiteName());
  }

  public boolean getNhsWardMatchedLocation()
  {
    return getWard().equals(getLocationName());
  }

  public boolean getNhsStartAndEndMatchedShift()
  {
    return getShiftName() != null;
  }

  public boolean getAllMatched()
  {
    return getNhsStaffNameMatchedApplicant() && 
           getNhsLocationMatchedSite() && 
           getNhsWardMatchedLocation() && 
           getNhsStartAndEndMatchedShift();
  }

  public Boolean getWorkedDiffersFromBooked()
  {
    if (workedStartTime == null || workedEndTime == null)
    {
      // Worked times not available so they must differ...
    }
    else
    {
      // Worked times available...
      if (workedStartTime.equals(getStartTime()) && workedEndTime.equals(getEndTime()))
      {
        // Worked times equal booked times. No Difference.
        return false;
      }
    }
    return true;
  }
  
  public String getStatus()
  {
    String status = "";
    if (workedNoOfHours != null && workedNoOfHours.compareTo(new BigDecimal(0)) > 0)
    {
      status = "W";
    }
    if (subcontractInvoiceSentDate != null)
    {
      status = "S";
    }
    if (subcontractInvoicePaidDate != null)
    {
      status = "P";
    }
    return status;
  }
  
  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setApplicantFirstName(rs.getString("APPLICANTFIRSTNAME"));
    setApplicantLastName(rs.getString("APPLICANTLASTNAME"));
    setApplicantEmailAddress(rs.getString("APPLICANTEMAILADDRESS"));
    setApplicantOriginalAgencyId(rs.getInt("APPLICANTORIGINALAGENCYID"));
    setShiftName(rs.getString("SHIFTNAME"));
    setClientName(rs.getString("CLIENTNAME"));
    setSiteName(rs.getString("SITENAME"));
    setLocationName(rs.getString("LOCATIONNAME"));
    setJobFamilyName(rs.getString("JOBFAMILYNAME"));
    setJobSubFamilyName(rs.getString("JOBSUBFAMILYNAME"));
    setJobProfileName(rs.getString("JOBPROFILENAME"));
    setJobFamilyCode(rs.getString("JOBFAMILYCODE"));
    setJobSubFamilyCode(rs.getString("JOBSUBFAMILYCODE"));
    setJobProfileCode(rs.getString("JOBPROFILECODE"));
    setWorkedStartTime(rs.getTime("WORKEDSTARTTIME"));
    setWorkedEndTime(rs.getTime("WORKEDENDTIME"));
    setWorkedBreakStartTime(rs.getTime("WORKEDBREAKSTARTTIME"));
    setWorkedBreakEndTime(rs.getTime("WORKEDBREAKENDTIME"));
    setWorkedNoOfHours(rs.getBigDecimal("WORKEDNOOFHOURS"));
    setWorkedBreakNoOfHours(rs.getBigDecimal("WORKEDBREAKNOOFHOURS"));
    setBackingReport(rs.getString("BACKINGREPORT"));
    setSubcontractInvoiceSentDate(rs.getDate("SUBCONTRACTINVOICESENTDATE"));
    setSubcontractInvoicePaidDate(rs.getDate("SUBCONTRACTINVOICEPAIDDATE"));
    setRate(rs.getBigDecimal("RATE"));
  }

  public String toString()
  {
    StringBuffer text = new StringBuffer(super.toString());
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
    text.append(",");
    text.append("clientName=");
    text.append(clientName);
    text.append(",");
    text.append("siteName=");
    text.append(siteName);
    text.append(",");
    text.append("locationName=");
    text.append(locationName);
    text.append(",");
    text.append("jobProfileName=");
    text.append(jobProfileName);
    text.append(",");
    text.append("subcontractInvoiceSentDate=");
    text.append(subcontractInvoiceSentDate == null ? "NULL" : formatDate.format(subcontractInvoiceSentDate));
    text.append(",");
    text.append("subcontractInvoicePaidDate=");
    text.append(subcontractInvoicePaidDate == null ? "NULL" : formatDate.format(subcontractInvoicePaidDate));
    return text.toString();
  }
}
