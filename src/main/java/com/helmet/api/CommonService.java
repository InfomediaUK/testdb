package com.helmet.api;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.helmet.bean.Agency;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.AreaOfSpeciality;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingUser;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgency;
import com.helmet.bean.ClientAgencyGrade;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyUser;
import com.helmet.bean.ClientAgencyUserEntity;
import com.helmet.bean.ClientUser;
import com.helmet.bean.CompliancyTest;
import com.helmet.bean.Country;
import com.helmet.bean.DisciplineCategory;
import com.helmet.bean.DisciplineCategoryUser;
import com.helmet.bean.DressCode;
import com.helmet.bean.EmailAction;
import com.helmet.bean.Expense;
import com.helmet.bean.GeographicalRegion;
import com.helmet.bean.Grade;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.Location;
import com.helmet.bean.LocationEntity;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationJobProfileUser;
import com.helmet.bean.LocationManagerJobProfile;
import com.helmet.bean.LocationManagerJobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.Manager;
import com.helmet.bean.ManagerAccess;
import com.helmet.bean.ManagerAccessGroup;
import com.helmet.bean.ManagerAccessGroupUser;
import com.helmet.bean.ManagerAccessUser;
import com.helmet.bean.ManagerEntity;
import com.helmet.bean.MgrAccess;
import com.helmet.bean.MgrAccessGroup;
import com.helmet.bean.MgrAccessGroupEntity;
import com.helmet.bean.MgrAccessGroupItem;
import com.helmet.bean.NhsBackingReport;
import com.helmet.bean.NhsBackingReportUser;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.IdDocument;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.RecordCount;
import com.helmet.bean.Regulator;
import com.helmet.bean.Shift;
import com.helmet.bean.Site;
import com.helmet.bean.SiteUser;
import com.helmet.bean.SiteUserEntity;
import com.helmet.bean.Uplift;
import com.helmet.bean.UpliftMinute;
import com.helmet.bean.UpliftMinuteUser;
import com.helmet.bean.VisaType;

public interface CommonService {

	public int insertBudgetTransaction(Integer locationId, Integer jobProfileId, BigDecimal value, BigDecimal vatValue, BigDecimal expenseValue, BigDecimal nonPayValue, String comment, Integer auditorId);
	 	
	public LocationJobProfile getLocationJobProfileForLocationAndJobProfile(Integer locationId, Integer jobProfileId);

	public BookingDateUserApplicant getBookingDateUserApplicantAndBookingDate(Integer bookingDateId);
	
 	public int updateBookingDateActivated(Integer bookingDateId, Integer noOfChanges, Integer auditorId);
 	public int updateBookingDatesActivated(String bookingDateIdStrs, Integer auditorId);
 	public int updateBookingDateAuthorized(BookingDate bookingDate, Integer auditorId);
  public int updateBookingDatesAuthorized(String bookingDateIdStrs, Integer auditorId);
  public int updateBookingDatesBackingReport(String bookingDateIdStrs, String backingReports, Integer auditorId);
 	
	public List<ReasonForRequest> getReasonForRequestsForClient(Integer clientId, boolean showOnlyActive);
	public List<ReasonForRequest> getReasonForRequestsForClient(Integer clientId);
	public ReasonForRequest getReasonForRequest(Integer reasonForRequestId);
	public int insertReasonForRequest(ReasonForRequest reasonForRequest, Integer auditorId);
	public int updateReasonForRequest(ReasonForRequest reasonForRequest, Integer auditorId);
	public int deleteReasonForRequest(Integer reasonForRequestId, Integer noOfChanges, Integer auditorId);
	public int updateReasonForRequestDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

	public List<Grade> getGradesForClient(Integer clientId, boolean showOnlyActive);
	public List<Grade> getGradesForClient(Integer clientId);
	public Grade getGrade(Integer gradeId);
	public int insertGrade(Grade grade, Integer auditorId);
	public int updateGrade(Grade grade, Integer auditorId);
	public int deleteGrade(Integer gradeId, Integer noOfChanges, Integer auditorId);
	public int updateGradeDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

