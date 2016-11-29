package com.helmet.bean;

import java.sql.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class PublicHoliday extends Base {

	private Integer publicHolidayId;

	private Integer clientId;

	private String name;

	private Date phDate;

	public Integer getPublicHolidayId() {
		return publicHolidayId;
	}

	public void setPublicHolidayId(Integer publicHolidayId) {
		this.publicHolidayId = publicHolidayId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPhDate() {
		return phDate;
	}

	public void setPhDate(Date phDate) {
		this.phDate = phDate;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setPublicHolidayId(rs.getInt("PUBLICHOLIDAYID"));
        setClientId(rs.getInt("CLIENTID"));		
		setName(rs.getString("NAME"));
		setPhDate(rs.getDate("PHDATE"));
	}

}
