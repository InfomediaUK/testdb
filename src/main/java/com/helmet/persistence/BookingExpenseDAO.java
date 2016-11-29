package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.BookingExpense;
import com.helmet.bean.Expense;

public interface BookingExpenseDAO {

	public List<BookingExpense> getBookingExpensesForBooking(Integer bookingId, boolean showOnlyActive);
	public List<BookingExpense> getBookingExpensesForBookingNotForBookingDate(Integer bookingId, Integer bookingDateId);

	public BookingExpense getBookingExpense(Integer bookingExpenseId);
	public int insertBookingExpense(BookingExpense bookingExpense, Integer auditorId);
	public int updateBookingExpense(BookingExpense bookingExpense, Integer auditorId);
	public int deleteBookingExpense(Integer bookingExpenseId, Integer noOfChanges, Integer auditorId);

    public int deleteBookingExpensesForBooking(Integer bookingId, Integer auditorId);
    public int insertBookingExpenses(Integer bookingId, Expense[] bookingExpenses, Integer auditorId);
	
}
