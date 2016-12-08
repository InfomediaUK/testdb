package com.helmet.application.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AppService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.BookingGradeApplicantDateUserEntity;
import com.helmet.bean.BookingGradeApplicantUser;
import com.helmet.bean.BookingGradeApplicantUserEntity;

public class BookingDatesView extends AppAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

    	Integer bookingGradeApplicantId = AppUtilities.getCurrentBookingGradeApplicantId(request);

    	String[] selectedBookingDates = (String[])dynaForm.get("selectedBookingDates");

    	String bookingDateIdStrs = "";
    	
    	for (String bookingDateIdStr: selectedBookingDates) {
    		if (!"".equals(bookingDateIdStrs)) {
    			bookingDateIdStrs = bookingDateIdStrs + ",";
    		}
			bookingDateIdStrs = bookingDateIdStrs + bookingDateIdStr;
    	}
    	
     	AppService appService = ServiceFactory.getInstance().getAppService();
     	
		BookingGradeApplicantUserEntity bookingGradeApplicant = appService.getBookingGradeApplicantUserEntityAndBookingDates(bookingGradeApplicantId, bookingDateIdStrs);
     	
//     	BookingGradeApplicantUser bookingGradeApplicant = appService.getBookingGradeApplicantUser(bookingGradeApplicantId);
//     	
//     	List<BookingGradeApplicantDateUserEntity> bookingGradeApplicantDates = appService.getBookingGradeApplicantDateUsersForBookingGradeApplicantAndBookingDates(bookingGradeApplicantId, bookingDateIdStrs);

		// in ancestor class
		sortShiftTotals(bookingGradeApplicant.getBookingGradeApplicantDateUserEntities(), dynaForm); 

		dynaForm.set("bookingGradeApplicant", bookingGradeApplicant); 
		dynaForm.set("bookingDateIdStrs", bookingDateIdStrs);
    	
    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");
    }

}