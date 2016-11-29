package com.helmet.bean;

import java.util.List;

public class JobSubFamilyEntity extends JobSubFamily {

	private List<JobProfile> jobProfiles;

	public List<JobProfile> getJobProfiles() {
		return jobProfiles;
	}

	public void setJobProfiles(List<JobProfile> jobProfiles) {
		this.jobProfiles = jobProfiles;
	}

}
