package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.Administrator;
import com.helmet.bean.Country;
import com.helmet.persistence.CountryDAO;
import com.helmet.persistence.RecordFactory;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultCountryDAO extends JdbcDaoSupport implements CountryDAO {

	private static StringBuffer insertCountrySQL;

	private static StringBuffer updateCountrySQL;

	private static StringBuffer deleteCountrySQL;

	private static StringBuffer selectCountrySQL;

	private static StringBuffer selectCountryForNameSQL;

	private static StringBuffer selectCountryForIsoCodeSQL;

	private static StringBuffer selectCountriesSQL;

	private static StringBuffer selectActiveCountriesSQL;

	public static void init() {
		// Get insert Country SQL.
		insertCountrySQL = new StringBuffer();
		insertCountrySQL.append("INSERT INTO COUNTRY ");
		insertCountrySQL.append("(  ");
		insertCountrySQL.append("  COUNTRYID, ");
		insertCountrySQL.append("  NAME, ");
		insertCountrySQL.append("  ISOCODE, ");
		insertCountrySQL.append("  CREATIONTIMESTAMP, ");
		insertCountrySQL.append("  AUDITORID, ");
		insertCountrySQL.append("  AUDITTIMESTAMP ");
		insertCountrySQL.append(")  ");
		insertCountrySQL.append("VALUES  ");
		insertCountrySQL.append("(  ");
		insertCountrySQL.append("  ^, ");
		insertCountrySQL.append("  ^, ");
		insertCountrySQL.append("  ^, ");
		insertCountrySQL.append("  ^, ");
		insertCountrySQL.append("  ^, ");
		insertCountrySQL.append("  ^ ");
		insertCountrySQL.append(")  ");
		// Get update Country SQL.
		updateCountrySQL = new StringBuffer();
		updateCountrySQL.append("UPDATE COUNTRY ");
		updateCountrySQL.append("SET  NOOFCHANGES = NOOFCHANGES + 1, ");
		updateCountrySQL.append("     NAME = ^, ");
		updateCountrySQL.append("     ISOCODE = ^, ");
		updateCountrySQL.append("     AUDITORID = ^, ");
		updateCountrySQL.append("     AUDITTIMESTAMP = ^ ");
		updateCountrySQL.append("WHERE COUNTRYID = ^ ");
		updateCountrySQL.append("AND   NOOFCHANGES = ^ ");
		// Get delete Country SQL.
		deleteCountrySQL = new StringBuffer();
		// deleteCountrySQL = new StringBuffer();
		deleteCountrySQL.append("UPDATE COUNTRY ");
		deleteCountrySQL.append("SET ACTIVE = FALSE, ");
		deleteCountrySQL.append("    AUDITORID = ^, ");
		deleteCountrySQL.append("    AUDITTIMESTAMP = ^, ");
		deleteCountrySQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteCountrySQL.append("WHERE COUNTRYID = ^ ");
		deleteCountrySQL.append("AND   NOOFCHANGES = ^ ");
		// Get select Countries SQL.
		selectCountriesSQL = new StringBuffer();
		selectCountriesSQL.append("SELECT COUNTRYID, ");
		selectCountriesSQL.append("       NAME, ");
		selectCountriesSQL.append("       ISOCODE, ");
		selectCountriesSQL.append("       CREATIONTIMESTAMP, ");
		selectCountriesSQL.append("       AUDITORID, ");
		selectCountriesSQL.append("       AUDITTIMESTAMP, ");
		selectCountriesSQL.append("       ACTIVE, ");
		selectCountriesSQL.append("       NOOFCHANGES ");
		selectCountriesSQL.append("FROM COUNTRY ");
		// Get select Country SQL.
		selectCountrySQL = new StringBuffer(selectCountriesSQL);
		selectCountrySQL.append("WHERE COUNTRYID = ^ ");
		// Get select Active Countries SQL.
		selectActiveCountriesSQL = new StringBuffer(selectCountriesSQL);
		selectActiveCountriesSQL.append("WHERE ACTIVE = TRUE ");
		// Get select Country for Name SQL.
		selectCountryForNameSQL = new StringBuffer(selectActiveCountriesSQL);
		selectCountryForNameSQL.append("AND NAME = ^ ");
		// Get select Country for Iso Code SQL.
		selectCountryForIsoCodeSQL = new StringBuffer(selectActiveCountriesSQL);
		selectCountryForIsoCodeSQL.append("AND ISOCODE = ^ ");
	}

	public int insertCountry(Country country, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertCountrySQL.toString());
		// Replace the parameters with supplied values.
		country.setCountryId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "country"));
		Utilities.replace(sql, country.getCountryId());
		Utilities.replaceAndQuote(sql, country.getName());
		Utilities.replaceAndQuote(sql, country.getIsoCode());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int updateCountry(Country country, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(updateCountrySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, country.getName());
		Utilities.replaceAndQuote(sql, country.getIsoCode());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, country.getCountryId());
		Utilities.replace(sql, country.getNoOfChanges());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public int deleteCountry(Integer countryId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteCountrySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, countryId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	public Country getCountry(Integer countryId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectCountrySQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, countryId);
		return (Country) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Country.class.getName());
	}

	public Country getCountryForName(String name) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectCountryForNameSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, name);
		return (Country) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Country.class.getName());
	}

	public Country getCountryForIsoCode(String isoCode) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectCountryForIsoCodeSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replaceAndQuote(sql, isoCode);
		return (Country) RecordFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Country.class.getName());
	}

	public List<Country> getCountries() {

		return getCountries(false);
		
	}

	public List<Country> getCountries(boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveCountriesSQL.toString());
		}
		else {
			sql = new StringBuffer(selectCountriesSQL.toString()); 
		}
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Country.class.getName());

	}

	public List<Country> getActiveCountries() {

		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(selectActiveCountriesSQL.toString());
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), Country.class.getName());

	}

}
