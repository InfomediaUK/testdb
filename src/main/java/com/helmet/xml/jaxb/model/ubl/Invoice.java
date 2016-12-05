package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Invoice")
@XmlType(propOrder = { "ublVersionId", "customizationId", "profileId", "id", "issueDate",
    "invoiceTypeCode", "note", "taxPointDate", "documentCurrencyCode", "additionalDocumentReference",
    "accountingSupplierParty", "accountingCustomerParty", "delivery", "taxTotal",
    "legalMonetaryTotal", "invoiceLine" })
public class Invoice
{
  private String ublVersionId;
  private String customizationId;
  private SchemeId profileId;
  private String id;
  private String issueDate;
  private InvoiceTypeCode invoiceTypeCode;
  private String note;
  private String taxPointDate;
  private String documentCurrencyCode;
  private AdditionalDocumentReference additionalDocumentReference;
  private AccountingParty accountingSupplierParty;
  private AccountingParty accountingCustomerParty;
  private Delivery delivery;
  private TaxTotal taxTotal;
  private LegalMonetaryTotal legalMonetaryTotal;
  private InvoiceLine invoiceLine;

  @XmlElement(name = "UBLVersionID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getUblVersionId()
  {
    return ublVersionId;
  }

  public void setUblVersionId(String ublVersionId)
  {
    this.ublVersionId = ublVersionId;
  }

  @XmlElement(name = "CustomizationID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getCustomizationId()
  {
    return customizationId;
  }

  public void setCustomizationId(String customizationId)
  {
    this.customizationId = customizationId;
  }

  @XmlElement(name = "ProfileID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public SchemeId getProfileId()
  {
    return profileId;
  }

  public void setProfileId(SchemeId profileId)
  {
    this.profileId = profileId;
  }

  @XmlElement(name = "ID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @XmlElement(name = "IssueDate", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getIssueDate()
  {
    return issueDate;
  }

  public void setIssueDate(String issueDate)
  {
    this.issueDate = issueDate;
  }

  @XmlElement(name = "InvoiceTypeCode", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public InvoiceTypeCode getInvoiceTypeCode()
  {
    return invoiceTypeCode;
  }

  public void setInvoiceTypeCode(InvoiceTypeCode invoiceTypeCode)
  {
    this.invoiceTypeCode = invoiceTypeCode;
  }

  @XmlElement(name = "Note", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getNote()
  {
    return note;
  }

  public void setNote(String note)
  {
    this.note = note;
  }

  @XmlElement(name = "TaxPointDate", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getTaxPointDate()
  {
    return taxPointDate;
  }

  public void setTaxPointDate(String taxPointDate)
  {
    this.taxPointDate = taxPointDate;
  }

  @XmlElement(name = "DocumentCurrencyCode", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getDocumentCurrencyCode()
  {
    return documentCurrencyCode;
  }

  public void setDocumentCurrencyCode(String documentCurrencyCode)
  {
    this.documentCurrencyCode = documentCurrencyCode;
  }

  @XmlElement(name = "AdditionalDocumentReference", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public AdditionalDocumentReference getAdditionalDocumentReference()
  {
    return additionalDocumentReference;
  }

  public void setAdditionalDocumentReference(AdditionalDocumentReference additionalDocumentReference)
  {
    this.additionalDocumentReference = additionalDocumentReference;
  }

  @XmlElement(name = "AccountingSupplierParty", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public AccountingParty getAccountingSupplierParty()
  {
    return accountingSupplierParty;
  }

  public void setAccountingSupplierParty(AccountingParty accountingSupplierParty)
  {
    this.accountingSupplierParty = accountingSupplierParty;
  }

  @XmlElement(name = "AccountingCustomerParty", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public AccountingParty getAccountingCustomerParty()
  {
    return accountingCustomerParty;
  }

  public void setAccountingCustomerParty(AccountingParty accountingCustomerParty)
  {
    this.accountingCustomerParty = accountingCustomerParty;
  }

  @XmlElement(name = "Delivery", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public Delivery getDelivery()
  {
    return delivery;
  }

  public void setDelivery(Delivery delivery)
  {
    this.delivery = delivery;
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

  @XmlElement(name = "LegalMonetaryTotal", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public LegalMonetaryTotal getLegalMonetaryTotal()
  {
    return legalMonetaryTotal;
  }

  public void setLegalMonetaryTotal(LegalMonetaryTotal legalMonetaryTotal)
  {
    this.legalMonetaryTotal = legalMonetaryTotal;
  }

  @XmlElement(name = "InvoiceLine", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public InvoiceLine getInvoiceLine()
  {
    return invoiceLine;
  }

  public void setInvoiceLine(InvoiceLine invoiceLine)
  {
    this.invoiceLine = invoiceLine;
  }

}
