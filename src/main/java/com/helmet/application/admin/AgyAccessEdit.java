package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.AgyAccess;


public class AgyAccessEdit extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
     	AgyAccess agyAccess = (AgyAccess)dynaForm.get("agyAccess");

		AdminService adminService = ServiceFactory.getInstance().getAdminService();

		agyAccess = adminService.getAgyAccess(agyAccess.getAgyAccessId());
        
		dynaForm.set("agyAccess", agyAccess); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
