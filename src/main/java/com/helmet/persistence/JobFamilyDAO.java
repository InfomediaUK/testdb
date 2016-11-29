package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.JobFamily;
import com.helmet.bean.JobFamilyEntity;

public interface JobFamilyDAO {

	public List<JobFamily> getJobFamiliesForClient(Integer clientId, boolean showOnlyActive);
	public JobFamily getJobFamily(Integer jobFamilyId);
	public JobFamily getJobFamilyForName(Integer clientId, String name);
	public JobFamily getJobFamilyForCode(Integer clientId, String code);
	public JobFamilyEntity getJobFamilyEntity(Integer jobFamilyId);
	public int insertJobFamily(JobFamily jobFamily, Integer auditorId);
	public int updateJobFamily(JobFamily jobFamily, Integer auditorId);
	public int deleteJobFamily(Integer jobFamilyId, Integer noOfChanges, Integer auditorId);
	public int updateJobFamilyDisplayOrder(Integer jobFamilyId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
