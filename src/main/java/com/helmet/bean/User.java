package com.helmet.bean;

import com.helmet.application.Utilities;

public class User {

	private String firstName;

	private String lastName;

  // NEW -->
  private String nhsStaffName;
  // <-- NEW

  private String emailAddress;

	private String login;

	private String pwd;

	private String pwdHint;

	private String secretWord;

	private Boolean showPageHelp = false;

	private Boolean superUser = false;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

  // NEW -->
  public String getNhsStaffName()
  {
    return nhsStaffName;
  }

  public void setNhsStaffName(String nhsStaffName)
  {
    this.nhsStaffName = nhsStaffName;
  }
  // <-- NEW

	public String getPwdHint() {
		return pwdHint;
	}

	public void setPwdHint(String pwdHint) {
		this.pwdHint = pwdHint;
	}

	public String getSecretWord() {
		return secretWord;
	}

	public void setSecretWord(String secretWord) {
		this.secretWord = secretWord;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public String getFullNameLastFirst() {
		return lastName + ", " + firstName;
	}

	public Boolean getShowPageHelp() {
		return showPageHelp;
	}

	public void setShowPageHelp(Boolean showPageHelp) {
		this.showPageHelp = showPageHelp;
	}

	public Boolean getSuperUser() {
		return superUser;
	}

	public void setSuperUser(Boolean superUser) {
		this.superUser = superUser;
	}

	public Boolean getHasChangedSecretWord() {
		return !secretWord.equals(login); 
	}
	
	public String getNiceEmailAddress() {
		return Utilities.makeNiceEmailAddress(getFullName(), getEmailAddress());
	}
	
  public boolean getNhsMatched()
  {
    return (lastName + " " + firstName).equals(nhsStaffName);
  }
  
}
