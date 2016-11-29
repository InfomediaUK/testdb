package com.helmet.persistence.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Unavailable;
import com.helmet.persistence.UnavailableDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultUnavailableDAO extends JdbcDaoSupport implements UnavailableDAO 
{

	private static StringBuffer insertUnavailableSQL;

	private static StringBuffer selectUnavailableSQL;

  private static StringBuffer selectUnavailablesForApplicantSQL;

  private static StringBuffer selectActiveUnavailablesForApplicantSQL;

  private static StringBuffer selectUnavailablesForApplicantInDateRangeSQL;

  private static StringBuffer selectActiveUnavailablesForApplicantInDateRangeSQL;

  private static StringBuffer selectActiveUnavailablesInDateRangeSQL;

	private static StringBuffer selectUnavailablesSQL;

  private static StringBuffer updateUnavailableSQL;

	public static void init() 
  {
		// Get insert Unavailable SQL.
		insertUnavailableSQL = new StringBuffer();
		insertUnavailableSQL.append("INSERT INTO UNAVAILABLE ");
		insertUnavailableSQL.append("(  ");
		insertUnavailableSQL.append("  UNAVAILABLEID, ");
    insertUnavailableSQL.append("  AGENCYID, ");
    insertUnavailableSQL.append("  APPLICANTID, ");
    insertUnavailableSQL.append("  UNAVAILABLEDATE, ");
    insertUnavailableSQL.append("  CREATIONTIMESTAMP, ");
    insertUnavailableSQL.append("  AUDITORID, ");
    insertUnavailableSQL.append("  AUDITTIMESTAMP ");
		insertUnavailableSQL.append(")  ");
		insertUnavailableSQL.append("VALUES  ");
		insertUnavailableSQL.append("(  ");
		insertUnavailableSQL.append("  ^, ");
		insertUnavailableSQL.append("  ^, ");
    insertUnavailableSQL.append("  ^, ");
    insertUnavailableSQL.append("  ^, ");
    insertUnavailableSQL.append("  ^, ");
    insertUnavailableSQL.append("  ^, ");
		insertUnavailableSQL.append("  ^ ");
		insertUnavailableSQL.append(")  ");
		// Get select Unavailables SQL.
		selectUnavailablesSQL = new StringBuffer();
		selectUnavailablesSQL.append("SELECT UNAVAILABLEID, ");
    selectUnavailablesSQL.append("       AGENCYID, ");
    selectUnavailablesSQL.append("       APPLICANTID, ");
    selectUnavailablesSQL.append("       UNAVAILABLEDATE, ");
    selectUnavailablesSQL.append("       CREATIONTIMESTAMP, ");
    selectUnavailablesSQL.append("       AUDITORID, ");
    selectUnavailablesSQL.append("       AUDITTIMESTAMP, ");
    selectUnavailablesSQL.append("       ACTIVE, ");
		selectUnavailablesSQL.append("       NOOFCHANGES ");
		selectUnavailablesSQL.append("FROM UNAVAILABLE ");
		// Get select Unavailable SQL.
		selectUnavailableSQL = new StringBuffer(selectUnavailablesSQL);
		selectUnavailableSQL.append("WHERE UNAVAILABLEID = ^ ");
    // Get select Unavailables for Applicant SQL.
    selectUnavailablesForApplicantSQL = new StringBuffer(selectUnavailablesSQL);
    selectUnavailablesForApplicantSQL.append("WHERE AGENCYID = ^ ");
    selectUnavailablesForApplicantSQL.append("AND APPLICANTID = ^ ");
    // Get select Unavailables for Applicant within Date Range SQL.
    selectUnavailablesForApplicantInDateRangeSQL = new StringBuffer(selectUnavailablesForApplicantSQL);
    selectUnavailablesForApplicantInDateRangeSQL.append("AND UNAVAILABLEDATE BETWEEN ^ AND ^ ");
    selectUnavailablesForApplicantInDateRangeSQL.append("ORDER BY UNAVAILABLEDATE ");
    // Get select ACTIVE Unavailables for Applicant SQL.
    selectActiveUnavailablesForApplicantSQL = new StringBuffer(selectUnavailablesForApplicantSQL);
    selectActiveUnavailablesForApplicantSQL.append("AND ACTIVE = TRUE ");
    // Get select ACTIVE Unavailables for Applicant within Date Range SQL.
    selectActiveUnavailablesForApplicantInDateRangeSQL = new StringBuffer(selectActiveUnavailablesForApplicantSQL);
    selectActiveUnavailablesForApplicantInDateRangeSQL.append("AND UNAVAILABLEDATE BETWEEN ^ AND ^ ");
    selectActiveUnavailablesForApplicantInDateRangeSQL.append("ORDER BY UNAVAILABLEDATE ");
    // Get select ACTIVE Unavailables within Date Range SQL.
    selectActiveUnavailablesInDateRangeSQL = new StringBuffer(selectUnavailablesSQL);
    selectActiveUnavailablesInDateRangeSQL.append("WHERE AGENCYID = ^ ");
    selectActiveUnavailablesInDateRangeSQL.append("AND ACTIVE = TRUE ");
    selectActiveUnavailablesInDateRangeSQL.append("AND UNAVAILABLEDATE BETWEEN ^ AND ^ ");
    selectActiveUnavailablesInDateRangeSQL.append("ORDER BY UNAVAILABLEDATE ");
    // Add Order By...
    selectActiveUnavailablesForApplicantSQL.append("ORDER BY UNAVAILABLEDATE ");
    // Get update Unavailable SQL.
    updateUnavailableSQL = new StringBuffer();
    updateUnavailableSQL.append("UPDATE UNAVAILABLE ");
    updateUnavailableSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
    updateUnavailableSQL.append("     ACTIVE = ^, ");
    updateUnavailableSQL.append("     AUDITORID = ^, ");
    updateUnavailableSQL.append("     AUDITTIMESTAMP = ^ ");
    updateUnavailableSQL.append("WHERE UNAVAILABLEID = ^ ");
    updateUnavailableSQL.append("AND   NOOFCHANGES = ^ ");
	}

	public int insertUnavailable(Unavailable unavailable, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertUnavailableSQL.toString());
		// Replace the parameters with supplied values.
		unavailable.setUnavailableId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "unavailable"));
		Utilities.replace(sql, unavailable.getUnavailableId());
    Utilities.replace(sql, unavailable.getAgencyId());
    Utilities.replace(sql, unavailable.getApplicantId());
    Utilities.replaceAndQuote(sql, unavailable.getUnavailableDate());
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

  public int updateUnavailable(Unavailable unavailable, Integer auditorId) {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(updateUnavailableSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, unavailable.getActive());
    Utilities.replace(sql, auditorId);
    Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
    Utilities.replace(sql, unavailable.getUnavailableId());
    Utilities.replace(sql, unavailable.getNoOfChanges());
    return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
  }
  
	public Unavailable getUnavailable(Integer unavailableId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectUnavailableSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, unavailableId);
		return (Unavailable) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Unavailable.class.getName());
	}

  public List<Unavailable> getUnavailablesForApplicant(Integer agencyId, Integer applicantId, boolean showOnlyActive) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = null;
    if (showOnlyActive)
    {
      sql = new StringBuffer(selectActiveUnavailablesForApplicantSQL.toString());
    }
    else
    {
      sql = new StringBuffer(selectUnavailablesForApplicantSQL.toString());
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, applicantId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Unavailable.class.getName());
  }

  public List<Unavailable> getUnavailablesForApplicantInDateRange(Integer agencyId, Integer applicantId, boolean showOnlyActive, java.util.Date fromDate, java.util.Date toDate) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = null;
    if (showOnlyActive)
    {
      sql = new StringBuffer(selectActiveUnavailablesForApplicantInDateRangeSQL.toString());
    }
    else
    {
      sql = new StringBuffer(selectUnavailablesForApplicantInDateRangeSQL.toString());
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replace(sql, applicantId);
    Utilities.replaceAndQuote(sql, fromDate);
    Utilities.replaceAndQuote(sql, toDate);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Unavailable.class.getName());
  }

  public List<Unavailable> getActiveUnavailablesInDateRange(Integer agencyId, java.util.Date fromDate, java.util.Date toDate) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectActiveUnavailablesInDateRangeSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, agencyId);
    Utilities.replaceAndQuote(sql, fromDate);
    Utilities.replaceAndQuote(sql, toDate);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Unavailable.class.getName());
  }

	public List<Unavailable> getUnavailables() 
  {
		StringBuffer sql = null;
    sql = new StringBuffer(selectUnavailablesSQL.toString()); 
		return RecordListFactory.getInstance().get(getJdbcTemplate(),	sql.toString(), Unavailable.class.getName());
	}

}
