package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;

public class PartyIdentification
{
  private PartyIdentificationId partyIdentificationId;

  @XmlElement(name = "ID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public PartyIdentificationId getPartyIdentificationId()
  {
    return partyIdentificationId;
  }

  public void setPartyIdentificationId(PartyIdentificationId partyIdentificationId)
  {
    this.partyIdentificationId = partyIdentificationId;
  }

}
