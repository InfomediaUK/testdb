package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = { "id", "invoicedQuantity", "lineExtensionAmount", "taxTotal", "item", "price" })
public class InvoiceLine
{
  private String id;
  private Quantity invoicedQuantity;
  private CurrencyAmount lineExtensionAmount;
  private TaxTotal taxTotal;
  private Item item;
  private Price price;

  @XmlElement(name = "ID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @XmlElement(name = "InvoicedQuantity", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public Quantity getInvoicedQuantity()
  {
    return invoicedQuantity;
  }

  public void setInvoicedQuantity(Quantity invoicedQuantity)
  {
    this.invoicedQuantity = invoicedQuantity;
  }

  @XmlElement(name = "LineExtensionAmount", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public CurrencyAmount getLineExtensionAmount()
  {
    return lineExtensionAmount;
  }

  public void setLineExtensionAmount(CurrencyAmount taxableAmount)
  {
    this.lineExtensionAmount = taxableAmount;
  }

  @XmlElement(name = "TaxTotal", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public TaxTotal getTaxTotal()
  {
    return taxTotal;
  }

  public void setTaxTotal(TaxTotal taxTotal)
  {
    this.taxTotal = taxTotal;
  }

  @XmlElement(name = "Item", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public Item getItem()
  {
    return item;
  }

  public void setItem(Item item)
  {
    this.item = item;
  }

  @XmlElement(name = "Price", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public Price getPrice()
  {
    return price;
  }

  public void setPrice(Price price)
  {
    this.price = price;
  }


}
