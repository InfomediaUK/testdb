package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Client;
import com.helmet.bean.Site;
import com.helmet.bean.SiteUser;
import com.helmet.bean.SiteUserEntity;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.SiteDAO;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultSiteDAO extends JdbcDaoSupport implements SiteDAO {

	private static StringBuffer insertSiteSQL;

	private static StringBuffer updateSiteSQL;

	private static StringBuffer updateSiteDisplayOrderSQL;

	private static StringBuffer deleteSiteSQL;

	private static StringBuffer selectSitesSQL;

	private static StringBuffer selectSiteSQL;

	private static StringBuffer selectSiteForNameSQL;

	private static StringBuffer selectSiteForCodeSQL;

	private static StringBuffer selectSitesForClientSQL;

	private static StringBuffer selectSiteUsersSQL;
	
	private static StringBuffer selectSiteUserSQL;

	private static StringBuffer selectSiteUsersForClientSQL;

	private static StringBuffer selectActiveSiteUsersForClientSQL;

	private static StringBuffer selectSiteUsersForManagerSQL;

  private static StringBuffer selectSiteUsersForAgencySQL;

  private static StringBuffer selectSitesForNhsLocationSQL;

	public static void init() {
		// Get insert Site SQL.
		insertSiteSQL = new StringBuffer();
		insertSiteSQL.append("INSERT INTO SITE ");
		insertSiteSQL.append("(  ");
		insertSiteSQL.append("  SITEID, ");
		insertSiteSQL.append("  CLIENTID, ");
		insertSiteSQL.append("  NAME, ");
		insertSiteSQL.append("  ADDRESS1, ");
		insertSiteSQL.append("  ADDRESS2, ");
		insertSiteSQL.append("  ADDRESS3, ");
		insertSiteSQL.append("  ADDRESS4, ");
		insertSiteSQL.append("  POSTALCODE, ");
		insertSiteSQL.append("  COUNTRYID, ");
		insertSiteSQL.append("  CODE, ");
    // NEW -->
    insertSiteSQL.append("  NHSLOCATION, ");
    // <-- NEW
		insertSiteSQL.append("  CREATIONTIMESTAMP, ");
		insertSiteSQL.append("  AUDITORID, ");
		insertSiteSQL.append("  AUDITTIMESTAMP ");
		insertSiteSQL.append(")  ");
		insertSiteSQL.append("VALUES  ");
		insertSiteSQL.append("(  ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
    // NEW -->
    insertSiteSQL.append("  ^, ");
    // <-- NEW
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^, ");
		insertSiteSQL.append("  ^ ");
		insertSiteSQL.append(")  ");
		// Get update Site SQL.
		updateSiteSQL = new StringBuffer();
		updateSiteSQL.append("UPDATE SITE ");
		updateSiteSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateSiteSQL.append("     CLIENTID = ^, ");
		updateSiteSQL.append("     NAME = ^, ");
		updateSiteSQL.append("     ADDRESS1 = ^, ");
		updateSiteSQL.append("     ADDRESS2 = ^, ");
		updateSiteSQL.append("     ADDRESS3 = ^, ");
		updateSiteSQL.append("     ADDRESS4 = ^, ");
		updateSiteSQL.append("     POSTALCODE = ^, ");
		updateSiteSQL.append("     COUNTRYID = ^, ");
		updateSiteSQL.append("     CODE = ^, ");
    // NEW -->
    updateSiteSQL.append("     NHSLOCATION = ^, ");
    // <-- NEW
		updateSiteSQL.append("     AUDITORID = ^, ");
		updateSiteSQL.append("     AUDITTIMESTAMP = ^ ");
		updateSiteSQL.append("WHERE SITEID = ^ ");
		updateSiteSQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateSiteDisplayOrder SQL.
		updateSiteDisplayOrderSQL = new StringBuffer();
		updateSiteDisplayOrderSQL.append("UPDATE SITE ");
		updateSiteDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateSiteDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateSiteDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateSiteDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateSiteDisplayOrderSQL.append("WHERE SITEID = ^ ");
		updateSiteDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Site SQL.
		deleteSiteSQL = new StringBuffer();
		deleteSiteSQL.append("UPDATE SITE ");
		deleteSiteSQL.append("SET ACTIVE = FALSE, ");
		deleteSiteSQL.append("    AUDITORID = ^, ");
		deleteSiteSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteSiteSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteSiteSQL.append("WHERE SITEID = ^ ");
		deleteSiteSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Sites SQL.
		selectSitesSQL = new StringBuffer();
		selectSitesSQL.append("SELECT SITEID, ");
		selectSitesSQL.append("       CLIENTID, ");
		selectSitesSQL.append("       NAME, ");
		selectSitesSQL.append("       ADDRESS1, ");
		selectSitesSQL.append("       ADDRESS2, ");
		selectSitesSQL.append("       ADDRESS3, ");
		selectSitesSQL.append("       ADDRESS4, ");
		selectSitesSQL.append("       POSTALCODE, ");
		selectSitesSQL.append("       COUNTRYID, ");
    selectSitesSQL.append("       CODE, ");
    // NEW -->
    selectSitesSQL.append("       NHSLOCATION, ");
    // <-- NEW
		selectSitesSQL.append("       DISPLAYORDER, ");
		selectSitesSQL.append("       CREATIONTIMESTAMP, ");
		selectSitesSQL.append("       AUDITORID, ");
		selectSitesSQL.append("       AUDITTIMESTAMP, ");
		selectSitesSQL.append("       ACTIVE, ");
		selectSitesSQL.append("       NOOFCHANGES ");
		selectSitesSQL.append("FROM SITE ");
		// Get select Site SQL.
		selectSiteSQL = new StringBuffer(selectSitesSQL);
		selectSiteSQL.append("WHERE SITEID = ^ ");
		// Get select Site for Name SQL.
		selectSiteForNameSQL = new StringBuffer(selectSitesSQL);
		selectSiteForNameSQL.append("WHERE CLIENTID = ^ ");
		selectSiteForNameSQL.append("AND   NAME = ^ ");
		selectSiteForNameSQL.append("AND   ACTIVE = TRUE ");
		// Get select Site for Code SQL.
		selectSiteForCodeSQL = new StringBuffer(selectSitesSQL);
		selectSiteForCodeSQL.append("WHERE CLIENTID = ^ ");
		selectSiteForCodeSQL.append("AND   CODE = ^ ");
		selectSiteForCodeSQL.append("AND   ACTIVE = TRUE ");
		// Get select Sites For Client SQL.
		selectSitesForClientSQL = new StringBuffer(selectSitesSQL);
		selectSitesForClientSQL.append("WHERE CLIENTID = ^ ");
		// Get select SiteUsers SQL.
		selectSiteUsersSQL = new StringBuffer();
		selectSiteUsersSQL.append("SELECT S.SITEID, ");
		selectSiteUsersSQL.append("	   S.CLIENTID, ");
		selectSiteUsersSQL.append("	   S.NAME, ");
		selectSiteUsersSQL.append("	   S.ADDRESS1, ");
		selectSiteUsersSQL.append("	   S.ADDRESS2, ");
		selectSiteUsersSQL.append("	   S.ADDRESS3, ");
		selectSiteUsersSQL.append("	   S.ADDRESS4, ");
		selectSiteUsersSQL.append("	   S.POSTALCODE, ");
		selectSiteUsersSQL.append("	   S.COUNTRYID, ");
    selectSiteUsersSQL.append("    S.CODE, ");
    selectSiteUsersSQL.append("    S.NHSLOCATION, ");
		selectSiteUsersSQL.append("	   S.DISPLAYORDER, ");
		selectSiteUsersSQL.append("	   S.CREATIONTIMESTAMP, ");
		selectSiteUsersSQL.append("	   S.AUDITORID, ");
		selectSiteUsersSQL.append("	   S.AUDITTIMESTAMP, ");
		selectSiteUsersSQL.append("	   S.ACTIVE, ");
		selectSiteUsersSQL.append("	   S.NOOFCHANGES, ");
    selectSiteUsersSQL.append("    CO.NAME AS COUNTRYNAME, ");
    selectSiteUsersSQL.append("    C.NAME AS CLIENTNAME ");
		selectSiteUsersSQL.append("FROM SITE S, COUNTRY CO, CLIENT C ");
    selectSiteUsersSQL.append("WHERE CO.COUNTRYID  = S.COUNTRYID ");    
    selectSiteUsersSQL.append("AND   C.CLIENTID = S.CLIENTID ");    
		// Get select SiteUser SQL.
		selectSiteUserSQL = new StringBuffer(selectSiteUsersSQL);
		selectSiteUserSQL.append("AND S.SITEID = ^ ");
		// Get select SiteUsersForClient SQL.
		selectSiteUsersForClientSQL = new StringBuffer(selectSiteUsersSQL);
		selectSiteUsersForClientSQL.append("AND S.CLIENTID = ^ ");
		// Get select ActiveSiteUsersForClient SQL.
		selectActiveSiteUsersForClientSQL = new StringBuffer(selectSiteUsersForClientSQL);
		selectActiveSiteUsersForClientSQL.append("AND S.ACTIVE = TRUE ");
		// Get select SiteUsers For Manager SQL.
		selectSiteUsersForManagerSQL = new StringBuffer(selectSiteUsersSQL);
		selectSiteUsersForManagerSQL.append("AND S.ACTIVE = TRUE ");
		selectSiteUsersForManagerSQL.append("AND EXISTS ");
		selectSiteUsersForManagerSQL.append("( ");
		selectSiteUsersForManagerSQL.append(" SELECT NULL ");
		selectSiteUsersForManagerSQL.append(" FROM LOCATIONMANAGERJOBPROFILE LMJP, ");
		selectSiteUsersForManagerSQL.append("      LOCATION L, ");
		selectSiteUsersForManagerSQL.append("      JOBPROFILE JP ");
		selectSiteUsersForManagerSQL.append(" WHERE LMJP.MANAGERID = ^ ");
		selectSiteUsersForManagerSQL.append(" AND LMJP.ACTIVE = TRUE ");
		selectSiteUsersForManagerSQL.append(" AND L.LOCATIONID = LMJP.LOCATIONID ");
		selectSiteUsersForManagerSQL.append(" AND L.ACTIVE = TRUE ");
		selectSiteUsersForManagerSQL.append(" AND JP.JOBPROFILEID = LMJP.JOBPROFILEID ");
		selectSiteUsersForManagerSQL.append(" AND JP.ACTIVE = TRUE ");
		selectSiteUsersForManagerSQL.append(" AND L.SITEID = S.SITEID ");
		selectSiteUsersForManagerSQL.append(") ");

		// Get select SiteUsers For Agency SQL.
		selectSiteUsersForAgencySQL = new StringBuffer(selectSiteUsersSQL);
		selectSiteUsersForAgencySQL.append("AND S.ACTIVE = TRUE ");
		selectSiteUsersForAgencySQL.append("AND EXISTS ");
		selectSiteUsersForAgencySQL.append("( ");
		selectSiteUsersForAgencySQL.append(" SELECT NULL ");
		selectSiteUsersForAgencySQL.append(" FROM CLIENTAGENCY CA ");
		selectSiteUsersForAgencySQL.append(" WHERE S.CLIENTID = CA.CLIENTID ");
		selectSiteUsersForAgencySQL.append(" AND CA.ACTIVE = TRUE ");
		selectSiteUsersForAgencySQL.append(" AND CA.AGENCYID = ^ ");
		selectSiteUsersForAgencySQL.append(") ");

		selectSiteUsersForAgencySQL.append("AND ( ");
		selectSiteUsersForAgencySQL.append(" ^ IS NULL "); //
		selectSiteUsersForAgencySQL.append("OR ");
		selectSiteUsersForAgencySQL.append(" S.CLIENTID = ^ "); //
		selectSiteUsersForAgencySQL.append(") ");

    selectSitesForNhsLocationSQL = new StringBuffer(selectSitesSQL);
    selectSitesForNhsLocationSQL.append("WHERE CLIENTID = ^ ");
    selectSitesForNhsLocationSQL.append("AND NHSLOCATION = ^ ");
    selectSitesForNhsLocationSQL.append("AND ACTIVE = TRUE ");
    selectSitesForNhsLocationSQL.append("ORDER BY DISPLAYORDER, NAME ");
    
    //
		selectSiteUsersForClientSQL.append("ORDER BY S.DISPLAYORDER, S.NAME ");
		selectActiveSiteUsersForClientSQL.append("ORDER BY S.DISPLAYORDER, S.NAME ");
		selectSiteUsersForManagerSQL.append("ORDER BY S.DISPLAYORDER, S.NAME ");
		selectSiteUsersForAgencySQL.append("ORDER BY S.DISPLAYORDER, S.NAME ");

	}

	public int insertSite(Site site,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertSiteSQL.toString());
		// Replace the parameters with supplied values.
		site.setSiteId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "site"));
		Utilities.replace(sql, site.getSiteId());
		Utilities.replace(sql, site.getClientId());
		Utilities.replaceAndQuote(sql, site.getName().trim());
		Utilities.replaceAndQuote(sql, site.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, site.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, site.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, site.getAddress().getAddress4());
		Utilities.replaceAndQuote(sql, site.getAddress().getPostalCode());
		Utilities.replace(sql, site.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, site.getCode());
    // NEW -->
    Utilities.replaceAndQuoteNullable(sql, site.getNhsLocation()== null ? null : site.getNhsLocation().trim());
    // <-- NEW
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateSite(Site site,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateSiteSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, site.getClientId());
		Utilities.replaceAndQuote(sql, site.getName().trim());
		Utilities.replaceAndQuote(sql, site.getAddress().getAddress1());
		Utilities.replaceAndQuoteNullable(sql, site.getAddress().getAddress2());
		Utilities.replaceAndQuoteNullable(sql, site.getAddress().getAddress3());
		Utilities.replaceAndQuoteNullable(sql, site.getAddress().getAddress4());
		Utilities.replaceAndQuote(sql, site.getAddress().getPostalCode());
		Utilities.replace(sql, site.getAddress().getCountryId());
		Utilities.replaceAndQuoteNullable(sql, site.getCode());
    // NEW -->
    Utilities.replaceAndQuoteNullable(sql, site.getNhsLocation().trim());
    // <-- NEW
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, site.getSiteId());
		Utilities.replace(sql, site.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteSite(Integer siteId,
			Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteSiteSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public Site getSite(Integer siteId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectSiteSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, siteId);
		return (Site) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Site.class.getName());
	}

	public Site getSiteForName(Integer clientId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectSiteForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, name);
		return (Site) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Site.class.getName());
	}

	public Site getSiteForCode(Integer clientId, String code) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectSiteForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		Utilities.replaceAndQuote(sql, code);
		return (Site) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Site.class.getName());
	}

	public SiteUser getSiteUser(Integer siteId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectSiteUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, siteId);
		return (SiteUser) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), SiteUser.class.getName());
	}

	public SiteUserEntity getSiteUserEntity(Integer siteId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectSiteUserSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, siteId);
		return (SiteUserEntity) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), SiteUserEntity.class.getName());
	}

	public List<Site> getSitesForClient(Integer clientId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectSitesForClientSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				Site.class.getName());
	}

	public List<SiteUser> getSiteUsersForClient(Integer clientId, boolean showOnlyActive) {
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveSiteUsersForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectSiteUsersForClientSQL.toString());
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				SiteUser.class.getName());
	}

	public List<SiteUser> getSiteUsersForManager(Integer managerId) {
		StringBuffer sql = new StringBuffer(selectSiteUsersForManagerSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, managerId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				SiteUser.class.getName());
	}

	public List<SiteUser> getSiteUsersForAgency(Integer agencyId, Integer clientId) {
		StringBuffer sql = new StringBuffer(selectSiteUsersForAgencySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, agencyId);
		Utilities.replace(sql, clientId);
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				SiteUser.class.getName());
	}

  public List<Site> getSitesForNhsLocation(Integer clientId, String nhsLocation) 
  {
    StringBuffer sql = new StringBuffer(selectSitesForNhsLocationSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, clientId);
    Utilities.replaceAndQuote(sql, nhsLocation);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Site.class.getName());
  }

	public int updateSiteDisplayOrder(Integer siteId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateSiteDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, siteId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	
}
