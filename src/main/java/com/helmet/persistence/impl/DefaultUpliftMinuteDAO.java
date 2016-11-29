package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.UpliftMinute;
import com.helmet.bean.UpliftMinuteUser;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.UpliftMinuteDAO;
import com.helmet.persistence.Utilities;

public class DefaultUpliftMinuteDAO extends JdbcDaoSupport implements UpliftMinuteDAO 
{

	private static StringBuffer insertUpliftMinuteSQL;

	private static StringBuffer updateUpliftMinuteSQL;

  private static StringBuffer deleteUpliftMinuteSQL;

  private static StringBuffer deleteUpliftMinutesForUpliftSQL;

	private static StringBuffer selectUpliftMinuteSQL;

  private static StringBuffer selectUpliftMinutesSQL;

  private static StringBuffer selectUpliftMinuteUsersSQL;

  private static StringBuffer selectUpliftMinutesForUpliftSQL;

  private static StringBuffer selectUpliftMinuteUsersForUpliftSQL;

  private static StringBuffer selectActiveUpliftMinutesForUpliftSQL;

  private static StringBuffer selectActiveUpliftMinuteUsersForUpliftSQL;

  private static StringBuffer selectUpliftMinutesForClientSQL;

  private static StringBuffer selectActiveUpliftMinutesForClientSQL;

