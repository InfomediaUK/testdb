package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AdminAccess extends BaseAccess {

	private Integer adminAccessId;

	public Integer getAdminAccessId() {
		return adminAccessId;
	}

	public void setAdminAccessId(Integer adminAccessId) {
		this.adminAccessId = adminAccessId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAdminAccessId(rs.getInt("ADMINACCESSID"));
	}

}
