package com.helmet.bean.nhs;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.helmet.bean.NhsBackingReport;

public class BackingReport
{
  private String fileName;
  private Integer clientId;
  private Boolean clientInvalid;
  private Boolean agencyHasSubcontractors;
  private String name;
  private Boolean nameInvalid;
  private String trust;
  private String hospital;
  private String ward;
  private BackingReportColumnHeadings backingReportColumnHeadings;
  private BigDecimal totalCommission;
  private BigDecimal totalCost;
  private Boolean grandTotalCostInvalid;
  private Integer subcontractAgencyId;
  private List<BackingReportError> errors;
  private List<BackingReportLine> backingReportLines;
  private NhsBackingReport nhsBackingReport;
  private Boolean valid;
  private Boolean canAccept;
  private Integer errorCount;
  
  public BackingReport(String fileName)
  {
    this.fileName = fileName;
    errors = new ArrayList<BackingReportError>();
    valid = true;
    canAccept = true;
    errorCount = 0;
  }

  public String getFileName()
  {
    return fileName;
  }

  public Integer getClientId()
  {
    return clientId;
  }

  public void setClientId(Integer clientId)
  {
    this.clientId = clientId;
  }

  public Boolean getClientInvalid()
  {
    return clientInvalid;
  }

  public void setClientInvalid(BackingReportError errorMessage)
  {
    clientInvalid = false;
    addError(errorMessage);
    setValid(false);
    canAccept = false;
  }

  public void setAgencyInvalid(BackingReportError errorMessage)
  {
    // Name set here deliberately.
    nameInvalid = false;
    addError(errorMessage);
    setValid(false);
    canAccept = false;
  }

  public Boolean getAgencyHasSubcontractors()
  {
    return agencyHasSubcontractors;
  }

  public void setAgencyHasSubcontractors(Boolean agencyHasSubcontractors)
  {
    this.agencyHasSubcontractors = agencyHasSubcontractors;
  }

  public void setBookingReportCompleted(BackingReportError errorMessage)
  {
    // Name set here deliberately.
    nameInvalid = false;
    addError(errorMessage);
    setValid(false);
    canAccept = false;
  }

  public void setHasDocumentation(BackingReportError errorMessage)
  {
    // Name set here deliberately.
    nameInvalid = false;
    addError(errorMessage);
    setValid(false);
  }

  public void setDocumentationAlreadySentToTradeshift(BackingReportError errorMessage)
  {
    // Name set here deliberately.
    nameInvalid = false;
    addError(errorMessage);
    setValid(false);
    canAccept = false;
  }

