package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientAgencyGrade extends Base {

	private Integer clientAgencyGradeId;

	private Integer clientAgencyId;

	private Integer gradeId;

	private BigDecimal rate;
	
	private transient Integer rank; // not persisted

	private transient BigDecimal value; // not persisted

	public Integer getClientAgencyGradeId() {
		return clientAgencyGradeId;
	}

	public void setClientAgencyGradeId(Integer clientAgencyGradeId) {
		this.clientAgencyGradeId = clientAgencyGradeId;
	}

	public Integer getClientAgencyId() {
		return clientAgencyId;
	}

	public void setClientAgencyId(Integer clientAgencyId) {
		this.clientAgencyId = clientAgencyId;
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

	public void load(SqlRowSet rs) {
		super.load(rs);
		setClientAgencyGradeId(rs.getInt("CLIENTAGENCYGRADEID"));
		setClientAgencyId(rs.getInt("CLIENTAGENCYID"));
		setGradeId(rs.getInt("GRADEID"));
		setRate(rs.getBigDecimal("RATE"));
	}

}
