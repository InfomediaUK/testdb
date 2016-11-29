package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BookingDateExpenseUser extends BookingDateExpense {

    String expenseName;	
    String expenseDescription;	
    String expenseCode;	
    BigDecimal expenseMultiplier;	
    BigDecimal expenseVatRate;	
    Integer expenseDisplayOrder;	

	public String getExpenseCode() {
		return expenseCode;
	}

	public void setExpenseCode(String expenseCode) {
		this.expenseCode = expenseCode;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public Integer getExpenseDisplayOrder() {
		return expenseDisplayOrder;
	}

	public void setExpenseDisplayOrder(Integer expenseDisplayOrder) {
		this.expenseDisplayOrder = expenseDisplayOrder;
	}

	public BigDecimal getExpenseMultiplier() {
		return expenseMultiplier;
	}

	public void setExpenseMultiplier(BigDecimal expenseMultiplier) {
		this.expenseMultiplier = expenseMultiplier;
	}

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public BigDecimal getExpenseVatRate() {
		return expenseVatRate;
	}

	public void setExpenseVatRate(BigDecimal expenseVatRate) {
		this.expenseVatRate = expenseVatRate;
	}

	public boolean getIsMultiplier() {
		// multiplier is NOT equal to 1
		return !(expenseMultiplier.compareTo(new BigDecimal(1)) == 0);
	}
	
	public void load(SqlRowSet rs)
	{
	    super.load(rs);
        setExpenseName(rs.getString("EXPENSENAME"));	
        setExpenseDescription(rs.getString("EXPENSEDESCRIPTION"));	
        setExpenseCode(rs.getString("EXPENSECODE"));	
        setExpenseMultiplier(rs.getBigDecimal("EXPENSEMULTIPLIER"));	
        setExpenseVatRate(rs.getBigDecimal("EXPENSEVATRATE"));	
        setExpenseDisplayOrder(rs.getInt("EXPENSEDISPLAYORDER"));	
	}

}
