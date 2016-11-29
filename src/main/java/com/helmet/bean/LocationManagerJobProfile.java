package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LocationManagerJobProfile extends Base {

	private Integer locationManagerJobProfileId;

	private Integer locationId;

	private Integer managerId;

	private Integer jobProfileId;

	public Integer getLocationManagerJobProfileId() {
		return locationManagerJobProfileId;
	}

	public void setLocationManagerJobProfileId(
			Integer locationManagerJobProfileId) {
		this.locationManagerJobProfileId = locationManagerJobProfileId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setLocationManagerJobProfileId(rs.getInt("LOCATIONMANAGERJOBPROFILEID"));
		setLocationId(rs.getInt("LOCATIONID"));
		setManagerId(rs.getInt("MANAGERID"));
		setJobProfileId(rs.getInt("JOBPROFILEID"));
	}

}
