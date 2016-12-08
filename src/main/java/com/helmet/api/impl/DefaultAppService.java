package com.helmet.api.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.helmet.api.AppService;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.api.exceptions.InvalidDetailException;
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
import com.helmet.bean.BookingGradeApplicantDateUserEntity;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.BookingUser;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgency;
import com.helmet.bean.ClientUser;
import com.helmet.bean.IntValue;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.Uplift;
import com.helmet.bean.UpliftMinute;
import com.helmet.persistence.AgencyDAO;
import com.helmet.persistence.ApplicantDAO;
import com.helmet.persistence.BookingDAO;
import com.helmet.persistence.BookingDateDAO;
import com.helmet.persistence.BookingDateExpenseDAO;
import com.helmet.persistence.BookingDateHourDAO;
import com.helmet.persistence.BookingExpenseDAO;
import com.helmet.persistence.BookingGradeApplicantDAO;
import com.helmet.persistence.BookingGradeApplicantDateDAO;
import com.helmet.persistence.BookingGradeDAO;
import com.helmet.persistence.ClientAgencyDAO;
import com.helmet.persistence.ClientDAO;
import com.helmet.persistence.JobProfileDAO;
import com.helmet.persistence.NhsBookingDAO;
import com.helmet.persistence.PublicHolidayDAO;
import com.helmet.persistence.UpliftDAO;
import com.helmet.persistence.UpliftMinuteDAO;

public class DefaultAppService implements AppService {

	private BookingGradeApplicantDAO bookingGradeApplicantDAO;

	private BookingGradeApplicantDateDAO bookingGradeApplicantDateDAO;

	private ApplicantDAO applicantDAO;

	private AgencyDAO agencyDAO;

	private ClientDAO clientDAO;

	private ClientAgencyDAO clientAgencyDAO;

	private BookingExpenseDAO bookingExpenseDAO;

	private BookingGradeDAO bookingGradeDAO;

	private BookingDAO bookingDAO;

  private UpliftDAO upliftDAO;
  
  private UpliftMinuteDAO upliftMinuteDAO;
  
	private PublicHolidayDAO publicHolidayDAO;
	
	private BookingDateDAO bookingDateDAO;

	private BookingDateHourDAO bookingDateHourDAO;

	private BookingDateExpenseDAO bookingDateExpenseDAO;

	private JobProfileDAO jobProfileDAO;
  
  private NhsBookingDAO nhsBookingDAO;

	public void setBookingGradeApplicantDAO(BookingGradeApplicantDAO bookingGradeApplicantDAO) {
		this.bookingGradeApplicantDAO = bookingGradeApplicantDAO;
	}

	public void setBookingGradeApplicantDateDAO(BookingGradeApplicantDateDAO bookingGradeApplicantDateDAO) {
		this.bookingGradeApplicantDateDAO = bookingGradeApplicantDateDAO;
	}

	public void setApplicantDAO(ApplicantDAO applicantDAO) {
		this.applicantDAO = applicantDAO;
	}

