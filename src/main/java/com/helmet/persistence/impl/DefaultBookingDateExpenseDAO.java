package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.BookingDateExpense;
import com.helmet.bean.BookingDateExpenseUser;
import com.helmet.bean.BookingExpense;
import com.helmet.persistence.BookingDateExpenseDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultBookingDateExpenseDAO extends JdbcDaoSupport implements BookingDateExpenseDAO {

	private static StringBuffer insertBookingDateExpenseSQL;

	private static StringBuffer updateBookingDateExpenseSQL;
	
	private static StringBuffer deleteBookingDateExpenseSQL;
	
	private static StringBuffer selectBookingDateExpensesSQL;

	private static StringBuffer selectActiveBookingDateExpensesSQL;

	private static StringBuffer selectBookingDateExpenseSQL;

	private static StringBuffer selectBookingDateExpenseUsersSQL;

	private static StringBuffer selectBookingDateExpenseUserSQL;

	private static StringBuffer selectBookingDateExpenseUsersForBookingDateSQL;

	public static void init() {
		// Get insert BookingDate SQL.
		insertBookingDateExpenseSQL = new StringBuffer();
		insertBookingDateExpenseSQL.append("INSERT INTO BOOKINGDATEEXPENSE ");
		insertBookingDateExpenseSQL.append("(  ");
		insertBookingDateExpenseSQL.append("  BOOKINGDATEEXPENSEID, ");
		insertBookingDateExpenseSQL.append("  BOOKINGDATEID, ");
		insertBookingDateExpenseSQL.append("  BOOKINGEXPENSEID, ");
		insertBookingDateExpenseSQL.append("  QTY, ");
		insertBookingDateExpenseSQL.append("  VALUE, ");
		insertBookingDateExpenseSQL.append("  VATVALUE, ");
		insertBookingDateExpenseSQL.append("  FILENAME, ");
		insertBookingDateExpenseSQL.append("  TEXT, ");
		insertBookingDateExpenseSQL.append("  CREATIONTIMESTAMP, ");
		insertBookingDateExpenseSQL.append("  AUDITORID, ");
		insertBookingDateExpenseSQL.append("  AUDITTIMESTAMP ");
		insertBookingDateExpenseSQL.append(")  ");
		insertBookingDateExpenseSQL.append("VALUES  ");
		insertBookingDateExpenseSQL.append("(  ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^, ");
		insertBookingDateExpenseSQL.append("  ^ ");
		insertBookingDateExpenseSQL.append(")  ");
		// Get update BookingDateExpense SQL.
		updateBookingDateExpenseSQL = new StringBuffer();
		updateBookingDateExpenseSQL.append("UPDATE BOOKINGDATEEXPENSE ");
		updateBookingDateExpenseSQL.append("SET BOOKINGDATEID = ^, ");
		updateBookingDateExpenseSQL.append("    BOOKINGEXPENSEID = ^, ");
		updateBookingDateExpenseSQL.append("    QTY = ^, ");
		updateBookingDateExpenseSQL.append("    VALUE = ^, ");
		updateBookingDateExpenseSQL.append("    VATVALUE = ^, ");
		updateBookingDateExpenseSQL.append("    FILENAME = ^, ");
		updateBookingDateExpenseSQL.append("    TEXT = ^, ");
		updateBookingDateExpenseSQL.append("    AUDITORID = ^, ");
		updateBookingDateExpenseSQL.append("    AUDITTIMESTAMP = ^, ");
		updateBookingDateExpenseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateBookingDateExpenseSQL.append("WHERE BOOKINGDATEEXPENSEID = ^ ");
		updateBookingDateExpenseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete BookingDateExpense SQL.
		deleteBookingDateExpenseSQL = new StringBuffer();
		deleteBookingDateExpenseSQL.append("UPDATE BOOKINGDATEEXPENSE ");
		deleteBookingDateExpenseSQL.append("SET ACTIVE = FALSE, ");
		deleteBookingDateExpenseSQL.append("    AUDITORID = ^, ");
		deleteBookingDateExpenseSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteBookingDateExpenseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteBookingDateExpenseSQL.append("WHERE BOOKINGDATEEXPENSEID = ^ ");
		deleteBookingDateExpenseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select BookingDateExpenses SQL.
		selectBookingDateExpensesSQL = new StringBuffer();
		selectBookingDateExpensesSQL.append("SELECT BOOKINGDATEEXPENSEID, ");
		selectBookingDateExpensesSQL.append("    BOOKINGDATEID, ");
		selectBookingDateExpensesSQL.append("    BOOKINGEXPENSEID, ");
		selectBookingDateExpensesSQL.append("    QTY, ");
		selectBookingDateExpensesSQL.append("    VALUE, ");
		selectBookingDateExpensesSQL.append("    VATVALUE, ");
		selectBookingDateExpensesSQL.append("    FILENAME, ");
		selectBookingDateExpensesSQL.append("    TEXT, ");
		selectBookingDateExpensesSQL.append("    CREATIONTIMESTAMP, ");
		selectBookingDateExpensesSQL.append("    AUDITORID, ");
		selectBookingDateExpensesSQL.append("    AUDITTIMESTAMP, ");
		selectBookingDateExpensesSQL.append("    ACTIVE, ");
		selectBookingDateExpensesSQL.append("    NOOFCHANGES  ");
		selectBookingDateExpensesSQL.append("FROM BOOKINGDATEEXPENSE ");
		// Get select Active BookingDateExpenses SQL.
		selectActiveBookingDateExpensesSQL = new StringBuffer(selectBookingDateExpensesSQL);
		selectActiveBookingDateExpensesSQL.append("WHERE ACTIVE = TRUE ");
		// Get select BookingDateExpense SQL.
		selectBookingDateExpenseSQL = new StringBuffer(selectBookingDateExpensesSQL);
		selectBookingDateExpenseSQL.append("WHERE BOOKINGDATEEXPENSEID = ^ ");
		// Get select BookingDateExpenseUsers SQL.
		selectBookingDateExpenseUsersSQL = new StringBuffer();
		selectBookingDateExpenseUsersSQL.append("SELECT BDE.BOOKINGDATEEXPENSEID, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.BOOKINGDATEID, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.BOOKINGEXPENSEID, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.QTY, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.VALUE, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.VATVALUE, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.FILENAME, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.TEXT, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.CREATIONTIMESTAMP, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.AUDITORID, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.AUDITTIMESTAMP, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.ACTIVE, ");
		selectBookingDateExpenseUsersSQL.append("    BDE.NOOFCHANGES,  ");
		selectBookingDateExpenseUsersSQL.append("    BE.EXPENSENAME,  ");
		selectBookingDateExpenseUsersSQL.append("    BE.EXPENSEDESCRIPTION,  ");
		selectBookingDateExpenseUsersSQL.append("    BE.EXPENSECODE,  ");
		selectBookingDateExpenseUsersSQL.append("    BE.EXPENSEMULTIPLIER,  ");
		selectBookingDateExpenseUsersSQL.append("    BE.EXPENSEVATRATE,  ");
		selectBookingDateExpenseUsersSQL.append("    BE.EXPENSEDISPLAYORDER  ");
		selectBookingDateExpenseUsersSQL.append("FROM BOOKINGDATEEXPENSE BDE, ");
		selectBookingDateExpenseUsersSQL.append("     BOOKINGEXPENSE BE ");
		selectBookingDateExpenseUsersSQL.append("WHERE BE.BOOKINGEXPENSEID = BDE.BOOKINGEXPENSEID ");
		selectBookingDateExpenseUsersSQL.append("AND BE.ACTIVE = TRUE ");
		selectBookingDateExpenseUsersSQL.append("AND BDE.ACTIVE = TRUE ");
		// Get select BookingDateExpenseUsers for BookingDate SQL.
		selectBookingDateExpenseUsersForBookingDateSQL = new StringBuffer(selectBookingDateExpenseUsersSQL);
		selectBookingDateExpenseUsersForBookingDateSQL.append("AND BOOKINGDATEID = ^ ");
		// Get select BookingDateExpenseUser SQL.
		selectBookingDateExpenseUserSQL = new StringBuffer(selectBookingDateExpenseUsersSQL);
		selectBookingDateExpenseUserSQL.append("AND BOOKINGDATEEXPENSEID = ^ ");

		// ORDER BYs
		selectBookingDateExpenseUsersForBookingDateSQL.append("ORDER BY BE.EXPENSEDISPLAYORDER, BE.EXPENSENAME ");

	}

	public int insertBookingDateExpense(BookingDateExpense bookingDateExpense, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertBookingDateExpenseSQL.toString());
		// Replace the parameters with supplied values.
		bookingDateExpense.setBookingDateExpenseId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "bookingDateExpense"));
		Utilities.replace(sql, bookingDateExpense.getBookingDateExpenseId());
		Utilities.replace(sql, bookingDateExpense.getBookingDateId());
		Utilities.replace(sql, bookingDateExpense.getBookingExpenseId());
		Utilities.replace(sql, bookingDateExpense.getQty());
		Utilities.replace(sql, bookingDateExpense.getValue());
		Utilities.replace(sql, bookingDateExpense.getVatValue());
        Utilities.replaceAndQuoteNullable(sql, bookingDateExpense.getFilename());
        Utilities.replaceAndQuoteNullable(sql, bookingDateExpense.getText());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateBookingDateExpense(BookingDateExpense bookingDateExpense, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateBookingDateExpenseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingDateExpense.getBookingDateId());
		Utilities.replace(sql, bookingDateExpense.getBookingExpenseId());
		Utilities.replace(sql, bookingDateExpense.getQty());
		Utilities.replace(sql, bookingDateExpense.getValue());
		Utilities.replace(sql, bookingDateExpense.getVatValue());
        Utilities.replaceAndQuoteNullable(sql, bookingDateExpense.getFilename());
        Utilities.replaceAndQuoteNullable(sql, bookingDateExpense.getText());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateExpense.getBookingDateExpenseId());
		Utilities.replace(sql, bookingDateExpense.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteBookingDateExpense(Integer bookingDateExpenseId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteBookingDateExpenseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateExpenseId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public BookingDateExpense getBookingDateExpense(Integer bookingDateExpenseId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBookingDateExpenseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingDateExpenseId);
		return (BookingDateExpense) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateExpense.class.getName());
	}

	public List<BookingDateExpenseUser> getBookingDateExpenseUsersForBookingDate(Integer bookingDateId) {

		StringBuffer sql = new StringBuffer(selectBookingDateExpenseUsersForBookingDateSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingDateId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateExpenseUser.class.getName());
	}
	
}
