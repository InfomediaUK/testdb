package com.helmet.application.agy.abztract;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.IllegalAccessException;
import com.helmet.application.FileHandler;
import com.helmet.application.Utilities;
import com.helmet.application.agy.AgyUtilities;
import com.helmet.bean.AgencyInvoiceUser;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.Consultant;
import com.helmet.bean.IntValue;
import com.helmet.bean.StatusCount;

public abstract class AgyAction extends Action {

    private Consultant consultantLoggedIn;
    
	public Consultant getConsultantLoggedIn() {
		return consultantLoggedIn;
	}
	public void setConsultantLoggedIn(Consultant consultantLoggedIn) {
		this.consultantLoggedIn = consultantLoggedIn;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Consultant consultant = AgyUtilities.getCurrentConsultant(request);

		if (consultant == null) {
			throw new IllegalAccessException();
		}

		setConsultantLoggedIn(consultant);

		// store counts needed for menu - shown on EVERY page
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		// all is well, so call the actual execute method
		
		ActionForward af = beforeDoExecute(mapping, form, request, response);
		if (af != null) {
			return af;
		}
		try {
			return doExecute(mapping, form, request, response);
		}
		finally {

//			if (getConsultantLoggedIn().getUser().getSuperUser()) {
//				
			
			// bookings
			List<StatusCount> bookingStatusCounts = agyService.getBookingStatusCountsForAgency(consultant.getAgencyId());
	        request.setAttribute("bookingStatusCounts", bookingStatusCounts);

			// shifts
			List<StatusCount> shiftStatusCounts = agyService.getShiftStatusCountsForAgency(consultant.getAgencyId());
	        request.setAttribute("shiftStatusCounts", shiftStatusCounts);

	        // shifts to activate
	        //IntValue iv = new IntValue();
	        //iv.setValue(999);
	        //IntValue shiftsToActivateCount = iv;
	        IntValue shiftsToActivateCount = agyService.getShiftsToActivateCountForAgency(consultant.getAgencyId());
	        request.setAttribute("shiftsToActivateCount", shiftsToActivateCount);
	        
	        // shifts outstanding
	        IntValue shiftsOutstandingCount = agyService.getShiftsOutstandingCountForAgency(consultant.getAgencyId());
	        request.setAttribute("shiftsOutstandingCount", shiftsOutstandingCount);
	        
			// offered bookings
			List<StatusCount> bookingGradeApplicantDateStatusCounts = agyService.getBookingGradeApplicantDateStatusCountsForAgency(consultant.getAgencyId());
	        request.setAttribute("bookingGradeApplicantDateStatusCounts", bookingGradeApplicantDateStatusCounts);

			// worked shifts
			List<StatusCount> shiftWorkedStatusCounts = agyService.getShiftWorkedStatusCountsForAgency(consultant.getAgencyId());
	        request.setAttribute("shiftWorkedStatusCounts", shiftWorkedStatusCounts);

			// agencyInvoices
			List<StatusCount> agencyInvoiceStatusCounts = agyService.getAgencyInvoiceStatusCountsForAgency(consultant.getAgencyId());
	        request.setAttribute("agencyInvoiceStatusCounts", agencyInvoiceStatusCounts);

//
//			}

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

  protected Integer getWeekToShow(HttpServletRequest request, DynaValidatorForm dynaForm)
  {
    HttpSession session = request.getSession();
    // Try and get Week To Show from form.
    Integer weekToShow = (Integer)dynaForm.get("weekToShow");
    if (weekToShow == null)
    {
      // Week To Show NOT on form, try Session.
      weekToShow = (Integer)session.getAttribute("weekToShow");
      if (weekToShow == null)
      {
        // Week To Show NOT in Session, set to Current Week.
       weekToShow = new Integer(0);
      }
    }
    // Put Week To Show into Session
    session.setAttribute("weekToShow", weekToShow);
    dynaForm.set("weekToShow", weekToShow);
    return weekToShow;
  }

  protected Integer getPageNumber(String pageName, HttpServletRequest request, DynaValidatorForm dynaForm)
  {
    HttpSession session = request.getSession();
    // Try and get Page Number for the Page Name from form.
    Integer pageNumber = (Integer)dynaForm.get("page");
    if (pageNumber == null)
    {
      // Page Number NOT on form, try Session.
      pageNumber = (Integer)session.getAttribute(pageName);
      if (pageNumber == null)
      {
        // Page Number NOT in Session, set to 1.
       pageNumber = new Integer(1);
      }
    }
    // Put Page Number into Session
    session.setAttribute(pageName, pageNumber);
    dynaForm.set("page", pageNumber);
    return pageNumber;
  }

  protected Date convertDate(String dateStr, SimpleDateFormat simpleDateFormat, ActionMessages errors, MessageResources messageResources, String messageKey)
  {
    Date date = null;
    if (StringUtils.isNotEmpty(dateStr))
    {
      try
      {
        date = new Date(simpleDateFormat.parse(dateStr).getTime());
      }
      catch (ParseException e)
      {
        errors.add("dateFormatError", new ActionMessage("errors.invalidDateFormat", messageResources.getMessage(messageKey)));
      }
    }
    return date;
  }
  
  protected void sortShiftTotals(List list, DynaValidatorForm dynaForm)
  {
    // also in bookingsearch
    BigDecimal totalHours = new BigDecimal(0);
    BigDecimal totalAgreedValue = new BigDecimal(0); // Charge Rate
    BigDecimal totalAgreedWageRateValue = new BigDecimal(0); // Wage Rate
    BigDecimal totalActualValue = new BigDecimal(0); // Charge Rate
    BigDecimal totalActualWageRateValue = new BigDecimal(0); // Wage Rate
    BigDecimal totalActualHours = new BigDecimal(0);
    BigDecimal totalActualVatValue = new BigDecimal(0);
    BigDecimal totalExpenseValue = new BigDecimal(0);
    BigDecimal totalExpenseVatValue = new BigDecimal(0);
    BigDecimal totalTotalVatValue = new BigDecimal(0);
    BigDecimal totalActualTotalValue = new BigDecimal(0);

    BigDecimal totalNetValue = new BigDecimal(0);
    BigDecimal totalVatValue = new BigDecimal(0);
    BigDecimal totalValue = new BigDecimal(0);

    for (int i = 0; i < list.size(); i++)
    {
      BookingDateUserApplicant bookingDateUserApplicant = (BookingDateUserApplicant) list.get(i);
      // LYNDON. The following 'add' code did NOT have the defensive check for null in it. 11/07/2011
      if (bookingDateUserApplicant.getShiftNoOfHours() != null && bookingDateUserApplicant.getUndefinedShift() == false)
      {
        totalHours = totalHours.add(bookingDateUserApplicant.getShiftNoOfHours());
      }
      if (bookingDateUserApplicant.getChargeRateValue() != null && bookingDateUserApplicant.getUndefinedShift() == false)
      {
        totalAgreedValue = totalAgreedValue.add(bookingDateUserApplicant.getChargeRateValue());
      }
      // LYNDON. The following 'add' code did NOT have the defensive check for null in it. 11/07/2011
      if (bookingDateUserApplicant.getWageRateValue() != null && bookingDateUserApplicant.getUndefinedShift() == false)
      {
        totalAgreedWageRateValue = totalAgreedWageRateValue.add(bookingDateUserApplicant.getWageRateValue());
      }
      // if (bookingDateUserApplicant.getWorkedStatus() ==
      // BookingDate.BOOKINGDATE_WORKEDSTATUS_AUTHORIZED ||
      // bookingDateUserApplicant.getWorkedStatus() ==
      // BookingDate.BOOKINGDATE_WORKEDSTATUS_INVOICED) {
      // ONLY authorized AND invoiced

      if (bookingDateUserApplicant.getWorkedChargeRateValue() != null)
      {
        totalActualValue = totalActualValue.add(bookingDateUserApplicant.getWorkedChargeRateValue());
      }
      if (bookingDateUserApplicant.getWorkedWageRateValue() != null)
      {
        totalActualWageRateValue = totalActualWageRateValue.add(bookingDateUserApplicant.getWorkedWageRateValue());
      }
      if (bookingDateUserApplicant.getWorkedNoOfHours() != null)
      {
        totalActualHours = totalActualHours.add(bookingDateUserApplicant.getWorkedNoOfHours());
      }
      if (bookingDateUserApplicant.getWorkedVatValue() != null)
      {
        totalActualVatValue = totalActualVatValue.add(bookingDateUserApplicant.getWorkedVatValue());
      }
      if (bookingDateUserApplicant.getExpenseValue() != null)
      {
        totalExpenseValue = totalExpenseValue.add(bookingDateUserApplicant.getExpenseValue());
      }
      if (bookingDateUserApplicant.getExpenseVatValue() != null)
      {
        totalExpenseVatValue = totalExpenseVatValue.add(bookingDateUserApplicant.getExpenseVatValue());
      }
      if (bookingDateUserApplicant.getTotalVatValue() != null)
      {
        totalTotalVatValue = totalTotalVatValue.add(bookingDateUserApplicant.getTotalVatValue());
      }
      if (bookingDateUserApplicant.getWorkedTotalValue() != null)
      {
        totalActualTotalValue = totalActualTotalValue.add(bookingDateUserApplicant.getWorkedTotalValue());
      }

      // }

      if (bookingDateUserApplicant.getWorkedTotalNetValue() != null)
      {
        totalNetValue = totalNetValue.add(bookingDateUserApplicant.getWorkedTotalNetValue());
      }
      if (bookingDateUserApplicant.getTotalVatValue() != null)
      {
        totalVatValue = totalVatValue.add(bookingDateUserApplicant.getTotalVatValue());
      }
      if (bookingDateUserApplicant.getWorkedTotalValue() != null)
      {
        totalValue = totalValue.add(bookingDateUserApplicant.getWorkedTotalValue());
      }
    }

    dynaForm.set("totalHours", totalHours);
    dynaForm.set("totalAgreedValue", totalAgreedValue);
    dynaForm.set("totalAgreedWageRateValue", totalAgreedWageRateValue);
    dynaForm.set("totalActualValue", totalActualValue);
    dynaForm.set("totalActualWageRateValue", totalActualWageRateValue);
    dynaForm.set("totalActualHours", totalActualHours);
    dynaForm.set("totalActualVatValue", totalActualVatValue);
    dynaForm.set("totalExpenseValue", totalExpenseValue);
    dynaForm.set("totalExpenseVatValue", totalExpenseVatValue);
    dynaForm.set("totalTotalVatValue", totalTotalVatValue);
    dynaForm.set("totalActualTotalValue", totalActualTotalValue);

    dynaForm.set("totalNetValue", totalNetValue);
    dynaForm.set("totalVatValue", totalVatValue);
    dynaForm.set("totalValue", totalValue);

    // // also in bookingsearch
    // BigDecimal totalHours = new BigDecimal(0);
    // BigDecimal totalChargeRateValue = new BigDecimal(0);
    // BigDecimal totalPayRateValue = new BigDecimal(0);
    // BigDecimal totalWageRateValue = new BigDecimal(0);
    // BigDecimal totalExpenseValue = new BigDecimal(0);
    // BigDecimal totalNetValue = new BigDecimal(0);
    // BigDecimal totalVatValue = new BigDecimal(0);
    // BigDecimal totalValue = new BigDecimal(0);
    //
    // for (int i = 0; i < list.size(); i++) {
    // BookingDateUserApplicant bookingDateUserApplicant =
    // (BookingDateUserApplicant)list.get(i);
    // totalHours =
    // totalHours.add(bookingDateUserApplicant.getShiftNoOfHours());
    // if (bookingDateUserApplicant.getChargeRateValue() != null) {
    // totalChargeRateValue =
    // totalChargeRateValue.add(bookingDateUserApplicant.getChargeRateValue());
    // }
    // if (bookingDateUserApplicant.getPayRateValue() != null) {
    // totalPayRateValue =
    // totalPayRateValue.add(bookingDateUserApplicant.getPayRateValue());
    // }
    // if (bookingDateUserApplicant.getWageRateValue() != null) {
    // totalWageRateValue =
    // totalWageRateValue.add(bookingDateUserApplicant.getWageRateValue());
    // }
    //        
    // if (bookingDateUserApplicant.getExpenseValue() != null) {
    // totalExpenseValue =
    // totalExpenseValue.add(bookingDateUserApplicant.getExpenseValue());
    // }
    // if (bookingDateUserApplicant.getWorkedTotalNetValue() != null) {
    // totalNetValue =
    // totalNetValue.add(bookingDateUserApplicant.getWorkedTotalNetValue());
    // }
    // if (bookingDateUserApplicant.getTotalVatValue() != null) {
    // totalVatValue =
    // totalVatValue.add(bookingDateUserApplicant.getTotalVatValue());
    // }
    // if (bookingDateUserApplicant.getWorkedTotalValue() != null) {
    // totalValue =
    // totalValue.add(bookingDateUserApplicant.getWorkedTotalValue());
    // }
    //	    
    // }
    //	    
    // dynaForm.set("totalHours", totalHours);
    // dynaForm.set("totalChargeRateValue", totalChargeRateValue);
    // dynaForm.set("totalPayRateValue", totalPayRateValue);
    // dynaForm.set("totalWageRateValue", totalWageRateValue);
    //
    //     	dynaForm.set("totalExpenseValue", totalExpenseValue);
    //		dynaForm.set("totalNetValue", totalNetValue);
    //  		dynaForm.set("totalVatValue", totalVatValue);
    //     	dynaForm.set("totalValue", totalValue);

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
    
    protected String getApplicantNotes(Applicant applicant)
    {
      String notesFileName = FileHandler.getInstance().getApplicantFileLocation() +
                             FileHandler.getInstance().getApplicantFileFolder() + 
                             "/" + applicant.getApplicantId() + "/notes.txt";
      StringBuffer notes   = new StringBuffer(); 
      Utilities.suckInFile(notesFileName, notes);
      return notes.toString();
    }
    
    protected void saveApplicantNotes(Applicant applicant, String notes)
    {
      String notesFileName = FileHandler.getInstance().getApplicantFileLocation() +
                             FileHandler.getInstance().getApplicantFileFolder() + 
                             "/" + applicant.getApplicantId() + "/notes.txt";
      File folder = new File(notesFileName).getParentFile();
      if (!folder.exists())
      {
        // Create any required directories.
        folder.mkdirs();
      }
      try
      {
        Utilities.saveFile(notesFileName, notes);
      }
      catch (IOException e)
      {
        // TODO 
        System.out.println("IOException - uploading " + notesFileName);
      }
    }
    
    protected void signAndDateNotes(StringBuffer notesStringBuffer)
    {
      if (notesStringBuffer.length() > 0)
      {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        notesStringBuffer.append("\n[");
        notesStringBuffer.append(sdf.format(new Date(calendar.getTimeInMillis())));
        notesStringBuffer.append(" ");
        notesStringBuffer.append(getConsultantLoggedIn().getUser().getFullName());
        notesStringBuffer.append("]\n");
      }
    }
}
