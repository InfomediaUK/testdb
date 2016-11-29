package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.BookingExpense;
import com.helmet.bean.Expense;
import com.helmet.persistence.BookingExpenseDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultBookingExpenseDAO extends JdbcDaoSupport implements BookingExpenseDAO {

	private static StringBuffer insertBookingExpenseSQL;

	private static StringBuffer updateBookingExpenseSQL;

	private static StringBuffer deleteBookingExpenseSQL;

	private static StringBuffer selectBookingExpenseSQL;

	private static StringBuffer selectBookingExpensesSQL;

	private static StringBuffer selectBookingExpensesForBookingSQL;

	private static StringBuffer selectActiveBookingExpensesForBookingSQL;

	private static StringBuffer selectBookingExpensesForBookingNotForBookingDateSQL;

	public static void init() {
		// Get insert BookingExpense SQL.
		insertBookingExpenseSQL = new StringBuffer();
		insertBookingExpenseSQL.append("INSERT INTO BOOKINGEXPENSE ");
		insertBookingExpenseSQL.append("(  ");
		insertBookingExpenseSQL.append("  BOOKINGEXPENSEID, ");
		insertBookingExpenseSQL.append("  BOOKINGID, ");
		insertBookingExpenseSQL.append("  EXPENSEID, ");
		insertBookingExpenseSQL.append("  EXPENSENAME, ");
		insertBookingExpenseSQL.append("  EXPENSEDESCRIPTION, ");
		insertBookingExpenseSQL.append("  EXPENSECODE, ");
		insertBookingExpenseSQL.append("  EXPENSEMULTIPLIER, ");
		insertBookingExpenseSQL.append("  EXPENSEVATRATE, ");
		insertBookingExpenseSQL.append("  EXPENSEDISPLAYORDER, ");
		insertBookingExpenseSQL.append("  CREATIONTIMESTAMP, ");
		insertBookingExpenseSQL.append("  AUDITORID, ");
		insertBookingExpenseSQL.append("  AUDITTIMESTAMP ");
		insertBookingExpenseSQL.append(")  ");
		insertBookingExpenseSQL.append("VALUES  ");
		insertBookingExpenseSQL.append("(  ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^, ");
		insertBookingExpenseSQL.append("  ^ ");
		insertBookingExpenseSQL.append(")  ");
		// Get update BookingExpense SQL.
		updateBookingExpenseSQL = new StringBuffer();
		updateBookingExpenseSQL.append("UPDATE BOOKINGEXPENSE ");
		updateBookingExpenseSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateBookingExpenseSQL.append("     EXPENSEID = ^, ");
		updateBookingExpenseSQL.append("     EXPENSENAME = ^, ");
		updateBookingExpenseSQL.append("     EXPENSEDESCRIPTION = ^, ");
		updateBookingExpenseSQL.append("     EXPENSECODE = ^, ");
		updateBookingExpenseSQL.append("     EXPENSEMULTIPLIER = ^, ");
		updateBookingExpenseSQL.append("     EXPENSEVATRATE = ^, ");
		updateBookingExpenseSQL.append("     EXPENSEDISPLAYORDER = ^, ");
		updateBookingExpenseSQL.append("     AUDITORID = ^, ");
		updateBookingExpenseSQL.append("     AUDITTIMESTAMP = ^ ");
		updateBookingExpenseSQL.append("WHERE BOOKINGEXPENSEID = ^ ");
		updateBookingExpenseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete BookingExpense SQL.
		deleteBookingExpenseSQL = new StringBuffer();
		deleteBookingExpenseSQL.append("UPDATE BOOKINGEXPENSE ");
		deleteBookingExpenseSQL.append("SET ACTIVE = FALSE, ");
		deleteBookingExpenseSQL.append("    AUDITORID = ^, ");
		deleteBookingExpenseSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteBookingExpenseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteBookingExpenseSQL.append("WHERE BOOKINGEXPENSEID = ^ ");
		deleteBookingExpenseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select BookingExpenses SQL.
		selectBookingExpensesSQL = new StringBuffer();
		selectBookingExpensesSQL.append("SELECT B.BOOKINGEXPENSEID, ");
		selectBookingExpensesSQL.append("  B.BOOKINGID, ");
		selectBookingExpensesSQL.append("  B.EXPENSEID, ");
		selectBookingExpensesSQL.append("  B.EXPENSENAME, ");
		selectBookingExpensesSQL.append("  B.EXPENSEDESCRIPTION, ");
		selectBookingExpensesSQL.append("  B.EXPENSECODE, ");
		selectBookingExpensesSQL.append("  B.EXPENSEMULTIPLIER, ");
		selectBookingExpensesSQL.append("  B.EXPENSEVATRATE, ");
		selectBookingExpensesSQL.append("  B.EXPENSEDISPLAYORDER, ");
		selectBookingExpensesSQL.append("  B.CREATIONTIMESTAMP, ");
		selectBookingExpensesSQL.append("  B.AUDITORID, ");
		selectBookingExpensesSQL.append("  B.AUDITTIMESTAMP, ");
		selectBookingExpensesSQL.append("  B.ACTIVE, ");
		selectBookingExpensesSQL.append("  B.NOOFCHANGES ");
        //
		selectBookingExpensesSQL.append("FROM BOOKINGEXPENSE B ");
		// Get select BookingExpenses for Booking SQL.
		selectBookingExpensesForBookingSQL = new StringBuffer(selectBookingExpensesSQL);
		selectBookingExpensesForBookingSQL.append("WHERE B.BOOKINGID = ^ ");
		// Get select Active BookingExpenses for BookingSQL.
		selectActiveBookingExpensesForBookingSQL = new StringBuffer(selectBookingExpensesForBookingSQL);
		selectActiveBookingExpensesForBookingSQL.append("AND B.ACTIVE = TRUE ");
		// Get select BookingExpense SQL.
		selectBookingExpenseSQL = new StringBuffer(selectBookingExpensesSQL);
		selectBookingExpenseSQL.append("WHERE B.BOOKINGEXPENSEID = ^ ");
		// Get select BookingExpenses for Booking NOT for BookingDate SQL.
		selectBookingExpensesForBookingNotForBookingDateSQL = new StringBuffer(selectActiveBookingExpensesForBookingSQL);
		selectBookingExpensesForBookingNotForBookingDateSQL.append("AND NOT EXISTS ");
		selectBookingExpensesForBookingNotForBookingDateSQL.append("( ");
		selectBookingExpensesForBookingNotForBookingDateSQL.append(" SELECT NULL ");
		selectBookingExpensesForBookingNotForBookingDateSQL.append(" FROM BOOKINGDATEEXPENSE BDE ");
		selectBookingExpensesForBookingNotForBookingDateSQL.append(" WHERE BDE.ACTIVE = TRUE ");
		selectBookingExpensesForBookingNotForBookingDateSQL.append(" AND BDE.BOOKINGDATEID = ^ ");
		selectBookingExpensesForBookingNotForBookingDateSQL.append(" AND BDE.BOOKINGEXPENSEID = B.BOOKINGEXPENSEID ");
		selectBookingExpensesForBookingNotForBookingDateSQL.append(") ");
		// ORDER BYs
		selectBookingExpensesForBookingSQL.append("ORDER BY B.EXPENSEDISPLAYORDER, B.EXPENSENAME ");
		selectActiveBookingExpensesForBookingSQL.append("ORDER BY B.EXPENSEDISPLAYORDER, B.EXPENSENAME ");
		selectBookingExpensesForBookingNotForBookingDateSQL.append("ORDER BY B.EXPENSEDISPLAYORDER, B.EXPENSENAME ");
	}

	public int insertBookingExpense(BookingExpense bookingExpense, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertBookingExpenseSQL.toString());
		// Replace the parameters with supplied values.
		bookingExpense.setBookingExpenseId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "bookingExpense"));
		Utilities.replace(sql, bookingExpense.getBookingExpenseId());
		Utilities.replace(sql, bookingExpense.getBookingId());
		Utilities.replace(sql, bookingExpense.getExpenseId());
		Utilities.replaceAndQuote(sql, bookingExpense.getExpenseName());
		Utilities.replaceAndQuoteNullable(sql, bookingExpense.getExpenseDescription());
		Utilities.replaceAndQuote(sql, bookingExpense.getExpenseCode());
		Utilities.replace(sql, bookingExpense.getExpenseMultiplier());
		Utilities.replace(sql, bookingExpense.getExpenseVatRate());
		Utilities.replace(sql, bookingExpense.getExpenseDisplayOrder());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingExpense(BookingExpense bookingExpense, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingExpenseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingExpense.getExpenseId());
		Utilities.replaceAndQuote(sql, bookingExpense.getExpenseName());
		Utilities.replaceAndQuoteNullable(sql, bookingExpense.getExpenseDescription());
		Utilities.replaceAndQuote(sql, bookingExpense.getExpenseCode());
		Utilities.replace(sql, bookingExpense.getExpenseMultiplier());
		Utilities.replace(sql, bookingExpense.getExpenseVatRate());
		Utilities.replace(sql, bookingExpense.getExpenseDisplayOrder());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingExpense.getBookingExpenseId());
		Utilities.replace(sql, bookingExpense.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteBookingExpense(Integer bookingExpenseId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteBookingExpenseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingExpenseId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public BookingExpense getBookingExpense(Integer bookingExpenseId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingExpenseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingExpenseId);
		return (BookingExpense) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingExpense.class.getName());
	}

	public List<BookingExpense> getBookingExpensesForBooking(Integer bookingId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingExpensesForBookingSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingExpensesForBookingSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingExpense.class.getName());

	}

	public List<BookingExpense> getBookingExpensesForBookingNotForBookingDate(Integer bookingId, Integer bookingDateId) {

		StringBuffer sql = new StringBuffer(selectBookingExpensesForBookingNotForBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingId);
		Utilities.replace(sql, bookingDateId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingExpense.class.getName());

	}

	public int deleteBookingExpensesForBooking(Integer bookingId, Integer auditorId) {

		int i = 0;
		List<BookingExpense> bookingExpenses = getBookingExpensesForBooking(bookingId, true);
		for (BookingExpense bookingExpense: bookingExpenses) {
            i += deleteBookingExpense(bookingExpense.getBookingExpenseId(), bookingExpense.getNoOfChanges(), auditorId);
		}
		return i;

	}

	public int insertBookingExpenses(Integer bookingId, Expense[] bookingExpenses, Integer auditorId) {

		int i = 0;
        for (Expense expense: bookingExpenses) {
        	BookingExpense bookingExpense = new BookingExpense();
        	bookingExpense.setBookingId(bookingId);
        	bookingExpense.setExpenseId(expense.getExpenseId());
        	bookingExpense.setExpenseName(expense.getName().trim());
        	bookingExpense.setExpenseDescription(expense.getDescription());
        	bookingExpense.setExpenseCode(expense.getCode());
        	bookingExpense.setExpenseMultiplier(expense.getMultiplier());
        	bookingExpense.setExpenseVatRate(expense.getVatRate());
        	bookingExpense.setExpenseDisplayOrder(expense.getDisplayOrder());
        	i += insertBookingExpense(bookingExpense, auditorId);
        }
        return i;
        
	}

}
