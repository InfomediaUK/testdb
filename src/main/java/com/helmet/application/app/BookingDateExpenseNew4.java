package com.helmet.application.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.application.FileHandler;
import com.helmet.application.app.abztract.AppAction;


public class BookingDateExpenseNew4 extends AppAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	Integer page = (Integer)dynaForm.get("page");
    	if (isCancelled(request)) {
    		dynaForm.set("page", page - 1);
         	return mapping.findForward("back");
    	}

    	FormFile myFile = (FormFile)dynaForm.get("document");
        String contentType = myFile.getContentType();
        String filename    = myFile.getFileName();
        int fileSize       = myFile.getFileSize();
    
        if (filename != null && !"".equals(filename)) { 
        	// uploaded file to process

		    int indexOfLastDot = myFile.getFileName().lastIndexOf(".");
		    
		    String fileExtension = "";
		    
		    if (indexOfLastDot > -1) {
		    	fileExtension = myFile.getFileName().substring(indexOfLastDot);
		    }
		    	
			String tempFileName = request.getSession().getId() + fileExtension;
			String tempFileUrl = request.getContextPath() + FileHandler.getInstance().getTempFileFolder() + 
			                     "/" + tempFileName;
			String tempFilePath = FileHandler.getInstance().getTempFileLocation() + 
			                      FileHandler.getInstance().getTempFileFolder() + 
			                      "/" + tempFileName;
			
			dynaForm.set("contentType", contentType);
			dynaForm.set("filename", filename);
			dynaForm.set("fileSize", fileSize);
			dynaForm.set("tempFileName", tempFileName);
			dynaForm.set("tempFileUrl", tempFileUrl);
			dynaForm.set("tempFilePath", tempFilePath);
		
			// Read the InputStream and store it in a 'byteArrayOutputStream'.
		    try
		    {
		        byte[] fileData = myFile.getFileData();
		
		        File grandParentFolder = new File(tempFilePath).getParentFile().getParentFile();
		        if (!grandParentFolder.exists())
		        {
		        	// Create any required directories.
		        	grandParentFolder.mkdirs();
		        }
		        File parentFolder = new File(tempFilePath).getParentFile();
		        if (!parentFolder.exists())
		        {
		        	// Create any required directories.
		        	parentFolder.mkdirs();
		        }
		        FileOutputStream fileOutputStream =  new FileOutputStream(tempFilePath);
		        fileOutputStream.write(fileData);
		        fileOutputStream.close();
		        	
		        }
		    catch(IOException e)
		    {
		         System.out.println("IOException");
		    }
        }
		
    	logger.exit("Out going !!!");

     	return mapping.findForward("success");
    }

}
