package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AdminAccess;

public interface AdminAccessDAO {

	public List<AdminAccess> getAdminAccesses(boolean showOnlyActive);
	public List<AdminAccess> getActiveAdminAccessesForAdministrator(Integer administratorId);
	public List<AdminAccess> getAdminAccessesNotForAdministrator(Integer administratorId);
	public List<AdminAccess> getAdminAccessesNotForAdminAccessGroup(Integer adminiAccessGroupId);
	public AdminAccess getAdminAccess(Integer adminAccessId);
	public AdminAccess getAdminAccessForName(String name);
	public int insertAdminAccess(AdminAccess adminAccess, Integer auditorId);
	public int updateAdminAccess(AdminAccess adminAccess, Integer auditorId);
	public int deleteAdminAccess(Integer adminAccessId, Integer noOfChanges, Integer auditorId);

}
