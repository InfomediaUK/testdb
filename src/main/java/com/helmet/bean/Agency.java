package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;

public class Agency extends BaseOwner
{

  private Integer agencyId;

  private String freeText2;

  private String invoiceCreditFreeText;

  private String invoiceCreditFooterFreeText;

  private String invoiceLogoFilename;

  private Integer invoiceLogoWidth;

  private Integer invoiceLogoHeight;

  public String payrollContactName;

  public String payrollContactEmailAddress;

  public String payrollContactTelephoneNumber;

  public String payrollContactFaxNumber;

  public String clientConfirmationEmailFreeText;

  public String applicantConfirmationEmailFreeText;

  public String agencyAdminEmailAddress;

  public String paymentTermsText;

  public String bankDetailsText;

  // Tradshift Agency specific values.
  private String tradeshiftConsumerKey;
  private String tradeshiftConsumerSecret;
  private String tradeshiftTokenKey;
  private String tradeshiftTokenSecret;
  private String tradeshiftTenantId;
  
  private Boolean hasSubcontractors;
  
  public Integer getAgencyId()
  {
    return agencyId;
  }

  public void setAgencyId(Integer agencyId)
  {
    this.agencyId = agencyId;
  }

  public String getFreeText2()
  {
    return freeText2;
  }

  public void setFreeText2(String freeText2)
  {
    this.freeText2 = freeText2;
  }

  public String getInvoiceLogoFilename()
  {
    return invoiceLogoFilename;
  }

  public void setInvoiceLogoFilename(String invoiceLogoFilename)
  {
    this.invoiceLogoFilename = invoiceLogoFilename;
  }

  public Integer getInvoiceLogoHeight()
  {
    return invoiceLogoHeight;
  }

  public void setInvoiceLogoHeight(Integer invoiceLogoHeight)
  {
    this.invoiceLogoHeight = invoiceLogoHeight;
  }

  public Integer getInvoiceLogoWidth()
  {
    return invoiceLogoWidth;
  }

  public void setInvoiceLogoWidth(Integer invoiceLogoWidth)
  {
    this.invoiceLogoWidth = invoiceLogoWidth;
  }

  public String getLogoUrl()
  {
    return FileHandler.getInstance().getLogoFileFolder() + "/a" + getAgencyId() + "/" + getLogoFilename();
  }

  public String getInvoiceLogoUrl()
  {
    return FileHandler.getInstance().getLogoFileFolder() + "/a" + getAgencyId() + "/" + getInvoiceLogoFilename();
  }

  public String getApplicantConfirmationEmailFreeText()
  {
    return applicantConfirmationEmailFreeText;
  }

  public void setApplicantConfirmationEmailFreeText(String applicantConfirmationEmailFreeText)
  {
    this.applicantConfirmationEmailFreeText = applicantConfirmationEmailFreeText;
  }

  public String getClientConfirmationEmailFreeText()
  {
    return clientConfirmationEmailFreeText;
  }

  public void setClientConfirmationEmailFreeText(String clientConfirmationEmailFreeText)
  {
    this.clientConfirmationEmailFreeText = clientConfirmationEmailFreeText;
  }

  public String getPayrollContactEmailAddress()
  {
    return payrollContactEmailAddress;
  }

  public void setPayrollContactEmailAddress(String payrollContactEmailAddress)
  {
    this.payrollContactEmailAddress = payrollContactEmailAddress;
  }

  public String getPayrollContactFaxNumber()
  {
    return payrollContactFaxNumber;
  }

  public void setPayrollContactFaxNumber(String payrollContactFaxNumber)
  {
    this.payrollContactFaxNumber = payrollContactFaxNumber;
  }

  public String getPayrollContactName()
  {
    return payrollContactName;
  }

  public void setPayrollContactName(String payrollContactName)
  {
    this.payrollContactName = payrollContactName;
  }

  public String getPayrollContactTelephoneNumber()
  {
    return payrollContactTelephoneNumber;
  }

  public void setPayrollContactTelephoneNumber(String payrollContactTelephoneNumber)
  {
    this.payrollContactTelephoneNumber = payrollContactTelephoneNumber;
  }

  public String getInvoiceCreditFooterFreeText()
  {
    return invoiceCreditFooterFreeText;
  }

  public void setInvoiceCreditFooterFreeText(String invoiceCreditFooterFreeText)
  {
    this.invoiceCreditFooterFreeText = invoiceCreditFooterFreeText;
  }

  public String getInvoiceCreditFreeText()
  {
    return invoiceCreditFreeText;
  }

