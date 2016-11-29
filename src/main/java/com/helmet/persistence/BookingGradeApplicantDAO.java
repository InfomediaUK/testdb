package com.helmet.persistence;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.BookingGradeApplicant;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.IntValue;

public interface BookingGradeApplicantDAO {

	public List<BookingGradeApplicantUser> getBookingGradeApplicantUsersForBookingGradeAndAgency(Integer bookingGradeId, Integer agencyId, boolean showOnlyActive);
	public List<BookingGradeApplicantUser> getBookingGradeApplicantUsersForBookingAndClient(Integer bookingId, Integer clientId, boolean showOnlyActive);
  public BookingGradeApplicantUser getBookingGradeApplicantUser(Integer bookingGradeApplicantId);
  public BookingGradeApplicantUser getBookingGradeApplicantUser(Integer bookingGradeId, Integer applicantId);
	public BookingGradeApplicantUser getBookingGradeApplicantUserForBookingFilledSingleCandidate(Integer bookingId);
	public int insertBookingGradeApplicant(BookingGradeApplicant bookingGradeApplicant, Integer auditorId);
	public int updateBookingGradeApplicant(BookingGradeApplicant bookingGradeApplicant, Integer auditorId);

    public int updateBookingGradeApplicantStatus(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId, int status);
    public int updateBookingGradeApplicantReject(Integer bookingGradeApplicantId, String rejectText, Integer noOfChanges, Integer auditorId);
    public int updateBookingGradeApplicantRenegotiate(Integer bookingGradeApplicantId, String renegotiateText, Integer noOfChanges, Integer auditorId);
    public int updateBookingGradeApplicantActivated(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId);
    
	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntity(Integer bookingGradeApplicantId);

	public IntValue getBookingExpensesCount(Integer bookingId);
  public int updateBookingGradeApplicantChecklistCreatedTime(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId, Date createdTime);
  public IntValue getActiveBookingGradeApplicantsCountForClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId);

}
