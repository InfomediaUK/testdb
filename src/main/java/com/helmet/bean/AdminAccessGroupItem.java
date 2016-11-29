package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AdminAccessGroupItem extends Base {

	private Integer adminAccessGroupItemId;

	private Integer adminAccessGroupId;

	private Integer adminAccessId;

	public Integer getAdminAccessGroupItemId() {
		return adminAccessGroupItemId;
	}

	public void setAdminAccessGroupItemId(Integer adminAccessGroupItemId) {
		this.adminAccessGroupItemId = adminAccessGroupItemId;
	}


	public Integer getAdminAccessGroupId() {
		return adminAccessGroupId;
	}

	public void setAdminAccessGroupId(Integer adminAccessGroupId) {
		this.adminAccessGroupId = adminAccessGroupId;
	}

	
	public Integer getAdminAccessId() {
		return adminAccessId;
	}

	public void setAdminAccessId(Integer adminAccessId) {
		this.adminAccessId = adminAccessId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAdminAccessGroupItemId(rs.getInt("ADMINACCESSGROUPITEMID"));
		setAdminAccessGroupId(rs.getInt("ADMINACCESSGROUPID"));
		setAdminAccessId(rs.getInt("ADMINACCESSID"));
	}

}
