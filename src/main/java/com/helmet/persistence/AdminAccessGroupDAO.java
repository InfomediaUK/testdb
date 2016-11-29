package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AdminAccessGroup;
import com.helmet.bean.AdminAccessGroupEntity;

public interface AdminAccessGroupDAO {

	public List<AdminAccessGroup> getAdminAccessGroups(boolean showOnlyActive);
	public List<AdminAccessGroup> getAdminAccessGroupsNotForAdministrator(Integer administratorId);
	public AdminAccessGroup getAdminAccessGroup(Integer adminAccessGroupId);
	public AdminAccessGroupEntity getAdminAccessGroupEntity(Integer adminAccessGroupId);
	public AdminAccessGroup getAdminAccessGroupForName(String name);
	public int insertAdminAccessGroup(AdminAccessGroup adminAccessGroup, Integer auditorId);
	public int updateAdminAccessGroup(AdminAccessGroup adminAccessGroup, Integer auditorId);
	public int deleteAdminAccessGroup(Integer adminAccessGroupId, Integer noOfChanges, Integer auditorId);

}
