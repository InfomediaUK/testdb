package com.helmet.application.agy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.bean.NhsBookingPaymentUpload;

public class NhsBookingPaymentFileUploadProcess extends NhsFileUploadCommon
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    logger.entry("In coming !!!");
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    long startTime = System.nanoTime();
    ActionMessages errors = new ActionMessages();
    ActionForward actionForward = null;
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    String paymentDateStr = (String)dynaForm.get("paymentDateStr");
    FormFile nhsBookingPaymentFile = (FormFile)dynaForm.get("nhsBookingPaymentFormFile");
    String nhsBookingPaymentFilename = nhsBookingPaymentFile.getFileName();
    List<NhsBookingPaymentUpload> listNhsBookingPaymentUpload = new ArrayList<NhsBookingPaymentUpload>();
    if (StringUtils.isEmpty(nhsBookingPaymentFilename))
    {
      errors.add("nhsBookingPaymentUpload", new ActionMessage("error.nhsBookingPaymentFile.notSupplied"));
    }
    else
    {
      // Now upload the NHS Booking file.
      String folderUrl = FileHandler.getInstance().getNhsBookingFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/payments";
      String folderPath = FileHandler.getInstance().getNhsBookingFileLocation() + folderUrl;
      File folder = new File(folderPath);
      if (!folder.exists())
      {
        folder.mkdir();
      }
      String filePath = folderPath + "/" + nhsBookingPaymentFilename;
      uploadFile(nhsBookingPaymentFile, filePath);
      HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
      String columnNumbersFilePath = FileHandler.getInstance().getNhsBookingFileLocation() + FileHandler.getInstance().getNhsBookingFileFolder();
      columnNumbers(columnMap, columnNumbersFilePath, errors, "nhsBookingPaymentUpload", "error.nhsBookingColumnNumbersFile");
      if (errors.isEmpty())
      {
        validate(agyService, columnMap, listNhsBookingPaymentUpload, filePath, dynaForm, errors, messageResources);
      }
    }
    dynaForm.set("list", listNhsBookingPaymentUpload);
    long endTime = System.nanoTime();
    long duration = endTime - startTime;
    logger.debug("Out going !!! - Duration: " + duration + "ms.");
    if (errors.isEmpty())
    {
      actionForward = mapping.findForward("success");
    }
    else
    {
      saveErrors(request, errors);
      actionForward = mapping.findForward("error");
    }
    return actionForward;
  }
   
  private void validate(AgyService agyService, HashMap<String, Integer> columnMap, List<NhsBookingPaymentUpload> listNhsBookingPaymentUpload, String csvFile, DynaValidatorForm dynaForm, ActionMessages errors, MessageResources messageResources)
  {
    long startTime = System.currentTimeMillis();
    long endTime = 999;
    long duration = 0;
   
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
    formatDate.setLenient(false);
    formatTime.setLenient(false);
    BufferedReader br = null;
    String line = "";
    Integer countValidNhsBooking = new Integer(0);
    Integer countInvalidNhsBooking = new Integer(0);
    NhsBookingPaymentUpload nhsBookingPaymentUpload = null;
    try
    {
      br = new BufferedReader(new FileReader(csvFile));
      if ((line = br.readLine()) == null)
      {
        errors.add("nhsBookingPaymentUpload", new ActionMessage("error.nhsBookingPaymentFile.empty", csvFile));
      }
      else
      {
        // Assume Column Names record found.
        logger.debug("Column Names record found.");
        String[] nhsBookingArray = line.split(getColumnDelimeter());
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
              throw new Exception("Error On Column Names. " + columnName + " Expected "
                + columnMap.get(columnName).intValue() + " Found " + columnNumber); 
            }
            ++columnNumber;
          }              
        }
        Date date = null;
        Time timeStart = null;
        Time timeEnd = null;
        while ((line = br.readLine()) != null)
        {
          nhsBookingArray = line.split(getColumnDelimeter());// string.replaceAll("^\"|\"$", "");
          if (StringUtils.countMatches(line, getColumnDelimeter()) >= getUseNumberOfColumns())
          {
            cleanRecord(nhsBookingArray);
            String bankReqNum = nhsBookingArray[columnMap.get("BankReqNum").intValue()].trim();
            String staffName = nhsBookingArray[columnMap.get("Staff Name").intValue()].trim();
            String uploadDate = nhsBookingArray[columnMap.get("Date").intValue()].trim(); 
            String uploadStart = nhsBookingArray[columnMap.get("Start").intValue()].trim(); 
            String uploadEnd = nhsBookingArray[columnMap.get("End").intValue()].trim(); 
            String location = nhsBookingArray[columnMap.get("Location").intValue()].trim();
            String ward = nhsBookingArray[columnMap.get("Ward").intValue()].trim();
            String assignment = nhsBookingArray[columnMap.get("Assignment").intValue()].trim();
            nhsBookingPaymentUpload = agyService.getNhsBookingPaymentUploadForBankReqNum(getConsultantLoggedIn().getAgencyId(), bankReqNum);
            if (nhsBookingPaymentUpload == null)
            {
              // This NHS Booking does NOT exist in the NHS Booking table.
              ++countInvalidNhsBooking;
              nhsBookingPaymentUpload = new NhsBookingPaymentUpload();
              nhsBookingPaymentUpload.setBankReqNum(bankReqNum);
              nhsBookingPaymentUpload.setUploadStaffName(staffName);
              nhsBookingPaymentUpload.setUploadDate(uploadDate);
              nhsBookingPaymentUpload.setUploadStart(uploadStart);
              nhsBookingPaymentUpload.setUploadEnd(uploadEnd);
              nhsBookingPaymentUpload.setUploadLocation(location);
              nhsBookingPaymentUpload.setUploadWard(ward);
              nhsBookingPaymentUpload.setUploadAssignment(assignment);
            }
            else
            {
              // This NHS Booking EXISTS in the NHS Booking table.
              logger.debug("Booking EXISTS: " + nhsBookingPaymentUpload.getBankReqNum());
              ++countValidNhsBooking;
              nhsBookingPaymentUpload.setUploadStaffName(staffName);
              if (staffName.equals(nhsBookingPaymentUpload.getStaffName()))
              {
                nhsBookingPaymentUpload.setValidStaffName(true);
              }
              else
              {
                nhsBookingPaymentUpload.setValidStaffName(false);
              }
              nhsBookingPaymentUpload.setUploadDate(uploadDate);
              if (isValidDate(uploadDate))
              {
                // Date appears valid. Get as Date.
                date = getDate(formatDate, uploadDate);
                if (date.equals(nhsBookingPaymentUpload.getDate()))
                {
                  // Date same as on NHS Booking. Valid.
                  nhsBookingPaymentUpload.setValidDate(true);
                }
                else
                {
                  // Date differs from that on NHS Booking. Invalid.
                  nhsBookingPaymentUpload.setValidDate(false);
                }
              }
              else
              {
                // Invalid Date.
                nhsBookingPaymentUpload.setValidDate(false);
              }
              nhsBookingPaymentUpload.setUploadStart(uploadStart);
              if (isValidTime(uploadStart))
              {
                // Time appears valid. Get as Time.
                timeStart = getTime(formatTime, uploadStart);
                if (timeStart.equals(nhsBookingPaymentUpload.getStartTime()))
                {
                  nhsBookingPaymentUpload.setValidStart(true);
                }
                else
                {
                  nhsBookingPaymentUpload.setValidStart(false);
                }
              }
              else
              {
                // Invalid Time.
                nhsBookingPaymentUpload.setValidStart(false);
              }
              nhsBookingPaymentUpload.setUploadEnd(uploadEnd);
              if (isValidTime(uploadEnd))
              {
                // Time appears valid. Get as Time.
                timeEnd = getTime(formatTime, uploadEnd);
                if (timeEnd.equals(nhsBookingPaymentUpload.getEndTime()))
                {
                  nhsBookingPaymentUpload.setValidEnd(true);
                }
                else
                {
                  nhsBookingPaymentUpload.setValidEnd(false);
                }
              }
              else
              {
                // Invalid Time.
                nhsBookingPaymentUpload.setValidEnd(false);
              }
              nhsBookingPaymentUpload.setUploadLocation(location);
              if (location.equals(nhsBookingPaymentUpload.getLocation()))
              {
                nhsBookingPaymentUpload.setValidLocation(true);
              }
              else
              {
                nhsBookingPaymentUpload.setValidLocation(false);
              }
              nhsBookingPaymentUpload.setUploadWard(ward);
              if (ward.equals(nhsBookingPaymentUpload.getWard()))
              {
                nhsBookingPaymentUpload.setValidWard(true);
              }
              else
              {
                nhsBookingPaymentUpload.setValidWard(false);
              }
              nhsBookingPaymentUpload.setUploadAssignment(assignment);
              if (assignment.equals(nhsBookingPaymentUpload.getAssignment()))
              {
                nhsBookingPaymentUpload.setValidAssignment(true);
              }
              else
              {
                nhsBookingPaymentUpload.setValidAssignment(false);
              }
            }
            listNhsBookingPaymentUpload.add(nhsBookingPaymentUpload);
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            System.out.println("Duration so far: " + duration);
          }
          else
          {
            
          }
        }
      }
    }
    catch (Exception e)
    {
      errors.add("nhsBookingPaymentUpload", new ActionMessage("error.nhsBookingPaymentFile.exception", e.getMessage()));
      e.printStackTrace();
    }
    finally
    {
      // Be sure the Maps have gone from memory...
      if (br != null)
      {
        try
        {
          br.close();
        }
        catch (IOException e)
        {
          errors.add("nhsBookingPaymentUpload", new ActionMessage("error.nhsBookingPaymentFile.ioException", e.getMessage()));
          e.printStackTrace();
        }
      }
    }
    dynaForm.set("countInvalidNhsBooking", countInvalidNhsBooking);
    dynaForm.set("countValidNhsBooking", countValidNhsBooking);
    logger.debug("Done");
  }

  // Remove any double quotes.
  private void cleanRecord(String[] nhsBookingArray)
  {
    for(int i = 0; i < nhsBookingArray.length; i++)
    {
      nhsBookingArray[i] = nhsBookingArray[i].replaceAll("^\"|\"$", "");
    }
  }
  
}
