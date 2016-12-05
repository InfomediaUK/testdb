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
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobFamily;


public class JobFamilyDelete extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
     	JobFamily jobFamily = (JobFamily)dynaForm.get("jobFamily");

		AdminService adminService = ServiceFactory.getInstance().getAdminService();

		jobFamily = adminService.getJobFamily(jobFamily.getJobFamilyId());
    	
		ClientUser client = adminService.getClientUser(jobFamily.getClientId());
    	
		dynaForm.set("client", client); 
		dynaForm.set("jobFamily", jobFamily);    	

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
