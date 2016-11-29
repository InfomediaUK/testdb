package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class InvoiceAgency extends Base {

	private Integer invoiceAgencyId;

	private Integer invoiceId;
	
	private Integer agencyId;

	private BigDecimal chargeRateValue = new BigDecimal(0);

	private BigDecimal payRateValue = new BigDecimal(0);

	private BigDecimal wtdValue = new BigDecimal(0);

	private BigDecimal niValue = new BigDecimal(0);

	private BigDecimal commissionValue = new BigDecimal(0);

	private BigDecimal expenseValue = new BigDecimal(0);

	private BigDecimal vatValue = new BigDecimal(0);

	private BigDecimal noOfHours = new BigDecimal(0);

	private BigDecimal feeValue = new BigDecimal(0);

	private Integer paymentTerms;
	
	private String reference;

	public Integer getInvoiceAgencyId() {
		return invoiceAgencyId;
	}

	public void setInvoiceAgencyId(Integer invoiceAgencyId) {
		this.invoiceAgencyId = invoiceAgencyId;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
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

	public Integer getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(Integer paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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
		setInvoiceAgencyId(rs.getInt("INVOICEAGENCYID"));
		setAgencyId(rs.getInt("AGENCYID"));
		setChargeRateValue(rs.getBigDecimal("CHARGERATEVALUE"));
		setPayRateValue(rs.getBigDecimal("PAYRATEVALUE"));
		setWtdValue(rs.getBigDecimal("WTDVALUE"));
		setNiValue(rs.getBigDecimal("NIVALUE"));
		setCommissionValue(rs.getBigDecimal("COMMISSIONVALUE"));
		setExpenseValue(rs.getBigDecimal("EXPENSEVALUE"));
		setVatValue(rs.getBigDecimal("VATVALUE"));
		setNoOfHours(rs.getBigDecimal("NOOFHOURS"));
		setFeeValue(rs.getBigDecimal("FEEVALUE"));
		setPaymentTerms(rs.getInt("PAYMENTTERMS"));
		setReference(rs.getString("REFERENCE"));
	}

}
