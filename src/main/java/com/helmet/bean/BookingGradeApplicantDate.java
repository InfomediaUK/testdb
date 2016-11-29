package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BookingGradeApplicantDate extends Base {

	public final static int BOOKINGGRADEAPPLICANTDATE_STATUS_DRAFT = 0;

	public final static int BOOKINGGRADEAPPLICANTDATE_STATUS_OPEN = 1;

	public final static int BOOKINGGRADEAPPLICANTDATE_STATUS_OFFERED = 2;

	public final static int BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED = 3;

	public final static int BOOKINGGRADEAPPLICANTDATE_STATUS_REFUSED = 4;

	public final static int BOOKINGGRADEAPPLICANTDATE_STATUS_UNSUCCESSFUL = 5;

	public final static int BOOKINGGRADEAPPLICANTDATE_STATUS_REJECTED = 6;

	public final static int BOOKINGGRADEAPPLICANTDATE_STATUS_WITHDRAWN = 7;

	public final static int BOOKINGGRADEAPPLICANTDATE_STATUS_RENEGOTIATE = 8;

	public final static String[] BOOKINGGRADEAPPLICANTDATE_STATUS_DESCRIPTION_KEYS = {
			"bookingGradeApplicantDate.status.draft",
			"bookingGradeApplicantDate.status.open",
			"bookingGradeApplicantDate.status.offered",
			"bookingGradeApplicantDate.status.filled",
			"bookingGradeApplicantDate.status.refused",
			"bookingGradeApplicantDate.status.unsuccessful",
			"bookingGradeApplicantDate.status.rejected",
			"bookingGradeApplicantDate.status.withdrawn",
			"bookingGradeApplicantDate.status.renegotiate" };

	public final static String BOOKINGGRADEAPPLICANTDATE_APPLICANT_STATUSES = BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED
			+ ""; // csv

	private Integer bookingGradeApplicantDateId;

	private Integer bookingGradeApplicantId;

	private Integer bookingDateId;

	private BigDecimal value;

	private BigDecimal payRateValue;

	private BigDecimal wageRateValue;

	private Integer status;

	private Boolean canDo;

	public Integer getBookingDateId() {
		return bookingDateId;
	}

	public void setBookingDateId(Integer bookingDateId) {
		this.bookingDateId = bookingDateId;
	}

	public Integer getBookingGradeApplicantDateId() {
		return bookingGradeApplicantDateId;
	}

	public void setBookingGradeApplicantDateId(
			Integer bookingGradeApplicantDateId) {
		this.bookingGradeApplicantDateId = bookingGradeApplicantDateId;
	}

	public Integer getBookingGradeApplicantId() {
		return bookingGradeApplicantId;
	}

	public void setBookingGradeApplicantId(Integer bookingGradeApplicantId) {
		this.bookingGradeApplicantId = bookingGradeApplicantId;
	}

	public Boolean getCanDo() {
		return canDo;
	}

	public void setCanDo(Boolean canDo) {
		this.canDo = canDo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public boolean getCanBeOffered() {
		return status == BOOKINGGRADEAPPLICANTDATE_STATUS_OPEN;
	}

	public String getStatusDescriptionKey() {
		return BOOKINGGRADEAPPLICANTDATE_STATUS_DESCRIPTION_KEYS[status];
	}

	public boolean getIsFilled() {
		return status == BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setBookingGradeApplicantDateId(rs.getInt("BOOKINGGRADEAPPLICANTDATEID"));
		setBookingGradeApplicantId(rs.getInt("BOOKINGGRADEAPPLICANTID"));
		setBookingDateId(rs.getInt("BOOKINGDATEID"));
		setValue(rs.getBigDecimal("VALUE"));
		setPayRateValue(rs.getBigDecimal("PAYRATEVALUE"));
		setWageRateValue(rs.getBigDecimal("WAGERATEVALUE"));
		setStatus(rs.getInt("STATUS"));
		setCanDo(rs.getBoolean("CANDO"));
	}

}
