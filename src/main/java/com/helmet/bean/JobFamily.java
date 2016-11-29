package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JobFamily extends BaseDisplayOrder {

	private Integer jobFamilyId;

	private Integer clientId;

	private String name;

	private String code;

	public Integer getJobFamilyId() {
		return jobFamilyId;
	}

	public void setJobFamilyId(Integer jobFamilyId) {
		this.jobFamilyId = jobFamilyId;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setJobFamilyId(rs.getInt("JOBFAMILYID"));
		setClientId(rs.getInt("CLIENTID"));
		setName(rs.getString("NAME"));
		setCode(rs.getString("CODE"));
	}

}
