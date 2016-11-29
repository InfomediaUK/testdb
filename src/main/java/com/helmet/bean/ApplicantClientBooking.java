package com.helmet.bean;

import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ApplicantClientBooking extends Base
{
  private Integer bookingId;
  private Date minBookingDate;
  private Date maxBookingDate;

  public Integer getBookingId()
  {
    return bookingId;
  }

  public void setBookingId(Integer bookingId)
  {
    this.bookingId = bookingId;
  }

  public Date getMaxBookingDate()
  {
    return maxBookingDate;
  }

  public void setMaxBookingDate(Date maxBookingDate)
  {
    this.maxBookingDate = maxBookingDate;
  }

  public Date getMinBookingDate()
  {
    return minBookingDate;
  }

  public void setMinBookingDate(Date minBookingDate)
  {
    this.minBookingDate = minBookingDate;
  }

  public void load(SqlRowSet rs)
  {
    // DO NOT CALL SUPER.
    setBookingId(rs.getInt("BOOKINGID"));
    setMinBookingDate(rs.getDate("MINBOOKINGDATE"));
    setMaxBookingDate(rs.getDate("MAXBOOKINGDATE"));
  }

}