	public void setAgencyDAO(AgencyDAO agencyDAO) {
		this.agencyDAO = agencyDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public void setClientAgencyDAO(ClientAgencyDAO clientAgencyDAO) {
		this.clientAgencyDAO = clientAgencyDAO;
	}

	public void setBookingExpenseDAO(BookingExpenseDAO bookingExpenseDAO) {
		this.bookingExpenseDAO = bookingExpenseDAO;
	}

	public void setBookingGradeDAO(BookingGradeDAO bookingGradeDAO) {
		this.bookingGradeDAO = bookingGradeDAO;
	}

	public void setBookingDateDAO(BookingDateDAO bookingDateDAO) {
		this.bookingDateDAO = bookingDateDAO;
	}

	public void setBookingDAO(BookingDAO bookingDAO) {
		this.bookingDAO = bookingDAO;
	}

	public void setPublicHolidayDAO(PublicHolidayDAO publicHolidayDAO) {
		this.publicHolidayDAO = publicHolidayDAO;
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
  
	public void setBookingDateHourDAO(BookingDateHourDAO bookingDateHourDAO) {
		this.bookingDateHourDAO = bookingDateHourDAO;
	}

	public void setBookingDateExpenseDAO(BookingDateExpenseDAO bookingDateExpenseDAO) {
		this.bookingDateExpenseDAO = bookingDateExpenseDAO;
	}

	public void setJobProfileDAO(JobProfileDAO jobProfileDAO) {
		this.jobProfileDAO = jobProfileDAO;
	}

  public void setNhsBookingDAO(NhsBookingDAO nhsBookingDAO)
  {
    this.nhsBookingDAO = nhsBookingDAO;
  }
  
	public BookingGradeApplicant validateLogin(BookingGradeApplicant bookingGradeApplicant, String login, String pwd) {

		BookingGradeApplicant bookingGradeApplicantX = bookingGradeApplicantDAO.getBookingGradeApplicantUser(bookingGradeApplicant.getBookingGradeApplicantId());
		if (bookingGradeApplicantX == null) {
			throw new DataNotFoundException();
		}
		if (!bookingGradeApplicantX.getLogin().equals(login)) {
			throw new DataNotFoundException();
		}
		if (!bookingGradeApplicantX.getPwd().equals(pwd)) {
			throw new InvalidDetailException();
		}
		
		return bookingGradeApplicantX;

	}

	public Applicant validateSecretWord(Applicant applicant) {

		Applicant applicantX = applicantDAO.getApplicant(applicant.getApplicantId());
		if (applicantX == null) {
			throw new DataNotFoundException();
		}
		if (!applicant.getUser().getSecretWord().equalsIgnoreCase(applicantX.getUser().getSecretWord())) {
			throw new InvalidDetailException();
		}
		return applicantX;
	
	}
	
	public Applicant getApplicant(Integer applicantId) {
		
		Applicant applicant = null;
    	applicant = applicantDAO.getApplicant(applicantId);
		return applicant;
		
	}

	public int updateApplicant(Applicant applicant, Integer auditorId) {

		Applicant duplicateApplicant = applicantDAO.getApplicantForLogin(applicant.getAgencyId(), applicant.getUser().getLogin());
		if (duplicateApplicant != null &&
			!duplicateApplicant.getApplicantId().equals(applicant.getApplicantId())) {
			throw new DuplicateDataException("login");
		}
        int rc = applicantDAO.updateApplicant(applicant, auditorId);
		return rc;

	}

  	public int updateApplicantSecretWord(Integer applicantId, String newSecretWord, Integer noOfChanges, Integer auditorId) {

		int rc = applicantDAO.updateApplicantSecretWord(applicantId, newSecretWord, noOfChanges, auditorId);
		return rc;
	
	}
	
  	public Client getClientForBookingGradeApplicant(Integer bookingGradeApplicantId) {
  		
		Client client = null;
    	client = clientDAO.getClientForBookingGradeApplicant(bookingGradeApplicantId);
		return client;

  	}	
  	
  	public Agency getAgency(Integer agencyId) {
		
		Agency agency = null;
    	agency = agencyDAO.getAgency(agencyId);
		return agency;
		
  	}
	
	public Booking getBooking(Integer bookingId) {
		
		Booking booking = null;
		booking = bookingDAO.getBooking(bookingId);
		return booking;
		
	}

	public ClientUser getClientUser(Integer clientId) {
		
		ClientUser client = null;
		client = clientDAO.getClientUser(clientId);
		return client;

	}
	
  	public AgencyUser getAgencyUser(Integer agencyId) {
  		
		AgencyUser agency = null;
    	agency = agencyDAO.getAgencyUser(agencyId);
		return agency;

  	}
	
  	public JobProfileUser getJobProfileUser(Integer jobProfileId) {
  		
		JobProfileUser jobProfile = null;
		jobProfile = jobProfileDAO.getJobProfileUser(jobProfileId);
		return jobProfile;

  	}
	
  	public BookingUser getBookingUser(Integer bookingId) {
  		
  		BookingUser booking = null;
		booking = bookingDAO.getBookingUser(bookingId);
		return booking;

  	}
	
	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntity(Integer bookingGradeApplicantId, Boolean showOnlyOutstanding) {

		BookingGradeApplicantUserEntity bookingGradeApplicantUser = null;
		bookingGradeApplicantUser = bookingGradeApplicantDAO.getBookingGradeApplicantUserEntity(bookingGradeApplicantId);

		if (showOnlyOutstanding) {
			bookingGradeApplicantUser.setBookingGradeApplicantDateUserEntities(bookingGradeApplicantDateDAO.getOutstandingBookingGradeApplicantDateUserEntitiesForBookingGradeApplicantForApplicant(bookingGradeApplicantId));
		}
		else {
			bookingGradeApplicantUser.setBookingGradeApplicantDateUserEntities(bookingGradeApplicantDateDAO.getBookingGradeApplicantDateUserEntitiesForBookingGradeApplicantForApplicant(bookingGradeApplicantId));
		}

		IntValue expensesCount = bookingGradeApplicantDAO.getBookingExpensesCount(bookingGradeApplicantUser.getBookingId());
		
		for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity: bookingGradeApplicantUser.getBookingGradeApplicantDateUserEntities()) {
			if (expensesCount.getValue() > 0) {
				// TODO Add any claimed expenses for each date - should really all be got together and then merged in
				bookingGradeApplicantDateUserEntity.setBookingDateExpenseUsers(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingGradeApplicantDateUserEntity.getBookingDateId()));
			}
			if (bookingGradeApplicantDateUserEntity.getWorkedNoOfHours() != null) {
				if (bookingGradeApplicantDateUserEntity.getWorkedNoOfHours().compareTo(new BigDecimal(0)) > 0) {
					// only get workedNoOfHours > 0
					bookingGradeApplicantDateUserEntity.setBookingDateHours(bookingDateHourDAO.getBookingDateHoursForBookingDate(bookingGradeApplicantDateUserEntity.getBookingDateId(), true));
				}
			}
		}
		
		bookingGradeApplicantUser.setBookingExpensesCount(expensesCount);

		return bookingGradeApplicantUser;

	}

	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntityForDateRange(Integer bookingGradeApplicantId, Date startDate, Date endDate) {

		BookingGradeApplicantUserEntity bookingGradeApplicantUser = null;
		bookingGradeApplicantUser = bookingGradeApplicantDAO.getBookingGradeApplicantUserEntity(bookingGradeApplicantId);

		bookingGradeApplicantUser.setBookingGradeApplicantDateUserEntities(bookingGradeApplicantDateDAO.getBookingGradeApplicantDateUserEntitiesForBookingGradeApplicantForDateRange(bookingGradeApplicantId, startDate, endDate));
		
		IntValue expensesCount = bookingGradeApplicantDAO.getBookingExpensesCount(bookingGradeApplicantUser.getBookingId());
		
		for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity: bookingGradeApplicantUser.getBookingGradeApplicantDateUserEntities()) {
			if (expensesCount.getValue() > 0) {
				// TODO Add any claimed expenses for each date - should really all be got together and then merged in
				bookingGradeApplicantDateUserEntity.setBookingDateExpenseUsers(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingGradeApplicantDateUserEntity.getBookingDateId()));
			}
			if (bookingGradeApplicantDateUserEntity.getWorkedNoOfHours() != null) {
				if (bookingGradeApplicantDateUserEntity.getWorkedNoOfHours().compareTo(new BigDecimal(0)) > 0) {
					// only get workedNoOfHours > 0
					bookingGradeApplicantDateUserEntity.setBookingDateHours(bookingDateHourDAO.getBookingDateHoursForBookingDate(bookingGradeApplicantDateUserEntity.getBookingDateId(), true));
				}
			}
		}
		
		bookingGradeApplicantUser.setBookingExpensesCount(expensesCount);

		return bookingGradeApplicantUser;
		
	}
	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntityAndBookingDates(Integer bookingGradeApplicantId, String bookingDateIdStrs) {

		BookingGradeApplicantUserEntity bookingGradeApplicantUser = null;
		bookingGradeApplicantUser = bookingGradeApplicantDAO.getBookingGradeApplicantUserEntity(bookingGradeApplicantId);

		bookingGradeApplicantUser.setBookingGradeApplicantDateUserEntities(bookingGradeApplicantDateDAO.getBookingGradeApplicantDateUserEntitiesForBookingGradeApplicantForApplicantAndBookingDates(bookingGradeApplicantId, bookingDateIdStrs));
		
		IntValue expensesCount = bookingGradeApplicantDAO.getBookingExpensesCount(bookingGradeApplicantUser.getBookingId());
		
		for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity: bookingGradeApplicantUser.getBookingGradeApplicantDateUserEntities()) {
			if (expensesCount.getValue() > 0) {
				// TODO Add any claimed expenses for each date - should really all be got together and then merged in
				bookingGradeApplicantDateUserEntity.setBookingDateExpenseUsers(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingGradeApplicantDateUserEntity.getBookingDateId()));
			}
			if (bookingGradeApplicantDateUserEntity.getWorkedNoOfHours() != null) {
				if (bookingGradeApplicantDateUserEntity.getWorkedNoOfHours().compareTo(new BigDecimal(0)) > 0) {
					// only get workedNoOfHours > 0
					bookingGradeApplicantDateUserEntity.setBookingDateHours(bookingDateHourDAO.getBookingDateHoursForBookingDate(bookingGradeApplicantDateUserEntity.getBookingDateId(), true));
				}
			}
		}
		
		bookingGradeApplicantUser.setBookingExpensesCount(expensesCount);

		return bookingGradeApplicantUser;

	}

	
	
	public BookingGradeApplicantDateUser getBookingGradeApplicantDateUser(Integer bookingGradeApplicantDateId) {
		
		BookingGradeApplicantDateUser bookingGradeApplicantDate = null;
		bookingGradeApplicantDate = bookingGradeApplicantDateDAO.getBookingGradeApplicantDateUser(bookingGradeApplicantDateId);
		return bookingGradeApplicantDate;
		
	}

	public BookingDateUser getBookingDateUser(Integer bookingDateId) {
		
		BookingDateUser bookingDate = null;
		bookingDate = bookingDateDAO.getBookingDateUser(bookingDateId);
		return bookingDate;
		
	}

