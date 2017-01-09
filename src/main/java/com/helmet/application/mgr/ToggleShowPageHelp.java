package com.helmet.application.mgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.MgrService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.mgr.abztract.MgrAction;
import com.helmet.bean.Manager;


public class ToggleShowPageHelp extends MgrAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
		Manager manager = getManagerLoggedIn();

		MgrService mgrService = ServiceFactory.getInstance().getMgrService();

      // Get the lastest version of the manager - noOfChanges the main reason behind this
		manager = mgrService.getManager(manager.getManagerId());
		manager.getUser().setShowPageHelp(!manager.getUser().getShowPageHelp());

		int rc = mgrService.updateManagerShowPageHelp(manager, manager.getManagerId());
		
		MgrUtilities.setCurrentManager(request, manager);
		
		logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
