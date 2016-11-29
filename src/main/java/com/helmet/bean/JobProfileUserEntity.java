package com.helmet.bean;

import java.util.List;

public class JobProfileUserEntity extends JobProfileUser {

	private List<JobProfileGradeUser> jobProfileGradeUsers;

	private List<Grade> grades;

	private List<ClientAgencyJobProfileUser> clientAgencyJobProfileUsers;

	private List<ClientAgencyUser> clientAgencyUsers;

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public List<JobProfileGradeUser> getJobProfileGradeUsers() {
		return jobProfileGradeUsers;
	}

	public void setJobProfileGradeUsers(
			List<JobProfileGradeUser> jobProfileGradeUsers) {
		this.jobProfileGradeUsers = jobProfileGradeUsers;
	}

	public List<ClientAgencyJobProfileUser> getClientAgencyJobProfileUsers() {
		return clientAgencyJobProfileUsers;
	}

	public void setClientAgencyJobProfileUsers(
			List<ClientAgencyJobProfileUser> clientAgencyJobProfileUsers) {
		this.clientAgencyJobProfileUsers = clientAgencyJobProfileUsers;
	}

	public List<ClientAgencyUser> getClientAgencyUsers() {
		return clientAgencyUsers;
	}

	public void setClientAgencyUsers(List<ClientAgencyUser> clientAgencyUsers) {
		this.clientAgencyUsers = clientAgencyUsers;
	}
	
}
