package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.DressCode;
import com.helmet.persistence.DressCodeDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultDressCodeDAO extends JdbcDaoSupport implements DressCodeDAO {

	private static StringBuffer insertDressCodeSQL;

	private static StringBuffer updateDressCodeSQL;

	private static StringBuffer updateDressCodeDisplayOrderSQL;

	private static StringBuffer deleteDressCodeSQL;

	private static StringBuffer selectDressCodesSQL;

	private static StringBuffer selectDressCodesForLocationSQL;

	private static StringBuffer selectActiveDressCodesForLocationSQL;

	private static StringBuffer selectDressCodeSQL;

	private static StringBuffer selectDressCodeForNameSQL;

	public static void init() {
		// Get insert DressCode SQL.
		insertDressCodeSQL = new StringBuffer();
		insertDressCodeSQL.append("INSERT INTO DRESSCODE ");
		insertDressCodeSQL.append("(  ");
		insertDressCodeSQL.append("  DRESSCODEID, ");
		insertDressCodeSQL.append("  LOCATIONID, ");
		insertDressCodeSQL.append("  NAME, ");
		insertDressCodeSQL.append("  CREATIONTIMESTAMP, ");
		insertDressCodeSQL.append("  AUDITORID, ");
		insertDressCodeSQL.append("  AUDITTIMESTAMP ");
		insertDressCodeSQL.append(")  ");
		insertDressCodeSQL.append("VALUES  ");
		insertDressCodeSQL.append("(  ");
		insertDressCodeSQL.append("  ^, ");
		insertDressCodeSQL.append("  ^, ");
		insertDressCodeSQL.append("  ^, ");
		insertDressCodeSQL.append("  ^, ");
		insertDressCodeSQL.append("  ^, ");
		insertDressCodeSQL.append("  ^ ");
		insertDressCodeSQL.append(")  ");
		// Get update DressCode SQL.
		updateDressCodeSQL = new StringBuffer();
		updateDressCodeSQL.append("UPDATE DRESSCODE ");
		updateDressCodeSQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateDressCodeSQL.append("     LOCATIONID = ^, ");
		updateDressCodeSQL.append("     NAME = ^, ");
		updateDressCodeSQL.append("     AUDITORID = ^, ");
		updateDressCodeSQL.append("     AUDITTIMESTAMP = ^ ");
		updateDressCodeSQL.append("WHERE DRESSCODEID = ^ ");
		updateDressCodeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get updateDressCodeDisplayOrder SQL.
		updateDressCodeDisplayOrderSQL = new StringBuffer();
		updateDressCodeDisplayOrderSQL.append("UPDATE DRESSCODE ");
		updateDressCodeDisplayOrderSQL.append("SET DISPLAYORDER = ^, ");
		updateDressCodeDisplayOrderSQL.append("    AUDITORID = ^, ");
		updateDressCodeDisplayOrderSQL.append("    AUDITTIMESTAMP = ^, ");
		updateDressCodeDisplayOrderSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		updateDressCodeDisplayOrderSQL.append("WHERE DRESSCODEID = ^ ");
		updateDressCodeDisplayOrderSQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete DressCode SQL.
		deleteDressCodeSQL = new StringBuffer();
		deleteDressCodeSQL.append("UPDATE DRESSCODE ");
		deleteDressCodeSQL.append("SET ACTIVE = FALSE, ");
		deleteDressCodeSQL.append("    AUDITORID = ^, ");
		deleteDressCodeSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteDressCodeSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteDressCodeSQL.append("WHERE DRESSCODEID = ^ ");
		deleteDressCodeSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select DressCodes SQL.
		selectDressCodesSQL = new StringBuffer();
		selectDressCodesSQL.append("SELECT ");
		selectDressCodesSQL.append("       D.DRESSCODEID, ");
		selectDressCodesSQL.append("       D.LOCATIONID, ");
		selectDressCodesSQL.append("       D.NAME, ");
		selectDressCodesSQL.append("       D.DISPLAYORDER, ");
		selectDressCodesSQL.append("       D.CREATIONTIMESTAMP, ");
		selectDressCodesSQL.append("       D.AUDITORID, ");
		selectDressCodesSQL.append("       D.AUDITTIMESTAMP, ");
		selectDressCodesSQL.append("       D.ACTIVE, ");
		selectDressCodesSQL.append("       D.NOOFCHANGES ");
		selectDressCodesSQL.append("FROM DRESSCODE D ");
		// Get select DressCode SQL.
		selectDressCodeSQL = new StringBuffer(selectDressCodesSQL);
		selectDressCodeSQL.append("WHERE D.DRESSCODEID = ^ ");
		// Get select DressCodes for Location SQL.
		selectDressCodesForLocationSQL = new StringBuffer(selectDressCodesSQL);
		selectDressCodesForLocationSQL.append("WHERE D.LOCATIONID = ^ ");
		// Get select Active DressCodes for Location SQL.
		selectActiveDressCodesForLocationSQL = new StringBuffer(selectDressCodesForLocationSQL);
		selectActiveDressCodesForLocationSQL.append("AND D.ACTIVE = TRUE ");
		// Get select DressCode for Name SQL.
		selectDressCodeForNameSQL = new StringBuffer(selectActiveDressCodesForLocationSQL);
		selectDressCodeForNameSQL.append("AND D.NAME = ^ ");
        //
		selectDressCodesForLocationSQL.append("ORDER BY D.DISPLAYORDER, D.NAME ");
		//
		selectActiveDressCodesForLocationSQL.append("ORDER BY D.DISPLAYORDER, D.NAME "); 
		
	}

	public List<DressCode> getDressCodesForLocation(Integer locationId, boolean showOnlyActive) {
		
		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveDressCodesForLocationSQL.toString());
		}
		else {
			sql = new StringBuffer(selectDressCodesForLocationSQL.toString());
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(), sql.toString(),
				DressCode.class.getName());
		
	}
	public DressCode getDressCode(Integer dressCodeId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectDressCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, dressCodeId);
		return (DressCode) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), DressCode.class.getName());
	}
	public DressCode getDressCodeForName(Integer locationId, String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectDressCodeForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, locationId);
		Utilities.replaceAndQuote(sql, name);
		return (DressCode) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), DressCode.class.getName());
	}
	public int insertDressCode(DressCode dressCode, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertDressCodeSQL.toString());
		// Replace the parameters with supplied values.
		dressCode.setDressCodeId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "dressCode"));
		Utilities.replace(sql, dressCode.getDressCodeId());
		Utilities.replace(sql, dressCode.getLocationId());
		Utilities.replaceAndQuote(sql, dressCode.getName());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int updateDressCode(DressCode dressCode, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateDressCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, dressCode.getLocationId());
		Utilities.replaceAndQuote(sql, dressCode.getName());
        Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, dressCode.getDressCodeId());
		Utilities.replace(sql, dressCode.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
	public int deleteDressCode(Integer dressCodeId, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteDressCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date().getTime()).toString());
		Utilities.replace(sql, dressCodeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}

	public int updateDressCodeDisplayOrder(Integer dressCodeId, Integer displayOrder, Integer noOfChanges, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateDressCodeDisplayOrderSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, displayOrder);
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, dressCodeId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(), sql.toString());
	}
}
