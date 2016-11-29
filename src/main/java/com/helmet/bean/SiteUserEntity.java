package com.helmet.bean;

import java.util.List;

public class SiteUserEntity extends SiteUser {

	private List<Location> locations;

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	
}
