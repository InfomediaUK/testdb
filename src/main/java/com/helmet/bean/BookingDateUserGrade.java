package com.helmet.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.Utilities;

public class BookingDateUserGrade extends BookingDateUserLong {

    BigDecimal divisor100 = new BigDecimal(100);

    private Integer bookingGradeId;
	
	private BigDecimal bookingGradePayRate;
	
	private BigDecimal bookingGradeWtdPercentage;

	private BigDecimal bookingGradeNiPercentage;

	private BigDecimal bookingGradeRate;

	private BigDecimal bookingRate;

	private Integer gradeId;
	
	private String gradeName;
	
	transient private BigDecimal bookingGradeWtdRate;

	transient private BigDecimal bookingGradeNiRate;
	

	public Integer getBookingGradeId() {
		return bookingGradeId;
	}


	public void setBookingGradeId(Integer bookingGradeId) {
		this.bookingGradeId = bookingGradeId;
	}


	public BigDecimal getBookingGradeRate() {
		return bookingGradeRate;
	}


	public void setBookingGradeRate(BigDecimal bookingGradeRate) {
		this.bookingGradeRate = bookingGradeRate;
	}


	public BigDecimal getBookingGradePayRate() {
		return bookingGradePayRate;
	}


	public void setBookingGradePayRate(BigDecimal bookingGradePayRate) {
		this.bookingGradePayRate = bookingGradePayRate;
	}


	public BigDecimal getBookingRate() {
		return bookingRate;
	}


	public void setBookingRate(BigDecimal bookingRate) {
		this.bookingRate = bookingRate;
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


	public BigDecimal getBookingGradeNiPercentage() {
		return bookingGradeNiPercentage;
	}


	public void setBookingGradeNiPercentage(BigDecimal bookingGradeNiPercentage) {
		this.bookingGradeNiPercentage = bookingGradeNiPercentage;
	}


	public BigDecimal getBookingGradeWtdPercentage() {
		return bookingGradeWtdPercentage;
	}


	public void setBookingGradeWtdPercentage(BigDecimal bookingGradeWtdPercentage) {
		this.bookingGradeWtdPercentage = bookingGradeWtdPercentage;
	}


	public BigDecimal getBookingGradeNiRate() {
		return bookingGradeNiRate;
	}


	public void setBookingGradeNiRate(BigDecimal bookingGradeNiRate) {
		this.bookingGradeNiRate = bookingGradeNiRate;
	}


	public BigDecimal getBookingGradeWtdRate() {
		return bookingGradeWtdRate;
	}


	public void setBookingGradeWtdRate(BigDecimal bookingGradeWtdRate) {
		this.bookingGradeWtdRate = bookingGradeWtdRate;
	}

    public BigDecimal getBookingGradeWageRate() {
        return getBookingGradePayRate().add(getBookingGradeWtdRate()).add(getBookingGradeNiRate());
    }


	public void load(SqlRowSet rs)
	{
	    super.load(rs);
	    setBookingGradeId(rs.getInt("BOOKINGGRADEID"));
	    setBookingGradePayRate(rs.getBigDecimal("BOOKINGGRADEPAYRATE"));
	    setBookingGradeWtdPercentage(rs.getBigDecimal("BOOKINGGRADEWTDPERCENTAGE"));
	    setBookingGradeNiPercentage(rs.getBigDecimal("BOOKINGGRADENIPERCENTAGE"));
	    setBookingGradeRate(rs.getBigDecimal("BOOKINGGRADERATE"));
	    setBookingRate(rs.getBigDecimal("BOOKINGRATE"));
		setGradeId(rs.getInt("GRADEID"));
		setGradeName(rs.getString("GRADENAME"));
		
 		setBookingGradeWtdRate(Utilities.round(getBookingGradePayRate().multiply(getBookingGradeWtdPercentage().divide(divisor100, 5, RoundingMode.HALF_UP))));
 		setBookingGradeNiRate(Utilities.round((getBookingGradePayRate().add(getBookingGradeWtdRate())).multiply(getBookingGradeNiPercentage().divide(divisor100, 5, RoundingMode.HALF_UP))));

		setChargeRateValue((getValue().multiply(getBookingGradeRate().divide(getBookingRate(), 5, RoundingMode.HALF_UP))));
		setPayRateValue((getValue().multiply(getBookingGradePayRate().divide(getBookingRate(), 5, RoundingMode.HALF_UP))));
		setWageRateValue((getValue().multiply(getBookingGradeWageRate().divide(getBookingRate(), 5, RoundingMode.HALF_UP))));
 		
	}


}
