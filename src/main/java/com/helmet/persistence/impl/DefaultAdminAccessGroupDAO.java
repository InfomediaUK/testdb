package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AdminAccessGroup;
import com.helmet.bean.AdminAccessGroupEntity;
import com.helmet.persistence.AdminAccessGroupDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAdminAccessGroupDAO extends JdbcDaoSupport implements AdminAccessGroupDAO {

	private static StringBuffer insertAdminAccessGroupSQL;

	private static StringBuffer updateAdminAccessGroupSQL;

	private static StringBuffer deleteAdminAccessGroupSQL;

	private static StringBuffer selectAdminAccessGroupSQL;

	private static StringBuffer selectAdminAccessGroupForNameSQL;

	private static StringBuffer selectAdminAccessGroupsSQL;

	private static StringBuffer selectActiveAdminAccessGroupsSQL;

	private static StringBuffer selectAdminAccessGroupsNotForAdministratorSQL;

	public static void init() {
		// Get insert AdminAccessGroup SQL.
		insertAdminAccessGroupSQL = new StringBuffer();
		insertAdminAccessGroupSQL.append("INSERT INTO ADMINACCESSGROUP ");
		insertAdminAccessGroupSQL.append("(  ");
		insertAdminAccessGroupSQL.append("  ADMINACCESSGROUPID, ");
		insertAdminAccessGroupSQL.append("  NAME, ");
		insertAdminAccessGroupSQL.append("  CREATIONTIMESTAMP, ");
		insertAdminAccessGroupSQL.append("  AUDITORID, ");
		insertAdminAccessGroupSQL.append("  AUDITTIMESTAMP ");
		insertAdminAccessGroupSQL.append(")  ");
		insertAdminAccessGroupSQL.append("VALUES  ");
		insertAdminAccessGroupSQL.append("(  ");
		insertAdminAccessGroupSQL.append("  ^, ");
		insertAdminAccessGroupSQL.append("  ^, ");
		insertAdminAccessGroupSQL.append("  ^, ");
		insertAdminAccessGroupSQL.append("  ^, ");
		insertAdminAccessGroupSQL.append("  ^ ");
		insertAdminAccessGroupSQL.append(")  ");
		// Get update AdminAccessGroup SQL.
		updateAdminAccessGroupSQL = new StringBuffer();
		updateAdminAccessGroupSQL.append("UPDATE ADMINACCESSGROUP ");
		updateAdminAccessGroupSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAdminAccessGroupSQL.append("     NAME = ^, ");
		updateAdminAccessGroupSQL.append("     AUDITORID = ^, ");
		updateAdminAccessGroupSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAdminAccessGroupSQL.append("WHERE ADMINACCESSGROUPID = ^ ");
		updateAdminAccessGroupSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete AdminAccessGroup SQL.
		deleteAdminAccessGroupSQL = new StringBuffer();
		// deleteAdminAccessGroupSQL = new StringBuffer();
		deleteAdminAccessGroupSQL.append("UPDATE ADMINACCESSGROUP ");
		deleteAdminAccessGroupSQL.append("SET ACTIVE = FALSE, ");
		deleteAdminAccessGroupSQL.append("    AUDITORID = ^, ");
		deleteAdminAccessGroupSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAdminAccessGroupSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAdminAccessGroupSQL.append("WHERE ADMINACCESSGROUPID = ^ ");
		deleteAdminAccessGroupSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AdminAccessGroups SQL.
		selectAdminAccessGroupsSQL = new StringBuffer();
		selectAdminAccessGroupsSQL.append("SELECT A.ADMINACCESSGROUPID, ");
		selectAdminAccessGroupsSQL.append("       A.NAME, ");
		selectAdminAccessGroupsSQL.append("       A.CREATIONTIMESTAMP, ");
		selectAdminAccessGroupsSQL.append("       A.AUDITORID, ");
		selectAdminAccessGroupsSQL.append("       A.AUDITTIMESTAMP, ");
		selectAdminAccessGroupsSQL.append("       A.ACTIVE, ");
		selectAdminAccessGroupsSQL.append("       A.NOOFCHANGES ");
		selectAdminAccessGroupsSQL.append("FROM ADMINACCESSGROUP A ");
		// Get select Active AdminAccessGroups SQL.
		selectActiveAdminAccessGroupsSQL = new StringBuffer(selectAdminAccessGroupsSQL);
		selectActiveAdminAccessGroupsSQL.append("WHERE A.ACTIVE = TRUE ");
		// Get select AdminAccessGroup SQL.
		selectAdminAccessGroupSQL = new StringBuffer(selectAdminAccessGroupsSQL);
		selectAdminAccessGroupSQL.append("WHERE A.ADMINACCESSGROUPID = ^ ");
		// Get select AdminAccessGroup for Name SQL.
		selectAdminAccessGroupForNameSQL = new StringBuffer(selectActiveAdminAccessGroupsSQL);
		selectAdminAccessGroupForNameSQL.append("AND A.NAME = ^ ");
		// Get select AdminAccessGroups NOT for Administrator SQL.
		selectAdminAccessGroupsNotForAdministratorSQL = new StringBuffer(selectAdminAccessGroupsSQL);
		selectAdminAccessGroupsNotForAdministratorSQL.append("WHERE A.ACTIVE = TRUE ");
		selectAdminAccessGroupsNotForAdministratorSQL.append("AND NOT EXISTS ");
		selectAdminAccessGroupsNotForAdministratorSQL.append("( ");
		selectAdminAccessGroupsNotForAdministratorSQL.append(" SELECT NULL ");
		selectAdminAccessGroupsNotForAdministratorSQL.append(" FROM ADMINISTRATORACCESSGROUP AA ");
		selectAdminAccessGroupsNotForAdministratorSQL.append(" WHERE AA.ADMINISTRATORID = ^ ");
		selectAdminAccessGroupsNotForAdministratorSQL.append(" AND AA.ACTIVE = TRUE ");
		selectAdminAccessGroupsNotForAdministratorSQL.append(" AND A.ADMINACCESSGROUPID = AA.ADMINACCESSGROUPID ");
		selectAdminAccessGroupsNotForAdministratorSQL.append(") ");
		selectAdminAccessGroupsNotForAdministratorSQL.append("ORDER BY A.NAME ");
        // ORDER BY 
		selectAdminAccessGroupsSQL.append("ORDER BY A.NAME ");
		selectActiveAdminAccessGroupsSQL.append("ORDER BY A.NAME ");
	
	}

	public int insertAdminAccessGroup(AdminAccessGroup adminAccessGroup, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAdminAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		adminAccessGroup.setAdminAccessGroupId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "adminAccessGroup"));
		Utilities.replace(sql, adminAccessGroup.getAdminAccessGroupId());
		Utilities.replaceAndQuote(sql, adminAccessGroup.getName().trim());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateAdminAccessGroup(AdminAccessGroup adminAccessGroup, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAdminAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, adminAccessGroup.getName().trim());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, adminAccessGroup.getAdminAccessGroupId());
		Utilities.replace(sql, adminAccessGroup.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteAdminAccessGroup(Integer adminAccessGroupId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAdminAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, adminAccessGroupId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public AdminAccessGroup getAdminAccessGroup(Integer adminAccessGroupId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdminAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, adminAccessGroupId);
		return (AdminAccessGroup) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccessGroup.class.getName());
	}

	public AdminAccessGroup getAdminAccessGroupForName(String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdminAccessGroupForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (AdminAccessGroup) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccessGroup.class.getName());
	}

	public List<AdminAccessGroup> getAdminAccessGroups(boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveAdminAccessGroupsSQL.toString());
		}
		else {
			sql = new StringBuffer(selectAdminAccessGroupsSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccessGroup.class.getName());

	}

	public List<AdminAccessGroup> getAdminAccessGroupsNotForAdministrator(Integer administratorId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdminAccessGroupsNotForAdministratorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, administratorId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccessGroup.class.getName());

	}

	public AdminAccessGroupEntity getAdminAccessGroupEntity(Integer adminAccessGroupId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdminAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, adminAccessGroupId);
		return (AdminAccessGroupEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccessGroupEntity.class.getName());
	}


}
