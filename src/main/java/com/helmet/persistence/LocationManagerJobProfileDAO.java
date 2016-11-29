package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.LocationManagerJobProfile;
import com.helmet.bean.LocationManagerJobProfileUser;

public interface LocationManagerJobProfileDAO {

	public List<LocationManagerJobProfileUser> getLocationManagerJobProfileUsersForManager(Integer managerId);
	public int insertLocationManagerJobProfile(LocationManagerJobProfile locationManagerJobProfile, Integer auditorId);
	public int deleteLocationManagerJobProfile(Integer locationManagerJobProfileId, Integer noOfChanges, Integer auditorId);

}
