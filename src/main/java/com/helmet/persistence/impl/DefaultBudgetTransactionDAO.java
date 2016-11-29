package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.BudgetTransactionUser;
import com.helmet.persistence.BudgetTransactionDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultBudgetTransactionDAO extends JdbcDaoSupport implements BudgetTransactionDAO {

	private static StringBuffer insertBudgetTransactionSQL;

//	private static StringBuffer updateBudgetTransactionSQL;

	private static StringBuffer deleteBudgetTransactionSQL;

	private static StringBuffer selectBudgetTransactionSQL;

	private static StringBuffer selectBudgetTransactionsSQL;

	private static StringBuffer selectBudgetTransactionUsersSQL;

	private static StringBuffer selectBudgetTransactionUsersForClientSQL;

	private static StringBuffer selectActiveBudgetTransactionUsersForClientSQL;
	
	private static StringBuffer selectBudgetTransactionsForLocationJobProfileSQL;

	public static void init() {
		// Get insert BudgetTransaction SQL.
		insertBudgetTransactionSQL = new StringBuffer();
		insertBudgetTransactionSQL.append("INSERT INTO BUDGETTRANSACTION ");
		insertBudgetTransactionSQL.append("(  ");
		insertBudgetTransactionSQL.append("  BUDGETTRANSACTIONID, ");
		insertBudgetTransactionSQL.append("  LOCATIONJOBPROFILEID, ");
		insertBudgetTransactionSQL.append("  VALUE, ");
		insertBudgetTransactionSQL.append("  VATVALUE, ");
		insertBudgetTransactionSQL.append("  EXPENSEVALUE, ");
		insertBudgetTransactionSQL.append("  NONPAYVALUE, ");
		insertBudgetTransactionSQL.append("  BALANCE, ");
		insertBudgetTransactionSQL.append("  VATBALANCE, ");
		insertBudgetTransactionSQL.append("  EXPENSEBALANCE, ");
		insertBudgetTransactionSQL.append("  NONPAYBALANCE, ");
		insertBudgetTransactionSQL.append("  COMMENT, ");
		insertBudgetTransactionSQL.append("  CREATIONTIMESTAMP, ");
		insertBudgetTransactionSQL.append("  AUDITORID, ");
		insertBudgetTransactionSQL.append("  AUDITTIMESTAMP ");
		insertBudgetTransactionSQL.append(")  ");
		insertBudgetTransactionSQL.append("VALUES  ");
		insertBudgetTransactionSQL.append("(  ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^, ");
		insertBudgetTransactionSQL.append("  ^ ");
		insertBudgetTransactionSQL.append(")  ");
//		// Get update BudgetTransaction SQL.
//		updateBudgetTransactionSQL = new StringBuffer();
//		updateBudgetTransactionSQL.append("UPDATE BUDGETTRANSACTION ");
//		updateBudgetTransactionSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
//		updateBudgetTransactionSQL.append("     LOCATIONJOBPROFILEID = ^, ");
//		updateBudgetTransactionSQL.append("     VALUE = ^, ");
//		updateBudgetTransactionSQL.append("     VATVALUE = ^, ");
//		updateBudgetTransactionSQL.append("     EXPENSEVALUE = ^, ");
//		updateBudgetTransactionSQL.append("     NONPAYVALUE = ^, ");
//		updateBudgetTransactionSQL.append("     BALANCE = BALANCE + ^, ");
//		updateBudgetTransactionSQL.append("     VATBALANCE = VATBALANCE + ^, ");
//		updateBudgetTransactionSQL.append("     EXPENSEBALANCE = EXPENSEBALANCE + ^, ");
//		updateBudgetTransactionSQL.append("     NONPAYBALANCE = NONPAYBALANCE + ^, ");
//		updateBudgetTransactionSQL.append("     COMMENT = ^, ");
//		updateBudgetTransactionSQL.append("     AUDITORID = ^, ");
//		updateBudgetTransactionSQL.append("     AUDITTIMESTAMP = ^ ");
//		updateBudgetTransactionSQL.append("WHERE BUDGETTRANSACTIONID = ^ ");
//		updateBudgetTransactionSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete BudgetTransaction SQL.
		deleteBudgetTransactionSQL = new StringBuffer();
		// deleteBudgetTransactionSQL = new StringBuffer();
		deleteBudgetTransactionSQL.append("UPDATE BUDGETTRANSACTION ");
		deleteBudgetTransactionSQL.append("SET ACTIVE = FALSE, ");
		deleteBudgetTransactionSQL.append("    AUDITORID = ^, ");
		deleteBudgetTransactionSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteBudgetTransactionSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteBudgetTransactionSQL.append("WHERE BUDGETTRANSACTIONID = ^ ");
		deleteBudgetTransactionSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select BudgetTransactions SQL.
		selectBudgetTransactionsSQL = new StringBuffer();
		selectBudgetTransactionsSQL.append("SELECT B.BUDGETTRANSACTIONID, ");
		selectBudgetTransactionsSQL.append("       B.LOCATIONJOBPROFILEID, ");
		selectBudgetTransactionsSQL.append("       B.VALUE, ");
		selectBudgetTransactionsSQL.append("       B.VATVALUE, ");
		selectBudgetTransactionsSQL.append("       B.EXPENSEVALUE, ");
		selectBudgetTransactionsSQL.append("       B.NONPAYVALUE, ");
		selectBudgetTransactionsSQL.append("       B.BALANCE, ");
		selectBudgetTransactionsSQL.append("       B.VATBALANCE, ");
		selectBudgetTransactionsSQL.append("       B.EXPENSEBALANCE, ");
		selectBudgetTransactionsSQL.append("       B.NONPAYBALANCE, ");
		selectBudgetTransactionsSQL.append("       B.COMMENT, ");
		selectBudgetTransactionsSQL.append("       B.CREATIONTIMESTAMP, ");
		selectBudgetTransactionsSQL.append("       B.AUDITORID, ");
		selectBudgetTransactionsSQL.append("       B.AUDITTIMESTAMP, ");
		selectBudgetTransactionsSQL.append("       B.ACTIVE, ");
		selectBudgetTransactionsSQL.append("       B.NOOFCHANGES ");
		// Get select BudgetTransactions for Client SQL.
		selectBudgetTransactionUsersSQL = new StringBuffer(selectBudgetTransactionsSQL);
		//
		selectBudgetTransactionsSQL.append("FROM BUDGETTRANSACTION B ");
		// Get select BudgetTransactionUsers for Client SQL.
		selectBudgetTransactionUsersForClientSQL = new StringBuffer(selectBudgetTransactionUsersSQL);
		selectBudgetTransactionUsersForClientSQL.append(", ");
		selectBudgetTransactionUsersForClientSQL.append("L.LOCATIONID, ");
		selectBudgetTransactionUsersForClientSQL.append("L.NAME AS LOCATIONNAME, ");
		selectBudgetTransactionUsersForClientSQL.append("JP.JOBPROFILEID, ");
		selectBudgetTransactionUsersForClientSQL.append("JP.NAME AS JOBPROFILENAME ");
		selectBudgetTransactionUsersForClientSQL.append("FROM BUDGETTRANSACTION B, ");
		selectBudgetTransactionUsersForClientSQL.append("LOCATIONJOBPROFILE LJP, ");
		selectBudgetTransactionUsersForClientSQL.append("LOCATION L, ");
		selectBudgetTransactionUsersForClientSQL.append("SITE S, ");
		selectBudgetTransactionUsersForClientSQL.append("JOBPROFILE JP ");
		selectBudgetTransactionUsersForClientSQL.append("WHERE LJP.LOCATIONJOBPROFILEID = B.LOCATIONJOBPROFILEID ");
		selectBudgetTransactionUsersForClientSQL.append("AND LJP.ACTIVE = TRUE ");
		selectBudgetTransactionUsersForClientSQL.append("AND JP.JOBPROFILEID = LJP.JOBPROFILEID ");
		selectBudgetTransactionUsersForClientSQL.append("AND JP.ACTIVE = TRUE ");
		selectBudgetTransactionUsersForClientSQL.append("AND L.LOCATIONID = LJP.LOCATIONID ");
		selectBudgetTransactionUsersForClientSQL.append("AND L.ACTIVE = TRUE ");
		selectBudgetTransactionUsersForClientSQL.append("AND S.SITEID = L.SITEID ");
		selectBudgetTransactionUsersForClientSQL.append("AND S.ACTIVE = TRUE ");
		selectBudgetTransactionUsersForClientSQL.append("AND S.CLIENTID = ^ ");
		// Get select Active BudgetTransactions for Client SQL.
		selectActiveBudgetTransactionUsersForClientSQL = new StringBuffer(selectBudgetTransactionUsersForClientSQL);
		selectActiveBudgetTransactionUsersForClientSQL.append("AND B.ACTIVE = TRUE ");
		// Get select BudgetTransaction SQL.
		selectBudgetTransactionSQL = new StringBuffer(selectBudgetTransactionsSQL);
		selectBudgetTransactionSQL.append("WHERE B.BUDGETTRANSACTIONID = ^ ");
		// Get select BudgetTransaction for LocationJobProfile SQL.
		selectBudgetTransactionsForLocationJobProfileSQL = new StringBuffer(selectBudgetTransactionsSQL);
		selectBudgetTransactionsForLocationJobProfileSQL.append("WHERE B.LOCATIONJOBPROFILEID = ^ ");
		// ORDER BY 
		selectBudgetTransactionUsersForClientSQL.append("ORDER BY B.CREATIONTIMESTAMP ");
		selectActiveBudgetTransactionUsersForClientSQL.append("ORDER BY B.CREATIONTIMESTAMP ");
		selectBudgetTransactionsForLocationJobProfileSQL.append("ORDER BY B.CREATIONTIMESTAMP ");

	}

	public int insertBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertBudgetTransactionSQL.toString());
		// Replace the parameters with supplied values.
		budgetTransaction.setBudgetTransactionId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "budgetTransaction"));
		Utilities.replace(sql, budgetTransaction.getBudgetTransactionId());
		Utilities.replace(sql, budgetTransaction.getLocationJobProfileId());
		Utilities.replace(sql, budgetTransaction.getValue());
		Utilities.replace(sql, budgetTransaction.getVatValue());
		Utilities.replace(sql, budgetTransaction.getExpenseValue());
		Utilities.replace(sql, budgetTransaction.getNonPayValue());
		Utilities.replace(sql, budgetTransaction.getBalance());
		Utilities.replace(sql, budgetTransaction.getVatBalance());
		Utilities.replace(sql, budgetTransaction.getExpenseBalance());
		Utilities.replace(sql, budgetTransaction.getNonPayBalance());
		Utilities.replaceAndQuoteNullable(sql, budgetTransaction.getComment());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

