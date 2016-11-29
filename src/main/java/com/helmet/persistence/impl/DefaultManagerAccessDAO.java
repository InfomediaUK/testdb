package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ManagerAccess;
import com.helmet.bean.ManagerAccessUser;
import com.helmet.persistence.ManagerAccessDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultManagerAccessDAO extends JdbcDaoSupport implements ManagerAccessDAO {

	private static StringBuffer insertManagerAccessSQL;

	private static StringBuffer deleteManagerAccessSQL;

	private static StringBuffer selectManagerAccessUsersSQL;

	private static StringBuffer selectManagerAccessUsersForManagerSQL;

	public static void init() {
		// Get insert ManagerAccess SQL.
		insertManagerAccessSQL = new StringBuffer();
		insertManagerAccessSQL.append("INSERT INTO MANAGERACCESS ");
		insertManagerAccessSQL.append("(  ");
		insertManagerAccessSQL.append("  MANAGERACCESSID, ");
		insertManagerAccessSQL.append("  MANAGERID, ");
		insertManagerAccessSQL.append("  MGRACCESSID, ");
		insertManagerAccessSQL.append("  CREATIONTIMESTAMP, ");
		insertManagerAccessSQL.append("  AUDITORID, ");
		insertManagerAccessSQL.append("  AUDITTIMESTAMP ");
		insertManagerAccessSQL.append(")  ");
		insertManagerAccessSQL.append("VALUES  ");
		insertManagerAccessSQL.append("(  ");
		insertManagerAccessSQL.append("  ^, ");
		insertManagerAccessSQL.append("  ^, ");
		insertManagerAccessSQL.append("  ^, ");
		insertManagerAccessSQL.append("  ^, ");
		insertManagerAccessSQL.append("  ^, ");
		insertManagerAccessSQL.append("  ^ ");
		insertManagerAccessSQL.append(")  ");
		// Get delete ManagerAccess SQL.
		deleteManagerAccessSQL = new StringBuffer();
		// deleteManagerAccessSQL = new StringBuffer();
		deleteManagerAccessSQL.append("UPDATE MANAGERACCESS ");
		deleteManagerAccessSQL.append("SET ACTIVE = FALSE, ");
		deleteManagerAccessSQL.append("    AUDITORID = ^, ");
		deleteManagerAccessSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteManagerAccessSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteManagerAccessSQL.append("WHERE MANAGERACCESSID = ^ ");
		deleteManagerAccessSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ManagerAccessUsers SQL.
		selectManagerAccessUsersSQL = new StringBuffer();
		selectManagerAccessUsersSQL.append("SELECT A.MANAGERACCESSID, ");
		selectManagerAccessUsersSQL.append("       A.MANAGERID, ");
		selectManagerAccessUsersSQL.append("       A.MGRACCESSID, ");
		selectManagerAccessUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectManagerAccessUsersSQL.append("       A.AUDITORID, ");
		selectManagerAccessUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectManagerAccessUsersSQL.append("       A.ACTIVE, ");
		selectManagerAccessUsersSQL.append("       A.NOOFCHANGES, ");
		selectManagerAccessUsersSQL.append("       B.NAME AS MGRACCESSNAME ");
		selectManagerAccessUsersSQL.append("FROM MANAGERACCESS A, ");
		selectManagerAccessUsersSQL.append("     MGRACCESS B ");
		selectManagerAccessUsersSQL.append("WHERE B.MGRACCESSID = A.MGRACCESSID ");
		// Get select ManagerAccessUsers for Manager SQL.
		selectManagerAccessUsersForManagerSQL = new StringBuffer(selectManagerAccessUsersSQL);
		selectManagerAccessUsersForManagerSQL.append("AND A.MANAGERID = ^ ");
		selectManagerAccessUsersForManagerSQL.append("AND A.ACTIVE = TRUE ");
		selectManagerAccessUsersForManagerSQL.append("ORDER BY B.NAME ");
	}

	public int insertManagerAccess(ManagerAccess managerAccess, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertManagerAccessSQL.toString());
		// Replace the parameters with supplied values.
		managerAccess.setManagerAccessId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "managerAccess"));
		Utilities.replace(sql, managerAccess.getManagerAccessId());
		Utilities.replace(sql, managerAccess.getManagerId());
		Utilities.replace(sql, managerAccess.getMgrAccessId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteManagerAccess(Integer managerAccessId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteManagerAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, managerAccessId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<ManagerAccessUser> getManagerAccessUsersForManager(Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectManagerAccessUsersForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ManagerAccessUser.class.getName());

	}

}
