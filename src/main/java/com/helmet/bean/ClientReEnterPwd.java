package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientReEnterPwd extends Base {

	private Integer clientReEnterPwdId;

	private Integer clientId;
	
	private Integer reEnterPwdId;

	public Integer getClientReEnterPwdId() {
		return clientReEnterPwdId;
	}

	public void setClientReEnterPwdId(Integer clientReEnterPwdId) {
		this.clientReEnterPwdId = clientReEnterPwdId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getReEnterPwdId() {
		return reEnterPwdId;
	}

	public void setReEnterPwdId(Integer reEnterPwdId) {
		this.reEnterPwdId = reEnterPwdId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setClientReEnterPwdId(rs.getInt("CLIENTREENTERPWDID"));
		setClientId(rs.getInt("CLIENTID"));
		setReEnterPwdId(rs.getInt("REENTERPWDID"));
	}

}
