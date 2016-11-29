package com.helmet.bean;

import java.util.List;

public class ManagerEntity extends Manager {

	private List<LocationManagerJobProfileUser> locationManagerJobProfileUsers;

	private List<LocationJobProfileUser> locationJobProfileUsers;

	private List<ManagerAccessUser> managerAccessUsers;

	private List<MgrAccess> mgrAccesses;

	private List<ManagerAccessGroupUser> managerAccessGroupUsers;

	private List<MgrAccessGroup> mgrAccessGroups;

	public List<LocationManagerJobProfileUser> getLocationManagerJobProfileUsers() {
		return locationManagerJobProfileUsers;
	}

	public void setLocationManagerJobProfileUsers(
			List<LocationManagerJobProfileUser> locationManagerJobProfileUsers) {
		this.locationManagerJobProfileUsers = locationManagerJobProfileUsers;
	}

	public List<LocationJobProfileUser> getLocationJobProfileUsers() {
		return locationJobProfileUsers;
	}

	public void setLocationJobProfileUsers(List<LocationJobProfileUser> locationJobProfileUsers) {
		this.locationJobProfileUsers = locationJobProfileUsers;
	}

	public List<ManagerAccessGroupUser> getManagerAccessGroupUsers() {
		return managerAccessGroupUsers;
	}

	public void setManagerAccessGroupUsers(
			List<ManagerAccessGroupUser> managerAccessGroupUsers) {
		this.managerAccessGroupUsers = managerAccessGroupUsers;
	}

	public List<ManagerAccessUser> getManagerAccessUsers() {
		return managerAccessUsers;
	}

	public void setManagerAccessUsers(List<ManagerAccessUser> managerAccessUsers) {
		this.managerAccessUsers = managerAccessUsers;
	}

	public List<MgrAccess> getMgrAccesses() {
		return mgrAccesses;
	}

	public void setMgrAccesses(List<MgrAccess> mgrAccesses) {
		this.mgrAccesses = mgrAccesses;
	}

	public List<MgrAccessGroup> getMgrAccessGroups() {
		return mgrAccessGroups;
	}

	public void setMgrAccessGroups(List<MgrAccessGroup> mgrAccessGroups) {
		this.mgrAccessGroups = mgrAccessGroups;
	}

}
