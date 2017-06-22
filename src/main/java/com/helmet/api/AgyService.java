package com.helmet.api;

import java.sql.Date;
import java.util.List;

import com.helmet.application.NhsBookingReportGroup;
import com.helmet.application.agy.ApplicantSearchParameters;
import com.helmet.bean.Agency;
import com.helmet.bean.AgencyInvoice;
import com.helmet.bean.AgencyInvoiceCredit;
import com.helmet.bean.AgencyInvoiceUser;
import com.helmet.bean.AgencyInvoiceUserEntity;
import com.helmet.bean.AgyAccess;
import com.helmet.bean.Applicant;
import com.helmet.bean.ApplicantClientBooking;
import com.helmet.bean.ApplicantEntity;
import com.helmet.bean.ApplicantTrainingCourse;
import com.helmet.bean.ApplicantTrainingCourseUser;
import com.helmet.bean.ApplicantTrainingCoursesInfo;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingDateUserApplicantEntity;
import com.helmet.bean.BookingDateUserGrade;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeAgy;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicant;
import com.helmet.bean.BookingGradeApplicantDate;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.Booking;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientAgencyJobProfileUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Consultant;
import com.helmet.bean.EmailAction;
import com.helmet.bean.Grade;
import com.helmet.bean.IntValue;
import com.helmet.bean.InvoiceAgency;
import com.helmet.bean.InvoiceAgencyUser;
import com.helmet.bean.InvoiceAgencyUserEntity;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.LocationUser;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.NhsBookingGroup;
import com.helmet.bean.ApplicantPaymentUpload;
import com.helmet.bean.NhsBookingUser;
import com.helmet.bean.RecordCount;
import com.helmet.bean.Site;
import com.helmet.bean.SiteUser;
import com.helmet.bean.StatusCount;
import com.helmet.bean.SubcontractInvoice;
import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceItemHistory;
import com.helmet.bean.SubcontractInvoiceItemUser;
import com.helmet.bean.SubcontractInvoiceUser;
import com.helmet.bean.TrainingCompany;
import com.helmet.bean.TrainingCourseUser;
import com.helmet.bean.Unavailable;

public interface AgyService extends CommonService {

	public Consultant validateLogin(Consultant consultant);

	public Consultant validateSecretWord(Consultant consultant);

