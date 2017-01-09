package com.helmet.application.mgr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.application.Utilities;
import com.helmet.bean.Client;
import com.helmet.bean.ClientReEnterPwdUser;
import com.helmet.bean.Manager;
import com.helmet.bean.MgrAccess;


public class LoginProcess extends Action {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
        ActionForward af = validateLogin(mapping, form, request);
		
        if (af != null) {
        	return af;
        }
        
        return Utilities.whereToNow(mapping, request);
        
    }

    public ActionForward validateLogin(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request) {
    	
		DynaValidatorForm dynaForm = (DynaValidatorForm)form;

      	Manager manager = (Manager)dynaForm.get("manager");
      	String clientCode = (String)dynaForm.get("clientCode");
      	
		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);
		
		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

		Client client = null;
		
      	try {
      		client = mgrService.getClientForCode(clientCode);
      	}
      	catch (DataNotFoundException e) {
            errors.add("login", new ActionMessage("errors.invalidCaseSensitive", messageResources.getMessage("label.clientCode")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
		
		// Set the managers clientId
		manager.setClientId(client.getClientId());
		
      	try {
      		manager = mgrService.validateLogin(manager);
      	}
      	catch (DataNotFoundException e) {
            errors.add("login", new ActionMessage("errors.invalidCaseSensitive", messageResources.getMessage("label.login")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	catch (InvalidDetailException e) {
           	errors.add("login", new ActionMessage("errors.invalidCaseSensitive", messageResources.getMessage("label.pwd")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}

      	List<MgrAccess> mgrAccesses = mgrService.getActiveMgrAccessesForManager(manager.getManagerId());
      	
      	MgrUtilities.setCurrentManager(request, manager);
      	MgrUtilities.setCurrentManagerMgrAccesses(request, mgrAccesses);

      	MgrUtilities.setCurrentClient(request, client);
      	
      	
      	List<ClientReEnterPwdUser> clientReEnterPwdUsers = mgrService.getClientReEnterPwdUsersForClient(client.getClientId());
      	
      	MgrUtilities.setCurrentManagerClientReEnterPwdUsers(request, clientReEnterPwdUsers);

      	// re-initialize the form
    	dynaForm.initialize(mapping);

      	return null;

    }

}
