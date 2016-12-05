package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "description", "name", "sellersItemIdentification" })
public class Item
{
  private String description;
  private String name;
  private SellersItemIdentification sellersItemIdentification;

  @XmlElement(name = "Description", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getDescription()
  {
    return description;
  }

  public void setDescription(String name)
  {
    this.description = name;
  }

  @XmlElement(name = "Name", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @XmlElement(name = "SellersItemIdentification", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public SellersItemIdentification getSellersItemIdentification()
  {
    return sellersItemIdentification;
  }

  public void setSellersItemIdentification(SellersItemIdentification sellersItemIdentification)
  {
    this.sellersItemIdentification = sellersItemIdentification;
  }


}
