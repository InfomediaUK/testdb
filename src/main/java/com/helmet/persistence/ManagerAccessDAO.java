package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.ManagerAccess;
import com.helmet.bean.ManagerAccessUser;

public interface ManagerAccessDAO {

	public List<ManagerAccessUser> getManagerAccessUsersForManager(Integer managerId);
	public int insertManagerAccess(ManagerAccess managerAccess, Integer auditorId);
	public int deleteManagerAccess(Integer managerAccessId, Integer noOfChanges, Integer auditorId);

}
