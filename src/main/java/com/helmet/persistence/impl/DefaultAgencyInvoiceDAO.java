package com.helmet.persistence.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AgencyInvoice;
import com.helmet.bean.AgencyInvoiceUser;
import com.helmet.bean.AgencyInvoiceUserEntity;
import com.helmet.persistence.AgencyInvoiceDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAgencyInvoiceDAO extends JdbcDaoSupport implements AgencyInvoiceDAO {

	private static StringBuffer insertAgencyInvoiceSQL;

	private static StringBuffer updateAgencyInvoiceSQL;

	private static StringBuffer updateAgencyInvoiceValuesSQL;

	private static StringBuffer updateAgencyInvoiceAuthorizedSQL;
	
	private static StringBuffer updateAgencyInvoicePaidSQL;
	
	private static StringBuffer updateAgencyInvoiceCreditedSQL;
	
	private static StringBuffer updateAgencyInvoiceTimesheetSQL;

	private static StringBuffer selectAgencyInvoiceUsersSQL;

	private static StringBuffer selectAgencyInvoiceUsersForManagerSQL;

	private static StringBuffer selectAgencyInvoiceUsersForManagerAndStatusSQL;

	private static StringBuffer selectAgencyInvoiceUsersForAgencySQL;

	private static StringBuffer selectAgencyInvoiceUsersForAgencyAndStatusSQL;

	private static StringBuffer selectAgencyInvoiceUsersForManagerAndAgencyInvoicesSQL;

	private static StringBuffer selectAgencyInvoiceUserSQL;
	
	private static StringBuffer selectAgencyInvoiceUsersForAgencySearchSQL;

	private static StringBuffer selectAgencyInvoiceUsersForAgencySearchWithDateRangeSQL;

	public static void init() {
		// Get insert AgencyInvoice SQL.
		insertAgencyInvoiceSQL = new StringBuffer();
		insertAgencyInvoiceSQL.append("INSERT INTO AGENCYINVOICE ");
		insertAgencyInvoiceSQL.append("(  ");
		insertAgencyInvoiceSQL.append("  AGENCYINVOICEID, ");
		insertAgencyInvoiceSQL.append("  AGENCYID, ");
		insertAgencyInvoiceSQL.append("  CLIENTID, ");
		insertAgencyInvoiceSQL.append("  CHARGERATEVALUE, ");
		insertAgencyInvoiceSQL.append("  PAYRATEVALUE, ");
		insertAgencyInvoiceSQL.append("  WAGERATEVALUE, ");
		insertAgencyInvoiceSQL.append("  WTDVALUE, ");
		insertAgencyInvoiceSQL.append("  NIVALUE, ");
		insertAgencyInvoiceSQL.append("  COMMISSIONVALUE, ");
		insertAgencyInvoiceSQL.append("  EXPENSEVALUE, ");
		insertAgencyInvoiceSQL.append("  VATVALUE, ");
		insertAgencyInvoiceSQL.append("  NOOFHOURS, ");
		insertAgencyInvoiceSQL.append("  FEEVALUE, ");
		insertAgencyInvoiceSQL.append("  SUBMITTEDBYCONSULTANTID, ");
		insertAgencyInvoiceSQL.append("  SUBMITTEDTIMESTAMP, ");
		insertAgencyInvoiceSQL.append("  CREATIONTIMESTAMP, ");
		insertAgencyInvoiceSQL.append("  AUDITORID, ");
		insertAgencyInvoiceSQL.append("  AUDITTIMESTAMP ");
		insertAgencyInvoiceSQL.append(")  ");
		insertAgencyInvoiceSQL.append("VALUES  ");
		insertAgencyInvoiceSQL.append("(  ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^, ");
		insertAgencyInvoiceSQL.append("  ^ ");
		insertAgencyInvoiceSQL.append(")  ");
		// Get update AgencyInvoice SQL.
		updateAgencyInvoiceSQL = new StringBuffer();
		updateAgencyInvoiceSQL.append("UPDATE AGENCYINVOICE ");
		updateAgencyInvoiceSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAgencyInvoiceSQL.append("     REFERENCE = ^, ");
		updateAgencyInvoiceSQL.append("     DISCOUNTTEXT = ^, ");
		updateAgencyInvoiceSQL.append("     DISCOUNTVALUE = ^, ");
		updateAgencyInvoiceSQL.append("     DISCOUNTVATRATE = ^, ");
		updateAgencyInvoiceSQL.append("     DISCOUNTVATVALUE = ^, ");
		updateAgencyInvoiceSQL.append("     VATVALUE = VATVALUE + DISCOUNTVATVALUE - ^, ");
		updateAgencyInvoiceSQL.append("     AUDITORID = ^, ");
		updateAgencyInvoiceSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAgencyInvoiceSQL.append("WHERE AGENCYINVOICEID = ^ ");
		updateAgencyInvoiceSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update AgencyInvoiceValues SQL.
		updateAgencyInvoiceValuesSQL = new StringBuffer();
		updateAgencyInvoiceValuesSQL.append("UPDATE AGENCYINVOICE ");
		updateAgencyInvoiceValuesSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAgencyInvoiceValuesSQL.append("     CHARGERATEVALUE = ^, ");
		updateAgencyInvoiceValuesSQL.append("     PAYRATEVALUE = ^, ");
		updateAgencyInvoiceValuesSQL.append("     WAGERATEVALUE = ^, ");
		updateAgencyInvoiceValuesSQL.append("     WTDVALUE = ^, ");
		updateAgencyInvoiceValuesSQL.append("     NIVALUE = ^, ");
		updateAgencyInvoiceValuesSQL.append("     COMMISSIONVALUE = ^, ");
		updateAgencyInvoiceValuesSQL.append("     EXPENSEVALUE = ^, ");
		updateAgencyInvoiceValuesSQL.append("     VATVALUE = ^, ");
		updateAgencyInvoiceValuesSQL.append("     NOOFHOURS = ^, ");
		updateAgencyInvoiceValuesSQL.append("     FEEVALUE = ^, ");
		updateAgencyInvoiceValuesSQL.append("     AUDITORID = ^, ");
		updateAgencyInvoiceValuesSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAgencyInvoiceValuesSQL.append("WHERE AGENCYINVOICEID = ^ ");
		updateAgencyInvoiceValuesSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update AgencyInvoice authorized SQL.
		updateAgencyInvoiceAuthorizedSQL = new StringBuffer();
		updateAgencyInvoiceAuthorizedSQL.append("UPDATE AGENCYINVOICE ");
		updateAgencyInvoiceAuthorizedSQL.append("SET STATUS = " + AgencyInvoice.AGENCYINVOICE_STATUS_AUTHORIZED + ", ");
		updateAgencyInvoiceAuthorizedSQL.append("    AUTHORIZEDBYMANAGERID = ^, ");
		updateAgencyInvoiceAuthorizedSQL.append("    AUTHORIZEDTIMESTAMP = ^, ");
		updateAgencyInvoiceAuthorizedSQL.append("    AUDITORID = ^, ");
		updateAgencyInvoiceAuthorizedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateAgencyInvoiceAuthorizedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateAgencyInvoiceAuthorizedSQL.append("WHERE AGENCYINVOICEID = ^ ");
		updateAgencyInvoiceAuthorizedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update AgencyInvoice paid SQL.
		updateAgencyInvoicePaidSQL = new StringBuffer();
		updateAgencyInvoicePaidSQL.append("UPDATE AGENCYINVOICE ");
		updateAgencyInvoicePaidSQL.append("SET STATUS = " + AgencyInvoice.AGENCYINVOICE_STATUS_PAID + ", ");
		updateAgencyInvoicePaidSQL.append("    PAIDBYMANAGERID = ^, ");
		updateAgencyInvoicePaidSQL.append("    PAIDTIMESTAMP = ^, ");
		updateAgencyInvoicePaidSQL.append("    AUDITORID = ^, ");
		updateAgencyInvoicePaidSQL.append("    AUDITTIMESTAMP = ^, ");
		updateAgencyInvoicePaidSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateAgencyInvoicePaidSQL.append("WHERE AGENCYINVOICEID = ^ ");
		updateAgencyInvoicePaidSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update AgencyInvoice credited SQL.
		updateAgencyInvoiceCreditedSQL = new StringBuffer();
		updateAgencyInvoiceCreditedSQL.append("UPDATE AGENCYINVOICE ");
		updateAgencyInvoiceCreditedSQL.append("SET STATUS = " + AgencyInvoice.AGENCYINVOICE_STATUS_CREDITED + ", ");
		updateAgencyInvoiceCreditedSQL.append("    AGENCYINVOICECREDITID = ^, ");
		updateAgencyInvoiceCreditedSQL.append("    CREDITEDBYID = ^, ");
		updateAgencyInvoiceCreditedSQL.append("    CREDITEDTIMESTAMP = ^, ");
		updateAgencyInvoiceCreditedSQL.append("    AUDITORID = ^, ");
		updateAgencyInvoiceCreditedSQL.append("    AUDITTIMESTAMP = ^, ");
		updateAgencyInvoiceCreditedSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateAgencyInvoiceCreditedSQL.append("WHERE AGENCYINVOICEID = ^ ");
		updateAgencyInvoiceCreditedSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update AgencyInvoice timesheet SQL.
		updateAgencyInvoiceTimesheetSQL = new StringBuffer();
		updateAgencyInvoiceTimesheetSQL.append("UPDATE AGENCYINVOICE ");
		updateAgencyInvoiceTimesheetSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAgencyInvoiceTimesheetSQL.append("     TIMESHEETFILENAME = ^, ");
		updateAgencyInvoiceTimesheetSQL.append("     AUDITORID = ^, ");
		updateAgencyInvoiceTimesheetSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAgencyInvoiceTimesheetSQL.append("WHERE AGENCYINVOICEID = ^ ");
		updateAgencyInvoiceTimesheetSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AgencyInvoiceUsers SQL.
		selectAgencyInvoiceUsersSQL = new StringBuffer();
		selectAgencyInvoiceUsersSQL.append("SELECT AI.AGENCYINVOICEID, ");
		selectAgencyInvoiceUsersSQL.append("       AI.AGENCYID, ");
		selectAgencyInvoiceUsersSQL.append("       AI.CLIENTID, ");
		selectAgencyInvoiceUsersSQL.append("       AI.CHARGERATEVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.PAYRATEVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.WAGERATEVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.WTDVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.NIVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.COMMISSIONVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.EXPENSEVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.VATVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.NOOFHOURS, ");
		selectAgencyInvoiceUsersSQL.append("       AI.FEEVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.PAYMENTTERMS, ");
		selectAgencyInvoiceUsersSQL.append("       AI.REFERENCE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.DISCOUNTTEXT, ");
		selectAgencyInvoiceUsersSQL.append("       AI.DISCOUNTVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.DISCOUNTVATRATE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.DISCOUNTVATVALUE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.STATUS, ");
		selectAgencyInvoiceUsersSQL.append("       AI.SUBMITTEDBYCONSULTANTID, ");
		selectAgencyInvoiceUsersSQL.append("       AI.SUBMITTEDTIMESTAMP, ");
		selectAgencyInvoiceUsersSQL.append("       AI.AUTHORIZEDBYMANAGERID, ");
		selectAgencyInvoiceUsersSQL.append("       AI.AUTHORIZEDTIMESTAMP, ");
		selectAgencyInvoiceUsersSQL.append("       AI.PAIDBYMANAGERID, ");
		selectAgencyInvoiceUsersSQL.append("       AI.PAIDTIMESTAMP, ");
		selectAgencyInvoiceUsersSQL.append("       AI.CREDITEDBYID, ");
		selectAgencyInvoiceUsersSQL.append("       AI.CREDITEDTIMESTAMP, ");
		selectAgencyInvoiceUsersSQL.append("       AI.TIMESHEETFILENAME, ");
		selectAgencyInvoiceUsersSQL.append("       AI.AGENCYINVOICECREDITID, ");
		selectAgencyInvoiceUsersSQL.append("       AI.CREATIONTIMESTAMP, ");
		selectAgencyInvoiceUsersSQL.append("       AI.AUDITORID, ");
		selectAgencyInvoiceUsersSQL.append("       AI.AUDITTIMESTAMP, ");
		selectAgencyInvoiceUsersSQL.append("       AI.ACTIVE, ");
		selectAgencyInvoiceUsersSQL.append("       AI.NOOFCHANGES, ");
		selectAgencyInvoiceUsersSQL.append("       C.CODE AS CLIENTCODE, ");
		selectAgencyInvoiceUsersSQL.append("       C.NAME AS CLIENTNAME, ");
		selectAgencyInvoiceUsersSQL.append("       A.CODE AS AGENCYCODE, ");
		selectAgencyInvoiceUsersSQL.append("       A.NAME AS AGENCYNAME, ");

		selectAgencyInvoiceUsersSQL.append("       X1.LOGIN AS SUBMITTEDBYLOGIN, ");
		selectAgencyInvoiceUsersSQL.append("       X1.FIRSTNAME AS SUBMITTEDBYFIRSTNAME, ");
		selectAgencyInvoiceUsersSQL.append("       X1.LASTNAME AS SUBMITTEDEDBYLASTNAME, ");
		selectAgencyInvoiceUsersSQL.append("       X2.LOGIN AS AUTHORIZEDBYLOGIN, ");
		selectAgencyInvoiceUsersSQL.append("       X2.FIRSTNAME AS AUTHORIZEDBYFIRSTNAME, ");
		selectAgencyInvoiceUsersSQL.append("       X2.LASTNAME AS AUTHORIZEDBYLASTNAME, ");
		selectAgencyInvoiceUsersSQL.append("       X3.LOGIN AS PAIDBYLOGIN, ");
		selectAgencyInvoiceUsersSQL.append("       X3.FIRSTNAME AS PAIDBYFIRSTNAME, ");
		selectAgencyInvoiceUsersSQL.append("       X3.LASTNAME AS PAIDBYLASTNAME, ");
		selectAgencyInvoiceUsersSQL.append("       X4.LOGIN AS CREDITEDBYLOGIN, ");
		selectAgencyInvoiceUsersSQL.append("       X4.FIRSTNAME AS CREDITEDBYFIRSTNAME, ");
		selectAgencyInvoiceUsersSQL.append("       X4.LASTNAME AS CREDITEDBYLASTNAME ");
		
		selectAgencyInvoiceUsersSQL.append("FROM AGENCYINVOICE AI ");
		
		selectAgencyInvoiceUsersSQL.append(" LEFT OUTER JOIN CONSULTANT X1 ON X1.CONSULTANTID = AI.SUBMITTEDBYCONSULTANTID AND X1.ACTIVE = TRUE ");
		selectAgencyInvoiceUsersSQL.append(" LEFT OUTER JOIN MANAGER X2 ON X2.MANAGERID = AI.AUTHORIZEDBYMANAGERID AND X2.ACTIVE = TRUE ");
		selectAgencyInvoiceUsersSQL.append(" LEFT OUTER JOIN MANAGER X3 ON X3.MANAGERID = AI.PAIDBYMANAGERID AND X3.ACTIVE = TRUE ");
		selectAgencyInvoiceUsersSQL.append(" LEFT OUTER JOIN AUDITORVIEW X4 ON X4.AUDITORID = AI.CREDITEDBYID ");
		selectAgencyInvoiceUsersSQL.append(", ");
		
		selectAgencyInvoiceUsersSQL.append("     CLIENT C, ");
		selectAgencyInvoiceUsersSQL.append("     AGENCY A ");
		selectAgencyInvoiceUsersSQL.append("WHERE AI.ACTIVE = TRUE ");
		selectAgencyInvoiceUsersSQL.append("AND C.CLIENTID = AI.CLIENTID ");
		selectAgencyInvoiceUsersSQL.append("AND C.ACTIVE = TRUE ");
		selectAgencyInvoiceUsersSQL.append("AND A.AGENCYID = AI.AGENCYID ");
		selectAgencyInvoiceUsersSQL.append("AND A.ACTIVE = TRUE ");
		// Get select AgencyInvoiceUser SQL.
		selectAgencyInvoiceUserSQL = new StringBuffer(selectAgencyInvoiceUsersSQL);
		selectAgencyInvoiceUserSQL.append("AND AI.AGENCYINVOICEID = ^ ");
		// Get select AgencyInvoiceUsers for Manager SQL.
		selectAgencyInvoiceUsersForManagerSQL = new StringBuffer(selectAgencyInvoiceUsersSQL);
		selectAgencyInvoiceUsersForManagerSQL.append("AND AI.CLIENTID = ^ ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND EXISTS ");
		selectAgencyInvoiceUsersForManagerSQL.append("( ");
		selectAgencyInvoiceUsersForManagerSQL.append("SELECT NULL ");
		selectAgencyInvoiceUsersForManagerSQL.append("FROM LOCATIONMANAGERJOBPROFILE LMJP, ");
		selectAgencyInvoiceUsersForManagerSQL.append("     LOCATIONJOBPROFILE LJP, ");
		selectAgencyInvoiceUsersForManagerSQL.append("     BOOKINGDATE BD, ");
		selectAgencyInvoiceUsersForManagerSQL.append("     BOOKING B ");
		selectAgencyInvoiceUsersForManagerSQL.append("WHERE LMJP.MANAGERID = ^ ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND B.LOCATIONID = LMJP.LOCATIONID ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND B.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND BD.AGENCYINVOICEID = AI.AGENCYINVOICEID ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND BD.ACTIVE = TRUE ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND B.BOOKINGID = BD.BOOKINGID ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND B.ACTIVE = TRUE ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND LJP.LOCATIONID = LMJP.LOCATIONID ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND LJP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectAgencyInvoiceUsersForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
		selectAgencyInvoiceUsersForManagerSQL.append(") ");		
		// Get select AgencyInvoiceUsers for Manager and Status SQL.
		selectAgencyInvoiceUsersForManagerAndStatusSQL = new StringBuffer(selectAgencyInvoiceUsersForManagerSQL);
		selectAgencyInvoiceUsersForManagerAndStatusSQL.append("AND ( ");
		selectAgencyInvoiceUsersForManagerAndStatusSQL.append(" ^ IS NULL "); //
		selectAgencyInvoiceUsersForManagerAndStatusSQL.append("OR ");
		selectAgencyInvoiceUsersForManagerAndStatusSQL.append(" AI.STATUS = ^ "); //
		selectAgencyInvoiceUsersForManagerAndStatusSQL.append(") ");
		// Get select AgencyInvoiceUsers for Manager and AgencyInvoice Ids SQL.
		selectAgencyInvoiceUsersForManagerAndAgencyInvoicesSQL = new StringBuffer(selectAgencyInvoiceUsersForManagerSQL);
		selectAgencyInvoiceUsersForManagerAndAgencyInvoicesSQL.append("AND AI.AGENCYINVOICEID IN (^) ");

		// Get select AgencyInvoiceUsers for Manager SQL.
		selectAgencyInvoiceUsersForAgencySQL = new StringBuffer(selectAgencyInvoiceUsersSQL);
		selectAgencyInvoiceUsersForAgencySQL.append("AND AI.AGENCYID = ^ ");
		// Get select AgencyInvoiceUsers for Agency and Status SQL.
		selectAgencyInvoiceUsersForAgencyAndStatusSQL = new StringBuffer(selectAgencyInvoiceUsersForAgencySQL);
		selectAgencyInvoiceUsersForAgencyAndStatusSQL.append("AND ( ^ IS NULL OR AI.STATUS = ^ ) ");

		selectAgencyInvoiceUsersForAgencySearchSQL = new StringBuffer(selectAgencyInvoiceUsersForAgencyAndStatusSQL);
		selectAgencyInvoiceUsersForAgencySearchSQL.append("AND ( ^ IS NULL OR AI.AGENCYINVOICEID = ^ ) ");
		selectAgencyInvoiceUsersForAgencySearchSQL.append("AND ( ^ IS NULL OR AI.CLIENTID = ^ ) ");
		
		//
		selectAgencyInvoiceUsersForAgencySearchWithDateRangeSQL = new StringBuffer(selectAgencyInvoiceUsersForAgencySearchSQL);
		selectAgencyInvoiceUsersForAgencySearchWithDateRangeSQL.append("AND DATE_TRUNC('DAY', AI.CREATIONTIMESTAMP) BETWEEN ^ AND ^ ");

		// Order By
		selectAgencyInvoiceUsersForManagerSQL.append("ORDER BY AI.AGENCYINVOICEID ");		
		selectAgencyInvoiceUsersForManagerAndStatusSQL.append("ORDER BY AI.AGENCYINVOICEID ");		
		selectAgencyInvoiceUsersForManagerAndAgencyInvoicesSQL.append("ORDER BY AI.AGENCYINVOICEID ");		
		selectAgencyInvoiceUsersForAgencySQL.append("ORDER BY AI.AGENCYINVOICEID ");		
		selectAgencyInvoiceUsersForAgencyAndStatusSQL.append("ORDER BY AI.AGENCYINVOICEID ");		
		selectAgencyInvoiceUsersForAgencySearchSQL.append("ORDER BY AI.AGENCYINVOICEID ");		
		selectAgencyInvoiceUsersForAgencySearchWithDateRangeSQL.append("ORDER BY AI.AGENCYINVOICEID ");		
		
	}

	public int insertAgencyInvoice(AgencyInvoice agencyInvoice, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAgencyInvoiceSQL.toString());
		// Replace the parameters with supplied values.
		agencyInvoice.setAgencyInvoiceId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "agencyInvoice"));
		String now = new Timestamp(new java.util.Date().getTime()).toString(); 
		Utilities.replace(sql, agencyInvoice.getAgencyInvoiceId());
		Utilities.replace(sql, agencyInvoice.getAgencyId());
		Utilities.replace(sql, agencyInvoice.getClientId());
		Utilities.replace(sql, agencyInvoice.getChargeRateValue());
		Utilities.replace(sql, agencyInvoice.getPayRateValue());
		Utilities.replace(sql, agencyInvoice.getWageRateValue());
		Utilities.replace(sql, agencyInvoice.getWtdValue());
		Utilities.replace(sql, agencyInvoice.getNiValue());
		Utilities.replace(sql, agencyInvoice.getCommissionValue());
		Utilities.replace(sql, agencyInvoice.getExpenseValue());
		Utilities.replace(sql, agencyInvoice.getVatValue());
		Utilities.replace(sql, agencyInvoice.getNoOfHours());
		Utilities.replace(sql, agencyInvoice.getFeeValue());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, now);
		Utilities.replaceAndQuote(sql, now);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, now);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateAgencyInvoice(AgencyInvoice agencyInvoice, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgencyInvoiceSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuoteNullable(sql, agencyInvoice.getReference());
		Utilities.replaceAndQuoteNullable(sql, agencyInvoice.getDiscountText());
		Utilities.replace(sql, agencyInvoice.getDiscountValue());
		Utilities.replace(sql, agencyInvoice.getDiscountVatRate());
		Utilities.replace(sql, agencyInvoice.getDiscountVatValue());
		Utilities.replace(sql, agencyInvoice.getDiscountVatValue());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, agencyInvoice.getAgencyInvoiceId());
		Utilities.replace(sql, agencyInvoice.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateAgencyInvoiceValues(AgencyInvoice agencyInvoice, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgencyInvoiceValuesSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyInvoice.getChargeRateValue());
		Utilities.replace(sql, agencyInvoice.getPayRateValue());
		Utilities.replace(sql, agencyInvoice.getWageRateValue());
		Utilities.replace(sql, agencyInvoice.getWtdValue());
		Utilities.replace(sql, agencyInvoice.getNiValue());
		Utilities.replace(sql, agencyInvoice.getCommissionValue());
		Utilities.replace(sql, agencyInvoice.getExpenseValue());
		Utilities.replace(sql, agencyInvoice.getVatValue());
		Utilities.replace(sql, agencyInvoice.getNoOfHours());
		Utilities.replace(sql, agencyInvoice.getFeeValue());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, agencyInvoice.getAgencyInvoiceId());
		Utilities.replace(sql, agencyInvoice.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

    public int updateAgencyInvoiceAuthorized(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId) {

    	// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgencyInvoiceAuthorizedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agencyInvoiceId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	
    }

    public int updateAgencyInvoicePaid(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId) {

    	// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgencyInvoicePaidSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agencyInvoiceId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	
    }

    public int updateAgencyInvoiceCredited(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId, Integer agencyInvoiceCreditId) {

    	// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgencyInvoiceCreditedSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyInvoiceCreditId);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agencyInvoiceId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	
    }

	public int updateAgencyInvoiceTimesheet(AgencyInvoice agencyInvoice, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgencyInvoiceTimesheetSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuoteNullable(sql, agencyInvoice.getTimesheetFilename());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, agencyInvoice.getAgencyInvoiceId());
		Utilities.replace(sql, agencyInvoice.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public AgencyInvoiceUser getAgencyInvoiceUser(Integer agencyInvoiceId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyInvoiceUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyInvoiceId);
		return (AgencyInvoiceUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyInvoiceUser.class.getName());
	}

	public AgencyInvoiceUserEntity getAgencyInvoiceUserEntity(Integer agencyInvoiceId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyInvoiceUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyInvoiceId);
		return (AgencyInvoiceUserEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyInvoiceUserEntity.class.getName());
	}

	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForManagerAndStatus(Integer clientId, Integer managerId, Integer status) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyInvoiceUsersForManagerAndStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, managerId);

		Utilities.replace(sql, status);
		Utilities.replace(sql, status);

		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyInvoiceUser.class.getName());

		
	}
	
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForManagerAndAgencyInvoices(Integer clientId, Integer managerId, String agencyInvoiceIdStrs) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyInvoiceUsersForManagerAndAgencyInvoicesSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, managerId);

		Utilities.replace(sql, agencyInvoiceIdStrs);

		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyInvoiceUser.class.getName());

	}
	
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForAgencyAndStatus(Integer agencyId, Integer status) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyInvoiceUsersForAgencyAndStatusSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);

		Utilities.replace(sql, status);
		Utilities.replace(sql, status);

		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyInvoiceUser.class.getName());

	}

	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForAgency(Integer agencyId, Integer agencyInvoiceId, 
			Integer clientId, Integer status, Date fromDate, Date toDate) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = null;
		if (fromDate == null) {
			sql = new StringBuffer(selectAgencyInvoiceUsersForAgencySearchSQL.toString());
		}
		else {
			sql = new StringBuffer(selectAgencyInvoiceUsersForAgencySearchWithDateRangeSQL.toString());
		}

		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);

		Utilities.replace(sql, status);
		Utilities.replace(sql, status);
		Utilities.replace(sql, agencyInvoiceId);
		Utilities.replace(sql, agencyInvoiceId);
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, clientId);

		if (fromDate != null) {
		    if (toDate == null) {
                // FORCE for last year
		    	Utilities.replace(sql, "CURRENT_DATE - INTERVAL '1 YEAR'");
				Utilities.replace(sql, "CURRENT_DATE");
		    }
		    else {
				Utilities.replaceAndQuote(sql, fromDate);
				Utilities.replaceAndQuote(sql, toDate);
		    }
		}

		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyInvoiceUser.class.getName());

	}
	
}