	public static void init() 
  {
		// Get insert UpliftMinute SQL.
		insertUpliftMinuteSQL = new StringBuffer();
		insertUpliftMinuteSQL.append("INSERT INTO UPLIFTMINUTE ");
		insertUpliftMinuteSQL.append("(  ");
    insertUpliftMinuteSQL.append("  UPLIFTMINUTEID, ");
    insertUpliftMinuteSQL.append("  UPLIFTID, ");
		insertUpliftMinuteSQL.append("  UPLIFTMINUTE, ");
		insertUpliftMinuteSQL.append("  UPLIFTFACTOR, ");
		insertUpliftMinuteSQL.append("  UPLIFTVALUE, ");
		insertUpliftMinuteSQL.append("  CREATIONTIMESTAMP, ");
		insertUpliftMinuteSQL.append("  AUDITORID, ");
		insertUpliftMinuteSQL.append("  AUDITTIMESTAMP ");
		insertUpliftMinuteSQL.append(")  ");
		insertUpliftMinuteSQL.append("VALUES  ");
		insertUpliftMinuteSQL.append("(  ");
		insertUpliftMinuteSQL.append("  ^, ");
		insertUpliftMinuteSQL.append("  ^, ");
		insertUpliftMinuteSQL.append("  ^, ");
		insertUpliftMinuteSQL.append("  ^, ");
		insertUpliftMinuteSQL.append("  ^, ");
		insertUpliftMinuteSQL.append("  ^, ");
		insertUpliftMinuteSQL.append("  ^, ");
		insertUpliftMinuteSQL.append("  ^ ");
		insertUpliftMinuteSQL.append(")  ");
		// Get update UpliftMinute SQL.
		updateUpliftMinuteSQL = new StringBuffer();
		updateUpliftMinuteSQL.append("UPDATE UPLIFTMINUTE ");
		updateUpliftMinuteSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateUpliftMinuteSQL.append("     UPLIFTFACTOR = ^, ");
		updateUpliftMinuteSQL.append("     UPLIFTVALUE = ^, ");
    updateUpliftMinuteSQL.append("     ACTIVE = TRUE, ");
    updateUpliftMinuteSQL.append("     AUDITORID = ^, ");
		updateUpliftMinuteSQL.append("     AUDITTIMESTAMP = ^ ");
		updateUpliftMinuteSQL.append("WHERE UPLIFTMINUTEID = ^ ");
		updateUpliftMinuteSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete UpliftMinute SQL.
		deleteUpliftMinuteSQL = new StringBuffer();
		// deleteUpliftMinuteSQL = new StringBuffer();
		deleteUpliftMinuteSQL.append("UPDATE UPLIFTMINUTE ");
		deleteUpliftMinuteSQL.append("SET ACTIVE = FALSE, ");
		deleteUpliftMinuteSQL.append("    AUDITORID = ^, ");
		deleteUpliftMinuteSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteUpliftMinuteSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteUpliftMinuteSQL.append("WHERE UPLIFTMINUTEID = ^ ");
		deleteUpliftMinuteSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select UpliftMinutes SQL.
		selectUpliftMinutesSQL = new StringBuffer();
		selectUpliftMinutesSQL.append("SELECT UM.UPLIFTMINUTEID, ");
		selectUpliftMinutesSQL.append("       UM.UPLIFTID, ");
		selectUpliftMinutesSQL.append("       UM.UPLIFTMINUTE, ");
		selectUpliftMinutesSQL.append("       UM.UPLIFTFACTOR, ");
		selectUpliftMinutesSQL.append("       UM.UPLIFTVALUE, ");
		selectUpliftMinutesSQL.append("       UM.CREATIONTIMESTAMP, ");
		selectUpliftMinutesSQL.append("       UM.AUDITORID, ");
		selectUpliftMinutesSQL.append("       UM.AUDITTIMESTAMP, ");
		selectUpliftMinutesSQL.append("       UM.ACTIVE, ");
		selectUpliftMinutesSQL.append("       UM.NOOFCHANGES ");
		selectUpliftMinutesSQL.append("FROM UPLIFTMINUTE UM ");
    // Get select UpliftMinuteUsers SQL.
    selectUpliftMinuteUsersSQL = new StringBuffer();
    selectUpliftMinuteUsersSQL.append("SELECT UM.UPLIFTMINUTEID, ");
    selectUpliftMinuteUsersSQL.append("       UM.UPLIFTID, ");
    selectUpliftMinuteUsersSQL.append("       UM.UPLIFTMINUTE, ");
    selectUpliftMinuteUsersSQL.append("       UM.UPLIFTFACTOR, ");
    selectUpliftMinuteUsersSQL.append("       UM.UPLIFTVALUE, ");
    selectUpliftMinuteUsersSQL.append("       UM.CREATIONTIMESTAMP, ");
    selectUpliftMinuteUsersSQL.append("       UM.AUDITORID, ");
    selectUpliftMinuteUsersSQL.append("       UM.AUDITTIMESTAMP, ");
    selectUpliftMinuteUsersSQL.append("       UM.ACTIVE, ");
    selectUpliftMinuteUsersSQL.append("       UM.NOOFCHANGES, ");
    selectUpliftMinuteUsersSQL.append("       U.UPLIFTDAY, ");
    selectUpliftMinuteUsersSQL.append("       U.UPLIFTHOUR, ");
    selectUpliftMinuteUsersSQL.append("       U.UPLIFTMINUTEPERIOD ");
    selectUpliftMinuteUsersSQL.append("FROM UPLIFTMINUTE UM ");
    selectUpliftMinuteUsersSQL.append("JOIN UPLIFT U ");
    selectUpliftMinuteUsersSQL.append("ON  U.UPLIFTID = UM.UPLIFTID ");
		// Get select UpliftMinutes for Uplift SQL.
		selectUpliftMinutesForUpliftSQL = new StringBuffer(selectUpliftMinutesSQL);
		selectUpliftMinutesForUpliftSQL.append("WHERE UM.UPLIFTID = ^ ");
		// Get select Active UpliftMinutes for Uplift SQL.
		selectActiveUpliftMinutesForUpliftSQL = new StringBuffer(selectUpliftMinutesForUpliftSQL);
		selectActiveUpliftMinutesForUpliftSQL.append("AND UM.ACTIVE = TRUE ");
    // Get select UpliftMinuteUsers for Uplift SQL.
    selectUpliftMinuteUsersForUpliftSQL = new StringBuffer(selectUpliftMinuteUsersSQL);
    selectUpliftMinuteUsersForUpliftSQL.append("WHERE UM.UPLIFTID = ^ ");
    // Get select Active UpliftMinuteUsers for Uplift SQL.
    selectActiveUpliftMinuteUsersForUpliftSQL = new StringBuffer(selectUpliftMinuteUsersForUpliftSQL);
    selectActiveUpliftMinuteUsersForUpliftSQL.append("AND UM.ACTIVE = TRUE ");
    // Get select UpliftMinutes for Client SQL.
    selectUpliftMinutesForClientSQL = new StringBuffer(selectUpliftMinutesSQL);
    selectUpliftMinutesForClientSQL.append("JOIN UPLIFT U ");
    selectUpliftMinutesForClientSQL.append("ON U.UPLIFTID = UM.UPLIFTID ");
    selectUpliftMinutesForClientSQL.append("WHERE U.CLIENTID = ^ ");
    // Get select Active UpliftMinutes for Client SQL.
    selectActiveUpliftMinutesForClientSQL = new StringBuffer(selectUpliftMinutesForClientSQL);
    selectActiveUpliftMinutesForClientSQL.append("AND UM.ACTIVE = TRUE ");
		// Get select UpliftMinute SQL.
		selectUpliftMinuteSQL = new StringBuffer(selectUpliftMinutesSQL);
		selectUpliftMinuteSQL.append("WHERE UM.UPLIFTMINUTEID = ^ ");
		// ORDER BY 
    selectUpliftMinutesForUpliftSQL.append("ORDER BY UM.UPLIFTMINUTE ");
    selectActiveUpliftMinutesForUpliftSQL.append("ORDER BY UM.UPLIFTMINUTE ");
    selectUpliftMinuteUsersForUpliftSQL.append("ORDER BY UM.UPLIFTMINUTE ");
    selectActiveUpliftMinuteUsersForUpliftSQL.append("ORDER BY UM.UPLIFTMINUTE ");
    selectUpliftMinutesForClientSQL.append("ORDER BY U.UPLIFTID, UM.UPLIFTMINUTE ");
    selectActiveUpliftMinutesForClientSQL.append("ORDER BY U.UPLIFTDAY, U.UPLIFTHOUR, UM.UPLIFTMINUTE ");
	}

