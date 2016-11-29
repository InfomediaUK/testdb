package com.helmet.persistence.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.helmet.application.agy.AgyConstants;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.NhsBackingReportUser;
import com.helmet.bean.RecordCount;
import com.helmet.persistence.NhsBackingReportDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultNhsBackingReportDAO extends DefaultPagingDaoSupport implements NhsBackingReportDAO 
{
  private static StringBuffer insertNhsBackingReportSQL;

  private static StringBuffer updateNhsBackingReportSQL;

  private static StringBuffer updateNhsBackingReportTradeshiftDocumentIdSQL;

  private static StringBuffer updateNhsBackingReportSubcontractSQL;

  private static StringBuffer updateNhsBackingReportSubcontractDocumentationSentDateSQL;

  private static StringBuffer reactivateNhsBackingReportSQL;

	private static StringBuffer deleteNhsBackingReportSQL;

  private static StringBuffer selectNhsBackingReportSQL;

  private static StringBuffer selectNhsBackingReportUserSQL;

  private static StringBuffer selectNhsBackingReportForNameSQL;

  private static StringBuffer selectActiveNhsBackingReportForNameSQL;

  private static StringBuffer selectInactiveNhsBackingReportForNameSQL;

  private static StringBuffer selectNhsBackingReportsSQL;

  private static StringBuffer selectNhsBackingReportsCountSQL;

  private static StringBuffer selectActiveNhsBackingReportsCountSQL;

  private static StringBuffer selectActiveNhsBackingReportsPaidCountSQL;

  private static StringBuffer selectActiveNhsBackingReportsUnpaidCountSQL;

  private static StringBuffer selectNhsBackingReportUsersSQL;

  private static StringBuffer selectNhsBackingReportUsersInListSQL;

  private static StringBuffer selectNhsBackingReportUsersOffsetLimitSQL;

  private static StringBuffer selectActiveNhsBackingReportsSQL;

  private static StringBuffer selectActiveNhsBackingReportUsersSQL;

  private static StringBuffer selectActiveNhsBackingReportUsersPaidSQL;

  private static StringBuffer selectActiveNhsBackingReportUsersUnpaidSQL;

  private static StringBuffer selectActiveNhsBackingReportUsersOffsetLimitSQL;

  private static StringBuffer selectActiveNhsBackingReportUsersPaidOffsetLimitSQL;

  private static StringBuffer selectActiveNhsBackingReportUsersUnpaidOffsetLimitSQL;

  private static StringBuffer selectActiveNhsBackingReportsForDateRangeSQL;

  private static StringBuffer selectActiveNhsBackingReportUsersForDateRangeSQL;

	public static void init() 
  {
		// Get insert NhsBackingReport SQL.
		insertNhsBackingReportSQL = new StringBuffer();
		insertNhsBackingReportSQL.append("INSERT INTO NHSBACKINGREPORT ");
		insertNhsBackingReportSQL.append("(  ");
    insertNhsBackingReportSQL.append("  NHSBACKINGREPORTID, ");
    insertNhsBackingReportSQL.append("  AGENCYID, ");
    insertNhsBackingReportSQL.append("  CLIENTID, ");
    insertNhsBackingReportSQL.append("  NAME, ");
    insertNhsBackingReportSQL.append("  STARTDATE, ");
    insertNhsBackingReportSQL.append("  ENDDATE, ");
    insertNhsBackingReportSQL.append("  AGREEDVALUE, ");
    insertNhsBackingReportSQL.append("  PAIDVALUE, ");
    insertNhsBackingReportSQL.append("  SUBCONTRACTVALUE, ");
    insertNhsBackingReportSQL.append("  SUBCONTRACTAGENCYID, ");
    insertNhsBackingReportSQL.append("  COMMENT, ");
    insertNhsBackingReportSQL.append("  DOCUMENTATIONFILENAME, ");
    insertNhsBackingReportSQL.append("  REJECTEDDOCUMENTATIONFILENAME, ");
    insertNhsBackingReportSQL.append("  CREATIONTIMESTAMP, ");
    insertNhsBackingReportSQL.append("  AUDITORID, ");
    insertNhsBackingReportSQL.append("  AUDITTIMESTAMP ");
		insertNhsBackingReportSQL.append(")  ");
		insertNhsBackingReportSQL.append("VALUES  ");
		insertNhsBackingReportSQL.append("(  ");
		insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
    insertNhsBackingReportSQL.append("  ^, ");
		insertNhsBackingReportSQL.append("  ^ ");
		insertNhsBackingReportSQL.append(")  ");
		// Get update NhsBackingReport SQL.
		updateNhsBackingReportSQL = new StringBuffer();
		updateNhsBackingReportSQL.append("UPDATE NHSBACKINGREPORT ");
		updateNhsBackingReportSQL.append("SET NOOFCHANGES = NOOFCHANGES + 1, ");
    updateNhsBackingReportSQL.append("    NAME = ^, ");
    updateNhsBackingReportSQL.append("    CLIENTID = ^, ");
    updateNhsBackingReportSQL.append("    STARTDATE = ^, ");
    updateNhsBackingReportSQL.append("    ENDDATE = ^, ");
    updateNhsBackingReportSQL.append("    AGREEDVALUE = ^, ");
    updateNhsBackingReportSQL.append("    PAIDVALUE = ^, ");
    updateNhsBackingReportSQL.append("    SUBCONTRACTVALUE = ^, ");
//    updateNhsBackingReportSQL.append("    SUBCONTRACTPAIDDATE = ^, ");
    updateNhsBackingReportSQL.append("    SUBCONTRACTAGENCYID = ^, ");
    updateNhsBackingReportSQL.append("    COMPLETEDATE = ^, ");
    updateNhsBackingReportSQL.append("    COMMENT = ^, ");
    updateNhsBackingReportSQL.append("    DOCUMENTATIONFILENAME = ^, ");
    updateNhsBackingReportSQL.append("    REJECTEDDOCUMENTATIONFILENAME = ^, ");
    updateNhsBackingReportSQL.append("    AUDITORID = ^, ");
    updateNhsBackingReportSQL.append("    AUDITTIMESTAMP = ^ ");
		updateNhsBackingReportSQL.append("WHERE NHSBACKINGREPORTID = ^ ");
    updateNhsBackingReportSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update NhsBackingReport Tradeshift Document Id SQL.
    updateNhsBackingReportTradeshiftDocumentIdSQL = new StringBuffer();
    updateNhsBackingReportTradeshiftDocumentIdSQL.append("UPDATE NHSBACKINGREPORT ");
    updateNhsBackingReportTradeshiftDocumentIdSQL.append("SET NOOFCHANGES = NOOFCHANGES + 1, ");
    updateNhsBackingReportTradeshiftDocumentIdSQL.append("    TRADESHIFTDOCUMENTID = ^, ");
    updateNhsBackingReportTradeshiftDocumentIdSQL.append("    AUDITORID = ^, ");
    updateNhsBackingReportTradeshiftDocumentIdSQL.append("    AUDITTIMESTAMP = ^ ");
    updateNhsBackingReportTradeshiftDocumentIdSQL.append("WHERE NHSBACKINGREPORTID = ^ ");
    updateNhsBackingReportTradeshiftDocumentIdSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update NhsBackingReport Subcontract values SQL.
    updateNhsBackingReportSubcontractSQL = new StringBuffer();
    updateNhsBackingReportSubcontractSQL.append("UPDATE NHSBACKINGREPORT ");
    updateNhsBackingReportSubcontractSQL.append("SET NOOFCHANGES = NOOFCHANGES + 1, ");
    updateNhsBackingReportSubcontractSQL.append("    SUBCONTRACTVALUE = ^, ");
    updateNhsBackingReportSubcontractSQL.append("    SUBCONTRACTPAIDDATE = ^, ");
    updateNhsBackingReportSubcontractSQL.append("    AUDITORID = ^, ");
    updateNhsBackingReportSubcontractSQL.append("    AUDITTIMESTAMP = ^ ");
    updateNhsBackingReportSubcontractSQL.append("WHERE NHSBACKINGREPORTID = ^ ");
    updateNhsBackingReportSubcontractSQL.append("AND   NOOFCHANGES = ^ ");
    // Get update NhsBackingReport Subcontract Documentation Sent Date SQL.
    updateNhsBackingReportSubcontractDocumentationSentDateSQL = new StringBuffer();
    updateNhsBackingReportSubcontractDocumentationSentDateSQL.append("UPDATE NHSBACKINGREPORT ");
    updateNhsBackingReportSubcontractDocumentationSentDateSQL.append("SET NOOFCHANGES = NOOFCHANGES + 1, ");
    updateNhsBackingReportSubcontractDocumentationSentDateSQL.append("    SUBCONTRACTDOCUMENTATIONSENTDATE = ^, ");
    updateNhsBackingReportSubcontractDocumentationSentDateSQL.append("    AUDITORID = ^, ");
    updateNhsBackingReportSubcontractDocumentationSentDateSQL.append("    AUDITTIMESTAMP = ^ ");
    updateNhsBackingReportSubcontractDocumentationSentDateSQL.append("WHERE NHSBACKINGREPORTID = ^ ");
    updateNhsBackingReportSubcontractDocumentationSentDateSQL.append("AND   NOOFCHANGES = ^ ");
    // Get Reactivate NhsBackingReport SQL.
    reactivateNhsBackingReportSQL = new StringBuffer();
    reactivateNhsBackingReportSQL.append("UPDATE NHSBACKINGREPORT ");
    reactivateNhsBackingReportSQL.append("SET NOOFCHANGES = NOOFCHANGES + 1, ");
    reactivateNhsBackingReportSQL.append("    NAME = ^, ");
    reactivateNhsBackingReportSQL.append("    CLIENTID = ^, ");
    reactivateNhsBackingReportSQL.append("    STARTDATE = ^, ");
    reactivateNhsBackingReportSQL.append("    ENDDATE = ^, ");
    reactivateNhsBackingReportSQL.append("    AGREEDVALUE = ^, ");
    reactivateNhsBackingReportSQL.append("    PAIDVALUE = ^, ");
    reactivateNhsBackingReportSQL.append("    SUBCONTRACTVALUE = ^, ");
    reactivateNhsBackingReportSQL.append("    SUBCONTRACTPAIDDATE = ^, ");
    reactivateNhsBackingReportSQL.append("    SUBCONTRACTAGENCYID = ^, ");
    reactivateNhsBackingReportSQL.append("    COMPLETEDATE = ^, ");
    reactivateNhsBackingReportSQL.append("    COMMENT = ^, ");
    reactivateNhsBackingReportSQL.append("    DOCUMENTATIONFILENAME = ^, ");
    reactivateNhsBackingReportSQL.append("    REJECTEDDOCUMENTATIONFILENAME = ^, ");
    reactivateNhsBackingReportSQL.append("    ACTIVE = TRUE, ");
    reactivateNhsBackingReportSQL.append("    AUDITORID = ^, ");
    reactivateNhsBackingReportSQL.append("    AUDITTIMESTAMP = ^ ");
    reactivateNhsBackingReportSQL.append("WHERE NHSBACKINGREPORTID = ^ ");
    reactivateNhsBackingReportSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete NhsBackingReport SQL.
		deleteNhsBackingReportSQL = new StringBuffer();
		deleteNhsBackingReportSQL.append("UPDATE NHSBACKINGREPORT ");
		deleteNhsBackingReportSQL.append("SET ACTIVE = FALSE, ");
    deleteNhsBackingReportSQL.append("    AUDITORID = ^, ");
    deleteNhsBackingReportSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteNhsBackingReportSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteNhsBackingReportSQL.append("WHERE NHSBACKINGREPORTID = ^ ");
		deleteNhsBackingReportSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select NhsBackingReports SQL.
		selectNhsBackingReportsSQL = new StringBuffer();
		selectNhsBackingReportsSQL.append("SELECT NBR.NHSBACKINGREPORTID, ");
    selectNhsBackingReportsSQL.append("       NBR.AGENCYID, ");
    selectNhsBackingReportsSQL.append("       NBR.CLIENTID, ");
    selectNhsBackingReportsSQL.append("       NBR.NAME, ");
    selectNhsBackingReportsSQL.append("       NBR.STARTDATE, ");
    selectNhsBackingReportsSQL.append("       NBR.ENDDATE, ");
    selectNhsBackingReportsSQL.append("       NBR.AGREEDVALUE, ");
    selectNhsBackingReportsSQL.append("       NBR.PAIDVALUE, ");
    selectNhsBackingReportsSQL.append("       NBR.SUBCONTRACTVALUE, ");
    selectNhsBackingReportsSQL.append("       NBR.SUBCONTRACTPAIDDATE, ");
    selectNhsBackingReportsSQL.append("       NBR.SUBCONTRACTDOCUMENTATIONSENTDATE, ");
    selectNhsBackingReportsSQL.append("       NBR.SUBCONTRACTAGENCYID, ");
    selectNhsBackingReportsSQL.append("       NBR.COMPLETEDATE, ");
    selectNhsBackingReportsSQL.append("       NBR.COMMENT, ");
    selectNhsBackingReportsSQL.append("       NBR.DOCUMENTATIONFILENAME, ");
    selectNhsBackingReportsSQL.append("       NBR.REJECTEDDOCUMENTATIONFILENAME, ");
    selectNhsBackingReportsSQL.append("       NBR.TRADESHIFTDOCUMENTID, ");
    selectNhsBackingReportsSQL.append("       NBR.CREATIONTIMESTAMP, ");
    selectNhsBackingReportsSQL.append("       NBR.AUDITORID, ");
    selectNhsBackingReportsSQL.append("       NBR.AUDITTIMESTAMP, ");
    selectNhsBackingReportsSQL.append("       NBR.ACTIVE, ");
		selectNhsBackingReportsSQL.append("       NBR.NOOFCHANGES ");
    // Get select NhsBackingReportUsers SQL.
    selectNhsBackingReportUsersSQL = new StringBuffer(selectNhsBackingReportsSQL);
    selectNhsBackingReportsSQL.append("FROM NHSBACKINGREPORT NBR ");
    // Finish the select NhsBackingReportUsers SQL.
    selectNhsBackingReportUsersSQL.append(", ");
    selectNhsBackingReportUsersSQL.append("       C.NAME AS CLIENTNAME,");
    selectNhsBackingReportUsersSQL.append("       C.CODE AS CLIENTCODE ");
    selectNhsBackingReportUsersSQL.append("FROM NHSBACKINGREPORT NBR ");
    selectNhsBackingReportUsersSQL.append("LEFT OUTER JOIN CLIENT C ");
    selectNhsBackingReportUsersSQL.append("ON C.CLIENTID = NBR.CLIENTID ");
    // Get select NhsBackingReportUser SQL.
    selectNhsBackingReportUserSQL = new StringBuffer(selectNhsBackingReportUsersSQL);
    selectNhsBackingReportUserSQL.append("WHERE NBR.NHSBACKINGREPORTID = ^ ");
    //
    selectNhsBackingReportUsersSQL.append("WHERE NBR.AGENCYID = ^ ");
    // Get select NhsBackingReportUsers in List SQL. Comma delimited list of NhsBackingReportId.
    selectNhsBackingReportUsersInListSQL = new StringBuffer(selectNhsBackingReportUsersSQL);
    selectNhsBackingReportUsersInListSQL.append("AND NBR.NHSBACKINGREPORTID IN ( ^ ) ");
    
		// Get select NhsBackingReport SQL.
		selectNhsBackingReportSQL = new StringBuffer(selectNhsBackingReportsSQL);
		selectNhsBackingReportSQL.append("WHERE NBR.NHSBACKINGREPORTID = ^ ");

    
    // Get select NhsBackingReport for Name SQL.
    selectNhsBackingReportForNameSQL = new StringBuffer(selectNhsBackingReportsSQL);
    selectNhsBackingReportForNameSQL.append("WHERE NBR.NAME = ^ ");
    // Get select InactiveNhsBackingReport for Name SQL.
    selectInactiveNhsBackingReportForNameSQL = new StringBuffer(selectNhsBackingReportForNameSQL);
    selectInactiveNhsBackingReportForNameSQL.append("AND NBR.ACTIVE = FALSE ");
    // All NhsBackingReports (always for Agency) SQL 
    selectNhsBackingReportsSQL.append("WHERE NBR.AGENCYID = ^ ");
    // Get select Active NhsBackingReports SQL.
    selectActiveNhsBackingReportsSQL = new StringBuffer(selectNhsBackingReportsSQL);
    selectActiveNhsBackingReportsSQL.append("AND NBR.ACTIVE = TRUE ");
    // Get select Active NhsBackingReportUsers SQL.
    selectActiveNhsBackingReportUsersSQL = new StringBuffer(selectNhsBackingReportUsersSQL);
    selectActiveNhsBackingReportUsersSQL.append("AND NBR.ACTIVE = TRUE ");
    // Get select Active NhsBackingReports SQL.
    selectActiveNhsBackingReportsForDateRangeSQL = new StringBuffer(selectActiveNhsBackingReportsSQL);
    selectActiveNhsBackingReportsForDateRangeSQL.append("AND NBR.STARTDATE <= ^  "); // End of Week
    selectActiveNhsBackingReportsForDateRangeSQL.append("AND NBR.ENDDATE   >= ^  "); // Start of Week
    // Get select Active NhsBackingReportUsers SQL.
    selectActiveNhsBackingReportUsersForDateRangeSQL = new StringBuffer(selectActiveNhsBackingReportUsersSQL);
    selectActiveNhsBackingReportUsersForDateRangeSQL.append("AND NBR.STARTDATE <= ^  "); // End of Week
    selectActiveNhsBackingReportUsersForDateRangeSQL.append("AND NBR.ENDDATE   >= ^  "); // Start of Week
    // Get select Active NhsBackingReportUsers filter PAID ONLY SQL.
    selectActiveNhsBackingReportUsersPaidSQL = new StringBuffer(selectActiveNhsBackingReportUsersSQL);
    selectActiveNhsBackingReportUsersPaidSQL.append("AND NBR.PAIDVALUE > 0  "); // PAID
    // Get select Active NhsBackingReportUsers filter UNPAID ONLY SQL.
    selectActiveNhsBackingReportUsersUnpaidSQL = new StringBuffer(selectActiveNhsBackingReportUsersSQL);
    selectActiveNhsBackingReportUsersUnpaidSQL.append("AND NBR.PAIDVALUE = 0  "); // UNPAID
    // Get select ActiveNhsBackingReport for Name SQL.
    selectActiveNhsBackingReportForNameSQL = new StringBuffer(selectNhsBackingReportForNameSQL);
    selectActiveNhsBackingReportForNameSQL.append("AND NBR.ACTIVE = TRUE ");
    // Add Order By
    selectNhsBackingReportsSQL.append("ORDER BY NBR.NAME DESC");
    selectNhsBackingReportUsersSQL.append("ORDER BY NBR.NAME DESC");
    selectNhsBackingReportUsersInListSQL.append("ORDER BY CLIENTNAME, NBR.NAME ");
    selectActiveNhsBackingReportsSQL.append("ORDER BY NBR.NAME DESC");
    selectActiveNhsBackingReportUsersSQL.append("ORDER BY NBR.NAME DESC");
    selectActiveNhsBackingReportUsersPaidSQL.append("ORDER BY NBR.NAME DESC");
    selectActiveNhsBackingReportUsersUnpaidSQL.append("ORDER BY NBR.NAME DESC");
    // Offset and Limit versions of SQL.
    selectNhsBackingReportUsersOffsetLimitSQL = new StringBuffer(selectNhsBackingReportUsersSQL);
    selectNhsBackingReportUsersOffsetLimitSQL.append(" OFFSET ^ ");
    selectNhsBackingReportUsersOffsetLimitSQL.append(" LIMIT ^ ");
    selectActiveNhsBackingReportUsersOffsetLimitSQL = new StringBuffer(selectActiveNhsBackingReportUsersSQL);
    selectActiveNhsBackingReportUsersOffsetLimitSQL.append(" OFFSET ^ ");
    selectActiveNhsBackingReportUsersOffsetLimitSQL.append(" LIMIT ^ ");
    selectActiveNhsBackingReportUsersPaidOffsetLimitSQL = new StringBuffer(selectActiveNhsBackingReportUsersPaidSQL);
    selectActiveNhsBackingReportUsersPaidOffsetLimitSQL.append(" OFFSET ^ ");
    selectActiveNhsBackingReportUsersPaidOffsetLimitSQL.append(" LIMIT ^ ");
    selectActiveNhsBackingReportUsersUnpaidOffsetLimitSQL = new StringBuffer(selectActiveNhsBackingReportUsersUnpaidSQL);
    selectActiveNhsBackingReportUsersUnpaidOffsetLimitSQL.append(" OFFSET ^ ");
    selectActiveNhsBackingReportUsersUnpaidOffsetLimitSQL.append(" LIMIT ^ ");
    // Get select NhsBackingReports Count SQL.
    selectNhsBackingReportsCountSQL = new StringBuffer();
    selectNhsBackingReportsCountSQL.append("SELECT COUNT(*) ");
    selectNhsBackingReportsCountSQL.append("FROM NHSBACKINGREPORT NBR ");
    selectNhsBackingReportsCountSQL.append("WHERE NBR.AGENCYID = ^ ");
    // Get select Active NhsBackingReports Count SQL.
    selectActiveNhsBackingReportsCountSQL = new StringBuffer(selectNhsBackingReportsCountSQL);
    selectActiveNhsBackingReportsCountSQL.append("AND NBR.ACTIVE = TRUE ");
    // Get select Active NhsBackingReports PAID Count SQL.
    selectActiveNhsBackingReportsPaidCountSQL = new StringBuffer(selectActiveNhsBackingReportsCountSQL);
    selectActiveNhsBackingReportsPaidCountSQL.append("AND NBR.PAIDVALUE > 0 ");
    // Get select Active NhsBackingReports UNPAID Count SQL.
    selectActiveNhsBackingReportsUnpaidCountSQL = new StringBuffer(selectActiveNhsBackingReportsCountSQL);
    selectActiveNhsBackingReportsUnpaidCountSQL.append("AND NBR.PAIDVALUE = 0 ");
	}

	public int insertNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertNhsBackingReportSQL.toString());
		// Replace the parameters with supplied values.
		nhsBackingReport.setNhsBackingReportId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "nhsBackingReport"));
    Utilities.replace(sql, nhsBackingReport.getNhsBackingReportId());
    Utilities.replace(sql, nhsBackingReport.getAgencyId());
    Utilities.replace(sql, nhsBackingReport.getClientId());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getName().trim());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getStartDate());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getEndDate());
    Utilities.replace(sql, nhsBackingReport.getAgreedValue() == null ? new BigDecimal(0) : nhsBackingReport.getAgreedValue());
    Utilities.replace(sql, nhsBackingReport.getPaidValue() == null ? new BigDecimal(0) : nhsBackingReport.getPaidValue());
    Utilities.replace(sql, nhsBackingReport.getSubcontractValue() == null ? new BigDecimal(0) : nhsBackingReport.getSubcontractValue());
    Utilities.replace(sql, nhsBackingReport.getSubcontractAgencyId());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getComment());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getDocumentationFileName());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getRejectedDocumentationFileName());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateNhsBackingReportSQL.toString());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getName().trim());
    Utilities.replace(sql, nhsBackingReport.getClientId());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getStartDate());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getEndDate());
    Utilities.replace(sql, nhsBackingReport.getAgreedValue() == null ? new BigDecimal(0) : nhsBackingReport.getAgreedValue());
    Utilities.replace(sql, nhsBackingReport.getPaidValue() == null ? new BigDecimal(0) : nhsBackingReport.getPaidValue());
    Utilities.replace(sql, nhsBackingReport.getSubcontractValue() == null ? new BigDecimal(0) : nhsBackingReport.getSubcontractValue());
