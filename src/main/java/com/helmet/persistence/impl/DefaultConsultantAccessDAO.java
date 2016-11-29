package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ConsultantAccess;
import com.helmet.bean.ConsultantAccessUser;
import com.helmet.persistence.ConsultantAccessDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultConsultantAccessDAO extends JdbcDaoSupport implements ConsultantAccessDAO {

	private static StringBuffer insertConsultantAccessSQL;

	private static StringBuffer deleteConsultantAccessSQL;

	private static StringBuffer selectConsultantAccessUsersSQL;

	private static StringBuffer selectConsultantAccessUsersForConsultantSQL;

	public static void init() {
		// Get insert ConsultantAccess SQL.
		insertConsultantAccessSQL = new StringBuffer();
		insertConsultantAccessSQL.append("INSERT INTO CONSULTANTACCESS ");
		insertConsultantAccessSQL.append("(  ");
		insertConsultantAccessSQL.append("  CONSULTANTACCESSID, ");
		insertConsultantAccessSQL.append("  CONSULTANTID, ");
		insertConsultantAccessSQL.append("  AGYACCESSID, ");
		insertConsultantAccessSQL.append("  CREATIONTIMESTAMP, ");
		insertConsultantAccessSQL.append("  AUDITORID, ");
		insertConsultantAccessSQL.append("  AUDITTIMESTAMP ");
		insertConsultantAccessSQL.append(")  ");
		insertConsultantAccessSQL.append("VALUES  ");
		insertConsultantAccessSQL.append("(  ");
		insertConsultantAccessSQL.append("  ^, ");
		insertConsultantAccessSQL.append("  ^, ");
		insertConsultantAccessSQL.append("  ^, ");
		insertConsultantAccessSQL.append("  ^, ");
		insertConsultantAccessSQL.append("  ^, ");
		insertConsultantAccessSQL.append("  ^ ");
		insertConsultantAccessSQL.append(")  ");
		// Get delete ConsultantAccess SQL.
		deleteConsultantAccessSQL = new StringBuffer();
		// deleteConsultantAccessSQL = new StringBuffer();
		deleteConsultantAccessSQL.append("UPDATE CONSULTANTACCESS ");
		deleteConsultantAccessSQL.append("SET ACTIVE = FALSE, ");
		deleteConsultantAccessSQL.append("    AUDITORID = ^, ");
		deleteConsultantAccessSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteConsultantAccessSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteConsultantAccessSQL.append("WHERE CONSULTANTACCESSID = ^ ");
		deleteConsultantAccessSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ConsultantAccessUsers SQL.
		selectConsultantAccessUsersSQL = new StringBuffer();
		selectConsultantAccessUsersSQL.append("SELECT A.CONSULTANTACCESSID, ");
		selectConsultantAccessUsersSQL.append("       A.CONSULTANTID, ");
		selectConsultantAccessUsersSQL.append("       A.AGYACCESSID, ");
		selectConsultantAccessUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectConsultantAccessUsersSQL.append("       A.AUDITORID, ");
		selectConsultantAccessUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectConsultantAccessUsersSQL.append("       A.ACTIVE, ");
		selectConsultantAccessUsersSQL.append("       A.NOOFCHANGES, ");
		selectConsultantAccessUsersSQL.append("       B.NAME AS AGYACCESSNAME ");
		selectConsultantAccessUsersSQL.append("FROM CONSULTANTACCESS A, ");
		selectConsultantAccessUsersSQL.append("     AGYACCESS B ");
		selectConsultantAccessUsersSQL.append("WHERE B.AGYACCESSID = A.AGYACCESSID ");
		// Get select ConsultantAccessUsers for Consultant SQL.
		selectConsultantAccessUsersForConsultantSQL = new StringBuffer(selectConsultantAccessUsersSQL);
		selectConsultantAccessUsersForConsultantSQL.append("AND A.CONSULTANTID = ^ ");
		selectConsultantAccessUsersForConsultantSQL.append("AND A.ACTIVE = TRUE ");
		selectConsultantAccessUsersForConsultantSQL.append("ORDER BY B.NAME ");
	}

	public int insertConsultantAccess(ConsultantAccess consultantAccess, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertConsultantAccessSQL.toString());
		// Replace the parameters with supplied values.
		consultantAccess.setConsultantAccessId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "consultantAccess"));
		Utilities.replace(sql, consultantAccess.getConsultantAccessId());
		Utilities.replace(sql, consultantAccess.getConsultantId());
		Utilities.replace(sql, consultantAccess.getAgyAccessId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteConsultantAccess(Integer consultantAccessId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteConsultantAccessSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, consultantAccessId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<ConsultantAccessUser> getConsultantAccessUsersForConsultant(Integer consultantId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectConsultantAccessUsersForConsultantSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, consultantId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ConsultantAccessUser.class.getName());

	}

}
