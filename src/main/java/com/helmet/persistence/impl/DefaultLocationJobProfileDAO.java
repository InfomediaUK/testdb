package com.helmet.persistence.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationJobProfileUser;
import com.helmet.persistence.LocationJobProfileDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultLocationJobProfileDAO extends JdbcDaoSupport implements LocationJobProfileDAO {

	private static StringBuffer insertLocationJobProfileSQL;

	private static StringBuffer deleteLocationJobProfileSQL;

	private static StringBuffer updateLocationJobProfileBudgetSQL;

	private static StringBuffer updateLocationJobProfileRateSQL;

	private static StringBuffer selectLocationJobProfilesSQL;

	private static StringBuffer selectLocationJobProfileSQL;

	private static StringBuffer selectLocationJobProfileForLocationAndJobProfileSQL;

	private static StringBuffer selectLocationJobProfileUsersSQL;

	private static StringBuffer selectLocationJobProfileUserSQL;

	private static StringBuffer selectLocationJobProfileUserForManagerSQL;

	private static StringBuffer selectLocationJobProfileUsersForClientSQL;

	private static StringBuffer selectActiveLocationJobProfileUsersForClientSQL;

  private static StringBuffer selectLocationJobProfileUsersForLocationSQL;

  private static StringBuffer selectLocationJobProfileUsersForLocationAndNhsAssignmentSQL;

	private static StringBuffer selectLocationJobProfileUsersNotForManagerSQL;

	private static StringBuffer selectLocationJobProfileUsersForManagerSQL;

	private static StringBuffer selectLocationJobProfileUsersForManagerForLocationSQL;

	public static void init() {
		// Get insert LocationJobProfile SQL.
		insertLocationJobProfileSQL = new StringBuffer();
		insertLocationJobProfileSQL.append("INSERT INTO LOCATIONJOBPROFILE ");
		insertLocationJobProfileSQL.append("(  ");
		insertLocationJobProfileSQL.append("  LOCATIONJOBPROFILEID, ");
		insertLocationJobProfileSQL.append("  LOCATIONID, ");
		insertLocationJobProfileSQL.append("  JOBPROFILEID, ");
		insertLocationJobProfileSQL.append("  CREATIONTIMESTAMP, ");
		insertLocationJobProfileSQL.append("  AUDITORID, ");
		insertLocationJobProfileSQL.append("  AUDITTIMESTAMP ");
		insertLocationJobProfileSQL.append(")  ");
		insertLocationJobProfileSQL.append("VALUES  ");
		insertLocationJobProfileSQL.append("(  ");
		insertLocationJobProfileSQL.append("  ^, ");
		insertLocationJobProfileSQL.append("  ^, ");
		insertLocationJobProfileSQL.append("  ^, ");
		insertLocationJobProfileSQL.append("  ^, ");
		insertLocationJobProfileSQL.append("  ^, ");
		insertLocationJobProfileSQL.append("  ^ ");
		insertLocationJobProfileSQL.append(")  ");
		// Get delete LocationJobProfile SQL.
		deleteLocationJobProfileSQL = new StringBuffer();
		deleteLocationJobProfileSQL.append("UPDATE LOCATIONJOBPROFILE ");
		deleteLocationJobProfileSQL.append("SET ACTIVE = FALSE, ");
		deleteLocationJobProfileSQL.append("    AUDITORID = ^, ");
		deleteLocationJobProfileSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteLocationJobProfileSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteLocationJobProfileSQL.append("WHERE LOCATIONJOBPROFILEID = ^ ");
		deleteLocationJobProfileSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update LocationJobProfile budget SQL.
		updateLocationJobProfileBudgetSQL = new StringBuffer();
		updateLocationJobProfileBudgetSQL.append("UPDATE LOCATIONJOBPROFILE ");
		updateLocationJobProfileBudgetSQL.append("SET BUDGET = BUDGET + ^, ");
		updateLocationJobProfileBudgetSQL.append("    VATBUDGET = VATBUDGET + ^, ");
		updateLocationJobProfileBudgetSQL.append("    EXPENSEBUDGET = EXPENSEBUDGET + ^, ");
		updateLocationJobProfileBudgetSQL.append("    NONPAYBUDGET = NONPAYBUDGET + ^, ");
		updateLocationJobProfileBudgetSQL.append("    AUDITORID = ^, ");
		updateLocationJobProfileBudgetSQL.append("    AUDITTIMESTAMP = ^, ");
		updateLocationJobProfileBudgetSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateLocationJobProfileBudgetSQL.append("WHERE LOCATIONJOBPROFILEID = ^ ");
		updateLocationJobProfileBudgetSQL.append("AND   NOOFCHANGES = ^ ");
		// Get update LocationJobProfile rate SQL.
		updateLocationJobProfileRateSQL = new StringBuffer();
		updateLocationJobProfileRateSQL.append("UPDATE LOCATIONJOBPROFILE ");
		updateLocationJobProfileRateSQL.append("SET RATE = ^, ");
		updateLocationJobProfileRateSQL.append("    AUDITORID = ^, ");
		updateLocationJobProfileRateSQL.append("    AUDITTIMESTAMP = ^, ");
		updateLocationJobProfileRateSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateLocationJobProfileRateSQL.append("WHERE LOCATIONJOBPROFILEID = ^ ");
		updateLocationJobProfileRateSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select LocationJobProfile SQL.
		selectLocationJobProfilesSQL = new StringBuffer();
		selectLocationJobProfilesSQL.append("SELECT LJP.LOCATIONJOBPROFILEID, ");
		selectLocationJobProfilesSQL.append("       LJP.LOCATIONID, ");
		selectLocationJobProfilesSQL.append("       LJP.JOBPROFILEID, ");
		selectLocationJobProfilesSQL.append("       LJP.BUDGET, ");
		selectLocationJobProfilesSQL.append("       LJP.VATBUDGET, ");
		selectLocationJobProfilesSQL.append("       LJP.EXPENSEBUDGET, ");
		selectLocationJobProfilesSQL.append("       LJP.NONPAYBUDGET, ");
		selectLocationJobProfilesSQL.append("       LJP.RATE, ");
		selectLocationJobProfilesSQL.append("       LJP.CREATIONTIMESTAMP, ");
		selectLocationJobProfilesSQL.append("       LJP.AUDITORID, ");
		selectLocationJobProfilesSQL.append("       LJP.AUDITTIMESTAMP, ");
		selectLocationJobProfilesSQL.append("       LJP.ACTIVE, ");
		selectLocationJobProfilesSQL.append("       LJP.NOOFCHANGES ");
		// Get select LocationJobProfileUsers SQL.
		selectLocationJobProfileUsersSQL = new StringBuffer(selectLocationJobProfilesSQL);
		selectLocationJobProfileUsersSQL.append(", ");
		selectLocationJobProfileUsersSQL.append("       L.NAME AS LOCATIONNAME, ");
		selectLocationJobProfileUsersSQL.append("       L.CODE AS LOCATIONCODE, ");
		selectLocationJobProfileUsersSQL.append("       C.CLIENTID, ");
		selectLocationJobProfileUsersSQL.append("       C.NAME AS CLIENTNAME, ");
		selectLocationJobProfileUsersSQL.append("       C.CODE AS CLIENTCODE, ");
		selectLocationJobProfileUsersSQL.append("       S.SITEID, ");
		selectLocationJobProfileUsersSQL.append("       S.NAME AS SITENAME, ");
		selectLocationJobProfileUsersSQL.append("       S.CODE AS SITECODE, ");
		selectLocationJobProfileUsersSQL.append("       JP.NAME AS JOBPROFILENAME, ");
    selectLocationJobProfileUsersSQL.append("       JP.CODE AS JOBPROFILECODE, ");
    selectLocationJobProfileUsersSQL.append("       JP.NHSASSIGNMENT, ");
		selectLocationJobProfileUsersSQL.append("       JP.DOCUMENTURL, ");
		selectLocationJobProfileUsersSQL.append("       JSF.JOBSUBFAMILYID, ");
		selectLocationJobProfileUsersSQL.append("       JSF.NAME AS JOBSUBFAMILYNAME, ");
		selectLocationJobProfileUsersSQL.append("       JSF.CODE AS JOBSUBFAMILYCODE, ");
		selectLocationJobProfileUsersSQL.append("       JF.JOBFAMILYID, ");
		selectLocationJobProfileUsersSQL.append("       JF.NAME AS JOBFAMILYNAME, ");
		selectLocationJobProfileUsersSQL.append("       JF.CODE AS JOBFAMILYCODE ");
		selectLocationJobProfileUsersSQL.append("FROM LOCATIONJOBPROFILE LJP, ");
		selectLocationJobProfileUsersSQL.append("     LOCATION L, ");
		selectLocationJobProfileUsersSQL.append("     CLIENT C, ");
		selectLocationJobProfileUsersSQL.append("     SITE S, ");
		selectLocationJobProfileUsersSQL.append("     JOBPROFILE JP, ");
		selectLocationJobProfileUsersSQL.append("     JOBSUBFAMILY JSF, ");
		selectLocationJobProfileUsersSQL.append("     JOBFAMILY JF ");
		selectLocationJobProfileUsersSQL.append("WHERE L.LOCATIONID = LJP.LOCATIONID ");
		selectLocationJobProfileUsersSQL.append("AND   L.ACTIVE = TRUE ");
		selectLocationJobProfileUsersSQL.append("AND   S.SITEID = L.SITEID ");
		selectLocationJobProfileUsersSQL.append("AND   S.ACTIVE = TRUE ");
		selectLocationJobProfileUsersSQL.append("AND   C.CLIENTID = S.CLIENTID ");
		selectLocationJobProfileUsersSQL.append("AND   C.ACTIVE = TRUE ");
		selectLocationJobProfileUsersSQL.append("AND   JP.JOBPROFILEID = LJP.JOBPROFILEID ");
		selectLocationJobProfileUsersSQL.append("AND   JP.ACTIVE = TRUE ");
		selectLocationJobProfileUsersSQL.append("AND   JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
		selectLocationJobProfileUsersSQL.append("AND   JSF.ACTIVE = TRUE ");
		selectLocationJobProfileUsersSQL.append("AND   JF.JOBFAMILYID = JSF.JOBFAMILYID ");
		selectLocationJobProfileUsersSQL.append("AND   JF.ACTIVE = TRUE ");
		// Get select LocationJobProfiles SQL.
		selectLocationJobProfilesSQL.append("FROM LOCATIONJOBPROFILE LJP ");
		// Get select LocationJobProfile SQL.
		selectLocationJobProfileSQL = new StringBuffer(selectLocationJobProfilesSQL);
		selectLocationJobProfileSQL.append("WHERE LJP.LOCATIONJOBPROFILEID = ^ ");
		// Get select LocationJobProfile for Location and JobProfile SQL.
		selectLocationJobProfileForLocationAndJobProfileSQL = new StringBuffer(selectLocationJobProfilesSQL);
		selectLocationJobProfileForLocationAndJobProfileSQL.append("WHERE LJP.LOCATIONID = ^ ");
		selectLocationJobProfileForLocationAndJobProfileSQL.append("AND LJP.JOBPROFILEID = ^ ");
		selectLocationJobProfileForLocationAndJobProfileSQL.append("AND LJP.ACTIVE = TRUE ");
		// Get select LocationJobProfileUser SQL.
		selectLocationJobProfileUserSQL = new StringBuffer(selectLocationJobProfileUsersSQL);
		selectLocationJobProfileUserSQL.append("AND LJP.LOCATIONJOBPROFILEID = ^ ");
		// Get select LocationJobProfileUser for Manager SQL.
		selectLocationJobProfileUserForManagerSQL = new StringBuffer(selectLocationJobProfileUserSQL);
		selectLocationJobProfileUserForManagerSQL.append("AND EXISTS ");
		selectLocationJobProfileUserForManagerSQL.append("( ");
		selectLocationJobProfileUserForManagerSQL.append(" SELECT NULL ");
		selectLocationJobProfileUserForManagerSQL.append(" FROM LOCATIONMANAGERJOBPROFILE LMJP ");
		selectLocationJobProfileUserForManagerSQL.append(" WHERE LMJP.MANAGERID = ^ ");
		selectLocationJobProfileUserForManagerSQL.append(" AND LMJP.ACTIVE = TRUE ");
		selectLocationJobProfileUserForManagerSQL.append(" AND L.LOCATIONID = LMJP.LOCATIONID ");
		selectLocationJobProfileUserForManagerSQL.append(" AND JP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectLocationJobProfileUserForManagerSQL.append(") ");
		// Get select LocationJobProfileUsers for Location SQL.
		selectLocationJobProfileUsersForLocationSQL = new StringBuffer(selectLocationJobProfileUsersSQL);
		selectLocationJobProfileUsersForLocationSQL.append("AND LJP.LOCATIONID = ^ ");
		selectLocationJobProfileUsersForLocationSQL.append("AND LJP.ACTIVE = TRUE ");

    // Get select LocationJobProfileUsers for Location and NHS Assignment SQL.
    selectLocationJobProfileUsersForLocationAndNhsAssignmentSQL = new StringBuffer(selectLocationJobProfileUsersForLocationSQL);
    selectLocationJobProfileUsersForLocationAndNhsAssignmentSQL.append("AND JP.NHSASSIGNMENT = ^ ");
    selectLocationJobProfileUsersForLocationAndNhsAssignmentSQL.append("ORDER BY S.DISPLAYORDER, S.NAME, ");
    selectLocationJobProfileUsersForLocationAndNhsAssignmentSQL.append("L.DISPLAYORDER, L.NAME, ");
    selectLocationJobProfileUsersForLocationAndNhsAssignmentSQL.append("JF.DISPLAYORDER, JF.NAME, ");
    selectLocationJobProfileUsersForLocationAndNhsAssignmentSQL.append("JSF.DISPLAYORDER, JSF.NAME, ");
    selectLocationJobProfileUsersForLocationAndNhsAssignmentSQL.append("JP.DISPLAYORDER, JP.NAME ");
    // Add Order By clause:    
		selectLocationJobProfileUsersForLocationSQL.append("ORDER BY S.DISPLAYORDER, S.NAME, ");
		selectLocationJobProfileUsersForLocationSQL.append("L.DISPLAYORDER, L.NAME, ");
		selectLocationJobProfileUsersForLocationSQL.append("JF.DISPLAYORDER, JF.NAME, ");
		selectLocationJobProfileUsersForLocationSQL.append("JSF.DISPLAYORDER, JSF.NAME, ");
		selectLocationJobProfileUsersForLocationSQL.append("JP.DISPLAYORDER, JP.NAME ");
		// Get select LocationJobProfileUsers for Client SQL.
		selectLocationJobProfileUsersForClientSQL = new StringBuffer(selectLocationJobProfileUsersSQL);
		selectLocationJobProfileUsersForClientSQL.append("AND C.CLIENTID = ^ ");
		// Get select Active LocationJobProfileUsers for Client SQL.
		selectActiveLocationJobProfileUsersForClientSQL = new StringBuffer(selectLocationJobProfileUsersForClientSQL);
		selectActiveLocationJobProfileUsersForClientSQL.append("AND LJP.ACTIVE = TRUE ");
		//
		selectLocationJobProfileUsersForClientSQL.append("ORDER BY S.DISPLAYORDER, S.NAME, ");
		selectLocationJobProfileUsersForClientSQL.append("L.DISPLAYORDER, L.NAME, ");
		selectLocationJobProfileUsersForClientSQL.append("JF.DISPLAYORDER, JF.NAME, ");
		selectLocationJobProfileUsersForClientSQL.append("JSF.DISPLAYORDER, JSF.NAME, ");
		selectLocationJobProfileUsersForClientSQL.append("JP.DISPLAYORDER, JP.NAME ");
        //
		selectActiveLocationJobProfileUsersForClientSQL.append("ORDER BY S.DISPLAYORDER, S.NAME, ");
		selectActiveLocationJobProfileUsersForClientSQL.append("L.DISPLAYORDER, L.NAME, ");
		selectActiveLocationJobProfileUsersForClientSQL.append("JF.DISPLAYORDER, JF.NAME, ");
		selectActiveLocationJobProfileUsersForClientSQL.append("JSF.DISPLAYORDER, JSF.NAME, ");
		selectActiveLocationJobProfileUsersForClientSQL.append("JP.DISPLAYORDER, JP.NAME ");
		// Get select LocationJobProfileUsers not for Manager SQL.
		selectLocationJobProfileUsersNotForManagerSQL = new StringBuffer(selectLocationJobProfileUsersSQL);
		selectLocationJobProfileUsersNotForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
		selectLocationJobProfileUsersNotForManagerSQL.append("AND JF.CLIENTID = ^ ");
		selectLocationJobProfileUsersNotForManagerSQL.append("AND NOT EXISTS ");
		selectLocationJobProfileUsersNotForManagerSQL.append("( ");
		selectLocationJobProfileUsersNotForManagerSQL.append(" SELECT NULL ");
		selectLocationJobProfileUsersNotForManagerSQL.append(" FROM LOCATIONMANAGERJOBPROFILE LMJP ");
		selectLocationJobProfileUsersNotForManagerSQL.append(" WHERE LMJP.MANAGERID = ^ ");
		selectLocationJobProfileUsersNotForManagerSQL.append(" AND LMJP.ACTIVE = TRUE ");
		selectLocationJobProfileUsersNotForManagerSQL.append(" AND L.LOCATIONID = LMJP.LOCATIONID ");
		selectLocationJobProfileUsersNotForManagerSQL.append(" AND JP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectLocationJobProfileUsersNotForManagerSQL.append(") ");
		selectLocationJobProfileUsersNotForManagerSQL.append("ORDER BY S.DISPLAYORDER, S.NAME, ");
		selectLocationJobProfileUsersNotForManagerSQL.append("L.DISPLAYORDER, L.NAME, ");
		selectLocationJobProfileUsersNotForManagerSQL.append("JF.DISPLAYORDER, JF.NAME, ");
		selectLocationJobProfileUsersNotForManagerSQL.append("JSF.DISPLAYORDER, JSF.NAME, ");
		selectLocationJobProfileUsersNotForManagerSQL.append("JP.DISPLAYORDER, JP.NAME ");
		// Get select LocationJobProfileUsers for Manager SQL.
		selectLocationJobProfileUsersForManagerSQL = new StringBuffer(selectLocationJobProfileUsersSQL);
		selectLocationJobProfileUsersForManagerSQL.append("AND LJP.ACTIVE = TRUE ");
		selectLocationJobProfileUsersForManagerSQL.append("AND EXISTS ");
		selectLocationJobProfileUsersForManagerSQL.append("( ");
		selectLocationJobProfileUsersForManagerSQL.append(" SELECT NULL ");
		selectLocationJobProfileUsersForManagerSQL.append(" FROM LOCATIONMANAGERJOBPROFILE LMJP ");
		selectLocationJobProfileUsersForManagerSQL.append(" WHERE LMJP.MANAGERID = ^ ");
		selectLocationJobProfileUsersForManagerSQL.append(" AND LMJP.ACTIVE = TRUE ");
		selectLocationJobProfileUsersForManagerSQL.append(" AND L.LOCATIONID = LMJP.LOCATIONID ");
		selectLocationJobProfileUsersForManagerSQL.append(" AND JP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectLocationJobProfileUsersForManagerSQL.append(") ");
		// Get select LocationJobProfileUsers for Manager for Location SQL.
		selectLocationJobProfileUsersForManagerForLocationSQL = new StringBuffer(selectLocationJobProfileUsersForManagerSQL);
		selectLocationJobProfileUsersForManagerForLocationSQL.append("AND L.LOCATIONID = ^ ");
        //
		selectLocationJobProfileUsersForManagerSQL.append("ORDER BY S.DISPLAYORDER, S.NAME, ");
		selectLocationJobProfileUsersForManagerSQL.append("L.DISPLAYORDER, L.NAME, ");
		selectLocationJobProfileUsersForManagerSQL.append("JF.DISPLAYORDER, JF.NAME, ");
		selectLocationJobProfileUsersForManagerSQL.append("JSF.DISPLAYORDER, JSF.NAME, ");
		selectLocationJobProfileUsersForManagerSQL.append("JP.DISPLAYORDER, JP.NAME ");
        //
		selectLocationJobProfileUsersForManagerForLocationSQL.append("ORDER BY S.DISPLAYORDER, S.NAME, ");
		selectLocationJobProfileUsersForManagerForLocationSQL.append("L.DISPLAYORDER, L.NAME, ");
		selectLocationJobProfileUsersForManagerForLocationSQL.append("JF.DISPLAYORDER, JF.NAME, ");
		selectLocationJobProfileUsersForManagerForLocationSQL.append("JSF.DISPLAYORDER, JSF.NAME, ");
		selectLocationJobProfileUsersForManagerForLocationSQL.append("JP.DISPLAYORDER, JP.NAME ");

	}

	public int insertLocationJobProfile(LocationJobProfile locationJobProfile, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertLocationJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		locationJobProfile.setLocationJobProfileId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "locationJobProfile"));
		Utilities.replace(sql, locationJobProfile.getLocationJobProfileId());
		Utilities.replace(sql, locationJobProfile.getLocationId());
		Utilities.replace(sql, locationJobProfile.getJobProfileId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteLocationJobProfile(Integer locationJobProfileId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteLocationJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, locationJobProfileId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public LocationJobProfile getLocationJobProfile(Integer locationJobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationJobProfileId);
		return (LocationJobProfile) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationJobProfile.class.getName());

	}
	
	public LocationJobProfile getLocationJobProfileForLocationAndJobProfile(Integer locationId, Integer jobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationJobProfileForLocationAndJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, jobProfileId);
		return (LocationJobProfile) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationJobProfile.class.getName());

	}
	
	public LocationJobProfileUser getLocationJobProfileUser(Integer locationJobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationJobProfileUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationJobProfileId);
		return (LocationJobProfileUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationJobProfileUser.class.getName());

	}
	
	public LocationJobProfileUser getLocationJobProfileUserForManager(Integer managerId, Integer locationJobProfileId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationJobProfileUserForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationJobProfileId);
		Utilities.replace(sql, managerId);
		return (LocationJobProfileUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationJobProfileUser.class.getName());

	}
	
	public List<LocationJobProfileUser> getLocationJobProfileUsersForLocation(Integer locationId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationJobProfileUsersForLocationSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationJobProfileUser.class.getName());

	}
	
  public List<LocationJobProfileUser> getLocationJobProfileUsersForLocationAndNhsAssignment(Integer locationId, String nhsAssignment) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectLocationJobProfileUsersForLocationAndNhsAssignmentSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, locationId);
    Utilities.replaceAndQuote(sql, nhsAssignment);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), LocationJobProfileUser.class.getName());
  }
  
	public List<LocationJobProfileUser> getLocationJobProfileUsersNotForManager(Integer clientId, Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationJobProfileUsersNotForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationJobProfileUser.class.getName());

	}

	public List<LocationJobProfileUser> getLocationJobProfileUsersForManager(Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationJobProfileUsersForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationJobProfileUser.class.getName());

	}

	public List<LocationJobProfileUser> getLocationJobProfileUsersForManagerForLocation(Integer managerId, Integer locationId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationJobProfileUsersForManagerForLocationSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, locationId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationJobProfileUser.class.getName());

	}

	public int updateLocationJobProfileBudget(Integer locationJobProfileId, BigDecimal value, BigDecimal vatValue, BigDecimal expenseValue, BigDecimal nonPayValue, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateLocationJobProfileBudgetSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, value);
		Utilities.replace(sql, vatValue);
		Utilities.replace(sql, expenseValue);
		Utilities.replace(sql, nonPayValue);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, locationJobProfileId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateLocationJobProfileRate(Integer locationJobProfileId, BigDecimal value, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateLocationJobProfileRateSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, value);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, locationJobProfileId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<LocationJobProfileUser> getLocationJobProfileUsersForClient(Integer clientId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveLocationJobProfileUsersForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectLocationJobProfileUsersForClientSQL.toString()); 
		}
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationJobProfileUser.class.getName());

	}
	
}
