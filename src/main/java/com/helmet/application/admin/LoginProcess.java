package com.helmet.application.admin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorForm;

import com.helmet.api.AdminService;
import com.helmet.api.ServiceFactory;
import com.helmet.api.exceptions.DataNotFoundException;
import com.helmet.api.exceptions.InvalidDetailException;
import com.helmet.application.Constants;
import com.helmet.bean.AdminAccess;
import com.helmet.bean.Administrator;


public class LoginProcess extends Action {

    protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

		if (isCancelled(request)){
			return mapping.findForward("cancel");
		}		
		
      	DynaValidatorForm dynaForm = (DynaValidatorForm)form;

      	Administrator administrator = (Administrator)dynaForm.get("administrator");

		ActionMessages errors = new ActionMessages();

		MessageResources messageResources = getResources(request);
		
		AdminService adminService = ServiceFactory.getInstance().getAdminService();
		
      	try {
      		administrator = adminService.validateLogin(administrator);
      	}
      	catch (DataNotFoundException e) {
            errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.login")));
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}
      	catch (InvalidDetailException e) {
            if (administrator.getUser().getPwdHint() != null && !"".equals(administrator.getUser().getPwdHint())) {
                // show pwdHint
            	errors.add("login", new ActionMessage("errors.invalidPwd", administrator.getUser().getPwdHint()));
            }
            else {
                // no pwdHint
            	errors.add("login", new ActionMessage("errors.invalid", messageResources.getMessage("label.pwd")));
            }
            saveErrors(request, errors);
    		return mapping.getInputForward();
      	}

      	List<AdminAccess> adminAccesses = adminService.getActiveAdminAccessesForAdministrator(administrator.getAdministratorId());
      	
      	AdminUtilities.setCurrentAdministrator(request, administrator);
      	AdminUtilities.setCurrentAdministratorAdminAccesses(request, adminAccesses);
      	
        // Go to where we were trying to go before we got sent to login page
        HttpSession session = request.getSession();
        String requestedURL = (String)session.getAttribute(Constants.REQUESTED_URL);

        if (requestedURL == null) {
            // If they went straight to the login page
            return mapping.findForward("success");
        }
        else {
	        session.removeAttribute(Constants.REQUESTED_URL);

            // Get the servlet path which will be /webapp/rest of path
            URL url = null;
			try {
				url = new URL(requestedURL);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String redirectUrlServletPath = url.getPath();
            String query = url.getQuery();

            // Remove the /webapp/
            String regexp = request.getContextPath();
            redirectUrlServletPath = redirectUrlServletPath.replaceFirst(regexp, "");

            // Append the query
            if (query != null && query.length() > 0) {
                redirectUrlServletPath += "?";
                redirectUrlServletPath += query;
            }

//            ActionForward forward = new ActionForward("loginRedirect", redirectUrlServletPath, true, true);
            ActionForward forward = new ActionForward("loginRedirect", redirectUrlServletPath, true);
            return forward;
        }
      	
    }

}
