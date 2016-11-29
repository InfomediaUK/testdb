package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.GeographicalRegion;
import com.helmet.persistence.GeographicalRegionDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultGeographicalRegionDAO extends JdbcDaoSupport implements GeographicalRegionDAO 
{

	private static StringBuffer insertGeographicalRegionSQL;

	private static StringBuffer updateGeographicalRegionSQL;

	private static StringBuffer deleteGeographicalRegionSQL;

	private static StringBuffer selectGeographicalRegionSQL;

	private static StringBuffer selectGeographicalRegionForNameSQL;

	private static StringBuffer selectGeographicalRegionForCodeSQL;

	private static StringBuffer selectGeographicalRegionsSQL;

	private static StringBuffer selectActiveGeographicalRegionsSQL;

	public static void init() 
  {
		// Get insert GeographicalRegion SQL.
		insertGeographicalRegionSQL = new StringBuffer();
		insertGeographicalRegionSQL.append("INSERT INTO GEOGRAPHICALREGION ");
		insertGeographicalRegionSQL.append("(  ");
		insertGeographicalRegionSQL.append("  GEOGRAPHICALREGIONID, ");
		insertGeographicalRegionSQL.append("  CODE, ");
    insertGeographicalRegionSQL.append("  NAME, ");
    insertGeographicalRegionSQL.append("  CREATIONTIMESTAMP, ");
    insertGeographicalRegionSQL.append("  AUDITORID, ");
    insertGeographicalRegionSQL.append("  AUDITTIMESTAMP ");
		insertGeographicalRegionSQL.append(")  ");
		insertGeographicalRegionSQL.append("VALUES  ");
		insertGeographicalRegionSQL.append("(  ");
		insertGeographicalRegionSQL.append("  ^, ");
		insertGeographicalRegionSQL.append("  ^, ");
    insertGeographicalRegionSQL.append("  ^, ");
    insertGeographicalRegionSQL.append("  ^, ");
    insertGeographicalRegionSQL.append("  ^, ");
		insertGeographicalRegionSQL.append("  ^ ");
		insertGeographicalRegionSQL.append(")  ");
		// Get update GeographicalRegion SQL.
		updateGeographicalRegionSQL = new StringBuffer();
		updateGeographicalRegionSQL.append("UPDATE GEOGRAPHICALREGION ");
		updateGeographicalRegionSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateGeographicalRegionSQL.append("     CODE = ^, ");
    updateGeographicalRegionSQL.append("     NAME = ^, ");
    updateGeographicalRegionSQL.append("     AUDITORID = ^, ");
    updateGeographicalRegionSQL.append("     AUDITTIMESTAMP = ^ ");
		updateGeographicalRegionSQL.append("WHERE GEOGRAPHICALREGIONID = ^ ");
		updateGeographicalRegionSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete GeographicalRegion SQL.
		deleteGeographicalRegionSQL = new StringBuffer();
		deleteGeographicalRegionSQL.append("UPDATE GEOGRAPHICALREGION ");
		deleteGeographicalRegionSQL.append("SET ACTIVE = FALSE, ");
    deleteGeographicalRegionSQL.append("     AUDITORID = ^, ");
    deleteGeographicalRegionSQL.append("     AUDITTIMESTAMP = ^ ");
		deleteGeographicalRegionSQL.append("WHERE GEOGRAPHICALREGIONID = ^ ");
		deleteGeographicalRegionSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select GeographicalRegions SQL.
		selectGeographicalRegionsSQL = new StringBuffer();
		selectGeographicalRegionsSQL.append("SELECT GEOGRAPHICALREGIONID, ");
		selectGeographicalRegionsSQL.append("       CODE, ");
    selectGeographicalRegionsSQL.append("       NAME, ");
    selectGeographicalRegionsSQL.append("       CREATIONTIMESTAMP, ");
    selectGeographicalRegionsSQL.append("       AUDITORID, ");
    selectGeographicalRegionsSQL.append("       AUDITTIMESTAMP, ");
    selectGeographicalRegionsSQL.append("       ACTIVE, ");
		selectGeographicalRegionsSQL.append("       NOOFCHANGES ");
		selectGeographicalRegionsSQL.append("FROM GEOGRAPHICALREGION ");
		// Get select GeographicalRegion SQL.
		selectGeographicalRegionSQL = new StringBuffer(selectGeographicalRegionsSQL);
		selectGeographicalRegionSQL.append("WHERE GEOGRAPHICALREGIONID = ^ ");
		// Get select Active GeographicalRegions SQL.
		selectActiveGeographicalRegionsSQL = new StringBuffer(selectGeographicalRegionsSQL);
		selectActiveGeographicalRegionsSQL.append("WHERE ACTIVE = TRUE ");
		// Get select GeographicalRegion for Name SQL.
		selectGeographicalRegionForNameSQL = new StringBuffer(selectActiveGeographicalRegionsSQL);
		selectGeographicalRegionForNameSQL.append("AND NAME = ^ ");
		// Get select GeographicalRegion for Iso Code SQL.
		selectGeographicalRegionForCodeSQL = new StringBuffer(selectActiveGeographicalRegionsSQL);
		selectGeographicalRegionForCodeSQL.append("AND CODE = ^ ");
	}

	public int insertGeographicalRegion(GeographicalRegion geographicalRegion, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertGeographicalRegionSQL.toString());
		// Replace the parameters with supplied values.
		geographicalRegion.setGeographicalRegionId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "geographicalRegion"));
		Utilities.replace(sql, geographicalRegion.getGeographicalRegionId());
		Utilities.replaceAndQuote(sql, geographicalRegion.getCode());
    Utilities.replaceAndQuote(sql, geographicalRegion.getName());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateGeographicalRegion(GeographicalRegion geographicalRegion, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateGeographicalRegionSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, geographicalRegion.getCode());
    Utilities.replaceAndQuote(sql, geographicalRegion.getName());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, geographicalRegion.getGeographicalRegionId());
		Utilities.replace(sql, geographicalRegion.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteGeographicalRegion(Integer geographicalRegionId, Integer noOfChanges,
			Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteGeographicalRegionSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, geographicalRegionId);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public GeographicalRegion getGeographicalRegion(Integer geographicalRegionId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectGeographicalRegionSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, geographicalRegionId);
		return (GeographicalRegion) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), GeographicalRegion.class.getName());
	}

	public GeographicalRegion getGeographicalRegionForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectGeographicalRegionForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (GeographicalRegion) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), GeographicalRegion.class.getName());
	}

	public GeographicalRegion getGeographicalRegionForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectGeographicalRegionForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (GeographicalRegion) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), GeographicalRegion.class.getName());
	}

	public List<GeographicalRegion> getGeographicalRegions() 
  {
		return getGeographicalRegions(false);
	}

	public List<GeographicalRegion> getGeographicalRegions(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveGeographicalRegionsSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectGeographicalRegionsSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), GeographicalRegion.class.getName());
	}

	public List<GeographicalRegion> getActiveGeographicalRegions() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveGeographicalRegionsSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), GeographicalRegion.class.getName());

	}

}
