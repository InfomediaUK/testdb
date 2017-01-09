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
import com.helmet.bean.BookingUserEntityMgr;


public class BookingEditInfo extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingUserEntityMgr booking = (BookingUserEntityMgr)dynaForm.get("booking");

    	dynaForm.initialize(mapping);

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		booking = mgrService.getBookingUserSummary(booking.getBookingId(), getManagerLoggedIn().getManagerId());

    	// TODO need to check for NULL object ...
        
		if (booking == null) {
	     	return mapping.findForward("illegalaccess");
		}

        dynaForm.set("booking", booking); 

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
