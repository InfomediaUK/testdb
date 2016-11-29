package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Agency;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.AgencyUserEntity;
import com.helmet.bean.VisaType;

public interface AgencyDAO 
{
  public List<Agency> getAgencies(boolean showOnlyActive);
	public List<AgencyUser> getAgencyUsers(boolean showOnlyActive);
	public List<AgencyUser> getAgencyUsersNotForClient(Integer clientId);
	public Agency getAgency(Integer agencyId);
	public Agency getAgencyForName(String name);
	public Agency getAgencyForCode(String code);
	public AgencyUser getAgencyUser(Integer agencyId);
	public AgencyUserEntity getAgencyUserEntity(Integer agencyId);
	public int insertAgency(Agency agency, Integer auditorId);
	public int updateAgency(Agency agency, Integer auditorId);
	public int deleteAgency(Integer agencyId, Integer noOfChanges, Integer auditorId);
	public int updateAgencyDisplayOrder(Integer agencyId, Integer displayOrder, Integer noOfChanges, Integer auditorId);
}
