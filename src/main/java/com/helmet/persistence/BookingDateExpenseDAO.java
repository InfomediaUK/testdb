package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.BookingDateExpense;
import com.helmet.bean.BookingDateExpenseUser;

public interface BookingDateExpenseDAO {

	public BookingDateExpense getBookingDateExpense(Integer bookingDateExpenseId);
    public int insertBookingDateExpense(BookingDateExpense bookingDateExpense, Integer auditorId);
    public int updateBookingDateExpense(BookingDateExpense bookingDateExpense, Integer auditorId);
    public int deleteBookingDateExpense(Integer bookingDateExpenseId, Integer noOfChanges, Integer auditorId);
	public List<BookingDateExpenseUser> getBookingDateExpenseUsersForBookingDate(Integer bookingDateId);
    
}
