package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.MgrAccessGroup;
import com.helmet.bean.MgrAccessGroupEntity;
import com.helmet.persistence.MgrAccessGroupDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultMgrAccessGroupDAO extends JdbcDaoSupport implements MgrAccessGroupDAO {

	private static StringBuffer insertMgrAccessGroupSQL;

	private static StringBuffer updateMgrAccessGroupSQL;

	private static StringBuffer deleteMgrAccessGroupSQL;

	private static StringBuffer selectMgrAccessGroupSQL;

	private static StringBuffer selectMgrAccessGroupForNameSQL;

	private static StringBuffer selectMgrAccessGroupsSQL;

	private static StringBuffer selectMgrAccessGroupsForClientSQL;

	private static StringBuffer selectActiveMgrAccessGroupsForClientSQL;

	private static StringBuffer selectMgrAccessGroupsNotForManagerSQL;

	public static void init() {
		// Get insert MgrAccessGroup SQL.
		insertMgrAccessGroupSQL = new StringBuffer();
		insertMgrAccessGroupSQL.append("INSERT INTO MGRACCESSGROUP ");
		insertMgrAccessGroupSQL.append("(  ");
		insertMgrAccessGroupSQL.append("  MGRACCESSGROUPID, ");
		insertMgrAccessGroupSQL.append("  CLIENTID, ");
		insertMgrAccessGroupSQL.append("  NAME, ");
		insertMgrAccessGroupSQL.append("  CREATIONTIMESTAMP, ");
		insertMgrAccessGroupSQL.append("  AUDITORID, ");
		insertMgrAccessGroupSQL.append("  AUDITTIMESTAMP ");
		insertMgrAccessGroupSQL.append(")  ");
		insertMgrAccessGroupSQL.append("VALUES  ");
		insertMgrAccessGroupSQL.append("(  ");
		insertMgrAccessGroupSQL.append("  ^, ");
		insertMgrAccessGroupSQL.append("  ^, ");
		insertMgrAccessGroupSQL.append("  ^, ");
		insertMgrAccessGroupSQL.append("  ^, ");
		insertMgrAccessGroupSQL.append("  ^, ");
		insertMgrAccessGroupSQL.append("  ^ ");
		insertMgrAccessGroupSQL.append(")  ");
		// Get update MgrAccessGroup SQL.
		updateMgrAccessGroupSQL = new StringBuffer();
		updateMgrAccessGroupSQL.append("UPDATE MGRACCESSGROUP ");
		updateMgrAccessGroupSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateMgrAccessGroupSQL.append("     CLIENTID = ^, ");
		updateMgrAccessGroupSQL.append("     NAME = ^, ");
		updateMgrAccessGroupSQL.append("     AUDITORID = ^, ");
		updateMgrAccessGroupSQL.append("     AUDITTIMESTAMP = ^ ");
		updateMgrAccessGroupSQL.append("WHERE MGRACCESSGROUPID = ^ ");
		updateMgrAccessGroupSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete MgrAccessGroup SQL.
		deleteMgrAccessGroupSQL = new StringBuffer();
		// deleteMgrAccessGroupSQL = new StringBuffer();
		deleteMgrAccessGroupSQL.append("UPDATE MGRACCESSGROUP ");
		deleteMgrAccessGroupSQL.append("SET ACTIVE = FALSE, ");
		deleteMgrAccessGroupSQL.append("    AUDITORID = ^, ");
		deleteMgrAccessGroupSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteMgrAccessGroupSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteMgrAccessGroupSQL.append("WHERE MGRACCESSGROUPID = ^ ");
		deleteMgrAccessGroupSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select MgrAccessGroups SQL.
		selectMgrAccessGroupsSQL = new StringBuffer();
		selectMgrAccessGroupsSQL.append("SELECT A.MGRACCESSGROUPID, ");
		selectMgrAccessGroupsSQL.append("       A.CLIENTID, ");
		selectMgrAccessGroupsSQL.append("       A.NAME, ");
		selectMgrAccessGroupsSQL.append("       A.CREATIONTIMESTAMP, ");
		selectMgrAccessGroupsSQL.append("       A.AUDITORID, ");
		selectMgrAccessGroupsSQL.append("       A.AUDITTIMESTAMP, ");
		selectMgrAccessGroupsSQL.append("       A.ACTIVE, ");
		selectMgrAccessGroupsSQL.append("       A.NOOFCHANGES ");
		selectMgrAccessGroupsSQL.append("FROM MGRACCESSGROUP A ");
		// Get select MgrAccessGroups for Client SQL.
		selectMgrAccessGroupsForClientSQL = new StringBuffer(selectMgrAccessGroupsSQL);
		selectMgrAccessGroupsForClientSQL.append("WHERE A.CLIENTID = ^ ");
		// Get select Active MgrAccessGroups SQL.
		selectActiveMgrAccessGroupsForClientSQL = new StringBuffer(selectMgrAccessGroupsForClientSQL);
		selectActiveMgrAccessGroupsForClientSQL.append("AND A.ACTIVE = TRUE ");
		// Get select MgrAccessGroup SQL.
		selectMgrAccessGroupSQL = new StringBuffer(selectMgrAccessGroupsSQL);
		selectMgrAccessGroupSQL.append("WHERE A.MGRACCESSGROUPID = ^ ");
		// Get select MgrAccessGroup for Name SQL.
		selectMgrAccessGroupForNameSQL = new StringBuffer(selectActiveMgrAccessGroupsForClientSQL);
		selectMgrAccessGroupForNameSQL.append("AND A.NAME = ^ ");
		// Get select MgrAccessGroups NOT for Manager SQL.
		selectMgrAccessGroupsNotForManagerSQL = new StringBuffer(selectActiveMgrAccessGroupsForClientSQL);
		selectMgrAccessGroupsNotForManagerSQL.append("AND NOT EXISTS ");
		selectMgrAccessGroupsNotForManagerSQL.append("( ");
		selectMgrAccessGroupsNotForManagerSQL.append(" SELECT NULL ");
		selectMgrAccessGroupsNotForManagerSQL.append(" FROM MANAGERACCESSGROUP AA ");
		selectMgrAccessGroupsNotForManagerSQL.append(" WHERE AA.MANAGERID = ^ ");
		selectMgrAccessGroupsNotForManagerSQL.append(" AND AA.ACTIVE = TRUE ");
		selectMgrAccessGroupsNotForManagerSQL.append(" AND A.MGRACCESSGROUPID = AA.MGRACCESSGROUPID ");
		selectMgrAccessGroupsNotForManagerSQL.append(") ");
		selectMgrAccessGroupsNotForManagerSQL.append("ORDER BY A.NAME ");
        // ORDER BY 
		selectMgrAccessGroupsForClientSQL.append("ORDER BY A.NAME ");
		selectActiveMgrAccessGroupsForClientSQL.append("ORDER BY A.NAME ");
	
	}

	public int insertMgrAccessGroup(MgrAccessGroup mgrAccessGroup, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertMgrAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		mgrAccessGroup.setMgrAccessGroupId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "mgrAccessGroup"));
		Utilities.replace(sql, mgrAccessGroup.getMgrAccessGroupId());
		Utilities.replace(sql, mgrAccessGroup.getClientId());
		Utilities.replaceAndQuote(sql, mgrAccessGroup.getName());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateMgrAccessGroup(MgrAccessGroup mgrAccessGroup, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateMgrAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, mgrAccessGroup.getClientId());
		Utilities.replaceAndQuote(sql, mgrAccessGroup.getName());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, mgrAccessGroup.getMgrAccessGroupId());
		Utilities.replace(sql, mgrAccessGroup.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteMgrAccessGroup(Integer mgrAccessGroupId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteMgrAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, mgrAccessGroupId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public MgrAccessGroup getMgrAccessGroup(Integer mgrAccessGroupId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectMgrAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, mgrAccessGroupId);
		return (MgrAccessGroup) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccessGroup.class.getName());
	}

	public MgrAccessGroup getMgrAccessGroupForName(Integer clientId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectMgrAccessGroupForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, name);
		return (MgrAccessGroup) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccessGroup.class.getName());
	}

	public List<MgrAccessGroup> getMgrAccessGroupsForClient(Integer clientId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveMgrAccessGroupsForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectMgrAccessGroupsForClientSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccessGroup.class.getName());

	}

	public List<MgrAccessGroup> getMgrAccessGroupsNotForManager(Integer clientId, Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectMgrAccessGroupsNotForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccessGroup.class.getName());

	}

	public MgrAccessGroupEntity getMgrAccessGroupEntity(Integer mgrAccessGroupId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectMgrAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, mgrAccessGroupId);
		return (MgrAccessGroupEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), MgrAccessGroupEntity.class.getName());
	}


}
