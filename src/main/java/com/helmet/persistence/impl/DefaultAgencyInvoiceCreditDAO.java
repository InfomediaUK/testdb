package com.helmet.persistence.impl;

import java.sql.Timestamp;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AgencyInvoiceCredit;
import com.helmet.persistence.AgencyInvoiceCreditDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAgencyInvoiceCreditDAO extends JdbcDaoSupport implements AgencyInvoiceCreditDAO {

	private static StringBuffer insertAgencyInvoiceCreditSQL;

	private static StringBuffer selectAgencyInvoiceCreditSQL;

	public static void init() {
		// Get insert AgencyInvoiceCredit SQL.
		insertAgencyInvoiceCreditSQL = new StringBuffer();
		insertAgencyInvoiceCreditSQL.append("INSERT INTO AGENCYINVOICECREDIT ");
		insertAgencyInvoiceCreditSQL.append("(  ");
		insertAgencyInvoiceCreditSQL.append("  AGENCYINVOICECREDITID, ");
		insertAgencyInvoiceCreditSQL.append("  AGENCYINVOICEID, ");
		insertAgencyInvoiceCreditSQL.append("  REASONTEXT, ");
		insertAgencyInvoiceCreditSQL.append("  CREATIONTIMESTAMP, ");
		insertAgencyInvoiceCreditSQL.append("  AUDITORID, ");
		insertAgencyInvoiceCreditSQL.append("  AUDITTIMESTAMP ");
		insertAgencyInvoiceCreditSQL.append(")  ");
		insertAgencyInvoiceCreditSQL.append("VALUES  ");
		insertAgencyInvoiceCreditSQL.append("(  ");
		insertAgencyInvoiceCreditSQL.append("  ^, ");
		insertAgencyInvoiceCreditSQL.append("  ^, ");
		insertAgencyInvoiceCreditSQL.append("  ^, ");
		insertAgencyInvoiceCreditSQL.append("  ^, ");
		insertAgencyInvoiceCreditSQL.append("  ^, ");
		insertAgencyInvoiceCreditSQL.append("  ^ ");
		insertAgencyInvoiceCreditSQL.append(")  ");

		// Get select AgencyInvoiceCredit SQL.
		selectAgencyInvoiceCreditSQL = new StringBuffer();
		selectAgencyInvoiceCreditSQL.append("SELECT AGENCYINVOICECREDITID, ");
		selectAgencyInvoiceCreditSQL.append("       AGENCYINVOICEID, ");
		selectAgencyInvoiceCreditSQL.append("       REASONTEXT, ");
		selectAgencyInvoiceCreditSQL.append("       STATUS, ");
		selectAgencyInvoiceCreditSQL.append("       CREATIONTIMESTAMP, ");
		selectAgencyInvoiceCreditSQL.append("       AUDITORID, ");
		selectAgencyInvoiceCreditSQL.append("       AUDITTIMESTAMP, ");
		selectAgencyInvoiceCreditSQL.append("       ACTIVE, ");
		selectAgencyInvoiceCreditSQL.append("       NOOFCHANGES ");
		selectAgencyInvoiceCreditSQL.append("FROM AGENCYINVOICECREDIT ");
		selectAgencyInvoiceCreditSQL.append("WHERE AGENCYINVOICECREDITID = ^ ");
	
	}

	public int insertAgencyInvoiceCredit(AgencyInvoiceCredit agencyInvoiceCredit, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAgencyInvoiceCreditSQL.toString());
		// Replace the parameters with supplied values.
		//
		// shares sequence with agencyInvoice
		agencyInvoiceCredit.setAgencyInvoiceCreditId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "agencyInvoice"));
		String now = new Timestamp(new java.util.Date().getTime()).toString(); 
		Utilities.replace(sql, agencyInvoiceCredit.getAgencyInvoiceCreditId());
		Utilities.replace(sql, agencyInvoiceCredit.getAgencyInvoiceId());
		Utilities.replaceAndQuote(sql, agencyInvoiceCredit.getReasonText());
		Utilities.replaceAndQuote(sql, now);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, now);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public AgencyInvoiceCredit getAgencyInvoiceCredit(Integer agencyInvoiceCreditId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgencyInvoiceCreditSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyInvoiceCreditId);
		return (AgencyInvoiceCredit) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgencyInvoiceCredit.class.getName());
	}

	
}
