package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AdministratorAccessGroup extends Base {

	private Integer administratorAccessGroupId;

	private Integer administratorId;

	private Integer adminAccessGroupId;

	public Integer getAdministratorAccessGroupId() {
		return administratorAccessGroupId;
	}

	public void setAdministratorAccessGroupId(Integer administratorAccessGroupId) {
		this.administratorAccessGroupId = administratorAccessGroupId;
	}

	public Integer getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}

	public Integer getAdminAccessGroupId() {
		return adminAccessGroupId;
	}

	public void setAdminAccessGroupId(Integer adminAccessGroupId) {
		this.adminAccessGroupId = adminAccessGroupId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAdministratorAccessGroupId(rs.getInt("ADMINISTRATORACCESSGROUPID"));
		setAdministratorId(rs.getInt("ADMINISTRATORID"));
		setAdminAccessGroupId(rs.getInt("ADMINACCESSGROUPID"));
	}

}
