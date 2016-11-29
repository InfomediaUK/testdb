package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BudgetTransaction extends Base {

	private Integer budgetTransactionId;

	private Integer locationJobProfileId;

	private BigDecimal value;
	
	private BigDecimal vatValue;
	
	private BigDecimal expenseValue;
	
	private BigDecimal nonPayValue;
	
	private BigDecimal balance;
	
	private BigDecimal vatBalance;
	
	private BigDecimal expenseBalance;
	
	private BigDecimal nonPayBalance;
	
	private String comment;
	
	public Integer getBudgetTransactionId() {
		return budgetTransactionId;
	}

	public void setBudgetTransactionId(Integer budgetTransactionId) {
		this.budgetTransactionId = budgetTransactionId;
	}

	public Integer getLocationJobProfileId() {
		return locationJobProfileId;
	}

	public void setLocationJobProfileId(
			Integer locationJobProfileId) {
		this.locationJobProfileId = locationJobProfileId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getExpenseBalance() {
		return expenseBalance;
	}

	public void setExpenseBalance(BigDecimal expenseBalance) {
		this.expenseBalance = expenseBalance;
	}

	public BigDecimal getExpenseValue() {
		return expenseValue;
	}

	public void setExpenseValue(BigDecimal expenseValue) {
		this.expenseValue = expenseValue;
	}

	public BigDecimal getNonPayBalance() {
		return nonPayBalance;
	}

	public void setNonPayBalance(BigDecimal nonPayBalance) {
		this.nonPayBalance = nonPayBalance;
	}

	public BigDecimal getNonPayValue() {
		return nonPayValue;
	}

	public void setNonPayValue(BigDecimal nonPayValue) {
		this.nonPayValue = nonPayValue;
	}

	public BigDecimal getVatBalance() {
		return vatBalance;
	}

	public void setVatBalance(BigDecimal vatBalance) {
		this.vatBalance = vatBalance;
	}

	public BigDecimal getVatValue() {
		return vatValue;
	}

	public void setVatValue(BigDecimal vatValue) {
		this.vatValue = vatValue;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setBudgetTransactionId(rs.getInt("BUDGETTRANSACTIONID"));
		setLocationJobProfileId(rs.getInt("LOCATIONJOBPROFILEID"));
		setValue(rs.getBigDecimal("VALUE"));
		setVatValue(rs.getBigDecimal("VATVALUE"));
		setExpenseValue(rs.getBigDecimal("EXPENSEVALUE"));
		setNonPayValue(rs.getBigDecimal("NONPAYVALUE"));
		setBalance(rs.getBigDecimal("BALANCE"));
		setVatBalance(rs.getBigDecimal("VATBALANCE"));
		setExpenseBalance(rs.getBigDecimal("EXPENSEBALANCE"));
		setNonPayBalance(rs.getBigDecimal("NONPAYBALANCE"));
		setComment(rs.getString("COMMENT"));
	}

}