  	public int updateConsultantPwd(Integer consultantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
	
  	public int updateConsultantSecretWord(Integer consultantId, String newSecretWord, Integer noOfChanges, Integer auditorId);

    public int updateApplicantChecklistCreatedTime(Integer applicantId, Integer noOfChanges, Integer auditorId, Date createdTime);
    
    public int updateApplicantSecretWord(Integer applicantId, String newSecretWord, Integer noOfChanges, Integer auditorId);
    public int updateApplicantPassword(Integer applicantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
    
  	public List<AgyAccess> getActiveAgyAccessesForConsultant(Integer consultantId);

    public List<Booking> getBookingsForBookingReference(String bookingReference); 

//	public List<BookingUser> getBookingUsersForAgency(Integer agencyId);
//	public List<BookingUserAgy> getBookingUserAgysForAgency(Integer agencyId);
//	public List<BookingGradeAgy> getBookingGradeAgysForAgency(Integer agencyId);

//	public BookingUserEntityAgy getBookingUserEntityAgy(Integer bookingId, Integer agencyId);

	public Consultant getConsultant(Integer consultantId);
	
	public int updateConsultant(Consultant consultant, Integer consultantId);
	public int updateConsultantShowPageHelp(Consultant consultant, Integer consultantId);

	public Agency getAgencyForCode(String agencyCode);

  public List<Applicant> getApplicantsForAgency(Integer agencyId);
  public List<Applicant> getApplicantsForAgencySearch(Integer agencyId, ApplicantSearchParameters applicantSearchParameters, boolean showOnlyActive);
  public List<Applicant> getApplicantsForAgencyInLastNameGroup(Integer agencyId);
  public List<Applicant> getApplicantsForAgencyInLastNameGroup(Integer agencyId, String indexLetter);
  public List<Applicant> getApplicantsForNhsStaffName(Integer clientId, String nhsStaffName);
  public List<Applicant> getWorkingApplicantsForAgency(Integer agencyId);
  public List<Applicant> getApplicantsForAgencyNew(Integer agencyId, Date dateToday);
  public List<Applicant> getApplicantsForAgencyCrbAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyDbsAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyFitToWorkAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyRegistrationAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyIdDocumentAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyTrainingAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<ApplicantEntity> getApplicantEntitiesForAgencyTrainingAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyVisaAboutToExpire(Integer agencyId, Date dateToCheck);

  public List<Applicant> getApplicantsForAgencyReference1NotSatisfied(Integer agencyId, Date dateToday);
  public List<Applicant> getApplicantsForAgencyReference2NotSatisfied(Integer agencyId, Date dateToday);
  public List<Applicant> getApplicantsForAgencyDrivingLicenseAboutToExpire(Integer agencyId, Date dateToCheck);
  public List<Applicant> getApplicantsForAgencyUnarchived(Integer agencyId);
  public List<Applicant> getApplicantsForAgencyRecentlyCompliant(Integer agencyId);
  public List<Applicant> getApplicantsForAgencyRecentProspect(Integer agencyId);
  
  public List<Applicant> getApplicantsForAgencyAndNotForBookingGrade(Integer agencyId, Integer bookingGradeId);
  public List<Applicant> getApplicantsForAgencyAndNotForBookingGradeInLastNameGroup(Integer agencyId, Integer bookingGradeId, String indexLetter);
	public Applicant getApplicant(Integer applicantId);
  public ApplicantEntity getApplicantEntity(Integer applicantId, Date startDate, Date endDate);
  public List<ApplicantClientBooking> getApplicantClientBookings(Integer applicantId, Integer clientId, Integer agencyId, Date searchDate); 
  
	public int insertApplicant(Applicant applicant, Integer auditorId);
	public int updateApplicant(Applicant applicant, Integer auditorId);
  public int archiveApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId);
  public int unarchiveApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId);
  public int deleteApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId);

  public BookingGradeApplicantUser getBookingGradeApplicantUser(Integer bookingGradeApplicantId);
  public BookingGradeApplicantUser getBookingGradeApplicantUser(Integer bookingGradeId, Integer applicantId);
  // Called from NhsBookingsBookTask.
  public int updateBookingGradeStatus(Integer bookingGradeId, Integer noOfChanges, Integer auditorId, int status);

	public int insertBookingGradeApplicant(BookingGradeApplicant bookingGradeApplicant, BookingGradeApplicantDate[] bookingGradeApplicantDates, Integer auditorId);
	public int updateBookingGradeApplicant(BookingGradeApplicant bookingGradeApplicant, BookingGradeApplicantDate[] bookingGradeApplicantDates, Integer auditorId);
	
    public int updateBookingGradeAsViewed(Integer bookingGradeId, Integer noOfChanges, Integer auditorId);
    public int updateBookingGradeAsNotViewed(Integer bookingGradeId, Integer noOfChanges, Integer auditorId);
	
    public BookingGradeAgy getBookingGradeAgy(Integer bookingGradeId, Integer agencyId);
    public BookingGradeAgyEntity getBookingGradeAgyEntity(Integer bookingGradeId, Integer agencyId);
	public List<BookingGradeAgy> getBookingGradeAgysForAgency(Integer agencyId, Boolean singleCandidate);
  public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndDateRange(Integer agencyId, Date startDate, Date endDate, Boolean singleCandidate, boolean showOnlyActive); 
	public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndStatus(Integer agencyId, Integer bookingGradeStatus, Boolean singleCandidate, Boolean viewed);
	public List<BookingGradeAgy> getBookingGradeAgysForAgencyUnviewed(Integer agencyId, Boolean singleCandidate);
	public List<BookingGradeAgy> getBookingGradeAgysForBookingGradeApplicantDateAgencyAndStatus(Integer agencyId, Integer bookingGradeApplicantDateStatus, Boolean singleCandidate);
	
	public List<BookingDateUser> getBookingDateUsersForBookingAndAgency(Integer bookingId, Integer agencyId);

	public int updateBookingGradeApplicantSubmit(Integer bookingId, Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId);
	public int updateBookingGradeApplicantAccept(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId);
	public int updateBookingGradeApplicantRefuse(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId);
	public int updateBookingGradeApplicantWithdraw(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId);
  public int updateBookingGradeApplicantChecklistCreatedTime(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId, Date nowDate); 
	
	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntity(Integer bookingGradeApplicantId);
	
  public BookingGrade getBookingGrade(Integer bookingGradeId);
  // NOTE. There can be more that ONE BookingGrade for a Booking so ONLY use this when you know for a fact
  //       that there is only one. Eg. For an NHS Booking.
  public BookingGrade getBookingGradeForBookingClientAgencyJobProfileGrade(Integer bookingId, Integer clientAgencyJobProfileGradeId);