	public int insertUpliftMinute(UpliftMinute upliftMinute, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertUpliftMinuteSQL.toString());
		// Replace the parameters with supplied values.
		upliftMinute.setUpliftMinuteId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "upliftMinute"));
		Utilities.replace(sql, upliftMinute.getUpliftMinuteId());
		Utilities.replace(sql, upliftMinute.getUpliftId());
		Utilities.replace(sql, upliftMinute.getUpliftMinute());
		Utilities.replace(sql, upliftMinute.getUpliftFactor());
		Utilities.replace(sql, upliftMinute.getUpliftValue());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateUpliftMinute(UpliftMinute upliftMinute, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateUpliftMinuteSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, upliftMinute.getUpliftFactor());
		Utilities.replace(sql, upliftMinute.getUpliftValue());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, upliftMinute.getUpliftMinuteId());
		Utilities.replace(sql, upliftMinute.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteUpliftMinute(Integer upliftMinuteId, Integer noOfChanges, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteUpliftMinuteSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, upliftMinuteId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public UpliftMinute getUpliftMinute(Integer upliftMinuteId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectUpliftMinuteSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, upliftMinuteId);
		return (UpliftMinute) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), UpliftMinute.class.getName());
	}

	public List<UpliftMinute> getUpliftMinutesForUplift(Integer upliftId, boolean showOnlyActive) 
  {
		StringBuffer sql = null;
		if (showOnlyActive) 
    {
			sql = new StringBuffer(selectActiveUpliftMinutesForUpliftSQL.toString());
		}
		else 
    {
			sql = new StringBuffer(selectUpliftMinutesForUpliftSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, upliftId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), UpliftMinute.class.getName());
	}

  public List<UpliftMinuteUser> getUpliftMinuteUsersForUplift(Integer upliftId, boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveUpliftMinuteUsersForUpliftSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectUpliftMinuteUsersForUpliftSQL.toString()); 
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, upliftId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), UpliftMinuteUser.class.getName());
  }

  public List<UpliftMinute> getUpliftMinutesForClient(Integer clientId) 
  {
    return getUpliftMinutesForClient(clientId, true);
  }
  
  public List<UpliftMinute> getUpliftMinutesForClient(Integer clientId, boolean showOnlyActive) 
  {
    StringBuffer sql = null;
    if (showOnlyActive) 
    {
      sql = new StringBuffer(selectActiveUpliftMinutesForClientSQL.toString());
    }
    else 
    {
      sql = new StringBuffer(selectUpliftMinutesForClientSQL.toString()); 
    }
    // Replace the parameters with supplied values.
    Utilities.replace(sql, clientId);
    return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), UpliftMinute.class.getName());
  }

}
