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
import com.helmet.bean.ApplicantPaymentUpload;

public class ApplicantPaymentFileUploadProcess extends NhsFileUploadCommon
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
    FormFile applicantPaymentFile = (FormFile)dynaForm.get("applicantPaymentFormFile");
    String applicantPaymentFilename = applicantPaymentFile.getFileName();
    List<ApplicantPaymentUpload> listApplicantPaymentUpload = new ArrayList<ApplicantPaymentUpload>();
    if (StringUtils.isEmpty(applicantPaymentFilename))
    {
      errors.add("applicantPaymentUpload", new ActionMessage("error.applicantPaymentFile.notSupplied"));
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
      String filePath = folderPath + "/" + applicantPaymentFilename;
      uploadFile(applicantPaymentFile, filePath);
      HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
      String columnNumbersFilePath = FileHandler.getInstance().getNhsBookingFileLocation() + FileHandler.getInstance().getNhsBookingFileFolder();
      columnNumbers(columnMap, columnNumbersFilePath, errors, "applicantPaymentUpload", "error.nhsBookingColumnNumbersFile");
      if (errors.isEmpty())
      {
        validate(agyService, columnMap, listApplicantPaymentUpload, filePath, dynaForm, errors, messageResources);
      }
    }
    dynaForm.set("list", listApplicantPaymentUpload);
    dynaForm.set("applicantPaymentFilename", applicantPaymentFilename);
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
   
  private void validate(AgyService agyService, HashMap<String, Integer> columnMap, List<ApplicantPaymentUpload> listApplicantPaymentUpload, String csvFile, DynaValidatorForm dynaForm, ActionMessages errors, MessageResources messageResources)
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
    ApplicantPaymentUpload applicantPaymentUpload = null;
    try
    {
      br = new BufferedReader(new FileReader(csvFile));
      if ((line = br.readLine()) == null)
      {
        errors.add("applicantPaymentUpload", new ActionMessage("error.applicantPaymentFile.empty", csvFile));
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
            applicantPaymentUpload = agyService.getApplicantPaymentUploadForBankReqNum(getConsultantLoggedIn().getAgencyId(), bankReqNum);
            if (applicantPaymentUpload == null)
            {
              // This NHS Booking does NOT exist in the NHS Booking table.
              ++countInvalidNhsBooking;
              applicantPaymentUpload = new ApplicantPaymentUpload();
              applicantPaymentUpload.setBankReqNum(bankReqNum);
              applicantPaymentUpload.setUploadStaffName(staffName);
              applicantPaymentUpload.setUploadDate(uploadDate);
              applicantPaymentUpload.setUploadStart(uploadStart);
              applicantPaymentUpload.setUploadEnd(uploadEnd);
              applicantPaymentUpload.setUploadLocation(location);
              applicantPaymentUpload.setUploadWard(ward);
              applicantPaymentUpload.setUploadAssignment(assignment);
            }
            else
            {
              // This NHS Booking EXISTS in the NHS Booking table.
              logger.debug("Booking EXISTS: " + applicantPaymentUpload.getBankReqNum());
              applicantPaymentUpload.setUploadStaffName(staffName);
              if (staffName.equals(applicantPaymentUpload.getStaffName()))
              {
                applicantPaymentUpload.setValidStaffName(true);
              }
              else
              {
                applicantPaymentUpload.setValidStaffName(false);
              }
              applicantPaymentUpload.setUploadDate(uploadDate);
              if (isValidDate(uploadDate))
              {
                // Date appears valid. Get as Date.
                date = getDate(formatDate, uploadDate);
                if (date.equals(applicantPaymentUpload.getDate()))
                {
                  // Date same as on NHS Booking. Valid.
                  applicantPaymentUpload.setValidDate(true);
                }
                else
                {
                  // Date differs from that on NHS Booking. Invalid.
                  applicantPaymentUpload.setValidDate(false);
                }
              }
              else
              {
                // Invalid Date.
                applicantPaymentUpload.setValidDate(false);
              }
              applicantPaymentUpload.setUploadStart(uploadStart);
              if (isValidTime(uploadStart))
              {
                // Time appears valid. Get as Time.
                timeStart = getTime(formatTime, uploadStart);
                if (timeStart.equals(applicantPaymentUpload.getStartTime()))
                {
                  applicantPaymentUpload.setValidStart(true);
                }
                else
                {
                  applicantPaymentUpload.setValidStart(false);
                }
              }
              else
              {
                // Invalid Time.
                applicantPaymentUpload.setValidStart(false);
              }
              applicantPaymentUpload.setUploadEnd(uploadEnd);
              if (isValidTime(uploadEnd))
              {
                // Time appears valid. Get as Time.
                timeEnd = getTime(formatTime, uploadEnd);
                if (timeEnd.equals(applicantPaymentUpload.getEndTime()))
                {
                  applicantPaymentUpload.setValidEnd(true);
                }
                else
                {
                  applicantPaymentUpload.setValidEnd(false);
                }
              }
              else
              {
                // Invalid Time.
                applicantPaymentUpload.setValidEnd(false);
              }
              applicantPaymentUpload.setUploadLocation(location);
              if (location.equals(applicantPaymentUpload.getLocation()))
              {
                applicantPaymentUpload.setValidLocation(true);
              }
              else
              {
                applicantPaymentUpload.setValidLocation(false);
              }
              applicantPaymentUpload.setUploadWard(ward);
              if (ward.equals(applicantPaymentUpload.getWard()))
              {
                applicantPaymentUpload.setValidWard(true);
              }
              else
              {
                applicantPaymentUpload.setValidWard(false);
              }
              applicantPaymentUpload.setUploadAssignment(assignment);
              if (assignment.equals(applicantPaymentUpload.getAssignment()))
              {
                applicantPaymentUpload.setValidAssignment(true);
              }
              else
              {
                applicantPaymentUpload.setValidAssignment(false);
              }
              if (applicantPaymentUpload.isValid())
              {
                ++countValidNhsBooking;
              }
              else
              {
                ++countInvalidNhsBooking;
              }
            }
            listApplicantPaymentUpload.add(applicantPaymentUpload);
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
      errors.add("applicantPaymentUpload", new ActionMessage("error.applicantPaymentFile.exception", e.getMessage()));
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
          errors.add("applicantPaymentUpload", new ActionMessage("error.applicantPaymentFile.ioException", e.getMessage()));
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
