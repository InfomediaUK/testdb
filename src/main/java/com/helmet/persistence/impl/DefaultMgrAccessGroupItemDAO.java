package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.MgrAccessGroupItem;
import com.helmet.bean.MgrAccessGroupItemUser;
import com.helmet.persistence.MgrAccessGroupItemDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultMgrAccessGroupItemDAO extends JdbcDaoSupport implements MgrAccessGroupItemDAO {

	private static StringBuffer insertMgrAccessGroupItemSQL;

	private static StringBuffer deleteMgrAccessGroupItemSQL;

	private static StringBuffer selectMgrAccessGroupItemUsersSQL;

	private static StringBuffer selectMgrAccessGroupItemUsersForMgrAccessGroupSQL;

	public static void init() {
		// Get insert MgrAccessGroupItem SQL.
		insertMgrAccessGroupItemSQL = new StringBuffer();
		insertMgrAccessGroupItemSQL.append("INSERT INTO MGRACCESSGROUPITEM ");
		insertMgrAccessGroupItemSQL.append("(  ");
		insertMgrAccessGroupItemSQL.append("  MGRACCESSGROUPITEMID, ");
		insertMgrAccessGroupItemSQL.append("  MGRACCESSGROUPID, ");
		insertMgrAccessGroupItemSQL.append("  MGRACCESSID, ");
		insertMgrAccessGroupItemSQL.append("  CREATIONTIMESTAMP, ");
		insertMgrAccessGroupItemSQL.append("  AUDITORID, ");
		insertMgrAccessGroupItemSQL.append("  AUDITTIMESTAMP ");
		insertMgrAccessGroupItemSQL.append(")  ");
		insertMgrAccessGroupItemSQL.append("VALUES  ");
		insertMgrAccessGroupItemSQL.append("(  ");
		insertMgrAccessGroupItemSQL.append("  ^, ");
		insertMgrAccessGroupItemSQL.append("  ^, ");
		insertMgrAccessGroupItemSQL.append("  ^, ");
		insertMgrAccessGroupItemSQL.append("  ^, ");
		insertMgrAccessGroupItemSQL.append("  ^, ");
		insertMgrAccessGroupItemSQL.append("  ^ ");
		insertMgrAccessGroupItemSQL.append(")  ");
		// Get delete MgrAccessGroupItem SQL.
		deleteMgrAccessGroupItemSQL = new StringBuffer();
		deleteMgrAccessGroupItemSQL.append("UPDATE MGRACCESSGROUPITEM ");
		deleteMgrAccessGroupItemSQL.append("SET ACTIVE = FALSE, ");
		deleteMgrAccessGroupItemSQL.append("    AUDITORID = ^, ");
		deleteMgrAccessGroupItemSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteMgrAccessGroupItemSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteMgrAccessGroupItemSQL.append("WHERE MGRACCESSGROUPITEMID = ^ ");
		deleteMgrAccessGroupItemSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select MgrAccessGroupItemUsers SQL.
		selectMgrAccessGroupItemUsersSQL = new StringBuffer();
		selectMgrAccessGroupItemUsersSQL.append("SELECT A.MGRACCESSGROUPITEMID, ");
		selectMgrAccessGroupItemUsersSQL.append("       A.MGRACCESSGROUPID, ");
		selectMgrAccessGroupItemUsersSQL.append("       A.MGRACCESSID, ");
		selectMgrAccessGroupItemUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectMgrAccessGroupItemUsersSQL.append("       A.AUDITORID, ");
		selectMgrAccessGroupItemUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectMgrAccessGroupItemUsersSQL.append("       A.ACTIVE, ");
		selectMgrAccessGroupItemUsersSQL.append("       A.NOOFCHANGES, ");
		selectMgrAccessGroupItemUsersSQL.append("       B.NAME AS MGRACCESSNAME ");
		selectMgrAccessGroupItemUsersSQL.append("FROM MGRACCESSGROUPITEM A, ");
		selectMgrAccessGroupItemUsersSQL.append("     MGRACCESS B ");
		selectMgrAccessGroupItemUsersSQL.append("WHERE B.MGRACCESSID = A.MGRACCESSID ");
		// Get select MgrAccessGroupItemUsers for Manager SQL.
		selectMgrAccessGroupItemUsersForMgrAccessGroupSQL = new StringBuffer(selectMgrAccessGroupItemUsersSQL);
		selectMgrAccessGroupItemUsersForMgrAccessGroupSQL.append("AND A.MGRACCESSGROUPID = ^ ");
		selectMgrAccessGroupItemUsersForMgrAccessGroupSQL.append("AND A.ACTIVE = TRUE ");
		selectMgrAccessGroupItemUsersForMgrAccessGroupSQL.append("ORDER BY B.NAME ");
	}

	public int insertMgrAccessGroupItem(MgrAccessGroupItem mgrAccessGroupItem, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertMgrAccessGroupItemSQL.toString());
		// Replace the parameters with supplied values.
		mgrAccessGroupItem.setMgrAccessGroupItemId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "mgrAccessGroupItem"));
		Utilities.replace(sql, mgrAccessGroupItem.getMgrAccessGroupItemId());
		Utilities.replace(sql, mgrAccessGroupItem.getMgrAccessGroupId());
		Utilities.replace(sql, mgrAccessGroupItem.getMgrAccessId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteMgrAccessGroupItem(Integer mgrAccessGroupItemId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteMgrAccessGroupItemSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, mgrAccessGroupItemId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<MgrAccessGroupItemUser> getMgrAccessGroupItemUsersForMgrAccessGroup(Integer mgrAccessGroupId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectMgrAccessGroupItemUsersForMgrAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, mgrAccessGroupId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccessGroupItemUser.class.getName());

	}

}
