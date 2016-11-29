package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JobProfileGroupItem extends Base {

	private Integer jobProfileGroupItemId;

	private Integer jobProfileGroupId;

	private Integer jobProfileId;

	public Integer getJobProfileGroupItemId() {
		return jobProfileGroupItemId;
	}

	public void setJobProfileGroupItemId(Integer jobProfileGroupItemId) {
		this.jobProfileGroupItemId = jobProfileGroupItemId;
	}


	public Integer getJobProfileGroupId() {
		return jobProfileGroupId;
	}

	public void setJobProfileGroupId(Integer jobProfileGroupId) {
		this.jobProfileGroupId = jobProfileGroupId;
	}

	
	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setJobProfileGroupItemId(rs.getInt("JOBPROFILEGROUPITEMID"));
		setJobProfileGroupId(rs.getInt("JOBPROFILEGROUPID"));
		setJobProfileId(rs.getInt("JOBPROFILEID"));
	}

}
