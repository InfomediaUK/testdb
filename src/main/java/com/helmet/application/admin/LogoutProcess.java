package com.helmet.application.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helmet.application.admin.abztract.AdminAction;


public class LogoutProcess extends AdminAction {

    public ActionForward doExecute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
    	
      	AdminUtilities.removeCurrentAdministrator(request);
      	AdminUtilities.removeCurrentAdministratorAdminAccesses(request);
      	
      	return mapping.findForward("success");
    }

}
