package com.helmet.application.agy;

import java.util.ArrayList;
import java.util.List;

import com.helmet.bean.BookingDateUserApplicant;

public class BookingBookingDateUserApplicant
{
  private Integer applicantId;
  private Integer bookingId;
  private Integer weekToShow;
  
  private List<BookingDateUserApplicant> list = new ArrayList<BookingDateUserApplicant>();
  
  public BookingBookingDateUserApplicant(Integer applicantId, Integer bookingId, Integer weekToShow)
  {
    super();
    this.applicantId = applicantId;
    this.bookingId = bookingId;
    this.weekToShow = weekToShow;
  }

  public Integer getApplicantId()
  {
    return applicantId;
  }

  public Integer getBookingId()
  {
    return bookingId;
  }

  public Integer getWeekToShow()
  {
    return weekToShow;
  }

  public List<BookingDateUserApplicant> getList()
  {
    return list;
  }

  public void setBookingId(Integer bookingId)
  {
    this.bookingId = bookingId;
  }

  public BookingDateUserApplicant getFirstBookingDate()
  {
    return list.size() == 0 ? null : list.get(0);
  }
  
  public BookingDateUserApplicant addBookingDateUserApplicantToList(BookingDateUserApplicant bookingDateUserApplicant)
  {
    list.add(bookingDateUserApplicant);
    return bookingDateUserApplicant;
  }

  public Boolean getCanMultiAuthorize()
  {
    return checkAllBookingDateUserApplicantsCanBeAuthorized();
  }
  
  public Boolean checkAllBookingDateUserApplicantsCanBeAuthorized()
  {
    Boolean canMultiAuthorize = list.size() == 0 ?  new Boolean(false) : new Boolean(true);
    for (BookingDateUserApplicant bookingDateUserApplicant: list)
    {
      if (!bookingDateUserApplicant.getCanBeAuthorized())
      {
        canMultiAuthorize = new Boolean(false);
        break;
      }
    }
    return canMultiAuthorize;
  }

  public String getSelectedBookingDates()
  {
    if (getCanMultiAuthorize())
    {
      return getCanBeAuthorizedBookingDateIds();
    }
    else if (getCanMultiInvoice())
    {
      return getCanBeInvoicedBookingDateIds();
    }
    else
    {
      return null;
    }
  }
  
  public String getCanBeAuthorizedBookingDateIds()
  {
    StringBuffer bookingDateIds = new StringBuffer(); 
    for (BookingDateUserApplicant bookingDateUserApplicant: list)
    {
      if (bookingDateUserApplicant.getCanBeAuthorized())
      {
        if (bookingDateIds.length() > 0)
        {
          bookingDateIds.append(",");
        }
        bookingDateIds.append(bookingDateUserApplicant.getBookingDateId());
      }
    }
    return bookingDateIds.toString();
  }

  public Boolean getCanMultiInvoice()
  {
    return checkAllBookingDateUserApplicantsCanBeInvoiced();
  }
  
  public Boolean checkAllBookingDateUserApplicantsCanBeInvoiced()
  {
    Boolean canMultiInvoice = list.size() == 0 ?  new Boolean(false) : new Boolean(true);
    for (BookingDateUserApplicant bookingDateUserApplicant: list)
    {
      if (!bookingDateUserApplicant.getCanBeInvoiced())
      {
        canMultiInvoice = new Boolean(false);
        break;
      }
    }
    return canMultiInvoice;
  }

  public String getCanBeInvoicedBookingDateIds()
  {
    StringBuffer bookingDateIds = new StringBuffer(); 
    for (BookingDateUserApplicant bookingDateUserApplicant: list)
    {
      if (bookingDateUserApplicant.getCanBeInvoiced())
      {
        if (bookingDateIds.length() > 0)
        {
          bookingDateIds.append(",");
        }
        bookingDateIds.append(bookingDateUserApplicant.getBookingDateId());
      }
    }
    return bookingDateIds.toString();
  }
  
}
