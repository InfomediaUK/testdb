package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ConsultantAccessGroup;
import com.helmet.bean.ConsultantAccessGroupUser;
import com.helmet.persistence.ConsultantAccessGroupDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultConsultantAccessGroupDAO extends JdbcDaoSupport implements ConsultantAccessGroupDAO {

	private static StringBuffer insertConsultantAccessGroupSQL;

	private static StringBuffer deleteConsultantAccessGroupSQL;

	private static StringBuffer selectConsultantAccessGroupUsersSQL;

	private static StringBuffer selectConsultantAccessGroupUsersForConsultantSQL;

	public static void init() {
		// Get insert ConsultantAccessGroup SQL.
		insertConsultantAccessGroupSQL = new StringBuffer();
		insertConsultantAccessGroupSQL.append("INSERT INTO CONSULTANTACCESSGROUP ");
		insertConsultantAccessGroupSQL.append("(  ");
		insertConsultantAccessGroupSQL.append("  CONSULTANTACCESSGROUPID, ");
		insertConsultantAccessGroupSQL.append("  CONSULTANTID, ");
		insertConsultantAccessGroupSQL.append("  AGYACCESSGROUPID, ");
		insertConsultantAccessGroupSQL.append("  CREATIONTIMESTAMP, ");
		insertConsultantAccessGroupSQL.append("  AUDITORID, ");
		insertConsultantAccessGroupSQL.append("  AUDITTIMESTAMP ");
		insertConsultantAccessGroupSQL.append(")  ");
		insertConsultantAccessGroupSQL.append("VALUES  ");
		insertConsultantAccessGroupSQL.append("(  ");
		insertConsultantAccessGroupSQL.append("  ^, ");
		insertConsultantAccessGroupSQL.append("  ^, ");
		insertConsultantAccessGroupSQL.append("  ^, ");
		insertConsultantAccessGroupSQL.append("  ^, ");
		insertConsultantAccessGroupSQL.append("  ^, ");
		insertConsultantAccessGroupSQL.append("  ^ ");
		insertConsultantAccessGroupSQL.append(")  ");
		// Get delete ConsultantAccessGroup SQL.
		deleteConsultantAccessGroupSQL = new StringBuffer();
		// deleteConsultantAccessGroupSQL = new StringBuffer();
		deleteConsultantAccessGroupSQL.append("UPDATE CONSULTANTACCESSGROUP ");
		deleteConsultantAccessGroupSQL.append("SET ACTIVE = FALSE, ");
		deleteConsultantAccessGroupSQL.append("    AUDITORID = ^, ");
		deleteConsultantAccessGroupSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteConsultantAccessGroupSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteConsultantAccessGroupSQL.append("WHERE CONSULTANTACCESSGROUPID = ^ ");
		deleteConsultantAccessGroupSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ConsultantAccessGroupUsers SQL.
		selectConsultantAccessGroupUsersSQL = new StringBuffer();
		selectConsultantAccessGroupUsersSQL.append("SELECT A.CONSULTANTACCESSGROUPID, ");
		selectConsultantAccessGroupUsersSQL.append("       A.CONSULTANTID, ");
		selectConsultantAccessGroupUsersSQL.append("       A.AGYACCESSGROUPID, ");
		selectConsultantAccessGroupUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectConsultantAccessGroupUsersSQL.append("       A.AUDITORID, ");
		selectConsultantAccessGroupUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectConsultantAccessGroupUsersSQL.append("       A.ACTIVE, ");
		selectConsultantAccessGroupUsersSQL.append("       A.NOOFCHANGES, ");
		selectConsultantAccessGroupUsersSQL.append("       B.NAME AS AGYACCESSGROUPNAME ");
		selectConsultantAccessGroupUsersSQL.append("FROM CONSULTANTACCESSGROUP A, ");
		selectConsultantAccessGroupUsersSQL.append("     AGYACCESSGROUP B ");
		selectConsultantAccessGroupUsersSQL.append("WHERE B.AGYACCESSGROUPID = A.AGYACCESSGROUPID ");
		// Get select ConsultantAccessGroupUsers for Consultant SQL.
		selectConsultantAccessGroupUsersForConsultantSQL = new StringBuffer(selectConsultantAccessGroupUsersSQL);
		selectConsultantAccessGroupUsersForConsultantSQL.append("AND A.CONSULTANTID = ^ ");
		selectConsultantAccessGroupUsersForConsultantSQL.append("AND A.ACTIVE = TRUE ");
		selectConsultantAccessGroupUsersForConsultantSQL.append("ORDER BY B.NAME ");
	}

	public int insertConsultantAccessGroup(ConsultantAccessGroup consultantAccessGroup, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertConsultantAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		consultantAccessGroup.setConsultantAccessGroupId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "consultantAccessGroup"));
		Utilities.replace(sql, consultantAccessGroup.getConsultantAccessGroupId());
		Utilities.replace(sql, consultantAccessGroup.getConsultantId());
		Utilities.replace(sql, consultantAccessGroup.getAgyAccessGroupId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteConsultantAccessGroup(Integer consultantAccessGroupId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteConsultantAccessGroupSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, consultantAccessGroupId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<ConsultantAccessGroupUser> getConsultantAccessGroupUsersForConsultant(Integer consultantId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectConsultantAccessGroupUsersForConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, consultantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ConsultantAccessGroupUser.class.getName());

	}

}
