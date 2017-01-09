package com.helmet.application.mgr.abztract;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.IllegalAccessException;
import com.helmet.application.mgr.MgrUtilities;
import com.helmet.bean.AgencyInvoiceUser;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingUser;
import com.helmet.bean.IntValue;
import com.helmet.bean.Manager;
import com.helmet.bean.StatusCount;

public abstract class MgrAction extends Action {

    private Manager managerLoggedIn;
    
	public Manager getManagerLoggedIn() {
		return managerLoggedIn;
	}
	public void setManagerLoggedIn(Manager managerLoggedIn) {
		this.managerLoggedIn = managerLoggedIn;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Manager manager = MgrUtilities.getCurrentManager(request);

		if (manager == null) {
			throw new IllegalAccessException();
		}

//		mapping.getModuleConfig().findActionConfigs()		
//		mapping.getModuleConfig().findActionConfig(mapping.getPath())
//		mapping.getModuleConfig().getPrefix()
		
		setManagerLoggedIn(manager);

		// all is well, so call the actual execute method
		
		// store counts needed for menu - shown on EVERY page
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		ActionForward af = beforeDoExecute(mapping, form, request, response);
		if (af != null) {
			return af;
		}
		try {
			return doExecute(mapping, form, request, response);
		}
		finally {

			// bookings
			List<StatusCount> bookingStatusCounts = mgrService.getBookingStatusCountsForManager(manager.getManagerId());
	        request.setAttribute("bookingStatusCounts", bookingStatusCounts);
			
	        // shifts
			List<StatusCount> shiftStatusCounts = mgrService.getShiftStatusCountsForManager(manager.getManagerId());
	        request.setAttribute("shiftStatusCounts", shiftStatusCounts);
			
	        // timesheets - booking based !!!
			List<StatusCount> bookingWorkedStatusCounts = mgrService.getBookingWorkedStatusCountsForManager(manager.getManagerId());
	        request.setAttribute("bookingWorkedStatusCounts", bookingWorkedStatusCounts);
			
	        // timesheets - shift based ???
			List<StatusCount> shiftWorkedStatusCounts = mgrService.getShiftWorkedStatusCountsForManager(manager.getManagerId());
	        request.setAttribute("shiftWorkedStatusCounts", shiftWorkedStatusCounts);
			
	        // applicants
			List<StatusCount> bookingGradeApplicantDateStatusCounts = mgrService.getBookingGradeApplicantDateStatusCountsForManager(manager.getManagerId());
	        request.setAttribute("bookingGradeApplicantDateStatusCounts", bookingGradeApplicantDateStatusCounts);
			
	        // shifts to activate
	        IntValue shiftsToActivateCount = mgrService.getShiftsToActivateCountForManager(manager.getManagerId());
	        request.setAttribute("shiftsToActivateCount", shiftsToActivateCount);
	        
	        // shifts outstanding
	        IntValue shiftsOutstandingCount = mgrService.getShiftsOutstandingCountForManager(manager.getManagerId());
	        request.setAttribute("shiftsOutstandingCount", shiftsOutstandingCount);
	        
			// agencyInvoices
			List<StatusCount> agencyInvoiceStatusCounts = mgrService.getAgencyInvoiceStatusCountsForManager(manager.getClientId(), manager.getManagerId());
	        request.setAttribute("agencyInvoiceStatusCounts", agencyInvoiceStatusCounts);
	        
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
    
    protected void sortBookingTotals(List<BookingUser> list, DynaValidatorForm dynaForm) {
		// also in bookingsearch
	    BigDecimal totalHours = new BigDecimal(0);
	    BigDecimal totalRRPValue = new BigDecimal(0);
	    BigDecimal totalAgreedValue = new BigDecimal(0);
	    BigDecimal totalActualValue = new BigDecimal(0);
	    BigDecimal totalActualHours = new BigDecimal(0);

	    for (BookingUser bookingUser: list) {
//	    	if (bookingUser.getStatus() != Booking.BOOKING_STATUS_CANCELLED) {
		    	totalHours = totalHours.add(bookingUser.getNoOfHours());
		    	totalRRPValue = totalRRPValue.add(bookingUser.getValue());
		    	totalAgreedValue = totalAgreedValue.add(bookingUser.getFilledValue());
		    	totalActualValue = totalActualValue.add(bookingUser.getWorkedValue());
		    	totalActualHours = totalActualHours.add(bookingUser.getWorkedNoOfHours());
//	    	}
        }
		
     	dynaForm.set("totalHours", totalHours);
     	dynaForm.set("totalRRPValue", totalRRPValue);
     	dynaForm.set("totalAgreedValue", totalAgreedValue);
     	dynaForm.set("totalActualValue", totalActualValue);
     	dynaForm.set("totalActualValue", totalActualValue);
     	dynaForm.set("totalActualHours", totalActualHours);
    }

    protected void sortShiftTotals(List<BookingDateUserApplicant> list, DynaValidatorForm dynaForm) {
		// also in bookingsearch
	    BigDecimal totalHours = new BigDecimal(0);
	    BigDecimal totalRRPValue = new BigDecimal(0);
	    BigDecimal totalAgreedValue = new BigDecimal(0);
	    BigDecimal totalActualValue = new BigDecimal(0);
	    BigDecimal totalActualHours = new BigDecimal(0);
	    BigDecimal totalActualVatValue = new BigDecimal(0);
	    BigDecimal totalExpenseValue = new BigDecimal(0);
	    BigDecimal totalExpenseVatValue = new BigDecimal(0);
	    BigDecimal totalTotalVatValue = new BigDecimal(0);
	    BigDecimal totalActualTotalValue = new BigDecimal(0);

	    for (BookingDateUserApplicant bookingDateUserApplicant: list) {
//	    	if (bookingDateUserApplicant.getStatus() != BookingDateUserApplicant.BOOKINGDATE_STATUS_CANCELLED) {
		    	totalHours = totalHours.add(bookingDateUserApplicant.getShiftNoOfHours());
		    	totalRRPValue = totalRRPValue.add(bookingDateUserApplicant.getValue());
		    	if (bookingDateUserApplicant.getChargeRateValue() != null) {
			    	totalAgreedValue = totalAgreedValue.add(bookingDateUserApplicant.getChargeRateValue());
		    	}

		    	
		    	if (bookingDateUserApplicant.getWorkedChargeRateValue() != null) {
		    	    totalActualValue = totalActualValue.add(bookingDateUserApplicant.getWorkedChargeRateValue());
		    	}
			    if (bookingDateUserApplicant.getWorkedNoOfHours() != null) {
			    	totalActualHours = totalActualHours.add(bookingDateUserApplicant.getWorkedNoOfHours());
		    	}
			    if (bookingDateUserApplicant.getWorkedVatValue() != null) {
		    		totalActualVatValue = totalActualVatValue.add(bookingDateUserApplicant.getWorkedVatValue());
		    	}
			    if (bookingDateUserApplicant.getExpenseValue() != null) {
			    	totalExpenseValue = totalExpenseValue.add(bookingDateUserApplicant.getExpenseValue());
		    	}
			    if (bookingDateUserApplicant.getExpenseVatValue() != null) {
		    		totalExpenseVatValue = totalExpenseVatValue.add(bookingDateUserApplicant.getExpenseVatValue());
		    	}
			    if (bookingDateUserApplicant.getTotalVatValue() != null) {
		    		totalTotalVatValue = totalTotalVatValue.add(bookingDateUserApplicant.getTotalVatValue());
		    	}
			    if (bookingDateUserApplicant.getWorkedTotalValue() != null) {
		    		totalActualTotalValue = totalActualTotalValue.add(bookingDateUserApplicant.getWorkedTotalValue());
		    	}
		    	
//		    	if (bookingDateUserApplicant.getWorkedStatus() == BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED ||
//		    		bookingDateUserApplicant.getWorkedStatus() == BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED) {
//		    		// ONLY authorized AND invoiced
//		    		totalActualValue = totalActualValue.add(bookingDateUserApplicant.getWorkedChargeRateValue());
//			    	totalActualHours = totalActualHours.add(bookingDateUserApplicant.getWorkedNoOfHours());
//		    		totalActualVatValue = totalActualVatValue.add(bookingDateUserApplicant.getWorkedVatValue());
//			    	totalExpenseValue = totalExpenseValue.add(bookingDateUserApplicant.getExpenseValue());
//		    		totalExpenseVatValue = totalExpenseVatValue.add(bookingDateUserApplicant.getExpenseVatValue());
//		    		totalTotalVatValue = totalTotalVatValue.add(bookingDateUserApplicant.getTotalVatValue());
//		    		totalActualTotalValue = totalActualTotalValue.add(bookingDateUserApplicant.getWorkedTotalValue());
//		    	}

//	    	}
        }
		
     	dynaForm.set("totalHours", totalHours);
     	dynaForm.set("totalRRPValue", totalRRPValue);
     	dynaForm.set("totalAgreedValue", totalAgreedValue);
     	dynaForm.set("totalActualValue", totalActualValue);
     	dynaForm.set("totalActualHours", totalActualHours);
     	dynaForm.set("totalActualVatValue", totalActualVatValue);
     	dynaForm.set("totalExpenseValue", totalExpenseValue);
     	dynaForm.set("totalExpenseVatValue", totalExpenseVatValue);
     	dynaForm.set("totalTotalVatValue", totalTotalVatValue);
     	dynaForm.set("totalActualTotalValue", totalActualTotalValue);
    }

    protected void sortAgencyInvoiceTotals(List<AgencyInvoiceUser> list, DynaValidatorForm dynaForm) {

    	BigDecimal totalPayRateValue = new BigDecimal(0);
	    BigDecimal totalWtdValue = new BigDecimal(0);
		BigDecimal totalNiValue = new BigDecimal(0);
		BigDecimal totalWageRateValue = new BigDecimal(0);
		BigDecimal totalCommissionValue = new BigDecimal(0);
		BigDecimal totalChargeRateValue = new BigDecimal(0);
		BigDecimal totalExpenseValue = new BigDecimal(0);
		BigDecimal totalDiscountValue = new BigDecimal(0);
		BigDecimal totalVatValue = new BigDecimal(0);
		BigDecimal totalTotal = new BigDecimal(0);
		BigDecimal totalHours = new BigDecimal(0);
										     
	    for (AgencyInvoiceUser agencyInvoice: list) {

	    	totalPayRateValue = totalPayRateValue.add(agencyInvoice.getPayRateValue());
	    	totalWtdValue = totalWtdValue.add(agencyInvoice.getWtdValue());
	    	totalNiValue = totalNiValue.add(agencyInvoice.getNiValue());
	    	totalWageRateValue = totalWageRateValue.add(agencyInvoice.getWageRateValue());
	    	totalCommissionValue = totalCommissionValue.add(agencyInvoice.getCommissionValue());
	    	totalChargeRateValue = totalChargeRateValue.add(agencyInvoice.getChargeRateValue());
	    	totalExpenseValue = totalExpenseValue.add(agencyInvoice.getExpenseValue());
	    	totalDiscountValue = totalDiscountValue.add(agencyInvoice.getDiscountValue());
	    	totalVatValue = totalVatValue.add(agencyInvoice.getVatValue());
	    	totalTotal = totalTotal.add(agencyInvoice.getTotalValue());
	    	totalHours = totalHours.add(agencyInvoice.getNoOfHours());
	    	
        }
		

    	dynaForm.set("totalPayRateValue", totalPayRateValue);
	    dynaForm.set("totalWtdValue", totalWtdValue);
		dynaForm.set("totalNiValue", totalNiValue);
		dynaForm.set("totalWageRateValue", totalWageRateValue);
		dynaForm.set("totalCommissionValue", totalCommissionValue);
		dynaForm.set("totalChargeRateValue", totalChargeRateValue);
		dynaForm.set("totalExpenseValue", totalExpenseValue);
		dynaForm.set("totalDiscountValue", totalDiscountValue);
		dynaForm.set("totalVatValue", totalVatValue);
		dynaForm.set("totalTotal", totalTotal);
	    dynaForm.set("totalHours", totalHours);

    }
    
}
