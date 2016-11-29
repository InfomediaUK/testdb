package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ConsultantAccess extends Base {

	private Integer consultantAccessId;

	private Integer consultantId;

	private Integer agyAccessId;

	public Integer getConsultantAccessId() {
		return consultantAccessId;
	}

	public void setConsultantAccessId(Integer consultantAccessId) {
		this.consultantAccessId = consultantAccessId;
	}

	public Integer getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(Integer consultantId) {
		this.consultantId = consultantId;
	}

	public Integer getAgyAccessId() {
		return agyAccessId;
	}

	public void setAgyAccessId(Integer agyAccessId) {
		this.agyAccessId = agyAccessId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setConsultantAccessId(rs.getInt("CONSULTANTACCESSID"));
		setConsultantId(rs.getInt("CONSULTANTID"));
		setAgyAccessId(rs.getInt("AGYACCESSID"));
	}

}
