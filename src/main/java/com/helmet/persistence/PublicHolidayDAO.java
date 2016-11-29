package com.helmet.persistence;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.PublicHoliday;

public interface PublicHolidayDAO {

	public List<PublicHoliday> getPublicHolidaysForClient(Integer clientId, boolean showOnlyActive);
	public PublicHoliday getPublicHoliday(Integer publicHolidayId);
  public PublicHoliday getPublicHolidayForClientDate(Integer clientId, Date date);
	public int insertPublicHoliday(PublicHoliday publicHoliday, Integer auditorId);
	public int updatePublicHoliday(PublicHoliday publicHoliday, Integer auditorId);
	public int deletePublicHoliday(Integer publicHolidayId, Integer noOfChanges, Integer auditorId);

}
