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
import com.helmet.bean.DressCode;
import com.helmet.bean.Location;

public class DressCodeActionDispatcher extends MgrAction {

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
		
		DressCode dressCode = (DressCode)dynaForm.get("dressCode");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + dressCode.getLocationId(),
	    	                         actionForward.getRedirect());
			
		}		
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.insertDressCode(dressCode, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("dressCode", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + dressCode.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward view(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		DressCode dressCode = (DressCode)dynaForm.get("dressCode");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + dressCode.getLocationId(),
	    	                         actionForward.getRedirect());
		}		
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		dressCode = mgrService.getDressCode(dressCode.getDressCodeId());
		
		dynaForm.set("dressCode", dressCode); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

    public ActionForward edit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

	 	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
	
		logger.entry("In coming !!!");
		
		DressCode dressCode = (DressCode)dynaForm.get("dressCode");
	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
	
		dressCode = mgrService.getDressCode(dressCode.getDressCodeId());
	
		dynaForm.set("dressCode", dressCode); 
		
		logger.exit("Out going !!!");
		
	 	return mapping.findForward("success");
	}
	
    public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		DressCode dressCode = (DressCode)dynaForm.get("dressCode");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?dressCode.dressCodeId=" + dressCode.getDressCodeId(),
	    	                         actionForward.getRedirect());
		}		
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.updateDressCode(dressCode, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("dressCode", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + dressCode.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward deleteProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		DressCode dressCode = (DressCode)dynaForm.get("dressCode");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?dressCode.dressCodeId=" + dressCode.getDressCodeId(),
	    	                         actionForward.getRedirect());
		}		

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowCount = mgrService.deleteDressCode(dressCode.getDressCodeId(), dressCode.getNoOfChanges(), getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + dressCode.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward order(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Location location = (Location)dynaForm.get("location");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		List<DressCode> list = mgrService.getDressCodesForLocation(location.getLocationId());
		
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
		
		Location location = (Location)dynaForm.get("location");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?location.locationId=" + location.getLocationId(),
	    	                         actionForward.getRedirect());
			
		}
		
		String order = (String)dynaForm.get("order");
		Boolean zeroiseDisplayOrder = (Boolean)dynaForm.get("zeroiseDisplayOrder");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowcount = mgrService.updateDressCodeDisplayOrder(order, zeroiseDisplayOrder, getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?location.locationId=" + location.getLocationId(),
    	                         actionForward.getRedirect());
		
	}
    
}
