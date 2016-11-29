package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Uplift;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.UpliftDAO;
import com.helmet.persistence.Utilities;

public class DefaultUpliftDAO extends JdbcDaoSupport implements UpliftDAO {

	private static StringBuffer insertUpliftSQL;

	private static StringBuffer updateUpliftSQL;

	private static StringBuffer deleteUpliftSQL;

	private static StringBuffer selectUpliftSQL;

	private static StringBuffer selectUpliftsSQL;

	private static StringBuffer selectUpliftsForClientSQL;

	private static StringBuffer selectActiveUpliftsForClientSQL;

	public static void init() {
		// Get insert Uplift SQL.
		insertUpliftSQL = new StringBuffer();
		insertUpliftSQL.append("INSERT INTO UPLIFT ");
		insertUpliftSQL.append("(  ");
		insertUpliftSQL.append("  UPLIFTID, ");
		insertUpliftSQL.append("  CLIENTID, ");
		insertUpliftSQL.append("  UPLIFTDAY, ");
		insertUpliftSQL.append("  UPLIFTHOUR, ");
		insertUpliftSQL.append("  UPLIFTFACTOR, ");
    insertUpliftSQL.append("  UPLIFTVALUE, ");
    insertUpliftSQL.append("  UPLIFTMINUTEPERIOD, ");
		insertUpliftSQL.append("  CREATIONTIMESTAMP, ");
		insertUpliftSQL.append("  AUDITORID, ");
		insertUpliftSQL.append("  AUDITTIMESTAMP ");
		insertUpliftSQL.append(")  ");
		insertUpliftSQL.append("VALUES  ");
		insertUpliftSQL.append("(  ");
		insertUpliftSQL.append("  ^, ");
		insertUpliftSQL.append("  ^, ");
		insertUpliftSQL.append("  ^, ");
    insertUpliftSQL.append("  ^, ");
    insertUpliftSQL.append("  ^, ");
		insertUpliftSQL.append("  ^, ");
		insertUpliftSQL.append("  ^, ");
		insertUpliftSQL.append("  ^, ");
		insertUpliftSQL.append("  ^, ");
		insertUpliftSQL.append("  ^ ");
		insertUpliftSQL.append(")  ");
		// Get update Uplift SQL.
		updateUpliftSQL = new StringBuffer();
		updateUpliftSQL.append("UPDATE UPLIFT ");
		updateUpliftSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateUpliftSQL.append("     CLIENTID = ^, ");
		updateUpliftSQL.append("     UPLIFTDAY = ^, ");
		updateUpliftSQL.append("     UPLIFTHOUR = ^, ");
		updateUpliftSQL.append("     UPLIFTFACTOR = ^, ");
    updateUpliftSQL.append("     UPLIFTVALUE = ^, ");
    updateUpliftSQL.append("     UPLIFTMINUTEPERIOD = ^, ");
		updateUpliftSQL.append("     AUDITORID = ^, ");
		updateUpliftSQL.append("     AUDITTIMESTAMP = ^ ");
		updateUpliftSQL.append("WHERE UPLIFTID = ^ ");
		updateUpliftSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Uplift SQL.
		deleteUpliftSQL = new StringBuffer();
		// deleteUpliftSQL = new StringBuffer();
		deleteUpliftSQL.append("UPDATE UPLIFT ");
		deleteUpliftSQL.append("SET ACTIVE = FALSE, ");
		deleteUpliftSQL.append("    AUDITORID = ^, ");
		deleteUpliftSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteUpliftSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteUpliftSQL.append("WHERE UPLIFTID = ^ ");
		deleteUpliftSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Uplifts SQL.
		selectUpliftsSQL = new StringBuffer();
		selectUpliftsSQL.append("SELECT U.UPLIFTID, ");
		selectUpliftsSQL.append("       U.CLIENTID, ");
		selectUpliftsSQL.append("       U.UPLIFTDAY, ");
		selectUpliftsSQL.append("       U.UPLIFTHOUR, ");
		selectUpliftsSQL.append("       U.UPLIFTFACTOR, ");
    selectUpliftsSQL.append("       U.UPLIFTVALUE, ");
    selectUpliftsSQL.append("       U.UPLIFTMINUTEPERIOD, ");
		selectUpliftsSQL.append("       U.CREATIONTIMESTAMP, ");
		selectUpliftsSQL.append("       U.AUDITORID, ");
		selectUpliftsSQL.append("       U.AUDITTIMESTAMP, ");
		selectUpliftsSQL.append("       U.ACTIVE, ");
		selectUpliftsSQL.append("       U.NOOFCHANGES ");
		selectUpliftsSQL.append("FROM UPLIFT U ");
		// Get select Uplifts for Client SQL.
		selectUpliftsForClientSQL = new StringBuffer(selectUpliftsSQL);
		selectUpliftsForClientSQL.append("WHERE U.CLIENTID = ^ ");
		// Get select Active Uplifts for Client SQL.
		selectActiveUpliftsForClientSQL = new StringBuffer(selectUpliftsForClientSQL);
		selectActiveUpliftsForClientSQL.append("AND U.ACTIVE = TRUE ");
		// Get select Uplift SQL.
		selectUpliftSQL = new StringBuffer(selectUpliftsSQL);
		selectUpliftSQL.append("WHERE U.UPLIFTID = ^ ");
		// ORDER BY 
		selectUpliftsForClientSQL.append("ORDER BY U.UPLIFTDAY, U.UPLIFTHOUR ");
		selectActiveUpliftsForClientSQL.append("ORDER BY U.UPLIFTDAY, U.UPLIFTHOUR ");
	}

	public int insertUplift(Uplift uplift, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertUpliftSQL.toString());
		// Replace the parameters with supplied values.
		uplift.setUpliftId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "uplift"));
		Utilities.replace(sql, uplift.getUpliftId());
		Utilities.replace(sql, uplift.getClientId());
		Utilities.replace(sql, uplift.getUpliftDay());
		Utilities.replace(sql, uplift.getUpliftHour());
		Utilities.replace(sql, uplift.getUpliftFactor());
    Utilities.replace(sql, uplift.getUpliftValue());
    Utilities.replace(sql, uplift.getUpliftMinutePeriod());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateUplift(Uplift uplift, Integer auditorId) 
  {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateUpliftSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, uplift.getClientId());
		Utilities.replace(sql, uplift.getUpliftDay());
		Utilities.replace(sql, uplift.getUpliftHour());
		Utilities.replace(sql, uplift.getUpliftFactor());
		Utilities.replace(sql, uplift.getUpliftValue());
    Utilities.replace(sql, uplift.getUpliftMinutePeriod());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, uplift.getUpliftId());
		Utilities.replace(sql, uplift.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int deleteUplift(Integer upliftId, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteUpliftSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, upliftId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public Uplift getUplift(Integer upliftId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectUpliftSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, upliftId);
		return (Uplift) RecordFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Uplift.class.getName());
	}

	public List<Uplift> getUpliftsForClient(Integer clientId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveUpliftsForClientSQL.toString());
		}
		else {
			sql = new StringBuffer(selectUpliftsForClientSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, clientId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(), Uplift.class.getName());

	}

}
