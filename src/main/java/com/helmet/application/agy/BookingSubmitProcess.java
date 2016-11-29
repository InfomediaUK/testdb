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
import com.helmet.bean.Booking;


public class BookingSubmitProcess extends AgyBookingLockAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
     	Integer bookingId = (Integer)dynaForm.get("bookingId");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		// 0 (zero) passed as noOfChanges as this is always called for a NEW booking for and agency
		int rc = agyService.updateBookingOpen(bookingId, 0, getConsultantLoggedIn().getConsultantId());

    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");
    	
    }

}
