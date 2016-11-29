package com.helmet.application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.helmet.bean.Client;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.NhsBookingUser;
import com.helmet.bean.Site;

public class NhsBookingReportGroup extends ClientSiteLocationReportGroup
{
  private Integer jobProfileId;
  private String jobFamilyName;
  private String jobFamilyCode;
  private String jobSubFamilyName;
  private String jobSubFamilyCode;
  private String jobProfileName;
  private String jobProfileCode;
  private String assignment;
  private List<NhsBookingUser> listNhsBookingUser = new ArrayList<NhsBookingUser>();;

  
  public NhsBookingReportGroup(NhsBookingUser nhsBookingUser)
  {
    super(nhsBookingUser.getClientId(), nhsBookingUser.getClientName(), nhsBookingUser.getSiteId(), nhsBookingUser.getSiteName(), nhsBookingUser.getLocationId(), nhsBookingUser.getLocationName());
    jobProfileId = nhsBookingUser.getJobProfileId();
    jobProfileName = nhsBookingUser.getJobProfileName();
    jobFamilyName = nhsBookingUser.getJobFamilyName();
    jobFamilyCode = nhsBookingUser.getJobFamilyCode();
    jobSubFamilyName = nhsBookingUser.getJobSubFamilyName();
    jobSubFamilyCode = nhsBookingUser.getJobSubFamilyCode();
    assignment = nhsBookingUser.getAssignment();
  }

  public NhsBookingReportGroup(Client client, Site site, Location location, JobProfileUser jobProfileUser)
  {
    super(client.getClientId(), client.getName(), site.getSiteId(), site.getName(), location.getLocationId(), location.getName());
    jobProfileId = jobProfileUser.getJobProfileId();
    jobProfileName = jobProfileUser.getName();
    jobFamilyName = jobProfileUser.getJobFamilyName();
    jobFamilyCode = jobProfileUser.getJobFamilyCode();
    jobSubFamilyName = jobProfileUser.getJobSubFamilyName();
    jobSubFamilyCode = jobProfileUser.getJobSubFamilyCode();
    this.assignment = jobProfileUser.getNhsAssignment();
  }
  
  public String getAssignment()
  {
    return assignment;
  }

  public void setAssignment(String assignment)
  {
    this.assignment = assignment;
  }

  public String getJobFamilyCode()
  {
    return jobFamilyCode;
  }

  public void setJobFamilyCode(String jobFamilyCode)
  {
    this.jobFamilyCode = jobFamilyCode;
  }

  public String getJobFamilyName()
  {
    return jobFamilyName;
  }

  public void setJobFamilyName(String jobFamilyName)
  {
    this.jobFamilyName = jobFamilyName;
  }

  public String getJobProfileCode()
  {
    return jobProfileCode;
  }

  public void setJobProfileCode(String jobProfileCode)
  {
    this.jobProfileCode = jobProfileCode;
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

  public String getJobSubFamilyName()
  {
    return jobSubFamilyName;
  }

  public void setJobSubFamilyName(String jobSubFamilyName)
  {
    this.jobSubFamilyName = jobSubFamilyName;
  }

  public Integer getJobProfileId()
  {
    return jobProfileId;
  }

  public void setJobProfileId(Integer gradeId)
  {
    this.jobProfileId = gradeId;
  }

  public void addNhsBookingUserToList(NhsBookingUser nhsBookingUser)
  {
    listNhsBookingUser.add(nhsBookingUser);
  }

  public List<NhsBookingUser> getListNhsBookingUser()
  {
    return listNhsBookingUser;
  }

  public String getReportGroupKey()
  {
    return super.getReportGroupKey() + "," + jobProfileId; 
  }

  public int getNoOfNhsBookings()
  {
    int noOfNhsBookings = 0;
    for (NhsBookingUser nhsBookingUser : listNhsBookingUser)
    {
      if (nhsBookingUser.getActive())
      {
        ++noOfNhsBookings;
      }
    }
    return  noOfNhsBookings; 
  }
  
  public BigDecimal getNhsBookingsToInvoiceValue()
  {
    BigDecimal nhsBookingsToInvoiceValue = new BigDecimal(0);
    for (NhsBookingUser nhsBookingUser : listNhsBookingUser)
    {
      if (nhsBookingUser.getActive())
      {
        // NHS Booking is Active...
        if (nhsBookingUser.getBookingId() != null)
        {
          // NHS Booking has actual Booking.
          if (nhsBookingUser.getSubcontractInvoiceId() <= 0)
          {
            // NHS Booking is NOT on a Subcontract Invoice (yet) or has been Credited.
            if (nhsBookingUser.getWorkedBreakNoOfHours() != null)
            {
              // Actual Worked Hours has been entered for the shift (BookingDate).
              if (nhsBookingUser.getValue().compareTo(new BigDecimal(0)) > 0)
              {
                // NHS Booking has a Value. So it can become an Invoice/Credit Note line. Accumulate it.
                nhsBookingsToInvoiceValue = nhsBookingsToInvoiceValue.add(nhsBookingUser.getValue());
              }
            }            
          }
        }
      }
    }
    return  nhsBookingsToInvoiceValue; 
  }
  
  public BigDecimal getNhsBookingsInvoicedValue()
  {
    BigDecimal nhsBookingsInvoicedValue = new BigDecimal(0);
    for (NhsBookingUser nhsBookingUser : listNhsBookingUser)
    {
      if (nhsBookingUser.getActive())
      {
        // NHS Booking is Active...
        if (nhsBookingUser.getBookingId() != null)
        {
          // NHS Booking has actual Booking.
          if (nhsBookingUser.getSubcontractInvoiceId().compareTo(new Integer(0)) > 0)
          {
            // NHS Booking is on a Subcontract Invoice.
            if (nhsBookingUser.getValue().compareTo(new BigDecimal(0)) > 0)
            {
              // NHS Booking has a Value so accumulate it.
              nhsBookingsInvoicedValue = nhsBookingsInvoicedValue.add(nhsBookingUser.getValue());
            }
          }
        }
      }
    }
    return  nhsBookingsInvoicedValue; 
  }
  
  public String toString()
  {
    StringBuffer text = new StringBuffer(super.toString());
    text.append("jobFamilyName=");
    text.append(jobFamilyName);
    text.append(",");
    text.append("jobFamilyCode=");
    text.append(jobFamilyCode);
    text.append(",");
    text.append("jobSubFamilyName=");
    text.append(jobSubFamilyName);
    text.append(",");
    text.append("jobSubFamilyCode=");
    text.append(jobSubFamilyCode);
    text.append(",");
    text.append("jobProfileName=");
    text.append(jobProfileName);
    text.append(",");
    text.append("jobProfileCode=");
    text.append(jobProfileCode);
    text.append(",");
    text.append("assignment=");
    text.append(assignment);
    return text.toString();
  }

}
