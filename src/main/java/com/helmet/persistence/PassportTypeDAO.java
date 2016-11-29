package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.PassportType;

public interface PassportTypeDAO 
{
	public List<PassportType> getPassportTypes(boolean showOnlyActive);
	public PassportType getPassportType(Integer passportTypeId);
	public PassportType getPassportTypeForName(String name);
	public PassportType getPassportTypeForCode(String code);
	public int insertPassportType(PassportType passportType, Integer auditorId);
  public int updatePassportType(PassportType passportType, Integer auditorId);
  public int updatePassportTypeDisplayOrder(PassportType passportType, Integer auditorId);
	public int deletePassportType(Integer passportTypeId, Integer noOfChanges, Integer auditorId);
}
