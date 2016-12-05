package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;

public class AccountingParty
{
  private Party party;

  @XmlElement(name = "Party", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public Party getParty()
  {
    return party;
  }

  public void setParty(Party party)
  {
    this.party = party;
  }


}
