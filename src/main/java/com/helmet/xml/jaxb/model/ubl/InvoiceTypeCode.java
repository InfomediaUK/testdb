package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(propOrder = { "value", "listVersionId", "listId", "listAgencyId" })
public class InvoiceTypeCode
{
  private String value;
  private String listVersionId;
  private String listId;
  private String listAgencyId;

  @XmlValue
  public String getValue()
  {
    return value;
  }

  public void setValue(String id)
  {
    this.value = id;
  }

  @XmlAttribute(name = "listVersionID")
  public String getListVersionId()
  {
    return listVersionId;
  }

  public void setListVersionId(String listVersionId)
  {
    this.listVersionId = listVersionId;
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

  @XmlAttribute(name = "listAgencyID")
  public String getListAgencyId()
  {
    return listAgencyId;
  }

  public void setListAgencyId(String listAgencyId)
  {
    this.listAgencyId = listAgencyId;
  }


}
