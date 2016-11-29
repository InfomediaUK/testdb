package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.DressCode;

public interface DressCodeDAO {

	public List<DressCode> getDressCodesForLocation(Integer locationId, boolean showOnlyActive);
	public DressCode getDressCode(Integer dressCodeId);
	public DressCode getDressCodeForName(Integer locationId, String name);	
	public int insertDressCode(DressCode dressCode, Integer auditorId);
	public int updateDressCode(DressCode dressCode, Integer auditorId);
	public int deleteDressCode(Integer dressCodeId, Integer noOfChanges, Integer auditorId);
	public int updateDressCodeDisplayOrder(Integer dressCodeId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
