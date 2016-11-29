package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ConsultantAccessGroupUser extends ConsultantAccessGroup {

	private String agyAccessGroupName;

	public String getAgyAccessGroupName() {
		return agyAccessGroupName;
	}

	public void setAgyAccessGroupName(String agyAccessGroupName) {
		this.agyAccessGroupName = agyAccessGroupName;
	}

    public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setAgyAccessGroupName(rs.getString("AGYACCESSGROUPNAME"));		
	}
	
}
