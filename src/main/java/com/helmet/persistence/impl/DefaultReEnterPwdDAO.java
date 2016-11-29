package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ReEnterPwd;
import com.helmet.persistence.ReEnterPwdDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultReEnterPwdDAO extends JdbcDaoSupport implements ReEnterPwdDAO {

	private static StringBuffer insertReEnterPwdSQL;

	private static StringBuffer updateReEnterPwdSQL;

	private static StringBuffer deleteReEnterPwdSQL;

	private static StringBuffer selectReEnterPwdSQL;

	private static StringBuffer selectReEnterPwdForNameSQL;

	private static StringBuffer selectReEnterPwdsSQL;

	private static StringBuffer selectActiveReEnterPwdsSQL;

	private static StringBuffer selectReEnterPwdsNotForClientSQL;
	
	public static void init() {
		// Get insert ReEnterPwd SQL.
		insertReEnterPwdSQL = new StringBuffer();
		insertReEnterPwdSQL.append("INSERT INTO REENTERPWD ");
		insertReEnterPwdSQL.append("(  ");
		insertReEnterPwdSQL.append("  REENTERPWDID, ");
		insertReEnterPwdSQL.append("  NAME, ");
		insertReEnterPwdSQL.append("  CREATIONTIMESTAMP, ");
		insertReEnterPwdSQL.append("  AUDITORID, ");
		insertReEnterPwdSQL.append("  AUDITTIMESTAMP ");
		insertReEnterPwdSQL.append(")  ");
		insertReEnterPwdSQL.append("VALUES  ");
		insertReEnterPwdSQL.append("(  ");
		insertReEnterPwdSQL.append("  ^, ");
		insertReEnterPwdSQL.append("  ^, ");
		insertReEnterPwdSQL.append("  ^, ");
		insertReEnterPwdSQL.append("  ^, ");
		insertReEnterPwdSQL.append("  ^ ");
		insertReEnterPwdSQL.append(")  ");
		// Get update ReEnterPwd SQL.
		updateReEnterPwdSQL = new StringBuffer();
		updateReEnterPwdSQL.append("UPDATE REENTERPWD ");
		updateReEnterPwdSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateReEnterPwdSQL.append("     NAME = ^, ");
		updateReEnterPwdSQL.append("     AUDITORID = ^, ");
		updateReEnterPwdSQL.append("     AUDITTIMESTAMP = ^ ");
		updateReEnterPwdSQL.append("WHERE REENTERPWDID = ^ ");
		updateReEnterPwdSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete ReEnterPwd SQL.
		deleteReEnterPwdSQL = new StringBuffer();
		// deleteReEnterPwdSQL = new StringBuffer();
		deleteReEnterPwdSQL.append("UPDATE REENTERPWD ");
		deleteReEnterPwdSQL.append("SET ACTIVE = FALSE, ");
		deleteReEnterPwdSQL.append("    AUDITORID = ^, ");
		deleteReEnterPwdSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteReEnterPwdSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteReEnterPwdSQL.append("WHERE REENTERPWDID = ^ ");
		deleteReEnterPwdSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ReEnterPwds SQL.
		selectReEnterPwdsSQL = new StringBuffer();
		selectReEnterPwdsSQL.append("SELECT R.REENTERPWDID, ");
		selectReEnterPwdsSQL.append("       R.NAME, ");
		selectReEnterPwdsSQL.append("       R.CREATIONTIMESTAMP, ");
		selectReEnterPwdsSQL.append("       R.AUDITORID, ");
		selectReEnterPwdsSQL.append("       R.AUDITTIMESTAMP, ");
		selectReEnterPwdsSQL.append("       R.ACTIVE, ");
		selectReEnterPwdsSQL.append("       R.NOOFCHANGES ");
		selectReEnterPwdsSQL.append("FROM REENTERPWD R ");
		// Get select ReEnterPwd SQL.
		selectReEnterPwdSQL = new StringBuffer(selectReEnterPwdsSQL);
		selectReEnterPwdSQL.append("WHERE R.REENTERPWDID = ^ ");
		// Get select Active Countries SQL.
		selectActiveReEnterPwdsSQL = new StringBuffer(selectReEnterPwdsSQL);
		selectActiveReEnterPwdsSQL.append("WHERE R.ACTIVE = TRUE ");
		// Get select ReEnterPwd for Name SQL.
		selectReEnterPwdForNameSQL = new StringBuffer(selectActiveReEnterPwdsSQL);
		selectReEnterPwdForNameSQL.append("AND R.NAME = ^ ");
		// Get select ReEnterPwds not for Client SQL.
		selectReEnterPwdsNotForClientSQL = new StringBuffer(selectActiveReEnterPwdsSQL);
		selectReEnterPwdsNotForClientSQL.append("AND NOT EXISTS ");
		selectReEnterPwdsNotForClientSQL.append("( ");
		selectReEnterPwdsNotForClientSQL.append(" SELECT NULL ");
		selectReEnterPwdsNotForClientSQL.append(" FROM CLIENTREENTERPWD CR ");
		selectReEnterPwdsNotForClientSQL.append(" WHERE CR.CLIENTID = ^ ");
		selectReEnterPwdsNotForClientSQL.append(" AND CR.ACTIVE = TRUE ");
		selectReEnterPwdsNotForClientSQL.append(" AND CR.REENTERPWDID = R.REENTERPWDID ");
		selectReEnterPwdsNotForClientSQL.append(") ");
		selectReEnterPwdsNotForClientSQL.append("ORDER BY R.NAME ");
	
	
	}

	public int insertReEnterPwd(ReEnterPwd reEnterPwd, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertReEnterPwdSQL.toString());
		// Replace the parameters with supplied values.
		reEnterPwd.setReEnterPwdId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "reEnterPwd"));
		Utilities.replace(sql, reEnterPwd.getReEnterPwdId());
		Utilities.replaceAndQuote(sql, reEnterPwd.getName());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateReEnterPwd(ReEnterPwd reEnterPwd, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateReEnterPwdSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, reEnterPwd.getName());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, reEnterPwd.getReEnterPwdId());
		Utilities.replace(sql, reEnterPwd.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteReEnterPwd(Integer reEnterPwdId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteReEnterPwdSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, reEnterPwdId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public ReEnterPwd getReEnterPwd(Integer reEnterPwdId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectReEnterPwdSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, reEnterPwdId);
		return (ReEnterPwd) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ReEnterPwd.class.getName());
	}

	public ReEnterPwd getReEnterPwdForName(String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectReEnterPwdForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (ReEnterPwd) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ReEnterPwd.class.getName());
	}

	public List<ReEnterPwd> getreEnterPwds() {

		return getReEnterPwds(false);
		
	}

	public List<ReEnterPwd> getReEnterPwds(boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveReEnterPwdsSQL.toString());
		}
		else {
			sql = new StringBuffer(selectReEnterPwdsSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ReEnterPwd.class.getName());

	}

	public List<ReEnterPwd> getActiveReEnterPwds() {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveReEnterPwdsSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ReEnterPwd.class.getName());

	}

	public List<ReEnterPwd> getReEnterPwdsNotForClient(Integer clientId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectReEnterPwdsNotForClientSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ReEnterPwd.class.getName());

	}

}
