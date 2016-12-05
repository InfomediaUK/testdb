package com.helmet.application.admin;

import java.math.BigDecimal;
import java.sql.Time;

import com.helmet.bean.Shift;

public final class ShiftUtilities {

	public static final void sortTimes(Shift shift, String shiftStartHour,
			String shiftStartMinute, String shiftEndHour,
			String shiftEndMinute, String shiftBreakStartHour,
			String shiftBreakStartMinute, String shiftBreakEndHour,
			String shiftBreakEndMinute) {

		shift.setStartTime(Time.valueOf(shiftStartHour + ":" + shiftStartMinute
				+ ":00"));
		shift.setEndTime(Time.valueOf(shiftEndHour + ":" + shiftEndMinute
				+ ":00"));

		if (shiftBreakStartHour != null && !"".equals(shiftBreakStartHour)
				&& shiftBreakStartMinute != null
				&& !"".equals(shiftBreakStartMinute)
				&& shiftBreakEndHour != null && !"".equals(shiftBreakEndHour)
				&& shiftBreakEndMinute != null
				&& !"".equals(shiftBreakEndMinute)) {
			shift.setBreakStartTime(Time.valueOf(shiftBreakStartHour + ":"
					+ shiftBreakStartMinute + ":00"));
			shift.setBreakEndTime(Time.valueOf(shiftBreakEndHour + ":"
					+ shiftBreakEndMinute + ":00"));
		}

		long milliSecondsPerHour = 1000 * 60 * 60;
		long milliSecondsPerDay = 24 * milliSecondsPerHour;
		long diffInMilliSeconds = 0;

		// if end is before start the shift runs through midnight so
		if (shift.getStartTime().before(shift.getEndTime())) {
			diffInMilliSeconds = shift.getEndTime().getTime() - shift.getStartTime().getTime();
		} else {
			diffInMilliSeconds = (milliSecondsPerDay - shift.getStartTime().getTime()) + shift.getEndTime().getTime();
		}

		BigDecimal diffInHours = new BigDecimal(diffInMilliSeconds / (milliSecondsPerHour * 1.0F));

		BigDecimal breakDiffInHours = new BigDecimal(0);
		
		if (shift.getBreakStartTime() != null && shift.getBreakEndTime() != null) {
			long breakDiffInMilliSeconds = 0;
			// if end is before start the break runs through midnight so
			if (shift.getBreakStartTime().before(shift.getBreakEndTime())) {
				breakDiffInMilliSeconds = shift.getBreakEndTime().getTime() - shift.getBreakStartTime().getTime();
			}
			else {
				breakDiffInMilliSeconds = (milliSecondsPerDay - shift.getBreakStartTime().getTime()) + shift.getBreakEndTime().getTime(); 
			}
			breakDiffInHours = new BigDecimal(breakDiffInMilliSeconds / (milliSecondsPerHour * 1.0F));
			diffInHours = diffInHours.subtract(breakDiffInHours);
		}
		
		shift.setNoOfHours(diffInHours);
		shift.setBreakNoOfHours(breakDiffInHours);

	}

}
