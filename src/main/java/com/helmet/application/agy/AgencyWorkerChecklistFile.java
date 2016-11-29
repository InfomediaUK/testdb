package com.helmet.application.agy;

import java.util.Date;

import com.helmet.application.FileHandler;

public class AgencyWorkerChecklistFile
{
  private String filename;
  private long lastModified;
  public AgencyWorkerChecklistFile(String filename, long lastModified)
  {
    super();
    this.filename = filename;
    this.lastModified = lastModified;
  }

  public String getFilename()
  {
    return filename;
  }

  public void setFilename(String filename)
  {
    this.filename = filename;
  }

  public long getLastModified()
  {
    return lastModified;
  }

  public void setLastModified(long lastModified)
  {
    this.lastModified = lastModified;
  }

  public Integer getApplicantId()
  {
    int indexOfFirstHyphen = filename.indexOf("-");
    String applicantId = filename.substring("checklist".length(), indexOfFirstHyphen);
    return Integer.valueOf(applicantId);
  }
  
  public Integer getBookingId()
  {
    int indexOfFirstHyphen = filename.indexOf("-");
    int indexOfSecondHyphen = filename.indexOf("-", indexOfFirstHyphen + 1);
    String bookingId = filename.substring(indexOfFirstHyphen + 1, indexOfSecondHyphen);
    return Integer.valueOf(bookingId);
  }
  
  public Integer getBookingGradeApplicantId()
  {
    int indexOfFirstHyphen = filename.indexOf("-");
    int indexOfSecondHyphen = filename.indexOf("-", indexOfFirstHyphen + 1);
    String bookingGradeApplicantId = filename.substring(indexOfSecondHyphen + 1, filename.indexOf("["));
    return Integer.valueOf(bookingGradeApplicantId);
  }
  
  public String getClientName()
  {
    String clientName = filename.substring(filename.indexOf("[") + 1, filename.indexOf("]"));
    return clientName;
  }
  
  public Date getFileDate()
  {
    return new Date(lastModified);
  }

  public String getChecklistFileUrl()
  {
    return FileHandler.getInstance().getApplicantFileFolder() + "/" + getApplicantId() + "/" + filename; 
  }

  public String getChecklistFilePath()
  {
    return FileHandler.getInstance().getApplicantFileLocation() + getChecklistFileUrl(); 
  }

  public boolean getCurrent()
  {
    // No .bak or .del part present.
    return filename.endsWith("].000.pdf");
  }
  
  public int getBackupNumber()
  {
    // File will end with ].000.pdf or ].nnn.bak.pdf or ].nnn.del.pdf
    int start = filename.indexOf("]") + 2;
    int end = filename.indexOf("]") + 5;
    String number = filename.substring(start, end);
    return number.equals("") ? 0 : Integer.valueOf(number);
  }
}
