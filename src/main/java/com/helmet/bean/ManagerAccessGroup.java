package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ManagerAccessGroup extends Base {

	private Integer managerAccessGroupId;

	private Integer managerId;

	private Integer mgrAccessGroupId;

	public Integer getManagerAccessGroupId() {
		return managerAccessGroupId;
	}

	public void setManagerAccessGroupId(Integer managerAccessGroupId) {
		this.managerAccessGroupId = managerAccessGroupId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getMgrAccessGroupId() {
		return mgrAccessGroupId;
	}

	public void setMgrAccessGroupId(Integer mgrAccessGroupId) {
		this.mgrAccessGroupId = mgrAccessGroupId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setManagerAccessGroupId(rs.getInt("MANAGERACCESSGROUPID"));
		setManagerId(rs.getInt("MANAGERID"));
		setMgrAccessGroupId(rs.getInt("MGRACCESSGROUPID"));
	}

}
