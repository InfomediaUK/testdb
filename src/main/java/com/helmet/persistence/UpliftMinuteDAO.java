package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.UpliftMinute;
import com.helmet.bean.UpliftMinuteUser;

public interface UpliftMinuteDAO 
{
  public List<UpliftMinute> getUpliftMinutesForUplift(Integer upliftMinuteId, boolean showOnlyActive);
  public List<UpliftMinuteUser> getUpliftMinuteUsersForUplift(Integer upliftMinuteId, boolean showOnlyActive);
  public List<UpliftMinute> getUpliftMinutesForClient(Integer clientId);
  public List<UpliftMinute> getUpliftMinutesForClient(Integer clientId, boolean showOnlyActive);
	public UpliftMinute getUpliftMinute(Integer upliftMinuteId);
	public int insertUpliftMinute(UpliftMinute upliftMinute, Integer auditorId);
	public int updateUpliftMinute(UpliftMinute upliftMinute, Integer auditorId);
	public int deleteUpliftMinute(Integer upliftMinuteId, Integer noOfChanges, Integer auditorId);
}
