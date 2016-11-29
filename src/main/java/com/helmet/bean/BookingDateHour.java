package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BookingDateHour extends Base {

	private Integer bookingDateHourId;

	private Integer bookingDateId;

	private Integer hourOfDay;

	private BigDecimal portionOfHour;

	private BigDecimal chargeRate;

	private BigDecimal payRate;

	private BigDecimal wageRate;

	private BigDecimal upliftFactor;

	private BigDecimal upliftValue;

	private BigDecimal upliftedChargeRate;

	private BigDecimal upliftedPayRate;

	private BigDecimal upliftedWageRate;

	private BigDecimal chargeRateValue;

	private BigDecimal chargeRateVatValue;

	private BigDecimal payRateValue;

	private BigDecimal payRateVatValue;

	private BigDecimal wageRateValue;

	private BigDecimal wtdRate;

	private BigDecimal wtdValue;

	private BigDecimal wtdVatValue;

	private BigDecimal niRate;

	private BigDecimal niValue;

	private BigDecimal niVatValue;

	private BigDecimal commissionRate;

	private BigDecimal commissionValue;

	private BigDecimal commissionVatValue;

	public Integer getBookingDateHourId() {
		return bookingDateHourId;
	}

	public void setBookingDateHourId(Integer bookingDateHourId) {
		this.bookingDateHourId = bookingDateHourId;
	}

	public Integer getBookingDateId() {
		return bookingDateId;
	}

	public void setBookingDateId(Integer bookingDateId) {
		this.bookingDateId = bookingDateId;
	}

	public BigDecimal getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(BigDecimal chargeRate) {
		this.chargeRate = chargeRate;
	}

	public BigDecimal getChargeRateValue() {
		return chargeRateValue;
	}

	public void setChargeRateValue(BigDecimal chargeRateValue) {
		this.chargeRateValue = chargeRateValue;
	}

	public BigDecimal getChargeRateVatValue() {
		return chargeRateVatValue;
	}

	public void setChargeRateVatValue(BigDecimal chargeRateVatValue) {
		this.chargeRateVatValue = chargeRateVatValue;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public BigDecimal getCommissionValue() {
		return commissionValue;
	}

	public void setCommissionValue(BigDecimal commissionValue) {
		this.commissionValue = commissionValue;
	}

	public BigDecimal getCommissionVatValue() {
		return commissionVatValue;
	}

	public void setCommissionVatValue(BigDecimal commissionVatValue) {
		this.commissionVatValue = commissionVatValue;
	}

	public Integer getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public BigDecimal getNiRate() {
		return niRate;
	}

	public void setNiRate(BigDecimal niRate) {
		this.niRate = niRate;
	}

	public BigDecimal getNiValue() {
		return niValue;
	}

	public void setNiValue(BigDecimal niValue) {
		this.niValue = niValue;
	}

	public BigDecimal getNiVatValue() {
		return niVatValue;
	}

	public void setNiVatValue(BigDecimal niVatValue) {
		this.niVatValue = niVatValue;
	}

	public BigDecimal getPayRate() {
		return payRate;
	}

	public void setPayRate(BigDecimal payRate) {
		this.payRate = payRate;
	}

	public BigDecimal getPayRateValue() {
		return payRateValue;
	}

	public void setPayRateValue(BigDecimal payRateValue) {
		this.payRateValue = payRateValue;
	}

	public BigDecimal getPayRateVatValue() {
		return payRateVatValue;
	}

	public void setPayRateVatValue(BigDecimal payRateVatValue) {
		this.payRateVatValue = payRateVatValue;
	}

	public BigDecimal getPortionOfHour() {
		return portionOfHour;
	}

	public void setPortionOfHour(BigDecimal portionOfHour) {
		this.portionOfHour = portionOfHour;
	}

	public BigDecimal getUpliftedChargeRate() {
		return upliftedChargeRate;
	}

	public void setUpliftedChargeRate(BigDecimal upliftedChargeRate) {
		this.upliftedChargeRate = upliftedChargeRate;
	}

	public BigDecimal getUpliftFactor() {
		return upliftFactor;
	}

	public void setUpliftFactor(BigDecimal upliftFactor) {
		this.upliftFactor = upliftFactor;
	}

	public BigDecimal getUpliftedPayRate() {
		return upliftedPayRate;
	}

	public void setUpliftedPayRate(BigDecimal upliftedPayRate) {
		this.upliftedPayRate = upliftedPayRate;
	}

	public BigDecimal getUpliftValue() {
		return upliftValue;
	}

	public void setUpliftValue(BigDecimal upliftValue) {
		this.upliftValue = upliftValue;
	}

	public BigDecimal getWtdRate() {
		return wtdRate;
	}

	public void setWtdRate(BigDecimal wtdRate) {
		this.wtdRate = wtdRate;
	}

	public BigDecimal getWtdValue() {
		return wtdValue;
	}

	public void setWtdValue(BigDecimal wtdValue) {
		this.wtdValue = wtdValue;
	}

	public BigDecimal getWtdVatValue() {
		return wtdVatValue;
	}

	public void setWtdVatValue(BigDecimal wtdVatValue) {
		this.wtdVatValue = wtdVatValue;
	}

	public BigDecimal getWageRate() {
		return wageRate;
	}

	public void setWageRate(BigDecimal wageRate) {
		this.wageRate = wageRate;
	}

	public BigDecimal getUpliftedWageRate() {
		return upliftedWageRate;
	}

	public void setUpliftedWageRate(BigDecimal upliftedWageRate) {
		this.upliftedWageRate = upliftedWageRate;
	}

	public BigDecimal getWageRateValue() {
		return wageRateValue;
	}

	public void setWageRateValue(BigDecimal wageRateValue) {
		this.wageRateValue = wageRateValue;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setBookingDateHourId(rs.getInt("BOOKINGDATEHOURID"));
		setBookingDateId(rs.getInt("BOOKINGDATEID"));
		setHourOfDay(rs.getInt("HOUROFDAY"));
		setPortionOfHour(rs.getBigDecimal("PORTIONOFHOUR"));
		setChargeRate(rs.getBigDecimal("CHARGERATE"));
		setPayRate(rs.getBigDecimal("PAYRATE"));
		setUpliftFactor(rs.getBigDecimal("UPLIFTFACTOR"));
		setUpliftValue(rs.getBigDecimal("UPLIFTVALUE"));
		setUpliftedChargeRate(rs.getBigDecimal("UPLIFTEDCHARGERATE"));
		setUpliftedPayRate(rs.getBigDecimal("UPLIFTEDPAYRATE"));
		setUpliftedWageRate(rs.getBigDecimal("UPLIFTEDWAGERATE"));
		setChargeRateValue(rs.getBigDecimal("CHARGERATEVALUE"));
		setChargeRateVatValue(rs.getBigDecimal("CHARGERATEVATVALUE"));
		setPayRateValue(rs.getBigDecimal("PAYRATEVALUE"));
		setPayRateVatValue(rs.getBigDecimal("PAYRATEVATVALUE"));
		setWtdRate(rs.getBigDecimal("WTDRATE"));
		setWtdValue(rs.getBigDecimal("WTDVALUE"));
		setWtdVatValue(rs.getBigDecimal("WTDVATVALUE"));
		setNiRate(rs.getBigDecimal("NIRATE"));
		setNiValue(rs.getBigDecimal("NIVALUE"));
		setNiVatValue(rs.getBigDecimal("NIVATVALUE"));
		setCommissionRate(rs.getBigDecimal("COMMISSIONRATE"));
		setCommissionValue(rs.getBigDecimal("COMMISSIONVALUE"));
		setCommissionVatValue(rs.getBigDecimal("COMMISSIONVATVALUE"));
		setWageRate(rs.getBigDecimal("WAGERATE"));
		setWageRateValue(rs.getBigDecimal("WAGERATEVALUE"));
	}

}
