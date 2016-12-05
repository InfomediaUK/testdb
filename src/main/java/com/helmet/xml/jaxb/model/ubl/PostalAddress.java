package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "addressFormatCode", "buildingNumber", "streetName", "additionalStreetName", "cityName", "countrySubEntity", "postalZone", "country" })
public class PostalAddress
{
  private AddressFormatCode addressFormatCode;
  private String buildingNumber;
  private String streetName;
  private String additionalStreetName;
  private String cityName;
  private Country countrySubEntity;
  private String postalZone;
  private Country country;

  @XmlElement(name = "AddressFormatCode", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public AddressFormatCode getAddressFormatCode()
  {
    return addressFormatCode;
  }

  public void setAddressFormatCode(AddressFormatCode addressFormatCode)
  {
    this.addressFormatCode = addressFormatCode;
  }

  @XmlElement(name = "BuildingNumber", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getBuildingNumber()
  {
    return buildingNumber;
  }

  public void setBuildingNumber(String buildingNumber)
  {
    this.buildingNumber = buildingNumber;
  }

  @XmlElement(name = "StreetName", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getStreetName()
  {
    return streetName;
  }

  public void setStreetName(String streetName)
  {
    this.streetName = streetName;
  }

  @XmlElement(name = "AdditionalStreetName", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getAdditionalStreetName()
  {
    return additionalStreetName;
  }

  public void setAdditionalStreetName(String additionalStreetName)
  {
    this.additionalStreetName = additionalStreetName;
  }

  @XmlElement(name = "CityName", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getCityName()
  {
    return cityName;
  }

  public void setCityName(String cityName)
  {
    this.cityName = cityName;
  }

  @XmlElement(name = "CountrySubentity", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public Country getCountrySubEntity()
  {
    return countrySubEntity;
  }

  public void setCountrySubEntity(Country countrySubEntity)
  {
    this.countrySubEntity = countrySubEntity;
  }

  @XmlElement(name = "PostalZone", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getPostalZone()
  {
    return postalZone;
  }

  public void setPostalZone(String postalZone)
  {
    this.postalZone = postalZone;
  }

  @XmlElement(name = "Country", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public Country getCountry()
  {
    return country;
  }

  public void setCountry(Country country)
  {
    this.country = country;
  }

}
