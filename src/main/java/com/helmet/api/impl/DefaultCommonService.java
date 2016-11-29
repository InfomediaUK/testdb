package com.helmet.api.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.helmet.api.CommonService;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.bean.Agency;
import com.helmet.bean.AgencyInvoice;
import com.helmet.bean.AgencyUser;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeApplicantDate;
import com.helmet.bean.BookingGradeApplicantDateUser;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeUser;
import com.helmet.bean.BookingUser;
import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgency;
import com.helmet.bean.ClientAgencyGrade;
import com.helmet.bean.ClientAgencyJobProfile;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyUser;
import com.helmet.bean.ClientAgencyUserEntity;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Country;
import com.helmet.bean.DressCode;
import com.helmet.bean.Expense;
import com.helmet.bean.Grade;
import com.helmet.bean.IntValue;
import com.helmet.bean.Invoice;
import com.helmet.bean.InvoiceAgency;
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
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.RecordCount;
import com.helmet.bean.Shift;
import com.helmet.bean.Site;
import com.helmet.bean.SiteUser;
import com.helmet.bean.SiteUserEntity;
import com.helmet.bean.Uplift;
import com.helmet.bean.UpliftMinute;
import com.helmet.bean.UpliftMinuteUser;
import com.helmet.persistence.AgencyDAO;
import com.helmet.persistence.AgencyInvoiceCreditDAO;
import com.helmet.persistence.AgencyInvoiceDAO;
import com.helmet.persistence.BookingDAO;
import com.helmet.persistence.BookingDateDAO;
import com.helmet.persistence.BookingExpenseDAO;
import com.helmet.persistence.BookingGradeApplicantDAO;
import com.helmet.persistence.BookingGradeApplicantDateDAO;
import com.helmet.persistence.BookingGradeDAO;
import com.helmet.persistence.BudgetTransactionDAO;
import com.helmet.persistence.ClientAgencyDAO;
import com.helmet.persistence.ClientAgencyGradeDAO;
import com.helmet.persistence.ClientAgencyJobProfileDAO;
import com.helmet.persistence.ClientAgencyJobProfileGradeDAO;
import com.helmet.persistence.ClientDAO;
import com.helmet.persistence.CountryDAO;
import com.helmet.persistence.DressCodeDAO;
import com.helmet.persistence.ExpenseDAO;
import com.helmet.persistence.GradeDAO;
import com.helmet.persistence.InvoiceAgencyDAO;
import com.helmet.persistence.InvoiceDAO;
import com.helmet.persistence.JobProfileDAO;
import com.helmet.persistence.LocationDAO;
import com.helmet.persistence.LocationJobProfileDAO;
import com.helmet.persistence.LocationManagerJobProfileDAO;
import com.helmet.persistence.ManagerAccessDAO;
import com.helmet.persistence.ManagerAccessGroupDAO;
import com.helmet.persistence.ManagerDAO;
import com.helmet.persistence.MgrAccessDAO;
import com.helmet.persistence.MgrAccessGroupDAO;
import com.helmet.persistence.MgrAccessGroupItemDAO;
import com.helmet.persistence.NhsBackingReportDAO;
import com.helmet.persistence.NhsBookingDAO;
import com.helmet.persistence.PublicHolidayDAO;
import com.helmet.persistence.ReasonForRequestDAO;
import com.helmet.persistence.ShiftDAO;
import com.helmet.persistence.SiteDAO;
import com.helmet.persistence.UpliftDAO;
import com.helmet.persistence.UpliftMinuteDAO;

import org.apache.commons.lang3.StringUtils;

public abstract class DefaultCommonService implements CommonService {
	
	private BookingDAO bookingDAO;
	
	private BookingDateDAO bookingDateDAO;
	
  private CountryDAO countryDAO;
  
	private LocationJobProfileDAO locationJobProfileDAO;
	
	private BudgetTransactionDAO budgetTransactionDAO;

	private ReasonForRequestDAO reasonForRequestDAO;
	
	private GradeDAO gradeDAO;

	private PublicHolidayDAO publicHolidayDAO;
	
	private ClientDAO clientDAO;

	private SiteDAO siteDAO;
	
	private LocationDAO locationDAO;
	
	private ShiftDAO shiftDAO;
	
	private ExpenseDAO expenseDAO;
	
	private DressCodeDAO dressCodeDAO;
	
	private JobProfileDAO jobProfileDAO;
	
	private ManagerDAO managerDAO;

	private LocationManagerJobProfileDAO locationManagerJobProfileDAO;
	
	private MgrAccessDAO mgrAccessDAO;
	
	private MgrAccessGroupDAO mgrAccessGroupDAO;
	
	private MgrAccessGroupItemDAO mgrAccessGroupItemDAO;
	
	private ManagerAccessDAO managerAccessDAO;
	
	private ManagerAccessGroupDAO managerAccessGroupDAO;
  
  private UpliftDAO upliftDAO;
  
  private UpliftMinuteDAO upliftMinuteDAO;
  
	private ClientAgencyDAO clientAgencyDAO;
	
	private ClientAgencyGradeDAO clientAgencyGradeDAO;
	
	private BookingGradeDAO bookingGradeDAO;
	
	private BookingExpenseDAO bookingExpenseDAO;
	
	private ClientAgencyJobProfileDAO clientAgencyJobProfileDAO;
	
	private ClientAgencyJobProfileGradeDAO clientAgencyJobProfileGradeDAO;
	
	private BookingGradeApplicantDateDAO bookingGradeApplicantDateDAO;
	
	private InvoiceDAO invoiceDAO;
	
	private InvoiceAgencyDAO invoiceAgencyDAO;
	
	private AgencyInvoiceDAO agencyInvoiceDAO;
	
	private BookingGradeApplicantDAO bookingGradeApplicantDAO;
	
	private AgencyDAO agencyDAO;

	private AgencyInvoiceCreditDAO agencyInvoiceCreditDAO;
  
  private NhsBookingDAO nhsBookingDAO;
  
  private NhsBackingReportDAO nhsBackingReportDAO;
  
	public NhsBookingDAO getNhsBookingDAO()
  {
    return nhsBookingDAO;
  }

  public void setNhsBookingDAO(NhsBookingDAO nhsBookingDAO)
  {
    this.nhsBookingDAO = nhsBookingDAO;
  }

  public NhsBackingReportDAO getNhsBackingReportDAO()
  {
    return nhsBackingReportDAO;
  }

  public void setNhsBackingReportDAO(NhsBackingReportDAO nhsBackingReportDAO)
  {
    this.nhsBackingReportDAO = nhsBackingReportDAO;
  }

  public ClientAgencyGradeDAO getClientAgencyGradeDAO() {
		return clientAgencyGradeDAO;
	}

	public void setClientAgencyGradeDAO(ClientAgencyGradeDAO clientAgencyGradeDAO) {
		this.clientAgencyGradeDAO = clientAgencyGradeDAO;
	}

	public BookingDAO getBookingDAO() {
		return bookingDAO;
	}

 	public void setBookingDAO(BookingDAO bookingDAO) {
		this.bookingDAO = bookingDAO;
	}

	public BookingDateDAO getBookingDateDAO() {
		return bookingDateDAO;
	}

	public void setBookingDateDAO(BookingDateDAO bookingDateDAO) {
		this.bookingDateDAO = bookingDateDAO;
	}

	public BudgetTransactionDAO getBudgetTransactionDAO() {
		return budgetTransactionDAO;
	}

	public void setBudgetTransactionDAO(BudgetTransactionDAO budgetTransactionDAO) {
		this.budgetTransactionDAO = budgetTransactionDAO;
	}

  public CountryDAO getCountryDAO() 
  {
    return countryDAO;
  }

  public void setCountryDAO(CountryDAO countryDAO) 
  {
    this.countryDAO = countryDAO;
  }

	public LocationJobProfileDAO getLocationJobProfileDAO() {
		return locationJobProfileDAO;
	}

	public void setLocationJobProfileDAO(LocationJobProfileDAO locationJobProfileDAO) {
		this.locationJobProfileDAO = locationJobProfileDAO;
	}

	public ReasonForRequestDAO getReasonForRequestDAO() {
		return reasonForRequestDAO;
	}

	public void setReasonForRequestDAO(ReasonForRequestDAO reasonForRequestDAO) {
		this.reasonForRequestDAO = reasonForRequestDAO;
	}

	public GradeDAO getGradeDAO() {
		return gradeDAO;
	}

