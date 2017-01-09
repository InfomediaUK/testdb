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
import com.helmet.bean.BookingUserEntityMgr;
import com.helmet.bean.Shift;


public class BookingViewTimesheets extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingUserEntityMgr booking = (BookingUserEntityMgr)dynaForm.get("booking");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		booking = mgrService.getBookingUserShifts(booking.getBookingId(), getManagerLoggedIn().getManagerId());

    	// TODO need to check for NULL object ...
        
		if (booking == null) {
	     	return mapping.findForward("illegalaccess");
		}

//		// temporary also in EDIT
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
		
		dynaForm.set("booking", booking); 
		// booking Id needed for locking
		dynaForm.set("bookingId", booking.getBookingId()); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
