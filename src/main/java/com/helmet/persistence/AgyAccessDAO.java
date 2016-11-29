package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AgyAccess;

public interface AgyAccessDAO {

	public List<AgyAccess> getAgyAccesses(boolean showOnlyActive);
	public List<AgyAccess> getActiveAgyAccessesForConsultant(Integer consultantId);
	public List<AgyAccess> getAgyAccessesNotForConsultant(Integer consultantId);
	public List<AgyAccess> getAgyAccessesNotForAgyAccessGroup(Integer adminAccessGroupId);
	public AgyAccess getAgyAccess(Integer agyAccessId);
	public AgyAccess getAgyAccessForName(String name);
	public int insertAgyAccess(AgyAccess agyAccess, Integer auditorId);
	public int updateAgyAccess(AgyAccess agyAccess, Integer auditorId);
	public int deleteAgyAccess(Integer agyAccessId, Integer noOfChanges, Integer auditorId);

}
