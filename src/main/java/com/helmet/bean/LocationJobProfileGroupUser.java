package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LocationJobProfileGroupUser extends LocationJobProfileGroup {

	private String jobProfileGroupName;

	public String getJobProfileGroupName() {
		return jobProfileGroupName;
	}

	public void setJobProfileGroupName(String jobProfileGroupName) {
		this.jobProfileGroupName = jobProfileGroupName;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setJobProfileGroupName(rs.getString("JOBPROFILEGROUPNAME"));
	}

}
