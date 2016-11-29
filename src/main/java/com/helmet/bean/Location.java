package com.helmet.bean;

import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Location extends BaseDisplayOrder {

	private Integer locationId;
	private Integer siteId;
	private String name;
	private String code;
	private String description;
	private String contactName;
	private String contactJobTitle;
	private String contactEmailAddress;
	private String contactTelephoneNumber;
	private Boolean singleCandidateShow = true;
	private Boolean cvRequiredShow = true;
	private Boolean interviewRequiredShow = true;
	private Boolean canProvideAccommodationShow = true;
	private Boolean carRequiredShow = true;
	private Boolean singleCandidateDefault = false;
	private Boolean cvRequiredDefault = false;
	private Boolean interviewRequiredDefault = false;
	private Boolean canProvideAccommodationDefault = false;
	private Boolean carRequiredDefault = false;
	private String commentsDefault;
  private String nhsWard;
  private Time nhsDayStartTime;
  private Time nhsDayEndTime;
  

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactEmailAddress() {
		return contactEmailAddress;
	}

	public void setContactEmailAddress(String contactEmailAddress) {
		this.contactEmailAddress = contactEmailAddress;
	}

	public String getContactJobTitle() {
		return contactJobTitle;
	}

	public void setContactJobTitle(String contactJobTitle) {
		this.contactJobTitle = contactJobTitle;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTelephoneNumber() {
		return contactTelephoneNumber;
	}

	public void setContactTelephoneNumber(String contactTelephoneNumber) {
		this.contactTelephoneNumber = contactTelephoneNumber;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	public Boolean getCanProvideAccommodationDefault() {
		return canProvideAccommodationDefault;
	}

	public void setCanProvideAccommodationDefault(
			Boolean canProvideAccommodationDefault) {
		this.canProvideAccommodationDefault = canProvideAccommodationDefault;
	}

	public Boolean getCanProvideAccommodationShow() {
		return canProvideAccommodationShow;
	}

	public void setCanProvideAccommodationShow(Boolean canProvideAccommodationShow) {
		this.canProvideAccommodationShow = canProvideAccommodationShow;
	}

	public Boolean getCarRequiredDefault() {
		return carRequiredDefault;
	}

	public void setCarRequiredDefault(Boolean carRequiredDefault) {
		this.carRequiredDefault = carRequiredDefault;
	}

	public Boolean getCarRequiredShow() {
		return carRequiredShow;
	}

	public void setCarRequiredShow(Boolean carRequiredShow) {
		this.carRequiredShow = carRequiredShow;
	}

	public String getCommentsDefault() {
		return commentsDefault;
	}

	public void setCommentsDefault(String commentsDefault) {
		this.commentsDefault = commentsDefault;
	}

	public Boolean getCvRequiredDefault() {
		return cvRequiredDefault;
	}

	public void setCvRequiredDefault(Boolean cvRequiredDefault) {
		this.cvRequiredDefault = cvRequiredDefault;
	}

	public Boolean getCvRequiredShow() {
		return cvRequiredShow;
	}

	public void setCvRequiredShow(Boolean cvRequiredShow) {
		this.cvRequiredShow = cvRequiredShow;
	}

	public Boolean getInterviewRequiredDefault() {
		return interviewRequiredDefault;
	}

	public void setInterviewRequiredDefault(Boolean interviewRequiredDefault) {
		this.interviewRequiredDefault = interviewRequiredDefault;
	}

	public Boolean getInterviewRequiredShow() {
		return interviewRequiredShow;
	}

	public void setInterviewRequiredShow(Boolean interviewRequiredShow) {
		this.interviewRequiredShow = interviewRequiredShow;
	}

	public Boolean getSingleCandidateDefault() {
		return singleCandidateDefault;
	}

	public void setSingleCandidateDefault(Boolean singleCandidateDefault) {
		this.singleCandidateDefault = singleCandidateDefault;
	}

	public Boolean getSingleCandidateShow() {
		return singleCandidateShow;
	}

	public void setSingleCandidateShow(Boolean singleCandidateShow) {
		this.singleCandidateShow = singleCandidateShow;
	}

	public String getNhsWard()
  {
    return nhsWard;
  }

  public void setNhsWard(String nhsWard)
  {
    this.nhsWard = nhsWard;
  }

  public Time getNhsDayEndTime()
  {
    return nhsDayEndTime;
  }

  public void setNhsDayEndTime(Time nhsDayEndTime)
  {
    this.nhsDayEndTime = nhsDayEndTime;
  }

  public Time getNhsDayStartTime()
  {
    return nhsDayStartTime;
  }

  public void setNhsDayStartTime(Time nhsDayStartTime)
  {
    this.nhsDayStartTime = nhsDayStartTime;
  }

  public boolean getNhsDayTimesEntered()
  {
    return (nhsDayStartTime != null) && (nhsDayEndTime != null);
  }
  
  public boolean getNhsMatched()
  {
    return name.equals(nhsWard);
  }
  
  public void load(SqlRowSet rs) {
		super.load(rs);
		setLocationId(rs.getInt("LOCATIONID"));
		setSiteId(rs.getInt("SITEID"));
		setName(rs.getString("NAME"));
		setDescription(rs.getString("DESCRIPTION"));
		setContactName(rs.getString("CONTACTNAME"));
		setContactJobTitle(rs.getString("CONTACTJOBTITLE"));
		setContactEmailAddress(rs.getString("CONTACTEMAILADDRESS"));
		setContactTelephoneNumber(rs.getString("CONTACTTELEPHONENUMBER"));
		setCode(rs.getString("CODE"));
        setSingleCandidateShow(rs.getBoolean("SINGLECANDIDATESHOW"));
        setCvRequiredShow(rs.getBoolean("CVREQUIREDSHOW"));
        setInterviewRequiredShow(rs.getBoolean("INTERVIEWREQUIREDSHOW"));
        setCanProvideAccommodationShow(rs.getBoolean("CANPROVIDEACCOMMODATIONSHOW"));
        setCarRequiredShow(rs.getBoolean("CARREQUIREDSHOW"));
        setSingleCandidateDefault(rs.getBoolean("SINGLECANDIDATEDEFAULT"));
        setCvRequiredDefault(rs.getBoolean("CVREQUIREDDEFAULT"));
        setInterviewRequiredDefault(rs.getBoolean("INTERVIEWREQUIREDDEFAULT"));
        setCanProvideAccommodationDefault(rs.getBoolean("CANPROVIDEACCOMMODATIONDEFAULT"));
        setCarRequiredDefault(rs.getBoolean("CARREQUIREDDEFAULT"));
        setCommentsDefault(rs.getString("COMMENTSDEFAULT"));
    setNhsWard(rs.getString("NHSWARD"));
    setNhsDayStartTime(rs.getTime("NHSDAYSTARTTIME"));
    setNhsDayEndTime(rs.getTime("NHSDAYENDTIME"));

	}

}
