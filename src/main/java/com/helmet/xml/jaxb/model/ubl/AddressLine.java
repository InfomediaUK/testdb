package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "AddressLine", propOrder = { "value", "scheme" })
public class AddressLine
{
  private String value;
  private String scheme;

  @XmlValue
  public String getValue()
  {
    return value;
  }

  public void setValue(String id)
  {
    this.value = id;
  }

  @XmlAttribute(name = "scheme")
  public String getScheme()
  {
    return scheme;
  }

  public void setScheme(String schemeId)
  {
    this.scheme = schemeId;
  }

}
