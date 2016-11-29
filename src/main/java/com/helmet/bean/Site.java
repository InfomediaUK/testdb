package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Site extends BaseDisplayOrder {

	private Integer siteId;

	private Integer clientId;

	private String name;

	private String code;

	private Address address = new Address();

  private String nhsLocation;
  
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNhsLocation()
  {
    return nhsLocation;
  }

  public void setNhsLocation(String nhsLocation)
  {
    this.nhsLocation = nhsLocation;
  }

  public boolean getNhsMatched()
  {
    return name.equals(nhsLocation);
  }
  
  public void load(SqlRowSet rs)
    {
	    super.load(rs);
        setSiteId(rs.getInt("SITEID"));		
        setClientId(rs.getInt("CLIENTID"));		
        setName(rs.getString("NAME"));
        getAddress().setAddress1(rs.getString("ADDRESS1"));		
        getAddress().setAddress2(rs.getString("ADDRESS2"));		
        getAddress().setAddress3(rs.getString("ADDRESS3"));		
        getAddress().setAddress4(rs.getString("ADDRESS4"));		
        getAddress().setPostalCode(rs.getString("POSTALCODE"));		
        getAddress().setCountryId(rs.getInt("COUNTRYID"));		
        setCode(rs.getString("CODE"));
        setNhsLocation(rs.getString("NHSLOCATION"));
	}
	
}
