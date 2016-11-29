package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.MgrAccessGroup;
import com.helmet.bean.MgrAccessGroupEntity;

public interface MgrAccessGroupDAO {

	public List<MgrAccessGroup> getMgrAccessGroupsForClient(Integer clientId, boolean showOnlyActive);
	public List<MgrAccessGroup> getMgrAccessGroupsNotForManager(Integer clientId, Integer managerId);
	public MgrAccessGroup getMgrAccessGroup(Integer mgrAccessGroupId);
	public MgrAccessGroupEntity getMgrAccessGroupEntity(Integer mgrAccessGroupId);
	public MgrAccessGroup getMgrAccessGroupForName(Integer clientId, String name);
	public int insertMgrAccessGroup(MgrAccessGroup mgrAccessGroup, Integer auditorId);
	public int updateMgrAccessGroup(MgrAccessGroup mgrAccessGroup, Integer auditorId);
	public int deleteMgrAccessGroup(Integer mgrAccessGroupId, Integer noOfChanges, Integer auditorId);

}
