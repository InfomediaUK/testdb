package com.helmet.bean;

import java.util.List;

public class AgencyUserEntity extends AgencyUser {

	private List<Consultant> consultants;

	private List<AgyAccessGroup> agyAccessGroups;

	private List<ClientAgencyUser> clientAgencyUsers;

	public List<Consultant> getConsultants() {
		return consultants;
	}

	public void setConsultants(List<Consultant> consultants) {
		this.consultants = consultants;
	}

	public List<AgyAccessGroup> getAgyAccessGroups() {
		return agyAccessGroups;
	}

	public void setAgyAccessGroups(List<AgyAccessGroup> agyAccessGroups) {
		this.agyAccessGroups = agyAccessGroups;
	}

	public List<ClientAgencyUser> getClientAgencyUsers() {
		return clientAgencyUsers;
	}

	public void setClientAgencyUsers(List<ClientAgencyUser> clientAgencyUsers) {
		this.clientAgencyUsers = clientAgencyUsers;
	}
	
}
