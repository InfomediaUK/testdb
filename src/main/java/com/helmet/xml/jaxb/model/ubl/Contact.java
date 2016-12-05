package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = { "id", "name", "telephone", "electronicMail" })
public class Contact
{
  private String id;
  private String name;
  private String telephone;
  private String electronicMail;

  @XmlElement(name = "ID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
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

  @XmlElement(name = "Telephone", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getTelephone()
  {
    return telephone;
  }

  public void setTelephone(String telephone)
  {
    this.telephone = telephone;
  }

  @XmlElement(name = "ElectronicMail", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getElectronicMail()
  {
    return electronicMail;
  }

  public void setElectronicMail(String electronicMail)
  {
    this.electronicMail = electronicMail;
  }


}
