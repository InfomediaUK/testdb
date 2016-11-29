package com.helmet.application.agy;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyInvoiceUser;


public class AgencyInvoiceEditProcess extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		AgencyInvoiceUser agencyInvoice = (AgencyInvoiceUser)dynaForm.get("agencyInvoice");
     	
     	// caluclate discountVatValue
		agencyInvoice.setDiscountVatValue(Utilities.calculateRoundedVat(agencyInvoice.getDiscountValue(), agencyInvoice.getDiscountVatRate()));
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
     	int rowCount = agyService.updateAgencyInvoice(agencyInvoice, getConsultantLoggedIn().getConsultantId());

    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?agencyInvoice.agencyInvoiceId=" + agencyInvoice.getAgencyInvoiceId(),
    	                         actionForward.getRedirect());

    }

}