	public void setGradeDAO(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	public PublicHolidayDAO getPublicHolidayDAO() {
		return publicHolidayDAO;
	}

	public void setPublicHolidayDAO(PublicHolidayDAO publicHolidayDAO) {
		this.publicHolidayDAO = publicHolidayDAO;
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public SiteDAO getSiteDAO() {
		return siteDAO;
	}

	public void setSiteDAO(SiteDAO siteDAO) {
		this.siteDAO = siteDAO;
	}

	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	public DressCodeDAO getDressCodeDAO() {
		return dressCodeDAO;
	}

	public void setDressCodeDAO(DressCodeDAO dressCodeDAO) {
		this.dressCodeDAO = dressCodeDAO;
	}

	public ExpenseDAO getExpenseDAO() {
		return expenseDAO;
	}

	public void setExpenseDAO(ExpenseDAO expenseDAO) {
		this.expenseDAO = expenseDAO;
	}

	public JobProfileDAO getJobProfileDAO() {
		return jobProfileDAO;
	}

	public void setJobProfileDAO(JobProfileDAO jobProfileDAO) {
		this.jobProfileDAO = jobProfileDAO;
	}

	public ShiftDAO getShiftDAO() {
		return shiftDAO;
	}

	public void setShiftDAO(ShiftDAO shiftDAO) {
		this.shiftDAO = shiftDAO;
	}

	public ManagerDAO getManagerDAO() {
		return managerDAO;
	}

	public void setManagerDAO(ManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}

	public LocationManagerJobProfileDAO getLocationManagerJobProfileDAO() {
		return locationManagerJobProfileDAO;
	}

	public void setLocationManagerJobProfileDAO(LocationManagerJobProfileDAO locationManagerJobProfileDAO) {
		this.locationManagerJobProfileDAO = locationManagerJobProfileDAO;
	}

	public ManagerAccessDAO getManagerAccessDAO() {
		return managerAccessDAO;
	}

	public void setMgrAccessDAO(MgrAccessDAO mgrAccessDAO) {
		this.mgrAccessDAO = mgrAccessDAO;
	}

	public ManagerAccessGroupDAO getManagerAccessGroupDAO() {
		return managerAccessGroupDAO;
	}

	public void setMgrAccessGroupDAO(MgrAccessGroupDAO mgrAccessGroupDAO) {
		this.mgrAccessGroupDAO = mgrAccessGroupDAO;
	}

	public MgrAccessGroupItemDAO getMgrAccessGroupItemDAO() {
		return mgrAccessGroupItemDAO;
	}

	public void setMgrAccessGroupItemDAO(MgrAccessGroupItemDAO mgrAccessGroupItemDAO) {
		this.mgrAccessGroupItemDAO = mgrAccessGroupItemDAO;
	}

	public MgrAccessDAO getMgrAccessDAO() {
		return mgrAccessDAO;
	}

	public void setManagerAccessDAO(ManagerAccessDAO managerAccessDAO) {
		this.managerAccessDAO = managerAccessDAO;
	}

	public MgrAccessGroupDAO getMgrAccessGroupDAO() {
		return mgrAccessGroupDAO;
	}

	public void setManagerAccessGroupDAO(ManagerAccessGroupDAO managerAccessGroupDAO) {
		this.managerAccessGroupDAO = managerAccessGroupDAO;
	}

	public UpliftDAO getUpliftDAO() {
		return upliftDAO;
	}

	public void setUpliftDAO(UpliftDAO upliftDAO) {
		this.upliftDAO = upliftDAO;
	}
	
  public UpliftMinuteDAO getUpliftMinuteDAO() 
  {
    return upliftMinuteDAO;
  }

  public void setUpliftMinuteDAO(UpliftMinuteDAO upliftMinuteDAO) 
  {
    this.upliftMinuteDAO = upliftMinuteDAO;
  }
  
	public ClientAgencyDAO getClientAgencyDAO() {
		return clientAgencyDAO;
	}

	public void setClientAgencyDAO(ClientAgencyDAO clientAgencyDAO) {
		this.clientAgencyDAO = clientAgencyDAO;
	}

	public BookingExpenseDAO getBookingExpenseDAO() {
		return bookingExpenseDAO;
	}

	public void setBookingExpenseDAO(BookingExpenseDAO bookingExpenseDAO) {
		this.bookingExpenseDAO = bookingExpenseDAO;
	}

	public BookingGradeDAO getBookingGradeDAO() {
		return bookingGradeDAO;
	}

	public void setBookingGradeDAO(BookingGradeDAO bookingGradeDAO) {
		this.bookingGradeDAO = bookingGradeDAO;
	}

	public ClientAgencyJobProfileDAO getClientAgencyJobProfileDAO() {
		return clientAgencyJobProfileDAO;
	}

	public void setClientAgencyJobProfileDAO(
			ClientAgencyJobProfileDAO clientAgencyJobProfileDAO) {
		this.clientAgencyJobProfileDAO = clientAgencyJobProfileDAO;
	}

	public ClientAgencyJobProfileGradeDAO getClientAgencyJobProfileGradeDAO() {
		return clientAgencyJobProfileGradeDAO;
	}

	public void setClientAgencyJobProfileGradeDAO(
			ClientAgencyJobProfileGradeDAO clientAgencyJobProfileGradeDAO) {
		this.clientAgencyJobProfileGradeDAO = clientAgencyJobProfileGradeDAO;
	}

	public BookingGradeApplicantDateDAO getBookingGradeApplicantDateDAO() {
		return bookingGradeApplicantDateDAO;
	}

	public void setBookingGradeApplicantDateDAO(
			BookingGradeApplicantDateDAO bookingGradeApplicantDateDAO) {
		this.bookingGradeApplicantDateDAO = bookingGradeApplicantDateDAO;
	}

	public InvoiceAgencyDAO getInvoiceAgencyDAO() {
		return invoiceAgencyDAO;
	}

	public void setInvoiceAgencyDAO(InvoiceAgencyDAO invoiceAgencyDAO) {
		this.invoiceAgencyDAO = invoiceAgencyDAO;
	}

	public InvoiceDAO getInvoiceDAO() {
		return invoiceDAO;
	}

	public void setInvoiceDAO(InvoiceDAO invoiceDAO) {
		this.invoiceDAO = invoiceDAO;
	}

	public AgencyInvoiceDAO getAgencyInvoiceDAO() {
		return agencyInvoiceDAO;
	}

	public void setAgencyInvoiceDAO(AgencyInvoiceDAO agencyInvoiceDAO) {
		this.agencyInvoiceDAO = agencyInvoiceDAO;
	}

	public BookingGradeApplicantDAO getBookingGradeApplicantDAO() {
		return bookingGradeApplicantDAO;
	}

	public void setBookingGradeApplicantDAO(
			BookingGradeApplicantDAO bookingGradeApplicantDAO) {
		this.bookingGradeApplicantDAO = bookingGradeApplicantDAO;
	}

	public AgencyDAO getAgencyDAO() {
		return agencyDAO;
	}

	public void setAgencyDAO(AgencyDAO agencyDAO) {
		this.agencyDAO = agencyDAO;
	}

	public AgencyInvoiceCreditDAO getAgencyInvoiceCreditDAO() {
		return agencyInvoiceCreditDAO;
	}

	public void setAgencyInvoiceCreditDAO(
			AgencyInvoiceCreditDAO agencyInvoiceCreditDAO) {
		this.agencyInvoiceCreditDAO = agencyInvoiceCreditDAO;
	}

	public int updateBookingDateAuthorized(BookingDate bookingDate, Integer auditorId) {

        // bd.workedStatus - auth'd
 		// bd.status - completed
 		// booking.workedValue and workedNoOfHours
 		// budgetTransaction - bd.chargeRateValue - bd.workedValue
 		
 		int rc = bookingDateDAO.updateBookingDateAuthorized(bookingDate.getBookingDateId(), 
        		bookingDate.getNoOfChanges(),
        		auditorId);

        BookingDateUserApplicant bookingDateUserApplicant = getBookingDateUserApplicantAndBookingDate(bookingDate.getBookingDateId());
        BigDecimal workedValue = bookingDateUserApplicant.getWorkedChargeRateValue();
        BigDecimal workedNoOfHours = bookingDateUserApplicant.getWorkedNoOfHours();
        BigDecimal chargeRateValue = bookingDateUserApplicant.getChargeRateValue();
        
        // TODO - probably should be getting expense details to so the booking can be updated - ALSO VAT !!!
 		
        Booking booking = bookingDAO.getBooking(bookingDate.getBookingId());

        // if all dates are authorized/invoiced - mark the booking as completed

        Integer status = booking.getStatus();
        
	    IntValue countNotFilled = bookingDAO.getBookingCountNotFilled(bookingDate.getBookingId());
	    if (countNotFilled.getValue() == 0) {
		    IntValue countNotAuthorized = bookingDAO.getBookingCountNotAuthorized(bookingDate.getBookingId());
		    if (countNotAuthorized.getValue() == 0) {
                status = Booking.BOOKING_STATUS_COMPLETED;
		    }
	    }

        rc = bookingDAO.updateBookingWorkedValue(booking.getBookingId(), booking.getNoOfChanges(), auditorId, workedValue, workedNoOfHours, status);

        BigDecimal vatValue = bookingDateUserApplicant.getWorkedVatValue().add(bookingDateUserApplicant.getExpenseVatValue());
        BigDecimal expenseValue = bookingDateUserApplicant.getExpenseValue();
        
        DecimalFormat df = new DecimalFormat("#000");
        rc = insertBudgetTransaction(booking.getLocationId(), 
                                         booking.getJobProfileId(), 
                                         chargeRateValue.subtract(workedValue), 
                                         vatValue.multiply(new BigDecimal(-1)),
                                         expenseValue.multiply(new BigDecimal(-1)),
                                         new BigDecimal(0),
                                         // needs to be multi-lingual
                                         "Timesheet authorised - " + df.format(bookingDate.getBookingId()) + "." + df.format(bookingDate.getBookingDateId()), 
	                                     auditorId);
	    
		return rc;
 		
 	}

 	public int updateBookingDatesAuthorized(String bookingDateIdStrs, Integer auditorId) {
 		
        int rc = 0;
        StringTokenizer st = new StringTokenizer(bookingDateIdStrs, ",");
        while (st.hasMoreTokens()) {
            String bookingDateIdStr = st.nextToken();
            Integer bookingDateId = Integer.parseInt(bookingDateIdStr);
     		// for each bookingDate call the updateBookingDateAuthorized method in this class.
	 		BookingDate bookingDate = bookingDateDAO.getBookingDate(bookingDateId);
	 		//
	 		rc += updateBookingDateAuthorized(bookingDate, auditorId);
        }
 		return rc;
 	
 	}
 	
  public int updateBookingDatesBackingReport(String bookingDateIdStrs, String backingReports, Integer auditorId)
  {

    int rc = 0;
    BookingDate bookingDate = null;
    StringTokenizer stBookingDateId = new StringTokenizer(bookingDateIdStrs, ",");
    StringTokenizer stBackingReport = null;
    while (stBookingDateId.hasMoreTokens())
    {
      String bookingDateIdStr = stBookingDateId.nextToken();
      Integer bookingDateId = Integer.parseInt(bookingDateIdStr);
      // for each bookingDate call the updateBookingDateAuthorized method in
      // this class.
      bookingDate = bookingDateDAO.getBookingDate(bookingDateId);
      //
      stBackingReport = new StringTokenizer(backingReports, ",");
      while (stBackingReport.hasMoreTokens())
      {
        String backingReport = stBackingReport.nextToken();
        if (backingReport.startsWith("-"))
        {
          // Remove minus sign from BackingReport.
          backingReport = backingReport.substring(1);
          if (StringUtils.isNotEmpty(bookingDate.getBackingReport()))
          {
            // Remove BackingReport from BookingDate.
            bookingDate.setBackingReport(StringUtils.replace(bookingDate.getBackingReport(), backingReport, ""));
            // Correct any double commas that may have been created.
            bookingDate.setBackingReport(StringUtils.replace(bookingDate.getBackingReport(), ",,", ","));
            if (bookingDate.getBackingReport().startsWith(","))
            {
              // BackingReport starts with a comma, get rid of it.
              bookingDate.setBackingReport(bookingDate.getBackingReport().substring(1));
            }
            if (bookingDate.getBackingReport().endsWith(","))
            {
              // BackingReport ends with a comma, get rid of it.
              bookingDate.setBackingReport(bookingDate.getBackingReport().substring(0, bookingDate.getBackingReport().length() - 1));
            }
          }          
        }
        else
        {
          if (backingReport.startsWith("+"))
          {
            // Remove plus sign from BackingReport.
            backingReport = backingReport.substring(1);
          }
          if (!StringUtils.contains(bookingDate.getBackingReport(), backingReport))
          {
            // BackingReport NOT on BookingDate yet.
            if (StringUtils.isEmpty(bookingDate.getBackingReport()))
            {
              // BookingDate BackingReport currently empty...
              bookingDate.setBackingReport(backingReport);
            }
            else
            {
              // BookingDate BackingReport NOT currently empty, append it after a comma...
              bookingDate.setBackingReport(bookingDate.getBackingReport() + "," + backingReport);
            }
          }
        }
      }
      rc += bookingDateDAO.updateBookingDateBackingReport(bookingDate, auditorId);
    }
    return rc;

  }

	public int updateBookingDateActivated(Integer bookingDateId, Integer noOfChanges, Integer auditorId) {

        int rc = bookingDateDAO.updateBookingDateActivated(bookingDateId, 
        		noOfChanges,
        		auditorId);

		return rc;

	}
	
 	public int updateBookingDatesActivated(String bookingDateIdStrs, Integer auditorId) {
 		
        int rc = 0;
        StringTokenizer st = new StringTokenizer(bookingDateIdStrs, ",");
        while (st.hasMoreTokens()) {
            String bookingDateIdStr = st.nextToken();
            Integer bookingDateId = Integer.parseInt(bookingDateIdStr);
     		// for each bookingDate call the updateBookingDateActivated method in this class.
	 		BookingDate bookingDate = bookingDateDAO.getBookingDate(bookingDateId);
	 		//
	 		rc += updateBookingDateActivated(bookingDateId, bookingDate.getNoOfChanges(), auditorId);
        }
 		return rc;

 	}

	public int insertBudgetTransaction(Integer locationId, Integer jobProfileId, BigDecimal value, BigDecimal vatValue, BigDecimal expenseValue, BigDecimal nonPayValue, String comment, Integer auditorId)
  {
    int rc = 0;
    LocationJobProfile locationJobProfile = getLocationJobProfileForLocationAndJobProfile(locationId, jobProfileId);
    if (locationJobProfile == null)
    {
      // Defensive code block for corrupt data. 
      System.out.println("LocationJobProfile NOT FOUND! locationId = [" + locationId + "] jobProfileId = [" + jobProfileId + "]");
    }
    else
    {
      // LocationJobProfile HAS been found (as it should be)...
      BudgetTransaction budgetTransaction = new BudgetTransaction();
      budgetTransaction.setLocationJobProfileId(locationJobProfile.getLocationJobProfileId());
      budgetTransaction.setValue(value);
      budgetTransaction.setVatValue(vatValue);
      budgetTransaction.setExpenseValue(expenseValue);
      budgetTransaction.setNonPayValue(nonPayValue);
      //
      budgetTransaction.setBalance(locationJobProfile.getBudget().add(budgetTransaction.getValue()));
      budgetTransaction.setVatBalance(locationJobProfile.getVatBudget().add(budgetTransaction.getVatValue()));
      budgetTransaction.setExpenseBalance(locationJobProfile.getExpenseBudget().add(budgetTransaction.getExpenseValue()));
      budgetTransaction.setNonPayBalance(locationJobProfile.getNonPayBudget().add(budgetTransaction.getNonPayValue()));
      //
      budgetTransaction.setComment(comment);
      rc = budgetTransactionDAO.insertBudgetTransaction(budgetTransaction, auditorId);
      // add the value to the locationJobProfile
      rc = locationJobProfileDAO.updateLocationJobProfileBudget(budgetTransaction.getLocationJobProfileId(), budgetTransaction.getValue(), budgetTransaction.getVatValue(), budgetTransaction
          .getExpenseValue(), budgetTransaction.getNonPayValue(), locationJobProfile.getNoOfChanges(), auditorId);
    }    
    //
    return rc;

  }
 	
	public LocationJobProfile getLocationJobProfileForLocationAndJobProfile(Integer locationId, Integer jobProfileId) {
	
			LocationJobProfile locationJobProfile = null;
			locationJobProfile = locationJobProfileDAO.getLocationJobProfileForLocationAndJobProfile(locationId, jobProfileId);
			return locationJobProfile;
	
	}
	
	public BookingDateUserApplicant getBookingDateUserApplicantAndBookingDate(Integer bookingDateId) {
	
			BookingDateUserApplicant bookingDateUserApplicant = null;
			bookingDateUserApplicant = bookingDateDAO.getBookingDateUserApplicantForApplicantAndBookingDate(bookingDateId);
			return bookingDateUserApplicant;
	
	}
	
	/*
	 * ReasonForRequest stuff
	 * 
	 */

	public List<ReasonForRequest> getReasonForRequestsForClient(Integer clientId, boolean showOnlyActive) {

		List<ReasonForRequest> reasonForRequests = null;
		reasonForRequests = reasonForRequestDAO.getReasonForRequestsForClient(clientId, showOnlyActive);
		return reasonForRequests;

	}

	public List<ReasonForRequest> getReasonForRequestsForClient(Integer clientId) {

		return getReasonForRequestsForClient(clientId, true);

	}

	public ReasonForRequest getReasonForRequest(Integer reasonForRequestId) {

		ReasonForRequest reasonForRequest = null;
		reasonForRequest = reasonForRequestDAO.getReasonForRequest(reasonForRequestId);
		return reasonForRequest;

	}

	public int insertReasonForRequest(ReasonForRequest reasonForRequest, Integer auditorId) {

		ReasonForRequest duplicateReasonForRequest = reasonForRequestDAO.getReasonForRequestForName(reasonForRequest.getClientId(), reasonForRequest.getName());
		if (duplicateReasonForRequest != null) {
			throw new DuplicateDataException("name");
		}
		duplicateReasonForRequest = reasonForRequestDAO.getReasonForRequestForCode(reasonForRequest.getClientId(), reasonForRequest.getCode());
		if (duplicateReasonForRequest != null) {
			throw new DuplicateDataException("code");
		}
		int rc = reasonForRequestDAO.insertReasonForRequest(reasonForRequest, auditorId);
		return rc;

	}

	public int updateReasonForRequest(ReasonForRequest reasonForRequest, Integer auditorId) {

		ReasonForRequest duplicateReasonForRequest = reasonForRequestDAO.getReasonForRequestForName(reasonForRequest.getClientId(), reasonForRequest.getName());
		if (duplicateReasonForRequest  != null && 
			!duplicateReasonForRequest.getReasonForRequestId().equals(reasonForRequest.getReasonForRequestId())) {
			throw new DuplicateDataException("name");
		}
		duplicateReasonForRequest = reasonForRequestDAO.getReasonForRequestForCode(reasonForRequest.getClientId(), reasonForRequest.getCode());
		if (duplicateReasonForRequest  != null && 
			!duplicateReasonForRequest.getReasonForRequestId().equals(reasonForRequest.getReasonForRequestId())) {
			throw new DuplicateDataException("code");
		}
        int rc = reasonForRequestDAO.updateReasonForRequest(reasonForRequest, auditorId);
		return rc;

	}

	public int deleteReasonForRequest(Integer reasonForRequestId, Integer noOfChanges, Integer auditorId) {

		int rc = reasonForRequestDAO.deleteReasonForRequest(reasonForRequestId, noOfChanges, auditorId);
		return rc;

	}

	public int updateReasonForRequestDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
		int rc = 0;

		StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
		String commaDelimitedKey = null;
		int displayOrder = 0;
		while (stringTokenizer.hasMoreTokens())
		{
		    commaDelimitedKey = stringTokenizer.nextToken();
		    if (!zeroiseDisplayOrder)
		    {
		      // Increment the display order.
		      displayOrder++;
		    }
			StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
			Integer reasonForRequestId = Integer.parseInt(st.nextToken());
			Integer noOfChanges = Integer.parseInt(st.nextToken());
		    rc += reasonForRequestDAO.updateReasonForRequestDisplayOrder(reasonForRequestId, displayOrder, noOfChanges, auditorId);
		}
		
		return rc;
	}

	/*
	 * Grade stuff
	 * 
	 */

	public List<Grade> getGradesForClient(Integer clientId, boolean showOnlyActive) {

		List<Grade> grades = null;
		grades = gradeDAO.getGradesForClient(clientId, showOnlyActive);
		return grades;

	}

	public List<Grade> getGradesForClient(Integer clientId) {

		return getGradesForClient(clientId, true);

	}

	public Grade getGrade(Integer gradeId) {

		Grade grade = null;
		grade = gradeDAO.getGrade(gradeId);
		return grade;

	}

	public int insertGrade(Grade grade, Integer auditorId) {

		Grade duplicateGrade = gradeDAO.getGradeForName(grade.getClientId(), grade.getName());
		if (duplicateGrade != null) {
			throw new DuplicateDataException("name");
		}
		int rc = gradeDAO.insertGrade(grade, auditorId);
		return rc;

	}

	public int updateGrade(Grade grade, Integer auditorId) {

		Grade duplicateGrade = gradeDAO.getGradeForName(grade.getClientId(), grade.getName());
		if (duplicateGrade  != null && 
			!duplicateGrade.getGradeId().equals(grade.getGradeId())) {
			throw new DuplicateDataException("name");
		}
        int rc = gradeDAO.updateGrade(grade, auditorId);
		return rc;

	}

	public int deleteGrade(Integer gradeId, Integer noOfChanges, Integer auditorId) {

		int rc = gradeDAO.deleteGrade(gradeId, noOfChanges, auditorId);
		return rc;

	}

	public int updateGradeDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
		int rc = 0;

		StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
		String commaDelimitedKey = null;
		int displayOrder = 0;
		while (stringTokenizer.hasMoreTokens())
		{
		    commaDelimitedKey = stringTokenizer.nextToken();
		    if (!zeroiseDisplayOrder)
		    {
		      // Increment the display order.
		      displayOrder++;
		    }
			StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
			Integer gradeId = Integer.parseInt(st.nextToken());
			Integer noOfChanges = Integer.parseInt(st.nextToken());
		    rc += gradeDAO.updateGradeDisplayOrder(gradeId, displayOrder, noOfChanges, auditorId);
		}
		
		return rc;
	}

