package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ConsultantAccessGroup extends Base {

	private Integer consultantAccessGroupId;

	private Integer consultantId;

	private Integer agyAccessGroupId;

	public Integer getConsultantAccessGroupId() {
		return consultantAccessGroupId;
	}

	public void setConsultantAccessGroupId(Integer consultantAccessGroupId) {
		this.consultantAccessGroupId = consultantAccessGroupId;
	}

	public Integer getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(Integer consultantId) {
		this.consultantId = consultantId;
	}

	public Integer getAgyAccessGroupId() {
		return agyAccessGroupId;
	}

	public void setAgyAccessGroupId(Integer agyAccessGroupId) {
		this.agyAccessGroupId = agyAccessGroupId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setConsultantAccessGroupId(rs.getInt("CONSULTANTACCESSGROUPID"));
		setConsultantId(rs.getInt("CONSULTANTID"));
		setAgyAccessGroupId(rs.getInt("AGYACCESSGROUPID"));
	}

}
