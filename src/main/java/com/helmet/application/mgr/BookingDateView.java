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
import com.helmet.bean.BookingDateUserApplicantEntity;

public class BookingDateView extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

//    	BookingDateUserApplicant bookingDate = (BookingDateUserApplicant)dynaForm.get("bookingDate");
    	BookingDateUserApplicantEntity bookingDate = (BookingDateUserApplicantEntity)dynaForm.get("bookingDate");

     	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
     	
//     	bookingDate = mgrService.getBookingDateUserApplicantForManagerAndBookingDate(getManagerLoggedIn().getManagerId(), bookingDate.getBookingDateId());
     	bookingDate = mgrService.getBookingDateUserApplicantEntityForManagerAndBookingDate(getManagerLoggedIn().getManagerId(), bookingDate.getBookingDateId());
		
     	dynaForm.set("bookingDate", bookingDate);
     	dynaForm.set("bookingId", bookingDate.getBookingId());
    	
    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");

    }

}