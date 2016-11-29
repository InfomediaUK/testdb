package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.BookingDateHour;

public interface BookingDateHourDAO {

    public int deleteBookingDateHoursForBookingDate(Integer bookingDateId, Integer auditorId);
    public int insertBookingDateHours(List<BookingDateHour> bookingDateHours, Integer auditorId);
    public List<BookingDateHour> getBookingDateHoursForBookingDate(Integer bookingDateId, boolean showOnlyActive);
    
}
