package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.ClientAgency;
import com.helmet.bean.ClientAgencyUser;
import com.helmet.bean.ClientAgencyUserEntity;

public interface ClientAgencyDAO {

	public List<ClientAgencyUser> getClientAgencyUsersForClient(Integer clientId, boolean showOnlyActive);
	public List<ClientAgencyUser> getClientAgencyUsersForClient(Integer clientId);
	public List<ClientAgencyUser> getClientAgencyUsersForAgency(Integer agencyId, boolean showOnlyActive);
	public List<ClientAgencyUser> getClientAgencyUsersForAgency(Integer agencyId);
  public List<ClientAgencyUser> getClientAgencyUsersForAgencyInNameGroup(Integer agencyId, String indexLetter, boolean showOnlyActive);
  public List<ClientAgencyUser> getClientAgencyUsersForAgencyInNameGroup(Integer agencyId, String indexLetter);
	public List<ClientAgencyUser> getClientAgencyUsersNotForJobProfile(Integer clientId, Integer jobProfileId);
	public ClientAgency getClientAgency(Integer clientAgencyId);
	public ClientAgency getClientAgencyForClientAndAgency(Integer clientId, Integer agencyId);
	public ClientAgencyUserEntity getClientAgencyUserEntity(Integer clientAgencyId);
	public int insertClientAgency(ClientAgency clientAgency, Integer auditorId);
	public int deleteClientAgency(Integer clientAgencyId, Integer noOfChanges, Integer auditorId);
	public int updateClientAgency(ClientAgency clientAgency, Integer auditorId);

}
