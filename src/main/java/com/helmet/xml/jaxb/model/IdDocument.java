package com.helmet.xml.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "id", "code", "name", "idDocumentType", "requiresVisa", "displayOrder" })
public class IdDocument
{
  private Integer id;
  private String code;
  private String name;
  private Integer idDocumentType;
  private Boolean requiresVisa;
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

  public Integer getIdDocumentType()
  {
    return idDocumentType;
  }

  public void setIdDocumentType(Integer idDocumentType)
  {
    this.idDocumentType = idDocumentType;
  }

  public Boolean getRequiresVisa()
  {
    return requiresVisa;
  }

  public void setRequiresVisa(Boolean requiresVisa)
  {
    this.requiresVisa = requiresVisa;
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
