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
import com.helmet.bean.ReEnterPwd;


public class ReEnterPwdView extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	ReEnterPwd reEnterPwd = (ReEnterPwd)dynaForm.get("reEnterPwd");

		AdminService adminService = ServiceFactory.getInstance().getAdminService();

		reEnterPwd = adminService.getReEnterPwd(reEnterPwd.getReEnterPwdId());
    	
		// TODO check not null, maybe service should throw a known exception
        
		dynaForm.set("reEnterPwd", reEnterPwd); 
		
    	logger.exit("Out going !!!");
    	
     	return mapping.findForward("success");
    }

}