	/*
	 * Public Holiday stuff
	 * 
	 */

	public List<PublicHoliday> getPublicHolidaysForClient(Integer clientId) {

		return publicHolidayDAO.getPublicHolidaysForClient(clientId, true);

	}

	public List<PublicHoliday> getPublicHolidaysForClient(Integer clientId, boolean showOnlyActive) {

		List<PublicHoliday> publicHolidays = null;
		publicHolidays = publicHolidayDAO.getPublicHolidaysForClient(clientId, showOnlyActive);
		return publicHolidays;

	}

  public PublicHoliday getPublicHoliday(Integer publicHolidayId) {

    PublicHoliday publicHoliday = null;
    publicHoliday = publicHolidayDAO.getPublicHoliday(publicHolidayId);
    return publicHoliday;

  }

  public PublicHoliday getPublicHolidayForClientDate(Integer clientId, Date date) 
  {
    PublicHoliday publicHoliday = null;
    publicHoliday = publicHolidayDAO.getPublicHolidayForClientDate(clientId, date);
    return publicHoliday;
  }

	public int insertPublicHoliday(PublicHoliday publicHoliday, Integer auditorId) {

		int rc = publicHolidayDAO.insertPublicHoliday(publicHoliday, auditorId);
		return rc;

	}

	public int updatePublicHoliday(PublicHoliday publicHoliday, Integer auditorId) {

        int rc = publicHolidayDAO.updatePublicHoliday(publicHoliday, auditorId);
		return rc;

	}

	public int deletePublicHoliday(Integer publicHolidayId, Integer noOfChanges, Integer auditorId) {

		int rc = publicHolidayDAO.deletePublicHoliday(publicHolidayId, noOfChanges, auditorId);
		return rc;

	}

	/*
	 * Agency stuff
	 * 
	 */

	public AgencyUser getAgencyUser(Integer agencyId) {
		
		AgencyUser agencyUser = null;
		agencyUser = agencyDAO.getAgencyUser(agencyId);
		return agencyUser;
		
	}

	public int updateAgency(Agency agency, Integer auditorId) {

		Agency duplicateAgency = agencyDAO.getAgencyForName(agency.getName());
		if (duplicateAgency != null &&
			!duplicateAgency.getAgencyId().equals(agency.getAgencyId())) {
			throw new DuplicateDataException("name");
		}
		duplicateAgency = agencyDAO.getAgencyForCode(agency.getCode());
		if (duplicateAgency != null &&
			!duplicateAgency.getAgencyId().equals(agency.getAgencyId())) {
			throw new DuplicateDataException("code");
		}

		int rc = agencyDAO.updateAgency(agency, auditorId);
		return rc;
	
	}

	/*
	 * Client stuff
	 * 
	 */

	public ClientUser getClientUser(Integer clientId) {
		
		ClientUser clientUser = null;
		clientUser = clientDAO.getClientUser(clientId);
		return clientUser;
		
	}

	public int updateClient(Client client, Integer auditorId) {

		Client duplicateClient = clientDAO.getClientForName(client.getName());
		if (duplicateClient != null &&
			!duplicateClient.getClientId().equals(client.getClientId())) {
			throw new DuplicateDataException("name");
		}
		duplicateClient = clientDAO.getClientForCode(client.getCode());
		if (duplicateClient != null &&
			!duplicateClient.getClientId().equals(client.getClientId())) {
			throw new DuplicateDataException("code");
		}

		int rc = clientDAO.updateClient(client, auditorId);
		return rc;
	
	}

  public int updateClientTradeshiftDetails(Client client, Integer auditorId) 
  {
    int rc = clientDAO.updateClientTradeshiftDetails(client, auditorId);
    return rc;
  }


	/*
	 * Site stuff ...
	 * 
	 */

	public List<SiteUser> getSiteUsersForClient(Integer clientId) {
		
		return getSiteUsersForClient(clientId, true);
		
	}
	public List<SiteUser> getSiteUsersForClient(Integer clientId, boolean showOnlyActive) {

		List<SiteUser> siteUsers = null;
		siteUsers = siteDAO.getSiteUsersForClient(clientId, showOnlyActive);
		return siteUsers;
		
	}
	public Site getSite(Integer siteId) {
		
		Site site = null;
		site = siteDAO.getSite(siteId);
		return site;
		
	}
	public SiteUser getSiteUser(Integer siteId) {
		
		SiteUser siteUser = null;
		siteUser = siteDAO.getSiteUser(siteId);
		return siteUser;
		
	}
	public SiteUserEntity getSiteUserEntity(Integer siteId) {
		
		return getSiteUserEntity(siteId, true);
		
	}

	public SiteUserEntity getSiteUserEntity(Integer siteId, boolean showOnlyActive) {

		SiteUserEntity siteUserEntity = null;
		siteUserEntity = siteDAO.getSiteUserEntity(siteId);
		siteUserEntity.setLocations(locationDAO.getLocationsForSite(siteId, showOnlyActive));
		return siteUserEntity;

	}
	public int insertSite(Site site, Integer auditorId) {

		Site duplicateSite = siteDAO.getSiteForName(site.getClientId(), site.getName());
		if (duplicateSite != null) {
			throw new DuplicateDataException("name");
		}
		duplicateSite = siteDAO.getSiteForCode(site.getClientId(), site.getCode());
		if (duplicateSite != null) {
			throw new DuplicateDataException("code");
		}
		
		int rc = siteDAO.insertSite(site, auditorId);
		return rc;
	
	}
	public int updateSite(Site site, Integer auditorId) {

		Site duplicateSite = siteDAO.getSiteForName(site.getClientId(), site.getName());
		if (duplicateSite != null &&
			!duplicateSite.getSiteId().equals(site.getSiteId())) {
			throw new DuplicateDataException("name");
		}
		duplicateSite = siteDAO.getSiteForCode(site.getClientId(), site.getCode());
		if (duplicateSite != null &&
			!duplicateSite.getSiteId().equals(site.getSiteId())) {
			throw new DuplicateDataException("code");
		}

		int rc = siteDAO.updateSite(site, auditorId);
		return rc;
	
	}
	public int deleteSite(Integer siteId, Integer noOfChanges, Integer auditorId) {

        int rc = siteDAO.deleteSite(siteId, noOfChanges, auditorId);
		return rc;
	
	}

	public int updateSiteDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
		int rc = 0;

		StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
		String commaDelimitedKey = null;
		int displayOrder = 0;
		while (stringTokenizer.hasMoreTokens())
		{
		    commaDelimitedKey = stringTokenizer.nextToken();
		    if (!zeroiseDisplayOrder)
		    {
		      // Increment the display order.
		      displayOrder++;
		    }
			StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
			Integer siteId = Integer.parseInt(st.nextToken());
			Integer noOfChanges = Integer.parseInt(st.nextToken());
		    rc += siteDAO.updateSiteDisplayOrder(siteId, displayOrder, noOfChanges, auditorId);
		}
		
