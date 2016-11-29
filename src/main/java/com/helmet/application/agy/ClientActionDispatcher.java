package com.helmet.application.agy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.FileHandler;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Client;
import com.helmet.bean.ClientAgency;
import com.helmet.bean.ClientAgencyUser;
import com.helmet.bean.ClientUser;
import com.helmet.bean.PublicHoliday;
import com.helmet.bean.ReasonForRequest;
import com.helmet.bean.SiteUser;
import com.helmet.bean.Uplift;

public class ClientActionDispatcher extends AgyAction {

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
 
  public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {

    HttpSession session = request.getSession();
    String clientIndexLetter = (String)session.getAttribute("clientIndexLetter");
    clientIndexLetter = clientIndexLetter == null ? "A" : clientIndexLetter;
    String indexLetter = request.getParameter("indexLetter") == null ? clientIndexLetter : request.getParameter("indexLetter");
    session.setAttribute("clientIndexLetter", indexLetter);
    DynaValidatorForm dynaForm = (DynaValidatorForm) form;
    logger.entry("In coming !!!");
    AgyService agyService = ServiceFactory.getInstance().getAgyService();
    List<ClientAgencyUser> list = agyService.getClientAgencyUsersForAgencyInNameGroup(getConsultantLoggedIn().getAgencyId(), indexLetter);
    dynaForm.set("list", list);
    logger.exit("Out going !!!");
    return mapping.findForward("success");
  }

    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		ClientUser client = (ClientUser)dynaForm.get("client");
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		// BEFORE - getting client check the clientAgency relationship exists - otherwise - illegalAccess!
		
		ClientAgency clientAgency = agyService.getClientAgencyForClientAndAgency(client.getClientId(), getConsultantLoggedIn().getAgencyId());
		
		if (clientAgency == null) {
	     	return mapping.findForward("illegalaccess");
		}
			
		client = agyService.getClientUser(client.getClientId());
		
		List<SiteUser> sites = agyService.getSiteUsersForClient(client.getClientId());
		List<PublicHoliday> publicHolidays = agyService.getPublicHolidaysForClient(client.getClientId());
		List<ReasonForRequest> reasonForRequests = agyService.getReasonForRequestsForClient(client.getClientId());
		List<Uplift> uplifts = agyService.getUpliftsForClient(client.getClientId());
		
		dynaForm.set("client", client); 
		dynaForm.set("sites", sites); 
		dynaForm.set("publicHolidays", publicHolidays); 
		dynaForm.set("reasonForRequests", reasonForRequests); 
		dynaForm.set("uplifts", uplifts); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

	public ActionForward edit(ActionMapping mapping,
				    ActionForm form,
				    HttpServletRequest request,
				    HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		ClientUser client = (ClientUser)dynaForm.get("client");
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		// BEFORE - getting client check the clientAgency relationship exists - otherwise - illegalAccess!
		
		ClientAgency clientAgency = agyService.getClientAgencyForClientAndAgency(client.getClientId(), getConsultantLoggedIn().getAgencyId());
		
		if (clientAgency == null) {
	     	return mapping.findForward("illegalaccess");
		}
			
		client = agyService.getClientUser(client.getClientId());
		
		dynaForm.set("client", client); 
		
		logger.exit("Out going !!!");
	
		return mapping.findForward("success");
	}

	public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Client client = (Client)dynaForm.get("client");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?client.clientId=" + client.getClientId(),
	    	                         actionForward.getRedirect());
		}		

		AgyService agyService = ServiceFactory.getInstance().getAgyService();

		// BEFORE - getting client check the clientAgency relationship exists - otherwise - illegalAccess!
		
		ClientAgency clientAgency = agyService.getClientAgencyForClientAndAgency(client.getClientId(), getConsultantLoggedIn().getAgencyId());
		
		if (clientAgency == null) {
	     	return mapping.findForward("illegalaccess");
		}
		
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
		
		try {
		int rowCount = agyService.updateClient(client, getConsultantLoggedIn().getConsultantId());
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
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?client.clientId=" + client.getClientId(),
    	                         actionForward.getRedirect());
	
	}

}
