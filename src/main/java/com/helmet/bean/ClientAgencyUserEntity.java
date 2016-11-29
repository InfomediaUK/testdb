package com.helmet.bean;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ClientAgencyUserEntity extends ClientAgencyUser {

	private List<ClientAgencyGradeUser> clientAgencyGradeUsers;

	private List<Grade> grades;

    public List<ClientAgencyGradeUser> getClientAgencyGradeUsers() {
		return clientAgencyGradeUsers;
	}

	public void setClientAgencyGradeUsers(List<ClientAgencyGradeUser> clientAgencyGradeUsers) {
		this.clientAgencyGradeUsers = clientAgencyGradeUsers;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

}
