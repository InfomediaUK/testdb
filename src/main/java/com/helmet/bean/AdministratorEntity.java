package com.helmet.bean;

import java.util.List;

public class AdministratorEntity extends Administrator {

	private List<AdministratorAccessUser> administratorAccessUsers;

	private List<AdminAccess> adminAccesses;

	private List<AdministratorAccessGroupUser> administratorAccessGroupUsers;

	private List<AdminAccessGroup> adminAccessGroups;

	public List<AdminAccess> getAdminAccesses() {
		return adminAccesses;
	}

	public void setAdminAccesses(List<AdminAccess> adminAccesses) {
		this.adminAccesses = adminAccesses;
	}

	public List<AdministratorAccessUser> getAdministratorAccessUsers() {
		return administratorAccessUsers;
	}

	public void setAdministratorAccessUsers(
			List<AdministratorAccessUser> administratorAccessUsers) {
		this.administratorAccessUsers = administratorAccessUsers;
	}

	public List<AdminAccessGroup> getAdminAccessGroups() {
		return adminAccessGroups;
	}

	public void setAdminAccessGroups(List<AdminAccessGroup> adminAccessGroups) {
		this.adminAccessGroups = adminAccessGroups;
	}

	public List<AdministratorAccessGroupUser> getAdministratorAccessGroupUsers() {
		return administratorAccessGroupUsers;
	}

	public void setAdministratorAccessGroupUsers(
			List<AdministratorAccessGroupUser> administratorAccessGroupUsers) {
		this.administratorAccessGroupUsers = administratorAccessGroupUsers;
	}

	
}
