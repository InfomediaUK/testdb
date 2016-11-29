package com.helmet.persistence;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.helmet.bean.Booking;
import com.helmet.bean.BookingUser;
import com.helmet.bean.BookingUserEntityMgr;
import com.helmet.bean.IntValue;
import com.helmet.bean.StatusCount;

public interface BookingDAO {

  public List<Booking> getBookingsForBookingReference(String bookingReference); 
	public List<BookingUser> getBookingUsersForManager(Integer managerId, Boolean singleCandidate, boolean showOnlyActive, Integer siteId, Integer locationId, Integer jobProfileId);
	public List<BookingUser> getBookingUsersForManagerAndBooking(Integer managerId, Integer bookingId);
	public List<BookingUser> getBookingUsersForManagerAndStatus(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId);
	public List<BookingUser> getBookingUsersForManagerAndStatusAndDateRange(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
    public List<BookingUser> getBookingUsersForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);

	public List<BookingUser> getBookingUsersForManagerAndWorkedStatus(Integer managerId, Integer workedStatus);
	public List<BookingUser> getBookingUsersForManagerAndBookingGradeApplicantDateStatus(Integer managerId, Integer bookingGradeApplicantDateStatus);

	public Booking getBooking(Integer bookingId);
	public BookingUser getBookingUser(Integer bookingId);
	public BookingUserEntityMgr getBookingUserEntityMgr(Integer bookingId, Integer  managerId);
	public int insertBooking(Booking booking, Integer auditorId);
	public int updateBooking(Booking booking, Integer auditorId);
	
	public int updateBookingExtend(Booking booking, Integer auditorId);
	
	public int deleteBooking(Integer bookingId, Integer noOfChanges, Integer auditorId);
    public int updateBookingStatus(Integer bookingId, Integer noOfChanges, Integer auditorId, int status);
    public int updateBookingOpen(Integer bookingId, Integer noOfChanges, Integer auditorId);
    
    public int updateBookingCancel(Integer bookingId, String canceText, Integer noOfChanges, Integer auditorId);
    public int updateBookingClosed(Integer bookingId, Integer noOfChanges, Integer auditorId, BigDecimal filledValue);
	public int updateBookingFilledValue(Integer bookingId, Integer noOfChanges, Integer auditorId, BigDecimal filledValue);
	public int updateBookingWorkedValue(Integer bookingId, Integer noOfChanges, Integer auditorId, BigDecimal workedValue, BigDecimal workedNoOfHours, Integer status);
    
	public int updateBookingInfo(Booking booking, Integer auditorId);
	public int updateBookingExpensesText(Integer bookingId, String expensesText, Integer noOfChanges, Integer auditorId);
	
	public IntValue getBookingCountNotFilled(Integer bookingId);
    public IntValue getBookingCountNotAuthorized(Integer bookingId);
	public List<StatusCount> getBookingStatusCountsForManager(Integer managerId);
	public List<StatusCount> getShiftStatusCountsForManager(Integer managerId);
	public List<StatusCount> getBookingWorkedStatusCountsForManager(Integer managerId);
	public List<StatusCount> getShiftWorkedStatusCountsForManager(Integer managerId);
	public List<StatusCount> getBookingGradeApplicantDateStatusCountsForManager(Integer managerId);
	public List<StatusCount> getAgencyInvoiceStatusCountsForManager(Integer clientId, Integer managerId);
	public List<StatusCount> getAgencyInvoiceStatusCountsForAgency(Integer agencyId);
	
	public IntValue getShiftsToActivateCountForManager(Integer managerId);
	public IntValue getShiftsToActivateCountForAgency(Integer agencyId);
	public IntValue getShiftsOutstandingCountForManager(Integer managerId);
	public IntValue getShiftsOutstandingCountForAgency(Integer agencyId);

  public List<Booking> getBookingsForLocation(Integer locationId); 

}
