package com.helmet.application.app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AppService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.Utilities;
import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.BookingDateHour;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.ClientAgency;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.Shift;
import com.helmet.bean.Uplift;

public class BookingDateWorkedProcess extends AppAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	BookingDateUserApplicant bookingDate = (BookingDateUserApplicant)dynaForm.get("bookingDate");
    	String comment = bookingDate.getComment();
    	
     	AppService appService = ServiceFactory.getInstance().getAppService();
		
        bookingDate = appService.getBookingDateUserApplicantForApplicantForBookingDate(bookingDate.getBookingDateId());
        // should probably check noOfChanges ????

     	String workedStartHour = (String)dynaForm.get("workedStartHour");
     	String workedStartMinute = (String)dynaForm.get("workedStartMinute");
     	String workedEndHour = (String)dynaForm.get("workedEndHour");
     	String workedEndMinute = (String)dynaForm.get("workedEndMinute");
     	String workedBreakStartHour = (String)dynaForm.get("workedBreakStartHour");
     	String workedBreakStartMinute = (String)dynaForm.get("workedBreakStartMinute");
     	String workedBreakEndHour = (String)dynaForm.get("workedBreakEndHour");
     	String workedBreakEndMinute = (String)dynaForm.get("workedBreakEndMinute");

     	// if the applicant has NOT worked the shift the start and end time may be 'empty'
     	// if this is the case then a comment MUST be entered
     	// break validation must be added to check that the break exists within the start end end times - remember could cross midnight
     	
		if (workedStartHour != null && !"".equals(workedStartHour)
		 && workedStartMinute != null && !"".equals(workedStartMinute)
		 && workedEndHour != null && !"".equals(workedEndHour)
		 && workedEndMinute != null && !"".equals(workedEndMinute)) {
     	
			// times have been entered so shift has been worked
			
	     	bookingDate.setWorkedStartTime(Time.valueOf(workedStartHour + ":" + workedStartMinute + ":00"));
	     	bookingDate.setWorkedEndTime(Time.valueOf(workedEndHour + ":" + workedEndMinute + ":00"));
	
			if (workedBreakStartHour != null && !"".equals(workedBreakStartHour)
			 && workedBreakStartMinute != null && !"".equals(workedBreakStartMinute)
			 && workedBreakEndHour != null && !"".equals(workedBreakEndHour)
			 && workedBreakEndMinute != null && !"".equals(workedBreakEndMinute)) {
				bookingDate.setWorkedBreakStartTime(Time.valueOf(workedBreakStartHour + ":"	+ workedBreakStartMinute + ":00"));
				bookingDate.setWorkedBreakEndTime(Time.valueOf(workedBreakEndHour + ":"	+ workedBreakEndMinute + ":00"));
			}
			else {
				bookingDate.setWorkedBreakStartTime(null);
				bookingDate.setWorkedBreakEndTime(null);
				
			}

			long milliSecondsPerHour = 1000 * 60 * 60;
			long milliSecondsPerDay = 24 * milliSecondsPerHour;
			long diffInMilliSeconds = 0;
	
			// if end is before start the shift runs through midnight so
			if (bookingDate.getWorkedStartTime().before(bookingDate.getWorkedEndTime())) {
				diffInMilliSeconds = bookingDate.getWorkedEndTime().getTime() - bookingDate.getWorkedStartTime().getTime();
			} else {
				diffInMilliSeconds = (milliSecondsPerDay - bookingDate.getWorkedStartTime().getTime()) + bookingDate.getWorkedEndTime().getTime();
			}
	
			BigDecimal diffInHours = new BigDecimal(diffInMilliSeconds / (milliSecondsPerHour * 1.0F));
	
			BigDecimal breakDiffInHours = new BigDecimal(0);
			
			if (bookingDate.getWorkedBreakStartTime() != null && bookingDate.getWorkedBreakEndTime() != null) {
				long breakDiffInMilliSeconds = 0;
				// if end is before start the break runs through midnight so
				if (bookingDate.getWorkedBreakStartTime().before(bookingDate.getWorkedBreakEndTime())) {
					breakDiffInMilliSeconds = bookingDate.getWorkedBreakEndTime().getTime() - bookingDate.getWorkedBreakStartTime().getTime();
				}
				else {
					breakDiffInMilliSeconds = (milliSecondsPerDay - bookingDate.getWorkedBreakStartTime().getTime()) + bookingDate.getWorkedBreakEndTime().getTime(); 
				}
				breakDiffInHours = new BigDecimal(breakDiffInMilliSeconds / (milliSecondsPerHour * 1.0F));
				diffInHours = diffInHours.subtract(breakDiffInHours);
			}
			
			bookingDate.setWorkedNoOfHours(diffInHours);
			bookingDate.setWorkedBreakNoOfHours(breakDiffInHours);

		}	
		else {
			// no times entered
			
			// TODO - check none have been entered and not just partially entered - AND ensure a comment has been entered

			bookingDate.setWorkedStartTime(null);
			bookingDate.setWorkedEndTime(null);
			bookingDate.setWorkedBreakStartTime(null);
			bookingDate.setWorkedBreakEndTime(null);
			bookingDate.setWorkedNoOfHours(new BigDecimal(0));
			bookingDate.setWorkedBreakNoOfHours(new BigDecimal(0));

		}
			
		bookingDate.setComment(comment);
		
     	//
     	
		Shift shift = new Shift();
		// should be got from the actual shift for the date
		shift.setUseShiftUplift(bookingDate.getShiftUseUplift());
		shift.setUpliftFactor(bookingDate.getShiftUpliftFactor());
		shift.setUpliftValue(bookingDate.getShiftUpliftValue());
		shift.setNoOfHours(bookingDate.getWorkedNoOfHours()); // noOfHours used for calculation
		//
		shift.setStartTime(bookingDate.getWorkedStartTime());
		shift.setEndTime(bookingDate.getWorkedEndTime());
		shift.setBreakStartTime(bookingDate.getWorkedBreakStartTime());
		shift.setBreakEndTime(bookingDate.getWorkedBreakEndTime());
		
		// TODO - rubbish but ... need some way of getting the client
        Integer clientId = AppUtilities.getCurrentClient(request).getClientId();
        
        List<PublicHoliday> publicHolidays = appService.getPublicHolidaysForClient(clientId);
        List<Uplift> uplifts = appService.getUpliftsForClient(clientId);
        
        //
        // the following will update the bookingDate workedValue (chargeRate) and workedPayRateValue and also return the 
        // hourly records that made up these values - including the break record(s)
        // these should be saved for the bookingDate
        //
        List<BookingDateHour> bookingDateHours = Utilities.calculateIt(bookingDate, publicHolidays, uplifts);

        Integer agencyId = AppUtilities.getCurrentAgency(request).getAgencyId();

        // 
        // TODO - the following should be getting the fee details based on the bookingDate.bookingDate rather than just the current value
        //
        // get the fee details
		ClientAgency clientAgency = appService.getClientAgencyForClientAndAgency(clientId, agencyId, bookingDate.getBookingDate());
		bookingDate.setFeePerShift(clientAgency.getFeePerShift());
		bookingDate.setFeePerHour(clientAgency.getFeePerHour());
		bookingDate.setFeePercentage(clientAgency.getFeePercentage());
		// if feePerHour is > 0 multiply the noOfHours worked by the feePerHour
		// else multiply the worked by the chargeRate value by the feePercentage 
        BigDecimal feeValue = new BigDecimal(0); 
        if (bookingDate.getFeePerShift().compareTo(new BigDecimal(0)) > 0) {
            // charging per shift
        	feeValue = bookingDate.getFeePerShift();
        }
        else if (bookingDate.getFeePerHour().compareTo(new BigDecimal(0)) > 0) {
        	// charging fixed price per hour
        	feeValue = Utilities.round(bookingDate.getWorkedNoOfHours().multiply(bookingDate.getFeePerHour()));
        }
        else {        	
        	// charging as a percentage of charge rate
        	feeValue = Utilities.round(bookingDate.getWorkedChargeRateValue().multiply(bookingDate.getFeePercentage()));		
        }
		bookingDate.setFeeValue(feeValue);

     	int rowCount = appService.updateBookingDateWorked(bookingDate, bookingDateHours, getApplicantLoggedIn().getApplicantId());
    	
    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");

    }

}