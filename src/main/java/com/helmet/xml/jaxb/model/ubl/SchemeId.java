package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(propOrder = { "value", "schemeVersionId", "schemeId", "schemeAgencyId" })
public class SchemeId
{
  private String value;
  private String schemeVersionId;
  private String schemeId;
  private String schemeAgencyId;

  @XmlValue
  public String getValue()
  {
    return value;
  }

  public void setValue(String id)
  {
    this.value = id;
  }

  @XmlAttribute(name = "schemeVersionID")
  public String getSchemeVersionId()
  {
    return schemeVersionId;
  }

  public void setSchemeVersionId(String schemeVersionId)
  {
    this.schemeVersionId = schemeVersionId;
  }

  @XmlAttribute(name = "schemeID")
  public String getSchemeId()
  {
    return schemeId;
  }

  public void setSchemeId(String schemeId)
  {
    this.schemeId = schemeId;
  }

  @XmlAttribute(name = "schemeAgencyID")
  public String getSchemeAgencyId()
  {
    return schemeAgencyId;
  }

  public void setSchemeAgencyId(String schemeAgencyId)
  {
    this.schemeAgencyId = schemeAgencyId;
  }


}
