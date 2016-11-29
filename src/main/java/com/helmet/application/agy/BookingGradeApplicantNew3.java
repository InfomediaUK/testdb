package com.helmet.application.agy;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.helmet.application.Utilities;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingGradeAgy;
import com.helmet.bean.ClientAgencyJobProfileGradeUser;


public class BookingGradeApplicantNew3 extends AgyAction {

    BigDecimal divisor100 = new BigDecimal(100);

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingGradeAgy bookingGradeAgy = (BookingGradeAgy)dynaForm.get("bookingGrade");

    	if (isCancelled(request)) {
    		dynaForm.set("page", (Integer)dynaForm.get("page") - 1);
        	ActionForward actionForward = mapping.findForward("back");
        	return new ActionForward(actionForward.getName(),
        							 actionForward.getPath() + "?bookingGrade.bookingGradeId=" + bookingGradeAgy.getBookingGradeId(),
        	                         actionForward.getRedirect());
    	}

    	Applicant applicant = (Applicant)dynaForm.get("applicant");
    	if (applicant.getApplicantId() == null || applicant.getApplicantId() == 0) {
     		return mapping.findForward("bookingApplicantNew");
    	}

    	AgyService agyService = ServiceFactory.getInstance().getAgyService();
		applicant = agyService.getApplicant(applicant.getApplicantId());
     	dynaForm.set("applicant", applicant);

		DecimalFormat df = new DecimalFormat("#0.00");
     	
		BigDecimal rate = null;
		BigDecimal payRate = null;
		BigDecimal wtdPercentage = null;
		BigDecimal niPercentage = null;
		BigDecimal wageRate = null;

		BigDecimal chargeRateVatRate = null;
		BigDecimal payRateVatRate = null;
		BigDecimal wtdVatRate = null;
		BigDecimal niVatRate = null;
		BigDecimal commissionVatRate = null;

		try {
			
			rate = new BigDecimal(df.parse((String) dynaForm.get("rateStr")).doubleValue());
			payRate = new BigDecimal(df.parse((String) dynaForm.get("payRateStr")).doubleValue());
			wtdPercentage = new BigDecimal(df.parse((String) dynaForm.get("wtdPercentageStr")).doubleValue());
			niPercentage = new BigDecimal(df.parse((String) dynaForm.get("niPercentageStr")).doubleValue());
			wageRate = new BigDecimal(df.parse((String) dynaForm.get("wageRateStr")).doubleValue());

			chargeRateVatRate = new BigDecimal(df.parse((String) dynaForm.get("chargeRateVatRateStr")).doubleValue());
			payRateVatRate = new BigDecimal(df.parse((String) dynaForm.get("payRateVatRateStr")).doubleValue());
			wtdVatRate = new BigDecimal(df.parse((String) dynaForm.get("wtdVatRateStr")).doubleValue());
			niVatRate = new BigDecimal(df.parse((String) dynaForm.get("niVatRateStr")).doubleValue());
			commissionVatRate = new BigDecimal(df.parse((String) dynaForm.get("commissionVatRateStr")).doubleValue());

		} catch (Exception e) {
			// TODO: handle exception
		    e.printStackTrace();
		}     	
		
		dynaForm.set("rate", rate);
		dynaForm.set("payRate", payRate);
		dynaForm.set("wtdPercentage", wtdPercentage);
		dynaForm.set("niPercentage", niPercentage);
		dynaForm.set("wageRate", wageRate);

		dynaForm.set("chargeRateVatRate", chargeRateVatRate);
		dynaForm.set("payRateVatRate", payRateVatRate);
		dynaForm.set("wtdVatRate", wtdVatRate);
		dynaForm.set("niVatRate", niVatRate);
		dynaForm.set("commissionVatRate", commissionVatRate);
		
	    // calculate wtd, ni, commission and vat values
		BigDecimal wtdRate =  Utilities.round(payRate.multiply(wtdPercentage.divide(divisor100, 5, RoundingMode.HALF_UP)));
		BigDecimal niRate = Utilities.round((payRate.add(wtdRate)).multiply(niPercentage.divide(divisor100, 5, RoundingMode.HALF_UP)));
		BigDecimal commissionRate = Utilities.round(rate.subtract(payRate).subtract(wtdRate).subtract(niRate));

		BigDecimal chargeRateVatValue = Utilities.round(rate.multiply(chargeRateVatRate.divide(divisor100, 5, RoundingMode.HALF_UP)));
		BigDecimal payRateVatValue = Utilities.round(payRate.multiply(payRateVatRate.divide(divisor100, 5, RoundingMode.HALF_UP)));
		BigDecimal wtdVatValue = Utilities.round(wtdRate.multiply(wtdVatRate.divide(divisor100, 5, RoundingMode.HALF_UP)));
		BigDecimal niVatValue = Utilities.round(niRate.multiply(niVatRate.divide(divisor100, 5, RoundingMode.HALF_UP)));
		BigDecimal commissionVatValue = Utilities.round(commissionRate.multiply(commissionVatRate.divide(divisor100, 5, RoundingMode.HALF_UP)));

    	dynaForm.set("wtdRate", wtdRate);
	    dynaForm.set("niRate", niRate);
	    dynaForm.set("commissionRate", commissionRate);
     	
		dynaForm.set("chargeRateVatValue", chargeRateVatValue);
		dynaForm.set("payRateVatValue", payRateVatValue);
		dynaForm.set("wtdVatValue", wtdVatValue);
		dynaForm.set("niVatValue", niVatValue);
		dynaForm.set("commissionVatValue", commissionVatValue);
     	
