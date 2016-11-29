package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AgyAccessGroup;
import com.helmet.bean.AgyAccessGroupEntity;
import com.helmet.persistence.AgyAccessGroupDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAgyAccessGroupDAO extends JdbcDaoSupport implements AgyAccessGroupDAO {

	private static StringBuffer insertAgyAccessGroupSQL;

	private static StringBuffer updateAgyAccessGroupSQL;

	private static StringBuffer deleteAgyAccessGroupSQL;

	private static StringBuffer selectAgyAccessGroupSQL;

	private static StringBuffer selectAgyAccessGroupForNameSQL;

	private static StringBuffer selectAgyAccessGroupsSQL;

	private static StringBuffer selectAgyAccessGroupsForAgencySQL;

	private static StringBuffer selectActiveAgyAccessGroupsForAgencySQL;

	private static StringBuffer selectAgyAccessGroupsNotForConsultantSQL;

	public static void init() {
		// Get insert AgyAccessGroup SQL.
		insertAgyAccessGroupSQL = new StringBuffer();
		insertAgyAccessGroupSQL.append("INSERT INTO AGYACCESSGROUP ");
		insertAgyAccessGroupSQL.append("(  ");
		insertAgyAccessGroupSQL.append("  AGYACCESSGROUPID, ");
		insertAgyAccessGroupSQL.append("  AGENCYID, ");
		insertAgyAccessGroupSQL.append("  NAME, ");
		insertAgyAccessGroupSQL.append("  CREATIONTIMESTAMP, ");
		insertAgyAccessGroupSQL.append("  AUDITORID, ");
		insertAgyAccessGroupSQL.append("  AUDITTIMESTAMP ");
		insertAgyAccessGroupSQL.append(")  ");
		insertAgyAccessGroupSQL.append("VALUES  ");
		insertAgyAccessGroupSQL.append("(  ");
		insertAgyAccessGroupSQL.append("  ^, ");
		insertAgyAccessGroupSQL.append("  ^, ");
		insertAgyAccessGroupSQL.append("  ^, ");
		insertAgyAccessGroupSQL.append("  ^, ");
		insertAgyAccessGroupSQL.append("  ^, ");
		insertAgyAccessGroupSQL.append("  ^ ");
		insertAgyAccessGroupSQL.append(")  ");
		// Get update AgyAccessGroup SQL.
		updateAgyAccessGroupSQL = new StringBuffer();
		updateAgyAccessGroupSQL.append("UPDATE AGYACCESSGROUP ");
		updateAgyAccessGroupSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAgyAccessGroupSQL.append("     AGENCYID = ^, ");
		updateAgyAccessGroupSQL.append("     NAME = ^, ");
		updateAgyAccessGroupSQL.append("     AUDITORID = ^, ");
		updateAgyAccessGroupSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAgyAccessGroupSQL.append("WHERE AGYACCESSGROUPID = ^ ");
		updateAgyAccessGroupSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete AgyAccessGroup SQL.
		deleteAgyAccessGroupSQL = new StringBuffer();
		// deleteAgyAccessGroupSQL = new StringBuffer();
		deleteAgyAccessGroupSQL.append("UPDATE AGYACCESSGROUP ");
		deleteAgyAccessGroupSQL.append("SET ACTIVE = FALSE, ");
		deleteAgyAccessGroupSQL.append("    AUDITORID = ^, ");
		deleteAgyAccessGroupSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAgyAccessGroupSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAgyAccessGroupSQL.append("WHERE AGYACCESSGROUPID = ^ ");
		deleteAgyAccessGroupSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AgyAccessGroups SQL.
		selectAgyAccessGroupsSQL = new StringBuffer();
		selectAgyAccessGroupsSQL.append("SELECT A.AGYACCESSGROUPID, ");
		selectAgyAccessGroupsSQL.append("       A.AGENCYID, ");
		selectAgyAccessGroupsSQL.append("       A.NAME, ");
		selectAgyAccessGroupsSQL.append("       A.CREATIONTIMESTAMP, ");
		selectAgyAccessGroupsSQL.append("       A.AUDITORID, ");
		selectAgyAccessGroupsSQL.append("       A.AUDITTIMESTAMP, ");
		selectAgyAccessGroupsSQL.append("       A.ACTIVE, ");
		selectAgyAccessGroupsSQL.append("       A.NOOFCHANGES ");
		selectAgyAccessGroupsSQL.append("FROM AGYACCESSGROUP A ");
		// Get select AgyAccessGroups for Agency SQL.
		selectAgyAccessGroupsForAgencySQL = new StringBuffer(selectAgyAccessGroupsSQL);
		selectAgyAccessGroupsForAgencySQL.append("WHERE A.AGENCYID = ^ ");
		// Get select Active AgyAccessGroups SQL.
		selectActiveAgyAccessGroupsForAgencySQL = new StringBuffer(selectAgyAccessGroupsForAgencySQL);
		selectActiveAgyAccessGroupsForAgencySQL.append("AND A.ACTIVE = TRUE ");
		// Get select AgyAccessGroup SQL.
		selectAgyAccessGroupSQL = new StringBuffer(selectAgyAccessGroupsSQL);
		selectAgyAccessGroupSQL.append("WHERE A.AGYACCESSGROUPID = ^ ");
		// Get select AgyAccessGroup for Name SQL.
		selectAgyAccessGroupForNameSQL = new StringBuffer(selectActiveAgyAccessGroupsForAgencySQL);
		selectAgyAccessGroupForNameSQL.append("AND A.NAME = ^ ");
		// Get select AgyAccessGroups NOT for Consultant SQL.
		selectAgyAccessGroupsNotForConsultantSQL = new StringBuffer(selectActiveAgyAccessGroupsForAgencySQL);
		selectAgyAccessGroupsNotForConsultantSQL.append("AND NOT EXISTS ");
		selectAgyAccessGroupsNotForConsultantSQL.append("( ");
		selectAgyAccessGroupsNotForConsultantSQL.append(" SELECT NULL ");
		selectAgyAccessGroupsNotForConsultantSQL.append(" FROM CONSULTANTACCESSGROUP AA ");
		selectAgyAccessGroupsNotForConsultantSQL.append(" WHERE AA.CONSULTANTID = ^ ");
		selectAgyAccessGroupsNotForConsultantSQL.append(" AND AA.ACTIVE = TRUE ");
		selectAgyAccessGroupsNotForConsultantSQL.append(" AND A.AGYACCESSGROUPID = AA.AGYACCESSGROUPID ");
		selectAgyAccessGroupsNotForConsultantSQL.append(") ");
		selectAgyAccessGroupsNotForConsultantSQL.append("ORDER BY A.NAME ");
        // ORDER BY 
		selectAgyAccessGroupsForAgencySQL.append("ORDER BY A.NAME ");
		selectActiveAgyAccessGroupsForAgencySQL.append("ORDER BY A.NAME ");
	
	}

	public int insertAgyAccessGroup(AgyAccessGroup agyAccessGroup, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAgyAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		agyAccessGroup.setAgyAccessGroupId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "agyAccessGroup"));
		Utilities.replace(sql, agyAccessGroup.getAgyAccessGroupId());
		Utilities.replace(sql, agyAccessGroup.getAgencyId());
		Utilities.replaceAndQuote(sql, agyAccessGroup.getName().trim());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateAgyAccessGroup(AgyAccessGroup agyAccessGroup, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgyAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agyAccessGroup.getAgencyId());
		Utilities.replaceAndQuote(sql, agyAccessGroup.getName().trim());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agyAccessGroup.getAgyAccessGroupId());
		Utilities.replace(sql, agyAccessGroup.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteAgyAccessGroup(Integer agyAccessGroupId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAgyAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agyAccessGroupId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public AgyAccessGroup getAgyAccessGroup(Integer agyAccessGroupId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgyAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agyAccessGroupId);
		return (AgyAccessGroup) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccessGroup.class.getName());
	}

	public AgyAccessGroup getAgyAccessGroupForName(Integer agencyId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgyAccessGroupForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replaceAndQuote(sql, name);
		return (AgyAccessGroup) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccessGroup.class.getName());
	}

	public List<AgyAccessGroup> getAgyAccessGroupsForAgency(Integer agencyId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveAgyAccessGroupsForAgencySQL.toString());
		}
		else {
			sql = new StringBuffer(selectAgyAccessGroupsForAgencySQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccessGroup.class.getName());

	}

	public List<AgyAccessGroup> getAgyAccessGroupsNotForConsultant(Integer agencyId, Integer consultantId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgyAccessGroupsNotForConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, consultantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccessGroup.class.getName());

	}

	public AgyAccessGroupEntity getAgyAccessGroupEntity(Integer agyAccessGroupId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgyAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agyAccessGroupId);
		return (AgyAccessGroupEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccessGroupEntity.class.getName());
	}


}
