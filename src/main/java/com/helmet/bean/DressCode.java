package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class DressCode extends BaseDisplayOrder {

	private Integer dressCodeId;

	private Integer locationId;

	private String name;

	public Integer getDressCodeId() {
		return dressCodeId;
	}

	public void setDressCodeId(Integer dressCodeId) {
		this.dressCodeId = dressCodeId;
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

	public void load(SqlRowSet rs) {
		super.load(rs);
		setDressCodeId(rs.getInt("DRESSCODEID"));
		setLocationId(rs.getInt("LOCATIONID"));
		setName(rs.getString("NAME"));
	}

}