//	public int updateBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId) {
//		// Create a new local StringBuffer containing the parameterised SQL.
//		StringBuffer sql = new StringBuffer(updateBudgetTransactionSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replace(sql, budgetTransaction.getLocationJobProfileId());
//		Utilities.replace(sql, budgetTransaction.getValue());
//		Utilities.replace(sql, budgetTransaction.getVatValue());
//		Utilities.replace(sql, budgetTransaction.getExpenseValue());
//		Utilities.replace(sql, budgetTransaction.getNonPayValue());
//		Utilities.replace(sql, budgetTransaction.getValue());
//		Utilities.replace(sql, budgetTransaction.getVatValue());
//		Utilities.replace(sql, budgetTransaction.getExpenseValue());
//		Utilities.replace(sql, budgetTransaction.getNonPayValue());
//		Utilities.replaceAndQuoteNullable(sql, budgetTransaction.getComment());
//		Utilities.replace(sql, auditorId);
//		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
//				.getTime()).toString());
//		Utilities.replace(sql, budgetTransaction.getBudgetTransactionId());
//		Utilities.replace(sql, budgetTransaction.getNoOfChanges());
//		return UpdateHandler.getInstance().update(getJdbcTemplate(),
//				sql.toString());
//	}

	public int deleteBudgetTransaction(Integer budgetTransactionId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteBudgetTransactionSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, budgetTransactionId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public BudgetTransaction getBudgetTransaction(Integer budgetTransactionId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectBudgetTransactionSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, budgetTransactionId);
		return (BudgetTransaction) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BudgetTransaction.class.getName());
	}

	public List<BudgetTransactionUser> getBudgetTransactionUsersForClient(Integer clientId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBudgetTransactionUsersForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBudgetTransactionUsersForClientSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BudgetTransactionUser.class.getName());

	}

	public List<BudgetTransaction> getBudgetTransactionsForLocationJobProfile(Integer locationJobProfileId) {

		StringBuffer sql = new StringBuffer(selectBudgetTransactionsForLocationJobProfileSQL.toString()); 
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationJobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BudgetTransaction.class.getName());

	}

}
