package com.helmet.bean;

import java.util.List;

public class AdminAccessGroupEntity extends AdminAccessGroup {

	private List<AdminAccessGroupItemUser> adminAccessGroupItemUsers;

	private List<AdminAccess> adminAccesses;

	public List<AdminAccess> getAdminAccesses() {
		return adminAccesses;
	}

	public void setAdminAccesses(List<AdminAccess> adminAccesses) {
		this.adminAccesses = adminAccesses;
	}

	public List<AdminAccessGroupItemUser> getAdminAccessGroupItemUsers() {
		return adminAccessGroupItemUsers;
	}

	public void setAdminAccessGroupItemUsers(
			List<AdminAccessGroupItemUser> adminAccessGroupItemUsers) {
		this.adminAccessGroupItemUsers = adminAccessGroupItemUsers;
	}

}
