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
import com.helmet.application.mgr.abztract.MgrBookingLockAction;
import com.helmet.bean.BookingGradeApplicantUserEntity;


public class XBookingGradeApplicantActivateProcess extends MgrBookingLockAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	BookingGradeApplicantUserEntity bookingGradeApplicant = (BookingGradeApplicantUserEntity)dynaForm.get("bookingGradeApplicant");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

//		int rc = mgrService.updateBookingGradeApplicantActivated(bookingGradeApplicant.getBookingGradeApplicantId(), bookingGradeApplicant.getNoOfChanges(), getManagerLoggedIn().getManagerId());
		
    	logger.exit("Out going !!!");
    	
     	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?bookingGradeApplicant.bookingGradeApplicantId=" + bookingGradeApplicant.getBookingGradeApplicantId(),
    	                         actionForward.getRedirect());
  
    }

}
