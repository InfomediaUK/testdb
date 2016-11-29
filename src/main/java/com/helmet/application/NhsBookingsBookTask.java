package com.helmet.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.util.MessageResources;

import com.helmet.api.AgyService;
import com.helmet.application.agy.AgyConstants;
import com.helmet.application.agy.ApplicantBookingConfirmationMessage;
import com.helmet.bean.Agency;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicant;
import com.helmet.bean.BookingGradeApplicantDate;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Consultant;
import com.helmet.bean.Expense;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.NhsBooking;
import com.helmet.bean.NhsBookingApplicantIds;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Shift;
import com.helmet.bean.SiteUser;
import com.helmet.bean.Uplift;


public class NhsBookingsBookTask implements Callable<NhsBookingsBookTaskResult>
{
  private AgyService agyService;
  private Agency agency;
  private ClientUser clientUser;
  private SiteUser siteUser; 
  private LocationUser locationUser; 
  private JobProfileUser jobProfileUser; 
  private ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser;
  private Consultant consultantLoggedIn;
  private BigDecimal hourlyRate;
  private BigDecimal wageRate;
  private BigDecimal value;
  private List<Integer> listNhsBookingId;
  private MessageResources messageResources;
  private String cssFileName;
  private String serverName;
  private ClientAgencyJobProfileGradeUser[] bookingGrades;
  private Expense[] bookingExpenses;
  private List<ReasonForRequest> listReasonForRequest;
  private ReasonForRequest reasonForRequest;
  private List<PublicHoliday> listPublicHoliday;
  private List<Uplift> listUplift;
  private List<NhsBookingApplicantIds> listNhsBookingApplicantIds = new ArrayList<NhsBookingApplicantIds>();
  NhsBookingsBookTaskResult nhsBookingsBookTaskResult;

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());


  public NhsBookingsBookTask(AgyService agyService,
                             Agency agency,
                             ClientUser clientUser, 
                             SiteUser siteUser, 
                             LocationUser locationUser, 
                             JobProfileUser jobProfileUser, 
                             ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser,
                             Consultant consultantLoggedIn,
                             BigDecimal hourlyRate, 
                             BigDecimal wageRate,
                             BigDecimal value,
                             MessageResources messageResources,
                             String cssFileName,
                             String serverName,
                             List<Integer> listNhsBookingId)
  {
    super();
    this.agyService = agyService;
    this.agency = agency;
    this.clientUser = clientUser;
    this.siteUser = siteUser;
    this.locationUser = locationUser;
    this.jobProfileUser = jobProfileUser;
    this.clientAgencyJobProfileGradeUser = clientAgencyJobProfileGradeUser;
    this.consultantLoggedIn = consultantLoggedIn;
    this.hourlyRate = hourlyRate;
    this.wageRate = wageRate;
    this.value = value;
    this.messageResources = messageResources;
    this.cssFileName = cssFileName;
    this.serverName = serverName;
    this.listNhsBookingId = listNhsBookingId;
    nhsBookingsBookTaskResult = new NhsBookingsBookTaskResult(clientUser.getName(),
                                                              siteUser.getName(),
                                                              locationUser.getName(),
                                                              jobProfileUser.getName(),
                                                              new Integer(listNhsBookingId.size())
                                                             );
  }

  public NhsBookingsBookTaskResult call() throws Exception
  {
    bookingGrades = new ClientAgencyJobProfileGradeUser[1];
    bookingExpenses = new Expense[0];
    bookingGrades[0] = clientAgencyJobProfileGradeUser;
    listReasonForRequest = agyService.getReasonForRequestsForClient(clientUser.getClientId());
    reasonForRequest = listReasonForRequest.get(0);
    NhsBooking nhsBooking = null;
    Booking newBooking = null;
    BookingDate bookingDate = null;
    BookingDate[] bookingDates = null;
    Shift shift = null;
    BookingGrade bookingGrade = null;
    BookingGradeApplicant bookingGradeApplicant = null;
    BookingGradeApplicantDate bookingGradeApplicantDate = null;
    BookingGradeApplicantDate[] bookingGradeApplicantDates = new BookingGradeApplicantDate[1];
    BookingGradeApplicantUserEntity bookingGradeApplicantUserEntity = null;
    BookingGradeAgyEntity bookingGradeAgyEntity = null;
    listPublicHoliday = agyService.getPublicHolidaysForClient(clientUser.getClientId());
    listUplift = agyService.getUpliftsForClient(clientUser.getClientId());
    MultipartEmailer multipartEmailer = null;
    ApplicantBookingConfirmationMessage applicantBookingConfirmationMessage = null;
    String fromNiceEmailAddress = null;
    String toNiceEmailAddress = null;
    String subject = null;
    String message = null;
    int status = 0;
    int rowCount = 0;
    for (Integer nhsBookingId : listNhsBookingId)
    {
      nhsBooking = agyService.getNhsBooking(nhsBookingId);
      if (nhsBooking != null)
      {
        shift = agyService.getShift(nhsBooking.getShiftId());
        bookingDates = new BookingDate[1];
        bookingDate = loadBookingDate(nhsBooking, shift);
        bookingDates[0] = bookingDate;
        newBooking = loadBooking(nhsBooking, clientUser, locationUser, jobProfileUser, clientAgencyJobProfileGradeUser, reasonForRequest, shift, listPublicHoliday, listUplift, bookingDates);
        rowCount += agyService.insertBooking(newBooking, bookingDates, bookingGrades, bookingExpenses, consultantLoggedIn.getConsultantId());
        if (lockBooking(newBooking.getBookingId()))
        {
          try
          {
            rowCount += agyService.updateBookingOpen(newBooking.getBookingId(), 0, consultantLoggedIn.getConsultantId());
          }
          finally
          {
            unlockBooking(newBooking.getBookingId());
          }
        }
        bookingGrade = agyService.getBookingGradeForBookingClientAgencyJobProfileGrade(newBooking.getBookingId(), clientAgencyJobProfileGradeUser.getClientAgencyJobProfileGradeId());
        nhsBooking.setBookingId(newBooking.getBookingId());
        nhsBooking.setBookingTime(new Timestamp(new Date().getTime()));
        nhsBooking.setBookingDateId(bookingDate.getBookingDateId());
        nhsBooking.setBookingGradeId(bookingGrade.getBookingGradeId());
        nhsBooking.setValue(value);
        agyService.updateNhsBooking(nhsBooking, consultantLoggedIn.getConsultantId());
        bookingGradeApplicant = loadBookingGradeApplicant(nhsBooking.getApplicantId(), bookingGrade, wageRate);
        bookingGradeApplicantDate = loadBookingGradeApplicantDate(bookingDate, bookingGradeApplicant, newBooking);
        bookingGradeApplicantDates[0] = bookingGradeApplicantDate;
        rowCount += agyService.insertBookingGradeApplicant(bookingGradeApplicant, bookingGradeApplicantDates, consultantLoggedIn.getConsultantId());
        rowCount += agyService.updateBookingGradeApplicantSubmit(newBooking.getBookingId(), bookingGradeApplicant.getBookingGradeApplicantId(), bookingGradeApplicant.getNoOfChanges(), consultantLoggedIn.getConsultantId());
        bookingGradeApplicantUserEntity = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicant.getBookingGradeApplicantId());
        // Setting the BookingGrade's Status to CLOSED is necessary to allow the BookingGradeAgyEntity to be found from the following line of code. 
        int bookingGradeRowCount = agyService.updateBookingGradeStatus(bookingGrade.getBookingGradeId(), bookingGrade.getNoOfChanges(), consultantLoggedIn.getConsultantId(), BookingGrade.BOOKINGGRADE_STATUS_CLOSED);
        if (bookingGradeRowCount == 0)
        {
          // Something has gone wrong! Try to recover by reselecting the BookingGrade and setting its status to CLOSED.
          bookingGrade = agyService.getBookingGrade(bookingGrade.getBookingGradeId());
          agyService.updateBookingGradeStatus(bookingGrade.getBookingGradeId(), bookingGrade.getNoOfChanges(), consultantLoggedIn.getConsultantId(), BookingGrade.BOOKINGGRADE_STATUS_CLOSED);
        }
        bookingGradeAgyEntity = agyService.getBookingGradeAgyEntity(bookingGrade.getBookingGradeId(), agency.getAgencyId());
        applicantBookingConfirmationMessage = new ApplicantBookingConfirmationMessage(bookingGradeApplicantUserEntity, bookingGradeAgyEntity, agency, clientUser, consultantLoggedIn, messageResources, serverName);
        fromNiceEmailAddress = applicantBookingConfirmationMessage.getFromNiceEmailAddress();
        toNiceEmailAddress = applicantBookingConfirmationMessage.getToNiceEmailAddress();
        subject = applicantBookingConfirmationMessage.getSubject();
        message = applicantBookingConfirmationMessage.getMessage();
        multipartEmailer = new MultipartEmailer(agency, messageResources, cssFileName, serverName, fromNiceEmailAddress, toNiceEmailAddress, null, fromNiceEmailAddress, subject, message, null);
        status = multipartEmailer.sendEmail();
        if (status == 0)
        {
          nhsBooking = agyService.getNhsBooking(nhsBooking.getNhsBookingId());
          nhsBooking.setApplicantNotificationSent(new Timestamp(new java.util.Date().getTime()));
          rowCount += agyService.updateNhsBookingApplicantNotificationSent(nhsBooking, consultantLoggedIn.getConsultantId());
          nhsBookingsBookTaskResult.setBookingsProcessedOk(nhsBookingsBookTaskResult.getBookingsProcessedOk() + 1);
        }
        else
        {
          nhsBookingsBookTaskResult.setBookingsFailed(nhsBookingsBookTaskResult.getBookingsFailed() + 1);
        }
      }
    }
    return nhsBookingsBookTaskResult;
  }

  private Booking loadBooking(NhsBooking nhsBooking, ClientUser clientUser, LocationUser locationUser, JobProfileUser jobProfileUser, ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser, ReasonForRequest reasonForRequest, Shift shift, List<PublicHoliday> listPublicHoliday, List<Uplift> listUplift, BookingDate[] bookingDates)
  {
    Booking booking = new Booking();
    booking.setReasonForRequestId(reasonForRequest.getReasonForRequestId());
    booking.setLocationId(locationUser.getLocationId());
    booking.setJobProfileId(jobProfileUser.getJobProfileId());
    booking.setShiftId(shift.getShiftId());
    // This prevents the booking getting selected by the CallOffTask process. DO NOT REMOVE OR CHANGE. 
    booking.setBookingReference(AgyConstants.NHS_BOOKINGS_BANK_REQUEST_NUMBER_LABEL + nhsBooking.getBankReqNum());
    booking.setContactName(locationUser.getContactName());
    booking.setContactJobTitle(locationUser.getContactJobTitle());
    booking.setContactEmailAddress(locationUser.getContactEmailAddress());
    booking.setContactTelephoneNumber(locationUser.getContactTelephoneNumber());
    booking.setAccountContactName(StringUtils.isEmpty(clientUser.getAccountContactName()) ? clientUser.getName() :  clientUser.getAccountContactName());
    booking.setAccountContactAddress(StringUtils.isEmpty(clientUser.getAccountContactAddress().getAddress1()) ? clientUser.getAddress() : clientUser.getAccountContactAddress());
    booking.setAccountContactEmailAddress(clientUser.getAccountContactEmailAddress());
    booking.setSingleCandidate(true);
    booking.setAutoFill(true);
    booking.setAutoActivate(true);
    BigDecimal noOfHours = shift.getNoOfHours();
    booking.setRate(hourlyRate);
    booking.setNoOfHours(noOfHours);
    // See OrderStaff7.java line 96. Calculate RRP and update BookingDate Value.
    BigDecimal rrp = Utilities.calculateIt(bookingDates, hourlyRate, listPublicHoliday, listUplift, true);    
    booking.setValue(rrp);
    //  See OrderStaff7.java line 121.
    BigDecimal value = Utilities.calculateIt(bookingDates, clientAgencyJobProfileGradeUser.getRate(), listPublicHoliday, listUplift);    
    clientAgencyJobProfileGradeUser.setValue(value);
    return booking;
  }
  
  private BookingDate loadBookingDate(NhsBooking nhsBooking, Shift shift)
  {
    BookingDate bookingDate = new BookingDate();
    bookingDate.setBookingDate(nhsBooking.getDate());
    bookingDate.setShiftId(shift.getShiftId());
    bookingDate.setShiftName(shift.getName());
    bookingDate.setShiftCode(shift.getCode());
    bookingDate.setShiftStartTime(shift.getStartTime());
    bookingDate.setShiftEndTime(shift.getEndTime());
    bookingDate.setShiftBreakStartTime(shift.getBreakStartTime());
    bookingDate.setShiftBreakEndTime(shift.getBreakEndTime());
    bookingDate.setShiftNoOfHours(shift.getNoOfHours());
    bookingDate.setShiftBreakNoOfHours(shift.getBreakNoOfHours());
    bookingDate.setShiftUseUplift(shift.getUseShiftUplift());
    bookingDate.setShiftUpliftFactor(shift.getUpliftFactor());
    bookingDate.setShiftUpliftValue(shift.getUpliftValue());
    bookingDate.setShiftOvertimeNoOfHours(shift.getOvertimeNoOfHours());
    bookingDate.setShiftOvertimeUpliftFactor(shift.getOvertimeUpliftFactor());
    return bookingDate;
  }
  
  private boolean lockBooking(Integer bookingId)
  {
    logger.debug("About to lock - " + bookingId + " from " + getClass().getName());
    try
    {
      BookingLockHandler.getInstance().addLock(bookingId);
    }
    catch (AlreadyLockedRuntimeException e)
    {
      logger.warn("***** Already locked - " + bookingId);
      return false;
    }
    return true;
  }

  private boolean unlockBooking(Integer bookingId)
  {
    logger.debug("About to unlock - " + bookingId);
    try
    {
      BookingLockHandler.getInstance().removeLock(bookingId);
    }
    catch (NotLockedRuntimeException e)
    {
      logger.error("Error trying to remove lock on bookingId - " + bookingId);
      return false;
    }
    return true;
  }

  private BookingGradeApplicant loadBookingGradeApplicant(Integer applicantId, BookingGrade bookingGrade, BigDecimal wageRate)
  {
    BookingGradeApplicant bookingGradeApplicant = new BookingGradeApplicant();
    bookingGradeApplicant.setBookingGradeId(bookingGrade.getBookingGradeId());
    bookingGradeApplicant.setApplicantId(applicantId);
    bookingGradeApplicant.setRate(bookingGrade.getRate());
    bookingGradeApplicant.setPayRate(bookingGrade.getPayRate());
    bookingGradeApplicant.setWtdPercentage(bookingGrade.getWtdPercentage());
    bookingGradeApplicant.setNiPercentage(bookingGrade.getNiPercentage());
    bookingGradeApplicant.setWageRate(wageRate);
    bookingGradeApplicant.setChargeRateVatRate(bookingGrade.getChargeRateVatRate());
    bookingGradeApplicant.setPayRateVatRate(bookingGrade.getPayRateVatRate());
    bookingGradeApplicant.setWtdVatRate(bookingGrade.getWtdVatRate());
    bookingGradeApplicant.setNiVatRate(bookingGrade.getNiVatRate());
    bookingGradeApplicant.setCommissionVatRate(bookingGrade.getCommissionVatRate());
    bookingGradeApplicant.setClientAgencyJobProfileGradeId(bookingGrade.getClientAgencyJobProfileGradeId());
    bookingGradeApplicant.setNoOfChanges(0);
    return bookingGradeApplicant;
  }
  
  private BookingGradeApplicantDate loadBookingGradeApplicantDate(BookingDate bookingDate, BookingGradeApplicant bookingGradeApplicant, Booking booking)
  {
    BigDecimal chargeRateValue = Utilities.round(bookingDate.getValue().multiply(bookingGradeApplicant.getRate().divide(booking.getRate(), 5, RoundingMode.HALF_UP)));
    BigDecimal payRateValue    = Utilities.round(bookingDate.getValue().multiply(bookingGradeApplicant.getPayRate().divide(booking.getRate(), 5, RoundingMode.HALF_UP)));
    BigDecimal wageRateValue   = Utilities.round(bookingDate.getValue().multiply(bookingGradeApplicant.getWageRate().divide(booking.getRate(), 5, RoundingMode.HALF_UP)));
    BookingGradeApplicantDate bookingGradeApplicantDate = new BookingGradeApplicantDate();
    bookingGradeApplicantDate.setBookingDateId(bookingDate.getBookingDateId());
    bookingGradeApplicantDate.setValue(chargeRateValue);
    bookingGradeApplicantDate.setPayRateValue(payRateValue);
    bookingGradeApplicantDate.setWageRateValue(wageRateValue);
    return bookingGradeApplicantDate;
  }

}
