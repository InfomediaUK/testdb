package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Regulator;

public interface RegulatorDAO 
{
	public List<Regulator> getRegulators(boolean showOnlyActive);
	public Regulator getRegulator(Integer regulatorId);
	public Regulator getRegulatorForName(String name);
	public Regulator getRegulatorForCode(String code);
	public int insertRegulator(Regulator regulator, Integer auditorId);
  public int updateRegulator(Regulator regulator, Integer auditorId);
  public int updateRegulatorDisplayOrder(Regulator regulator, Integer auditorId);
	public int deleteRegulator(Integer regulatorId, Integer noOfChanges, Integer auditorId);
}
