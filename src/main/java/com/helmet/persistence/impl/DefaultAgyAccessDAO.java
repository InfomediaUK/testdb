package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AgyAccess;
import com.helmet.persistence.AgyAccessDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAgyAccessDAO extends JdbcDaoSupport implements AgyAccessDAO {

	private static StringBuffer insertAgyAccessSQL;

	private static StringBuffer updateAgyAccessSQL;

	private static StringBuffer deleteAgyAccessSQL;

	private static StringBuffer selectAgyAccessSQL;

	private static StringBuffer selectAgyAccessForNameSQL;

	private static StringBuffer selectAgyAccessesSQL;

	private static StringBuffer selectActiveAgyAccessesSQL;

	private static StringBuffer selectAgyAccessesNotForConsultantSQL;

	private static StringBuffer selectAgyAccessesNotForAgyAccessGroupSQL;

	private static StringBuffer selectActiveAgyAccessesForConsultantSQL;

	public static void init() {
		// Get insert AgyAccess SQL.
		insertAgyAccessSQL = new StringBuffer();
		insertAgyAccessSQL.append("INSERT INTO AGYACCESS ");
		insertAgyAccessSQL.append("(  ");
		insertAgyAccessSQL.append("  AGYACCESSID, ");
		insertAgyAccessSQL.append("  NAME, ");
		insertAgyAccessSQL.append("  DESCRIPTION, ");
		insertAgyAccessSQL.append("  STARTSWITH, ");
		insertAgyAccessSQL.append("  GLOBAL, ");
		insertAgyAccessSQL.append("  CREATIONTIMESTAMP, ");
		insertAgyAccessSQL.append("  AUDITORID, ");
		insertAgyAccessSQL.append("  AUDITTIMESTAMP ");
		insertAgyAccessSQL.append(")  ");
		insertAgyAccessSQL.append("VALUES  ");
		insertAgyAccessSQL.append("(  ");
		insertAgyAccessSQL.append("  ^, ");
		insertAgyAccessSQL.append("  ^, ");
		insertAgyAccessSQL.append("  ^, ");
		insertAgyAccessSQL.append("  ^, ");
		insertAgyAccessSQL.append("  ^, ");
		insertAgyAccessSQL.append("  ^, ");
		insertAgyAccessSQL.append("  ^, ");
		insertAgyAccessSQL.append("  ^ ");
		insertAgyAccessSQL.append(")  ");
		// Get update AgyAccess SQL.
		updateAgyAccessSQL = new StringBuffer();
		updateAgyAccessSQL.append("UPDATE AGYACCESS ");
		updateAgyAccessSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateAgyAccessSQL.append("     NAME = ^, ");
		updateAgyAccessSQL.append("     DESCRIPTION = ^, ");
		updateAgyAccessSQL.append("     STARTSWITH = ^, ");
		updateAgyAccessSQL.append("     GLOBAL = ^, ");
		updateAgyAccessSQL.append("     AUDITORID = ^, ");
		updateAgyAccessSQL.append("     AUDITTIMESTAMP = ^ ");
		updateAgyAccessSQL.append("WHERE AGYACCESSID = ^ ");
		updateAgyAccessSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete AgyAccess SQL.
		deleteAgyAccessSQL = new StringBuffer();
		// deleteAgyAccessSQL = new StringBuffer();
		deleteAgyAccessSQL.append("UPDATE AGYACCESS ");
		deleteAgyAccessSQL.append("SET ACTIVE = FALSE, ");
		deleteAgyAccessSQL.append("    AUDITORID = ^, ");
		deleteAgyAccessSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteAgyAccessSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteAgyAccessSQL.append("WHERE AGYACCESSID = ^ ");
		deleteAgyAccessSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AgyAccesses SQL.
		selectAgyAccessesSQL = new StringBuffer();
		selectAgyAccessesSQL.append("SELECT A.AGYACCESSID, ");
		selectAgyAccessesSQL.append("       A.NAME, ");
		selectAgyAccessesSQL.append("       A.DESCRIPTION, ");
		selectAgyAccessesSQL.append("       A.STARTSWITH, ");
		selectAgyAccessesSQL.append("       A.GLOBAL, ");
		selectAgyAccessesSQL.append("       A.CREATIONTIMESTAMP, ");
		selectAgyAccessesSQL.append("       A.AUDITORID, ");
		selectAgyAccessesSQL.append("       A.AUDITTIMESTAMP, ");
		selectAgyAccessesSQL.append("       A.ACTIVE, ");
		selectAgyAccessesSQL.append("       A.NOOFCHANGES ");
		selectAgyAccessesSQL.append("FROM AGYACCESS A ");
		// Get select Active AgyAccesses SQL.
		selectActiveAgyAccessesSQL = new StringBuffer(selectAgyAccessesSQL);
		selectActiveAgyAccessesSQL.append("WHERE A.ACTIVE = TRUE ");
		// Get select AgyAccess SQL.
		selectAgyAccessSQL = new StringBuffer(selectAgyAccessesSQL);
		selectAgyAccessSQL.append("WHERE A.AGYACCESSID = ^ ");
		// Get select AgyAccess for Name SQL.
		selectAgyAccessForNameSQL = new StringBuffer(selectActiveAgyAccessesSQL);
		selectAgyAccessForNameSQL.append("AND A.NAME = ^ ");
		// Get select AgyAccesses NOT for Consultant SQL.
		selectAgyAccessesNotForConsultantSQL = new StringBuffer(selectAgyAccessesSQL);
		selectAgyAccessesNotForConsultantSQL.append("WHERE A.ACTIVE = TRUE ");
		selectAgyAccessesNotForConsultantSQL.append("AND   A.GLOBAL = FALSE ");
		selectAgyAccessesNotForConsultantSQL.append("AND NOT EXISTS ");
		selectAgyAccessesNotForConsultantSQL.append("( ");
		selectAgyAccessesNotForConsultantSQL.append(" SELECT NULL ");
		selectAgyAccessesNotForConsultantSQL.append(" FROM CONSULTANTACCESS AA ");
		selectAgyAccessesNotForConsultantSQL.append(" WHERE AA.CONSULTANTID = ^ ");
		selectAgyAccessesNotForConsultantSQL.append(" AND AA.ACTIVE = TRUE ");
		selectAgyAccessesNotForConsultantSQL.append(" AND A.AGYACCESSID = AA.AGYACCESSID ");
		selectAgyAccessesNotForConsultantSQL.append(") ");
		selectAgyAccessesNotForConsultantSQL.append("ORDER BY A.NAME ");
		// Get select AgyAccesses NOT for AgyAccessGroup SQL.
		selectAgyAccessesNotForAgyAccessGroupSQL = new StringBuffer(selectAgyAccessesSQL);
		selectAgyAccessesNotForAgyAccessGroupSQL.append("WHERE A.ACTIVE = TRUE ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append("AND   A.GLOBAL = FALSE ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append("AND NOT EXISTS ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append("( ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append(" SELECT NULL ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append(" FROM AGYACCESSGROUPITEM AA ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append(" WHERE AA.AGYACCESSGROUPID = ^ ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append(" AND AA.ACTIVE = TRUE ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append(" AND A.AGYACCESSID = AA.AGYACCESSID ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append(") ");
		selectAgyAccessesNotForAgyAccessGroupSQL.append("ORDER BY A.NAME ");
		// Get select Active AgyAccessesForConsultant SQL.
		selectActiveAgyAccessesForConsultantSQL = new StringBuffer(selectAgyAccessesSQL);
		selectActiveAgyAccessesForConsultantSQL.append(", ");
		selectActiveAgyAccessesForConsultantSQL.append("    CONSULTANTACCESS AA ");
		selectActiveAgyAccessesForConsultantSQL.append("WHERE AA.AGYACCESSID = A.AGYACCESSID ");
		selectActiveAgyAccessesForConsultantSQL.append("AND AA.CONSULTANTID = ^ ");
		selectActiveAgyAccessesForConsultantSQL.append("AND A.ACTIVE = TRUE ");
		selectActiveAgyAccessesForConsultantSQL.append("AND AA.ACTIVE = TRUE ");
		selectActiveAgyAccessesForConsultantSQL.append("UNION ");
		selectActiveAgyAccessesForConsultantSQL.append(selectAgyAccessesSQL);
		selectActiveAgyAccessesForConsultantSQL.append(", ");
        selectActiveAgyAccessesForConsultantSQL.append("AGYACCESSGROUPITEM AAGI, ");
		selectActiveAgyAccessesForConsultantSQL.append("CONSULTANTACCESSGROUP AAG ");
		selectActiveAgyAccessesForConsultantSQL.append("WHERE AAGI.AGYACCESSID = A.AGYACCESSID ");
		selectActiveAgyAccessesForConsultantSQL.append("AND AAG.AGYACCESSGROUPID = AAGI.AGYACCESSGROUPID ");
		selectActiveAgyAccessesForConsultantSQL.append("AND AAG.CONSULTANTID = ^ ");
		selectActiveAgyAccessesForConsultantSQL.append("AND A.ACTIVE = TRUE ");
		selectActiveAgyAccessesForConsultantSQL.append("AND AAGI.ACTIVE = TRUE ");
		selectActiveAgyAccessesForConsultantSQL.append("AND AAG.ACTIVE = TRUE ");
		selectActiveAgyAccessesForConsultantSQL.append("UNION ");
		selectActiveAgyAccessesForConsultantSQL.append(selectAgyAccessesSQL);
		selectActiveAgyAccessesForConsultantSQL.append("WHERE A.ACTIVE = TRUE ");
		selectActiveAgyAccessesForConsultantSQL.append("AND A.GLOBAL = TRUE ");
		// ORDER BY 
		selectAgyAccessesSQL.append("ORDER BY A.NAME ");
		selectActiveAgyAccessesSQL.append("ORDER BY A.GLOBAL, A.NAME ");
	}

	public int insertAgyAccess(AgyAccess agyAccess, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAgyAccessSQL.toString());
		// Replace the parameters with supplied values.
		agyAccess.setAgyAccessId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "agyAccess"));
		Utilities.replace(sql, agyAccess.getAgyAccessId());
		Utilities.replaceAndQuote(sql, agyAccess.getName().trim());
		Utilities.replaceAndQuoteNullable(sql, agyAccess.getDescription());
		Utilities.replace(sql, agyAccess.getStartsWith());
		Utilities.replace(sql, agyAccess.getGlobal());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateAgyAccess(AgyAccess agyAccess, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAgyAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, agyAccess.getName().trim());
		Utilities.replaceAndQuoteNullable(sql, agyAccess.getDescription());
		Utilities.replace(sql, agyAccess.getStartsWith());
		Utilities.replace(sql, agyAccess.getGlobal());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agyAccess.getAgyAccessId());
		Utilities.replace(sql, agyAccess.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteAgyAccess(Integer agyAccessId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAgyAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, agyAccessId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public AgyAccess getAgyAccess(Integer agyAccessId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgyAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agyAccessId);
		return (AgyAccess) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccess.class.getName());
	}

	public AgyAccess getAgyAccessForName(String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgyAccessForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (AgyAccess) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccess.class.getName());
	}

	public List<AgyAccess> getAgyAccesses(boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveAgyAccessesSQL.toString());
		}
		else {
			sql = new StringBuffer(selectAgyAccessesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccess.class.getName());

	}

	public List<AgyAccess> getAgyAccessesNotForConsultant(Integer consultantId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgyAccessesNotForConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, consultantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccess.class.getName());

	}

	public List<AgyAccess> getAgyAccessesNotForAgyAccessGroup(Integer agyAccessGroupId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAgyAccessesNotForAgyAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agyAccessGroupId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccess.class.getName());

	}

	public List<AgyAccess> getActiveAgyAccessesForConsultant(Integer consultantId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveAgyAccessesForConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, consultantId);
		Utilities.replace(sql, consultantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AgyAccess.class.getName());
	}

}
