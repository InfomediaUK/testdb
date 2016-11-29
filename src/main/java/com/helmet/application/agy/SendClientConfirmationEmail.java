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
import com.helmet.application.Utilities;
import com.helmet.bean.Agency;
import com.helmet.bean.Booking;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicantUserEntity;
import com.helmet.bean.ClientUser;


public class SendClientConfirmationEmail extends SendEmail {

    protected ActionForward setFormProperties(ActionMapping mapping, DynaValidatorForm dynaForm, HttpServletRequest request) {

     	BookingGradeApplicantUserEntity bookingGradeApplicant = (BookingGradeApplicantUserEntity)dynaForm.get("bookingGradeApplicant");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		bookingGradeApplicant = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicant.getBookingGradeApplicantId());
		
        if (bookingGradeApplicant == null) {
	     	return mapping.findForward("illegalaccess");
        }

        // currently using booking/client account info - should be using booking 'contact' info
        
		String contactName = "";
		String contactEmailAddress = ""; 

 		BookingGradeAgyEntity bookingGrade = agyService.getBookingGradeAgyEntity(bookingGradeApplicant.getBookingGradeId(), bookingGradeApplicant.getAgencyId());
		
		contactName = bookingGrade.getContactName();
		contactEmailAddress = bookingGrade.getContactEmailAddress(); 
		if ((contactName == null || "".equals(contactName)) && (contactEmailAddress == null || "".equals(contactEmailAddress))) {
			contactName = bookingGrade.getAccountContactName();
			contactEmailAddress = bookingGrade.getAccountContactEmailAddress(); 
		}
		if ((contactName == null || "".equals(contactName)) && (contactEmailAddress == null || "".equals(contactEmailAddress))) {
			ClientUser client = agyService.getClientUser(bookingGradeApplicant.getClientId());
			contactName = client.getAccountContactName();
			contactEmailAddress = client.getAccountContactEmailAddress(); 
		}
        
		String toNiceEmailAddress = Utilities.makeNiceEmailAddress(contactName, contactEmailAddress);

     	dynaForm.set("toEmailAddress", toNiceEmailAddress);

        Agency agency = AgyUtilities.getCurrentAgency(request);
		
		MessageResources messageResources = getResources(request);

		SimpleDateFormat ldf = new SimpleDateFormat(messageResources.getMessage("format.longDateFormat"));
		SimpleDateFormat tdf = new SimpleDateFormat("HH:mm");
		DecimalFormat df = new DecimalFormat("#,##0.00");
		DecimalFormat dfBookingId = new DecimalFormat("#000");
		
		String subject = messageResources.getMessage("emailSubject.applicantBookingConfirmation", agency.getName());

     	dynaForm.set("subject", subject);

     	StringBuffer messageBuffer = new StringBuffer();

     	messageBuffer.append("Dear " + contactName);
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("Thank you for using " + agency.getName() + ". Please find your booking confirmation details below. ");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("You will receive electronic invoices for this booking. Should you wish a copy of the invoices to be sent ");
     	messageBuffer.append("to your finance department please forward their email address to ");
     	if (agency.getPayrollContactEmailAddress() == null || "".equals(agency.getPayrollContactEmailAddress())) {
         	messageBuffer.append("our payroll department");
     	}
     	else {
         	messageBuffer.append(agency.getPayrollContactEmailAddress());
     	}
     	messageBuffer.append(".");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("Should you require any further information please do not hesitate to contact me.");
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");

     	String clientName = bookingGradeApplicant.getClientName();
        String locationName = bookingGradeApplicant.getLocationName();
        String siteName = bookingGradeApplicant.getSiteName();
        String jobProfileName = bookingGradeApplicant.getJobProfileName();
        String gradeName = bookingGradeApplicant.getGradeName();
        String applicantFullName = bookingGradeApplicant.getApplicantFullName();
     	
     	messageBuffer.append(messageResources.getMessage("label.bookingNo") + " " + dfBookingId.format(bookingGradeApplicant.getBookingId()));
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.client") + " : " + clientName);
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.site") + " : " + siteName);
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.location") + " : " + locationName);
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.jobProfile") + " : " + jobProfileName);
     	if (bookingGrade.getJobFamilyCode() != null && !"".equals(bookingGrade.getJobFamilyCode())
     		 && bookingGrade.getJobSubFamilyCode() != null && !"".equals(bookingGrade.getJobSubFamilyCode())
     		 && bookingGrade.getJobProfileCode() != null && !"".equals(bookingGrade.getJobProfileCode())) {
         	messageBuffer.append(" (" + bookingGrade.getJobFamilyCode() + "." + 
         			bookingGrade.getJobSubFamilyCode() + "." + 
         			bookingGrade.getJobProfileCode() + ")");
     	}
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.grade") + " : " + gradeName);
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.applicant") + " : " + applicantFullName);
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.startDate") + " : " + ldf.format(bookingGradeApplicant.getMinBookingDate()));
     	messageBuffer.append("\n");
     	messageBuffer.append(messageResources.getMessage("label.startTime") + " : " + tdf.format(bookingGradeApplicant.getMinBookingDateShiftStartTime()));
     	messageBuffer.append("\n");

     	if (bookingGrade.getDuration() != null && !"".equals(bookingGrade.getDuration())) {
         	messageBuffer.append(messageResources.getMessage("label.duration") + " : " + bookingGrade.getDuration());
         	messageBuffer.append("\n");
     	}
     	else {
         	messageBuffer.append(messageResources.getMessage("label.endDate") + " : " + ldf.format(bookingGradeApplicant.getMaxBookingDate()));
         	messageBuffer.append("\n");
         	messageBuffer.append(messageResources.getMessage("label.days") + " : " + bookingGrade.getNoOfBookingDates());
         	messageBuffer.append("\n");
         	messageBuffer.append(messageResources.getMessage("label.noOfHours") + " : " + df.format(bookingGradeApplicant.getNoOfHours()));
         	messageBuffer.append("\n");
     	}
     	
