package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LocationManagerJobProfileUser extends LocationManagerJobProfile {

	private Integer jobFamilyId;

	private String jobFamilyName;

	private String jobFamilyCode;

	private Integer jobSubFamilyId;

	private String jobSubFamilyName;

	private String jobSubFamilyCode;

	private String jobProfileName;

	private String jobProfileCode;

	private Integer clientId;

	private String clientName;

	private String clientCode;

	private Integer siteId;

	private String siteName;

	private String siteCode;

	private String locationName;

	private String locationCode;

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

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setJobFamilyId(rs.getInt("JOBFAMILYID"));
		setJobFamilyName(rs.getString("JOBFAMILYNAME"));
		setJobFamilyCode(rs.getString("JOBFAMILYCODE"));
		setJobSubFamilyId(rs.getInt("JOBSUBFAMILYID"));
		setJobSubFamilyName(rs.getString("JOBSUBFAMILYNAME"));
		setJobSubFamilyCode(rs.getString("JOBSUBFAMILYCODE"));
		setJobProfileName(rs.getString("JOBPROFILENAME"));
		setJobProfileCode(rs.getString("JOBPROFILECODE"));
		setClientId(rs.getInt("CLIENTID"));
		setClientName(rs.getString("CLIENTNAME"));
		setClientCode(rs.getString("CLIENTCODE"));
		setSiteId(rs.getInt("SITEID"));
		setSiteName(rs.getString("SITENAME"));
		setSiteCode(rs.getString("SITECODE"));
		setLocationName(rs.getString("LOCATIONNAME"));
		setLocationCode(rs.getString("LOCATIONCODE"));
	}

}
