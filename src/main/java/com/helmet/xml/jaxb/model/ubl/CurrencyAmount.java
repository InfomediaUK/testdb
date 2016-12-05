package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class CurrencyAmount
{
  private String value;
  private String currencyId;

  @XmlValue
  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  @XmlAttribute(name = "currencyID")
  public String getCurrencyId()
  {
    return currencyId;
  }

  public void setCurrencyId(String schemeId)
  {
    this.currencyId = schemeId;
  }


}
