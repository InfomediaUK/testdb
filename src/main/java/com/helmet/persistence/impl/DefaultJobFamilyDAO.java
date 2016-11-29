package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.JobFamily;
import com.helmet.bean.JobFamilyEntity;
import com.helmet.persistence.JobFamilyDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultJobFamilyDAO extends JdbcDaoSupport implements JobFamilyDAO {

	private static StringBuffer insertJobFamilySQL;

	private static StringBuffer updateJobFamilySQL;

	private static StringBuffer updateJobFamilyDisplayOrderSQL;

	private static StringBuffer deleteJobFamilySQL;

	private static StringBuffer selectJobFamilySQL;

	private static StringBuffer selectJobFamilyForNameSQL;

	private static StringBuffer selectJobFamilyForCodeSQL;

	private static StringBuffer selectJobFamiliesSQL;

	private static StringBuffer selectJobFamiliesForClientSQL;

	private static StringBuffer selectActiveJobFamiliesForClientSQL;

	public static void init() {
		// Get insert JobFamily SQL.
		insertJobFamilySQL = new StringBuffer();
		insertJobFamilySQL.append("INSERT INTO JOBFAMILY ");
		insertJobFamilySQL.append("(  ");
		insertJobFamilySQL.append("  JOBFAMILYID, ");
		insertJobFamilySQL.append("  CLIENTID, ");
		insertJobFamilySQL.append("  NAME, ");
		insertJobFamilySQL.append("  CODE, ");
		insertJobFamilySQL.append("  CREATIONTIMESTAMP, ");
		insertJobFamilySQL.append("  AUDITORID, ");
		insertJobFamilySQL.append("  AUDITTIMESTAMP ");
		insertJobFamilySQL.append(")  ");
		insertJobFamilySQL.append("VALUES  ");
		insertJobFamilySQL.append("(  ");
		insertJobFamilySQL.append("  ^, ");
		insertJobFamilySQL.append("  ^, ");
		insertJobFamilySQL.append("  ^, ");
		insertJobFamilySQL.append("  ^, ");
		insertJobFamilySQL.append("  ^, ");
		insertJobFamilySQL.append("  ^, ");
		insertJobFamilySQL.append("  ^ ");
		insertJobFamilySQL.append(")  ");
		// Get update JobFamily SQL.
		updateJobFamilySQL = new StringBuffer();
		updateJobFamilySQL.append("UPDATE JOBFAMILY ");
		updateJobFamilySQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateJobFamilySQL.append("     CLIENTID = ^, ");
		updateJobFamilySQL.append("     NAME = ^, ");
		updateJobFamilySQL.append("     CODE = ^, ");
		updateJobFamilySQL.append("     AUDITORID = ^, ");
		updateJobFamilySQL.append("     AUDITTIMESTAMP = ^ ");
		updateJobFamilySQL.append("WHERE JOBFAMILYID = ^ ");
		updateJobFamilySQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateJobFamilyDisplayOrder SQL.
		updateJobFamilyDisplayOrderSQL = new StringBuffer();
		updateJobFamilyDisplayOrderSQL.append("UPDATE JOBFAMILY ");
		updateJobFamilyDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateJobFamilyDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateJobFamilyDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateJobFamilyDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateJobFamilyDisplayOrderSQL.append("WHERE JOBFAMILYID = ^ ");
		updateJobFamilyDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete JobFamily SQL.
		deleteJobFamilySQL = new StringBuffer();
		deleteJobFamilySQL.append("UPDATE JOBFAMILY ");
		deleteJobFamilySQL.append("SET ACTIVE = FALSE, ");
		deleteJobFamilySQL.append("    AUDITORID = ^, ");
		deleteJobFamilySQL.append("    AUDITTIMESTAMP = ^, ");
		deleteJobFamilySQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteJobFamilySQL.append("WHERE JOBFAMILYID = ^ ");
		deleteJobFamilySQL.append("AND   NOOFCHANGES = ^ ");
		// Get select JobFamilys SQL.
		selectJobFamiliesSQL = new StringBuffer();
		selectJobFamiliesSQL.append("SELECT JOBFAMILYID, ");
		selectJobFamiliesSQL.append("       CLIENTID, ");
		selectJobFamiliesSQL.append("       NAME, ");
		selectJobFamiliesSQL.append("       CODE, ");
		selectJobFamiliesSQL.append("       DISPLAYORDER, ");
		selectJobFamiliesSQL.append("       CREATIONTIMESTAMP, ");
		selectJobFamiliesSQL.append("       AUDITORID, ");
		selectJobFamiliesSQL.append("       AUDITTIMESTAMP, ");
		selectJobFamiliesSQL.append("       ACTIVE, ");
		selectJobFamiliesSQL.append("       NOOFCHANGES ");
		selectJobFamiliesSQL.append("FROM JOBFAMILY ");
		// Get select JobFamily SQL.
		selectJobFamilySQL = new StringBuffer(selectJobFamiliesSQL);
		selectJobFamilySQL.append("WHERE JOBFAMILYID = ^ ");
		// Get select JobFamilies for Client SQL.
		selectJobFamiliesForClientSQL = new StringBuffer(selectJobFamiliesSQL);
		selectJobFamiliesForClientSQL.append("WHERE CLIENTID = ^ ");
		// Get select Active JobFamilies for Client SQL.
		selectActiveJobFamiliesForClientSQL = new StringBuffer(selectJobFamiliesForClientSQL);
		selectActiveJobFamiliesForClientSQL.append("AND ACTIVE = TRUE ");
		// Get select JobFamily for Name SQL.
		selectJobFamilyForNameSQL = new StringBuffer(selectActiveJobFamiliesForClientSQL);
		selectJobFamilyForNameSQL.append("AND NAME = ^ ");
		// Get select JobFamily for Code SQL.
		selectJobFamilyForCodeSQL = new StringBuffer(selectActiveJobFamiliesForClientSQL);
		selectJobFamilyForCodeSQL.append("AND CODE = ^ ");

		selectJobFamiliesForClientSQL.append("ORDER BY DISPLAYORDER, NAME ");
		selectActiveJobFamiliesForClientSQL.append("ORDER BY DISPLAYORDER, NAME ");

	}

	public int insertJobFamily(JobFamily jobFamily,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertJobFamilySQL.toString());
		// Replace the parameters with supplied values.
		jobFamily.setJobFamilyId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "jobFamily"));
		Utilities.replace(sql, jobFamily.getJobFamilyId());
		Utilities.replace(sql, jobFamily.getClientId());
		Utilities.replaceAndQuote(sql, jobFamily.getName());
		Utilities.replaceAndQuote(sql, jobFamily.getCode());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateJobFamily(JobFamily jobFamily,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateJobFamilySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobFamily.getClientId());
		Utilities.replaceAndQuote(sql, jobFamily.getName());
		Utilities.replaceAndQuote(sql, jobFamily.getCode());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobFamily.getJobFamilyId());
		Utilities.replace(sql, jobFamily.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteJobFamily(Integer jobFamilyId,
			Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteJobFamilySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobFamilyId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public JobFamily getJobFamily(Integer jobFamilyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobFamilySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobFamilyId);
		return (JobFamily) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobFamily.class.getName());
	}

	public JobFamily getJobFamilyForName(Integer clientId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobFamilyForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, name);
		return (JobFamily) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobFamily.class.getName());
	}

	public JobFamily getJobFamilyForCode(Integer clientId, String code) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobFamilyForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, code);
		return (JobFamily) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobFamily.class.getName());
	}

	public JobFamilyEntity getJobFamilyEntity(Integer jobFamilyId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobFamilySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobFamilyId);
		return (JobFamilyEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobFamilyEntity.class.getName());
	}

	public List<JobFamily> getJobFamiliesForClient(Integer clientId, boolean showOnlyActive) {
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveJobFamiliesForClientSQL.toString());	
		}
		else {
			sql = new StringBuffer(selectJobFamiliesForClientSQL.toString());	
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				JobFamily.class.getName());
	}

	public int updateJobFamilyDisplayOrder(Integer jobFamilyId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateJobFamilyDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobFamilyId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

}
