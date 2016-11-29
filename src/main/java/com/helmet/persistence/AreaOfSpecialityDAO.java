package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.AreaOfSpeciality;

public interface AreaOfSpecialityDAO 
{
	public List<AreaOfSpeciality> getAreaOfSpecialities(boolean showOnlyActive);
	public AreaOfSpeciality getAreaOfSpeciality(Integer areaOfSpecialityId);
  public AreaOfSpeciality getAreaOfSpecialityForName(String name);
  public AreaOfSpeciality getAreaOfSpecialityForCode(String code);
	public int insertAreaOfSpeciality(AreaOfSpeciality areaOfSpeciality, Integer auditorId);
	public int updateAreaOfSpeciality(AreaOfSpeciality areaOfSpeciality, Integer auditorId);
	public int deleteAreaOfSpeciality(Integer areaOfSpecialityId, Integer noOfChanges, Integer auditorId);
}
