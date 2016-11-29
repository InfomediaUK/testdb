package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.GeographicalRegion;

public interface GeographicalRegionDAO 
{
	public List<GeographicalRegion> getGeographicalRegions(boolean showOnlyActive);
	public GeographicalRegion getGeographicalRegion(Integer geographicalRegionId);
	public GeographicalRegion getGeographicalRegionForName(String name);
	public GeographicalRegion getGeographicalRegionForCode(String code);
	public int insertGeographicalRegion(GeographicalRegion geographicalRegion, Integer auditorId);
	public int updateGeographicalRegion(GeographicalRegion geographicalRegion, Integer auditorId);
	public int deleteGeographicalRegion(Integer geographicalRegionId, Integer noOfChanges, Integer auditorId);
}
