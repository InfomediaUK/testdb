package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Grade extends BaseDisplayOrder {

	private Integer gradeId;

	private Integer clientId;

	private String name;

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
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
		setGradeId(rs.getInt("GRADEID"));
        setClientId(rs.getInt("CLIENTID"));		
		setName(rs.getString("NAME"));
	}

}
