package com.helmet.bean.nhs;

import org.apache.commons.lang3.StringUtils;

public class BackingReportColumnHeadings
{
  private String date;
  private String refNum;
  private String agencyWorkerName;
  private String trust;
  private String ward;
  private String assignment;
  private String contract;
  private String actual;
  private String contractStart;
  private String contractEnd;
  private String contractBreakInMinutes;
  private String contractTotal;
  private String actualStart;
  private String actualEnd;
  private String actualBreakInMinutes;
  private String actualTotal;
  private String commission;
  private String totalCost;
  private String rate;

  public String getActual()
  {
    return actual;
  }

  public void setActual(String actual)
  {
    this.actual = actual;
  }

  public String getAgencyWorkerName()
  {
    return agencyWorkerName;
  }

  public void setAgencyWorkerName(String agencyWorkerName)
  {
    this.agencyWorkerName = agencyWorkerName;
  }

  public String getAssignment()
  {
    return assignment;
  }

  public void setAssignment(String assignment)
  {
    this.assignment = assignment;
  }

  public String getContractBreakInMinutes()
  {
    return contractBreakInMinutes;
  }

  public void setContractBreakInMinutes(String breakInMinutes)
  {
    this.contractBreakInMinutes = breakInMinutes;
  }

  public String getDate()
  {
    return date;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public String getRefNum()
  {
    return refNum;
  }

  public void setRefNum(String refNum)
  {
    this.refNum = refNum;
  }

  public String getTrust()
  {
    return trust;
  }

  public void setTrust(String trust)
  {
    this.trust = trust;
  }

  public String getWard()
  {
    return ward;
  }

  public void setWard(String ward)
  {
    this.ward = ward;
  }

  public String getContract()
  {
    return contract;
  }

  public void setContract(String contract)
  {
    this.contract = contract;
  }

  public String getContractStart()
  {
    return contractStart;
  }

  public void setContractStart(String start)
  {
    this.contractStart = start;
  }

  public String getContractEnd()
  {
    return contractEnd;
  }

  public void setContractEnd(String end)
  {
    this.contractEnd = end;
  }

  public String getContractTotal()
  {
    return contractTotal;
  }

  public void setContractTotal(String total)
  {
    this.contractTotal = total;
  }

  public String getActualBreakInMinutes()
  {
    return actualBreakInMinutes;
  }

  public void setActualBreakInMinutes(String actualBreakInMinutes)
  {
    this.actualBreakInMinutes = actualBreakInMinutes;
  }

  public String getActualEnd()
  {
    return actualEnd;
  }

  public void setActualEnd(String actualEnd)
  {
    this.actualEnd = actualEnd;
  }

  public String getActualStart()
  {
    return actualStart;
  }

  public void setActualStart(String actualStart)
  {
    this.actualStart = actualStart;
  }

  public String getActualTotal()
  {
    return actualTotal;
  }

  public void setActualTotal(String actualTotal)
  {
    this.actualTotal = actualTotal;
  }

  public String getCommission()
  {
    return commission;
  }

  public void setCommission(String commission)
  {
    this.commission = commission;
  }

  public String getTotalCost()
  {
    return totalCost;
  }

  public void setTotalCost(String totalCost)
  {
    this.totalCost = totalCost;
  }

  public String getRate()
  {
    return rate;
  }

  public void setRate(String rate)
  {
    this.rate = rate;
  }

  public String validateColumnHeadings()
  {
    Boolean valid = new Boolean(true);
    if (StringUtils.isEmpty(date) ||
        StringUtils.isEmpty(refNum) ||
        StringUtils.isEmpty(agencyWorkerName) ||
        StringUtils.isEmpty(trust) ||
        StringUtils.isEmpty(ward) ||
        StringUtils.isEmpty(assignment) ||
        StringUtils.isEmpty(contract) ||
        StringUtils.isEmpty(actual) ||
        StringUtils.isEmpty(contractStart) ||
        StringUtils.isEmpty(contractEnd) ||
        StringUtils.isEmpty(contractBreakInMinutes) ||
        StringUtils.isEmpty(contractTotal) ||
        StringUtils.isEmpty(actualStart) ||
        StringUtils.isEmpty(actualEnd) ||
        StringUtils.isEmpty(actualBreakInMinutes) ||
        StringUtils.isEmpty(actualTotal) ||
        StringUtils.isEmpty(commission) ||
        StringUtils.isEmpty(totalCost) ||
        StringUtils.isEmpty(rate)
        )
    {
      valid = false;
    }
    return valid ? "" : toString();
  }
 
  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("BackingReportColumnHeadings 1st Line:");
    result.append("\n");
    result.append("date=");
    result.append(StringUtils.isEmpty(date)? "EMPTY" : date);
    result.append(",");
    result.append("refNum=");
    result.append(StringUtils.isEmpty(refNum)? "EMPTY" : refNum);
    result.append(",");
    result.append("agencyWorkerName=");
    result.append(StringUtils.isEmpty(agencyWorkerName)? "EMPTY" : agencyWorkerName);
    result.append(",");
    result.append("trust=");
    result.append(StringUtils.isEmpty(trust)? "EMPTY" : trust);
    result.append(",");
    result.append("ward=");
    result.append(StringUtils.isEmpty(ward)? "EMPTY" : ward);
    result.append(",");
    result.append("assignment=");
    result.append(StringUtils.isEmpty(assignment)? "EMPTY" : assignment);
    result.append(",");
    result.append("contract=");
    result.append(StringUtils.isEmpty(contract)? "EMPTY" : contract);
    result.append(",");
    result.append("actual=");
    result.append(StringUtils.isEmpty(actual)? "EMPTY" : actual);
    result.append(",");
    result.append("commission=");
    result.append(StringUtils.isEmpty(commission)? "EMPTY" : commission);
    result.append(",");
    result.append("totalCost=");
    result.append(StringUtils.isEmpty(totalCost)? "EMPTY" : totalCost);
    result.append(",");
    result.append("rate=");
    result.append(StringUtils.isEmpty(rate)? "EMPTY" : rate);
    result.append("\n");
    result.append("BackingReportColumnHeadings 2nd Line:");
    result.append("\n");
    result.append("contractStart=");
    result.append(StringUtils.isEmpty(contractStart)? "EMPTY" : contractStart);
    result.append(",");
    result.append("contractEnd=");
    result.append(StringUtils.isEmpty(contractEnd)? "EMPTY" : contractEnd);
    result.append(",");
    result.append("contractBreakInMinutes=");
    result.append(StringUtils.isEmpty(contractBreakInMinutes)? "EMPTY" : contractBreakInMinutes);
    result.append(",");
    result.append("contractTotal=");
    result.append(StringUtils.isEmpty(contractTotal)? "EMPTY" : contractTotal);
    result.append(",");
    result.append("actualStart=");
    result.append(StringUtils.isEmpty(actualStart)? "EMPTY" : actualStart);
    result.append(",");
    result.append("actualEnd=");
    result.append(StringUtils.isEmpty(actualEnd)? "EMPTY" : actualEnd);
    result.append(",");
    result.append("actualBreakInMinutes=");
    result.append(StringUtils.isEmpty(actualBreakInMinutes)? "EMPTY" : actualBreakInMinutes);
    result.append(",");
    result.append("actualTotal=");
    result.append(StringUtils.isEmpty(actualTotal)? "EMPTY" : actualTotal);
    return result.toString();
  }
}
