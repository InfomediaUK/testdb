package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.JobProfileGrade;
import com.helmet.bean.JobProfileGradeUser;

public interface JobProfileGradeDAO {

	public List<JobProfileGradeUser> getJobProfileGradeUsersForJobProfile(Integer jobProfileGroupId);
	public int insertJobProfileGrade(JobProfileGrade jobProfileGrade, Integer auditorId);
	public int deleteJobProfileGrade(Integer jobProfileGradeId, Integer noOfChanges, Integer auditorId);

}
