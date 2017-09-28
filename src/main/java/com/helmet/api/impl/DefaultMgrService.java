package com.helmet.api.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.struts.util.MessageResources;

import com.helmet.api.MgrService;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.application.FileHandler;
import com.helmet.application.MailHandler;
import com.helmet.application.agy.AgyUtilities;
import com.helmet.bean.Agency;
import com.helmet.bean.AgencyInvoice;
import com.helmet.bean.AgencyInvoiceUser;
import com.helmet.bean.AgencyInvoiceUserEntity;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingDateUserApplicantEntity;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeApplicant;
import com.helmet.bean.BookingGradeApplicantDate;
import com.helmet.bean.BookingGradeApplicantDateUser;
import com.helmet.bean.BookingGradeApplicantDateUserEntity;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.BookingGradeUser;
import com.helmet.bean.BookingUser;
import com.helmet.bean.BookingUserEntityMgr;
import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgency;
import com.helmet.bean.ClientAgencyJobProfile;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientAgencyJobProfileUser;
import com.helmet.bean.ClientReEnterPwdUser;
import com.helmet.bean.DressCode;
import com.helmet.bean.Expense;
import com.helmet.bean.Grade;
import com.helmet.bean.IntValue;
import com.helmet.bean.Invoice;
import com.helmet.bean.InvoiceAgency;
import com.helmet.bean.InvoiceEntity;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.LocationJobProfileUser;
import com.helmet.bean.LocationManagerJobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.Manager;
import com.helmet.bean.MgrAccess;
import com.helmet.bean.SiteUser;
import com.helmet.bean.StatusCount;
import com.helmet.persistence.BookingDateExpenseDAO;
import com.helmet.persistence.BookingDateHourDAO;
import com.helmet.persistence.ClientReEnterPwdDAO;
import com.helmet.persistence.Utilities;

public class DefaultMgrService extends DefaultCommonService implements MgrService {
	
	private String agyBookingOpenFromEmailAddress = "helmet@durley.net";
    private String agyBookingOpenSubject = "New Booking";	
	private String agyBookingOpenLink = "/agy/bookingGradeViewSummary.do?bookingGrade.bookingGradeId=";
	
	private ClientReEnterPwdDAO clientReEnterPwdDAO;
	
	private BookingDateHourDAO bookingDateHourDAO;
	
	private BookingDateExpenseDAO bookingDateExpenseDAO;

	public void setAgyBookingOpenFromEmailAddress(String agyBookingOpenFromEmailAddress) {
		this.agyBookingOpenFromEmailAddress = agyBookingOpenFromEmailAddress;
	}

	public void setAgyBookingOpenSubject(String agyBookingOpenSubject) {
		this.agyBookingOpenSubject = agyBookingOpenSubject;
	}

	public void setAgyBookingOpenLink(String agyBookingOpenLink) {
		this.agyBookingOpenLink = agyBookingOpenLink;
	}

	public void setClientReEnterPwdDAO(ClientReEnterPwdDAO clientReEnterPwdDAO) {
		this.clientReEnterPwdDAO = clientReEnterPwdDAO;
	}

	public void setBookingDateHourDAO(BookingDateHourDAO bookingDateHourDAO) {
		this.bookingDateHourDAO = bookingDateHourDAO;
	}

	public void setBookingDateExpenseDAO(BookingDateExpenseDAO bookingDateExpenseDAO) {
		this.bookingDateExpenseDAO = bookingDateExpenseDAO;
	}

	public Manager validateLogin(Manager manager) {

		Manager managerX = getManagerDAO().getManagerForLogin(manager.getClientId(), manager.getUser().getLogin());
		if (managerX == null) {
			throw new DataNotFoundException();
		}
		String encryptedPwd = Utilities.encryptPassword(manager.getUser().getPwd());
		if (!encryptedPwd.equals(managerX.getUser().getPwd())) {
			manager.getUser().setPwdHint(managerX.getUser().getPwdHint());
			throw new InvalidDetailException();
		}
		return managerX;

	}

	public Manager validateSecretWord(Manager manager) {

		Manager managerX = getManagerDAO().getManagerForLogin(manager.getClientId(), manager.getUser().getLogin());
		if (managerX == null) {
			throw new DataNotFoundException();
		}
		if (!manager.getUser().getSecretWord().equalsIgnoreCase(managerX.getUser().getSecretWord())) {
			throw new InvalidDetailException();
		}
		manager.getUser().setPwdHint(managerX.getUser().getPwdHint());
		manager.setManagerId(managerX.getManagerId());
		return managerX;
	
	}

	public int updateManagerShowPageHelp(Manager manager, Integer auditorId) {
		
		int rc = getManagerDAO().updateManagerShowPageHelp(manager, auditorId);
		return rc;

	}

  	public List<LocationManagerJobProfileUser> getLocationManagerJobProfileUsersForManager(Integer managerId) {

		List<LocationManagerJobProfileUser> locationManagerJobProfileUsers = null;
		locationManagerJobProfileUsers = getLocationManagerJobProfileDAO().getLocationManagerJobProfileUsersForManager(managerId);
		return locationManagerJobProfileUsers;

	}

	public List<SiteUser> getSiteUsersForManager(Integer siteId) {

		List<SiteUser> sites = null;
		sites = getSiteDAO().getSiteUsersForManager(siteId);
		return sites;

	}

	public List<LocationUser> getLocationUsersForManager(Integer managerId) {

		List<LocationUser> locationUsers = null;
		locationUsers = getLocationDAO().getLocationUsersForManager(managerId);
		return locationUsers;

	}

	public List<LocationUser> getLocationUsersForManagerForSite(Integer managerId, Integer siteId) {

		List<LocationUser> locationUsers = null;
		locationUsers = getLocationDAO().getLocationUsersForManagerForSite(managerId, siteId);
		return locationUsers;

	}

	public JobProfileUser getJobProfileUser(Integer jobProfileId) {

		JobProfileUser jobProfileUser = null;
    	jobProfileUser = getJobProfileDAO().getJobProfileUser(jobProfileId);
		return jobProfileUser;

	}

	public List<JobProfileUser> getJobProfileUsersForManagerForLocation(Integer managerId, Integer locationId) {

		List<JobProfileUser> jobProfileUsers = null;
		jobProfileUsers = getJobProfileDAO().getJobProfileUsersForManagerForLocation(managerId, locationId);
		return jobProfileUsers;

	}

	public List<JobProfileUser> getJobProfileUsersForManagerForSite(Integer managerId, Integer siteId) {

		List<JobProfileUser> jobProfileUsers = null;
		jobProfileUsers = getJobProfileDAO().getJobProfileUsersForManagerForSite(managerId, siteId);
		return jobProfileUsers;

	}

	public List<JobProfileUser> getJobProfileUsersForManager(Integer managerId) {

		List<JobProfileUser> jobProfileUsers = null;
		jobProfileUsers = getJobProfileDAO().getJobProfileUsersForManager(managerId);
		return jobProfileUsers;

	}

	public List<Grade> getGradesForJobProfile(Integer jobProfileId) {

		List<Grade> grades = null;
		grades = getGradeDAO().getGradesForJobProfile(jobProfileId);
		return grades;

	}

	public Grade getGrade(Integer gradeId) {

		Grade grade = null;
    	grade = getGradeDAO().getGrade(gradeId);
		return grade;

	}

	public List<ClientAgencyJobProfileUser> getClientAgencyJobProfileUsersForJobProfile(Integer jobProfileId) {

		List<ClientAgencyJobProfileUser> clientAgencyJobProfileUsers = null;
		clientAgencyJobProfileUsers = getClientAgencyJobProfileDAO().getClientAgencyJobProfileUsersForJobProfile(jobProfileId);
		return clientAgencyJobProfileUsers;

	}

	public List<ClientAgencyJobProfileGrade> getClientAgencyJobProfileGradesForJobProfile(Integer jobProfileId) {

		List<ClientAgencyJobProfileGrade> clientAgencyJobProfileGrades = null;
		clientAgencyJobProfileGrades = getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGradesForJobProfile(jobProfileId);
		return clientAgencyJobProfileGrades;

	}

	public ClientAgencyJobProfileGradeUser getClientAgencyJobProfileGradeUser(Integer clientAgencyJobProfileGradeId) {

		ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser = null;
		clientAgencyJobProfileGradeUser = getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGradeUser(clientAgencyJobProfileGradeId);
		return clientAgencyJobProfileGradeUser;

	}

