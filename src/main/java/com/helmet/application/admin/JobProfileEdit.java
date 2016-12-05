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
import com.helmet.bean.JobProfile;
import com.helmet.bean.JobSubFamily;


public class JobProfileEdit extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");

     	JobProfile jobProfile = (JobProfile)dynaForm.get("jobProfile");

		AdminService adminService = ServiceFactory.getInstance().getAdminService();

		jobProfile = adminService.getJobProfile(jobProfile.getJobProfileId());
		
		JobSubFamily jobSubFamily = adminService.getJobSubFamily(jobProfile.getJobSubFamilyId());
		JobFamily jobFamily = adminService.getJobFamily(jobSubFamily.getJobFamilyId());
		ClientUser client = adminService.getClientUser(jobFamily.getClientId());
    	
		dynaForm.set("client", client); 
		dynaForm.set("jobFamily", jobFamily); 
		dynaForm.set("jobSubFamily", jobSubFamily); 
		dynaForm.set("jobProfile", jobProfile); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
