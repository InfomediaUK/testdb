package com.helmet.api;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.Agency;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.Applicant;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateExpense;
import com.helmet.bean.BookingDateHour;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingGradeApplicant;
import com.helmet.bean.BookingGradeApplicantDateUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.BookingUser;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgency;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.Uplift;
import com.helmet.bean.UpliftMinute;

public interface AppService {

	public BookingGradeApplicant validateLogin(BookingGradeApplicant bookingGradeApplicant, String login, String pwd);
	public Applicant validateSecretWord(Applicant applicant);

	public Applicant getApplicant(Integer applicantId);
	public int updateApplicant(Applicant applicant, Integer auditorId);
  	public int updateApplicantSecretWord(Integer applicantId, String newSecretWord, Integer noOfChanges, Integer auditorId);
	
	public Booking getBooking(Integer bookingId);

	public Client getClientForBookingGradeApplicant(Integer bookingGradeApplicantId);
  	public Agency getAgency(Integer agencyId);
	public ClientAgency getClientAgencyForClientAndAgency(Integer clientId, Integer agencyId, Date bookingDate);

	public ClientUser getClientUser(Integer clientId);
  	public AgencyUser getAgencyUser(Integer agencyId);
  	public JobProfileUser getJobProfileUser(Integer jobProfileId);
  	public BookingUser getBookingUser(Integer bookingId);
	
	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntity(Integer bookingGradeApplicantId, Boolean showOnlyOutstanding);
	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntityForDateRange(Integer bookingGradeApplicantId, Date startDate, Date endDate);
	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntityAndBookingDates(Integer bookingGradeApplicantId, String bookingDateIdStrs);
	public BookingGradeApplicantDateUser getBookingGradeApplicantDateUser(Integer bookingGradeApplicantDateId);
	
	public BookingDateUser getBookingDateUser(Integer bookingDateId);
 	public BookingDateUserApplicant getBookingDateUserApplicantForApplicantForBookingDate(Integer bookingDateId);

	public BookingDateExpense getBookingDateExpense(Integer bookingDateExpenseId);
	public BookingExpense getBookingExpense(Integer bookingExpenseId);
 	
 	public int updateBookingDateWorked(BookingDate bookingDate, List<BookingDateHour> bookingDateHours, Integer auditorId);
 	public int updateBookingDateSubmitted(BookingDate bookingDate, Integer auditorId);
 	public int updateBookingDatesSubmitted(String bookingDateIdStrs, Integer auditorId);
 	public int updateBookingDateWithdrawn(BookingDate bookingDate, Integer auditorId);

 	public int insertBookingDateExpense(BookingDateExpense bookingDateExpense, Integer auditorId);
 	public int updateBookingDateExpense(BookingDateExpense bookingDateExpense, Integer auditorId);
 	public int deleteBookingDateExpense(Integer bookingDateExpenseId, Integer noOfChanges, Integer auditorId);
 	
	public List<BookingExpense> getBookingExpensesForBooking(Integer bookingId);
	public List<BookingExpense> getBookingExpensesForBookingNotForBookingDate(Integer bookingId, Integer bookingDateId);
 	
	public List<PublicHoliday> getPublicHolidaysForClient(Integer clientId);
    
	public List<Uplift> getUpliftsForClient(Integer clientId);
  public List<UpliftMinute> getUpliftMinutesForClient(Integer clientId);
  public void loadUpliftMinutesIntoUplifts(Integer clientId, List<Uplift> uplifts);

  public NhsBooking getActiveNhsBookingForBankReqNum(Integer agencyId, String bankReqNum);
  public NhsBooking getNhsBookingForBankReqNum(Integer agencyId, String bankReqNum);

}