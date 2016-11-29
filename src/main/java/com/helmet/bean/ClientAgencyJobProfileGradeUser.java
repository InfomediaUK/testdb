package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientAgencyJobProfileGradeUser extends ClientAgencyJobProfileGrade {

	private String agencyName;
	private BigDecimal percentage;
	private String gradeName;
	private String masterVendorName;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public String getMasterVendorName() {
		return masterVendorName;
	}

	public void setMasterVendorName(String masterVendorName) {
		this.masterVendorName = masterVendorName;
	}

	public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setAgencyName(rs.getString("AGENCYNAME"));		
		setPercentage(rs.getBigDecimal("PERCENTAGE"));		
		setGradeName(rs.getString("GRADENAME"));		
		setMasterVendorName(rs.getString("MASTERVENDORNAME"));		
	}
	
}
