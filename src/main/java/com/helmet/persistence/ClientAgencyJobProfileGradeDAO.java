package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;

public interface ClientAgencyJobProfileGradeDAO {

  public List<ClientAgencyJobProfileGradeUser> getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(Integer clientAgencyJobProfileId);
  public List<ClientAgencyJobProfileGradeUser> getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(Integer clientId, Integer agencyId, Integer jobProfileId);
  public List<ClientAgencyJobProfileGrade> getClientAgencyJobProfileGradesForJobProfile(Integer jobProfileId);
	public List<ClientAgencyJobProfileGrade> getClientAgencyJobProfileGradesForJobProfileAndAgency(Integer jobProfileId, Integer agencyId);
	public int insertClientAgencyJobProfileGrade(ClientAgencyJobProfileGrade clientAgencyJobProfileGrade, Integer auditorId);
	public int deleteClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId, Integer noOfChanges, Integer auditorId);
	public ClientAgencyJobProfileGrade getClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId);
	public ClientAgencyJobProfileGradeUser getClientAgencyJobProfileGradeUser(Integer clientAgencyJobProfileGradeId);
	public int updateClientAgencyJobProfileGrade(ClientAgencyJobProfileGrade clientAgencyJobProfileGrade, Integer auditorId);
	
}
