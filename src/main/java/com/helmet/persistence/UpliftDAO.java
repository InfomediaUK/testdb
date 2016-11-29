package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Uplift;

public interface UpliftDAO {

  public List<Uplift> getUpliftsForClient(Integer clientId, boolean showOnlyActive);
	public Uplift getUplift(Integer upliftId);
	public int insertUplift(Uplift uplift, Integer auditorId);
	public int updateUplift(Uplift uplift, Integer auditorId);
	public int deleteUplift(Integer upliftId, Integer noOfChanges, Integer auditorId);

}
