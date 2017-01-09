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
import com.helmet.bean.BookingDate;
import com.helmet.bean.InvoiceEntity;

public class InvoiceView extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	InvoiceEntity invoice = (InvoiceEntity)dynaForm.get("invoice");

     	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
     	
     	invoice = mgrService.getInvoiceEntity(getManagerLoggedIn(), invoice.getInvoiceId());
     	
     	if (invoice != null && invoice.getBookingDateUserApplicants().size() > 0) {
         	// TODO could be rather neater - currently checking if the manager has access to all items within the invoice by 
         	// summing the chargeRates on the bookingdates within the invoice and seeing if they equal thevalue on the invoice.
    		BigDecimal bookingDatesChargeRateValue = new BigDecimal(0.0);
         	for (BookingDate bookingDate: invoice.getBookingDateUserApplicants()) {
         		bookingDatesChargeRateValue = bookingDatesChargeRateValue.add(bookingDate.getWorkedChargeRateValue());
         	}
    		if (!invoice.getChargeRateValue().equals(bookingDatesChargeRateValue)) {
    			// manager doesn't have access to all bookingDates within the invoice so clear the bookingsDates from the invoiceEntity.
    			invoice.setBookingDateUserApplicants(null);
    		}
     	}
     	
     	dynaForm.set("invoice", invoice);
    	
    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");

    }

}