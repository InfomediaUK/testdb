package com.helmet.bean;

import java.util.List;

public class JobFamilyEntity extends JobFamily {

	private List<JobSubFamily> jobSubFamilies;

	public List<JobSubFamily> getJobSubFamilies() {
		return jobSubFamilies;
	}

	public void setJobSubFamilies(List<JobSubFamily> jobSubFamilies) {
		this.jobSubFamilies = jobSubFamilies;
	}

}
