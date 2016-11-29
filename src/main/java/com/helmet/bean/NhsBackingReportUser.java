package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class NhsBackingReportUser extends NhsBackingReport
{
  private String clientName;
  private String clientCode;

  public String getClientName()
  {
    return clientName;
  }

  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }

  public String getClientCode()
  {
    return clientCode;
  }

  public void setClientCode(String clientCode)
  {
    this.clientCode = clientCode;
  }

  public void load(SqlRowSet rs)
  {
    super.load(rs);
    setClientName(rs.getString("CLIENTNAME"));
    setClientCode(rs.getString("CLIENTCODE"));
  }

}
