package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JobProfile extends BaseDisplayOrder {

	private Integer jobProfileId;
	private Integer jobSubFamilyId;
	private String name;
	private String code;
	private Boolean autoFill = false;
	private String documentURL;
	private BigDecimal rate = new BigDecimal(0);
  // NEW -->
  private String nhsAssignment;
  // <-- NEW

	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	public Integer getJobSubFamilyId() {
		return jobSubFamilyId;
	}

	public void setJobSubFamilyId(Integer jobSubFamilyId) {
		this.jobSubFamilyId = jobSubFamilyId;
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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getDocumentURL() {
		return documentURL;
	}

	public void setDocumentURL(String documentURL) {
		this.documentURL = documentURL;
	}

	public Boolean getAutoFill() {
		return autoFill;
	}

	public void setAutoFill(Boolean autoFill) {
		this.autoFill = autoFill;
	}

  public String getNhsAssignment()
  {
    return nhsAssignment;
  }

  public void setNhsAssignment(String nhsAssignment)
  {
    this.nhsAssignment = nhsAssignment;
  }

	public void load(SqlRowSet rs)
    {
	    super.load(rs);
        setJobProfileId(rs.getInt("JOBPROFILEID"));		
        setJobSubFamilyId(rs.getInt("JOBSUBFAMILYID"));		
        setName(rs.getString("NAME"));		
        setCode(rs.getString("CODE"));		
        setRate(rs.getBigDecimal("RATE"));		
        setAutoFill(rs.getBoolean("AUTOFILL"));
        setDocumentURL(rs.getString("DOCUMENTURL"));		
        setNhsAssignment(rs.getString("NHSASSIGNMENT"));
	}
	
}
