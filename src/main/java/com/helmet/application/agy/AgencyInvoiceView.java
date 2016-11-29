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


public class AgencyInvoiceView extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	AgencyInvoiceUserEntity agencyInvoice = (AgencyInvoiceUserEntity)dynaForm.get("agencyInvoice");
     	
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		agencyInvoice = agyService.getAgencyInvoiceUserEntity(agencyInvoice.getAgencyInvoiceId());
		
		// could check agency is the same agency as the consultant logged in

		if (agencyInvoice == null || !agencyInvoice.getAgencyId().equals(getConsultantLoggedIn().getAgencyId())) {
	     	// either agencyInvoice doesn't exist OR it is for another agency!
			return mapping.findForward("illegalaccess");
		}
		
		MessageResources messageResources = getResources(request);
		try {
			Utilities.generateInvoicePDF(request, messageResources, agencyInvoice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dynaForm.set("agencyInvoice", agencyInvoice); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
