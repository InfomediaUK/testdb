package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class MgrAccessGroupItem extends Base {

	private Integer mgrAccessGroupItemId;

	private Integer mgrAccessGroupId;

	private Integer mgrAccessId;

	public Integer getMgrAccessGroupItemId() {
		return mgrAccessGroupItemId;
	}

	public void setMgrAccessGroupItemId(Integer mgrAccessGroupItemId) {
		this.mgrAccessGroupItemId = mgrAccessGroupItemId;
	}


	public Integer getMgrAccessGroupId() {
		return mgrAccessGroupId;
	}

	public void setMgrAccessGroupId(Integer mgrAccessGroupId) {
		this.mgrAccessGroupId = mgrAccessGroupId;
	}

	
	public Integer getMgrAccessId() {
		return mgrAccessId;
	}

	public void setMgrAccessId(Integer mgrAccessId) {
		this.mgrAccessId = mgrAccessId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setMgrAccessGroupItemId(rs.getInt("MGRACCESSGROUPITEMID"));
		setMgrAccessGroupId(rs.getInt("MGRACCESSGROUPID"));
		setMgrAccessId(rs.getInt("MGRACCESSID"));
	}

}