	public List<PublicHoliday> getPublicHolidaysForClient(Integer clientId, boolean showOnlyActive);
	public List<PublicHoliday> getPublicHolidaysForClient(Integer clientId);
  public PublicHoliday getPublicHoliday(Integer publicHolidayId);
  public PublicHoliday getPublicHolidayForClientDate(Integer clientId, Date date);
	public int insertPublicHoliday(PublicHoliday publicHoliday, Integer auditorId);
	public int updatePublicHoliday(PublicHoliday publicHoliday, Integer auditorId);
	public int deletePublicHoliday(Integer publicHolidayId, Integer noOfChanges, Integer auditorId);
	
	public ClientUser getClientUser(Integer clientId);
  public int updateClient(Client client, Integer auditorId);
  public int updateClientTradeshiftDetails(Client client, Integer auditorId);
	
	public List<SiteUser> getSiteUsersForClient(Integer clientId, boolean showOnlyActive);
	public List<SiteUser> getSiteUsersForClient(Integer clientId);
	public Site getSite(Integer siteId);
	public SiteUser getSiteUser(Integer siteId);
	public SiteUserEntity getSiteUserEntity(Integer siteId, boolean showOnlyActive);
	public SiteUserEntity getSiteUserEntity(Integer siteId);
	public int insertSite(Site site, Integer auditorId);
	public int updateSite(Site site, Integer auditorId);
	public int deleteSite(Integer siteId, Integer noOfChanges, Integer auditorId);
	public int updateSiteDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

	public List<Location> getLocationsForSite(Integer siteId, boolean showOnlyActive);
	public List<Location> getLocationsForSite(Integer siteId);
	public Location getLocation(Integer locationId);
	public LocationEntity getLocationEntity(Integer locationId, boolean showOnlyActive);
	public LocationEntity getLocationEntity(Integer locationId);
	public int insertLocation(Location location, Integer auditorId);
	public int updateLocation(Location location, Integer auditorId);
	public int deleteLocation(Integer locationId, Integer noOfChanges, Integer auditorId);
	public int updateLocationDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

	public List<DressCode> getDressCodesForLocation(Integer locationId, boolean showOnlyActive);
	public List<DressCode> getDressCodesForLocation(Integer locationId);
	public DressCode getDressCode(Integer dressCodeId);
	public int insertDressCode(DressCode dressCode, Integer auditorId);
	public int updateDressCode(DressCode dressCode, Integer auditorId);
	public int deleteDressCode(Integer dressCodeId, Integer noOfChanges, Integer auditorId);
	public int updateDressCodeDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

