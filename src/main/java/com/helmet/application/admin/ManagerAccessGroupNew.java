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
import com.helmet.bean.Manager;
import com.helmet.bean.ManagerAccessGroup;


public class ManagerAccessGroupNew extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	Manager manager = (Manager)dynaForm.get("manager");
       	String[] selectedItems = (String[])dynaForm.get("selectedItems");
		
     	AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
    	for (String str: selectedItems) {
            StringTokenizer st = new StringTokenizer(str, ",");
            ManagerAccessGroup aag = new ManagerAccessGroup();
            aag.setManagerId(manager.getManagerId());
            aag.setMgrAccessGroupId(new Integer(st.nextToken().trim()));
            int rowCount = adminService.insertManagerAccessGroup(aag, getAdministratorLoggedIn().getAdministratorId());
    	}
     	
    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?manager.managerId=" + manager.getManagerId(),
    	                         actionForward.getRedirect());
    	
    }

}
