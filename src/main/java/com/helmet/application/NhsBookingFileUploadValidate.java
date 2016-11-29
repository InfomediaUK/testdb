package com.helmet.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.helmet.api.AgyService;
import com.helmet.bean.Agency;
import com.helmet.bean.Client;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.NhsBookingUpload;

public class NhsBookingFileUploadValidate
{
  private AgyService agyService;
  private Agency agency;
  private Client client;
  private List<NhsBookingUpload> listNhsBookingUpload;
  private ActionMessages errors;
  private String csvFile;
  private String columnDelimeter;
  private Integer useNumberOfColumns;
  
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  
  public NhsBookingFileUploadValidate(AgyService agyService, Agency agency, Client client, ActionMessages errors, String csvFile)
  {
    super();
    this.agyService = agyService;
    this.agency = agency;
    this.client = client;
    this.listNhsBookingUpload = listNhsBookingUpload;
    this.errors = errors;
    this.csvFile = csvFile;
    listNhsBookingUpload = new ArrayList<NhsBookingUpload>();
  }

  public List<NhsBookingUpload> validate()
  {
    HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
    columnNumbers(columnMap, errors);
    if (errors.isEmpty())
    {
      SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
      SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
      formatDate.setLenient(false);
      formatTime.setLenient(false);
      BufferedReader br = null;
      String line = "";
      NhsBookingUpload nhsBooking = null;
      try
      {
        br = new BufferedReader(new FileReader(csvFile));
        if ((line = br.readLine()) == null)
        {
          errors.add("nhsBookingUpload", new ActionMessage("error.nhsBookingFile.empty", csvFile));
        }
        else
        {
          // Assume Column Names record found.
          logger.debug("Column Names record found.");
          String[] nhsBookingArray = line.split(columnDelimeter);
          int columnNumber = 0;
          for (String columnName : nhsBookingArray)
          {
            if (columnMap.get(columnName) == null)
            {
              // Column Name NOT found in Coloumn Map.
              throw new Exception("Column Name NOT found: " + columnName);
            }
            else
            {
              // Column Name found in Coloumn Map. Now check its Column Number...
              if (columnMap.get(columnName).intValue() != columnNumber) 
              { 
                throw new Exception("Error On Column Names. " + columnName + " Expected " + columnMap.get(columnName).intValue() + " Found " + columnNumber); 
              }
              ++columnNumber;
            }
          }
          Date date = null;
          Time timeStart = null;
          Time timeEnd = null;
          while ((line = br.readLine()) != null)
          {
            nhsBookingArray = line.split(columnDelimeter);
            if (nhsBookingArray.length >= useNumberOfColumns)
            {
              cleanRecord(nhsBookingArray);
              nhsBooking = new NhsBookingUpload();
              nhsBooking.setBankReqNum(nhsBookingArray[columnMap.get("BankReqNum").intValue()]);
              NhsBooking existingNhsBooking = agyService.getActiveNhsBookingForBankReqNum(agency.getAgencyId(), nhsBooking.getBankReqNum());
              if (existingNhsBooking == null)
              {
                // This NHS Booking does NOT currently exist in the NHS Booking table.
                String nhsDate = nhsBookingArray[columnMap.get("Date").intValue()];
                nhsBooking.setNhsDate(nhsDate);
                if (isValidDate(nhsDate))
                {
                  // Date appears valid. Get as Date.
                  nhsBooking.setValidDate(true);
                  date = getDate(formatDate, nhsDate);
                }
                else
                {
                  date = null;
                }
                String nhsStart = nhsBookingArray[columnMap.get("Start").intValue()];
                nhsBooking.setNhsStart(nhsStart);
                if (isValidTime(nhsStart))
                {
                  nhsBooking.setValidStart(true);
                  timeStart = getTime(formatTime, nhsStart);
                }
                else
                {
                  timeStart = null;
                }
                String nhsEnd = nhsBookingArray[columnMap.get("End").intValue()];
                nhsBooking.setNhsEnd(nhsEnd);
                if (isValidTime(nhsEnd))
                {
                  nhsBooking.setValidEnd(true);
                  timeEnd = getTime(formatTime, nhsEnd);
                }
                else
                {
                  timeEnd = null;
                }
                nhsBooking.setStaffName(nhsBookingArray[columnMap.get("Staff Name").intValue()]);
                nhsBooking.setDate(date);
                nhsBooking.setStartTime(timeStart);
                nhsBooking.setEndTime(timeEnd);
                nhsBooking.setLocation(nhsBookingArray[columnMap.get("Location").intValue()]);
                nhsBooking.setWard(nhsBookingArray[columnMap.get("Ward").intValue()]);
                nhsBooking.setAssignment(nhsBookingArray[columnMap.get("Assignment").intValue()]);
                nhsBooking.setClientId(client.getClientId());
                System.out.println(nhsBooking.toString());
                logger.debug("NHS Booking: " + nhsBooking.toString());
              }
              else
              {
                // This NHS Booking EXISTS in the NHS Booking table.
                logger.debug("Booking EXISTS: " + nhsBooking.getBankReqNum());
                nhsBooking.setNhsBookingId(existingNhsBooking.getNhsBookingId());
                nhsBooking.setBookingId(existingNhsBooking.getBookingId());
                nhsBooking.setStaffName(existingNhsBooking.getStaffName());
                nhsBooking.setDate(existingNhsBooking.getDate());
                nhsBooking.setStartTime(existingNhsBooking.getStartTime());
                nhsBooking.setEndTime(existingNhsBooking.getEndTime());
                nhsBooking.setLocation(existingNhsBooking.getLocation());
                nhsBooking.setWard(existingNhsBooking.getWard());
                nhsBooking.setAssignment(existingNhsBooking.getAssignment());
              }
              listNhsBookingUpload.add(nhsBooking);
            }
          }
        }
      }
      catch (Exception e)
      {
        errors.add("nhsBookingUpload", new ActionMessage("error.nhsBookingFile.exception", e.getMessage()));
        e.printStackTrace();
      }
      finally
      {
        if (br != null)
        {
          try
          {
            br.close();
          }
          catch (IOException e)
          {
            errors.add("nhsBookingUpload", new ActionMessage("error.nhsBookingFile.ioException", e.getMessage()));
            e.printStackTrace();
          }
        }
      }
    }    
    logger.debug("Done");
    return listNhsBookingUpload;
  }
  
