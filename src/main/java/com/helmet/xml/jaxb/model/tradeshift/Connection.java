package com.helmet.xml.jaxb.model.tradeshift;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement()
@XmlType(propOrder = { "connectionId", "connectionType", "companyName", "country", "companyAccountId", "state" })
public class Connection
{
  private String connectionId;
  private String connectionType;
  private String companyName;
  private String country;
  private String companyAccountId;
  private String state;
  
  @XmlElement(name = "ConnectionId")
  public String getConnectionId()
  {
    return connectionId;
  }

  public void setConnectionId(String connectionId)
  {
    this.connectionId = connectionId;
  }

  @XmlElement(name = "ConnectionType")
  public String getConnectionType()
  {
    return connectionType;
  }

  public void setConnectionType(String connectionType)
  {
    this.connectionType = connectionType;
  }

  @XmlElement(name = "CompanyName")
  public String getCompanyName()
  {
    return companyName;
  }

  public void setCompanyName(String ublVersionId)
  {
    this.companyName = ublVersionId;
  }

  @XmlElement(name = "Country")
  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  @XmlElement(name = "CompanyAccountId")
  public String getCompanyAccountId()
  {
    return companyAccountId;
  }

  public void setCompanyAccountId(String companyAccountId)
  {
    this.companyAccountId = companyAccountId;
  }

  @XmlElement(name = "State")
  public String getState()
  {
    return state;
  }

  public void setState(String state)
  {
    this.state = state;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("connectionId=");
    result.append(connectionId);
    result.append("\n");
    result.append("connectionType=");
    result.append(connectionType);
    result.append("\n");
    result.append("companyName=");
    result.append(companyName);
    result.append("\n");
    result.append("country=");
    result.append(country);
    result.append("\n");
    result.append("companyAccountId=");
    result.append(companyAccountId);
    result.append("\n");
    result.append("state=");
    result.append(state);
    return result.toString();
  }
}
