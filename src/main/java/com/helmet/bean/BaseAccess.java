package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BaseAccess extends Base {

	private String name;
	
	private String description;
	
	private Boolean startsWith = false;

	private Boolean global = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStartsWith() {
		return startsWith;
	}

	public void setStartsWith(Boolean startsWith) {
		this.startsWith = startsWith;
	}

	public Boolean getGlobal() {
		return global;
	}

	public void setGlobal(Boolean global) {
		this.global = global;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setName(rs.getString("NAME"));
		setDescription(rs.getString("DESCRIPTION"));
		setStartsWith(rs.getBoolean("STARTSWITH"));
		setGlobal(rs.getBoolean("GLOBAL"));
	}

}
