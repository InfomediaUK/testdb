package com.helmet.bean;

import java.util.List;

public class ClientUserEntity extends ClientUser {

	private List<SiteUser> sites;

	private List<Manager> managers;

	private List<JobFamily> jobFamilies;

	private List<JobProfileGroup> jobProfileGroups;

	private List<Grade> grades;

	private List<ClientAgencyUser> clientAgencies;

	private List<AgencyUser> agencies;

	private List<ReasonForRequest> reasonForRequests;

	private List<PublicHoliday> publicHolidays;

//	private List<Uplift> uplifts;

	private List<MgrAccessGroup> mgrAccessGroups;

	private List<ClientReEnterPwdUser> clientReEnterPwds;

	private List<ReEnterPwd> reEnterPwds;

	public List<SiteUser> getSites() {
		return sites;
	}

	public void setSites(List<SiteUser> sites) {
		this.sites = sites;
	}

	public List<Manager> getManagers() {
		return managers;
	}

	public void setManagers(List<Manager> managers) {
		this.managers = managers;
	}

	public List<JobFamily> getJobFamilies() {
		return jobFamilies;
	}

	public void setJobFamilies(List<JobFamily> jobFamilies) {
		this.jobFamilies = jobFamilies;
	}

	public List<JobProfileGroup> getJobProfileGroups() {
		return jobProfileGroups;
	}

	public void setJobProfileGroups(List<JobProfileGroup> jobProfileGroups) {
		this.jobProfileGroups = jobProfileGroups;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public List<ClientAgencyUser> getClientAgencies() {
		return clientAgencies;
	}

	public void setClientAgencies(List<ClientAgencyUser> clientAgencies) {
		this.clientAgencies = clientAgencies;
	}

	public List<AgencyUser> getAgencies() {
		return agencies;
	}

	public void setAgencies(List<AgencyUser> agencies) {
		this.agencies = agencies;
	}

	public List<ReasonForRequest> getReasonForRequests() {
		return reasonForRequests;
	}

	public void setReasonForRequests(List<ReasonForRequest> reasonForRequests) {
		this.reasonForRequests = reasonForRequests;
	}

	public List<PublicHoliday> getPublicHolidays() {
		return publicHolidays;
	}

	public void setPublicHolidays(List<PublicHoliday> publicHolidays) {
		this.publicHolidays = publicHolidays;
	}

//	public List<Uplift> getUplifts() {
//		return uplifts;
//	}
//
//	public void setUplifts(List<Uplift> uplifts) {
//		this.uplifts = uplifts;
//	}

	public List<MgrAccessGroup> getMgrAccessGroups() {
		return mgrAccessGroups;
	}

	public void setMgrAccessGroups(List<MgrAccessGroup> mgrAccessGroups) {
		this.mgrAccessGroups = mgrAccessGroups;
	}

	public List<ClientReEnterPwdUser> getClientReEnterPwds() {
		return clientReEnterPwds;
	}

	public void setClientReEnterPwds(List<ClientReEnterPwdUser> clientReEnterPwds) {
		this.clientReEnterPwds = clientReEnterPwds;
	}

	public List<ReEnterPwd> getReEnterPwds() {
		return reEnterPwds;
	}

	public void setReEnterPwds(List<ReEnterPwd> reEnterPwds) {
		this.reEnterPwds = reEnterPwds;
	}

	
}
