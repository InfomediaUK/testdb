package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.AreaOfSpeciality;
import com.helmet.persistence.AreaOfSpecialityDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultAreaOfSpecialityDAO extends JdbcDaoSupport implements AreaOfSpecialityDAO 
{

	private static StringBuffer insertAreaOfSpecialitySQL;

	private static StringBuffer updateAreaOfSpecialitySQL;

	private static StringBuffer deleteAreaOfSpecialitySQL;

	private static StringBuffer selectAreaOfSpecialitySQL;

	private static StringBuffer selectAreaOfSpecialityForNameSQL;

	private static StringBuffer selectAreaOfSpecialityForCodeSQL;

	private static StringBuffer selectAreaOfSpecialitiesSQL;

	private static StringBuffer selectActiveAreaOfSpecialitiesSQL;

	public static void init() 
  {
		// Get insert AreaOfSpeciality SQL.
		insertAreaOfSpecialitySQL = new StringBuffer();
		insertAreaOfSpecialitySQL.append("INSERT INTO AREAOFSPECIALITY ");
		insertAreaOfSpecialitySQL.append("(  ");
		insertAreaOfSpecialitySQL.append("  AREAOFSPECIALITYID, ");
    insertAreaOfSpecialitySQL.append("  NAME, ");
    insertAreaOfSpecialitySQL.append("  CREATIONTIMESTAMP, ");
    insertAreaOfSpecialitySQL.append("  AUDITORID, ");
    insertAreaOfSpecialitySQL.append("  AUDITTIMESTAMP ");
		insertAreaOfSpecialitySQL.append(")  ");
		insertAreaOfSpecialitySQL.append("VALUES  ");
		insertAreaOfSpecialitySQL.append("(  ");
		insertAreaOfSpecialitySQL.append("  ^, ");
    insertAreaOfSpecialitySQL.append("  ^, ");
    insertAreaOfSpecialitySQL.append("  ^, ");
    insertAreaOfSpecialitySQL.append("  ^, ");
		insertAreaOfSpecialitySQL.append("  ^ ");
		insertAreaOfSpecialitySQL.append(")  ");
		// Get update AreaOfSpeciality SQL.
		updateAreaOfSpecialitySQL = new StringBuffer();
		updateAreaOfSpecialitySQL.append("UPDATE AREAOFSPECIALITY ");
		updateAreaOfSpecialitySQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateAreaOfSpecialitySQL.append("     NAME = ^, ");
    updateAreaOfSpecialitySQL.append("     AUDITORID = ^, ");
    updateAreaOfSpecialitySQL.append("     AUDITTIMESTAMP = ^ ");
		updateAreaOfSpecialitySQL.append("WHERE AREAOFSPECIALITYID = ^ ");
		updateAreaOfSpecialitySQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete AreaOfSpeciality SQL.
		deleteAreaOfSpecialitySQL = new StringBuffer();
		deleteAreaOfSpecialitySQL.append("UPDATE AREAOFSPECIALITY ");
		deleteAreaOfSpecialitySQL.append("SET ACTIVE = FALSE, ");
    deleteAreaOfSpecialitySQL.append("     AUDITORID = ^, ");
    deleteAreaOfSpecialitySQL.append("     AUDITTIMESTAMP = ^ ");
		deleteAreaOfSpecialitySQL.append("WHERE AREAOFSPECIALITYID = ^ ");
		deleteAreaOfSpecialitySQL.append("AND   NOOFCHANGES = ^ ");
		// Get select AreaOfSpecialities SQL.
		selectAreaOfSpecialitiesSQL = new StringBuffer();
		selectAreaOfSpecialitiesSQL.append("SELECT AREAOFSPECIALITYID, ");
    selectAreaOfSpecialitiesSQL.append("       NAME, ");
    selectAreaOfSpecialitiesSQL.append("       CREATIONTIMESTAMP, ");
    selectAreaOfSpecialitiesSQL.append("       AUDITORID, ");
    selectAreaOfSpecialitiesSQL.append("       AUDITTIMESTAMP, ");
    selectAreaOfSpecialitiesSQL.append("       ACTIVE, ");
		selectAreaOfSpecialitiesSQL.append("       NOOFCHANGES ");
		selectAreaOfSpecialitiesSQL.append("FROM AREAOFSPECIALITY ");
		// Get select AreaOfSpeciality SQL.
		selectAreaOfSpecialitySQL = new StringBuffer(selectAreaOfSpecialitiesSQL);
		selectAreaOfSpecialitySQL.append("WHERE AREAOFSPECIALITYID = ^ ");
		// Get select Active AreaOfSpecialities SQL.
		selectActiveAreaOfSpecialitiesSQL = new StringBuffer(selectAreaOfSpecialitiesSQL);
    selectActiveAreaOfSpecialitiesSQL.append("WHERE ACTIVE = TRUE ");
    selectActiveAreaOfSpecialitiesSQL.append("ORDER BY DISPLAYORDER, NAME ");
		// Get select AreaOfSpeciality for Name SQL.
		selectAreaOfSpecialityForNameSQL = new StringBuffer(selectActiveAreaOfSpecialitiesSQL);
		selectAreaOfSpecialityForNameSQL.append("AND NAME = ^ ");
		// Get select AreaOfSpeciality for Iso Code SQL.
		selectAreaOfSpecialityForCodeSQL = new StringBuffer(selectActiveAreaOfSpecialitiesSQL);
		selectAreaOfSpecialityForCodeSQL.append("AND CODE = ^ ");
	}

	public int insertAreaOfSpeciality(AreaOfSpeciality areaOfSpeciality, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertAreaOfSpecialitySQL.toString());
		// Replace the parameters with supplied values.
		areaOfSpeciality.setAreaOfSpecialityId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "areaOfSpeciality"));
		Utilities.replace(sql, areaOfSpeciality.getAreaOfSpecialityId());
    Utilities.replaceAndQuote(sql, areaOfSpeciality.getName().trim());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateAreaOfSpeciality(AreaOfSpeciality areaOfSpeciality, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateAreaOfSpecialitySQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, areaOfSpeciality.getName().trim());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, areaOfSpeciality.getAreaOfSpecialityId());
		Utilities.replace(sql, areaOfSpeciality.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteAreaOfSpeciality(Integer areaOfSpecialityId, Integer noOfChanges,
			Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteAreaOfSpecialitySQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, areaOfSpecialityId);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public AreaOfSpeciality getAreaOfSpeciality(Integer areaOfSpecialityId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAreaOfSpecialitySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, areaOfSpecialityId);
		return (AreaOfSpeciality) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AreaOfSpeciality.class.getName());
	}

	public AreaOfSpeciality getAreaOfSpecialityForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAreaOfSpecialityForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (AreaOfSpeciality) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AreaOfSpeciality.class.getName());
	}

	public AreaOfSpeciality getAreaOfSpecialityForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectAreaOfSpecialityForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (AreaOfSpeciality) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), AreaOfSpeciality.class.getName());
	}

	public List<AreaOfSpeciality> getAreaOfSpecialities() 
  {
		return getAreaOfSpecialities(false);
	}

	public List<AreaOfSpeciality> getAreaOfSpecialities(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveAreaOfSpecialitiesSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectAreaOfSpecialitiesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), AreaOfSpeciality.class.getName());
	}

	public List<AreaOfSpeciality> getActiveAreaOfSpecialities() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveAreaOfSpecialitiesSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), AreaOfSpeciality.class.getName());

	}

}
