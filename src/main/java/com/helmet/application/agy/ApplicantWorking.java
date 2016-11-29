package com.helmet.application.agy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ApplicantWorking
{
  private Integer applicantId;
  private Integer agencyId;
  private String firstName;
  private String lastName;
  private List<ApplicantWorkingDate> listWorkingDate;

  
  public ApplicantWorking(Integer applicantId, Integer agencyId, String firstName, String lastName, Date dateStart, Date dateEnd)
  {
    super();
    this.applicantId = applicantId;
    this.agencyId = agencyId;
    this.firstName = firstName;
    this.lastName = lastName;
    Long millisecondsInDay = new Long(60 * 60 * 24 * 1000);
    listWorkingDate = new ArrayList<ApplicantWorkingDate>();
    Date day = dateStart;
    while (!day.after(dateEnd))
    {
      listWorkingDate.add(new ApplicantWorkingDate(day));
      day = new Date(day.getTime() + millisecondsInDay);
    }
  }

  public Integer getApplicantId()
  {
    return applicantId;
  }

  public void setApplicantId(Integer applicantId)
  {
    this.applicantId = applicantId;
  }
  
  public Integer getAgencyId()
  {
    return agencyId;
  }
  
  public void setAgencyId(Integer agencyId)
  {
    this.agencyId = agencyId;
  }
  
  public String getFirstName()
  {
    return firstName;
  }
  
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }
  
  public String getLastName()
  {
    return lastName;
  }
  
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public List<ApplicantWorkingDate> getListWorkingDate()
  {
    return listWorkingDate;
  }
 
  public void setFinishedPeriodForDate(int index, Integer finishedPeriod)
  {
    if (index < listWorkingDate.size())
    {
      // The specified index is in range 0 - (size - 1). 
      ApplicantWorkingDate applicantWorkingDate = listWorkingDate.get(index);
      if (finishedPeriod > applicantWorkingDate.getFinishedPeriod())
      {
        applicantWorkingDate.setFinishedPeriod(finishedPeriod);
      }      
    }    
  }

  public void setWorkedStatusForDate(int index, Integer workedStatus)
  {
    if (index < listWorkingDate.size())
    {
      // The specified index is in range 0 - (size - 1). 
      ApplicantWorkingDate applicantWorkingDate = listWorkingDate.get(index);
      applicantWorkingDate.setWorkedStatus(workedStatus);
    }    
  }
  
  public void setUnavailableForDate(int index)
  {
    if (index < listWorkingDate.size())
    {
      // The specified index is in range 0 - (size - 1). 
      ApplicantWorkingDate applicantWorkingDate = listWorkingDate.get(index);
      applicantWorkingDate.setUnavailable(true);
    }    
  }
  
  public void updateHoursScheduled(int index, BigDecimal shiftNoOfHours)
  {
    if (index < listWorkingDate.size())
    {
      // The specified index is in range 0 - (size - 1). 
      ApplicantWorkingDate applicantWorkingDate = listWorkingDate.get(index);
      applicantWorkingDate.updateHoursScheduled(shiftNoOfHours);
    }    
  }
  
  public void updateHoursWorked(int index, BigDecimal workedNoOfHours)
  {
    if (index < listWorkingDate.size())
    {
      // The specified index is in range 0 - (size - 1). 
      ApplicantWorkingDate applicantWorkingDate = listWorkingDate.get(index);
      applicantWorkingDate.updateHoursWorked(workedNoOfHours);
    }    
  }

  public Boolean hasBooking()
  {
    BigDecimal zero = new BigDecimal(0);
    for (ApplicantWorkingDate applicantWorkingDate : listWorkingDate)
    {
      if (applicantWorkingDate.getHoursScheduled().equals(zero) && applicantWorkingDate.getHoursWorked().equals(zero))
      {
        continue;
      }
      else
      {
        // Applicant has at least one Booking... 
        return new Boolean(true);
      }
    }
    return new Boolean(false);
  }
  
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("ApplicantWorking\n");
    sb.append("applicantId: " + applicantId + "\n");
    sb.append("agencyId: " + agencyId + "\n");
    sb.append("firstName: " + firstName + "\n");
    sb.append("lastName: " + lastName + "\n");
    sb.append("listWorkingDate.size(): " + listWorkingDate.size() + "\n");
    return sb.toString();
  }
}
