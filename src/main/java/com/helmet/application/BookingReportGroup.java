package com.helmet.application;

import java.util.ArrayList;
import java.util.List;

import com.helmet.application.agy.AgyConstants;
import com.helmet.bean.BookingGradeAgy;

public class BookingReportGroup extends ClientSiteLocationReportGroup
{
  private List<BookingGradeAgy> listBookingGradeAgy;

  
  public BookingReportGroup(BookingGradeAgy bookingGradeAgy)
  {
    super(bookingGradeAgy.getClientId(), bookingGradeAgy.getClientName(), bookingGradeAgy.getSiteId(), bookingGradeAgy.getSiteName(), bookingGradeAgy.getLocationId(), bookingGradeAgy.getLocationName());
    listBookingGradeAgy = new ArrayList<BookingGradeAgy>();
  }


  public void addBookingGradeAgyToList(BookingGradeAgy bookingGradeAgy)
  {
    listBookingGradeAgy.add(bookingGradeAgy);
  }

  public List<BookingGradeAgy> getListBookingGradeAgy()
  {
    return listBookingGradeAgy;
  }

  public int getNoOfActiveBookings()
  {
    int noOfActiveBookings = 0;
    for (BookingGradeAgy bookingGradeAgy : listBookingGradeAgy)
    {
      if (bookingGradeAgy.getActive())
      {
        ++noOfActiveBookings;
      }
    }
    return  noOfActiveBookings; 
  }
  
  public int getNoOfInactiveBookings()
  {
    int noOfInactiveBookings = 0;
    for (BookingGradeAgy bookingGradeAgy : listBookingGradeAgy)
    {
      if (!bookingGradeAgy.getActive())
      {
        ++noOfInactiveBookings;
      }
    }
    return  noOfInactiveBookings; 
  }
  
  public int getNoOfNhsBookings()
  {
    int noOfNhsBookings = 0;
    for (BookingGradeAgy bookingGradeAgy : listBookingGradeAgy)
    {
      if (bookingGradeAgy.getActive() && bookingGradeAgy.getBookingReference().startsWith(AgyConstants.NHS_BOOKINGS_BANK_REQUEST_NUMBER_LABEL))
      {
        ++noOfNhsBookings;
      }
    }
    return  noOfNhsBookings; 
  }
  
}
