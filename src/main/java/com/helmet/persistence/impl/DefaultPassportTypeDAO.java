package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.PassportType;
import com.helmet.persistence.PassportTypeDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultPassportTypeDAO extends JdbcDaoSupport implements PassportTypeDAO 
{

	private static StringBuffer insertPassportTypeSQL;

	private static StringBuffer updatePassportTypeSQL;

  private static StringBuffer updatePassportTypeDisplayOrderSQL;

	private static StringBuffer deletePassportTypeSQL;

	private static StringBuffer selectPassportTypeSQL;

	private static StringBuffer selectPassportTypeForNameSQL;

	private static StringBuffer selectPassportTypeForCodeSQL;

	private static StringBuffer selectPassportTypesSQL;

	private static StringBuffer selectActivePassportTypesSQL;

	public static void init() 
  {
		// Get insert PassportType SQL.
		insertPassportTypeSQL = new StringBuffer();
		insertPassportTypeSQL.append("INSERT INTO PASSPORTTYPE ");
		insertPassportTypeSQL.append("(  ");
		insertPassportTypeSQL.append("  PASSPORTTYPEID, ");
		insertPassportTypeSQL.append("  CODE, ");
    insertPassportTypeSQL.append("  NAME, ");
    insertPassportTypeSQL.append("  DISPLAYORDER, ");
    insertPassportTypeSQL.append("  CREATIONTIMESTAMP, ");
    insertPassportTypeSQL.append("  AUDITORID, ");
    insertPassportTypeSQL.append("  AUDITTIMESTAMP ");
		insertPassportTypeSQL.append(")  ");
		insertPassportTypeSQL.append("VALUES  ");
		insertPassportTypeSQL.append("(  ");
		insertPassportTypeSQL.append("  ^, ");
    insertPassportTypeSQL.append("  ^, ");
    insertPassportTypeSQL.append("  ^, ");
    insertPassportTypeSQL.append("  ^, ");
    insertPassportTypeSQL.append("  ^, ");
    insertPassportTypeSQL.append("  ^, ");
		insertPassportTypeSQL.append("  ^ ");
		insertPassportTypeSQL.append(")  ");
		// Get update PassportType SQL.
    // NOTE. Updates DisplayOrder too...
		updatePassportTypeSQL = new StringBuffer();
		updatePassportTypeSQL.append("UPDATE PASSPORTTYPE ");
		updatePassportTypeSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updatePassportTypeSQL.append("     CODE = ^, ");
    updatePassportTypeSQL.append("     NAME = ^, ");
    updatePassportTypeSQL.append("     DISPLAYORDER = ^, ");
    updatePassportTypeSQL.append("     AUDITORID = ^, ");
    updatePassportTypeSQL.append("     AUDITTIMESTAMP = ^ ");
		updatePassportTypeSQL.append("WHERE PASSPORTTYPEID = ^ ");
		updatePassportTypeSQL.append("AND   NOOFCHANGES = ^ ");
    // Get updatePassportTypeDisplayOrder SQL.
    updatePassportTypeDisplayOrderSQL = new StringBuffer();
    updatePassportTypeDisplayOrderSQL.append("UPDATE PASSPORTTYPE ");
    updatePassportTypeDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
    updatePassportTypeDisplayOrderSQL.append("    AUDITORID = ^, ");
    updatePassportTypeDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
    updatePassportTypeDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updatePassportTypeDisplayOrderSQL.append("WHERE PASSPORTTYPEID = ^ ");
    updatePassportTypeDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete PassportType SQL.
		deletePassportTypeSQL = new StringBuffer();
		deletePassportTypeSQL.append("UPDATE PASSPORTTYPE ");
		deletePassportTypeSQL.append("SET ACTIVE = FALSE, ");
    deletePassportTypeSQL.append("    AUDITORID = ^, ");
    deletePassportTypeSQL.append("    AUDITTIMESTAMP = ^, ");
    deletePassportTypeSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deletePassportTypeSQL.append("WHERE PASSPORTTYPEID = ^ ");
		deletePassportTypeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select PassportTypes SQL.
		selectPassportTypesSQL = new StringBuffer();
		selectPassportTypesSQL.append("SELECT PASSPORTTYPEID, ");
		selectPassportTypesSQL.append("       CODE, ");
    selectPassportTypesSQL.append("       NAME, ");
    selectPassportTypesSQL.append("       DISPLAYORDER, ");
    selectPassportTypesSQL.append("       CREATIONTIMESTAMP, ");
    selectPassportTypesSQL.append("       AUDITORID, ");
    selectPassportTypesSQL.append("       AUDITTIMESTAMP, ");
    selectPassportTypesSQL.append("       ACTIVE, ");
		selectPassportTypesSQL.append("       NOOFCHANGES ");
		selectPassportTypesSQL.append("FROM PASSPORTTYPE ");
		// Get select PassportType SQL.
		selectPassportTypeSQL = new StringBuffer(selectPassportTypesSQL);
		selectPassportTypeSQL.append("WHERE PASSPORTTYPEID = ^ ");
    // Get select PassportType for Name SQL.
    selectPassportTypeForNameSQL = new StringBuffer(selectPassportTypesSQL);
    selectPassportTypeForNameSQL.append("WHERE NAME = ^ ");
    // Get select PassportType for Iso Code SQL.
    selectPassportTypeForCodeSQL = new StringBuffer(selectPassportTypesSQL);
    selectPassportTypeForCodeSQL.append("WHERE CODE = ^ ");
		// Get select Active PassportTypes SQL.
		selectActivePassportTypesSQL = new StringBuffer(selectPassportTypesSQL);
    selectActivePassportTypesSQL.append("WHERE ACTIVE = TRUE ");
    selectActivePassportTypesSQL.append("ORDER BY DISPLAYORDER, NAME ");
    // Put order by on now...
    selectPassportTypesSQL.append("ORDER BY DISPLAYORDER, NAME ");

	}

	public int insertPassportType(PassportType passportType, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertPassportTypeSQL.toString());
		// Replace the parameters with supplied values.
		passportType.setPassportTypeId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "passportType"));
		Utilities.replace(sql, passportType.getPassportTypeId());
		Utilities.replaceAndQuote(sql, passportType.getCode());
    Utilities.replaceAndQuote(sql, passportType.getName());
    Utilities.replace(sql, passportType.getDisplayOrder());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updatePassportType(PassportType passportType, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updatePassportTypeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, passportType.getCode());
    Utilities.replaceAndQuote(sql, passportType.getName());
    Utilities.replace(sql, passportType.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, passportType.getPassportTypeId());
		Utilities.replace(sql, passportType.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updatePassportTypeDisplayOrder(PassportType passportType, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updatePassportTypeDisplayOrderSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, passportType.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, passportType.getPassportTypeId());
    Utilities.replace(sql, passportType.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deletePassportType(Integer passportTypeId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deletePassportTypeSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, passportTypeId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public PassportType getPassportType(Integer passportTypeId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectPassportTypeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, passportTypeId);
		return (PassportType) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), PassportType.class.getName());
	}

	public PassportType getPassportTypeForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectPassportTypeForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (PassportType) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), PassportType.class.getName());
	}

	public PassportType getPassportTypeForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectPassportTypeForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (PassportType) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), PassportType.class.getName());
	}

	public List<PassportType> getPassportTypes() 
  {
		return getPassportTypes(false);
	}

	public List<PassportType> getPassportTypes(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActivePassportTypesSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectPassportTypesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), PassportType.class.getName());
	}

	public List<PassportType> getActivePassportTypes() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActivePassportTypesSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), PassportType.class.getName());

	}

}
