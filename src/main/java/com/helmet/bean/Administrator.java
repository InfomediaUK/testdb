package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Administrator extends Base {

	private Integer administratorId;

    private User user = new User();

	private boolean showOnlyActive;

	public Integer getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public boolean getShowOnlyActive() {
		return showOnlyActive;
	}

	public void setShowOnlyActive(boolean showOnlyActive) {
		this.showOnlyActive = showOnlyActive;
	}

	public void load(SqlRowSet rs)
	{
	    super.load(rs);
	    setAdministratorId(rs.getInt("ADMINISTRATORID"));		
        setShowOnlyActive(rs.getBoolean("SHOWONLYACTIVE"));
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
