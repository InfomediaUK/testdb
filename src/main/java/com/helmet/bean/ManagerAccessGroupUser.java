package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ManagerAccessGroupUser extends ManagerAccessGroup {

	private String mgrAccessGroupName;

	public String getMgrAccessGroupName() {
		return mgrAccessGroupName;
	}

	public void setMgrAccessGroupName(String mgrAccessGroupName) {
		this.mgrAccessGroupName = mgrAccessGroupName;
	}

    public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setMgrAccessGroupName(rs.getString("MGRACCESSGROUPNAME"));		
	}
	
}
