package com.helmet.persistence.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.helmet.bean.BookingDateHour;
import com.helmet.persistence.BookingDateHourDAO;
import com.helmet.persistence.RecordListFactory;
import com.helmet.persistence.UpdateHandler;
import com.helmet.persistence.Utilities;

public class DefaultBookingDateHourDAO extends JdbcDaoSupport implements BookingDateHourDAO {

	private static StringBuffer insertBookingDateHourSQL;

	private static StringBuffer deleteBookingDateHourSQL;

	private static StringBuffer selectBookingDateHoursSQL;

	private static StringBuffer selectBookingDateHoursForBookingDateSQL;

	private static StringBuffer selectActiveBookingDateHoursForBookingDateSQL;
	

	public static void init() {
		// Get insert BookingDate SQL.
		insertBookingDateHourSQL = new StringBuffer();
		insertBookingDateHourSQL.append("INSERT INTO BOOKINGDATEHOUR ");
		insertBookingDateHourSQL.append("(  ");
		insertBookingDateHourSQL.append("  BOOKINGDATEHOURID, ");
		insertBookingDateHourSQL.append("  BOOKINGDATEID, ");
		insertBookingDateHourSQL.append("  HOUROFDAY, ");
		insertBookingDateHourSQL.append("  PORTIONOFHOUR, ");
		insertBookingDateHourSQL.append("  CHARGERATE, ");
		insertBookingDateHourSQL.append("  PAYRATE, ");
		insertBookingDateHourSQL.append("  WAGERATE, ");
		insertBookingDateHourSQL.append("  UPLIFTFACTOR, ");
		insertBookingDateHourSQL.append("  UPLIFTVALUE, ");
		insertBookingDateHourSQL.append("  UPLIFTEDCHARGERATE, ");
		insertBookingDateHourSQL.append("  UPLIFTEDPAYRATE, ");
		insertBookingDateHourSQL.append("  UPLIFTEDWAGERATE, ");
		insertBookingDateHourSQL.append("  CHARGERATEVALUE, ");
		insertBookingDateHourSQL.append("  CHARGERATEVATVALUE, ");
		insertBookingDateHourSQL.append("  PAYRATEVALUE, ");
		insertBookingDateHourSQL.append("  PAYRATEVATVALUE, ");
		insertBookingDateHourSQL.append("  WTDRATE, ");
		insertBookingDateHourSQL.append("  WTDVALUE, ");
		insertBookingDateHourSQL.append("  WTDVATVALUE, ");
		insertBookingDateHourSQL.append("  NIRATE, ");
		insertBookingDateHourSQL.append("  NIVALUE, ");
		insertBookingDateHourSQL.append("  NIVATVALUE, ");
		insertBookingDateHourSQL.append("  COMMISSIONRATE, ");
		insertBookingDateHourSQL.append("  COMMISSIONVALUE, ");
		insertBookingDateHourSQL.append("  COMMISSIONVATVALUE, ");
		insertBookingDateHourSQL.append("  WAGERATEVALUE, ");
		insertBookingDateHourSQL.append("  CREATIONTIMESTAMP, ");
		insertBookingDateHourSQL.append("  AUDITORID, ");
		insertBookingDateHourSQL.append("  AUDITTIMESTAMP ");
		insertBookingDateHourSQL.append(")  ");
		insertBookingDateHourSQL.append("VALUES  ");
		insertBookingDateHourSQL.append("(  ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^, ");
		insertBookingDateHourSQL.append("  ^ ");
		insertBookingDateHourSQL.append(")  ");
		// Get delete BookingDateHour SQL.
		deleteBookingDateHourSQL = new StringBuffer();
		deleteBookingDateHourSQL.append("UPDATE BOOKINGDATEHOUR ");
		deleteBookingDateHourSQL.append("SET ACTIVE = FALSE, ");
		deleteBookingDateHourSQL.append("    AUDITORID = ^, ");
		deleteBookingDateHourSQL.append("    AUDITTIMESTAMP = ^, ");
		deleteBookingDateHourSQL.append("    NOOFCHANGES = NOOFCHANGES + 1 ");
		deleteBookingDateHourSQL.append("WHERE BOOKINGDATEHOURID = ^ ");
		deleteBookingDateHourSQL.append("AND   NOOFCHANGES = ^ ");
		// Get select BookingDateHours SQL.
		selectBookingDateHoursSQL = new StringBuffer();
		selectBookingDateHoursSQL.append("SELECT  ");
		selectBookingDateHoursSQL.append("  BOOKINGDATEHOURID, ");
		selectBookingDateHoursSQL.append("  BOOKINGDATEID, ");
		selectBookingDateHoursSQL.append("  HOUROFDAY, ");
		selectBookingDateHoursSQL.append("  PORTIONOFHOUR, ");
		selectBookingDateHoursSQL.append("  CHARGERATE, ");
		selectBookingDateHoursSQL.append("  PAYRATE, ");
		selectBookingDateHoursSQL.append("  WAGERATE, ");
		selectBookingDateHoursSQL.append("  UPLIFTFACTOR, ");
		selectBookingDateHoursSQL.append("  UPLIFTVALUE, ");
		selectBookingDateHoursSQL.append("  UPLIFTEDCHARGERATE, ");
		selectBookingDateHoursSQL.append("  UPLIFTEDPAYRATE, ");
		selectBookingDateHoursSQL.append("  UPLIFTEDWAGERATE, ");
		selectBookingDateHoursSQL.append("  CHARGERATEVALUE, ");
		selectBookingDateHoursSQL.append("  CHARGERATEVATVALUE, ");
		selectBookingDateHoursSQL.append("  PAYRATEVALUE, ");
		selectBookingDateHoursSQL.append("  PAYRATEVATVALUE, ");
		selectBookingDateHoursSQL.append("  WTDRATE, ");
		selectBookingDateHoursSQL.append("  WTDVALUE, ");
		selectBookingDateHoursSQL.append("  WTDVATVALUE, ");
		selectBookingDateHoursSQL.append("  NIRATE, ");
		selectBookingDateHoursSQL.append("  NIVALUE, ");
		selectBookingDateHoursSQL.append("  NIVATVALUE, ");
		selectBookingDateHoursSQL.append("  COMMISSIONRATE, ");
		selectBookingDateHoursSQL.append("  COMMISSIONVALUE, ");
		selectBookingDateHoursSQL.append("  COMMISSIONVATVALUE, ");
		selectBookingDateHoursSQL.append("  WAGERATEVALUE, ");
		selectBookingDateHoursSQL.append("  CREATIONTIMESTAMP, ");
		selectBookingDateHoursSQL.append("  AUDITORID, ");
		selectBookingDateHoursSQL.append("  AUDITTIMESTAMP, ");
		selectBookingDateHoursSQL.append("  ACTIVE, ");
		selectBookingDateHoursSQL.append("  NOOFCHANGES ");
		selectBookingDateHoursSQL.append("FROM BOOKINGDATEHOUR ");
		// Get select BookingDateHours for BookingDateSQL.
		selectBookingDateHoursForBookingDateSQL = new StringBuffer(selectBookingDateHoursSQL);
		selectBookingDateHoursForBookingDateSQL.append("WHERE BOOKINGDATEID = ^ ");
		// Get select active BookingDateHours for BookingDateSQL.
		selectActiveBookingDateHoursForBookingDateSQL = new StringBuffer(selectBookingDateHoursForBookingDateSQL);
		selectActiveBookingDateHoursForBookingDateSQL.append("AND ACTIVE = TRUE ");
		selectActiveBookingDateHoursForBookingDateSQL.append("ORDER BY BOOKINGDATEHOURID ");
	}

