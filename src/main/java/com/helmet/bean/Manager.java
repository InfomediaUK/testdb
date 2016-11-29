package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Manager extends Base {

	private Integer managerId;

	private Integer clientId;
	
	private User user = new User();

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void load(SqlRowSet rs)
	{
	    super.load(rs);
	    setManagerId(rs.getInt("MANAGERID"));		
	    setClientId(rs.getInt("CLIENTID"));		
	    getUser().setFirstName(rs.getString("FIRSTNAME"));		
	    getUser().setLastName(rs.getString("LASTNAME"));		
	    getUser().setEmailAddress(rs.getString("EMAILADDRESS"));		
	    getUser().setLogin(rs.getString("LOGIN"));		
	    getUser().setPwd(rs.getString("PWD"));		
	    getUser().setPwdHint(rs.getString("PWDHINT"));		
	    getUser().setSecretWord(rs.getString("SECRETWORD"));		
	    getUser().setShowPageHelp(rs.getBoolean("SHOWPAGEHELP"));
	    getUser().setSuperUser(rs.getBoolean("SUPERUSER"));
	}

}
