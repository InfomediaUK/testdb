package com.helmet.application.mgr;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
import com.helmet.application.comparator.BookingDateUserApplicantComparatorByApplicantName;
import com.helmet.application.comparator.BookingDateUserApplicantComparatorByBookingDateAndStartTime;
import com.helmet.application.comparator.BookingDateUserApplicantComparatorByJobProfileName;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUserApplicant;


public class ShiftSearchProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		String bookingIdStr = (String)dynaForm.get("bookingId");
		String shiftNoStr = (String)dynaForm.get("shiftNo");
		String loginIdStr = (String)dynaForm.get("loginId");
		String invoiceIdStr = (String)dynaForm.get("invoiceId");
		String bookingDateStatusStr = (String)dynaForm.get("bookingDateStatus");
		String singleCandidateStr = (String)dynaForm.get("singleCandidate");
		String activatedStr = (String)dynaForm.get("activated");
		String fromDateStr = (String)dynaForm.get("fromDate");
		String toDateStr = (String)dynaForm.get("toDate");
		String workedStatusStr = (String)dynaForm.get("workedStatus");
		
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
 		Integer bookingId = "".equals(bookingIdStr) ? null : Integer.parseInt(bookingIdStr);
 		Integer bookingDateId = "".equals(shiftNoStr) ? null : Integer.parseInt(shiftNoStr.substring(shiftNoStr.indexOf(".") + 1));
 		Integer bookingGradeApplicantId = "".equals(loginIdStr) ? null : Integer.parseInt(loginIdStr.substring(0, loginIdStr.indexOf("-")));
 		Integer invoiceId = "".equals(invoiceIdStr) ? null : Integer.parseInt(invoiceIdStr);
 		Boolean singleCandidate = "".equals(singleCandidateStr) ? null : "true".equals(singleCandidateStr);
 		Boolean activated = "".equals(activatedStr) ? null : "true".equals(activatedStr);
		Integer bookingDateStatus = "".equals(bookingDateStatusStr) ? null : Integer.parseInt(bookingDateStatusStr);
		Integer workedStatus = "".equals(workedStatusStr) ? null : Integer.parseInt(workedStatusStr);
 		Integer siteId = "".equals(siteIdStr) ? null : Integer.parseInt(siteIdStr);
		Integer locationId = "".equals(locationIdStr) ? null : Integer.parseInt(locationIdStr);
		Integer jobProfileId = "".equals(jobProfileIdStr) ? null : Integer.parseInt(jobProfileIdStr);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
        
		List<BookingDateUserApplicant> list = mgrService.getBookingDateUserApplicantsForManager(getManagerLoggedIn().getManagerId(), 
				                                bookingId, bookingDateId, bookingGradeApplicantId, invoiceId, bookingDateStatus, workedStatus,                                                  
				                                singleCandidate, activated, siteId, locationId, jobProfileId, fromDate, toDate);

		String actionPath = mapping.getPath();
		
		// default BD.BOOKINGDATE + BD.SHIFTSTARTTIME
		
		if (actionPath.equals("/shiftSearchProcess")) {
		    Collections.sort(list, new BookingDateUserApplicantComparatorByBookingDateAndStartTime());
		}
		else if (actionPath.equals("/shiftSearchProcess2")) {
		    Collections.sort(list, new BookingDateUserApplicantComparatorByJobProfileName());
		}
		else if (actionPath.equals("/shiftSearchProcess3")) {
		    Collections.sort(list, new BookingDateUserApplicantComparatorByApplicantName());
		}
		
		// in ancestor class
		sortShiftTotals(list, dynaForm);

		dynaForm.set("list", list);
    	
		dynaForm.set("canMultiActivate", bookingDateStatus != null && bookingDateStatus.equals(BookingDate.BOOKINGDATE_STATUS_FILLED) 
				                              && activated != null && activated.equals(false));           
		dynaForm.set("canMultiAuthorize", workedStatus != null && workedStatus.equals(BookingDate.BOOKINGDATE_WORKEDSTATUS_SUBMITTED));           
		dynaForm.set("canMultiInvoice", workedStatus != null && workedStatus.equals(BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED));           
		
		logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");

    }

}
