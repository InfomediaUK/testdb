package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Shift;

public interface ShiftDAO {

	public List<Shift> getShiftsForLocation(Integer locationId, boolean showOnlyActive);
	public Shift getShift(Integer shiftId);
	public Shift getShiftForName(Integer locationId, String name);	
	public Shift getShiftForCode(Integer locationId, String code);
	public int insertShift(Shift shift, Integer auditorId);
	public int updateShift(Shift shift, Integer auditorId);
	public int deleteShift(Integer shiftId, Integer noOfChanges, Integer auditorId);
	public int updateShiftDisplayOrder(Integer shiftId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
