package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ManagerAccessGroup;
import com.helmet.bean.ManagerAccessGroupUser;
import com.helmet.persistence.ManagerAccessGroupDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultManagerAccessGroupDAO extends JdbcDaoSupport implements ManagerAccessGroupDAO {

	private static StringBuffer insertManagerAccessGroupSQL;

	private static StringBuffer deleteManagerAccessGroupSQL;

	private static StringBuffer selectManagerAccessGroupUsersSQL;

	private static StringBuffer selectManagerAccessGroupUsersForManagerSQL;

	public static void init() {
		// Get insert ManagerAccessGroup SQL.
		insertManagerAccessGroupSQL = new StringBuffer();
		insertManagerAccessGroupSQL.append("INSERT INTO MANAGERACCESSGROUP ");
		insertManagerAccessGroupSQL.append("(  ");
		insertManagerAccessGroupSQL.append("  MANAGERACCESSGROUPID, ");
		insertManagerAccessGroupSQL.append("  MANAGERID, ");
		insertManagerAccessGroupSQL.append("  MGRACCESSGROUPID, ");
		insertManagerAccessGroupSQL.append("  CREATIONTIMESTAMP, ");
		insertManagerAccessGroupSQL.append("  AUDITORID, ");
		insertManagerAccessGroupSQL.append("  AUDITTIMESTAMP ");
		insertManagerAccessGroupSQL.append(")  ");
		insertManagerAccessGroupSQL.append("VALUES  ");
		insertManagerAccessGroupSQL.append("(  ");
		insertManagerAccessGroupSQL.append("  ^, ");
		insertManagerAccessGroupSQL.append("  ^, ");
		insertManagerAccessGroupSQL.append("  ^, ");
		insertManagerAccessGroupSQL.append("  ^, ");
		insertManagerAccessGroupSQL.append("  ^, ");
		insertManagerAccessGroupSQL.append("  ^ ");
		insertManagerAccessGroupSQL.append(")  ");
		// Get delete ManagerAccessGroup SQL.
		deleteManagerAccessGroupSQL = new StringBuffer();
		// deleteManagerAccessGroupSQL = new StringBuffer();
		deleteManagerAccessGroupSQL.append("UPDATE MANAGERACCESSGROUP ");
		deleteManagerAccessGroupSQL.append("SET ACTIVE = FALSE, ");
		deleteManagerAccessGroupSQL.append("    AUDITORID = ^, ");
		deleteManagerAccessGroupSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteManagerAccessGroupSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteManagerAccessGroupSQL.append("WHERE MANAGERACCESSGROUPID = ^ ");
		deleteManagerAccessGroupSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ManagerAccessGroupUsers SQL.
		selectManagerAccessGroupUsersSQL = new StringBuffer();
		selectManagerAccessGroupUsersSQL.append("SELECT A.MANAGERACCESSGROUPID, ");
		selectManagerAccessGroupUsersSQL.append("       A.MANAGERID, ");
		selectManagerAccessGroupUsersSQL.append("       A.MGRACCESSGROUPID, ");
		selectManagerAccessGroupUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectManagerAccessGroupUsersSQL.append("       A.AUDITORID, ");
		selectManagerAccessGroupUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectManagerAccessGroupUsersSQL.append("       A.ACTIVE, ");
		selectManagerAccessGroupUsersSQL.append("       A.NOOFCHANGES, ");
		selectManagerAccessGroupUsersSQL.append("       B.NAME AS MGRACCESSGROUPNAME ");
		selectManagerAccessGroupUsersSQL.append("FROM MANAGERACCESSGROUP A, ");
		selectManagerAccessGroupUsersSQL.append("     MGRACCESSGROUP B ");
		selectManagerAccessGroupUsersSQL.append("WHERE B.MGRACCESSGROUPID = A.MGRACCESSGROUPID ");
		// Get select ManagerAccessGroupUsers for Manager SQL.
		selectManagerAccessGroupUsersForManagerSQL = new StringBuffer(selectManagerAccessGroupUsersSQL);
		selectManagerAccessGroupUsersForManagerSQL.append("AND A.MANAGERID = ^ ");
		selectManagerAccessGroupUsersForManagerSQL.append("AND A.ACTIVE = TRUE ");
		selectManagerAccessGroupUsersForManagerSQL.append("ORDER BY B.NAME ");
	}

	public int insertManagerAccessGroup(ManagerAccessGroup managerAccessGroup, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertManagerAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		managerAccessGroup.setManagerAccessGroupId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "managerAccessGroup"));
		Utilities.replace(sql, managerAccessGroup.getManagerAccessGroupId());
		Utilities.replace(sql, managerAccessGroup.getManagerId());
		Utilities.replace(sql, managerAccessGroup.getMgrAccessGroupId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteManagerAccessGroup(Integer managerAccessGroupId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteManagerAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, managerAccessGroupId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<ManagerAccessGroupUser> getManagerAccessGroupUsersForManager(Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectManagerAccessGroupUsersForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ManagerAccessGroupUser.class.getName());

	}

}
