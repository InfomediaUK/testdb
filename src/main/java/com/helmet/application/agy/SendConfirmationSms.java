package com.helmet.application.agy;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.bean.Agency;
import com.helmet.bean.BookingGradeApplicantUserEntity;


public class SendConfirmationSms extends SendSms {

    protected ActionForward setFormProperties(ActionMapping mapping, DynaValidatorForm dynaForm, HttpServletRequest request) {

     	BookingGradeApplicantUserEntity bookingGradeApplicant = (BookingGradeApplicantUserEntity)dynaForm.get("bookingGradeApplicant");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		bookingGradeApplicant = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicant.getBookingGradeApplicantId());
		
        if (bookingGradeApplicant == null) {
	     	return mapping.findForward("illegalaccess");
        }

     	dynaForm.set("mobileNumber", bookingGradeApplicant.getApplicantMobileNumber());

        Agency agency = AgyUtilities.getCurrentAgency(request);
		
		MessageResources messageResources = getResources(request);

     	StringBuffer messageBuffer = new StringBuffer();

     	messageBuffer.append("Hi " + bookingGradeApplicant.getApplicantFirstName());
     	messageBuffer.append("\n");
     	messageBuffer.append(agency.getName() + " online times notification.");
     	messageBuffer.append("\n");
     	messageBuffer.append("http://" + request.getServerName() + "/timesheet");
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.login") + " : " + bookingGradeApplicant.getLogin());
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.pwd") + " : " + bookingGradeApplicant.getPwd());
     	messageBuffer.append("\n");
     	messageBuffer.append(agency.getName());

     	dynaForm.set("message", messageBuffer.toString());
     	
     	return null;
    	
    }

}
