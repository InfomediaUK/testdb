package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "taxAmount", "taxSubTotal" })
public class TaxTotal
{
  private CurrencyAmount currencyAmount;
  private TaxSubTotal taxSubTotal;

  @XmlElement(name = "TaxAmount", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public CurrencyAmount getTaxAmount()
  {
    return currencyAmount;
  }

  public void setTaxAmount(CurrencyAmount currencyAmount)
  {
    this.currencyAmount = currencyAmount;
  }

  @XmlElement(name = "TaxSubtotal", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public TaxSubTotal getTaxSubTotal()
  {
    return taxSubTotal;
  }

  public void setTaxSubTotal(TaxSubTotal taxSubTotal)
  {
    this.taxSubTotal = taxSubTotal;
  }

}
