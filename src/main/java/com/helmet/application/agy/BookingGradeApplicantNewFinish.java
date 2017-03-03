package com.helmet.application.agy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.helmet.application.FileHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.BookingDateUser;
import com.helmet.bean.BookingGradeAgy;
import com.helmet.bean.BookingGradeApplicant;
import com.helmet.bean.BookingGradeApplicantDate;
import com.helmet.bean.BookingGradeApplicantUser;


public class BookingGradeApplicantNewFinish extends AgyAction {

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

    	// checks !!!
    	
    	BookingGradeAgy bookingGradeAgy = (BookingGradeAgy)dynaForm.get("bookingGrade");
     	Applicant applicant = (Applicant)dynaForm.get("applicant");

     	if (bookingGradeAgy.getBookingGradeId() == null || bookingGradeAgy.getBookingGradeId() == 0) {
     		return mapping.findForward("bookingGradeApplicantNew");
    	}
     	if (applicant.getApplicantId() == null || applicant.getApplicantId() == 0) {
     		return mapping.findForward("bookingGradeApplicantNew");
    	}

     	// TODO
     	//
     	// MUST check that ... 
     	//		
     	//		applicant has already been put forward, that the job is still available etc etc 
     	//
     	
        String[] selectedBookingDates = (String[])dynaForm.get("selectedBookingDates");

		List<BookingDateUser> list = (List<BookingDateUser>)dynaForm.get("list");
     	
     	BookingGradeApplicantDate[] bookingGradeApplicantDates = new BookingGradeApplicantDate[selectedBookingDates.length - 1]; // array includes a dummy 0 entry - checkbox related!
        int i = 0;
		for (String bookingDateIdStr : selectedBookingDates) {
    		int bookingDateId = Integer.parseInt(bookingDateIdStr);
    		if (bookingDateId > 0) {
    			BookingGradeApplicantDate bookingGradeApplicantDate = new BookingGradeApplicantDate();
    			bookingGradeApplicantDate.setBookingDateId(bookingDateId);
    			
    			// set value and payRateValue from bookingDates agyValue and payRateValue

    	     	for (BookingDateUser bookingDateUser: list) {
                    if (bookingDateUser.getBookingDateId().equals(bookingDateId)) {
            			bookingGradeApplicantDate.setValue(bookingDateUser.getChargeRateValue());
            			bookingGradeApplicantDate.setPayRateValue(bookingDateUser.getPayRateValue());
            			bookingGradeApplicantDate.setWageRateValue(bookingDateUser.getWageRateValue());
            			break;
                    }
    	    	}
    	        
    			bookingGradeApplicantDates[i] = bookingGradeApplicantDate;
    			i++;
    		}
    	}

    	BookingGradeApplicant bookingGradeApplicant = (BookingGradeApplicant)dynaForm.get("bookingGradeApplicant");

    	boolean newBookingGradeApplicant = bookingGradeApplicant == null || bookingGradeApplicant.getBookingGradeApplicantId() == null;
    	
    	if (newBookingGradeApplicant) {
    		// it's a NEW one
         	bookingGradeApplicant = new BookingGradeApplicant();

         	bookingGradeApplicant.setBookingGradeId(bookingGradeAgy.getBookingGradeId());
         	bookingGradeApplicant.setApplicantId(applicant.getApplicantId());

    	}
    	
		String filename = (String)dynaForm.get("filename");
        if (filename != null && !"".equals(filename)) {
         	bookingGradeApplicant.setFilename(filename);
        }

		DecimalFormat df = new DecimalFormat("#0.00");
     	
		BigDecimal rate = (BigDecimal)dynaForm.get("rate");
		BigDecimal payRate = (BigDecimal)dynaForm.get("payRate");
		BigDecimal wtdPercentage = (BigDecimal)dynaForm.get("wtdPercentage");
		BigDecimal niPercentage = (BigDecimal)dynaForm.get("niPercentage");
		BigDecimal wageRate = (BigDecimal)dynaForm.get("wageRate");

