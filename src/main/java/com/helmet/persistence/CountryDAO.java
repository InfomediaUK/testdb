package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Country;

public interface CountryDAO {

	public List<Country> getCountries(boolean showOnlyActive);
	public Country getCountry(Integer countryId);
	public Country getCountryForName(String name);
	public Country getCountryForIsoCode(String isoCode);
	public int insertCountry(Country country, Integer auditorId);
	public int updateCountry(Country country, Integer auditorId);
	public int deleteCountry(Integer countryId, Integer noOfChanges, Integer auditorId);

}
