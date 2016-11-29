package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AdministratorAccess;
import com.helmet.bean.AdministratorAccessUser;

public interface AdministratorAccessDAO {

	public List<AdministratorAccessUser> getAdministratorAccessUsersForAdministrator(Integer administratorId);
//	public List<AdministratorAccessUser> getAdministratorAccessUsersForAdministratorAccessGroups(Integer administratorId);
//	public List<AdministratorAccessUser> getAdministratorAccessUsersForAdminAccess();
	public int insertAdministratorAccess(AdministratorAccess administratorAccess, Integer auditorId);
	public int deleteAdministratorAccess(Integer administratorAccessId, Integer noOfChanges, Integer auditorId);

}
