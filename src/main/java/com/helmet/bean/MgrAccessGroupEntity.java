package com.helmet.bean;

import java.util.List;

public class MgrAccessGroupEntity extends MgrAccessGroup {

	private List<MgrAccessGroupItemUser> mgrAccessGroupItemUsers;

	private List<MgrAccess> mgrAccesses;

	public List<MgrAccess> getMgrAccesses() {
		return mgrAccesses;
	}

	public void setMgrAccesses(List<MgrAccess> mgrAccesses) {
		this.mgrAccesses = mgrAccesses;
	}

	public List<MgrAccessGroupItemUser> getMgrAccessGroupItemUsers() {
		return mgrAccessGroupItemUsers;
	}

	public void setMgrAccessGroupItemUsers(
			List<MgrAccessGroupItemUser> mgrAccessGroupItemUsers) {
		this.mgrAccessGroupItemUsers = mgrAccessGroupItemUsers;
	}

}