		// Sort out if the grades has been changed - 
		//
     	// if it has been changed set the rates and vatRates in the form based on the new grade. 
     	// if it hasn't been changed use the rates from the form
     	//
     	
     	// default to values from form
//     	BigDecimal rate = (BigDecimal)dynaForm.get("rate"); // chargeRate
//     	BigDecimal payRate = (BigDecimal)dynaForm.get("payRate");
//     	BigDecimal wtdPercentage = (BigDecimal)dynaForm.get("wtdPercentage");
//     	BigDecimal niPercentage = (BigDecimal)dynaForm.get("niPercentage");
//     	BigDecimal wageRate = (BigDecimal)dynaForm.get("wageRate");

//     	Integer clientAgencyJobProfileGradeId = (Integer)dynaForm.get("clientAgencyJobProfileGradeId");
//    	
//    	if (!clientAgencyJobProfileGradeId.equals(bookingGradeAgy.getClientAgencyJobProfileGradeId())) {
//    		// changed so set form rates - first find it in the available list
//    		List<ClientAgencyJobProfileGradeUser> clientAgencyJobProfileGrades = (List<ClientAgencyJobProfileGradeUser>)dynaForm.get("clientAgencyJobProfileGrades");
//            for (ClientAgencyJobProfileGradeUser clientAgencyJobProfileGradeUser: clientAgencyJobProfileGrades) {
//            	if (clientAgencyJobProfileGradeUser.getClientAgencyJobProfileGradeId().equals(clientAgencyJobProfileGradeId)) {
//                 	rate = clientAgencyJobProfileGradeUser.getRate(); // chargeRate
//                 	payRate = clientAgencyJobProfileGradeUser.getPayRate();
//                 	wtdPercentage = clientAgencyJobProfileGradeUser.getWtdPercentage();
//                 	niPercentage = clientAgencyJobProfileGradeUser.getNiPercentage();
//                 	dynaForm.set("rate", rate);
//                 	dynaForm.set("payRate", payRate);
//                 	dynaForm.set("wtdPercentage", wtdPercentage);
//                 	dynaForm.set("niPercentage", niPercentage);
//                 	dynaForm.set("wageRate", wageRate);
//                 	dynaForm.set("gradeName", clientAgencyJobProfileGradeUser.getGradeName());
//            		break;
//            	}
//            }
//    	}
//    	else {
//         	String requestedGradeName = (String)dynaForm.get("requestedGradeName");
//         	dynaForm.set("gradeName", requestedGradeName);
//    	}

//        // PAY RATE STUFF !!!!!
//     	
//     	BigDecimal divisor100 = new BigDecimal(100);
//
//        BigDecimal wtdRate = Utilities.round(payRate.multiply(wtdPercentage).divide(divisor100, 5, RoundingMode.HALF_UP));
//        BigDecimal niRate = Utilities.round((payRate.add(wtdRate)).multiply(niPercentage).divide(divisor100, 5, RoundingMode.HALF_UP)); 
//        
//		BigDecimal payeRate = payRate.add(wtdRate).add(niRate);
//		BigDecimal commissionRate = rate.subtract(payeRate);
//		
//		dynaForm.set("wtdRate", wtdRate);
//		dynaForm.set("niRate", niRate);
//
//		dynaForm.set("commissionRate", commissionRate);
     	
		List<BookingDateUser> list = agyService.getBookingDateUsersForBookingAndAgency(bookingGradeAgy.getBookingId(), getConsultantLoggedIn().getAgencyId());

     	for (BookingDateUser bookingDateUser: list) {

    		// TODO - calculations SHOULD be working it out using the calculate method - rather than multiplying by a factor
     		// shift of 7.5 hours at 25.97 = 194.78 if rate up'd to 30.00, 194.78 * (30.00/25.97) = 225.01 !!!!
     		
     		bookingDateUser.setChargeRateValue(Utilities.round(bookingDateUser.getValue().multiply(rate.divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
     		bookingDateUser.setPayRateValue(Utilities.round(bookingDateUser.getValue().multiply(payRate.divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
     		bookingDateUser.setWageRateValue(Utilities.round(bookingDateUser.getValue().multiply(wageRate.divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));

//     		bookingDateUser.setChargeRateValue(Utilities.round(bookingDateUser.getValue().multiply(bookingGradeAgy.getRate().divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
//     		bookingDateUser.setPayRateValue(Utilities.round(bookingDateUser.getValue().multiply(payRate.divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
//     		bookingDateUser.setWageRateValue(Utilities.round(bookingDateUser.getValue().multiply(wageRate.divide(bookingGradeAgy.getBookingRate(), 5, RoundingMode.HALF_UP))));
    	}
		
		if (bookingGradeAgy.getSingleCandidate()) {
			// single candidate so default ALL dates ON
	    	String[] selectedBookingDates = new String[list.size() + 1];
	    	selectedBookingDates[0] = "0";
	    	int i = 1;
	    	for (BookingDateUser bookingDateUser: list) {
	    		selectedBookingDates[i] = bookingDateUser.getBookingDateId().toString();
                i++;
	    	}
	    	dynaForm.set("selectedBookingDates", selectedBookingDates);
		}

     	dynaForm.set("list", list);
		
    	logger.exit("Out going !!!");

     	return mapping.findForward("success");
    }

}
