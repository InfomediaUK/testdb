package com.helmet.application.agy;

import java.util.List;

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
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.agy.abztract.AgyAction;
import com.helmet.bean.Client;
import com.helmet.bean.ReasonForRequest;

public class ReasonForRequestActionDispatcher extends AgyAction {

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
 
    public ActionForward newX(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

    	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
    	
    	logger.entry("In coming !!!");
    	
    	logger.exit("Out going !!!");
    	
    	return mapping.findForward("success");

    }
    
    public ActionForward newProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");

		ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?client.clientId=" + reasonForRequest.getClientId(),
	    	                         actionForward.getRedirect());
			
		}		
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		try {
			int rowCount = agyService.insertReasonForRequest(reasonForRequest, getConsultantLoggedIn().getConsultantId());
		}  
		catch (DuplicateDataException e) {
			errors.add("reasonForRequest", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?reasonForRequest.reasonForRequestId=" + reasonForRequest.getReasonForRequestId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?client.clientId=" + reasonForRequest.getClientId(),
	    	                         actionForward.getRedirect());
		}		
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		reasonForRequest = agyService.getReasonForRequest(reasonForRequest.getReasonForRequestId());
		
		dynaForm.set("reasonForRequest", reasonForRequest); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

    public ActionForward edit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

	 	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
	
		logger.entry("In coming !!!");
		
		
		ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
	
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
	
		reasonForRequest = agyService.getReasonForRequest(reasonForRequest.getReasonForRequestId());
	
		dynaForm.set("reasonForRequest", reasonForRequest); 
		
		logger.exit("Out going !!!");
		
	 	return mapping.findForward("success");
	}
	
    public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?reasonForRequest.reasonForRequestId=" + reasonForRequest.getReasonForRequestId(),
	    	                         actionForward.getRedirect());
		}		
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		try {
			int rowCount = agyService.updateReasonForRequest(reasonForRequest, getConsultantLoggedIn().getConsultantId());
		}  
		catch (DuplicateDataException e) {
			errors.add("reasonForRequest", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?reasonForRequest.reasonForRequestId=" + reasonForRequest.getReasonForRequestId(),
                                 actionForward.getRedirect());
		
	}
    
    public ActionForward deleteProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?reasonForRequest.reasonForRequestId=" + reasonForRequest.getReasonForRequestId(),
	    	                         actionForward.getRedirect());
		}		

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		int rowCount = agyService.deleteReasonForRequest(reasonForRequest.getReasonForRequestId(), reasonForRequest.getNoOfChanges(), getConsultantLoggedIn().getConsultantId());
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?client.clientId=" + reasonForRequest.getClientId(),
                                 actionForward.getRedirect());
		
	}
    
    public ActionForward order(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");

		Client client = (Client)dynaForm.get("client");

		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		List<ReasonForRequest> list = agyService.getReasonForRequestsForClient(client.getClientId());
		
		dynaForm.set("list", list);
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");

    }
    
    public ActionForward orderProcess(ActionMapping mapping,
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
		
		String order = (String)dynaForm.get("order");
		Boolean zeroiseDisplayOrder = (Boolean)dynaForm.get("zeroiseDisplayOrder");
		
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		
		int rowcount = agyService.updateReasonForRequestDisplayOrder(order, zeroiseDisplayOrder, getConsultantLoggedIn().getConsultantId());
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?client.clientId=" + client.getClientId(),
                                 actionForward.getRedirect());
		
	}

    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
}
