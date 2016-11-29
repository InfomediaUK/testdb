package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AgencyInvoiceUser extends AgencyInvoice {

	private String clientCode;
	
	private String clientName;
	
	private String agencyCode;
	
	private String agencyName;

	private String submittedByLogin;
	private String submittedByFirstName;
	private String submittedByLastName;
	private String authorizedByLogin;
	private String authorizedByFirstName;
	private String authorizedByLastName;
	private String paidByLogin;
	private String paidByFirstName;
	private String paidByLastName;
	private String creditedByLogin;
	private String creditedByFirstName;
	private String creditedByLastName;
	
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAuthorizedByFirstName() {
		return authorizedByFirstName;
	}

	public void setAuthorizedByFirstName(String authorizedByFirstName) {
		this.authorizedByFirstName = authorizedByFirstName;
	}

	public String getAuthorizedByLastName() {
		return authorizedByLastName;
	}

	public void setAuthorizedByLastName(String authorizedByLastName) {
		this.authorizedByLastName = authorizedByLastName;
	}

	public String getAuthorizedByLogin() {
		return authorizedByLogin;
	}

	public void setAuthorizedByLogin(String authorizedByLogin) {
		this.authorizedByLogin = authorizedByLogin;
	}

	public String getPaidByFirstName() {
		return paidByFirstName;
	}

	public void setPaidByFirstName(String paidByFirstName) {
		this.paidByFirstName = paidByFirstName;
	}

	public String getPaidByLastName() {
		return paidByLastName;
	}

	public void setPaidByLastName(String paidByLastName) {
		this.paidByLastName = paidByLastName;
	}

	public String getPaidByLogin() {
		return paidByLogin;
	}

	public void setPaidByLogin(String paidByLogin) {
		this.paidByLogin = paidByLogin;
	}

	public String getSubmittedByFirstName() {
		return submittedByFirstName;
	}

	public void setSubmittedByFirstName(String submittedByFirstName) {
		this.submittedByFirstName = submittedByFirstName;
	}

	public String getSubmittedByLastName() {
		return submittedByLastName;
	}

	public void setSubmittedByLastName(String submittedByLastName) {
		this.submittedByLastName = submittedByLastName;
	}

	public String getSubmittedByLogin() {
		return submittedByLogin;
	}

	public void setSubmittedByLogin(String submittedByLogin) {
		this.submittedByLogin = submittedByLogin;
	}

	public String getCreditedByFirstName() {
		return creditedByFirstName;
	}

	public void setCreditedByFirstName(String creditedByFirstName) {
		this.creditedByFirstName = creditedByFirstName;
	}

	public String getCreditedByLastName() {
		return creditedByLastName;
	}

	public void setCreditedByLastName(String creditedByLastName) {
		this.creditedByLastName = creditedByLastName;
	}

	public String getCreditedByLogin() {
		return creditedByLogin;
	}

	public void setCreditedByLogin(String creditedByLogin) {
		this.creditedByLogin = creditedByLogin;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setClientCode(rs.getString("CLIENTCODE"));
		setClientName(rs.getString("CLIENTNAME"));
		setAgencyCode(rs.getString("AGENCYCODE"));
		setAgencyName(rs.getString("AGENCYNAME"));
		
		setSubmittedByLogin(rs.getString("SUBMITTEDBYLOGIN"));
		setSubmittedByFirstName(rs.getString("SUBMITTEDBYFIRSTNAME"));
		setSubmittedByLastName(rs.getString("SUBMITTEDEDBYLASTNAME"));
		setAuthorizedByLogin(rs.getString("AUTHORIZEDBYLOGIN"));
		setAuthorizedByFirstName(rs.getString("AUTHORIZEDBYFIRSTNAME"));
		setAuthorizedByLastName(rs.getString("AUTHORIZEDBYLASTNAME"));
		setPaidByLogin(rs.getString("PAIDBYLOGIN"));
		setPaidByFirstName(rs.getString("PAIDBYFIRSTNAME"));
		setPaidByLastName(rs.getString("PAIDBYLASTNAME"));
		setCreditedByLogin(rs.getString("CREDITEDBYLOGIN"));
		setCreditedByFirstName(rs.getString("CREDITEDBYFIRSTNAME"));
		setCreditedByLastName(rs.getString("CREDITEDBYLASTNAME"));
		
	}

}
