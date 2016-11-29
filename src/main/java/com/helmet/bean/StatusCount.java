package com.helmet.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class StatusCount extends Base implements Serializable {
	private Integer status;

	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void load(SqlRowSet rs) {
		setStatus(rs.getInt(1));
		setCount(rs.getInt(2));
	}
}