package com.helmet.application.agy;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.bean.AgencyInvoiceUserEntity;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.ClientUser;


public class SendInvoiceCreditEmail extends SendEmail {

    protected ActionForward setFormProperties(ActionMapping mapping, DynaValidatorForm dynaForm, HttpServletRequest request) {

     	com.helmet.bean.AgencyInvoiceCredit agencyInvoiceCredit = (com.helmet.bean.AgencyInvoiceCredit)dynaForm.get("agencyInvoiceCredit");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		agencyInvoiceCredit = agyService.getAgencyInvoiceCredit(agencyInvoiceCredit.getAgencyInvoiceCreditId());

		if (agencyInvoiceCredit == null) {
	     	// either agencyInvoice doesn't exist OR it is for another agency!
			return mapping.findForward("illegalaccess");
		}

		AgencyInvoiceUserEntity agencyInvoice = agyService.getAgencyInvoiceUserEntity(agencyInvoiceCredit.getAgencyInvoiceId());
		
		// could check agency is the same agency as the consultant logged in

		if (agencyInvoice == null || !agencyInvoice.getAgencyId().equals(getConsultantLoggedIn().getAgencyId())) {
	     	// either agencyInvoice doesn't exist OR it is for another agency!
			return mapping.findForward("illegalaccess");
		}

		String accountContactName = "";
		String accountContactEmailAddress = ""; 

		if (agencyInvoice.getBookingDateUserApplicants().size() > 0) {
			// using first in list - well shaky!
			BookingDateUserApplicant bookingDate = agencyInvoice.getBookingDateUserApplicants().get(0);
			Booking booking = agyService.getBooking(bookingDate.getBookingId());
			accountContactName = booking.getAccountContactName();
			accountContactEmailAddress = booking.getAccountContactEmailAddress(); 
		}
		else {
			ClientUser client = agyService.getClientUser(agencyInvoice.getClientId());
			accountContactName = client.getAccountContactName();
			accountContactEmailAddress = client.getAccountContactEmailAddress(); 
		}
		
		String toNiceEmailAddress = Utilities.makeNiceEmailAddress(accountContactName, accountContactEmailAddress);

     	dynaForm.set("toEmailAddress", toNiceEmailAddress);

		String attachmentFileName = "aic" + agencyInvoiceCredit.getAgencyInvoiceCreditId() + ".pdf";
    	String attachment = FileHandler.getInstance().getTempFileLocation() + 
        FileHandler.getInstance().getTempFileFolder() + 
        "/" + attachmentFileName;
     	
     	dynaForm.set("attachmentFileName", attachmentFileName);
		dynaForm.set("attachment", attachment);
     	
		MessageResources messageResources = getResources(request);
		
		SimpleDateFormat sdf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
		DecimalFormat df = new DecimalFormat("#00000");

		String subject = messageResources.getMessage("emailSubject.invoiceCredit", df.format(agencyInvoiceCredit.getAgencyInvoiceCreditId()), agencyInvoice.getAgencyName());

     	dynaForm.set("subject", subject);

     	StringBuffer messageBuffer = new StringBuffer();

     	messageBuffer.append("Hi " + accountContactName);
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("Please find attached invoice credit " + df.format(agencyInvoiceCredit.getAgencyInvoiceCreditId()) + " dated " + sdf.format(agencyInvoiceCredit.getCreationTimestamp()) + ".");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("The invoice credit appears as an electronic pdf document.");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("This pdf invoice credit can be read with Acrobat Reader. If you don't have this, it can be downloaded, free of charge, from the Adobe web site at:");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("<LINK>http://www.adobe.com/products/acrobat/readstep2.html</LINK>");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("Just double-click on the appropriate file to retrieve your invoice credit and produce a hardcopy.");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("Kind regards");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append(agencyInvoice.getAgencyName());

     	dynaForm.set("message", messageBuffer.toString());
     	
     	return null;
    	
    }

}
