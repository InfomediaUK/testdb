package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.MgrAccess;
import com.helmet.persistence.MgrAccessDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultMgrAccessDAO extends JdbcDaoSupport implements MgrAccessDAO {

	private static StringBuffer insertMgrAccessSQL;

	private static StringBuffer updateMgrAccessSQL;

	private static StringBuffer deleteMgrAccessSQL;

	private static StringBuffer selectMgrAccessSQL;

	private static StringBuffer selectMgrAccessForNameSQL;

	private static StringBuffer selectMgrAccessesSQL;

	private static StringBuffer selectActiveMgrAccessesSQL;

	private static StringBuffer selectMgrAccessesNotForManagerSQL;

	private static StringBuffer selectMgrAccessesNotForMgrAccessGroupSQL;

	private static StringBuffer selectActiveMgrAccessesForManagerSQL;

	public static void init() {
		// Get insert MgrAccess SQL.
		insertMgrAccessSQL = new StringBuffer();
		insertMgrAccessSQL.append("INSERT INTO MGRACCESS ");
		insertMgrAccessSQL.append("(  ");
		insertMgrAccessSQL.append("  MGRACCESSID, ");
		insertMgrAccessSQL.append("  NAME, ");
		insertMgrAccessSQL.append("  DESCRIPTION, ");
		insertMgrAccessSQL.append("  STARTSWITH, ");
		insertMgrAccessSQL.append("  GLOBAL, ");
		insertMgrAccessSQL.append("  CREATIONTIMESTAMP, ");
		insertMgrAccessSQL.append("  AUDITORID, ");
		insertMgrAccessSQL.append("  AUDITTIMESTAMP ");
		insertMgrAccessSQL.append(")  ");
		insertMgrAccessSQL.append("VALUES  ");
		insertMgrAccessSQL.append("(  ");
		insertMgrAccessSQL.append("  ^, ");
		insertMgrAccessSQL.append("  ^, ");
		insertMgrAccessSQL.append("  ^, ");
		insertMgrAccessSQL.append("  ^, ");
		insertMgrAccessSQL.append("  ^, ");
		insertMgrAccessSQL.append("  ^, ");
		insertMgrAccessSQL.append("  ^, ");
		insertMgrAccessSQL.append("  ^ ");
		insertMgrAccessSQL.append(")  ");
		// Get update MgrAccess SQL.
		updateMgrAccessSQL = new StringBuffer();
		updateMgrAccessSQL.append("UPDATE MGRACCESS ");
		updateMgrAccessSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateMgrAccessSQL.append("     NAME = ^, ");
		updateMgrAccessSQL.append("     DESCRIPTION = ^, ");
		updateMgrAccessSQL.append("     STARTSWITH = ^, ");
		updateMgrAccessSQL.append("     GLOBAL = ^, ");
		updateMgrAccessSQL.append("     AUDITORID = ^, ");
		updateMgrAccessSQL.append("     AUDITTIMESTAMP = ^ ");
		updateMgrAccessSQL.append("WHERE MGRACCESSID = ^ ");
		updateMgrAccessSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete MgrAccess SQL.
		deleteMgrAccessSQL = new StringBuffer();
		// deleteMgrAccessSQL = new StringBuffer();
		deleteMgrAccessSQL.append("UPDATE MGRACCESS ");
		deleteMgrAccessSQL.append("SET ACTIVE = FALSE, ");
		deleteMgrAccessSQL.append("    AUDITORID = ^, ");
		deleteMgrAccessSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteMgrAccessSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteMgrAccessSQL.append("WHERE MGRACCESSID = ^ ");
		deleteMgrAccessSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select MgrAccesses SQL.
		selectMgrAccessesSQL = new StringBuffer();
		selectMgrAccessesSQL.append("SELECT A.MGRACCESSID, ");
		selectMgrAccessesSQL.append("       A.NAME, ");
		selectMgrAccessesSQL.append("       A.DESCRIPTION, ");
		selectMgrAccessesSQL.append("       A.STARTSWITH, ");
		selectMgrAccessesSQL.append("       A.GLOBAL, ");
		selectMgrAccessesSQL.append("       A.CREATIONTIMESTAMP, ");
		selectMgrAccessesSQL.append("       A.AUDITORID, ");
		selectMgrAccessesSQL.append("       A.AUDITTIMESTAMP, ");
		selectMgrAccessesSQL.append("       A.ACTIVE, ");
		selectMgrAccessesSQL.append("       A.NOOFCHANGES ");
		selectMgrAccessesSQL.append("FROM MGRACCESS A ");
		// Get select Active MgrAccesses SQL.
		selectActiveMgrAccessesSQL = new StringBuffer(selectMgrAccessesSQL);
		selectActiveMgrAccessesSQL.append("WHERE A.ACTIVE = TRUE ");
		// Get select MgrAccess SQL.
		selectMgrAccessSQL = new StringBuffer(selectMgrAccessesSQL);
		selectMgrAccessSQL.append("WHERE A.MGRACCESSID = ^ ");
		// Get select MgrAccess for Name SQL.
		selectMgrAccessForNameSQL = new StringBuffer(selectActiveMgrAccessesSQL);
		selectMgrAccessForNameSQL.append("AND A.NAME = ^ ");
		// Get select MgrAccesses NOT for Manager SQL.
		selectMgrAccessesNotForManagerSQL = new StringBuffer(selectMgrAccessesSQL);
		selectMgrAccessesNotForManagerSQL.append("WHERE A.ACTIVE = TRUE ");
		selectMgrAccessesNotForManagerSQL.append("AND   A.GLOBAL = FALSE ");
		selectMgrAccessesNotForManagerSQL.append("AND NOT EXISTS ");
		selectMgrAccessesNotForManagerSQL.append("( ");
		selectMgrAccessesNotForManagerSQL.append(" SELECT NULL ");
		selectMgrAccessesNotForManagerSQL.append(" FROM MANAGERACCESS AA ");
		selectMgrAccessesNotForManagerSQL.append(" WHERE AA.MANAGERID = ^ ");
		selectMgrAccessesNotForManagerSQL.append(" AND AA.ACTIVE = TRUE ");
		selectMgrAccessesNotForManagerSQL.append(" AND A.MGRACCESSID = AA.MGRACCESSID ");
		selectMgrAccessesNotForManagerSQL.append(") ");
		selectMgrAccessesNotForManagerSQL.append("ORDER BY A.NAME ");
		// Get select MgrAccesses NOT for MgrAccessGroup SQL.
		selectMgrAccessesNotForMgrAccessGroupSQL = new StringBuffer(selectMgrAccessesSQL);
		selectMgrAccessesNotForMgrAccessGroupSQL.append("WHERE A.ACTIVE = TRUE ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append("AND   A.GLOBAL = FALSE ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append("AND NOT EXISTS ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append("( ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append(" SELECT NULL ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append(" FROM MGRACCESSGROUPITEM AA ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append(" WHERE AA.MGRACCESSGROUPID = ^ ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append(" AND AA.ACTIVE = TRUE ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append(" AND A.MGRACCESSID = AA.MGRACCESSID ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append(") ");
		selectMgrAccessesNotForMgrAccessGroupSQL.append("ORDER BY A.NAME ");
		// Get select Active MgrAccessesForManager SQL.
		selectActiveMgrAccessesForManagerSQL = new StringBuffer(selectMgrAccessesSQL);
		selectActiveMgrAccessesForManagerSQL.append(", ");
		selectActiveMgrAccessesForManagerSQL.append("    MANAGERACCESS AA ");
		selectActiveMgrAccessesForManagerSQL.append("WHERE AA.MGRACCESSID = A.MGRACCESSID ");
		selectActiveMgrAccessesForManagerSQL.append("AND AA.MANAGERID = ^ ");
		selectActiveMgrAccessesForManagerSQL.append("AND A.ACTIVE = TRUE ");
		selectActiveMgrAccessesForManagerSQL.append("AND AA.ACTIVE = TRUE ");
		selectActiveMgrAccessesForManagerSQL.append("UNION ");
		selectActiveMgrAccessesForManagerSQL.append(selectMgrAccessesSQL);
		selectActiveMgrAccessesForManagerSQL.append(", ");
        selectActiveMgrAccessesForManagerSQL.append("MGRACCESSGROUPITEM AAGI, ");
		selectActiveMgrAccessesForManagerSQL.append("MANAGERACCESSGROUP AAG ");
		selectActiveMgrAccessesForManagerSQL.append("WHERE AAGI.MGRACCESSID = A.MGRACCESSID ");
		selectActiveMgrAccessesForManagerSQL.append("AND AAG.MGRACCESSGROUPID = AAGI.MGRACCESSGROUPID ");
		selectActiveMgrAccessesForManagerSQL.append("AND AAG.MANAGERID = ^ ");
		selectActiveMgrAccessesForManagerSQL.append("AND A.ACTIVE = TRUE ");
		selectActiveMgrAccessesForManagerSQL.append("AND AAGI.ACTIVE = TRUE ");
		selectActiveMgrAccessesForManagerSQL.append("AND AAG.ACTIVE = TRUE ");
		selectActiveMgrAccessesForManagerSQL.append("UNION ");
		selectActiveMgrAccessesForManagerSQL.append(selectMgrAccessesSQL);
		selectActiveMgrAccessesForManagerSQL.append("WHERE A.ACTIVE = TRUE ");
		selectActiveMgrAccessesForManagerSQL.append("AND A.GLOBAL = TRUE ");
		// ORDER BY 
		selectMgrAccessesSQL.append("ORDER BY A.NAME ");
		selectActiveMgrAccessesSQL.append("ORDER BY A.GLOBAL, A.NAME ");
	}

	public int insertMgrAccess(MgrAccess mgrAccess, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertMgrAccessSQL.toString());
		// Replace the parameters with supplied values.
		mgrAccess.setMgrAccessId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "mgrAccess"));
		Utilities.replace(sql, mgrAccess.getMgrAccessId());
		Utilities.replaceAndQuote(sql, mgrAccess.getName());
		Utilities.replaceAndQuoteNullable(sql, mgrAccess.getDescription());
		Utilities.replace(sql, mgrAccess.getStartsWith());
		Utilities.replace(sql, mgrAccess.getGlobal());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateMgrAccess(MgrAccess mgrAccess, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateMgrAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, mgrAccess.getName());
		Utilities.replaceAndQuoteNullable(sql, mgrAccess.getDescription());
		Utilities.replace(sql, mgrAccess.getStartsWith());
		Utilities.replace(sql, mgrAccess.getGlobal());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, mgrAccess.getMgrAccessId());
		Utilities.replace(sql, mgrAccess.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteMgrAccess(Integer mgrAccessId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteMgrAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, mgrAccessId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public MgrAccess getMgrAccess(Integer mgrAccessId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectMgrAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, mgrAccessId);
		return (MgrAccess) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccess.class.getName());
	}

	public MgrAccess getMgrAccessForName(String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectMgrAccessForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (MgrAccess) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccess.class.getName());
	}

	public List<MgrAccess> getMgrAccesses(boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveMgrAccessesSQL.toString());
		}
		else {
			sql = new StringBuffer(selectMgrAccessesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccess.class.getName());

	}

	public List<MgrAccess> getMgrAccessesNotForManager(Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectMgrAccessesNotForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccess.class.getName());

	}

	public List<MgrAccess> getMgrAccessesNotForMgrAccessGroup(Integer mgrAccessGroupId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectMgrAccessesNotForMgrAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, mgrAccessGroupId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccess.class.getName());

	}

	public List<MgrAccess> getActiveMgrAccessesForManager(Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveMgrAccessesForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccess.class.getName());
	}

}
