package com.helmet.bean;

import java.math.BigDecimal;
import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Shift extends BaseDisplayOrder {

	private Integer shiftId;

	private Integer locationId;

	private String name;

	private String code;

	private String description;
	
	private Time startTime;
	
	private Time endTime;
	
	private Time breakStartTime;
	
	private Time breakEndTime;
	
	private BigDecimal noOfHours;
	
	private BigDecimal breakNoOfHours;
	
	private BigDecimal upliftFactor = new BigDecimal(1);
	
	private BigDecimal upliftValue = new BigDecimal(0);
	
	private Boolean useShiftUplift = false;
	
	private BigDecimal overtimeNoOfHours = new BigDecimal(0);
	
	private BigDecimal overtimeUpliftFactor = new BigDecimal(1);
	
	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Time getBreakStartTime() {
		return breakStartTime;
	}

	public void setBreakStartTime(Time breakStartTime) {
		this.breakStartTime = breakStartTime;
	}
	
	public Time getBreakEndTime() {
		return breakEndTime;
	}

	public void setBreakEndTime(Time breakEndTime) {
		this.breakEndTime = breakEndTime;
	}

	public BigDecimal getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(BigDecimal noOfHours) {
		this.noOfHours = noOfHours;
	}

	public BigDecimal getBreakNoOfHours() {
		return breakNoOfHours;
	}

	public void setBreakNoOfHours(BigDecimal breakNoOfHours) {
		this.breakNoOfHours = breakNoOfHours;
	}

	public BigDecimal getUpliftFactor() {
		return upliftFactor;
	}

	public void setUpliftFactor(BigDecimal upliftFactor) {
		this.upliftFactor = upliftFactor;
	}

	public BigDecimal getUpliftValue() {
		return upliftValue;
	}

	public void setUpliftValue(BigDecimal upliftValue) {
		this.upliftValue = upliftValue;
	}

	public Boolean getUseShiftUplift() {
		return useShiftUplift;
	}

	public void setUseShiftUplift(Boolean useShiftUplift) {
		this.useShiftUplift = useShiftUplift;
	}

	public BigDecimal getOvertimeNoOfHours() {
		return overtimeNoOfHours;
	}

	public void setOvertimeNoOfHours(BigDecimal overtimeNoOfHours) {
		this.overtimeNoOfHours = overtimeNoOfHours;
	}

	public BigDecimal getOvertimeUpliftFactor() {
		return overtimeUpliftFactor;
	}

	public void setOvertimeUpliftFactor(BigDecimal overtimeUpliftFactor) {
		this.overtimeUpliftFactor = overtimeUpliftFactor;
	}

  public Boolean getUndefined()
  {
    return startTime.equals(endTime);
  }
  
	public void load(SqlRowSet rs) {
		super.load(rs);
		setShiftId(rs.getInt("SHIFTID"));
		setLocationId(rs.getInt("LOCATIONID"));
		setName(rs.getString("NAME"));
		setCode(rs.getString("CODE"));
		setDescription(rs.getString("DESCRIPTION"));
		setStartTime(rs.getTime("STARTTIME"));
		setEndTime(rs.getTime("ENDTIME"));
		setBreakStartTime(rs.getTime("BREAKSTARTTIME"));
		setBreakEndTime(rs.getTime("BREAKENDTIME"));
		setNoOfHours(rs.getBigDecimal("NOOFHOURS"));
		setBreakNoOfHours(rs.getBigDecimal("BREAKNOOFHOURS"));
		setUpliftFactor(rs.getBigDecimal("UPLIFTFACTOR"));
		setUpliftValue(rs.getBigDecimal("UPLIFTVALUE"));
		setUseShiftUplift(rs.getBoolean("USESHIFTUPLIFT"));
		setOvertimeNoOfHours(rs.getBigDecimal("OVERTIMENOOFHOURS"));
		setOvertimeUpliftFactor(rs.getBigDecimal("OVERTIMEUPLIFTFACTOR"));
	}

}
