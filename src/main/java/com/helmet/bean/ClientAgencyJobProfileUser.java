package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientAgencyJobProfileUser extends ClientAgencyJobProfile {

	private Integer agencyId;

	private String agencyName;

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}
	
	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

    public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setAgencyId(rs.getInt("AGENCYID"));		
		setAgencyName(rs.getString("AGENCYNAME"));		
	}

}
