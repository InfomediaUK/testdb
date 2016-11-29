package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ReasonForRequest;
import com.helmet.persistence.ReasonForRequestDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultReasonForRequestDAO extends JdbcDaoSupport implements ReasonForRequestDAO {

	private static StringBuffer insertReasonForRequestSQL;

	private static StringBuffer updateReasonForRequestSQL;

	private static StringBuffer updateReasonForRequestDisplayOrderSQL;

	private static StringBuffer deleteReasonForRequestSQL;

	private static StringBuffer selectReasonForRequestSQL;

	private static StringBuffer selectReasonForRequestForNameSQL;

	private static StringBuffer selectReasonForRequestForCodeSQL;

	private static StringBuffer selectReasonForRequestsSQL;

	private static StringBuffer selectReasonForRequestsForClientSQL;

	private static StringBuffer selectActiveReasonForRequestsForClientSQL;

	public static void init() {
		// Get insert ReasonForRequest SQL.
		insertReasonForRequestSQL = new StringBuffer();
		insertReasonForRequestSQL.append("INSERT INTO REASONFORREQUEST ");
		insertReasonForRequestSQL.append("(  ");
		insertReasonForRequestSQL.append("  REASONFORREQUESTID, ");
		insertReasonForRequestSQL.append("  CLIENTID, ");
		insertReasonForRequestSQL.append("  NAME, ");
		insertReasonForRequestSQL.append("  CODE, ");
		insertReasonForRequestSQL.append("  CREATIONTIMESTAMP, ");
		insertReasonForRequestSQL.append("  AUDITORID, ");
		insertReasonForRequestSQL.append("  AUDITTIMESTAMP ");
		insertReasonForRequestSQL.append(")  ");
		insertReasonForRequestSQL.append("VALUES  ");
		insertReasonForRequestSQL.append("(  ");
		insertReasonForRequestSQL.append("  ^, ");
		insertReasonForRequestSQL.append("  ^, ");
		insertReasonForRequestSQL.append("  ^, ");
		insertReasonForRequestSQL.append("  ^, ");
		insertReasonForRequestSQL.append("  ^, ");
		insertReasonForRequestSQL.append("  ^, ");
		insertReasonForRequestSQL.append("  ^ ");
		insertReasonForRequestSQL.append(")  ");
		// Get update ReasonForRequest SQL.
		updateReasonForRequestSQL = new StringBuffer();
		updateReasonForRequestSQL.append("UPDATE REASONFORREQUEST ");
		updateReasonForRequestSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateReasonForRequestSQL.append("     CLIENTID = ^, ");
		updateReasonForRequestSQL.append("     NAME = ^, ");
		updateReasonForRequestSQL.append("     CODE = ^, ");
		updateReasonForRequestSQL.append("     AUDITORID = ^, ");
		updateReasonForRequestSQL.append("     AUDITTIMESTAMP = ^ ");
		updateReasonForRequestSQL.append("WHERE REASONFORREQUESTID = ^ ");
		updateReasonForRequestSQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateReasonForRequestDisplayOrder SQL.
		updateReasonForRequestDisplayOrderSQL = new StringBuffer();
		updateReasonForRequestDisplayOrderSQL.append("UPDATE REASONFORREQUEST ");
		updateReasonForRequestDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateReasonForRequestDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateReasonForRequestDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateReasonForRequestDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateReasonForRequestDisplayOrderSQL.append("WHERE REASONFORREQUESTID = ^ ");
		updateReasonForRequestDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete ReasonForRequest SQL.
		deleteReasonForRequestSQL = new StringBuffer();
		deleteReasonForRequestSQL.append("UPDATE REASONFORREQUEST ");
		deleteReasonForRequestSQL.append("SET ACTIVE = FALSE, ");
		deleteReasonForRequestSQL.append("    AUDITORID = ^, ");
		deleteReasonForRequestSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteReasonForRequestSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteReasonForRequestSQL.append("WHERE REASONFORREQUESTID = ^ ");
		deleteReasonForRequestSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ReasonForRequests SQL.
		selectReasonForRequestsSQL = new StringBuffer();
		selectReasonForRequestsSQL.append("SELECT R.REASONFORREQUESTID, ");
		selectReasonForRequestsSQL.append("       R.CLIENTID, ");
		selectReasonForRequestsSQL.append("       R.NAME, ");
		selectReasonForRequestsSQL.append("       R.CODE, ");
		selectReasonForRequestsSQL.append("       R.DISPLAYORDER, ");
		selectReasonForRequestsSQL.append("       R.CREATIONTIMESTAMP, ");
		selectReasonForRequestsSQL.append("       R.AUDITORID, ");
		selectReasonForRequestsSQL.append("       R.AUDITTIMESTAMP, ");
		selectReasonForRequestsSQL.append("       R.ACTIVE, ");
		selectReasonForRequestsSQL.append("       R.NOOFCHANGES ");
		selectReasonForRequestsSQL.append("FROM REASONFORREQUEST R ");
		// Get select ReasonForRequests for Client SQL.
		selectReasonForRequestsForClientSQL = new StringBuffer(selectReasonForRequestsSQL);
		selectReasonForRequestsForClientSQL.append("WHERE R.CLIENTID = ^ ");
		// Get select Active ReasonForRequests for Client SQL.
		selectActiveReasonForRequestsForClientSQL = new StringBuffer(selectReasonForRequestsForClientSQL);
		selectActiveReasonForRequestsForClientSQL.append("AND R.ACTIVE = TRUE ");
		// Get select ReasonForRequest SQL.
		selectReasonForRequestSQL = new StringBuffer(selectReasonForRequestsSQL);
		selectReasonForRequestSQL.append("WHERE R.REASONFORREQUESTID = ^ ");
		// Get select ReasonForRequest for Name SQL.
		selectReasonForRequestForNameSQL = new StringBuffer(selectActiveReasonForRequestsForClientSQL);
		selectReasonForRequestForNameSQL.append("AND R.NAME = ^ ");
		// Get select ReasonForRequest for Code SQL.
		selectReasonForRequestForCodeSQL = new StringBuffer(selectActiveReasonForRequestsForClientSQL);
		selectReasonForRequestForCodeSQL.append("AND R.CODE = ^ ");
		// ORDER BY 
		selectReasonForRequestsForClientSQL.append("ORDER BY R.DISPLAYORDER, R.NAME ");
		selectActiveReasonForRequestsForClientSQL.append("ORDER BY R.DISPLAYORDER, R.NAME ");
	}

	public int insertReasonForRequest(ReasonForRequest reasonForRequest, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertReasonForRequestSQL.toString());
		// Replace the parameters with supplied values.
		reasonForRequest.setReasonForRequestId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "reasonForRequest"));
		Utilities.replace(sql, reasonForRequest.getReasonForRequestId());
		Utilities.replace(sql, reasonForRequest.getClientId());
		Utilities.replaceAndQuote(sql, reasonForRequest.getName());
		Utilities.replaceAndQuote(sql, reasonForRequest.getCode());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateReasonForRequest(ReasonForRequest reasonForRequest, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateReasonForRequestSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, reasonForRequest.getClientId());
		Utilities.replaceAndQuote(sql, reasonForRequest.getName());
		Utilities.replaceAndQuote(sql, reasonForRequest.getCode());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, reasonForRequest.getReasonForRequestId());
		Utilities.replace(sql, reasonForRequest.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteReasonForRequest(Integer reasonForRequestId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteReasonForRequestSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, reasonForRequestId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public ReasonForRequest getReasonForRequest(Integer reasonForRequestId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectReasonForRequestSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, reasonForRequestId);
		return (ReasonForRequest) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ReasonForRequest.class.getName());
	}

	public ReasonForRequest getReasonForRequestForName(Integer clientId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectReasonForRequestForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, name);
		return (ReasonForRequest) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ReasonForRequest.class.getName());
	}

	public ReasonForRequest getReasonForRequestForCode(Integer clientId, String code) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectReasonForRequestForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, code);
		return (ReasonForRequest) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ReasonForRequest.class.getName());
	}

	public List<ReasonForRequest> getReasonForRequestsForClient(Integer clientId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveReasonForRequestsForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectReasonForRequestsForClientSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ReasonForRequest.class.getName());

	}

	public int updateReasonForRequestDisplayOrder(Integer reasonForRequestId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateReasonForRequestDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, reasonForRequestId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

}
