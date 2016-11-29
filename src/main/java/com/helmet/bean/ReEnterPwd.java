package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ReEnterPwd extends Base {

	private Integer reEnterPwdId;

	private String name;

	public Integer getReEnterPwdId() {
		return reEnterPwdId;
	}

	public void setReEnterPwdId(Integer reEnterPwdId) {
		this.reEnterPwdId = reEnterPwdId;
	}

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void load(SqlRowSet rs)
	{
	    super.load(rs);
	    setReEnterPwdId(rs.getInt("REENTERPWDID"));		
		setName(rs.getString("NAME"));		
	}
	
	
}
