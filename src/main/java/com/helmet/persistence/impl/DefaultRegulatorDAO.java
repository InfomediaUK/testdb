package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Regulator;
import com.helmet.persistence.RegulatorDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultRegulatorDAO extends JdbcDaoSupport implements RegulatorDAO 
{

	private static StringBuffer insertRegulatorSQL;

	private static StringBuffer updateRegulatorSQL;

  private static StringBuffer updateRegulatorDisplayOrderSQL;

	private static StringBuffer deleteRegulatorSQL;

	private static StringBuffer selectRegulatorSQL;

	private static StringBuffer selectRegulatorForNameSQL;

	private static StringBuffer selectRegulatorForCodeSQL;

	private static StringBuffer selectRegulatorsSQL;

	private static StringBuffer selectActiveRegulatorsSQL;

	public static void init() 
  {
		// Get insert Regulator SQL.
		insertRegulatorSQL = new StringBuffer();
		insertRegulatorSQL.append("INSERT INTO REGULATOR ");
		insertRegulatorSQL.append("(  ");
		insertRegulatorSQL.append("  REGULATORID, ");
		insertRegulatorSQL.append("  CODE, ");
    insertRegulatorSQL.append("  NAME, ");
    insertRegulatorSQL.append("  DISPLAYORDER, ");
    insertRegulatorSQL.append("  CREATIONTIMESTAMP, ");
    insertRegulatorSQL.append("  AUDITORID, ");
    insertRegulatorSQL.append("  AUDITTIMESTAMP ");
		insertRegulatorSQL.append(")  ");
		insertRegulatorSQL.append("VALUES  ");
		insertRegulatorSQL.append("(  ");
		insertRegulatorSQL.append("  ^, ");
    insertRegulatorSQL.append("  ^, ");
    insertRegulatorSQL.append("  ^, ");
    insertRegulatorSQL.append("  ^, ");
    insertRegulatorSQL.append("  ^, ");
    insertRegulatorSQL.append("  ^, ");
		insertRegulatorSQL.append("  ^ ");
		insertRegulatorSQL.append(")  ");
		// Get update Regulator SQL.
    // NOTE. Updates DisplayOrder too...
		updateRegulatorSQL = new StringBuffer();
		updateRegulatorSQL.append("UPDATE REGULATOR ");
		updateRegulatorSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateRegulatorSQL.append("     CODE = ^, ");
    updateRegulatorSQL.append("     NAME = ^, ");
    updateRegulatorSQL.append("     DISPLAYORDER = ^, ");
    updateRegulatorSQL.append("     AUDITORID = ^, ");
    updateRegulatorSQL.append("     AUDITTIMESTAMP = ^ ");
		updateRegulatorSQL.append("WHERE REGULATORID = ^ ");
		updateRegulatorSQL.append("AND   NOOFCHANGES = ^ ");
    // Get updateRegulatorDisplayOrder SQL.
    updateRegulatorDisplayOrderSQL = new StringBuffer();
    updateRegulatorDisplayOrderSQL.append("UPDATE REGULATOR ");
    updateRegulatorDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
    updateRegulatorDisplayOrderSQL.append("    AUDITORID = ^, ");
    updateRegulatorDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
    updateRegulatorDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updateRegulatorDisplayOrderSQL.append("WHERE REGULATORID = ^ ");
    updateRegulatorDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Regulator SQL.
		deleteRegulatorSQL = new StringBuffer();
		deleteRegulatorSQL.append("UPDATE REGULATOR ");
		deleteRegulatorSQL.append("SET ACTIVE = FALSE, ");
    deleteRegulatorSQL.append("    AUDITORID = ^, ");
    deleteRegulatorSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteRegulatorSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteRegulatorSQL.append("WHERE REGULATORID = ^ ");
		deleteRegulatorSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Regulators SQL.
		selectRegulatorsSQL = new StringBuffer();
		selectRegulatorsSQL.append("SELECT REGULATORID, ");
		selectRegulatorsSQL.append("       CODE, ");
    selectRegulatorsSQL.append("       NAME, ");
    selectRegulatorsSQL.append("       DISPLAYORDER, ");
    selectRegulatorsSQL.append("       CREATIONTIMESTAMP, ");
    selectRegulatorsSQL.append("       AUDITORID, ");
    selectRegulatorsSQL.append("       AUDITTIMESTAMP, ");
    selectRegulatorsSQL.append("       ACTIVE, ");
		selectRegulatorsSQL.append("       NOOFCHANGES ");
		selectRegulatorsSQL.append("FROM REGULATOR ");
		// Get select Regulator SQL.
		selectRegulatorSQL = new StringBuffer(selectRegulatorsSQL);
		selectRegulatorSQL.append("WHERE REGULATORID = ^ ");
    // Get select Regulator for Name SQL.
    selectRegulatorForNameSQL = new StringBuffer(selectRegulatorsSQL);
    selectRegulatorForNameSQL.append("WHERE NAME = ^ ");
    // Get select Regulator for Iso Code SQL.
    selectRegulatorForCodeSQL = new StringBuffer(selectRegulatorsSQL);
    selectRegulatorForCodeSQL.append("WHERE CODE = ^ ");
		// Get select Active Regulators SQL.
		selectActiveRegulatorsSQL = new StringBuffer(selectRegulatorsSQL);
    selectActiveRegulatorsSQL.append("WHERE ACTIVE = TRUE ");
    selectActiveRegulatorsSQL.append("ORDER BY DISPLAYORDER, NAME ");
    // Put order by on now...
    selectRegulatorsSQL.append("ORDER BY DISPLAYORDER, NAME ");

	}

	public int insertRegulator(Regulator regulator, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertRegulatorSQL.toString());
		// Replace the parameters with supplied values.
		regulator.setRegulatorId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "regulator"));
		Utilities.replace(sql, regulator.getRegulatorId());
		Utilities.replaceAndQuote(sql, regulator.getCode());
    Utilities.replaceAndQuote(sql, regulator.getName());
    Utilities.replace(sql, regulator.getDisplayOrder());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateRegulator(Regulator regulator, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateRegulatorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, regulator.getCode());
    Utilities.replaceAndQuote(sql, regulator.getName());
    Utilities.replace(sql, regulator.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, regulator.getRegulatorId());
		Utilities.replace(sql, regulator.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateRegulatorDisplayOrder(Regulator regulator, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateRegulatorDisplayOrderSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, regulator.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, regulator.getRegulatorId());
    Utilities.replace(sql, regulator.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deleteRegulator(Integer regulatorId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteRegulatorSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, regulatorId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public Regulator getRegulator(Integer regulatorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectRegulatorSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, regulatorId);
		return (Regulator) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Regulator.class.getName());
	}

	public Regulator getRegulatorForName(String name) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectRegulatorForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (Regulator) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Regulator.class.getName());
	}

	public Regulator getRegulatorForCode(String code) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectRegulatorForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, code);
		return (Regulator) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Regulator.class.getName());
	}

	public List<Regulator> getRegulators() 
  {
		return getRegulators(false);
	}

	public List<Regulator> getRegulators(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveRegulatorsSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectRegulatorsSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), Regulator.class.getName());
	}

	public List<Regulator> getActiveRegulators() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveRegulatorsSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), Regulator.class.getName());

	}

}
