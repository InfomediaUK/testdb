package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientReEnterPwdUser extends ClientReEnterPwd {

	private String reEnterPwdName;

    public String getReEnterPwdName() {
		return reEnterPwdName;
	}

	public void setReEnterPwdName(String reEnterPwdName) {
		this.reEnterPwdName = reEnterPwdName;
	}

	public void load(SqlRowSet rs)
	{
	    super.load(rs);
		setReEnterPwdName(rs.getString("REENTERPWDNAME"));		
	}
	
}
