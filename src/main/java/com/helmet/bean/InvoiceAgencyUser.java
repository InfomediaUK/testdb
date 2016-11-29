package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class InvoiceAgencyUser extends InvoiceAgency {

	private Integer clientId;
	private String agencyCode;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setClientId(rs.getInt("CLIENTID"));
		setAgencyCode(rs.getString("AGENCYCODE"));
	}

}
