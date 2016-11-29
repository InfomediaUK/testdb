package com.helmet.bean;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.helmet.application.FileHandler;

public class BaseOwner extends BaseDisplayOrder {

	private String name;

	private String code;
	
	private Address address = new Address();

	private String telephoneNumber;
	
	private String faxNumber;
	
	private String logoFilename;
	
	private Integer logoWidth;
	
	private Integer logoHeight;
	
	private String vatNumber;
	
	private String reference;
	
	private String freeText;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public String getLogoFilename() {
		return logoFilename;
	}

	public void setLogoFilename(String logoFilename) {
		this.logoFilename = logoFilename;
	}

	public Integer getLogoHeight() {
		return logoHeight;
	}

	public void setLogoHeight(Integer logoHeight) {
		this.logoHeight = logoHeight;
	}

	public Integer getLogoWidth() {
		return logoWidth;
	}

	public void setLogoWidth(Integer logoWidth) {
		this.logoWidth = logoWidth;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public void load(SqlRowSet rs)
    {
	    super.load(rs);
        setName(rs.getString("NAME"));		
        getAddress().setAddress1(rs.getString("ADDRESS1"));		
        getAddress().setAddress2(rs.getString("ADDRESS2"));		
        getAddress().setAddress3(rs.getString("ADDRESS3"));		
        getAddress().setAddress4(rs.getString("ADDRESS4"));		
        getAddress().setPostalCode(rs.getString("POSTALCODE"));		
        getAddress().setCountryId(rs.getInt("COUNTRYID"));		
        setCode(rs.getString("CODE"));
        setTelephoneNumber(rs.getString("TELEPHONENUMBER"));
        setFaxNumber(rs.getString("FAXNUMBER"));
        setLogoFilename(rs.getString("LOGOFILENAME"));
        setLogoWidth(rs.getInt("LOGOWIDTH"));
        setLogoHeight(rs.getInt("LOGOHEIGHT"));
        setVatNumber(rs.getString("VATNUMBER"));
        setReference(rs.getString("REFERENCE"));
        setFreeText(rs.getString("FREETEXT"));
	}
	
}
