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

import com.helmet.api.AgyService;
import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DuplicateDataException;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.Applicant;
import com.helmet.bean.Manager;
import com.helmet.bean.ManagerEntity;

public class ManagerActionDispatcher extends MgrAction {

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

		List<Manager> list = mgrService.getManagersForClient(getManagerLoggedIn().getClientId());

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
		
		Manager manager = (Manager)dynaForm.get("manager");
		manager.setClientId(getManagerLoggedIn().getClientId());
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.insertManager(manager, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("manager", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
		ActionForward actionForward = mapping.findForward("success");
		return new ActionForward(actionForward.getName(),
								 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
		                         actionForward.getRedirect());
		
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
		
		ManagerEntity manager = (ManagerEntity)dynaForm.get("manager");
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		manager = mgrService.getManagerEntity(manager.getManagerId());
		
		dynaForm.set("manager", manager); 
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
	}

    public ActionForward edit(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

	 	DynaValidatorForm dynaForm = (DynaValidatorForm)form;
	
		logger.entry("In coming !!!");
		
		Manager manager = (Manager)dynaForm.get("manager");
	
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
	
		manager = mgrService.getManager(manager.getManagerId());
	
		dynaForm.set("manager", manager); 
		
		logger.exit("Out going !!!");
		
	 	return mapping.findForward("success");
	}
	
    public ActionForward editProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Manager manager = (Manager)dynaForm.get("manager");

		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
	    	                         actionForward.getRedirect());
		}		
		
		manager.setClientId(getManagerLoggedIn().getClientId());
		
		ActionMessages errors = new ActionMessages();
		
		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		try {
		int rowCount = mgrService.updateManager(manager, getManagerLoggedIn().getManagerId());
		}  
		catch (DuplicateDataException e) {
		errors.add("manager", new ActionMessage("errors.duplicate", messageResources.getMessage("label." + e.getField())));
		saveErrors(request, errors);
		return mapping.getInputForward();
		}
		
		logger.exit("Out going !!!");
		
		ActionForward actionForward = mapping.findForward("success");
		return new ActionForward(actionForward.getName(),
								 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
		                         actionForward.getRedirect());
		
	}
    
    public ActionForward deleteProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Manager manager = (Manager)dynaForm.get("manager");
		
		if (isCancelled(request)){
	    	ActionForward actionForward = mapping.findForward("cancel");
	    	return new ActionForward(actionForward.getName(),
	    							 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
	    	                         actionForward.getRedirect());
		}		

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowCount = mgrService.deleteManager(manager.getManagerId(), manager.getNoOfChanges(), getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
		return mapping.findForward("success");
		
	}
    
    public ActionForward resetSecretWordProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {

		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Manager manager = (Manager)dynaForm.get("manager");
		
		if (isCancelled(request)){
			ActionForward actionForward = mapping.findForward("cancel");
			return new ActionForward(actionForward.getName(),
									 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
			                         actionForward.getRedirect());
		}		
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowCount = mgrService.updateManagerSecretWord(manager.getManagerId(), manager.getUser().getLogin(), manager.getNoOfChanges(), getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
		ActionForward actionForward = mapping.findForward("success");
		return new ActionForward(actionForward.getName(),
								 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
		                         actionForward.getRedirect());
		
	}

    public ActionForward resetPasswordProcess(ActionMapping mapping,
		            ActionForm form,
		            HttpServletRequest request,
		            HttpServletResponse response) {
		
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;
		
		logger.entry("In coming !!!");
		
		Manager manager = (Manager)dynaForm.get("manager");
		
		if (isCancelled(request)){
			ActionForward actionForward = mapping.findForward("cancel");
			return new ActionForward(actionForward.getName(),
									 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
			                         actionForward.getRedirect());
		}		
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
		int rowCount = mgrService.updateManagerPwd(manager.getManagerId(), MgrConstants.RESETPWDHINT, MgrConstants.RESETPWDHINT, manager.getNoOfChanges(), getManagerLoggedIn().getManagerId());
		
		logger.exit("Out going !!!");
		
		ActionForward actionForward = mapping.findForward("success");
		return new ActionForward(actionForward.getName(),
								 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
		                         actionForward.getRedirect());
		
	}
		

}