		return rc;
	}
	
	/*
	 * Location stuff ...
	 * 
	 */

	public List<Location> getLocationsForSite(Integer siteId) {
		
		return getLocationsForSite(siteId, true);
		
	}
	
	public List<Location> getLocationsForSite(Integer siteId, boolean showOnlyActive) {

		List<Location> locations = null;
		locations = locationDAO.getLocationsForSite(siteId, showOnlyActive);
		return locations;
	}

	public Location getLocation(Integer locationId) {
		
		Location location = null;
    	location = locationDAO.getLocation(locationId);
		return location;
		
	}

	public LocationEntity getLocationEntity(Integer locationId) {
	
		return getLocationEntity(locationId, true);
		
	}
		
	public LocationEntity getLocationEntity(Integer locationId, boolean showOnlyActive) {
		
		LocationEntity location = null;
    	location = locationDAO.getLocationEntity(locationId);
		location.setShifts(shiftDAO.getShiftsForLocation(locationId, showOnlyActive));
		location.setDressCodes(dressCodeDAO.getDressCodesForLocation(locationId, showOnlyActive));
		location.setExpenses(expenseDAO.getExpensesForLocation(locationId, showOnlyActive));
    	location.setLocationJobProfileUsers(getLocationJobProfileUsersForLocation(locationId));
		location.setJobProfileUsers(getJobProfileUsersNotForLocation(location.getClientId(), locationId));
		return location;
		
	}

	public List<LocationJobProfileUser> getLocationJobProfileUsersForLocation(Integer locationId) {

		return locationJobProfileDAO.getLocationJobProfileUsersForLocation(locationId);

	}
	
  public List<LocationJobProfileUser> getLocationJobProfileUsersForLocationAndNhsAssignment(Integer locationId, String nhsAssignment) 
  {
    return locationJobProfileDAO.getLocationJobProfileUsersForLocationAndNhsAssignment(locationId, nhsAssignment);
  }
  
	public List<JobProfileUser> getJobProfileUsersNotForLocation(Integer clientId, Integer locationId) {
		
		return jobProfileDAO.getJobProfileUsersNotForLocation(clientId, locationId);
		
	}
		
	public int insertLocation(Location location, Integer auditorId) {

		Location duplicateLocation = locationDAO.getLocationForName(location.getSiteId(), location.getName());
		if (duplicateLocation != null) {
			throw new DuplicateDataException("name");
		}
		duplicateLocation = locationDAO.getLocationForCode(location.getSiteId(), location.getCode());
		if (duplicateLocation != null) {
			throw new DuplicateDataException("code");
		}
        int rc = locationDAO.insertLocation(location, auditorId);
		return rc;

	}

	public int updateLocation(Location location, Integer auditorId) {

		Location duplicateLocation = locationDAO.getLocationForName(location.getSiteId(), location.getName());
		if (duplicateLocation != null && 
			!duplicateLocation.getLocationId().equals(location.getLocationId())) {
			throw new DuplicateDataException("name");
		}
		duplicateLocation = locationDAO.getLocationForCode(location.getSiteId(), location.getCode());
		if (duplicateLocation != null && 
			!duplicateLocation.getLocationId().equals(location.getLocationId())) {
			throw new DuplicateDataException("code");
		}
        int rc = locationDAO.updateLocation(location, auditorId);
		return rc;

	}

	public int deleteLocation(Integer locationId, Integer noOfChanges, Integer auditorId) {

        int rc = locationDAO.deleteLocation(locationId, noOfChanges, auditorId);
		return rc;

	}

	public int updateLocationDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
		int rc = 0;

		StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
		String commaDelimitedKey = null;
		int displayOrder = 0;
		while (stringTokenizer.hasMoreTokens())
		{
		    commaDelimitedKey = stringTokenizer.nextToken();
		    if (!zeroiseDisplayOrder)
		    {
		      // Increment the display order.
		      displayOrder++;
		    }
			StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
			Integer locationId = Integer.parseInt(st.nextToken());
			Integer noOfChanges = Integer.parseInt(st.nextToken());
		    rc += locationDAO.updateLocationDisplayOrder(locationId, displayOrder, noOfChanges, auditorId);
		}
		
		return rc;
	}
	
    /*
     * DressCode stuff
     */

	public List<DressCode> getDressCodesForLocation(Integer locationId) {
		
		return getDressCodesForLocation(locationId, true);
		
	}

	public List<DressCode> getDressCodesForLocation(Integer locationId, boolean showOnlyActive) {

		List<DressCode> dressCodes = null;
		dressCodes = dressCodeDAO.getDressCodesForLocation(locationId, showOnlyActive);
		return dressCodes;

	}

	public DressCode getDressCode(Integer dressCodeId) {

		DressCode dressCode = null;
		dressCode = dressCodeDAO.getDressCode(dressCodeId);
		return dressCode;

	}

	public int insertDressCode(DressCode dressCode, Integer auditorId) {

		DressCode duplicateDressCode = dressCodeDAO.getDressCodeForName(dressCode.getLocationId(), dressCode.getName());
		if (duplicateDressCode != null) {
			throw new DuplicateDataException("name");
		}
        int rc = dressCodeDAO.insertDressCode(dressCode, auditorId);
		return rc;

	}

	public int updateDressCode(DressCode dressCode, Integer auditorId) {

		DressCode duplicateDressCode = dressCodeDAO.getDressCodeForName(dressCode.getLocationId(), dressCode.getName());
		if (duplicateDressCode != null && 
			!duplicateDressCode.getDressCodeId().equals(dressCode.getDressCodeId())) {
			throw new DuplicateDataException("name");
		}
        int rc = dressCodeDAO.updateDressCode(dressCode, auditorId);
		return rc;

	}

	public int deleteDressCode(Integer dressCodeId, Integer noOfChanges, Integer auditorId) {

        int rc = dressCodeDAO.deleteDressCode(dressCodeId, noOfChanges, auditorId);
		return rc;

	}
	
	public int updateDressCodeDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
		int rc = 0;

		StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
		String commaDelimitedKey = null;
		int displayOrder = 0;
		while (stringTokenizer.hasMoreTokens())
		{
		    commaDelimitedKey = stringTokenizer.nextToken();
		    if (!zeroiseDisplayOrder)
		    {
		      // Increment the display order.
		      displayOrder++;
		    }
			StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
			Integer dressCodeId = Integer.parseInt(st.nextToken());
			Integer noOfChanges = Integer.parseInt(st.nextToken());
		    rc += dressCodeDAO.updateDressCodeDisplayOrder(dressCodeId, displayOrder, noOfChanges, auditorId);
		}
		
		return rc;
	}

    /*
     * Shift stuff
     */

	public List<Shift> getShiftsForLocation(Integer locationId) {
		
		return getShiftsForLocation(locationId, true);
		
	}
	
	public List<Shift> getShiftsForLocation(Integer locationId, boolean showOnlyActive) {

		List<Shift> shifts = null;
		shifts = shiftDAO.getShiftsForLocation(locationId, showOnlyActive);
		return shifts;

	}

	public Shift getShift(Integer shiftId) {

		Shift shift = null;
		shift = shiftDAO.getShift(shiftId);
		return shift;

	}

	public int insertShift(Shift shift, Integer auditorId) {

		Shift duplicateShift = shiftDAO.getShiftForName(shift.getLocationId(), shift.getName());
		if (duplicateShift != null) {
			throw new DuplicateDataException("name");
		}
		duplicateShift = shiftDAO.getShiftForCode(shift.getLocationId(), shift.getCode());
		if (duplicateShift != null) {
			throw new DuplicateDataException("code");
		}
        int rc = shiftDAO.insertShift(shift, auditorId);
		return rc;

	}

	public int updateShift(Shift shift, Integer auditorId) {

		Shift duplicateShift = shiftDAO.getShiftForName(shift.getLocationId(), shift.getName());
		if (duplicateShift != null && 
			!duplicateShift.getShiftId().equals(shift.getShiftId())) {
			throw new DuplicateDataException("name");
		}
		duplicateShift = shiftDAO.getShiftForCode(shift.getLocationId(), shift.getCode());
		if (duplicateShift != null && 
			!duplicateShift.getShiftId().equals(shift.getShiftId())) {
			throw new DuplicateDataException("code");
		}
        int rc = shiftDAO.updateShift(shift, auditorId);
		return rc;

	}

	public int deleteShift(Integer shiftId, Integer noOfChanges, Integer auditorId) {

        int rc = shiftDAO.deleteShift(shiftId, noOfChanges, auditorId);
		return rc;

	}

	public int updateShiftDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
		int rc = 0;

		StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
		String commaDelimitedKey = null;
		int displayOrder = 0;
		while (stringTokenizer.hasMoreTokens())
		{
		    commaDelimitedKey = stringTokenizer.nextToken();
		    if (!zeroiseDisplayOrder)
		    {
		      // Increment the display order.
		      displayOrder++;
		    }
			StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
			Integer shiftId = Integer.parseInt(st.nextToken());
			Integer noOfChanges = Integer.parseInt(st.nextToken());
		    rc += shiftDAO.updateShiftDisplayOrder(shiftId, displayOrder, noOfChanges, auditorId);
		}
		
		return rc;
	}
	
	/*
	 * Expense stuff
	 * 
	 */

	public List<Expense> getExpensesForLocation(Integer locationId) {
		
		return getExpensesForLocation(locationId, true);
		
	}
	
	public List<Expense> getExpensesForLocation(Integer locationId, boolean showOnlyActive) {

		List<Expense> expenses = null;
		expenses = getExpenseDAO().getExpensesForLocation(locationId, showOnlyActive);
		return expenses;

	}

	public Expense getExpense(Integer expenseId) {

		Expense expense = null;
		expense = getExpenseDAO().getExpense(expenseId);
		return expense;

	}

	public int insertExpense(Expense expense, Integer auditorId) {

		Expense duplicateExpense = getExpenseDAO().getExpenseForName(expense.getLocationId(), expense.getName());
		if (duplicateExpense != null) {
			throw new DuplicateDataException("name");
		}
		duplicateExpense = getExpenseDAO().getExpenseForCode(expense.getLocationId(), expense.getCode());
		if (duplicateExpense != null) {
			throw new DuplicateDataException("code");
		}
		int rc = getExpenseDAO().insertExpense(expense, auditorId);
		return rc;

	}

	public int updateExpense(Expense expense, Integer auditorId) {

		Expense duplicateExpense = getExpenseDAO().getExpenseForName(expense.getLocationId(), expense.getName());
		if (duplicateExpense  != null && 
			!duplicateExpense.getExpenseId().equals(expense.getExpenseId())) {
			throw new DuplicateDataException("name");
		}
		duplicateExpense = getExpenseDAO().getExpenseForCode(expense.getLocationId(), expense.getCode());
		if (duplicateExpense  != null && 
			!duplicateExpense.getExpenseId().equals(expense.getExpenseId())) {
			throw new DuplicateDataException("code");
		}
        int rc = getExpenseDAO().updateExpense(expense, auditorId);
		return rc;

	}

	public int deleteExpense(Integer expenseId, Integer noOfChanges, Integer auditorId) {

		int rc = getExpenseDAO().deleteExpense(expenseId, noOfChanges, auditorId);
		return rc;

	}

	public int updateExpenseDisplayOrder(String order, boolean zeroiseDisplayOrder, Integer auditorId) {
		int rc = 0;

		StringTokenizer stringTokenizer = new StringTokenizer(order, "|");
		String commaDelimitedKey = null;
		int displayOrder = 0;
		while (stringTokenizer.hasMoreTokens())
		{
		    commaDelimitedKey = stringTokenizer.nextToken();
		    if (!zeroiseDisplayOrder)
		    {
		      // Increment the display order.
		      displayOrder++;
		    }
			StringTokenizer st = new StringTokenizer(commaDelimitedKey, ",");
			Integer expenseId = Integer.parseInt(st.nextToken());
			Integer noOfChanges = Integer.parseInt(st.nextToken());
		    rc += getExpenseDAO().updateExpenseDisplayOrder(expenseId, displayOrder, noOfChanges, auditorId);
		}
		
		return rc;
	}

	/*
	 * LocationJobProfile stuff
	 * 
	 */

	public List<LocationJobProfileUser> getLocationJobProfileUsersForClient(Integer clientId) {
		
		return getLocationJobProfileUsersForClient(clientId);
		
	}

	public List<LocationJobProfileUser> getLocationJobProfileUsersForClient(Integer clientId, boolean showOnlyActive) {
		
		List<LocationJobProfileUser> locationJobProfileUsers = null;
		locationJobProfileUsers = locationJobProfileDAO.getLocationJobProfileUsersForClient(clientId, showOnlyActive);
		return locationJobProfileUsers;

	}
	
	public LocationJobProfileUser getLocationJobProfileUser(Integer locationJobProfileUserId) {

		LocationJobProfileUser locationJobProfileUser = null;
		locationJobProfileUser = locationJobProfileDAO.getLocationJobProfileUser(locationJobProfileUserId);
		return locationJobProfileUser;

	}

	public int insertLocationJobProfile(LocationJobProfile locationJobProfile, Integer auditorId) {

        int rc = locationJobProfileDAO.insertLocationJobProfile(locationJobProfile, auditorId);
		return rc;

	}

	public int deleteLocationJobProfile(Integer locationJobProfileId, Integer noOfChanges, Integer auditorId) {

		int rc = locationJobProfileDAO.deleteLocationJobProfile(locationJobProfileId, noOfChanges, auditorId);
		return rc;

	}
	
	public int updateLocationJobProfileRate(Integer locationJobProfileId, BigDecimal value, Integer noOfChanges, Integer auditorId) {

		int rc = locationJobProfileDAO.updateLocationJobProfileRate(locationJobProfileId, value, noOfChanges, auditorId);
		return rc;

	}
	
	/*
	 * Manager stuff ...
	 * 
	 */

	public List<Manager> getManagersForClient(Integer clientId) {
		
		return getManagersForClient(clientId, true);
	}
	
	public List<Manager> getManagersForClient(Integer clientId, boolean showOnlyActive) {

		List<Manager> managers = null;
		managers = managerDAO.getManagersForClient(clientId, showOnlyActive);
		return managers;
	}

	public Manager getManager(Integer managerId) {
		
		Manager manager = null;
    	manager = managerDAO.getManager(managerId);
		return manager;
		
	}

	public ManagerEntity getManagerEntity(Integer managerId) {

		ManagerEntity managerEntity = null;
		managerEntity = managerDAO.getManagerEntity(managerId);
		managerEntity.setLocationManagerJobProfileUsers(locationManagerJobProfileDAO.getLocationManagerJobProfileUsersForManager(managerId));
		managerEntity.setLocationJobProfileUsers(getLocationJobProfileDAO().getLocationJobProfileUsersNotForManager(managerEntity.getClientId(), managerId));
		managerEntity.setManagerAccessUsers(managerAccessDAO.getManagerAccessUsersForManager(managerId));
		managerEntity.setMgrAccesses(mgrAccessDAO.getMgrAccessesNotForManager(managerId));
		managerEntity.setManagerAccessGroupUsers(managerAccessGroupDAO.getManagerAccessGroupUsersForManager(managerId));
		managerEntity.setMgrAccessGroups(mgrAccessGroupDAO.getMgrAccessGroupsNotForManager(managerEntity.getClientId(), managerId));
		return managerEntity;

	}

	
	public int insertManager(Manager manager, Integer auditorId) {

		Manager duplicateManager = managerDAO.getManagerForLogin(manager.getClientId(), manager.getUser().getLogin());
		if (duplicateManager != null) {
			throw new DuplicateDataException("login");
		}
        int rc = managerDAO.insertManager(manager, auditorId);
		return rc;

	}

	public int updateManager(Manager manager, Integer auditorId) {

		Manager duplicateManager = managerDAO.getManagerForLogin(manager.getClientId(), manager.getUser().getLogin());
		if (duplicateManager != null &&
			!duplicateManager.getManagerId().equals(manager.getManagerId())) {
			throw new DuplicateDataException("login");
		}
        int rc = managerDAO.updateManager(manager, auditorId);
		return rc;

	}

	public int deleteManager(Integer managerId, Integer noOfChanges, Integer auditorId) {

        int rc = managerDAO.deleteManager(managerId, noOfChanges, auditorId);
		return rc;

	}

  	public int updateManagerPwd(Integer managerId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId) {

		int rc = managerDAO.updateManagerPwd(managerId, newPwd, pwdHint, noOfChanges, auditorId);
		return rc;
	
	}
	
  	public int updateManagerSecretWord(Integer managerId, String newSecretWord, Integer noOfChanges, Integer auditorId) {

		int rc = managerDAO.updateManagerSecretWord(managerId, newSecretWord, noOfChanges, auditorId);
		return rc;
	
	}

	/*
	 * LocationManagerJobProfile stuff
	 * 
	 */

	public List<LocationManagerJobProfileUser> getLocationManagerJobProfileUsersForManager(Integer managerId) {

		List<LocationManagerJobProfileUser> locationManagerJobProfileUsers = null;
		locationManagerJobProfileUsers = locationManagerJobProfileDAO.getLocationManagerJobProfileUsersForManager(managerId);
		return locationManagerJobProfileUsers;

	}

	public int insertLocationManagerJobProfile(LocationManagerJobProfile locationManagerJobProfile, Integer auditorId) {

        int rc = locationManagerJobProfileDAO.insertLocationManagerJobProfile(locationManagerJobProfile, auditorId);
		return rc;

	}

	public int deleteLocationManagerJobProfile(Integer locationManagerJobProfileId, Integer noOfChanges, Integer auditorId) {

		int rc = locationManagerJobProfileDAO.deleteLocationManagerJobProfile(locationManagerJobProfileId, noOfChanges, auditorId);
		return rc;

	}

	/*
	 * MgrAccess stuff
	 * 
	 */

	public List<MgrAccess> getMgrAccesses(boolean showOnlyActive) {

		List<MgrAccess> mgrAccesses = null;
		mgrAccesses = mgrAccessDAO.getMgrAccesses(showOnlyActive);
		return mgrAccesses;

	}
	public List<MgrAccess> getActiveMgrAccessesForManager(Integer managerId) {

		List<MgrAccess> mgrAccesses = null;
		mgrAccesses = mgrAccessDAO.getActiveMgrAccessesForManager(managerId);
		return mgrAccesses;

	}
	public MgrAccess getMgrAccess(Integer mgrAccessId) {

		MgrAccess mgrAccess = null;
		mgrAccess = mgrAccessDAO.getMgrAccess(mgrAccessId);
		return mgrAccess;

	}
	public int insertMgrAccess(MgrAccess mgrAccess, Integer auditorId) {

		MgrAccess duplicateMgrAccess = mgrAccessDAO.getMgrAccessForName(mgrAccess.getName());
		if (duplicateMgrAccess != null) {
			throw new DuplicateDataException("name");
		}
		int rc = mgrAccessDAO.insertMgrAccess(mgrAccess, auditorId);
		return rc;

	}

	public int updateMgrAccess(MgrAccess mgrAccess, Integer auditorId) {

		MgrAccess duplicateMgrAccess = mgrAccessDAO.getMgrAccessForName(mgrAccess.getName());
		if (duplicateMgrAccess  != null && 
			!duplicateMgrAccess.getMgrAccessId().equals(mgrAccess.getMgrAccessId())) {
			throw new DuplicateDataException("name");
		}
        int rc = mgrAccessDAO.updateMgrAccess(mgrAccess, auditorId);
		return rc;

	}

	public int deleteMgrAccess(Integer mgrAccessId, Integer noOfChanges, Integer auditorId) {

		int rc = mgrAccessDAO.deleteMgrAccess(mgrAccessId, noOfChanges, auditorId);
		return rc;

	}

	/*
	 * MgrAccessGroup stuff
	 * 
	 */
	
	public List<MgrAccessGroup> getMgrAccessGroupsForClient(Integer clientId, boolean showOnlyActive) {

		List<MgrAccessGroup> mgrAccessGroups = null;
		mgrAccessGroups = mgrAccessGroupDAO.getMgrAccessGroupsForClient(clientId, showOnlyActive);
		return mgrAccessGroups;

	}
	public MgrAccessGroup getMgrAccessGroup(Integer mgrAccessGroupId) {

		MgrAccessGroup mgrAccessGroup = null;
		mgrAccessGroup = mgrAccessGroupDAO.getMgrAccessGroup(mgrAccessGroupId);
		return mgrAccessGroup;

	}
	public MgrAccessGroupEntity getMgrAccessGroupEntity(Integer mgrAccessGroupId) {

		MgrAccessGroupEntity mgrAccessGroupEntity = null;
		mgrAccessGroupEntity = mgrAccessGroupDAO.getMgrAccessGroupEntity(mgrAccessGroupId);
		mgrAccessGroupEntity.setMgrAccessGroupItemUsers(mgrAccessGroupItemDAO.getMgrAccessGroupItemUsersForMgrAccessGroup(mgrAccessGroupId));
		mgrAccessGroupEntity.setMgrAccesses(mgrAccessDAO.getMgrAccessesNotForMgrAccessGroup(mgrAccessGroupId));
		return mgrAccessGroupEntity;

	}
	public int insertMgrAccessGroup(MgrAccessGroup mgrAccessGroup, Integer auditorId) {

		MgrAccessGroup duplicateMgrAccessGroup = mgrAccessGroupDAO.getMgrAccessGroupForName(mgrAccessGroup.getClientId(), mgrAccessGroup.getName());
		if (duplicateMgrAccessGroup != null) {
			throw new DuplicateDataException("name");
		}
		int rc = mgrAccessGroupDAO.insertMgrAccessGroup(mgrAccessGroup, auditorId);
		return rc;

	}

	public int updateMgrAccessGroup(MgrAccessGroup mgrAccessGroup, Integer auditorId) {

		MgrAccessGroup duplicateMgrAccessGroup = mgrAccessGroupDAO.getMgrAccessGroupForName(mgrAccessGroup.getClientId(), mgrAccessGroup.getName());
		if (duplicateMgrAccessGroup  != null && 
			!duplicateMgrAccessGroup.getMgrAccessGroupId().equals(mgrAccessGroup.getMgrAccessGroupId())) {
			throw new DuplicateDataException("name");
		}
        int rc = mgrAccessGroupDAO.updateMgrAccessGroup(mgrAccessGroup, auditorId);
		return rc;

	}

	public int deleteMgrAccessGroup(Integer mgrAccessGroupId, Integer noOfChanges, Integer auditorId) {

		int rc = mgrAccessGroupDAO.deleteMgrAccessGroup(mgrAccessGroupId, noOfChanges, auditorId);
		return rc;

	}

	public int insertMgrAccessGroupItem(MgrAccessGroupItem mgrAccessGroupItem, Integer auditorId) {

        int rc = mgrAccessGroupItemDAO.insertMgrAccessGroupItem(mgrAccessGroupItem, auditorId);
		return rc;

	}

	public int deleteMgrAccessGroupItem(Integer mgrAccessGroupItemId, Integer noOfChanges, Integer auditorId) {

		int rc = mgrAccessGroupItemDAO.deleteMgrAccessGroupItem(mgrAccessGroupItemId, noOfChanges, auditorId);
		return rc;

	}

	
	/*
	 * ManagerAccess stuff
	 * 
	 */

	public List<ManagerAccessUser> getManagerAccessUsersForManager(Integer managerId) {

		List<ManagerAccessUser> managerAccesses = null;
		managerAccesses = managerAccessDAO.getManagerAccessUsersForManager(managerId);
		return managerAccesses;

	}

	public int insertManagerAccess(ManagerAccess managerAccess, Integer auditorId) {

        int rc = managerAccessDAO.insertManagerAccess(managerAccess, auditorId);
		return rc;

	}

	public int deleteManagerAccess(Integer managerAccessId, Integer noOfChanges, Integer auditorId) {

		int rc = managerAccessDAO.deleteManagerAccess(managerAccessId, noOfChanges, auditorId);
		return rc;

	}

	/*
	 * ManagerAccessGroup stuff
	 * 
	 */

	public List<ManagerAccessGroupUser> getManagerAccessGroupUsersForManager(Integer managerId) {

		List<ManagerAccessGroupUser> managerAccessGroupes = null;
		managerAccessGroupes = managerAccessGroupDAO.getManagerAccessGroupUsersForManager(managerId);
		return managerAccessGroupes;

	}

	public int insertManagerAccessGroup(ManagerAccessGroup managerAccessGroup, Integer auditorId) {

        int rc = managerAccessGroupDAO.insertManagerAccessGroup(managerAccessGroup, auditorId);
		return rc;

	}

	public int deleteManagerAccessGroup(Integer managerAccessGroupId, Integer noOfChanges, Integer auditorId) {

		int rc = managerAccessGroupDAO.deleteManagerAccessGroup(managerAccessGroupId, noOfChanges, auditorId);
		return rc;

	}

	/*
	 * Uplift stuff
	 * 
	 */

  public List<Uplift> getUpliftsForClient(Integer clientId) {

    return getUpliftsForClient(clientId, true);

  }

  public List<Uplift> getUpliftsForClient(Integer clientId, boolean showOnlyActive) {

    List<Uplift> uplifts = null;
    uplifts = upliftDAO.getUpliftsForClient(clientId, showOnlyActive);
    // Load any UpliftMinutes into their respective Uplifts...
    loadUpliftMinutesIntoUplifts(clientId, uplifts);
    return uplifts;

  }

	public Uplift getUplift(Integer upliftId) {

		Uplift uplift = null;
		uplift = upliftDAO.getUplift(upliftId);
		return uplift;

	}

	public int insertUplift(Uplift uplift, Integer auditorId) {

		int rc = upliftDAO.insertUplift(uplift, auditorId);
		return rc;

	}

	public int updateUplift(Uplift uplift, Integer auditorId) {

        int rc = upliftDAO.updateUplift(uplift, auditorId);
		return rc;

	}

	public int deleteUplift(Integer upliftId, Integer noOfChanges, Integer auditorId) {

		int rc = upliftDAO.deleteUplift(upliftId, noOfChanges, auditorId);
		return rc;

	}

  /*
   * UpliftMinute stuff
   * 
   */

  public List<UpliftMinute> getUpliftMinutesForUplift(Integer upliftId) 
  {

    return getUpliftMinutesForUplift(upliftId, true);

  }

  public List<UpliftMinute> getUpliftMinutesForUplift(Integer upliftId, boolean showOnlyActive) 
  {

    List<UpliftMinute> upliftMinutes = null;
    upliftMinutes = upliftMinuteDAO.getUpliftMinutesForUplift(upliftId, showOnlyActive);
    return upliftMinutes;

  }

  public List<UpliftMinute> getUpliftMinutesForClient(Integer clientId) 
  {

    return getUpliftMinutesForClient(clientId, true);

  }

  public List<UpliftMinute> getUpliftMinutesForClient(Integer clientId, boolean showOnlyActive) 
  {

    List<UpliftMinute> upliftMinutes = null;
    upliftMinutes = upliftMinuteDAO.getUpliftMinutesForClient(clientId, showOnlyActive);
    return upliftMinutes;

  }

  public UpliftMinute getUpliftMinute(Integer upliftMinuteId) 
  {

    UpliftMinute upliftMinute = null;
    upliftMinute = upliftMinuteDAO.getUpliftMinute(upliftMinuteId);
    return upliftMinute;

  }

  public int insertUpliftMinute(UpliftMinute upliftMinute, Integer auditorId) 
  {

    int rc = upliftMinuteDAO.insertUpliftMinute(upliftMinute, auditorId);
    return rc;

  }

  public int updateUpliftMinute(UpliftMinute upliftMinute, Integer auditorId) 
  {

    int rc = upliftMinuteDAO.updateUpliftMinute(upliftMinute, auditorId);
    return rc;

  }

  public int deleteUpliftMinute(Integer upliftMinuteId, Integer noOfChanges, Integer auditorId) 
  {

    int rc = upliftMinuteDAO.deleteUpliftMinute(upliftMinuteId, noOfChanges, auditorId);
    return rc;

  }

  public List<UpliftMinuteUser> getUpliftMinuteUsersForUplift(Integer upliftId) 
  {

    return getUpliftMinuteUsersForUplift(upliftId, true);

  }

  public List<UpliftMinuteUser> getUpliftMinuteUsersForUplift(Integer upliftId, boolean showOnlyActive) 
  {

    List<UpliftMinuteUser> upliftMinuteUsers = null;
    upliftMinuteUsers = upliftMinuteDAO.getUpliftMinuteUsersForUplift(upliftId, showOnlyActive);
    return upliftMinuteUsers;

  }

  public void loadUpliftMinutesIntoUplifts(Integer clientId, List<Uplift> uplifts)
  {
    List<UpliftMinute> upliftMinutes = getUpliftMinutesForClient(clientId);
    if (!upliftMinutes.isEmpty())
    {
      for (Uplift uplift : uplifts)
      {
        // For each Uplift...
        if (uplift.getUpliftMinutePeriod() > 0)
        {
          // This Uplift has UpliftMinutes...
          for (UpliftMinute upliftMinute : upliftMinutes)
          {
            // // For each UpliftMinute...
            if (uplift.getUpliftId().equals(upliftMinute.getUpliftId()))
            {
              // The current UpliftMinute IS for the current Uplift. 
              if (uplift.getUpliftMinutes() == null)
              {
                // The current Uplift does NOT have an UpliftMinute list yet. Create one.
                uplift.setUpliftMinutes(new ArrayList<UpliftMinute>());
              }
              // Add upliftMinute to the Uplift's UpliftMinute list.
              uplift.getUpliftMinutes().add(upliftMinute);
              System.out.println("Day: " + uplift.getUpliftDay() + " Hour: " + uplift.getUpliftHour() + " Minute: " + upliftMinute.getUpliftMinute() + " Factor: " + upliftMinute.getUpliftFactor());
            }
          }
        }
      }
    }
  }

	public List<ClientAgencyUser> getClientAgencyUsersForAgency(Integer agencyId) {
		
		return getClientAgencyUsersForAgency(agencyId, true);
		
	}
	public List<ClientAgencyUser> getClientAgencyUsersForAgency(Integer agencyId, boolean showOnlyActive) {
		
		List<ClientAgencyUser> clientAgencyUsers = null;
		clientAgencyUsers = clientAgencyDAO.getClientAgencyUsersForAgency(agencyId, showOnlyActive);
		return clientAgencyUsers;

	}
	
  public List<ClientAgencyUser> getClientAgencyUsersForAgencyInNameGroup(Integer agencyId, String indexLetter) 
  {
    return getClientAgencyUsersForAgencyInNameGroup(agencyId, indexLetter, true);
  }

  public List<ClientAgencyUser> getClientAgencyUsersForAgencyInNameGroup(Integer agencyId, String indexLetter, boolean showOnlyActive) 
  {
    List<ClientAgencyUser> clientAgencyUsers = null;
    clientAgencyUsers = clientAgencyDAO.getClientAgencyUsersForAgencyInNameGroup(agencyId, indexLetter, showOnlyActive);
    return clientAgencyUsers;
  }
  
	public ClientAgency getClientAgency(Integer clientAgencyId) {
		
		ClientAgency clientAgency = null;
		clientAgency = clientAgencyDAO.getClientAgency(clientAgencyId);
		return clientAgency;
		
	}

	public ClientAgency getClientAgencyForClientAndAgency(Integer clientId, Integer agencyId) {
	
		ClientAgency clientAgency = null;
		clientAgency = clientAgencyDAO.getClientAgencyForClientAndAgency(clientId, agencyId);
		return clientAgency;
		
	}

	public ClientAgencyUserEntity getClientAgencyUserEntity(Integer clientAgencyId, boolean showOnlyActive) {

		ClientAgencyUserEntity clientAgencyUserEntity = null;
		clientAgencyUserEntity = clientAgencyDAO.getClientAgencyUserEntity(clientAgencyId);
		clientAgencyUserEntity.setClientAgencyGradeUsers(clientAgencyGradeDAO.getClientAgencyGradeUsersForClientAgency(clientAgencyId));
		clientAgencyUserEntity.setGrades(getGradeDAO().getGradesNotForClientAgency(clientAgencyUserEntity.getClientId(), clientAgencyId));
		return clientAgencyUserEntity;

	}

	public int insertClientAgency(ClientAgency clientAgency, Integer auditorId) {

        int rc = clientAgencyDAO.insertClientAgency(clientAgency, auditorId);
		return rc;

	}

	public int deleteClientAgency(Integer clientAgencyId, Integer noOfChanges, Integer auditorId) {

		int rc = clientAgencyDAO.deleteClientAgency(clientAgencyId, noOfChanges, auditorId);
		return rc;

	}

	
	public int updateClientAgency(ClientAgency clientAgency, Integer auditorId) {
	
	    int rc = clientAgencyDAO.updateClientAgency(clientAgency, auditorId);
		return rc;
	
	}
	
	public int insertClientAgencyGrade(ClientAgencyGrade clientAgencyGrade, Integer auditorId) {

        int rc = clientAgencyGradeDAO.insertClientAgencyGrade(clientAgencyGrade, auditorId);
		return rc;

	}

	public int deleteClientAgencyGrade(Integer clientAgencyGradeId, Integer noOfChanges, Integer auditorId) {

		int rc = clientAgencyGradeDAO.deleteClientAgencyGrade(clientAgencyGradeId, noOfChanges, auditorId);
		return rc;

	}

	public ClientAgencyGrade getClientAgencyGrade(Integer clientAgencyGradeId) {
		
		ClientAgencyGrade clientAgencyGrade = null;
		clientAgencyGrade = clientAgencyGradeDAO.getClientAgencyGrade(clientAgencyGradeId);
		return clientAgencyGrade;
		
	}

	public int updateClientAgencyGrade(ClientAgencyGrade clientAgencyGrade, Integer auditorId) {

		int	rc = clientAgencyGradeDAO.updateClientAgencyGrade(clientAgencyGrade, auditorId);
		return rc;
	
	}

	public List<ClientAgencyGrade> getClientAgencyGradesForJobProfile(Integer jobProfileId) {

		List<ClientAgencyGrade> clientAgencyGrades = null;
		clientAgencyGrades = clientAgencyGradeDAO.getClientAgencyGradesForJobProfile(jobProfileId);
		return clientAgencyGrades;

	}

	public LocationUser getLocationUser(Integer locationId) {

		LocationUser locationUser = null;
    	locationUser = locationDAO.getLocationUser(locationId);
		return locationUser;

	}
	
	public int insertBooking(Booking booking, 
				            BookingDate[] bookingDates, 
				            ClientAgencyJobProfileGrade[] bookingGrades, 
				            Expense[] bookingExpenses, 
				            Integer auditorId) {

		booking.setMinBookingDate(bookingDates[0].getBookingDate());
		booking.setMaxBookingDate(bookingDates[bookingDates.length - 1].getBookingDate());
		booking.setNoOfBookingDates(bookingDates.length);
		int b = bookingDAO.insertBooking(booking, auditorId);
		int d = bookingDateDAO.insertBookingDates(booking.getBookingId(), bookingDates, auditorId);
		int g = bookingGradeDAO.insertBookingGrades(booking.getBookingId(), bookingGrades, auditorId);
		int e = bookingExpenseDAO.insertBookingExpenses(booking.getBookingId(), bookingExpenses, auditorId);
		
		// need to decrement the budget for the locationJobProfile
		
		// TODO - tidy up !!!
		DecimalFormat df = new DecimalFormat("#000");
		int bt = insertBudgetTransaction(booking.getLocationId(), 
 	 	                                 booking.getJobProfileId(), 
				                         booking.getValue().multiply(new BigDecimal(-1)),
				                         new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
				                         // needs to be multi-lingual
						                 "New booking - " + df.format(booking.getBookingId()), 
						                 auditorId);
		
		return b;
		
	}

	public int updateBooking(Booking booking, 
							BookingDate[] bookingDates, 
							ClientAgencyJobProfileGrade[] bookingGrades, 
							Expense[] bookingExpenses, 
							Integer auditorId) {
		
		Booking origBooking = bookingDAO.getBooking(booking.getBookingId()); 
		
		booking.setMinBookingDate(bookingDates[0].getBookingDate());
		booking.setMaxBookingDate(bookingDates[bookingDates.length - 1].getBookingDate());
		booking.setNoOfBookingDates(bookingDates.length);
		int b = bookingDAO.updateBooking(booking, auditorId);
		int d1 = bookingDateDAO.deleteBookingDatesForBooking(booking.getBookingId(), auditorId);
		int d2 = bookingDateDAO.insertBookingDates(booking.getBookingId(), bookingDates, auditorId);
		int g1 = bookingGradeDAO.deleteBookingGradesForBooking(booking.getBookingId(), auditorId);
		int g2 = bookingGradeDAO.insertBookingGrades(booking.getBookingId(), bookingGrades, auditorId);
		int e1 = bookingExpenseDAO.deleteBookingExpensesForBooking(booking.getBookingId(), auditorId);
		int e2 = bookingExpenseDAO.insertBookingExpenses(booking.getBookingId(), bookingExpenses, auditorId);
		
		// need to ADJUST the budget by the difference for the locationJobProfile
		// (add it in and then take it out as location/jobProfile may have changed) 
		
		// TODO - tidy up !!!
		DecimalFormat df = new DecimalFormat("#000");
		int bt1 = insertBudgetTransaction(origBooking.getLocationId(), 
                                  		  origBooking.getJobProfileId(), 
		                                  origBooking.getValue(), 
		                                  new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
		                                  // needs to be multi-lingual
		                                  "Amended booking - " + df.format(origBooking.getBookingId()), 
		                                  auditorId);
		int bt2 = insertBudgetTransaction(booking.getLocationId(), 
		                                  booking.getJobProfileId(), 
		                                  booking.getValue().multiply(new BigDecimal(-1)), 
		                                  new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
 		                                  // needs to be multi-lingual
		                                  "Amended booking - " + df.format(booking.getBookingId()), 
		                                  auditorId);

		return b;
		
	}

    public int updateBookingOpen(Integer bookingId, Integer noOfChanges, Integer auditorId) {

        //
		// long winded for now !!!!
		//
		
		// get now
		Timestamp now = new Timestamp(new java.util.Date().getTime());
		// get the start timestamp of the first shift
        List<BookingDate> bookingDates = getBookingDateDAO().getBookingDatesForBooking(bookingId, true);
        // update the status of the dates
        for (BookingDate bookingDate: bookingDates) {
        	int rc = getBookingDateDAO().updateBookingDateStatus(bookingDate.getBookingDateId(), bookingDate.getNoOfChanges(), auditorId, BookingGrade.BOOKINGGRADE_STATUS_OPEN);
        }
        
        // get first date
        BookingDate bookingDate = bookingDates.get(0);
        //
        Integer shiftId = bookingDate.getShiftId();
        //
        Shift shift = getShiftDAO().getShift(shiftId);
        //
        Timestamp firstShiftTimestamp = new Timestamp(bookingDate.getBookingDate().getTime() + shift.getStartTime().getTime());
        //
        System.out.println(firstShiftTimestamp.toString());
        //
        long diff = firstShiftTimestamp.getTime() - now.getTime();
        //		
        List<BookingGradeUser> bookingGrades = getBookingGradeDAO().getBookingGradeUsersForBooking(bookingId, true);		
        //
        for (BookingGradeUser bookingGradeUser: bookingGrades) {
        
        	Integer clientAgencyJobProfileGradeId = bookingGradeUser.getClientAgencyJobProfileGradeId();
        	ClientAgencyJobProfileGrade clientAgencyJobProfileGrade = clientAgencyJobProfileGradeDAO.getClientAgencyJobProfileGrade(clientAgencyJobProfileGradeId); 
        	Integer clientAgencyJobProfileId = clientAgencyJobProfileGrade.getClientAgencyJobProfileId();
        	ClientAgencyJobProfile clientAgencyJobProfile = clientAgencyJobProfileDAO.getClientAgencyJobProfile(clientAgencyJobProfileId);
        	BigDecimal percentage = clientAgencyJobProfile.getPercentage();

    		// find out the number of milliseconds between 'now' and the first shift = 'X'
    		// go through the agency/grades and based on the %ages set the sendtimestamps ...
    		//
    		// now + (((100 - %age) / 100) * 'X')
    		//
    		
        	Float f = new Float( ((100.0 - percentage.floatValue()) / 100.0) * diff );
        	Long l = new Long(f.longValue());
        	Timestamp sendTimestamp = new Timestamp(now.getTime() + l);
        	
        	bookingGradeUser.setSendTimestamp(sendTimestamp);
        	
        	int rc = getBookingGradeDAO().updateBookingGrade(bookingGradeUser, auditorId);
  
        }
		
        int rc = getBookingDAO().updateBookingOpen(bookingId, noOfChanges, auditorId);
		
		return rc;

	}

	public int updateBookingDateOvertime(BookingDateUserApplicant bookingDate, Integer auditorId) {

		int rc = bookingDateDAO.updateBookingDateOvertime(bookingDate, auditorId);
		return rc;
	
	}
	
  public Booking getBooking(Integer bookingId) {
    
    Booking booking = null;
      booking = bookingDAO.getBooking(bookingId);
    return booking;

  }

  public BookingUser getBookingUser(Integer bookingId) 
  {
    BookingUser bookingUser = null;
    bookingUser = bookingDAO.getBookingUser(bookingId);
    return bookingUser;
  }

  public List<Booking> getBookingsForLocation(Integer locationId) 
  {
    List<Booking> bookings = getBookingDAO().getBookingsForLocation(locationId);
    return bookings;
  }

  public List<NhsBooking> getNhsBookingsForLocation(Integer agencyId, Integer locationId) 
  {
    List<NhsBooking> nhsBookings = getNhsBookingDAO().getNhsBookingsForLocation(agencyId, locationId);
    return nhsBookings;
  }

	public int updateBookingInfo(Booking booking, Integer auditorId) {

		int rc = bookingDAO.updateBookingInfo(booking, auditorId);
		return rc;
	
	}

    public int updateBookingExpenses(Booking booking, Expense[] bookingExpenses, Integer auditorId) {

		int rc = getBookingDAO().updateBookingExpensesText(booking.getBookingId(), booking.getExpensesText(), booking.getNoOfChanges(), auditorId);

		// TODO - shouldn't really be deleting them - they may have already been used !!!
        int e1 = getBookingExpenseDAO().deleteBookingExpensesForBooking(booking.getBookingId(), auditorId);
        int e2 = getBookingExpenseDAO().insertBookingExpenses(booking.getBookingId(), bookingExpenses, auditorId);
		
		return rc;

    }
	
	public int updateBookingDateCancel(Integer bookingDateId, String cancelText, Integer noOfChanges, Integer auditorId) {

		int rc = 0;
		
        BookingDate bookingDate = getBookingDateDAO().getBookingDate(bookingDateId);
        Booking booking = getBookingDAO().getBooking(bookingDate.getBookingId());
        
		// checking the status of the booking before proceeding AND adding money back into budgets !!!
		//
        // BECAUSE ... if this is the only uncancelled date of the booking and ALL the others dates are cancelled we should
        //             ALSO cancel the booking not just cancel the date. we find this out by checking for min and max dates
        //             if none are found there cannot be set - the min and max will remain as the LAST date to be cancelled
                
        // store the current status ... this will be overwritten below if necessary ...
        Integer status = booking.getStatus();

        Integer lastBookingDateShiftId = 0;
        boolean oneShiftForBookingDates = true;

//        if (bookingDate.getBookingDate().equals(booking.getMinBookingDate()) ||
//           	bookingDate.getBookingDate().equals(booking.getMaxBookingDate())) {
            // need to change min or max date - loop through dates an find min and max NON cancelled dates
            List<BookingDate> bookingDates = getBookingDateDAO().getBookingDatesForBooking(booking.getBookingId(), true); 
            Date minBookingDate = null;
            Date maxBookingDate = null;
            for (BookingDate bookingDateX: bookingDates) {
            	if (!bookingDateX.getBookingDate().equals(bookingDate.getBookingDate())) {
        			// not the date we are currently cancelling
        			if (bookingDateX.getStatus() != BookingDate.BOOKINGDATE_STATUS_CANCELLED) {
            			// date isn't cancelled
        				if (minBookingDate == null || bookingDateX.getBookingDate().before(minBookingDate)) {
        					// min not set OR current date is before the current min variable
        					minBookingDate = bookingDateX.getBookingDate();
        				}
        				if (maxBookingDate == null || bookingDateX.getBookingDate().after(maxBookingDate)) {
        					// max not set OR current date is after the current max variable
        					maxBookingDate = bookingDateX.getBookingDate();
        				}
        				//
        	        	if (lastBookingDateShiftId.equals(0)) {
        	        		// first one
        	        		lastBookingDateShiftId = bookingDateX.getShiftId();
        	        	}
        	        	if (!bookingDateX.getShiftId().equals(lastBookingDateShiftId)) {
        	        		// more than one shift selected so set the boolean to false
        	        		oneShiftForBookingDates = false;
        	        	}
        			}
            	}
            }
            if (minBookingDate == null) {
                // No NON-canclled dates found so CANCEL the booking - and DON'T change min and max dates or shift !!!
            	status = Booking.BOOKING_STATUS_CANCELLED;
            	// Close all Open bookingGrades
        		List<BookingGradeUser> bookingGradeUsers = getBookingGradeDAO().getBookingGradeUsersForBooking(booking.getBookingId(), true);
                for (BookingGradeUser bookingGradeUser: bookingGradeUsers) {
                    if (bookingGradeUser.getStatus() == BookingGrade.BOOKINGGRADE_STATUS_OPEN) {
                    	int bg = getBookingGradeDAO().updateBookingGradeStatus(bookingGradeUser.getBookingGradeId(), bookingGradeUser.getNoOfChanges(), auditorId, BookingGrade.BOOKINGGRADE_STATUS_CLOSED);         	
                    }
                }
            	
            }
            else {
	            booking.setMinBookingDate(minBookingDate);
	            booking.setMaxBookingDate(maxBookingDate);
	    	    if (oneShiftForBookingDates) {
	    	    	booking.setShiftId(lastBookingDateShiftId);
	    	    }
            }
//        }

            
        // TODO - below - temporary should have a better way of finding out how much the manager has agreed with the agency
        List<BookingGradeApplicantDateUser> bookingGradeApplicantDates = bookingGradeApplicantDateDAO.getBookingGradeApplicantDateUsersForBookingFilled(booking.getBookingId(), true);
        
        BigDecimal agyValue = null; // bookingDate.getAgyValue();
        Integer agencyId = null;
        
        for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser: bookingGradeApplicantDates) {
        	if (bookingGradeApplicantDateUser.getBookingDateId().equals(bookingDateId)) {
        		
        		agyValue = bookingGradeApplicantDateUser.getValue();
        		agencyId = bookingGradeApplicantDateUser.getAgencyId();
        		
        		break;
        	}
        }
        // TODO - above
    
        // adjust budget - give the money back!
		DecimalFormat df = new DecimalFormat("#000");
		
        // if completed (authorized !!!) need to use worked values
        // if filled use the 'agreed' agency value else use RRP value

		BigDecimal value = bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_COMPLETED ? bookingDate.getWorkedChargeRateValue() :
								bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_FILLED ? agyValue : 
									bookingDate.getValue();
		BigDecimal vatValue = bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_COMPLETED ? bookingDate.getTotalVatValue() : 
								new BigDecimal(0);
		BigDecimal expenseValue = bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_COMPLETED ? bookingDate.getExpenseValue() : 
									new BigDecimal(0);
		BigDecimal nonPayValue = new BigDecimal(0);
		
        rc = insertBudgetTransaction(booking.getLocationId(), 
                                     booking.getJobProfileId(), 
                                     value, 
       		                         vatValue, 
       		                         expenseValue, 
       		                         nonPayValue,
                                     // needs to be multi-lingual
                                     "Cancelled shift - " + df.format(bookingDate.getBookingId()) + "." + df.format(bookingDate.getBookingDateId()), 
	                                 auditorId);
	    
        // TODO
        //
        // inform agencies
        //
	
        // booking changes ...
        //
        // value, filledValue, noOfBookingDates, noOfHours, minBookingDate, maxBookingDate
        // ... and potential status change
        //
        //
        // need to update booking start and end dates and the number of days
        // update values on booking - rrp, agreed and actual?? also number of hours
        //
        // mark booking as filled if no more shifts to fill
        // mark booking as complete if no more shifts to complete
        //
        // in ONE update !!! Get the booking and amend what is needed, then update it ... shouldn't take long
        
        // reduce the booking RRP, filled and worked values
        booking.setValue(booking.getValue().subtract(bookingDate.getValue()));

        // reduce the booking filled value IF date was filled
        if (bookingDate.getStatus().equals(BookingDate.BOOKINGDATE_STATUS_FILLED)) {
            booking.setFilledValue(booking.getFilledValue().subtract(agyValue));
        }

        // reduce the booking filled and worked values IF date was completed
        if (bookingDate.getStatus().equals(BookingDate.BOOKINGDATE_STATUS_COMPLETED)) {
            booking.setFilledValue(booking.getFilledValue().subtract(agyValue));
            booking.setWorkedValue(booking.getWorkedValue().subtract(bookingDate.getWorkedChargeRateValue()));
            booking.setWorkedNoOfHours(booking.getWorkedNoOfHours().subtract(bookingDate.getWorkedNoOfHours()));
        }
        
        // reduce number of dates by 1
        booking.setNoOfBookingDates(booking.getNoOfBookingDates() - 1);
	
        // noOfHours
        Shift shift = getShiftDAO().getShift(bookingDate.getShiftId());
        // reduce noOfHours by the shift noOfHours        
        booking.setNoOfHours(booking.getNoOfHours().subtract(shift.getNoOfHours()));

        

        // invoice stuff
        if (bookingDate.getWorkedStatus().equals(BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED)) {
        	// need to sort invoice and invoiceAgency records
        	
        	Manager manager = getManagerDAO().getManager(auditorId);
        	
        	if (bookingDate.getInvoiceId() > 0) {
        	
        		// manager invoice
        		
	        	Invoice invoice = invoiceDAO.getInvoice(manager.getClientId(), bookingDate.getInvoiceId());
	        	InvoiceAgency invoiceAgency = invoiceAgencyDAO.getInvoiceAgencyUserForInvoiceForAgency(bookingDate.getInvoiceId(), agencyId);
	        	
	        	invoice.setChargeRateValue(invoice.getChargeRateValue().subtract(bookingDate.getWorkedChargeRateValue()));
	        	invoice.setPayRateValue(invoice.getPayRateValue().subtract(bookingDate.getWorkedPayRateValue()));
	        	invoice.setWtdValue(invoice.getWtdValue().subtract(bookingDate.getWorkedWtdValue()));
	        	invoice.setNiValue(invoice.getNiValue().subtract(bookingDate.getWorkedNiValue()));
	        	invoice.setCommissionValue(invoice.getCommissionValue().subtract(bookingDate.getWorkedCommissionValue()));
	        	invoice.setExpenseValue(invoice.getExpenseValue().subtract(bookingDate.getExpenseValue()));
	        	invoice.setVatValue(invoice.getVatValue().subtract(bookingDate.getTotalVatValue()));
	        	invoice.setFeeValue(invoice.getFeeValue().subtract(bookingDate.getFeeValue()));
	        	invoice.setNoOfHours(invoice.getNoOfHours().subtract(bookingDate.getWorkedNoOfHours()));
	
	            invoiceDAO.updateInvoiceValues(invoice, auditorId);
	        	
	        	invoiceAgency.setChargeRateValue(invoiceAgency.getChargeRateValue().subtract(bookingDate.getWorkedChargeRateValue()));
	        	invoiceAgency.setPayRateValue(invoiceAgency.getPayRateValue().subtract(bookingDate.getWorkedPayRateValue()));
	        	invoiceAgency.setWtdValue(invoiceAgency.getWtdValue().subtract(bookingDate.getWorkedWtdValue()));
	        	invoiceAgency.setNiValue(invoiceAgency.getNiValue().subtract(bookingDate.getWorkedNiValue()));
	        	invoiceAgency.setCommissionValue(invoiceAgency.getCommissionValue().subtract(bookingDate.getWorkedCommissionValue()));
	        	invoiceAgency.setExpenseValue(invoiceAgency.getExpenseValue().subtract(bookingDate.getExpenseValue()));
	        	invoiceAgency.setVatValue(invoiceAgency.getVatValue().subtract(bookingDate.getTotalVatValue()));
	        	invoiceAgency.setFeeValue(invoiceAgency.getFeeValue().subtract(bookingDate.getFeeValue()));
	        	invoiceAgency.setNoOfHours(invoiceAgency.getNoOfHours().subtract(bookingDate.getWorkedNoOfHours()));
	        	
	            invoiceAgencyDAO.updateInvoiceAgencyValues(invoiceAgency, auditorId);
        	
        	}

        	if (bookingDate.getAgencyInvoiceId() > 0) {
            	
        		// agency invoice
        		
	        	AgencyInvoice agencyInvoice = agencyInvoiceDAO.getAgencyInvoiceUser(bookingDate.getAgencyInvoiceId());
	        	
	        	agencyInvoice.setChargeRateValue(agencyInvoice.getChargeRateValue().subtract(bookingDate.getWorkedChargeRateValue()));
	        	agencyInvoice.setPayRateValue(agencyInvoice.getPayRateValue().subtract(bookingDate.getWorkedPayRateValue()));
	        	agencyInvoice.setWtdValue(agencyInvoice.getWtdValue().subtract(bookingDate.getWorkedWtdValue()));
	        	agencyInvoice.setNiValue(agencyInvoice.getNiValue().subtract(bookingDate.getWorkedNiValue()));
	        	agencyInvoice.setCommissionValue(agencyInvoice.getCommissionValue().subtract(bookingDate.getWorkedCommissionValue()));
	        	agencyInvoice.setExpenseValue(agencyInvoice.getExpenseValue().subtract(bookingDate.getExpenseValue()));
	        	agencyInvoice.setVatValue(agencyInvoice.getVatValue().subtract(bookingDate.getTotalVatValue()));
	        	agencyInvoice.setFeeValue(agencyInvoice.getFeeValue().subtract(bookingDate.getFeeValue()));
	        	agencyInvoice.setNoOfHours(agencyInvoice.getNoOfHours().subtract(bookingDate.getWorkedNoOfHours()));
	        	
	            agencyInvoiceDAO.updateAgencyInvoiceValues(agencyInvoice, auditorId);
        	
        	}

        }
        
        
        
        
        
        //
        //
        //
        // TODO - check if shiftId needs to be set as there may now be only one shift on the booking !!!
        //
        //
        //
        
        // MUST before checking notFilled and notAuthorized checks.

        rc = getBookingDateDAO().updateBookingDateCancel(bookingDateId, cancelText, noOfChanges, auditorId);         	

        if (!status.equals(Booking.BOOKING_STATUS_CANCELLED)) {
        	
            IntValue countNotFilled = getBookingDAO().getBookingCountNotFilled(bookingDate.getBookingId());
		    if (countNotFilled.getValue() == 0) {
			    // No more to fill
		    	IntValue countNotAuthorized = getBookingDAO().getBookingCountNotAuthorized(bookingDate.getBookingId());
			    if (countNotAuthorized.getValue() == 0) {
		            // No more to authorize
			    	status = Booking.BOOKING_STATUS_COMPLETED;
			    }
			    else {
			    	// Mark as filled
			    	status = Booking.BOOKING_STATUS_CLOSED;
			    }
		    }
        }    

        booking.setStatus(status);
		 
	    rc = getBookingDAO().updateBooking(booking, auditorId);
    
		return rc;

	}

	public int updateBookingCancel(Integer bookingId, String cancelText, Integer noOfChanges, Integer auditorId) {

		// get the booking here so that we know the amount to create to the budget further down !!!
		
        Booking booking = getBookingDAO().getBooking(bookingId);
        
		// what about bookingDates and bookingGrades ???
		List<BookingDate> bookingDates = getBookingDateDAO().getBookingDatesForBooking(bookingId, true);
        for (BookingDate bookingDate: bookingDates) {
        	// only if not cancelled !!!
        	if (bookingDate.getStatus() != BookingDate.BOOKINGDATE_STATUS_CANCELLED) {
            	int bd = getBookingDateDAO().updateBookingDateCancel(bookingDate.getBookingDateId(), cancelText, bookingDate.getNoOfChanges(), auditorId);         	
        	}
        }
    	// Close all Open bookingGrades
		List<BookingGradeUser> bookingGradeUsers = getBookingGradeDAO().getBookingGradeUsersForBooking(bookingId, true);
        for (BookingGradeUser bookingGradeUser: bookingGradeUsers) {
            if (bookingGradeUser.getStatus() == BookingGrade.BOOKINGGRADE_STATUS_OPEN) {
            	int bg = getBookingGradeDAO().updateBookingGradeStatus(bookingGradeUser.getBookingGradeId(), bookingGradeUser.getNoOfChanges(), auditorId, BookingGrade.BOOKINGGRADE_STATUS_CLOSED);         	
            }
        }
		
        int rc = getBookingDAO().updateBookingCancel(bookingId, cancelText, noOfChanges, auditorId);
        
        // adjust budget - give the money back!
		DecimalFormat df = new DecimalFormat("#000");
        rc = insertBudgetTransaction(booking.getLocationId(), 
                                         booking.getJobProfileId(), 
                                         booking.getValue(), 
        		                         new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
                                         // needs to be multi-lingual
                                         "Cancelled booking - " + df.format(booking.getBookingId()), 
	                                     auditorId);
	    
        // TODO
        //
        // DON'T mark all shifts as cancelled
        //
        // mark all open grades (agy) as cancelled
        //
        // inform agencies
        //
        
        
		return rc;

	}
    
 	public BookingGradeApplicantUser getBookingGradeApplicantUserForBookingFilledSingleCandidate(Integer bookingId) {
 		
 		BookingGradeApplicantUser bookingGradeApplicantUser = null;
 		bookingGradeApplicantUser = bookingGradeApplicantDAO.getBookingGradeApplicantUserForBookingFilledSingleCandidate(bookingId);
		return bookingGradeApplicantUser;
 	}

    public int updateBookingClosed(Integer bookingId, Integer noOfChanges, Integer auditorId) {
    	
        int rc = getBookingDAO().updateBookingStatus(bookingId, noOfChanges, auditorId, Booking.BOOKING_STATUS_CLOSED);
		
		return rc;

    }

	public int updateBookingExtend(Booking booking, BookingDate[] bookingDates, BookingGradeApplicantUser bookingGradeApplicant, Integer auditorId) {
	    
		BigDecimal additionalValue = updateBookingExtend(booking, bookingDates, auditorId);
	    
	    // now sort out filling it ...

		BigDecimal additionalFilledValue = new BigDecimal(0);

    	String bookingDateIdStrs = "";
    	
		for (BookingDate bookingDate: bookingDates) {
			
			BookingDate bookingDateX = getBookingDateDAO().getBookingDate(bookingDate.getBookingDateId());

			BookingGradeApplicantDate bookingGradeApplicantDate = new BookingGradeApplicantDate();

			bookingGradeApplicantDate.setBookingGradeApplicantId(bookingGradeApplicant.getBookingGradeApplicantId());
			bookingGradeApplicantDate.setBookingDateId(bookingDate.getBookingDateId());

            BigDecimal chargeRateValue = com.helmet.application.Utilities.round(bookingDate.getValue().multiply(bookingGradeApplicant.getRate().divide(booking.getRate(), 5, RoundingMode.HALF_UP)));
            BigDecimal payRateValue = com.helmet.application.Utilities.round(bookingDate.getValue().multiply(bookingGradeApplicant.getPayRate().divide(booking.getRate(), 5, RoundingMode.HALF_UP)));
            BigDecimal wageRateValue = com.helmet.application.Utilities.round(bookingDate.getValue().multiply(bookingGradeApplicant.getWageRate().divide(booking.getRate(), 5, RoundingMode.HALF_UP)));

        	bookingGradeApplicantDate.setValue(chargeRateValue);
        	bookingGradeApplicantDate.setPayRateValue(payRateValue);
        	bookingGradeApplicantDate.setWageRateValue(wageRateValue);

            // insert a bookingGradeApplicantDate for each date
        	int bgad = getBookingGradeApplicantDateDAO().insertBookingGradeApplicantDate(bookingGradeApplicantDate, auditorId);

        	int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDate.getBookingGradeApplicantDateId(),
        			0, // noOfChanges - record has just been entered - so it will be 0
        			auditorId,
        			BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED);

            // fill the date    		
        	int bd = getBookingDateDAO().updateBookingDateFilled(bookingDateX.getBookingDateId(), 
                    bookingDateX.getNoOfChanges(), 
                    auditorId, 
                    bookingGradeApplicantDate.getBookingGradeApplicantDateId());

            // sum up the 'agency' value of the dates that we are filling
            additionalFilledValue = additionalFilledValue.add(chargeRateValue);
        	
    		if (!"".equals(bookingDateIdStrs)) {
    			bookingDateIdStrs = bookingDateIdStrs + ",";
    		}
			bookingDateIdStrs = bookingDateIdStrs + bookingDate.getBookingDateId();
		}

		int rc = getBookingDAO().updateBookingFilledValue(booking.getBookingId(), booking.getNoOfChanges() + 1, auditorId, additionalFilledValue);
		
        // TODO - tidy up !!!
		DecimalFormat df = new DecimalFormat("#000");
        int bt = insertBudgetTransaction(booking.getLocationId(), 
                booking.getJobProfileId(), 
                additionalValue.subtract(additionalFilledValue), 
                new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
                // needs to be multi-lingual
                "Extended booking - filled - " + df.format(booking.getBookingId()), 
	             auditorId);
		
        if (booking.getAutoActivate()) {
	        // if auto activate ....
	     	int bda = updateBookingDatesActivated(bookingDateIdStrs, auditorId);
        }
        
	    return rc;
	}
	
	private BigDecimal updateBookingExtend(Booking booking, BookingDate[] bookingDates, Integer auditorId) {

		// loop through the dates to calculate the value to be added to the booking 

        Integer bookingShiftId = booking.getShiftId();
        BigDecimal bookingNoOfHours = booking.getNoOfHours();
        BigDecimal bookingValue = booking.getValue();
        Integer bookingNoOfBookingDates = booking.getNoOfBookingDates() + bookingDates.length;
        Date bookingMinBookingDate = booking.getMinBookingDate();
        Date bookingMaxBookingDate = booking.getMaxBookingDate();
        
        if (bookingDates[0].getBookingDate().before(bookingMinBookingDate)) {
        	bookingMinBookingDate = bookingDates[0].getBookingDate();
        }
        if (bookingDates[bookingDates.length - 1].getBookingDate().after(bookingMaxBookingDate)) {
        	bookingMaxBookingDate = bookingDates[bookingDates.length - 1].getBookingDate();
        }
        
		List<BookingGradeUser> bookingGrades = getBookingGradeDAO().getBookingGradeUsersForBooking(booking.getBookingId(), true);		
		
        BigDecimal additionalValue = new BigDecimal(0);

		for (BookingDate bookingDate: bookingDates) {

            if (bookingShiftId != null && !bookingShiftId.equals(bookingDate.getShiftId())) {
            	// booking shiftId was same for all dates - now we have a different one so set the booking shiftId to null
            	bookingShiftId = null;
            }
            // add in the noOfHours
            bookingNoOfHours = bookingNoOfHours.add(bookingDate.getShiftNoOfHours());
            //        
     		
            additionalValue = additionalValue.add(bookingDate.getValue());

            // sort out agency records 
            for (BookingGrade bookingGrade: bookingGrades) {
                BigDecimal chargeRateValue = com.helmet.application.Utilities.round(bookingDate.getValue().multiply(bookingGrade.getRate().divide(booking.getRate(), 5, RoundingMode.HALF_UP)));
                bookingGrade.setValue(bookingGrade.getValue().add(chargeRateValue));            	
            }
            
     	}
        // add in the values
        bookingValue = bookingValue.add(additionalValue);
     	
        // update the booking object
        booking.setShiftId(bookingShiftId);
        booking.setNoOfHours(bookingNoOfHours);
        booking.setNoOfBookingDates(bookingNoOfBookingDates);
        booking.setMinBookingDate(bookingMinBookingDate);
        booking.setMaxBookingDate(bookingMaxBookingDate);
        booking.setValue(bookingValue);
        
		int b = getBookingDAO().updateBookingExtend(booking, auditorId);
		int d = getBookingDateDAO().insertBookingDates(booking.getBookingId(), bookingDates, auditorId);
        for (BookingGrade bookingGrade: bookingGrades) {
        	int g = getBookingGradeDAO().updateBookingGrade(bookingGrade, auditorId);
        }
		
        // TODO - tidy up !!!
		DecimalFormat df = new DecimalFormat("#000");
        int bt = insertBudgetTransaction(booking.getLocationId(), 
                booking.getJobProfileId(), 
                additionalValue.multiply(new BigDecimal(-1)), 
                new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
                // needs to be multi-lingual
                "Extended booking - " + df.format(booking.getBookingId()), 
	             auditorId);
		
		return additionalValue;

	}

  public Country getCountry(Integer countryId) 
  {
    Country country = null;
    country = countryDAO.getCountry(countryId);
    return country;

  }

  public Integer getNhsBackingReportPagingLimit()
  {
    Integer nhsBackingReportPagingLimit = nhsBackingReportDAO.getPagingLimit();
    return nhsBackingReportPagingLimit;
  }

  public Integer getNhsBackingReportPagingGroupSize()
  {
    Integer nhsBackingReportPagingGroupSize = nhsBackingReportDAO.getPagingGroupSize();
    return nhsBackingReportPagingGroupSize;
  }

  public NhsBackingReport getNhsBackingReport(Integer nhsBackingReportId)
  {
    NhsBackingReport nhsBackingReport = nhsBackingReportDAO.getNhsBackingReport(nhsBackingReportId);
    return nhsBackingReport;
  }

  public RecordCount getNhsBackingReportsCount(Integer agencyId, boolean showOnlyActive, String filter) 
  {
    RecordCount nhsBackingReportsCount = null;
    nhsBackingReportsCount = getNhsBackingReportDAO().getNhsBackingReportsCount(agencyId, showOnlyActive, filter);
    return nhsBackingReportsCount;
  }

  public List<NhsBackingReport> getNhsBackingReports(Integer agencyId, boolean showOnlyActive) 
  {
    List<NhsBackingReport> nhsBackingReports = null;
    nhsBackingReports = getNhsBackingReportDAO().getNhsBackingReports(agencyId, showOnlyActive);
    return nhsBackingReports;
  }

  public List<NhsBackingReportUser> getNhsBackingReportUsers(Integer agencyId, boolean showOnlyActive) 
  {
    List<NhsBackingReportUser> nhsBackingReportUsers = null;
    nhsBackingReportUsers = getNhsBackingReportDAO().getNhsBackingReportUsers(agencyId, showOnlyActive);
    return nhsBackingReportUsers;
  }

  public List<NhsBackingReportUser> getNhsBackingReportUsersInList(Integer agencyId, String nhsBackingReportIdList) 
  {
    List<NhsBackingReportUser> nhsBackingReportUsers = null;
    nhsBackingReportUsers = getNhsBackingReportDAO().getNhsBackingReportUsersInList(agencyId, nhsBackingReportIdList);
    return nhsBackingReportUsers;
  }

  public List<NhsBackingReportUser> getNhsBackingReportUsersOffset(Integer agencyId, boolean showOnlyActive, Integer offset, String filter) 
  {
    List<NhsBackingReportUser> nhsBackingReportUsers = null;
    nhsBackingReportUsers = getNhsBackingReportDAO().getNhsBackingReportUsersOffset(agencyId, showOnlyActive, offset, filter);
    return nhsBackingReportUsers;
  }

  public NhsBackingReportUser getNhsBackingReportUser(Integer nhsBackingReportId)
  {
    NhsBackingReportUser nhsBackingReportUser = nhsBackingReportDAO.getNhsBackingReportUser(nhsBackingReportId);
    return nhsBackingReportUser;
  }

  public NhsBackingReport getNhsBackingReportForName(String backingReportName, boolean showOnlyActive)
  {
    NhsBackingReport nhsBackingReport = nhsBackingReportDAO.getNhsBackingReportForName(backingReportName, showOnlyActive);
    return nhsBackingReport;
  }

  public NhsBackingReport getInactiveNhsBackingReportForName(String backingReportName)
  {
    NhsBackingReport nhsBackingReport = nhsBackingReportDAO.getInactiveNhsBackingReportForName(backingReportName);
    return nhsBackingReport;
  }

  public List<NhsBackingReport> getNhsBackingReportsForAgencyDateRange(Integer agencyId, Date startOfWeekDate, Date endOfWeekDate)
  {
    List<NhsBackingReport> listNhsBackingReport = nhsBackingReportDAO.getNhsBackingReportsForAgencyDateRange(agencyId, startOfWeekDate, endOfWeekDate);
    return listNhsBackingReport;
  }
  
  public List<NhsBackingReportUser> getNhsBackingReportUsersForAgencyDateRange(Integer agencyId, Date startOfWeekDate, Date endOfWeekDate)
  {
    List<NhsBackingReportUser> listNhsBackingReportUser = nhsBackingReportDAO.getNhsBackingReportUsersForAgencyDateRange(agencyId, startOfWeekDate, endOfWeekDate);
    return listNhsBackingReportUser;
  }

  public int deleteNhsBackingReport(Integer nhsBackingReportId, Integer noOfChanges, Integer auditorId) 
  {
    int rc = getNhsBackingReportDAO().deleteNhsBackingReport(nhsBackingReportId, noOfChanges, auditorId);
    return rc;
  }

  public int insertNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId) {

    NhsBackingReport duplicateNhsBackingReport = getNhsBackingReportDAO().getNhsBackingReportForName(nhsBackingReport.getName(), true);
    if (duplicateNhsBackingReport != null) {
      throw new DuplicateDataException("name");
    }
    int rc = getNhsBackingReportDAO().insertNhsBackingReport(nhsBackingReport, auditorId);
    return rc;
  }

  public int updateNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId) 
  {
    NhsBackingReport duplicateNhsBackingReport = getNhsBackingReportDAO().getNhsBackingReportForName(nhsBackingReport.getName(), true);
    if (duplicateNhsBackingReport != null && 
      !duplicateNhsBackingReport.getNhsBackingReportId().equals(nhsBackingReport.getNhsBackingReportId())) {
      throw new DuplicateDataException("name");
    }
    int rc = getNhsBackingReportDAO().updateNhsBackingReport(nhsBackingReport, auditorId);
    return rc;
  }
  
  public int reactivateNhsBackingReport(NhsBackingReport nhsBackingReport, Integer auditorId) 
  {
    NhsBackingReport duplicateNhsBackingReport = getNhsBackingReportDAO().getNhsBackingReportForName(nhsBackingReport.getName(), true);
    if (duplicateNhsBackingReport != null && 
      !duplicateNhsBackingReport.getNhsBackingReportId().equals(nhsBackingReport.getNhsBackingReportId())) {
      throw new DuplicateDataException("name");
    }
    int rc = getNhsBackingReportDAO().reactivateNhsBackingReport(nhsBackingReport, auditorId);
    return rc;
  }
  
  public int updateNhsBackingReportTradeshiftDocumentId(NhsBackingReport nhsBackingReport, Integer auditorId)
  {
    int rc = getNhsBackingReportDAO().updateNhsBackingReportTradeshiftDocumentId(nhsBackingReport, auditorId);
    return rc;
  }

  public int updateNhsBackingReportSubcontract(NhsBackingReport nhsBackingReport, Integer auditorId)
  {
    int rc = getNhsBackingReportDAO().updateNhsBackingReportSubcontract(nhsBackingReport, auditorId);
    return rc;
  }

  public int updateNhsBackingReportSubcontractDocumentationSentDate(NhsBackingReport nhsBackingReport, Integer auditorId)
  {
    int rc = getNhsBackingReportDAO().updateNhsBackingReportSubcontractDocumentationSentDate(nhsBackingReport, auditorId);
    return rc;
  }

  public List<BookingDate> getBookingDatesForNhsBackingReport(String nhsBackingReport)
  {
    List<BookingDate> bookingDates = bookingDateDAO.getBookingDatesForNhsBackingReport(nhsBackingReport);
    return bookingDates;
  }
}
