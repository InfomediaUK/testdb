package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ManagerAccess extends Base {

	private Integer managerAccessId;

	private Integer managerId;

	private Integer mgrAccessId;

	public Integer getManagerAccessId() {
		return managerAccessId;
	}

	public void setManagerAccessId(Integer managerAccessId) {
		this.managerAccessId = managerAccessId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getMgrAccessId() {
		return mgrAccessId;
	}

	public void setMgrAccessId(Integer mgrAccessId) {
		this.mgrAccessId = mgrAccessId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setManagerAccessId(rs.getInt("MANAGERACCESSID"));
		setManagerId(rs.getInt("MANAGERID"));
		setMgrAccessId(rs.getInt("MGRACCESSID"));
	}

}
