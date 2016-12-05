package com.helmet.xml.jaxb.model.tradeshift;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.helmet.xml.jaxb.model.ubl.AddressLine;
import com.helmet.xml.jaxb.model.ubl.Identifier;

@XmlRootElement(name = "CompanyAccountInfo")
@XmlType(propOrder = { "companyName", "country", "companyAccountId", "state", "url", "description",
    "identifiers", "addressLines" })
public class CompanyAccountInfo
{
  private String companyName;
  private String country;
  private String companyAccountId;
  private String state;
  private String url;
  private String description;
  private List<Identifier> identifiers;
  private List<AddressLine> addressLines;
  
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

  @XmlElement(name = "Url")
  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  @XmlElement(name = "Description")
  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  @XmlElementWrapper(name="Identifiers")
  @XmlElement(name = "Identifier")
  public List<Identifier> getIdentifiers()
  {
    return identifiers;
  }

  public void setIdentifiers(List<Identifier> identifiers)
  {
    this.identifiers = identifiers;
  }

  @XmlElementWrapper(name="AddressLines")
  @XmlElement(name = "AddressLine")
  public List<AddressLine> getAddressLines()
  {
    return addressLines;
  }

  public void setAddressLines(List<AddressLine> addressLines)
  {
    this.addressLines = addressLines;
  }

  public String getIdentifier(String scheme, String prefix)
  {
    for (Identifier identifier : identifiers)
    {
      if (identifier.getScheme().equals(scheme))
      {
        if (identifier.getValue().contains(prefix))
        {
          return identifier.getValue();
        }
      }
    }
    return scheme + " for " + prefix + " Not Found";
  }
  
  public String getAddressLine(String scheme)
  {
    for (AddressLine addressLine : addressLines)
    {
      if (addressLine.getScheme().equals(scheme))
      {
        return addressLine.getValue();
      }
    }
    return scheme + " Not Found";
  }
  
  public String toString()
  {
    StringBuffer result = new StringBuffer();
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
    result.append("\n");
    result.append("description=");
    result.append(description);
    result.append("\n");
    result.append("identifiers");
    for (Identifier identifier : identifiers)
    {
      result.append(", ");
      result.append("[scheme=");
      result.append(identifier.getScheme());
      result.append(", ");
      result.append("value=");
      result.append(identifier.getValue());
      result.append("]");
    }
    result.append("\n");
    result.append("addressLines");
    for (AddressLine addressLine : addressLines)
    {
      result.append(", ");
      result.append("[scheme=");
      result.append(addressLine.getScheme());
      result.append(", ");
      result.append("value=");
      result.append(addressLine.getValue());
      result.append("]");
    }
    return result.toString();
  }
}
