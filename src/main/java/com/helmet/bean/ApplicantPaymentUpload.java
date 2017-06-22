package com.helmet.bean;

public class ApplicantPaymentUpload extends NhsBooking
{
  private String uploadStaffName;
  private boolean validStaffName;
  private String uploadDate;
  private boolean validDate;
  private String uploadStart;
  private boolean validStart;
  private String uploadEnd;
  private boolean validEnd;
  private String uploadLocation;
  private boolean validLocation;
  private String uploadWard;
  private boolean validWard;
  private String uploadAssignment;
  private boolean validAssignment;
  
  public String getUploadStaffName()
  {
    return uploadStaffName;
  }

  public void setUploadStaffName(String uploadStaffName)
  {
    this.uploadStaffName = uploadStaffName;
  }

  public boolean isValidStaffName()
  {
    return validStaffName;
  }

  public void setValidStaffName(boolean validStaffName)
  {
    this.validStaffName = validStaffName;
  }

  public boolean getBothTimesValid()
  {
    return (validStart && validEnd);
  }

  public String getUploadEnd()
  {
    return uploadEnd;
  }

  public void setUploadEnd(String uploadEnd)
  {
    this.uploadEnd = uploadEnd;
  }

  public boolean isValidEnd()
  {
    return validEnd;
  }

  public void setValidEnd(boolean validEnd)
  {
    this.validEnd = validEnd;
  }

  public String getUploadDate()
  {
    return uploadDate;
  }

  public void setUploadDate(String uploadDate)
  {
    this.uploadDate = uploadDate;
  }

  public boolean isValidDate()
  {
    return validDate;
  }

  public void setValidDate(boolean validDate)
  {
    this.validDate = validDate;
  }

  public String getUploadStart()
  {
    return uploadStart;
  }

  public void setUploadStart(String uploadStart)
  {
    this.uploadStart = uploadStart;
  }

  public boolean isValidStart()
  {
    return validStart;
  }

  public void setValidStart(boolean validStart)
  {
    this.validStart = validStart;
  }

  public String getUploadLocation()
  {
    return uploadLocation;
  }

  public void setUploadLocation(String uploadLocation)
  {
    this.uploadLocation = uploadLocation;
  }

  public boolean getValidLocation()
  {
    return validLocation;
  }

  public void setValidLocation(boolean validLocation)
  {
    this.validLocation = validLocation;
  }

  public String getUploadWard()
  {
    return uploadWard;
  }

  public void setUploadWard(String uploadWard)
  {
    this.uploadWard = uploadWard;
  }

  public boolean getValidWard()
  {
    return validWard;
  }

  public void setValidWard(boolean validWard)
  {
    this.validWard = validWard;
  }

  public String getUploadAssignment()
  {
    return uploadAssignment;
  }

  public void setUploadAssignment(String uploadAssignment)
  {
    this.uploadAssignment = uploadAssignment;
  }

  public boolean getValidAssignment()
  {
    return validAssignment;
  }

  public void setValidAssignment(boolean validAssignment)
  {
    this.validAssignment = validAssignment;
  }

  public Boolean getValid()
  {
    return validStaffName && validDate && validStart && validEnd && validLocation & validWard && validAssignment;
  }
}
