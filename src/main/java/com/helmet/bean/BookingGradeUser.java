package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BookingGradeUser extends BookingGrade {

	private String agencyName;
	private String agencyCode;
	private BigDecimal percentage;
	private String gradeName;
	private BigDecimal currentRate;

	private transient BigDecimal value; // not persisted
	
	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public BigDecimal getCurrentRate() {
		return currentRate;
	}

	public void setCurrentRate(BigDecimal currentRate) {
		this.currentRate = currentRate;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setAgencyName(rs.getString("AGENCYNAME"));
		setAgencyCode(rs.getString("AGENCYCODE"));
		setPercentage(rs.getBigDecimal("PERCENTAGE"));
		setGradeName(rs.getString("GRADENAME"));
		setCurrentRate(rs.getBigDecimal("CURRENTRATE"));
	}
	
}
