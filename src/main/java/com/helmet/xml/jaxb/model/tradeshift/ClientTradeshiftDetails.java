package com.helmet.xml.jaxb.model.tradeshift;

import org.apache.commons.lang3.StringUtils;

public class ClientTradeshiftDetails
{
  private String clientName;
  private String sbsPayablesCode;
  private String oldCompanyAccountId;
  private String oldState;
  private String newCompanyAccountId;
  private String newState;

  public String getClientName()
  {
    return clientName;
  }

  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  }

  public String getSbsPayablesCode()
  {
    return sbsPayablesCode;
  }

  public void setSbsPayablesCode(String sbsPayablesCode)
  {
    this.sbsPayablesCode = sbsPayablesCode;
  }

  public String getNewCompanyAccountId()
  {
    return newCompanyAccountId;
  }

  public void setNewCompanyAccountId(String newCompanyAccountId)
  {
    this.newCompanyAccountId = newCompanyAccountId;
  }

  public String getNewState()
  {
    return newState;
  }

  public void setNewState(String newState)
  {
    this.newState = newState;
  }

  public String getOldCompanyAccountId()
  {
    return oldCompanyAccountId;
  }

  public void setOldCompanyAccountId(String oldCompanyAccountId)
  {
    this.oldCompanyAccountId = oldCompanyAccountId;
  }

  public String getOldState()
  {
    return oldState;
  }

  public void setOldState(String oldState)
  {
    this.oldState = oldState;
  }
  
  public Boolean changed()
  {
    return !(StringUtils.equals(oldCompanyAccountId, newCompanyAccountId) && StringUtils.equals(oldState, newState));
  }
}
