package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Quantity
{
  private String value;
  private String unitCode;

  @XmlValue
  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  @XmlAttribute(name = "unitCode")
  public String getUnitCode()
  {
    return unitCode;
  }

  public void setUnitCode(String schemeId)
  {
    this.unitCode = schemeId;
  }


}
