package com.helmet.jersey.tradeshift;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;

import com.helmet.xml.jaxb.model.tradeshift.TradeshiftConstants;
import com.helmet.xml.jaxb.model.ubl.AccountingParty;
import com.helmet.xml.jaxb.model.ubl.AdditionalDocumentReference;
import com.helmet.xml.jaxb.model.ubl.AddressFormatCode;
import com.helmet.xml.jaxb.model.ubl.Attachment;
import com.helmet.xml.jaxb.model.ubl.Contact;
import com.helmet.xml.jaxb.model.ubl.Country;
import com.helmet.xml.jaxb.model.ubl.CurrencyAmount;
import com.helmet.xml.jaxb.model.ubl.Delivery;
import com.helmet.xml.jaxb.model.ubl.DocumentTypeCode;
import com.helmet.xml.jaxb.model.ubl.EmbeddedDocumentBinaryObject;
import com.helmet.xml.jaxb.model.ubl.Invoice;
import com.helmet.xml.jaxb.model.ubl.InvoiceLine;
import com.helmet.xml.jaxb.model.ubl.InvoiceTypeCode;
import com.helmet.xml.jaxb.model.ubl.Item;
import com.helmet.xml.jaxb.model.ubl.LegalMonetaryTotal;
import com.helmet.xml.jaxb.model.ubl.Party;
import com.helmet.xml.jaxb.model.ubl.PartyIdentification;
import com.helmet.xml.jaxb.model.ubl.PartyIdentificationId;
import com.helmet.xml.jaxb.model.ubl.PartyName;
import com.helmet.xml.jaxb.model.ubl.PostalAddress;
import com.helmet.xml.jaxb.model.ubl.Price;
import com.helmet.xml.jaxb.model.ubl.Quantity;
import com.helmet.xml.jaxb.model.ubl.SchemeId;
import com.helmet.xml.jaxb.model.ubl.SellersItemIdentification;
import com.helmet.xml.jaxb.model.ubl.TaxCategory;
import com.helmet.xml.jaxb.model.ubl.TaxScheme;
import com.helmet.xml.jaxb.model.ubl.TaxSubTotal;
import com.helmet.xml.jaxb.model.ubl.TaxTotal;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class TradeshiftInvoice extends TradeshiftBase
{
  public static final String VAT = "VAT";
  public static final String CURRENCY_CODE = "GBP";

  private AdditionalDocumentReference additionalDocumentReference;
  // Invoice.
  private String trust;
  private String invoiceId;
  private String invoiceDate;
  private String description;
  // Money...
  private BigDecimal agreedValue;
  private BigDecimal vatRate;
  private BigDecimal vat;
  // Invoice Note (Free Text)
  private String note; 
  
  public String getTrust()
  {
    return trust;
  }

  public void setTrust(String trust)
  {
    this.trust = trust;
  }

  public TradeshiftInvoice(String hostName, String tenantIdHeaderName, String userAgent, String restUrlPart, String documentsUrlPart, String dispatcherUrlPart, String documentProfileId, String document_type_code)
  {
    super(hostName, tenantIdHeaderName, userAgent, restUrlPart, documentsUrlPart, dispatcherUrlPart, documentProfileId, document_type_code);
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void setAgreedValue(BigDecimal agreedValue)
  {
    this.agreedValue = agreedValue;
  }

  public void setVatRate(BigDecimal vatRate)
  {
    this.vatRate = vatRate;
  }

  public void setVat(BigDecimal vat)
  {
    this.vat = vat;
  }

  public void setNote(String note)
  {
    this.note = note;
  }

  public void setInvoiceId(String invoiceId)
  {
    this.invoiceId = invoiceId;
  }

  public void setInvoiceDate(String invoiceDate)
  {
    this.invoiceDate = invoiceDate;
  }
  
  public void handleNhsBackingReportAttachment(String nhsBackingReportFilePath) throws IOException
  {
    File file = new File(nhsBackingReportFilePath);
    int size = (int)file.length();
    if (size > Integer.MAX_VALUE)
    {
      System.out.println("File is to large!");
      throw new IOException("File is to large!");
    }
    byte[] bytes = new byte[size];
    DataInputStream dis = new DataInputStream(new FileInputStream(file));
    int read = 0;
    int numRead = 0;
    while (read < bytes.length && (numRead = dis.read(bytes, read, bytes.length - read)) >= 0)
    {
      read = read + numRead;
    }
    dis.close();
    // Ensure all the bytes have been read in
    if (read < bytes.length)
    {
        System.out.println("Could not completely read: " + nhsBackingReportFilePath);
        throw new IOException("Could not completely read: " + nhsBackingReportFilePath);
    }
    // EmbeddedDocumentBinaryObject
    EmbeddedDocumentBinaryObject embeddedDocumentBinaryObject = new EmbeddedDocumentBinaryObject();
    embeddedDocumentBinaryObject.setMimeCode("application/pdf");
    // Only the actual file name NOT the full path.
    embeddedDocumentBinaryObject.setFilename(nhsBackingReportFilePath.substring(nhsBackingReportFilePath.lastIndexOf("/") + 1));
    embeddedDocumentBinaryObject.setEncodingCode("Base64");
    String value = DatatypeConverter.printBase64Binary(bytes);
    embeddedDocumentBinaryObject.setValue(value);

    Attachment attachment = new Attachment();
    attachment.setEmbeddedDocumentBinaryObject(embeddedDocumentBinaryObject);

    DocumentTypeCode documentTypeCode = new DocumentTypeCode();
    documentTypeCode.setListId(this.documentTypeCode);
    documentTypeCode.setValue("attachment");

    // AdditionalDocumentReference
    additionalDocumentReference = new AdditionalDocumentReference();
    additionalDocumentReference.setId("1");
    additionalDocumentReference.setDocumentTypeCode(documentTypeCode);
    additionalDocumentReference.setAttachment(attachment);
  }
  
  public String send() throws Exception
  {
    DecimalFormat df = new DecimalFormat("#0.00");

    // InvoiceTypeCode.
    InvoiceTypeCode invoiceTypeCode = new InvoiceTypeCode();
    invoiceTypeCode.setValue("380");
    invoiceTypeCode.setListAgencyId("6");
    invoiceTypeCode.setListId("UN/ECE 1001 Subset");
    invoiceTypeCode.setListVersionId("D08B");

    // ProfileID.
    SchemeId profileId = new SchemeId();
    profileId.setValue("urn:www.cenbii.eu:profile:bii04:ver1.0");
    profileId.setSchemeAgencyId("CEN/ISSS WS/BII");
    profileId.setSchemeId("CWA 16073:2010");
    profileId.setSchemeVersionId("1");

    // AddressFormatCode.
    AddressFormatCode addressFormatCode = new AddressFormatCode();
    addressFormatCode.setValue("5");
    addressFormatCode.setListAgencyId("6");
    addressFormatCode.setListId("UN/ECE 3477");
    addressFormatCode.setListVersionId("D08B");
    
    // Supplier Country.
    Country supplierCountry = new Country();
    supplierCountry.setIdentificationCode(getSupplierCountryIsoCode());

    // Supplier Contact.
    Contact supplierContact = new Contact();
    // supplierContact.setId("1");
    supplierContact.setName(getSupplierContactName());
    supplierContact.setElectronicMail(getSupplierContactEmailAddress());
    supplierContact.setTelephone(getSupplierContactTelephone());
    
    // Supplier PostalAddress.
    PostalAddress supplierPostalAddress = new PostalAddress();
    supplierPostalAddress.setAddressFormatCode(addressFormatCode);
    supplierPostalAddress.setStreetName(getSupplierAddressLine1());
    supplierPostalAddress.setAdditionalStreetName(getSupplierAddressLine2());
    supplierPostalAddress.setCityName(getSupplierAddressLine3());
    supplierPostalAddress.setPostalZone(getSupplierPostalCode());
    supplierPostalAddress.setCountry(supplierCountry);
    
    // AccountingSupplierParty.
    AccountingParty accountingSupplierParty = new AccountingParty();
    Party supplierParty = new Party();
    PartyIdentification supplierPartyIdentification = new PartyIdentification();
    PartyIdentificationId supplierPartyIdentificationId = new PartyIdentificationId();
    // Use VAT Number as Identification ID. 
    supplierPartyIdentificationId.setValue(supplierCountry.getIdentificationCode() + StringUtils.removePattern(getSupplierVatNumber(), " "));
    supplierPartyIdentificationId.setSchemeId(supplierCountry.getIdentificationCode() + ":" + VAT);
    supplierPartyIdentification.setPartyIdentificationId(supplierPartyIdentificationId);
    PartyName supplierPartyName = new PartyName();
    supplierPartyName.setName(getSupplierName());
    supplierParty.setPartyIdentification(supplierPartyIdentification);
    supplierParty.setPartyName(supplierPartyName);
    supplierParty.setPostalAddress(supplierPostalAddress);
    supplierParty.setContact(supplierContact);
    accountingSupplierParty.setParty(supplierParty);

    // Customer Country
    Country customerCountry = new Country();
    customerCountry.setIdentificationCode(getCustomerAccountInfo().getCountry());
        
    // Customer Contact.
    // Contact customerContact = new Contact();
    // customerContact.setId("1");
    // customerContact.setName(customerContactName);
    // customerContact.setElectronicMail(customerContactEmailAddress);

    // Customer PostalAddress
    PostalAddress customerPostalAddress = new PostalAddress();
    customerPostalAddress.setAddressFormatCode(addressFormatCode);
    customerPostalAddress.setStreetName(getTrust() + "\n" + getCustomerAccountInfo().getAddressLine(TradeshiftConstants.ADDRESS_LINE_STREET));
    customerPostalAddress.setAdditionalStreetName(getCustomerAccountInfo().getAddressLine(TradeshiftConstants.ADDRESS_LINE_LOCALITY));
    customerPostalAddress.setCityName(getCustomerAccountInfo().getAddressLine(TradeshiftConstants.ADDRESS_LINE_CITY));
    customerPostalAddress.setPostalZone(getCustomerAccountInfo().getAddressLine(TradeshiftConstants.ADDRESS_LINE_ZIP));
    customerPostalAddress.setCountry(customerCountry);

    // AccountingCustomerParty
    AccountingParty accountingCustomerParty = new AccountingParty();
    Party customerParty = new Party();
    PartyIdentification customerPartyIdentification = new PartyIdentification();
    PartyIdentificationId customerPartyIdentificationId = new PartyIdentificationId();
    customerPartyIdentificationId.setValue(getCustomerAccountInfo().getIdentifier(TradeshiftConstants.IDENTIFIER_SCHEME, TradeshiftConstants.IDENTIFIER_PAYABLES));
    customerPartyIdentificationId.setSchemeId(TradeshiftConstants.IDENTIFIER_SCHEME);
    customerPartyIdentification.setPartyIdentificationId(customerPartyIdentificationId);
    PartyName customerPartyName = new PartyName();
    customerPartyName.setName(getCustomerAccountInfo().getCompanyName());
    customerParty.setPartyIdentification(customerPartyIdentification);
    customerParty.setPartyName(customerPartyName);
    customerParty.setPostalAddress(customerPostalAddress);
    // customerParty.setContact(customerContact);
    accountingCustomerParty.setParty(customerParty);

    // Delivery
    Delivery delivery = new Delivery();
    delivery.setActualDeliveryDate(invoiceDate);
    
    // TaxableAmount
    CurrencyAmount taxableAmount = new CurrencyAmount();
    taxableAmount.setCurrencyId(CURRENCY_CODE);
    taxableAmount.setValue(df.format(agreedValue));

    // TaxAmount
    CurrencyAmount taxAmount = new CurrencyAmount();
    taxAmount.setCurrencyId(CURRENCY_CODE);
    taxAmount.setValue(df.format(vat));

    // TaxScheme ID
    SchemeId taxSchemeId = new SchemeId();
    taxSchemeId.setValue(VAT);
    taxSchemeId.setSchemeAgencyId("6");
    taxSchemeId.setSchemeId("UN/ECE 5153 Subset");
    taxSchemeId.setSchemeVersionId("D08B");

    // TaxScheme
    TaxScheme taxScheme = new TaxScheme();
    taxScheme.setId(taxSchemeId);
    taxScheme.setName(VAT);

    // TaxCategory ID
    SchemeId taxCategoryId = new SchemeId();
    taxCategoryId.setValue("S");
    taxCategoryId.setSchemeAgencyId("6");
    taxCategoryId.setSchemeId("UN/ECE 5305");
    taxCategoryId.setSchemeVersionId("D08B");

    // TaxCategory
    TaxCategory taxCategory = new TaxCategory();
    taxCategory.setId(taxCategoryId);
    taxCategory.setPercent(df.format(vatRate));
    taxCategory.setTaxScheme(taxScheme);

    // TaxSubTotal
    TaxSubTotal taxSubTotal = new TaxSubTotal();
    taxSubTotal.setTaxableAmount(taxableAmount);
    taxSubTotal.setTaxAmount(taxAmount);
    taxSubTotal.setTaxCategory(taxCategory);

    // TaxTotal
    TaxTotal taxTotal = new TaxTotal();
    taxTotal.setTaxAmount(taxAmount);
    taxTotal.setTaxSubTotal(taxSubTotal);

    // LineExtensionAmount
    CurrencyAmount lineExtensionAmount = new CurrencyAmount();
    lineExtensionAmount.setCurrencyId(CURRENCY_CODE);
    lineExtensionAmount.setValue(df.format(agreedValue));

    // TaxExclusiveAmount
    CurrencyAmount taxExclusiveAmount = new CurrencyAmount();
    taxExclusiveAmount.setCurrencyId(CURRENCY_CODE);
    taxExclusiveAmount.setValue(df.format(vat));

    // TaxInclusiveAmount
    CurrencyAmount taxInclusiveAmount = new CurrencyAmount();
    taxInclusiveAmount.setCurrencyId(CURRENCY_CODE);
    taxInclusiveAmount.setValue(df.format(agreedValue.add(vat)));

    // PayableAmount
    CurrencyAmount payableAmount = new CurrencyAmount();
    payableAmount.setCurrencyId(CURRENCY_CODE);
    payableAmount.setValue(df.format(agreedValue.add(vat)));

    // LegalMonetaryTotal
    LegalMonetaryTotal legalMonetaryTotal = new LegalMonetaryTotal();
    legalMonetaryTotal.setLineExtensionAmount(lineExtensionAmount);
    legalMonetaryTotal.setTaxExclusiveAmount(taxExclusiveAmount);
    legalMonetaryTotal.setTaxInclusiveAmount(taxInclusiveAmount);
    legalMonetaryTotal.setPayableAmount(payableAmount);
    
    // SellersItemIdentification SchemeId
    SchemeId sellersItemIdentificationId = new SchemeId();
    sellersItemIdentificationId.setValue("1");        // This is the Item/Line No. on the Invoice.
    sellersItemIdentificationId.setSchemeAgencyId("9");
    sellersItemIdentificationId.setSchemeId("GTIN");

    // SellersItemIdentification
    SellersItemIdentification sellersItemIdentification = new SellersItemIdentification();
    sellersItemIdentification.setId(sellersItemIdentificationId);

    // InvoicedQuantity
    Quantity invoicedQuantity = new Quantity();
    invoicedQuantity.setValue("1");
    invoicedQuantity.setUnitCode("EA");

    // Item
    Item item = new Item();
    item.setDescription(description);
    item.setName("Test Name");
    item.setSellersItemIdentification(sellersItemIdentification);

    // PriceAmount
    CurrencyAmount priceAmount = new CurrencyAmount();
    priceAmount.setCurrencyId("GBP");
    priceAmount.setValue(df.format(agreedValue));            /////////////////////////////////////
    
    // BaseQuantity
    Quantity baseQuantity = new Quantity();
    baseQuantity.setValue("1");
    baseQuantity.setUnitCode("EA");
    
    // Price
    Price price = new Price();
    price.setPriceAmount(priceAmount);
    price.setBaseQuantity(baseQuantity);
    price.setOrderableUnitFactorRate(1);
    
    // InvoiceLine
    InvoiceLine invoiceLine = new InvoiceLine();
    invoiceLine.setId("1");
    invoiceLine.setInvoicedQuantity(invoicedQuantity);
    invoiceLine.setLineExtensionAmount(lineExtensionAmount);
    invoiceLine.setTaxTotal(taxTotal);
    invoiceLine.setItem(item);
    invoiceLine.setPrice(price);

    // Invoice.
    Invoice invoice = new Invoice();
    invoice.setUblVersionId("2.0");
    invoice.setCustomizationId(TradeshiftConstants.INVOICE_CUSTOMIZATION_ID);
    invoice.setId(invoiceId);
    invoice.setIssueDate(invoiceDate);
    invoice.setInvoiceTypeCode(invoiceTypeCode);
    invoice.setNote(note);
    invoice.setTaxPointDate(invoiceDate);
    invoice.setDocumentCurrencyCode("GBP");
    invoice.setProfileId(profileId);
    invoice.setAdditionalDocumentReference(additionalDocumentReference);
    invoice.setAccountingSupplierParty(accountingSupplierParty);
    invoice.setAccountingCustomerParty(accountingCustomerParty);
    invoice.setDelivery(delivery); 
    invoice.setTaxTotal(taxTotal);
    invoice.setLegalMonetaryTotal(legalMonetaryTotal);
    invoice.setInvoiceLine(invoiceLine);

    JAXBContext context = JAXBContext.newInstance(Invoice.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    StringWriter stringWriter = new StringWriter();
    m.marshal(invoice, stringWriter);
    System.out.println(stringWriter);

    String documentId = UUID.randomUUID().toString();

    WebResource webResource = getClient().resource(getURI(dispatcherUrl));
    webResource.addFilter(getFilter());
    webResource.accept(MediaType.TEXT_XML);
    ClientResponse clientResponse = webResource
        .queryParam("documentId", documentId)
        .queryParam("documentProfileId", documentProfileId)
        .header("X-Tradeshift-TenantId", tenantId)
        .header("User-Agent", "TradeshiftJerseyTest/0.1")
        .entity(stringWriter.toString(), MediaType.APPLICATION_XML)
        .post(ClientResponse.class);
    
    System.out.println("Status: " + clientResponse.getStatus());
    
    if (clientResponse.getStatus() == 201)
    {
      System.out.println("Location: " + clientResponse.getLocation());
    }
    else
    {
      Exception e = new Exception("Tradeshift Invoice Upload failed with Status: " + clientResponse.getStatus());
      throw e;
    }
    
    return documentId;
  }
}
