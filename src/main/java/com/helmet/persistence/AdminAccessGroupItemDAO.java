package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AdminAccessGroupItem;
import com.helmet.bean.AdminAccessGroupItemUser;

public interface AdminAccessGroupItemDAO {

	public List<AdminAccessGroupItemUser> getAdminAccessGroupItemUsersForAdminAccessGroup(Integer adminAccessGroupId);
	public int insertAdminAccessGroupItem(AdminAccessGroupItem adminAccessGroupItem, Integer auditorId);
	public int deleteAdminAccessGroupItem(Integer adminAccessGroupItemId, Integer noOfChanges, Integer auditorId);

}
