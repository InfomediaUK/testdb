package com.helmet.application.agy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.FileHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.AgencyInvoiceUser;


public class AgencyInvoiceUploadTimesheetProcess extends AgyAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

		AgencyInvoiceUser agencyInvoice = (AgencyInvoiceUser)dynaForm.get("agencyInvoice");
     	
    	FormFile uploadedTimesheetFile = (FormFile)dynaForm.get("timesheetFile");
        String uploadedTimesheetContentType = uploadedTimesheetFile.getContentType();
        String uploadedTimesheetFilename    = uploadedTimesheetFile.getFileName();
        int uploadedTimesheetFileSize       = uploadedTimesheetFile.getFileSize();
    
        if (uploadedTimesheetFilename != null && !"".equals(uploadedTimesheetFilename)) { 

	        int indexOfLastDot = uploadedTimesheetFile.getFileName().lastIndexOf(".");
	        
	        String uploadedFileExtension = "";
	        
	        if (indexOfLastDot > -1) {
	        	uploadedFileExtension = uploadedTimesheetFile.getFileName().substring(indexOfLastDot);
	        }

	        // if uploaded file is a tif or a tiff
	        // - we need to convert it to a pdf
	        // - update the agencyInvoice to link to the pdf 
	        // - but still upload the original file - so we have it - incase all goes pants!

	        agencyInvoice.setTimesheetFilename(uploadedTimesheetFilename);
	    	String uploadedFilePath = FileHandler.getInstance().getInvoiceFileLocation() + agencyInvoice.getTimesheetUrl();
	    	
	    	String pdfFilePath = null;
	    	
	        if (uploadedFileExtension.equalsIgnoreCase(".tif") || uploadedFileExtension.equalsIgnoreCase(".tiff")) {
	        	String pdfFilename = uploadedTimesheetFilename.substring(0, indexOfLastDot) + ".pdf";
	        	agencyInvoice.setTimesheetFilename(pdfFilename);
		    	pdfFilePath = FileHandler.getInstance().getInvoiceFileLocation() + agencyInvoice.getTimesheetUrl();

		    	System.out.println(pdfFilePath);
	        	
	        }
	        
			AgyService agyService = ServiceFactory.getInstance().getAgyService();
			
	     	int rowCount = agyService.updateAgencyInvoiceTimesheet(agencyInvoice, getConsultantLoggedIn().getConsultantId());

            // now upload the file        
        
			// Read the InputStream and store it in a 'byteArrayOutputStream'.

	     	try
	        {
	            byte[] fileData = uploadedTimesheetFile.getFileData();
	
	            File folder = new File(uploadedFilePath).getParentFile();
	            if (!folder.exists())
	            {
	              // Create any required directories.
	              folder.mkdirs();
	            }
	            FileOutputStream fileOutputStream =  new FileOutputStream(uploadedFilePath);
	            fileOutputStream.write(fileData);
	            fileOutputStream.close();
	            
	            if (pdfFilePath != null) {
	            	// convert the uploaded tiff file to a pdf
	            	FileHandler.getInstance().convertTiffToPdf(uploadedFilePath, pdfFilePath);
	            }
	            
	        	
	        }
	        catch(IOException e)
	        {
	            // TODO
	        	System.out.println("IOException - uploading " + uploadedTimesheetFilename);
	        }
        }        
     	
     	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?agencyInvoice.agencyInvoiceId=" + agencyInvoice.getAgencyInvoiceId(),
    	                         actionForward.getRedirect());

    }
    
}
