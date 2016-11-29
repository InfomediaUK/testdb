package com.helmet.bean;

import java.math.BigDecimal;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;

public class BookingDateExpense extends Base {

	private Integer bookingDateExpenseId;
	private Integer bookingDateId;
	private Integer bookingExpenseId;
	private BigDecimal qty;
	private BigDecimal value;
	private BigDecimal vatValue;
	private String filename;
	private String text;

	public Integer getBookingDateExpenseId() {
		return bookingDateExpenseId;
	}

	public void setBookingDateExpenseId(Integer bookingDateExpenseId) {
		this.bookingDateExpenseId = bookingDateExpenseId;
	}

	public Integer getBookingDateId() {
		return bookingDateId;
	}

	public void setBookingDateId(Integer bookingDateId) {
		this.bookingDateId = bookingDateId;
	}

	public Integer getBookingExpenseId() {
		return bookingExpenseId;
	}

	public void setBookingExpenseId(Integer bookingExpenseId) {
		this.bookingExpenseId = bookingExpenseId;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getVatValue() {
		return vatValue;
	}

	public void setVatValue(BigDecimal vatValue) {
		this.vatValue = vatValue;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
        this.filename = filename;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDocumentUrl() {
		return FileHandler.getInstance().getReceiptFileFolder() + "/" + bookingDateExpenseId + "/" + filename;
	}

	public void load(SqlRowSet rs)
    {
	    super.load(rs);
        setBookingDateExpenseId(rs.getInt("BOOKINGDATEEXPENSEID"));	
        setBookingDateId(rs.getInt("BOOKINGDATEID"));	
        setBookingExpenseId(rs.getInt("BOOKINGEXPENSEID"));	
        setQty(rs.getBigDecimal("QTY"));
		setValue(rs.getBigDecimal("VALUE"));
		setVatValue(rs.getBigDecimal("VATVALUE"));
        setFilename(rs.getString("FILENAME"));
        setText(rs.getString("TEXT"));
	}
	
}
