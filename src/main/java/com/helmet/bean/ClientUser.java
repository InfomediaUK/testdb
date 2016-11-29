package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientUser extends Client {

	private String countryName;
	private String accountContactCountryName;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

    public String getAccountContactCountryName() {
		return accountContactCountryName;
	}

	public void setAccountContactCountryName(String accountContactCountryName) {
		this.accountContactCountryName = accountContactCountryName;
	}

  public String getListName() 
  {
    return getName() + " (" + getCode() + ")";
  }

	public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setCountryName(rs.getString("COUNTRYNAME"));		
		setAccountContactCountryName(rs.getString("ACCOUNTCONTACTCOUNTRYNAME"));		
	}
	
}
