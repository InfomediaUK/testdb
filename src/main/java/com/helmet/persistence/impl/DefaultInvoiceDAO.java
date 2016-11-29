package com.helmet.persistence.impl;

import java.sql.Timestamp;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Invoice;
import com.helmet.bean.InvoiceAgency;
import com.helmet.bean.InvoiceEntity;
import com.helmet.persistence.InvoiceDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultInvoiceDAO extends JdbcDaoSupport implements InvoiceDAO {

	private static StringBuffer insertInvoiceSQL;

	private static StringBuffer updateInvoiceValuesSQL;

	private static StringBuffer selectInvoicesSQL;

	private static StringBuffer selectInvoiceSQL;

	public static void init() {
		// Get insert Invoice SQL.
		insertInvoiceSQL = new StringBuffer();
		insertInvoiceSQL.append("INSERT INTO INVOICE ");
		insertInvoiceSQL.append("(  ");
		insertInvoiceSQL.append("  INVOICEID, ");
		insertInvoiceSQL.append("  CLIENTID, ");
		insertInvoiceSQL.append("  CHARGERATEVALUE, ");
		insertInvoiceSQL.append("  PAYRATEVALUE, ");
		insertInvoiceSQL.append("  WTDVALUE, ");
		insertInvoiceSQL.append("  NIVALUE, ");
		insertInvoiceSQL.append("  COMMISSIONVALUE, ");
		insertInvoiceSQL.append("  EXPENSEVALUE, ");
		insertInvoiceSQL.append("  VATVALUE, ");
		insertInvoiceSQL.append("  NOOFHOURS, ");
		insertInvoiceSQL.append("  FEEVALUE, ");
		insertInvoiceSQL.append("  CREATIONTIMESTAMP, ");
		insertInvoiceSQL.append("  AUDITORID, ");
		insertInvoiceSQL.append("  AUDITTIMESTAMP ");
		insertInvoiceSQL.append(")  ");
		insertInvoiceSQL.append("VALUES  ");
		insertInvoiceSQL.append("(  ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^, ");
		insertInvoiceSQL.append("  ^ ");
		insertInvoiceSQL.append(")  ");
		// Get update InvoiceValues SQL.
		updateInvoiceValuesSQL = new StringBuffer();
		updateInvoiceValuesSQL.append("UPDATE INVOICE ");
		updateInvoiceValuesSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateInvoiceValuesSQL.append("     CHARGERATEVALUE = ^, ");
		updateInvoiceValuesSQL.append("     PAYRATEVALUE = ^, ");
		updateInvoiceValuesSQL.append("     WTDVALUE = ^, ");
		updateInvoiceValuesSQL.append("     NIVALUE = ^, ");
		updateInvoiceValuesSQL.append("     COMMISSIONVALUE = ^, ");
		updateInvoiceValuesSQL.append("     EXPENSEVALUE = ^, ");
		updateInvoiceValuesSQL.append("     VATVALUE = ^, ");
		updateInvoiceValuesSQL.append("     NOOFHOURS = ^, ");
		updateInvoiceValuesSQL.append("     FEEVALUE = ^, ");
		updateInvoiceValuesSQL.append("     AUDITORID = ^, ");
		updateInvoiceValuesSQL.append("     AUDITTIMESTAMP = ^ ");
		updateInvoiceValuesSQL.append("WHERE INVOICEID = ^ ");
		updateInvoiceValuesSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Invoices SQL.
		selectInvoicesSQL = new StringBuffer();
		selectInvoicesSQL.append("SELECT INVOICEID, ");
		selectInvoicesSQL.append("       CLIENTID, ");
		selectInvoicesSQL.append("       CHARGERATEVALUE, ");
		selectInvoicesSQL.append("       PAYRATEVALUE, ");
		selectInvoicesSQL.append("       WTDVALUE, ");
		selectInvoicesSQL.append("       NIVALUE, ");
		selectInvoicesSQL.append("       COMMISSIONVALUE, ");
		selectInvoicesSQL.append("       EXPENSEVALUE, ");
		selectInvoicesSQL.append("       VATVALUE, ");
		selectInvoicesSQL.append("       NOOFHOURS, ");
		selectInvoicesSQL.append("       FEEVALUE, ");
		selectInvoicesSQL.append("       CREATIONTIMESTAMP, ");
		selectInvoicesSQL.append("       AUDITORID, ");
		selectInvoicesSQL.append("       AUDITTIMESTAMP, ");
		selectInvoicesSQL.append("       ACTIVE, ");
		selectInvoicesSQL.append("       NOOFCHANGES ");
		selectInvoicesSQL.append("FROM INVOICE ");
		// Get select Invoice SQL.
		selectInvoiceSQL = new StringBuffer(selectInvoicesSQL);
		selectInvoiceSQL.append("WHERE CLIENTID = ^ ");
		selectInvoiceSQL.append("AND INVOICEID = ^ ");
	}

	public int insertInvoice(Invoice invoice, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertInvoiceSQL.toString());
		// Replace the parameters with supplied values.
		invoice.setInvoiceId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "invoice"));
		Utilities.replace(sql, invoice.getInvoiceId());
		Utilities.replace(sql, invoice.getClientId());
		Utilities.replace(sql, invoice.getChargeRateValue());
		Utilities.replace(sql, invoice.getPayRateValue());
		Utilities.replace(sql, invoice.getWtdValue());
		Utilities.replace(sql, invoice.getNiValue());
		Utilities.replace(sql, invoice.getCommissionValue());
		Utilities.replace(sql, invoice.getExpenseValue());
		Utilities.replace(sql, invoice.getVatValue());
		Utilities.replace(sql, invoice.getNoOfHours());
		Utilities.replace(sql, invoice.getFeeValue());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateInvoiceValues(Invoice invoice, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateInvoiceValuesSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, invoice.getChargeRateValue());
		Utilities.replace(sql, invoice.getPayRateValue());
		Utilities.replace(sql, invoice.getWtdValue());
		Utilities.replace(sql, invoice.getNiValue());
		Utilities.replace(sql, invoice.getCommissionValue());
		Utilities.replace(sql, invoice.getExpenseValue());
		Utilities.replace(sql, invoice.getVatValue());
		Utilities.replace(sql, invoice.getNoOfHours());
		Utilities.replace(sql, invoice.getFeeValue());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, invoice.getInvoiceId());
		Utilities.replace(sql, invoice.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public Invoice getInvoice(Integer clientId, Integer invoiceId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectInvoiceSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, invoiceId);
		return (Invoice) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Invoice.class.getName());
	}

	public InvoiceEntity getInvoiceEntity(Integer clientId, Integer invoiceId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectInvoiceSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, invoiceId);
		return (InvoiceEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), InvoiceEntity.class.getName());
	}

}
