package com.helmet.bean;

public class Address {

	private String address1;

	private String address2;

	private String address3;

	private String address4;

	private String postalCode;

	private Integer countryId;

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getFullAddress() {
		String fullAddress = address1;
		if (address2 != null && !"".equals(address2)) {
			fullAddress += ", " + address2;
		}
		if (address3 != null && !"".equals(address3)) {
			fullAddress += ", " + address3;
		}
		if (address4 != null && !"".equals(address4)) {
			fullAddress += ", " + address4;
		}
		if (postalCode != null && !"".equals(postalCode)) {
			fullAddress += ", " + postalCode;
		}
		
		return fullAddress;
	}

}
