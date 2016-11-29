package com.helmet.bean.nhs;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.SubcontractInvoice;

public class PaymentLine
{
  private Payments payments;
  private Date date;
  private Boolean dateValid;
  private String documentName;
  private Boolean documentNameValid;
  private String clientName;
  private BigDecimal payment;
  private Boolean paymentCorrect;
  private String status;
  private NhsBackingReport nhsBackingReport;
  private SubcontractInvoice subcontractInvoice;
  private List<PaymentLineError> errors;
  private Boolean valid;
  private Integer errorCount;
  
  public PaymentLine(Payments payments)
  {
    super();
    this.payments = payments;
    valid = true;
    errorCount = 0;
    errors = new ArrayList<PaymentLineError>();
    dateValid = true;
    documentNameValid = true;
    paymentCorrect = true;
  }

  public Payments getPayments()
  {
    return payments;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public Boolean getDateValid()
  {
    return dateValid;
  }

  public void setDateInvalid(PaymentLineError errorMessage)
  {
    dateValid = false;
    addError(errorMessage);
    payments.setValid(false);
  }

  public String getDocumentName()
  {
    return documentName;
  }

  public void setDocumentName(String documentName)
  {
    this.documentName = documentName;
  }

  public String getClientName()
  {
    return clientName;
  }

  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }

  public BigDecimal getPayment()
  {
    return payment;
  }

  public void setPayment(BigDecimal payment)
  {
    this.payment = payment;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public NhsBackingReport getNhsBackingReport()
  {
    return nhsBackingReport;
  }

  public void setNhsBackingReport(NhsBackingReport nhsBackingReport)
  {
    this.nhsBackingReport = nhsBackingReport;
  }

  public SubcontractInvoice getSubcontractInvoice()
  {
    return subcontractInvoice;
  }

  public void setSubcontractInvoice(SubcontractInvoice subcontractInvoice)
  {
    this.subcontractInvoice = subcontractInvoice;
  }

  private void addError(PaymentLineError error)
  {
    errors.add(error);
  }
  
  public List<PaymentLineError> getErrors()
  {
    return errors;
  }

  public Boolean getDocumentNameValid()
  {
    return documentNameValid;
  }

  public void setDocumentNameInvalid(PaymentLineError errorMessage)
  {
    documentNameValid = false;
    addError(errorMessage);
    setValid(false);
    payments.setValid(false);
    payments.setCanAccept(false);
  }

  public void setAgencyInvalid(PaymentLineError errorMessage)
  {
    // Backing Report Name set here deliberately.
    documentNameValid = false;
    addError(errorMessage);
    setValid(false);
    payments.setValid(false);
    payments.setCanAccept(false);
  }

  public void setClientReferenceInvalid(PaymentLineError errorMessage)
  {
    addError(errorMessage);
    setValid(false);
    payments.setValid(false);
    payments.setCanAccept(false);
  }

  public Boolean getPaymentCorrect()
  {
    return paymentCorrect;
  }

  public void setPaymentIncorrect(PaymentLineError errorMessage)
  {
    paymentCorrect = false;
    addError(errorMessage);
    setValid(false);
    payments.setValid(false);
  }

  public Boolean getValid()
  {
    return valid;
  }

  public void setValid(Boolean valid)
  {
    this.valid = valid;
    if (!valid)
    {
      ++errorCount;
    }
  }

  public Integer getErrorCount()
  {
    return errorCount;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    result.append("PaymentLine:");
    result.append("\n");
    result.append("date=");
    result.append(dateFormat.format(date));
    result.append("\n");
    result.append("documentName=");
    result.append(documentName);
    result.append("\n");
    result.append("clientName=");
    result.append(clientName);
    result.append("\n");
    result.append("payment=");
    result.append(payment);
    result.append("\n");
    result.append("paymentCorrect=");
    result.append(paymentCorrect);
    result.append("\n");
    result.append("status=");
    result.append(status);
    result.append("\n");
    result.append("valid=");
    result.append(valid);
    result.append("\n");
    result.append("errorCount=");
    result.append(errorCount);
    result.append("\n\n");
    return result.toString();
  }
  
}