// 	public int updateBookingGradeApplicantDateWorked(BookingGradeApplicantDate bookingGradeApplicantDate, Integer auditorId) {
//
//        int rc = bookingGradeApplicantDateDAO.updateBookingGradeApplicantDateWorked(bookingGradeApplicantDate, auditorId);
//		return rc;
//
//	}

 	public int updateBookingDateWorked(BookingDate bookingDate, List<BookingDateHour> bookingDateHours, Integer auditorId) {

        int rc = bookingDateDAO.updateBookingDateWorked(bookingDate, auditorId);
        
        int d1 = bookingDateHourDAO.deleteBookingDateHoursForBookingDate(bookingDate.getBookingDateId(), auditorId);
        int d2 = bookingDateHourDAO.insertBookingDateHours(bookingDateHours, auditorId);
        
		return rc;

	}

 	public int updateBookingDateSubmitted(BookingDate bookingDate, Integer auditorId) {

        int rc = bookingDateDAO.updateBookingDateSubmitted(bookingDate, auditorId);
		return rc;

	}

 	public int updateBookingDatesSubmitted(String bookingDateIdStrs, Integer auditorId) {

        int rc = 0;
        
 		List<BookingDate> bookingDates = bookingDateDAO.getBookingDatesForBookingDates(bookingDateIdStrs);
    	
    	for (BookingDate bookingDate: bookingDates) {
    		rc =+ bookingDateDAO.updateBookingDateSubmitted(bookingDate, auditorId);
    	}
    	
    	return rc;
        
 	}
 
 	public int updateBookingDateWithdrawn(BookingDate bookingDate, Integer auditorId) {

        int rc = bookingDateDAO.updateBookingDateWithdrawn(bookingDate, auditorId);
		return rc;

	}
 	
