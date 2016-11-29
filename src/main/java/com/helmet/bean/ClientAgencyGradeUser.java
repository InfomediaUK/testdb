package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientAgencyGradeUser extends ClientAgencyGrade {

	private Integer agencyId;

	private String agencyName;

	private String gradeName;

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

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setAgencyId(rs.getInt("AGENCYID"));		
		setAgencyName(rs.getString("AGENCYNAME"));		
		setGradeName(rs.getString("GRADENAME"));		
	}
	
}
