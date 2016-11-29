package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JobProfileGrade extends Base {

	private Integer jobProfileGradeId;

	private Integer jobProfileId;

	private Integer gradeId;

	public Integer getJobProfileGradeId() {
		return jobProfileGradeId;
	}

	public void setJobProfileGradeId(Integer jobProfileGradeId) {
		this.jobProfileGradeId = jobProfileGradeId;
	}


	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	
	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setJobProfileGradeId(rs.getInt("JOBPROFILEGRADEID"));
		setJobProfileId(rs.getInt("JOBPROFILEID"));
		setGradeId(rs.getInt("GRADEID"));
	}

}
