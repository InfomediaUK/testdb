package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Invoice extends Base {

	private Integer invoiceId;

	private Integer clientId;

	private BigDecimal chargeRateValue;

	private BigDecimal payRateValue;

	private BigDecimal wtdValue;

	private BigDecimal niValue;

	private BigDecimal commissionValue;

	private BigDecimal expenseValue;

	private BigDecimal vatValue;

	private BigDecimal noOfHours;

	private BigDecimal feeValue;

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public BigDecimal getChargeRateValue() {
		return chargeRateValue;
	}

	public void setChargeRateValue(BigDecimal chargeRateValue) {
		this.chargeRateValue = chargeRateValue;
	}

	public BigDecimal getPayRateValue() {
		return payRateValue;
	}

	public void setPayRateValue(BigDecimal payRateValue) {
		this.payRateValue = payRateValue;
	}

	public BigDecimal getWtdValue() {
		return wtdValue;
	}

	public void setWtdValue(BigDecimal wtdValue) {
		this.wtdValue = wtdValue;
	}

	public BigDecimal getNiValue() {
		return niValue;
	}

	public void setNiValue(BigDecimal niValue) {
		this.niValue = niValue;
	}

	public BigDecimal getCommissionValue() {
		return commissionValue;
	}

	public void setCommissionValue(BigDecimal commissionValue) {
		this.commissionValue = commissionValue;
	}

	public BigDecimal getExpenseValue() {
		return expenseValue;
	}

	public void setExpenseValue(BigDecimal expenseValue) {
		this.expenseValue = expenseValue;
	}

	public BigDecimal getVatValue() {
		return vatValue;
	}

	public void setVatValue(BigDecimal vatValue) {
		this.vatValue = vatValue;
	}

	public BigDecimal getFeeValue() {
		return feeValue;
	}

	public void setFeeValue(BigDecimal feeValue) {
		this.feeValue = feeValue;
	}

	public BigDecimal getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(BigDecimal noOfHours) {
		this.noOfHours = noOfHours;
	}

	public BigDecimal getTotalNetValue() {
		return getChargeRateValue().add(getExpenseValue());
	}

	public BigDecimal getTotalValue() {
		return getChargeRateValue().add(getExpenseValue()).add(getVatValue());
	}
	
	public BigDecimal getWageRateValue() {
		return getPayRateValue() == null ? new BigDecimal(0) : getPayRateValue().add(getWtdValue()).add(getNiValue());
	}
	
	public void load(SqlRowSet rs) {
		super.load(rs);
		setInvoiceId(rs.getInt("INVOICEID"));
		setClientId(rs.getInt("CLIENTID"));
		setChargeRateValue(rs.getBigDecimal("CHARGERATEVALUE"));
		setPayRateValue(rs.getBigDecimal("PAYRATEVALUE"));
		setWtdValue(rs.getBigDecimal("WTDVALUE"));
		setNiValue(rs.getBigDecimal("NIVALUE"));
		setCommissionValue(rs.getBigDecimal("COMMISSIONVALUE"));
		setExpenseValue(rs.getBigDecimal("EXPENSEVALUE"));
		setVatValue(rs.getBigDecimal("VATVALUE"));
		setNoOfHours(rs.getBigDecimal("NOOFHOURS"));
		setFeeValue(rs.getBigDecimal("FEEVALUE"));
	}

}
