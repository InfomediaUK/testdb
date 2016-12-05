package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "Identifier", propOrder = { "value", "scheme" })
public class Identifier
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