  public void setNumberOfLinesDiffer(BackingReportError errorMessage)
  {
    // Name set here deliberately.
    nameInvalid = false;
    addError(errorMessage);
    setValid(false);
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Boolean getNameInvalid()
  {
    return nameInvalid;
  }

  public void setNameInvalid(BackingReportError errorMessage)
  {
    nameInvalid = false;
    addError(errorMessage);
    setValid(false);
  }

  public String getTrust()
  {
    return trust;
  }

  public void setTrust(String trust)
  {
    this.trust = trust;
  }

  public String getHospital()
  {
    return hospital;
  }

  public void setHospital(String hospital)
  {
    this.hospital = hospital;
  }

  public String getWard()
  {
    return ward;
  }

  public void setWard(String ward)
  {
    this.ward = ward;
  }

  public BackingReportColumnHeadings getBackingReportColumnHeadings()
  {
    return backingReportColumnHeadings;
  }

  public void setBackingReportColumnHeadings(BackingReportColumnHeadings backingReportColumnHeadings)
  {
    this.backingReportColumnHeadings = backingReportColumnHeadings;
  }

  public BigDecimal getTotalCommission()
  {
    return totalCommission;
  }

  public void setTotalCommission(BigDecimal totalCommission)
  {
    this.totalCommission = totalCommission;
  }

  public BigDecimal getTotalCost()
  {
    return totalCost;
  }

  public void setTotalCost(BigDecimal totalCost)
  {
    this.totalCost = totalCost;
  }

  public BigDecimal getGrandTotalCost()
  {
    return totalCommission.add(totalCost);
  }

  public Boolean getGrandTotalCostInvalid()
  {
    return grandTotalCostInvalid;
  }

  public void setGrandTotalCostInvalid(BackingReportError errorMessage)
  {
    grandTotalCostInvalid = false;
    addError(errorMessage);
    setValid(false);
  }

  public Integer getSubcontractAgencyId()
  {
    return subcontractAgencyId;
  }

  public void setSubcontractAgencyId(Integer subcontractAgencyId)
  {
    this.subcontractAgencyId = subcontractAgencyId;
  }

  public List<BackingReportLine> getBackingReportLines()
  {
    return backingReportLines;
  }

  public void setBackingReportLines(List<BackingReportLine> backingReportLines)
  {
    this.backingReportLines = backingReportLines;
  }

  public NhsBackingReport getNhsBackingReport()
  {
    return nhsBackingReport;
  }

  public void setNhsBackingReport(NhsBackingReport nhsBackingReport)
  {
    this.nhsBackingReport = nhsBackingReport;
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

  public void zeroiseCommission()
  {
    for (BackingReportLine backingReportLine : backingReportLines)
    {
      backingReportLine.setCommission(new BigDecimal(0));
    }
  }
  
  public void recalculateMoneyTotals()
  {
    totalCommission = new BigDecimal(0);
    totalCost = new BigDecimal(0);
    for (BackingReportLine backingReportLine : backingReportLines)
    {
      if (backingReportLine.notAlreadyInvoiced())
      {
        // This BackingReport Line has NOT already been invoiced. Total it up...
        totalCommission = totalCommission.add(backingReportLine.getCommission());
        totalCost = totalCost.add(backingReportLine.getTotalCost());
      }
    }
  }

  public Boolean getHasRejectedLines()
  {
    Boolean rejected = new Boolean(false);
    for (BackingReportLine backingReportLine : backingReportLines)
    {
      if (backingReportLine.getAlreadyInvoiced())
      {
        // This BackingReport Line has already been invoiced.
        rejected = true;
        break;
      }
      if (getAgencyHasSubcontractors() && backingReportLine.getAgencyWorkerNameNotValid())
      {
        // 4SW and This BackingReport Line has already been invoiced.
        rejected = true;
        break;
      }
    }
    return rejected;
  }
  
  public Date getStartDate()
  {
    Date startDate = backingReportLines.get(0).getDate();
    for (BackingReportLine backingReportLine : backingReportLines)
    {
      if (backingReportLine.getDate().before(startDate))
      {
        startDate = backingReportLine.getDate();
      }
    }
    return startDate;
  }
  
  public Date getEndDate()
  {
    Date endDate = backingReportLines.get(0).getDate();
    for (BackingReportLine backingReportLine : backingReportLines)
    {
      if (backingReportLine.getDate().after(endDate))
      {
        endDate = backingReportLine.getDate();
      }
    }
    return endDate;
  }
  
  private void addError(BackingReportError error)
  {
    errors.add(error);
  }
  
  public List<BackingReportError> getErrors()
  {
    return errors;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("BackingReport:");
    result.append("\n");
    result.append("fileName=");
    result.append(fileName);
    result.append("\n\n");
    result.append("name=");
    result.append(name);
    result.append("\n\n");
    result.append("trust=");
    result.append(trust);
    result.append("\n\n");
    result.append("hospital=");
    result.append(hospital);
    result.append("\n\n");
    result.append("ward=");
    result.append(ward);
    result.append("\n\n");
    for (BackingReportLine backingReportLine : backingReportLines)
    {
      result.append(backingReportLine.toString());
    }
    result.append("\n");
    result.append("totalCommission=");
    result.append(totalCommission);
    result.append("\n");
    result.append("totalCost=");
    result.append(totalCost);
    result.append("\n");
    result.append("grandTotalCost=");
    result.append(getGrandTotalCost());
    result.append("\n");
    return result.toString();
  }
  
}
