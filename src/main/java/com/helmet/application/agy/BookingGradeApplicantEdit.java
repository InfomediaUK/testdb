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
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingGradeAgy;
import com.helmet.bean.BookingGradeApplicantDate;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;


public class BookingGradeApplicantEdit extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    	
    	logger.entry("In coming !!!");
    	
    	BookingGradeApplicantUser bookingGradeApplicant = (BookingGradeApplicantUser)dynaForm.get("bookingGradeApplicant");

    	dynaForm.initialize(mapping);

    	AgyService agyService = ServiceFactory.getInstance().getAgyService();

    	bookingGradeApplicant = agyService.getBookingGradeApplicantUser(bookingGradeApplicant.getBookingGradeApplicantId());
        dynaForm.set("bookingGradeApplicant", bookingGradeApplicant);

    	BookingGradeAgy bookingGradeAgy = agyService.getBookingGradeAgy(bookingGradeApplicant.getBookingGradeId(), getConsultantLoggedIn().getAgencyId());
        dynaForm.set("bookingGrade", bookingGradeAgy);
     	
    	Applicant applicant = new Applicant();
    	applicant.setApplicantId(bookingGradeApplicant.getApplicantId());
        dynaForm.set("applicant", applicant);

        // TODO - this is rubbish - just to get the dates
    	BookingGradeApplicantUserEntity bgaue = agyService.getBookingGradeApplicantUserEntity(bookingGradeApplicant.getBookingGradeApplicantId());
     	
    	int i = 0;
		String[] selectedBookingDates = new String[bgaue.getBookingGradeApplicantDateUserEntities().size() + 1];
		selectedBookingDates[i] = "0";
		for (BookingGradeApplicantDate bookingGradeApplicantDate: bgaue.getBookingGradeApplicantDateUserEntities()) {
			String theBookingDateId = bookingGradeApplicantDate.getBookingDateId().toString();
			i++;
			selectedBookingDates[i] = theBookingDateId;
		}
        dynaForm.set("selectedBookingDates", selectedBookingDates);
     	
        dynaForm.set("rate", bookingGradeApplicant.getRate());
        dynaForm.set("payRate", bookingGradeApplicant.getPayRate());
        dynaForm.set("wtdPercentage", bookingGradeApplicant.getWtdPercentage());
        dynaForm.set("niPercentage", bookingGradeApplicant.getNiPercentage());
        dynaForm.set("wtdRate", bookingGradeApplicant.getWtdRate());
        dynaForm.set("niRate", bookingGradeApplicant.getNiRate());
        dynaForm.set("wageRate", bookingGradeApplicant.getWageRate());
        dynaForm.set("commissionRate", bookingGradeApplicant.getCommissionRate());
        dynaForm.set("chargeRateVatRate", bookingGradeApplicant.getChargeRateVatRate());
        dynaForm.set("commissionVatRate", bookingGradeApplicant.getCommissionVatRate());
        dynaForm.set("payRateVatRate", bookingGradeApplicant.getPayRateVatRate());
        dynaForm.set("wtdVatRate", bookingGradeApplicant.getWtdVatRate());
        dynaForm.set("niVatRate", bookingGradeApplicant.getNiVatRate());

        dynaForm.set("clientAgencyJobProfileGradeId", bookingGradeApplicant.getClientAgencyJobProfileGradeId());
        dynaForm.set("gradeName", bookingGradeApplicant.getGradeName());

    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");
    }

}
