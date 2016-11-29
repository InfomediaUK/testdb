package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BookingUser extends Booking {

	private String reasonForRequestName;

	private String reasonForRequestCode;

	private Integer clientId;

	private String clientName;

	private String clientCode;

	private Integer siteId;

	private String siteName;

	private String siteCode;

	private String locationName;

	private String locationCode;

	private String locationDescription;

	private Integer jobFamilyId;

	private String jobFamilyName;

	private String jobFamilyCode;

	private Integer jobSubFamilyId;

	private String jobSubFamilyName;

	private String jobSubFamilyCode;

	private String jobProfileName;

	private String jobProfileCode;

	private BigDecimal jobProfileRate;

	private BigDecimal locationJobProfileRate;

	private String shiftName;

	private String shiftCode;

	private String shiftDescription;

	private Time shiftStartTime;

	private Time shiftEndTime;

	private Time shiftBreakStartTime;

	private Time shiftBreakEndTime;

	private BigDecimal shiftNoOfHours;

	private String bookedByFirstName;

	private String bookedByLastName;

	private String bookedByEmailAddress;

	private String dressCodeName;

	private Date minBookingDate;

	private Date maxBookingDate;

	private Integer noOfBookingDates;

	private String accountContactCountryName;

	private String cancelledByLogin;

	private String cancelledByFirstName;

	private String cancelledByLastName;

	private String submittedByLogin;

	private String submittedByFirstName;

	private String submittedByLastName;

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

	public String getJobProfileName() {
		return jobProfileName;
	}

	public void setJobProfileName(String jobProfileName) {
		this.jobProfileName = jobProfileName;
	}

	public BigDecimal getJobProfileRate() {
		return jobProfileRate;
	}

	public void setJobProfileRate(BigDecimal jobProfileRate) {
		this.jobProfileRate = jobProfileRate;
	}

	public BigDecimal getLocationJobProfileRate() {
		return locationJobProfileRate;
	}

	public void setLocationJobProfileRate(BigDecimal locationJobProfileRate) {
		this.locationJobProfileRate = locationJobProfileRate;
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

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getReasonForRequestCode() {
		return reasonForRequestCode;
	}

	public void setReasonForRequestCode(String reasonForRequestCode) {
		this.reasonForRequestCode = reasonForRequestCode;
	}

	public String getReasonForRequestName() {
		return reasonForRequestName;
	}

	public void setReasonForRequestName(String reasonForRequestName) {
		this.reasonForRequestName = reasonForRequestName;
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

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public String getShiftDescription() {
		return shiftDescription;
	}

	public void setShiftDescription(String shiftDescription) {
		this.shiftDescription = shiftDescription;
	}

	public Time getShiftEndTime() {
		return shiftEndTime;
	}

	public void setShiftEndTime(Time shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
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

	public String getDressCodeName() {
		return dressCodeName;
	}

	public void setDressCodeName(String dressCodeName) {
		this.dressCodeName = dressCodeName;
	}

	public String getBookedByEmailAddress() {
		return bookedByEmailAddress;
	}

	public void setBookedByEmailAddress(String bookedByEmailAddress) {
		this.bookedByEmailAddress = bookedByEmailAddress;
	}

	public String getBookedByFirstName() {
		return bookedByFirstName;
	}

	public void setBookedByFirstName(String bookedByFirstName) {
		this.bookedByFirstName = bookedByFirstName;
	}

	public String getBookedByLastName() {
		return bookedByLastName;
	}

	public void setBookedByLastName(String bookedByLastName) {
		this.bookedByLastName = bookedByLastName;
	}

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

	public String getCancelledByFirstName() {
		return cancelledByFirstName;
	}

	public void setCancelledByFirstName(String cancelledByFirstName) {
		this.cancelledByFirstName = cancelledByFirstName;
	}

	public String getCancelledByLastName() {
		return cancelledByLastName;
	}

	public void setCancelledByLastName(String cancelledByLastName) {
		this.cancelledByLastName = cancelledByLastName;
	}

	public String getCancelledByLogin() {
		return cancelledByLogin;
	}

	public void setCancelledByLogin(String cancelledByLogin) {
		this.cancelledByLogin = cancelledByLogin;
	}

	public String getSubmittedByFirstName() {
		return submittedByFirstName;
	}

	public void setSubmittedByFirstName(String submittedByFirstName) {
		this.submittedByFirstName = submittedByFirstName;
	}

	public String getSubmittedByLastName() {
		return submittedByLastName;
	}

	public void setSubmittedByLastName(String submittedByLastName) {
		this.submittedByLastName = submittedByLastName;
	}

	public String getSubmittedByLogin() {
		return submittedByLogin;
	}

	public void setSubmittedByLogin(String submittedByLogin) {
		this.submittedByLogin = submittedByLogin;
	}

	public String getAccountContactCountryName() {
		return accountContactCountryName;
	}

	public void setAccountContactCountryName(String accountContactCountryName) {
		this.accountContactCountryName = accountContactCountryName;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setReasonForRequestName(rs.getString("REASONFORREQUESTNAME"));
		setReasonForRequestCode(rs.getString("REASONFORREQUESTCODE"));
		setClientId(rs.getInt("CLIENTID"));
		setClientName(rs.getString("CLIENTNAME"));
		setClientCode(rs.getString("CLIENTCODE"));
		setSiteId(rs.getInt("SITEID"));
		setSiteName(rs.getString("SITENAME"));
		setSiteCode(rs.getString("SITECODE"));
		setLocationName(rs.getString("LOCATIONNAME"));
		setLocationCode(rs.getString("LOCATIONCODE"));
		setLocationDescription(rs.getString("LOCATIONDESCRIPTION"));
		setJobFamilyId(rs.getInt("JOBFAMILYID"));
		setJobFamilyName(rs.getString("JOBFAMILYNAME"));
		setJobFamilyCode(rs.getString("JOBFAMILYCODE"));
		setJobSubFamilyId(rs.getInt("JOBSUBFAMILYID"));
		setJobSubFamilyName(rs.getString("JOBSUBFAMILYNAME"));
		setJobSubFamilyCode(rs.getString("JOBSUBFAMILYCODE"));
		setJobProfileName(rs.getString("JOBPROFILENAME"));
		setJobProfileCode(rs.getString("JOBPROFILECODE"));
		setJobProfileRate(rs.getBigDecimal("JOBPROFILERATE"));
		setLocationJobProfileRate(rs.getBigDecimal("LOCATIONJOBPROFILERATE"));
		setShiftCode(rs.getString("SHIFTCODE"));
		setShiftName(rs.getString("SHIFTNAME"));
		setShiftDescription(rs.getString("SHIFTDESCRIPTION"));
		setShiftStartTime(rs.getTime("SHIFTSTARTTIME"));
		setShiftEndTime(rs.getTime("SHIFTENDTIME"));
		setShiftBreakStartTime(rs.getTime("SHIFTBREAKSTARTTIME"));
		setShiftBreakEndTime(rs.getTime("SHIFTBREAKENDTIME"));
		setShiftNoOfHours(rs.getBigDecimal("SHIFTNOOFHOURS"));
		setBookedByFirstName(rs.getString("BOOKEDBYFIRSTNAME"));
		setBookedByLastName(rs.getString("BOOKEDBYLASTNAME"));
		setBookedByEmailAddress(rs.getString("BOOKEDBYEMAILADDRESS"));
		setDressCodeName(rs.getString("DRESSCODENAME"));
		setMinBookingDate(rs.getDate("MINBOOKINGDATE"));
		setMaxBookingDate(rs.getDate("MAXBOOKINGDATE"));
		setNoOfBookingDates(rs.getInt("NOOFBOOKINGDATES"));

		setAccountContactCountryName(rs.getString("ACCOUNTCONTACTCOUNTRYNAME"));

		setCancelledByLogin(rs.getString("CANCELLEDBYLOGIN"));
		setCancelledByFirstName(rs.getString("CANCELLEDBYFIRSTNAME"));
		setCancelledByLastName(rs.getString("CANCELLEDBYLASTNAME"));
		setSubmittedByLogin(rs.getString("SUBMITTEDBYLOGIN"));
		setSubmittedByFirstName(rs.getString("SUBMITTEDBYFIRSTNAME"));
		setSubmittedByLastName(rs.getString("SUBMITTEDBYLASTNAME"));

	}

}
