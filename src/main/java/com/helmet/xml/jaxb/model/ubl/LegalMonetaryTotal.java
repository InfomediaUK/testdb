package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "lineExtensionAmount", "taxExclusiveAmount", "taxInclusiveAmount", "payableAmount" })
public class LegalMonetaryTotal
{
  private CurrencyAmount lineExtensionAmount;
  private CurrencyAmount taxExclusiveAmount;
  private CurrencyAmount taxInclusiveAmount;
  private CurrencyAmount payableAmount;

  @XmlElement(name = "LineExtensionAmount", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public CurrencyAmount getLineExtensionAmount()
  {
    return lineExtensionAmount;
  }

  public void setLineExtensionAmount(CurrencyAmount taxableAmount)
  {
    this.lineExtensionAmount = taxableAmount;
  }

  @XmlElement(name = "TaxExclusiveAmount", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public CurrencyAmount getTaxExclusiveAmount()
  {
    return taxExclusiveAmount;
  }

  public void setTaxExclusiveAmount(CurrencyAmount partyIdentification)
  {
    this.taxExclusiveAmount = partyIdentification;
  }

  @XmlElement(name = "TaxInclusiveAmount", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public CurrencyAmount getTaxInclusiveAmount()
  {
    return taxInclusiveAmount;
  }

  public void setTaxInclusiveAmount(CurrencyAmount taxInclusiveAmount)
  {
    this.taxInclusiveAmount = taxInclusiveAmount;
  }

  @XmlElement(name = "PayableAmount", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public CurrencyAmount getPayableAmount()
  {
    return payableAmount;
  }

  public void setPayableAmount(CurrencyAmount payableAmount)
  {
    this.payableAmount = payableAmount;
  }

}
