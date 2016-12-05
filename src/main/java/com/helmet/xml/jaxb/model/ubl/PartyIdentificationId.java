package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class PartyIdentificationId
{
  private String value;
  private String schemeId;

  @XmlValue
  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  @XmlAttribute(name = "schemeID")
  public String getSchemeId()
  {
    return schemeId;
  }

  public void setSchemeId(String schemeId)
  {
    this.schemeId = schemeId;
  }


}
