package com.helmet.bean;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Uplift extends Base {

	public final static String[] UPLIFT_DAY_DESCRIPTION_KEYS = {"uplift.day.publicHoliday",
		"uplift.day.sunday",
		"uplift.day.monday",
		"uplift.day.tuesday",
		"uplift.day.wednesday",
		"uplift.day.thursday",
		"uplift.day.friday",
		"uplift.day.saturday"};

  private List<UpliftMinute> upliftMinutes;

	private Integer upliftId;

	private Integer clientId;

	private Integer upliftDay;

  private Integer upliftHour;

  private Integer upliftMinutePeriod;

	private BigDecimal upliftFactor = new BigDecimal(1);
	
	private BigDecimal upliftValue = new BigDecimal(0);
	
	public Integer getUpliftId() {
		return upliftId;
	}

	public void setUpliftId(Integer upliftId) {
		this.upliftId = upliftId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getUpliftDay() {
		return upliftDay;
	}

	public void setUpliftDay(Integer upliftDay) {
		this.upliftDay = upliftDay;
	}

	public Integer getUpliftHour() {
		return upliftHour;
	}

	public void setUpliftHour(Integer upliftHour) {
		this.upliftHour = upliftHour;
	}

	public Integer getUpliftMinutePeriod()
  {
    return upliftMinutePeriod;
  }

  public void setUpliftMinutePeriod(Integer upliftMinutePeriod)
  {
    this.upliftMinutePeriod = upliftMinutePeriod;
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

	public String getUpliftDayDescriptionKey() {
		return UPLIFT_DAY_DESCRIPTION_KEYS[upliftDay];
	}
  
  public List<UpliftMinute> getUpliftMinutes()
  {
    return upliftMinutes;
  }

  public void setUpliftMinutes(List<UpliftMinute> upliftMinutes)
  {
    this.upliftMinutes = upliftMinutes;
  }

  public String getUpliftMinutesAsString() 
  {
    StringBuffer result = new StringBuffer();
    for (UpliftMinute upliftMinute: upliftMinutes) 
    {
      if (result.length() > 0) 
      {
        result.append("\n ");
      }
      result.append("Day: ");
      result.append(getUpliftDay().toString());
      result.append(" Hour: ");
      result.append(getUpliftHour().toString());
      result.append(" ");
      result.append(upliftMinute.getUpliftMinuteAsString());
    }
    return result.toString();
  }

	public void load(SqlRowSet rs) {
		super.load(rs);
		setUpliftId(rs.getInt("UPLIFTID"));
		setClientId(rs.getInt("CLIENTID"));
		setUpliftDay(rs.getInt("UPLIFTDAY"));
    setUpliftHour(rs.getInt("UPLIFTHOUR"));
    setUpliftMinutePeriod(rs.getInt("UPLIFTMINUTEPERIOD"));
		setUpliftFactor(rs.getBigDecimal("UPLIFTFACTOR"));
		setUpliftValue(rs.getBigDecimal("UPLIFTVALUE"));
	}

}
