package com.helmet.application.admin;

import java.util.StringTokenizer;

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
import com.helmet.bean.Administrator;
import com.helmet.bean.AdministratorAccessGroup;


public class AdministratorAccessGroupNew extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	Administrator administrator = (Administrator)dynaForm.get("administrator");
       	String[] selectedItems = (String[])dynaForm.get("selectedItems");
		
     	AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
    	for (String str: selectedItems) {
            StringTokenizer st = new StringTokenizer(str, ",");
            AdministratorAccessGroup aag = new AdministratorAccessGroup();
            aag.setAdministratorId(administrator.getAdministratorId());
            aag.setAdminAccessGroupId(new Integer(st.nextToken().trim()));
            int rowCount = adminService.insertAdministratorAccessGroup(aag, getAdministratorLoggedIn().getAdministratorId());
    	}
     	
    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?administrator.administratorId=" + administrator.getAdministratorId(),
    	                         actionForward.getRedirect());
    	
    }

}
