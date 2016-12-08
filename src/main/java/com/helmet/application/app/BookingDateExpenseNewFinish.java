package com.helmet.application.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

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
import com.helmet.application.FileHandler;
import com.helmet.application.app.abztract.AppAction;
import com.helmet.bean.BookingDateExpense;
import com.helmet.bean.BookingDateUserApplicant;
import com.helmet.bean.BookingExpense;
import com.helmet.bean.BookingUserEntity;


public class BookingDateExpenseNewFinish extends AppAction {

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

    	BookingDateUserApplicant bookingDate = (BookingDateUserApplicant)dynaForm.get("bookingDate");
    	BookingExpense bookingExpense = (BookingExpense)dynaForm.get("bookingExpense");
		BigDecimal qty = (BigDecimal)dynaForm.get("qty");
		BigDecimal value = (BigDecimal)dynaForm.get("value");
		BigDecimal vatValue = (BigDecimal)dynaForm.get("vatValue");
     	String filename = (String)dynaForm.get("filename");
     	String text = (String)dynaForm.get("text");
    	
    	//
    	// construct and save the bookingDateExpense
    	//
     	
     	AppService appService = ServiceFactory.getInstance().getAppService();
     	
    	BookingDateExpense bookingDateExpense = (BookingDateExpense)dynaForm.get("bookingDateExpense");

     	if (bookingDateExpense == null || bookingDateExpense.getBookingDateExpenseId() == null) {
			
			// New
	     	BookingDateExpense newBookingDateExpense = new BookingDateExpense();
	     	newBookingDateExpense.setBookingDateId(bookingDate.getBookingDateId());
	     	newBookingDateExpense.setBookingExpenseId(bookingExpense.getBookingExpenseId());
	     	newBookingDateExpense.setQty(qty);
	     	newBookingDateExpense.setValue(value);
	     	newBookingDateExpense.setVatValue(vatValue);
	     	newBookingDateExpense.setFilename(filename);
	     	newBookingDateExpense.setText(text);

         	int rowCount = appService.insertBookingDateExpense(newBookingDateExpense, getApplicantLoggedIn().getApplicantId());
	
         	// set here so copying of uploaded temporary file works - it uses the id
	     	bookingDateExpense.setBookingDateExpenseId(newBookingDateExpense.getBookingDateExpenseId());

     	}
     	else {
     		
	     	bookingDateExpense.setBookingDateId(bookingDate.getBookingDateId());
	     	bookingDateExpense.setBookingExpenseId(bookingExpense.getBookingExpenseId());
	     	bookingDateExpense.setQty(qty);
	     	bookingDateExpense.setValue(value);
	     	bookingDateExpense.setVatValue(vatValue);
	     	bookingDateExpense.setFilename(filename);
	     	bookingDateExpense.setText(text);

	     	int rowCount = appService.updateBookingDateExpense(bookingDateExpense, getApplicantLoggedIn().getApplicantId());
	     	
     	}

     	// if a file has been uploaded, copy the temporary file to real location
    	
        if (filename != null && !"".equals(filename)) {

        	String tempFilePath = (String)dynaForm.get("tempFilePath");

            String documentLocation = FileHandler.getInstance().getReceiptFileLocation() +
              FileHandler.getInstance().getReceiptFileFolder() + 
              "/" + bookingDateExpense.getBookingDateExpenseId() + 
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
    	
     	return mapping.findForward("success");

    }

}
