package com.helmet.persistence;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeAgy;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeUser;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.IntValue;
import com.helmet.bean.StatusCount;

public interface BookingGradeDAO {

	public List<BookingGradeUser> getBookingGradeUsersForBooking(Integer bookingId, boolean showOnlyActive);
  public List<BookingGradeAgy> getBookingGradeAgysForAgency(Integer agencyId, Boolean singleCandidate, boolean showOnlyActive);
  public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndDateRange(Integer agencyId, Date startDate, Date endDate, Boolean singleCandidate, boolean showOnlyActive);
	public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndStatus(Integer agencyId, Integer bookingGradeStatus, Boolean singleCandidate, Boolean viewed);
	public List<BookingGradeAgy> getBookingGradeAgysForAgencyUnviewed(Integer agencyId, Boolean singleCandidate);
	public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatus(Integer agencyId, Integer bookingGradeApplicantDateStatus, Boolean singleCandidate);
	
	public BookingGradeUser getBookingGradeUserForBookingAndAgency(Integer bookingId, Integer agencyId, boolean showOnlyActive);
	public BookingGrade getBookingGrade(Integer bookingGradeId);
  public BookingGrade getBookingGradeForBookingClientAgencyJobProfileGrade(Integer bookingId, Integer clientAgencyJobProfileGradeId);

	public BookingGradeAgy getBookingGradeAgy(Integer bookingGradeId, Integer agencyId);
	public BookingGradeAgyEntity getBookingGradeAgyEntity(Integer bookingGradeId, Integer agencyId);
	
	public int insertBookingGrade(BookingGrade bookingGrade, Integer auditorId);
	public int updateBookingGrade(BookingGrade bookingGrade, Integer auditorId);
	public int deleteBookingGrade(Integer bookingGradeId, Integer noOfChanges, Integer auditorId);
    public int updateBookingGradeStatus(Integer bookingGradeId, Integer noOfChanges, Integer auditorId, int status);
    public int updateBookingGradeSentTimestamp(Integer bookingGradeId, Integer noOfChanges, Integer auditorId);
	public int updateBookingGradeSentStatus(Integer bookingGradeId, Integer noOfChanges, Integer sentStatus, Integer auditorId);
	public int updateBookingGradeViewed(Integer bookingGradeId, Boolean viewed, Integer noOfChanges, Integer auditorId);

    public int deleteBookingGradesForBooking(Integer bookingId, Integer auditorId);
    public int insertBookingGrades(Integer bookingId, ClientAgencyJobProfileGrade[] bookingGrades, Integer auditorId);

	public List<BookingGrade> getBookingGradesToSend();
	
	public List<StatusCount> getBookingStatusCountsForAgency(Integer agencyId);
	public List<StatusCount> getShiftStatusCountsForAgency(Integer agencyId);
	public List<StatusCount> getBookingGradeApplicantDateStatusCountsForAgency(Integer agencyId);
	public List<StatusCount> getShiftWorkedStatusCountsForAgency(Integer agencyId);
	
	public IntValue getBookingGradeIdForAgencyAndBookingDate(Integer agencyId, Integer bookingId);

}
