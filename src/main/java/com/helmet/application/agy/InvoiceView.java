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
import com.helmet.bean.AgencyUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.InvoiceAgencyUserEntity;


public class InvoiceView extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	InvoiceAgencyUserEntity invoiceAgency = (InvoiceAgencyUserEntity)dynaForm.get("invoiceAgency");
     	
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		invoiceAgency = agyService.getInvoiceAgencyUserEntityForInvoiceForAgency(invoiceAgency.getInvoiceId(), getConsultantLoggedIn().getAgencyId());
		
		if (invoiceAgency == null) {
	     	return mapping.findForward("illegalaccess");
		}
		
		ClientUser client = agyService.getClientUser(invoiceAgency.getClientId());
		AgencyUser agency = agyService.getAgencyUser(getConsultantLoggedIn().getAgencyId());

		dynaForm.set("invoiceAgency", invoiceAgency); 
		dynaForm.set("client", client); 
		dynaForm.set("agency", agency); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