//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgency(Integer agencyId, Boolean singleCandidate, Boolean viewed);
//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndStatus(Integer agencyId, Boolean singleCandidate, Boolean viewed,
//            Integer bookingDateStatusId);

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
  public RecordCount getBookingDatesUserApplicantsForAgencyNotOnBackingReportCount(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
      Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId); 
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyOnBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, Integer offset);
  public RecordCount getBookingDatesUserApplicantsForAgencyOnBackingReportCount(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
      Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId); 

  public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForAgency(Integer agencyId, 
			Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
        	Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, 
        	Date fromDate, Date toDate);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsToActivateForAgency(Integer agencyId);

	public List<BookingDateUserGrade> getBookingDateUserGradesForAgency(Integer agencyId, Boolean singleCandidate, Boolean viewed);
	public List<BookingDateUserGrade> getBookingDateUserGradesForAgencyAndStatus(Integer agencyId, Boolean singleCandidate, Boolean viewed,
			Integer bookingGradeStatusId);

	public List<StatusCount> getBookingStatusCountsForAgency(Integer agencyId);
	public List<StatusCount> getShiftStatusCountsForAgency(Integer agencyId);
	public List<StatusCount> getBookingGradeApplicantDateStatusCountsForAgency(Integer agencyId);
	public List<StatusCount> getShiftWorkedStatusCountsForAgency(Integer agencyId);
	public List<StatusCount> getAgencyInvoiceStatusCountsForAgency(Integer agencyId);
  	public IntValue getShiftsToActivateCountForAgency(Integer agencyId);
  	public IntValue getShiftsOutstandingCountForAgency(Integer agencyId);
	
	public IntValue getBookingGradeIdForAgencyAndBookingDate(Integer agencyId, Integer bookingId);

  public Client getClientForReference(String reference);

  public List<ClientUser> getClientUsersForAgency(Integer agencyId);

  public List<ClientUser> getNhsClientUsersForAgency(Integer agencyId);

  public List<SiteUser> getSiteUsersForAgency(Integer agencyId, Integer clientId);

  // NEW -->
  public List<Site> getSitesForNhsLocation(Integer clientId, String nhsLocation);

  public List<Location> getLocationsForNhsWard(Integer siteId, String nhsWard);
  // <-- NEW

	public List<LocationUser> getLocationUsersForAgency(Integer agencyId, Integer clientId, Integer siteId);

	public List<JobProfileUser> getJobProfileUsersForAgency(Integer agencyId, Integer clientId, Integer siteId, Integer locationId);

	public JobProfileUser getJobProfileUser(Integer jobProfileId);
	
