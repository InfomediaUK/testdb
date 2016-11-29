package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AdminAccess;
import com.helmet.persistence.AdminAccessDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAdminAccessDAO extends JdbcDaoSupport implements AdminAccessDAO {

	private static StringBuffer insertAdminAccessSQL;

	private static StringBuffer updateAdminAccessSQL;

	private static StringBuffer deleteAdminAccessSQL;

	private static StringBuffer selectAdminAccessSQL;

	private static StringBuffer selectAdminAccessForNameSQL;

	private static StringBuffer selectAdminAccessesSQL;

	private static StringBuffer selectActiveAdminAccessesSQL;

	private static StringBuffer selectAdminAccessesNotForAdministratorSQL;

	private static StringBuffer selectAdminAccessesNotForAdminAccessGroupSQL;

	private static StringBuffer selectActiveAdminAccessesForAdministratorSQL;

	public static void init() {
		// Get insert AdminAccess SQL.
		insertAdminAccessSQL = new StringBuffer();
		insertAdminAccessSQL.append("INSERT INTO ADMINACCESS ");
		insertAdminAccessSQL.append("(  ");
		insertAdminAccessSQL.append("  ADMINACCESSID, ");
		insertAdminAccessSQL.append("  NAME, ");
		insertAdminAccessSQL.append("  DESCRIPTION, ");
		insertAdminAccessSQL.append("  STARTSWITH, ");
		insertAdminAccessSQL.append("  GLOBAL, ");
		insertAdminAccessSQL.append("  CREATIONTIMESTAMP, ");
		insertAdminAccessSQL.append("  AUDITORID, ");
		insertAdminAccessSQL.append("  AUDITTIMESTAMP ");
		insertAdminAccessSQL.append(")  ");
		insertAdminAccessSQL.append("VALUES  ");
		insertAdminAccessSQL.append("(  ");
		insertAdminAccessSQL.append("  ^, ");
		insertAdminAccessSQL.append("  ^, ");
		insertAdminAccessSQL.append("  ^, ");
		insertAdminAccessSQL.append("  ^, ");
		insertAdminAccessSQL.append("  ^, ");
		insertAdminAccessSQL.append("  ^, ");
		insertAdminAccessSQL.append("  ^, ");
		insertAdminAccessSQL.append("  ^ ");
		insertAdminAccessSQL.append(")  ");
		// Get update AdminAccess SQL.
		updateAdminAccessSQL = new StringBuffer();
		updateAdminAccessSQL.append("UPDATE ADMINACCESS ");
		updateAdminAccessSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAdminAccessSQL.append("     NAME = ^, ");
		updateAdminAccessSQL.append("     DESCRIPTION = ^, ");
		updateAdminAccessSQL.append("     STARTSWITH = ^, ");
		updateAdminAccessSQL.append("     GLOBAL = ^, ");
		updateAdminAccessSQL.append("     AUDITORID = ^, ");
		updateAdminAccessSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAdminAccessSQL.append("WHERE ADMINACCESSID = ^ ");
		updateAdminAccessSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete AdminAccess SQL.
		deleteAdminAccessSQL = new StringBuffer();
		// deleteAdminAccessSQL = new StringBuffer();
		deleteAdminAccessSQL.append("UPDATE ADMINACCESS ");
		deleteAdminAccessSQL.append("SET ACTIVE = FALSE, ");
		deleteAdminAccessSQL.append("    AUDITORID = ^, ");
		deleteAdminAccessSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAdminAccessSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAdminAccessSQL.append("WHERE ADMINACCESSID = ^ ");
		deleteAdminAccessSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AdminAccesses SQL.
		selectAdminAccessesSQL = new StringBuffer();
		selectAdminAccessesSQL.append("SELECT A.ADMINACCESSID, ");
		selectAdminAccessesSQL.append("       A.NAME, ");
		selectAdminAccessesSQL.append("       A.DESCRIPTION, ");
		selectAdminAccessesSQL.append("       A.STARTSWITH, ");
		selectAdminAccessesSQL.append("       A.GLOBAL, ");
		selectAdminAccessesSQL.append("       A.CREATIONTIMESTAMP, ");
		selectAdminAccessesSQL.append("       A.AUDITORID, ");
		selectAdminAccessesSQL.append("       A.AUDITTIMESTAMP, ");
		selectAdminAccessesSQL.append("       A.ACTIVE, ");
		selectAdminAccessesSQL.append("       A.NOOFCHANGES ");
		selectAdminAccessesSQL.append("FROM ADMINACCESS A ");
		// Get select Active AdminAccesses SQL.
		selectActiveAdminAccessesSQL = new StringBuffer(selectAdminAccessesSQL);
		selectActiveAdminAccessesSQL.append("WHERE A.ACTIVE = TRUE ");
		// Get select AdminAccess SQL.
		selectAdminAccessSQL = new StringBuffer(selectAdminAccessesSQL);
		selectAdminAccessSQL.append("WHERE A.ADMINACCESSID = ^ ");
		// Get select AdminAccess for Name SQL.
		selectAdminAccessForNameSQL = new StringBuffer(selectActiveAdminAccessesSQL);
		selectAdminAccessForNameSQL.append("AND A.NAME = ^ ");
		// Get select AdminAccesses NOT for Administrator SQL.
		selectAdminAccessesNotForAdministratorSQL = new StringBuffer(selectAdminAccessesSQL);
		selectAdminAccessesNotForAdministratorSQL.append("WHERE A.ACTIVE = TRUE ");
		selectAdminAccessesNotForAdministratorSQL.append("AND   A.GLOBAL = FALSE ");
		selectAdminAccessesNotForAdministratorSQL.append("AND NOT EXISTS ");
		selectAdminAccessesNotForAdministratorSQL.append("( ");
		selectAdminAccessesNotForAdministratorSQL.append(" SELECT NULL ");
		selectAdminAccessesNotForAdministratorSQL.append(" FROM ADMINISTRATORACCESS AA ");
		selectAdminAccessesNotForAdministratorSQL.append(" WHERE AA.ADMINISTRATORID = ^ ");
		selectAdminAccessesNotForAdministratorSQL.append(" AND AA.ACTIVE = TRUE ");
		selectAdminAccessesNotForAdministratorSQL.append(" AND A.ADMINACCESSID = AA.ADMINACCESSID ");
		selectAdminAccessesNotForAdministratorSQL.append(") ");
		selectAdminAccessesNotForAdministratorSQL.append("ORDER BY A.NAME ");
		// Get select AdminAccesses NOT for AdminAccessGroup SQL.
		selectAdminAccessesNotForAdminAccessGroupSQL = new StringBuffer(selectAdminAccessesSQL);
		selectAdminAccessesNotForAdminAccessGroupSQL.append("WHERE A.ACTIVE = TRUE ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append("AND   A.GLOBAL = FALSE ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append("AND NOT EXISTS ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append("( ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append(" SELECT NULL ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append(" FROM ADMINACCESSGROUPITEM AA ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append(" WHERE AA.ADMINACCESSGROUPID = ^ ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append(" AND AA.ACTIVE = TRUE ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append(" AND A.ADMINACCESSID = AA.ADMINACCESSID ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append(") ");
		selectAdminAccessesNotForAdminAccessGroupSQL.append("ORDER BY A.NAME ");
		// Get select Active AdminAccessesForAdministrator SQL.
		selectActiveAdminAccessesForAdministratorSQL = new StringBuffer(selectAdminAccessesSQL);
		selectActiveAdminAccessesForAdministratorSQL.append(", ");
		selectActiveAdminAccessesForAdministratorSQL.append("    ADMINISTRATORACCESS AA ");
		selectActiveAdminAccessesForAdministratorSQL.append("WHERE AA.ADMINACCESSID = A.ADMINACCESSID ");
		selectActiveAdminAccessesForAdministratorSQL.append("AND AA.ADMINISTRATORID = ^ ");
		selectActiveAdminAccessesForAdministratorSQL.append("AND A.ACTIVE = TRUE ");
		selectActiveAdminAccessesForAdministratorSQL.append("AND AA.ACTIVE = TRUE ");
		selectActiveAdminAccessesForAdministratorSQL.append("UNION ");
		selectActiveAdminAccessesForAdministratorSQL.append(selectAdminAccessesSQL);
		selectActiveAdminAccessesForAdministratorSQL.append(", ");
        selectActiveAdminAccessesForAdministratorSQL.append("ADMINACCESSGROUPITEM AAGI, ");
		selectActiveAdminAccessesForAdministratorSQL.append("ADMINISTRATORACCESSGROUP AAG ");
		selectActiveAdminAccessesForAdministratorSQL.append("WHERE AAGI.ADMINACCESSID = A.ADMINACCESSID ");
		selectActiveAdminAccessesForAdministratorSQL.append("AND AAG.ADMINACCESSGROUPID = AAGI.ADMINACCESSGROUPID ");
		selectActiveAdminAccessesForAdministratorSQL.append("AND AAG.ADMINISTRATORID = ^ ");
		selectActiveAdminAccessesForAdministratorSQL.append("AND A.ACTIVE = TRUE ");
		selectActiveAdminAccessesForAdministratorSQL.append("AND AAGI.ACTIVE = TRUE ");
		selectActiveAdminAccessesForAdministratorSQL.append("AND AAG.ACTIVE = TRUE ");
		selectActiveAdminAccessesForAdministratorSQL.append("UNION ");
		selectActiveAdminAccessesForAdministratorSQL.append(selectAdminAccessesSQL);
		selectActiveAdminAccessesForAdministratorSQL.append("WHERE A.ACTIVE = TRUE ");
		selectActiveAdminAccessesForAdministratorSQL.append("AND A.GLOBAL = TRUE ");
		// ORDER BY 
		selectAdminAccessesSQL.append("ORDER BY A.NAME ");
		selectActiveAdminAccessesSQL.append("ORDER BY A.GLOBAL, A.NAME ");
	}

	public int insertAdminAccess(AdminAccess adminAccess, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAdminAccessSQL.toString());
		// Replace the parameters with supplied values.
		adminAccess.setAdminAccessId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "adminAccess"));
		Utilities.replace(sql, adminAccess.getAdminAccessId());
		Utilities.replaceAndQuote(sql, adminAccess.getName().trim());
		Utilities.replaceAndQuoteNullable(sql, adminAccess.getDescription());
		Utilities.replace(sql, adminAccess.getStartsWith());
		Utilities.replace(sql, adminAccess.getGlobal());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateAdminAccess(AdminAccess adminAccess, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAdminAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, adminAccess.getName().trim());
		Utilities.replaceAndQuoteNullable(sql, adminAccess.getDescription());
		Utilities.replace(sql, adminAccess.getStartsWith());
		Utilities.replace(sql, adminAccess.getGlobal());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, adminAccess.getAdminAccessId());
		Utilities.replace(sql, adminAccess.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteAdminAccess(Integer adminAccessId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAdminAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, adminAccessId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public AdminAccess getAdminAccess(Integer adminAccessId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdminAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, adminAccessId);
		return (AdminAccess) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccess.class.getName());
	}

	public AdminAccess getAdminAccessForName(String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdminAccessForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (AdminAccess) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccess.class.getName());
	}

	public List<AdminAccess> getAdminAccesses(boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveAdminAccessesSQL.toString());
		}
		else {
			sql = new StringBuffer(selectAdminAccessesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccess.class.getName());

	}

	public List<AdminAccess> getAdminAccessesNotForAdministrator(Integer administratorId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdminAccessesNotForAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, administratorId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccess.class.getName());

	}

	public List<AdminAccess> getAdminAccessesNotForAdminAccessGroup(Integer adminAccessGroupId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdminAccessesNotForAdminAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, adminAccessGroupId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccess.class.getName());

	}

	public List<AdminAccess> getActiveAdminAccessesForAdministrator(Integer administratorId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveAdminAccessesForAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, administratorId);
		Utilities.replace(sql, administratorId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccess.class.getName());
	}

}
