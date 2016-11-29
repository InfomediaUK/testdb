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
import com.helmet.bean.BookingDate;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingGradeAgyEntity;
import com.helmet.bean.BookingGradeApplicantUser;


public class BookingExtend extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	BookingGradeAgyEntity bookingGrade = (BookingGradeAgyEntity)dynaForm.get("bookingGrade");

    	dynaForm.initialize(mapping);

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

    	bookingGrade = agyService.getBookingGradeAgyEntity(bookingGrade.getBookingGradeId(), getConsultantLoggedIn().getAgencyId());

		if (bookingGrade == null) {
	     	return mapping.findForward("illegalaccess");
		}

     	BookingGradeApplicantUser bookingGradeApplicant = agyService.getBookingGradeApplicantUserForBookingFilledSingleCandidate(bookingGrade.getBookingId());
        
    	// TODO need to send a better response back to user ...
        
		if (bookingGradeApplicant == null) {
	     	return mapping.findForward("illegalaccess");
		}
		
		BookingDateUser[] bookingDates = new BookingDateUser[bookingGrade.getBookingDateUsers().size()];
        int i = 0;
        for (BookingDateUser bookingDate: bookingGrade.getBookingDateUsers()) {
        	bookingDates[i] = bookingDate;
        	i++;
        }

        dynaForm.set("currentBookingDates", bookingDates);
		
        dynaForm.set("hourlyRate", bookingGrade.getBookingRate());
        dynaForm.set("rrp", bookingGrade.getBookingValue());
        
        dynaForm.set("bookingGrade", bookingGrade); 
        dynaForm.set("bookingGradeApplicant", bookingGradeApplicant); 

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
