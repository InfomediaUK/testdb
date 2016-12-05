package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;

public class Country
{
  private String identificationCode;

  @XmlElement(name = "IdentificationCode", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getIdentificationCode()
  {
    return identificationCode;
  }

  public void setIdentificationCode(String name)
  {
    this.identificationCode = name;
  }


}
