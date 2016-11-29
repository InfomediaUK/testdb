package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientAgencyUser extends ClientAgency {

	private String agencyName;

	private String agencyCode;
	
	private Address agencyAddress = new Address();

	private String clientName;

	private String clientCode;
	
	private Address clientAddress = new Address();

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

    public Address getAgencyAddress() {
		return agencyAddress;
	}

	public void setAgencyAddress(Address agencyAddress) {
		this.agencyAddress = agencyAddress;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public Address getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(Address clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setAgencyName(rs.getString("AGENCYNAME"));
        setAgencyCode(rs.getString("AGENCYCODE"));
        getAgencyAddress().setAddress1(rs.getString("AGENCYADDRESS1"));		
        getAgencyAddress().setAddress2(rs.getString("AGENCYADDRESS2"));		
        getAgencyAddress().setAddress3(rs.getString("AGENCYADDRESS3"));		
        getAgencyAddress().setAddress4(rs.getString("AGENCYADDRESS4"));		
        getAgencyAddress().setPostalCode(rs.getString("AGENCYPOSTALCODE"));		
        getAgencyAddress().setCountryId(rs.getInt("AGENCYCOUNTRYID"));		
		setClientName(rs.getString("CLIENTNAME"));
        setClientCode(rs.getString("CLIENTCODE"));
        getClientAddress().setAddress1(rs.getString("CLIENTADDRESS1"));		
        getClientAddress().setAddress2(rs.getString("CLIENTADDRESS2"));		
        getClientAddress().setAddress3(rs.getString("CLIENTADDRESS3"));		
        getClientAddress().setAddress4(rs.getString("CLIENTADDRESS4"));		
        getClientAddress().setPostalCode(rs.getString("CLIENTPOSTALCODE"));		
        getClientAddress().setCountryId(rs.getInt("CLIENTCOUNTRYID"));		
	}
	
}
