package com.helmet.application.agy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.helmet.bean.Applicant;
import com.helmet.bean.Client;
import com.helmet.bean.Location;
import com.helmet.bean.LocationJobProfileUser;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.NhsBookingUpload;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.Shift;
import com.helmet.bean.Site;

public class NhsBookingFileUploadProcess extends NhsFileUploadCommon
{
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    long startTime = System.nanoTime();
    logger.entry("In coming !!!");
    ActionMessages errors = new ActionMessages();
    ActionForward actionForward = null;
    MessageResources messageResources = getResources(request);
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    Client client = (Client)dynaForm.get("client");
    FormFile nhsBookingFile = (FormFile)dynaForm.get("nhsBookingFormFile");
    client = agyService.getClientUser(client.getClientId());
    String nhsBookingFilename = nhsBookingFile.getFileName();
    List<NhsBookingUpload> listNhsBookingUpload = new ArrayList<NhsBookingUpload>();
    List<NhsBooking> listNhsBookingBooked = new ArrayList<NhsBooking>();
    if (StringUtils.isEmpty(nhsBookingFilename))
    {
      errors.add("nhsBookingUpload", new ActionMessage("error.nhsBookingFile.notSupplied"));
    }
    else
    {
      // Now upload the NHS Booking file.
      String fileUrl = FileHandler.getInstance().getNhsBookingFileFolder() + "/a" + getConsultantLoggedIn().getAgencyId() + "/" + nhsBookingFilename;
      String filePath = FileHandler.getInstance().getNhsBookingFileLocation() + fileUrl;
      uploadFile(nhsBookingFile, filePath);
      HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
      String columnNumbersFilePath = FileHandler.getInstance().getNhsBookingFileLocation() + FileHandler.getInstance().getNhsBookingFileFolder();
      columnNumbers(columnMap, columnNumbersFilePath, errors, "nhsBookingUpload", "error.nhsBookingColumnNumbersFile");
      if (errors.isEmpty())
      {
        validate(agyService, columnMap, client, listNhsBookingUpload, filePath, dynaForm, errors, messageResources);
      }
    }
    dynaForm.set("list", listNhsBookingUpload);
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
   
