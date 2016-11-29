package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AdministratorAccessGroupUser extends AdministratorAccessGroup {

	private String adminAccessGroupName;

	
	// need to add adminsistrator fields !!
	
	public String getAdminAccessGroupName() {
		return adminAccessGroupName;
	}

	public void setAdminAccessGroupName(String adminAccessGroupName) {
		this.adminAccessGroupName = adminAccessGroupName;
	}

    public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setAdminAccessGroupName(rs.getString("ADMINACCESSGROUPNAME"));		
	}
	
}
