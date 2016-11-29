package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class MgrAccessGroupItemUser extends MgrAccessGroupItem {

	private String mgrAccessName;

	
	// need to add adminsistrator fields !!
	
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
