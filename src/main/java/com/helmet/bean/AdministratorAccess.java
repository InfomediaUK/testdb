package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AdministratorAccess extends Base {

	private Integer administratorAccessId;

	private Integer administratorId;

	private Integer adminAccessId;

	public Integer getAdministratorAccessId() {
		return administratorAccessId;
	}

	public void setAdministratorAccessId(Integer administratorAccessId) {
		this.administratorAccessId = administratorAccessId;
	}

	public Integer getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}

	public Integer getAdminAccessId() {
		return adminAccessId;
	}

	public void setAdminAccessId(Integer adminAccessId) {
		this.adminAccessId = adminAccessId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAdministratorAccessId(rs.getInt("ADMINISTRATORACCESSID"));
		setAdministratorId(rs.getInt("ADMINISTRATORID"));
		setAdminAccessId(rs.getInt("ADMINACCESSID"));
	}

}
