package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.agy.abztract.AgyBookingLockAction;
import com.helmet.bean.BookingGradeApplicant;


public class BookingGradeApplicantRefuseProcess extends AgyBookingLockAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
     	BookingGradeApplicant bookingGradeApplicant = (BookingGradeApplicant)dynaForm.get("bookingGradeApplicant");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		int rc = agyService.updateBookingGradeApplicantRefuse(bookingGradeApplicant.getBookingGradeApplicantId(), bookingGradeApplicant.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());

    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
                   				 actionForward.getPath() + "?bookingGradeApplicant.bookingGradeApplicantId=" + bookingGradeApplicant.getBookingGradeApplicantId(),
    	                         actionForward.getRedirect());
    }

}
