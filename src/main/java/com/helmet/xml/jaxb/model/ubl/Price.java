package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "priceAmount", "baseQuantity", "orderableUnitFactorRate" })
public class Price
{
  private CurrencyAmount priceAmount;
  private Quantity baseQuantity;
  private Integer orderableUnitFactorRate;

  @XmlElement(name = "PriceAmount", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public CurrencyAmount getPriceAmount()
  {
    return priceAmount;
  }

  public void setPriceAmount(CurrencyAmount id)
  {
    this.priceAmount = id;
  }

  @XmlElement(name = "BaseQuantity", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public Quantity getBaseQuantity()
  {
    return baseQuantity;
  }

  public void setBaseQuantity(Quantity baseQuantity)
  {
    this.baseQuantity = baseQuantity;
  }

  @XmlElement(name = "OrderableUnitFactorRate", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public Integer getOrderableUnitFactorRate()
  {
    return orderableUnitFactorRate;
  }

  public void setOrderableUnitFactorRate(Integer orderableUnitFactorRate)
  {
    this.orderableUnitFactorRate = orderableUnitFactorRate;
  }


}
