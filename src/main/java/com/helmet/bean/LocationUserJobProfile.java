package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LocationUserJobProfile extends LocationUser {

	private Integer jobFamilyId;

	private String jobFamilyName;

	private String jobFamilyCode;

	private Integer jobSubFamilyId;

	private String jobSubFamilyName;

	private String jobSubFamilyCode;

	private Integer jobProfileId;

	private String jobProfileName;

	private String jobProfileCode;

	public String getJobFamilyCode() {
		return jobFamilyCode;
	}

	public void setJobFamilyCode(String jobFamilyCode) {
		this.jobFamilyCode = jobFamilyCode;
	}

	public Integer getJobFamilyId() {
		return jobFamilyId;
	}

	public void setJobFamilyId(Integer jobFamilyId) {
		this.jobFamilyId = jobFamilyId;
	}

	public String getJobFamilyName() {
		return jobFamilyName;
	}

	public void setJobFamilyName(String jobFamilyName) {
		this.jobFamilyName = jobFamilyName;
	}

	public String getJobProfileCode() {
		return jobProfileCode;
	}

	public void setJobProfileCode(String jobProfileCode) {
		this.jobProfileCode = jobProfileCode;
	}

	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	public String getJobProfileName() {
		return jobProfileName;
	}

	public void setJobProfileName(String jobProfileName) {
		this.jobProfileName = jobProfileName;
	}

	public String getJobSubFamilyCode() {
		return jobSubFamilyCode;
	}

	public void setJobSubFamilyCode(String jobSubFamilyCode) {
		this.jobSubFamilyCode = jobSubFamilyCode;
	}

	public Integer getJobSubFamilyId() {
		return jobSubFamilyId;
	}

	public void setJobSubFamilyId(Integer jobSubFamilyId) {
		this.jobSubFamilyId = jobSubFamilyId;
	}

	public String getJobSubFamilyName() {
		return jobSubFamilyName;
	}

	public void setJobSubFamilyName(String jobSubFamilyName) {
		this.jobSubFamilyName = jobSubFamilyName;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setJobFamilyId(rs.getInt("JOBFAMILYID"));
		setJobFamilyName(rs.getString("JOBFAMILYNAME"));
		setJobFamilyCode(rs.getString("JOBFAMILYCODE"));
		setJobSubFamilyId(rs.getInt("JOBSUBFAMILYID"));
		setJobSubFamilyName(rs.getString("JOBSUBFAMILYNAME"));
		setJobSubFamilyCode(rs.getString("JOBSUBFAMILYCODE"));
		setJobProfileId(rs.getInt("JOBPROFILEID"));
		setJobProfileName(rs.getString("JOBPROFILENAME"));
		setJobProfileCode(rs.getString("JOBPROFILECODE"));
	}

}
