package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.ClientAgencyGrade;
import com.helmet.bean.ClientAgencyGradeUser;
import com.helmet.persistence.ClientAgencyGradeDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultClientAgencyGradeDAO extends JdbcDaoSupport implements ClientAgencyGradeDAO {

	private static StringBuffer insertClientAgencyGradeSQL;

	private static StringBuffer updateClientAgencyGradeSQL;

	private static StringBuffer deleteClientAgencyGradeSQL;

	private static StringBuffer selectClientAgencyGradesSQL;

	private static StringBuffer selectClientAgencyGradeSQL;

	private static StringBuffer selectClientAgencyGradeUsersSQL;

	private static StringBuffer selectClientAgencyGradeUsersForClientAgencySQL;

	private static StringBuffer selectClientAgencyGradesForJobProfileSQL;

	public static void init() {
		// Get insert ClientAgencyGrade SQL.
		insertClientAgencyGradeSQL = new StringBuffer();
		insertClientAgencyGradeSQL.append("INSERT INTO CLIENTAGENCYGRADE ");
		insertClientAgencyGradeSQL.append("(  ");
		insertClientAgencyGradeSQL.append("  CLIENTAGENCYGRADEID, ");
		insertClientAgencyGradeSQL.append("  CLIENTAGENCYID, ");
		insertClientAgencyGradeSQL.append("  GRADEID, ");
		insertClientAgencyGradeSQL.append("  CREATIONTIMESTAMP, ");
		insertClientAgencyGradeSQL.append("  AUDITORID, ");
		insertClientAgencyGradeSQL.append("  AUDITTIMESTAMP ");
		insertClientAgencyGradeSQL.append(")  ");
		insertClientAgencyGradeSQL.append("VALUES  ");
		insertClientAgencyGradeSQL.append("(  ");
		insertClientAgencyGradeSQL.append("  ^, ");
		insertClientAgencyGradeSQL.append("  ^, ");
		insertClientAgencyGradeSQL.append("  ^, ");
		insertClientAgencyGradeSQL.append("  ^, ");
		insertClientAgencyGradeSQL.append("  ^, ");
		insertClientAgencyGradeSQL.append("  ^ ");
		insertClientAgencyGradeSQL.append(")  ");
		// Get update ClientAgencyGrade SQL.
		updateClientAgencyGradeSQL = new StringBuffer();
		updateClientAgencyGradeSQL.append("UPDATE CLIENTAGENCYGRADE ");
		updateClientAgencyGradeSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateClientAgencyGradeSQL.append("     RATE = ^, ");
		updateClientAgencyGradeSQL.append("     AUDITORID = ^, ");
		updateClientAgencyGradeSQL.append("     AUDITTIMESTAMP = ^ ");
		updateClientAgencyGradeSQL.append("WHERE CLIENTAGENCYGRADEID = ^ ");
		updateClientAgencyGradeSQL.append("AND   NOOFCHANGES = ^ ");
     	// Get delete ClientAgencyGrade SQL.
		deleteClientAgencyGradeSQL = new StringBuffer();
		deleteClientAgencyGradeSQL.append("UPDATE CLIENTAGENCYGRADE ");
		deleteClientAgencyGradeSQL.append("SET ACTIVE = FALSE, ");
		deleteClientAgencyGradeSQL.append("    AUDITORID = ^, ");
		deleteClientAgencyGradeSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteClientAgencyGradeSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteClientAgencyGradeSQL.append("WHERE CLIENTAGENCYGRADEID = ^ ");
		deleteClientAgencyGradeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select ClientAgencyGrades SQL.
		selectClientAgencyGradesSQL = new StringBuffer();
		selectClientAgencyGradesSQL.append("SELECT CAG.CLIENTAGENCYGRADEID, ");
		selectClientAgencyGradesSQL.append("       CAG.CLIENTAGENCYID, ");
		selectClientAgencyGradesSQL.append("       CAG.GRADEID, ");
		selectClientAgencyGradesSQL.append("       CAG.RATE, ");
		selectClientAgencyGradesSQL.append("       CAG.CREATIONTIMESTAMP, ");
		selectClientAgencyGradesSQL.append("       CAG.AUDITORID, ");
		selectClientAgencyGradesSQL.append("       CAG.AUDITTIMESTAMP, ");
		selectClientAgencyGradesSQL.append("       CAG.ACTIVE, ");
		selectClientAgencyGradesSQL.append("       CAG.NOOFCHANGES ");
		selectClientAgencyGradesSQL.append("FROM CLIENTAGENCYGRADE CAG ");
		// Get select ClientAgencyGrade SQL.
		selectClientAgencyGradeSQL = new StringBuffer(selectClientAgencyGradesSQL);
		selectClientAgencyGradeSQL.append("WHERE CAG.CLIENTAGENCYGRADEID = ^ ");
		// Get select ClientAgencyGrades for JobProfile SQL.
		selectClientAgencyGradesForJobProfileSQL = new StringBuffer(selectClientAgencyGradesSQL);
		selectClientAgencyGradesForJobProfileSQL.append("WHERE CAG.ACTIVE = TRUE ");
		selectClientAgencyGradesForJobProfileSQL.append("AND EXISTS "); 
		selectClientAgencyGradesForJobProfileSQL.append("( "); 
		selectClientAgencyGradesForJobProfileSQL.append("SELECT NULL "); 
		selectClientAgencyGradesForJobProfileSQL.append("FROM JOBPROFILEGRADE JPG ");
		selectClientAgencyGradesForJobProfileSQL.append("WHERE JPG.JOBPROFILEID = ^ ");
		selectClientAgencyGradesForJobProfileSQL.append("AND JPG.ACTIVE = TRUE ");
		selectClientAgencyGradesForJobProfileSQL.append("AND CAG.GRADEID = JPG.GRADEID ");
		selectClientAgencyGradesForJobProfileSQL.append(") "); 
		selectClientAgencyGradesForJobProfileSQL.append("ORDER BY CAG.CLIENTAGENCYID, CAG.RATE ");
		// Get select ClientAgencyGradeUsers SQL.
		selectClientAgencyGradeUsersSQL = new StringBuffer();
		selectClientAgencyGradeUsersSQL.append("SELECT CAG.CLIENTAGENCYGRADEID, ");
		selectClientAgencyGradeUsersSQL.append("       CAG.CLIENTAGENCYID, ");
		selectClientAgencyGradeUsersSQL.append("       CAG.GRADEID, ");
		selectClientAgencyGradeUsersSQL.append("       CAG.RATE, ");
		selectClientAgencyGradeUsersSQL.append("       CAG.CREATIONTIMESTAMP, ");
		selectClientAgencyGradeUsersSQL.append("       CAG.AUDITORID, ");
		selectClientAgencyGradeUsersSQL.append("       CAG.AUDITTIMESTAMP, ");
		selectClientAgencyGradeUsersSQL.append("       CAG.ACTIVE, ");
		selectClientAgencyGradeUsersSQL.append("       CAG.NOOFCHANGES, ");
		selectClientAgencyGradeUsersSQL.append("       A.AGENCYID, ");
		selectClientAgencyGradeUsersSQL.append("       A.NAME AS AGENCYNAME, ");
		selectClientAgencyGradeUsersSQL.append("       G.NAME AS GRADENAME ");
		selectClientAgencyGradeUsersSQL.append("FROM CLIENTAGENCYGRADE CAG, ");
		selectClientAgencyGradeUsersSQL.append("     CLIENTAGENCY CA, ");
		selectClientAgencyGradeUsersSQL.append("     AGENCY A, ");
		selectClientAgencyGradeUsersSQL.append("     GRADE G ");
		selectClientAgencyGradeUsersSQL.append("WHERE G.GRADEID = CAG.GRADEID ");
		selectClientAgencyGradeUsersSQL.append("AND G.ACTIVE = TRUE ");
		selectClientAgencyGradeUsersSQL.append("AND CA.CLIENTAGENCYID = CAG.CLIENTAGENCYID ");
		selectClientAgencyGradeUsersSQL.append("AND CA.ACTIVE = TRUE ");
		selectClientAgencyGradeUsersSQL.append("AND A.AGENCYID = CA.AGENCYID ");
		selectClientAgencyGradeUsersSQL.append("AND A.ACTIVE = TRUE ");
		// Get select ClientAgencyGradeUsers for JobProfile SQL.
		selectClientAgencyGradeUsersForClientAgencySQL = new StringBuffer(selectClientAgencyGradeUsersSQL);
		selectClientAgencyGradeUsersForClientAgencySQL.append("AND CAG.CLIENTAGENCYID = ^ ");
		selectClientAgencyGradeUsersForClientAgencySQL.append("AND CAG.ACTIVE = TRUE ");
		selectClientAgencyGradeUsersForClientAgencySQL.append("ORDER BY G.NAME ");
	}

	public int insertClientAgencyGrade(ClientAgencyGrade clientAgencyGrade, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertClientAgencyGradeSQL.toString());
		// Replace the parameters with supplied values.
		clientAgencyGrade.setClientAgencyGradeId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "clientAgencyGrade"));
		Utilities.replace(sql, clientAgencyGrade.getClientAgencyGradeId());
		Utilities.replace(sql, clientAgencyGrade.getClientAgencyId());
		Utilities.replace(sql, clientAgencyGrade.getGradeId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteClientAgencyGrade(Integer clientAgencyGradeId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteClientAgencyGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientAgencyGradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<ClientAgencyGradeUser> getClientAgencyGradeUsersForClientAgency(Integer clientAgencyId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyGradeUsersForClientAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyGradeUser.class.getName());

	}

	public ClientAgencyGrade getClientAgencyGrade(Integer clientAgencyGradeId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyGradeId);
		return (ClientAgencyGrade) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyGrade.class.getName());
	}

	public int updateClientAgencyGrade(ClientAgencyGrade clientAgencyGrade, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateClientAgencyGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientAgencyGrade.getRate());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, clientAgencyGrade.getClientAgencyGradeId());
		Utilities.replace(sql, clientAgencyGrade.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public List<ClientAgencyGrade> getClientAgencyGradesForJobProfile(Integer jobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectClientAgencyGradesForJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), ClientAgencyGrade.class.getName());

	}

}
