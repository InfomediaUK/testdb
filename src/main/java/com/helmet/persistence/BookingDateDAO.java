package com.helmet.persistence;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingDateUserApplicantEntity;
import com.helmet.bean.BookingDateUserGrade;
import com.helmet.bean.RecordCount;

public interface BookingDateDAO {

  public List<BookingDate> getBookingDatesForNhsBackingReport(String backingReport);
  public List<BookingDate> getBookingDatesForBooking(Integer bookingId, boolean showOnlyActive);
  public List<BookingDate> getBookingDatesForBookingAndAgency(Integer bookingId, Integer agencyId, boolean showOnlyActive);
	public List<BookingDate> getBookingDatesForBookingDates(String bookingDateIdStrs);

	public List<BookingDateUser> getBookingDateUsersForBooking(Integer bookingId, boolean showOnlyActive);
	public List<BookingDateUser> getBookingDateUsersForBookingAndAgency(Integer bookingId, Integer agencyId, boolean showOnlyActive);
	
	public BookingDate getBookingDate(Integer bookingDateId);
	public BookingDateUser getBookingDateUser(Integer bookingDateId);
	
	public BookingDateUserApplicant getBookingDateUserApplicantForManagerAndBookingDate(Integer managerId, Integer bookingDateId);
	public BookingDateUserApplicantEntity getBookingDateUserApplicantEntityForManagerAndBookingDate(Integer managerId, Integer bookingDateId);
	
	public int insertBookingDate(BookingDate bookingDate, Integer auditorId);
	public int updateBookingDate(BookingDate bookingDate, Integer auditorId);
	public int updateBookingDateStatus(Integer bookingDateId, Integer noOfChanges, Integer auditorId, int status);
	public int updateBookingDateOffered(Integer bookingDateId, Integer noOfChanges, Integer auditorId, Integer bookingGradeApplicantDateId);
	public int updateBookingDateFilled(Integer bookingDateId, Integer noOfChanges, Integer auditorId, Integer bookingGradeApplicantDateId);
	public int updateBookingDateRefused(Integer bookingDateId, Integer noOfChanges, Integer auditorId);
	public int updateBookingDateCancel(Integer bookingDateId, String cancelText, Integer noOfChanges, Integer auditorId);
	public int updateBookingDateActivated(Integer bookingDateId, Integer noOfChanges, Integer auditorId);
	public int updateBookingDateWorked(BookingDate bookingDate, Integer auditorId);
	public int updateBookingDateSubmitted(BookingDate bookingDate, Integer auditorId);
    public int updateBookingDateRejected(Integer bookingDateId, String rejectText, Integer noOfChanges, Integer auditorId);
    public int updateBookingDateAuthorized(Integer bookingDateId, Integer noOfChanges, Integer auditorId);
    public int updateBookingDateInvoiced(Integer bookingDateId, Integer invoiceId, Integer noOfChanges, Integer auditorId);
	public int updateBookingDateExpenses(Integer bookingDateId, Integer noOfChanges, BigDecimal value, BigDecimal vatValue, Integer auditorId);
	public int updateBookingDateWithdrawn(BookingDate bookingDate, Integer auditorId);
	
	public int updateBookingDatesCreditedForAgencyInvoice(Integer agencyInvoiceId, Integer auditorId, Integer agencyInvoiceCreditId);
  public int updateBookingDateBackingReport(BookingDate bookingDate, Integer auditorId); 
	
	public int deleteBookingDate(Integer bookingDateId, Integer noOfChanges, Integer auditorId);
	
    public int deleteBookingDatesForBooking(Integer bookingId, Integer auditorId);
    public int insertBookingDates(Integer bookingId, BookingDate[] bookingDates, Integer auditorId);
    
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, 
			Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingDateStatus, Integer workedStatus,                                                  
			Boolean singleCandidate, Boolean activated, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
    
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBooking(Integer managerId, Integer bookingId);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBooking(Integer managerId, Integer bookingId, Integer bookingDateStatus, Integer workedStatus);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingAndDateRange(Integer managerId, Integer bookingId, Integer bookingDateStatus, Integer workedStatus, Date fromDate, Date toDate);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingDate(Integer managerId, Integer bookingDateId);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingDates(Integer managerId, String bookingDateIdStrs);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingGradeApplicant(Integer managerId, Integer bookingGradeApplicantId);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatus(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatusAndDateRange(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatus(Integer managerId, Integer workedStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRange(Integer managerId, Integer workedStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndInvoice(Integer managerId, Integer invoiceId);
	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForManagerAndInvoice(Integer managerId, Integer invoiceId);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForBookingGradeApplicant(Integer bookingGradeApplicantId);
	
//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgency(Integer agencyId, Boolean singleCandidate, Boolean viewed);
//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndStatus(Integer agencyId, Boolean singleCandidate, Boolean viewed, Integer bookingDateStatus);
//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndBookingGradeApplicant(Integer agencyId, Boolean singleCandidate, Boolean viewed, Integer bookingGradeApplicantId);

  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgency(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId,
          Date fromDate, Date toDate);
  // This is the same as the above method except the data is sorted first by Client Name, Site Name and Location Name.
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyByClientSiteLocation(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId,
          Date fromDate, Date toDate);
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId,
          Date fromDate, Date toDate, String backingReportName);
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyNotOnBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, Integer offset);
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyOnBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, Integer offset);
	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForAgency(Integer agencyId, 
			Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
        	Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId,
        	Date fromDate, Date toDate);
	
	public List<BookingDateUserGrade> getBookingDateUserGradesForAgency(Integer agencyId, Boolean singleCandidate, Boolean viewed);
	public List<BookingDateUserGrade> getBookingDateUserGradesForAgencyAndStatus(Integer agencyId, Boolean singleCandidate, Boolean viewed, Integer bookingGradeStatus);

	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForAgencyAndInvoice(Integer agencyId, Integer invoiceId);
	
	public BookingDateUserApplicant getBookingDateUserApplicantForApplicantAndBookingDate(Integer bookingDateId);

	public BookingDateUserApplicant getBookingDateUserApplicantForAgencyAndBookingDate(Integer agencyId, Integer bookingDateId);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndBooking(Integer agencyId, Integer bookingId);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAdmin(Date fromDate, Date toDate);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgency(Integer applicantId, Integer agencyId);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(Integer applicantId, Integer agencyId, Date fromDate, Date toDate);
  // NEW ->
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndDateRange(Integer agencyId, Date fromDate, Date toDate);
  // NEW <-
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsToActivateForManager(Integer managerId);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsToActivateForAgency(Integer agencyId);

	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForAgencyAndAgencyInvoice(Integer agencyId, Integer agencyInvoiceId);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndBookingDates(Integer agencyId, String bookingDateIdStrs);

	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForManagerAndAgencyInvoice(Integer managerId, Integer agencyInvoiceId);

	public int updateBookingDateOvertime(BookingDateUserApplicant bookingDate, Integer auditorId);
	
  public Integer getPagingLimit();
  public void setPagingLimit(Integer pagingLimit);
  public Integer getPagingGroupSize();
  public void setPagingGroupSize(Integer pagingGroupSize);
  public RecordCount getBookingDatesUserApplicantsForAgencyNotOnBackingReportCount(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
      Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId); 

  public RecordCount getBookingDatesUserApplicantsForAgencyOnBackingReportCount(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
      Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId); 

}
