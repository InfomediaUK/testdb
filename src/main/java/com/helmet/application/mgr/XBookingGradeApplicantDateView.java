package com.helmet.application.mgr;

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
import com.helmet.bean.BookingGradeApplicantDateUser;

public class XBookingGradeApplicantDateView extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	BookingGradeApplicantDateUser bookingGradeApplicantDate = (BookingGradeApplicantDateUser)dynaForm.get("bookingGradeApplicantDate");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		bookingGradeApplicantDate = mgrService.getBookingGradeApplicantDateUser(bookingGradeApplicantDate.getBookingGradeApplicantDateId());

		// temporary - booking Id needed for locking
		
		BookingDate bd = mgrService.getBookingDate(bookingGradeApplicantDate.getBookingDateId()); 
			
     	dynaForm.set("bookingId", bd.getBookingId());

     	dynaForm.set("bookingGradeApplicantDate", bookingGradeApplicantDate); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}