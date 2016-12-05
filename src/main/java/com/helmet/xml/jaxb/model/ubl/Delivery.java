package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;

public class Delivery
{
  private String actualDeliveryDate;

  @XmlElement(name = "ActualDeliveryDate", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getActualDeliveryDate()
  {
    return actualDeliveryDate;
  }

  public void setActualDeliveryDate(String name)
  {
    this.actualDeliveryDate = name;
  }


}
