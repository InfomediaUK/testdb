package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = { "id", "name" })
public class TaxScheme
{
  private SchemeId id;
  private String name;

  @XmlElement(name = "ID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public SchemeId getId()
  {
    return id;
  }

  public void setId(SchemeId id)
  {
    this.id = id;
  }

  @XmlElement(name = "Name", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getName()
  {
    return name;
  }

  public void setName(String schemeId)
  {
    this.name = schemeId;
  }


}
