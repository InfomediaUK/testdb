package com.helmet.bean;

import java.util.List;

public class AgyAccessGroupEntity extends AgyAccessGroup {

	private List<AgyAccessGroupItemUser> agyAccessGroupItemUsers;

	private List<AgyAccess> agyAccesses;

	public List<AgyAccess> getAgyAccesses() {
		return agyAccesses;
	}

	public void setAgyAccesses(List<AgyAccess> agyAccesses) {
		this.agyAccesses = agyAccesses;
	}

	public List<AgyAccessGroupItemUser> getAgyAccessGroupItemUsers() {
		return agyAccessGroupItemUsers;
	}

	public void setAgyAccessGroupItemUsers(
			List<AgyAccessGroupItemUser> agyAccessGroupItemUsers) {
		this.agyAccessGroupItemUsers = agyAccessGroupItemUsers;
	}

}