	public List<Shift> getShiftsForLocation(Integer locationId, boolean showOnlyActive);
	public List<Shift> getShiftsForLocation(Integer locationId);
	public Shift getShift(Integer shiftId);
	public int insertShift(Shift shift, Integer auditorId);
	public int updateShift(Shift shift, Integer auditorId);
	public int deleteShift(Integer shiftId, Integer noOfChanges, Integer auditorId);
	public int updateShiftDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);
		
	public List<Expense> getExpensesForLocation(Integer locationId, boolean showOnlyActive);
	public List<Expense> getExpensesForLocation(Integer locationId);
	public Expense getExpense(Integer expenseId);
	public int insertExpense(Expense expense, Integer auditorId);
	public int updateExpense(Expense expense, Integer auditorId);
	public int deleteExpense(Integer expenseId, Integer noOfChanges, Integer auditorId);
	public int updateExpenseDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId);

	List<LocationJobProfileUser> getLocationJobProfileUsersForClient(Integer clientId, boolean showOnlyActive);
	public List<JobProfileUser> getJobProfileUsersNotForLocation(Integer clientId, Integer locationId);
	public LocationJobProfileUser getLocationJobProfileUser(Integer locationJobProfileUserId);
	public int insertLocationJobProfile(LocationJobProfile locationJobProfile, Integer auditorId);
	public int deleteLocationJobProfile(Integer locationJobProfileId, Integer noOfChanges, Integer auditorId);
	public int updateLocationJobProfileRate(Integer locationJobProfileId, BigDecimal value, Integer noOfChanges, Integer auditorId);

  public List<LocationJobProfileUser> getLocationJobProfileUsersForLocation(Integer locationId);
  public List<LocationJobProfileUser> getLocationJobProfileUsersForLocationAndNhsAssignment(Integer locationId, String nhsAssignment);

	public List<Manager> getManagersForClient(Integer clientId, boolean showOnlyActive);
	public List<Manager> getManagersForClient(Integer clientId);
	public Manager getManager(Integer managerId);
	public ManagerEntity getManagerEntity(Integer managerId);
	public int insertManager(Manager manager, Integer auditorId);
	public int updateManager(Manager manager, Integer auditorId);
	public int deleteManager(Integer managerId, Integer noOfChanges, Integer auditorId);

  	public int updateManagerPwd(Integer managerId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
  	public int updateManagerSecretWord(Integer managerId, String newSecretWord, Integer noOfChanges, Integer auditorId);

	public List<LocationManagerJobProfileUser> getLocationManagerJobProfileUsersForManager(Integer managerId);
	public int insertLocationManagerJobProfile(LocationManagerJobProfile locationManagerJobProfile, Integer auditorId);
	public int deleteLocationManagerJobProfile(Integer locationManagerJobProfileId, Integer noOfChanges, Integer auditorId);

	public List<MgrAccess> getMgrAccesses(boolean showOnlyActive);
	public List<MgrAccess> getActiveMgrAccessesForManager(Integer managerId);
	public MgrAccess getMgrAccess(Integer mgrAccessId);
	public int insertMgrAccess(MgrAccess mgrAccess, Integer auditorId);
	public int updateMgrAccess(MgrAccess mgrAccess, Integer auditorId);
	public int deleteMgrAccess(Integer mgrAccessId, Integer noOfChanges, Integer auditorId);

	public List<MgrAccessGroup> getMgrAccessGroupsForClient(Integer clientId, boolean showOnlyActive);
	public MgrAccessGroup getMgrAccessGroup(Integer mgrAccessGroupId);
	public MgrAccessGroupEntity getMgrAccessGroupEntity(Integer mgrAccessGroupId);
	public int insertMgrAccessGroup(MgrAccessGroup mgrAccessGroup, Integer auditorId);
	public int updateMgrAccessGroup(MgrAccessGroup mgrAccessGroup, Integer auditorId);
	public int deleteMgrAccessGroup(Integer mgrAccessGroupId, Integer noOfChanges, Integer auditorId);
	
	public int insertMgrAccessGroupItem(MgrAccessGroupItem mgrAccessGroupItem, Integer auditorId);
	public int deleteMgrAccessGroupItem(Integer mgrAccessGroupItemId, Integer noOfChanges, Integer auditorId);

	public List<ManagerAccessUser> getManagerAccessUsersForManager(Integer managerId);
	public int insertManagerAccess(ManagerAccess managerAccess, Integer auditorId);
	public int deleteManagerAccess(Integer managerAccessId, Integer noOfChanges, Integer auditorId);
	
	public List<ManagerAccessGroupUser> getManagerAccessGroupUsersForManager(Integer managerId);
	public int insertManagerAccessGroup(ManagerAccessGroup managerAccessGroup, Integer auditorId);
	public int deleteManagerAccessGroup(Integer managerAccessGroupId, Integer noOfChanges, Integer auditorId);

	public List<Uplift> getUpliftsForClient(Integer clientId, boolean showOnlyActive);
  public List<Uplift> getUpliftsForClient(Integer clientId);
	public Uplift getUplift(Integer upliftId);
	public int insertUplift(Uplift uplift, Integer auditorId);
	public int updateUplift(Uplift uplift, Integer auditorId);
	public int deleteUplift(Integer upliftId, Integer noOfChanges, Integer auditorId);

  public List<UpliftMinute> getUpliftMinutesForUplift(Integer upliftId, boolean showOnlyActive);
  public List<UpliftMinute> getUpliftMinutesForUplift(Integer upliftId);
  public List<UpliftMinute> getUpliftMinutesForClient(Integer clientId);
  public UpliftMinute getUpliftMinute(Integer upliftMinuteId);
  public int insertUpliftMinute(UpliftMinute upliftMinute, Integer auditorId);
  public int updateUpliftMinute(UpliftMinute upliftMinute, Integer auditorId);
  public int deleteUpliftMinute(Integer upliftMinuteId, Integer noOfChanges, Integer auditorId);
  public List<UpliftMinuteUser> getUpliftMinuteUsersForUplift(Integer upliftId, boolean showOnlyActive);
  public List<UpliftMinuteUser> getUpliftMinuteUsersForUplift(Integer upliftId);
  public void loadUpliftMinutesIntoUplifts(Integer clientId, List<Uplift> uplifts);

  public List<ClientAgencyUser> getClientAgencyUsersForAgency(Integer agencyId);
	public List<ClientAgencyUser> getClientAgencyUsersForAgency(Integer agencyId, boolean showOnlyActive);
  public List<ClientAgencyUser> getClientAgencyUsersForAgencyInNameGroup(Integer agencyId, String indexLetter);
  public List<ClientAgencyUser> getClientAgencyUsersForAgencyInNameGroup(Integer agencyId, String indexLetter, boolean showOnlyActive);
	public ClientAgency getClientAgency(Integer clientAgencyId);
	public ClientAgency getClientAgencyForClientAndAgency(Integer clientId, Integer  agencyId);
	
	public ClientAgencyUserEntity getClientAgencyUserEntity(Integer clientAgencyId, boolean showOnlyActive);
	public int insertClientAgency(ClientAgency clientAgency, Integer auditorId);
	public int deleteClientAgency(Integer clientAgencyId, Integer noOfChanges, Integer auditorId);
	public int updateClientAgency(ClientAgency clientAgency, Integer auditorId);

    public List<ClientAgencyGrade> getClientAgencyGradesForJobProfile(Integer jobProfileId); 
	public ClientAgencyGrade getClientAgencyGrade(Integer clientAgencyGradeId);
	public int insertClientAgencyGrade(ClientAgencyGrade clientAgencyGrade, Integer auditorId);
	public int updateClientAgencyGrade(ClientAgencyGrade clientAgencyGrade, Integer auditorId);
	public int deleteClientAgencyGrade(Integer clientAgencyGradeId, Integer noOfChanges, Integer auditorId);

    public LocationUser getLocationUser(Integer locationId);
	
	public int insertBooking(Booking booking, 
            BookingDate[] bookingDates, 
            ClientAgencyJobProfileGrade[] bookingGrades, 
            Expense[] bookingExpenses, 
            Integer auditorId);

	public int updateBooking(Booking booking, 
            BookingDate[] bookingDates, 
            ClientAgencyJobProfileGrade[] bookingGrades, 
            Expense[] bookingExpenses, 
            Integer auditorId);
    
	public int updateBookingOpen(Integer bookingId, Integer noOfChanges, Integer auditorId);

	public int updateBookingDateOvertime(BookingDateUserApplicant bookingDate, Integer auditorId);
	
  public Booking getBooking(Integer bookingId);

  public BookingUser getBookingUser(Integer bookingId);

  public List<Booking> getBookingsForLocation(Integer locationId); 

  public List<NhsBooking> getNhsBookingsForLocation(Integer agencyId, Integer locationId); 

  public Country getCountry(Integer countryId);

	public int updateBookingInfo(Booking booking, Integer auditorId);
	
    public int updateBookingExpenses(Booking booking, Expense[] bookingExpenses, Integer auditorId);
    
 	public int updateBookingDateCancel(Integer bookingDateId, String cancelText, Integer noOfChanges, Integer auditorId);
	
	public int updateBookingCancel(Integer bookingId, String cancelText, Integer noOfChanges, Integer auditorId);

 	public BookingGradeApplicantUser getBookingGradeApplicantUserForBookingFilledSingleCandidate(Integer bookingId);
 	
	public int updateBookingClosed(Integer bookingId, Integer noOfChanges, Integer auditorId);
	
	public int updateBookingExtend(Booking booking, BookingDate[] bookingDates, BookingGradeApplicantUser bookingGradeApplicant, Integer auditorId);
	
	public AgencyUser getAgencyUser(Integer agencyId);
	public int updateAgency(Agency agency, Integer auditorId);

  public Integer getNhsBackingReportPagingLimit();
  public Integer getNhsBackingReportPagingGroupSize();
  public NhsBackingReport getNhsBackingReport(Integer nhsBackingReportId);
  public RecordCount getNhsBackingReportsCount(Integer agencyId, boolean showOnlyActive, String filter);
  public List<NhsBackingReport> getNhsBackingReports(Integer agencyId, boolean showOnlyActive);
  public List<NhsBackingReportUser> getNhsBackingReportUsers(Integer agencyId, boolean showOnlyActive);
  public List<NhsBackingReportUser> getNhsBackingReportUsersInList(Integer agencyId, String nhsBackingReportIdList); 
  public List<NhsBackingReportUser> getNhsBackingReportUsersOffset(Integer agencyId, boolean showOnlyActive, Integer offset, String filter);
  public NhsBackingReportUser getNhsBackingReportUser(Integer nhsBackingReportId);
  public NhsBackingReport getNhsBackingReportForName(String nhsBackingReportName, boolean showOnlyActive);
  public NhsBackingReport getInactiveNhsBackingReportForName(String nhsBackingReportName);
  public List<NhsBackingReport> getNhsBackingReportsForAgencyDateRange(Integer agencyId, Date startOfWeekDate, Date endOfWeekDate); 
  public List<NhsBackingReportUser> getNhsBackingReportUsersForAgencyDateRange(Integer agencyId, Date startOfWeekDate, Date endOfWeekDate); 
  public int deleteNhsBackingReport(Integer nhsBackingReportId, Integer noOfChanges, Integer auditorId);
  public int insertNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId);
  public int updateNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId);
  public int reactivateNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId);
  public int updateNhsBackingReportTradeshiftDocumentId(NhsBackingReport nhsBackingReport, Integer auditorId);
  public int updateNhsBackingReportSubcontract(NhsBackingReport nhsBackingReport, Integer auditorId);
  public int updateNhsBackingReportSubcontractDocumentationSentDate(NhsBackingReport nhsBackingReport, Integer auditorId);

  public List<AgencyUser> getAgencyUsers(boolean showOnlyActive);
  public List<AreaOfSpeciality> getAreaOfSpecialities(boolean showOnlyActive);
  public List<ClientUser> getClientUsers(boolean showOnlyActive);
  public List<Country> getCountries(boolean showOnlyActive);
  public DisciplineCategory getDisciplineCategory(Integer disciplineCategoryId);
  public DisciplineCategoryUser getDisciplineCategoryUser(Integer disciplineCategoryId);
  public List<DisciplineCategory> getDisciplineCategories(boolean showOnlyActive);
  public List<DisciplineCategoryUser> getDisciplineCategoryUsers(boolean showOnlyActive);
  public List<EmailAction> getEmailActions(boolean showOnlyActive);
  public List<GeographicalRegion> getGeographicalRegions(boolean showOnlyActive);
  public List<IdDocument> getIdDocuments(boolean showOnlyActive);
  public IdDocument getIdDocument(Integer idDocumentId);
  public List<VisaType> getVisaTypes(boolean showOnlyActive);
  public List<Regulator> getRegulators(boolean showOnlyActive);
  public List<CompliancyTest> getCompliancyTests(boolean showOnlyActive);

  public int compliantApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId, Boolean compliant);

}
