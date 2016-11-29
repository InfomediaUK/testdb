package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Booking extends Base {

	public final static int BOOKING_STATUS_DRAFT = 0;
	public final static int BOOKING_STATUS_OPEN = 1;
	public final static int BOOKING_STATUS_OFFERED = 2;
	public final static int BOOKING_STATUS_CLOSED = 3;
	public final static int BOOKING_STATUS_COMPLETED = 4;
	public final static int BOOKING_STATUS_INVOICED = 5;
	public final static int BOOKING_STATUS_CANCELLED = 6;

	public final static String[] BOOKING_STATUS_DESCRIPTION_KEYS = {"booking.status.draft",
	                                                                "booking.status.open",
																	"booking.status.offered",
		                                                            "booking.status.closed",
                                                     		        "booking.status.completed",
                                                     		        "booking.status.invoiced",
																	"booking.status.cancelled"};
	
	private Integer bookingId;
	private Integer reasonForRequestId;
	private Integer locationId;
	private Integer jobProfileId;
	private Integer shiftId;
	private Integer bookedById;
	private Integer dressCodeId;
	private Integer status;
	private Boolean singleCandidate = false;
	private Boolean cvRequired = false;
	private Boolean interviewRequired = false;
	private Boolean canProvideAccommodation = false;
	private Boolean carRequired = false;
	private String comments;
	private String bookingReference;
	private Boolean autoFill = false;
	private Boolean autoActivate = false;
	private String reasonForRequestText;
	private String expensesText;
	private BigDecimal rate;
	private BigDecimal value;
	private BigDecimal noOfHours;
	private BigDecimal filledValue;
	private BigDecimal workedValue;
	private BigDecimal workedNoOfHours;
	private Date minBookingDate;
	private Date maxBookingDate;
	private Integer noOfBookingDates;
	private String duration;
	private String cancelText;
	private Integer submittedById;
	private Timestamp submittedTimestamp;
	private Integer cancelledById;
	private Timestamp cancelledTimestamp;
	private String accountContactName;
	private Address accountContactAddress = new Address();
	private String accountContactEmailAddress;
	private String contactName;
	private String contactJobTitle;
	private String contactEmailAddress;
	private String contactTelephoneNumber;
	
	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getBookingReference() {
		return bookingReference;
	}

	public void setBookingReference(String bookingReference) {
		this.bookingReference = bookingReference;
	}

	public Boolean getInterviewRequired() {
		return interviewRequired;
	}

	public void setInterviewRequired(Boolean interviewRequired) {
		this.interviewRequired = interviewRequired;
	}

	public Integer getJobProfileId() {
		return jobProfileId;
	}

	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getReasonForRequestId() {
		return reasonForRequestId;
	}

	public void setReasonForRequestId(Integer reasonForRequestId) {
		this.reasonForRequestId = reasonForRequestId;
	}

	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}

	public Boolean getSingleCandidate() {
		return singleCandidate;
	}

	public void setSingleCandidate(Boolean singleCandidate) {
		this.singleCandidate = singleCandidate;
	}

	public Integer getBookedById() {
		return bookedById;
	}

	public void setBookedById(Integer bookedById) {
		this.bookedById = bookedById;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReasonForRequestText() {
		return reasonForRequestText;
	}

	public void setReasonForRequestText(String reasonForRequestText) {
		this.reasonForRequestText = reasonForRequestText;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getExpensesText() {
		return expensesText;
	}

	public void setExpensesText(String expensesText) {
		this.expensesText = expensesText;
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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
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

	public BigDecimal getFilledValue() {
		return filledValue;
	}

	public void setFilledValue(BigDecimal filledValue) {
		this.filledValue = filledValue;
	}

	public BigDecimal getWorkedValue() {
		return workedValue;
	}

	public void setWorkedValue(BigDecimal workedValue) {
		this.workedValue = workedValue;
	}

	public BigDecimal getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(BigDecimal noOfHours) {
		this.noOfHours = noOfHours;
	}

	public BigDecimal getWorkedNoOfHours() {
		return workedNoOfHours;
	}

	public void setWorkedNoOfHours(BigDecimal workedNoOfHours) {
		this.workedNoOfHours = workedNoOfHours;
	}

	public String getCancelText() {
		return cancelText;
	}

	public void setCancelText(String cancelText) {
		this.cancelText = cancelText;
	}


	public Integer getCancelledById() {
		return cancelledById;
	}

	public void setCancelledById(Integer cancelledById) {
		this.cancelledById = cancelledById;
	}

	public Integer getSubmittedById() {
		return submittedById;
	}

	public void setSubmittedById(Integer submittedById) {
		this.submittedById = submittedById;
	}

	public Timestamp getCancelledTimestamp() {
		return cancelledTimestamp;
	}

	public void setCancelledTimestamp(Timestamp cancelledTimestamp) {
		this.cancelledTimestamp = cancelledTimestamp;
	}

	public Timestamp getSubmittedTimestamp() {
		return submittedTimestamp;
	}

	public void setSubmittedTimestamp(Timestamp submittedTimestamp) {
		this.submittedTimestamp = submittedTimestamp;
	}

	public boolean getCanBeEdited() {
		return status == BOOKING_STATUS_DRAFT;
	}

	public boolean getCanBeEditedWhenSubmitted() {
		return status == BOOKING_STATUS_OPEN ||
			   status == BOOKING_STATUS_OFFERED ||
			   status == BOOKING_STATUS_CLOSED;
	}

	public boolean getCanBeSubmitted() {
		return status == BOOKING_STATUS_DRAFT;
	}

	public String getStatusDescriptionKey() {
		return BOOKING_STATUS_DESCRIPTION_KEYS[status];
	}

	public boolean isDraft() {
		return status == BOOKING_STATUS_DRAFT;
	}

	public boolean isClosed() {
		return status == BOOKING_STATUS_CLOSED;
	}

	public boolean isCompleted() {
		return status == BOOKING_STATUS_COMPLETED;
	}

	public boolean isCancelled() {
		return status == BOOKING_STATUS_CANCELLED;
	}

	public boolean getCanBeCancelled() {
		return isDraft() ||
		       (status == BOOKING_STATUS_OPEN && getSingleCandidate());
	}
	
	public boolean getCanBeExtended() {
		return getSingleCandidate() && isClosed();
	}

	public boolean getCanBeExtendedCompleted() {
		return getSingleCandidate() && isCompleted();
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

	public void load(SqlRowSet rs)
    {
	    super.load(rs);
        setBookingId(rs.getInt("BOOKINGID"));	
        setReasonForRequestId(rs.getInt("REASONFORREQUESTID"));	
        setLocationId(rs.getInt("LOCATIONID"));	
        setJobProfileId(rs.getInt("JOBPROFILEID"));	
        setShiftId(rs.getInt("SHIFTID"));	
        setBookedById(rs.getInt("BOOKEDBYID"));	
        setDressCodeId(rs.getInt("DRESSCODEID"));
        setStatus(rs.getInt("STATUS"));	
        setSingleCandidate(rs.getBoolean("SINGLECANDIDATE"));
        setCvRequired(rs.getBoolean("CVREQUIRED"));
        setInterviewRequired(rs.getBoolean("INTERVIEWREQUIRED"));
        setCanProvideAccommodation(rs.getBoolean("CANPROVIDEACCOMMODATION"));
        setCarRequired(rs.getBoolean("CARREQUIRED"));
        setComments(rs.getString("COMMENTS"));
        setBookingReference(rs.getString("BOOKINGREFERENCE"));
        setAutoFill(rs.getBoolean("AUTOFILL"));
        setAutoActivate(rs.getBoolean("AUTOACTIVATE"));
        setReasonForRequestText(rs.getString("REASONFORREQUESTTEXT"));
        setExpensesText(rs.getString("EXPENSESTEXT"));
        setRate(rs.getBigDecimal("RATE"));
        setValue(rs.getBigDecimal("VALUE"));
        setNoOfHours(rs.getBigDecimal("NOOFHOURS"));
        setFilledValue(rs.getBigDecimal("FILLEDVALUE"));
        setWorkedValue(rs.getBigDecimal("WORKEDVALUE"));
        setWorkedNoOfHours(rs.getBigDecimal("WORKEDNOOFHOURS"));
        setMinBookingDate(rs.getDate("MINBOOKINGDATE"));
        setMaxBookingDate(rs.getDate("MAXBOOKINGDATE"));
        setNoOfBookingDates(rs.getInt("NOOFBOOKINGDATES"));
        setDuration(rs.getString("DURATION"));
        setCancelText(rs.getString("CANCELTEXT"));
        setCancelledById(rs.getInt("CANCELLEDBYID"));	
        setCancelledTimestamp(rs.getTimestamp("CANCELLEDTIMESTAMP"));	
        setSubmittedById(rs.getInt("SUBMITTEDBYID"));	
        setSubmittedTimestamp(rs.getTimestamp("SUBMITTEDTIMESTAMP"));	
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
	
}
