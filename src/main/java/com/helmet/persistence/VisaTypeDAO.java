package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.VisaType;

public interface VisaTypeDAO 
{
	public List<VisaType> getVisaTypes(boolean showOnlyActive);
	public VisaType getVisaType(Integer visaTypeId);
	public VisaType getVisaTypeForName(String name);
	public VisaType getVisaTypeForCode(String code);
	public int insertVisaType(VisaType visaType, Integer auditorId);
  public int updateVisaType(VisaType visaType, Integer auditorId);
  public int updateVisaTypeDisplayOrder(VisaType visaType, Integer auditorId);
	public int deleteVisaType(Integer visaTypeId, Integer noOfChanges, Integer auditorId);
}
