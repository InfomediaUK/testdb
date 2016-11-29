package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SubcontractInvoice extends Base
{
  public final static int SUBCONTRACTINVOICE_STATUS_SENT     = 0;
  public final static int SUBCONTRACTINVOICE_STATUS_UNPAID   = 1;
  public final static int SUBCONTRACTINVOICE_STATUS_PAID     = 2;
  public final static int SUBCONTRACTINVOICE_STATUS_CREDITED = 3;
  public final static int SUBCONTRACTINVOICE_STATUS_PAYMENT_PENDING = 4;

  public final static String[] SUBCONTRACTINVOICE_STATUS_DESCRIPTION_KEYS = 
  {
    "subcontractInvoice.status.sent",
    "subcontractInvoice.status.unpaid",
    "subcontractInvoice.status.paid",
    "subcontractInvoice.status.credited",
    "subcontractInvoice.status.payment_pending"
  };

  private Integer subcontractInvoiceId;
  private Integer fromAgencyId;
  private Integer toAgencyId;
  private Date date;
  private Integer clientId;
  private Integer siteId;
  private Integer locationId;
  private Integer jobProfileId;
  private Date startDate;
  private Date endDate;
  private BigDecimal value = new BigDecimal(0);
  private String notes;
  private Date sentDate;
  private Date paidDate;
  private Integer relatedSubcontractInvoiceId;
  private String remittanceAdvice;
  private transient Boolean remittanceAdviceIsFile = new Boolean(false);
  
  public Integer getFromAgencyId()
  {
    return fromAgencyId;
  }

  public void setFromAgencyId(Integer fromAgencyId)
  {
    this.fromAgencyId = fromAgencyId;
  }

  public Integer getToAgencyId()
  {
    return toAgencyId;
  }

  public void setToAgencyId(Integer toAgencyId)
  {
    this.toAgencyId = toAgencyId;
  }

  public Date getDate()
  {
    return date;
  }


  public void setDate(Date date)
  {
    this.date = date;
  }


  public Integer getJobProfileId()
  {
    return jobProfileId;
  }


  public void setJobProfileId(Integer jobProfileId)
  {
    this.jobProfileId = jobProfileId;
  }


  public Integer getLocationId()
  {
    return locationId;
  }


  public void setLocationId(Integer locationId)
  {
    this.locationId = locationId;
  }


  public Integer getSubcontractInvoiceId()
  {
    return subcontractInvoiceId;
  }


  public void setSubcontractInvoiceId(Integer subcontractInvoiceId)
  {
    this.subcontractInvoiceId = subcontractInvoiceId;
  }

  public Integer getClientId()
  {
    return clientId;
  }

  public void setClientId(Integer clientId)
  {
    this.clientId = clientId;
  }

  public Integer getSiteId()
  {
    return siteId;
  }


  public void setSiteId(Integer siteId)
  {
    this.siteId = siteId;
  }


  public Date getStartDate()
  {
    return startDate;
  }


  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }


  public Date getEndDate()
  {
    return endDate;
  }

  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }

  public BigDecimal getValue()
  {
    return value;
  }

  public void setValue(BigDecimal value)
  {
    this.value = value;
  }

  public String getNotes()
  {
    return notes;
  }

  public void setNotes(String notes)
  {
    this.notes = notes;
  }

  public void appendNotes(String notes)
  {
    if (StringUtils.isEmpty(this.notes))
    {
      this.notes = notes;
    }
    else
    {
      this.notes = this.notes + " \n" + notes;
    }
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

  public Integer getRelatedSubcontractInvoiceId()
  {
    return relatedSubcontractInvoiceId;
  }

  public void setRelatedSubcontractInvoiceId(Integer relatedSubcontractInvoiceId)
  {
    this.relatedSubcontractInvoiceId = relatedSubcontractInvoiceId;
  }

  public String getRemittanceAdvice()
  {
    return remittanceAdvice;
  }

  public void setRemittanceAdvice(String remittanceAdvice)
  {
    this.remittanceAdvice = remittanceAdvice;
  }

  public Boolean getRemittanceAdviceIsFile()
  {
    return remittanceAdviceIsFile;
  }

  public void setRemittanceAdviceIsFile(Boolean remittanceAdviceIsFile)
  {
    this.remittanceAdviceIsFile = remittanceAdviceIsFile;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setSubcontractInvoiceId(rs.getInt("SUBCONTRACTINVOICEID"));
    setDate(rs.getDate("DATE"));
    setFromAgencyId(rs.getInt("FROMAGENCYID"));
    setToAgencyId(rs.getInt("TOAGENCYID"));
    setClientId(rs.getInt("CLIENTID"));
    setSiteId(rs.getInt("SITEID"));
    setLocationId(rs.getInt("LOCATIONID"));
    setJobProfileId(rs.getInt("JOBPROFILEID"));
    setStartDate(rs.getDate("STARTDATE"));
    setEndDate(rs.getDate("ENDDATE"));
    setValue(rs.getBigDecimal("VALUE"));
    setNotes(rs.getString("NOTES"));
    setSentDate(rs.getDate("SENTDATE"));
    setPaidDate(rs.getDate("PAIDDATE"));
    setRelatedSubcontractInvoiceId(rs.getInt("RELATEDSUBCONTRACTINVOICEID"));
    setRemittanceAdvice(rs.getString("REMITTANCEADVICE"));
  }

  public String toString()
  {
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
    formatDate.setLenient(false);
    StringBuffer text = new StringBuffer();
    text.append("fromAgencyId=");
    text.append(fromAgencyId);
    text.append(",");
    text.append("toAgencyId=");
    text.append(toAgencyId);
    text.append(",");
    text.append("date=");
    text.append(date == null ? "NULL" : formatDate.format(date));
    text.append(",");
    text.append("clientId=");
    text.append(clientId);
    text.append(",");
    text.append("siteId=");
    text.append(siteId);
    text.append(",");
    text.append("locationId=");
    text.append(locationId);
    text.append(",");
    text.append("jobProfileId=");
    text.append(jobProfileId);
    text.append(",");
    text.append("startDate=");
    text.append(formatDate.format(startDate));
    text.append(",");
    text.append("endDate=");
    text.append(formatDate.format(endDate));
    text.append(",");
    text.append("value=");
    text.append(value);
    text.append(",");
    text.append("notes=");
    text.append(notes);
    text.append(",");
    text.append("sentDate=");
    text.append(sentDate == null ? "NULL" : formatDate.format(sentDate));
    text.append(",");
    text.append("paidDate=");
    text.append(paidDate == null ? "NULL" : formatDate.format(paidDate));
    text.append(",");
    text.append("remittanceAdvice=");
    text.append(remittanceAdvice);
    return text.toString();
  }

  public String getReportGroupKey()
  {
    return clientId + "," + siteId + "," + locationId + "," + jobProfileId; 
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