//	public List<LocationUser> getLocationUsersForAgencyForSite(Integer agencyId, Integer siteId);
//
//	public List<JobProfileUser> getJobProfileUsersForAgencyForLocation(Integer agencyId, Integer locationId);
//
//	public List<JobProfileUser> getJobProfileUsersForAgencyForSite(Integer agencyId, Integer siteId);

	public InvoiceAgencyUser getInvoiceAgencyUserForInvoiceForAgency(Integer invoiceId, Integer agencyId);

	public InvoiceAgencyUserEntity getInvoiceAgencyUserEntityForInvoiceForAgency(Integer invoiceId, Integer agencyId);

	public int updateInvoiceAgency(InvoiceAgency invoiceAgency, Integer auditorId);
	
	public ClientAgencyJobProfileGrade getClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId);
	
	public List<ClientAgencyJobProfileGradeUser> getClientAgencyJobProfileUsersForClientAgencyJobProfile(Integer clientAgencyJobProfileId);
	
	public AgencyInvoiceUser getAgencyInvoiceUser(Integer agencyInvoiceId);

	public AgencyInvoiceUserEntity getAgencyInvoiceUserEntity(Integer agencyInvoiceId);
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndBookingDates(Integer agencyId, String bookingDateIdStrs);

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgency(Integer applicantId, Integer agencyId);
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(Integer applicantId, Integer agencyId, Date fromDate, Date toDate);
  // NEW ->
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndDateRange(Integer agencyId, Date fromDate, Date toDate);
  // NEW <-
    public int insertAgencyInvoice(Integer agencyId, String bookingDateIdStrs, Integer auditorId);

    public boolean getBookingDatesAlreadyInvoiced(Integer agencyId, String bookingDateIdStrs);
    
    public boolean getBookingDatesAlreadyAuthorized(Integer agencyId, String bookingDateIdStrs);
    
	public int updateAgencyInvoice(AgencyInvoice agencyInvoice, Integer auditorId);

	public int updateAgencyInvoiceTimesheet(AgencyInvoice agencyInvoice, Integer auditorId);

    public int updateAgencyInvoiceCredited(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId, String reasonText);
    
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForAgencyAndStatus(Integer agencyId, Integer status);

	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForAgency(Integer agencyId, Integer agencyInvoiceId, 
			Integer clientId, Integer status, Date fromDate, Date toDate);
	
  public List<Grade> getGradesForJobProfile(Integer jobProfileId);
  // NEW -->
  public List<JobProfileUser> getJobProfileUsersForNhsAssignment(Integer clientId, String nhsAssignment);
  // <-- NEW
    public List<ClientAgencyJobProfileUser> getClientAgencyJobProfileUsersForJobProfileAndAgency(Integer jobProfileId, Integer agencyId);
    
    public List<ClientAgencyJobProfileGrade> getClientAgencyJobProfileGradesForJobProfileAndAgency(Integer jobProfileId, Integer agencyId);

    public List<ClientAgencyJobProfileGradeUser> getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(Integer clientId, Integer agencyId, Integer jobProfileId);

    public ClientAgencyJobProfileGradeUser getClientAgencyJobProfileGradeUser(Integer clientAgencyJobProfileGradeId);

	public BookingDateUserApplicant getBookingDateUserApplicantForAgencyAndBookingDate(Integer agencyId, Integer bookingDateId);
	
	public AgencyInvoiceCredit getAgencyInvoiceCredit(Integer agencyInvoiceCreditId);

  public List<Unavailable> getActiveUnavailablesInDateRange(Integer agencyId, java.util.Date fromDate, java.util.Date toDate);

  public List<Unavailable> getUnavailablesForApplicantInDateRange(Integer agencyId, Integer applicantId, boolean showOnlyActive, java.util.Date fromDate, java.util.Date toDate);

  public int insertUnavailable(Unavailable unavailable, Integer auditorId);
  
  public int updateUnavailable(Unavailable unavailable, Integer auditorId);

  public BookingDate getBookingDate(Integer bookingDateId); 
  
  public int insertNhsBooking(NhsBooking nhsBooking, Integer auditorId);
  public int updateNhsBooking(NhsBooking nhsBooking, Integer auditorId);
  public int updateNhsBookingApplicantNotificationSent(NhsBooking nhsBooking, Integer auditorId);
  public int updateNhsBookingCommentValueApplicantPaidDate(NhsBooking nhsBooking, Integer auditorId);
  public int updateNhsBookingSubcontractInvoiceId(NhsBooking nhsBooking, Integer auditorId);
  public int deleteNhsBooking(NhsBooking nhsBooking, Integer auditorId);
  public NhsBooking getNhsBookingForBankReqNum(Integer agencyId, String bankReqNum); 
  public ApplicantPaymentUpload getApplicantPaymentUploadForBankReqNum(Integer agencyId, String bankReqNum); 
  public NhsBooking getActiveNhsBookingForBankReqNum(Integer agencyId, String bankReqNum); 
  public NhsBooking getNhsBooking(Integer nhsBookingId); 
  public NhsBookingUser getNhsBookingUser(Integer nhsBookingId); 
  public List<NhsBookingUser> getNhsBookingUsersReadyToBook(Integer agencyId); 
  public List<NhsBookingUser> getActiveNhsBookingUsersForDateRange(Integer agencyId, Date startDate, Date endDate); 
  public List<NhsBookingUser> getNhsBookingUsersForAgencyDateRange(Integer agencyId, Date startDate, Date endDate, String filter); 
  public List<NhsBookingUser> getNhsBookingUsersForAgencyDateRangeNhsBookingReportGroup(Integer agencyId, Date startDate, Date endDate, NhsBookingReportGroup nhsBookingReportGroup); 
  public List<NhsBookingUser> getNhsBookingUsersForSubcontractInvoice(Integer agencyId, Integer subcontractInvoiceId); 
  public List<NhsBookingUser> getNhsBookingUsersReadyToBookForBookingGroup(Integer agencyId, Integer siteId, Integer locationId, Integer jobProfileId, Integer bookingGroupId); 
  public List<NhsBookingGroup> getNhsBookingGroupsReadyToBook(Integer agencyId); 
  public List<NhsBookingUser> getPickedNhsBookingUsersReadyToBook(Integer agencyId, String nhsBookingIds); 
