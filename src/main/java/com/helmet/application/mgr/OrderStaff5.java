package com.helmet.application.mgr;

import java.sql.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

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
import com.helmet.bean.ClientAgencyGrade;
import com.helmet.bean.ClientAgencyJobProfileUser;
import com.helmet.bean.Grade;
import com.helmet.bean.JobProfileUser;
import com.helmet.bean.LocationUser;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.Shift;


public class OrderStaff5 extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	Integer page = (Integer)dynaForm.get("page");
    	if (isCancelled(request)) {
    		dynaForm.set("page", page - 1);
         	return mapping.findForward("back");
    	}
    	
    	ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
    	if (reasonForRequest.getReasonForRequestId() == null || reasonForRequest.getReasonForRequestId() == 0) {
         	return mapping.findForward("orderStaff");
    	}
    	LocationUser locationUser = (LocationUser)dynaForm.get("location");
    	if (locationUser.getLocationId() == null || locationUser.getLocationId() == 0) {
         	return mapping.findForward("orderStaff");
    	}
    	JobProfileUser jobProfileUser = (JobProfileUser)dynaForm.get("jobProfile");
    	if (jobProfileUser.getJobProfileId() == null || jobProfileUser.getJobProfileId() == 0) {
         	return mapping.findForward("orderStaff");
    	}
    	String dates = (String)dynaForm.get("dates");
    	if (dates == null || "".equals(dates)) {
         	return mapping.findForward("orderStaff");
    	}

    	// determine whether the user is going forwards (next) or backwards (back)
    	boolean userPressedNext = page == 4;
    	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		List<Shift> shifts = mgrService.getShiftsForLocation(locationUser.getLocationId());
		int noOfShifts = shifts.size();

     	dynaForm.set("shifts", shifts);
     	dynaForm.set("noOfShifts", shifts.size());

		boolean singleShift = noOfShifts == 1;
     	
     	if (userPressedNext) {
     		// going forward ... 

         	BookingDate[] oldBookingDates = (BookingDate[])dynaForm.get("bookingDates");
     		
     		int noOfDates = new java.util.StringTokenizer(dates, ",").countTokens();
         	dynaForm.set("noOfDates", new Integer(noOfDates));
     		// load the dates WITHOUT shifts - unless there is only one !!!
        	StringTokenizer st = new StringTokenizer(dates, ",");
            //
        	// currently overwriting any previous selections - would be good to keep existing if possible
        	//
        	BookingDate[] bookingDates = new BookingDate[st.countTokens()];
        	int d = 0;
    		while (st.hasMoreTokens()) {
    			BookingDate bookingDate = new BookingDate();
    			bookingDate.setBookingDate(Date.valueOf(st.nextToken()));
    			if (singleShift) {
    				bookingDate.setShiftId(shifts.get(0).getShiftId());
//    				bookingDate.setShift(shifts.get(0));
    			}
    			else {
    				if (oldBookingDates != null) {
    					for (BookingDate oldBookingDate: oldBookingDates) {
    						if (oldBookingDate.getBookingDate().equals(bookingDate.getBookingDate())) {
    		    				if (oldBookingDate.getShiftId() != null && oldBookingDate.getShiftId() > 0) {
        							bookingDate.setShiftId(oldBookingDate.getShiftId());
        							bookingDate.setShiftName(oldBookingDate.getShiftName());
        							bookingDate.setShiftCode(oldBookingDate.getShiftCode());
        							bookingDate.setShiftStartTime(oldBookingDate.getShiftStartTime());
        							bookingDate.setShiftEndTime(oldBookingDate.getShiftEndTime());
        							bookingDate.setShiftBreakStartTime(oldBookingDate.getShiftBreakStartTime());
        							bookingDate.setShiftBreakEndTime(oldBookingDate.getShiftBreakEndTime());
        							bookingDate.setShiftNoOfHours(oldBookingDate.getShiftNoOfHours());
        							bookingDate.setShiftBreakNoOfHours(oldBookingDate.getShiftBreakNoOfHours());
        							bookingDate.setShiftUseUplift(oldBookingDate.getShiftUseUplift());
        							bookingDate.setShiftUpliftFactor(oldBookingDate.getShiftUpliftFactor());
        							bookingDate.setShiftUpliftValue(oldBookingDate.getShiftUpliftValue());
        							bookingDate.setShiftOvertimeNoOfHours(oldBookingDate.getShiftOvertimeNoOfHours());
        							bookingDate.setShiftOvertimeUpliftFactor(oldBookingDate.getShiftOvertimeUpliftFactor());
    		    				}
    							break;
    						}
    					}
    				}
    			}
    			bookingDates[d] = bookingDate;
        		d++;
        	}
         	dynaForm.set("bookingDates", bookingDates);
     	}
     	else {
     	    // going backwards so leave the booking dates well alone !!!
     	}
     	
		if (singleShift) {
            // miss out this step and based on the mapping config go to the next
	     	if (userPressedNext) {
	    		dynaForm.set("page", page + 1);
	     		return mapping.findForward("singleShiftSuccess");
		    }
	     	else {
	    		dynaForm.set("page", page - 1);
	         	return mapping.findForward("singleShiftBack");
	     	}
		}

     	return mapping.findForward("success");
    }

}
