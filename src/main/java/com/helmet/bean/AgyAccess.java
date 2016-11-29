package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AgyAccess extends BaseAccess {

	private Integer agyAccessId;

	public Integer getAgyAccessId() {
		return agyAccessId;
	}

	public void setAgyAccessId(Integer agyAccessId) {
		this.agyAccessId = agyAccessId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAgyAccessId(rs.getInt("AGYACCESSID"));
	}

}
