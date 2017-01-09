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

public class BookingDatesActivateProcess extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	String bookingDateIdStrs = (String)dynaForm.get("bookingDateIdStrs");
    	
     	MgrService mgrService = ServiceFactory.getInstance().getMgrService();

     	int rowCount = mgrService.updateBookingDatesActivated(bookingDateIdStrs, getManagerLoggedIn().getManagerId());
     	
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");

    }

}