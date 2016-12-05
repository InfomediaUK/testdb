package com.helmet.application.admin;

import java.util.List;

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


public class JobProfileOrder extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
		JobSubFamily jobSubFamily = (JobSubFamily)dynaForm.get("jobSubFamily");
    	
    	AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
    	jobSubFamily = adminService.getJobSubFamily(jobSubFamily.getJobSubFamilyId());
    	JobFamily jobFamily = adminService.getJobFamily(jobSubFamily.getJobFamilyId());
    	ClientUser client = adminService.getClientUser(jobFamily.getClientId());
		List<JobProfile> list = adminService.getJobProfilesForJobSubFamily(jobSubFamily.getJobSubFamilyId(), getShowOnlyActive());
     	
     	dynaForm.set("client", client);
     	dynaForm.set("jobFamily", jobFamily);
     	dynaForm.set("jobSubFamily", jobSubFamily);
     	dynaForm.set("list", list);

    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
