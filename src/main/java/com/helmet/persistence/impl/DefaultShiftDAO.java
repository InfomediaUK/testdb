package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Shift;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.ShiftDAO;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultShiftDAO extends JdbcDaoSupport implements ShiftDAO {

	private static StringBuffer insertShiftSQL;

	private static StringBuffer updateShiftSQL;

	private static StringBuffer updateShiftDisplayOrderSQL;

	private static StringBuffer deleteShiftSQL;

	private static StringBuffer selectShiftsSQL;

	private static StringBuffer selectShiftsForLocationSQL;

	private static StringBuffer selectActiveShiftsForLocationSQL;

	private static StringBuffer selectShiftSQL;

	private static StringBuffer selectShiftForNameSQL;

	private static StringBuffer selectShiftForCodeSQL;

	public static void init() {
		// Get insert Shift SQL.
		insertShiftSQL = new StringBuffer();
		insertShiftSQL.append("INSERT INTO SHIFT ");
		insertShiftSQL.append("(  ");
		insertShiftSQL.append("  SHIFTID, ");
		insertShiftSQL.append("  LOCATIONID, ");
		insertShiftSQL.append("  NAME, ");
		insertShiftSQL.append("  DESCRIPTION, ");
		insertShiftSQL.append("  CODE, ");
		insertShiftSQL.append("  STARTTIME, ");
		insertShiftSQL.append("  ENDTIME, ");
		insertShiftSQL.append("  BREAKSTARTTIME, ");
		insertShiftSQL.append("  BREAKENDTIME, ");
		insertShiftSQL.append("  NOOFHOURS, ");
		insertShiftSQL.append("  BREAKNOOFHOURS, ");
		insertShiftSQL.append("  UPLIFTFACTOR, ");
		insertShiftSQL.append("  UPLIFTVALUE, ");
		insertShiftSQL.append("  USESHIFTUPLIFT, ");
		insertShiftSQL.append("  OVERTIMENOOFHOURS, ");
		insertShiftSQL.append("  OVERTIMEUPLIFTFACTOR, ");
		insertShiftSQL.append("  CREATIONTIMESTAMP, ");
		insertShiftSQL.append("  AUDITORID, ");
		insertShiftSQL.append("  AUDITTIMESTAMP ");
		insertShiftSQL.append(")  ");
		insertShiftSQL.append("VALUES  ");
		insertShiftSQL.append("(  ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^, ");
		insertShiftSQL.append("  ^ ");
		insertShiftSQL.append(")  ");
		// Get update Shift SQL.
		updateShiftSQL = new StringBuffer();
		updateShiftSQL.append("UPDATE SHIFT ");
		updateShiftSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateShiftSQL.append("     LOCATIONID = ^, ");
		updateShiftSQL.append("     NAME = ^, ");
		updateShiftSQL.append("     DESCRIPTION = ^, ");
		updateShiftSQL.append("     CODE = ^, ");
		updateShiftSQL.append("     STARTTIME = ^, ");
		updateShiftSQL.append("     ENDTIME = ^, ");
		updateShiftSQL.append("     BREAKSTARTTIME = ^, ");
		updateShiftSQL.append("     BREAKENDTIME = ^, ");
		updateShiftSQL.append("     NOOFHOURS = ^, ");
		updateShiftSQL.append("     BREAKNOOFHOURS = ^, ");
		updateShiftSQL.append("     UPLIFTFACTOR = ^, ");
		updateShiftSQL.append("     UPLIFTVALUE = ^, ");
		updateShiftSQL.append("     USESHIFTUPLIFT = ^, ");
		updateShiftSQL.append("     OVERTIMENOOFHOURS = ^, ");
		updateShiftSQL.append("     OVERTIMEUPLIFTFACTOR = ^, ");
		updateShiftSQL.append("     AUDITORID = ^, ");
		updateShiftSQL.append("     AUDITTIMESTAMP = ^ ");
		updateShiftSQL.append("WHERE SHIFTID = ^ ");
		updateShiftSQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateShiftDisplayOrder SQL.
		updateShiftDisplayOrderSQL = new StringBuffer();
		updateShiftDisplayOrderSQL.append("UPDATE SHIFT ");
		updateShiftDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateShiftDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateShiftDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateShiftDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateShiftDisplayOrderSQL.append("WHERE SHIFTID = ^ ");
		updateShiftDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Shift SQL.
		deleteShiftSQL = new StringBuffer();
		deleteShiftSQL.append("UPDATE SHIFT ");
		deleteShiftSQL.append("SET ACTIVE = FALSE, ");
		deleteShiftSQL.append("    AUDITORID = ^, ");
		deleteShiftSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteShiftSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteShiftSQL.append("WHERE SHIFTID = ^ ");
		deleteShiftSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Shifts SQL.
		selectShiftsSQL = new StringBuffer();
		selectShiftsSQL.append("SELECT ");
		selectShiftsSQL.append("       S.SHIFTID, ");
		selectShiftsSQL.append("       S.LOCATIONID, ");
		selectShiftsSQL.append("       S.NAME, ");
		selectShiftsSQL.append("       S.DESCRIPTION, ");
		selectShiftsSQL.append("       S.CODE, ");
		selectShiftsSQL.append("       S.STARTTIME, ");
		selectShiftsSQL.append("       S.ENDTIME, ");
		selectShiftsSQL.append("       S.BREAKSTARTTIME, ");
		selectShiftsSQL.append("       S.BREAKENDTIME, ");
		selectShiftsSQL.append("       S.NOOFHOURS, ");
		selectShiftsSQL.append("       S.BREAKNOOFHOURS, ");
		selectShiftsSQL.append("       S.UPLIFTFACTOR, ");
		selectShiftsSQL.append("       S.UPLIFTVALUE, ");
		selectShiftsSQL.append("       S.USESHIFTUPLIFT, ");
		selectShiftsSQL.append("       S.OVERTIMENOOFHOURS, ");
		selectShiftsSQL.append("       S.OVERTIMEUPLIFTFACTOR, ");
		selectShiftsSQL.append("       S.DISPLAYORDER, ");
		selectShiftsSQL.append("       S.CREATIONTIMESTAMP, ");
		selectShiftsSQL.append("       S.AUDITORID, ");
		selectShiftsSQL.append("       S.AUDITTIMESTAMP, ");
		selectShiftsSQL.append("       S.ACTIVE, ");
		selectShiftsSQL.append("       S.NOOFCHANGES ");
		selectShiftsSQL.append("FROM SHIFT S ");
		// Get select Shift SQL.
		selectShiftSQL = new StringBuffer(selectShiftsSQL);
		selectShiftSQL.append("WHERE S.SHIFTID = ^ ");
		// Get select Shifts for Location SQL.
		selectShiftsForLocationSQL = new StringBuffer(selectShiftsSQL);
		selectShiftsForLocationSQL.append("WHERE S.LOCATIONID = ^ ");
		// Get select Active Shifts for Location SQL.
		selectActiveShiftsForLocationSQL = new StringBuffer(selectShiftsForLocationSQL);
		selectActiveShiftsForLocationSQL.append("AND S.ACTIVE = TRUE ");
		// Get select Shift for Name SQL.
		selectShiftForNameSQL = new StringBuffer(selectActiveShiftsForLocationSQL);
		selectShiftForNameSQL.append("AND S.NAME = ^ ");
		// Get select Shift for Code SQL.
		selectShiftForCodeSQL = new StringBuffer(selectActiveShiftsForLocationSQL);
		selectShiftForCodeSQL.append("AND S.CODE = ^ ");
        //
		selectShiftsForLocationSQL.append("ORDER BY S.DISPLAYORDER, S.STARTTIME ");
		//
		selectActiveShiftsForLocationSQL.append("ORDER BY S.DISPLAYORDER, S.STARTTIME ");
		
	}

	public List<Shift> getShiftsForLocation(Integer locationId, boolean showOnlyActive) {
		
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveShiftsForLocationSQL.toString());
		}
		else {
			sql = new StringBuffer(selectShiftsForLocationSQL.toString());
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				Shift.class.getName());
		
	}
	public Shift getShift(Integer shiftId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectShiftSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, shiftId);
		return (Shift) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Shift.class.getName());
	}
	public Shift getShiftForName(Integer locationId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectShiftForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		Utilities.replaceAndQuote(sql, name);
		return (Shift) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Shift.class.getName());
	}
	public Shift getShiftForCode(Integer locationId, String code) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectShiftForCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		Utilities.replaceAndQuote(sql, code);
		return (Shift) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Shift.class.getName());
	}
	public int insertShift(Shift shift, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertShiftSQL.toString());
		// Replace the parameters with supplied values.
		shift.setShiftId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "shift"));
		Utilities.replace(sql, shift.getShiftId());
		Utilities.replace(sql, shift.getLocationId());
		Utilities.replaceAndQuote(sql, shift.getName());
		Utilities.replaceAndQuoteNullable(sql, shift.getDescription());
		Utilities.replaceAndQuoteNullable(sql, shift.getCode());
		Utilities.replaceAndQuote(sql, shift.getStartTime());
		Utilities.replaceAndQuote(sql, shift.getEndTime());
		Utilities.replaceAndQuoteNullable(sql, shift.getBreakStartTime());
		Utilities.replaceAndQuoteNullable(sql, shift.getBreakEndTime());
		Utilities.replace(sql, shift.getNoOfHours());
		Utilities.replace(sql, shift.getBreakNoOfHours());
		Utilities.replace(sql, shift.getUpliftFactor());
		Utilities.replace(sql, shift.getUpliftValue());
		Utilities.replace(sql, shift.getUseShiftUplift());
		Utilities.replace(sql, shift.getOvertimeNoOfHours());
		Utilities.replace(sql, shift.getOvertimeUpliftFactor());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateShift(Shift shift, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateShiftSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, shift.getLocationId());
		Utilities.replaceAndQuote(sql, shift.getName());
		Utilities.replaceAndQuote(sql, shift.getDescription());
		Utilities.replaceAndQuoteNullable(sql, shift.getCode());
		Utilities.replaceAndQuote(sql, shift.getStartTime());
		Utilities.replaceAndQuote(sql, shift.getEndTime());
		Utilities.replaceAndQuoteNullable(sql, shift.getBreakStartTime());
		Utilities.replaceAndQuoteNullable(sql, shift.getBreakEndTime());
		Utilities.replace(sql, shift.getNoOfHours());
		Utilities.replace(sql, shift.getBreakNoOfHours());
		Utilities.replace(sql, shift.getUpliftFactor());
		Utilities.replace(sql, shift.getUpliftValue());
		Utilities.replace(sql, shift.getUseShiftUplift());
		Utilities.replace(sql, shift.getOvertimeNoOfHours());
		Utilities.replace(sql, shift.getOvertimeUpliftFactor());
        Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, shift.getShiftId());
		Utilities.replace(sql, shift.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int deleteShift(Integer shiftId, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteShiftSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, shiftId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateShiftDisplayOrder(Integer shiftId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateShiftDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, shiftId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
}
