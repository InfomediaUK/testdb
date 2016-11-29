package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.MgrAccess;

public interface MgrAccessDAO {

	public List<MgrAccess> getMgrAccesses(boolean showOnlyActive);
	public List<MgrAccess> getActiveMgrAccessesForManager(Integer managerId);
	public List<MgrAccess> getMgrAccessesNotForManager(Integer managerId);
	public List<MgrAccess> getMgrAccessesNotForMgrAccessGroup(Integer adminAccessGroupId);
	public MgrAccess getMgrAccess(Integer mgrAccessId);
	public MgrAccess getMgrAccessForName(String name);
	public int insertMgrAccess(MgrAccess mgrAccess, Integer auditorId);
	public int updateMgrAccess(MgrAccess mgrAccess, Integer auditorId);
	public int deleteMgrAccess(Integer mgrAccessId, Integer noOfChanges, Integer auditorId);

}
