package com.helmet.application.mgr;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.BookingUser;


public class BookingSearchProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		List<BookingUser> list = null;
		
		String bookingIdStr = (String)dynaForm.get("bookingId");
		String bookingStatusStr = (String)dynaForm.get("bookingStatus");
		String singleCandidateStr = (String)dynaForm.get("singleCandidate");
		String fromDateStr = (String)dynaForm.get("fromDate");
		String toDateStr = (String)dynaForm.get("toDate");
//		String workedStatusStr = (String)dynaForm.get("workedStatus");
//		String bookingGradeApplicantDateStatusStr = (String)dynaForm.get("bookingGradeApplicantDateStatus");

		String siteIdStr = (String)dynaForm.get("siteId");
		String locationIdStr = (String)dynaForm.get("locationId");
		String jobProfileIdStr = (String)dynaForm.get("jobProfileId");
		
		Date fromDate = null;
		Date toDate = null;
		// sort out the dates
		if (fromDateStr != null && !"".equals(fromDateStr) && toDateStr != null && !"".equals(toDateStr)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			
			try {
				fromDate = new Date(sdf.parse(fromDateStr).getTime());
			}
			catch (ParseException e) {
				ActionMessages errors = new ActionMessages();
				MessageResources messageResources = getResources(request);
	            errors.add("shiftSearch", new ActionMessage("errors.invalid", messageResources.getMessage("label.fromDate")));
	            saveErrors(request, errors);
	    		return mapping.getInputForward();
			}
			try {
				toDate = new Date(sdf.parse(toDateStr).getTime());
			}
			catch (ParseException e) {
				ActionMessages errors = new ActionMessages();
				MessageResources messageResources = getResources(request);
	            errors.add("shiftSearch", new ActionMessage("errors.invalid", messageResources.getMessage("label.toDate")));
	            saveErrors(request, errors);
	    		return mapping.getInputForward();
			}
			System.out.println(">>>>> " + fromDate + " ----- " + toDate + " <<<<<");
		}		
		
		// sort out single candidate
		Boolean singleCandidate = "".equals(singleCandidateStr) ? null : "true".equals(singleCandidateStr);
		Integer siteId = "".equals(siteIdStr) ? null : Integer.parseInt(siteIdStr);
		Integer locationId = "".equals(locationIdStr) ? null : Integer.parseInt(locationIdStr);
		Integer jobProfileId = "".equals(jobProfileIdStr) ? null : Integer.parseInt(jobProfileIdStr);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
        
		if (bookingIdStr != null && !"".equals(bookingIdStr)) {
			// bookingId
			list = mgrService.getBookingUsersForManagerAndBooking(getManagerLoggedIn().getManagerId(), Integer.parseInt(bookingIdStr));
		}
		else if (bookingStatusStr != null && !"".equals(bookingStatusStr)) {
			//
			// check to see if a date range has also been entered
			//
			if (fromDateStr != null && !"".equals(fromDateStr) && toDateStr != null && !"".equals(toDateStr)) {
				// date specific
				list = mgrService.getBookingUsersForManagerAndStatusAndDateRange(getManagerLoggedIn().getManagerId(), Integer.parseInt(bookingStatusStr), 
                        singleCandidate, siteId, locationId, jobProfileId, fromDate, toDate);
			}
			else {			
				// not date specific
				list = mgrService.getBookingUsersForManagerAndStatus(getManagerLoggedIn().getManagerId(), Integer.parseInt(bookingStatusStr), 
					                                             singleCandidate, siteId, locationId, jobProfileId);
			}
		}
		else if (fromDateStr != null && !"".equals(fromDateStr) && toDateStr != null && !"".equals(toDateStr)) {
			// date specific
			list = mgrService.getBookingUsersForManagerAndDateRange(getManagerLoggedIn().getManagerId(),  
                    singleCandidate, siteId, locationId, jobProfileId, fromDate, toDate);
		}
//		else if (workedStatusStr != null && !"".equals(workedStatusStr)) {
//			list = mgrService.getBookingUsersForManagerAndWorkedStatus(getManagerLoggedIn().getManagerId(), Integer.parseInt(workedStatusStr));
//		}
//		else if (bookingGradeApplicantDateStatusStr != null && !"".equals(bookingGradeApplicantDateStatusStr)) {
//			list = mgrService.getBookingUsersForManagerAndBookingGradeApplicantDateStatus(getManagerLoggedIn().getManagerId(), Integer.parseInt(bookingGradeApplicantDateStatusStr));
//		}
		else {
			list = mgrService.getBookingUsersForManager(getManagerLoggedIn().getManagerId(), singleCandidate, siteId, locationId, jobProfileId);
		}

		// in ancestor class
		sortBookingTotals(list, dynaForm);
 
     	dynaForm.set("list", list);
    	
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
