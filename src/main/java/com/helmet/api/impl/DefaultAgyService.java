package com.helmet.api.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.helmet.api.AgyService;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.application.MailHandler;
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
import com.helmet.bean.Booking;
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
import com.helmet.bean.BookingGradeApplicantDateUser;
import com.helmet.bean.BookingGradeApplicantDateUserEntity;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.BookingGradeUser;
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
import com.helmet.bean.Unavailable;
import com.helmet.persistence.AgyAccessDAO;
import com.helmet.persistence.ApplicantDAO;
import com.helmet.persistence.BookingDateExpenseDAO;
import com.helmet.persistence.BookingDateHourDAO;
import com.helmet.persistence.ConsultantDAO;
import com.helmet.persistence.SubcontractInvoiceDAO;
import com.helmet.persistence.SubcontractInvoiceItemDAO;
import com.helmet.persistence.UnavailableDAO;
import com.helmet.persistence.Utilities;

public class DefaultAgyService extends DefaultCommonService implements AgyService {

	private ConsultantDAO consultantDAO;

	private AgyAccessDAO agyAccessDAO;

	private ApplicantDAO applicantDAO;

	private BookingDateExpenseDAO bookingDateExpenseDAO;

	private BookingDateHourDAO bookingDateHourDAO;

  private UnavailableDAO unavailableDAO;
    
  private SubcontractInvoiceDAO subcontractInvoiceDAO;
  
  private SubcontractInvoiceItemDAO subcontractInvoiceItemDAO;
  
	public void setConsultantDAO(ConsultantDAO consultantDAO) {
		this.consultantDAO = consultantDAO;
	}

	public void setAgyAccessDAO(AgyAccessDAO agyAccessDAO) {
		this.agyAccessDAO = agyAccessDAO;
	}

	public void setApplicantDAO(ApplicantDAO applicantDAO) {
		this.applicantDAO = applicantDAO;
	}

	public void setBookingDateExpenseDAO(BookingDateExpenseDAO bookingDateExpenseDAO) {
		this.bookingDateExpenseDAO = bookingDateExpenseDAO;
	}

	public void setBookingDateHourDAO(BookingDateHourDAO bookingDateHourDAO) {
		this.bookingDateHourDAO = bookingDateHourDAO;
	}

  public void setUnavailableDAO(UnavailableDAO unavailableDAO) 
  {
    this.unavailableDAO = unavailableDAO;
  }

  public void setSubcontractInvoiceDAO(SubcontractInvoiceDAO subcontractInvoiceDAO)
  {
    this.subcontractInvoiceDAO = subcontractInvoiceDAO;
  }

  public void setSubcontractInvoiceItemDAO(SubcontractInvoiceItemDAO subcontractInvoiceItemDAO)
  {
    this.subcontractInvoiceItemDAO = subcontractInvoiceItemDAO;
  }

  public Consultant validateLogin(Consultant consultant) {

		Consultant consultantX = consultantDAO.getConsultantForLogin(consultant.getAgencyId(), consultant.getUser().getLogin());
		if (consultantX == null) {
			throw new DataNotFoundException();
		}
		String encryptedPwd = Utilities.encryptPassword(consultant.getUser().getPwd());
		if (!encryptedPwd.equals(consultantX.getUser().getPwd())) {
			consultant.getUser().setPwdHint(consultantX.getUser().getPwdHint());
			throw new InvalidDetailException();
		}
		return consultantX;

	}

	public List<AgyAccess> getActiveAgyAccessesForConsultant(Integer consultantId) {

		List<AgyAccess> agyAccesses = null;
		agyAccesses = agyAccessDAO.getActiveAgyAccessesForConsultant(consultantId);
		return agyAccesses;

	}
 
	public List<Booking> getBookingsForBookingReference(String bookingReference) 
  {
		List<Booking> booking = getBookingDAO().getBookingsForBookingReference(bookingReference);
		return booking;
	}

//	public List<BookingUserAgy> getBookingUserAgysForAgency(Integer agencyId) {
//
//		List<BookingUserAgy> bookingUserAgys = null;
//		bookingUserAgys = getBookingDAO().getBookingUserAgysForAgency(agencyId, true);
//		return bookingUserAgys;
//
//	}

//	public BookingUserEntityAgy getBookingUserEntityAgy(Integer bookingId, Integer agencyId) {
//		
//		BookingUserEntityAgy bookingUserEntityAgy = null;
//    	bookingUserEntityAgy = getBookingDAO().getBookingUserEntityAgy(bookingId);
//    	bookingUserEntityAgy.setBookingDates(bookingDateDAO.getBookingDatesForBooking(bookingId, true));
//    	bookingUserEntityAgy.setBookingExpenseUsers(bookingExpenseDAO.getBookingExpenseUsersForBooking(bookingId, true));
//    	bookingUserEntityAgy.setBookingGradeUser(getBookingGradeDAO().getBookingGradeUserForBookingAndAgency(bookingId, agencyId, true));
//    	bookingUserEntityAgy.setBookingGradeApplicantUsers(getBookingGradeApplicantDAO().getBookingGradeApplicantUsersForBookingAndAgency(bookingId, agencyId, true));
//    	return bookingUserEntityAgy;
//
//	}

	public Consultant getConsultant(Integer consultantId) {

		Consultant consultant = null;
		consultant = consultantDAO.getConsultant(consultantId);
		return consultant;

	}

	public int updateConsultant(Consultant consultant, Integer auditorId) {

		int rc = consultantDAO.updateConsultant(consultant, auditorId);
		return rc;
	
	}

	public int updateConsultantShowPageHelp(Consultant consultant, Integer auditorId) {

		int rc = consultantDAO.updateConsultantShowPageHelp(consultant, auditorId);
		return rc;
	
	}

	public Agency getAgencyForCode(String agencyCode) {

		Agency agency = getAgencyDAO().getAgencyForCode(agencyCode);
		if (agency == null) {
			throw new DataNotFoundException();
		}
		return agency;

	}

	/*
	 * Applicant stuff
	 */
	
  public List<Applicant> getApplicantsForAgency(Integer agencyId) {

    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgency(agencyId, true);
    return applicants;
  }

  public List<Applicant> getApplicantsForAgencySearch(Integer agencyId, ApplicantSearchParameters applicantSearchParameters, boolean showOnlyActive) 
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencySearch(agencyId, applicantSearchParameters, showOnlyActive);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyInLastNameGroup(Integer agencyId) {

    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyInLastNameGroup(agencyId, "A", true);
    return applicants;
  }
  public List<Applicant> getApplicantsForNhsStaffName(Integer agencyId, String nhsStaffName) 
  {

    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForNhsStaffName(agencyId, nhsStaffName);
    return applicants;
  }
//NEW -->
  public List<Applicant> getWorkingApplicantsForAgency(Integer agencyId) 
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgency(agencyId, false);
    return applicants;
  }
