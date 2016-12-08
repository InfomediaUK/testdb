package com.helmet.application.app.abztract;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.exceptions.IllegalAccessException;
import com.helmet.application.app.AppUtilities;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingGradeApplicantDateUserEntity;

public abstract class AppAction extends Action {

    private Applicant applicantLoggedIn;
    
	public Applicant getApplicantLoggedIn() {
		return applicantLoggedIn;
	}
	public void setApplicantLoggedIn(Applicant applicantLoggedIn) {
		this.applicantLoggedIn = applicantLoggedIn;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Applicant applicant = AppUtilities.getCurrentApplicant(request);

		if (applicant == null) {
			throw new IllegalAccessException();
		}

		setApplicantLoggedIn(applicant);

		// all is well, so call the actual execute method
		
		ActionForward af = beforeDoExecute(mapping, form, request, response);
		
		if (af != null) {
			return af;
		}
		try {
			return doExecute(mapping, form, request, response);
		}
		finally {
			afterDoExecute(mapping, form, request, response);
		}
	}

	protected ActionForward beforeDoExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// override in subclasses if required
		return null;
	}
	
	protected void afterDoExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// override in subclasses if required
	}

    protected abstract ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response);

    protected void sortShiftTotals(List<BookingGradeApplicantDateUserEntity> list, DynaValidatorForm dynaForm) {
    	
        Boolean atLeastOneToSubmit = false;
    	
    	BigDecimal totalAgreedHours = new BigDecimal(0);
    	BigDecimal totalAgreedValue = new BigDecimal(0);
    	BigDecimal totalActualHours = new BigDecimal(0);
    	BigDecimal totalActualValue = new BigDecimal(0);
    	BigDecimal totalExpenseValue = new BigDecimal(0);

        
    	for (BookingGradeApplicantDateUserEntity bookingGradeApplicantDate: list) {
            if (!bookingGradeApplicantDate.getBookingDateIsCancelled()) {
    	    	if (bookingGradeApplicantDate.getWageRateValue() != null) {
    	    		totalAgreedValue = totalAgreedValue.add(bookingGradeApplicantDate.getWageRateValue());
    	    	}
    	    	if (bookingGradeApplicantDate.getShiftNoOfHours() != null) {
    	    		totalAgreedHours = totalAgreedHours.add(bookingGradeApplicantDate.getShiftNoOfHours());
    	    	}
    	    	if (bookingGradeApplicantDate.getWorkedNoOfHours() != null) {
    	    		totalActualHours = totalActualHours.add(bookingGradeApplicantDate.getWorkedNoOfHours());
    	    		totalActualValue = totalActualValue.add(bookingGradeApplicantDate.getWorkedWageRateValue());
    	    	}
    	    	if (bookingGradeApplicantDate.getExpenseValue() != null) {
    	    		totalExpenseValue = totalExpenseValue.add(bookingGradeApplicantDate.getExpenseValue());
    	    	}
            }
            if (bookingGradeApplicantDate.getCanBeSubmitted()) {
            	atLeastOneToSubmit = true;
            }
    	}

    	dynaForm.set("totalAgreedHours", totalAgreedHours); 
    	dynaForm.set("totalAgreedValue", totalAgreedValue); 
    	dynaForm.set("totalActualHours", totalActualHours); 
    	dynaForm.set("totalActualValue", totalActualValue); 
    	dynaForm.set("totalExpenseValue", totalExpenseValue); 
    	dynaForm.set("atLeastOneToSubmit", atLeastOneToSubmit); 

    }

}
