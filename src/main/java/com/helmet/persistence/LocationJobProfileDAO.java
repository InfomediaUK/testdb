package com.helmet.persistence;

import java.math.BigDecimal;
import java.util.List;

import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationJobProfileUser;

public interface LocationJobProfileDAO {

	public List<LocationJobProfileUser> getLocationJobProfileUsersForClient(Integer clientId, boolean showOnlyActive);
  public List<LocationJobProfileUser> getLocationJobProfileUsersForLocation(Integer locationId);
  public List<LocationJobProfileUser> getLocationJobProfileUsersForLocationAndNhsAssignment(Integer locationId, String nhsAssignment);
	public List<LocationJobProfileUser> getLocationJobProfileUsersNotForManager(Integer clientId, Integer managerId);
	public List<LocationJobProfileUser> getLocationJobProfileUsersForManager(Integer managerId);
	public List<LocationJobProfileUser> getLocationJobProfileUsersForManagerForLocation(Integer managerId, Integer locationId);
	public LocationJobProfile getLocationJobProfile(Integer locationJobProfileId);
	public LocationJobProfile getLocationJobProfileForLocationAndJobProfile(Integer locationId, Integer jobProfileId);
	public LocationJobProfileUser getLocationJobProfileUser(Integer locationJobProfileUserId);
	public LocationJobProfileUser getLocationJobProfileUserForManager(Integer managerId, Integer locationJobProfileId);	
	public int insertLocationJobProfile(LocationJobProfile locationJobProfile, Integer auditorId);
	public int deleteLocationJobProfile(Integer locationJobProfileId, Integer noOfChanges, Integer auditorId);
	public int updateLocationJobProfileBudget(Integer locationJobProfileId, BigDecimal value, BigDecimal vatValue, BigDecimal expenseValue, BigDecimal nonPayValue, Integer noOfChanges, Integer auditorId);
	public int updateLocationJobProfileRate(Integer locationJobProfileId, BigDecimal value, Integer noOfChanges, Integer auditorId);

}
