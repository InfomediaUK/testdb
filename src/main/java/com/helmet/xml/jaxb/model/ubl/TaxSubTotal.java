package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "taxableAmount", "taxAmount", "taxCategory" })
public class TaxSubTotal
{
  private CurrencyAmount taxableAmount;
  private CurrencyAmount taxAmount;
  private TaxCategory taxCategory;

  @XmlElement(name = "TaxableAmount", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public CurrencyAmount getTaxableAmount()
  {
    return taxableAmount;
  }

  public void setTaxableAmount(CurrencyAmount taxableAmount)
  {
    this.taxableAmount = taxableAmount;
  }

  @XmlElement(name = "TaxAmount", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public CurrencyAmount getTaxAmount()
  {
    return taxAmount;
  }

  public void setTaxAmount(CurrencyAmount partyIdentification)
  {
    this.taxAmount = partyIdentification;
  }

  @XmlElement(name = "TaxCategory", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public TaxCategory getTaxCategory()
  {
    return taxCategory;
  }

  public void setTaxCategory(TaxCategory taxCategory)
  {
    this.taxCategory = taxCategory;
  }

}
