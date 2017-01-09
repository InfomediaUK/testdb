package com.helmet.application.mgr;

import java.util.List;

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
import com.helmet.bean.AgencyInvoiceUser;
import com.helmet.bean.BookingDateUserApplicant;


public class AgencyInvoiceList extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		String statusStr = (String)dynaForm.get("status");

		try {
			Integer.parseInt(statusStr);
		}
		catch (NumberFormatException e) {
		    statusStr = null;	
		}
		
		List<AgencyInvoiceUser> list = mgrService.getAgencyInvoiceUsersForManagerAndStatus(getManagerLoggedIn().getClientId(),
																						   getManagerLoggedIn().getManagerId(), 
																						   Integer.parseInt(statusStr));
		
		// in ancestor class
		sortAgencyInvoiceTotals(list, dynaForm);

		dynaForm.set("list", list); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
