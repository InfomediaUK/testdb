package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AgyAccessGroup;
import com.helmet.bean.AgyAccessGroupEntity;

public interface AgyAccessGroupDAO {

	public List<AgyAccessGroup> getAgyAccessGroupsForAgency(Integer agencyId, boolean showOnlyActive);
	public List<AgyAccessGroup> getAgyAccessGroupsNotForConsultant(Integer agencyId, Integer consultantId);
	public AgyAccessGroup getAgyAccessGroup(Integer agyAccessGroupId);
	public AgyAccessGroupEntity getAgyAccessGroupEntity(Integer agyAccessGroupId);
	public AgyAccessGroup getAgyAccessGroupForName(Integer agencyId, String name);
	public int insertAgyAccessGroup(AgyAccessGroup agyAccessGroup, Integer auditorId);
	public int updateAgyAccessGroup(AgyAccessGroup agyAccessGroup, Integer auditorId);
	public int deleteAgyAccessGroup(Integer agyAccessGroupId, Integer noOfChanges, Integer auditorId);

}
