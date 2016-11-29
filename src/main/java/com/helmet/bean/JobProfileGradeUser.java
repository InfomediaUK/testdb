package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JobProfileGradeUser extends JobProfileGrade {

	private String gradeName;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public void load(SqlRowSet rs) {
		super.load(rs);
		setGradeName(rs.getString("GRADENAME"));
	}

}
