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


public class AgencyInvoiceCreditProcess extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		AgencyInvoiceUser agencyInvoice = (AgencyInvoiceUser)dynaForm.get("agencyInvoice");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?agencyInvoice.agencyInvoiceId=" + agencyInvoice.getAgencyInvoiceId(),
	    	                         actionForward.getRedirect());
			
		}		
		
		String reasonText = (String)dynaForm.get("reasonText");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
     	int rowCount = agyService.updateAgencyInvoiceCredited(agencyInvoice.getAgencyInvoiceId(), agencyInvoice.getNoOfChanges(), getConsultantLoggedIn().getConsultantId(), reasonText);

     	// re get the agencyInvoice as we need the agencyInvoiceCreditId below
     	agencyInvoice = agyService.getAgencyInvoiceUser(agencyInvoice.getAgencyInvoiceId());
     	
    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?agencyInvoiceCredit.agencyInvoiceCreditId=" + agencyInvoice.getAgencyInvoiceCreditId(),
    	                         actionForward.getRedirect());

    }

}
