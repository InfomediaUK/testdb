package com.helmet.persistence;

import java.sql.Date;
import java.util.List;

import com.helmet.application.NhsBookingReportGroup;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.NhsBookingGroup;
import com.helmet.bean.NhsBookingUser;

public interface NhsBookingDAO 
{
  public List<NhsBooking> getNhsBookings(Integer agencyId, boolean showOnlyActive);
  public List<NhsBookingUser> getNhsBookingUsers(Integer agencyId, boolean showOnlyActive);
  public List<NhsBookingUser> getNhsBookingUsersReadyToBook(Integer agencyId);
  public List<NhsBookingUser> getActiveNhsBookingUsersForDateRange(Integer agencyId, Date startDate, Date endDate);
  public List<NhsBookingUser> getNhsBookingUsersForAgencyDateRange(Integer agencyId, Date startDate, Date endDate, String filter);
  public List<NhsBookingUser> getNhsBookingUsersForAgencyDateRangeNhsBookingReportGroup(Integer agencyId, Date startDate, Date endDate, NhsBookingReportGroup nhsBookingReportGroup);
  public List<NhsBookingUser> getNhsBookingUsersForSubcontractInvoice(Integer agencyId, Integer subcontractInvoiceId);
  public List<NhsBookingUser> getNhsBookingUsersReadyToBookForBookingGroup(Integer agencyId, Integer siteId, Integer locationId, Integer jobProfileId, Integer bookingGroupId);
  public List<NhsBookingGroup> getNhsBookingGroupsReadyToBook(Integer agencyId); 
  public List<NhsBookingUser> getPickedNhsBookingUsersReadyToBook(Integer agencyId, String nhsBookingIds);
  public NhsBooking getNhsBooking(Integer nhsBookingId);
  public NhsBookingUser getNhsBookingUser(Integer nhsBookingId);
  public NhsBooking getActiveNhsBookingForBankReqNum(Integer agencyId, String bankReqNum); 
  public NhsBooking getNhsBookingForBankReqNum(Integer agencyId, String bankReqNum); 
	public int insertNhsBooking(NhsBooking nhsBooking, Integer auditorId);
  public int updateNhsBooking(NhsBooking nhsBooking, Integer auditorId);
  public int updateNhsBookingApplicantNotificationSent(NhsBooking nhsBooking, Integer auditorId);
  public int updateNhsBookingCommentValueApplicantPaidDate(NhsBooking nhsBooking, Integer auditorId);
  public int updateNhsBookingSubcontractInvoiceId(NhsBooking nhsBooking, Integer auditorId);
	public int deleteNhsBooking(NhsBooking nhsBooking, Integer auditorId);

  public List<NhsBooking> getNhsBookingsForLocation(Integer agencyId, Integer locationId); 
}
