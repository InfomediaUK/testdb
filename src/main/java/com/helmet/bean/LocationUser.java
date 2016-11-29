package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LocationUser extends Location {

	private Integer clientId;

	private String clientName;

	private String clientCode;

	private String siteName;

	private String siteCode;

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setClientId(rs.getInt("CLIENTID"));
		setClientName(rs.getString("CLIENTNAME"));
		setClientCode(rs.getString("CLIENTCODE"));
		setSiteName(rs.getString("SITENAME"));
		setSiteCode(rs.getString("SITECODE"));
	}

}
