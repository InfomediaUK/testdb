package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "percent", "taxScheme" })
public class TaxCategory
{
  private SchemeId id;
  private String percent;
  private TaxScheme taxScheme;

  @XmlElement(name = "ID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public SchemeId getId()
  {
    return id;
  }

  public void setId(SchemeId id)
  {
    this.id = id;
  }

  @XmlElement(name = "Percent", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getPercent()
  {
    return percent;
  }

  public void setPercent(String percent)
  {
    this.percent = percent;
  }

  @XmlElement(name = "TaxScheme", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public TaxScheme getTaxScheme()
  {
    return taxScheme;
  }

  public void setTaxScheme(TaxScheme taxScheme)
  {
    this.taxScheme = taxScheme;
  }


}
