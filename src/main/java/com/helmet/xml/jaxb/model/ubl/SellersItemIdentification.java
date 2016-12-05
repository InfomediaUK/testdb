package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id" })
public class SellersItemIdentification
{
  private SchemeId id;
  @XmlElement(name = "ID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public SchemeId getId()
  {
    return id;
  }

  public void setId(SchemeId id)
  {
    this.id = id;
  }


}
