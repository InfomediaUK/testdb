package com.helmet.application.mgr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingGradeUser;
import com.helmet.bean.BookingUserEntityMgr;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;
import com.helmet.bean.DressCode;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.ReasonForRequest;


public class BookingEdit extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingUserEntityMgr booking = (BookingUserEntityMgr)dynaForm.get("booking");

    	dynaForm.initialize(mapping);

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		booking = mgrService.getBookingUserEntityMgr(booking.getBookingId(), getManagerLoggedIn().getManagerId());

    	// TODO need to check for NULL object ...
        
		if (booking == null) {
	     	return mapping.findForward("illegalaccess");
		}

		ReasonForRequest reasonForRequest = mgrService.getReasonForRequest(booking.getReasonForRequestId());
		LocationUser locationUser = mgrService.getLocationUser(booking.getLocationId());
		JobProfileUser jobProfileUser = mgrService.getJobProfileUser(booking.getJobProfileId());
		String dates = booking.getBookingDatesAsString();
//		Shift shift = mgrService.getShift(booking.getShiftId());
		DressCode dressCode = mgrService.getDressCode(booking.getDressCodeId());
		
		List<BookingGradeUser> bookingGradeUsers = booking.getBookingGradeUsers();
		ClientAgencyJobProfileGradeUser[] clientAgencyJobProfileGradeUserArray = new ClientAgencyJobProfileGradeUser[bookingGradeUsers.size()]; 
		int g = 0;
		for (BookingGradeUser bookingGradeUser: bookingGradeUsers){
			clientAgencyJobProfileGradeUserArray[g] = mgrService.getClientAgencyJobProfileGradeUser(bookingGradeUser.getClientAgencyJobProfileGradeId());
			g++;
		}
		
		List<BookingExpense> bookingExpenses = booking.getBookingExpenses();
		String[] selectedExpenses = new String[bookingExpenses.size() + 1];
		selectedExpenses[0] = "0"; // the DUMMY expense! 
		int e = 1;
		for (BookingExpense bookingExpense: bookingExpenses){
			selectedExpenses[e] = bookingExpense.getExpenseId().toString();
			e++;
		}

        BookingDate[] bookingDates = new BookingDate[booking.getBookingDates().size()];
        int i = 0;
        for (BookingDate bookingDate: booking.getBookingDates()) {
        	bookingDates[i] = bookingDate;
        	i++;
        }
		
        // temporary also in VIEW
//        List<Shift> shifts = mgrService.getShiftsForLocation(booking.getLocationId());
//        BookingDate[] bookingDates = new BookingDate[booking.getBookingDates().size()];
//        int i = 0;
//        for (BookingDate bookingDate: booking.getBookingDates()) {
//     		for (Shift theShift: shifts){
//     			if (theShift.getShiftId().equals(bookingDate.getShiftId())) {
//     				bookingDate.setShift(theShift);
//     				bookingDates[i] = bookingDate;
//                    i++;
//     				break;
//     			}
//     		}
//        }
		
        dynaForm.set("bookingDates", bookingDates);
		
        dynaForm.set("reasonForRequest", reasonForRequest);
        dynaForm.set("reasonForRequestText", booking.getReasonForRequestText());
        dynaForm.set("location", locationUser);
        dynaForm.set("jobProfile", jobProfileUser);
        dynaForm.set("dates", dates);
//        dynaForm.set("shift", shift);
        dynaForm.set("clientAgencyJobProfileGradeUserArray", clientAgencyJobProfileGradeUserArray);
        dynaForm.set("singleCandidate", booking.getSingleCandidate());
        dynaForm.set("cvRequired", booking.getCvRequired());
        dynaForm.set("interviewRequired", booking.getInterviewRequired());
        dynaForm.set("canProvideAccommodation", booking.getCanProvideAccommodation());
        dynaForm.set("carRequired", booking.getCarRequired());
        if (dressCode != null) {
          dynaForm.set("dressCode", dressCode);
        }
        dynaForm.set("selectedExpenses", selectedExpenses);
        dynaForm.set("expensesText", booking.getExpensesText());
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
        
        dynaForm.set("hourlyRate", booking.getRate());
        dynaForm.set("rrp", booking.getValue());
        
        dynaForm.set("booking", booking); 

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
