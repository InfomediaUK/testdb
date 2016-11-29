package com.helmet.application;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import com.helmet.api.AgyService;
import com.helmet.application.agy.AgyConstants;
import com.helmet.bean.Applicant;
import com.helmet.bean.Client;
import com.helmet.bean.Consultant;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.LocationJobProfileUser;
import com.helmet.bean.NhsBookingUpload;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.Shift;
import com.helmet.bean.Site;

public class NhsBookingFileUploadTask implements Callable
{
  private AgyService agyService;
  private Client client;
  private Consultant consultantLoggedIn;
  private List<NhsBookingUpload> listNhsBookingUpload;

  public NhsBookingFileUploadTask(AgyService agyService, Client client, Consultant consultantLoggedIn, List<NhsBookingUpload> listNhsBookingUpload)
  {
    super();
    this.agyService = agyService;
    this.client = client;
    this.consultantLoggedIn = consultantLoggedIn;
    this.listNhsBookingUpload = listNhsBookingUpload;
  }

  public Object call() throws Exception
  {
    HashMap<String, Site> siteMap = new HashMap<String, Site>();
    HashMap<String, Location> locationMap = new HashMap<String, Location>();
    HashMap<Integer, List<Shift>> listLocationShiftMap = new HashMap<Integer, List<Shift>>();
    Integer countNewNhsBooking = new Integer(0);
    Integer countExistingNhsBooking = new Integer(0);
    Integer countInvalidNhsBooking = new Integer(0);
    for (NhsBookingUpload nhsBooking : listNhsBookingUpload)
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
        agyService.insertNhsBooking(nhsBooking, consultantLoggedIn.getConsultantId());
      }
      else
      {
        ++countInvalidNhsBooking;
      }
    }    
    return null;
  }

  private void loadApplicantIdForStaffName(AgyService agyService, NhsBookingUpload nhsBooking)
  {
    List<Applicant> listApplicant = agyService.getApplicantsForNhsStaffName(consultantLoggedIn.getAgencyId(), nhsBooking.getStaffName());
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
      }
      else
      {
        if (listLocation.size() == 0)
        {
          nhsBooking.setLocationName("Not Found");
        }
        else
        {
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
