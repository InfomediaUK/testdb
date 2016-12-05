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
import com.helmet.bean.ClientAgencyGrade;


public class ClientAgencyGradeView extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
     	ClientAgencyGrade clientAgencyGrade = (ClientAgencyGrade)dynaForm.get("clientAgencyGrade");

		AdminService adminService = ServiceFactory.getInstance().getAdminService();

		clientAgencyGrade = adminService.getClientAgencyGrade(clientAgencyGrade.getClientAgencyGradeId());
    	
		dynaForm.set("clientAgencyGrade", clientAgencyGrade); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
