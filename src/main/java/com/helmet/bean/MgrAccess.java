package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class MgrAccess extends BaseAccess {

	private Integer mgrAccessId;

	public Integer getMgrAccessId() {
		return mgrAccessId;
	}

	public void setMgrAccessId(Integer mgrAccessId) {
		this.mgrAccessId = mgrAccessId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setMgrAccessId(rs.getInt("MGRACCESSID"));
	}

}
