package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Client;
import com.helmet.bean.ClientUser;
import com.helmet.bean.ClientUserEntity;

public interface ClientDAO 
{
	public List<ClientUser> getClientUsers(boolean showOnlyActive);
  public List<ClientUser> getClientUsersForAgency(Integer agencyId);
  public List<ClientUser> getNhsClientUsersForAgency(Integer agencyId);
	public List<ClientUserEntity> getClientUserEntities(boolean showOnlyActive);
	public Client getClient(Integer clientId);
	public Client getClientForName(String name);
  public Client getClientForCode(String code);
  public Client getClientForReference(String reference);
  public List<Client> getClientsForTradeshiftSbsPayablesCode(String tradeshiftSbsPayablesCode);
	public Client getClientForBookingGradeApplicant(Integer bookingGradeApplicantId);
	public ClientUser getClientUser(Integer clientId);
	public ClientUserEntity getClientUserEntity(Integer clientId);
	public int insertClient(Client client, Integer auditorId);
  public int updateClient(Client client, Integer auditorId);
  public int updateClientTradeshiftDetails(Client client, Integer auditorId);
	public int deleteClient(Integer clientId, Integer noOfChanges, Integer auditorId);
	public int updateClientDisplayOrder(Integer clientId, Integer displayOrder, Integer noOfChanges, Integer auditorId);
}
