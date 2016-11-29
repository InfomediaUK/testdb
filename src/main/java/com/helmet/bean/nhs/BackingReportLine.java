package com.helmet.bean.nhs;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BackingReportLine
{
  private BackingReport backingReport;
  private Date date;
  private Boolean dateValid;
  private String bankReqNum;
  private Boolean bankReqNumValid;
  private String agencyWorkerName;
  private Boolean agencyWorkerNameValid;
  private String agencyWorkerUniqueId;
  private String trust;
  private String hospital;
  private Boolean hospitalValid;
  private String ward;
  private Boolean wardValid;
  private String assignment;
  private Boolean assignmentValid;
  private BackingReportShift contractShift;
  private Boolean contractStartTimeValid;
  private Boolean contractEndTimeValid;
  private BackingReportShift actualShift;
  private Boolean actualStartTimeValid;
  private Boolean actualEndTimeValid;
  private BigDecimal commission;
  private BigDecimal totalCost;
  private String rate;
  private Boolean bookingValid;
  private Integer bookingId;
  private Integer bookingGradeId;
  private Integer bookingDateId;
  private Integer applicantOriginalAgencyId;
  private List<BackingReportLineError> errors;
  private Boolean alreadyInvoiced;
  private String subcontractInvoiceNumber;
  private Boolean totalCostChanged;
  
  public BackingReportLine(BackingReport backingReport)
  {
    super();
    this.backingReport = backingReport;
    errors = new ArrayList<BackingReportLineError>();
    dateValid = true;
    bankReqNumValid = true;
    agencyWorkerNameValid = true;
    hospitalValid = true;
    wardValid = true;
    assignmentValid = true;
    contractStartTimeValid = true;
    contractEndTimeValid = true;
    actualStartTimeValid = true;
    actualEndTimeValid = true;
    bookingValid = true;
    alreadyInvoiced = false;
    totalCostChanged = false;
  }

  public BackingReport getBackingReport()
  {
    return backingReport;
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

  public void setDateInvalid(BackingReportLineError errorMessage)
  {
    dateValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
  }

  public String getBankReqNum()
  {
    return bankReqNum;
  }

  public void setBankReqNum(String bankReqNum)
  {
    this.bankReqNum = bankReqNum;
  }

  public Boolean getBankReqNumValid()
  {
    return bankReqNumValid;
  }

  public void setBankReqNumInvalid(BackingReportLineError errorMessage)
  {
    bankReqNumValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
    backingReport.setCanAccept(false);
  }

  public String getAgencyWorkerName()
  {
    return agencyWorkerName;
  }

  public void setAgencyWorkerName(String agencyWorkerName)
  {
    this.agencyWorkerName = agencyWorkerName;
  }

  public Boolean getAgencyWorkerNameValid()
  {
    return agencyWorkerNameValid;
  }

  public Boolean getAgencyWorkerNameNotValid()
  {
    return !agencyWorkerNameValid;
  }

  public void setAgencyWorkerNameCaseChanged(BackingReportLineError errorMessage)
  {
    addError(errorMessage);
    backingReport.setValid(false);
  }

  public void setAgencyWorkerNameInvalid(BackingReportLineError errorMessage)
  {
    agencyWorkerNameValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
    if (backingReport.getAgencyHasSubcontractors().equals(false))
    {
      // NOT 4SW - Agency does NOT have subcontractors so Agency Worker Name is INVALID.
      backingReport.setCanAccept(false);
    }
  }

  public String getAgencyWorkerUniqueId()
  {
    return agencyWorkerUniqueId;
  }

  public void setAgencyWorkerUniqueId(String agencyWorkerUniqueId)
  {
    this.agencyWorkerUniqueId = agencyWorkerUniqueId;
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

  public Boolean getHospitalValid()
  {
    return hospitalValid;
  }

  public void setHospitalInvalid(BackingReportLineError errorMessage)
  {
    hospitalValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
    backingReport.setCanAccept(false);
  }

  public String getWard()
  {
    return ward;
  }

  public void setWard(String ward)
  {
    this.ward = ward;
  }

  public Boolean getWardValid()
  {
    return wardValid;
  }

  public void setWardInvalid(BackingReportLineError errorMessage)
  {
    wardValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
    backingReport.setCanAccept(false);
  }

  public String getAssignment()
  {
    return assignment;
  }

  public void setAssignment(String assignment)
  {
    this.assignment = assignment;
  }

  public Boolean getAssignmentValid()
  {
    return assignmentValid;
  }

  public void setAssignmentInvalid(BackingReportLineError errorMessage)
  {
    assignmentValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
    backingReport.setCanAccept(false);
  }

  public BackingReportShift getContractShift()
  {
    return contractShift;
  }

  public void setContractShift(BackingReportShift contractShift)
  {
    this.contractShift = contractShift;
  }

  public BackingReportShift getActualShift()
  {
    return actualShift;
  }

  public Boolean getContractStartTimeValid()
  {
    return contractStartTimeValid;
  }

  public void setContractStartTimeInvalid(BackingReportLineError errorMessage)
  {
    contractStartTimeValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
//    backingReport.setCanAccept(false);
  }

  public Boolean getContractEndTimeValid()
  {
    return contractEndTimeValid;
  }

  public void setContractEndTimeInvalid(BackingReportLineError errorMessage)
  {
    contractEndTimeValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
//    backingReport.setCanAccept(false);
  }

  public void setActualShift(BackingReportShift actualShift)
  {
    this.actualShift = actualShift;
  }

  public Boolean getActualStartTimeValid()
  {
    return actualStartTimeValid;
  }

  // Note. A difference in Actual Start Time CAN BE ACCEPTED.
  public void setActualStartTimeInvalid(BackingReportLineError errorMessage)
  {
    actualStartTimeValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
  }

  public Boolean getActualEndTimeValid()
  {
    return actualEndTimeValid;
  }

  // Note. A difference in Actual Start Time CAN BE ACCEPTED.
  public void setActualEndTimeInvalid(BackingReportLineError errorMessage)
  {
    actualEndTimeValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
  }

  public BigDecimal getCommission()
  {
    return commission;
  }

  public void setCommission(BigDecimal commission)
  {
    this.commission = commission;
  }

  public BigDecimal getTotalCost()
  {
    return totalCost;
  }

  public void setTotalCost(BigDecimal totalCost)
  {
    this.totalCost = totalCost;
  }

  public Boolean getTotalCostChanged()
  {
    return totalCostChanged;
  }

  public void setTotalCostChanged(Boolean totalCostChanged)
  {
    this.totalCostChanged = totalCostChanged;
  }

  public String getRate()
  {
    return rate;
  }

  public void setRate(String rate)
  {
    this.rate = rate;
  }

  public Integer getBookingId()
  {
    return bookingId;
  }

  public void setBookingId(Integer bookingId)
  {
    this.bookingId = bookingId;
  }

  // This is just a warning. Old Backing Reports will NOT have a NHS Booking.
  public void nhsBookingNotFound(BackingReportLineError errorMessage)
  {
    addError(errorMessage);
  }
  
  public void setBookingInvalid(BackingReportLineError errorMessage)
  {
    bookingValid = false;
    addError(errorMessage);
    backingReport.setValid(false);
    backingReport.setCanAccept(false);
  }

  public Boolean getBookingValid()
  {
    return bookingValid;
  }

  public Integer getBookingGradeId()
  {
    return bookingGradeId;
  }

  public void setBookingGradeId(Integer bookingGradeId)
  {
    this.bookingGradeId = bookingGradeId;
  }

  public Integer getBookingDateId()
  {
    return bookingDateId;
  }

  public void setBookingDateId(Integer bookingDateId)
  {
    this.bookingDateId = bookingDateId;
  }

  public Integer getApplicantOriginalAgencyId()
  {
    return applicantOriginalAgencyId;
  }

  public void setApplicantOriginalAgencyId(Integer applicantOriginalAgencyId)
  {
    this.applicantOriginalAgencyId = applicantOriginalAgencyId;
    backingReport.setSubcontractAgencyId(applicantOriginalAgencyId);
  }

  public Boolean getAlreadyInvoiced()
  {
    return alreadyInvoiced;
  }

  public void setAlreadyInvoiced(Boolean alreadyInvoiced)
  {
    this.alreadyInvoiced = alreadyInvoiced;
  }

  public String getSubcontractInvoiceNumber()
  {
    return subcontractInvoiceNumber;
  }

  public void setSubcontractInvoiceNumber(String subcontractInvoiceNumber)
  {
    this.subcontractInvoiceNumber = subcontractInvoiceNumber;
  }

  public Boolean notAlreadyInvoiced()
  {
    return !alreadyInvoiced;
  }

  public void alreadyInvoiced(BackingReportLineError errorMessage)
  {
    alreadyInvoiced = true;
    addError(errorMessage);
  }

  public Boolean getValid()
  {
    return errors.size() == 0;
  }

  private void addError(BackingReportLineError error)
  {
    errors.add(error);
  }
  
  public List<BackingReportLineError> getErrors()
  {
    return errors;
  }

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    result.append("BackingReportLine:");
    result.append("\n");
    result.append("date=");
    result.append(dateFormat.format(date));
    result.append("\n");
    result.append("bankReqNum=");
    result.append(bankReqNum);
    result.append("\n");
    result.append("agencyWorkerName=");
    result.append(agencyWorkerName);
    if (!agencyWorkerNameValid)
    {
      result.append("  *** INVALID ***");
    }
    result.append("\n");
    result.append("agencyWorkerUniqueId=");
    result.append(agencyWorkerUniqueId);
    result.append("\n");
    result.append("trust=");
    result.append(trust);
    result.append("\n");
    result.append("hospital=");
    result.append(hospital);
    result.append("\n");
    result.append("ward=");
    result.append(ward);
    result.append("\n");
    result.append("assignment=");
    result.append(assignment);
    result.append("\n");
    result.append("contractShift=(");
    result.append(contractShift.toString());
    result.append(")\n");
    result.append("actualShift=(");
    result.append(actualShift.toString());
    result.append(")\n");
    result.append("commission=");
    result.append(commission);
    result.append("\n");
    result.append("totalCost=");
    result.append(totalCost);
    result.append("\n");
    result.append("rate=");
    result.append(rate);
    result.append("\n\n");
    return result.toString();
  }
  
}
