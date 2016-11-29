package com.helmet.application.agy;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.ClientUser;
import com.helmet.bean.Country;
import com.helmet.bean.DressCode;
import com.helmet.bean.Expense;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Uplift;


public class OrderStaffCopy extends OrderStaffCopyBase 
{

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) 
    {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    	
      AgyService agyService = ServiceFactory.getInstance().getAgyService();
      BookingGradeAgyEntity bookingGradeAgyEntity = (BookingGradeAgyEntity) dynaForm.get("bookingGrade");
      // re-initialize the form
      dynaForm.initialize(mapping);
      bookingGradeAgyEntity = agyService.getBookingGradeAgyEntity(bookingGradeAgyEntity.getBookingGradeId(), getConsultantLoggedIn().getAgencyId());
      dynaForm.set("bookingGrade", bookingGradeAgyEntity);
      if (bookingGradeAgyEntity == null) { return mapping.findForward("illegalaccess"); }
      List<BookingExpense> listBookingExpense = bookingGradeAgyEntity.getBookingExpenses();
      dynaForm.set("listBookingExpense", listBookingExpense);
      String[] selectedExpenses = new String[listBookingExpense.size() + 1];
      selectedExpenses[0] = "0"; // the DUMMY expense! 
      int e = 1;
      for (BookingExpense bookingExpense : listBookingExpense)
      {
        selectedExpenses[e++] = bookingExpense.getExpenseId().toString();
      }
      dynaForm.set("selectedExpenses", selectedExpenses);
      Expense[] expenseArray = buildExpenseArray(selectedExpenses, agyService);
      dynaForm.set("expenseArray", expenseArray);
      // Get Client.
      ClientUser client = agyService.getClientUser(bookingGradeAgyEntity.getClientId());
      dynaForm.set("client", client);
      // Get Booking.
      Booking booking = agyService.getBooking(bookingGradeAgyEntity.getBookingId());
      ReasonForRequest reasonForRequest = agyService.getReasonForRequest(booking.getReasonForRequestId());
      DressCode dressCode = agyService.getDressCode(booking.getDressCodeId());
      Country country = agyService.getCountry(booking.getAccountContactAddress().getCountryId());
      loadFormFromBooking(dynaForm, booking, reasonForRequest, dressCode, country);
      // Get LocationUser.
      LocationUser locationUser = agyService.getLocationUser(bookingGradeAgyEntity.getLocationId());
      List<Expense> listExpense = agyService.getExpensesForLocation(locationUser.getLocationId());
      loadFormFromLocationUser(dynaForm, locationUser, listExpense);
      // Get JobProfileUser.
      JobProfileUser jobProfileUser = agyService.getJobProfileUser(bookingGradeAgyEntity.getJobProfileId());
      loadFormFromJobProfileUser(dynaForm, jobProfileUser);
      BookingDate[] bookingDates = buildBookingDatesArray(booking, bookingGradeAgyEntity.getBookingDateUsers());
      dynaForm.set("bookingDates", bookingDates);
      String dates = bookingGradeAgyEntity.getBookingDatesAsString();
      dynaForm.set("shiftName", bookingGradeAgyEntity.getShiftName());
      dynaForm.set("dates", dates);
      dynaForm.set("origDates", dates);
      List<PublicHoliday> publicHolidays = agyService.getPublicHolidaysForClient(client.getClientId());
      List<Uplift> uplifts = agyService.getUpliftsForClient(client.getClientId());
      Boolean undefinedShift = isUndefinedShift(bookingDates);
      loadFormWithClientAgencyJobProfileGradeStuff(dynaForm, agyService, jobProfileUser, bookingGradeAgyEntity.getGradeId(), bookingDates, publicHolidays, uplifts, booking.getNoOfHours(), booking.getRate(), booking.getValue(), undefinedShift);    
    	return mapping.findForward("success");
    }

    private void loadFormFromBooking(DynaValidatorForm dynaForm, Booking booking, ReasonForRequest reasonForRequest, DressCode dressCode, Country country)
    {
      dynaForm.set("singleCandidate", booking.getSingleCandidate());
      dynaForm.set("cvRequired", booking.getCvRequired());
      dynaForm.set("interviewRequired", booking.getInterviewRequired());
      dynaForm.set("canProvideAccommodation", booking.getCanProvideAccommodation());
      dynaForm.set("carRequired", booking.getCarRequired());
      dynaForm.set("comments", booking.getComments());
      dynaForm.set("duration", booking.getDuration());
      dynaForm.set("bookingReference", booking.getBookingReference());
      dynaForm.set("contactName", booking.getContactName());
      dynaForm.set("contactJobTitle", booking.getContactJobTitle());
      dynaForm.set("contactEmailAddress", booking.getContactEmailAddress());
      dynaForm.set("contactTelephoneNumber", booking.getContactTelephoneNumber());
      dynaForm.set("accountContactName", booking.getAccountContactName());
      dynaForm.set("accountContactAddress", booking.getAccountContactAddress());
      dynaForm.set("accountContactEmailAddress", booking.getAccountContactEmailAddress());
      dynaForm.set("autoFill", booking.getAutoFill());
      dynaForm.set("autoActivate", booking.getAutoActivate());
      dynaForm.set("reasonForRequestText", booking.getReasonForRequestText());
      dynaForm.set("expensesText", booking.getExpensesText());
      dynaForm.set("totalHours", booking.getNoOfHours());
      dynaForm.set("hourlyRate", booking.getRate());
      dynaForm.set("noOfDates", booking.getNoOfBookingDates());
      dynaForm.set("rrp", booking.getValue());
      dynaForm.set("reasonForRequest", reasonForRequest);
      dynaForm.set("dressCode", dressCode == null ? new DressCode() : dressCode);
      dynaForm.set("accountContactCountryName", country.getName());
    }
    
    private void loadFormFromLocationUser(DynaValidatorForm dynaForm, LocationUser locationUser, List<Expense> listExpense)
    {
      dynaForm.set("location", locationUser);
      dynaForm.set("origLocationId", locationUser.getLocationId());
      dynaForm.set("listExpense", listExpense); // NOT NEEDED YET...
    }
    
    private void loadFormFromJobProfileUser(DynaValidatorForm dynaForm, JobProfileUser jobProfileUser)
    {
      dynaForm.set("jobProfile", jobProfileUser);
      dynaForm.set("origJobProfileId", jobProfileUser.getJobProfileId());
    }

    private BookingDate[] buildBookingDatesArray(Booking booking, List<BookingDateUser> bookingDateUsers)
    {
      BookingDate[] bookingDates = new BookingDate[booking.getNoOfBookingDates()];
      BigDecimal chargeRateValue = new BigDecimal(0);
      BigDecimal payRateValue    = new BigDecimal(0);
      BigDecimal wageRateValue   = new BigDecimal(0);
      int d = 0;
      if (bookingDateUsers != null)
      {
        for (BookingDateUser bookingDate : bookingDateUsers)
        {
          bookingDates[d++] = bookingDate;
          chargeRateValue = chargeRateValue.add(bookingDate.getChargeRateValue());
          payRateValue = payRateValue.add(bookingDate.getPayRateValue());
          wageRateValue = wageRateValue.add(bookingDate.getWageRateValue());
        }
      }
      return bookingDates;
    }
}
