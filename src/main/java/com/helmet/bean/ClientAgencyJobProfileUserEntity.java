package com.helmet.bean;

import java.util.List;

public class ClientAgencyJobProfileUserEntity extends ClientAgencyJobProfileUser {

	private List<ClientAgencyJobProfileGradeUser> clientAgencyJobProfileGradeUsers;

	private List<Grade> grades;

    public List<ClientAgencyJobProfileGradeUser> getClientAgencyJobProfileGradeUsers() {
		return clientAgencyJobProfileGradeUsers;
	}

	public void setClientAgencyJobProfileGradeUsers(List<ClientAgencyJobProfileGradeUser> clientAgencyJobProfileGradeUsers) {
		this.clientAgencyJobProfileGradeUsers = clientAgencyJobProfileGradeUsers;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

}
