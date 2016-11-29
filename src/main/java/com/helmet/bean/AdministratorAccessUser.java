package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AdministratorAccessUser extends AdministratorAccess {

	private String adminAccessName;

	
	// need to add adminsistrator fields !!
	
	public String getAdminAccessName() {
		return adminAccessName;
	}

	public void setAdminAccessName(String adminAccessName) {
		this.adminAccessName = adminAccessName;
	}

    public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setAdminAccessName(rs.getString("ADMINACCESSNAME"));		
	}
	
}
