package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.JobProfile;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.JobProfileUserEntity;
import com.helmet.persistence.JobProfileDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultJobProfileDAO extends JdbcDaoSupport implements	JobProfileDAO {

	private static StringBuffer insertJobProfileSQL;

	private static StringBuffer updateJobProfileSQL;

	private static StringBuffer updateJobProfileDisplayOrderSQL;

	private static StringBuffer deleteJobProfileSQL;

	private static StringBuffer selectJobProfilesSQL;

	private static StringBuffer selectJobProfilesForJobSubFamilySQL;

	private static StringBuffer selectActiveJobProfilesForJobSubFamilySQL;

	private static StringBuffer selectJobProfileUsersSQL;

	private static StringBuffer selectJobProfileUserSQL;

	private static StringBuffer selectJobProfileUsersNotForLocationSQL;

	private static StringBuffer selectJobProfileUsersForManagerSQL;

	private static StringBuffer selectJobProfileUsersForManagerForLocationSQL;

	private static StringBuffer selectJobProfileUsersForManagerForSiteSQL;

	private static StringBuffer selectJobProfileUsersForAgencySQL;

//	private static StringBuffer selectJobProfileUsersForAgencyForLocationSQL;
//
//	private static StringBuffer selectJobProfileUsersForAgencyForSiteSQL;

	private static StringBuffer selectJobProfileSQL;

	private static StringBuffer selectJobProfileForNameSQL;

	private static StringBuffer selectJobProfileForCodeSQL;

	private static StringBuffer selectActiveJobProfileForNameSQL;

  private static StringBuffer selectJobProfileUsersForNhsAssignmentSQL;

	public static void init() {
		// Get insert JobProfile SQL.
		insertJobProfileSQL = new StringBuffer();
		insertJobProfileSQL.append("INSERT INTO JOBPROFILE ");
		insertJobProfileSQL.append("(  ");
		insertJobProfileSQL.append("  JOBPROFILEID, ");
		insertJobProfileSQL.append("  JOBSUBFAMILYID, ");
		insertJobProfileSQL.append("  NAME, ");
		insertJobProfileSQL.append("  CODE, ");
		insertJobProfileSQL.append("  RATE, ");
		insertJobProfileSQL.append("  AUTOFILL, ");
		insertJobProfileSQL.append("  DOCUMENTURL, ");
    // NEW -->
    insertJobProfileSQL.append("  NHSASSIGNMENT, ");
    // <-- NEW
		insertJobProfileSQL.append("  CREATIONTIMESTAMP, ");
		insertJobProfileSQL.append("  AUDITORID, ");
		insertJobProfileSQL.append("  AUDITTIMESTAMP ");
		insertJobProfileSQL.append(")  ");
		insertJobProfileSQL.append("VALUES  ");
		insertJobProfileSQL.append("(  ");
		insertJobProfileSQL.append("  ^, ");
		insertJobProfileSQL.append("  ^, ");
		insertJobProfileSQL.append("  ^, ");
		insertJobProfileSQL.append("  ^, ");
		insertJobProfileSQL.append("  ^, ");
		insertJobProfileSQL.append("  ^, ");
		insertJobProfileSQL.append("  ^, ");
    // NEW -->
    insertJobProfileSQL.append("  ^, ");
    // <-- NEW
		insertJobProfileSQL.append("  ^, ");
		insertJobProfileSQL.append("  ^, ");
		insertJobProfileSQL.append("  ^ ");
		insertJobProfileSQL.append(")  ");
		// Get update JobProfile SQL.
		updateJobProfileSQL = new StringBuffer();
		updateJobProfileSQL.append("UPDATE JOBPROFILE ");
		updateJobProfileSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateJobProfileSQL.append("     JOBSUBFAMILYID = ^, ");
		updateJobProfileSQL.append("     NAME = ^, ");
		updateJobProfileSQL.append("     CODE = ^, ");
		updateJobProfileSQL.append("     RATE = ^, ");
		updateJobProfileSQL.append("     AUTOFILL = ^, ");
		updateJobProfileSQL.append("     DOCUMENTURL = ^, ");
    // NEW -->
    updateJobProfileSQL.append("     NHSASSIGNMENT = ^, ");
    // <-- NEW
		updateJobProfileSQL.append("     AUDITORID = ^, ");
		updateJobProfileSQL.append("     AUDITTIMESTAMP = ^ ");
		updateJobProfileSQL.append("WHERE JOBPROFILEID = ^ ");
		updateJobProfileSQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateJobProfileDisplayOrder SQL.
		updateJobProfileDisplayOrderSQL = new StringBuffer();
		updateJobProfileDisplayOrderSQL.append("UPDATE JOBPROFILE ");
		updateJobProfileDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateJobProfileDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateJobProfileDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateJobProfileDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateJobProfileDisplayOrderSQL.append("WHERE JOBPROFILEID = ^ ");
		updateJobProfileDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete JobProfile SQL.
		deleteJobProfileSQL = new StringBuffer();
		deleteJobProfileSQL.append("UPDATE JOBPROFILE ");
		deleteJobProfileSQL.append("SET ACTIVE = FALSE, ");
		deleteJobProfileSQL.append("    AUDITORID = ^, ");
		deleteJobProfileSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteJobProfileSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteJobProfileSQL.append("WHERE JOBPROFILEID = ^ ");
		deleteJobProfileSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select JobProfiles SQL.
		selectJobProfilesSQL = new StringBuffer();
		selectJobProfilesSQL.append("SELECT J.JOBPROFILEID, ");
		selectJobProfilesSQL.append("       J.JOBSUBFAMILYID, ");
		selectJobProfilesSQL.append("       J.NAME, ");
		selectJobProfilesSQL.append("       J.CODE, ");
		selectJobProfilesSQL.append("       J.RATE, ");
		selectJobProfilesSQL.append("       J.AUTOFILL, ");
		selectJobProfilesSQL.append("       J.DOCUMENTURL, ");
    // NEW -->
    selectJobProfilesSQL.append("       J.NHSASSIGNMENT, ");
    // <-- NEW
		selectJobProfilesSQL.append("       J.DISPLAYORDER, ");
		selectJobProfilesSQL.append("       J.CREATIONTIMESTAMP, ");
		selectJobProfilesSQL.append("       J.AUDITORID, ");
		selectJobProfilesSQL.append("       J.AUDITTIMESTAMP, ");
		selectJobProfilesSQL.append("       J.ACTIVE, ");
		selectJobProfilesSQL.append("       J.NOOFCHANGES ");
		// Get select Job Profile Users SQL.
		selectJobProfileUsersSQL = new StringBuffer(selectJobProfilesSQL);
		selectJobProfileUsersSQL.append(" , ");
		selectJobProfileUsersSQL.append(" JF.CLIENTID, ");
		selectJobProfileUsersSQL.append(" JF.JOBFAMILYID, ");
		selectJobProfileUsersSQL.append(" JF.NAME AS JOBFAMILYNAME, ");
		selectJobProfileUsersSQL.append(" JF.CODE AS JOBFAMILYCODE, ");
		selectJobProfileUsersSQL.append(" JF.DISPLAYORDER AS JOBFAMILYDISPLAYORDER, ");
		selectJobProfileUsersSQL.append(" JSF.NAME AS JOBSUBFAMILYNAME, ");
		selectJobProfileUsersSQL.append(" JSF.CODE AS JOBSUBFAMILYCODE, ");
		selectJobProfileUsersSQL.append(" JSF.DISPLAYORDER AS JOBSUBFAMILYDISPLAYORDER ");
		selectJobProfileUsersSQL.append("FROM JOBPROFILE J, ");
		selectJobProfileUsersSQL.append("     JOBSUBFAMILY JSF, ");
		selectJobProfileUsersSQL.append("     JOBFAMILY JF ");
        
		//
		selectJobProfileUsersSQL.append("WHERE J.ACTIVE = TRUE ");
		selectJobProfileUsersSQL.append("AND JSF.JOBSUBFAMILYID = J.JOBSUBFAMILYID ");
		selectJobProfileUsersSQL.append("AND JSF.ACTIVE = TRUE ");
		selectJobProfileUsersSQL.append("AND JF.JOBFAMILYID = JSF.JOBFAMILYID ");
		selectJobProfileUsersSQL.append("AND JF.ACTIVE = TRUE ");
    
    // NEW -->
    // Get select (Active) JobProfiles for NHS Assignment SQL.
    selectJobProfileUsersForNhsAssignmentSQL = new StringBuffer(selectJobProfileUsersSQL);
    selectJobProfileUsersForNhsAssignmentSQL.append("AND JF.CLIENTID = ^ ");
    selectJobProfileUsersForNhsAssignmentSQL.append("AND J.NHSASSIGNMENT = ^ ");
    // <-- NEW    

        // Get select Job Profile User SQL.
		selectJobProfileUserSQL = new StringBuffer(selectJobProfileUsersSQL);
		selectJobProfileUserSQL.append("AND J.JOBPROFILEID = ^ ");
    
    // Get select JobProfileUsers for Manager for Location SQL.
		selectJobProfileUsersForManagerSQL = new StringBuffer(selectJobProfileUsersSQL);

//		selectJobProfileUsersForManagerSQL.append(" , ");
//		selectJobProfileUsersForManagerSQL.append(" LOCATIONMANAGERJOBPROFILE LMJP, ");
//		selectJobProfileUsersForManagerSQL.append(" LOCATIONJOBPROFILE LJP, ");
//		selectJobProfileUsersForManagerSQL.append(" LOCATION L ");
//		selectJobProfileUsersForManagerSQL.append("WHERE J.ACTIVE = TRUE ");
//		selectJobProfileUsersForManagerSQL.append("AND JSF.JOBSUBFAMILYID = J.JOBSUBFAMILYID ");
//		selectJobProfileUsersForManagerSQL.append("AND JSF.ACTIVE = TRUE ");
//		selectJobProfileUsersForManagerSQL.append("AND JF.JOBFAMILYID = JSF.JOBFAMILYID ");
//		selectJobProfileUsersForManagerSQL.append("AND JF.ACTIVE = TRUE ");
//		selectJobProfileUsersForManagerSQL.append("AND LJP.JOBPROFILEID = J.JOBPROFILEID ");
//		selectJobProfileUsersForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
//		selectJobProfileUsersForManagerSQL.append("AND L.LOCATIONID = L.LOCATIONID ");
//		selectJobProfileUsersForManagerSQL.append("AND L.ACTIVE = TRUE ");
//		selectJobProfileUsersForManagerSQL.append("AND LMJP.JOBPROFILEID = LJP.JOBPROFILEID ");
//		selectJobProfileUsersForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
//		selectJobProfileUsersForManagerSQL.append("AND LMJP.LOCATIONID = LJP.LOCATIONID ");
//		selectJobProfileUsersForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
//		selectJobProfileUsersForManagerSQL.append("AND LMJP.MANAGERID = ^ ");

		selectJobProfileUsersForManagerSQL.append("AND EXISTS ");
	    selectJobProfileUsersForManagerSQL.append("( ");
		selectJobProfileUsersForManagerSQL.append(" SELECT NULL ");
		selectJobProfileUsersForManagerSQL.append(" FROM LOCATIONMANAGERJOBPROFILE LMJP, ");  
		selectJobProfileUsersForManagerSQL.append("      LOCATIONJOBPROFILE LJP, ");  
		selectJobProfileUsersForManagerSQL.append("      LOCATION L "); 
		selectJobProfileUsersForManagerSQL.append(" WHERE LJP.JOBPROFILEID = J.JOBPROFILEID "); 
		selectJobProfileUsersForManagerSQL.append(" AND LJP.ACTIVE = TRUE "); 
		selectJobProfileUsersForManagerSQL.append(" AND L.LOCATIONID = L.LOCATIONID "); 
		selectJobProfileUsersForManagerSQL.append(" AND L.ACTIVE = TRUE "); 
		selectJobProfileUsersForManagerSQL.append(" AND LMJP.JOBPROFILEID = LJP.JOBPROFILEID "); 
		selectJobProfileUsersForManagerSQL.append(" AND LMJP.ACTIVE = TRUE "); 
		selectJobProfileUsersForManagerSQL.append(" AND LMJP.LOCATIONID = LJP.LOCATIONID "); 
		selectJobProfileUsersForManagerSQL.append(" AND LMJP.ACTIVE = TRUE "); 
		selectJobProfileUsersForManagerSQL.append(" AND LMJP.MANAGERID = ^ "); 
		
		// Get select JobProfileUsers for Manager for Location SQL.
		selectJobProfileUsersForManagerForLocationSQL = new StringBuffer(selectJobProfileUsersForManagerSQL);
		selectJobProfileUsersForManagerForLocationSQL.append("AND LMJP.LOCATIONID = ^ ");
		selectJobProfileUsersForManagerForLocationSQL.append(") ");
        // Get select JobProfileUsers for Manager for Site SQL.
		selectJobProfileUsersForManagerForSiteSQL = new StringBuffer(selectJobProfileUsersForManagerSQL);
		selectJobProfileUsersForManagerForSiteSQL.append("AND L.SITEID = ^ ");
		selectJobProfileUsersForManagerForSiteSQL.append(") ");

		selectJobProfileUsersForManagerSQL.append(") ");
		
		// Get select Job Profile Users for Agency SQL.
		selectJobProfileUsersForAgencySQL = new StringBuffer(selectJobProfileUsersSQL);
		selectJobProfileUsersForAgencySQL.append("AND J.ACTIVE = TRUE ");
		selectJobProfileUsersForAgencySQL.append("AND EXISTS ");
		selectJobProfileUsersForAgencySQL.append("( ");
		selectJobProfileUsersForAgencySQL.append(" SELECT NULL ");
		selectJobProfileUsersForAgencySQL.append(" FROM LOCATIONJOBPROFILE LJP, ");
		selectJobProfileUsersForAgencySQL.append(" LOCATIONVIEW LV, ");
//		selectJobProfileUsersForAgencySQL.append(" LOCATION L, ");
//		selectJobProfileUsersForAgencySQL.append(" SITE S, ");
//		selectJobProfileUsersForAgencySQL.append(" CLIENT C, ");
		selectJobProfileUsersForAgencySQL.append(" CLIENTAGENCY CA ");
		selectJobProfileUsersForAgencySQL.append(" WHERE LJP.JOBPROFILEID = J.JOBPROFILEID ");
		selectJobProfileUsersForAgencySQL.append(" AND LJP.ACTIVE = TRUE ");
		selectJobProfileUsersForAgencySQL.append(" AND LV.LOCATIONID = LJP.LOCATIONID ");
//		selectJobProfileUsersForAgencySQL.append(" AND L.LOCATIONID = LJP.LOCATIONID ");
//		selectJobProfileUsersForAgencySQL.append(" AND L.ACTIVE = TRUE ");
//		selectJobProfileUsersForAgencySQL.append(" AND S.SITEID = L.SITEID ");
//		selectJobProfileUsersForAgencySQL.append(" AND S.ACTIVE = TRUE ");
//		selectJobProfileUsersForAgencySQL.append(" AND C.CLIENTID = S.CLIENTID ");
//		selectJobProfileUsersForAgencySQL.append(" AND C.ACTIVE = TRUE ");
		selectJobProfileUsersForAgencySQL.append(" AND CA.CLIENTID = LV.CLIENTID ");
//		selectJobProfileUsersForAgencySQL.append(" AND CA.CLIENTID = C.CLIENTID ");
		selectJobProfileUsersForAgencySQL.append(" AND CA.ACTIVE = TRUE ");
		selectJobProfileUsersForAgencySQL.append(" AND CA.CLIENTID = JF.CLIENTID ");
		selectJobProfileUsersForAgencySQL.append(" AND CA.AGENCYID = ^ ");

		selectJobProfileUsersForAgencySQL.append(" AND ( ");
		selectJobProfileUsersForAgencySQL.append("  ^ IS NULL "); //
		selectJobProfileUsersForAgencySQL.append(" OR ");
		selectJobProfileUsersForAgencySQL.append("  LV.CLIENTID = ^ "); //
//		selectJobProfileUsersForAgencySQL.append("  C.CLIENTID = ^ "); //
		selectJobProfileUsersForAgencySQL.append(" ) ");
		
		selectJobProfileUsersForAgencySQL.append(" AND ( ");
		selectJobProfileUsersForAgencySQL.append("  ^ IS NULL "); //
		selectJobProfileUsersForAgencySQL.append(" OR ");
		selectJobProfileUsersForAgencySQL.append("  LV.SITEID = ^ "); //
//		selectJobProfileUsersForAgencySQL.append("  S.SITEID = ^ "); //
		selectJobProfileUsersForAgencySQL.append(" ) ");
		
		selectJobProfileUsersForAgencySQL.append(" AND ( ");
		selectJobProfileUsersForAgencySQL.append("  ^ IS NULL "); //
		selectJobProfileUsersForAgencySQL.append(" OR ");
		selectJobProfileUsersForAgencySQL.append("  LV.LOCATIONID = ^ "); //
//		selectJobProfileUsersForAgencySQL.append("  L.LOCATIONID = ^ "); //
		selectJobProfileUsersForAgencySQL.append(" ) ");
		
//		selectJobProfileUsersForAgencyForLocationSQL = new StringBuffer(selectJobProfileUsersForAgencySQL);
//		selectJobProfileUsersForAgencyForLocationSQL.append("AND L.LOCATIONID = ^ ");
//		selectJobProfileUsersForAgencyForLocationSQL.append(") ");
//
//		selectJobProfileUsersForAgencyForSiteSQL = new StringBuffer(selectJobProfileUsersForAgencySQL);
//		selectJobProfileUsersForAgencyForSiteSQL.append("AND S.SITEID = ^ ");
//		selectJobProfileUsersForAgencyForSiteSQL.append(") ");

		selectJobProfileUsersForAgencySQL.append(") ");
		
		// Get select Job Profile Users NOT for Location SQL.
		selectJobProfileUsersNotForLocationSQL = new StringBuffer(selectJobProfileUsersSQL);
		selectJobProfileUsersNotForLocationSQL.append("AND JF.CLIENTID = ^ ");
		selectJobProfileUsersNotForLocationSQL.append("AND NOT EXISTS ");
		selectJobProfileUsersNotForLocationSQL.append("( ");
		selectJobProfileUsersNotForLocationSQL.append(" SELECT NULL ");
		selectJobProfileUsersNotForLocationSQL.append(" FROM LOCATIONJOBPROFILE LJP ");
		selectJobProfileUsersNotForLocationSQL.append(" WHERE LJP.LOCATIONID = ^ ");
		selectJobProfileUsersNotForLocationSQL.append(" AND LJP.ACTIVE = TRUE ");
		selectJobProfileUsersNotForLocationSQL.append(" AND J.JOBPROFILEID = LJP.JOBPROFILEID ");
		selectJobProfileUsersNotForLocationSQL.append(") ");
		selectJobProfileUsersNotForLocationSQL.append("ORDER BY JF.DISPLAYORDER, JF.NAME, ");
		selectJobProfileUsersNotForLocationSQL.append("         JSF.DISPLAYORDER, JSF.NAME, ");
		selectJobProfileUsersNotForLocationSQL.append("         J.DISPLAYORDER, J.NAME ");
		//
		selectJobProfilesSQL.append("FROM JOBPROFILE J ");
		// Get select JobProfile SQL.
		selectJobProfileSQL = new StringBuffer(selectJobProfilesSQL);
		selectJobProfileSQL.append("WHERE J.JOBPROFILEID = ^ ");
		// Get select JobProfiles for JobSubFamily SQL.
		selectJobProfilesForJobSubFamilySQL = new StringBuffer(selectJobProfilesSQL);
		selectJobProfilesForJobSubFamilySQL.append("WHERE J.JOBSUBFAMILYID = ^ ");

		// Get select Active JobProfiles for JobSubFamily SQL.
		selectActiveJobProfilesForJobSubFamilySQL = new StringBuffer(selectJobProfilesForJobSubFamilySQL);
		selectActiveJobProfilesForJobSubFamilySQL.append("AND J.ACTIVE = TRUE ");

		// Get select JobProfile for Name SQL.
		selectJobProfileForNameSQL = new StringBuffer(selectActiveJobProfilesForJobSubFamilySQL);
		selectJobProfileForNameSQL.append("AND J.NAME = ^ ");
		// Get select JobProfile for Code SQL.
		selectJobProfileForCodeSQL = new StringBuffer(selectActiveJobProfilesForJobSubFamilySQL);
		selectJobProfileForCodeSQL.append("AND J.CODE = ^ ");

		//
		selectJobProfilesForJobSubFamilySQL.append("ORDER BY J.DISPLAYORDER, J.NAME ");
		//
		selectActiveJobProfilesForJobSubFamilySQL.append("ORDER BY J.DISPLAYORDER, J.NAME ");

		//
		selectJobProfileUsersForManagerSQL.append("ORDER BY JF.DISPLAYORDER, JF.NAME, ");
		selectJobProfileUsersForManagerSQL.append("         JSF.DISPLAYORDER, JSF.NAME, ");
		selectJobProfileUsersForManagerSQL.append("         J.DISPLAYORDER, J.NAME ");

		selectJobProfileUsersForManagerForLocationSQL.append("ORDER BY JF.DISPLAYORDER, JF.NAME, ");
		selectJobProfileUsersForManagerForLocationSQL.append("         JSF.DISPLAYORDER, JSF.NAME, ");
		selectJobProfileUsersForManagerForLocationSQL.append("         J.DISPLAYORDER, J.NAME ");

		selectJobProfileUsersForManagerForSiteSQL.append("ORDER BY JF.DISPLAYORDER, JF.NAME, ");
		selectJobProfileUsersForManagerForSiteSQL.append("         JSF.DISPLAYORDER, JSF.NAME, ");
		selectJobProfileUsersForManagerForSiteSQL.append("         J.DISPLAYORDER, J.NAME ");

		//
		selectJobProfileUsersForAgencySQL.append("ORDER BY JF.DISPLAYORDER, JF.NAME, ");
		selectJobProfileUsersForAgencySQL.append("         JSF.DISPLAYORDER, JSF.NAME, ");
		selectJobProfileUsersForAgencySQL.append("         J.DISPLAYORDER, J.NAME ");

//		selectJobProfileUsersForAgencyForLocationSQL.append("ORDER BY JF.DISPLAYORDER, JF.NAME, ");
//		selectJobProfileUsersForAgencyForLocationSQL.append("         JSF.DISPLAYORDER, JSF.NAME, ");
//		selectJobProfileUsersForAgencyForLocationSQL.append("         J.DISPLAYORDER, J.NAME ");
//
//		selectJobProfileUsersForAgencyForSiteSQL.append("ORDER BY JF.DISPLAYORDER, JF.NAME, ");
//		selectJobProfileUsersForAgencyForSiteSQL.append("         JSF.DISPLAYORDER, JSF.NAME, ");
//		selectJobProfileUsersForAgencyForSiteSQL.append("         J.DISPLAYORDER, J.NAME ");

	}

	public List<JobProfile> getJobProfilesForJobSubFamily(
			Integer jobSubFamilyId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveJobProfilesForJobSubFamilySQL
					.toString());
		} else {
			sql = new StringBuffer(selectJobProfilesForJobSubFamilySQL
					.toString());
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobSubFamilyId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfile.class.getName());

	}

	public JobProfile getJobProfile(Integer jobProfileId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		return (JobProfile) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfile.class.getName());
	}

	public JobProfile getJobProfileForName(Integer jobSubFamilyId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobSubFamilyId);
		Utilities.replaceAndQuote(sql, name);
		return (JobProfile) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfile.class.getName());
	}

	public JobProfile getJobProfileForCode(Integer jobSubFamilyId, String code) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobSubFamilyId);
		Utilities.replaceAndQuote(sql, code);
		return (JobProfile) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfile.class.getName());
	}

	public JobProfileUser getJobProfileUser(Integer jobProfileId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		return (JobProfileUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfileUser.class.getName());
	}

	public JobProfileUserEntity getJobProfileUserEntity(Integer jobProfileId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfileId);
		return (JobProfileUserEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfileUserEntity.class.getName());
	}

	public JobProfile getJobProfile(Integer jobSubFamilyId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveJobProfileForNameSQL
				.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobSubFamilyId);
		Utilities.replaceAndQuote(sql, name);
		return (JobProfile) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfile.class.getName());
	}

	public int insertJobProfile(JobProfile jobProfile, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		jobProfile.setJobProfileId(UpdateHandler.getInstance().getId(
				getJdbcTemplate(), "jobProfile"));
		Utilities.replace(sql, jobProfile.getJobProfileId());
		Utilities.replace(sql, jobProfile.getJobSubFamilyId());
		Utilities.replaceAndQuote(sql, jobProfile.getName().trim());
		Utilities.replaceAndQuoteNullable(sql, jobProfile.getCode().trim());
		Utilities.replace(sql, jobProfile.getRate());
		Utilities.replace(sql, jobProfile.getAutoFill());
		Utilities.replaceAndQuoteNullable(sql, jobProfile.getDocumentURL());
    // NEW -->
    Utilities.replaceAndQuoteNullable(sql, jobProfile.getNhsAssignment().trim());
    // <-- NEW
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateJobProfile(JobProfile jobProfile, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, jobProfile.getJobSubFamilyId());
		Utilities.replaceAndQuote(sql, jobProfile.getName().trim());
		Utilities.replaceAndQuote(sql, jobProfile.getCode().trim());
		Utilities.replace(sql, jobProfile.getRate());
		Utilities.replace(sql, jobProfile.getAutoFill());
		Utilities.replaceAndQuoteNullable(sql, jobProfile.getDocumentURL());
    // NEW -->
    Utilities.replaceAndQuoteNullable(sql, jobProfile.getNhsAssignment().trim());
    // <-- NEW
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobProfile.getJobProfileId());
		Utilities.replace(sql, jobProfile.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteJobProfile(Integer jobProfileId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<JobProfileUser> getJobProfileUsersNotForLocation(
			Integer clientId, Integer locationId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(
				selectJobProfileUsersNotForLocationSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, locationId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfileUser.class.getName());

	}

	public List<JobProfileUser> getJobProfileUsersForManagerForLocation(Integer managerId, Integer locationId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileUsersForManagerForLocationSQL.toString().replaceFirst("SELECT", "SELECT DISTINCT"));
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, locationId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfileUser.class.getName());

	}
	
	public List<JobProfileUser> getJobProfileUsersForManagerForSite(Integer managerId, Integer siteId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileUsersForManagerForSiteSQL.toString().replaceFirst("SELECT", "SELECT DISTINCT"));
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, siteId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfileUser.class.getName());

	}
	
	public List<JobProfileUser> getJobProfileUsersForManager(Integer managerId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileUsersForManagerSQL.toString().replaceFirst("SELECT", "SELECT DISTINCT"));
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfileUser.class.getName());

	}
	
	public List<JobProfileUser> getJobProfileUsersForAgency(Integer agencyId, Integer clientId, Integer siteId, Integer locationId) {
		
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectJobProfileUsersForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, locationId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), JobProfileUser.class.getName());

	}
	
