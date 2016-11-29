package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ClientReEnterPwd;
import com.helmet.bean.ClientReEnterPwdUser;
import com.helmet.persistence.ClientReEnterPwdDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultClientReEnterPwdDAO extends JdbcDaoSupport implements ClientReEnterPwdDAO {

	private static StringBuffer insertClientReEnterPwdSQL;

	private static StringBuffer deleteClientReEnterPwdSQL;

	private static StringBuffer selectClientReEnterPwdUsersSQL;

	private static StringBuffer selectClientReEnterPwdUsersForClientSQL;

	public static void init() {
		// Get insert ClientReEnterPwd SQL.
		insertClientReEnterPwdSQL = new StringBuffer();
		insertClientReEnterPwdSQL.append("INSERT INTO CLIENTREENTERPWD ");
		insertClientReEnterPwdSQL.append("(  ");
		insertClientReEnterPwdSQL.append("  CLIENTREENTERPWDID, ");
		insertClientReEnterPwdSQL.append("  CLIENTID, ");
		insertClientReEnterPwdSQL.append("  REENTERPWDID, ");
		insertClientReEnterPwdSQL.append("  CREATIONTIMESTAMP, ");
		insertClientReEnterPwdSQL.append("  AUDITORID, ");
		insertClientReEnterPwdSQL.append("  AUDITTIMESTAMP ");
		insertClientReEnterPwdSQL.append(")  ");
		insertClientReEnterPwdSQL.append("VALUES  ");
		insertClientReEnterPwdSQL.append("(  ");
		insertClientReEnterPwdSQL.append("  ^, ");
		insertClientReEnterPwdSQL.append("  ^, ");
		insertClientReEnterPwdSQL.append("  ^, ");
		insertClientReEnterPwdSQL.append("  ^, ");
		insertClientReEnterPwdSQL.append("  ^, ");
		insertClientReEnterPwdSQL.append("  ^ ");
		insertClientReEnterPwdSQL.append(")  ");
		// Get delete ClientReEnterPwd SQL.
		deleteClientReEnterPwdSQL = new StringBuffer();
		deleteClientReEnterPwdSQL.append("UPDATE CLIENTREENTERPWD ");
		deleteClientReEnterPwdSQL.append("SET ACTIVE = FALSE, ");
		deleteClientReEnterPwdSQL.append("    AUDITORID = ^, ");
		deleteClientReEnterPwdSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteClientReEnterPwdSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteClientReEnterPwdSQL.append("WHERE CLIENTREENTERPWDID = ^ ");
		deleteClientReEnterPwdSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ClientReEnterPwds SQL.
		selectClientReEnterPwdUsersSQL = new StringBuffer();
		selectClientReEnterPwdUsersSQL.append("SELECT CR.CLIENTREENTERPWDID, ");
		selectClientReEnterPwdUsersSQL.append("       CR.CLIENTID, ");
		selectClientReEnterPwdUsersSQL.append("       CR.REENTERPWDID, ");
		selectClientReEnterPwdUsersSQL.append("       CR.CREATIONTIMESTAMP, ");
		selectClientReEnterPwdUsersSQL.append("       CR.AUDITORID, ");
		selectClientReEnterPwdUsersSQL.append("       CR.AUDITTIMESTAMP, ");
		selectClientReEnterPwdUsersSQL.append("       CR.ACTIVE, ");
		selectClientReEnterPwdUsersSQL.append("       CR.NOOFCHANGES, ");
		selectClientReEnterPwdUsersSQL.append("       R.NAME AS REENTERPWDNAME ");
		selectClientReEnterPwdUsersSQL.append("FROM CLIENTREENTERPWD CR, ");
		selectClientReEnterPwdUsersSQL.append("     REENTERPWD R ");
		selectClientReEnterPwdUsersSQL.append("WHERE R.REENTERPWDID = CR.REENTERPWDID ");
		selectClientReEnterPwdUsersSQL.append("AND R.ACTIVE = TRUE ");
		// Get select ClientReEnterPwdUsers for Client SQL.
		selectClientReEnterPwdUsersForClientSQL = new StringBuffer(selectClientReEnterPwdUsersSQL);
		selectClientReEnterPwdUsersForClientSQL.append("AND CR.CLIENTID = ^ ");
		selectClientReEnterPwdUsersForClientSQL.append("AND CR.ACTIVE = TRUE ");
		selectClientReEnterPwdUsersForClientSQL.append("ORDER BY R.NAME ");
		
	}

	public int insertClientReEnterPwd(ClientReEnterPwd clientReEnterPwd, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertClientReEnterPwdSQL.toString());
		// Replace the parameters with supplied values.
		clientReEnterPwd.setClientReEnterPwdId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "clientReEnterPwd"));
		Utilities.replace(sql, clientReEnterPwd.getClientReEnterPwdId());
		Utilities.replace(sql, clientReEnterPwd.getClientId());
		Utilities.replace(sql, clientReEnterPwd.getReEnterPwdId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteClientReEnterPwd(Integer clientReEnterPwdId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteClientReEnterPwdSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientReEnterPwdId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}


	public List<ClientReEnterPwdUser> getClientReEnterPwdUsersForClient(Integer clientId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientReEnterPwdUsersForClientSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientReEnterPwdUser.class.getName());

	}

}
