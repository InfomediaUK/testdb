package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class MgrAccessGroup extends Base {

	private Integer mgrAccessGroupId;

	private Integer clientId;

	private String name;

	public Integer getMgrAccessGroupId() {
		return mgrAccessGroupId;
	}

	public void setMgrAccessGroupId(Integer mgrAccessGroupId) {
		this.mgrAccessGroupId = mgrAccessGroupId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setMgrAccessGroupId(rs.getInt("MGRACCESSGROUPID"));
		setClientId(rs.getInt("CLIENTID"));
		setName(rs.getString("NAME"));
	}

}
