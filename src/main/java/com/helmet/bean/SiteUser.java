package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SiteUser extends Site
{

  private String countryName;

  private String clientName;

  public String getCountryName()
  {
    return countryName;
  }

  public void setCountryName(String countryName)
  {
    this.countryName = countryName;
  }

  public String getClientName()
  {
    return clientName;
  }

  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setCountryName(rs.getString("COUNTRYNAME"));
    setClientName(rs.getString("CLIENTNAME"));
  }

}
