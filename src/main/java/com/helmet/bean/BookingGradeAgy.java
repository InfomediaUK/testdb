package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BookingGradeAgy extends BookingGrade {

	private Integer bookingStatus;

	private BigDecimal bookingRate;

	private BigDecimal bookingValue;

	private BigDecimal bookingNoOfHours;

	private String duration;
	
	private String bookingReference;
	
	private String accountContactName;

	private Address accountContactAddress = new Address();
	
	private String accountContactEmailAddress;

	private String contactName;
	
	private String contactJobTitle;
	
	private String contactEmailAddress;
	
	private String contactTelephoneNumber;

	private Boolean singleCandidate;

	private Boolean cvRequired;

	private Boolean interviewRequired;

	private Boolean canProvideAccommodation;

	private Boolean carRequired;

	private Boolean autoFill;

	private Boolean autoActivate;

	private Date minBookingDate;

	private Date maxBookingDate;

	private Integer noOfBookingDates;

	private Integer jobProfileId;

	private String jobProfileCode;

	private String jobProfileName;

	private Integer jobSubFamilyId;

	private String jobSubFamilyCode;

	private String jobSubFamilyName;

	private Integer jobFamilyId;

	private String jobFamilyCode;

	private String jobFamilyName;

	private Integer clientId;

	private String clientCode;

	private String clientName;

	private Integer siteId;

	private String siteCode;

	private String siteName;

	private Integer locationId;

	private String locationCode;

	private String locationName;

	private String locationDescription;

	private Integer shiftId;

	private String shiftCode;

	private String shiftName;

	private Time shiftStartTime;

	private Time shiftEndTime;
	
	private Time shiftBreakStartTime;
	
	private Time shiftBreakEndTime;
	
	private BigDecimal shiftNoOfHours;

	private Integer gradeId;

	private String gradeName;
	
	private Integer dressCodeId;

	private String dressCodeName;
	
	private String comments;

	private String expensesText;

	public Date getMaxBookingDate() {
		return maxBookingDate;
	}

	public void setMaxBookingDate(Date maxBookingDate) {
		this.maxBookingDate = maxBookingDate;
	}

	public Date getMinBookingDate() {
		return minBookingDate;
	}

	public void setMinBookingDate(Date minBookingDate) {
		this.minBookingDate = minBookingDate;
	}

	public Integer getNoOfBookingDates() {
		return noOfBookingDates;
	}

	public void setNoOfBookingDates(Integer noOfBookingDates) {
		this.noOfBookingDates = noOfBookingDates;
	}

	public Boolean getSingleCandidate() {
		return singleCandidate;
	}

	public void setSingleCandidate(Boolean singleCandidate) {
		this.singleCandidate = singleCandidate;
	}

	public Integer getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(Integer bookingStatus) {
		this.bookingStatus = bookingStatus;
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

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
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

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Boolean getCanProvideAccommodation() {
		return canProvideAccommodation;
	}

	public void setCanProvideAccommodation(Boolean canProvideAccommodation) {
		this.canProvideAccommodation = canProvideAccommodation;
	}

	public Boolean getCarRequired() {
		return carRequired;
	}

	public void setCarRequired(Boolean carRequired) {
		this.carRequired = carRequired;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Boolean getCvRequired() {
		return cvRequired;
	}

	public void setCvRequired(Boolean cvRequired) {
		this.cvRequired = cvRequired;
	}

	public Integer getDressCodeId() {
		return dressCodeId;
	}

	public void setDressCodeId(Integer dressCodeId) {
		this.dressCodeId = dressCodeId;
	}

	public String getDressCodeName() {
		return dressCodeName;
	}

	public void setDressCodeName(String dressCodeName) {
		this.dressCodeName = dressCodeName;
	}

	public String getExpensesText() {
		return expensesText;
	}

	public void setExpensesText(String expensesText) {
		this.expensesText = expensesText;
	}

	public Boolean getInterviewRequired() {
		return interviewRequired;
	}

	public void setInterviewRequired(Boolean interviewRequired) {
		this.interviewRequired = interviewRequired;
	}

	
	public Time getShiftBreakEndTime() {
		return shiftBreakEndTime;
	}

	public void setShiftBreakEndTime(Time shiftBreakEndTime) {
		this.shiftBreakEndTime = shiftBreakEndTime;
	}

	public Time getShiftBreakStartTime() {
		return shiftBreakStartTime;
	}

	public void setShiftBreakStartTime(Time shiftBreakStartTime) {
		this.shiftBreakStartTime = shiftBreakStartTime;
	}

	public Time getShiftEndTime() {
		return shiftEndTime;
	}

	public void setShiftEndTime(Time shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	public BigDecimal getShiftNoOfHours() {
		return shiftNoOfHours;
	}

	public void setShiftNoOfHours(BigDecimal shiftNoOfHours) {
		this.shiftNoOfHours = shiftNoOfHours;
	}

	public Time getShiftStartTime() {
		return shiftStartTime;
	}

	public void setShiftStartTime(Time shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}

	public Boolean getAutoFill() {
		return autoFill;
	}

	public void setAutoFill(Boolean autoFill) {
		this.autoFill = autoFill;
	}

	public Boolean getAutoActivate() {
		return autoActivate;
	}

	public void setAutoActivate(Boolean autoActivate) {
		this.autoActivate = autoActivate;
	}

	public BigDecimal getBookingRate() {
		return bookingRate;
	}

	public void setBookingRate(BigDecimal bookingRate) {
		this.bookingRate = bookingRate;
	}

	public BigDecimal getBookingValue() {
		return bookingValue;
	}

	public void setBookingValue(BigDecimal bookingValue) {
		this.bookingValue = bookingValue;
	}

	public BigDecimal getBookingNoOfHours() {
		return bookingNoOfHours;
	}

	public void setBookingNoOfHours(BigDecimal bookingNoOfHours) {
		this.bookingNoOfHours = bookingNoOfHours;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getBookingStatusDescriptionKey() {
		return Booking.BOOKING_STATUS_DESCRIPTION_KEYS[bookingStatus];
	}

	public Address getAccountContactAddress() {
		return accountContactAddress;
	}

	public void setAccountContactAddress(Address accountContactAddress) {
		this.accountContactAddress = accountContactAddress;
	}

	public String getAccountContactName() {
		return accountContactName;
	}

	public void setAccountContactName(String accountContactName) {
		this.accountContactName = accountContactName;
	}	

	public String getAccountContactEmailAddress() {
		return accountContactEmailAddress;
	}

	public void setAccountContactEmailAddress(String accountContactEmailAddress) {
		this.accountContactEmailAddress = accountContactEmailAddress;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getBookingReference() {
		return bookingReference;
	}

	public void setBookingReference(String bookingReference) {
		this.bookingReference = bookingReference;
	}

  public boolean getCanBeCopied() 
  {
    return bookingStatus == Booking.BOOKING_STATUS_OPEN && getSingleCandidate();
  }

	public boolean getCanBeCancelled() {
		return bookingStatus == Booking.BOOKING_STATUS_OPEN && 
		       getSingleCandidate();
	}

	public boolean getCanBeExtended() {
		return getSingleCandidate() && bookingStatus == Booking.BOOKING_STATUS_CLOSED;
	}

	public boolean getCanBeExtendedCompleted() {
		return getSingleCandidate() && bookingStatus == Booking.BOOKING_STATUS_COMPLETED;
	}
	
  public Boolean getUndefinedShift()
  {
    return shiftStartTime == null ? false : shiftStartTime.equals(shiftEndTime);
  }

	public void load(SqlRowSet rs) {
		super.load(rs);
		setBookingRate(rs.getBigDecimal("BOOKINGRATE"));
		setBookingValue(rs.getBigDecimal("BOOKINGVALUE"));
		setBookingNoOfHours(rs.getBigDecimal("BOOKINGNOOFHOURS"));
		setBookingStatus(rs.getInt("BOOKINGSTATUS"));
		setSingleCandidate(rs.getBoolean("SINGLECANDIDATE"));
		setCvRequired(rs.getBoolean("CVREQUIRED"));
		setInterviewRequired(rs.getBoolean("INTERVIEWREQUIRED"));
		setCanProvideAccommodation(rs.getBoolean("CANPROVIDEACCOMMODATION"));
		setCarRequired(rs.getBoolean("CARREQUIRED"));
		setAutoFill(rs.getBoolean("AUTOFILL"));
		setAutoActivate(rs.getBoolean("AUTOACTIVATE"));
		setComments(rs.getString("COMMENTS"));
		setExpensesText(rs.getString("EXPENSESTEXT"));
		setMinBookingDate(rs.getDate("MINBOOKINGDATE"));
		setMaxBookingDate(rs.getDate("MAXBOOKINGDATE"));
		setNoOfBookingDates(rs.getInt("NOOFBOOKINGDATES"));
		setJobProfileId(rs.getInt("JOBPROFILEID"));
		setJobProfileCode(rs.getString("JOBPROFILECODE"));
		setJobProfileName(rs.getString("JOBPROFILENAME"));
		setJobSubFamilyId(rs.getInt("JOBSUBFAMILYID"));
		setJobSubFamilyCode(rs.getString("JOBSUBFAMILYCODE"));
		setJobSubFamilyName(rs.getString("JOBSUBFAMILYNAME"));
		setJobFamilyId(rs.getInt("JOBFAMILYID"));
		setJobFamilyCode(rs.getString("JOBFAMILYCODE"));
		setJobFamilyName(rs.getString("JOBFAMILYNAME"));
		setClientId(rs.getInt("CLIENTID"));
		setClientCode(rs.getString("CLIENTCODE"));
		setClientName(rs.getString("CLIENTNAME"));
		setSiteId(rs.getInt("SITEID"));
		setSiteCode(rs.getString("SITECODE"));
		setSiteName(rs.getString("SITENAME"));
		setLocationId(rs.getInt("LOCATIONID"));
		setLocationCode(rs.getString("LOCATIONCODE"));
		setLocationName(rs.getString("LOCATIONNAME"));
		setLocationDescription(rs.getString("LOCATIONDESCRIPTION"));
		setShiftId(rs.getInt("SHIFTID"));
		setShiftCode(rs.getString("SHIFTCODE"));
		setShiftName(rs.getString("SHIFTNAME"));
		setShiftStartTime(rs.getTime("SHIFTSTARTTIME"));
		setShiftEndTime(rs.getTime("SHIFTENDTIME"));
		setShiftBreakStartTime(rs.getTime("SHIFTBREAKSTARTTIME"));
		setShiftBreakEndTime(rs.getTime("SHIFTBREAKENDTIME"));
		setShiftNoOfHours(rs.getBigDecimal("SHIFTNOOFHOURS"));
		setGradeId(rs.getInt("GRADEID"));
		setGradeName(rs.getString("GRADENAME"));
		setDressCodeId(rs.getInt("DRESSCODEID"));
		setDressCodeName(rs.getString("DRESSCODENAME"));
        setDuration(rs.getString("DURATION"));
        setBookingReference(rs.getString("BOOKINGREFERENCE"));
        setAccountContactName(rs.getString("ACCOUNTCONTACTNAME"));		
        getAccountContactAddress().setAddress1(rs.getString("ACCOUNTCONTACTADDRESS1"));		
        getAccountContactAddress().setAddress2(rs.getString("ACCOUNTCONTACTADDRESS2"));		
        getAccountContactAddress().setAddress3(rs.getString("ACCOUNTCONTACTADDRESS3"));		
        getAccountContactAddress().setAddress4(rs.getString("ACCOUNTCONTACTADDRESS4"));		
        getAccountContactAddress().setPostalCode(rs.getString("ACCOUNTCONTACTPOSTALCODE"));		
        getAccountContactAddress().setCountryId(rs.getInt("ACCOUNTCONTACTCOUNTRYID"));		
        setAccountContactEmailAddress(rs.getString("ACCOUNTCONTACTEMAILADDRESS"));		
		setContactName(rs.getString("CONTACTNAME"));
		setContactJobTitle(rs.getString("CONTACTJOBTITLE"));
		setContactEmailAddress(rs.getString("CONTACTEMAILADDRESS"));
		setContactTelephoneNumber(rs.getString("CONTACTTELEPHONENUMBER"));
	}
  // match method of same name in com.helmet.application.BookingReportGroup
  public String getReportGroupKey()
  {
    return clientId + "," + siteId + "," + locationId; 
  }

}
