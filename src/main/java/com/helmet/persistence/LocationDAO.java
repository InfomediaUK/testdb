package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Location;
import com.helmet.bean.LocationEntity;
import com.helmet.bean.LocationUser;
import com.helmet.bean.LocationUserJobProfile;

public interface LocationDAO {

	public List<Location> getLocationsForSite(Integer siteId, boolean showOnlyActive);
	public Location getLocation(Integer locationId);
	public Location getLocationForName(Integer siteId, String name);	
	public Location getLocationForCode(Integer siteId, String code);
	public LocationUser getLocationUser(Integer locationId);
	public LocationEntity getLocationEntity(Integer locationId);
	public List<LocationUserJobProfile> getLocationUserJobProfilesNotForManager(Integer clientId, Integer managerId);
  // NEW -->
  public List<Location> getLocationsForNhsWard(Integer siteId, String nhsWard);
  // <-- NEW
  public List<LocationUser> getLocationUsersForManager(Integer managerId);
	public List<LocationUser> getLocationUsersForManagerForSite(Integer managerId, Integer siteId);
	public List<LocationUser> getLocationUsersForAgency(Integer agencyId, Integer clientId, Integer siteId);
//	public List<LocationUser> getLocationUsersForAgencyForSite(Integer agencyId, Integer siteId);
	public int insertLocation(Location location, Integer auditorId);
	public int updateLocation(Location location, Integer auditorId);
	public int deleteLocation(Integer locationId, Integer noOfChanges, Integer auditorId);
	public int updateLocationDisplayOrder(Integer locationId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
