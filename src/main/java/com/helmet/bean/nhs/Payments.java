package com.helmet.bean.nhs;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.helmet.application.comparator.NhsPaymentsComparator;

public class Payments
{
  private String fileName;
  private BigDecimal totalPayment;
  private List<ClientPayment> clientPaymentList;
  private List<PaymentsError> errors;
  private List<PaymentLine> paymentLines;
  private Boolean valid;
  private Boolean canAccept;
  private Integer errorCount;
  
  public Payments(String fileName)
  {
    this.fileName = fileName;
    totalPayment = new BigDecimal(0);
    errors = new ArrayList<PaymentsError>();
    valid = true;
    canAccept = true;
    errorCount = 0;
  }

  public String getFileName()
  {
    return fileName;
  }

  public List<ClientPayment> getClientPaymentList()
  {
    return clientPaymentList;
  }

  public void setClientPaymentList(List<ClientPayment> clientList)
  {
    this.clientPaymentList = clientList;
  }

  public String getCommaDelimitedClientList()
  {
    StringBuffer commaDelimitedClientList = new StringBuffer();
    for (ClientPayment clientPayment : clientPaymentList)
    {
      if (commaDelimitedClientList.length() > 0)
      {
        commaDelimitedClientList.append(", ");
      }
      commaDelimitedClientList.append(clientPayment.getClient().getName());
    }
    return commaDelimitedClientList.toString();
  }

  public void setAgencyInvalid(PaymentsError errorMessage)
  {
    addError(errorMessage);
    setValid(false);
    canAccept = false;
  }

  public void setBookingReportCompleted(PaymentsError errorMessage)
  {
    addError(errorMessage);
    setValid(false);
    canAccept = false;
  }

  public void setNumberOfLinesDiffer(PaymentsError errorMessage)
  {
    addError(errorMessage);
    setValid(false);
  }

  public void setNameInvalid(PaymentsError errorMessage)
  {
    addError(errorMessage);
    setValid(false);
  }

  public List<PaymentLine> getPaymentLines()
  {
    return paymentLines;
  }

  public void setPaymentLines(List<PaymentLine> paymentLines)
  {
    this.paymentLines = paymentLines;
    totalPayment = new BigDecimal(0);
    for (PaymentLine paymentLine : paymentLines)
    {
      totalPayment = totalPayment.add(paymentLine.getPayment());
    }
  }

  public BigDecimal getTotalPayment()
  {
    return totalPayment;
  }

  public Boolean getValid()
  {
    return valid;
  }

  public void setValid(Boolean valid)
  {
    this.valid = valid;
    ++errorCount;
  }

  public Boolean getCanAccept()
  {
    return canAccept;
  }

  public void setCanAccept(Boolean canAccept)
  {
    this.canAccept = canAccept;
  }

  public Integer getErrorCount()
  {
    return errorCount;
  }

  public Date getStartDate()
  {
    Date startDate = paymentLines.get(0).getDate();
    for (PaymentLine paymentLine : paymentLines)
    {
      if (paymentLine.getDate().before(startDate))
      {
        startDate = paymentLine.getDate();
      }
    }
    return startDate;
  }
  
  public Date getEndDate()
  {
    Date endDate = paymentLines.get(0).getDate();
    for (PaymentLine paymentLine : paymentLines)
    {
      if (paymentLine.getDate().after(endDate))
      {
        endDate = paymentLine.getDate();
      }
    }
    return endDate;
  }
  
  private void addError(PaymentsError error)
  {
    errors.add(error);
  }
  
  public List<PaymentsError> getErrors()
  {
    return errors;
  }

  public void sortPaymentLines()
  {
    Collections.sort(paymentLines, new NhsPaymentsComparator());
  }
  
  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("Payments:");
    result.append("\n");
    result.append("fileName=");
    result.append(fileName);
    result.append("\n");
    result.append("totalPayment=");
    result.append(totalPayment);
    result.append("\n");
    result.append("valid=");
    result.append(valid);
    result.append("\n");
    result.append("canAccept=");
    result.append(canAccept);
    result.append("\n");
    result.append("errorCount=");
    result.append(errorCount);
    result.append("\n\n");
    for (ClientPayment clientPayment : clientPaymentList)
    {
      result.append(clientPayment.toString());
    }
    for (PaymentsError paymentsError : errors)
    {
      result.append(paymentsError.toString());
    }
    for (PaymentLine paymentLine : paymentLines)
    {
      result.append(paymentLine.toString());
    }
    result.append("\n");
    return result.toString();
  }
  
}
