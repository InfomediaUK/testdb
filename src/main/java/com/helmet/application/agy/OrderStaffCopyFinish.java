package com.helmet.application.agy;

import java.math.BigDecimal;

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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Address;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingUserEntity;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.DressCode;
import com.helmet.bean.Expense;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.ReasonForRequest;


public class OrderStaffCopyFinish extends AgyAction
{

  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    Integer page = (Integer)dynaForm.get("page");

    if (isCancelled(request)) 
    {
      dynaForm.set("page", page - 1);
      return mapping.findForward("back");
    }

    // checks !!!
    ClientUser client = (ClientUser)dynaForm.get("client");
    if (client.getClientId() == null || client.getClientId() == 0) { return mapping.findForward("orderStaff"); }

    Integer bookingsToCreate = (Integer)dynaForm.get("bookingsToCreate");
    ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
    LocationUser locationUser = (LocationUser)dynaForm.get("location");
    JobProfileUser jobProfileUser = (JobProfileUser)dynaForm.get("jobProfile");
    Boolean singleCandidate = (Boolean)dynaForm.get("singleCandidate");
    Boolean cvRequired = (Boolean)dynaForm.get("cvRequired");
    Boolean interviewRequired = (Boolean)dynaForm.get("interviewRequired");
    Boolean canProvideAccommodation = (Boolean)dynaForm.get("canProvideAccommodation");
    Boolean carRequired = (Boolean)dynaForm.get("carRequired");
    DressCode dressCode = (DressCode)dynaForm.get("dressCode");
    String comments = (String)dynaForm.get("comments");
    String duration = (String)dynaForm.get("duration");
    String bookingReference = (String)dynaForm.get("bookingReference");
    String contactName = (String)dynaForm.get("contactName");
    String contactJobTitle = (String)dynaForm.get("contactJobTitle");
    String contactEmailAddress = (String)dynaForm.get("contactEmailAddress");
    String contactTelephoneNumber = (String)dynaForm.get("contactTelephoneNumber");
    String accountContactName = (String)dynaForm.get("accountContactName");
    Address accountContactAddress = (Address)dynaForm.get("accountContactAddress");
    String accountContactEmailAddress = (String)dynaForm.get("accountContactEmailAddress");
    Boolean autoFill = (Boolean)dynaForm.get("autoFill");
    Boolean autoActivate = (Boolean)dynaForm.get("autoActivate");
    String reasonForRequestText = (String)dynaForm.get("reasonForRequestText");
    String expensesText = (String)dynaForm.get("expensesText");
    BigDecimal rate = (BigDecimal)dynaForm.get("hourlyRate");
    BigDecimal totalHours = (BigDecimal)dynaForm.get("totalHours");
    BigDecimal value = (BigDecimal)dynaForm.get("rrp");

    BookingUserEntity booking = (BookingUserEntity)dynaForm.get("booking");

    ClientAgencyJobProfileGradeUser[] bookingGrades = (ClientAgencyJobProfileGradeUser[])dynaForm.get("clientAgencyJobProfileGradeUserArray");
    String[] selectedExpenses = (String[])dynaForm.get("selectedExpenses");

    AgyService agyService = ServiceFactory.getInstance().getAgyService();

    BookingDate[] bookingDates = (BookingDate[])dynaForm.get("bookingDates");

    Integer shiftId = null;
    Integer lastBookingDateShiftId = 0;
    boolean oneShiftForBookingDates = true;
    for (BookingDate bookingDate : bookingDates)
    {
      if (lastBookingDateShiftId.equals(0))
      {
        // first one
        lastBookingDateShiftId = bookingDate.getShiftId();
      }
      if (!bookingDate.getShiftId().equals(lastBookingDateShiftId))
      {
        // more than one shift selected so set the boolean to false and break out of loop
        oneShiftForBookingDates = false;
        break;
      }
    }
    if (oneShiftForBookingDates)
    {
      shiftId = lastBookingDateShiftId;
    }

    Expense[] bookingExpenses = new Expense[selectedExpenses.length - 1]; // array includes a dummy 0 entry - checkbox related!
    int e = 0;
    for (String expenseIdStr : selectedExpenses)
    {
      int expenseId = Integer.parseInt(expenseIdStr);
      if (expenseId > 0)
      {
        bookingExpenses[e] = agyService.getExpense(expenseId);
        e++;
      }
    }

    Integer dressCodeId = null;
    if (dressCode != null && dressCode.getDressCodeId() != null)
    {
      dressCodeId = dressCode.getDressCodeId();
    }

    Booking newBooking = new Booking();
    newBooking.setReasonForRequestId(reasonForRequest.getReasonForRequestId());
    newBooking.setLocationId(locationUser.getLocationId());
    newBooking.setJobProfileId(jobProfileUser.getJobProfileId());
    newBooking.setShiftId(shiftId);
    newBooking.setDressCodeId(dressCodeId);
    newBooking.setSingleCandidate(singleCandidate);
    newBooking.setCvRequired(cvRequired);
    newBooking.setInterviewRequired(interviewRequired);
    newBooking.setCanProvideAccommodation(canProvideAccommodation);
    newBooking.setCarRequired(carRequired);
    //
    newBooking.setComments(comments);
    newBooking.setDuration(duration);
    newBooking.setBookingReference(bookingReference);
    newBooking.setContactName(contactName);
    newBooking.setContactJobTitle(contactJobTitle);
    newBooking.setContactEmailAddress(contactEmailAddress);
    newBooking.setContactTelephoneNumber(contactTelephoneNumber);
    newBooking.setAccountContactName(accountContactName);
    newBooking.setAccountContactAddress(accountContactAddress);
    newBooking.setAccountContactEmailAddress(accountContactEmailAddress);
    newBooking.setAutoFill(autoFill);
    newBooking.setAutoActivate(autoActivate);
    newBooking.setReasonForRequestText(reasonForRequestText);
    newBooking.setExpensesText(expensesText);

    newBooking.setRate(rate);
    newBooking.setNoOfHours(totalHours);
    newBooking.setValue(value);

    int rc = 0;
    StringBuffer bookingIds = new StringBuffer();
    for (int i = 0; i < bookingsToCreate; i++)
    {
      rc += agyService.insertBooking(newBooking, bookingDates, bookingGrades, bookingExpenses, getConsultantLoggedIn().getConsultantId());
      if (bookingIds.length() > 0)
      {
        bookingIds.append(",");
      }
      bookingIds.append(newBooking.getBookingId());
    }    
    ActionForward actionForward = mapping.findForward("success");

    return new ActionForward(actionForward.getName(), actionForward.getPath() + "?bookingIds=" + bookingIds.toString(), actionForward.getRedirect());
  }

}