	public List<Expense> getExpensesForLocation(Integer locationId) {

		List<Expense> expenses = null;
		expenses = getExpenseDAO().getExpensesForLocation(locationId, true);
		return expenses;

	}

	public Expense getExpense(Integer expenseId) {
		
		Expense expense = null;
    	expense = getExpenseDAO().getExpense(expenseId);
		return expense;

	}

	public int deleteBooking(Integer bookingId, Integer noOfChanges, Integer auditorId) {

        int d = getBookingDateDAO().deleteBookingDatesForBooking(bookingId, auditorId);
        int g = getBookingGradeDAO().deleteBookingGradesForBooking(bookingId, auditorId);
        int e = getBookingExpenseDAO().deleteBookingExpensesForBooking(bookingId, auditorId);
		int b = getBookingDAO().deleteBooking(bookingId, noOfChanges, auditorId);
		return b;
	}

	public List<BookingUser> getBookingUsersForManager(Integer managerId, Boolean singleCandidate) {
	
		return getBookingUsersForManager(managerId, singleCandidate, null, null, null);
	}

	public List<BookingUser> getBookingUsersForManager(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId) {

		List<BookingUser> bookingUsers = null;
		bookingUsers = getBookingDAO().getBookingUsersForManager(managerId, singleCandidate, true, siteId, locationId, jobProfileId);
		return bookingUsers;

	}

	public List<BookingUser> getBookingUsersForManagerAndBooking(Integer managerId, Integer bookingId) {
		
		List<BookingUser> bookingUsers = null;
		bookingUsers = getBookingDAO().getBookingUsersForManagerAndBooking(managerId, bookingId);
		return bookingUsers;

	}

	public List<BookingUser> getBookingUsersForManagerAndStatus(Integer managerId, Integer bookingStatus, Boolean singleCandidate) {
		
		return getBookingUsersForManagerAndStatus(managerId, bookingStatus, singleCandidate, null, null, null);
	}
		
	public List<BookingUser> getBookingUsersForManagerAndStatus(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId) {

		List<BookingUser> bookingUsers = null;
		bookingUsers = getBookingDAO().getBookingUsersForManagerAndStatus(managerId, bookingStatus, singleCandidate, siteId, locationId, jobProfileId);
		return bookingUsers;
	
	}

	public List<BookingUser> getBookingUsersForManagerAndStatusAndDateRange(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Date fromDate, Date toDate) {
		
		return getBookingUsersForManagerAndStatusAndDateRange(managerId, bookingStatus, singleCandidate, null, null, null, fromDate, toDate);

	}

