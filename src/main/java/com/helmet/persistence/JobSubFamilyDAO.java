package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.JobFamily;
import com.helmet.bean.JobSubFamily;
import com.helmet.bean.JobSubFamilyEntity;

public interface JobSubFamilyDAO {

	public List<JobSubFamily> getJobSubFamiliesForJobFamily(Integer jobFamilyId, boolean showOnlyActive);
	public JobSubFamily getJobSubFamily(Integer jobSubFamilyId);
	public JobSubFamily getJobSubFamilyForName(Integer jobFamilyId, String name);
	public JobSubFamily getJobSubFamilyForCode(Integer jobFamilyId, String code);
	public JobSubFamilyEntity getJobSubFamilyEntity(Integer jobSubFamilyId);
	public int insertJobSubFamily(JobSubFamily jobSubFamily, Integer auditorId);
	public int updateJobSubFamily(JobSubFamily jobSubFamily, Integer auditorId);
	public int deleteJobSubFamily(Integer jobSubFamilyId, Integer noOfChanges, Integer auditorId);
	public int updateJobSubFamilyDisplayOrder(Integer jobSubFamilyId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
