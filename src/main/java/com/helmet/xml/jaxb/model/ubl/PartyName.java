package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;

public class PartyName
{
  private String name;

  @XmlElement(name = "Name", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }


}
