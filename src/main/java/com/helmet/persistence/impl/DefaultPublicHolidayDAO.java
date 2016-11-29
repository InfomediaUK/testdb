package com.helmet.persistence.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.PublicHoliday;
import com.helmet.persistence.PublicHolidayDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultPublicHolidayDAO extends JdbcDaoSupport implements PublicHolidayDAO {

	private static StringBuffer insertPublicHolidaySQL;

	private static StringBuffer updatePublicHolidaySQL;

	private static StringBuffer deletePublicHolidaySQL;

  private static StringBuffer selectPublicHolidaySQL;

  private static StringBuffer selectPublicHolidayForClientDateSQL;

	private static StringBuffer selectPublicHolidaysSQL;

	private static StringBuffer selectPublicHolidaysForClientSQL;

	private static StringBuffer selectActivePublicHolidaysForClientSQL;

	public static void init() {
		// Get insert PublicHoliday SQL.
		insertPublicHolidaySQL = new StringBuffer();
		insertPublicHolidaySQL.append("INSERT INTO PUBLICHOLIDAY ");
		insertPublicHolidaySQL.append("(  ");
		insertPublicHolidaySQL.append("  PUBLICHOLIDAYID, ");
		insertPublicHolidaySQL.append("  CLIENTID, ");
		insertPublicHolidaySQL.append("  NAME, ");
		insertPublicHolidaySQL.append("  PHDATE, ");
		insertPublicHolidaySQL.append("  CREATIONTIMESTAMP, ");
		insertPublicHolidaySQL.append("  AUDITORID, ");
		insertPublicHolidaySQL.append("  AUDITTIMESTAMP ");
		insertPublicHolidaySQL.append(")  ");
		insertPublicHolidaySQL.append("VALUES  ");
		insertPublicHolidaySQL.append("(  ");
		insertPublicHolidaySQL.append("  ^, ");
		insertPublicHolidaySQL.append("  ^, ");
		insertPublicHolidaySQL.append("  ^, ");
		insertPublicHolidaySQL.append("  ^, ");
		insertPublicHolidaySQL.append("  ^, ");
		insertPublicHolidaySQL.append("  ^, ");
		insertPublicHolidaySQL.append("  ^ ");
		insertPublicHolidaySQL.append(")  ");
		// Get update PublicHoliday SQL.
		updatePublicHolidaySQL = new StringBuffer();
		updatePublicHolidaySQL.append("UPDATE PUBLICHOLIDAY ");
		updatePublicHolidaySQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updatePublicHolidaySQL.append("     CLIENTID = ^, ");
		updatePublicHolidaySQL.append("     NAME = ^, ");
		updatePublicHolidaySQL.append("     PHDATE = ^, ");
		updatePublicHolidaySQL.append("     AUDITORID = ^, ");
		updatePublicHolidaySQL.append("     AUDITTIMESTAMP = ^ ");
		updatePublicHolidaySQL.append("WHERE PUBLICHOLIDAYID = ^ ");
		updatePublicHolidaySQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete PublicHoliday SQL.
		deletePublicHolidaySQL = new StringBuffer();
		// deletePublicHolidaySQL = new StringBuffer();
		deletePublicHolidaySQL.append("UPDATE PUBLICHOLIDAY ");
		deletePublicHolidaySQL.append("SET ACTIVE = FALSE, ");
		deletePublicHolidaySQL.append("    AUDITORID = ^, ");
		deletePublicHolidaySQL.append("    AUDITTIMESTAMP = ^, ");
		deletePublicHolidaySQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deletePublicHolidaySQL.append("WHERE PUBLICHOLIDAYID = ^ ");
		deletePublicHolidaySQL.append("AND   NOOFCHANGES = ^ ");
		// Get select PublicHolidays SQL.
		selectPublicHolidaysSQL = new StringBuffer();
		selectPublicHolidaysSQL.append("SELECT P.PUBLICHOLIDAYID, ");
		selectPublicHolidaysSQL.append("       P.CLIENTID, ");
		selectPublicHolidaysSQL.append("       P.NAME, ");
		selectPublicHolidaysSQL.append("       P.PHDATE, ");
		selectPublicHolidaysSQL.append("       P.CREATIONTIMESTAMP, ");
		selectPublicHolidaysSQL.append("       P.AUDITORID, ");
		selectPublicHolidaysSQL.append("       P.AUDITTIMESTAMP, ");
		selectPublicHolidaysSQL.append("       P.ACTIVE, ");
		selectPublicHolidaysSQL.append("       P.NOOFCHANGES ");
		selectPublicHolidaysSQL.append("FROM PUBLICHOLIDAY P ");
		// Get select PublicHolidays for Client SQL.
		selectPublicHolidaysForClientSQL = new StringBuffer(selectPublicHolidaysSQL);
		selectPublicHolidaysForClientSQL.append("WHERE P.CLIENTID = ^ ");
		// Get select Active PublicHolidays for Client SQL.
		selectActivePublicHolidaysForClientSQL = new StringBuffer(selectPublicHolidaysForClientSQL);
		selectActivePublicHolidaysForClientSQL.append("AND P.ACTIVE = TRUE ");
    // Get select PublicHoliday SQL.
    selectPublicHolidaySQL = new StringBuffer(selectPublicHolidaysSQL);
    selectPublicHolidaySQL.append("WHERE P.PUBLICHOLIDAYID = ^ ");
    // Get select PublicHoliday for Client & Date SQL.
    selectPublicHolidayForClientDateSQL = new StringBuffer(selectPublicHolidaysForClientSQL);
    selectPublicHolidayForClientDateSQL.append("AND P.PHDATE = ^ ");
		// ORDER BY 
		selectPublicHolidaysForClientSQL.append("ORDER BY P.PHDATE ");
		selectActivePublicHolidaysForClientSQL.append("ORDER BY P.PHDATE ");
	}

	public int insertPublicHoliday(PublicHoliday publicHoliday, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertPublicHolidaySQL.toString());
		// Replace the parameters with supplied values.
		publicHoliday.setPublicHolidayId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "publicHoliday"));
		Utilities.replace(sql, publicHoliday.getPublicHolidayId());
		Utilities.replace(sql, publicHoliday.getClientId());
		Utilities.replaceAndQuote(sql, publicHoliday.getName());
		Utilities.replaceAndQuote(sql, publicHoliday.getPhDate());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updatePublicHoliday(PublicHoliday publicHoliday, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updatePublicHolidaySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, publicHoliday.getClientId());
		Utilities.replaceAndQuote(sql, publicHoliday.getName());
		Utilities.replaceAndQuote(sql, publicHoliday.getPhDate());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, publicHoliday.getPublicHolidayId());
		Utilities.replace(sql, publicHoliday.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deletePublicHoliday(Integer publicHolidayId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deletePublicHolidaySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, publicHolidayId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

  public PublicHoliday getPublicHoliday(Integer publicHolidayId) {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectPublicHolidaySQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, publicHolidayId);
    return (PublicHoliday) RecordFactory.getInstance().get(getJdbcTemplate(),
        sql.toString(), PublicHoliday.class.getName());
  }

  public PublicHoliday getPublicHolidayForClientDate(Integer clientId, Date date) 
  {
    // Create a new local StringBuffer containing the parameterised SQL.
    StringBuffer sql = new StringBuffer(selectPublicHolidayForClientDateSQL.toString());
    // Replace the parameters with supplied values.
    Utilities.replace(sql, clientId);
    Utilities.replaceAndQuote(sql, date);
    return (PublicHoliday) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), PublicHoliday.class.getName());
  }

	public List<PublicHoliday> getPublicHolidaysForClient(Integer clientId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActivePublicHolidaysForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectPublicHolidaysForClientSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), PublicHoliday.class.getName());

	}

}
