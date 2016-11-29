package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class AgyAccessGroupItem extends Base {

	private Integer agyAccessGroupItemId;

	private Integer agyAccessGroupId;

	private Integer agyAccessId;

	public Integer getAgyAccessGroupItemId() {
		return agyAccessGroupItemId;
	}

	public void setAgyAccessGroupItemId(Integer agyAccessGroupItemId) {
		this.agyAccessGroupItemId = agyAccessGroupItemId;
	}


	public Integer getAgyAccessGroupId() {
		return agyAccessGroupId;
	}

	public void setAgyAccessGroupId(Integer agyAccessGroupId) {
		this.agyAccessGroupId = agyAccessGroupId;
	}

	
	public Integer getAgyAccessId() {
		return agyAccessId;
	}

	public void setAgyAccessId(Integer agyAccessId) {
		this.agyAccessId = agyAccessId;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setAgyAccessGroupItemId(rs.getInt("AGYACCESSGROUPITEMID"));
		setAgyAccessGroupId(rs.getInt("AGYACCESSGROUPID"));
		setAgyAccessId(rs.getInt("AGYACCESSID"));
	}

}
