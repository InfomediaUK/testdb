package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SubcontractInvoiceItemHistory extends Base
{
  private Integer subcontractInvoiceId;
  private Integer relatedSubcontractInvoiceId;
  private BigDecimal invoiceValue = new BigDecimal(0);
  private Date sentDate;
  private Date paidDate;
  private Date date;
  private Time startTime;
  private Time endTime;
  private BigDecimal noOfHours;
  private BigDecimal rate;
  private BigDecimal value = new BigDecimal(0);

  public Integer getSubcontractInvoiceId()
  {
    return subcontractInvoiceId;
  }

  public void setSubcontractInvoiceId(Integer subcontractInvoiceId)
  {
    this.subcontractInvoiceId = subcontractInvoiceId;
  }

  public Integer getRelatedSubcontractInvoiceId()
  {
    return relatedSubcontractInvoiceId;
  }

  public void setRelatedSubcontractInvoiceId(Integer relatedSubcontractInvoiceId)
  {
    this.relatedSubcontractInvoiceId = relatedSubcontractInvoiceId;
  }

  public BigDecimal getInvoiceValue()
  {
    return invoiceValue;
  }

  public void setInvoiceValue(BigDecimal invoiceValue)
  {
    this.invoiceValue = invoiceValue;
  }

  public BigDecimal getValue()
  {
    return value;
  }

  public void setValue(BigDecimal value)
  {
    this.value = value;
  }

  public Date getSentDate()
  {
    return sentDate;
  }

  public void setSentDate(Date sentDate)
  {
    this.sentDate = sentDate;
  }

  public Date getPaidDate()
  {
    return paidDate;
  }

  public void setPaidDate(Date paidDate)
  {
    this.paidDate = paidDate;
  }

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public void setStartTime(Time startTime)
  {
    this.startTime = startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public void setEndTime(Time endTime)
  {
    this.endTime = endTime;
  }

  public BigDecimal getNoOfHours()
  {
    return noOfHours;
  }

  public void setNoOfHours(BigDecimal noOfHours)
  {
    this.noOfHours = noOfHours;
  }

  public BigDecimal getRate()
  {
    return rate;
  }

  public void setRate(BigDecimal rate)
  {
    this.rate = rate;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setSubcontractInvoiceId(rs.getInt("SUBCONTRACTINVOICEID"));
    setRelatedSubcontractInvoiceId(rs.getInt("RELATEDSUBCONTRACTINVOICEID"));
    setInvoiceValue(rs.getBigDecimal("INVOICEVALUE"));
    setSentDate(rs.getDate("SENTDATE"));
    setPaidDate(rs.getDate("PAIDDATE"));
    setDate(rs.getDate("DATE"));
    setStartTime(rs.getTime("STARTTIME"));
    setEndTime(rs.getTime("ENDTIME"));
    setNoOfHours(rs.getBigDecimal("NOOFHOURS"));
    setRate(rs.getBigDecimal("RATE"));
    setValue(rs.getBigDecimal("VALUE"));
  }

  public String toString()
  {
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
    formatDate.setLenient(false);
    StringBuffer text = new StringBuffer();
    text.append("value=");
    text.append(value);
    text.append(",");
    text.append(",");
    text.append("sentDate=");
    text.append(sentDate == null ? "NULL" : formatDate.format(sentDate));
    text.append(",");
    text.append("paidDate=");
    text.append(paidDate == null ? "NULL" : formatDate.format(paidDate));
    text.append(",");
    return text.toString();
  }

  public String getSubcontractInvoiceNumber()
  {
    if (value.compareTo(new BigDecimal(0)) >= 0)
    {
      // Positive value: Invoice.
      return "S" + String.format("%06d", subcontractInvoiceId);
    }
    else
    {
      // Negative value: Credit Note.
      return "C" + String.format("%06d", subcontractInvoiceId * -1);
    }
  }

  public String getRelatedSubcontractInvoiceNumber()
  {
    if (relatedSubcontractInvoiceId == null || relatedSubcontractInvoiceId == 0)
    {
      return "";
    }
    else
    {
      if (relatedSubcontractInvoiceId > 0)
      {
        // Positive value: Invoice.
        return "S" + String.format("%06d", relatedSubcontractInvoiceId);
      }
      else
      {
        // Negative value: Credit Note.
        return "C" + String.format("%06d", (relatedSubcontractInvoiceId * -1));
      }
    }    
  }

  public String getSubcontractInvoiceFileName()
  {
    return getSubcontractInvoiceNumber() + ".pdf";
  }

  public String getRelatedSubcontractInvoiceFileName()
  {
    return getRelatedSubcontractInvoiceNumber() + ".pdf";
  }

}