	public List<BookingUser> getBookingUsersForManagerAndStatusAndDateRange(Integer managerId, Integer bookingStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {

		List<BookingUser> bookingUsers = null;
		bookingUsers = getBookingDAO().getBookingUsersForManagerAndStatusAndDateRange(managerId, bookingStatus, singleCandidate, siteId, locationId, jobProfileId, fromDate, toDate);
		return bookingUsers;
		
	}
	
	public List<BookingUser> getBookingUsersForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {
	
		List<BookingUser> bookingUsers = null;
		bookingUsers = getBookingDAO().getBookingUsersForManagerAndDateRange(managerId, singleCandidate, siteId, locationId, jobProfileId, fromDate, toDate);
		return bookingUsers;
	
	}
	
	public List<BookingUser> getBookingUsersForManagerAndWorkedStatus(Integer managerId, Integer workedStatus) {

		List<BookingUser> bookingUsers = null;
		bookingUsers = getBookingDAO().getBookingUsersForManagerAndWorkedStatus(managerId, workedStatus);
		return bookingUsers;
	
	}
	
	public List<BookingUser> getBookingUsersForManagerAndBookingGradeApplicantDateStatus(Integer managerId, Integer bookingGradeApplicantDateStatus) {

		List<BookingUser> bookingUsers = null;
		bookingUsers = getBookingDAO().getBookingUsersForManagerAndBookingGradeApplicantDateStatus(managerId, bookingGradeApplicantDateStatus);
		return bookingUsers;
	
	}
	
	public BookingUser getBookingUser(Integer bookingId) {
		
		BookingUser bookingUser = null;
    	bookingUser = getBookingDAO().getBookingUser(bookingId);
		return bookingUser;

	}

	public BookingUserEntityMgr getBookingUserSummary(Integer bookingId, Integer  managerId) {
		
		BookingUserEntityMgr bookingUserSummary = null;
		bookingUserSummary = getBookingDAO().getBookingUserEntityMgr(bookingId, managerId);
        if (bookingUserSummary != null) {
    		// only fill the required bits
        	bookingUserSummary.setBookingExpenses(getBookingExpenseDAO().getBookingExpensesForBooking(bookingId, true));
        }
		return bookingUserSummary;

	}

	public BookingUserEntityMgr getBookingUserShifts(Integer bookingId, Integer  managerId) {
		
		BookingUserEntityMgr bookingUserShifts = null;
		bookingUserShifts = getBookingDAO().getBookingUserEntityMgr(bookingId, managerId);
        if (bookingUserShifts != null) {
			// only fill the required bits
	    	bookingUserShifts.setBookingDates(getBookingDateDAO().getBookingDatesForBooking(bookingId, true));
	    	
//	    	// TODO - to go - replaced by select below, below
//	    	bookingUserShifts.setBookingGradeApplicantDateUsers(getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingFilled(bookingId, true));

	    	bookingUserShifts.setBookingDateUserApplicants(getBookingDateDAO().getBookingDateUserApplicantsForManagerAndBooking(managerId, bookingId));
        
        }
	    return bookingUserShifts;

	}

	public BookingUserEntityMgr getBookingUserApplicants(Integer bookingId, Integer  managerId) {
		
		BookingUserEntityMgr bookingUserApplicants = null;
		bookingUserApplicants = getBookingDAO().getBookingUserEntityMgr(bookingId, managerId);
        if (bookingUserApplicants != null) {
			// only fill the required bits
	    	bookingUserApplicants.setBookingGradeApplicantUsers(getBookingGradeApplicantDAO().getBookingGradeApplicantUsersForBookingAndClient(bookingId, bookingUserApplicants.getClientId(), true));
        }
		return bookingUserApplicants;

	}

	public BookingUserEntityMgr getBookingUserGrades(Integer bookingId, Integer  managerId) {
		
		BookingUserEntityMgr bookingUserGrades = null;
		bookingUserGrades = getBookingDAO().getBookingUserEntityMgr(bookingId, managerId);
        if (bookingUserGrades != null) {
            // only fill the required bits
    	    bookingUserGrades.setBookingGradeUsers(getBookingGradeDAO().getBookingGradeUsersForBooking(bookingId, true));
        }
    	return bookingUserGrades;

	}

	public BookingUserEntityMgr getBookingUserEntityMgr(Integer bookingId, Integer  managerId) {
		
		BookingUserEntityMgr bookingUserEntityMgr = null;
    	bookingUserEntityMgr = getBookingDAO().getBookingUserEntityMgr(bookingId, managerId);
        if (bookingUserEntityMgr != null) {
	    	bookingUserEntityMgr.setBookingDates(getBookingDateDAO().getBookingDatesForBooking(bookingId, true));
	    	bookingUserEntityMgr.setBookingExpenses(getBookingExpenseDAO().getBookingExpensesForBooking(bookingId, true));
	    	bookingUserEntityMgr.setBookingGradeUsers(getBookingGradeDAO().getBookingGradeUsersForBooking(bookingId, true));
	    	bookingUserEntityMgr.setBookingGradeApplicantUsers(getBookingGradeApplicantDAO().getBookingGradeApplicantUsersForBookingAndClient(bookingId, bookingUserEntityMgr.getClientId(), true));
//	    	bookingUserEntityMgr.setBookingGradeApplicantDateUsers(getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingFilled(bookingId, true));
	    	bookingUserEntityMgr.setBookingDateUserApplicants(getBookingDateDAO().getBookingDateUserApplicantsForManagerAndBooking(managerId, bookingId));
        }
    	return bookingUserEntityMgr;

	}

	public List<DressCode> getDressCodesForLocation(Integer locationId) {

		List<DressCode> dressCodes = null;
		dressCodes = getDressCodeDAO().getDressCodesForLocation(locationId, true);
		return dressCodes;
	
	}

	public DressCode getDressCode(Integer dressCodeId) {
	
		DressCode dressCode = null;
    	dressCode = getDressCodeDAO().getDressCode(dressCodeId);
		return dressCode;

	}

	public List<MgrAccess> getActiveMgrAccessesForManager(Integer managerId) {

		List<MgrAccess> mgrAccesses = null;
		mgrAccesses = getMgrAccessDAO().getActiveMgrAccessesForManager(managerId);
		return mgrAccesses;

	}

	public int updateBookingGradeOpen(Integer bookingGradeId, Integer noOfChanges, Integer auditorId) {

		// update status
        int rc = getBookingGradeDAO().updateBookingGradeStatus(bookingGradeId, noOfChanges, auditorId, BookingGrade.BOOKINGGRADE_STATUS_OPEN);
		// send email
        //
        // Only send an email if the agency has an email address for the jobprofile
        //
        
        BookingGrade bookingGrade = getBookingGradeDAO().getBookingGrade(bookingGradeId);
        ClientAgencyJobProfileGrade clientAgencyJobProfileGrade = getClientAgencyJobProfileGradeDAO().getClientAgencyJobProfileGrade(bookingGrade.getClientAgencyJobProfileGradeId());
        ClientAgencyJobProfile clientAgencyJobProfile = getClientAgencyJobProfileDAO().getClientAgencyJobProfile(clientAgencyJobProfileGrade.getClientAgencyJobProfileId());
        ClientAgency clientAgency = getClientAgencyDAO().getClientAgency(clientAgencyJobProfile.getClientAgencyId());
        Client client = getClientDAO().getClient(clientAgency.getClientId());
        
        String sendEmailAddress = clientAgencyJobProfile.getSendEmailAddress(); 
        if (sendEmailAddress != null && !"".equals(sendEmailAddress)) {

        	BookingUserEntityMgr booking = getBookingUserEntityMgr(bookingGrade.getBookingId(), auditorId);
        	
        	// TODO need to check for NULL object ...
        	// TODO need to check for NULL object ...
        	// TODO need to check for NULL object ...
        	// TODO need to check for NULL object ...
        	// TODO need to check for NULL object ...
        	// TODO need to check for NULL object ...
        	// TODO need to check for NULL object ...
        	// TODO need to check for NULL object ...
        	// TODO need to check for NULL object ...
        	// TODO need to check for NULL object ...
        	

    		SimpleDateFormat ldf = new SimpleDateFormat("EEE, dd MMM yyyy");
    		SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
    		DecimalFormat df = new DecimalFormat("#,##0.00");
    		DecimalFormat dfBookingId = new DecimalFormat("#000");
        	
        	// needs lots of work !!!
        	String from = agyBookingOpenFromEmailAddress;
	        String to = sendEmailAddress;
	        String subject = agyBookingOpenSubject + " - " + dfBookingId.format(bookingGrade.getBookingId()) + " - " + booking.getJobProfileName() + " - " + booking.getClientName();

	        StringBuffer content = new StringBuffer();
	        StringBuffer htmlContent = new StringBuffer();

	        htmlContent.append("<html><head><title>MMJ Email - New Booking - " + dfBookingId.format(bookingGrade.getBookingId()) + "</title>");
	        
	        htmlContent.append("<style>");
	        String cssFileName = "/agy/site.css";

//	        com.helmet.application.Utilities.suckInFile(cssFileName, htmlContent);

	        htmlContent.append("body {color:#000; background:#fff; font:70% verdana,tahoma,helvetica,sans-serif; margin:10px; padding:0px; }\n");
       		htmlContent.append("img {border:0; }\n");
			htmlContent.append("h1 {font:100% verdana,tahoma,helvetica,sans-serif; font-weight: bold; }\n");
			htmlContent.append("h2 {font:100% verdana,tahoma,helvetica,sans-serif; font-weight: bold; }\n");
			htmlContent.append("pre {color:#000; font:100% verdana,tahoma,helvetica,sans-serif; padding:0px; }\n");
			htmlContent.append("a         { font:100% verdana,tahoma,helvetica,sans-serif; color:#cc0000; text-decoration:none; }\n");
	        htmlContent.append("a:link    { font:100% verdana,tahoma,helvetica,sans-serif; color:#cc0000; text-decoration:none; }\n");
	        htmlContent.append("a:visited { font:100% verdana,tahoma,helvetica,sans-serif; color:#cc0000; text-decoration:none; }\n");
	        htmlContent.append("a:active  { font:100% verdana,tahoma,helvetica,sans-serif; color:#cc0000; text-decoration:none; }\n");
	        htmlContent.append("a:hover   { font:100% verdana,tahoma,helvetica,sans-serif; color:#ff0000; text-decoration:underline; }\n");
	        htmlContent.append(".label { font:100% verdana,tahoma,helvetica,sans-serif; font-weight: bold; }\n");
	        htmlContent.append("table.simple thead tr th { background-color: #f0f0f0; }\n");
	        htmlContent.append("table.simple tr th { background-color: #f0f0f0; }\n");
	        htmlContent.append("table.simple { border: 1px solid #e0e0e0; border-collapse: collapse; }\n");
	        htmlContent.append("table.simple td { font:70% verdana,tahoma,helvetica,sans-serif; border: 1px solid #e0e0e0; padding-left:2px; padding-right:2px; }\n");
	        htmlContent.append("table.simple th { font:70% verdana,tahoma,helvetica,sans-serif; border: 1px solid #e0e0e0; padding-left:2px; padding-right:2px; font-weight: bold; }\n");
	        
	        htmlContent.append("</style>");

	        htmlContent.append("</head><body>");

	        // header start
	        
	        htmlContent.append("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
	        htmlContent.append("  <tr>");
	        htmlContent.append("    <td valign=\"top\" align=\"left\">");
	        
	        htmlContent.append("<img src=\"cid:mmjLogo\" border=\"0\">");

	        htmlContent.append("    </td>");
	    	htmlContent.append("    <td valign=\"top\" align=\"left\" width=\"100%\">");
	        htmlContent.append("      &nbsp;");
	        htmlContent.append("    </td>");
	        htmlContent.append("    <td valign=\"top\" align=\"right\">");
	        								
		    String clientLogoFilename = client.getLogoFilename();
		
		    if (clientLogoFilename == null || "".equals(clientLogoFilename)) {
		        htmlContent.append(client.getName());
		    }
		    else {
		        htmlContent.append("<img src=\"cid:clientLogo\" border=\"0\">");
		    }

	        htmlContent.append("    </td>");
	        htmlContent.append("  </tr>");
	        htmlContent.append("</table>");
	        
	        // header end
	        
	        // top divider start
	        
	        htmlContent.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
	        htmlContent.append("<tr><td height=\"1\" width=\"100%\" bgcolor=\"#e0e0e0\"></td></tr>");
	        htmlContent.append("</table>");

	        htmlContent.append("<br/>\n");	        

	        // top divider end

	        htmlContent.append("<h1>");
	        htmlContent.append("<a href=\"" + MailHandler.getInstance().getSiteUrl() + agyBookingOpenLink + bookingGrade.getBookingGradeId() + "\">");
	        htmlContent.append("New Booking - " + dfBookingId.format(bookingGrade.getBookingId()) + " - " + booking.getJobProfileName() + " - " + booking.getClientName());
	        htmlContent.append("</a>");	        
	        htmlContent.append("</h1>");
	        
	        content.append("New Booking - " + dfBookingId.format(bookingGrade.getBookingId()) + " - " + booking.getJobProfileName() + " - " + booking.getClientName());
	        content.append("\n\n");	        
	        content.append(MailHandler.getInstance().getSiteUrl() +  agyBookingOpenLink + bookingGrade.getBookingGradeId());	        
	        content.append("\n\n");	        

	        htmlContent.append("<table class=\"simple\" width=\"100%\">");

	        htmlContent.append("<tr><th align=\"left\" width=\"25%\">Job Profile</th><td align=\"left\" width=\"75%\">");	        
	        content.append("Job Profile - ");	        
	        content.append(booking.getJobProfileName() + " (" + booking.getJobFamilyCode() + "." + booking.getJobSubFamilyCode() + "." + booking.getJobProfileCode() + ")\n");	        
	        htmlContent.append(booking.getJobProfileName() + " (" + booking.getJobFamilyCode() + "." + booking.getJobSubFamilyCode() + "." + booking.getJobProfileCode() + ")");	        
	        htmlContent.append("</td></tr>");

	        // get grade
	        BookingGradeUser theBookingGradeUser = null;
        	for (BookingGradeUser bookingGradeUser: booking.getBookingGradeUsers()) {
        		if (bookingGradeUser.getBookingGradeId().equals(bookingGrade.getBookingGradeId())) {
                    theBookingGradeUser = bookingGradeUser;
        			break;
        		}
        	}
	        
	        htmlContent.append("<tr><th align=\"left\">Grade</th><td align=\"left\">");	        
	        content.append("Grade - ");	        
            if (theBookingGradeUser != null) {
    	        content.append(theBookingGradeUser.getGradeName() + "\n");
    	        htmlContent.append(theBookingGradeUser.getGradeName());
            }
	        htmlContent.append("</td></tr>");
	        
	        htmlContent.append("<tr><th align=\"left\">Single Applicant</th><td align=\"left\">");	        
	        content.append("Single Applicant - ");	        
	        content.append((booking.getSingleCandidate() ? "Yes" : "No") + "\n");
	        htmlContent.append(booking.getSingleCandidate() ? "Yes" : "No");
	        htmlContent.append("</td></tr>");
	        
	        htmlContent.append("<tr><th align=\"left\">Client</th><td align=\"left\">");	        
	        content.append("Client - ");	        
	        content.append(booking.getClientName() + "\n");	        
	        htmlContent.append(booking.getClientName() + "<br/>");	        
	        htmlContent.append("</td></tr>");

	        htmlContent.append("<tr><th align=\"left\">Site</th><td align=\"left\">");	        
	        content.append("Site - ");	        
	        content.append(booking.getSiteName() + "\n");	        
	        htmlContent.append(booking.getSiteName() + "<br/>");	        
	        htmlContent.append("</td></tr>");

	        htmlContent.append("<tr><th align=\"left\">Location</th><td align=\"left\">");	        
	        content.append("Location - ");	        
	        content.append(booking.getLocationName() + "\n");	        
	        htmlContent.append(booking.getLocationName() + "<br/>");	        
	        htmlContent.append("</td></tr>");
	        
	        htmlContent.append("<tr><th align=\"left\">Start</th><td align=\"left\">");	        
	        content.append("Start - ");	        
	        content.append(ldf.format(booking.getMinBookingDate()) + "\n");	        
	        htmlContent.append(ldf.format(booking.getMinBookingDate()) + "<br/>");	        
	        htmlContent.append("</td></tr>");

	        htmlContent.append("<tr><th align=\"left\">End</th><td align=\"left\">");	        
	        content.append("End - ");	        
	        content.append(ldf.format(booking.getMaxBookingDate()) + "\n");	        
	        htmlContent.append(ldf.format(booking.getMaxBookingDate()) + "<br/>");	        
	        htmlContent.append("</td></tr>");
	        
	        htmlContent.append("<tr><th align=\"left\">Days</th><td align=\"left\">");	        
	        content.append("Days - ");	        
	        content.append(booking.getNoOfBookingDates() + "\n");
	        htmlContent.append(booking.getNoOfBookingDates() + "<br/>");
	        htmlContent.append("</td></tr>");

	        // shift
	        htmlContent.append("<tr><th align=\"left\">Shift</th><td align=\"left\">");	        
	        content.append("Shift - ");	        
	        if (booking.getShiftId() != null) {
		        content.append(booking.getShiftName() + "\n");
		        htmlContent.append(booking.getShiftName());
	        }
	        else {
		        content.append("Varied" + "\n");
		        htmlContent.append("Varied" + "<br/>");
	        }
	        htmlContent.append("</td></tr>");

	        // total hours
	        htmlContent.append("<tr><th align=\"left\">Total Hours</th><td align=\"left\">");	        
	        content.append("Total Hours - ");	        
	        content.append(booking.getNoOfHours() + "\n");	        
	        htmlContent.append(booking.getNoOfHours() + "<br/>");	        
	        htmlContent.append("</td></tr>");

	        // auto fill
	        htmlContent.append("<tr><th align=\"left\">Auto Fill</th><td align=\"left\">");	        
	        content.append("Auto Fill - ");	        
        	content.append((booking.getAutoFill() ? "Yes" : "No") + "\n");	        
        	htmlContent.append(booking.getAutoFill() ? "Yes" : "No");	        
	        htmlContent.append("</td></tr>");

	        // auto activate
	        htmlContent.append("<tr><th align=\"left\">Auto Activate</th><td align=\"left\">");	        
	        content.append("Auto Activate - ");	        
        	content.append((booking.getAutoActivate() ? "Yes" : "No") + "\n");	        
        	htmlContent.append(booking.getAutoActivate() ? "Yes" : "No");	        
	        htmlContent.append("</td></tr>");

	        // charge rate
            if (theBookingGradeUser != null) {
    	        htmlContent.append("<tr><th align=\"left\">Charge Rate</th><td align=\"left\">");	        
    	        content.append("Charge Rate - ");	        
    	        content.append("�" + df.format(theBookingGradeUser.getRate()) + " " + "�" + df.format(theBookingGradeUser.getValue()) + "\n");
    	        htmlContent.append("�" + df.format(theBookingGradeUser.getRate()) + " " + "�" + df.format(theBookingGradeUser.getValue()));
    	        htmlContent.append("</td></tr>");
            }
            
            // wage rate
            if (theBookingGradeUser != null) {
    	        htmlContent.append("<tr><th align=\"left\">Wage Rate</th><td align=\"left\">");	        
    	        content.append("Wage Rate - ");	        
    	        content.append("�" + df.format(theBookingGradeUser.getWageRate()) + " " + "�" + df.format(theBookingGradeUser.getWageRateValue()) + "\n");
    	        htmlContent.append("�" + df.format(theBookingGradeUser.getWageRate()) + " " + "�" + df.format(theBookingGradeUser.getWageRateValue()));
    	        htmlContent.append("</td></tr>");
            } 

	        // specifics
	        htmlContent.append("<tr><th align=\"left\">CV Required</th><td align=\"left\">");	        
	        content.append("CV Required - ");	        
        	content.append((booking.getCvRequired() ? "Yes" : "No") + "\n");	        
        	htmlContent.append(booking.getCvRequired() ? "Yes" : "No");	        
	        htmlContent.append("</td></tr>");

	        htmlContent.append("<tr><th align=\"left\">Interview Required</th><td align=\"left\">");	        
	        content.append("Interview Required - ");	        
        	content.append((booking.getInterviewRequired() ? "Yes" : "No") + "\n");	        
        	htmlContent.append(booking.getInterviewRequired() ? "Yes" : "No");	        
	        htmlContent.append("</td></tr>");

	        htmlContent.append("<tr><th align=\"left\">Can Provide Accommodation</th><td align=\"left\">");	        
	        content.append("Can Provide Accommodation - ");	        
        	content.append((booking.getCanProvideAccommodation() ? "Yes" : "No") + "\n");	        
        	htmlContent.append(booking.getCanProvideAccommodation() ? "Yes" : "No");	        
	        htmlContent.append("</td></tr>");

	        htmlContent.append("<tr><th align=\"left\">Car Required</th><td align=\"left\">");	        
	        content.append("Car Required - ");	        
        	content.append((booking.getCarRequired() ? "Yes" : "No") + "\n");	        
        	htmlContent.append(booking.getCarRequired() ? "Yes" : "No");	        
	        htmlContent.append("</td></tr>");
        	
        	// expenses
        	if (booking.getBookingExpenses().size() > 0 ) {
	        	htmlContent.append("<tr><th align=\"left\">Expenses</th><td align=\"left\">");	        
		        content.append("Expenses - ");	        
		        for (BookingExpense bookingExpense: booking.getBookingExpenses()) {
			        content.append(bookingExpense.getExpenseName() + " ");
			        htmlContent.append(bookingExpense.getExpenseName() + "<br/>");
		        }
		        content.append("\n");
		        htmlContent.append("</td></tr>");
        	}
	       
        	// expenses text
        	if (booking.getExpensesText() != null && !"".equals(booking.getExpensesText())) {
    	        htmlContent.append("<tr><th align=\"left\">Expenses Text</th><td align=\"left\">");	        
		        content.append("Expenses Text - ");	        
    	        content.append(booking.getExpensesText() + "\n");
    	        htmlContent.append(booking.getExpensesText());
    	        htmlContent.append("</td></tr>");
        	}	
        	
        	// comments
        	if (booking.getComments() != null && !"".equals(booking.getComments())) {
		        htmlContent.append("<tr><th align=\"left\">Comments</th><td align=\"left\">");	        
		        content.append("Comments - ");	        
		        content.append(booking.getComments() + "\n");	        
		        htmlContent.append("<pre>" + booking.getComments() + "</pre>");	        
		        htmlContent.append("</td></tr>");
        	}
	        
	        htmlContent.append("</table>");
	        
	  	    // bottom divider start
	  	    
	        htmlContent.append("<br/>\n");
	        
	        htmlContent.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
	        htmlContent.append("<tr><td height=\"1\" width=\"100%\" bgcolor=\"#e0e0e0\"></td></tr>");
	        htmlContent.append("</table>");

	        // bottom divider end
	        
	        // footer start
	        
	        htmlContent.append("<div align=\"center\">");
	        htmlContent.append("  <br/>");
	        htmlContent.append("&copy; Copyright Ingenious Solutions Ltd 2007");
	        htmlContent.append("  <br/>");
	        htmlContent.append("</div>");
	        
	        // footer end

	        htmlContent.append("</body></html>");
	        
	        
	        String contentType = "text/plain";

	        
	        System.out.println(htmlContent.toString());
	        System.out.println(content.toString());
	        
	        
            // TODO - NEED TO sort out try - catch !!! shouldn't be here !!!
            try {
//          	add related mutip-part for root
	            MimeMultipart multipartRoot = new MimeMultipart("related");
	            MimeBodyPart contentRoot = new MimeBodyPart();
	            MimeMultipart multipartAlt = new MimeMultipart("alternative");
	
//              alternative message
	            BodyPart messageBodyPart;
	            messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setContent(content.toString(),"text/plain");
	            multipartAlt.addBodyPart(messageBodyPart);
	
	            messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setContent(htmlContent.toString(),"text/html");
	            multipartAlt.addBodyPart(messageBodyPart);
	
//              Hierarchy
	            contentRoot.setContent(multipartAlt);
	            multipartRoot.addBodyPart(contentRoot);
	            

//	          add a part for the image
	            messageBodyPart = new MimeBodyPart();
	            String imagePrefix = System.getenv("IMAGE_PREFIX");
	            String mmjLogo = FileHandler.getInstance().getFileLocation()  + "/images/" + imagePrefix + "master-logo.jpg";
	            DataSource fds = new FileDataSource(mmjLogo);
	            messageBodyPart.setDataHandler(new DataHandler(fds));
	            messageBodyPart.setHeader("Content-ID","<mmjLogo>");
	            messageBodyPart.setHeader("Content-Type", "image/jpeg");
	            messageBodyPart.setDisposition(MimeBodyPart.INLINE);
	            // add it
	            multipartRoot.addBodyPart(messageBodyPart);
	            
	            if (clientLogoFilename == null || "".equals(clientLogoFilename)) {
	                // nuffink	
	            }
	            else {
	            	// add a part for the image
	                messageBodyPart = new MimeBodyPart();
	                String agencyLogo = FileHandler.getInstance().getLogoFileLocation() + client.getLogoUrl();
	                fds = new FileDataSource(agencyLogo);
	                messageBodyPart.setDataHandler(new DataHandler(fds));
	                messageBodyPart.setHeader("Content-ID","<clientLogo>");
	                messageBodyPart.setHeader("Content-Type", "image/jpeg");
	                messageBodyPart.setDisposition(MimeBodyPart.INLINE);
	                // add it
	                multipartRoot.addBodyPart(messageBodyPart);
	            }
	
    	        int status = MailHandler.getInstance().sendSingleMail(from, to, subject, multipartRoot, contentType);
//    	        int status = MailHandler.getInstance().sendSingleMail(from, to, subject, content.toString(), contentType);
    	        System.out.println(status);
                if (status == 0) {
        	        // update sent time
        	        rc = getBookingGradeDAO().updateBookingGradeSentTimestamp(bookingGradeId, noOfChanges + 1, auditorId);
                }
                else {
        	        // update sent status
        	        rc = getBookingGradeDAO().updateBookingGradeSentStatus(bookingGradeId, noOfChanges + 1, status, auditorId);
                }

            
            } catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
	        
	        
	        
	        
// KRD START        
        
//			            // TODO - NEED TO sort out try - catch !!! shouldn't be here !!!
//			            try {
//			//          	add related mutip-part for root
//				            MimeMultipart multipartRoot = new MimeMultipart("related");
//				            MimeBodyPart contentRoot = new MimeBodyPart();
//				            MimeMultipart multipartAlt = new MimeMultipart("alternative");
//				
//			//              alternative message
//				            BodyPart messageBodyPart;
//				            messageBodyPart = new MimeBodyPart();
//				            String txt = "TEXT/PLAIN version";
//				            messageBodyPart.setContent(txt,"text/plain");
//				            multipartAlt.addBodyPart(messageBodyPart);
//				
//				            messageBodyPart = new MimeBodyPart();
//				            String htmlText = "<h1>TEXT/HTML</h1>";
//				            htmlText += "<br/><br/>";
//				            htmlText += "<font color=\"red\">version</font>";
//				            htmlText += "<br/>";
//				            htmlText += "<br/>";
//				            htmlText += "<h2>ENJOY!</h2>";
//				            messageBodyPart.setContent(htmlText,"text/html");
//				            multipartAlt.addBodyPart(messageBodyPart);
//				
//			//              Hierarchy
//				            contentRoot.setContent(multipartAlt);
//				            multipartRoot.addBodyPart(contentRoot);
//				
//					        int myStatus = MailHandler.getInstance().sendSingleMail(from, to, subject, multipartRoot, contentType);
//			                System.out.println(myStatus);
//			            } catch (MessagingException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
            
// KRD END        
        
        }
		
		return rc;

	}

	public BookingGrade getBookingGrade(Integer bookingGradeId) {
	
		BookingGrade bookingGrade = null;
    	bookingGrade = getBookingGradeDAO().getBookingGrade(bookingGradeId);
		return bookingGrade;

	}

	public List<BookingGrade> getBookingGradesToSend() {
		
		List<BookingGrade> bookingGrades = null;
		bookingGrades = getBookingGradeDAO().getBookingGradesToSend();
		return bookingGrades;

	}
	
	public List<LocationJobProfileUser> getLocationJobProfileUsersForManager(Integer managerId) {

		List<LocationJobProfileUser> locationJobProfileUsers = null;
		locationJobProfileUsers = getLocationJobProfileDAO().getLocationJobProfileUsersForManager(managerId);
		return locationJobProfileUsers;

	}

	public List<LocationJobProfileUser> getLocationJobProfileUsersForManagerForLocation(Integer managerId, Integer locationId) {

		List<LocationJobProfileUser> locationJobProfileUsers = null;
		locationJobProfileUsers = getLocationJobProfileDAO().getLocationJobProfileUsersForManagerForLocation(managerId, locationId);
		return locationJobProfileUsers;

	}

    public LocationJobProfileUser getLocationJobProfileUserForManager(Integer managerId, Integer locationJobProfileId) {
    	
    	LocationJobProfileUser locationJobProfileUser = null;
    	locationJobProfileUser = getLocationJobProfileDAO().getLocationJobProfileUserForManager(managerId, locationJobProfileId);
		return locationJobProfileUser;

    }

    public List<BudgetTransaction> getBudgetTransactionsForLocationJobProfile(Integer locationJobProfileId) {
    	
		List<BudgetTransaction> budgetTransactions = null;
		budgetTransactions = getBudgetTransactionDAO().getBudgetTransactionsForLocationJobProfile(locationJobProfileId);
		return budgetTransactions;
    	
    }
	
	public Client getClientForCode(String clientCode) {

		Client client = getClientDAO().getClientForCode(clientCode);
		if (client == null) {
			throw new DataNotFoundException();
		}
		return client;

	}

	public BookingGradeApplicantUserEntity getBookingGradeApplicantUserEntity(Integer bookingGradeApplicantId, Integer managerId) {

		BookingGradeApplicantUserEntity bookingGradeApplicantUser = null;
		bookingGradeApplicantUser = getBookingGradeApplicantDAO().getBookingGradeApplicantUserEntity(bookingGradeApplicantId);

		if (bookingGradeApplicantUser != null) {
			
			bookingGradeApplicantUser.setBookingGradeApplicantDateUserEntities(getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUserEntitiesForBookingGradeApplicant(bookingGradeApplicantId, true));

			IntValue expensesCount = getBookingGradeApplicantDAO().getBookingExpensesCount(bookingGradeApplicantUser.getBookingId());
			
			if (expensesCount.getValue() > 0) {
				for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDateUserEntity: bookingGradeApplicantUser.getBookingGradeApplicantDateUserEntities()) {
					bookingGradeApplicantDateUserEntity.setBookingDateExpenseUsers(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingGradeApplicantDateUserEntity.getBookingDateId()));
				}
			}
			
			bookingGradeApplicantUser.setBookingExpensesCount(expensesCount);
			
		}

		return bookingGradeApplicantUser;

	}
	