//    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getSubcontractPaidDate());
    Utilities.replace(sql, nhsBackingReport.getSubcontractAgencyId());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getCompleteDate());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getComment());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getDocumentationFileName());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getRejectedDocumentationFileName());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, nhsBackingReport.getNhsBackingReportId());
    Utilities.replace(sql, nhsBackingReport.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
//	public int updateNhsBackingReport(StringBuffer sql, NhsBackingReport nhsBackingReport, Integer auditorId) 
//  {
//		// Replace the parameters with supplied values.
//    Utilities.replaceAndQuote(sql, nhsBackingReport.getName().trim());
//    Utilities.replace(sql, nhsBackingReport.getClientId());
//    Utilities.replaceAndQuote(sql, nhsBackingReport.getStartDate());
//    Utilities.replaceAndQuote(sql, nhsBackingReport.getEndDate());
//    Utilities.replace(sql, nhsBackingReport.getAgreedValue() == null ? new BigDecimal(0) : nhsBackingReport.getAgreedValue());
//    Utilities.replace(sql, nhsBackingReport.getPaidValue() == null ? new BigDecimal(0) : nhsBackingReport.getPaidValue());
//    Utilities.replace(sql, nhsBackingReport.getSubcontractValue() == null ? new BigDecimal(0) : nhsBackingReport.getSubcontractValue());
////    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getSubcontractPaidDate());
//    Utilities.replace(sql, nhsBackingReport.getSubcontractAgencyId());
//    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getCompleteDate());
//    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getComment());
//    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getDocumentationFileName());
//    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getRejectedDocumentationFileName());
//    Utilities.replace(sql, auditorId);
//    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
//    // Start of Where clause.
//    Utilities.replace(sql, nhsBackingReport.getNhsBackingReportId());
//		Utilities.replace(sql, nhsBackingReport.getNoOfChanges());
//		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
//	}

  public int reactivateNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(reactivateNhsBackingReportSQL.toString());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getName().trim());
    Utilities.replace(sql, nhsBackingReport.getClientId());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getStartDate());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getEndDate());
    Utilities.replace(sql, nhsBackingReport.getAgreedValue() == null ? new BigDecimal(0) : nhsBackingReport.getAgreedValue());
    Utilities.replace(sql, nhsBackingReport.getPaidValue() == null ? new BigDecimal(0) : nhsBackingReport.getPaidValue());
    Utilities.replace(sql, nhsBackingReport.getSubcontractValue() == null ? new BigDecimal(0) : nhsBackingReport.getSubcontractValue());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getSubcontractPaidDate());
    Utilities.replace(sql, nhsBackingReport.getSubcontractAgencyId());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getCompleteDate());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getComment());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getDocumentationFileName());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getRejectedDocumentationFileName());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, nhsBackingReport.getNhsBackingReportId());
    Utilities.replace(sql, nhsBackingReport.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
  public int updateNhsBackingReportTradeshiftDocumentId(NhsBackingReport nhsBackingReport, Integer auditorId) 
  {
    // Replace the parameters with supplied values.
    StringBuffer sql = new StringBuffer(updateNhsBackingReportTradeshiftDocumentIdSQL.toString());
    Utilities.replaceAndQuote(sql, nhsBackingReport.getTradeshiftDocumentId());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, nhsBackingReport.getNhsBackingReportId());
    Utilities.replace(sql, nhsBackingReport.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
  public int updateNhsBackingReportSubcontract(NhsBackingReport nhsBackingReport, Integer auditorId) 
  {
    // Replace the parameters with supplied values.
    StringBuffer sql = new StringBuffer(updateNhsBackingReportSubcontractSQL.toString());
    Utilities.replace(sql, nhsBackingReport.getSubcontractValue());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getSubcontractPaidDate());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, nhsBackingReport.getNhsBackingReportId());
    Utilities.replace(sql, nhsBackingReport.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
  public int updateNhsBackingReportSubcontractDocumentationSentDate(NhsBackingReport nhsBackingReport, Integer auditorId) 
  {
    // Replace the parameters with supplied values.
    StringBuffer sql = new StringBuffer(updateNhsBackingReportSubcontractDocumentationSentDateSQL.toString());
    Utilities.replaceAndQuoteNullable(sql, nhsBackingReport.getSubcontractDocumentationSentDate());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, nhsBackingReport.getNhsBackingReportId());
    Utilities.replace(sql, nhsBackingReport.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
	public int deleteNhsBackingReport(Integer nhsBackingReportId, Integer noOfChanges,
			Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteNhsBackingReportSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, nhsBackingReportId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public NhsBackingReport getNhsBackingReport(Integer nhsBackingReportId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectNhsBackingReportSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, nhsBackingReportId);
		return (NhsBackingReport) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBackingReport.class.getName());
	}

  public NhsBackingReportUser getNhsBackingReportUser(Integer nhsBackingReportId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectNhsBackingReportUserSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, nhsBackingReportId);
    return (NhsBackingReportUser) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBackingReportUser.class.getName());
  }

	public NhsBackingReport getNhsBackingReportForName(String name, boolean showOnlyActive) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveNhsBackingReportForNameSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectNhsBackingReportForNameSQL.toString()); 
    }
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (NhsBackingReport) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBackingReport.class.getName());
	}

  public NhsBackingReport getInactiveNhsBackingReportForName(String name) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectInactiveNhsBackingReportForNameSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, name);
    return (NhsBackingReport) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBackingReport.class.getName());
  }

	public List<NhsBackingReport> getNhsBackingReports(Integer agencyId) 
  {
		return getNhsBackingReports(agencyId, false);
	}

	public List<NhsBackingReport> getNhsBackingReports(Integer agencyId, boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveNhsBackingReportsSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectNhsBackingReportsSQL.toString()); 
		}
    Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), NhsBackingReport.class.getName());
	}

  public RecordCount getNhsBackingReportsCount(Integer agencyId) 
  {
    return getNhsBackingReportsCount(agencyId, false, AgyConstants.NO_FILTER);
  }

  public RecordCount getNhsBackingReportsCount(Integer agencyId, boolean showOnlyActive, String filter) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      if (filter.equals(AgyConstants.PAID_FILTER))
      {
        sql = new StringBuffer(selectActiveNhsBackingReportsPaidCountSQL.toString());
      }
      else
      {
        if (filter.equals(AgyConstants.UNPAID_FILTER))
        {
          sql = new StringBuffer(selectActiveNhsBackingReportsUnpaidCountSQL.toString());
        }
        else
        {
          sql = new StringBuffer(selectActiveNhsBackingReportsCountSQL.toString());
        }
      }
    }
    else 
    {
      sql = new StringBuffer(selectNhsBackingReportsCountSQL.toString()); 
    }
    Utilities.replace(sql, agencyId);
    return (RecordCount)RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), RecordCount.class.getName());
  }

  public List<NhsBackingReportUser> getNhsBackingReportUsers(Integer agencyId, boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveNhsBackingReportUsersSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectNhsBackingReportUsersSQL.toString()); 
    }
    Utilities.replace(sql, agencyId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBackingReportUser.class.getName());
  }

  public List<NhsBackingReportUser> getNhsBackingReportUsersOffset(Integer agencyId, boolean showOnlyActive, Integer offset, String filter) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      if (filter.equals(AgyConstants.PAID_FILTER))
      {
        sql = new StringBuffer(selectActiveNhsBackingReportUsersPaidOffsetLimitSQL.toString());        
      }
      else
      {
        if (filter.equals(AgyConstants.UNPAID_FILTER))
        {
          sql = new StringBuffer(selectActiveNhsBackingReportUsersUnpaidOffsetLimitSQL.toString());
        }
        else
        {
          sql = new StringBuffer(selectActiveNhsBackingReportUsersOffsetLimitSQL.toString());
        }
      }
    }
    else 
    {
      sql = new StringBuffer(selectNhsBackingReportUsersOffsetLimitSQL.toString()); 
    }
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, offset);
    Utilities.replace(sql, getPagingLimit() == null ? 100 : getPagingLimit());
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBackingReportUser.class.getName());
  }

  public List<NhsBackingReportUser> getNhsBackingReportUsersInList(Integer agencyId, String nhsBackingReportIdList) 
  {
    StringBuffer sql = new StringBuffer(selectNhsBackingReportUsersInListSQL.toString()); 
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, nhsBackingReportIdList);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBackingReportUser.class.getName());
  }

	public List<NhsBackingReport> getActiveNhsBackingReports(Integer agencyId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveNhsBackingReportsSQL.toString());
    Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), NhsBackingReport.class.getName());
	}

  public List<NhsBackingReport> getNhsBackingReportsForAgencyDateRange(Integer agencyId, Date startOfWeekDate, Date endOfWeekDate)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectActiveNhsBackingReportsForDateRangeSQL.toString());
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, endOfWeekDate);
    Utilities.replaceAndQuote(sql, startOfWeekDate);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBackingReport.class.getName());
  }

  public List<NhsBackingReportUser> getNhsBackingReportUsersForAgencyDateRange(Integer agencyId, Date startOfWeekDate, Date endOfWeekDate)
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectActiveNhsBackingReportUsersForDateRangeSQL.toString());
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, endOfWeekDate);
    Utilities.replaceAndQuote(sql, startOfWeekDate);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), NhsBackingReportUser.class.getName());
  }

}
