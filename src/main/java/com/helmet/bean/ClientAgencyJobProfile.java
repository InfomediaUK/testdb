package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientAgencyJobProfile extends Base {

	private Integer clientAgencyJobProfileId;

	private Integer clientAgencyId;

	private Integer jobProfileId;

	private BigDecimal percentage;
	
	private String sendEmailAddress;

	private String masterVendorName;

	public Integer getClientAgencyJobProfileId() {
		return clientAgencyJobProfileId;
	}

	public void setClientAgencyJobProfileId(Integer clientAgencyJobProfileId) {
		this.clientAgencyJobProfileId = clientAgencyJobProfileId;
	}

	public Integer getClientAgencyId() {
		return clientAgencyId;
	}

	public void setClientAgencyId(Integer clientAgencyId) {
		this.clientAgencyId = clientAgencyId;
	}

	
	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public String getSendEmailAddress() {
		return sendEmailAddress;
	}

	public void setSendEmailAddress(String sendEmailAddress) {
		this.sendEmailAddress = sendEmailAddress;
	}

	public String getMasterVendorName() {
		return masterVendorName;
	}

	public void setMasterVendorName(String masterVendorName) {
		this.masterVendorName = masterVendorName;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setClientAgencyJobProfileId(rs.getInt("CLIENTAGENCYJOBPROFILEID"));
		setClientAgencyId(rs.getInt("CLIENTAGENCYID"));
		setJobProfileId(rs.getInt("JOBPROFILEID"));
		setPercentage(rs.getBigDecimal("PERCENTAGE"));
		setSendEmailAddress(rs.getString("SENDEMAILADDRESS"));
		setMasterVendorName(rs.getString("MASTERVENDORNAME"));
	}

}
