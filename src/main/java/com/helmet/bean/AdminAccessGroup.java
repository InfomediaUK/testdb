package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AdminAccessGroup extends Base {

	private Integer adminAccessGroupId;

	private String name;

	public Integer getAdminAccessGroupId() {
		return adminAccessGroupId;
	}

	public void setAdminAccessGroupId(Integer adminAccessGroupId) {
		this.adminAccessGroupId = adminAccessGroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAdminAccessGroupId(rs.getInt("ADMINACCESSGROUPID"));
		setName(rs.getString("NAME"));
	}

}
