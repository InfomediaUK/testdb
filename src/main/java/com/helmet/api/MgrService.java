package com.helmet.api;

import java.sql.Date;
import java.util.List;

import com.helmet.bean.AgencyInvoiceUser;
import com.helmet.bean.AgencyInvoiceUserEntity;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingDateUserApplicantEntity;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeApplicantDateUser;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.BookingUser;
import com.helmet.bean.BookingUserEntity;
import com.helmet.bean.BookingUserEntityMgr;
import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgencyGrade;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientAgencyJobProfileUser;
import com.helmet.bean.ClientReEnterPwdUser;
import com.helmet.bean.DressCode;
import com.helmet.bean.Expense;
import com.helmet.bean.Grade;
import com.helmet.bean.IntValue;
import com.helmet.bean.InvoiceEntity;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationJobProfileUser;
import com.helmet.bean.LocationManagerJobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.Manager;
import com.helmet.bean.MgrAccess;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Shift;
import com.helmet.bean.SiteUser;
import com.helmet.bean.StatusCount;
import com.helmet.bean.Uplift;

public interface MgrService extends CommonService {

	// login
	public Manager validateLogin(Manager manager);

	public Manager validateSecretWord(Manager manager);

	public int updateManagerShowPageHelp(Manager manager, Integer auditorId);
	
	public List<LocationManagerJobProfileUser> getLocationManagerJobProfileUsersForManager(Integer managerId);
	
	public List<SiteUser> getSiteUsersForManager(Integer managerId);

	public List<LocationUser> getLocationUsersForManager(Integer managerId);

	public List<LocationUser> getLocationUsersForManagerForSite(Integer managerId, Integer siteId);

    public JobProfileUser getJobProfileUser(Integer jobProfileId);

	public List<JobProfileUser> getJobProfileUsersForManagerForLocation(Integer managerId, Integer locationId);

	public List<JobProfileUser> getJobProfileUsersForManagerForSite(Integer managerId, Integer siteId);

	public List<JobProfileUser> getJobProfileUsersForManager(Integer managerId);
	
	public List<Grade> getGradesForJobProfile(Integer jobProfileId);

    public List<ClientAgencyJobProfileUser> getClientAgencyJobProfileUsersForJobProfile(Integer jobProfileId);
    
    public Grade getGrade(Integer gradeId);

    public List<ClientAgencyJobProfileGrade> getClientAgencyJobProfileGradesForJobProfile(Integer jobProfileId); 

    public ClientAgencyJobProfileGradeUser getClientAgencyJobProfileGradeUser(Integer clientAgencyJobProfileGradeId);
    
	public List<Expense> getExpensesForLocation(Integer locationId);

    public Expense getExpense(Integer expenseId);

	public int deleteBooking(Integer bookingId, Integer noOfChanges, Integer auditorId);
	
	public int updateBookingGradeOpen(Integer bookingGradeId, Integer noOfChanges, Integer auditorId);
	
	public List<BookingUser> getBookingUsersForManagerAndBooking(Integer managerId, Integer bookingId);

	public List<BookingUser> getBookingUsersForManager(Integer managerId, Boolean singleCandidate);

	public List<BookingUser> getBookingUsersForManager(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId);

	public List<BookingUser> getBookingUsersForManagerAndStatus(Integer managerId, Integer bookingStatus, Boolean singleCandidate);

	public List<BookingUser> getBookingUsersForManagerAndStatus(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId);

	public List<BookingUser> getBookingUsersForManagerAndStatusAndDateRange(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Date fromDate, Date toDate);
	