  public void setInvoiceCreditFreeText(String invoiceCreditFreeText)
  {
    this.invoiceCreditFreeText = invoiceCreditFreeText;
  }

  public String getAgencyAdminEmailAddress()
  {
    return agencyAdminEmailAddress;
  }

  public void setAgencyAdminEmailAddress(String agencyAdminEmailAddress)
  {
    this.agencyAdminEmailAddress = agencyAdminEmailAddress;
  }

  public String getPaymentTermsText()
  {
    return paymentTermsText;
  }

  public void setPaymentTermsText(String paymentTermsText)
  {
    this.paymentTermsText = paymentTermsText;
  }

  public String getBankDetailsText()
  {
    return bankDetailsText;
  }

  public void setBankDetailsText(String bankDetailsText)
  {
    this.bankDetailsText = bankDetailsText;
  }

  public String getTradeshiftConsumerKey()
  {
    return tradeshiftConsumerKey;
  }

  public void setTradeshiftConsumerKey(String tradeshiftConsumerKey)
  {
    this.tradeshiftConsumerKey = tradeshiftConsumerKey;
  }

  public String getTradeshiftConsumerSecret()
  {
    return tradeshiftConsumerSecret;
  }

  public void setTradeshiftConsumerSecret(String tradeshiftConsumerSecret)
  {
    this.tradeshiftConsumerSecret = tradeshiftConsumerSecret;
  }

  public String getTradeshiftTenantId()
  {
    return tradeshiftTenantId;
  }

  public void setTradeshiftTenantId(String tradeshiftTenantId)
  {
    this.tradeshiftTenantId = tradeshiftTenantId;
  }

  public String getTradeshiftTokenKey()
  {
    return tradeshiftTokenKey;
  }

  public void setTradeshiftTokenKey(String tradeshiftTokenKey)
  {
    this.tradeshiftTokenKey = tradeshiftTokenKey;
  }

  public String getTradeshiftTokenSecret()
  {
    return tradeshiftTokenSecret;
  }

  public void setTradeshiftTokenSecret(String tradeshiftTokenSecret)
  {
    this.tradeshiftTokenSecret = tradeshiftTokenSecret;
  }

  public Boolean getHasSubcontractors()
  {
    return hasSubcontractors;
  }

  public void setHasSubcontractors(Boolean hasSubcontractors)
  {
    this.hasSubcontractors = hasSubcontractors;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setAgencyId(rs.getInt("AGENCYID"));
    setFreeText2(rs.getString("FREETEXT2"));
    setInvoiceCreditFreeText(rs.getString("INVOICECREDITFREETEXT"));
    setInvoiceCreditFooterFreeText(rs.getString("INVOICECREDITFOOTERFREETEXT"));
    setInvoiceLogoFilename(rs.getString("INVOICELOGOFILENAME"));
    setInvoiceLogoWidth(rs.getInt("INVOICELOGOWIDTH"));
    setInvoiceLogoHeight(rs.getInt("INVOICELOGOHEIGHT"));
    setPayrollContactName(rs.getString("PAYROLLCONTACTNAME"));
    setPayrollContactEmailAddress(rs.getString("PAYROLLCONTACTEMAILADDRESS"));
    setPayrollContactTelephoneNumber(rs.getString("PAYROLLCONTACTTELEPHONENUMBER"));
    setPayrollContactFaxNumber(rs.getString("PAYROLLCONTACTFAXNUMBER"));
    setClientConfirmationEmailFreeText(rs.getString("CLIENTCONFIRMATIONEMAILFREETEXT"));
    setApplicantConfirmationEmailFreeText(rs.getString("APPLICANTCONFIRMATIONEMAILFREETEXT"));
    setAgencyAdminEmailAddress(rs.getString("AGENCYADMINEMAILADDRESS"));
    setPaymentTermsText(rs.getString("PAYMENTTERMSTEXT"));
    setBankDetailsText(rs.getString("BANKDETAILSTEXT"));
    setTradeshiftConsumerKey(rs.getString("TRADESHIFTCONSUMERKEY"));
    setTradeshiftConsumerSecret(rs.getString("TRADESHIFTCONSUMERSECRET"));
    setTradeshiftTokenKey(rs.getString("TRADESHIFTTOKENKEY"));
    setTradeshiftTokenSecret(rs.getString("TRADESHIFTTOKENSECRET"));
    setTradeshiftTenantId(rs.getString("TRADESHIFTTENANTID"));
    setHasSubcontractors(rs.getBoolean("HASSUBCONTRACTORS"));
  }

}
