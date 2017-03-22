package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.CompliancyTest;
import com.helmet.persistence.CompliancyTestDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultCompliancyTestDAO extends JdbcDaoSupport implements CompliancyTestDAO 
{

	private static StringBuffer insertCompliancyTestSQL;

	private static StringBuffer updateCompliancyTestSQL;

  private static StringBuffer updateCompliancyTestDisplayOrderSQL;

	private static StringBuffer deleteCompliancyTestSQL;

	private static StringBuffer selectCompliancyTestSQL;

	private static StringBuffer selectCompliancyTestForPropertySQL;

	private static StringBuffer selectCompliancyTestForValueSQL;

	private static StringBuffer selectCompliancyTestsSQL;

	private static StringBuffer selectActiveCompliancyTestsSQL;

	public static void init() 
  {
		// Get insert CompliancyTest SQL.
		insertCompliancyTestSQL = new StringBuffer();
		insertCompliancyTestSQL.append("INSERT INTO COMPLIANCYTEST ");
		insertCompliancyTestSQL.append("(  ");
		insertCompliancyTestSQL.append("  COMPLIANCYTESTID, ");
    insertCompliancyTestSQL.append("  PROPERTY, ");
    insertCompliancyTestSQL.append("  VALUE, ");
    insertCompliancyTestSQL.append("  REQUIREDDOCUMENTTEXT, ");
    insertCompliancyTestSQL.append("  DISPLAYORDER, ");
    insertCompliancyTestSQL.append("  CREATIONTIMESTAMP, ");
    insertCompliancyTestSQL.append("  AUDITORID, ");
    insertCompliancyTestSQL.append("  AUDITTIMESTAMP ");
		insertCompliancyTestSQL.append(")  ");
		insertCompliancyTestSQL.append("VALUES  ");
		insertCompliancyTestSQL.append("(  ");
		insertCompliancyTestSQL.append("  ^, ");
    insertCompliancyTestSQL.append("  ^, ");
    insertCompliancyTestSQL.append("  ^, ");
    insertCompliancyTestSQL.append("  ^, ");
    insertCompliancyTestSQL.append("  ^, ");
    insertCompliancyTestSQL.append("  ^, ");
    insertCompliancyTestSQL.append("  ^, ");
		insertCompliancyTestSQL.append("  ^ ");
		insertCompliancyTestSQL.append(")  ");
		// Get update CompliancyTest SQL.
    // NOTE. Updates DisplayOrder too...
		updateCompliancyTestSQL = new StringBuffer();
		updateCompliancyTestSQL.append("UPDATE COMPLIANCYTEST ");
		updateCompliancyTestSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateCompliancyTestSQL.append("     PROPERTY = ^, ");
    updateCompliancyTestSQL.append("     VALUE = ^, ");
    updateCompliancyTestSQL.append("     REQUIREDDOCUMENTTEXT = ^, ");
    updateCompliancyTestSQL.append("     DISPLAYORDER = ^, ");
    updateCompliancyTestSQL.append("     AUDITORID = ^, ");
    updateCompliancyTestSQL.append("     AUDITTIMESTAMP = ^ ");
		updateCompliancyTestSQL.append("WHERE COMPLIANCYTESTID = ^ ");
		updateCompliancyTestSQL.append("AND   NOOFCHANGES = ^ ");
    // Get updateCompliancyTestDisplayOrder SQL.
    updateCompliancyTestDisplayOrderSQL = new StringBuffer();
    updateCompliancyTestDisplayOrderSQL.append("UPDATE COMPLIANCYTEST ");
    updateCompliancyTestDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
    updateCompliancyTestDisplayOrderSQL.append("    AUDITORID = ^, ");
    updateCompliancyTestDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
    updateCompliancyTestDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
    updateCompliancyTestDisplayOrderSQL.append("WHERE COMPLIANCYTESTID = ^ ");
    updateCompliancyTestDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete CompliancyTest SQL.
		deleteCompliancyTestSQL = new StringBuffer();
		deleteCompliancyTestSQL.append("UPDATE COMPLIANCYTEST ");
		deleteCompliancyTestSQL.append("SET ACTIVE = FALSE, ");
    deleteCompliancyTestSQL.append("    AUDITORID = ^, ");
    deleteCompliancyTestSQL.append("    AUDITTIMESTAMP = ^, ");
    deleteCompliancyTestSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteCompliancyTestSQL.append("WHERE COMPLIANCYTESTID = ^ ");
		deleteCompliancyTestSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select CompliancyTests SQL.
		selectCompliancyTestsSQL = new StringBuffer();
		selectCompliancyTestsSQL.append("SELECT COMPLIANCYTESTID, ");
    selectCompliancyTestsSQL.append("       PROPERTY, ");
    selectCompliancyTestsSQL.append("       VALUE, ");
    selectCompliancyTestsSQL.append("       REQUIREDDOCUMENTTEXT, ");
    selectCompliancyTestsSQL.append("       DISPLAYORDER, ");
    selectCompliancyTestsSQL.append("       CREATIONTIMESTAMP, ");
    selectCompliancyTestsSQL.append("       AUDITORID, ");
    selectCompliancyTestsSQL.append("       AUDITTIMESTAMP, ");
    selectCompliancyTestsSQL.append("       ACTIVE, ");
		selectCompliancyTestsSQL.append("       NOOFCHANGES ");
		selectCompliancyTestsSQL.append("FROM COMPLIANCYTEST ");
		// Get select CompliancyTest SQL.
		selectCompliancyTestSQL = new StringBuffer(selectCompliancyTestsSQL);
		selectCompliancyTestSQL.append("WHERE COMPLIANCYTESTID = ^ ");
    // Get select CompliancyTest for Property SQL.
    selectCompliancyTestForPropertySQL = new StringBuffer(selectCompliancyTestsSQL);
    selectCompliancyTestForPropertySQL.append("WHERE PROPERTY = ^ ");
    // Get select CompliancyTest for Iso Value SQL.
    selectCompliancyTestForValueSQL = new StringBuffer(selectCompliancyTestsSQL);
    selectCompliancyTestForValueSQL.append("WHERE VALUE = ^ ");
		// Get select Active CompliancyTests SQL.
		selectActiveCompliancyTestsSQL = new StringBuffer(selectCompliancyTestsSQL);
    selectActiveCompliancyTestsSQL.append("WHERE ACTIVE = TRUE ");
    selectActiveCompliancyTestsSQL.append("ORDER BY DISPLAYORDER, PROPERTY ");
    // Put order by on now...
    selectCompliancyTestsSQL.append("ORDER BY DISPLAYORDER, PROPERTY ");

	}

	public int insertCompliancyTest(CompliancyTest compliancyTest, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertCompliancyTestSQL.toString());
		// Replace the parameters with supplied values.
		compliancyTest.setCompliancyTestId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "compliancyTest"));
		Utilities.replace(sql, compliancyTest.getCompliancyTestId());
    Utilities.replaceAndQuote(sql, compliancyTest.getProperty());
    Utilities.replaceAndQuote(sql, compliancyTest.getValue());
    Utilities.replaceAndQuote(sql, compliancyTest.getRequiredDocumentText());
    Utilities.replace(sql, compliancyTest.getDisplayOrder());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateCompliancyTest(CompliancyTest compliancyTest, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateCompliancyTestSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replaceAndQuote(sql, compliancyTest.getProperty());
    Utilities.replaceAndQuote(sql, compliancyTest.getValue());
    Utilities.replaceAndQuote(sql, compliancyTest.getRequiredDocumentText());
    Utilities.replace(sql, compliancyTest.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, compliancyTest.getCompliancyTestId());
		Utilities.replace(sql, compliancyTest.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateCompliancyTestDisplayOrder(CompliancyTest compliancyTest, Integer auditorId) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateCompliancyTestDisplayOrderSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, compliancyTest.getDisplayOrder());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, compliancyTest.getCompliancyTestId());
    Utilities.replace(sql, compliancyTest.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }

	public int deleteCompliancyTest(Integer compliancyTestId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteCompliancyTestSQL.toString());
		// Replace the parameters with supplied values.
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    // Start of Where clause.
    Utilities.replace(sql, compliancyTestId);
    Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public CompliancyTest getCompliancyTest(Integer compliancyTestId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectCompliancyTestSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, compliancyTestId);
		return (CompliancyTest) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), CompliancyTest.class.getName());
	}

	public CompliancyTest getCompliancyTestForProperty(String property) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectCompliancyTestForPropertySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, property);
		return (CompliancyTest) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), CompliancyTest.class.getName());
	}

	public List<CompliancyTest> getCompliancyTests() 
  {
		return getCompliancyTests(false);
	}

	public List<CompliancyTest> getCompliancyTests(boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveCompliancyTestsSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectCompliancyTestsSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), CompliancyTest.class.getName());
	}

	public List<CompliancyTest> getActiveCompliancyTests() 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveCompliancyTestsSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), CompliancyTest.class.getName());

	}

}
