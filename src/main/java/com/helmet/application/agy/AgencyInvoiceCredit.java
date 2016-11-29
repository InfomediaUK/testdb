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
import com.helmet.bean.AgencyInvoiceUser;


public class AgencyInvoiceCredit extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	AgencyInvoiceUser agencyInvoice = (AgencyInvoiceUser)dynaForm.get("agencyInvoice");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		agencyInvoice = agyService.getAgencyInvoiceUser(agencyInvoice.getAgencyInvoiceId());

		// could check agency is the same agency as the consultant logged in

		if (agencyInvoice == null || !agencyInvoice.getAgencyId().equals(getConsultantLoggedIn().getAgencyId())) {
	     	// either agencyInvoice doesn't exist OR it is for another agency!
			return mapping.findForward("illegalaccess");
		}
		
		dynaForm.set("agencyInvoice", agencyInvoice); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
