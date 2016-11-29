package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ManagerAccessUser extends ManagerAccess {

	private String mgrAccessName;

		public String getMgrAccessName() {
		return mgrAccessName;
	}

	public void setMgrAccessName(String mgrAccessName) {
		this.mgrAccessName = mgrAccessName;
	}

    public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setMgrAccessName(rs.getString("MGRACCESSNAME"));		
	}
	
}
