package com.helmet.application.mgr;

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

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.ReasonForRequest;

public class ReasonForRequestActionDispatcher extends MgrAction {

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
 
    public ActionForward list(ActionMapping mapping, 
    		                  ActionForm form,
    		                  HttpServletRequest request, 
    		                  HttpServletResponse response) {

		DynaValidatorForm dynaForm = (DynaValidatorForm) form;

		logger.entry("In coming !!!");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		List<ReasonForRequest> list = mgrService.getReasonForRequestsForClient(getManagerLoggedIn().getClientId());

		dynaForm.set("list", list);

		logger.exit("Out going !!!");

		return mapping.findForward("success");
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
		
		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
		reasonForRequest.setClientId(getManagerLoggedIn().getClientId());
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.insertReasonForRequest(reasonForRequest, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("reasonForRequest", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
	}
    
    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		ReasonForRequest reasonForRequest = (ReasonForRequest)dynaForm.get("reasonForRequest");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		reasonForRequest = mgrService.getReasonForRequest(reasonForRequest.getReasonForRequestId());
		
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
	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
	
		reasonForRequest = mgrService.getReasonForRequest(reasonForRequest.getReasonForRequestId());
	
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

		reasonForRequest.setClientId(getManagerLoggedIn().getClientId());
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.updateReasonForRequest(reasonForRequest, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("reasonForRequest", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
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

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowCount = mgrService.deleteReasonForRequest(reasonForRequest.getReasonForRequestId(), reasonForRequest.getNoOfChanges(), getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
	}
    
    public ActionForward order(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		List<ReasonForRequest> list = mgrService.getReasonForRequestsForClient(getManagerLoggedIn().getClientId());
		
		dynaForm.set("list", list);
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");

    }
    
    public ActionForward orderProcess(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		String order = (String)dynaForm.get("order");
		Boolean zeroiseDisplayOrder = (Boolean)dynaForm.get("zeroiseDisplayOrder");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowcount = mgrService.updateReasonForRequestDisplayOrder(order, zeroiseDisplayOrder, getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
	}
    
}
