package com.helmet.bean;

import java.util.List;

public class JobProfileGroupEntity extends JobProfileGroup {

	private List<JobProfileGroupItemUser> jobProfileGroupItemUsers;

	private List<JobProfileUser> jobProfileUsers;

	public List<JobProfileUser> getJobProfileUsers() {
		return jobProfileUsers;
	}

	public void setJobProfileUsers(List<JobProfileUser> jobProfileUsers) {
		this.jobProfileUsers = jobProfileUsers;
	}

	public List<JobProfileGroupItemUser> getJobProfileGroupItemUsers() {
		return jobProfileGroupItemUsers;
	}

	public void setJobProfileGroupItemUsers(
			List<JobProfileGroupItemUser> jobProfileGroupItemUsers) {
		this.jobProfileGroupItemUsers = jobProfileGroupItemUsers;
	}

}