	public List<BookingUser> getBookingUsersForManagerAndStatusAndDateRange(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
	
	public List<BookingUser> getBookingUsersForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
	

	public List<BookingUser> getBookingUsersForManagerAndWorkedStatus(Integer managerId, Integer workedStatus);

	public List<BookingUser> getBookingUsersForManagerAndBookingGradeApplicantDateStatus(Integer managerId, Integer bookingGradeApplicantDateStatus);


	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, 
			Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingDateStatus, Integer workedStatus,                                                  
			Boolean singleCandidate, Boolean activated, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
	
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBooking(Integer managerId, Integer bookingId, Integer bookingDateStatus, Integer workedStatus);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingAndDateRange(Integer managerId, Integer bookingId, Integer bookingDateStatus, Integer workedStatus, Date fromDate, Date toDate);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingDate(Integer managerId, Integer bookingDateId);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingDates(Integer managerId, String bookingDateIdStrs);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingGradeApplicant(Integer managerId, Integer bookingGradeApplicantId);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatus(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatusAndDateRange(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatus(Integer managerId, Integer workedStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRange(Integer managerId, Integer workedStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, Boolean singleCandidate);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatus(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatusAndDateRange(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Date fromDate, Date toDate);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatus(Integer managerId, Integer workedStatus, Boolean singleCandidate);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRange(Integer managerId, Integer workedStatus, Boolean singleCandidate, Date fromDate, Date toDate);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Date fromDate, Date toDate);
	
	public BookingUser getBookingUser(Integer bookingId);

	public BookingUserEntityMgr getBookingUserEntityMgr(Integer bookingId, Integer managerId);

	public BookingUserEntityMgr getBookingUserSummary(Integer bookingId, Integer managerId);

	public BookingUserEntityMgr getBookingUserShifts(Integer bookingId, Integer managerId);

	public BookingUserEntityMgr getBookingUserApplicants(Integer bookingId, Integer managerId);

	public BookingUserEntityMgr getBookingUserGrades(Integer bookingId, Integer managerId);

	public BookingGrade getBookingGrade(Integer bookingGradeId);

	public List<DressCode> getDressCodesForLocation(Integer locationId);

    public DressCode getDressCode(Integer dressCodeId);
    
  	public List<MgrAccess> getActiveMgrAccessesForManager(Integer managerId);
    
	public List<BookingGrade> getBookingGradesToSend();

	public List<LocationJobProfileUser> getLocationJobProfileUsersForManager(Integer managerId);
	
	public List<LocationJobProfileUser> getLocationJobProfileUsersForManagerForLocation(Integer managerId, Integer locationId);
	
    public LocationJobProfileUser getLocationJobProfileUserForManager(Integer managerId, Integer locationJobProfileId);

    public List<BudgetTransaction> getBudgetTransactionsForLocationJobProfile(Integer locationJobProfileId);
	
	public Client getClientForCode(String clientCode);

	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntity(Integer bookingGradeApplicantId, Integer managerId);
	
	public int updateBookingGradeApplicantReject(Integer bookingGradeApplicantId, String rejectText, Integer noOfChanges, Integer auditorId);

	public int updateBookingGradeApplicantRenegotiate(Integer bookingGradeApplicantId, String renegotiateText, Integer noOfChanges, Integer auditorId);

	public int updateBookingGradeApplicantOffer(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId);

 	public int updateBookingDateRejected(BookingDate bookingDate, Integer auditorId);
 	
 	public int updateBookingDateInvoiced(BookingDate bookingDate, Integer auditorId);

 	public BookingGradeApplicantDateUser getBookingGradeApplicantDateUser(Integer bookingGradeApplicantDateId);

	public BookingDate getBookingDate(Integer bookingDateId); 
	
	public BookingDateUserApplicant getBookingDateUserApplicantForManagerAndBookingDate(Integer managerId, Integer bookingDateId); 

    public BookingDateUserApplicantEntity getBookingDateUserApplicantEntityForManagerAndBookingDate(Integer managerId, Integer bookingDateId);
	
	public List<StatusCount> getBookingStatusCountsForManager(Integer managerId);

	public List<StatusCount> getShiftStatusCountsForManager(Integer managerId);

	public List<StatusCount> getBookingWorkedStatusCountsForManager(Integer managerId);
	
	public List<StatusCount> getShiftWorkedStatusCountsForManager(Integer managerId);
	
	public List<StatusCount> getBookingGradeApplicantDateStatusCountsForManager(Integer managerId);
	
	public List<StatusCount> getAgencyInvoiceStatusCountsForManager(Integer clientId, Integer managerId);

    public int insertBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId);
    
    public int insertInvoice(Integer clientId, String bookingDateIdStrs, Integer auditorId);
    
 	public InvoiceEntity getInvoiceEntity(Manager manager, Integer invoiceId);
   
  	public List<ClientReEnterPwdUser> getClientReEnterPwdUsersForClient(Integer clientId);
  	
  	public IntValue getShiftsToActivateCountForManager(Integer managerId);
  	 
  	public IntValue getShiftsOutstandingCountForManager(Integer managerId);
  	 
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsToActivateForManager(Integer managerId);

	public AgencyInvoiceUserEntity getAgencyInvoiceUserEntityForManager(Integer agencyInvoiceId, Integer managerId);

    public int updateAgencyInvoiceAuthorized(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId);

    public int updateAgencyInvoicePaid(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId);
	
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForManagerAndStatus(Integer clientId, Integer managerId, Integer status);
    
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForManagerAndAgencyInvoices(Integer clientId, Integer managerId, String agencyInvoiceIdStrs);

	public int updateAgencyInvoicesAuthorized(String agencyInvoiceIdStrs, Integer auditorId);

	public int updateAgencyInvoicesMarkAsPaid(String agencyInvoiceIdStrs, Integer auditorId);

}
