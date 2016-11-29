package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.JobProfile;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.JobProfileUserEntity;

public interface JobProfileDAO {

	public List<JobProfile> getJobProfilesForJobSubFamily(Integer jobSubFamilyId, boolean showOnlyActive);
  public List<JobProfileUser> getJobProfileUsersForNhsAssignment(Integer clientId, String nhsAssignment); 
	public List<JobProfileUser> getJobProfileUsersNotForLocation(Integer clientId, Integer locationId);
	public List<JobProfileUser> getJobProfileUsersForManagerForLocation(Integer managerId, Integer locationId);
	public List<JobProfileUser> getJobProfileUsersForManager(Integer managerId);
	public List<JobProfileUser> getJobProfileUsersForManagerForSite(Integer managerId, Integer siteId);
	public List<JobProfileUser> getJobProfileUsersForAgency(Integer agencyId, Integer clientId, Integer siteId, Integer locationId);
//	public List<JobProfileUser> getJobProfileUsersForAgencyForSite(Integer agencyId, Integer siteId);
//	public List<JobProfileUser> getJobProfileUsersForAgencyForLocation(Integer agencyId, Integer locationId);
	public JobProfile getJobProfile(Integer jobProfileId);
	public JobProfile getJobProfileForName(Integer jobSubFamilyId, String name);
	public JobProfile getJobProfileForCode(Integer jobSubFamilyId, String code);
	public JobProfileUser getJobProfileUser(Integer jobProfileId);
	public JobProfileUserEntity getJobProfileUserEntity(Integer jobProfileId);
	public JobProfile getJobProfile(Integer jobSubFamilyId, String name);
	public int insertJobProfile(JobProfile jobProfile, Integer auditorId);
	public int updateJobProfile(JobProfile jobProfile, Integer auditorId);
	public int deleteJobProfile(Integer jobProfileId, Integer noOfChanges, Integer auditorId);
	public int updateJobProfileDisplayOrder(Integer jobProfileId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
