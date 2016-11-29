package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AdministratorAccessGroup;
import com.helmet.bean.AdministratorAccessGroupUser;
import com.helmet.persistence.AdministratorAccessGroupDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAdministratorAccessGroupDAO extends JdbcDaoSupport implements AdministratorAccessGroupDAO {

	private static StringBuffer insertAdministratorAccessGroupSQL;

	private static StringBuffer deleteAdministratorAccessGroupSQL;

	private static StringBuffer selectAdministratorAccessGroupUsersSQL;

	private static StringBuffer selectAdministratorAccessGroupUsersForAdministratorSQL;

	public static void init() {
		// Get insert AdministratorAccessGroup SQL.
		insertAdministratorAccessGroupSQL = new StringBuffer();
		insertAdministratorAccessGroupSQL.append("INSERT INTO ADMINISTRATORACCESSGROUP ");
		insertAdministratorAccessGroupSQL.append("(  ");
		insertAdministratorAccessGroupSQL.append("  ADMINISTRATORACCESSGROUPID, ");
		insertAdministratorAccessGroupSQL.append("  ADMINISTRATORID, ");
		insertAdministratorAccessGroupSQL.append("  ADMINACCESSGROUPID, ");
		insertAdministratorAccessGroupSQL.append("  CREATIONTIMESTAMP, ");
		insertAdministratorAccessGroupSQL.append("  AUDITORID, ");
		insertAdministratorAccessGroupSQL.append("  AUDITTIMESTAMP ");
		insertAdministratorAccessGroupSQL.append(")  ");
		insertAdministratorAccessGroupSQL.append("VALUES  ");
		insertAdministratorAccessGroupSQL.append("(  ");
		insertAdministratorAccessGroupSQL.append("  ^, ");
		insertAdministratorAccessGroupSQL.append("  ^, ");
		insertAdministratorAccessGroupSQL.append("  ^, ");
		insertAdministratorAccessGroupSQL.append("  ^, ");
		insertAdministratorAccessGroupSQL.append("  ^, ");
		insertAdministratorAccessGroupSQL.append("  ^ ");
		insertAdministratorAccessGroupSQL.append(")  ");
		// Get delete AdministratorAccessGroup SQL.
		deleteAdministratorAccessGroupSQL = new StringBuffer();
		// deleteAdministratorAccessGroupSQL = new StringBuffer();
		deleteAdministratorAccessGroupSQL.append("UPDATE ADMINISTRATORACCESSGROUP ");
		deleteAdministratorAccessGroupSQL.append("SET ACTIVE = FALSE, ");
		deleteAdministratorAccessGroupSQL.append("    AUDITORID = ^, ");
		deleteAdministratorAccessGroupSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAdministratorAccessGroupSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAdministratorAccessGroupSQL.append("WHERE ADMINISTRATORACCESSGROUPID = ^ ");
		deleteAdministratorAccessGroupSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AdministratorAccessGroupUsers SQL.
		selectAdministratorAccessGroupUsersSQL = new StringBuffer();
		selectAdministratorAccessGroupUsersSQL.append("SELECT A.ADMINISTRATORACCESSGROUPID, ");
		selectAdministratorAccessGroupUsersSQL.append("       A.ADMINISTRATORID, ");
		selectAdministratorAccessGroupUsersSQL.append("       A.ADMINACCESSGROUPID, ");
		selectAdministratorAccessGroupUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectAdministratorAccessGroupUsersSQL.append("       A.AUDITORID, ");
		selectAdministratorAccessGroupUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectAdministratorAccessGroupUsersSQL.append("       A.ACTIVE, ");
		selectAdministratorAccessGroupUsersSQL.append("       A.NOOFCHANGES, ");
		selectAdministratorAccessGroupUsersSQL.append("       B.NAME AS ADMINACCESSGROUPNAME ");
		selectAdministratorAccessGroupUsersSQL.append("FROM ADMINISTRATORACCESSGROUP A, ");
		selectAdministratorAccessGroupUsersSQL.append("     ADMINACCESSGROUP B ");
		selectAdministratorAccessGroupUsersSQL.append("WHERE B.ADMINACCESSGROUPID = A.ADMINACCESSGROUPID ");
		// Get select AdministratorAccessGroupUsers for Administrator SQL.
		selectAdministratorAccessGroupUsersForAdministratorSQL = new StringBuffer(selectAdministratorAccessGroupUsersSQL);
		selectAdministratorAccessGroupUsersForAdministratorSQL.append("AND A.ADMINISTRATORID = ^ ");
		selectAdministratorAccessGroupUsersForAdministratorSQL.append("AND A.ACTIVE = TRUE ");
		selectAdministratorAccessGroupUsersForAdministratorSQL.append("ORDER BY B.NAME ");
	}

	public int insertAdministratorAccessGroup(AdministratorAccessGroup administratorAccessGroup, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAdministratorAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		administratorAccessGroup.setAdministratorAccessGroupId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "administratorAccessGroup"));
		Utilities.replace(sql, administratorAccessGroup.getAdministratorAccessGroupId());
		Utilities.replace(sql, administratorAccessGroup.getAdministratorId());
		Utilities.replace(sql, administratorAccessGroup.getAdminAccessGroupId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteAdministratorAccessGroup(Integer administratorAccessGroupId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAdministratorAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, administratorAccessGroupId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<AdministratorAccessGroupUser> getAdministratorAccessGroupUsersForAdministrator(Integer administratorId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdministratorAccessGroupUsersForAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, administratorId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdministratorAccessGroupUser.class.getName());

	}

}
