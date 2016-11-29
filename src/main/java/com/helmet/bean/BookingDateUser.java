package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BookingDateUser extends BookingDate {

	private BigDecimal rrpRate;
	
	private String bookingReference;
	
	private Integer jobProfileId;

	private String jobProfileCode;

	private String jobProfileName;

	private Integer jobSubFamilyId;

	private String jobSubFamilyCode;

	private String jobSubFamilyName;

	private Integer jobFamilyId;

	private String jobFamilyCode;

	private String jobFamilyName;

	private Integer locationId;

	private String locationCode;

	private String locationName;

	private Integer siteId;

	private String siteCode;

	private String siteName;

	private Integer clientId;

	private String clientCode;

	private String clientName;

  private Boolean clientUpliftCommission = false;

  private BigDecimal chargeRateValue;

	private BigDecimal payRateValue;

	private BigDecimal wageRateValue;

	public String getJobProfileCode() {
		return jobProfileCode;
	}


	public void setJobProfileCode(String jobProfileCode) {
		this.jobProfileCode = jobProfileCode;
	}


	public Integer getJobProfileId() {
		return jobProfileId;
	}


	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}


	public String getJobProfileName() {
		return jobProfileName;
	}


	public void setJobProfileName(String jobProfileName) {
		this.jobProfileName = jobProfileName;
	}


	public String getLocationCode() {
		return locationCode;
	}


	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}


	public Integer getLocationId() {
		return locationId;
	}


	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	public String getSiteCode() {
		return siteCode;
	}


	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}


	public Integer getSiteId() {
		return siteId;
	}


	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}


	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public BigDecimal getRrpRate() {
		return rrpRate;
	}

	public void setRrpRate(BigDecimal rrpRate) {
		this.rrpRate = rrpRate;
	}

	public String getBookingReference() {
		return bookingReference;
	}

	public void setBookingReference(String bookingReference) {
		this.bookingReference = bookingReference;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Boolean getClientUpliftCommission()
  {
    return clientUpliftCommission;
  }


  public void setClientUpliftCommission(Boolean clientUpliftCommission)
  {
    this.clientUpliftCommission = clientUpliftCommission;
  }


  public String getJobFamilyCode() {
		return jobFamilyCode;
	}

	public void setJobFamilyCode(String jobFamilyCode) {
		this.jobFamilyCode = jobFamilyCode;
	}

	public Integer getJobFamilyId() {
		return jobFamilyId;
	}

	public void setJobFamilyId(Integer jobFamilyId) {
		this.jobFamilyId = jobFamilyId;
	}

	public String getJobFamilyName() {
		return jobFamilyName;
	}

	public void setJobFamilyName(String jobFamilyName) {
		this.jobFamilyName = jobFamilyName;
	}

	public String getJobSubFamilyCode() {
		return jobSubFamilyCode;
	}

	public void setJobSubFamilyCode(String jobSubFamilyCode) {
		this.jobSubFamilyCode = jobSubFamilyCode;
	}

	public Integer getJobSubFamilyId() {
		return jobSubFamilyId;
	}

	public void setJobSubFamilyId(Integer jobSubFamilyId) {
		this.jobSubFamilyId = jobSubFamilyId;
	}

	public String getJobSubFamilyName() {
		return jobSubFamilyName;
	}

	public void setJobSubFamilyName(String jobSubFamilyName) {
		this.jobSubFamilyName = jobSubFamilyName;
	}

	public BigDecimal getChargeRateValue() {
		return chargeRateValue;
	}

	public void setChargeRateValue(BigDecimal chargeRateValue) {
		this.chargeRateValue = chargeRateValue;
	}

	public BigDecimal getPayRateValue() {
		return payRateValue;
	}

	public void setPayRateValue(BigDecimal payRateValue) {
		this.payRateValue = payRateValue;
	}

	public BigDecimal getWageRateValue() {
		return wageRateValue;
	}

	public void setWageRateValue(BigDecimal wageRateValue) {
		this.wageRateValue = wageRateValue;
	}

	public void load(SqlRowSet rs)
	{
	    super.load(rs);

	    setJobProfileId(rs.getInt("JOBPROFILEID"));
		setJobProfileCode(rs.getString("JOBPROFILECODE"));
		setJobProfileName(rs.getString("JOBPROFILENAME"));
	    setJobSubFamilyId(rs.getInt("JOBSUBFAMILYID"));
		setJobSubFamilyCode(rs.getString("JOBSUBFAMILYCODE"));
		setJobSubFamilyName(rs.getString("JOBSUBFAMILYNAME"));
	    setJobFamilyId(rs.getInt("JOBFAMILYID"));
		setJobFamilyCode(rs.getString("JOBFAMILYCODE"));
		setJobFamilyName(rs.getString("JOBFAMILYNAME"));
		setLocationId(rs.getInt("LOCATIONID"));
		setLocationCode(rs.getString("LOCATIONCODE"));
		setLocationName(rs.getString("LOCATIONNAME"));
		setSiteId(rs.getInt("SITEID"));
		setSiteCode(rs.getString("SITECODE"));
		setSiteName(rs.getString("SITENAME"));
		setClientId(rs.getInt("CLIENTID"));
		setClientCode(rs.getString("CLIENTCODE"));
		setClientName(rs.getString("CLIENTNAME"));
    setClientUpliftCommission(rs.getBoolean("CLIENTUPLIFTCOMMISSION"));
		setRrpRate(rs.getBigDecimal("RRPRATE"));
		setBookingReference(rs.getString("BOOKINGREFERENCE"));
	}

  public String getReportGroupKey()
  {
    return clientId + "," + siteId + "," + locationId; 
  }

}
