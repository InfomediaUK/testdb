package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.JobSubFamily;
import com.helmet.bean.JobSubFamilyEntity;
import com.helmet.persistence.JobSubFamilyDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultJobSubFamilyDAO extends JdbcDaoSupport implements JobSubFamilyDAO {

	private static StringBuffer insertJobSubFamilySQL;

	private static StringBuffer updateJobSubFamilySQL;

	private static StringBuffer updateJobSubFamilyDisplayOrderSQL;

	private static StringBuffer deleteJobSubFamilySQL;

	private static StringBuffer selectJobSubFamiliesSQL;

	private static StringBuffer selectJobSubFamilySQL;

	private static StringBuffer selectJobSubFamilyForNameSQL;

	private static StringBuffer selectJobSubFamilyForCodeSQL;

	private static StringBuffer selectJobSubFamiliesForJobFamilySQL;

	private static StringBuffer selectActiveJobSubFamiliesForJobFamilySQL;

	public static void init() {
		// Get insert JobSubFamily SQL.
		insertJobSubFamilySQL = new StringBuffer();
		insertJobSubFamilySQL.append("INSERT INTO JOBSUBFAMILY ");
		insertJobSubFamilySQL.append("(  ");
		insertJobSubFamilySQL.append("  JOBSUBFAMILYID, ");
		insertJobSubFamilySQL.append("  JOBFAMILYID, ");
		insertJobSubFamilySQL.append("  NAME, ");
		insertJobSubFamilySQL.append("  CODE, ");
		insertJobSubFamilySQL.append("  CREATIONTIMESTAMP, ");
		insertJobSubFamilySQL.append("  AUDITORID, ");
		insertJobSubFamilySQL.append("  AUDITTIMESTAMP ");
		insertJobSubFamilySQL.append(")  ");
		insertJobSubFamilySQL.append("VALUES  ");
		insertJobSubFamilySQL.append("(  ");
		insertJobSubFamilySQL.append("  ^, ");
		insertJobSubFamilySQL.append("  ^, ");
		insertJobSubFamilySQL.append("  ^, ");
		insertJobSubFamilySQL.append("  ^, ");
		insertJobSubFamilySQL.append("  ^, ");
		insertJobSubFamilySQL.append("  ^, ");
		insertJobSubFamilySQL.append("  ^ ");
		insertJobSubFamilySQL.append(")  ");
		// Get update JobSubFamily SQL.
		updateJobSubFamilySQL = new StringBuffer();
		updateJobSubFamilySQL.append("UPDATE JOBSUBFAMILY ");
		updateJobSubFamilySQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateJobSubFamilySQL.append("     JOBFAMILYID = ^, ");
		updateJobSubFamilySQL.append("     NAME = ^, ");
		updateJobSubFamilySQL.append("     CODE = ^, ");
		updateJobSubFamilySQL.append("     AUDITORID = ^, ");
		updateJobSubFamilySQL.append("     AUDITTIMESTAMP = ^ ");
		updateJobSubFamilySQL.append("WHERE JOBSUBFAMILYID = ^ ");
		updateJobSubFamilySQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateJobSubFamilyDisplayOrder SQL.
		updateJobSubFamilyDisplayOrderSQL = new StringBuffer();
		updateJobSubFamilyDisplayOrderSQL.append("UPDATE JOBSUBFAMILY ");
		updateJobSubFamilyDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateJobSubFamilyDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateJobSubFamilyDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateJobSubFamilyDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateJobSubFamilyDisplayOrderSQL.append("WHERE JOBSUBFAMILYID = ^ ");
		updateJobSubFamilyDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete JobSubFamily SQL.
		deleteJobSubFamilySQL = new StringBuffer();
		deleteJobSubFamilySQL.append("UPDATE JOBSUBFAMILY ");
		deleteJobSubFamilySQL.append("SET ACTIVE = FALSE, ");
		deleteJobSubFamilySQL.append("    AUDITORID = ^, ");
		deleteJobSubFamilySQL.append("    AUDITTIMESTAMP = ^, ");
		deleteJobSubFamilySQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteJobSubFamilySQL.append("WHERE JOBSUBFAMILYID = ^ ");
		deleteJobSubFamilySQL.append("AND   NOOFCHANGES = ^ ");
		// Get select JobSubFamilys SQL.
		selectJobSubFamiliesSQL = new StringBuffer();
		selectJobSubFamiliesSQL.append("SELECT JOBSUBFAMILYID, ");
		selectJobSubFamiliesSQL.append("       JOBFAMILYID, ");
		selectJobSubFamiliesSQL.append("       NAME, ");
		selectJobSubFamiliesSQL.append("       CODE, ");
		selectJobSubFamiliesSQL.append("       DISPLAYORDER, ");
		selectJobSubFamiliesSQL.append("       CREATIONTIMESTAMP, ");
		selectJobSubFamiliesSQL.append("       AUDITORID, ");
		selectJobSubFamiliesSQL.append("       AUDITTIMESTAMP, ");
		selectJobSubFamiliesSQL.append("       ACTIVE, ");
		selectJobSubFamiliesSQL.append("       NOOFCHANGES ");
		selectJobSubFamiliesSQL.append("FROM JOBSUBFAMILY ");
		// Get select JobSubFamily SQL.
		selectJobSubFamilySQL = new StringBuffer(selectJobSubFamiliesSQL);
		selectJobSubFamilySQL.append("WHERE JOBSUBFAMILYID = ^ ");
		// Get select JobSubFamiliesForJobFamily SQL.
		selectJobSubFamiliesForJobFamilySQL = new StringBuffer(selectJobSubFamiliesSQL);
		selectJobSubFamiliesForJobFamilySQL.append("WHERE JOBFAMILYID = ^ ");
		// Get select Active JobSubFamiliesForJobFamily SQL.
		selectActiveJobSubFamiliesForJobFamilySQL = new StringBuffer(selectJobSubFamiliesForJobFamilySQL);
		selectActiveJobSubFamiliesForJobFamilySQL.append("AND ACTIVE = TRUE ");
		// Get select JobSubFamily For Name SQL.
		selectJobSubFamilyForNameSQL = new StringBuffer(selectActiveJobSubFamiliesForJobFamilySQL);
		selectJobSubFamilyForNameSQL.append("AND NAME = ^ ");
		// Get select JobSubFamily For Code SQL.
		selectJobSubFamilyForCodeSQL = new StringBuffer(selectActiveJobSubFamiliesForJobFamilySQL);
		selectJobSubFamilyForCodeSQL.append("AND CODE = ^ ");

		selectJobSubFamiliesForJobFamilySQL.append("ORDER BY DISPLAYORDER, NAME ");
		selectActiveJobSubFamiliesForJobFamilySQL.append("ORDER BY DISPLAYORDER, NAME ");
	}

	public int insertJobSubFamily(JobSubFamily jobSubFamily,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertJobSubFamilySQL.toString());
		// Replace the parameters with supplied values.
		jobSubFamily.setJobSubFamilyId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "jobSubFamily"));
		Utilities.replace(sql, jobSubFamily.getJobSubFamilyId());
		Utilities.replace(sql, jobSubFamily.getJobFamilyId());
		Utilities.replaceAndQuote(sql, jobSubFamily.getName());
		Utilities.replaceAndQuote(sql, jobSubFamily.getCode());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateJobSubFamily(JobSubFamily jobSubFamily,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateJobSubFamilySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobSubFamily.getJobFamilyId());
		Utilities.replaceAndQuote(sql, jobSubFamily.getName());
		Utilities.replaceAndQuote(sql, jobSubFamily.getCode());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobSubFamily.getJobSubFamilyId());
		Utilities.replace(sql, jobSubFamily.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteJobSubFamily(Integer jobSubFamilyId,
			Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteJobSubFamilySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobSubFamilyId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public JobSubFamily getJobSubFamily(Integer jobSubFamilyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobSubFamilySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobSubFamilyId);
		return (JobSubFamily) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobSubFamily.class.getName());
	}

	public JobSubFamily getJobSubFamilyForName(Integer jobFamilyId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobSubFamilyForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobFamilyId);
		Utilities.replaceAndQuote(sql, name);
		return (JobSubFamily) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobSubFamily.class.getName());
	}

	public JobSubFamily getJobSubFamilyForCode(Integer jobFamilyId, String code) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobSubFamilyForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobFamilyId);
		Utilities.replaceAndQuote(sql, code);
		return (JobSubFamily) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobSubFamily.class.getName());
	}

	public JobSubFamilyEntity getJobSubFamilyEntity(Integer jobSubFamilyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobSubFamilySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobSubFamilyId);
		return (JobSubFamilyEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobSubFamilyEntity.class.getName());
	}

	public List<JobSubFamily> getJobSubFamiliesForJobFamily(Integer jobFamilyId, boolean showOnlyActive) {
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveJobSubFamiliesForJobFamilySQL.toString());	
		}
		else {
			sql = new StringBuffer(selectJobSubFamiliesForJobFamilySQL.toString());	
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobFamilyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				JobSubFamily.class.getName());
	}

	public int updateJobSubFamilyDisplayOrder(Integer jobSubFamilyId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateJobSubFamilyDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobSubFamilyId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

}
