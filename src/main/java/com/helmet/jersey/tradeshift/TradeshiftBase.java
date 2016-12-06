package com.helmet.jersey.tradeshift;

import java.io.StringReader;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.helmet.xml.jaxb.model.tradeshift.CompanyAccountInfo;
import com.helmet.xml.jaxb.model.tradeshift.ConnectionList;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;


public class TradeshiftBase
{
  public String consumerKey;    // Eg. "OwnAccount"; 
  public String consumerSecret; // Eg. "OwnAccount";
  public String tokenKey;       // Eg. "nV9qG8XBpVA9w4BZqqdrwAy@3rmxMc";
  public String tokenSecret;    // Eg. "W6nCgbk@g2mrknZcWPWAddr6ecrK-efkPHq8Zcyv";
  public String tenantId;       // Eg. "049b7104-4e04-4d46-bf24-1e4f7282065b";

  // See relevant build.properties for actual values.
  public String hostName;           // Eg. "https://api-sandbox.tradeshift.com";
  public String tenantIdHeaderName; // Eg. "X-Tradeshift-TenantId";
  public String userAgent;          // Eg. "TradeshiftJerseyTest/0.1";
  public String baseUrl;            // Eg. hostName + "/tradeshift/rest/external";
  public String documentsUrl;       // Eg. baseUrl + "/documents";
  public String dispatcherUrl;      // Eg. documentsUrl + "/dispatcher";
  public String documentProfileId;
  public String documentTypeCode;

  // This is the Jersey Client NOT the MMJ one.
  private Client client;
  private OAuthClientFilter filter;
  // Client (Customer) details.
  private String customerCompanyAccountId;
  private CompanyAccountInfo customerAccountInfo;
  // Agency (Supplier) details.
  private String supplierName;
  private String supplierVatNumber;
  private String supplierCountryIsoCode;
  private String supplierContactName;
  private String supplierContactEmailAddress;
  private String supplierContactTelephone;
  private String supplierAddressLine1;
  private String supplierAddressLine2;
  private String supplierAddressLine3;
  private String supplierPostalCode;
  
  public TradeshiftBase(String hostName, String tenantIdHeaderName, String userAgent, 
                           String restUrlPart,String documentsUrlPart, String dispatcherUrlPart, 
                           String documentProfileId, String document_type_code)
  {
    super();
    this.hostName = hostName;
    this.tenantIdHeaderName = tenantIdHeaderName;
    this.userAgent = userAgent;
    baseUrl = hostName + restUrlPart;
    documentsUrl = baseUrl + documentsUrlPart;
    dispatcherUrl = documentsUrl + dispatcherUrlPart;
    this.documentProfileId = documentProfileId;
    this.documentTypeCode = document_type_code;
  }

  public Client getClient()
  {
    return client;
  }

  public OAuthClientFilter getFilter()
  {
    return filter;
  }

  public void setConsumerKey(String consumerKey)
  {
    this.consumerKey = consumerKey;
  }

  public void setConsumerSecret(String consumerSecret)
  {
    this.consumerSecret = consumerSecret;
  }

  public void setTenantId(String tenantId)
  {
    this.tenantId = tenantId;
  }

  public void setTokenKey(String tokenKey)
  {
    this.tokenKey = tokenKey;
  }

  public void setTokenSecret(String tokenSecret)
  {
    this.tokenSecret = tokenSecret;
  }

  public CompanyAccountInfo getCustomerAccountInfo()
  {
    return customerAccountInfo;
  }

  public String getSupplierName()
  {
    return supplierName;
  }

  public void setSupplierName(String supplierName)
  {
    this.supplierName = supplierName;
  }

  public String getSupplierVatNumber()
  {
    return supplierVatNumber;
  }

  public void setSupplierVatNumber(String supplierVatNumber)
  {
    this.supplierVatNumber = supplierVatNumber;
  }

  public String getSupplierCountryIsoCode()
  {
    return supplierCountryIsoCode;
  }

