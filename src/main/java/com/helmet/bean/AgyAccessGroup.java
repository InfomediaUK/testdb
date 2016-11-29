package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AgyAccessGroup extends Base {

	private Integer agyAccessGroupId;

	private Integer agencyId;

	private String name;

	public Integer getAgyAccessGroupId() {
		return agyAccessGroupId;
	}

	public void setAgyAccessGroupId(Integer agyAccessGroupId) {
		this.agyAccessGroupId = agyAccessGroupId;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAgyAccessGroupId(rs.getInt("AGYACCESSGROUPID"));
		setAgencyId(rs.getInt("AGENCYID"));
		setName(rs.getString("NAME"));
	}

}