		BigDecimal chargeRateVatRate = (BigDecimal)dynaForm.get("chargeRateVatRate");
		BigDecimal payRateVatRate = (BigDecimal)dynaForm.get("payRateVatRate");
		BigDecimal wtdVatRate = (BigDecimal)dynaForm.get("wtdVatRate");
		BigDecimal niVatRate = (BigDecimal)dynaForm.get("niVatRate");
		BigDecimal commissionVatRate = (BigDecimal)dynaForm.get("commissionVatRate");

     	bookingGradeApplicant.setRate(rate);
     	bookingGradeApplicant.setPayRate(payRate);
     	bookingGradeApplicant.setWtdPercentage(wtdPercentage);
     	bookingGradeApplicant.setNiPercentage(niPercentage);
     	bookingGradeApplicant.setWageRate(wageRate);
     	
     	bookingGradeApplicant.setChargeRateVatRate(chargeRateVatRate);
     	bookingGradeApplicant.setPayRateVatRate(payRateVatRate);
     	bookingGradeApplicant.setWtdVatRate(wtdVatRate);
     	bookingGradeApplicant.setNiVatRate(niVatRate);
     	bookingGradeApplicant.setCommissionVatRate(commissionVatRate);
     	
     	Integer clientAgencyJobProfileGradeId = (Integer)dynaForm.get("clientAgencyJobProfileGradeId");
     	bookingGradeApplicant.setClientAgencyJobProfileGradeId(clientAgencyJobProfileGradeId);

     	AgyService agyService = ServiceFactory.getInstance().getAgyService();
     	
     	if (newBookingGradeApplicant) {
      	    int rowCount = agyService.insertBookingGradeApplicant(bookingGradeApplicant, bookingGradeApplicantDates, getConsultantLoggedIn().getConsultantId());
     	}
     	else {
      	    int rowCount = agyService.updateBookingGradeApplicant(bookingGradeApplicant, bookingGradeApplicantDates, getConsultantLoggedIn().getConsultantId());
     	}
		
    	// if a file has been uploaded, copy the temporary file to real location
    	
        if (filename != null && !"".equals(filename)) {

        	String tempFilePath = (String)dynaForm.get("tempFilePath");

            String documentLocation = FileHandler.getInstance().getApplicantFileLocation() +
              FileHandler.getInstance().getApplicantFileFolder() + 
              "/" + bookingGradeApplicant.getBookingGradeApplicantId() + 
              "/" + filename;

            File parentDir = new File(documentLocation).getParentFile();
            // Check that the directory exists before writing a backup.
            if (!parentDir.exists())
            {
              // If not, create it.
              parentDir.mkdirs();
            }
            
            FileInputStream from = null; // Stream to read from source
			FileOutputStream to = null; // Stream to write to destination
			try {
				from = new FileInputStream(tempFilePath); // Create input
															// stream
				to = new FileOutputStream(documentLocation); // Create output
																// stream
				byte[] buffer = new byte[4096]; // A buffer to hold file
												// contents
				int bytes_read; // How many bytes in buffer
				// Read a chunk of bytes into the buffer, then write them out,
				// looping until we reach the end of the file (when read()
				// returns -1).
				// Note the combination of assignment and comparison in this
				// while
				// loop. This is a common I/O programming idiom.
				while ((bytes_read = from.read(buffer)) != -1)
					// Read bytes until EOF
					to.write(buffer, 0, bytes_read); // write bytes
			}
			catch (IOException e)
			{
				logger.error("Trying to write " + documentLocation + " from " + tempFilePath);
			}
			// Always close the streams, even if exceptions were thrown
			finally {
				if (from != null)
					try {
						from.close();
					} catch (IOException e) {
						;
					}
				if (to != null)
					try {
						to.close();
					} catch (IOException e) {
						;
					}
			}

			boolean success = (new File(tempFilePath)).delete();
			if (!success) {
			    // Deletion failed
				logger.error("Trying to delete " + tempFilePath);
			}
        	
        }
    	
    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?bookingGradeApplicant.bookingGradeApplicantId=" + bookingGradeApplicant.getBookingGradeApplicantId(),
    	                         actionForward.getRedirect());

    }

}
