package com.helmet.application.mgr;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.ActionDispatcher;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.Manager;
import com.helmet.bean.ManagerAccessGroup;

public class ManagerAccessGroupActionDispatcher extends MgrAction {

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
 
    public ActionForward addProcess(ActionMapping mapping,
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
		
    	String[] selectedItems = (String[])dynaForm.get("selectedItems");
		
     	MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
    	for (String str: selectedItems) {
            StringTokenizer st = new StringTokenizer(str, ",");
            ManagerAccessGroup mag = new ManagerAccessGroup();
            mag.setManagerId(manager.getManagerId());
            mag.setMgrAccessGroupId(new Integer(st.nextToken().trim()));
            int rowCount = mgrService.insertManagerAccessGroup(mag, getManagerLoggedIn().getManagerId());
    	}

		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
    	                         actionForward.getRedirect());
		
	}
    
    public ActionForward removeProcess(ActionMapping mapping,
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

    	String[] selectedItems = (String[])dynaForm.get("selectedItems");

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();
		
    	for (String str: selectedItems) {
            StringTokenizer st = new StringTokenizer(str, ",");
            Integer managerAccessGroupId = new Integer(st.nextToken().trim());
            Integer noOfChanges = new Integer(st.nextToken().trim());
    		int rowCount = mgrService.deleteManagerAccessGroup(managerAccessGroupId, noOfChanges, getManagerLoggedIn().getManagerId());
    	}
		
		logger.exit("Out going !!!");
		
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
    	                         actionForward.getRedirect());
		
	}
    
}
