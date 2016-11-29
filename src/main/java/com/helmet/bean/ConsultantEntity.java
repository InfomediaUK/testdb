package com.helmet.bean;

import java.util.List;

public class ConsultantEntity extends Consultant {

	private List<ConsultantAccessUser> consultantAccessUsers;

	private List<AgyAccess> agyAccesses;

	private List<ConsultantAccessGroupUser> consultantAccessGroupUsers;

	private List<AgyAccessGroup> agyAccessGroups;

	public List<ConsultantAccessGroupUser> getConsultantAccessGroupUsers() {
		return consultantAccessGroupUsers;
	}

	public void setConsultantAccessGroupUsers(
			List<ConsultantAccessGroupUser> consultantAccessGroupUsers) {
		this.consultantAccessGroupUsers = consultantAccessGroupUsers;
	}

	public List<ConsultantAccessUser> getConsultantAccessUsers() {
		return consultantAccessUsers;
	}

	public void setConsultantAccessUsers(List<ConsultantAccessUser> consultantAccessUsers) {
		this.consultantAccessUsers = consultantAccessUsers;
	}

	public List<AgyAccess> getAgyAccesses() {
		return agyAccesses;
	}

	public void setAgyAccesses(List<AgyAccess> agyAccesses) {
		this.agyAccesses = agyAccesses;
	}

	public List<AgyAccessGroup> getAgyAccessGroups() {
		return agyAccessGroups;
	}

	public void setAgyAccessGroups(List<AgyAccessGroup> agyAccessGroups) {
		this.agyAccessGroups = agyAccessGroups;
	}

}
