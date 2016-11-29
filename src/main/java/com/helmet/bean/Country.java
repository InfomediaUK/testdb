package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Country extends Base {

	private Integer countryId;

	private String name;

	private String isoCode;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public void load(SqlRowSet rs)
	{
	    super.load(rs);
	    setCountryId(rs.getInt("COUNTRYID"));		
		setName(rs.getString("NAME"));		
		setIsoCode(rs.getString("ISOCODE"));		
	}
	
	
}
