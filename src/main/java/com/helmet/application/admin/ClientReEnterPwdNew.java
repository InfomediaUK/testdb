package com.helmet.application.admin;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.application.admin.abztract.AdminAction;
import com.helmet.bean.Client;
import com.helmet.bean.ClientReEnterPwd;


public class ClientReEnterPwdNew extends AdminAction {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
     	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

    	logger.entry("In coming !!!");
    	
    	Client client = (Client)dynaForm.get("client");
       	String[] selectedItems = (String[])dynaForm.get("selectedItems");

		ActionMessages errors = new ActionMessages();

		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
    	for (String str: selectedItems) {
            StringTokenizer st = new StringTokenizer(str, ",");
            ClientReEnterPwd cr = new ClientReEnterPwd();
            cr.setClientId(client.getClientId());
            cr.setReEnterPwdId(new Integer(st.nextToken().trim()));
            int rowCount = adminService.insertClientReEnterPwd(cr, getAdministratorLoggedIn().getAdministratorId());
    	}
     	     	
    	logger.exit("Out going !!!");
    	
    	ActionForward actionForward = mapping.findForward("success");
    	
    	return new ActionForward(actionForward.getName(),
    							 actionForward.getPath() + "?client.clientId=" + client.getClientId(),
    	                         actionForward.getRedirect());
    	
    }

}
