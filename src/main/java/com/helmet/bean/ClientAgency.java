package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientAgency extends Base {

	private Integer clientAgencyId;

	private Integer clientId;
	
	private Integer agencyId;

	private BigDecimal feePerShift;

	private BigDecimal feePerHour;

	private BigDecimal feePercentage;

	private Integer paymentTerms;

	public Integer getClientAgencyId() {
		return clientAgencyId;
	}

	public void setClientAgencyId(Integer clientAgencyId) {
		this.clientAgencyId = clientAgencyId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public BigDecimal getFeePerHour() {
		return feePerHour;
	}

	public void setFeePerHour(BigDecimal feePerHour) {
		this.feePerHour = feePerHour;
	}

	public BigDecimal getFeePerShift() {
		return feePerShift;
	}

	public void setFeePerShift(BigDecimal feePerShift) {
		this.feePerShift = feePerShift;
	}

	public BigDecimal getFeePercentage() {
		return feePercentage;
	}

	public void setFeePercentage(BigDecimal feePercentage) {
		this.feePercentage = feePercentage;
	}

	public Integer getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(Integer paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setClientAgencyId(rs.getInt("CLIENTAGENCYID"));
		setClientId(rs.getInt("CLIENTID"));
		setAgencyId(rs.getInt("AGENCYID"));
		setFeePerShift(rs.getBigDecimal("FEEPERSHIFT"));
		setFeePerHour(rs.getBigDecimal("FEEPERHOUR"));
		setFeePercentage(rs.getBigDecimal("FEEPERCENTAGE"));
		setPaymentTerms(rs.getInt("PAYMENTTERMS"));
	}

}
