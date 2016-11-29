package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Expense extends BaseDisplayOrder {

	private Integer expenseId;

	private Integer locationId;

	private String name;

	private String description;

	private String code;

	private BigDecimal multiplier = new BigDecimal(1);

	private BigDecimal vatRate = new BigDecimal(0);

	public Integer getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setExpenseId(rs.getInt("EXPENSEID"));
        setLocationId(rs.getInt("LOCATIONID"));		
		setName(rs.getString("NAME"));
		setDescription(rs.getString("DESCRIPTION"));
		setCode(rs.getString("CODE"));
		setMultiplier(rs.getBigDecimal("MULTIPLIER"));
		setVatRate(rs.getBigDecimal("VATRATE"));
	}

}