//
//  public NhsBooking getActiveNhsBookingsForLocationLimit1(String location); 
//
  public int insertSubcontractCreditNote(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int insertSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int updateSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersInList(Integer agencyId, String subcontractInvoiceIdList); 
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersForRemittanceAdvice(Integer agencyId, String remittanceAdvice); 

  public int updateSubcontractInvoiceValue(SubcontractInvoice subcontractInvoice, Integer auditorId);

  public int updateSubcontractInvoiceSentDate(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int updateSubcontractInvoicePaidDate(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int updateSubcontractInvoiceRelatedSubcontractInvoiceId(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public int deleteSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId);
  public SubcontractInvoice getSubcontractInvoice(Integer subcontractInvoiceId); 
  public SubcontractInvoiceUser getSubcontractInvoiceUser(Integer subcontractInvoiceId); 

  public SubcontractInvoiceItemUser getSubcontractInvoiceItemUser(Integer subcontractInvoiceItemId); 
  
  public int deleteSubcontractInvoiceItem(SubcontractInvoiceItem subcontractInvoiceItem, Integer auditorId);
  public int insertSubcontractInvoiceItem(SubcontractInvoiceItem subcontractInvoiceItem, Integer auditorId);
  public List<SubcontractInvoiceItem> getSubcontractInvoiceItemsForSubcontractInvoice(Integer subcontractInvoiceId); 
  public List<SubcontractInvoiceItemUser> getSubcontractInvoiceItemUsersForSubcontractInvoice(Integer subcontractInvoiceId); 
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersForAgency(Integer fromAgencyId, Integer subcontractInvoiceId, 
      Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer status, Date fromDate, Date toDate);

  public int updateSubcontractInvoiceItem(SubcontractInvoiceItem subcontractInvoiceItem, Integer auditorId);
  public List<SubcontractInvoiceItemHistory> getSubcontractInvoiceItemHistoryForBankReqNum(String bankReqNum); 
  
  public List<BookingDate> getBookingDatesForNhsBackingReport(String nhsBackingReport);
  public Integer getBookingDatePagingLimit();
  public Integer getBookingDatePagingGroupSize();

  public EmailAction getEmailAction(Integer emailActionId);

  public ApplicantTrainingCourse getApplicantTrainingCourse(Integer applicantTrainingCourseId);
  public ApplicantTrainingCourseUser getApplicantTrainingCourseUser(Integer applicantTrainingCourseId);
  public ApplicantTrainingCoursesInfo getApplicantTrainingCoursesInfoForApplicant(Integer applicantId);
  public List<ApplicantTrainingCourseUser> getApplicantTrainingCourseUsersForApplicant(Integer applicantId, boolean showOnlyActive);
  public int deleteApplicantTrainingCourse(Integer applicantTrainingCourseId, Integer noOfChanges, Integer auditorId);
  public List<TrainingCourseUser> getTrainingCourseUsersForDisciplineCategory(Integer disciplineCategoryId); 
  public List<TrainingCourseUser> getTrainingCoursesForApplicantSelect(Integer disciplineCategoryId); 
  public int updateApplicantTrainingCourse(ApplicantTrainingCourse applicantTrainingCourse, Integer auditorId);
  public List<TrainingCompany> getTrainingCompaniesForTrainingCourse(Integer trainingCourseId);

}
