package com.helmet.xml.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "id", "code", "name", "displayOrder" })
public class PassportType
{
  private Integer id;
  private String code;
  private String name;
  private Integer displayOrder;

  public Integer getId()
  {
    return id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }

  public Integer getDisplayOrder()
  {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder)
  {
    this.displayOrder = displayOrder;
  }

  @Override
  public String toString()
  {
    return String.format("%s %s", code, name);
  }

}