  // It looks like this:
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
  private boolean columnNumbers(HashMap<String, Integer> columnMap, ActionMessages errors)
  {
    String columnNumbersFile = "columnNumbers.txt";
    String columnNumbersFileUrl = FileHandler.getInstance().getNhsBookingFileFolder() + "/" + columnNumbersFile;
    String columnNumbersFilePath = FileHandler.getInstance().getNhsBookingFileLocation() + columnNumbersFileUrl;
    String line = "";
    String name = null;
    BufferedReader br = null;
    try
    {
      try
      {
        br = new BufferedReader(new FileReader(columnNumbersFilePath));
        while ((line = br.readLine()) != null)
        {
          name = line.substring(0, line.indexOf("="));
          if (name.equalsIgnoreCase("COLUMN_DELIMETER"))
          {
            String value = line.substring(line.indexOf("=") + 1);
            columnDelimeter = value;
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
      errors.add("nhsBookingUpload", new ActionMessage("error.nhsBookingColumnNumbersFile.notFound", columnNumbersFilePath));
      return false;
    }
    catch (IOException e)
    {
      errors.add("nhsBookingUpload", new ActionMessage("error.nhsBookingColumnNumbersFile.ioException", e.getMessage()));
      e.printStackTrace();
      return false;
    }
  }

  // Remove any double quotes.
  private void cleanRecord(String[] nhsBookingArray)
  {
    for(int i = 0; i < nhsBookingArray.length; i++)
    {
      nhsBookingArray[i] = nhsBookingArray[i].replaceAll("^\"|\"$", "");
    }
  }
  
  private boolean isValidTime(String timeString)
  {
    SimpleDateFormat df = new SimpleDateFormat("HH:mm");
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

  private Time getTime(SimpleDateFormat formatTime, String timeStr)
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

  private boolean isValidDate(String dateString)
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

  private Date getDate(SimpleDateFormat formatDate, String dateStr)
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
  
}
