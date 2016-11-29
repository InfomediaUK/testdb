package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AdministratorAccess;
import com.helmet.bean.AdministratorAccessUser;
import com.helmet.persistence.AdministratorAccessDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAdministratorAccessDAO extends JdbcDaoSupport implements AdministratorAccessDAO {

	private static StringBuffer insertAdministratorAccessSQL;

	private static StringBuffer deleteAdministratorAccessSQL;

	private static StringBuffer selectAdministratorAccessUsersSQL;

	private static StringBuffer selectAdministratorAccessUsersForAdministratorSQL;

//	private static StringBuffer selectAdministratorAccessUsersForAdministratorAccessGroupsSQL;

	public static void init() {
		// Get insert AdministratorAccess SQL.
		insertAdministratorAccessSQL = new StringBuffer();
		insertAdministratorAccessSQL.append("INSERT INTO ADMINISTRATORACCESS ");
		insertAdministratorAccessSQL.append("(  ");
		insertAdministratorAccessSQL.append("  ADMINISTRATORACCESSID, ");
		insertAdministratorAccessSQL.append("  ADMINISTRATORID, ");
		insertAdministratorAccessSQL.append("  ADMINACCESSID, ");
		insertAdministratorAccessSQL.append("  CREATIONTIMESTAMP, ");
		insertAdministratorAccessSQL.append("  AUDITORID, ");
		insertAdministratorAccessSQL.append("  AUDITTIMESTAMP ");
		insertAdministratorAccessSQL.append(")  ");
		insertAdministratorAccessSQL.append("VALUES  ");
		insertAdministratorAccessSQL.append("(  ");
		insertAdministratorAccessSQL.append("  ^, ");
		insertAdministratorAccessSQL.append("  ^, ");
		insertAdministratorAccessSQL.append("  ^, ");
		insertAdministratorAccessSQL.append("  ^, ");
		insertAdministratorAccessSQL.append("  ^, ");
		insertAdministratorAccessSQL.append("  ^ ");
		insertAdministratorAccessSQL.append(")  ");
		// Get delete AdministratorAccess SQL.
		deleteAdministratorAccessSQL = new StringBuffer();
		// deleteAdministratorAccessSQL = new StringBuffer();
		deleteAdministratorAccessSQL.append("UPDATE ADMINISTRATORACCESS ");
		deleteAdministratorAccessSQL.append("SET ACTIVE = FALSE, ");
		deleteAdministratorAccessSQL.append("    AUDITORID = ^, ");
		deleteAdministratorAccessSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAdministratorAccessSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAdministratorAccessSQL.append("WHERE ADMINISTRATORACCESSID = ^ ");
		deleteAdministratorAccessSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AdministratorAccessUsers SQL.
		selectAdministratorAccessUsersSQL = new StringBuffer();
		selectAdministratorAccessUsersSQL.append("SELECT A.ADMINISTRATORACCESSID, ");
		selectAdministratorAccessUsersSQL.append("       A.ADMINISTRATORID, ");
		selectAdministratorAccessUsersSQL.append("       A.ADMINACCESSID, ");
		selectAdministratorAccessUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectAdministratorAccessUsersSQL.append("       A.AUDITORID, ");
		selectAdministratorAccessUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectAdministratorAccessUsersSQL.append("       A.ACTIVE, ");
		selectAdministratorAccessUsersSQL.append("       A.NOOFCHANGES, ");
		selectAdministratorAccessUsersSQL.append("       B.NAME AS ADMINACCESSNAME ");
		selectAdministratorAccessUsersSQL.append("FROM ADMINISTRATORACCESS A, ");
		selectAdministratorAccessUsersSQL.append("     ADMINACCESS B ");
		selectAdministratorAccessUsersSQL.append("WHERE B.ADMINACCESSID = A.ADMINACCESSID ");
		// Get select AdministratorAccessUsers for Administrator SQL.
		selectAdministratorAccessUsersForAdministratorSQL = new StringBuffer(selectAdministratorAccessUsersSQL);
		selectAdministratorAccessUsersForAdministratorSQL.append("AND A.ADMINISTRATORID = ^ ");
		selectAdministratorAccessUsersForAdministratorSQL.append("AND A.ACTIVE = TRUE ");
		selectAdministratorAccessUsersForAdministratorSQL.append("ORDER BY B.NAME ");
	}

	public int insertAdministratorAccess(AdministratorAccess administratorAccess, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAdministratorAccessSQL.toString());
		// Replace the parameters with supplied values.
		administratorAccess.setAdministratorAccessId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "administratorAccess"));
		Utilities.replace(sql, administratorAccess.getAdministratorAccessId());
		Utilities.replace(sql, administratorAccess.getAdministratorId());
		Utilities.replace(sql, administratorAccess.getAdminAccessId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteAdministratorAccess(Integer administratorAccessId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAdministratorAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, administratorAccessId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<AdministratorAccessUser> getAdministratorAccessUsersForAdministrator(Integer administratorId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdministratorAccessUsersForAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, administratorId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdministratorAccessUser.class.getName());

	}

//	public List<AdministratorAccessUser> getAdministratorAccessUsersForAdministratorAccessGroups(Integer administratorId) {
//
//		// Create a new local StringBuffer containing the parameterised SQL.
//		StringBuffer sql = new StringBuffer(selectAdministratorAccessUsersForAdministratorAccessGroupsSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replace(sql, administratorId);
//		return RecordListFactory.getInstance().get(getJdbcTemplate(),
//				sql.toString(), AdministratorAccessUser.class.getName());
//
//	}

}
