package com.helmet.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.Utilities;

public class BookingGrade extends Base {

    BigDecimal divisor100 = new BigDecimal(100);
    BigDecimal bdZero = new BigDecimal(0);

	public final static int BOOKINGGRADE_STATUS_DRAFT = 0;
	public final static int BOOKINGGRADE_STATUS_OPEN = 1;
	public final static int BOOKINGGRADE_STATUS_CLOSED = 2;
	
	public final static String[] BOOKINGGRADE_STATUS_DESCRIPTION_KEYS = {"bookingGrade.status.draft",
        "bookingGrade.status.open",
        "bookingGrade.status.closed"};

	public final static String BOOKINGGRADE_AGENCY_STATUSES = BOOKINGGRADE_STATUS_OPEN + "," +
	 														  BOOKINGGRADE_STATUS_CLOSED; // csv


	private Integer bookingGradeId;
	private Integer bookingId;
	private Integer agencyId;
	private Integer clientAgencyJobProfileGradeId;
	private Integer status;
	private BigDecimal rate;
	private BigDecimal value;
	private BigDecimal payRate;
	private BigDecimal wtdPercentage;
	private BigDecimal niPercentage;
	private BigDecimal wageRate;
	private BigDecimal chargeRateVatRate;
	private BigDecimal commissionVatRate;
	private BigDecimal payRateVatRate;
	private BigDecimal wtdVatRate;
	private BigDecimal niVatRate;
	private Timestamp sendTimestamp;
	private Timestamp sentTimestamp;
	private Integer sentStatus;
	private Boolean viewed;
	private Boolean cannotFill;

	public Integer getBookingGradeId() {
		return bookingGradeId;
	}