  public void setSupplierCountryIsoCode(String supplierCountryIsoCode)
  {
    this.supplierCountryIsoCode = supplierCountryIsoCode;
  }

  public String getSupplierContactName()
  {
    return supplierContactName;
  }

  public void setSupplierContactName(String accountContactName)
  {
    this.supplierContactName = accountContactName;
  }

  public String getSupplierContactEmailAddress()
  {
    return supplierContactEmailAddress;
  }

  public void setSupplierContactEmailAddress(String accountContactEmailAddress)
  {
    this.supplierContactEmailAddress = accountContactEmailAddress;
  }

  public String getSupplierContactTelephone()
  {
    return supplierContactTelephone;
  }

  public void setSupplierContactTelephone(String supplierContactTelephone)
  {
    this.supplierContactTelephone = supplierContactTelephone;
  }

  public String getSupplierAddressLine1()
  {
    return supplierAddressLine1;
  }

  public void setSupplierAddressLine1(String supplierAddressLine1)
  {
    this.supplierAddressLine1 = supplierAddressLine1;
  }

  public String getSupplierAddressLine2()
  {
    return supplierAddressLine2;
  }

  public void setSupplierAddressLine2(String supplierAddressLine2)
  {
    this.supplierAddressLine2 = supplierAddressLine2;
  }

  public String getSupplierAddressLine3()
  {
    return supplierAddressLine3;
  }

  public void setSupplierAddressLine3(String supplierAddressLine3)
  {
    this.supplierAddressLine3 = supplierAddressLine3;
  }

  public String getSupplierPostalCode()
  {
    return supplierPostalCode;
  }

  public void setSupplierPostalCode(String supplierPostalCode)
  {
    this.supplierPostalCode = supplierPostalCode;
  }

  public String getCustomerCompanyAccountId()
  {
    return customerCompanyAccountId;
  }

  public void setCustomerCompanyAccountId(String customerCompanyAccountId)
  {
    this.customerCompanyAccountId = customerCompanyAccountId;
  }

  public Boolean authorised()
  {
    client = Client.create();
    OAuthParameters params = new OAuthParameters().signatureMethod("HMAC-SHA1").consumerKey(consumerKey).token(tokenKey).version();
    OAuthSecrets secrets = new OAuthSecrets().consumerSecret(consumerSecret).tokenSecret(tokenSecret);
    filter = new OAuthClientFilter(client.getProviders(), params, secrets);
    return filter != null;
  }

  public ConnectionList getConnections() throws JAXBException
  {
    WebResource webResource = client.resource(baseUrl + "/network/connections");
    webResource.addFilter(filter);
    String connectionsResult = webResource.header(tenantIdHeaderName, tenantId)
        .header("User-Agent", userAgent)
        .get(String.class);
    System.out.println(connectionsResult);
    StringReader reader = new StringReader(connectionsResult);
    JAXBContext context = JAXBContext.newInstance(ConnectionList.class);
    Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
    ConnectionList connectionList = (ConnectionList)jaxbUnmarshaller.unmarshal(reader);
    return connectionList;
  }
  
  public CompanyAccountInfo getCompanyAccounInfo(String companyAccountId) throws Exception
  {
    // Get Company Account Info.
    WebResource webResource = client.resource(baseUrl + "/companies/" + companyAccountId);
    webResource.addFilter(filter);
    String companyResult = webResource.header(tenantIdHeaderName, tenantId)
        .header("User-Agent", userAgent)
        .get(String.class);
    System.out.println(companyResult);
    StringReader reader = new StringReader(companyResult);
    JAXBContext context = JAXBContext.newInstance(CompanyAccountInfo.class);
    Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();
    customerAccountInfo =(CompanyAccountInfo)jaxbUnmarshaller.unmarshal(reader);
    return customerAccountInfo;
  }
  
  protected URI getURI(String url) 
  {
    return UriBuilder.fromUri(url).build();
  }

}
