package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.InvoiceAgency;
import com.helmet.bean.InvoiceAgencyUser;
import com.helmet.bean.InvoiceAgencyUserEntity;
import com.helmet.persistence.InvoiceAgencyDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultInvoiceAgencyDAO extends JdbcDaoSupport implements InvoiceAgencyDAO {

	private static StringBuffer insertInvoiceAgencySQL;

	private static StringBuffer updateInvoiceAgencySQL;

	private static StringBuffer updateInvoiceAgencyValuesSQL;

	private static StringBuffer selectInvoiceAgencyUsersForInvoiceSQL;

	private static StringBuffer selectInvoiceAgencyUserForInvoiceForAgencySQL;

	public static void init() {
		// Get insert InvoiceAgency SQL.
		insertInvoiceAgencySQL = new StringBuffer();
		insertInvoiceAgencySQL.append("INSERT INTO INVOICEAGENCY ");
		insertInvoiceAgencySQL.append("(  ");
		insertInvoiceAgencySQL.append("  INVOICEAGENCYID, ");
		insertInvoiceAgencySQL.append("  INVOICEID, ");
		insertInvoiceAgencySQL.append("  AGENCYID, ");
		insertInvoiceAgencySQL.append("  CHARGERATEVALUE, ");
		insertInvoiceAgencySQL.append("  PAYRATEVALUE, ");
		insertInvoiceAgencySQL.append("  WTDVALUE, ");
		insertInvoiceAgencySQL.append("  NIVALUE, ");
		insertInvoiceAgencySQL.append("  COMMISSIONVALUE, ");
		insertInvoiceAgencySQL.append("  EXPENSEVALUE, ");
		insertInvoiceAgencySQL.append("  VATVALUE, ");
		insertInvoiceAgencySQL.append("  NOOFHOURS, ");
		insertInvoiceAgencySQL.append("  FEEVALUE, ");
		insertInvoiceAgencySQL.append("  PAYMENTTERMS, ");
		insertInvoiceAgencySQL.append("  CREATIONTIMESTAMP, ");
		insertInvoiceAgencySQL.append("  AUDITORID, ");
		insertInvoiceAgencySQL.append("  AUDITTIMESTAMP ");
		insertInvoiceAgencySQL.append(")  ");
		insertInvoiceAgencySQL.append("VALUES  ");
		insertInvoiceAgencySQL.append("(  ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^, ");
		insertInvoiceAgencySQL.append("  ^ ");
		insertInvoiceAgencySQL.append(")  ");
		// Get update InvoiceAgency SQL.
		updateInvoiceAgencySQL = new StringBuffer();
		updateInvoiceAgencySQL.append("UPDATE INVOICEAGENCY ");
		updateInvoiceAgencySQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateInvoiceAgencySQL.append("     REFERENCE = ^, ");
		updateInvoiceAgencySQL.append("     AUDITORID = ^, ");
		updateInvoiceAgencySQL.append("     AUDITTIMESTAMP = ^ ");
		updateInvoiceAgencySQL.append("WHERE INVOICEAGENCYID = ^ ");
		updateInvoiceAgencySQL.append("AND   NOOFCHANGES = ^ ");
		// Get update InvoiceAgencyValues SQL.
		updateInvoiceAgencyValuesSQL = new StringBuffer();
		updateInvoiceAgencyValuesSQL.append("UPDATE INVOICEAGENCY ");
		updateInvoiceAgencyValuesSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateInvoiceAgencyValuesSQL.append("     CHARGERATEVALUE = ^, ");
		updateInvoiceAgencyValuesSQL.append("     PAYRATEVALUE = ^, ");
		updateInvoiceAgencyValuesSQL.append("     WTDVALUE = ^, ");
		updateInvoiceAgencyValuesSQL.append("     NIVALUE = ^, ");
		updateInvoiceAgencyValuesSQL.append("     COMMISSIONVALUE = ^, ");
		updateInvoiceAgencyValuesSQL.append("     EXPENSEVALUE = ^, ");
		updateInvoiceAgencyValuesSQL.append("     VATVALUE = ^, ");
		updateInvoiceAgencyValuesSQL.append("     NOOFHOURS = ^, ");
		updateInvoiceAgencyValuesSQL.append("     FEEVALUE = ^, ");
		updateInvoiceAgencyValuesSQL.append("     AUDITORID = ^, ");
		updateInvoiceAgencyValuesSQL.append("     AUDITTIMESTAMP = ^ ");
		updateInvoiceAgencyValuesSQL.append("WHERE INVOICEAGENCYID = ^ ");
		updateInvoiceAgencyValuesSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select InvoiceAgencyUsers for Invoice SQL.
		selectInvoiceAgencyUsersForInvoiceSQL = new StringBuffer();
		selectInvoiceAgencyUsersForInvoiceSQL.append("SELECT IA.INVOICEAGENCYID, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.INVOICEID, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.AGENCYID, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.CHARGERATEVALUE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.PAYRATEVALUE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.WTDVALUE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.NIVALUE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.COMMISSIONVALUE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.EXPENSEVALUE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.VATVALUE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.NOOFHOURS, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.FEEVALUE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.PAYMENTTERMS, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.REFERENCE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.CREATIONTIMESTAMP, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.AUDITORID, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.AUDITTIMESTAMP, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.ACTIVE, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       IA.NOOFCHANGES, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       I.CLIENTID, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("       A.CODE AS AGENCYCODE ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("FROM INVOICEAGENCY IA, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("     INVOICE I, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("     CLIENTAGENCY CA, ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("     AGENCY A ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("WHERE I.INVOICEID = ^ ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("AND   I.ACTIVE = TRUE ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("AND   IA.INVOICEID = I.INVOICEID ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("AND   IA.ACTIVE = TRUE ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("AND   CA.CLIENTID = I.CLIENTID ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("AND   CA.AGENCYID = IA.AGENCYID ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("AND   CA.ACTIVE = TRUE ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("AND   A.AGENCYID = CA.AGENCYID ");
		selectInvoiceAgencyUsersForInvoiceSQL.append("AND   A.ACTIVE = TRUE ");

		selectInvoiceAgencyUserForInvoiceForAgencySQL = new StringBuffer(selectInvoiceAgencyUsersForInvoiceSQL);
		selectInvoiceAgencyUserForInvoiceForAgencySQL.append("AND A.AGENCYID = ^ ");

		// Order by ...
		selectInvoiceAgencyUsersForInvoiceSQL.append("ORDER BY CA.DISPLAYORDER, A.NAME ");
	
	}

	public int insertInvoiceAgency(InvoiceAgency invoiceAgency, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertInvoiceAgencySQL.toString());
		// Replace the parameters with supplied values.
		invoiceAgency.setInvoiceAgencyId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "invoiceAgency"));
		Utilities.replace(sql, invoiceAgency.getInvoiceAgencyId());
		Utilities.replace(sql, invoiceAgency.getInvoiceId());
		Utilities.replace(sql, invoiceAgency.getAgencyId());
		Utilities.replace(sql, invoiceAgency.getChargeRateValue());
		Utilities.replace(sql, invoiceAgency.getPayRateValue());
		Utilities.replace(sql, invoiceAgency.getWtdValue());
		Utilities.replace(sql, invoiceAgency.getNiValue());
		Utilities.replace(sql, invoiceAgency.getCommissionValue());
		Utilities.replace(sql, invoiceAgency.getExpenseValue());
		Utilities.replace(sql, invoiceAgency.getVatValue());
		Utilities.replace(sql, invoiceAgency.getNoOfHours());
		Utilities.replace(sql, invoiceAgency.getFeeValue());
		Utilities.replace(sql, invoiceAgency.getPaymentTerms());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateInvoiceAgency(InvoiceAgency invoiceAgency, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateInvoiceAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuoteNullable(sql, invoiceAgency.getReference());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, invoiceAgency.getInvoiceAgencyId());
		Utilities.replace(sql, invoiceAgency.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateInvoiceAgencyValues(InvoiceAgency invoiceAgency, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateInvoiceAgencyValuesSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, invoiceAgency.getChargeRateValue());
		Utilities.replace(sql, invoiceAgency.getPayRateValue());
		Utilities.replace(sql, invoiceAgency.getWtdValue());
		Utilities.replace(sql, invoiceAgency.getNiValue());
		Utilities.replace(sql, invoiceAgency.getCommissionValue());
		Utilities.replace(sql, invoiceAgency.getExpenseValue());
		Utilities.replace(sql, invoiceAgency.getVatValue());
		Utilities.replace(sql, invoiceAgency.getNoOfHours());
		Utilities.replace(sql, invoiceAgency.getFeeValue());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, invoiceAgency.getInvoiceAgencyId());
		Utilities.replace(sql, invoiceAgency.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<InvoiceAgencyUser> getInvoiceAgencyUsersForInvoice(Integer invoiceId) {
		StringBuffer sql = new StringBuffer(selectInvoiceAgencyUsersForInvoiceSQL.toString());	
		// Replace the parameters with supplied values.
		Utilities.replace(sql, invoiceId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				InvoiceAgencyUser.class.getName());
	}

	public InvoiceAgencyUser getInvoiceAgencyUserForInvoiceForAgency(Integer invoiceId, Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectInvoiceAgencyUserForInvoiceForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, invoiceId);
		Utilities.replace(sql, agencyId);
		return (InvoiceAgencyUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), InvoiceAgencyUser.class.getName());
	}
	
	public InvoiceAgencyUserEntity getInvoiceAgencyUserEntityForInvoiceForAgency(Integer invoiceId, Integer agencyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectInvoiceAgencyUserForInvoiceForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, invoiceId);
		Utilities.replace(sql, agencyId);
		return (InvoiceAgencyUserEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), InvoiceAgencyUserEntity.class.getName());
	}
	
}
