package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyInvoiceUserEntity;


public class AgencyInvoiceCreditView extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	com.helmet.bean.AgencyInvoiceCredit agencyInvoiceCredit = (com.helmet.bean.AgencyInvoiceCredit)dynaForm.get("agencyInvoiceCredit");
     	
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		agencyInvoiceCredit = agyService.getAgencyInvoiceCredit(agencyInvoiceCredit.getAgencyInvoiceCreditId());
		
		if (agencyInvoiceCredit == null) {
	     	// either agencyInvoice doesn't exist OR it is for another agency!
			return mapping.findForward("illegalaccess");
		}
		
		// get the related agencyInvoice
     	AgencyInvoiceUserEntity agencyInvoice = agyService.getAgencyInvoiceUserEntity(agencyInvoiceCredit.getAgencyInvoiceId());
		
		// could check agency is the same agency as the consultant logged in

		if (agencyInvoice == null || !agencyInvoice.getAgencyId().equals(getConsultantLoggedIn().getAgencyId())) {
	     	// either agencyInvoice doesn't exist OR it is for another agency!
			return mapping.findForward("illegalaccess");
		}
		
		MessageResources messageResources = getResources(request);
		try {
			Utilities.generateInvoiceCreditPDF(request, messageResources, agencyInvoiceCredit, agencyInvoice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dynaForm.set("agencyInvoiceCredit", agencyInvoiceCredit); 
		dynaForm.set("agencyInvoice", agencyInvoice); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
