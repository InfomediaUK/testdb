package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class NhsBackingReport extends Base
{

  private Integer nhsBackingReportId;
  private Integer agencyId;
  private Integer clientId;
  private String name;
  private Date startDate;
  private Date endDate;
  private BigDecimal agreedValue      = new BigDecimal(0);
  private BigDecimal vatRate          = new BigDecimal(0);
  private BigDecimal paidValue        = new BigDecimal(0);
  private BigDecimal subcontractValue = new BigDecimal(0);
  private Date subcontractPaidDate;
  private Date subcontractDocumentationSentDate;
  private Integer subcontractAgencyId;
  private Date completeDate;
  private String comment;
  private String documentationFileName;
  private String rejectedDocumentationFileName;
  private String tradeshiftDocumentId;

  public Integer getNhsBackingReportId()
  {
    return nhsBackingReportId;
  }

  public void setNhsBackingReportId(Integer nhsBackingReportId)
  {
    this.nhsBackingReportId = nhsBackingReportId;
  }

  public Integer getAgencyId()
  {
    return agencyId;
  }

  public void setAgencyId(Integer agencyId)
  {
    this.agencyId = agencyId;
  }

  public Integer getClientId()
  {
    return clientId;
  }

  public void setClientId(Integer clientId)
  {
    this.clientId = clientId;
  }

  public Date getCompleteDate()
  {
    return completeDate;
  }

  public void setCompleteDate(Date completeDate)
  {
    this.completeDate = completeDate;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public void appendComment(String comment)
  {
    if (StringUtils.isEmpty(this.comment))
    {
      this.comment = comment;
    }
    else
    {
      this.comment = this.comment + " \n" + comment;
    }
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }

  public BigDecimal getPaidValue()
  {
    return paidValue;
  }

  public void setPaidValue(BigDecimal paidValue)
  {
    this.paidValue = paidValue;
  }

//  public void setPaidValue(String paidValue)
//  {
//    if (paidValue == null)
//    {
//      this.paidValue = new BigDecimal(0);
//    }
//    else
//    {
//      this.paidValue = BigDecimal.valueOf(Long.parseLong(paidValue));
//    }
//  }
//
  public BigDecimal getAgreedValue()
  {
    return agreedValue;
  }

  public void setAgreedValue(BigDecimal agreedValue)
  {
    this.agreedValue = agreedValue;
  }

  public BigDecimal getVatRate()
  {
    return vatRate;
  }

  public BigDecimal getSubcontractValue()
  {
    return subcontractValue;
  }

  public void setSubcontractValue(BigDecimal subcontractValue)
  {
    this.subcontractValue = subcontractValue;
  }

  public Date getSubcontractPaidDate()
  {
    return subcontractPaidDate;
  }

  public void setSubcontractPaidDate(Date subcontractPaidDate)
  {
    this.subcontractPaidDate = subcontractPaidDate;
  }

  public Date getSubcontractDocumentationSentDate()
  {
    return subcontractDocumentationSentDate;
  }

  public void setSubcontractDocumentationSentDate(Date subcontractDocumentationSentDate)
  {
    this.subcontractDocumentationSentDate = subcontractDocumentationSentDate;
  }

  public Integer getSubcontractAgencyId()
  {
    return subcontractAgencyId;
  }

  public void setSubcontractAgencyId(Integer subcontractAgencyId)
  {
    this.subcontractAgencyId = subcontractAgencyId;
  }

  public void setVatRate(BigDecimal vatRate)
  {
    this.vatRate = vatRate;
  }

  public String getDocumentationFileName()
  {
    return documentationFileName;
  }

  public void setDocumentationFileName(String documetationFileName)
  {
    this.documentationFileName = documetationFileName;
  }

  public String getRejectedDocumentationFileName()
  {
    return rejectedDocumentationFileName;
  }

  public void setRejectedDocumentationFileName(String rejectedDocumentationFileName)
  {
    this.rejectedDocumentationFileName = rejectedDocumentationFileName;
  }

  public String getTradeshiftDocumentId()
  {
    return tradeshiftDocumentId;
  }

  public void setTradeshiftDocumentId(String tradeshiftDocumentId)
  {
    this.tradeshiftDocumentId = tradeshiftDocumentId;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setNhsBackingReportId(rs.getInt("NHSBACKINGREPORTID"));
    setAgencyId(rs.getInt("AGENCYID"));
    setClientId(rs.getInt("CLIENTID"));
    setName(rs.getString("NAME"));
    setAgreedValue(rs.getBigDecimal("AGREEDVALUE"));
//    setVatRate(rs.getBigDecimal("VATRATE"));
    setPaidValue(rs.getBigDecimal("PAIDVALUE"));
    setSubcontractValue(rs.getBigDecimal("SUBCONTRACTVALUE"));
    setSubcontractPaidDate(rs.getDate("SUBCONTRACTPAIDDATE"));
    setSubcontractDocumentationSentDate(rs.getDate("SUBCONTRACTDOCUMENTATIONSENTDATE"));
    setSubcontractAgencyId(rs.getInt("SUBCONTRACTAGENCYID"));
    setStartDate(rs.getDate("STARTDATE"));
    setEndDate(rs.getDate("ENDDATE"));
    setCompleteDate(rs.getDate("COMPLETEDATE"));
    setComment(rs.getString("COMMENT"));
    setDocumentationFileName(rs.getString("DOCUMENTATIONFILENAME"));
    setRejectedDocumentationFileName(rs.getString("REJECTEDDOCUMENTATIONFILENAME"));
    setTradeshiftDocumentId(rs.getString("TRADESHIFTDOCUMENTID"));
  }

  public String toString()
  {
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
    StringBuffer text = new StringBuffer();
    text.append("name=");
    text.append(name);
    text.append(",");
    text.append("start time=");
    text.append(startDate == null ? "NULL" : formatDate.format(startDate));
    text.append(",");
    text.append("end time=");
    text.append(endDate == null ? "NULL" : formatDate.format(endDate));
    text.append(",");
    text.append(completeDate == null ? "NULL" : formatDate.format(completeDate));
    text.append(",");
    text.append("end time=");
    text.append("agreedValue=");
    text.append(agreedValue);
    text.append(",");
    text.append("paidValue=");
    text.append(paidValue);
    text.append(",");
    text.append("subcontractValue=");
    text.append(subcontractValue);
    text.append(",");
    text.append(subcontractPaidDate == null ? "NULL" : formatDate.format(subcontractPaidDate));
    text.append(",");
    text.append(subcontractDocumentationSentDate == null ? "NULL" : formatDate.format(subcontractDocumentationSentDate));
    return text.toString();
  }
}
