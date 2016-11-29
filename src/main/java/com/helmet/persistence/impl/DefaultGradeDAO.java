package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Grade;
import com.helmet.persistence.GradeDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultGradeDAO extends JdbcDaoSupport implements GradeDAO {

	private static StringBuffer insertGradeSQL;

	private static StringBuffer updateGradeSQL;

	private static StringBuffer updateGradeDisplayOrderSQL;

	private static StringBuffer deleteGradeSQL;

	private static StringBuffer selectGradeSQL;

	private static StringBuffer selectGradeForNameSQL;

	private static StringBuffer selectGradesSQL;

	private static StringBuffer selectGradesForClientSQL;

	private static StringBuffer selectGradesForJobProfileSQL;

	private static StringBuffer selectGradesNotForJobProfileSQL;

	private static StringBuffer selectGradesNotForClientAgencySQL;

	private static StringBuffer selectGradesNotForClientAgencyJobProfileSQL;

	private static StringBuffer selectActiveGradesForClientSQL;

	public static void init() {
		// Get insert Grade SQL.
		insertGradeSQL = new StringBuffer();
		insertGradeSQL.append("INSERT INTO GRADE ");
		insertGradeSQL.append("(  ");
		insertGradeSQL.append("  GRADEID, ");
		insertGradeSQL.append("  CLIENTID, ");
		insertGradeSQL.append("  NAME, ");
		insertGradeSQL.append("  CREATIONTIMESTAMP, ");
		insertGradeSQL.append("  AUDITORID, ");
		insertGradeSQL.append("  AUDITTIMESTAMP ");
		insertGradeSQL.append(")  ");
		insertGradeSQL.append("VALUES  ");
		insertGradeSQL.append("(  ");
		insertGradeSQL.append("  ^, ");
		insertGradeSQL.append("  ^, ");
		insertGradeSQL.append("  ^, ");
		insertGradeSQL.append("  ^, ");
		insertGradeSQL.append("  ^, ");
		insertGradeSQL.append("  ^ ");
		insertGradeSQL.append(")  ");
		// Get update Grade SQL.
		updateGradeSQL = new StringBuffer();
		updateGradeSQL.append("UPDATE GRADE ");
		updateGradeSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateGradeSQL.append("     CLIENTID = ^, ");
		updateGradeSQL.append("     NAME = ^, ");
		updateGradeSQL.append("     AUDITORID = ^, ");
		updateGradeSQL.append("     AUDITTIMESTAMP = ^ ");
		updateGradeSQL.append("WHERE GRADEID = ^ ");
		updateGradeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateGradeDisplayOrder SQL.
		updateGradeDisplayOrderSQL = new StringBuffer();
		updateGradeDisplayOrderSQL.append("UPDATE GRADE ");
		updateGradeDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateGradeDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateGradeDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateGradeDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateGradeDisplayOrderSQL.append("WHERE GRADEID = ^ ");
		updateGradeDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Grade SQL.
		deleteGradeSQL = new StringBuffer();
		// deleteGradeSQL = new StringBuffer();
		deleteGradeSQL.append("UPDATE GRADE ");
		deleteGradeSQL.append("SET ACTIVE = FALSE, ");
		deleteGradeSQL.append("    AUDITORID = ^, ");
		deleteGradeSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteGradeSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteGradeSQL.append("WHERE GRADEID = ^ ");
		deleteGradeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Grades SQL.
		selectGradesSQL = new StringBuffer();
		selectGradesSQL.append("SELECT G.GRADEID, ");
		selectGradesSQL.append("       G.CLIENTID, ");
		selectGradesSQL.append("       G.NAME, ");
		selectGradesSQL.append("       G.DISPLAYORDER, ");
		selectGradesSQL.append("       G.CREATIONTIMESTAMP, ");
		selectGradesSQL.append("       G.AUDITORID, ");
		selectGradesSQL.append("       G.AUDITTIMESTAMP, ");
		selectGradesSQL.append("       G.ACTIVE, ");
		selectGradesSQL.append("       G.NOOFCHANGES ");
		selectGradesSQL.append("FROM GRADE G ");
		// Get select Grades for JobProfile SQL.
		selectGradesForJobProfileSQL = new StringBuffer(selectGradesSQL);
		selectGradesForJobProfileSQL.append(" , ");
		selectGradesForJobProfileSQL.append(" JOBPROFILEGRADE JPG ");
		selectGradesForJobProfileSQL.append("WHERE G.GRADEID = JPG.GRADEID ");
		selectGradesForJobProfileSQL.append("AND G.ACTIVE = TRUE ");
		selectGradesForJobProfileSQL.append("AND JPG.ACTIVE = TRUE ");
		selectGradesForJobProfileSQL.append("AND JPG.JOBPROFILEID = ^ ");
		selectGradesForJobProfileSQL.append("ORDER BY G.DISPLAYORDER, G.NAME ");
		// Get select Grades for Client SQL.
		selectGradesForClientSQL = new StringBuffer(selectGradesSQL);
		selectGradesForClientSQL.append("WHERE G.CLIENTID = ^ ");
		// Get select Active Grades for Client SQL.
		selectActiveGradesForClientSQL = new StringBuffer(selectGradesForClientSQL);
		selectActiveGradesForClientSQL.append("AND G.ACTIVE = TRUE ");
		// Get select Grade SQL.
		selectGradeSQL = new StringBuffer(selectGradesSQL);
		selectGradeSQL.append("WHERE G.GRADEID = ^ ");
		// Get select Grade for Name SQL.
		selectGradeForNameSQL = new StringBuffer(selectActiveGradesForClientSQL);
		selectGradeForNameSQL.append("AND G.NAME = ^ ");
		// ORDER BY 
		selectGradesForClientSQL.append("ORDER BY G.DISPLAYORDER, G.NAME ");
		selectActiveGradesForClientSQL.append("ORDER BY G.DISPLAYORDER, G.NAME ");
		// Get select Grades not for Job Profile SQL.
		selectGradesNotForJobProfileSQL = new StringBuffer(selectGradesSQL);
		selectGradesNotForJobProfileSQL.append("WHERE G.ACTIVE = TRUE ");
		selectGradesNotForJobProfileSQL.append("AND G.CLIENTID = ^ ");
		selectGradesNotForJobProfileSQL.append("AND NOT EXISTS ");
		selectGradesNotForJobProfileSQL.append("( ");
		selectGradesNotForJobProfileSQL.append(" SELECT NULL ");
		selectGradesNotForJobProfileSQL.append(" FROM JOBPROFILEGRADE JPG ");
		selectGradesNotForJobProfileSQL.append(" WHERE JPG.JOBPROFILEID = ^ ");
		selectGradesNotForJobProfileSQL.append(" AND JPG.ACTIVE = TRUE ");
		selectGradesNotForJobProfileSQL.append(" AND JPG.GRADEID = G.GRADEID ");
		selectGradesNotForJobProfileSQL.append(") ");
		selectGradesNotForJobProfileSQL.append("ORDER BY G.DISPLAYORDER, G.NAME ");
		// Get select Grades not for Client Agency SQL.
		selectGradesNotForClientAgencySQL = new StringBuffer(selectGradesSQL);
		selectGradesNotForClientAgencySQL.append("WHERE G.ACTIVE = TRUE ");
		selectGradesNotForClientAgencySQL.append("AND G.CLIENTID = ^ ");
		selectGradesNotForClientAgencySQL.append("AND NOT EXISTS ");
		selectGradesNotForClientAgencySQL.append("( ");
		selectGradesNotForClientAgencySQL.append(" SELECT NULL ");
		selectGradesNotForClientAgencySQL.append(" FROM CLIENTAGENCYGRADE CAG ");
		selectGradesNotForClientAgencySQL.append(" WHERE CAG.CLIENTAGENCYID = ^ ");
		selectGradesNotForClientAgencySQL.append(" AND CAG.ACTIVE = TRUE ");
		selectGradesNotForClientAgencySQL.append(" AND CAG.GRADEID = G.GRADEID ");
		selectGradesNotForClientAgencySQL.append(") ");
		selectGradesNotForClientAgencySQL.append("ORDER BY G.DISPLAYORDER, G.NAME ");
		// Get select Grades not for Client Agency Job Profile SQL.
		selectGradesNotForClientAgencyJobProfileSQL = new StringBuffer(selectGradesSQL);
		selectGradesNotForClientAgencyJobProfileSQL.append(", JOBPROFILEGRADE JPG ");
		selectGradesNotForClientAgencyJobProfileSQL.append("WHERE G.GRADEID = JPG.GRADEID " );
		selectGradesNotForClientAgencyJobProfileSQL.append("AND G.ACTIVE = TRUE ");
		selectGradesNotForClientAgencyJobProfileSQL.append("AND JPG.JOBPROFILEID = ^ ");
		selectGradesNotForClientAgencyJobProfileSQL.append("AND JPG.ACTIVE = TRUE ");
		selectGradesNotForClientAgencyJobProfileSQL.append("AND NOT EXISTS ");
		selectGradesNotForClientAgencyJobProfileSQL.append("( ");
		selectGradesNotForClientAgencyJobProfileSQL.append(" SELECT NULL ");
		selectGradesNotForClientAgencyJobProfileSQL.append(" FROM CLIENTAGENCYJOBPROFILEGRADE CAJPG ");
		selectGradesNotForClientAgencyJobProfileSQL.append(" WHERE CAJPG.CLIENTAGENCYJOBPROFILEID = ^ ");
		selectGradesNotForClientAgencyJobProfileSQL.append(" AND CAJPG.ACTIVE = TRUE ");
		selectGradesNotForClientAgencyJobProfileSQL.append(" AND CAJPG.GRADEID = G.GRADEID ");
		selectGradesNotForClientAgencyJobProfileSQL.append(") ");
		selectGradesNotForClientAgencyJobProfileSQL.append("ORDER BY G.DISPLAYORDER, G.NAME ");
	}

	public int insertGrade(Grade grade, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertGradeSQL.toString());
		// Replace the parameters with supplied values.
		grade.setGradeId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "grade"));
		Utilities.replace(sql, grade.getGradeId());
		Utilities.replace(sql, grade.getClientId());
		Utilities.replaceAndQuote(sql, grade.getName());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateGrade(Grade grade, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, grade.getClientId());
		Utilities.replaceAndQuote(sql, grade.getName());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, grade.getGradeId());
		Utilities.replace(sql, grade.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteGrade(Integer gradeId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, gradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public Grade getGrade(Integer gradeId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, gradeId);
		return (Grade) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Grade.class.getName());
	}

	public Grade getGradeForName(Integer clientId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectGradeForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, name);
		return (Grade) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Grade.class.getName());
	}

	public List<Grade> getGradesForClient(Integer clientId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveGradesForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectGradesForClientSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Grade.class.getName());

	}

	public List<Grade> getGradesNotForJobProfile(Integer clientId, Integer jobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectGradesNotForJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, jobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Grade.class.getName());

	}

	public List<Grade> getGradesNotForClientAgency(Integer clientId, Integer clientAgencyId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectGradesNotForClientAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, clientAgencyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Grade.class.getName());

	}

	public List<Grade> getGradesForJobProfile(Integer jobProfileId) {
	
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectGradesForJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Grade.class.getName());

	}

	public List<Grade> getGradesNotForClientAgencyJobProfile(Integer jobProfileId, Integer clientAgencyJobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectGradesNotForClientAgencyJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, clientAgencyJobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Grade.class.getName());

	}

	public int updateGradeDisplayOrder(Integer gradeId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateGradeDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, gradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	
}
