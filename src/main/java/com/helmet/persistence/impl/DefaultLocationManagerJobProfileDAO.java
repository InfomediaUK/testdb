package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.LocationManagerJobProfile;
import com.helmet.bean.LocationManagerJobProfileUser;
import com.helmet.persistence.LocationManagerJobProfileDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultLocationManagerJobProfileDAO extends JdbcDaoSupport implements LocationManagerJobProfileDAO {

	private static StringBuffer insertLocationManagerJobProfileSQL;

	private static StringBuffer deleteLocationManagerJobProfileSQL;

	private static StringBuffer selectLocationManagerJobProfileUsersSQL;

	private static StringBuffer selectLocationManagerJobProfileUsersForManagerSQL;

	public static void init() {
		// Get insert LocationManagerJobProfile SQL.
		insertLocationManagerJobProfileSQL = new StringBuffer();
		insertLocationManagerJobProfileSQL.append("INSERT INTO LOCATIONMANAGERJOBPROFILE ");
		insertLocationManagerJobProfileSQL.append("(  ");
		insertLocationManagerJobProfileSQL.append("  LOCATIONMANAGERJOBPROFILEID, ");
		insertLocationManagerJobProfileSQL.append("  LOCATIONID, ");
		insertLocationManagerJobProfileSQL.append("  MANAGERID, ");
		insertLocationManagerJobProfileSQL.append("  JOBPROFILEID, ");
		insertLocationManagerJobProfileSQL.append("  CREATIONTIMESTAMP, ");
		insertLocationManagerJobProfileSQL.append("  AUDITORID, ");
		insertLocationManagerJobProfileSQL.append("  AUDITTIMESTAMP ");
		insertLocationManagerJobProfileSQL.append(")  ");
		insertLocationManagerJobProfileSQL.append("VALUES  ");
		insertLocationManagerJobProfileSQL.append("(  ");
		insertLocationManagerJobProfileSQL.append("  ^, ");
		insertLocationManagerJobProfileSQL.append("  ^, ");
		insertLocationManagerJobProfileSQL.append("  ^, ");
		insertLocationManagerJobProfileSQL.append("  ^, ");
		insertLocationManagerJobProfileSQL.append("  ^, ");
		insertLocationManagerJobProfileSQL.append("  ^, ");
		insertLocationManagerJobProfileSQL.append("  ^ ");
		insertLocationManagerJobProfileSQL.append(")  ");
		// Get delete LocationManagerJobProfile SQL.
		deleteLocationManagerJobProfileSQL = new StringBuffer();
		deleteLocationManagerJobProfileSQL.append("UPDATE LOCATIONMANAGERJOBPROFILE ");
		deleteLocationManagerJobProfileSQL.append("SET ACTIVE = FALSE, ");
		deleteLocationManagerJobProfileSQL.append("    AUDITORID = ^, ");
		deleteLocationManagerJobProfileSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteLocationManagerJobProfileSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteLocationManagerJobProfileSQL.append("WHERE LOCATIONMANAGERJOBPROFILEID = ^ ");
		deleteLocationManagerJobProfileSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select LocationManagerJobProfileUsers SQL.
		selectLocationManagerJobProfileUsersSQL = new StringBuffer();
		selectLocationManagerJobProfileUsersSQL.append("SELECT LMJP.LOCATIONMANAGERJOBPROFILEID, ");
		selectLocationManagerJobProfileUsersSQL.append("       LMJP.LOCATIONID, ");
		selectLocationManagerJobProfileUsersSQL.append("       LMJP.MANAGERID, ");
		selectLocationManagerJobProfileUsersSQL.append("       LMJP.JOBPROFILEID, ");
		selectLocationManagerJobProfileUsersSQL.append("       LMJP.CREATIONTIMESTAMP, ");
		selectLocationManagerJobProfileUsersSQL.append("       LMJP.AUDITORID, ");
		selectLocationManagerJobProfileUsersSQL.append("       LMJP.AUDITTIMESTAMP, ");
		selectLocationManagerJobProfileUsersSQL.append("       LMJP.ACTIVE, ");
		selectLocationManagerJobProfileUsersSQL.append("       LMJP.NOOFCHANGES, ");
		selectLocationManagerJobProfileUsersSQL.append("       L.NAME AS LOCATIONNAME, ");
		selectLocationManagerJobProfileUsersSQL.append("       L.CODE AS LOCATIONCODE, ");
		selectLocationManagerJobProfileUsersSQL.append("       C.CLIENTID, ");
		selectLocationManagerJobProfileUsersSQL.append("       C.NAME AS CLIENTNAME, ");
		selectLocationManagerJobProfileUsersSQL.append("       C.CODE AS CLIENTCODE, ");
		selectLocationManagerJobProfileUsersSQL.append("       S.SITEID, ");
		selectLocationManagerJobProfileUsersSQL.append("       S.NAME AS SITENAME, ");
		selectLocationManagerJobProfileUsersSQL.append("       S.CODE AS SITECODE, ");
		selectLocationManagerJobProfileUsersSQL.append("       JP.NAME AS JOBPROFILENAME, ");
		selectLocationManagerJobProfileUsersSQL.append("       JP.CODE AS JOBPROFILECODE, ");
		selectLocationManagerJobProfileUsersSQL.append("       JSF.JOBSUBFAMILYID, ");
		selectLocationManagerJobProfileUsersSQL.append("       JSF.NAME AS JOBSUBFAMILYNAME, ");
		selectLocationManagerJobProfileUsersSQL.append("       JSF.CODE AS JOBSUBFAMILYCODE, ");
		selectLocationManagerJobProfileUsersSQL.append("       JF.JOBFAMILYID, ");
		selectLocationManagerJobProfileUsersSQL.append("       JF.NAME AS JOBFAMILYNAME, ");
		selectLocationManagerJobProfileUsersSQL.append("       JF.CODE AS JOBFAMILYCODE ");
		selectLocationManagerJobProfileUsersSQL.append("FROM LOCATIONMANAGERJOBPROFILE LMJP, ");
		selectLocationManagerJobProfileUsersSQL.append("     LOCATION L, ");
		selectLocationManagerJobProfileUsersSQL.append("     CLIENT C, ");
		selectLocationManagerJobProfileUsersSQL.append("     SITE S, ");
		selectLocationManagerJobProfileUsersSQL.append("     JOBPROFILE JP, ");
		selectLocationManagerJobProfileUsersSQL.append("     JOBSUBFAMILY JSF, ");
		selectLocationManagerJobProfileUsersSQL.append("     JOBFAMILY JF ");
		selectLocationManagerJobProfileUsersSQL.append("WHERE L.LOCATIONID = LMJP.LOCATIONID ");
		selectLocationManagerJobProfileUsersSQL.append("AND   L.ACTIVE = TRUE ");
		selectLocationManagerJobProfileUsersSQL.append("AND   C.CLIENTID = S.CLIENTID ");
		selectLocationManagerJobProfileUsersSQL.append("AND   C.ACTIVE = TRUE ");
		selectLocationManagerJobProfileUsersSQL.append("AND   S.SITEID = L.SITEID ");
		selectLocationManagerJobProfileUsersSQL.append("AND   S.ACTIVE = TRUE ");
		selectLocationManagerJobProfileUsersSQL.append("AND   JP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectLocationManagerJobProfileUsersSQL.append("AND   JP.ACTIVE = TRUE ");
		selectLocationManagerJobProfileUsersSQL.append("AND   JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
		selectLocationManagerJobProfileUsersSQL.append("AND   JSF.ACTIVE = TRUE ");
		selectLocationManagerJobProfileUsersSQL.append("AND   JF.JOBFAMILYID = JSF.JOBFAMILYID ");
		selectLocationManagerJobProfileUsersSQL.append("AND   JF.ACTIVE = TRUE ");
		// Get select LocationManagerJobProfileUsers for Manager SQL.
		selectLocationManagerJobProfileUsersForManagerSQL = new StringBuffer(selectLocationManagerJobProfileUsersSQL);
		selectLocationManagerJobProfileUsersForManagerSQL.append("AND LMJP.MANAGERID = ^ ");
		selectLocationManagerJobProfileUsersForManagerSQL.append("AND LMJP.ACTIVE = TRUE ");
		selectLocationManagerJobProfileUsersForManagerSQL.append("ORDER BY S.DISPLAYORDER, S.NAME, ");
		selectLocationManagerJobProfileUsersForManagerSQL.append("L.DISPLAYORDER, L.NAME, ");
		selectLocationManagerJobProfileUsersForManagerSQL.append("JF.DISPLAYORDER, JF.NAME, ");
		selectLocationManagerJobProfileUsersForManagerSQL.append("JSF.DISPLAYORDER, JSF.NAME, ");
		selectLocationManagerJobProfileUsersForManagerSQL.append("JP.DISPLAYORDER, JP.NAME ");
	}

	public int insertLocationManagerJobProfile(LocationManagerJobProfile locationManagerJobProfile, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertLocationManagerJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		locationManagerJobProfile.setLocationManagerJobProfileId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "locationManagerJobProfile"));
		Utilities.replace(sql, locationManagerJobProfile.getLocationManagerJobProfileId());
		Utilities.replace(sql, locationManagerJobProfile.getLocationId());
		Utilities.replace(sql, locationManagerJobProfile.getManagerId());
		Utilities.replace(sql, locationManagerJobProfile.getJobProfileId());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteLocationManagerJobProfile(Integer locationManagerJobProfileId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteLocationManagerJobProfileSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, locationManagerJobProfileId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public List<LocationManagerJobProfileUser> getLocationManagerJobProfileUsersForManager(Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationManagerJobProfileUsersForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationManagerJobProfileUser.class.getName());

	}
	
}
