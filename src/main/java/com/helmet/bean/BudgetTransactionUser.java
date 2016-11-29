package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BudgetTransactionUser extends BudgetTransaction {

	private Integer locationId;

	private String locationName;

	private Integer jobProfileId;

	private String jobProfileName;

	public String getJobProfileName() {
		return jobProfileName;
	}

	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public void setJobProfileName(String jobProfileName) {
		this.jobProfileName = jobProfileName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setLocationId(rs.getInt("LOCATIONID"));
		setLocationName(rs.getString("LOCATIONNAME"));
		setJobProfileId(rs.getInt("JOBPROFILEID"));
		setJobProfileName(rs.getString("JOBPROFILENAME"));
	}

}
