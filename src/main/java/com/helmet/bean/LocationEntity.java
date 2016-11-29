package com.helmet.bean;

import java.util.List;

public class LocationEntity extends LocationUser {

	private List<LocationJobProfileGroupUser> locationJobProfileGroupUsers;

	private List<JobProfileGroup> jobProfileGroups;

	private List<Shift> shifts;

	private List<DressCode> dressCodes;

	private List<Expense> expenses;
	
	private List<LocationJobProfileUser> locationJobProfileUsers;

	private List<JobProfileUser> jobProfileUsers;

	public List<JobProfileGroup> getJobProfileGroups() {
		return jobProfileGroups;
	}

	public void setJobProfileGroups(List<JobProfileGroup> jobProfileGroups) {
		this.jobProfileGroups = jobProfileGroups;
	}

	public List<LocationJobProfileGroupUser> getLocationJobProfileGroupUsers() {
		return locationJobProfileGroupUsers;
	}

	public void setLocationJobProfileGroupUsers(
			List<LocationJobProfileGroupUser> locationJobProfileGroupUsers) {
		this.locationJobProfileGroupUsers = locationJobProfileGroupUsers;
	}

	public List<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(List<Shift> shifts) {
		this.shifts = shifts;
	}

	public List<DressCode> getDressCodes() {
		return dressCodes;
	}

	public void setDressCodes(List<DressCode> dressCodes) {
		this.dressCodes = dressCodes;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<JobProfileUser> getJobProfileUsers() {
		return jobProfileUsers;
	}

	public void setJobProfileUsers(List<JobProfileUser> jobProfileUsers) {
		this.jobProfileUsers = jobProfileUsers;
	}

	public List<LocationJobProfileUser> getLocationJobProfileUsers() {
		return locationJobProfileUsers;
	}

	public void setLocationJobProfileUsers(
			List<LocationJobProfileUser> locationJobProfileUsers) {
		this.locationJobProfileUsers = locationJobProfileUsers;
	}

}