//<-- NEW  
  public List<Applicant> getApplicantsForAgencyNew(Integer agencyId, Date dateToday)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyNew(agencyId, dateToday);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyCrbAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyCrbAboutToExpire(agencyId, dateToCheck);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyDbsAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyDbsAboutToExpire(agencyId, dateToCheck);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyFitToWorkAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyFitToWorkAboutToExpire(agencyId, dateToCheck);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyHpcAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyHpcAboutToExpire(agencyId, dateToCheck);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyIdDocumentAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyIdDocumentAboutToExpire(agencyId, dateToCheck);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyTrainingAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyTrainingAboutToExpire(agencyId, dateToCheck);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyVisaAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyVisaAboutToExpire(agencyId, dateToCheck);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyReference1NotSatisfied(Integer agencyId, Date dateToday)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyReference1NotSatisfied(agencyId, dateToday);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyReference2NotSatisfied(Integer agencyId, Date dateToday)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyReference2NotSatisfied(agencyId, dateToday);
    return applicants;
  }
  public List<Applicant> getApplicantsForAgencyDrivingLicenseAboutToExpire(Integer agencyId, Date dateToCheck)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyDrivingLicenseAboutToExpire(agencyId, dateToCheck);
    return applicants;
  }

  public List<Applicant> getApplicantsForAgencyUnarchived(Integer agencyId)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyUnarchived(agencyId);
    return applicants;
  }
  
  public List<Applicant> getApplicantsForAgencyRecentlyCompliant(Integer agencyId)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyRecentlyCompliant(agencyId);
    return applicants;
  }
  
  public List<Applicant> getApplicantsForAgencyRecentProspect(Integer agencyId)
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyRecentProspect(agencyId);
    return applicants;
  }
  
  public List<Applicant> getApplicantsForAgencyInLastNameGroup(Integer agencyId, String indexLetter) {

    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyInLastNameGroup(agencyId, indexLetter, true);
    return applicants;
  }

  public List<Applicant> getWorkingApplicantsForAgency(Integer agencyId, String indexLetter) 
  {
    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgency(agencyId, false);
    return applicants;
  }

  public List<Applicant> getApplicantsForAgencyAndNotForBookingGrade(Integer agencyId, Integer bookingGradeId) {

    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyAndNotForBookingGrade(agencyId, bookingGradeId);
    return applicants;

  }
  
  public List<Applicant> getApplicantsForAgencyAndNotForBookingGradeInLastNameGroup(Integer agencyId, Integer bookingGradeId, String indexLetter) 
  {

    List<Applicant> applicants = null;
    applicants = applicantDAO.getApplicantsForAgencyAndNotForBookingGradeInLastNameGroup(agencyId, bookingGradeId, indexLetter);
    return applicants;

  }
  
	public Applicant getApplicant(Integer applicantId) {
		
		Applicant applicant = null;
    	applicant = applicantDAO.getApplicant(applicantId);
		return applicant;
		
	}

  public List<ApplicantClientBooking> getApplicantClientBookings(Integer applicantId, Integer clientId, Integer agencyId, Date searchDate) 
  {
    List<ApplicantClientBooking> applicantClientBookings = null;
    applicantClientBookings = applicantDAO.getApplicantClientBookings(applicantId, clientId, agencyId, searchDate);
    return applicantClientBookings;
  }

	public int insertApplicant(Applicant applicant, Integer auditorId) {

		Applicant duplicateApplicant = applicantDAO.getApplicantForLogin(applicant.getAgencyId(), applicant.getUser().getLogin());
		if (duplicateApplicant != null) {
			throw new DuplicateDataException("login");
		}
        int rc = applicantDAO.insertApplicant(applicant, auditorId);
		return rc;

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

  public int archiveApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId) 
  {
    int rc = applicantDAO.archiveApplicant(applicantId, noOfChanges, auditorId);
    return rc;
  }

  public int unarchiveApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId) 
  {
    int rc = applicantDAO.unarchiveApplicant(applicantId, noOfChanges, auditorId);
    return rc;
  }

  public int compliantApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId, Boolean compliant) 
  {
    int rc = applicantDAO.compliantApplicant(applicantId, noOfChanges, auditorId, compliant);
    return rc;
  }

  public int deleteApplicant(Integer applicantId, Integer noOfChanges, Integer auditorId) 
  {
    int rc = applicantDAO.deleteApplicant(applicantId, noOfChanges, auditorId);
    return rc;
  }

  public BookingGradeApplicantUser getBookingGradeApplicantUser(Integer bookingGradeApplicantId) {
    
    BookingGradeApplicantUser bookingGradeApplicantUser = null;
    bookingGradeApplicantUser = getBookingGradeApplicantDAO().getBookingGradeApplicantUser(bookingGradeApplicantId);
    return bookingGradeApplicantUser;
    
  }

  public BookingGradeApplicantUser getBookingGradeApplicantUser(Integer bookingGradeId, Integer applicantId) 
  {
    
    BookingGradeApplicantUser bookingGradeApplicantUser = null;
    bookingGradeApplicantUser = getBookingGradeApplicantDAO().getBookingGradeApplicantUser(bookingGradeId, applicantId);
    return bookingGradeApplicantUser;
    
  }

  public int updateBookingGradeStatus(Integer bookingGradeId, Integer noOfChanges, Integer auditorId, int status)
  {
    return getBookingGradeDAO().updateBookingGradeStatus(bookingGradeId, noOfChanges, auditorId, status);
  }

	public int insertBookingGradeApplicant(BookingGradeApplicant bookingGradeApplicant, BookingGradeApplicantDate[] bookingGradeApplicantDates, Integer auditorId) {

		int rc = getBookingGradeApplicantDAO().insertBookingGradeApplicant(bookingGradeApplicant, auditorId);
		for (BookingGradeApplicantDate bookingGradeApplicantDate: bookingGradeApplicantDates) {
			bookingGradeApplicantDate.setBookingGradeApplicantId(bookingGradeApplicant.getBookingGradeApplicantId());
			int d = getBookingGradeApplicantDateDAO().insertBookingGradeApplicantDate(bookingGradeApplicantDate, auditorId);
		}

		return rc;

	}
	
	public int updateBookingGradeApplicant(BookingGradeApplicant bookingGradeApplicant, BookingGradeApplicantDate[] bookingGradeApplicantDates, Integer auditorId) {
		
		int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicant(bookingGradeApplicant, auditorId);
		
		List<BookingGradeApplicantDateUser> oldDates = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingGradeApplicant(bookingGradeApplicant.getBookingGradeApplicantId(), true);
 
		for (BookingGradeApplicantDate bookingGradeApplicantDate: oldDates) {
			int d = getBookingGradeApplicantDateDAO().deleteBookingGradeApplicantDate(bookingGradeApplicantDate.getBookingGradeApplicantDateId(), bookingGradeApplicantDate.getNoOfChanges(), auditorId);
		}
		
		for (BookingGradeApplicantDate bookingGradeApplicantDate: bookingGradeApplicantDates) {
			bookingGradeApplicantDate.setBookingGradeApplicantId(bookingGradeApplicant.getBookingGradeApplicantId());
			int d = getBookingGradeApplicantDateDAO().insertBookingGradeApplicantDate(bookingGradeApplicantDate, auditorId);
		}

		return rc;
	}

	public int updateBookingGradeAsViewed(Integer bookingGradeId, Integer noOfChanges, Integer auditorId) {

		int rc = getBookingGradeDAO().updateBookingGradeViewed(bookingGradeId, true, noOfChanges, auditorId);
		return rc;
	
	}

	public int updateBookingGradeAsNotViewed(Integer bookingGradeId, Integer noOfChanges, Integer auditorId) {

		int rc = getBookingGradeDAO().updateBookingGradeViewed(bookingGradeId, false, noOfChanges, auditorId);
		return rc;
	
	}

	public BookingGradeAgy getBookingGradeAgy(Integer bookingGradeId, Integer agencyId) {
		
		BookingGradeAgy bookingGradeAgy = null;
		bookingGradeAgy = getBookingGradeDAO().getBookingGradeAgy(bookingGradeId, agencyId);
		return bookingGradeAgy;
		
	}

	public List<BookingGradeAgy> getBookingGradeAgysForAgency(Integer agencyId, Boolean singleCandidate) {
		
		List<BookingGradeAgy> bookingGradeAgys = null;
		bookingGradeAgys = getBookingGradeDAO().getBookingGradeAgysForAgency(agencyId, singleCandidate, true);
		return bookingGradeAgys;
    
	}

  public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndDateRange(Integer agencyId, Date startDate, Date endDate, Boolean singleCandidate, boolean showOnlyActive) 
  {
    
    List<BookingGradeAgy> bookingGradeAgys = null;
    bookingGradeAgys = getBookingGradeDAO().getBookingGradeAgysForAgencyAndDateRange(agencyId, startDate, endDate, singleCandidate, showOnlyActive);
    return bookingGradeAgys;
    
  }

	
	public List<BookingGradeAgy> getBookingGradeAgysForAgencyAndStatus(Integer agencyId, Integer bookingGradeStatus, Boolean singleCandidate, Boolean viewed) {

		List<BookingGradeAgy> bookingGradeAgys = null;
		bookingGradeAgys = getBookingGradeDAO().getBookingGradeAgysForAgencyAndStatus(agencyId, bookingGradeStatus, singleCandidate, viewed);
		return bookingGradeAgys;

	}

	
	public List<BookingGradeAgy> getBookingGradeAgysForBookingGradeApplicantDateAgencyAndStatus(Integer agencyId, Integer bookingGradeApplicantDateStatus, Boolean singleCandidate) {

		List<BookingGradeAgy> bookingGradeAgys = null;
		bookingGradeAgys = getBookingGradeDAO().getBookingGradeAgysForAgencyAndBookingGradeApplicantDateStatus(agencyId, bookingGradeApplicantDateStatus, singleCandidate);
		return bookingGradeAgys;
		
	}
	
	
	public List<BookingGradeAgy> getBookingGradeAgysForAgencyUnviewed(Integer agencyId, Boolean singleCandidate) {

		List<BookingGradeAgy> bookingGradeAgys = null;
		bookingGradeAgys = getBookingGradeDAO().getBookingGradeAgysForAgencyUnviewed(agencyId, singleCandidate);
		return bookingGradeAgys;

	}
	
	public BookingGradeAgyEntity getBookingGradeAgyEntity(Integer bookingGradeId, Integer agencyId) {

//		java.util.Date before = null;
//		java.util.Date after = null;
		
		BookingGradeAgyEntity bookingGradeAgy = null;

//		before = new java.util.Date();
		bookingGradeAgy = getBookingGradeDAO().getBookingGradeAgyEntity(bookingGradeId, agencyId);
//		after = new java.util.Date();
//		System.out.println(after.getTime() -  before.getTime());
		
		if (bookingGradeAgy != null) {

//			before = new java.util.Date();
     		List<BookingDateUserApplicant> bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgencyAndBooking(agencyId, bookingGradeAgy.getBookingId());
//    		after = new java.util.Date();
//    		System.out.println(after.getTime() -  before.getTime());
			
			List<BookingDateUser> bookingDates = new ArrayList<BookingDateUser>();

//			before = new java.util.Date();
	     	for (BookingDateUserApplicant bookingDateUserApplicant: bookingDateUserApplicants) 
        {
	     		
	     		BookingDateUser bookingDateUser = bookingDateUserApplicant;
	     		
          if (bookingGradeAgy.getBookingRate().compareTo(BigDecimal.ZERO) == 0)
          {
            // This is defending against something that should NEVER happen. Lyndon 23/05/2013.
            BigDecimal zero = new BigDecimal(0.0);
            bookingDateUser.setChargeRateValue(zero);
            bookingDateUser.setPayRateValue(zero);
            bookingDateUser.setWageRateValue(zero);
          }
          else
          {
            if (bookingDateUserApplicant.getApplicantId() == null || bookingDateUserApplicant.getApplicantId() == 0)
            {
              // No applicant so use the bookingGrade values for rate, payRate and wageRate
              bookingDateUser.setChargeRateValue(bookingDateUser.getValue().multiply((bookingGradeAgy.getRate().divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
              bookingDateUser.setPayRateValue(bookingDateUser.getValue().multiply((bookingGradeAgy.getPayRate().divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
              bookingDateUser.setWageRateValue(bookingDateUser.getValue().multiply((bookingGradeAgy.getWageRate().divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
            }
            else
            {
              // Applicant exists so use the bookingGradeApplicant values for rate, payRate and wageRate 
              bookingDateUser.setChargeRateValue(bookingDateUser.getValue().multiply((bookingDateUserApplicant.getChargeRate().divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
              bookingDateUser.setPayRateValue(bookingDateUser.getValue().multiply((bookingDateUserApplicant.getPayRate().divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
              bookingDateUser.setWageRateValue(bookingDateUser.getValue().multiply((bookingDateUserApplicant.getWageRate().divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
            }
          }	     		
          bookingDates.add(bookingDateUser);
		     		
		    }
//			after = new java.util.Date();
//			System.out.println(after.getTime() -  before.getTime());

		    bookingGradeAgy.setBookingDateUsers(bookingDates);
	     	
//			before = new java.util.Date();
			bookingGradeAgy.setBookingExpenses(getBookingExpenseDAO().getBookingExpensesForBooking(bookingGradeAgy.getBookingId(), true));
//			after = new java.util.Date();
//			System.out.println(after.getTime() -  before.getTime());

//			before = new java.util.Date();
			bookingGradeAgy.setBookingGradeApplicantUsers(getBookingGradeApplicantDAO().getBookingGradeApplicantUsersForBookingGradeAndAgency(bookingGradeAgy.getBookingGradeId(), agencyId, true));
//			after = new java.util.Date();
//			System.out.println(after.getTime() -  before.getTime());

		}
		
		return bookingGradeAgy;

	}

	public List<BookingDateUser> getBookingDateUsersForBookingAndAgency(Integer bookingId, Integer agencyId) {

		List<BookingDateUser> bookingDateUsers = null;
		bookingDateUsers = getBookingDateDAO().getBookingDateUsersForBookingAndAgency(bookingId, agencyId, true);
		return bookingDateUsers;

	}

	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntity(Integer bookingGradeApplicantId) {

		BookingGradeApplicantUserEntity bookingGradeApplicantUser = null;
		bookingGradeApplicantUser = getBookingGradeApplicantDAO().getBookingGradeApplicantUserEntity(bookingGradeApplicantId);

		bookingGradeApplicantUser.setBookingGradeApplicantDateUserEntities(getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUserEntitiesForBookingGradeApplicant(bookingGradeApplicantId, true));
		
		IntValue expensesCount = getBookingGradeApplicantDAO().getBookingExpensesCount(bookingGradeApplicantUser.getBookingId());
		
		if (expensesCount.getValue() > 0) {
			for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity: bookingGradeApplicantUser.getBookingGradeApplicantDateUserEntities()) {
				bookingGradeApplicantDateUserEntity.setBookingDateExpenseUsers(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingGradeApplicantDateUserEntity.getBookingDateId()));
			}
		}
		
		bookingGradeApplicantUser.setBookingExpensesCount(expensesCount);
		
		//		bookingGradeApplicantUser.setBookingDateUserApplicants(getBookingDateDAO().getBookingDateUserApplicantsForAgencyAndBookingGradeApplicant(bookingGradeApplicantUser.getAgencyId(), null, null, bookingGradeApplicantId));
		
		return bookingGradeApplicantUser;

	}

	public int updateBookingGradeApplicantRefuse(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId) {

	    // update all bookingGradeApplicantDates
	
	    List<BookingGradeApplicantDateUser> dates = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingGradeApplicant(bookingGradeApplicantId, true);
	   	
        Integer bookingId = null;
        
	    for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser: dates) {
	
        	if (bookingGradeApplicantDateUser.getStatus() == BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OFFERED) {

        		// this date has been offered to thgis applicant - not all will be as may be offered to others, so
        		// as it is being refused make it open again
        		
        		BookingDate bookingDate = getBookingDateDAO().getBookingDate(bookingGradeApplicantDateUser.getBookingDateId());
        		int bd = getBookingDateDAO().updateBookingDateRefused(bookingDate.getBookingDateId(), 
        				                                         bookingDate.getNoOfChanges(), 
        				                                         auditorId);

        		// used below
        		bookingId = bookingDate.getBookingId();
        		
            	// set record to refused if still 'open'
            	
    	    	int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
	    				bookingGradeApplicantDateUser.getNoOfChanges(),
	    				auditorId,
	    				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_REFUSED);
	    	
        	}

		}
	    
        if (bookingId != null) {
            Booking booking = getBookingDAO().getBooking(bookingId);
            if (booking.getSingleCandidate()) {
                int b = getBookingDAO().updateBookingStatus(bookingId, booking.getNoOfChanges(), auditorId, Booking.BOOKING_STATUS_OPEN);
            }
        }

	    int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicantStatus(bookingGradeApplicantId, noOfChanges, auditorId, BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_REFUSED);
		return rc;

	}
	
	public int updateBookingGradeApplicantWithdraw(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId) {

	    // update all bookingGradeApplicantDates
	
	    List<BookingGradeApplicantDateUser> dates = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingGradeApplicant(bookingGradeApplicantId, true);
	   	
	    for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser: dates) {
	
        	// set record to withdrawn
        	
	    	int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
    				bookingGradeApplicantDateUser.getNoOfChanges(),
    				auditorId,
    				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_WITHDRAWN);
	    	
		}
	    
	    int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicantStatus(bookingGradeApplicantId, noOfChanges, auditorId, BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_WITHDRAWN);
		return rc;

	}
	
	public int updateBookingGradeApplicantAccept(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId) {

	    List<BookingGradeApplicantDateUser> bookingGradeApplicantDates = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingGradeApplicant(bookingGradeApplicantId, true);

	    BigDecimal filledValue = new BigDecimal(0);
	    BigDecimal unFilledValue = new BigDecimal(0);
	    
    	String bookingDateIdStrs = "";
    	
	    for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser: bookingGradeApplicantDates) {
	    	
	    	if (bookingGradeApplicantDateUser.getStatus() == BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OFFERED) {
	
	    		// this date has been offered to this applicant - not all will be as may be offered to others, so
	    		// as it is being accepted make it filled
	    		
	    		BookingDate bookingDate = getBookingDateDAO().getBookingDate(bookingGradeApplicantDateUser.getBookingDateId());
	    		int bd = getBookingDateDAO().updateBookingDateFilled(bookingDate.getBookingDateId(), 
	    				                                        bookingDate.getNoOfChanges(), 
	    				                                        auditorId, 
	    				                                        bookingGradeApplicantDateUser.getBookingGradeApplicantDateId());
	
	    		// sum up the 'agency' value of the dates that we are filling
	    		filledValue = filledValue.add(bookingGradeApplicantDateUser.getValue());
	    		// sum up the 'rrp' value of the dates that we are filling so the budgets can be adjusted
	    		unFilledValue = unFilledValue.add(bookingDate.getValue());
	    		
		    	int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
	    				bookingGradeApplicantDateUser.getNoOfChanges(),
	    				auditorId,
	    				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED);
	    	
	    		if (!"".equals(bookingDateIdStrs)) {
	    			bookingDateIdStrs = bookingDateIdStrs + ",";
	    		}
				bookingDateIdStrs = bookingDateIdStrs + bookingDate.getBookingDateId();

				// set ALL OTHER 'open' and 'renegotiate' applicant bookingGradeApplicantDate records to 'unsuccessful'
		    	
			    List<BookingGradeApplicantDate> dates = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDatesForBookingDateAndStatus(bookingDate.getBookingDateId(), BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OPEN);
			    
			    dates.addAll(getBookingGradeApplicantDateDAO().getBookingGradeApplicantDatesForBookingDateAndStatus(bookingDate.getBookingDateId(), BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_RENEGOTIATE));
			   	
			    for (BookingGradeApplicantDate bgadu: dates) {
			    	
			    	int x = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bgadu.getBookingGradeApplicantDateId(),
			    			bgadu.getNoOfChanges(),
		    				auditorId,
		    				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_UNSUCCESSFUL);
		    	
			    }
		    	
	    	}
	    }

	    // long winded way of getting to booking but ...
	    
	    BookingGradeApplicantUser bookingGradeApplicant = getBookingGradeApplicantDAO().getBookingGradeApplicantUser(bookingGradeApplicantId);
	    BookingGrade bookingGrade = getBookingGradeDAO().getBookingGrade(bookingGradeApplicant.getBookingGradeId());
	    Booking booking = getBookingDAO().getBooking(bookingGrade.getBookingId());
	    
        // if there are no dates left to fill mark the booking as filled
	    IntValue countNotFilled = getBookingDAO().getBookingCountNotFilled(booking.getBookingId());
	    if (countNotFilled.getValue() == 0) {
		    
	    	// set all 'open' and 'renegotiate' bookingApplicants to 'unsuccessful'
	    	
	    	// TODO - SHOULD not be passing -999 as clientId !!!
	    	Integer clientId = new Integer(-999);
	    	
		    List<BookingGradeApplicantUser> bookingGradeApplicants = getBookingGradeApplicantDAO().getBookingGradeApplicantUsersForBookingAndClient(booking.getBookingId(), clientId, true);
		    for (BookingGradeApplicantUser bga: bookingGradeApplicants) {
		    	if (bga.getStatus() == BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_OPEN ||
		    		bga.getStatus() == BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_RENEGOTIATE) {
			    	int a = getBookingGradeApplicantDAO().updateBookingGradeApplicantStatus(bga.getBookingGradeApplicantId(), bga.getNoOfChanges(), auditorId, BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_UNSUCCESSFUL);
		    	}
		    }
		    
		    // set all 'open' bookingGrades to 'closed'
		    List<BookingGradeUser> bookingGrades = getBookingGradeDAO().getBookingGradeUsersForBooking(booking.getBookingId(), true);
		    for (BookingGradeUser bg: bookingGrades) {
		    	if (bg.getStatus() == BookingGrade.BOOKINGGRADE_STATUS_OPEN) {
			    	int g = getBookingGradeDAO().updateBookingGradeStatus(bg.getBookingGradeId(), bg.getNoOfChanges(), auditorId, BookingGrade.BOOKINGGRADE_STATUS_CLOSED);
		    	}
		    }
            // set the booking as 'closed' and update (add to) the booking.filledValue
		    int b = getBookingDAO().updateBookingClosed(booking.getBookingId(), booking.getNoOfChanges(), auditorId, filledValue);
	    }
	    else {
            // update (add to) the booking.filledValue
	    	int b = getBookingDAO().updateBookingFilledValue(booking.getBookingId(), booking.getNoOfChanges(), auditorId, filledValue);
	    }

	    int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicantStatus(bookingGradeApplicantId, noOfChanges, auditorId, BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_FILLED);

        // TODO 
		DecimalFormat df = new DecimalFormat("#000");
	    rc = insertBudgetTransaction(booking.getLocationId(), 
				                     booking.getJobProfileId(), 
				                     unFilledValue.subtract(filledValue),
				                     new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
				                     "Applicant (" + bookingGradeApplicant.getApplicantFullNameLastFirst() + ") accepted - " + df.format(booking.getBookingId()), 
				                     auditorId);
	    
        if (booking.getAutoActivate()) {
        	// if auto activate ....
        	int bda = updateBookingDatesActivated(bookingDateIdStrs, auditorId);
        }
     	
	    return rc;

	}
	
	public int updateBookingGradeApplicantSubmit(Integer bookingId, Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId) {
		
		int rc = 0;
	    // if booking is autoFill call autoFill method ...	
		Booking booking = getBookingDAO().getBooking(bookingId);
		if (booking.getAutoFill()) {
			rc = updateBookingGradeApplicantAutoFill(bookingGradeApplicantId, noOfChanges, auditorId);
		}
		else {
			rc = updateBookingGradeApplicantOpen(bookingGradeApplicantId, noOfChanges, auditorId);
		}
		return rc; 

	}

	public int updateBookingGradeApplicantOpen(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId) {

        int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicantStatus(bookingGradeApplicantId, noOfChanges, auditorId, BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_OPEN);
        List<BookingGradeApplicantDateUser> bookingGradeApplicantDateUsers = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingGradeApplicant(bookingGradeApplicantId, true);
		for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser: bookingGradeApplicantDateUsers) {
			int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
																					bookingGradeApplicantDateUser.getNoOfChanges(),
																					auditorId,
																					BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OPEN);
		}
        return rc;

	}
	
	public int updateBookingGradeApplicantAutoFill(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId) {

		int datesFilled = 0;
		
	    List<BookingGradeApplicantDateUser> bookingGradeApplicantDates = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingGradeApplicant(bookingGradeApplicantId, true);
	   	
	    BigDecimal filledValue = new BigDecimal(0);
	    BigDecimal unFilledValue = new BigDecimal(0);
	    
    	String bookingDateIdStrs = "";
    	
	    for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser: bookingGradeApplicantDates) {
	    	
	    	if (bookingGradeApplicantDateUser.getBookingDateStatus() == BookingDate.BOOKINGDATE_STATUS_OPEN) {
	
	    		// this date is open so fill it
	    		
	    		BookingDate bookingDate = getBookingDateDAO().getBookingDate(bookingGradeApplicantDateUser.getBookingDateId());
	    		int bd = getBookingDateDAO().updateBookingDateFilled(bookingDate.getBookingDateId(), 
	    				                                        bookingDate.getNoOfChanges(), 
	    				                                        auditorId, 
	    				                                        bookingGradeApplicantDateUser.getBookingGradeApplicantDateId());
	
	    		// sum up the 'agency' value of the dates that we are filling
	    		filledValue = filledValue.add(bookingGradeApplicantDateUser.getValue());
	    		// sum up the 'rrp' value of the dates that we are filling so the budgets can be adjusted
	    		unFilledValue = unFilledValue.add(bookingDate.getValue());

	    		int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
	    				bookingGradeApplicantDateUser.getNoOfChanges(),
	    				auditorId,
	    				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_FILLED);
		    	
		    	datesFilled++;
	    	
	    		if (!"".equals(bookingDateIdStrs)) {
	    			bookingDateIdStrs = bookingDateIdStrs + ",";
	    		}
				bookingDateIdStrs = bookingDateIdStrs + bookingDate.getBookingDateId();
	    	}
	    	else {
	    		
		    	int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
	    				bookingGradeApplicantDateUser.getNoOfChanges(),
	    				auditorId,
	    				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_UNSUCCESSFUL);

	    	}
	    }

	    // long winded way of getting to booking but ...
	    
	    BookingGradeApplicantUser bookingGradeApplicant = getBookingGradeApplicantDAO().getBookingGradeApplicantUser(bookingGradeApplicantId);
	    BookingGrade bookingGrade = getBookingGradeDAO().getBookingGrade(bookingGradeApplicant.getBookingGradeId());
	    Booking booking = getBookingDAO().getBooking(bookingGrade.getBookingId());
	    
        // if there are no dates left to fill mark the booking as filled
	    IntValue countNotFilled = getBookingDAO().getBookingCountNotFilled(booking.getBookingId());
	    if (countNotFilled.getValue() == 0) {
		    
	    	// set all 'open' bookingApplicants to 'unsuccessful'
	    	
	    	// TODO - SHOULD not be passing -999 as clientId !!!
	    	Integer clientId = new Integer(-999);
	    	
		    List<BookingGradeApplicantUser> bookingGradeApplicants = getBookingGradeApplicantDAO().getBookingGradeApplicantUsersForBookingAndClient(booking.getBookingId(), clientId, true);
		    for (BookingGradeApplicantUser bga: bookingGradeApplicants) {
		    	if (bga.getStatus() == BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_OPEN) {
			    	int a = getBookingGradeApplicantDAO().updateBookingGradeApplicantStatus(bga.getBookingGradeApplicantId(), bga.getNoOfChanges(), auditorId, BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_UNSUCCESSFUL);
		    	}
		    }
		    
		    // set all 'open' bookingGrades to 'closed'
		    List<BookingGradeUser> bookingGrades = getBookingGradeDAO().getBookingGradeUsersForBooking(booking.getBookingId(), true);
		    for (BookingGradeUser bg: bookingGrades) {
		    	if (bg.getStatus() == BookingGrade.BOOKINGGRADE_STATUS_OPEN) {
			    	int g = getBookingGradeDAO().updateBookingGradeStatus(bg.getBookingGradeId(), bg.getNoOfChanges(), auditorId, BookingGrade.BOOKINGGRADE_STATUS_CLOSED);
		    	}
		    }
            // set the booking as 'closed' and update (add to) the booking.filledValue
		    int b = getBookingDAO().updateBookingClosed(booking.getBookingId(), booking.getNoOfChanges(), auditorId, filledValue);
	    }
	    else {
            // update (add to) the booking.filledValue
	    	int b = getBookingDAO().updateBookingFilledValue(booking.getBookingId(), booking.getNoOfChanges(), auditorId, filledValue);
	    }
	    
        int bookingGradeApplicantStatus = datesFilled > 0 ? BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_FILLED : BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_UNSUCCESSFUL;
	    
        int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicantStatus(bookingGradeApplicantId, 
	    			                                   noOfChanges, auditorId, 
	    			                                   bookingGradeApplicantStatus);

        // TODO 
        // TODO 
		DecimalFormat df = new DecimalFormat("#000");
		rc = insertBudgetTransaction(booking.getLocationId(), 
				                     booking.getJobProfileId(), 
				                     unFilledValue.subtract(filledValue),
				                     new BigDecimal(0), new BigDecimal(0), new BigDecimal(0),
				                     "Applicant (" + bookingGradeApplicant.getApplicantFullNameLastFirst() + ") auto filled - " + df.format(booking.getBookingId()), 
				                     auditorId);


		// TODO temporarily send an email to kevin@matchmyjob.co.uk
		//
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		if (MailHandler.getInstance().getSendAutoFillEmail()) {
			
	    	String from = "autofilled@matchmyjob.co.uk";
	        String to = "kevin@matchmyjob.co.uk";
	        String subject = "Booking Auto Filled";

	        StringBuffer content = new StringBuffer();
	        content.append("\n");
	        content.append("Booking Auto Filled");
	        content.append("\n\n");
	        content.append("Booking No.");
	        content.append("\t");
	        content.append(bookingGrade.getBookingId());
	        content.append("\n");
	        content.append("Client");
	        content.append("\t");
	        content.append(bookingGradeApplicant.getClientName() + " " + bookingGradeApplicant.getClientCode());
	        content.append("\n");

	        String contentType = "text/plain";

	        int status = MailHandler.getInstance().sendSingleMail(from, to, subject, content.toString(), contentType);
	        System.out.println(status);

		}
        
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
        if (booking.getAutoActivate()) {
        	// if auto activate ....
        	int bda = updateBookingDatesActivated(bookingDateIdStrs, auditorId);
        }
     	
     	
     	
     	
	    return rc;

	}
	
  public int updateBookingGradeApplicantChecklistCreatedTime(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId, Date checklistCreatedTime) 
  {
    int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicantChecklistCreatedTime(bookingGradeApplicantId, noOfChanges, auditorId, checklistCreatedTime);
    return rc;
  }
	
	public BookingGrade getBookingGrade(Integer bookingGradeId) {
		
		BookingGrade bookingGrade = null;
    	bookingGrade = getBookingGradeDAO().getBookingGrade(bookingGradeId);
		return bookingGrade;

	}

  // NOTE. There can be more that ONE BookingGrade for a Booking so ONLY use this when you know for a fact
  //       that there is only one. Eg. For an NHS Booking.
  public BookingGrade getBookingGradeForBookingClientAgencyJobProfileGrade(Integer bookingId, Integer clientAgencyJobProfileGradeId)
  {
    BookingGrade bookingGrade = null;
    bookingGrade = getBookingGradeDAO().getBookingGradeForBookingClientAgencyJobProfileGrade(bookingId, clientAgencyJobProfileGradeId);
    return bookingGrade;
  }

//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgency(Integer agencyId, Boolean singleCandidate, Boolean viewed) {
//		
//		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
//		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgency(agencyId, singleCandidate, viewed);
//		return bookingDateUserApplicants;
//
//	}
//
//	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndStatus(Integer agencyId, Boolean singleCandidate, Boolean viewed, Integer bookingDateStatus) {
//		
//		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
//		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgencyAndStatus(agencyId, singleCandidate, viewed, bookingDateStatus);
//		return bookingDateUserApplicants;
//
//	}

	public List<BookingDateUserGrade> getBookingDateUserGradesForAgency(Integer agencyId, Boolean singleCandidate, Boolean viewed) {
		
		List<BookingDateUserGrade> bookingDateUserGrades = null;
		bookingDateUserGrades = getBookingDateDAO().getBookingDateUserGradesForAgency(agencyId, singleCandidate, viewed);
		return bookingDateUserGrades;

	}
	
	public List<BookingDateUserGrade> getBookingDateUserGradesForAgencyAndStatus(Integer agencyId, Boolean singleCandidate, Boolean viewed, Integer bookingGradeStatus) {
		
		List<BookingDateUserGrade> bookingDateUserGrades = null;
		bookingDateUserGrades = getBookingDateDAO().getBookingDateUserGradesForAgencyAndStatus(agencyId, singleCandidate, viewed, bookingGradeStatus);
		return bookingDateUserGrades;

	}
	
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgency(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, 
          Date fromDate, Date toDate) 
  {
    List<BookingDateUserApplicant> bookingDateUserApplicants = null;
    bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgency(agencyId, 
        bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingGradeStatus, bookingDateStatus, workedStatus, 
        singleCandidate, activated, viewed, clientId, siteId, locationId, jobProfileId, applicantId,
        fromDate, toDate);
    return bookingDateUserApplicants;
  }
  
  // This is the same as the above method except the data is sorted first by Client Name, Site Name and Location Name.
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyByClientSiteLocation(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, 
          Date fromDate, Date toDate) 
  {
    List<BookingDateUserApplicant> bookingDateUserApplicants = null;
    bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgencyByClientSiteLocation(agencyId, 
        bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingGradeStatus, bookingDateStatus, workedStatus, 
        singleCandidate, activated, viewed, clientId, siteId, locationId, jobProfileId, applicantId,
        fromDate, toDate);
    return bookingDateUserApplicants;
  }
  
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, 
          Date fromDate, Date toDate, String backingReportName) 
  {
    List<BookingDateUserApplicant> bookingDateUserApplicants = null;
    bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgencyBackingReport(agencyId, 
        bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingGradeStatus, bookingDateStatus, workedStatus, 
        singleCandidate, activated, viewed, clientId, siteId, locationId, jobProfileId, applicantId,
        fromDate, toDate, backingReportName);
    return bookingDateUserApplicants;
  }
  
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyNotOnBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, Integer offset) 
  {
    List<BookingDateUserApplicant> bookingDateUserApplicants = null;
    bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgencyNotOnBackingReport(agencyId, 
        bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingGradeStatus, bookingDateStatus, workedStatus, 
        singleCandidate, activated, viewed, clientId, siteId, locationId, jobProfileId, applicantId, offset);
    return bookingDateUserApplicants;
  }
  
  public RecordCount getBookingDatesUserApplicantsForAgencyNotOnBackingReportCount(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
      Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId) 
  {
    RecordCount bookingDatesCount = null;
    bookingDatesCount = getBookingDateDAO().getBookingDatesUserApplicantsForAgencyNotOnBackingReportCount(agencyId, 
        bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingGradeStatus, bookingDateStatus, workedStatus, 
        singleCandidate, activated, viewed, clientId, siteId, locationId, jobProfileId, applicantId);
    return bookingDatesCount;
  }

  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyOnBackingReport(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
          Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, Integer offset) 
  {
    List<BookingDateUserApplicant> bookingDateUserApplicants = null;
    bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgencyOnBackingReport(agencyId, 
        bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingGradeStatus, bookingDateStatus, workedStatus, 
        singleCandidate, activated, viewed, clientId, siteId, locationId, jobProfileId, applicantId, offset);
    return bookingDateUserApplicants;
  }
  
  public RecordCount getBookingDatesUserApplicantsForAgencyOnBackingReportCount(Integer agencyId, 
      Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
      Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId) 
  {
    RecordCount bookingDatesCount = null;
    bookingDatesCount = getBookingDateDAO().getBookingDatesUserApplicantsForAgencyOnBackingReportCount(agencyId, 
        bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingGradeStatus, bookingDateStatus, workedStatus, 
        singleCandidate, activated, viewed, clientId, siteId, locationId, jobProfileId, applicantId);
    return bookingDatesCount;
  }

  public Integer getBookingDatePagingLimit()
  {
    Integer bookingDatePagingLimit = getBookingDateDAO().getPagingLimit();
    return bookingDatePagingLimit;
  }

  public Integer getBookingDatePagingGroupSize()
  {
    Integer bookingDatePagingGroupSize = getBookingDateDAO().getPagingGroupSize();
    return bookingDatePagingGroupSize;
  }

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsToActivateForAgency(Integer agencyId) {
		
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsToActivateForAgency(agencyId);
		return bookingDateUserApplicants;

	}

	public List<BookingDateUserApplicantEntity> getBookingDateUserApplicantEntitiesForAgency(Integer agencyId, 
			Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingGradeStatus, Integer bookingDateStatus, Integer workedStatus,                                                  
        	Boolean singleCandidate, Boolean activated, Boolean viewed, Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer applicantId, 
        	Date fromDate, Date toDate) {

		List<BookingDateUserApplicantEntity> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantEntitiesForAgency(agencyId, 
				bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingGradeStatus, bookingDateStatus, workedStatus, 
				singleCandidate, activated, viewed, clientId, siteId, locationId, jobProfileId, applicantId,
				fromDate, toDate);
		
		// add in other stuff ! like expenses and hours
        for (BookingDateUserApplicantEntity bookingDateUserApplicantEntity: bookingDateUserApplicants) {
    		bookingDateUserApplicantEntity.setBookingDateHours(bookingDateHourDAO.getBookingDateHoursForBookingDate(bookingDateUserApplicantEntity.getBookingDateId(), true));
    		bookingDateUserApplicantEntity.setBookingDateExpenses(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingDateUserApplicantEntity.getBookingDateId()));
        }
		
		return bookingDateUserApplicants;

	}

  public Client getClientForReference(String reference) 
  {
    Client client = null;
    client = getClientDAO().getClientForReference(reference);
    return client;
  }
	
	public List<StatusCount> getBookingStatusCountsForAgency(Integer agencyId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingGradeDAO().getBookingStatusCountsForAgency(agencyId);
		return statusCounts;
	
	}

	public List<StatusCount> getShiftStatusCountsForAgency(Integer agencyId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingGradeDAO().getShiftStatusCountsForAgency(agencyId);
		return statusCounts;
	
	}

	public List<StatusCount> getBookingGradeApplicantDateStatusCountsForAgency(Integer agencyId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingGradeDAO().getBookingGradeApplicantDateStatusCountsForAgency(agencyId);
		return statusCounts;
	
	}

	public List<StatusCount> getShiftWorkedStatusCountsForAgency(Integer agencyId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingGradeDAO().getShiftWorkedStatusCountsForAgency(agencyId);
		return statusCounts;
	
	}

	public List<StatusCount> getAgencyInvoiceStatusCountsForAgency(Integer agencyId) {
		
		List<StatusCount> statusCounts = null;
		statusCounts = getBookingDAO().getAgencyInvoiceStatusCountsForAgency(agencyId);
		return statusCounts;

	}
	
	public IntValue getBookingGradeIdForAgencyAndBookingDate(Integer agencyId, Integer bookingId) {
		
		IntValue bookingGradeId = null;
    	bookingGradeId = getBookingGradeDAO().getBookingGradeIdForAgencyAndBookingDate(agencyId, bookingId);
		return bookingGradeId;

	}

  	public IntValue getShiftsToActivateCountForAgency(Integer agencyId) {

		return getBookingDAO().getShiftsToActivateCountForAgency(agencyId);
  		
  	}
	
  	public IntValue getShiftsOutstandingCountForAgency(Integer agencyId) {

		return getBookingDAO().getShiftsOutstandingCountForAgency(agencyId);
  		
  	}
	
    public List<ClientUser> getClientUsersForAgency(Integer agencyId) {
      
      List<ClientUser> clients = null;
      clients = getClientDAO().getClientUsersForAgency(agencyId);
      return clients;

    }

    public List<ClientUser> getNhsClientUsersForAgency(Integer agencyId) 
    {
      List<ClientUser> clients = null;
      clients = getClientDAO().getNhsClientUsersForAgency(agencyId);
      return clients;
    }

	public List<SiteUser> getSiteUsersForAgency(Integer agencyId, Integer clientId) {

		List<SiteUser> sites = null;
		sites = getSiteDAO().getSiteUsersForAgency(agencyId, clientId);
		return sites;

	}

  // NEW -->
  public List<Site> getSitesForNhsLocation(Integer clientId, String nhsLocation) 
  {

    List<Site> sites = null;
    sites = getSiteDAO().getSitesForNhsLocation(clientId, nhsLocation);
    return sites;

  }

  public List<Location> getLocationsForNhsWard(Integer siteId, String nhsWard) 
  {

    List<Location> locations = null;
    locations = getLocationDAO().getLocationsForNhsWard(siteId, nhsWard);
    return locations;

  }
  // <-- NEW
  
	public List<LocationUser> getLocationUsersForAgency(Integer agencyId, Integer clientId, Integer siteId) {

		List<LocationUser> locationUsers = null;
		locationUsers = getLocationDAO().getLocationUsersForAgency(agencyId, clientId, siteId);
		return locationUsers;

	}

	public List<JobProfileUser> getJobProfileUsersForAgency(Integer agencyId, Integer clientId, Integer siteId, Integer locationId) {

		List<JobProfileUser> jobProfileUsers = null;
		jobProfileUsers = getJobProfileDAO().getJobProfileUsersForAgency(agencyId, clientId, siteId, locationId);
		return jobProfileUsers;

	}
	
//	public List<LocationUser> getLocationUsersForAgencyForSite(Integer agencyId, Integer siteId) {
//
//		List<LocationUser> locationUsers = null;
//		locationUsers = locationDAO.getLocationUsersForAgencyForSite(agencyId, siteId);
//		return locationUsers;
//
//	}
	
//	public List<JobProfileUser> getJobProfileUsersForAgencyForLocation(Integer agencyId, Integer locationId) {
//
//		List<JobProfileUser> jobProfileUsers = null;
//		jobProfileUsers = jobProfileDAO.getJobProfileUsersForAgencyForLocation(agencyId, locationId);
//		return jobProfileUsers;
//
//	}

//	public List<JobProfileUser> getJobProfileUsersForAgencyForSite(Integer agencyId, Integer siteId) {
//
//		List<JobProfileUser> jobProfileUsers = null;
//		jobProfileUsers = jobProfileDAO.getJobProfileUsersForAgencyForSite(agencyId, siteId);
//		return jobProfileUsers;
//
//	}

	public InvoiceAgencyUser getInvoiceAgencyUserForInvoiceForAgency(Integer invoiceId, Integer agencyId) {
		
		InvoiceAgencyUser invoiceAgency = null;
		invoiceAgency = getInvoiceAgencyDAO().getInvoiceAgencyUserForInvoiceForAgency(invoiceId, agencyId);
		return invoiceAgency;

	}
	
	public InvoiceAgencyUserEntity getInvoiceAgencyUserEntityForInvoiceForAgency(Integer invoiceId, Integer agencyId) {
		
		InvoiceAgencyUserEntity invoiceAgency = null;
		invoiceAgency = getInvoiceAgencyDAO().getInvoiceAgencyUserEntityForInvoiceForAgency(invoiceId, agencyId);
		
		if (invoiceAgency != null) {
			// NOTE - parameters are agencyId THEN invoiceId
			invoiceAgency.setBookingDateUserApplicants(getBookingDateDAO().getBookingDateUserApplicantEntitiesForAgencyAndInvoice(agencyId, invoiceId));

			// TODO - soooo rubbishly slow!
			
			if (invoiceAgency.getExpenseValue().compareTo(new BigDecimal(0)) > 0) {
			    // expenses
				for (BookingDateUserApplicantEntity bookingDate: invoiceAgency.getBookingDateUserApplicants()) {
					if (bookingDate.getExpenseValue().compareTo(new BigDecimal(0)) > 0) {
						bookingDate.setBookingDateExpenses(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingDate.getBookingDateId()));
					}
				}
			}
			
		}
		
		return invoiceAgency;

	}

	public int updateInvoiceAgency(InvoiceAgency invoiceAgency, Integer auditorId) {

        int rc = getInvoiceAgencyDAO().updateInvoiceAgency(invoiceAgency, auditorId);
		return rc;

	}

	public JobProfileUser getJobProfileUser(Integer jobProfileId) {
		
		JobProfileUser jobProfile = null;
		jobProfile = getJobProfileDAO().getJobProfileUser(jobProfileId);
		return jobProfile;

	}

	public ClientAgencyJobProfileGrade getClientAgencyJobProfileGrade(Integer clientAgencyJobProfileGradeId) {

		ClientAgencyJobProfileGrade clientAgencyJobProfileGrade = null;
		clientAgencyJobProfileGrade = getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGrade(clientAgencyJobProfileGradeId);
		return clientAgencyJobProfileGrade;
		
	}
	
	public List<ClientAgencyJobProfileGradeUser> getClientAgencyJobProfileUsersForClientAgencyJobProfile(Integer clientAgencyJobProfileId) {
		
		List<ClientAgencyJobProfileGradeUser> clientAgencyJobProfileUsers = null;
		clientAgencyJobProfileUsers = getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(clientAgencyJobProfileId);
		return clientAgencyJobProfileUsers;

	}
	
	public AgencyInvoiceUser getAgencyInvoiceUser(Integer agencyInvoiceId) {
		
		AgencyInvoiceUser agencyInvoice = null;
		agencyInvoice = getAgencyInvoiceDAO().getAgencyInvoiceUser(agencyInvoiceId);
		return agencyInvoice;

	}
	
	public AgencyInvoiceUserEntity getAgencyInvoiceUserEntity(Integer agencyInvoiceId) {
		
		AgencyInvoiceUserEntity agencyInvoice = null;
		agencyInvoice = getAgencyInvoiceDAO().getAgencyInvoiceUserEntity(agencyInvoiceId);
		
		if (agencyInvoice != null) {
			
			//

			List <BookingDateUserApplicantEntity> bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantEntitiesForAgencyAndAgencyInvoice(agencyInvoice.getAgencyId(), agencyInvoiceId); 

			// TODO - soooo rubbishly slow!
			
			for (BookingDateUserApplicantEntity bookingDate: bookingDateUserApplicants) {
				if (bookingDate.getExpenseValue().compareTo(new BigDecimal(0)) > 0) {
					bookingDate.setBookingDateExpenses(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingDate.getBookingDateId()));
				}
				if (bookingDate.getWorkedNoOfHours().compareTo(new BigDecimal(0)) > 0) {
					// only get workedNoOfHours > 0
					bookingDate.setBookingDateHours(bookingDateHourDAO.getBookingDateHoursForBookingDate(bookingDate.getBookingDateId(), true));
				}
			}
			
//			if (agencyInvoice.getExpenseValue().compareTo(new BigDecimal(0)) > 0) {
//			
//				// expenses
//				for (BookingDateUserApplicantEntity bookingDate: bookingDateUserApplicants) {
//					if (bookingDate.getExpenseValue().compareTo(new BigDecimal(0)) > 0) {
//						bookingDate.setBookingDateExpenses(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingDate.getBookingDateId()));
//					}
//				}
//			}
			
			agencyInvoice.setBookingDateUserApplicants(bookingDateUserApplicants);

		
		}
		
		return agencyInvoice;

	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndBookingDates(Integer agencyId, String bookingDateIdStrs) {

		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgencyAndBookingDates(agencyId, bookingDateIdStrs);
		return bookingDateUserApplicants;
	
	}

    public int insertAgencyInvoice(Integer agencyId, String bookingDateIdStrs, Integer auditorId) {
    	
    	List<BookingDateUserApplicant> bookingDates = getBookingDateDAO().getBookingDateUserApplicantsForAgencyAndBookingDates(agencyId, bookingDateIdStrs);

    	BigDecimal chargeRateValue = new BigDecimal(0);
    	BigDecimal payRateValue = new BigDecimal(0);
    	BigDecimal wageRateValue = new BigDecimal(0);
    	BigDecimal wtdValue = new BigDecimal(0);
    	BigDecimal niValue = new BigDecimal(0);
    	BigDecimal commissionValue = new BigDecimal(0);
    	BigDecimal expenseValue = new BigDecimal(0);
    	BigDecimal vatValue = new BigDecimal(0);
    	BigDecimal noOfHours = new BigDecimal(0);
    	BigDecimal feeValue = new BigDecimal(0);

    	for (BookingDateUserApplicant bookingDate: bookingDates) {

	    	chargeRateValue = chargeRateValue.add(bookingDate.getWorkedChargeRateValue());
			payRateValue = payRateValue.add(bookingDate.getWorkedPayRateValue());
			wageRateValue = wageRateValue.add(bookingDate.getWorkedWageRateValue());
			wtdValue = wtdValue.add(bookingDate.getWorkedWtdValue());
			niValue = niValue.add(bookingDate.getWorkedNiValue());
			commissionValue = commissionValue.add(bookingDate.getWorkedCommissionValue());
			expenseValue = expenseValue.add(bookingDate.getExpenseValue());
			vatValue = vatValue.add(bookingDate.getTotalVatValue());
		 	noOfHours = noOfHours.add(bookingDate.getWorkedNoOfHours());
	        feeValue = feeValue.add(bookingDate.getFeeValue());
	        
		}
    	
    	AgencyInvoice agencyInvoice = new AgencyInvoice();
    	agencyInvoice.setAgencyId(agencyId);
    	
    	// using the first one ...
    	agencyInvoice.setClientId(bookingDates.get(0).getClientId());
    	
    	agencyInvoice.setChargeRateValue(chargeRateValue);
    	agencyInvoice.setPayRateValue(payRateValue);
    	agencyInvoice.setWageRateValue(wageRateValue);
    	agencyInvoice.setWtdValue(wtdValue);
    	agencyInvoice.setNiValue(niValue);
    	agencyInvoice.setCommissionValue(commissionValue);
    	agencyInvoice.setExpenseValue(expenseValue);
    	agencyInvoice.setVatValue(vatValue);
    	agencyInvoice.setNoOfHours(noOfHours);
    	agencyInvoice.setFeeValue(feeValue);
    	
    	int rc = getAgencyInvoiceDAO().insertAgencyInvoice(agencyInvoice, auditorId);

    	Integer agencyInvoiceId = agencyInvoice.getAgencyInvoiceId();
    	
    	for (BookingDate bookingDate: bookingDates) {
    		rc = getBookingDateDAO().updateBookingDateInvoiced(bookingDate.getBookingDateId(), agencyInvoiceId, bookingDate.getNoOfChanges(), auditorId);
    	}
    	
    	// TODO a bit odd !!!
    	return agencyInvoiceId;
    	
    }
    
    public boolean getBookingDatesAlreadyInvoiced(Integer agencyId, String bookingDateIdStrs) {

    	List<BookingDateUserApplicant> bookingDates = getBookingDateDAO().getBookingDateUserApplicantsForAgencyAndBookingDates(agencyId, bookingDateIdStrs);

    	for (BookingDate bookingDate: bookingDates) {
    		if (bookingDate.getWorkedStatus() == BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED) {
    			return true;
    		}
    	}
    	return false;

    }

    public boolean getBookingDatesAlreadyAuthorized(Integer agencyId, String bookingDateIdStrs) {

    	List<BookingDateUserApplicant> bookingDates = getBookingDateDAO().getBookingDateUserApplicantsForAgencyAndBookingDates(agencyId, bookingDateIdStrs);

    	for (BookingDate bookingDate: bookingDates) {
    		if (bookingDate.getWorkedStatus() == BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED) {
    			return true;
    		}
    	}
    	return false;

    }

    public int updateAgencyInvoice(AgencyInvoice agencyInvoice, Integer auditorId) {

        int rc = getAgencyInvoiceDAO().updateAgencyInvoice(agencyInvoice, auditorId);
		return rc;

	}

	public int updateAgencyInvoiceTimesheet(AgencyInvoice agencyInvoice, Integer auditorId) {

        int rc = getAgencyInvoiceDAO().updateAgencyInvoiceTimesheet(agencyInvoice, auditorId);
		return rc;

	}

    public int updateAgencyInvoiceCredited(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId, String reasonText) {
    	
    	// create the agencyInvoiceCredit
    	AgencyInvoiceCredit agencyInvoiceCredit = new AgencyInvoiceCredit();
    	agencyInvoiceCredit.setAgencyInvoiceId(agencyInvoiceId);
    	agencyInvoiceCredit.setReasonText(reasonText);
    	//
    	int rc = getAgencyInvoiceCreditDAO().insertAgencyInvoiceCredit(agencyInvoiceCredit, auditorId);
    	
    	Integer agencyInvoiceCreditId = agencyInvoiceCredit.getAgencyInvoiceCreditId();
    	
    	// update all the bookingDates for the agencyInvoice
    	rc = getBookingDateDAO().updateBookingDatesCreditedForAgencyInvoice(agencyInvoiceId, auditorId, agencyInvoiceCreditId);
    	

    	
    	
    	// TODO - need to add the budget back in for each of the bookingDates - still not taking discount into account - in or out
    	
//    	// adjust budget - give the money back!
//		DecimalFormat df = new DecimalFormat("#000");
//    	
//        // if completed (authorized !!!) need to use worked values
//        // if filled use the 'agreed' agency value else use RRP value
//
//		BigDecimal value = bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_COMPLETED ? bookingDate.getWorkedChargeRateValue() :
//								bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_FILLED ? agyValue : 
//									bookingDate.getValue();
//		BigDecimal vatValue = bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_COMPLETED ? bookingDate.getTotalVatValue() : 
//								new BigDecimal(0);
//		BigDecimal expenseValue = bookingDate.getStatus() == BookingDate.BOOKINGDATE_STATUS_COMPLETED ? bookingDate.getExpenseValue() : 
//									new BigDecimal(0);
//		BigDecimal nonPayValue = new BigDecimal(0);
//		
//        rc = insertBudgetTransaction(booking.getLocationId(), 
//                                     booking.getJobProfileId(), 
//                                     value, 
//       		                         vatValue, 
//       		                         expenseValue, 
//       		                         nonPayValue,
//                                     // needs to be multi-lingual
//                                     "Cancelled shift - " + df.format(bookingDate.getBookingId()) + "." + df.format(bookingDate.getBookingDateId()), 
//	                                 auditorId);
    	
    	
    	
    	// update the agencyInvoice
    	rc = getAgencyInvoiceDAO().updateAgencyInvoiceCredited(agencyInvoiceId, noOfChanges, auditorId, agencyInvoiceCreditId);
		return rc;

    }

	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForAgencyAndStatus(Integer agencyId, Integer status) {
		
		List<AgencyInvoiceUser> agencyInvoices = null;
		agencyInvoices = getAgencyInvoiceDAO().getAgencyInvoiceUsersForAgencyAndStatus(agencyId, status);
		return agencyInvoices;
		
	}

	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForAgency(Integer agencyId, Integer agencyInvoiceId, 
			Integer clientId, Integer status, Date fromDate, Date toDate) {

		List<AgencyInvoiceUser> agencyInvoices = null;
		agencyInvoices = getAgencyInvoiceDAO().getAgencyInvoiceUsersForAgency(agencyId, agencyInvoiceId, clientId, status, fromDate, toDate);
		return agencyInvoices;
		
	}
	
	
	public Consultant validateSecretWord(Consultant consultant) {

		Consultant consultantX = consultantDAO.getConsultantForLogin(consultant.getAgencyId(), consultant.getUser().getLogin());
		if (consultantX == null) {
			throw new DataNotFoundException();
		}
		if (!consultant.getUser().getSecretWord().equalsIgnoreCase(consultantX.getUser().getSecretWord())) {
			throw new InvalidDetailException();
		}
		consultant.getUser().setPwdHint(consultantX.getUser().getPwdHint());
		consultant.setConsultantId(consultantX.getConsultantId());
		return consultantX;

	}

  	public int updateConsultantPwd(Integer consultantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId) {

		int rc = consultantDAO.updateConsultantPwd(consultantId, newPwd, pwdHint, noOfChanges, auditorId);
		return rc;

  	}
	
  	public int updateConsultantSecretWord(Integer consultantId, String newSecretWord, Integer noOfChanges, Integer auditorId) {

		int rc = consultantDAO.updateConsultantSecretWord(consultantId, newSecretWord, noOfChanges, auditorId);
		return rc;

  	}

    public int updateApplicantChecklistCreatedTime(Integer applicantId, Integer noOfChanges, Integer auditorId, Date checklistCreatedTime) 
    {
      int rc = applicantDAO.updateApplicantChecklistCreatedTime(applicantId, noOfChanges, auditorId, checklistCreatedTime);
      return rc;
    }
      
    public int updateApplicantSecretWord(Integer applicantId, String newSecretWord, Integer noOfChanges, Integer auditorId) {

      int rc = applicantDAO.updateApplicantSecretWord(applicantId, newSecretWord, noOfChanges, auditorId);
      return rc;
    
    }
      
  public int updateApplicantPassword(Integer applicantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId)
  {
    int rc = applicantDAO.updateApplicantPwd(applicantId, newPwd, pwdHint, noOfChanges, auditorId);
    return rc;
  }
  
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgency(Integer applicantId, Integer agencyId) {

		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForApplicantForAgency(applicantId, agencyId);
		return bookingDateUserApplicants;

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(Integer applicantId, Integer agencyId, Date fromDate, Date toDate) {

		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForApplicantForAgencyAndDateRange(applicantId, agencyId, fromDate, toDate);
		return bookingDateUserApplicants;

	}
  // NEW ->
  public List<BookingDateUserApplicant> getBookingDateUserApplicantsForAgencyAndDateRange(Integer agencyId, Date fromDate, Date toDate) 
  {
    List<BookingDateUserApplicant> bookingDateUserApplicants = null;
    bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForAgencyAndDateRange(agencyId, fromDate, toDate);
    return bookingDateUserApplicants;
  }
  public List<JobProfileUser> getJobProfileUsersForNhsAssignment(Integer clientId, String nhsAssignment) 
  {
    List<JobProfileUser> jobProfileUsers = null;
    jobProfileUsers = getJobProfileDAO().getJobProfileUsersForNhsAssignment(clientId, nhsAssignment);
    return jobProfileUsers;

  }
  // NEW <-
	public List<Grade> getGradesForJobProfile(Integer jobProfileId) {

		List<Grade> grades = null;
		grades = getGradeDAO().getGradesForJobProfile(jobProfileId);
		return grades;

	}

	public List<ClientAgencyJobProfileUser> getClientAgencyJobProfileUsersForJobProfileAndAgency(Integer jobProfileId, Integer agencyId) {

		List<ClientAgencyJobProfileUser> clientAgencyJobProfileUsers = null;
		clientAgencyJobProfileUsers = getClientAgencyJobProfileDAO().getClientAgencyJobProfileUsersForJobProfileAndAgency(jobProfileId, agencyId);
		return clientAgencyJobProfileUsers;

	}

	public List<ClientAgencyJobProfileGrade> getClientAgencyJobProfileGradesForJobProfileAndAgency(Integer jobProfileId, Integer agencyId) {

		List<ClientAgencyJobProfileGrade> clientAgencyJobProfileGrades = null;
		clientAgencyJobProfileGrades = getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGradesForJobProfileAndAgency(jobProfileId, agencyId);
		return clientAgencyJobProfileGrades;

	}

  public List<ClientAgencyJobProfileGradeUser> getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(Integer clientId, Integer agencyId, Integer jobProfileId)
  {
    List<ClientAgencyJobProfileGradeUser> clientAgencyJobProfileGradeUsers = null;
    clientAgencyJobProfileGradeUsers = getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGradeUsersForClientAgencyJobProfile(clientId, agencyId, jobProfileId);
    return clientAgencyJobProfileGradeUsers;
  }
  
	public ClientAgencyJobProfileGradeUser getClientAgencyJobProfileGradeUser(Integer clientAgencyJobProfileGradeId) {

		ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser = null;
		clientAgencyJobProfileGradeUser = getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGradeUser(clientAgencyJobProfileGradeId);
		return clientAgencyJobProfileGradeUser;

	}

	public BookingDateUserApplicant getBookingDateUserApplicantForAgencyAndBookingDate(Integer agencyId, Integer bookingDateId) {

		BookingDateUserApplicant bookingDateUserApplicant = null;
		bookingDateUserApplicant = getBookingDateDAO().getBookingDateUserApplicantForAgencyAndBookingDate(agencyId, bookingDateId);
		return bookingDateUserApplicant;

	}

	public AgencyInvoiceCredit getAgencyInvoiceCredit(Integer agencyInvoiceCreditId) {
		
		AgencyInvoiceCredit agencyInvoiceCredit = null;
		agencyInvoiceCredit = getAgencyInvoiceCreditDAO().getAgencyInvoiceCredit(agencyInvoiceCreditId);
		return agencyInvoiceCredit;

	}
	
  public List<Unavailable> getActiveUnavailablesInDateRange(Integer agencyId, java.util.Date fromDate, java.util.Date toDate)
  {
    List<Unavailable> unavailables = null;
    unavailables = unavailableDAO.getActiveUnavailablesInDateRange(agencyId, fromDate, toDate);
    return unavailables;
  }
  
  public List<Unavailable> getUnavailablesForApplicantInDateRange(Integer agencyId, Integer applicantId, boolean showOnlyActive, java.util.Date fromDate, java.util.Date toDate)
  {
    List<Unavailable> unavailables = null;
    unavailables = unavailableDAO.getUnavailablesForApplicantInDateRange(agencyId, applicantId, showOnlyActive, fromDate, toDate);
    return unavailables;
  }

  public int insertUnavailable(Unavailable unavailable, Integer auditorId)
  {
    int rc = unavailableDAO.insertUnavailable(unavailable, auditorId);
    return rc;
  }

  public int updateUnavailable(Unavailable unavailable, Integer auditorId)
  {
    int rc = unavailableDAO.updateUnavailable(unavailable, auditorId);
    return rc;
  }

  public BookingDate getBookingDate(Integer bookingDateId) {

    BookingDate bookingDate = null;
    bookingDate = getBookingDateDAO().getBookingDate(bookingDateId);
    return bookingDate;

  }

  public int insertNhsBooking(NhsBooking nhsBooking, Integer auditorId)
  {
    int rc = getNhsBookingDAO().insertNhsBooking(nhsBooking, auditorId);
    return rc;
  }

  public int updateNhsBooking(NhsBooking nhsBooking, Integer auditorId)
  {
    int rc = getNhsBookingDAO().updateNhsBooking(nhsBooking, auditorId);
    return rc;
  }

  public int updateNhsBookingApplicantNotificationSent(NhsBooking nhsBooking, Integer auditorId)
  {
    int rc = getNhsBookingDAO().updateNhsBookingApplicantNotificationSent(nhsBooking, auditorId);
    return rc;
  }

  public int updateNhsBookingCommentAndValue(NhsBooking nhsBooking, Integer auditorId)
  {
    int rc = getNhsBookingDAO().updateNhsBookingCommentAndValue(nhsBooking, auditorId);
    return rc;
  }

  public int updateNhsBookingSubcontractInvoiceId(NhsBooking nhsBooking, Integer auditorId)
  {
    int rc = getNhsBookingDAO().updateNhsBookingSubcontractInvoiceId(nhsBooking, auditorId);
    return rc;
  }

  public int deleteNhsBooking(NhsBooking nhsBooking, Integer auditorId)
  {
    int rc = getNhsBookingDAO().deleteNhsBooking(nhsBooking, auditorId);
    return rc;
  }

  public NhsBooking getActiveNhsBookingForBankReqNum(Integer agencyId, String bankReqNum)
  {
    NhsBooking nhsBooking = getNhsBookingDAO().getActiveNhsBookingForBankReqNum(agencyId, bankReqNum);
    return nhsBooking;
  }

  public NhsBooking getNhsBooking(Integer nhsBookingId)
  {
    NhsBooking nhsBooking = getNhsBookingDAO().getNhsBooking(nhsBookingId);
    return nhsBooking;
  }

  public NhsBookingUser getNhsBookingUser(Integer nhsBookingId)
  {
    NhsBookingUser nhsBookingUser = getNhsBookingDAO().getNhsBookingUser(nhsBookingId);
    return nhsBookingUser;
  }

  public List<NhsBookingUser> getNhsBookingUsersReadyToBook(Integer agencyId)
  {
    List<NhsBookingUser> listNhsBookingUsersReadyToBook = null;
    listNhsBookingUsersReadyToBook = getNhsBookingDAO().getNhsBookingUsersReadyToBook(agencyId);
    return listNhsBookingUsersReadyToBook;
  }

  public List<NhsBookingUser> getActiveNhsBookingUsersForDateRange(Integer agencyId, Date startDate, Date endDate)
  {
    List<NhsBookingUser> listNhsBookingUsers = null;
    listNhsBookingUsers = getNhsBookingDAO().getActiveNhsBookingUsersForDateRange(agencyId, startDate, endDate);
    return listNhsBookingUsers;
  }

  public List<NhsBookingUser> getNhsBookingUsersForAgencyDateRange(Integer agencyId, Date startDate, Date endDate, String filter)
  {
    List<NhsBookingUser> listNhsBookingUsers = null;
    listNhsBookingUsers = getNhsBookingDAO().getNhsBookingUsersForAgencyDateRange(agencyId, startDate, endDate, filter);
    return listNhsBookingUsers;
  }

  public List<NhsBookingUser> getNhsBookingUsersForAgencyDateRangeNhsBookingReportGroup(Integer agencyId, Date startDate, Date endDate, NhsBookingReportGroup nhsBookingReportGroup)
  {
    List<NhsBookingUser> listNhsBookingUsers = null;
    listNhsBookingUsers = getNhsBookingDAO().getNhsBookingUsersForAgencyDateRangeNhsBookingReportGroup(agencyId, startDate, endDate, nhsBookingReportGroup);
    return listNhsBookingUsers;
  }

  public List<NhsBookingUser> getNhsBookingUsersForSubcontractInvoice(Integer agencyId, Integer subcontractInvoiceId) 
  {
    List<NhsBookingUser> listNhsBookingUsers = null;
    listNhsBookingUsers = getNhsBookingDAO().getNhsBookingUsersForSubcontractInvoice(agencyId, subcontractInvoiceId);
    return listNhsBookingUsers;
  }
  
  public List<NhsBookingUser> getNhsBookingUsersReadyToBookForBookingGroup(Integer agencyId, Integer siteId, Integer locationId, Integer jobProfileId, Integer bookingGroupId)
  {
    List<NhsBookingUser> listNhsBookingUsersReadyToBook = null;
    listNhsBookingUsersReadyToBook = getNhsBookingDAO().getNhsBookingUsersReadyToBookForBookingGroup(agencyId, siteId, locationId, jobProfileId, bookingGroupId);
    return listNhsBookingUsersReadyToBook;
  }

  public List<NhsBookingGroup> getNhsBookingGroupsReadyToBook(Integer agencyId)
  {
    List<NhsBookingGroup> listNhsBookingGroupsReadyToBook = null;
    listNhsBookingGroupsReadyToBook = getNhsBookingDAO().getNhsBookingGroupsReadyToBook(agencyId);
    return listNhsBookingGroupsReadyToBook;
  }

  public List<NhsBookingUser> getPickedNhsBookingUsersReadyToBook(Integer agencyId, String nhsBookingIds)
  {
    List<NhsBookingUser> listNhsBookingUsersReadyToBook = null;
    listNhsBookingUsersReadyToBook = getNhsBookingDAO().getPickedNhsBookingUsersReadyToBook(agencyId, nhsBookingIds);
    return listNhsBookingUsersReadyToBook;
  }

  public int insertSubcontractCreditNote(SubcontractInvoice subcontractInvoice, Integer auditorId)
  {
    int rc = subcontractInvoiceDAO.insertSubcontractCreditNote(subcontractInvoice, auditorId);
    return rc;
  }

  public int insertSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId)
  {
    int rc = subcontractInvoiceDAO.insertSubcontractInvoice(subcontractInvoice, auditorId);
    return rc;
  }

  public int updateSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId)
  {
    int rc = subcontractInvoiceDAO.updateSubcontractInvoice(subcontractInvoice, auditorId);
    return rc;
  }

  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersInList(Integer agencyId, String subcontractInvoiceIdList)
  {
    List<SubcontractInvoiceUser> listSubcontractInvoiceUsers = null;
    listSubcontractInvoiceUsers = subcontractInvoiceDAO.getSubcontractInvoiceUsersInList(agencyId, subcontractInvoiceIdList);
    return listSubcontractInvoiceUsers;
  }

  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersForRemittanceAdvice(Integer agencyId, String remittanceAdvice) 
  {
    List<SubcontractInvoiceUser> listSubcontractInvoiceUsers = null;
    listSubcontractInvoiceUsers = subcontractInvoiceDAO.getSubcontractInvoiceUsersForRemittanceAdvice(agencyId, remittanceAdvice);
    return listSubcontractInvoiceUsers;
  }

  public int updateSubcontractInvoiceValue(SubcontractInvoice subcontractInvoice, Integer auditorId)
  {
    int rc = subcontractInvoiceDAO.updateSubcontractInvoiceValue(subcontractInvoice, auditorId);
    return rc;
  }

  public int updateSubcontractInvoiceSentDate(SubcontractInvoice subcontractInvoice, Integer auditorId)
  {
    int rc = subcontractInvoiceDAO.updateSubcontractInvoiceSentDate(subcontractInvoice, auditorId);
    return rc;
  }

  public int updateSubcontractInvoicePaidDate(SubcontractInvoice subcontractInvoice, Integer auditorId)
  {
    int rc = subcontractInvoiceDAO.updateSubcontractInvoicePaidDate(subcontractInvoice, auditorId);
    return rc;
  }

  public int updateSubcontractInvoiceRelatedSubcontractInvoiceId(SubcontractInvoice subcontractInvoice, Integer auditorId)
  {
    int rc = subcontractInvoiceDAO.updateSubcontractInvoiceRelatedSubcontractInvoiceId(subcontractInvoice, auditorId);
    return rc;
  }

  public int deleteSubcontractInvoice(SubcontractInvoice subcontractInvoice, Integer auditorId)
  {
    int rc = subcontractInvoiceDAO.deleteSubcontractInvoice(subcontractInvoice, auditorId);
    return rc;
  }

  public SubcontractInvoice getSubcontractInvoice(Integer subcontractInvoiceId)
  {
    SubcontractInvoice subcontractInvoice = subcontractInvoiceDAO.getSubcontractInvoice(subcontractInvoiceId);
    return subcontractInvoice;
  }

  public SubcontractInvoiceUser getSubcontractInvoiceUser(Integer subcontractInvoiceId)
  {
    SubcontractInvoiceUser subcontractInvoiceUser = subcontractInvoiceDAO.getSubcontractInvoiceUser(subcontractInvoiceId);
    return subcontractInvoiceUser;
  }

  public SubcontractInvoiceItemUser getSubcontractInvoiceItemUser(Integer subcontractInvoiceItemId)
  {
    SubcontractInvoiceItemUser subcontractInvoiceItemUser = subcontractInvoiceItemDAO.getSubcontractInvoiceItemUser(subcontractInvoiceItemId);
    return subcontractInvoiceItemUser;
  }

  public int deleteSubcontractInvoiceItem(SubcontractInvoiceItem subcontractInvoiceItem, Integer auditorId)
  {
    int rc = subcontractInvoiceItemDAO.deleteSubcontractInvoiceItem(subcontractInvoiceItem, auditorId);
    return rc;
  }

  public int insertSubcontractInvoiceItem(SubcontractInvoiceItem subcontractInvoiceItem, Integer auditorId)
  {
    int rc = subcontractInvoiceItemDAO.insertSubcontractInvoiceItem(subcontractInvoiceItem, auditorId);
    return rc;
  }

  public List<SubcontractInvoiceItem> getSubcontractInvoiceItemsForSubcontractInvoice(Integer subcontractInvoiceId) 
  {
    List<SubcontractInvoiceItem> listSubcontractInvoiceItem = null;
    listSubcontractInvoiceItem = subcontractInvoiceItemDAO.getSubcontractInvoiceItemsForSubcontractInvoice(subcontractInvoiceId);
    return listSubcontractInvoiceItem;
  }
  
  public List<SubcontractInvoiceItemUser> getSubcontractInvoiceItemUsersForSubcontractInvoice(Integer subcontractInvoiceId) 
  {
    List<SubcontractInvoiceItemUser> listSubcontractInvoiceItemUser = null;
    listSubcontractInvoiceItemUser = subcontractInvoiceItemDAO.getSubcontractInvoiceItemUsersForSubcontractInvoice(subcontractInvoiceId);
    return listSubcontractInvoiceItemUser;
  }
  
  public List<SubcontractInvoiceUser> getSubcontractInvoiceUsersForAgency(Integer fromAgencyId, Integer subcontractInvoiceId, 
      Integer clientId, Integer siteId, Integer locationId, Integer jobProfileId, Integer status, Date fromDate, Date toDate) 
  {
    List<SubcontractInvoiceUser> subcontractInvoices = null;
    subcontractInvoices = subcontractInvoiceDAO.getSubcontractInvoiceUsersForAgency(fromAgencyId, subcontractInvoiceId, clientId, siteId, locationId, jobProfileId, status, fromDate, toDate);
    return subcontractInvoices;
  }
  
  public int updateSubcontractInvoiceItem(SubcontractInvoiceItem subcontractInvoiceItem, Integer auditorId)
  {
    int rc = subcontractInvoiceItemDAO.updateSubcontractInvoiceItem(subcontractInvoiceItem, auditorId);
    return rc;
  }

  public List<SubcontractInvoiceItemHistory> getSubcontractInvoiceItemHistoryForBankReqNum(String bankReqNum) 
  {
    List<SubcontractInvoiceItemHistory> listSubcontractInvoiceItemHistory = null;
    listSubcontractInvoiceItemHistory = subcontractInvoiceItemDAO.getSubcontractInvoiceItemHistoryForBankReqNum(bankReqNum);
    return listSubcontractInvoiceItemHistory;
  }
  
  public EmailAction getEmailAction(Integer emailActionId) 
  {
    EmailAction emailAction = null;
    emailAction = getEmailActionDAO().getEmailAction(emailActionId);
    return emailAction;
  }

}


