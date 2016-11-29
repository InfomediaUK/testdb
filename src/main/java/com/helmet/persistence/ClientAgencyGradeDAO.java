package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.ClientAgencyGrade;
import com.helmet.bean.ClientAgencyGradeUser;

public interface ClientAgencyGradeDAO {

	public List<ClientAgencyGradeUser> getClientAgencyGradeUsersForClientAgency(Integer clientAgencyId);
	public List<ClientAgencyGrade> getClientAgencyGradesForJobProfile(Integer jobProfileId);
	public int insertClientAgencyGrade(ClientAgencyGrade clientAgencyGrade, Integer auditorId);
	public int deleteClientAgencyGrade(Integer clientAgencyGradeId, Integer noOfChanges, Integer auditorId);
	public ClientAgencyGrade getClientAgencyGrade(Integer clientAgencyGradeId);
	public int updateClientAgencyGrade(ClientAgencyGrade clientAgencyGrade, Integer auditorId);
	
}
