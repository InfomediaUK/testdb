package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.ClientAgencyJobProfile;
import com.helmet.bean.ClientAgencyJobProfileUser;
import com.helmet.bean.ClientAgencyJobProfileUserEntity;

public interface ClientAgencyJobProfileDAO {

	public List<ClientAgencyJobProfileUser> getClientAgencyJobProfileUsersForJobProfile(Integer jobProfileId);
	public List<ClientAgencyJobProfileUser> getClientAgencyJobProfileUsersForJobProfileAndAgency(Integer jobProfileId, Integer agencyId);
	public ClientAgencyJobProfile getClientAgencyJobProfile(Integer clientAgencyJobProfileId);
	public ClientAgencyJobProfileUserEntity getClientAgencyJobProfileUserEntity(Integer clientAgencyJobProfileId);
	public int insertClientAgencyJobProfile(ClientAgencyJobProfile clientAgencyJobProfile, Integer auditorId);
	public int updateClientAgencyJobProfile(ClientAgencyJobProfile clientAgencyJobProfile, Integer auditorId);
	public int deleteClientAgencyJobProfile(Integer clientAgencyJobProfileId, Integer noOfChanges, Integer auditorId);
	
}
