package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class BaseDisplayOrder extends Base {

	private Integer displayOrder;

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public void load(SqlRowSet rs)
    {
	    super.load(rs);
        setDisplayOrder(rs.getInt("DISPLAYORDER"));		
	}

}
