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
import com.helmet.bean.AgencyUser;
import com.helmet.bean.ClientAgency;
import com.helmet.bean.ClientAgencyJobProfileUserEntity;
import com.helmet.bean.ClientUser;
import com.helmet.bean.JobFamily;
import com.helmet.bean.JobProfile;
import com.helmet.bean.JobSubFamily;


public class ClientAgencyJobProfileView extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
     	ClientAgencyJobProfileUserEntity clientAgencyJobProfile = (ClientAgencyJobProfileUserEntity)dynaForm.get("clientAgencyJobProfile");

		AdminService adminService = ServiceFactory.getInstance().getAdminService();

		clientAgencyJobProfile = adminService.getClientAgencyJobProfileUserEntity(clientAgencyJobProfile.getClientAgencyJobProfileId());
    	
		ClientAgency clientAgency = adminService.getClientAgency(clientAgencyJobProfile.getClientAgencyId());
		
		ClientUser client = adminService.getClientUser(clientAgency.getClientId());
		AgencyUser agency = adminService.getAgencyUser(clientAgency.getAgencyId());
		JobProfile jobProfile = adminService.getJobProfile(clientAgencyJobProfile.getJobProfileId());
		JobSubFamily jobSubFamily = adminService.getJobSubFamily(jobProfile.getJobSubFamilyId());
		JobFamily jobFamily = adminService.getJobFamily(jobSubFamily.getJobFamilyId());
		
		dynaForm.set("client", client); 
		dynaForm.set("agency", agency); 
		dynaForm.set("jobProfile", jobProfile); 
		dynaForm.set("jobSubFamily", jobSubFamily); 
		dynaForm.set("jobFamily", jobFamily); 
		
		dynaForm.set("clientAgencyJobProfile", clientAgencyJobProfile); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
