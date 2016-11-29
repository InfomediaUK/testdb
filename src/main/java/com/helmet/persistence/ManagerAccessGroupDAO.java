package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.ManagerAccessGroup;
import com.helmet.bean.ManagerAccessGroupUser;

public interface ManagerAccessGroupDAO {

	public List<ManagerAccessGroupUser> getManagerAccessGroupUsersForManager(Integer managerId);
	public int insertManagerAccessGroup(ManagerAccessGroup managerAccessGroup, Integer auditorId);
	public int deleteManagerAccessGroup(Integer managerAccessId, Integer noOfChanges, Integer auditorId);

}
