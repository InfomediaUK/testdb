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
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.BookingDateUserApplicant;


public class BookingDateEditOvertime extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingDateUserApplicant bookingDate = (BookingDateUserApplicant)dynaForm.get("bookingDate");

    	dynaForm.initialize(mapping);

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		bookingDate = agyService.getBookingDateUserApplicantForAgencyAndBookingDate(getConsultantLoggedIn().getAgencyId(), bookingDate.getBookingDateId());

    	// TODO need to check for NULL object ...
        
		if (bookingDate == null) {
	     	return mapping.findForward("illegalaccess");
		}

        dynaForm.set("bookingDate", bookingDate); 

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