	public void setBookingGradeId(Integer bookingGradeId) {
		this.bookingGradeId = bookingGradeId;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public Integer getClientAgencyJobProfileGradeId() {
		return clientAgencyJobProfileGradeId;
	}

	public void setClientAgencyJobProfileGradeId(Integer clientAgencyJobProfileGradeId) {
		this.clientAgencyJobProfileGradeId = clientAgencyJobProfileGradeId;
	}

	public Timestamp getSendTimestamp() {
		return sendTimestamp;
	}

	public void setSendTimestamp(Timestamp sendTimestamp) {
		this.sendTimestamp = sendTimestamp;
	}

	public Timestamp getSentTimestamp() {
		return sentTimestamp;
	}

	public void setSentTimestamp(Timestamp sentTimestamp) {
		this.sentTimestamp = sentTimestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(Integer sentStatus) {
		this.sentStatus = sentStatus;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getNiPercentage() {
		return niPercentage;
	}

	public void setNiPercentage(BigDecimal niPercentage) {
		this.niPercentage = niPercentage;
	}

	public BigDecimal getPayRate() {
		return payRate;
	}

	public void setPayRate(BigDecimal payRate) {
		this.payRate = payRate;
	}

	public BigDecimal getWtdPercentage() {
		return wtdPercentage;
	}

	public void setWtdPercentage(BigDecimal wtdPercentage) {
		this.wtdPercentage = wtdPercentage;
	}

	public BigDecimal getChargeRateVatRate() {
		return chargeRateVatRate;
	}

	public void setChargeRateVatRate(BigDecimal chargeRateVatRate) {
		this.chargeRateVatRate = chargeRateVatRate;
	}

	public BigDecimal getCommissionVatRate() {
		return commissionVatRate;
	}

	public void setCommissionVatRate(BigDecimal commissionVatRate) {
		this.commissionVatRate = commissionVatRate;
	}

	public BigDecimal getNiVatRate() {
		return niVatRate;
	}

	public void setNiVatRate(BigDecimal niVatRate) {
		this.niVatRate = niVatRate;
	}

	public BigDecimal getPayRateVatRate() {
		return payRateVatRate;
	}

	public void setPayRateVatRate(BigDecimal payRateVatRate) {
		this.payRateVatRate = payRateVatRate;
	}

	public BigDecimal getWtdVatRate() {
		return wtdVatRate;
	}

	public void setWtdVatRate(BigDecimal wtdVatRate) {
		this.wtdVatRate = wtdVatRate;
	}

	public Boolean getCannotFill() {
		return cannotFill;
	}

	public void setCannotFill(Boolean cannotFill) {
		this.cannotFill = cannotFill;
	}

	public Boolean getViewed() {
		return viewed;
	}

	public void setViewed(Boolean viewed) {
		this.viewed = viewed;
	}

	public Boolean getCanApply() {
		return status == BOOKINGGRADE_STATUS_OPEN;
	}

	public String getStatusDescriptionKey() {
		return BOOKINGGRADE_STATUS_DESCRIPTION_KEYS[status];
	}
	
	public BigDecimal getCommissionRate() {
		return rate.subtract(getWageRate());
	}

    public BigDecimal getWtdRate() {
        return Utilities.round(getPayRate().multiply(getWtdPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
    }
	
    public BigDecimal getNiRate() {
        return Utilities.round((getPayRate().add(getWtdRate())).multiply(getNiPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
    }
	
    public BigDecimal getPayeRate() {
        return getPayRate().add(getWtdRate()).add(getNiRate());
    }
	
	public BigDecimal getWageRate() {
		return wageRate;
	}

	public void setWageRate(BigDecimal wageRate) {
		this.wageRate = wageRate;
	}

  // Lyndon 15/07/90 - The getPayeRate() (note the E in Paye) method was getPayRate() which does NOT take WTD or NI into account. 
	public BigDecimal getPayRateValue() {
		return getRate().compareTo(bdZero) > 0 ? 
				Utilities.round(getValue().multiply(getPayeRate()).divide(getRate(), 5, RoundingMode.HALF_UP)) : 
				  bdZero;
	}
	
	// TODO - not that accurate because of rounding - i think!
	public BigDecimal getWageRateValue() {
		return getRate().compareTo(bdZero) > 0 ? 
				Utilities.round(getValue().multiply(getWageRate()).divide(getRate(), 5, RoundingMode.HALF_UP)) : 
				  bdZero;
	}
	
	public BigDecimal getChargeRateVatValue() {
        return Utilities.round(getRate().multiply(chargeRateVatRate.divide(new BigDecimal(100), 5, RoundingMode.HALF_UP)));
	}

	public BigDecimal getPayRateVatValue() {
        return Utilities.round(getPayRate().multiply(payRateVatRate.divide(new BigDecimal(100), 5, RoundingMode.HALF_UP)));
	}

	public BigDecimal getWtdVatValue() {
        return Utilities.round(getWtdRate().multiply(wtdVatRate.divide(new BigDecimal(100), 5, RoundingMode.HALF_UP)));
	}

	public BigDecimal getNiVatValue() {
        return Utilities.round(getNiRate().multiply(niVatRate.divide(new BigDecimal(100), 5, RoundingMode.HALF_UP)));
	}

	public BigDecimal getCommissionVatValue() {
        return Utilities.round(getCommissionRate().multiply(commissionVatRate.divide(new BigDecimal(100), 5, RoundingMode.HALF_UP)));
	}

	public void load(SqlRowSet rs)
    {
	    super.load(rs);
        setBookingGradeId(rs.getInt("BOOKINGGRADEID"));	
        setBookingId(rs.getInt("BOOKINGID"));	
        setAgencyId(rs.getInt("AGENCYID"));	
        setClientAgencyJobProfileGradeId(rs.getInt("CLIENTAGENCYJOBPROFILEGRADEID"));
        setStatus(rs.getInt("STATUS"));
        setRate(rs.getBigDecimal("RATE"));
        setValue(rs.getBigDecimal("VALUE"));
		setPayRate(rs.getBigDecimal("PAYRATE"));
		setWtdPercentage(rs.getBigDecimal("WTDPERCENTAGE"));
		setNiPercentage(rs.getBigDecimal("NIPERCENTAGE"));
		setWageRate(rs.getBigDecimal("WAGERATE"));
		setChargeRateVatRate(rs.getBigDecimal("CHARGERATEVATRATE"));
		setCommissionVatRate(rs.getBigDecimal("COMMISSIONVATRATE"));
		setPayRateVatRate(rs.getBigDecimal("PAYRATEVATRATE"));
		setWtdVatRate(rs.getBigDecimal("WTDVATRATE"));
		setNiVatRate(rs.getBigDecimal("NIVATRATE"));
        setSendTimestamp(rs.getTimestamp("SENDTIMESTAMP"));
        setSentTimestamp(rs.getTimestamp("SENTTIMESTAMP"));
        setSentStatus(rs.getInt("SENTSTATUS"));
        setViewed(rs.getBoolean("VIEWED"));
        setCannotFill(rs.getBoolean("CANNOTFILL"));
	}
	
}
