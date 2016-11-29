package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.JobProfileGrade;
import com.helmet.bean.JobProfileGradeUser;
import com.helmet.persistence.JobProfileGradeDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultJobProfileGradeDAO extends JdbcDaoSupport implements JobProfileGradeDAO {

	private static StringBuffer insertJobProfileGradeSQL;

	private static StringBuffer deleteJobProfileGradeSQL;

	private static StringBuffer selectJobProfileGradeUsersSQL;

	private static StringBuffer selectJobProfileGradeUsersForJobProfileSQL;

	public static void init() {
		// Get insert JobProfileGrade SQL.
		insertJobProfileGradeSQL = new StringBuffer();
		insertJobProfileGradeSQL.append("INSERT INTO JOBPROFILEGRADE ");
		insertJobProfileGradeSQL.append("(  ");
		insertJobProfileGradeSQL.append("  JOBPROFILEGRADEID, ");
		insertJobProfileGradeSQL.append("  JOBPROFILEID, ");
		insertJobProfileGradeSQL.append("  GRADEID, ");
		insertJobProfileGradeSQL.append("  CREATIONTIMESTAMP, ");
		insertJobProfileGradeSQL.append("  AUDITORID, ");
		insertJobProfileGradeSQL.append("  AUDITTIMESTAMP ");
		insertJobProfileGradeSQL.append(")  ");
		insertJobProfileGradeSQL.append("VALUES  ");
		insertJobProfileGradeSQL.append("(  ");
		insertJobProfileGradeSQL.append("  ^, ");
		insertJobProfileGradeSQL.append("  ^, ");
		insertJobProfileGradeSQL.append("  ^, ");
		insertJobProfileGradeSQL.append("  ^, ");
		insertJobProfileGradeSQL.append("  ^, ");
		insertJobProfileGradeSQL.append("  ^ ");
		insertJobProfileGradeSQL.append(")  ");
		// Get delete JobProfileGrade SQL.
		deleteJobProfileGradeSQL = new StringBuffer();
		deleteJobProfileGradeSQL.append("UPDATE JOBPROFILEGRADE ");
		deleteJobProfileGradeSQL.append("SET ACTIVE = FALSE, ");
		deleteJobProfileGradeSQL.append("    AUDITORID = ^, ");
		deleteJobProfileGradeSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteJobProfileGradeSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteJobProfileGradeSQL.append("WHERE JOBPROFILEGRADEID = ^ ");
		deleteJobProfileGradeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select JobProfileGradeUsers SQL.
		selectJobProfileGradeUsersSQL = new StringBuffer();
		selectJobProfileGradeUsersSQL.append("SELECT A.JOBPROFILEGRADEID, ");
		selectJobProfileGradeUsersSQL.append("       A.JOBPROFILEID, ");
		selectJobProfileGradeUsersSQL.append("       A.GRADEID, ");
		selectJobProfileGradeUsersSQL.append("       A.CREATIONTIMESTAMP, ");
		selectJobProfileGradeUsersSQL.append("       A.AUDITORID, ");
		selectJobProfileGradeUsersSQL.append("       A.AUDITTIMESTAMP, ");
		selectJobProfileGradeUsersSQL.append("       A.ACTIVE, ");
		selectJobProfileGradeUsersSQL.append("       A.NOOFCHANGES, ");
		selectJobProfileGradeUsersSQL.append("       G.NAME AS GRADENAME ");
		selectJobProfileGradeUsersSQL.append("FROM JOBPROFILEGRADE A, ");
		selectJobProfileGradeUsersSQL.append("     GRADE G ");
		selectJobProfileGradeUsersSQL.append("WHERE G.GRADEID = A.GRADEID ");
		selectJobProfileGradeUsersSQL.append("AND G.ACTIVE = TRUE ");
		// Get select JobProfileGradeUsers for JobProfile SQL.
		selectJobProfileGradeUsersForJobProfileSQL = new StringBuffer(selectJobProfileGradeUsersSQL);
		selectJobProfileGradeUsersForJobProfileSQL.append("AND A.JOBPROFILEID = ^ ");
		selectJobProfileGradeUsersForJobProfileSQL.append("AND A.ACTIVE = TRUE ");
		selectJobProfileGradeUsersForJobProfileSQL.append("ORDER BY G.NAME ");
	}

	public int insertJobProfileGrade(JobProfileGrade jobProfileGrade, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertJobProfileGradeSQL.toString());
		// Replace the parameters with supplied values.
		jobProfileGrade.setJobProfileGradeId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "jobProfileGrade"));
		Utilities.replace(sql, jobProfileGrade.getJobProfileGradeId());
		Utilities.replace(sql, jobProfileGrade.getJobProfileId());
		Utilities.replace(sql, jobProfileGrade.getGradeId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteJobProfileGrade(Integer jobProfileGradeId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteJobProfileGradeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobProfileGradeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<JobProfileGradeUser> getJobProfileGradeUsersForJobProfile(Integer jobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileGradeUsersForJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfileGradeUser.class.getName());

	}

}
