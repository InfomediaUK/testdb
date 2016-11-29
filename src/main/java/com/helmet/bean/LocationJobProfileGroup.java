package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LocationJobProfileGroup extends Base {

	private Integer locationJobProfileGroupId;

	private Integer locationId;

	private Integer jobProfileGroupId;

	public Integer getLocationJobProfileGroupId() {
		return locationJobProfileGroupId;
	}

	public void setLocationJobProfileGroupId(Integer locationJobProfileGroupId) {
		this.locationJobProfileGroupId = locationJobProfileGroupId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getJobProfileGroupId() {
		return jobProfileGroupId;
	}

	public void setJobProfileGroupId(Integer jobProfileGroupId) {
		this.jobProfileGroupId = jobProfileGroupId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setLocationJobProfileGroupId(rs.getInt("LOCATIONJOBPROFILEGROUPID"));
		setLocationId(rs.getInt("LOCATIONID"));
		setJobProfileGroupId(rs.getInt("JOBPROFILEGROUPID"));
	}

}
