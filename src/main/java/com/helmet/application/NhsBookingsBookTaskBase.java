package com.helmet.application;

public class NhsBookingsBookTaskBase
{
  private String clientName;
  private String siteName;
  private String locationName;
  private String jobProfileName;

  public NhsBookingsBookTaskBase(String clientName, String siteName, String locationName, String jobProfileName)
  {
    super();
    this.clientName = clientName;
    this.siteName = siteName;
    this.locationName = locationName;
    this.jobProfileName = jobProfileName;
  }

  public String getClientName()
  {
    return clientName;
  }

  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }

  public String getJobProfileName()
  {
    return jobProfileName;
  }

  public void setJobProfileName(String jobProfileName)
  {
    this.jobProfileName = jobProfileName;
  }

  public String getLocationName()
  {
    return locationName;
  }

  public void setLocationName(String locationName)
  {
    this.locationName = locationName;
  }

  public String getSiteName()
  {
    return siteName;
  }

  public void setSiteName(String siteName)
  {
    this.siteName = siteName;
  }

}
