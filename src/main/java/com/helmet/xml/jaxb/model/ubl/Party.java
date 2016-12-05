package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "partyIdentification", "partyName", "postalAddress", "contact" })
public class Party
{
  private PartyIdentification partyIdentification;
  private PartyName partyName;
  private PostalAddress postalAddress;
  private Contact contact;


  @XmlElement(name = "PartyIdentification", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public PartyIdentification getPartyIdentification()
  {
    return partyIdentification;
  }

  public void setPartyIdentification(PartyIdentification partyIdentification)
  {
    this.partyIdentification = partyIdentification;
  }

  @XmlElement(name = "PartyName", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public PartyName getPartyName()
  {
    return partyName;
  }

  public void setPartyName(PartyName partyName)
  {
    this.partyName = partyName;
  }

  @XmlElement(name = "PostalAddress", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public PostalAddress getPostalAddress()
  {
    return postalAddress;
  }

  public void setPostalAddress(PostalAddress postalAddress)
  {
    this.postalAddress = postalAddress;
  }

  @XmlElement(name = "Contact", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public Contact getContact()
  {
    return contact;
  }

  public void setContact(Contact contact)
  {
    this.contact = contact;
  }

}
