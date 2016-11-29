package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class LocationJobProfile extends Base {

	private Integer locationJobProfileId;

	private Integer locationId;

	private Integer jobProfileId;

	private BigDecimal budget;
	
	private BigDecimal vatBudget;
	
	private BigDecimal expenseBudget;
	
	private BigDecimal nonPayBudget;
	
	private BigDecimal rate;
	
	public Integer getLocationJobProfileId() {
		return locationJobProfileId;
	}

	public void setLocationJobProfileId(
			Integer locationJobProfileId) {
		this.locationJobProfileId = locationJobProfileId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getExpenseBudget() {
		return expenseBudget;
	}

	public void setExpenseBudget(BigDecimal expenseBudget) {
		this.expenseBudget = expenseBudget;
	}

	public BigDecimal getNonPayBudget() {
		return nonPayBudget;
	}

	public void setNonPayBudget(BigDecimal nonPayBudget) {
		this.nonPayBudget = nonPayBudget;
	}

	public BigDecimal getVatBudget() {
		return vatBudget;
	}

	public void setVatBudget(BigDecimal vatBudget) {
		this.vatBudget = vatBudget;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setLocationJobProfileId(rs.getInt("LOCATIONJOBPROFILEID"));
		setLocationId(rs.getInt("LOCATIONID"));
		setJobProfileId(rs.getInt("JOBPROFILEID"));
		setBudget(rs.getBigDecimal("BUDGET"));
		setVatBudget(rs.getBigDecimal("VATBUDGET"));
		setExpenseBudget(rs.getBigDecimal("EXPENSEBUDGET"));
		setNonPayBudget(rs.getBigDecimal("NONPAYBUDGET"));
		setRate(rs.getBigDecimal("RATE"));
	}

}
