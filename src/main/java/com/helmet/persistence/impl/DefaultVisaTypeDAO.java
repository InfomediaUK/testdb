package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.VisaType;
import com.helmet.persistence.VisaTypeDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultVisaTypeDAO extends JdbcDaoSupport implements VisaTypeDAO 
{

	private static StringBuffer insertVisaTypeSQL;

	private static StringBuffer updateVisaTypeSQL;

  private static StringBuffer updateVisaTypeDisplayOrderSQL;

	private static StringBuffer deleteVisaTypeSQL;

	private static StringBuffer selectVisaTypeSQL;

	private static StringBuffer selectVisaTypeForNameSQL;

	private static StringBuffer selectVisaTypeForCodeSQL;

	private static StringBuffer selectVisaTypesSQL;

	private static StringBuffer selectActiveVisaTypesSQL;

	public static void init() 
  {
		// Get insert VisaType SQL.
		insertVisaTypeSQL = new StringBuffer();
		insertVisaTypeSQL.append("INSERT INTO VISATYPE ");
		insertVisaTypeSQL.append("(  ");
		insertVisaTypeSQL.append("  VISATYPEID, ");
		insertVisaTypeSQL.append("  CODE, ");
    insertVisaTypeSQL.append("  NAME, ");
    insertVisaTypeSQL.append("  DISPLAYORDER, ");
    insertVisaTypeSQL.append("  CREATIONTIMESTAMP, ");
    insertVisaTypeSQL.append("  AUDITORID, ");
    insertVisaTypeSQL.append("  AUDITTIMESTAMP ");
		insertVisaTypeSQL.append(")  ");
		insertVisaTypeSQL.append("VALUES  ");
		insertVisaTypeSQL.append("(  ");
		insertVisaTypeSQL.append("  ^, ");
    insertVisaTypeSQL.append("  ^, ");
    insertVisaTypeSQL.append("  ^, ");
    insertVisaTypeSQL.append("  ^, ");
    insertVisaTypeSQL.append("  ^, ");
    insertVisaTypeSQL.append("  ^, ");
		insertVisaTypeSQL.append("  ^ ");
		insertVisaTypeSQL.append(")  ");
		// Get update VisaType SQL.
    // NOTE. Updates DisplayOrder too...
		updateVisaTypeSQL = new StringBuffer();
		updateVisaTypeSQL.append("UPDATE VISATYPE ");
		updateVisaTypeSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateVisaTypeSQL.append("     CODE = ^, ");
    updateVisaTypeSQL.append("     NAME = ^, ");
    updateVisaTypeSQL.append("     DISPLAYORDER = ^, ");
    updateVisaTypeSQL.append("     AUDITORID = ^, ");
    updateVisaTypeSQL.append("     AUDITTIMESTAMP = ^ ");
		updateVisaTypeSQL.append("WHERE VISATYPEID = ^ ");
		updateVisaTypeSQL.append("AND   NOOFCHANGES = ^ ");
    // Get updateVisaTypeDisplayOrder SQL.
    updateVisaTypeDisplayOrderSQL = new StringBuffer();
    updateVisaTypeDisplayOrderSQL.append("UPDATE VISATYPE ");
    updateVisaTypeDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
    updateVisaTypeDisplayOrderSQL.append("    AUDITORID = ^, ");
    updateVisaTypeDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
    updateVisaTypeDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updateVisaTypeDisplayOrderSQL.append("WHERE VISATYPEID = ^ ");
    updateVisaTypeDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete VisaType SQL.
		deleteVisaTypeSQL = new StringBuffer();
		deleteVisaTypeSQL.append("UPDATE VISATYPE ");
		deleteVisaTypeSQL.append("SET ACTIVE = FALSE, ");
    deleteVisaTypeSQL.append("    AUDITORID = ^, ");
    deleteVisaTypeSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteVisaTypeSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteVisaTypeSQL.append("WHERE VISATYPEID = ^ ");
		deleteVisaTypeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select VisaTypes SQL.
		selectVisaTypesSQL = new StringBuffer();
		selectVisaTypesSQL.append("SELECT VISATYPEID, ");
		selectVisaTypesSQL.append("       CODE, ");
    selectVisaTypesSQL.append("       NAME, ");
    selectVisaTypesSQL.append("       DISPLAYORDER, ");
    selectVisaTypesSQL.append("       CREATIONTIMESTAMP, ");
    selectVisaTypesSQL.append("       AUDITORID, ");
    selectVisaTypesSQL.append("       AUDITTIMESTAMP, ");
    selectVisaTypesSQL.append("       ACTIVE, ");
		selectVisaTypesSQL.append("       NOOFCHANGES ");
		selectVisaTypesSQL.append("FROM VISATYPE ");
		// Get select VisaType SQL.
		selectVisaTypeSQL = new StringBuffer(selectVisaTypesSQL);
		selectVisaTypeSQL.append("WHERE VISATYPEID = ^ ");
    // Get select VisaType for Name SQL.
    selectVisaTypeForNameSQL = new StringBuffer(selectVisaTypesSQL);
    selectVisaTypeForNameSQL.append("WHERE NAME = ^ ");
    // Get select VisaType for Iso Code SQL.
    selectVisaTypeForCodeSQL = new StringBuffer(selectVisaTypesSQL);
    selectVisaTypeForCodeSQL.append("WHERE CODE = ^ ");
		// Get select Active VisaTypes SQL.
		selectActiveVisaTypesSQL = new StringBuffer(selectVisaTypesSQL);
    selectActiveVisaTypesSQL.append("WHERE ACTIVE = TRUE ");
    selectActiveVisaTypesSQL.append("ORDER BY DISPLAYORDER, NAME ");
    // Put order by on now...
    selectVisaTypesSQL.append("ORDER BY DISPLAYORDER, NAME ");

	}

	public int insertVisaType(VisaType visaType, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertVisaTypeSQL.toString());
		// Replace the parameters with supplied values.
		visaType.setVisaTypeId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "visaType"));
		Utilities.replace(sql, visaType.getVisaTypeId());
		Utilities.replaceAndQuote(sql, visaType.getCode());
    Utilities.replaceAndQuote(sql, visaType.getName());
    Utilities.replace(sql, visaType.getDisplayOrder());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateVisaType(VisaType visaType, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateVisaTypeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, visaType.getCode());
    Utilities.replaceAndQuote(sql, visaType.getName());
    Utilities.replace(sql, visaType.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, visaType.getVisaTypeId());
		Utilities.replace(sql, visaType.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateVisaTypeDisplayOrder(VisaType visaType, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateVisaTypeDisplayOrderSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, visaType.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, visaType.getVisaTypeId());
    Utilities.replace(sql, visaType.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deleteVisaType(Integer visaTypeId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteVisaTypeSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, visaTypeId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public VisaType getVisaType(Integer visaTypeId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectVisaTypeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, visaTypeId);
		return (VisaType) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), VisaType.class.getName());
	}

	public VisaType getVisaTypeForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectVisaTypeForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (VisaType) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), VisaType.class.getName());
	}

	public VisaType getVisaTypeForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectVisaTypeForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (VisaType) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), VisaType.class.getName());
	}

	public List<VisaType> getVisaTypes() 
  {
		return getVisaTypes(false);
	}

	public List<VisaType> getVisaTypes(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveVisaTypesSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectVisaTypesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), VisaType.class.getName());
	}

	public List<VisaType> getActiveVisaTypes() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveVisaTypesSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), VisaType.class.getName());

	}

}