	public int updateBookingGradeApplicantReject(Integer bookingGradeApplicantId, String rejectText, Integer noOfChanges, Integer auditorId) {

        // update all bookingGradeApplicantDates

        List<BookingGradeApplicantDateUser> dates = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingGradeApplicant(bookingGradeApplicantId, true);
       	
        for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser: dates) {

        	// probably should be checking current status ???
        	
        	int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
        				bookingGradeApplicantDateUser.getNoOfChanges(),
        				auditorId,
        				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_REJECTED);
        	
    	}
        
        int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicantReject(bookingGradeApplicantId, rejectText, noOfChanges, auditorId);
        return rc;

	}

	public int updateBookingGradeApplicantRenegotiate(Integer bookingGradeApplicantId, String renegotiateText, Integer noOfChanges, Integer auditorId) {

        // update all bookingGradeApplicantDates

        List<BookingGradeApplicantDateUser> dates = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingGradeApplicant(bookingGradeApplicantId, true);
       	
        for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser: dates) {

        	// probably should be checking current status ???
        	
        	int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
        				bookingGradeApplicantDateUser.getNoOfChanges(),
        				auditorId,
        				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_RENEGOTIATE);
        	
    	}
        
        int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicantRenegotiate(bookingGradeApplicantId, renegotiateText, noOfChanges, auditorId);
        return rc;

	}

	public int updateBookingGradeApplicantOffer(Integer bookingGradeApplicantId, Integer noOfChanges, Integer auditorId) {

        // update all bookingGradeApplicateDates
        
        List<BookingGradeApplicantDateUser> dates = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUsersForBookingGradeApplicant(bookingGradeApplicantId, true);
       	
        Integer bookingId = null;
        
        for (BookingGradeApplicantDateUser bookingGradeApplicantDateUser: dates) {

        	// probably should be checking current status ???

        	if (bookingGradeApplicantDateUser.getBookingDateStatus() == BookingDate.BOOKINGDATE_STATUS_OPEN) {

        		BookingDate bookingDate = getBookingDateDAO().getBookingDate(bookingGradeApplicantDateUser.getBookingDateId());
        		int bd = getBookingDateDAO().updateBookingDateOffered(bookingDate.getBookingDateId(), 
        				                                        bookingDate.getNoOfChanges(), 
        				                                        auditorId, 
        				                                        bookingGradeApplicantDateUser.getBookingGradeApplicantDateId());
        		
        		// used below
        		bookingId = bookingDate.getBookingId();
        		
	        	int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
	        				bookingGradeApplicantDateUser.getNoOfChanges(),
	        				auditorId,
	        				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_OFFERED);

        	}
        	if (bookingGradeApplicantDateUser.getBookingDateStatus() == BookingDate.BOOKINGDATE_STATUS_OFFERED ||
        		bookingGradeApplicantDateUser.getBookingDateStatus() == BookingDate.BOOKINGDATE_STATUS_FILLED) {
        		
        		// already offered or filled so mark as unsuccessful
        		
	        	int d = getBookingGradeApplicantDateDAO().updateBookingGradeApplicantDateStatus(bookingGradeApplicantDateUser.getBookingGradeApplicantDateId(),
        				bookingGradeApplicantDateUser.getNoOfChanges(),
        				auditorId,
        				BookingGradeApplicantDate.BOOKINGGRADEAPPLICANTDATE_STATUS_UNSUCCESSFUL);

        	}
        	
    	}

        if (bookingId != null) {
            Booking booking = getBookingDAO().getBooking(bookingId);
            if (booking.getSingleCandidate()) {
                int b = getBookingDAO().updateBookingStatus(bookingId, booking.getNoOfChanges(), auditorId, Booking.BOOKING_STATUS_OFFERED);
            }
        }
        
        int rc = getBookingGradeApplicantDAO().updateBookingGradeApplicantStatus(bookingGradeApplicantId, noOfChanges, auditorId, BookingGradeApplicant.BOOKINGGRADEAPPLICANT_STATUS_OFFERED);
        return rc;

	}

	public BookingGradeApplicantDateUser getBookingGradeApplicantDateUser(Integer bookingGradeApplicantDateId) {
		
		BookingGradeApplicantDateUser bookingGradeApplicantDate = null;
		bookingGradeApplicantDate = getBookingGradeApplicantDateDAO().getBookingGradeApplicantDateUser(bookingGradeApplicantDateId);
		return bookingGradeApplicantDate;
		
	}

	public BookingDate getBookingDate(Integer bookingDateId) {

		BookingDate bookingDate = null;
		bookingDate = getBookingDateDAO().getBookingDate(bookingDateId);
		return bookingDate;

	}

	public BookingDateUserApplicant getBookingDateUserApplicantForManagerAndBookingDate(Integer managerId, Integer bookingDateId) {

		BookingDateUserApplicant bookingDateUserApplicant = null;
		bookingDateUserApplicant = getBookingDateDAO().getBookingDateUserApplicantForManagerAndBookingDate(managerId, bookingDateId);
		return bookingDateUserApplicant;

	}
	
	public BookingDateUserApplicantEntity getBookingDateUserApplicantEntityForManagerAndBookingDate(Integer managerId, Integer bookingDateId) {
		
		BookingDateUserApplicantEntity bookingDateUserApplicantEntity = null;
		bookingDateUserApplicantEntity = getBookingDateDAO().getBookingDateUserApplicantEntityForManagerAndBookingDate(managerId, bookingDateId);

		bookingDateUserApplicantEntity.setBookingDateHours(bookingDateHourDAO.getBookingDateHoursForBookingDate(bookingDateId, true));
		bookingDateUserApplicantEntity.setBookingDateExpenses(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingDateId));
		
		return bookingDateUserApplicantEntity;

	}

	public List<StatusCount> getBookingStatusCountsForManager(Integer managerId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingDAO().getBookingStatusCountsForManager(managerId);
		return statusCounts;
	
	}

	public List<StatusCount> getShiftStatusCountsForManager(Integer managerId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingDAO().getShiftStatusCountsForManager(managerId);
		return statusCounts;
	
	}

	public List<StatusCount> getBookingWorkedStatusCountsForManager(Integer managerId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingDAO().getBookingWorkedStatusCountsForManager(managerId);
		return statusCounts;
	
	}
	
	public List<StatusCount> getShiftWorkedStatusCountsForManager(Integer managerId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingDAO().getShiftWorkedStatusCountsForManager(managerId);
		return statusCounts;
	
	}
	
	public List<StatusCount> getBookingGradeApplicantDateStatusCountsForManager(Integer managerId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingDAO().getBookingGradeApplicantDateStatusCountsForManager(managerId);
		return statusCounts;
	
	}

	public List<StatusCount> getAgencyInvoiceStatusCountsForManager(Integer clientId, Integer managerId) {

		List<StatusCount> statusCounts = null;
		statusCounts = getBookingDAO().getAgencyInvoiceStatusCountsForManager(clientId, managerId);
		return statusCounts;
	
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, 
			Integer bookingId, Integer bookingDateId, Integer bookingGradeApplicantId, Integer invoiceId, Integer bookingDateStatus, Integer workedStatus,                                                  
			Boolean singleCandidate, Boolean activated, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {

		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManager(managerId, 
                bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingDateStatus, workedStatus,        
				singleCandidate, activated, siteId, locationId, jobProfileId, fromDate, toDate);
		return bookingDateUserApplicants;
		
	}
	
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBooking(Integer managerId, Integer bookingId, Integer bookingDateStatus, Integer workedStatus) {
		
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndBooking(managerId, bookingId, bookingDateStatus, workedStatus);
		return bookingDateUserApplicants;

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingAndDateRange(Integer managerId, Integer bookingId, Integer bookingDateStatus, Integer workedStatus, Date fromDate, Date toDate) {
		
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndBookingAndDateRange(managerId, bookingId, bookingDateStatus, workedStatus, fromDate, toDate);
		return bookingDateUserApplicants;

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingDate(Integer managerId, Integer bookingDateId) {

		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndBookingDate(managerId, bookingDateId);
		return bookingDateUserApplicants;
	
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingDates(Integer managerId, String bookingDateIdStrs) {

		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndBookingDates(managerId, bookingDateIdStrs);
		return bookingDateUserApplicants;
	
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndBookingGradeApplicant(Integer managerId, Integer bookingGradeApplicantId) {

		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndBookingGradeApplicant(managerId, bookingGradeApplicantId);
		return bookingDateUserApplicants;
	
	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, Boolean singleCandidate) {

		return getBookingDateUserApplicantsForManager(managerId, singleCandidate, null, null, null);

	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManager(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId) {
			
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManager(managerId, singleCandidate, siteId, locationId, jobProfileId);
		return bookingDateUserApplicants;

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatus(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate) {

		return getBookingDateUserApplicantsForManagerAndStatus(managerId, bookingDateStatus, singleCandidate, null, null, null);

	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatus(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId) {
			
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndStatus(managerId, bookingDateStatus, singleCandidate, siteId, locationId, jobProfileId);
		return bookingDateUserApplicants;

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatusAndDateRange(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Date fromDate, Date toDate) {
    
		return getBookingDateUserApplicantsForManagerAndStatusAndDateRange(managerId, bookingDateStatus, singleCandidate, null, null, null, fromDate, toDate);

	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndStatusAndDateRange(Integer managerId, Integer bookingDateStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {
			
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndStatusAndDateRange(managerId, bookingDateStatus, singleCandidate, siteId, locationId, jobProfileId, fromDate, toDate);
		return bookingDateUserApplicants;

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Date fromDate, Date toDate) {

		return getBookingDateUserApplicantsForManagerAndDateRange(managerId, singleCandidate, null, null, null, fromDate, toDate);

	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndDateRange(Integer managerId, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {
		
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndDateRange(managerId, singleCandidate, siteId, locationId, jobProfileId, fromDate, toDate);
		return bookingDateUserApplicants;

	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatus(Integer managerId, Integer workedStatus, Boolean singleCandidate) {

		return getBookingDateUserApplicantsForManagerAndWorkedStatus(managerId, workedStatus, singleCandidate, null, null, null);
	
	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatus(Integer managerId, Integer workedStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId) {
			
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndWorkedStatus(managerId, workedStatus, singleCandidate, siteId, locationId, jobProfileId);
		return bookingDateUserApplicants;

	}

	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRange(Integer managerId, Integer workedStatus, Boolean singleCandidate, Date fromDate, Date toDate) {

		return getBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRange(managerId, workedStatus, singleCandidate, null, null, null, fromDate, toDate);
		
	}
	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRange(Integer managerId, Integer workedStatus, Boolean singleCandidate, Integer siteId, Integer locationId, Integer jobProfileId, Date fromDate, Date toDate) {
			
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndWorkedStatusAndDateRange(managerId, workedStatus, singleCandidate, siteId, locationId, jobProfileId, fromDate, toDate);
		return bookingDateUserApplicants;

	}

    public int insertBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId) {
    	
        LocationJobProfile locationJobProfile = getLocationJobProfileDAO().getLocationJobProfile(budgetTransaction.getLocationJobProfileId());
		//
		budgetTransaction.setBalance(locationJobProfile.getBudget().add(budgetTransaction.getValue()));
		budgetTransaction.setVatBalance(locationJobProfile.getVatBudget().add(budgetTransaction.getVatValue()));
		budgetTransaction.setExpenseBalance(locationJobProfile.getExpenseBudget().add(budgetTransaction.getExpenseValue()));
		budgetTransaction.setNonPayBalance(locationJobProfile.getNonPayBudget().add(budgetTransaction.getNonPayValue()));
        //
		int rc = getBudgetTransactionDAO().insertBudgetTransaction(budgetTransaction, auditorId);
        // add the value to the locationJobProfile
		rc = getLocationJobProfileDAO().updateLocationJobProfileBudget(budgetTransaction.getLocationJobProfileId(), 
				                                                  budgetTransaction.getValue(), 
													              budgetTransaction.getVatValue(), 
													              budgetTransaction.getExpenseValue(), 
													              budgetTransaction.getNonPayValue(), 
				                                                  locationJobProfile.getNoOfChanges(), 
				                                                  auditorId);
		//        
    	return rc;
    	
    }

 	public int updateBookingDateRejected(BookingDate bookingDate, Integer auditorId) {
 		
        int rc = getBookingDateDAO().updateBookingDateRejected(bookingDate.getBookingDateId(), 
        		bookingDate.getRejectText(),
        		bookingDate.getNoOfChanges(),
        		auditorId);

		return rc;
 		
 	}
    
 	public int updateBookingDateInvoiced(BookingDate bookingDate, Integer auditorId) {
 		
        int rc = getBookingDateDAO().updateBookingDateInvoiced(bookingDate.getBookingDateId(),
        		bookingDate.getInvoiceId(),
        		bookingDate.getNoOfChanges(),
        		auditorId);

		return rc;
 		
 	}
 	
    public int insertInvoice(Integer clientId, String bookingDateIdStrs, Integer auditorId) {
    	
    	// TODO - currently assuming auditorId is for a manager!
    	Manager manager = getManagerDAO().getManager(auditorId);
    	
    	List<InvoiceAgency> invoiceAgencies = new ArrayList<InvoiceAgency>();
    	
    	List<BookingDateUserApplicant> bookingDates = getBookingDateDAO().getBookingDateUserApplicantsForManagerAndBookingDates(auditorId, bookingDateIdStrs);

    	for (BookingDateUserApplicant bookingDate: bookingDates) {

    		Integer agencyId = bookingDate.getAgencyId();
    		InvoiceAgency currentInvoiceAgency = null;
    		
    		for (InvoiceAgency invoiceAgency: invoiceAgencies) {
    			if (invoiceAgency.getAgencyId().equals(agencyId)) {
    				currentInvoiceAgency = invoiceAgency;
    				break;
    			}
    		}
    		if (currentInvoiceAgency == null) {
    			ClientAgency currentClientAgency = getClientAgencyDAO().getClientAgencyForClientAndAgency(manager.getClientId(), agencyId);
    			
    			currentInvoiceAgency = new InvoiceAgency();
    			currentInvoiceAgency.setAgencyId(agencyId);
    			currentInvoiceAgency.setPaymentTerms(currentClientAgency.getPaymentTerms());
    			invoiceAgencies.add(currentInvoiceAgency);
    		}

    		currentInvoiceAgency.setPayRateValue(currentInvoiceAgency.getPayRateValue().add(bookingDate.getWorkedPayRateValue()));
    		currentInvoiceAgency.setWtdValue(currentInvoiceAgency.getWtdValue().add(bookingDate.getWorkedWtdValue()));
    		currentInvoiceAgency.setNiValue(currentInvoiceAgency.getNiValue().add(bookingDate.getWorkedNiValue()));
    		currentInvoiceAgency.setCommissionValue(currentInvoiceAgency.getCommissionValue().add(bookingDate.getWorkedCommissionValue()));
    		currentInvoiceAgency.setExpenseValue(currentInvoiceAgency.getExpenseValue().add(bookingDate.getExpenseValue()));
    		currentInvoiceAgency.setVatValue(currentInvoiceAgency.getVatValue().add(bookingDate.getWorkedVatValue().add(bookingDate.getExpenseVatValue())));
    		currentInvoiceAgency.setNoOfHours(currentInvoiceAgency.getNoOfHours().add(bookingDate.getWorkedNoOfHours()));
    	 	currentInvoiceAgency.setFeeValue(currentInvoiceAgency.getFeeValue().add(bookingDate.getFeeValue()));
    		
    	}

    	BigDecimal chargeRateValue = new BigDecimal(0);
    	BigDecimal payRateValue = new BigDecimal(0);
    	BigDecimal wtdValue = new BigDecimal(0);
    	BigDecimal niValue = new BigDecimal(0);
    	BigDecimal commissionValue = new BigDecimal(0);
    	BigDecimal expenseValue = new BigDecimal(0);
    	BigDecimal vatValue = new BigDecimal(0);
    	BigDecimal noOfHours = new BigDecimal(0);
    	BigDecimal feeValue = new BigDecimal(0);

		for (InvoiceAgency invoiceAgency: invoiceAgencies) {
			// calculate the invoiceAgency chargeRateValue
			invoiceAgency.setChargeRateValue(invoiceAgency.getPayRateValue().add(
					                          invoiceAgency.getWtdValue()).add(
					                           invoiceAgency.getNiValue()).add(
					                            invoiceAgency.getCommissionValue()));

	    	chargeRateValue = chargeRateValue.add(invoiceAgency.getChargeRateValue());
			payRateValue = payRateValue.add(invoiceAgency.getPayRateValue());
			wtdValue = wtdValue.add(invoiceAgency.getWtdValue());
			niValue = niValue.add(invoiceAgency.getNiValue());
			commissionValue = commissionValue.add(invoiceAgency.getCommissionValue());
			expenseValue = expenseValue.add(invoiceAgency.getExpenseValue());
			vatValue = vatValue.add(invoiceAgency.getVatValue());
		 	noOfHours = noOfHours.add(invoiceAgency.getNoOfHours());
	        feeValue = feeValue.add(invoiceAgency.getFeeValue());
		}
    	
    	Invoice invoice = new Invoice();
    	invoice.setClientId(clientId);
    	invoice.setChargeRateValue(chargeRateValue);
    	invoice.setPayRateValue(payRateValue);
    	invoice.setWtdValue(wtdValue);
    	invoice.setNiValue(niValue);
    	invoice.setCommissionValue(commissionValue);
    	invoice.setExpenseValue(expenseValue);
    	invoice.setVatValue(vatValue);
    	invoice.setNoOfHours(noOfHours);
    	invoice.setFeeValue(feeValue);
    	
    	int rc = getInvoiceDAO().insertInvoice(invoice, auditorId);

    	Integer invoiceId = invoice.getInvoiceId();
    	
    	for (InvoiceAgency invoiceAgency: invoiceAgencies) {
    		invoiceAgency.setInvoiceId(invoiceId);
    		rc = getInvoiceAgencyDAO().insertInvoiceAgency(invoiceAgency, auditorId);
    	}

    	for (BookingDate bookingDate: bookingDates) {
    		rc = getBookingDateDAO().updateBookingDateInvoiced(bookingDate.getBookingDateId(), invoiceId, bookingDate.getNoOfChanges(), auditorId);
    	}
    	
    	// TODO a bit odd !!!
    	return invoiceId;
    	
    }

 	public InvoiceEntity getInvoiceEntity(Manager manager, Integer invoiceId) {
 		
		InvoiceEntity invoiceEntity = null;
		invoiceEntity = getInvoiceDAO().getInvoiceEntity(manager.getClientId(), invoiceId);

		if (invoiceEntity != null) {

			invoiceEntity.setInvoiceAgencies(getInvoiceAgencyDAO().getInvoiceAgencyUsersForInvoice(invoiceId));
			
			invoiceEntity.setBookingDateUserApplicants(getBookingDateDAO().getBookingDateUserApplicantsForManagerAndInvoice(manager.getManagerId(), invoiceId));
			
//			invoiceEntity.setBookingDateUserApplicantEntities(getBookingDateDAO().getBookingDateUserApplicantEntitiesForManagerAndInvoice(managerId, invoiceId));

//			// for each bookingDateUserApplicantEntity we need to load the expenses and the hours
//	        for (BookingDateUserApplicantEntity bookingDateUserApplicantEntity: invoiceEntity.getBookingDateUserApplicantEntities()) {
//	    		bookingDateUserApplicantEntity.setBookingDateHours(bookingDateHourDAO.getBookingDateHoursForBookingDate(bookingDateUserApplicantEntity.getBookingDateId(), true));
//	    		bookingDateUserApplicantEntity.setBookingDateExpenses(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingDateUserApplicantEntity.getBookingDateId()));
//	        }
		}

		return invoiceEntity;
 		
 	}

 	public List<ClientReEnterPwdUser> getClientReEnterPwdUsersForClient(Integer clientId) {

		return clientReEnterPwdDAO.getClientReEnterPwdUsersForClient(clientId);

 	}

  	public IntValue getShiftsToActivateCountForManager(Integer managerId) {
  		
		return getBookingDAO().getShiftsToActivateCountForManager(managerId);

  	}
 	
  	public IntValue getShiftsOutstandingCountForManager(Integer managerId) {
  		
		return getBookingDAO().getShiftsOutstandingCountForManager(managerId);

  	}
 	
	public List<BookingDateUserApplicant> getBookingDateUserApplicantsToActivateForManager(Integer managerId) {
		
		List<BookingDateUserApplicant> bookingDateUserApplicants = null;
		bookingDateUserApplicants = getBookingDateDAO().getBookingDateUserApplicantsToActivateForManager(managerId);
		return bookingDateUserApplicants;

	}

	public AgencyInvoiceUserEntity getAgencyInvoiceUserEntityForManager(Integer agencyInvoiceId, Integer managerId) {
		
		AgencyInvoiceUserEntity agencyInvoice = null;
		agencyInvoice = getAgencyInvoiceDAO().getAgencyInvoiceUserEntity(agencyInvoiceId);
		
		if (agencyInvoice != null) {
			
			//

			agencyInvoice.setBookingDateUserApplicants(getBookingDateDAO().getBookingDateUserApplicantEntitiesForManagerAndAgencyInvoice(managerId, agencyInvoiceId));

			// TODO - soooo rubbishly slow!
			
			if (agencyInvoice.getExpenseValue().compareTo(new BigDecimal(0)) > 0) {
			    // expenses
				for (BookingDateUserApplicantEntity bookingDate: agencyInvoice.getBookingDateUserApplicants()) {
					if (bookingDate.getExpenseValue().compareTo(new BigDecimal(0)) > 0) {
						bookingDate.setBookingDateExpenses(bookingDateExpenseDAO.getBookingDateExpenseUsersForBookingDate(bookingDate.getBookingDateId()));
					}
				}
			}
			
		}
		
		return agencyInvoice;

	}
	
    public int updateAgencyInvoiceAuthorized(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId) {
    	
		int rc = getAgencyInvoiceDAO().updateAgencyInvoiceAuthorized(agencyInvoiceId, noOfChanges, auditorId);
		return rc;

    }

    public int updateAgencyInvoicePaid(Integer agencyInvoiceId, Integer noOfChanges, Integer auditorId) {
    	
		int rc = getAgencyInvoiceDAO().updateAgencyInvoicePaid(agencyInvoiceId, noOfChanges, auditorId);
		return rc;

    }
	
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForManagerAndStatus(Integer clientId, Integer managerId, Integer status) {
		
		List<AgencyInvoiceUser> agencyInvoiceUsers = null;
		agencyInvoiceUsers = getAgencyInvoiceDAO().getAgencyInvoiceUsersForManagerAndStatus(clientId, managerId, status);
		return agencyInvoiceUsers;

	}
    
	public List<AgencyInvoiceUser> getAgencyInvoiceUsersForManagerAndAgencyInvoices(Integer clientId, Integer managerId, String agencyInvoiceIdStrs) {
		
		List<AgencyInvoiceUser> agencyInvoiceUsers = null;
		agencyInvoiceUsers = getAgencyInvoiceDAO().getAgencyInvoiceUsersForManagerAndAgencyInvoices(clientId, managerId, agencyInvoiceIdStrs);
		return agencyInvoiceUsers;

	}
    
	public int updateAgencyInvoicesAuthorized(String agencyInvoiceIdStrs, Integer auditorId) {

        int rc = 0;
        StringTokenizer st = new StringTokenizer(agencyInvoiceIdStrs, ",");
        while (st.hasMoreTokens()) {
            String agencyInvoiceIdStr = st.nextToken();
            Integer agencyInvoiceId = Integer.parseInt(agencyInvoiceIdStr);
     		// for each agencyInvoice call the updateAgencyInvoiceAuthorized method in this class.
	 		AgencyInvoice agencyInvoice = getAgencyInvoiceDAO().getAgencyInvoiceUser(agencyInvoiceId);
	 		//
	 		rc += updateAgencyInvoiceAuthorized(agencyInvoice.getAgencyInvoiceId(), agencyInvoice.getNoOfChanges(), auditorId);
        }
 		return rc;
 	
	}
	
	public int updateAgencyInvoicesMarkAsPaid(String agencyInvoiceIdStrs, Integer auditorId) {

        int rc = 0;
        StringTokenizer st = new StringTokenizer(agencyInvoiceIdStrs, ",");
        while (st.hasMoreTokens()) {
            String agencyInvoiceIdStr = st.nextToken();
            Integer agencyInvoiceId = Integer.parseInt(agencyInvoiceIdStr);
     		// for each agencyInvoice call the updateAgencyInvoicePaid method in this class.
	 		AgencyInvoice agencyInvoice = getAgencyInvoiceDAO().getAgencyInvoiceUser(agencyInvoiceId);
	 		//
	 		rc += updateAgencyInvoicePaid(agencyInvoice.getAgencyInvoiceId(), agencyInvoice.getNoOfChanges(), auditorId);
        }
 		return rc;
 	
	}
	
}
