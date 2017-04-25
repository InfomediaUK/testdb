package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class TrainingCompanyUser extends TrainingCompany
{

  private String countryName;

  public String getCountryName()
  {
    return countryName;
  }

  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setCountryName(rs.getString("COUNTRYNAME"));
  }

  @Override
  public String toString()
  {
    super.toString();
    return "TrainingCompanyUser [countryName=" + countryName + "]";
  }

}
