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

public class BookingDatesView extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	String[] selectedBookingDates = (String[])dynaForm.get("selectedBookingDates");

    	String bookingDateIdStrs = "";
    	
    	for (String bookingDateIdStr: selectedBookingDates) {
    		if (!"".equals(bookingDateIdStrs)) {
    			bookingDateIdStrs = bookingDateIdStrs + ",";
    		}
			bookingDateIdStrs = bookingDateIdStrs + bookingDateIdStr;
    	}
    	
     	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
     	
     	List bookingDateUserApplicants = mgrService.getBookingDateUserApplicantsForManagerAndBookingDates(getManagerLoggedIn().getManagerId(), bookingDateIdStrs);
		
		// in ancestor class
		sortShiftTotals(bookingDateUserApplicants, dynaForm);

		dynaForm.set("bookingDateIdStrs", bookingDateIdStrs);
		dynaForm.set("list", bookingDateUserApplicants);
    	
    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");

    }

}