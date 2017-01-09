package com.helmet.application.mgr;

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
import org.apache.struts.actions.ActionDispatcher;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.FileHandler;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.Client;
import com.helmet.bean.ClientUser;

public class ClientActionDispatcher extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    protected ActionDispatcher dispatcher = new ActionDispatcher(this, ActionDispatcher.MAPPING_FLAVOR);

    public ActionForward doExecute(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
                	
    	try {
			return dispatcher.execute(mapping, form, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
    }
 
    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		ClientUser client = mgrService.getClientUser(getManagerLoggedIn().getClientId());
		
		dynaForm.set("client", client); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

    public ActionForward edit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

	 	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
	
		logger.entry("In coming !!!");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		ClientUser client = mgrService.getClientUser(getManagerLoggedIn().getClientId());
		
		dynaForm.set("client", client); 
		
		logger.exit("Out going !!!");
		
	 	return mapping.findForward("success");
	}
	
    public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Client client = (Client)dynaForm.get("client");
		client.setClientId(getManagerLoggedIn().getClientId());
		
    	FormFile logoFile = (FormFile)dynaForm.get("logoFile");
        String logoContentType = logoFile.getContentType();
        String logoFilename    = logoFile.getFileName();
        int logoFileSize       = logoFile.getFileSize();
    
        if (logoFilename != null && !"".equals(logoFilename)) { 
	        client.setLogoFilename(logoFilename);
        }     	
     	
    	FormFile logo2File = (FormFile)dynaForm.get("logo2File");
        String logo2ContentType = logo2File.getContentType();
        String logo2Filename    = logo2File.getFileName();
        int logo2FileSize       = logo2File.getFileSize();
    
        if (logo2Filename != null && !"".equals(logo2Filename)) { 
	        client.setLogo2Filename(logo2Filename);
        }     	
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.updateClient(client, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("client", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
        if (logoFilename != null && !"".equals(logoFilename)) { 
            // now upload the file        
        
	        int indexOfLastDot = logoFile.getFileName().lastIndexOf(".");
	        
	        String fileExtension = "";
	        
	        if (indexOfLastDot > -1) {
	        	fileExtension = logoFile.getFileName().substring(indexOfLastDot);
	        }
	        	
	    	String filePath = FileHandler.getInstance().getLogoFileLocation() + client.getLogoUrl();
	    	
			// Read the InputStream and store it in a 'byteArrayOutputStream'.
	        try
	        {
	            byte[] fileData = logoFile.getFileData();
	
	            File folder = new File(filePath).getParentFile();
	            if (!folder.exists())
	            {
	              // Create any required directories.
	              folder.mkdirs();
	            }
	            FileOutputStream fileOutputStream =  new FileOutputStream(filePath);
	            fileOutputStream.write(fileData);
	            fileOutputStream.close();
	        	
	        }
	        catch(IOException e)
	        {
	            // TODO
	        	System.out.println("IOException - uploading " + logoFilename);
	        }
        }        
    	
        if (logo2Filename != null && !"".equals(logo2Filename)) { 
            // now upload the file        
        
	        int indexOfLastDot = logo2File.getFileName().lastIndexOf(".");
	        
	        String fileExtension = "";
	        
	        if (indexOfLastDot > -1) {
	        	fileExtension = logo2File.getFileName().substring(indexOfLastDot);
	        }
	        	
	    	String filePath = FileHandler.getInstance().getLogoFileLocation() + client.getLogo2Url();
	    	
			// Read the InputStream and store it in a 'byteArrayOutputStream'.
	        try
	        {
	            byte[] fileData = logo2File.getFileData();
	
	            File folder = new File(filePath).getParentFile();
	            if (!folder.exists())
	            {
	              // Create any required directories.
	              folder.mkdirs();
	            }
	            FileOutputStream fileOutputStream =  new FileOutputStream(filePath);
	            fileOutputStream.write(fileData);
	            fileOutputStream.close();
	        	
	        }
	        catch(IOException e)
	        {
	            // TODO
	        	System.out.println("IOException - uploading " + logo2Filename);
	        }
        }        
		
		// set the client in the session
		MgrUtilities.setCurrentClient(request, client);
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
	}
    
}
