package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JobSubFamily extends BaseDisplayOrder {

	private Integer jobSubFamilyId;

	private Integer jobFamilyId;

	private String name;

	private String code;

	public Integer getJobSubFamilyId() {
		return jobSubFamilyId;
	}

	public void setJobSubFamilyId(Integer jobSubFamilyId) {
		this.jobSubFamilyId = jobSubFamilyId;
	}

	public Integer getJobFamilyId() {
		return jobFamilyId;
	}

	public void setJobFamilyId(Integer jobFamilyId) {
		this.jobFamilyId = jobFamilyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void load(SqlRowSet rs)
    {
	    super.load(rs);
        setJobSubFamilyId(rs.getInt("JOBSUBFAMILYID"));		
        setJobFamilyId(rs.getInt("JOBFAMILYID"));		
        setName(rs.getString("NAME"));		
        setCode(rs.getString("CODE"));		
	}
	
}
