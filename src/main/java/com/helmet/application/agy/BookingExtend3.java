package com.helmet.application.agy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.LocationJobProfile;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.Shift;
import com.helmet.bean.Uplift;


public class BookingExtend3 extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	Integer page = (Integer)dynaForm.get("page");
    	if (isCancelled(request)) {
    		dynaForm.set("page", page - 1);
         	return mapping.findForward("back");
    	}

    	BookingDateUser[] bookingDates = (BookingDateUser[])dynaForm.get("bookingDates");

    	List<Shift> shifts = (List<Shift>)dynaForm.get("shifts");
    	
     	BigDecimal totalHours = new BigDecimal(0);
     	for (BookingDateUser bookingDate: bookingDates) {
     		for (Shift shift: shifts){
     			if (shift.getShiftId().equals(bookingDate.getShiftId())) {
     				totalHours = totalHours.add(shift.getNoOfHours());
     				
//     				bookingDate.setShift(shift);
     				
     				bookingDate.setShiftName(shift.getName());
     				bookingDate.setShiftCode(shift.getCode());
     				bookingDate.setShiftUseUplift(shift.getUseShiftUplift());
     				bookingDate.setShiftUpliftFactor(shift.getUpliftFactor());
     				bookingDate.setShiftUpliftValue(shift.getUpliftValue());
     				bookingDate.setShiftStartTime(shift.getStartTime());
     				bookingDate.setShiftEndTime(shift.getEndTime());
     				bookingDate.setShiftBreakStartTime(shift.getBreakStartTime());
     				bookingDate.setShiftBreakEndTime(shift.getBreakEndTime());
     				bookingDate.setShiftNoOfHours(shift.getNoOfHours());
     				bookingDate.setShiftBreakNoOfHours(shift.getBreakNoOfHours());
					bookingDate.setShiftOvertimeNoOfHours(shift.getOvertimeNoOfHours());
					bookingDate.setShiftOvertimeUpliftFactor(shift.getOvertimeUpliftFactor());
     				
     				break;
     			}
     		}
            // ensure all bookingDates have had a shift assigned
            if (bookingDate.getShiftStartTime() == null) {
        		ActionMessages errors = new ActionMessages();
        		MessageResources messageResources = getResources(request);
                errors.add("bookingExtend", new ActionMessage("errors.pleaseMakeASelection"));
                saveErrors(request, errors);
           		return mapping.getInputForward();
            }
     	}
     	dynaForm.set("totalHours", totalHours);
    	
    	AgyService agyService = ServiceFactory.getInstance().getAgyService();

    	BookingGradeAgyEntity bookingGrade = (BookingGradeAgyEntity)dynaForm.get("bookingGrade");
		
    	// CALCULATE 'UPLIFTED' RATE

        List<PublicHoliday> publicHolidays = agyService.getPublicHolidaysForClient(bookingGrade.getClientId());
        List<Uplift> uplifts = agyService.getUpliftsForClient(bookingGrade.getClientId());
    	
        BigDecimal hourlyRate = bookingGrade.getBookingRate();
     	
        dynaForm.set("hourlyRate", hourlyRate);
        
        BigDecimal rrp = Utilities.calculateIt(bookingDates, hourlyRate, publicHolidays, uplifts, true);
        
        BookingGradeApplicantUser bookingGradeApplicant = (BookingGradeApplicantUser)dynaForm.get("bookingGradeApplicant"); 
        
		BigDecimal chargeRateValueTotal = new BigDecimal(0);
		BigDecimal payRateValueTotal = new BigDecimal(0);
		BigDecimal wageRateValueTotal = new BigDecimal(0);

		for (BookingDateUser bookingDate: bookingDates) {

    		BigDecimal chargeRateValue = Utilities.round(bookingDate.getValue().multiply(bookingGradeApplicant.getRate().divide(hourlyRate, 5, RoundingMode.HALF_UP)));
    		BigDecimal payRateValue = Utilities.round(bookingDate.getValue().multiply(bookingGradeApplicant.getPayRate().divide(hourlyRate, 5, RoundingMode.HALF_UP)));
    		BigDecimal wageRateValue = Utilities.round(bookingDate.getValue().multiply(bookingGradeApplicant.getWageRate().divide(hourlyRate, 5, RoundingMode.HALF_UP)));

     		bookingDate.setChargeRateValue(chargeRateValue);
     		bookingDate.setPayRateValue(payRateValue);
     		bookingDate.setWageRateValue(wageRateValue);
     		
     		chargeRateValueTotal = chargeRateValueTotal.add(chargeRateValue); 
     		payRateValueTotal = payRateValueTotal.add(payRateValue); 
     		wageRateValueTotal = wageRateValueTotal.add(wageRateValue); 
    	
     	}

     	dynaForm.set("chargeRateValueTotal", chargeRateValueTotal);
     	dynaForm.set("payRateValueTotal", payRateValueTotal);
     	dynaForm.set("wageRateValueTotal", wageRateValueTotal);
     	
     	dynaForm.set("rrp", rrp);
     	
     	
     	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