//	public int updateBookingGradeApplicantDateSubmitted(BookingGradeApplicantDate bookingGradeApplicantDate, Integer auditorId) {
//
//        int rc = bookingGradeApplicantDateDAO.updateBookingGradeApplicantDateWorkedStatus(bookingGradeApplicantDate.getBookingGradeApplicantDateId(), 
//        		bookingGradeApplicantDate.getNoOfChanges(),
//        		auditorId,
//        		BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_WORKEDSTATUS_SUBMITTED);
//        
//		return rc;
//
//	}

	public List<BookingExpense> getBookingExpensesForBooking(Integer bookingId) {

		List<BookingExpense> expenses = bookingExpenseDAO.getBookingExpensesForBooking(bookingId, true);
		return expenses;
	}

	public List<BookingExpense> getBookingExpensesForBookingNotForBookingDate(Integer bookingId, Integer bookingDateId) {
	
		List<BookingExpense> expenses = bookingExpenseDAO.getBookingExpensesForBookingNotForBookingDate(bookingId, bookingDateId);
		return expenses;
	}
	
	public List<PublicHoliday> getPublicHolidaysForClient(Integer clientId) {

		List<PublicHoliday> publicHolidays = null;
		publicHolidays = publicHolidayDAO.getPublicHolidaysForClient(clientId, true);
		return publicHolidays;
	}

	public List<Uplift> getUpliftsForClient(Integer clientId) {

		List<Uplift> uplifts = null;
		uplifts = upliftDAO.getUpliftsForClient(clientId, true);
    // Load any UpliftMinutes into their respective Uplifts...
    loadUpliftMinutesIntoUplifts(clientId, uplifts);
		return uplifts;

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

 	public BookingDateUserApplicant getBookingDateUserApplicantForApplicantForBookingDate(Integer bookingDateId) {
 		
		BookingDateUserApplicant bookingDateApplicant = null;
		bookingDateApplicant = bookingDateDAO.getBookingDateUserApplicantForApplicantAndBookingDate(bookingDateId);
		return bookingDateApplicant;

 	}
 	
	public int insertBookingDateExpense(BookingDateExpense bookingDateExpense, Integer auditorId) {

		int rc = bookingDateExpenseDAO.insertBookingDateExpense(bookingDateExpense, auditorId);
		// get the bookingDate record (noOfChanges)
		BookingDate bookingDate = bookingDateDAO.getBookingDate(bookingDateExpense.getBookingDateId());
		// update the bookingDate record
		rc = bookingDateDAO.updateBookingDateExpenses(bookingDate.getBookingDateId(), bookingDate.getNoOfChanges(), 
				                                      bookingDateExpense.getValue(), bookingDateExpense.getVatValue(), 
				                                      auditorId);
		//
		return rc;

	}
 	
	public int updateBookingDateExpense(BookingDateExpense bookingDateExpense, Integer auditorId) {

 		// get the OLD bookingDateExpense so the bookingDate values can be updated
		BookingDateExpense bookingDateExpenseOld = bookingDateExpenseDAO.getBookingDateExpense(bookingDateExpense.getBookingDateExpenseId());
		// get the bookingDate record (noOfChanges)
		BookingDate bookingDate = bookingDateDAO.getBookingDate(bookingDateExpense.getBookingDateId());
		// update the bookingDate record
		int rc = bookingDateDAO.updateBookingDateExpenses(bookingDate.getBookingDateId(), bookingDate.getNoOfChanges(), 
			     	                                      bookingDateExpense.getValue().subtract(bookingDateExpenseOld.getValue()), 
			     	                                      bookingDateExpense.getVatValue().subtract(bookingDateExpenseOld.getVatValue()), 
				                                          auditorId);
		// do the update
		rc = bookingDateExpenseDAO.updateBookingDateExpense(bookingDateExpense, auditorId);

		return rc;
		
	}

 	public int deleteBookingDateExpense(Integer bookingDateExpenseId, Integer noOfChanges, Integer auditorId) {
 		
 		// get the OLD bookingDateExpense so the bookingDate values can be updated
		BookingDateExpense bookingDateExpense = bookingDateExpenseDAO.getBookingDateExpense(bookingDateExpenseId);
		// get the bookingDate record (noOfChanges)
		BookingDate bookingDate = bookingDateDAO.getBookingDate(bookingDateExpense.getBookingDateId());
		// update the bookingDate record
		BigDecimal minusOne = new BigDecimal(-1);
		int rc = bookingDateDAO.updateBookingDateExpenses(bookingDate.getBookingDateId(), bookingDate.getNoOfChanges(), 
			     	                                      bookingDateExpense.getValue().multiply(minusOne), 
			     	                                      bookingDateExpense.getVatValue().multiply(minusOne), 
				                                          auditorId);
		// do the delete
		rc = bookingDateExpenseDAO.deleteBookingDateExpense(bookingDateExpenseId, noOfChanges, auditorId);

		return rc;
 		
 	}
	
	public BookingDateExpense getBookingDateExpense(Integer bookingDateExpenseId) {

		BookingDateExpense bookingDateExpense = null;
		bookingDateExpense = bookingDateExpenseDAO.getBookingDateExpense(bookingDateExpenseId);
		return bookingDateExpense;

	}
	
	public BookingExpense getBookingExpense(Integer bookingExpenseId) {

		BookingExpense bookingExpense = null;
		bookingExpense = bookingExpenseDAO.getBookingExpense(bookingExpenseId);
		return bookingExpense;

	}
	
	public ClientAgency getClientAgencyForClientAndAgency(Integer clientId, Integer agencyId, Date bookingDate) {

		ClientAgency clientAgency = null;
		// TODO - bookingDate NOT currently used !!!
		clientAgency = clientAgencyDAO.getClientAgencyForClientAndAgency(clientId, agencyId);
		return clientAgency;
		
	}
	
  public NhsBooking getActiveNhsBookingForBankReqNum(Integer agencyId, String bankReqNum)
  {
    NhsBooking nhsBooking = nhsBookingDAO.getActiveNhsBookingForBankReqNum(agencyId, bankReqNum);
    return nhsBooking;
  }
  
  public NhsBooking getNhsBookingForBankReqNum(Integer agencyId, String bankReqNum)
  {
    NhsBooking nhsBooking = nhsBookingDAO.getNhsBookingForBankReqNum(agencyId, bankReqNum);
    return nhsBooking;
  }
  
}