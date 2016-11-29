package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Location;
import com.helmet.bean.LocationEntity;
import com.helmet.bean.LocationUser;
import com.helmet.bean.LocationUserJobProfile;
import com.helmet.persistence.LocationDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultLocationDAO extends JdbcDaoSupport implements LocationDAO {

	private static StringBuffer insertLocationSQL;

	private static StringBuffer updateLocationSQL;

	private static StringBuffer updateLocationDisplayOrderSQL;

	private static StringBuffer deleteLocationSQL;

	private static StringBuffer selectLocationsSQL;

	private static StringBuffer selectLocationsForSiteSQL;

  private static StringBuffer selectLocationsForNhsWardSQL;

	private static StringBuffer selectLocationUsersSQL;

	private static StringBuffer selectLocationUsersForManagerSQL;

	private static StringBuffer selectLocationUsersForManagerForSiteSQL;

	private static StringBuffer selectLocationUsersForAgencySQL;

//	private static StringBuffer selectLocationUsersForAgencyForSiteSQL;

	private static StringBuffer selectLocationUserSQL;

	private static StringBuffer selectLocationUserJobProfilesSQL;

	private static StringBuffer selectLocationUserJobProfilesNotForManagerSQL;

	private static StringBuffer selectActiveLocationsForSiteSQL;

	private static StringBuffer selectLocationSQL;

	private static StringBuffer selectLocationForNameSQL;

	private static StringBuffer selectLocationForCodeSQL;

	public static void init() {
		// Get insert Location SQL.
		insertLocationSQL = new StringBuffer();
		insertLocationSQL.append("INSERT INTO LOCATION ");
		insertLocationSQL.append("(  ");
		insertLocationSQL.append("  LOCATIONID, ");
		insertLocationSQL.append("  SITEID, ");
		insertLocationSQL.append("  NAME, ");
		insertLocationSQL.append("  DESCRIPTION, ");
		insertLocationSQL.append("  CONTACTNAME, ");
		insertLocationSQL.append("  CONTACTJOBTITLE, ");
		insertLocationSQL.append("  CONTACTEMAILADDRESS, ");
		insertLocationSQL.append("  CONTACTTELEPHONENUMBER, ");
		insertLocationSQL.append("  CODE, ");
		insertLocationSQL.append("  SINGLECANDIDATESHOW, ");
		insertLocationSQL.append("  CVREQUIREDSHOW, ");
		insertLocationSQL.append("  INTERVIEWREQUIREDSHOW, ");
		insertLocationSQL.append("  CANPROVIDEACCOMMODATIONSHOW, ");
		insertLocationSQL.append("  CARREQUIREDSHOW, ");
		insertLocationSQL.append("  SINGLECANDIDATEDEFAULT, ");
		insertLocationSQL.append("  CVREQUIREDDEFAULT, ");
		insertLocationSQL.append("  INTERVIEWREQUIREDDEFAULT, ");
		insertLocationSQL.append("  CANPROVIDEACCOMMODATIONDEFAULT, ");
		insertLocationSQL.append("  CARREQUIREDDEFAULT, ");
    insertLocationSQL.append("  COMMENTSDEFAULT, ");
    // NEW -->
    insertLocationSQL.append("  NHSWARD, ");
    insertLocationSQL.append("  NHSDAYSTARTTIME, ");
    insertLocationSQL.append("  NHSDAYENDTIME, ");
    // <-- NEW
		insertLocationSQL.append("  CREATIONTIMESTAMP, ");
		insertLocationSQL.append("  AUDITORID, ");
		insertLocationSQL.append("  AUDITTIMESTAMP ");
		insertLocationSQL.append(")  ");
		insertLocationSQL.append("VALUES  ");
		insertLocationSQL.append("(  ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
    insertLocationSQL.append("  ^, ");
    insertLocationSQL.append("  ^, ");
    // NEW -->
    insertLocationSQL.append("  ^, ");
    insertLocationSQL.append("  ^, ");
    insertLocationSQL.append("  ^, ");
    // <-- NEW
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^, ");
		insertLocationSQL.append("  ^ ");
		insertLocationSQL.append(")  ");
		// Get update Location SQL.
		updateLocationSQL = new StringBuffer();
		updateLocationSQL.append("UPDATE LOCATION ");
		updateLocationSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateLocationSQL.append("     SITEID = ^, ");
		updateLocationSQL.append("     NAME = ^, ");
		updateLocationSQL.append("     DESCRIPTION = ^, ");
		updateLocationSQL.append("     CONTACTNAME = ^, ");
		updateLocationSQL.append("     CONTACTJOBTITLE = ^, ");
		updateLocationSQL.append("     CONTACTEMAILADDRESS = ^, ");
		updateLocationSQL.append("     CONTACTTELEPHONENUMBER = ^, ");
		updateLocationSQL.append("     CODE = ^, ");
		updateLocationSQL.append("     SINGLECANDIDATESHOW = ^, ");
		updateLocationSQL.append("     CVREQUIREDSHOW = ^, ");
		updateLocationSQL.append("     INTERVIEWREQUIREDSHOW = ^, ");
		updateLocationSQL.append("     CANPROVIDEACCOMMODATIONSHOW = ^, ");
		updateLocationSQL.append("     CARREQUIREDSHOW = ^, ");
		updateLocationSQL.append("     SINGLECANDIDATEDEFAULT = ^, ");
		updateLocationSQL.append("     CVREQUIREDDEFAULT = ^, ");
		updateLocationSQL.append("     INTERVIEWREQUIREDDEFAULT = ^, ");
		updateLocationSQL.append("     CANPROVIDEACCOMMODATIONDEFAULT = ^, ");
		updateLocationSQL.append("     CARREQUIREDDEFAULT = ^, ");
    updateLocationSQL.append("     COMMENTSDEFAULT = ^, ");
    // NEW -->
    updateLocationSQL.append("     NHSWARD = ^, ");
    updateLocationSQL.append("     NHSDAYSTARTTIME = ^, ");
    updateLocationSQL.append("     NHSDAYENDTIME = ^, ");
    // <-- NEW
		updateLocationSQL.append("     AUDITORID = ^, ");
		updateLocationSQL.append("     AUDITTIMESTAMP = ^ ");
		updateLocationSQL.append("WHERE LOCATIONID = ^ ");
		updateLocationSQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateLocationDisplayOrder SQL.
		updateLocationDisplayOrderSQL = new StringBuffer();
		updateLocationDisplayOrderSQL.append("UPDATE LOCATION ");
		updateLocationDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateLocationDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateLocationDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateLocationDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateLocationDisplayOrderSQL.append("WHERE LOCATIONID = ^ ");
		updateLocationDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Location SQL.
		deleteLocationSQL = new StringBuffer();
		deleteLocationSQL.append("UPDATE LOCATION ");
		deleteLocationSQL.append("SET ACTIVE = FALSE, ");
		deleteLocationSQL.append("    AUDITORID = ^, ");
		deleteLocationSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteLocationSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteLocationSQL.append("WHERE LOCATIONID = ^ ");
		deleteLocationSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Locations SQL.
		selectLocationsSQL = new StringBuffer();
		selectLocationsSQL.append("SELECT ");
		selectLocationsSQL.append("       L.LOCATIONID, ");
		selectLocationsSQL.append("       L.SITEID, ");
		selectLocationsSQL.append("       L.NAME, ");
		selectLocationsSQL.append("       L.DESCRIPTION, ");
		selectLocationsSQL.append("       L.CONTACTNAME, ");
		selectLocationsSQL.append("       L.CONTACTJOBTITLE, ");
		selectLocationsSQL.append("       L.CONTACTEMAILADDRESS, ");
		selectLocationsSQL.append("       L.CONTACTTELEPHONENUMBER, ");
		selectLocationsSQL.append("       L.CODE, ");
		selectLocationsSQL.append("       L.SINGLECANDIDATESHOW, ");
		selectLocationsSQL.append("       L.CVREQUIREDSHOW, ");
		selectLocationsSQL.append("       L.INTERVIEWREQUIREDSHOW, ");
		selectLocationsSQL.append("       L.CANPROVIDEACCOMMODATIONSHOW, ");
		selectLocationsSQL.append("       L.CARREQUIREDSHOW, ");
		selectLocationsSQL.append("       L.SINGLECANDIDATEDEFAULT, ");
		selectLocationsSQL.append("       L.CVREQUIREDDEFAULT, ");
		selectLocationsSQL.append("       L.INTERVIEWREQUIREDDEFAULT, ");
		selectLocationsSQL.append("       L.CANPROVIDEACCOMMODATIONDEFAULT, ");
		selectLocationsSQL.append("       L.CARREQUIREDDEFAULT, ");
    selectLocationsSQL.append("       L.COMMENTSDEFAULT, ");
    // NEW -->
    selectLocationsSQL.append("       L.NHSWARD, ");
    selectLocationsSQL.append("       L.NHSDAYSTARTTIME, ");
    selectLocationsSQL.append("       L.NHSDAYENDTIME, ");
    // <-- NEW
		selectLocationsSQL.append("       L.DISPLAYORDER, ");
		selectLocationsSQL.append("       L.CREATIONTIMESTAMP, ");
		selectLocationsSQL.append("       L.AUDITORID, ");
		selectLocationsSQL.append("       L.AUDITTIMESTAMP, ");
		selectLocationsSQL.append("       L.ACTIVE, ");
		selectLocationsSQL.append("       L.NOOFCHANGES ");
		// Get select LocationUser SQL.
		selectLocationUsersSQL = new StringBuffer(selectLocationsSQL);
		selectLocationUsersSQL.append(", ");
		selectLocationUsersSQL.append(" C.CLIENTID, ");
		selectLocationUsersSQL.append(" C.DISPLAYORDER, ");
		selectLocationUsersSQL.append(" C.NAME AS CLIENTNAME, ");
		selectLocationUsersSQL.append(" C.CODE AS CLIENTCODE, ");
		selectLocationUsersSQL.append(" S.DISPLAYORDER, ");
		selectLocationUsersSQL.append(" S.NAME AS SITENAME, ");
		selectLocationUsersSQL.append(" S.CODE AS SITECODE ");
		
		// Get select LocationUserJobProfile SQL.
		selectLocationUserJobProfilesSQL = new StringBuffer(selectLocationUsersSQL);
		selectLocationUserJobProfilesSQL.append(", ");
		selectLocationUserJobProfilesSQL.append(" JF.JOBFAMILYID, ");
		selectLocationUserJobProfilesSQL.append(" JF.DISPLAYORDER, ");
		selectLocationUserJobProfilesSQL.append(" JF.NAME AS JOBFAMILYNAME, ");
		selectLocationUserJobProfilesSQL.append(" JF.CODE AS JOBFAMILYCODE, ");
		selectLocationUserJobProfilesSQL.append(" JSF.JOBSUBFAMILYID, ");
		selectLocationUserJobProfilesSQL.append(" JSF.DISPLAYORDER, ");
		selectLocationUserJobProfilesSQL.append(" JSF.NAME AS JOBSUBFAMILYNAME, ");
		selectLocationUserJobProfilesSQL.append(" JSF.CODE AS JOBSUBFAMILYCODE, ");
		selectLocationUserJobProfilesSQL.append(" JP.JOBPROFILEID, ");
		selectLocationUserJobProfilesSQL.append(" JP.DISPLAYORDER, ");
		selectLocationUserJobProfilesSQL.append(" JP.NAME AS JOBPROFILENAME, ");
		selectLocationUserJobProfilesSQL.append(" JP.CODE AS JOBPROFILECODE ");
        //
		selectLocationsSQL.append("FROM LOCATION L ");
        //
		selectLocationUsersSQL.append("FROM LOCATION L, ");
		selectLocationUsersSQL.append("     SITE S, ");
		selectLocationUsersSQL.append("     CLIENT C ");
		selectLocationUsersSQL.append("WHERE S.SITEID = L.SITEID ");
		selectLocationUsersSQL.append("AND   S.ACTIVE = TRUE ");
		selectLocationUsersSQL.append("AND   C.CLIENTID = S.CLIENTID ");
		selectLocationUsersSQL.append("AND   C.ACTIVE = TRUE ");
		// Get select LocationUser SQL.
		selectLocationUserSQL = new StringBuffer(selectLocationUsersSQL);
		selectLocationUserSQL.append("AND L.LOCATIONID = ^ ");

		// Get select LocationUsers for Manager SQL.
		selectLocationUsersForManagerSQL = new StringBuffer(selectLocationUsersSQL);
		selectLocationUsersForManagerSQL.append("AND L.ACTIVE = TRUE ");
		selectLocationUsersForManagerSQL.append("AND EXISTS ");
		selectLocationUsersForManagerSQL.append("( ");
		selectLocationUsersForManagerSQL.append(" SELECT NULL ");
		selectLocationUsersForManagerSQL.append(" FROM LOCATIONMANAGERJOBPROFILE LMJP ");
		selectLocationUsersForManagerSQL.append(" WHERE L.LOCATIONID = LMJP.LOCATIONID ");
		selectLocationUsersForManagerSQL.append(" AND LMJP.ACTIVE = TRUE ");
		selectLocationUsersForManagerSQL.append(" AND LMJP.MANAGERID = ^ ");
		selectLocationUsersForManagerSQL.append(") ");
		
		// Get select LocationUsers for Manager for Site SQL.
		selectLocationUsersForManagerForSiteSQL = new StringBuffer(selectLocationUsersForManagerSQL);
		selectLocationUsersForManagerForSiteSQL.append("AND S.SITEID = ^ ");

		// Get select LocationUsersForAgency SQL.
		selectLocationUsersForAgencySQL = new StringBuffer(selectLocationUsersSQL.toString());
		selectLocationUsersForAgencySQL.append("AND L.ACTIVE = TRUE ");
		selectLocationUsersForAgencySQL.append("AND EXISTS ");
		selectLocationUsersForAgencySQL.append("( ");
		selectLocationUsersForAgencySQL.append(" SELECT NULL ");
		selectLocationUsersForAgencySQL.append(" FROM CLIENTAGENCY CA ");
		selectLocationUsersForAgencySQL.append(" WHERE C.CLIENTID = CA.CLIENTID ");
		selectLocationUsersForAgencySQL.append(" AND CA.ACTIVE = TRUE ");
		selectLocationUsersForAgencySQL.append(" AND CA.AGENCYID = ^ ");
		selectLocationUsersForAgencySQL.append(") ");

		selectLocationUsersForAgencySQL.append("AND ( ");
		selectLocationUsersForAgencySQL.append(" ^ IS NULL "); //
		selectLocationUsersForAgencySQL.append("OR ");
		selectLocationUsersForAgencySQL.append(" S.CLIENTID = ^ "); //
		selectLocationUsersForAgencySQL.append(") ");
		
		selectLocationUsersForAgencySQL.append("AND ( ");
		selectLocationUsersForAgencySQL.append(" ^ IS NULL "); //
		selectLocationUsersForAgencySQL.append("OR ");
		selectLocationUsersForAgencySQL.append(" S.SITEID = ^ "); //
		selectLocationUsersForAgencySQL.append(") ");
		
//		// Get select LocationUsers for Agency for Site SQL.
//		selectLocationUsersForAgencyForSiteSQL = new StringBuffer(selectLocationUsersForAgencySQL);
//		selectLocationUsersForAgencyForSiteSQL.append("AND S.SITEID = ^ ");
		
		//
		selectLocationUserJobProfilesSQL.append("FROM LOCATION L, ");
		selectLocationUserJobProfilesSQL.append("     SITE S, ");
		selectLocationUserJobProfilesSQL.append("     CLIENT C, ");
		selectLocationUserJobProfilesSQL.append("     LOCATIONJOBPROFILEGROUP LJPG, ");
		selectLocationUserJobProfilesSQL.append("     JOBPROFILEGROUP JPG, ");
		selectLocationUserJobProfilesSQL.append("     JOBPROFILEGROUPITEM JPGI, ");
		selectLocationUserJobProfilesSQL.append("     JOBPROFILE JP, ");
		selectLocationUserJobProfilesSQL.append("     JOBSUBFAMILY JSF, ");
		selectLocationUserJobProfilesSQL.append("     JOBFAMILY JF ");
		selectLocationUserJobProfilesSQL.append("WHERE S.SITEID = L.SITEID ");
		selectLocationUserJobProfilesSQL.append("AND   S.ACTIVE = TRUE ");
		selectLocationUserJobProfilesSQL.append("AND   C.CLIENTID = S.CLIENTID ");
		selectLocationUserJobProfilesSQL.append("AND   C.ACTIVE = TRUE ");
		selectLocationUserJobProfilesSQL.append("AND   LJPG.LOCATIONID = L.LOCATIONID ");
		selectLocationUserJobProfilesSQL.append("AND   LJPG.ACTIVE = TRUE ");
		selectLocationUserJobProfilesSQL.append("AND   JPG.JOBPROFILEGROUPID = LJPG.JOBPROFILEGROUPID ");
		selectLocationUserJobProfilesSQL.append("AND   JPG.ACTIVE = TRUE ");
		selectLocationUserJobProfilesSQL.append("AND   JPGI.JOBPROFILEGROUPID = LJPG.JOBPROFILEGROUPID ");
		selectLocationUserJobProfilesSQL.append("AND   JPGI.ACTIVE = TRUE ");
		selectLocationUserJobProfilesSQL.append("AND   JP.JOBPROFILEID = JPGI.JOBPROFILEID ");
		selectLocationUserJobProfilesSQL.append("AND   JP.ACTIVE = TRUE ");
		selectLocationUserJobProfilesSQL.append("AND   JSF.JOBSUBFAMILYID = JP.JOBSUBFAMILYID ");
		selectLocationUserJobProfilesSQL.append("AND   JSF.ACTIVE = TRUE ");
		selectLocationUserJobProfilesSQL.append("AND   JF.JOBFAMILYID = JSF.JOBFAMILYID ");
		selectLocationUserJobProfilesSQL.append("AND   JF.ACTIVE = TRUE ");
		// MUST GO HERE
		selectLocationUserJobProfilesNotForManagerSQL = new StringBuffer(selectLocationUserJobProfilesSQL);
		selectLocationUserJobProfilesNotForManagerSQL.append("AND C.CLIENTID = ^ ");
		selectLocationUserJobProfilesNotForManagerSQL.append("AND L.ACTIVE = TRUE ");
		selectLocationUserJobProfilesNotForManagerSQL.append("AND NOT EXISTS ");
		selectLocationUserJobProfilesNotForManagerSQL.append("( ");
		selectLocationUserJobProfilesNotForManagerSQL.append(" SELECT NULL ");
		selectLocationUserJobProfilesNotForManagerSQL.append(" FROM LOCATIONMANAGERJOBPROFILE LM ");
		selectLocationUserJobProfilesNotForManagerSQL.append(" WHERE LM.MANAGERID = ^ ");
		selectLocationUserJobProfilesNotForManagerSQL.append(" AND LM.ACTIVE = TRUE ");
		selectLocationUserJobProfilesNotForManagerSQL.append(" AND LM.LOCATIONID = L.LOCATIONID ");
		selectLocationUserJobProfilesNotForManagerSQL.append(" AND LM.JOBPROFILEID = JP.JOBPROFILEID ");
		selectLocationUserJobProfilesNotForManagerSQL.append(") ");
		selectLocationUserJobProfilesNotForManagerSQL.append("ORDER BY C.DISPLAYORDER, C.NAME, S.DISPLAYORDER, S.NAME, L.DISPLAYORDER, L.NAME, ");
		selectLocationUserJobProfilesNotForManagerSQL.append("         JF.DISPLAYORDER, JF.NAME, JSF.DISPLAYORDER, JSF.NAME, JP.DISPLAYORDER, JP.NAME ");
		// Get select Location SQL.
		selectLocationSQL = new StringBuffer(selectLocationsSQL);
		selectLocationSQL.append("WHERE L.LOCATIONID = ^ ");
    // Get select Locations for Site SQL.
    selectLocationsForSiteSQL = new StringBuffer(selectLocationsSQL);
    selectLocationsForSiteSQL.append("WHERE L.SITEID = ^ ");
		// Get select Active Locations for Site SQL.
		selectActiveLocationsForSiteSQL = new StringBuffer(selectLocationsForSiteSQL);
		selectActiveLocationsForSiteSQL.append("AND L.ACTIVE = TRUE ");
		// Get select Location for Name SQL.
		selectLocationForNameSQL = new StringBuffer(selectActiveLocationsForSiteSQL);
		selectLocationForNameSQL.append("AND L.NAME = ^ ");
		// Get select Location for Code SQL.
		selectLocationForCodeSQL = new StringBuffer(selectActiveLocationsForSiteSQL);
		selectLocationForCodeSQL.append("AND L.CODE = ^ ");
    // NEW -->
    // Get select Locations for NHS Ward SQL.
    selectLocationsForNhsWardSQL = new StringBuffer(selectLocationsSQL);
    selectLocationsForNhsWardSQL.append("WHERE L.SITEID = ^ ");
    selectLocationsForNhsWardSQL.append("AND L.NHSWARD = ^ ");
    selectLocationsForNhsWardSQL.append("AND L.ACTIVE = TRUE ");
		//
    selectLocationsForNhsWardSQL.append("ORDER BY L.DISPLAYORDER, L.NAME ");
    // <-- NEW
    selectLocationsForSiteSQL.append("ORDER BY L.DISPLAYORDER, L.NAME ");
		selectActiveLocationsForSiteSQL.append("ORDER BY L.DISPLAYORDER, L.NAME ");
		selectLocationUsersForManagerSQL.append("ORDER BY C.DISPLAYORDER, C.NAME, S.DISPLAYORDER, S.NAME, L.DISPLAYORDER, L.NAME ");
		selectLocationUsersForManagerForSiteSQL.append("ORDER BY L.DISPLAYORDER, L.NAME ");
		selectLocationUsersForAgencySQL.append("ORDER BY C.DISPLAYORDER, C.NAME, S.DISPLAYORDER, S.NAME, L.DISPLAYORDER, L.NAME ");
//		selectLocationUsersForAgencyForSiteSQL.append("ORDER BY L.DISPLAYORDER, L.NAME ");
		
	}

	public List<Location> getLocationsForSite(Integer siteId, boolean showOnlyActive) {
		
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveLocationsForSiteSQL.toString());
		}
		else {
			sql = new StringBuffer(selectLocationsForSiteSQL.toString());
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, siteId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				Location.class.getName());
		
	}
	public List<LocationUserJobProfile> getLocationUserJobProfilesNotForManager(Integer clientId, Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationUserJobProfilesNotForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationUserJobProfile.class.getName());

	}
	public Location getLocation(Integer locationId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		return (Location) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Location.class.getName());
	}
	public Location getLocationForName(Integer siteId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, siteId);
		Utilities.replaceAndQuote(sql, name);
		return (Location) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Location.class.getName());
	}
	public Location getLocationForCode(Integer siteId, String code) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, siteId);
		Utilities.replaceAndQuote(sql, code);
		return (Location) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Location.class.getName());
	}
	public int insertLocation(Location location, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertLocationSQL.toString());
		// Replace the parameters with supplied values.
		location.setLocationId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "location"));
		Utilities.replace(sql, location.getLocationId());
		Utilities.replace(sql, location.getSiteId());
		Utilities.replaceAndQuote(sql, location.getName().trim());
		Utilities.replaceAndQuoteNullable(sql, location.getDescription());
		Utilities.replaceAndQuoteNullable(sql, location.getContactName());
		Utilities.replaceAndQuoteNullable(sql, location.getContactJobTitle());
		Utilities.replaceAndQuoteNullable(sql, location.getContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, location.getContactTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, location.getCode());
		Utilities.replace(sql, location.getSingleCandidateShow());
		Utilities.replace(sql, location.getCvRequiredShow());
		Utilities.replace(sql, location.getInterviewRequiredShow());
		Utilities.replace(sql, location.getCanProvideAccommodationShow());
		Utilities.replace(sql, location.getCarRequiredShow());
		Utilities.replace(sql, location.getSingleCandidateDefault());
		Utilities.replace(sql, location.getCvRequiredDefault());
		Utilities.replace(sql, location.getInterviewRequiredDefault());
		Utilities.replace(sql, location.getCanProvideAccommodationDefault());
		Utilities.replace(sql, location.getCarRequiredDefault());
		Utilities.replaceAndQuoteNullable(sql, location.getCommentsDefault());
    // NEW -->
    Utilities.replaceAndQuoteNullable(sql, location.getNhsWard() == null ? null : location.getNhsWard().trim());
    Utilities.replaceAndQuoteNullable(sql, location.getNhsDayStartTime());
    Utilities.replaceAndQuoteNullable(sql, location.getNhsDayEndTime());
    // <-- NEW
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateLocation(Location location, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateLocationSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, location.getSiteId());
		Utilities.replaceAndQuote(sql, location.getName().trim());
		Utilities.replaceAndQuoteNullable(sql, location.getDescription());
		Utilities.replaceAndQuoteNullable(sql, location.getContactName());
		Utilities.replaceAndQuoteNullable(sql, location.getContactJobTitle());
		Utilities.replaceAndQuoteNullable(sql, location.getContactEmailAddress());
		Utilities.replaceAndQuoteNullable(sql, location.getContactTelephoneNumber());
		Utilities.replaceAndQuoteNullable(sql, location.getCode());
		Utilities.replace(sql, location.getSingleCandidateShow());
		Utilities.replace(sql, location.getCvRequiredShow());
		Utilities.replace(sql, location.getInterviewRequiredShow());
		Utilities.replace(sql, location.getCanProvideAccommodationShow());
		Utilities.replace(sql, location.getCarRequiredShow());
		Utilities.replace(sql, location.getSingleCandidateDefault());
		Utilities.replace(sql, location.getCvRequiredDefault());
		Utilities.replace(sql, location.getInterviewRequiredDefault());
		Utilities.replace(sql, location.getCanProvideAccommodationDefault());
		Utilities.replace(sql, location.getCarRequiredDefault());
		Utilities.replaceAndQuoteNullable(sql, location.getCommentsDefault());
    // NEW -->
    Utilities.replaceAndQuoteNullable(sql, location.getNhsWard().trim());
    Utilities.replaceAndQuoteNullable(sql, location.getNhsDayStartTime());
    Utilities.replaceAndQuoteNullable(sql, location.getNhsDayEndTime());
    // <-- NEW
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, location.getLocationId());
		Utilities.replace(sql, location.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int deleteLocation(Integer locationId, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteLocationSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public LocationUser getLocationUser(Integer locationId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		return (LocationUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationUser.class.getName());
	}

	public LocationEntity getLocationEntity(Integer locationId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		return (LocationEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationEntity.class.getName());
	}

  // NEW -->
  public List<Location> getLocationsForNhsWard(Integer siteId, String nhsWard) 
  {
    StringBuffer sql = new StringBuffer(selectLocationsForNhsWardSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, siteId);
    Utilities.replaceAndQuote(sql, nhsWard);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Location.class.getName());
  }
  // <-- NEW
  
	public List<LocationUser> getLocationUsersForManager(Integer managerId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationUsersForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationUser.class.getName());

	}

	public List<LocationUser> getLocationUsersForManagerForSite(Integer managerId, Integer siteId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationUsersForManagerForSiteSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		Utilities.replace(sql, siteId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationUser.class.getName());

	}

	public List<LocationUser> getLocationUsersForAgency(Integer agencyId, Integer clientId, Integer siteId) {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectLocationUsersForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, siteId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), LocationUser.class.getName());

	}

//	public List<LocationUser> getLocationUsersForAgencyForSite(Integer agencyId, Integer siteId) {
//
//		// Create a new local StringBuffer containing the parameterised SQL.
//		StringBuffer sql = new StringBuffer(selectLocationUsersForAgencyForSiteSQL.toString());
//		// Replace the parameters with supplied values.
//		Utilities.replace(sql, agencyId);
//		Utilities.replace(sql, siteId);
//		return RecordListFactory.getInstance().get(getJdbcTemplate(),
//				sql.toString(), LocationUser.class.getName());
//
//	}

	public int updateLocationDisplayOrder(Integer locationId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateLocationDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, locationId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
}
