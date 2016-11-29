package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JobProfileUser extends JobProfile {

	private Integer clientId;

	private Integer jobFamilyId;

	private String jobFamilyName;

	private String jobFamilyCode;

	private String jobSubFamilyName;

	private String jobSubFamilyCode;

	public String getJobFamilyCode() {
		return jobFamilyCode;
	}

	public void setJobFamilyCode(String jobFamilyCode) {
		this.jobFamilyCode = jobFamilyCode;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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

	public String getJobSubFamilyCode() {
		return jobSubFamilyCode;
	}

	public void setJobSubFamilyCode(String jobSubFamilyCode) {
		this.jobSubFamilyCode = jobSubFamilyCode;
	}

	public String getJobSubFamilyName() {
		return jobSubFamilyName;
	}

	public void setJobSubFamilyName(String jobSubFamilyName) {
		this.jobSubFamilyName = jobSubFamilyName;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setClientId(rs.getInt("CLIENTID"));
		setJobFamilyId(rs.getInt("JOBFAMILYID"));
		setJobFamilyName(rs.getString("JOBFAMILYNAME"));
		setJobFamilyCode(rs.getString("JOBFAMILYCODE"));
		setJobSubFamilyName(rs.getString("JOBSUBFAMILYNAME"));
		setJobSubFamilyCode(rs.getString("JOBSUBFAMILYCODE"));
	}

}
