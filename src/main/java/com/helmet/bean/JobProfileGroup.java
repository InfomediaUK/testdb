package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JobProfileGroup extends Base {

	private Integer jobProfileGroupId;

	private Integer clientId;

	private String name;

	public Integer getJobProfileGroupId() {
		return jobProfileGroupId;
	}

	public void setJobProfileGroupId(Integer jobProfileGroupId) {
		this.jobProfileGroupId = jobProfileGroupId;
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
		setJobProfileGroupId(rs.getInt("JOBPROFILEGROUPID"));
        setClientId(rs.getInt("CLIENTID"));		
		setName(rs.getString("NAME"));
	}

}
