package com.helmet.application;

public class ClientSiteLocationReportGroup
{
  private Integer clientId;
  private Integer siteId;
  private Integer locationId;
  private String clientName;
  private String siteName;
  private String locationName;

  
  public ClientSiteLocationReportGroup(Integer  clientId, String clientName, Integer  siteId, String siteName, Integer  locationId, String locationName)
  {
    super();
    this.clientId = clientId;
    this.clientName = clientName;
    this.siteId = siteId;
    this.siteName = siteName;
    this.locationId = locationId;
    this.locationName = locationName;
  }


  public String getClientName()
  {
    return clientName;
  }


  public void setClientName(String clientName)
  {
    this.clientName = clientName;
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


  public Integer getLocationId()
  {
    return locationId;
  }


  public void setLocationId(Integer locationId)
  {
    this.locationId = locationId;
  }


  public Integer getClientId()
  {
    return clientId;
  }

  public void setClientId(Integer clientId)
  {
    this.clientId = clientId;
  }

  public Integer getSiteId()
  {
    return siteId;
  }


  public void setSiteId(Integer siteId)
  {
    this.siteId = siteId;
  }

  public String getReportGroupKey()
  {
    return clientId + "," + siteId + "," + locationId; 
  }

  public String getReportGroupName()
  {
    return clientName + "," + siteName + "," + locationName; 
  }

  public String toString()
  {
    StringBuffer text = new StringBuffer();
    text.append("clientName=");
    text.append(clientName);
    text.append(",");
    text.append("siteName=");
    text.append(siteName);
    text.append(",");
    text.append("locationName=");
    text.append(locationName);
    return text.toString();
  }

}
