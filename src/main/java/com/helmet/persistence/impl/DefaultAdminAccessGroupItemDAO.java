package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AdminAccessGroupItem;
import com.helmet.bean.AdminAccessGroupItemUser;
import com.helmet.persistence.AdminAccessGroupItemDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAdminAccessGroupItemDAO extends JdbcDaoSupport implements AdminAccessGroupItemDAO {

	private static StringBuffer insertAdminAccessGroupItemSQL;

	private static StringBuffer deleteAdminAccessGroupItemSQL;

	private static StringBuffer selectAdminAccessGroupItemUsersSQL;

	private static StringBuffer selectAdminAccessGroupItemUsersForAdminAccessGroupSQL;

	public static void init() {
		// Get insert AdminAccessGroupItem SQL.
		insertAdminAccessGroupItemSQL = new StringBuffer();
		insertAdminAccessGroupItemSQL.append("INSERT INTO ADMINACCESSGROUPITEM ");
		insertAdminAccessGroupItemSQL.append("(  ");
		insertAdminAccessGroupItemSQL.append("  ADMINACCESSGROUPITEMID, ");
		insertAdminAccessGroupItemSQL.append("  ADMINACCESSGROUPID, ");
		insertAdminAccessGroupItemSQL.append("  ADMINACCESSID, ");
		insertAdminAccessGroupItemSQL.append("  CREATIONTIMESTAMP, ");
		insertAdminAccessGroupItemSQL.append("  AUDITORID, ");
		insertAdminAccessGroupItemSQL.append("  AUDITTIMESTAMP ");
		insertAdminAccessGroupItemSQL.append(")  ");
		insertAdminAccessGroupItemSQL.append("VALUES  ");
		insertAdminAccessGroupItemSQL.append("(  ");
		insertAdminAccessGroupItemSQL.append("  ^, ");
		insertAdminAccessGroupItemSQL.append("  ^, ");
		insertAdminAccessGroupItemSQL.append("  ^, ");
		insertAdminAccessGroupItemSQL.append("  ^, ");
		insertAdminAccessGroupItemSQL.append("  ^, ");
		insertAdminAccessGroupItemSQL.append("  ^ ");
		insertAdminAccessGroupItemSQL.append(")  ");
		// Get delete AdminAccessGroupItem SQL.
		deleteAdminAccessGroupItemSQL = new StringBuffer();
		deleteAdminAccessGroupItemSQL.append("UPDATE ADMINACCESSGROUPITEM ");
		deleteAdminAccessGroupItemSQL.append("SET ACTIVE = FALSE, ");
		deleteAdminAccessGroupItemSQL.append("    AUDITORID = ^, ");
		deleteAdminAccessGroupItemSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAdminAccessGroupItemSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAdminAccessGroupItemSQL.append("WHERE ADMINACCESSGROUPITEMID = ^ ");
		deleteAdminAccessGroupItemSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AdminAccessGroupItemUsers SQL.
		selectAdminAccessGroupItemUsersSQL = new StringBuffer();
		selectAdminAccessGroupItemUsersSQL.append("SELECT A.ADMINACCESSGROUPITEMID, ");
		selectAdminAccessGroupItemUsersSQL.append("       A.ADMINACCESSGROUPID, ");
		selectAdminAccessGroupItemUsersSQL.append("       A.ADMINACCESSID, ");
		selectAdminAccessGroupItemUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectAdminAccessGroupItemUsersSQL.append("       A.AUDITORID, ");
		selectAdminAccessGroupItemUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectAdminAccessGroupItemUsersSQL.append("       A.ACTIVE, ");
		selectAdminAccessGroupItemUsersSQL.append("       A.NOOFCHANGES, ");
		selectAdminAccessGroupItemUsersSQL.append("       B.NAME AS ADMINACCESSNAME ");
		selectAdminAccessGroupItemUsersSQL.append("FROM ADMINACCESSGROUPITEM A, ");
		selectAdminAccessGroupItemUsersSQL.append("     ADMINACCESS B ");
		selectAdminAccessGroupItemUsersSQL.append("WHERE B.ADMINACCESSID = A.ADMINACCESSID ");
		// Get select AdminAccessGroupItemUsers for Administrator SQL.
		selectAdminAccessGroupItemUsersForAdminAccessGroupSQL = new StringBuffer(selectAdminAccessGroupItemUsersSQL);
		selectAdminAccessGroupItemUsersForAdminAccessGroupSQL.append("AND A.ADMINACCESSGROUPID = ^ ");
		selectAdminAccessGroupItemUsersForAdminAccessGroupSQL.append("AND A.ACTIVE = TRUE ");
		selectAdminAccessGroupItemUsersForAdminAccessGroupSQL.append("ORDER BY B.NAME ");
	}

	public int insertAdminAccessGroupItem(AdminAccessGroupItem adminAccessGroupItem, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAdminAccessGroupItemSQL.toString());
		// Replace the parameters with supplied values.
		adminAccessGroupItem.setAdminAccessGroupItemId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "adminAccessGroupItem"));
		Utilities.replace(sql, adminAccessGroupItem.getAdminAccessGroupItemId());
		Utilities.replace(sql, adminAccessGroupItem.getAdminAccessGroupId());
		Utilities.replace(sql, adminAccessGroupItem.getAdminAccessId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteAdminAccessGroupItem(Integer adminAccessGroupItemId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAdminAccessGroupItemSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, adminAccessGroupItemId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<AdminAccessGroupItemUser> getAdminAccessGroupItemUsersForAdminAccessGroup(Integer adminAccessGroupId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAdminAccessGroupItemUsersForAdminAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, adminAccessGroupId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AdminAccessGroupItemUser.class.getName());

	}

}
