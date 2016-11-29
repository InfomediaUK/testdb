package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AdministratorAccessGroup;
import com.helmet.bean.AdministratorAccessGroupUser;

public interface AdministratorAccessGroupDAO {

	public List<AdministratorAccessGroupUser> getAdministratorAccessGroupUsersForAdministrator(Integer administratorId);
	public int insertAdministratorAccessGroup(AdministratorAccessGroup administratorAccessGroup, Integer auditorId);
	public int deleteAdministratorAccessGroup(Integer administratorAccessId, Integer noOfChanges, Integer auditorId);

}
