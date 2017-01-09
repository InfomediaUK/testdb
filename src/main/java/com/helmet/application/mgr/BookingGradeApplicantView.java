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
import com.helmet.bean.BookingGrade;
import com.helmet.bean.BookingGradeApplicantUserEntity;


public class BookingGradeApplicantView extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	BookingGradeApplicantUserEntity bookingGradeApplicant = (BookingGradeApplicantUserEntity)dynaForm.get("bookingGradeApplicant");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		// TODO - needs to pass manager logged in id !!!!
		
		bookingGradeApplicant = mgrService.getBookingGradeApplicantUserEntity(bookingGradeApplicant.getBookingGradeApplicantId(), getManagerLoggedIn().getManagerId());

		if (bookingGradeApplicant == null) {
	     	return mapping.findForward("illegalaccess");
		}
		
		// temporary - booking Id needed for locking
		
		BookingGrade bg = mgrService.getBookingGrade(bookingGradeApplicant.getBookingGradeId()); 
			
     	dynaForm.set("bookingId", bg.getBookingId());

		dynaForm.set("bookingGradeApplicant", bookingGradeApplicant); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
