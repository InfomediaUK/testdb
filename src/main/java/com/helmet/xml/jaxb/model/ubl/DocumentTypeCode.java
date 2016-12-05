package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(propOrder = { "value", "listId" })
public class DocumentTypeCode
{
  private String value;
  private String listId;
  @XmlValue
  public String getValue()
  {
    return value;
  }

  public void setValue(String id)
  {
    this.value = id;
  }

  @XmlAttribute(name = "listID")
  public String getListId()
  {
    return listId;
  }

  public void setListId(String listId)
  {
    this.listId = listId;
  }


}