     	String shiftName = bookingGradeApplicant.getShiftName();
     	messageBuffer.append(messageResources.getMessage("label.shift") + " : " + (shiftName == null ? 
     																				messageResources.getMessage("label.varied") :
     																				 shiftName));
     	messageBuffer.append("\n");
     	
     	String dressCodeName = bookingGrade.getDressCodeName();
     	if (dressCodeName != null && !"".equals(dressCodeName)) {
         	messageBuffer.append(messageResources.getMessage("label.dressCode") + " : " + dressCodeName);
         	messageBuffer.append("\n");
     	}
     	
     	messageBuffer.append(messageResources.getMessage("label.specifics") + " : ");
     	String specifics = "";
     	if (bookingGrade.getCvRequired()) {
         	if (!"".equals(specifics)) {
         		specifics += ", ";
         	}
     		specifics += messageResources.getMessage("label.cvRequired");
     	}
     	if (bookingGrade.getInterviewRequired()) {
         	if (!"".equals(specifics)) {
         		specifics += ", ";
         	}
     		specifics += messageResources.getMessage("label.interviewRequired");
     	}
     	if (bookingGrade.getCanProvideAccommodation()) {
         	if (!"".equals(specifics)) {
         		specifics += ", ";
         	}
     		specifics += messageResources.getMessage("label.canProvideAccommodation");
     	}
     	if (bookingGrade.getCarRequired()) {
         	if (!"".equals(specifics)) {
         		specifics += ", ";
         	}
     		specifics += messageResources.getMessage("label.carRequired");
     	}
     	if ("".equals(specifics)) {
         	specifics = messageResources.getMessage("label.none");
     	}
     	messageBuffer.append(specifics);
     	messageBuffer.append("\n");
     	
     	messageBuffer.append(messageResources.getMessage("label.expenses") + " : ");
     	String expenses = "";
     	if (bookingGradeApplicant.getBookingExpensesCount().getValue() == 0) {
         	expenses = messageResources.getMessage("label.none");
     	}
     	else {
     	    for (BookingExpense bookingExpense: bookingGrade.getBookingExpenses()) {
             	if (!"".equals(expenses)) {
             		expenses += ", ";
             	}
             	expenses += bookingExpense.getExpenseName();
     	    }
     	}
     	messageBuffer.append(expenses);
     	messageBuffer.append("\n");
     	
     	String expensesText = bookingGrade.getExpensesText();
     	if (expensesText != null && !"".equals(expensesText)) {
         	messageBuffer.append(expensesText);
         	messageBuffer.append("\n");
     	}

     	messageBuffer.append(messageResources.getMessage("label.chargeRate") + " : ");
     	messageBuffer.append(messageResources.getMessage("label.currencySymbolActual") + df.format(bookingGradeApplicant.getChargeRate()));
     	messageBuffer.append("\n");

     	messageBuffer.append("\n");
     	
     	String clientConfirmationEmailFreeText = agency.getClientConfirmationEmailFreeText();
     	
     	if (clientConfirmationEmailFreeText != null && !"".equals(clientConfirmationEmailFreeText)) {
     		
     		messageBuffer.append(clientConfirmationEmailFreeText);
         	messageBuffer.append("\n");
         	messageBuffer.append("\n");
     	
     	}

     	messageBuffer.append("Kind regards");
     	
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append(getConsultantLoggedIn().getUser().getFullName());
     	if (getConsultantLoggedIn().getJobTitle() != null && !"".equals(getConsultantLoggedIn().getJobTitle())) {
         	messageBuffer.append("\n");
         	messageBuffer.append(getConsultantLoggedIn().getJobTitle());
     	}
     	messageBuffer.append("\n");
     	messageBuffer.append(agency.getName());
     	messageBuffer.append("\n");
     	messageBuffer.append(agency.getAddress().getAddress1());
     	messageBuffer.append("\n");
     	if (agency.getAddress().getAddress2() != null && !"".equals(agency.getAddress().getAddress2())) {
         	messageBuffer.append(agency.getAddress().getAddress2());
         	messageBuffer.append("\n");
     	}
     	if (agency.getAddress().getAddress3() != null && !"".equals(agency.getAddress().getAddress3())) {
         	messageBuffer.append(agency.getAddress().getAddress3());
         	messageBuffer.append("\n");
     	}
     	if (agency.getAddress().getAddress4() != null && !"".equals(agency.getAddress().getAddress4())) {
         	messageBuffer.append(agency.getAddress().getAddress4());
         	messageBuffer.append("\n");
     	}
     	messageBuffer.append(agency.getAddress().getPostalCode());
     	messageBuffer.append("\n");
     	messageBuffer.append("\n");
     	messageBuffer.append("Tel: " + agency.getTelephoneNumber());
     	messageBuffer.append("\n");
     	messageBuffer.append("Fax: " + agency.getFaxNumber());
     	messageBuffer.append("\n");
     	messageBuffer.append("Email: " + getConsultantLoggedIn().getUser().getEmailAddress());
     	messageBuffer.append("\n");
     	messageBuffer.append("Website: " + agency.getWebsiteAddress());

     	dynaForm.set("message", messageBuffer.toString());
     	
     	return null;
    	
    }

}
