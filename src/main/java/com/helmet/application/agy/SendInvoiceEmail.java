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


public class SendInvoiceEmail extends SendEmail {

    protected ActionForward setFormProperties(ActionMapping mapping, DynaValidatorForm dynaForm, HttpServletRequest request) {

     	AgencyInvoiceUserEntity agencyInvoice = (AgencyInvoiceUserEntity)dynaForm.get("agencyInvoice");
     	
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		agencyInvoice = agyService.getAgencyInvoiceUserEntity(agencyInvoice.getAgencyInvoiceId());
		
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

		String attachmentFileName = "ai" + agencyInvoice.getAgencyInvoiceId() + "ts.pdf";
    	String attachment = FileHandler.getInstance().getTempFileLocation() + 
        FileHandler.getInstance().getTempFileFolder() + 
        "/" + attachmentFileName;
     	
     	dynaForm.set("attachmentFileName", attachmentFileName);
		dynaForm.set("attachment", attachment);
     	
		MessageResources messageResources = getResources(request);
		
		SimpleDateFormat sdf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
		DecimalFormat df = new DecimalFormat("#00000");

		String subject = messageResources.getMessage("emailSubject.invoiceForPayment", df.format(agencyInvoice.getAgencyInvoiceId()), agencyInvoice.getAgencyName());

     	dynaForm.set("subject", subject);

     	StringBuffer messageBuffer = new StringBuffer();

     	messageBuffer.append("Hi " + accountContactName);
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("Please find attached invoice " + df.format(agencyInvoice.getAgencyInvoiceId()) + " dated " + sdf.format(agencyInvoice.getCreationTimestamp()) + ".");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("The invoice appears as an electronic pdf document.");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("This pdf invoice can be read with Acrobat Reader. If you don't have this, it can be downloaded, free of charge, from the Adobe web site at:");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("<LINK>http://www.adobe.com/products/acrobat/readstep2.html</LINK>");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("Just double-click on the appropriate file to retrieve your invoice and produce a hardcopy.");
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
