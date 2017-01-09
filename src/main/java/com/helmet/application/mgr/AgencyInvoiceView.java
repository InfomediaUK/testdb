package com.helmet.application.mgr;

import java.math.BigDecimal;

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
import com.helmet.bean.AgencyInvoiceUserEntity;
import com.helmet.bean.BookingDate;


public class AgencyInvoiceView extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	AgencyInvoiceUserEntity agencyInvoice = (AgencyInvoiceUserEntity)dynaForm.get("agencyInvoice");
     	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		agencyInvoice = mgrService.getAgencyInvoiceUserEntityForManager(agencyInvoice.getAgencyInvoiceId(), getManagerLoggedIn().getManagerId());
		
     	if (agencyInvoice != null && agencyInvoice.getBookingDateUserApplicants().size() > 0) {
         	// TODO could be rather neater - currently checking if the manager has access to all items within the invoice by 
         	// summing the chargeRates on the bookingdates within the invoice and seeing if they equal thevalue on the invoice.
    		BigDecimal bookingDatesChargeRateValue = new BigDecimal(0.0);
         	for (BookingDate bookingDate: agencyInvoice.getBookingDateUserApplicants()) {
         		bookingDatesChargeRateValue = bookingDatesChargeRateValue.add(bookingDate.getWorkedChargeRateValue());
         	}
    		if (!agencyInvoice.getChargeRateValue().equals(bookingDatesChargeRateValue)) {
    			// manager doesn't have access to all bookingDates within the invoice so clear the bookingsDates from the invoiceEntity.
    			agencyInvoice.setBookingDateUserApplicants(null);
    		}
     	}
     	
		if (agencyInvoice == null || !agencyInvoice.getClientId().equals(getManagerLoggedIn().getClientId())) {
	     	// either agencyInvoice doesn't exist, is for a different client OR manager doesn't have access to all the items
			return mapping.findForward("illegalaccess");
		}
		
		dynaForm.set("agencyInvoice", agencyInvoice); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
