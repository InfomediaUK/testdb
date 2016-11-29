package com.helmet.application.agy;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

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
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.ClientAgencyJobProfileGrade;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;


public class BookingGradeApplicantNew2 extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	if (isCancelled(request)) {
    		dynaForm.set("page", (Integer)dynaForm.get("page") - 1);
         	return mapping.findForward("back");
    	}

    	BookingGradeAgy bookingGradeAgy = (BookingGradeAgy)dynaForm.get("bookingGrade");
    	
    	AgyService agyService = ServiceFactory.getInstance().getAgyService();

    	bookingGradeAgy = agyService.getBookingGradeAgy(bookingGradeAgy.getBookingGradeId(), getConsultantLoggedIn().getAgencyId());
    	
    	BookingGradeApplicantUser bookingGradeApplicant = (BookingGradeApplicantUser)dynaForm.get("bookingGradeApplicant");

		ClientAgencyJobProfileGrade cajpg = null;
		
		if (bookingGradeApplicant == null || bookingGradeApplicant.getBookingGradeApplicantId() == null) {
			
			cajpg = agyService.getClientAgencyJobProfileGrade(bookingGradeAgy.getClientAgencyJobProfileGradeId());
		
		}
		else {
			
			cajpg = agyService.getClientAgencyJobProfileGrade(bookingGradeApplicant.getClientAgencyJobProfileGradeId());

		}
     	
		
		List<ClientAgencyJobProfileGradeUser> clientAgencyJobProfileGrades = agyService.getClientAgencyJobProfileUsersForClientAgencyJobProfile(cajpg.getClientAgencyJobProfileId());
		
		// TODO - temporary master vendor stuff
		String masterVendorName = null;
		if (clientAgencyJobProfileGrades.size() > 0) {
			masterVendorName = clientAgencyJobProfileGrades.get(0).getMasterVendorName();
		}
		dynaForm.set("masterVendorName", masterVendorName);
		
		BigDecimal bdZero = new BigDecimal(0);

     	Integer clientAgencyJobProfileGradeId = (Integer)dynaForm.get("clientAgencyJobProfileGradeId");

		DecimalFormat df = new DecimalFormat("#0.00");

		if (clientAgencyJobProfileGradeId == 0) {

     		// first time through
     			
         	dynaForm.set("bookingGrade", bookingGradeAgy);
         	if (bookingGradeAgy.getRate().compareTo(bdZero) > 0) {
             	dynaForm.set("rateStr", df.format(bookingGradeAgy.getRate()));
         	}
         	if (bookingGradeAgy.getPayRate().compareTo(bdZero) > 0) {
             	dynaForm.set("payRateStr", df.format(bookingGradeAgy.getPayRate()));
         	}
         	if (bookingGradeAgy.getWtdPercentage().compareTo(bdZero) > 0) {
             	dynaForm.set("wtdPercentageStr", df.format(bookingGradeAgy.getWtdPercentage()));
         	}
         	if (bookingGradeAgy.getWtdRate().compareTo(bdZero) > 0) {
             	dynaForm.set("wtdRateStr", df.format(bookingGradeAgy.getWtdRate()));
         	}
         	if (bookingGradeAgy.getNiPercentage().compareTo(bdZero) > 0) {
             	dynaForm.set("niPercentageStr", df.format(bookingGradeAgy.getNiPercentage()));
         	}
         	if (bookingGradeAgy.getNiRate().compareTo(bdZero) > 0) {
             	dynaForm.set("niRateStr", df.format(bookingGradeAgy.getNiRate()));
         	}
         	if (bookingGradeAgy.getCommissionRate().compareTo(bdZero) > 0) {
             	dynaForm.set("commissionRateStr", df.format(bookingGradeAgy.getCommissionRate()));
         	}
         	if (bookingGradeAgy.getWageRate().compareTo(bdZero) > 0) {
             	dynaForm.set("wageRateStr", df.format(bookingGradeAgy.getWageRate()));
         	}

         	dynaForm.set("chargeRateVatRateStr", df.format(bookingGradeAgy.getChargeRateVatRate()));
            dynaForm.set("payRateVatRateStr", df.format(bookingGradeAgy.getPayRateVatRate()));
            dynaForm.set("wtdVatRateStr", df.format(bookingGradeAgy.getWtdVatRate()));
            dynaForm.set("niVatRateStr", df.format(bookingGradeAgy.getNiVatRate()));
         	dynaForm.set("commissionVatRateStr", df.format(bookingGradeAgy.getCommissionVatRate()));
         	
         	dynaForm.set("chargeRateVatValueStr", df.format(bookingGradeAgy.getChargeRateVatValue()));
            dynaForm.set("payRateVatValueStr", df.format(bookingGradeAgy.getPayRateVatValue()));
            dynaForm.set("wtdVatValueStr", df.format(bookingGradeAgy.getWtdVatValue()));
            dynaForm.set("niVatValueStr", df.format(bookingGradeAgy.getNiVatValue()));
         	dynaForm.set("commissionVatValueStr", df.format(bookingGradeAgy.getCommissionVatValue()));

         	dynaForm.set("clientAgencyJobProfileGradeId", bookingGradeAgy.getClientAgencyJobProfileGradeId());
         	dynaForm.set("gradeName", bookingGradeAgy.getGradeName());

     	}
     	else {
     		
     		// should format the string values

     		
         	dynaForm.set("rateStr", df.format(dynaForm.get("rate")));
         	dynaForm.set("payRateStr", df.format(dynaForm.get("payRate")));
         	dynaForm.set("wtdPercentageStr", df.format(dynaForm.get("wtdPercentage")));
         	dynaForm.set("wtdRateStr", df.format(dynaForm.get("wtdRate")));
         	dynaForm.set("niPercentageStr", df.format(dynaForm.get("niPercentage")));
         	dynaForm.set("niRateStr", df.format(dynaForm.get("niRate")));
         	dynaForm.set("commissionRateStr", df.format(dynaForm.get("commissionRate")));
         	dynaForm.set("wageRateStr", df.format(dynaForm.get("wageRate")));

         	dynaForm.set("chargeRateVatRateStr", df.format(dynaForm.get("chargeRateVatRate")));
	        dynaForm.set("payRateVatRateStr", df.format(dynaForm.get("payRateVatRate")));
	        dynaForm.set("wtdVatRateStr", df.format(dynaForm.get("wtdVatRate")));
	        dynaForm.set("niVatRateStr", df.format(dynaForm.get("niVatRate")));
	     	dynaForm.set("commissionVatRateStr", df.format(dynaForm.get("commissionVatRate")));
     	
	     	dynaForm.set("chargeRateVatValueStr", df.format(dynaForm.get("chargeRateVatValue")));
	        dynaForm.set("payRateVatValueStr", df.format(dynaForm.get("payRateVatValue")));
	        dynaForm.set("wtdVatValueStr", df.format(dynaForm.get("wtdVatValue")));
	        dynaForm.set("niVatValueStr", df.format(dynaForm.get("niVatValue")));
	     	dynaForm.set("commissionVatValueStr", df.format(dynaForm.get("commissionVatValue")));
     		
     	}

     	// needed in case they change the grade back to the requested one
     	dynaForm.set("requestedGradeName", bookingGradeAgy.getGradeName());
		
        dynaForm.set("clientAgencyJobProfileGrades", clientAgencyJobProfileGrades);

    	logger.exit("Out going !!!");

     	return mapping.findForward("success");
    }

}
