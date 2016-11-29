package com.helmet.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.Utilities;

public class ClientAgencyJobProfileGrade extends Base {

    BigDecimal divisor100 = new BigDecimal(100);

    private Integer clientAgencyJobProfileGradeId;

	private Integer clientAgencyJobProfileId;

	private Integer gradeId;

	private BigDecimal rate;
	
	private BigDecimal payRate;
	
	private BigDecimal wtdPercentage;
	
	private BigDecimal niPercentage;
	
	private BigDecimal chargeRateVatRate;
	
	private BigDecimal commissionVatRate;
	
	private BigDecimal payRateVatRate;
	
	private BigDecimal wtdVatRate;
	
	private BigDecimal niVatRate;
	
  private Boolean available = false;
  
	private transient Integer rank; // not persisted

	private transient BigDecimal value; // not persisted

	public Integer getClientAgencyJobProfileGradeId() {
		return clientAgencyJobProfileGradeId;
	}

	public void setClientAgencyJobProfileGradeId(Integer clientAgencyJobProfileGradeId) {
		this.clientAgencyJobProfileGradeId = clientAgencyJobProfileGradeId;
	}

	public Integer getClientAgencyJobProfileId() {
		return clientAgencyJobProfileId;
	}

	public void setClientAgencyJobProfileId(Integer clientAgencyJobProfileId) {
		this.clientAgencyJobProfileId = clientAgencyJobProfileId;
	}

	
	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
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

	public BigDecimal getCommission() {
		return Utilities.round(rate.subtract(payRate).subtract(getWtdValue()).subtract(getNiValue()));
	}

	public BigDecimal getWtdValue() {
        return Utilities.round(payRate.multiply(wtdPercentage.divide(new BigDecimal(100), 5, RoundingMode.HALF_UP)));
	}
	
	public BigDecimal getNiValue() {
        return Utilities.round(payRate.add(getWtdValue())).multiply((niPercentage.divide(new BigDecimal(100), 5, RoundingMode.HALF_UP)));
	}
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
    public BigDecimal getWtdRate() {
        return Utilities.round(getPayRate().multiply(getWtdPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
    }
	
    public BigDecimal getNiRate() {
        return Utilities.round((getPayRate().add(getWtdRate())).multiply(getNiPercentage().divide(divisor100, 5, RoundingMode.HALF_UP)));
    }
	
    public BigDecimal getPayeRate() {
      // Was: return getPayRate().add(getWtdRate()).add(getNiRate());  
      // LYNDON 16/10/2013. PayRate can be more than 
      // two decimal places so it is necessary to round it. I think.
      return Utilities.round(getPayRate().add(getWtdRate()).add(getNiRate()));
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
        return Utilities.round(getCommission().multiply(commissionVatRate.divide(new BigDecimal(100), 5, RoundingMode.HALF_UP)));
	}

    public Boolean getAvailable()
  {
    return available;
  }

  public void setAvailable(Boolean available)
  {
    this.available = available;
  }

    public void load(SqlRowSet rs) {
		super.load(rs);
		setClientAgencyJobProfileGradeId(rs.getInt("CLIENTAGENCYJOBPROFILEGRADEID"));
		setClientAgencyJobProfileId(rs.getInt("CLIENTAGENCYJOBPROFILEID"));
		setGradeId(rs.getInt("GRADEID"));
		setRate(rs.getBigDecimal("RATE"));
		setPayRate(rs.getBigDecimal("PAYRATE"));
		setWtdPercentage(rs.getBigDecimal("WTDPERCENTAGE"));
		setNiPercentage(rs.getBigDecimal("NIPERCENTAGE"));
		setChargeRateVatRate(rs.getBigDecimal("CHARGERATEVATRATE"));
		setCommissionVatRate(rs.getBigDecimal("COMMISSIONVATRATE"));
		setPayRateVatRate(rs.getBigDecimal("PAYRATEVATRATE"));
		setWtdVatRate(rs.getBigDecimal("WTDVATRATE"));
    setNiVatRate(rs.getBigDecimal("NIVATRATE"));
    setAvailable(rs.getBoolean("AVAILABLE"));
	}

}