	public int insertBookingDateHour(BookingDateHour bookingDateHour, Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(insertBookingDateHourSQL.toString());
		// Replace the parameters with supplied values.
		bookingDateHour.setBookingDateHourId(UpdateHandler.getInstance().getId(getJdbcTemplate(), "bookingDateHour"));
		Utilities.replace(sql, bookingDateHour.getBookingDateHourId());
		Utilities.replace(sql, bookingDateHour.getBookingDateId());
		Utilities.replace(sql, bookingDateHour.getHourOfDay());
		Utilities.replace(sql, bookingDateHour.getPortionOfHour());
		Utilities.replace(sql, bookingDateHour.getChargeRate());
		Utilities.replace(sql, bookingDateHour.getPayRate());
		Utilities.replace(sql, bookingDateHour.getWageRate());
		Utilities.replace(sql, bookingDateHour.getUpliftFactor());
		Utilities.replace(sql, bookingDateHour.getUpliftValue());
		Utilities.replace(sql, bookingDateHour.getUpliftedChargeRate());
		Utilities.replace(sql, bookingDateHour.getUpliftedPayRate());
		Utilities.replace(sql, bookingDateHour.getUpliftedWageRate());
		Utilities.replace(sql, bookingDateHour.getChargeRateValue());
		Utilities.replace(sql, bookingDateHour.getChargeRateVatValue());
		Utilities.replace(sql, bookingDateHour.getPayRateValue());
		Utilities.replace(sql, bookingDateHour.getPayRateVatValue());
		Utilities.replace(sql, bookingDateHour.getWtdRate());
		Utilities.replace(sql, bookingDateHour.getWtdValue());
		Utilities.replace(sql, bookingDateHour.getWtdVatValue());
		Utilities.replace(sql, bookingDateHour.getNiRate());
		Utilities.replace(sql, bookingDateHour.getNiValue());
		Utilities.replace(sql, bookingDateHour.getNiVatValue());
		Utilities.replace(sql, bookingDateHour.getCommissionRate());
		Utilities.replace(sql, bookingDateHour.getCommissionValue());
		Utilities.replace(sql, bookingDateHour.getCommissionVatValue());
		Utilities.replace(sql, bookingDateHour.getWageRateValue());
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	
	public int deleteBookingDateHour(Integer bookingDateHourId, Integer noOfChanges,
			Integer auditorId) {
		// Create a new local StringBuffer containing the parameterised SQL.
		StringBuffer sql = new StringBuffer(deleteBookingDateHourSQL.toString());
		// Replace the parameters with supplied values.
		Utilities.replace(sql, auditorId);
		Utilities.replaceAndQuote(sql, new Timestamp(new java.util.Date()
				.getTime()).toString());
		Utilities.replace(sql, bookingDateHourId);
		Utilities.replace(sql, noOfChanges);
		return UpdateHandler.getInstance().update(getJdbcTemplate(),
				sql.toString());
	}

	
	public int deleteBookingDateHoursForBookingDate(Integer bookingDateId, Integer auditorId) {

		int i = 0;
		List<BookingDateHour> bookingDateHours = getBookingDateHoursForBookingDate(bookingDateId, true);
		for (BookingDateHour bookingDateHour: bookingDateHours) {
            i += deleteBookingDateHour(bookingDateHour.getBookingDateHourId(), bookingDateHour.getNoOfChanges(), auditorId);
		}
		return i;

	}

	public int insertBookingDateHours(List<BookingDateHour> bookingDateHours, Integer auditorId) {

		int i = 0;
		for (BookingDateHour bookingDateHour: bookingDateHours) {
        	i += insertBookingDateHour(bookingDateHour, auditorId);
        }
		return i;
		
	}

	public List<BookingDateHour> getBookingDateHoursForBookingDate(Integer bookingDateId, boolean showOnlyActive) {

		StringBuffer sql = null;
		if (showOnlyActive) {
			sql = new StringBuffer(selectActiveBookingDateHoursForBookingDateSQL.toString());
		}
		else {
			sql = new StringBuffer(selectBookingDateHoursForBookingDateSQL.toString()); 
		}
		// Replace the parameters with supplied values.
		Utilities.replace(sql, bookingDateId);
		return RecordListFactory.getInstance().get(getJdbcTemplate(),
				sql.toString(), BookingDateHour.class.getName());

	}

	
	
	
}
