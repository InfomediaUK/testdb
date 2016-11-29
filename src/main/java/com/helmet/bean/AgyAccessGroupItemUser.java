package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AgyAccessGroupItemUser extends AgyAccessGroupItem {

	private String agyAccessName;

	public String getAgyAccessName() {
		return agyAccessName;
	}

	public void setAgyAccessName(String agyAccessName) {
		this.agyAccessName = agyAccessName;
	}

    public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setAgyAccessName(rs.getString("AGYACCESSNAME"));		
	}
	
}