//	public List<JobProfileUser> getJobProfileUsersForAgencyForSite(Integer agencyId, Integer siteId) {
//
//		// Create a new local StringBuffer containing the parameterised SQL.
//		StringBuffer sql = new StringBuffer(selectJobProfileUsersForAgencyForSiteSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replace(sql, agencyId);
//		Utilities.replace(sql, siteId);
//		return RecordListFactory.getInstance().get(getJdbcTemplate(),
//				sql.toString(), JobProfileUser.class.getName());
//
//	}
//	
//	public List<JobProfileUser> getJobProfileUsersForAgencyForLocation(Integer agencyId, Integer locationId) {
//
//		// Create a new local StringBuffer containing the parameterised SQL.
//		StringBuffer sql = new StringBuffer(selectJobProfileUsersForAgencyForLocationSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replace(sql, agencyId);
//		Utilities.replace(sql, locationId);
//		return RecordListFactory.getInstance().get(getJdbcTemplate(),
//				sql.toString(), JobProfileUser.class.getName());
//
//	}
	
	public int updateJobProfileDisplayOrder(Integer jobProfileId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateJobProfileDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, jobProfileId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public List<JobProfileUser> getJobProfileUsersForNhsAssignment(Integer clientId, String nhsAssignment) 
  {
    StringBuffer sql = null;
    sql = new StringBuffer(selectJobProfileUsersForNhsAssignmentSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, clientId);
    Utilities.replaceAndQuote(sql, nhsAssignment);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), JobProfileUser.class.getName());
  }


}
