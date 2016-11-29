package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Expense;
import com.helmet.persistence.ExpenseDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultExpenseDAO extends JdbcDaoSupport implements ExpenseDAO {

	private static StringBuffer insertExpenseSQL;

	private static StringBuffer updateExpenseSQL;

	private static StringBuffer updateExpenseDisplayOrderSQL;

	private static StringBuffer deleteExpenseSQL;

	private static StringBuffer selectExpenseSQL;

	private static StringBuffer selectExpenseForNameSQL;

	private static StringBuffer selectExpenseForCodeSQL;

	private static StringBuffer selectExpensesSQL;

	private static StringBuffer selectExpensesForLocationSQL;

	private static StringBuffer selectActiveExpensesForLocationSQL;

	public static void init() {
		// Get insert Expense SQL.
		insertExpenseSQL = new StringBuffer();
		insertExpenseSQL.append("INSERT INTO EXPENSE ");
		insertExpenseSQL.append("(  ");
		insertExpenseSQL.append("  EXPENSEID, ");
		insertExpenseSQL.append("  LOCATIONID, ");
		insertExpenseSQL.append("  NAME, ");
		insertExpenseSQL.append("  DESCRIPTION, ");
		insertExpenseSQL.append("  CODE, ");
		insertExpenseSQL.append("  MULTIPLIER, ");
		insertExpenseSQL.append("  VATRATE, ");
		insertExpenseSQL.append("  CREATIONTIMESTAMP, ");
		insertExpenseSQL.append("  AUDITORID, ");
		insertExpenseSQL.append("  AUDITTIMESTAMP ");
		insertExpenseSQL.append(")  ");
		insertExpenseSQL.append("VALUES  ");
		insertExpenseSQL.append("(  ");
		insertExpenseSQL.append("  ^, ");
		insertExpenseSQL.append("  ^, ");
		insertExpenseSQL.append("  ^, ");
		insertExpenseSQL.append("  ^, ");
		insertExpenseSQL.append("  ^, ");
		insertExpenseSQL.append("  ^, ");
		insertExpenseSQL.append("  ^, ");
		insertExpenseSQL.append("  ^, ");
		insertExpenseSQL.append("  ^, ");
		insertExpenseSQL.append("  ^ ");
		insertExpenseSQL.append(")  ");
		// Get update Expense SQL.
		updateExpenseSQL = new StringBuffer();
		updateExpenseSQL.append("UPDATE EXPENSE ");
		updateExpenseSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateExpenseSQL.append("     LOCATIONID = ^, ");
		updateExpenseSQL.append("     NAME = ^, ");
		updateExpenseSQL.append("     DESCRIPTION = ^, ");
		updateExpenseSQL.append("     CODE = ^, ");
		updateExpenseSQL.append("     MULTIPLIER = ^, ");
		updateExpenseSQL.append("     VATRATE = ^, ");
		updateExpenseSQL.append("     AUDITORID = ^, ");
		updateExpenseSQL.append("     AUDITTIMESTAMP = ^ ");
		updateExpenseSQL.append("WHERE EXPENSEID = ^ ");
		updateExpenseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateExpenseDisplayOrder SQL.
		updateExpenseDisplayOrderSQL = new StringBuffer();
		updateExpenseDisplayOrderSQL.append("UPDATE EXPENSE ");
		updateExpenseDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateExpenseDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateExpenseDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateExpenseDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateExpenseDisplayOrderSQL.append("WHERE EXPENSEID = ^ ");
		updateExpenseDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Expense SQL.
		deleteExpenseSQL = new StringBuffer();
		// deleteExpenseSQL = new StringBuffer();
		deleteExpenseSQL.append("UPDATE EXPENSE ");
		deleteExpenseSQL.append("SET ACTIVE = FALSE, ");
		deleteExpenseSQL.append("    AUDITORID = ^, ");
		deleteExpenseSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteExpenseSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteExpenseSQL.append("WHERE EXPENSEID = ^ ");
		deleteExpenseSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Expenses SQL.
		selectExpensesSQL = new StringBuffer();
		selectExpensesSQL.append("SELECT E.EXPENSEID, ");
		selectExpensesSQL.append("       E.LOCATIONID, ");
		selectExpensesSQL.append("       E.NAME, ");
		selectExpensesSQL.append("       E.DESCRIPTION, ");
		selectExpensesSQL.append("       E.CODE, ");
		selectExpensesSQL.append("       E.MULTIPLIER, ");
		selectExpensesSQL.append("       E.VATRATE, ");
		selectExpensesSQL.append("       E.DISPLAYORDER, ");
		selectExpensesSQL.append("       E.CREATIONTIMESTAMP, ");
		selectExpensesSQL.append("       E.AUDITORID, ");
		selectExpensesSQL.append("       E.AUDITTIMESTAMP, ");
		selectExpensesSQL.append("       E.ACTIVE, ");
		selectExpensesSQL.append("       E.NOOFCHANGES ");
		selectExpensesSQL.append("FROM EXPENSE E ");
		// Get select Expenses for Location SQL.
		selectExpensesForLocationSQL = new StringBuffer(selectExpensesSQL);
		selectExpensesForLocationSQL.append("WHERE E.LOCATIONID = ^ ");
		// Get select Active Expenses for Location SQL.
		selectActiveExpensesForLocationSQL = new StringBuffer(selectExpensesForLocationSQL);
		selectActiveExpensesForLocationSQL.append("AND E.ACTIVE = TRUE ");
		// Get select Expense SQL.
		selectExpenseSQL = new StringBuffer(selectExpensesSQL);
		selectExpenseSQL.append("WHERE E.EXPENSEID = ^ ");
		// Get select Expense for Name SQL.
		selectExpenseForNameSQL = new StringBuffer(selectActiveExpensesForLocationSQL);
		selectExpenseForNameSQL.append("AND E.NAME = ^ ");
		// Get select Expense for Code SQL.
		selectExpenseForCodeSQL = new StringBuffer(selectActiveExpensesForLocationSQL);
		selectExpenseForCodeSQL.append("AND E.CODE = ^ ");
		// ORDER BY 
		selectExpensesForLocationSQL.append("ORDER BY E.DISPLAYORDER, E.NAME ");
		selectActiveExpensesForLocationSQL.append("ORDER BY E.DISPLAYORDER, E.NAME ");
	}

	public int insertExpense(Expense expense, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertExpenseSQL.toString());
		// Replace the parameters with supplied values.
		expense.setExpenseId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "expense"));
		Utilities.replace(sql, expense.getExpenseId());
		Utilities.replace(sql, expense.getLocationId());
		Utilities.replaceAndQuote(sql, expense.getName());
		Utilities.replaceAndQuoteNullable(sql, expense.getDescription());
		Utilities.replaceAndQuote(sql, expense.getCode());
		Utilities.replace(sql, expense.getMultiplier());
		Utilities.replace(sql, expense.getVatRate());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateExpense(Expense expense, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateExpenseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, expense.getLocationId());
		Utilities.replaceAndQuote(sql, expense.getName());
		Utilities.replaceAndQuoteNullable(sql, expense.getDescription());
		Utilities.replaceAndQuote(sql, expense.getCode());
		Utilities.replace(sql, expense.getMultiplier());
		Utilities.replace(sql, expense.getVatRate());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, expense.getExpenseId());
		Utilities.replace(sql, expense.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteExpense(Integer expenseId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteExpenseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, expenseId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public Expense getExpense(Integer expenseId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectExpenseSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, expenseId);
		return (Expense) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Expense.class.getName());
	}

	public Expense getExpenseForName(Integer locationId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectExpenseForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		Utilities.replaceAndQuote(sql, name);
		return (Expense) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Expense.class.getName());
	}

	public Expense getExpenseForCode(Integer locationId, String code) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectExpenseForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		Utilities.replaceAndQuote(sql, code);
		return (Expense) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Expense.class.getName());
	}

	public List<Expense> getExpensesForLocation(Integer locationId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveExpensesForLocationSQL.toString());
		}
		else {
			sql = new StringBuffer(selectExpensesForLocationSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Expense.class.getName());

	}

	public int updateExpenseDisplayOrder(Integer expenseId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateExpenseDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, expenseId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}


}