  private void validate(AgyService agyService, HashMap<String, Integer> columnMap, Client client, List<NhsBookingUpload> listNhsBookingUpload, String csvFile, DynaValidatorForm dynaForm, ActionMessages errors, MessageResources messageResources)
  {
    long startTime = System.currentTimeMillis();
    long endTime = 999;
    long duration = 0;
   
    HashMap<String, Site> siteMap = new HashMap<String, Site>();
    HashMap<String, Location> locationMap = new HashMap<String, Location>();
    HashMap<Integer, List<Shift>> listLocationShiftMap = new HashMap<Integer, List<Shift>>();
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
    formatDate.setLenient(false);
    formatTime.setLenient(false);
    BufferedReader br = null;
    String line = "";
    Integer countNewNhsBooking = new Integer(0);
    Integer countExistingNhsBooking = new Integer(0);
    Integer countInvalidNhsBooking = new Integer(0);
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
            nhsBooking = new NhsBookingUpload();
            nhsBooking.setBankReqNum(nhsBookingArray[columnMap.get("BankReqNum").intValue()].trim());
            NhsBooking existingNhsBooking = agyService.getActiveNhsBookingForBankReqNum(getConsultantLoggedIn().getAgencyId(), nhsBooking.getBankReqNum());
            if (existingNhsBooking == null)
            {
              // This NHS Booking does NOT currently exist in the NHS Booking table.
              String nhsDate = nhsBookingArray[columnMap.get("Date").intValue()].trim(); 
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
              String nhsStart = nhsBookingArray[columnMap.get("Start").intValue()].trim(); 
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
              String nhsEnd = nhsBookingArray[columnMap.get("End").intValue()].trim(); 
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
              nhsBooking.setStaffName(nhsBookingArray[columnMap.get("Staff Name").intValue()].trim());
              nhsBooking.setDate(date);
              nhsBooking.setStartTime(timeStart);
              nhsBooking.setEndTime(timeEnd);
              nhsBooking.setLocation(nhsBookingArray[columnMap.get("Location").intValue()].trim());
              nhsBooking.setWard(nhsBookingArray[columnMap.get("Ward").intValue()].trim());
              nhsBooking.setAssignment(nhsBookingArray[columnMap.get("Assignment").intValue()].trim());
              nhsBooking.setAgencyId(getConsultantLoggedIn().getAgencyId());
              nhsBooking.setClientId(client.getClientId());
              System.out.println(nhsBooking.toString());
              logger.debug("NHS Booking: " + nhsBooking.toString());
              if (loadSiteIdForLocation(agyService, client.getClientId(), nhsBooking, siteMap))
              {
                // One or more Sites found for Client and NHS Location.
                loadApplicantIdForStaffName(agyService, nhsBooking);
                if (nhsBooking.getSiteId() != null)
                {
                  loadLocationIdForWard(agyService, nhsBooking, locationMap);
                }
                if (nhsBooking.getLocationId() != null)
                {
                  loadJobProfileIdForAssignment(agyService, nhsBooking);
                }                
                loadBookingGroupId(agyService, nhsBooking, client.getClientId(), locationMap);
                if (nhsBooking.getLocationId() != null)
                {
                  loadShiftIdForTime(agyService, nhsBooking, listLocationShiftMap);
                }
                if (nhsBooking.isValid())
                {
                  // This NHS Booking record appears to be validated to our database. Insert it.
                  ++countNewNhsBooking;
                  agyService.insertNhsBooking(nhsBooking, getConsultantLoggedIn().getConsultantId());
                }
                else
                {
                  ++countInvalidNhsBooking;
                }
              }
              else
              {
                // No Sites found for Client and NHS Location.
                errors.add("nhsBookingUpload", new ActionMessage("error.noSitesForClientNhsLocation", client.getName(), nhsBooking.getLocation()));
                break;
              }
            }
            else
            {
              // This NHS Booking EXISTS in the NHS Booking table.
              logger.debug("Booking EXISTS: " + nhsBooking.getBankReqNum());
              ++countExistingNhsBooking;
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
      errors.add("nhsBookingUpload", new ActionMessage("error.nhsBookingFile.exception", e.getMessage()));
      e.printStackTrace();
    }
    finally
    {
      // Be sure the Maps have gone from memory...
      siteMap.clear();
      locationMap.clear();
      siteMap = null;
      locationMap = null;
      listLocationShiftMap.clear();
      listLocationShiftMap = null;
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
    dynaForm.set("countNewNhsBooking", countNewNhsBooking);
    dynaForm.set("countInvalidNhsBooking", countInvalidNhsBooking);
    dynaForm.set("countExistingNhsBooking", countExistingNhsBooking);
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
  
  private void loadApplicantIdForStaffName(AgyService agyService, NhsBookingUpload nhsBooking)
  {
    List<Applicant> listApplicant = agyService.getApplicantsForNhsStaffName(getConsultantLoggedIn().getAgencyId(), nhsBooking.getStaffName());
    if (listApplicant.size() == 1)
    {
      Applicant applicant = agyService.getApplicant(listApplicant.get(0).getApplicantId()); 
      nhsBooking.setApplicantId(applicant.getApplicantId());
      nhsBooking.setApplicantFirstName(applicant.getFirstName());
      nhsBooking.setApplicantLastName(applicant.getLastName());
      nhsBooking.setApplicantEmailAddress(applicant.getEmailAddress());
    }
    else
    {
      nhsBooking.setApplicantFirstName("Problem:");
      nhsBooking.setApplicantLastName(listApplicant.size() + " Found");
      nhsBooking.setListApplicant(listApplicant);
    }
  }
  
  private boolean loadSiteIdForLocation(AgyService agyService, Integer clientId, NhsBookingUpload nhsBooking, HashMap<String, Site> siteMap)
  {
    // First try and get Site from the Site Map
    Site site = siteMap.get(nhsBooking.getLocation());
    if (site == null)
    {
      // Site NOT in Site Map, try and get it from the database.
      List<Site> listSite = agyService.getSitesForNhsLocation(clientId, nhsBooking.getLocation());
      if (listSite.size() == 1)
      {
        // Only ONE Site found, go with it.
        site = agyService.getSite(listSite.get(0).getSiteId());
        siteMap.put(nhsBooking.getLocation(), site);
        nhsBooking.setSiteId(site.getSiteId());
        nhsBooking.setSiteName(site.getName());
        return true;
      }
      else
      {
        if (listSite.size() == 0)
        {
          // ERROR - No Sites found for Client and NHS Location
          return false;
        }
        else
        {
          nhsBooking.setListSite(listSite);
          return true;
        }        
      }
    }
    else
    {
      nhsBooking.setSiteId(site.getSiteId());
      nhsBooking.setSiteName(site.getName());      
      return true;
    }
  }
  
  private void loadLocationIdForWard(AgyService agyService, NhsBookingUpload nhsBooking, HashMap<String, Location> locationMap)
  {
    // First try and get Location from the Location Map
    Location location = locationMap.get(nhsBooking.getWard());
    if (location == null)
    {
      // Location NOT in Location Map, try and get it from the database.
      List<Location> listLocation = agyService.getLocationsForNhsWard(nhsBooking.getSiteId(), nhsBooking.getWard());
      if (listLocation.size() == 1)
      {
        // Only ONE Location found, go with it.
        location = agyService.getLocation(listLocation.get(0).getLocationId());
        locationMap.put(nhsBooking.getWard(), location);
        nhsBooking.setLocationId(location.getLocationId());
        nhsBooking.setLocationName(location.getName());
        if (!location.getNhsDayTimesEntered())
        {
          nhsBooking.setLocationName("NHS Day Times missing");
        }
      }
      else
      {
        if (listLocation.size() == 0)
        {
          nhsBooking.setLocationName("Not Found");
        }
        else
        {
          nhsBooking.setLocationName(listLocation.size() + " Found");
          nhsBooking.setListLocation(listLocation);
        }        
      }
    }    
    else
    {
      nhsBooking.setLocationId(location.getLocationId());
      nhsBooking.setLocationName(location.getName());      
    }
  }

  private void loadJobProfileIdForAssignment(AgyService agyService, NhsBookingUpload nhsBooking)
  {
    List<LocationJobProfileUser> listLocationJobProfileUser = agyService.getLocationJobProfileUsersForLocationAndNhsAssignment(nhsBooking.getLocationId(), nhsBooking.getAssignment());
    if (listLocationJobProfileUser.size() == 1)
    {
      // Only ONE LocationJobProfile found, go with it.
      LocationJobProfileUser jobProfileUser = listLocationJobProfileUser.get(0);
      nhsBooking.setJobProfileId(jobProfileUser.getJobProfileId());
      nhsBooking.setJobProfileName(jobProfileUser.getJobProfileName());
      nhsBooking.setJobFamilyName(jobProfileUser.getJobFamilyName());
      nhsBooking.setJobFamilyCode(jobProfileUser.getJobFamilyCode());
      nhsBooking.setJobSubFamilyName(jobProfileUser.getJobSubFamilyName());
      nhsBooking.setJobSubFamilyCode(jobProfileUser.getJobSubFamilyCode());
    }
    else
    {
      if (listLocationJobProfileUser.size() == 0)
      {
        nhsBooking.setJobProfileName("Not Found");
      }
      else
      {
        nhsBooking.setJobProfileName(listLocationJobProfileUser.size() + " Found");
        nhsBooking.setListJobProfileUser(listLocationJobProfileUser);
      }
    }
  }
  
  private void loadShiftIdForTime(AgyService agyService, NhsBookingUpload nhsBooking, HashMap<Integer, List<Shift>> listLocationShiftMap)
  {
    // First try and get Location's Shifts from the Location Shift Map
    List<Shift> listShift = listLocationShiftMap.get(nhsBooking.getLocationId());
    if (listShift == null)
    {
      listShift = agyService.getShiftsForLocation(nhsBooking.getLocationId());
      listLocationShiftMap.put(nhsBooking.getLocationId(), listShift);
    }
    for (Shift shift : listShift)
    {
      if (shift.getStartTime().equals(nhsBooking.getStartTime()) && shift.getEndTime().equals(nhsBooking.getEndTime()))
      {
        nhsBooking.setShiftId(shift.getShiftId());
        nhsBooking.setShiftName(shift.getName());
        break;
      }
    }
  }

  private void loadBookingGroupId(AgyService agyService, NhsBookingUpload nhsBooking, Integer clientId, HashMap<String, Location> locationMap)
  {
    if (nhsBooking.getDate() != null)
    {
      PublicHoliday publicHoliday = agyService.getPublicHolidayForClientDate(clientId, nhsBooking.getDate());
      if (publicHoliday == null)
      {
        Calendar c = Calendar.getInstance();
        c.setTime(nhsBooking.getDate());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1)
        {
          // Sunday.
          nhsBooking.setBookingGroupId(AgyConstants.SUNDAY_OR_PUBLICHOLIDAY);
        }
        else
        {
          if (dayOfWeek == 7)
          {
            // Saturday.
            nhsBooking.setBookingGroupId(AgyConstants.SATURDAY_OR_WEEKDAY_LATE);
          }
          else
          {
            Location location = locationMap.get(nhsBooking.getWard());
            if (location != null && location.getNhsDayStartTime() != null && location.getNhsDayEndTime() != null)
            {
              // Location found with NHS Day Start and End times.
              Timestamp dayStartTime = new Timestamp(location.getNhsDayStartTime().getTime()); //getTime(formatTime, "07:45:00");
              Timestamp dayEndTime = new Timestamp(location.getNhsDayEndTime().getTime()); //getTime(formatTime, "19:15:00");
              Timestamp bookingStartTime = new Timestamp(nhsBooking.getStartTime().getTime());
              // If End Time is after midnight (it will be less than Start Time) we need to add 24 hours to it 
              // so that it's on the next day. Eg. 19:15 - 07:45
              Timestamp bookingEndTime = (nhsBooking.getStartTime().before(nhsBooking.getEndTime())) ? new Timestamp(nhsBooking.getEndTime().getTime()) : new Timestamp(nhsBooking.getEndTime().getTime() + (24 * 60 * 60 * 1000));
              if (bookingEndTime.before(dayStartTime) || bookingStartTime.after(dayEndTime))
              {
                // Definitely a Late, no overlap.
                nhsBooking.setBookingGroupId(AgyConstants.SATURDAY_OR_WEEKDAY_LATE);
              }
              else
              {
                // Take the latest Start Time and the earliest End Time from the booking and day time ranges.
                Timestamp overlapStartTime = (bookingStartTime.after(dayStartTime)) ? bookingStartTime : dayStartTime;
                Timestamp overlapEndTime = (bookingEndTime.before(dayEndTime)) ? bookingEndTime : dayEndTime;
                long overlapDuration = overlapEndTime.getTime() - overlapStartTime.getTime();
                long bookingDuration = bookingEndTime.getTime() - nhsBooking.getStartTime().getTime();
                if (overlapDuration > (bookingDuration - overlapDuration))
                {
                  // Bulk of booking time range is in Day Time.
                  nhsBooking.setBookingGroupId(AgyConstants.WEEKDAY_EARLY);
                }
                else
                {
                  // Bulk of booking time range is outside Day Time.
                  nhsBooking.setBookingGroupId(AgyConstants.SATURDAY_OR_WEEKDAY_LATE);
                }
              }
            }            
          }
        }
      }
      else
      {
        // Public Holiday.
        nhsBooking.setBookingGroupId(AgyConstants.SUNDAY_OR_PUBLICHOLIDAY);
      }
    }
  }
}
