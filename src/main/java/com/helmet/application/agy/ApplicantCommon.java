package com.helmet.application.agy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helmet.api.AgyService;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.Unavailable;


public class ApplicantCommon extends AgyAction 
{

  @Override
  protected ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    // Does nothing here, override in sub-classes.
    return null;
  }

  protected String getApplicantNotes(Applicant applicant)
  {
    String notesFileName = FileHandler.getInstance().getApplicantFileLocation() +
                           FileHandler.getInstance().getApplicantFileFolder() + 
                           "/" + applicant.getApplicantId() + "/notes.txt";
    StringBuffer notes   = new StringBuffer(); 
    Utilities.suckInFile(notesFileName, notes);
    return notes.toString();
  }
  
  protected List<AgencyWorkerChecklistFile> loadAgencyWorkerChecklists(String checklistFilePath, int applicantId)
  {
    List<AgencyWorkerChecklistFile> agencyWorkerChecklists = new ArrayList<AgencyWorkerChecklistFile>();
    AgencyWorkerChecklistFile agencyWorkerChecklistFile = null;
    File folder = new File(checklistFilePath);
    AgencyWorkerChecklistFileListFilter filter = new AgencyWorkerChecklistFileListFilter(applicantId);
    File[] files = folder.listFiles(filter);
    if (files != null)
    {
      for (File file : files)
      {
        agencyWorkerChecklistFile = new AgencyWorkerChecklistFile(file.getName(), file.lastModified());
        agencyWorkerChecklists.add(agencyWorkerChecklistFile);
      }
    }
    return agencyWorkerChecklists;
  }
  
  protected List <BookingBookingDateUserApplicant> loadBookingBookingDateUserApplicantList(Integer applicantId, List<BookingDateUserApplicant> list, Integer weekToShow)
  {
    List <BookingBookingDateUserApplicant> listBookingBookingDateUserApplicant = new ArrayList<BookingBookingDateUserApplicant>();
    Integer bookingId = null;
    BookingBookingDateUserApplicant bookingBookingDateUserApplicant = null;
    for (BookingDateUserApplicant bookingDateUserApplicant : list)
    {
      bookingId = bookingDateUserApplicant.getBookingId();
      if (listBookingBookingDateUserApplicant.size() == 0)
      {
        bookingBookingDateUserApplicant = new BookingBookingDateUserApplicant(applicantId, bookingId, weekToShow);
        listBookingBookingDateUserApplicant.add(bookingBookingDateUserApplicant);
      }
      else
      {
        bookingBookingDateUserApplicant = getBookingBookingDateUserApplicantForBookingId(listBookingBookingDateUserApplicant, bookingId);
        if (bookingBookingDateUserApplicant == null)
        {
          bookingBookingDateUserApplicant = new BookingBookingDateUserApplicant(applicantId, bookingId, weekToShow);
          listBookingBookingDateUserApplicant.add(bookingBookingDateUserApplicant);
        }
      }
      bookingBookingDateUserApplicant.addBookingDateUserApplicantToList(bookingDateUserApplicant);
    }
    return listBookingBookingDateUserApplicant;
  }

  public BookingBookingDateUserApplicant getBookingBookingDateUserApplicantForBookingId(List<BookingBookingDateUserApplicant> listBookingBookingDateUserApplicant, Integer bookingId)
  {
    for (BookingBookingDateUserApplicant bookingBookingDateUserApplicant: listBookingBookingDateUserApplicant)
    {
      if (bookingBookingDateUserApplicant.getBookingId().equals(bookingId))
      {
        return bookingBookingDateUserApplicant;
      }
    }
    return null;
  }

  protected String getUnavailablesForApplicant(AgyService agyService, Applicant applicant)
  {
    // Set Calendar to Start of Today one year ago.
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new java.util.Date());
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.add(Calendar.YEAR, -1);
    java.util.Date oneYearAgoDate = calendar.getTime();
    // Set Calendar to End of Today 1 year ahead.
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    calendar.add(Calendar.YEAR, 2);
    java.util.Date oneYearInFutureDate = calendar.getTime();
    List<Unavailable> listUnavailableDates = agyService.getUnavailablesForApplicantInDateRange(applicant.getAgencyId(), applicant.getApplicantId(), true, oneYearAgoDate, oneYearInFutureDate);
    StringBuffer unavailableDates = new StringBuffer();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    for (Unavailable unavailable : listUnavailableDates)
    {
      if (unavailableDates.length() > 0)
      {
        unavailableDates.append(',');
      }
      unavailableDates.append(sdf.format(unavailable.getUnavailableDate()));
    }
    return unavailableDates.toString();
  }
}
