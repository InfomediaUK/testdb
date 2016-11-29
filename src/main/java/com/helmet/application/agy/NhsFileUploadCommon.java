package com.helmet.application.agy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.helmet.api.AgyService;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.nhs.BackingReport;
import com.helmet.bean.nhs.BackingReportLine;

public abstract class NhsFileUploadCommon extends AgyAction
{
  private static final String DATE_REGEX = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
  private static final String TIME_REGEX = "([0-9]{2}):([0-9]{2})";
  private String columnDelimeter;
  private String dateFormat;
  private String timeFormat = "HH:mm";
  private Integer useNumberOfColumns;

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());
  
  public static String getDATE_REGEX()
  {
    return DATE_REGEX;
  }

  public static String getTIME_REGEX()
  {
    return TIME_REGEX;
  }

  public String getColumnDelimeter()
  {
    return columnDelimeter;
  }

  public void setColumnDelimeter(String columnDelimeter)
  {
    this.columnDelimeter = columnDelimeter;
  }

  public String getDateFormat()
  {
    return dateFormat;
  }

  public void setDateFormat(String dateFormat)
  {
    this.dateFormat = dateFormat;
  }

  public String getTimeFormat()
  {
    return timeFormat;
  }

  public Integer getUseNumberOfColumns()
  {
    return useNumberOfColumns;
  }

  public void setUseNumberOfColumns(Integer useNumberOfColumns)
  {
    this.useNumberOfColumns = useNumberOfColumns;
  }

  protected void uploadFile(FormFile formFile, String filePath)
  {
    FileOutputStream fileOutputStream = null;
    // Read the InputStream and store it in a FileOutputStream.
    try
    {
      byte[] fileData = formFile.getFileData();

      File folder = new File(filePath).getParentFile();
      if (!folder.exists())
      {
        // Create any required directories.
        folder.mkdirs();
      }
      try
      {
        fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(fileData);
      }
      finally
      {
        fileOutputStream.close();
      }
    }
    catch (IOException e)
    {
      logger.error("IOException - uploading " + filePath);
    }
  }

  // For example, for NHS Bookings it looks like this:
  // COLUMN_DELIMETER=,
  // USE_NO_OF_COLUMNS=8
  // BankReqNum = 0
  // Staff Name = 1
  // Date = 2
  // Start = 3
  // End = 4
  // Location = 5
  // Ward = 6
  // Assignment = 7
  // Training = 8
  protected boolean columnNumbers(HashMap<String, Integer> columnMap, String columnNumbersFilePath, ActionMessages errors, String errorGroup, String errorMessagePrefix)
  {
    String line = "";
    String name = null;
    StringBuffer columnNumbers = new StringBuffer();
    BufferedReader br = null;
    try
    {
      try
      {
        br = new BufferedReader(new FileReader(columnNumbersFilePath + "/columnNumbers.txt"));
        while ((line = br.readLine()) != null)
        {
          columnNumbers.append(" ");
          columnNumbers.append(line);
          name = line.substring(0, line.indexOf("="));
          if (name.equalsIgnoreCase("COLUMN_DELIMETER"))
          {
            String value = line.substring(line.indexOf("=") + 1);
            columnDelimeter = value;
          }
          else
          {
            if (name.equalsIgnoreCase("DATE_FORMAT"))
            {
              String value = line.substring(line.indexOf("=") + 1);
              dateFormat = value;
            }
            else
            {
              Integer value = Integer.valueOf(line.substring(line.indexOf("=") + 1));
              if (name.equalsIgnoreCase("USE_NO_OF_COLUMNS"))
              {
                useNumberOfColumns = value;
              }
              else
              {
                columnMap.put(name, value);
              }
            }            
          }          
        }
        return true;
      }
      finally
      {
        if (br != null)
        {
          br.close();
        }        
      }
    }
    catch (FileNotFoundException e)
    {
      errors.add(errorGroup, new ActionMessage(errorMessagePrefix + ".notFound", columnNumbersFilePath));
      e.printStackTrace();
      return false;
    }
    catch (NumberFormatException e)
    {
      errors.add(errorGroup, new ActionMessage(errorMessagePrefix + ".numberFormatException", columnNumbers.toString(),  e.getMessage()));
      e.printStackTrace();
      return false;
    }
    catch (IOException e)
    {
      errors.add(errorGroup, new ActionMessage(errorMessagePrefix + ".ioException", e.getMessage()));
      e.printStackTrace();
      return false;
    }
  }

  protected DecimalFormat getDecimalFormat()
  {
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator(',');
    symbols.setDecimalSeparator('.');
    String pattern = "#,##0.0#";
    DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
    decimalFormat.setParseBigDecimal(true);
    return decimalFormat;
  }
  
  protected Boolean isTime(String s)
  {
    return s.matches(TIME_REGEX);
  }

  protected boolean isValidTime(String timeString)
  {
    SimpleDateFormat df = new SimpleDateFormat(getTimeFormat());
    // Only allow 24 hours.
    df.setLenient(false);
    try
    {
      df.parse(timeString);
      return true;
    }
    catch (ParseException e)
    {
      return false;
    }
  }

  protected Time getTime(SimpleDateFormat formatTime, String timeStr)
  {
    Time time = null;
    try
    {
      time = new Time(formatTime.parse(timeStr).getTime());
    }
    catch (ParseException e)
    {
      // Do Nothing...
    }
    return time;
  }

  protected Boolean isDate(String s)
  {
    return s.matches(DATE_REGEX);
  }
  
  protected boolean isValidDate(String dateString)
  {
    int hyphenCount = StringUtils.countMatches(dateString, "-");
    if (hyphenCount == 2)
    {
      String year = dateString.substring(dateString.lastIndexOf("-") + 1);
      if (year.length() == 4)
      {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        // Only allow correct number of days for the month.
        df.setLenient(false);
        try
        {
          df.parse(dateString);
          return true;
        }
        catch (ParseException e)
        {
          return false;
        }
      }      
      else
      {
        // Year NOT yyyy.
        return false;
      }
    }
    else
    {
      // Date NOT in format dd-MMM-yyyy.
      return false;
    }
  }

  protected Date getDate(SimpleDateFormat formatDate, String dateStr)
  {
    Date date = null;
    try
    {
      date = new Date(formatDate.parse(dateStr).getTime());
    }
    catch (ParseException e)
    {
      // Do Nothing...
    }
    return date;
  }

  protected Integer newNhsBackingReport(AgyService agyService, NhsBackingReport nhsBackingReport, BackingReport backingReport)
  {
    int rowCount = 0;
    if (nhsBackingReport.getNhsBackingReportId() == null)
    {
      // No NHS Backing Report has ever existed for this Backing Report Name.
      fillNhsBackingReport(nhsBackingReport, backingReport);
      nhsBackingReport.setNoOfChanges(0);
      rowCount = agyService.insertNhsBackingReport(nhsBackingReport, getConsultantLoggedIn().getConsultantId());
    }
    else
    {
      // An inactive NHS Backing Report was found for this Backing Report Name. Reactivate it!
      fillNhsBackingReport(nhsBackingReport, backingReport);
      nhsBackingReport.setActive(true);
      rowCount = agyService.reactivateNhsBackingReport(nhsBackingReport, getConsultantLoggedIn().getConsultantId());
    } 
    return rowCount;
  }

  private void fillNhsBackingReport(NhsBackingReport nhsBackingReport, BackingReport backingReport)
  {
    nhsBackingReport.setAgencyId(getConsultantLoggedIn().getAgencyId());
    nhsBackingReport.setClientId(backingReport.getClientId());
    nhsBackingReport.setSubcontractAgencyId(backingReport.getSubcontractAgencyId());
    nhsBackingReport.setName(backingReport.getName());
    nhsBackingReport.setStartDate(backingReport.getStartDate());
    nhsBackingReport.setEndDate(backingReport.getEndDate());
    nhsBackingReport.setAgreedValue(backingReport.getGrandTotalCost());
    if (backingReport.getSubcontractAgencyId() != null)
    {
      if (allWorkersAreSubcontracted(backingReport))
      {
        // All workers have an Original Agency. Set Subcontracted value to Agreed value.
        nhsBackingReport.setSubcontractValue(nhsBackingReport.getAgreedValue());
      }
    }
  }

  private Boolean allWorkersAreSubcontracted(BackingReport backingReport)
  {
    for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
    {
      if (backingReportLine.getAgencyWorkerNameValid() && backingReportLine.getApplicantOriginalAgencyId().equals(0))
      {
        // Worker found that does not have an Original Agency.
        return false;
      }
    }
    // All workers have an Original Agency.
    return true;
  }
  
  protected Integer updateBookingDatesForBackingReport(AgyService agyService, BackingReport backingReport)
  { 
    int rowCount = 0;
    BookingDate bookingDate = null;
    StringBuffer bookingDateIdStrs = new StringBuffer();
    for (BackingReportLine backingReportLine : backingReport.getBackingReportLines())
    {
      if (backingReportLine.getBookingDateId() != null)
      {
        bookingDate = agyService.getBookingDate(backingReportLine.getBookingDateId());
        if (bookingDate != null)
        {
          if (bookingDateIdStrs.length() > 0)
          {
            bookingDateIdStrs.append(',');
          }
          bookingDateIdStrs.append(bookingDate.getBookingDateId());
        }        
      }      
    }
    if (bookingDateIdStrs.length() > 0)
    {
      rowCount = agyService.updateBookingDatesBackingReport(bookingDateIdStrs.toString(), "+" + backingReport.getName(), getConsultantLoggedIn().getConsultantId());
    }    
    return rowCount;
  }
  
  protected String encode(String urlString) 
  {
    URI uri = null;
    try
    {
      uri = new URI(urlString);
    }
    catch (URISyntaxException e)
    {
      e.printStackTrace();
    }
    return uri.toASCIIString();
  }
}
