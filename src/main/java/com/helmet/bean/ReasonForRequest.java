package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ReasonForRequest extends BaseDisplayOrder {

	private Integer reasonForRequestId;

	private Integer clientId;

	private String name;

	private String code;

	public Integer getReasonForRequestId() {
		return reasonForRequestId;
	}

	public void setReasonForRequestId(Integer reasonForRequestId) {
		this.reasonForRequestId = reasonForRequestId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setReasonForRequestId(rs.getInt("REASONFORREQUESTID"));
        setClientId(rs.getInt("CLIENTID"));		
		setName(rs.getString("NAME"));
		setCode(rs.getString("CODE"));
	}

}
